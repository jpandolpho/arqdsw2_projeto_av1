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
	
	public boolean existeConflito(int disponibilidadeId) throws SQLException {
	    String sql = "SELECT COUNT(*) FROM Agendamento WHERE disponibilidade_id = ?";
	    try (Connection conn = DatabaseConnection.getConnection();
	         PreparedStatement stmt = conn.prepareStatement(sql)) {
	        stmt.setInt(1, disponibilidadeId);
	        try (ResultSet rs = stmt.executeQuery()) {
	            return rs.next() && rs.getInt(1) > 0;
	        }
	    }
	}


	
	public boolean createWithTransaction(Agendamento agendamento) throws SQLException {
	    String checkSql = "SELECT COUNT(*) FROM Agendamento WHERE disponibilidade_id = ?";
	    String insertAg = """
	        INSERT INTO Agendamento
	          (cliente_id, prestador_id, disponibilidade_id, status, criado_em)
	        VALUES (?, ?, ?, ?, CURRENT_TIMESTAMP)
	    """;
	    String insertLog = """
	        INSERT INTO LogAgendamento
	          (agendamento_id, status, data_hora)
	        VALUES (?, ?, CURRENT_TIMESTAMP)
	    """;

	    try (Connection conn = DatabaseConnection.getConnection()) {
	        conn.setAutoCommit(false);
	        try (PreparedStatement st = conn.prepareStatement(checkSql)) {
	            st.setInt(1, agendamento.getDisponibilidadeId());
	            try (ResultSet rs = st.executeQuery()) {
	                if (rs.next() && rs.getInt(1) > 0) {
	                    conn.rollback();
	                    return false;  
	                }
	            }
	        }

	        int agId;
	        try (PreparedStatement st = conn.prepareStatement(insertAg, Statement.RETURN_GENERATED_KEYS)) {
	            st.setInt(1, agendamento.getClienteId());
	            st.setInt(2, agendamento.getPrestadorId());
	            st.setInt(3, agendamento.getDisponibilidadeId());
	            st.setString(4, agendamento.getEstado().name().toLowerCase());
	            st.executeUpdate();
	            try (ResultSet keys = st.getGeneratedKeys()) {
	                if (!keys.next()) throw new SQLException("Não gerou ID de agendamento");
	                agId = keys.getInt(1);
	            }
	        }

	        try (PreparedStatement st = conn.prepareStatement(insertLog)) {
	            st.setInt(1, agId);
	            st.setString(2, agendamento.getEstado().name().toLowerCase());
	            st.executeUpdate();
	        }
	        conn.commit();
	        return true;
	    } catch (SQLException e) {
	        e.printStackTrace();
	        throw e;
	    }
	}

}
