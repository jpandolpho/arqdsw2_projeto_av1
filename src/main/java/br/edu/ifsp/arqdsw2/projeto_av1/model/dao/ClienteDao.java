package br.edu.ifsp.arqdsw2.projeto_av1.model.dao;

import java.sql.SQLException;

import br.edu.ifsp.arqdsw2.projeto_av1.model.dao.connection.DatabaseConnection;
import br.edu.ifsp.arqdsw2.projeto_av1.model.entity.Cliente;

public class ClienteDao {
	private static final String INSERT = "INSERT INTO cliente(nome,cpf,endereco,contato,email,senha_hash,cidade_id) VALUES (?,?,?,?,?,?,?)";
	
	public boolean insert(Cliente c, String cidade) {
		int rows = 0;
		if(c!=null) {
			try(var connection = DatabaseConnection.getConnection();
					var stmt = connection.prepareStatement(INSERT)){
				stmt.setString(1, c.getNome());
				stmt.setString(2, c.getCpf());
				stmt.setString(3, c.getEndereco());
				stmt.setString(4, c.getContato());
				stmt.setString(5, c.getEmail());
				stmt.setString(6, c.getSenha());
				CidadeDao dao = new CidadeDao();
				int id = dao.fetchByNome(cidade);
				stmt.setInt(7, id);
				
				rows = stmt.executeUpdate();
			}catch(SQLException e) {
				e.printStackTrace();
			}
		}
		return rows > 0;
	}
}
