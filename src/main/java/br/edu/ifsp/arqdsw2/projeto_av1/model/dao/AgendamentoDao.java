package br.edu.ifsp.arqdsw2.projeto_av1.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import br.edu.ifsp.arqdsw2.projeto_av1.model.dao.connection.DatabaseConnection;
import br.edu.ifsp.arqdsw2.projeto_av1.model.entity.Agendamento;
import br.edu.ifsp.arqdsw2.projeto_av1.model.entity.LogAgendamento;
import br.edu.ifsp.arqdsw2.projeto_av1.model.enums.Status;

public class AgendamentoDao {
	private static final String SELECT_PAGED = "SELECT a.id, dia_mes, hora_inicio, hora_fim, status, criado_em FROM agendamento a "+
			"INNER JOIN disponibilidade d ON a.disponibilidade_id=d.id WHERE a.prestador_id=? ORDER BY criado_em DESC LIMIT ? OFFSET ?";
	private static final String SELECT_FILTERED_PAGED = "SELECT a.id, dia_mes, hora_inicio, hora_fim, status, criado_em FROM agendamento a "+
			"INNER JOIN disponibilidade d ON a.disponibilidade_id=d.id WHERE a.prestador_id=? AND status=? ORDER BY criado_em DESC LIMIT ? OFFSET ?";
	private static final String SELECT_COUNT = "SELECT COUNT(prestador_id) AS total_registros FROM agendamento WHERE prestador_id=? GROUP BY prestador_id";
	private static final String SELECT_FILTERED_COUNT = "SELECT COUNT(prestador_id) AS total_registros FROM agendamento WHERE prestador_id=? AND status=? GROUP BY prestador_id";
	private static final String UPDATE = "UPDATE agendamento SET status=? WHERE id=?";
	
	private final int AGENDAMENTOS_POR_PAGINA = 20;
	
