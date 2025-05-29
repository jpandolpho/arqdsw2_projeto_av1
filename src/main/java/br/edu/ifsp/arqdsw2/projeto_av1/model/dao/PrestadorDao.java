package br.edu.ifsp.arqdsw2.projeto_av1.model.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.edu.ifsp.arqdsw2.projeto_av1.model.dao.connection.DatabaseConnection;
import br.edu.ifsp.arqdsw2.projeto_av1.model.entity.Prestador;

public class PrestadorDao {
    public static final int PAGE_SIZE = 5;  // número de prestadores por página

    private static final String INSERT =
        "INSERT INTO Prestador(nome_fantasia,nome_completo,foto_perfil,especialidade,endereco,descricao,email,senha_hash,cidade_id)" +
        "VALUES (?,?,?,?,?,?,?,?,?)";
    private static final String SELECT_BY_EMAIL = "SELECT * FROM prestador WHERE email=?";
    private static final String SELECT_ID_BY_EMAIL = "SELECT id FROM prestador WHERE email=?";

    public boolean insert(Prestador p, String cidade) {
        if (p == null) return false;
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
            stmt.setInt(9, dao.fetchByNome(cidade));

            rows = stmt.executeUpdate();
            if (rows > 0) {
                try (var keys = stmt.getGeneratedKeys()) {
                    if (keys.next()) {
                        p.setId(keys.getInt(1));
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rows > 0;
    }

    public int countAll(String cidade, String especialidade) {
        StringBuilder sql = new StringBuilder(
            "SELECT COUNT(*) FROM prestador p JOIN cidade c ON p.cidade_id = c.id WHERE 1=1"
        );
        if (cidade != null && !cidade.isBlank()) sql.append(" AND c.nome = ?");
        if (especialidade != null && !especialidade.isBlank()) sql.append(" AND p.especialidade = ?");

        try (var conn = DatabaseConnection.getConnection();
             var stmt = conn.prepareStatement(sql.toString())) {
            int idx = 1;
            if (cidade != null && !cidade.isBlank()) stmt.setString(idx++, cidade);
            if (especialidade != null && !especialidade.isBlank()) stmt.setString(idx++, especialidade);

            try (var rs = stmt.executeQuery()) {
                return rs.next() ? rs.getInt(1) : 0;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public List<Prestador> buscarPaginado(String cidade, String especialidade, int page) {
        List<Prestador> prestadores = new ArrayList<>();
        StringBuilder sql = new StringBuilder(
            "SELECT p.* FROM prestador p JOIN cidade c ON p.cidade_id = c.id WHERE 1=1"
        );
        if (cidade != null && !cidade.isBlank()) sql.append(" AND c.nome = ?");
        if (especialidade != null && !especialidade.isBlank()) sql.append(" AND p.especialidade = ?");
        sql.append(" LIMIT ? OFFSET ?");

        try (var conn = DatabaseConnection.getConnection();
             var stmt = conn.prepareStatement(sql.toString())) {
            int idx = 1;
            if (cidade != null && !cidade.isBlank()) stmt.setString(idx++, cidade);
            if (especialidade != null && !especialidade.isBlank()) stmt.setString(idx++, especialidade);
            stmt.setInt(idx++, PAGE_SIZE);
            stmt.setInt(idx,   page * PAGE_SIZE);

            try (var rs = stmt.executeQuery()) {
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
                    prestadores.add(p);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return prestadores;
    }

    public Prestador findByEmail(String email) {
        Prestador p = null;
        try (var conn = DatabaseConnection.getConnection();
             var stmt = conn.prepareStatement(SELECT_BY_EMAIL)) {
            stmt.setString(1, email);
            try (var rs = stmt.executeQuery()) {
                if (rs.next()) {
                    p = mapRowToPrestador(rs);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return p;
    }

    public int findIdByEmail(String email) {
        int id = -1;
        try (var conn = DatabaseConnection.getConnection();
             var stmt = conn.prepareStatement(SELECT_ID_BY_EMAIL)) {
            stmt.setString(1, email);
            try (var rs = stmt.executeQuery()) {
                if (rs.next()) id = rs.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return id;
    }

    public Prestador findById(int id) {
        Prestador p = null;
        String sql = "SELECT * FROM prestador WHERE id = ?";
        try (var conn = DatabaseConnection.getConnection();
             var stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            try (var rs = stmt.executeQuery()) {
                if (rs.next()) {
                    p = mapRowToPrestador(rs);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return p;
    }

    private Prestador mapRowToPrestador(java.sql.ResultSet rs) throws SQLException {
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
        ImagemServDao imgDao = new ImagemServDao();
        p.addAllImagens(imgDao.retriveImagens(p.getEmail()));
        return p;
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

	    System.out.println("Executando buscarPorCidadeEspecialidade com cidade=" + cidade + ", especialidade=" + especialidade);
	    
	    return prestadores;
	}
}
