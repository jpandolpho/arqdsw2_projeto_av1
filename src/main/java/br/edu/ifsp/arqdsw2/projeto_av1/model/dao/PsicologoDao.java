package br.edu.ifsp.arqdsw2.projeto_av1.model.dao;

import java.sql.SQLException;

import br.edu.ifsp.arqdsw2.projeto_av1.model.dao.connection.DatabaseConnection;
import br.edu.ifsp.arqdsw2.projeto_av1.model.entity.Prestador;

public class PsicologoDao {
	private static final String INSERT = "INSERT INTO Prestador(nome_fantasia,nome_completo,foto_perfil,especialidade,endereco,descricao,email,senha_hash,cidade_id)"+
			"VALUES (?,?,?,?,?,?,?,?,?)";
	
	public boolean insert(Prestador p, String cidade) {
		int rows = 0;
		if(p!=null) {
			try(var connection = DatabaseConnection.getConnection();
					var stmt = connection.prepareStatement(INSERT)){
				stmt.setString(1, p.getNomeFantasia());
				stmt.setString(2, p.getNome());
				stmt.setString(3, p.getFotoPerfil());
				stmt.setString(4, p.getEspecialidade());
				stmt.setString(5, p.getEndereco());
				stmt.setString(6, p.getDescricao());
				stmt.setString(7, p.getEmail());
				stmt.setString(8, p.getSenha());
				CidadeDao dao = new CidadeDao();
				int id = dao.fetchByNome(cidade);
				stmt.setInt(9, id);
				
				rows = stmt.executeUpdate();
			}catch(SQLException e) {
				e.printStackTrace();
			}
		}
		return rows > 0;
	}
}