	public List<Agendamento> retrieve(int id, int page, String filtro){
		List<Agendamento> agendamentos = new ArrayList<>();
		try(var connection = DatabaseConnection.getConnection()){
			PreparedStatement stmt;
			if(filtro.isBlank()) {
				stmt = connection.prepareStatement(SELECT_PAGED);
				stmt.setInt(2, AGENDAMENTOS_POR_PAGINA);
				stmt.setInt(3, AGENDAMENTOS_POR_PAGINA*page);
			}else {
				stmt = connection.prepareStatement(SELECT_FILTERED_PAGED);
				stmt.setString(2, filtro);
				stmt.setInt(3, AGENDAMENTOS_POR_PAGINA);
				stmt.setInt(4, AGENDAMENTOS_POR_PAGINA*page);
			}
			stmt.setInt(1, id);
			
			var resultSet = stmt.executeQuery();
			
			while(resultSet.next()) {
				Agendamento a = new Agendamento();
				a.setCriacao(resultSet.getDate("criado_em"));
				a.setDiaMes(resultSet.getDate("dia_mes"));
				a.setHoraInicio(resultSet.getTime("hora_inicio"));
				a.setHoraFim(resultSet.getTime("hora_fim"));
				a.setId(resultSet.getInt("id"));
				
				String status = resultSet.getString("status");
				if(status.equals("solicitado")) {
					a.setEstado(Status.SOLICITADO);
				}else if(status.equals("aceito")) {
					a.setEstado(Status.ACEITO);
				}else if(status.equals("concluido")) {
					a.setEstado(Status.CONCLUIDO);
				}else {
					a.setEstado(Status.REJEITADO);
				}
				
				LogAgendamentoDao logDao = new LogAgendamentoDao();
				a.addAllMudanças(logDao.retrieve(a.getId()));
				
				agendamentos.add(a);
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		
		return agendamentos;
	}

	public int retrieve(int id, String filtro) {
		int res = 0;
		try(var connection = DatabaseConnection.getConnection()){
			PreparedStatement stmt;
			if(filtro.isBlank()) {
				stmt = connection.prepareStatement(SELECT_COUNT);
			}else {
				stmt = connection.prepareStatement(SELECT_FILTERED_COUNT);
				stmt.setString(2, filtro);
			}
			stmt.setInt(1, id);
			
			var resultSet = stmt.executeQuery();
			if(resultSet.next()) {
				res = resultSet.getInt("total_registros");
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return res/AGENDAMENTOS_POR_PAGINA;
	}
	
	public boolean update(String status, int id) {
		int rows = 0;
		try(var connection = DatabaseConnection.getConnection();
				var stmt = connection.prepareStatement(UPDATE)){
			stmt.setString(1,status);
			stmt.setInt(2,id);
			
			rows = stmt.executeUpdate();
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return rows > 0;
	}
	
	public boolean existeConflito(int disponibilidadeId, Date diaMes, Time horaInicio, Time horaFim) throws SQLException {
	    String sql = "SELECT COUNT(*) FROM Agendamento WHERE disponibilidade_id = ? AND dia_mes = ? AND " +
	                 "((hora_inicio < ? AND hora_fim > ?) OR (hora_inicio < ? AND hora_fim > ?) OR (hora_inicio >= ? AND hora_fim <= ?)) " +
	                 "AND estado IN ('SOLICITADO', 'CONFIRMADO')"; 
	    
	    try (Connection conn = DatabaseConnection.getConnection();
	         PreparedStatement stmt = conn.prepareStatement(sql)) {
	        stmt.setInt(1, disponibilidadeId);
	        stmt.setDate(2, new java.sql.Date(diaMes.getTime()));
	        stmt.setTime(3, horaFim);
	        stmt.setTime(4, horaInicio);
	        stmt.setTime(5, horaFim);
	        stmt.setTime(6, horaInicio);
	        stmt.setTime(7, horaInicio);
	        stmt.setTime(8, horaFim);

	        try (ResultSet rs = stmt.executeQuery()) {
	            if (rs.next()) {
	                return rs.getInt(1) > 0;
	            }
	        }
	    }
	    return false;
	}

	
	public void createWithTransaction(Agendamento agendamento) throws SQLException {
	    Connection conn = null;
	    PreparedStatement stmtAgendamento = null;
	    PreparedStatement stmtLog = null;
	    ResultSet rs = null;

	    try {
	        conn = DatabaseConnection.getConnection();
	        conn.setAutoCommit(false);

	        String sqlCheck = """
	            SELECT COUNT(*) FROM Agendamento
	            WHERE prestador_id = ? AND dia_mes = ? AND (
	                (hora_inicio < ? AND hora_fim > ?) OR
	                (hora_inicio < ? AND hora_fim > ?) OR
	                (hora_inicio >= ? AND hora_fim <= ?)
	            )
	        """;

	        try (PreparedStatement stmtCheck = conn.prepareStatement(sqlCheck)) {
	            stmtCheck.setInt(1, agendamento.getPrestadorId());
	            stmtCheck.setDate(2, new java.sql.Date(agendamento.getDiaMes().getTime()));
	            stmtCheck.setTime(3, agendamento.getHoraFim());
	            stmtCheck.setTime(4, agendamento.getHoraFim());
	            stmtCheck.setTime(5, agendamento.getHoraInicio());
	            stmtCheck.setTime(6, agendamento.getHoraInicio());
	            stmtCheck.setTime(7, agendamento.getHoraInicio());
	            stmtCheck.setTime(8, agendamento.getHoraFim());

	            rs = stmtCheck.executeQuery();
	            if (rs.next() && rs.getInt(1) > 0) {
	                conn.rollback();
	                throw new SQLException("Conflito de agendamento detectado.");
	            }
	        }

	        String sqlInsert = """
	            INSERT INTO Agendamento (cliente_id, prestador_id, disponibilidade_id, estado, criacao, dia_mes, hora_inicio, hora_fim)
	            VALUES (?, ?, ?, ?, ?, ?, ?, ?)
	        """;

	        stmtAgendamento = conn.prepareStatement(sqlInsert, Statement.RETURN_GENERATED_KEYS);
	        stmtAgendamento.setInt(1, agendamento.getClienteId());
	        stmtAgendamento.setInt(2, agendamento.getPrestadorId());
	        stmtAgendamento.setInt(3, agendamento.getDisponibilidadeId());
	        stmtAgendamento.setString(4, agendamento.getEstado().name());
	        stmtAgendamento.setTimestamp(5, new Timestamp(agendamento.getCriacao().getTime()));
	        stmtAgendamento.setDate(6, new java.sql.Date(agendamento.getDiaMes().getTime()));
	        stmtAgendamento.setTime(7, agendamento.getHoraInicio());
	        stmtAgendamento.setTime(8, agendamento.getHoraFim());

	        int affectedRows = stmtAgendamento.executeUpdate();
	        if (affectedRows == 0) {
	            conn.rollback();
	            throw new SQLException("Falha ao inserir agendamento.");
	        }

	        rs = stmtAgendamento.getGeneratedKeys();
	        if (rs.next()) {
	            agendamento.setId(rs.getInt(1));
	        }

	        String sqlLog = """
	            INSERT INTO LogAgendamento (agendamento_id, estado, data_hora)
	            VALUES (?, ?, ?)
	        """;
	        stmtLog = conn.prepareStatement(sqlLog);
	        for (LogAgendamento log : agendamento.getLog()) {
	            stmtLog.setInt(1, agendamento.getId());
	            stmtLog.setString(2, log.getEstado().name());
	            stmtLog.setTimestamp(3, new Timestamp(log.getHoraMudanca().getTime()));
	            stmtLog.executeUpdate();
	        }

	        conn.commit();
	    } catch (SQLException e) {
	        if (conn != null) {
	            conn.rollback();
	        }
	        throw e;
	    } finally {
	        if (rs != null) rs.close();
	        if (stmtLog != null) stmtLog.close();
	        if (stmtAgendamento != null) stmtAgendamento.close();
	        if (conn != null) conn.setAutoCommit(true);
	        if (conn != null) conn.close();
	    }
	}
}
