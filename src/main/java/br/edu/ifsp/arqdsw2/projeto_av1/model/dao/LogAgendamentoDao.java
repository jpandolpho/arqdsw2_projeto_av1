package br.edu.ifsp.arqdsw2.projeto_av1.model.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.edu.ifsp.arqdsw2.projeto_av1.model.dao.connection.DatabaseConnection;
import br.edu.ifsp.arqdsw2.projeto_av1.model.entity.LogAgendamento;
import br.edu.ifsp.arqdsw2.projeto_av1.model.enums.Status;

public class LogAgendamentoDao {
	private static final String SELECT_BY_ID = "SELECT status,data_hora FROM logagendamento WHERE agendamento_id=?";
	
	public List<LogAgendamento> retrieve(int id){
		List<LogAgendamento> logs = new ArrayList<>();
		try(var connection = DatabaseConnection.getConnection();
				var stmt = connection.prepareStatement(SELECT_BY_ID)){
			
			stmt.setInt(1, id);
			
			var resultSet = stmt.executeQuery();
			while(resultSet.next()) {
				LogAgendamento log = new LogAgendamento();
				
				String status = resultSet.getString("status");
				if(status.equals("solicitado")) {
					log.setEstado(Status.SOLICITADO);
				}else if(status.equals("aceito")) {
					log.setEstado(Status.ACEITO);
				}else if(status.equals("concluido")) {
					log.setEstado(Status.CONCLUIDO);
				}else {
					log.setEstado(Status.REJEITADO);
				}
				log.setHoraMudanca(resultSet.getDate("data_hora"));
				
				logs.add(log);
			}
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
		
		return logs;
	}
}
