package br.edu.ifsp.arqdsw2.projeto_av1.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.sql.Time;
import java.util.List;
import java.sql.Date;

import br.edu.ifsp.arqdsw2.projeto_av1.model.entity.Disponibilidade;
import br.edu.ifsp.arqdsw2.projeto_av1.model.enums.DiaSemana;
import br.edu.ifsp.arqdsw2.projeto_av1.model.dao.connection.DatabaseConnection;

public class DisponibilidadeDao {

    public void inserir(Disponibilidade disponibilidade) throws SQLException {
        String sql = "INSERT INTO Disponibilidade (prestador_id, dia_mes, dia_semana, hora_inicio, hora_fim) VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, disponibilidade.getPrestadorId());
            stmt.setDate(2, new Date(disponibilidade.getDiaMes().getTime()));
            stmt.setString(3, disponibilidade.getDiaSemana().name());
            stmt.setTime(4, Time.valueOf(disponibilidade.getHoraInicio()));
            stmt.setTime(5, Time.valueOf(disponibilidade.getHoraFim()));

            stmt.executeUpdate();
        }
    }
    
    public List<Disponibilidade> buscarPorPrestador(int prestadorId) throws SQLException {
        List<Disponibilidade> disponibilidades = new ArrayList<>();
        String sql = "SELECT * FROM Disponibilidade WHERE prestador_id = ? AND dia_mes >= CURRENT_DATE ORDER BY dia_mes, hora_inicio";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, prestadorId);

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Disponibilidade d = new Disponibilidade();
                    d.setId(rs.getInt("id"));
                    d.setPrestadorId(rs.getInt("prestador_id"));
                    d.setDiaMes(rs.getDate("dia_mes"));
                    d.setDiaSemana(DiaSemana.valueOf(rs.getString("dia_semana")));
                    d.setHoraInicio(rs.getTime("hora_inicio").toLocalTime());
                    d.setHoraFim(rs.getTime("hora_fim").toLocalTime());
                    disponibilidades.add(d);
                }
            }
        }

        return disponibilidades;
    }
    
    public Disponibilidade buscarPorId(int disponibilidadeId) throws SQLException {
        String sql = "SELECT * FROM Disponibilidade WHERE id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, disponibilidadeId);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    Disponibilidade d = new Disponibilidade();
                    d.setPrestadorId(rs.getInt("prestador_id"));
                    d.setDiaMes(rs.getDate("dia_mes"));
                    d.setDiaSemana(DiaSemana.valueOf(rs.getString("dia_semana")));
                    d.setHoraInicio(rs.getTime("hora_inicio").toLocalTime());
                    d.setHoraFim(rs.getTime("hora_fim").toLocalTime());
                    d.setId(rs.getInt("id"));
                    return d;
                } else {
                    return null;
                }
            }
        }
    }

}