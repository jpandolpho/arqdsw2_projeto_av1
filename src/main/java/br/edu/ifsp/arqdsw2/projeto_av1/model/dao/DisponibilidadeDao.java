package br.edu.ifsp.arqdsw2.projeto_av1.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Time;
import java.sql.Date;

import br.edu.ifsp.arqdsw2.projeto_av1.model.entity.Disponibilidade;
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
}