package com.petsync.dao;

import com.petsync.model.Pet;
import com.petsync.util.ConnectionFactory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class PetDAO {

    public int salvar(Pet pet) throws SQLException {
        String sql = "INSERT INTO pet (cliente_id, nome, especie, raca, porte) VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setInt(1, pet.getClienteId());
            stmt.setString(2, pet.getNome());
            stmt.setString(3, pet.getEspecie());
            stmt.setString(4, pet.getRaca());
            stmt.setString(5, pet.getPorte());
            stmt.executeUpdate();

            try (ResultSet rs = stmt.getGeneratedKeys()) {
                rs.next();
                return rs.getInt(1);
            }
        }
    }

    public Integer buscarIdPorClienteEPet(String clienteNome, String petNome) throws SQLException {
        String sql = "SELECT p.id FROM pet p "
                + "JOIN cliente c ON c.id = p.cliente_id "
                + "WHERE c.nome = ? AND p.nome = ? LIMIT 1";

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, clienteNome);
            stmt.setString(2, petNome);

            try (ResultSet rs = stmt.executeQuery()) {
                return rs.next() ? rs.getInt("id") : null;
            }
        }
    }
}
