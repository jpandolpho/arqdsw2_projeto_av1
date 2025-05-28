package br.edu.ifsp.arqdsw2.projeto_av1.model.dao;

import java.sql.SQLException;
import java.util.List;
import java.util.ArrayList;

import br.edu.ifsp.arqdsw2.projeto_av1.model.dao.connection.DatabaseConnection;
import br.edu.ifsp.arqdsw2.projeto_av1.model.entity.Prestador;

public class PrestadorDao {
	private static final String INSERT = "INSERT INTO Prestador(nome_fantasia,nome_completo,foto_perfil,especialidade,endereco,descricao,email,senha_hash,cidade_id)"+
			"VALUES (?,?,?,?,?,?,?,?,?)";
	private static final String SELECT_BY_EMAIL = "SELECT * FROM prestador WHERE email=?";
	private static final String SELECT_ID_BY_EMAIL = "SELECT id FROM prestador WHERE email=?";
	
	public boolean insert(Prestador p, String cidade) {
	    if (p == null) {
	        return false;
	    }

	    int rows = 0;

	    try (var connection = DatabaseConnection.getConnection();
	         var stmt = connection.prepareStatement(INSERT, java.sql.Statement.RETURN_GENERATED_KEYS)) {

	        stmt.setString(1, p.getNomeFantasia());
	        stmt.setString(2, p.getNome());
	        stmt.setString(3, p.getFotoPerfil());
	        stmt.setString(4, p.getEspecialidade());
	        stmt.setString(5, p.getEndereco());
	        stmt.setString(6, p.getDescricao());
	        stmt.setString(7, p.getEmail());
	        stmt.setString(8, p.getSenha());

	        CidadeDao dao = new CidadeDao();
	        int idCidade = dao.fetchByNome(cidade);
	        stmt.setInt(9, idCidade);

	        rows = stmt.executeUpdate();

	        if (rows > 0) {
	            try (var generatedKeys = stmt.getGeneratedKeys()) {
	                if (generatedKeys.next()) {
	                    p.setId(generatedKeys.getInt(1));  // armazena o ID gerado no objeto
	                }
	            }
	        }

	    } catch (SQLException e) {
	        e.printStackTrace();
	    }

	    return rows > 0;
	}
	
	public Prestador findByEmail(String email) {
		Prestador p = null;
		try(var connection = DatabaseConnection.getConnection();
				var stmt = connection.prepareStatement(SELECT_BY_EMAIL)){
			stmt.setString(1, email);
			
			var resultSet = stmt.executeQuery();
			if(resultSet.next()) {
				p = new Prestador(resultSet.getString("nome_fantasia"),resultSet.getString("foto_perfil"),resultSet.getString("especialidade"),
						resultSet.getString("descricao"),resultSet.getString("nome_completo"),resultSet.getString("endereco"),
						resultSet.getString("email"),resultSet.getString("senha_hash"),true);
				p.setId(resultSet.getInt("id"));
				
				ImagemServDao imagemDao = new ImagemServDao();
				p.addAllImagens(imagemDao.retriveImagens(p.getEmail()));
			}
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
		
		return p;
	}
	
	public int findIdByEmail(String email) {
		int id = -1;
		try(var connection = DatabaseConnection.getConnection();
				var stmt = connection.prepareStatement(SELECT_ID_BY_EMAIL)){
			stmt.setString(1, email);
			
			var resultSet = stmt.executeQuery();
			if(resultSet.next()) {
				id = resultSet.getInt("id");
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		
		return id;
	}
	
	public List<Prestador> buscarPorCidadeEspecialidade(String cidade, String especialidade) {
	    List<Prestador> prestadores = new ArrayList<>();
	    
	    StringBuilder sql = new StringBuilder(
	        "SELECT p.* FROM prestador p JOIN cidade c ON p.cidade_id = c.id WHERE 1=1"
	    );
	    
	    if (cidade != null && !cidade.isBlank()) {
	        sql.append(" AND c.nome = ?");
	    }
	    if (especialidade != null && !especialidade.isBlank()) {
	        sql.append(" AND p.especialidade = ?");
	    }

	    try (var connection = DatabaseConnection.getConnection();
	         var stmt = connection.prepareStatement(sql.toString())) {
	         
	        int index = 1;
	        if (cidade != null && !cidade.isBlank()) {
	            stmt.setString(index++, cidade);
	        }
	        if (especialidade != null && !especialidade.isBlank()) {
	            stmt.setString(index++, especialidade);
	        }

	        var rs = stmt.executeQuery();
	        while (rs.next()) {
	            Prestador p = new Prestador(
	                rs.getString("nome_fantasia"),
	                rs.getString("foto_perfil"),
	                rs.getString("especialidade"),
	                rs.getString("descricao"),
	                rs.getString("nome_completo"),
	                rs.getString("endereco"),
	                rs.getString("email"),
	                rs.getString("senha_hash"),
	                true
	            );
	            p.setId(rs.getInt("id"));
	            
	            ImagemServDao imagemDao = new ImagemServDao();
	            p.addAllImagens(imagemDao.retriveImagens(p.getEmail()));
	            
	            prestadores.add(p);
	        }

	    } catch (SQLException e) {
	        e.printStackTrace();
	    }

	    return prestadores;
	}

}
