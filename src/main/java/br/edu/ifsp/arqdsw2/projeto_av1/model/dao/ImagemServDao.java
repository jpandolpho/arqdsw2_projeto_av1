package br.edu.ifsp.arqdsw2.projeto_av1.model.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.edu.ifsp.arqdsw2.projeto_av1.model.dao.connection.DatabaseConnection;
import br.edu.ifsp.arqdsw2.projeto_av1.model.entity.ImagemServ;

public class ImagemServDao {
	private static final String INSERT = "INSERT INTO imagemservico(caminho_imagem,descricao,prestador_id) VALUES (?,?,?)";
	private static final String SELECT_BY_PRESTADOR = "SELECT caminho_imagem,descricao FROM imagemservico WHERE prestador_id=?";
	
	public boolean insert(ImagemServ img, String email) {
		int rows = 0;
		if(img!=null) {
			try(var connection = DatabaseConnection.getConnection();
					var stmt = connection.prepareStatement(INSERT)){
				stmt.setString(1, img.getCaminho());
				stmt.setString(2,img.getDescricao());
				PrestadorDao dao = new PrestadorDao();
				int id = dao.findIdByEmail(email);
				stmt.setInt(3, id);
				
				rows = stmt.executeUpdate();
			}catch(SQLException e) {
				e.printStackTrace();
			}
		}
		return rows > 0;
	}
	
	public List<ImagemServ> retriveImagens(String email){
		List<ImagemServ> imagens = new ArrayList<>();
		try(var connection = DatabaseConnection.getConnection();
				var stmt = connection.prepareStatement(INSERT)){
			PrestadorDao dao = new PrestadorDao();
			int id = dao.findIdByEmail(email);
			
			stmt.setInt(1, id);
			var result = stmt.executeQuery();
			
			while(result.next()) {
				ImagemServ img = new ImagemServ();
				img.setCaminho(result.getString("caminho_imagem"));
				img.setDescricao(result.getString("descricao"));
				
				imagens.add(img);
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return imagens;
	}
}
