package com.petsync.dao;

import com.petsync.model.Agendamento;
import com.petsync.util.ConnectionFactory;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

public class AgendamentoDAO {

    public void salvar(Agendamento agendamento) throws SQLException {
        String sql = "INSERT INTO agendamento (pet_id, servico_id, data, hora, status, observacoes) "
                + "VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, agendamento.getPetId());
            stmt.setInt(2, agendamento.getServicoId());
            stmt.setDate(3, Date.valueOf(agendamento.getData()));
            stmt.setTime(4, Time.valueOf(agendamento.getHora()));
            stmt.setString(5, agendamento.getStatus());
            stmt.setString(6, agendamento.getObservacoes());
            stmt.executeUpdate();
        }
    }

    public List<Agendamento> listarTodos() throws SQLException {
        List<Agendamento> agendamentos = new ArrayList<>();
        String sql = "SELECT a.id, a.pet_id, a.servico_id, c.nome AS cliente, p.nome AS pet, "
                + "s.nome AS servico, a.data, a.hora, a.status, a.observacoes "
                + "FROM agendamento a "
                + "JOIN pet p ON p.id = a.pet_id "
                + "JOIN cliente c ON c.id = p.cliente_id "
                + "JOIN servico s ON s.id = a.servico_id "
                + "ORDER BY a.data DESC, a.hora DESC";

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Agendamento agendamento = new Agendamento();
                agendamento.setId(rs.getInt("id"));
                agendamento.setPetId(rs.getInt("pet_id"));
                agendamento.setServicoId(rs.getInt("servico_id"));
                agendamento.setClienteNome(rs.getString("cliente"));
                agendamento.setPetNome(rs.getString("pet"));
                agendamento.setServicoNome(rs.getString("servico"));
                agendamento.setData(rs.getDate("data").toLocalDate());
                agendamento.setHora(rs.getTime("hora").toLocalTime());
                agendamento.setStatus(rs.getString("status"));
                agendamento.setObservacoes(rs.getString("observacoes"));
                agendamentos.add(agendamento);
            }
        }

        return agendamentos;
    }

    public void atualizarStatus(int id, String status) throws SQLException {
        String sql = "UPDATE agendamento SET status = ? WHERE id = ?";

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, status);
            stmt.setInt(2, id);
            stmt.executeUpdate();
        }
    }
}
