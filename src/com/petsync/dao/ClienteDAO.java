package com.petsync.dao;

import com.petsync.model.Cliente;
import com.petsync.util.ConnectionFactory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ClienteDAO {

    public int salvar(Cliente cliente) throws SQLException {
        String sql = "INSERT INTO cliente (nome, telefone, tipo) VALUES (?, ?, ?)";

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setString(1, cliente.getNome());
            stmt.setString(2, cliente.getTelefone());
            stmt.setString(3, cliente.getTipo());
            stmt.executeUpdate();

            try (ResultSet rs = stmt.getGeneratedKeys()) {
                rs.next();
                return rs.getInt(1);
            }
        }
    }

    public List<Cliente> listarTodos() throws SQLException {
        List<Cliente> clientes = new ArrayList<>();
        String sql = "SELECT id, nome, telefone, tipo FROM cliente ORDER BY id DESC";

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                clientes.add(new Cliente(
                        rs.getInt("id"),
                        rs.getString("nome"),
                        rs.getString("telefone"),
                        rs.getString("tipo")
                ));
            }
        }

        return clientes;
    }

    public Cliente buscarPorNome(String nome) throws SQLException {
        String sql = "SELECT id, nome, telefone, tipo FROM cliente WHERE nome = ? LIMIT 1";

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, nome);

            try (ResultSet rs = stmt.executeQuery()) {
                if (!rs.next()) {
                    return null;
                }

                return new Cliente(
                        rs.getInt("id"),
                        rs.getString("nome"),
                        rs.getString("telefone"),
                        rs.getString("tipo")
                );
            }
        }
    }
}
