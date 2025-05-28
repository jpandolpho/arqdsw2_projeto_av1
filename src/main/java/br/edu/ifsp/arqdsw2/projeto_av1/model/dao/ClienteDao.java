package br.edu.ifsp.arqdsw2.projeto_av1.model.dao;

import java.sql.SQLException;

import br.edu.ifsp.arqdsw2.projeto_av1.model.dao.connection.DatabaseConnection;
import br.edu.ifsp.arqdsw2.projeto_av1.model.entity.Cliente;

public class ClienteDao {
	private static final String INSERT = "INSERT INTO cliente(nome,cpf,endereco,contato,email,senha_hash,cidade_id) VALUES (?,?,?,?,?,?,?)";
	private static final String SELECT_BY_EMAIL = "SELECT * FROM cliente WHERE email=?";
	
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
	
	public Cliente findByEmail(String email) {
        Cliente c = null;
        try (var connection = DatabaseConnection.getConnection();
             var stmt = connection.prepareStatement(SELECT_BY_EMAIL)) {
            stmt.setString(1, email);

            var resultSet = stmt.executeQuery();
            if (resultSet.next()) {
                c = new Cliente(
                    resultSet.getString("cpf"),
                    resultSet.getString("contato"),
                    resultSet.getString("nome"),
                    resultSet.getString("endereco"),
                    resultSet.getString("email"),
                    resultSet.getString("senha_hash"),
                    true
                );
                c.setId(resultSet.getInt("id"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return c;
    }}
