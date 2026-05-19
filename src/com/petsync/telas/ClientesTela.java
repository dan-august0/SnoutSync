package com.petsync.telas;

import java.awt.Color;
import java.awt.Font;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class ClientesTela extends JFrame {

    public ClientesTela() {
        Navegacao.configurarJanela(this, "PetSync - Clientes");
        add(Navegacao.criarSidebar(this, "Clientes & Pets"));
        Navegacao.adicionarTopo(this, "Clientes & Pets");

        JLabel titulo = new JLabel("Lista de Clientes");
        titulo.setFont(new Font("Segoe UI", Font.BOLD, 13));
        titulo.setForeground(Navegacao.AZUL);
        titulo.setBounds(310, 112, 180, 22);
        add(titulo);

        JTextField busca = Navegacao.campo("Buscar por pet ou cliente...");
        busca.setBounds(310, 145, 520, 32);
        add(busca);

        JComboBox<String> tipo = new JComboBox<>(new String[]{"Todos os tipos", "Plano", "Avulso"});
        tipo.setBounds(845, 145, 120, 32);
        add(tipo);

        int y = 235;
        for (AppDados.Cliente cliente : AppDados.clientes) {
            JPanel card = criarCard(cliente);
            card.setBounds(310, y, 655, 68);
            add(card);
            y += 86;
        }
    }

    private JPanel criarCard(AppDados.Cliente cliente) {
        JPanel card = Navegacao.card(18);

        JLabel iniciais = new JLabel(iniciais(cliente.tutor));
        iniciais.setHorizontalAlignment(JLabel.CENTER);
        iniciais.setOpaque(true);
        iniciais.setBackground(Navegacao.AZUL);
        iniciais.setForeground(Color.WHITE);
        iniciais.setFont(new Font("Segoe UI", Font.BOLD, 14));
        iniciais.setBounds(18, 16, 38, 38);
        card.add(iniciais);

        JLabel tutorLabel = new JLabel(cliente.tutor);
        tutorLabel.setFont(new Font("Segoe UI", Font.PLAIN, 18));
        tutorLabel.setForeground(Navegacao.TEXTO);
        tutorLabel.setBounds(75, 12, 300, 25);
        card.add(tutorLabel);

        JLabel petLabel = new JLabel("1 pet  -  " + cliente.telefone);
        petLabel.setFont(new Font("Segoe UI", Font.PLAIN, 10));
        petLabel.setBounds(75, 40, 260, 18);
        card.add(petLabel);

        JLabel tipoLabel = new JLabel("Plano".equals(cliente.tipo) ? "Plano mensal" : "Avulso");
        tipoLabel.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        tipoLabel.setForeground("Plano".equals(cliente.tipo) ? Navegacao.AZUL : new Color(65, 170, 40));
        tipoLabel.setBounds(530, 24, 110, 22);
        card.add(tipoLabel);

        return card;
    }

    private String iniciais(String nome) {
        String[] partes = nome.trim().split(" ");
        if (partes.length == 1) {
            return partes[0].substring(0, Math.min(2, partes[0].length())).toUpperCase();
        }
        return (partes[0].substring(0, 1) + partes[partes.length - 1].substring(0, 1)).toUpperCase();
    }

    public static void main(String[] args) {
        java.awt.EventQueue.invokeLater(() -> new ClientesTela().setVisible(true));
    }
}
