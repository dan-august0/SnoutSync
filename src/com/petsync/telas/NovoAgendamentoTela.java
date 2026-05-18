package com.petsync.telas;

import java.awt.Color;
import java.awt.Font;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class NovoAgendamentoTela extends JFrame {

    public NovoAgendamentoTela() {

        setTitle("PetSync - Novo Agendamento");

        setSize(720, 620);

        setLocationRelativeTo(null);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setLayout(null);

        setResizable(false);

        getContentPane().setBackground(
                new Color(245,247,255)
        );

        JLabel titulo = new JLabel("Novo Agendamento");

        titulo.setFont(
                new Font("Segoe UI", Font.BOLD, 28)
        );

        titulo.setForeground(
                new Color(74,99,255)
        );

        titulo.setBounds(40,30,350,40);

        add(titulo);

        // CLIENTE
        adicionarLabel("Cliente",40,100);

        JTextField cliente = criarCampo(40,130);

        add(cliente);

        // PET
        adicionarLabel("Pet",370,100);

        JTextField pet = criarCampo(370,130);

        add(pet);

        // SERVIÇO
        adicionarLabel("Serviço",40,210);

        String[] servicos = {
                "Banho",
                "Tosa",
                "Banho + Tosa"
        };

        JComboBox<String> comboServico =
                new JComboBox<>(servicos);

        comboServico.setBounds(40,240,250,40);

        add(comboServico);

        // DATA
        adicionarLabel("Data",370,210);

        JTextField data = criarCampo(370,240);

        data.setText("10/05/2026");

        add(data);

        // HORARIO
        adicionarLabel("Horário",40,320);

        JTextField horario = criarCampo(40,350);

        horario.setText("14:00");

        add(horario);

        // STATUS
        adicionarLabel("Status",370,320);

        String[] status = {
                "Confirmado",
                "Pendente",
                "Cancelado"
        };

        JComboBox<String> comboStatus =
                new JComboBox<>(status);

        comboStatus.setBounds(370,350,250,40);

        add(comboStatus);

        // BOTAO
        JButton agendar = new JButton(
                "Salvar Agendamento"
        );

        agendar.setBounds(220,500,250,45);

        agendar.setBackground(
                new Color(74,99,255)
        );

        agendar.setForeground(Color.WHITE);

        agendar.setFocusPainted(false);

        agendar.setFont(
                new Font("Segoe UI", Font.BOLD, 16)
        );

        add(agendar);
    }

    private void adicionarLabel(String texto,
                                int x,
                                int y) {

        JLabel label = new JLabel(texto);

        label.setFont(
                new Font("Segoe UI", Font.BOLD, 15)
        );

        label.setBounds(x,y,220,20);

        add(label);
    }

    private JTextField criarCampo(int x,
                                  int y) {

        JTextField campo = new JTextField();

        campo.setBounds(x,y,250,40);

        campo.setBorder(
                BorderFactory.createEmptyBorder(
                        5,10,5,10
                )
        );

        return campo;
    }

    public static void main(String[] args) {

        java.awt.EventQueue.invokeLater(() -> {

            new NovoAgendamentoTela().setVisible(true);

        });
    }
}