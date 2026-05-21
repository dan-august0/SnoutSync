package com.petsync.dao;

import com.petsync.model.Servico;
import com.petsync.util.ConnectionFactory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ServicoDAO {

    public int salvar(Servico servico) throws SQLException {
        String sql = "INSERT INTO servico "
                + "(nome, descricao, preco_pequeno, preco_medio, preco_grande, duracao_pequeno, duracao_medio, duracao_grande) "
                + "VALUES (?, ?, ?, ?, ?, 30, 45, 60)";

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setString(1, servico.getNome());
            stmt.setString(2, servico.getNome());
            stmt.setDouble(3, servico.getPrecoPequeno());
            stmt.setDouble(4, servico.getPrecoMedio());
            stmt.setDouble(5, servico.getPrecoGrande());
            stmt.executeUpdate();

            try (ResultSet rs = stmt.getGeneratedKeys()) {
                rs.next();
                return rs.getInt(1);
            }
        }
    }

    public Integer buscarIdPorNome(String nome) throws SQLException {
        String sql = "SELECT id FROM servico WHERE nome = ? LIMIT 1";

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, nome);

            try (ResultSet rs = stmt.executeQuery()) {
                return rs.next() ? rs.getInt("id") : null;
            }
        }
    }
}
