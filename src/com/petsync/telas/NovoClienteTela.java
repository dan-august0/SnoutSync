package com.petsync.telas;

import java.awt.Color;
import java.awt.Font;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class NovoClienteTela extends JFrame {

    public NovoClienteTela() {

        setTitle("PetSync - Novo Cliente");

        setSize(700, 550);

        setLocationRelativeTo(null);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setLayout(null);

        setResizable(false);

        getContentPane().setBackground(
                new Color(245,247,255)
        );

        JLabel titulo = new JLabel("Cadastrar Cliente");

        titulo.setFont(
                new Font("Segoe UI", Font.BOLD, 28)
        );

        titulo.setForeground(
                new Color(74,99,255)
        );

        titulo.setBounds(40,30,300,40);

        add(titulo);

        // NOME TUTOR
        adicionarLabel("Nome do Tutor", 40, 100);

        JTextField tutor = criarCampo(40,130);

        add(tutor);

        // TELEFONE
        adicionarLabel("Telefone", 370,100);

        JTextField telefone = criarCampo(370,130);

        add(telefone);

        // NOME PET
        adicionarLabel("Nome do Pet", 40,210);

        JTextField pet = criarCampo(40,240);

        add(pet);

        // RAÇA
        adicionarLabel("Raça", 370,210);

        JTextField raca = criarCampo(370,240);

        add(raca);

        // TIPO
        adicionarLabel("Tipo de Atendimento", 40,320);

        String[] tipos = {
                "Plano",
                "Avulso"
        };

        JComboBox<String> combo =
                new JComboBox<>(tipos);

        combo.setBounds(40,350,250,40);

        add(combo);

        // BOTAO
        JButton salvar = new JButton("Salvar Cliente");

        salvar.setBounds(220,440,220,45);

        salvar.setBackground(
                new Color(74,99,255)
        );

        salvar.setForeground(Color.WHITE);

        salvar.setFocusPainted(false);

        salvar.setFont(
                new Font("Segoe UI", Font.BOLD, 16)
        );

        add(salvar);

    }

    private void adicionarLabel(String texto,
                                int x,
                                int y) {

        JLabel label = new JLabel(texto);

        label.setFont(
                new Font("Segoe UI", Font.BOLD, 15)
        );

        label.setBounds(x,y,200,20);

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

            new NovoClienteTela().setVisible(true);

        });
    }
}