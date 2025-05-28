package br.edu.ifsp.arqdsw2.projeto_av1.model.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.edu.ifsp.arqdsw2.projeto_av1.model.dao.connection.DatabaseConnection;
import br.edu.ifsp.arqdsw2.projeto_av1.model.entity.Cidade;

public class CidadeDao {
	private static final String SELECT_ALL = "SELECT nome FROM cidade";
	private static final String SELECT_BY_NOME = "SELECT id FROM cidade WHERE nome=?";
	
	public List<Cidade> fetchAll(){
		List<Cidade> cidades = new ArrayList<>();
		try(var connection = DatabaseConnection.getConnection();
				var stmt = connection.prepareStatement(SELECT_ALL)){
			var result = stmt.executeQuery();
			
			while(result.next()) {
				Cidade cidade = new Cidade();
				cidade.setNome(result.getString("nome"));
				
				cidades.add(cidade);
			}
		}catch(SQLException e) {
			e.printStackTrace(); 
		}
		return cidades;
	}
	
	public int fetchByNome(String nome) {
		int id = -1;
		try(var connection = DatabaseConnection.getConnection();
				var stmt = connection.prepareStatement(SELECT_BY_NOME)){
			stmt.setString(1, nome);
			var resultSet = stmt.executeQuery();
			if(resultSet.next()) {
				id = resultSet.getInt("id");
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return id;
	}
}
