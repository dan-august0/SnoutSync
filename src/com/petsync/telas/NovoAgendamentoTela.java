package com.petsync.telas;

import java.awt.Color;
import java.awt.Font;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class NovoAgendamentoTela extends JFrame {

    private JTextField cliente;
    private JTextField pet;
    private JTextField data;
    private JTextField horario;
    private JComboBox<String> comboServico;
    private JComboBox<String> comboStatus;

    public NovoAgendamentoTela() {
        setTitle("PetSync - Novo Agendamento");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(null);
        setResizable(false);
        getContentPane().setBackground(Navegacao.FUNDO);
        getContentPane().setPreferredSize(new java.awt.Dimension(1000, 600));
        pack();
        setLocationRelativeTo(null);

        JLabel titulo = new JLabel("+ Novo agendamento");
        titulo.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        titulo.setForeground(Navegacao.TEXTO);
        titulo.setBounds(500, 55, 180, 24);
        add(titulo);

        JPanel card = Navegacao.card(18);
        card.setBounds(185, 110, 650, 360);
        add(card);

        JLabel secao = new JLabel("Dados do Agendamento");
        secao.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        secao.setForeground(Navegacao.AZUL);
        secao.setBounds(25, 18, 250, 24);
        card.add(secao);

        adicionarLabel(card, "Cliente", 25, 65);
        cliente = criarCampo(25, 88, 245);
        card.add(cliente);

        adicionarLabel(card, "Pet", 300, 65);
        pet = criarCampo(300, 88, 245);
        card.add(pet);

        adicionarLabel(card, "Data", 25, 130);
        data = criarCampo(25, 153, 220);
        data.setText("01/01/26");
        card.add(data);

        adicionarLabel(card, "Tipo de agendamento", 300, 130);
        comboServico = new JComboBox<>(new String[]{"Avulso", "Banho", "Tosa", "Banho + Tosa"});
        comboServico.setBounds(300, 153, 170, 40);
        card.add(comboServico);

        JPanel plano = Navegacao.card(8);
        plano.setBackground(Navegacao.AMARELO);
        plano.setBounds(480, 153, 145, 55);
        JLabel planoTexto = new JLabel("<html>Plano mensal<br>4 banhos/mes</html>");
        planoTexto.setFont(new Font("Segoe UI", Font.PLAIN, 10));
        planoTexto.setForeground(Navegacao.TEXTO);
        planoTexto.setBounds(12, 8, 125, 40);
        plano.add(planoTexto);
        card.add(plano);

        adicionarLabel(card, "Hora", 25, 200);
        horario = criarCampo(25, 223, 220);
        horario.setText("04:00");
        card.add(horario);

        comboStatus = new JComboBox<>(new String[]{"Confirmado", "Pendente", "Cancelado"});
        comboStatus.setBounds(300, 223, 170, 35);
        card.add(comboStatus);

        adicionarLabel(card, "Observacoes", 25, 235);
        JTextField obs = criarCampo(25, 258, 600);
        obs.setText("EX: Nervoso com estranhos, alergias...");
        card.add(obs);

        JButton fechar = Navegacao.botaoCinza("Fechar");
        fechar.setBounds(395, 305, 120, 35);
        fechar.addActionListener(e -> dispose());
        card.add(fechar);

        JButton agendar = new JButton("Cadastrar");
        agendar.setBounds(525, 305, 100, 35);
        agendar.setBackground(Navegacao.AZUL);
        agendar.setForeground(Color.WHITE);
        agendar.setBorderPainted(false);
        agendar.setFocusPainted(false);
        agendar.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        agendar.addActionListener(e -> salvarAgendamento());
        card.add(agendar);
    }

    private void salvarAgendamento() {
        if (campoVazio(cliente) || campoVazio(pet) || campoVazio(data) || campoVazio(horario)) {
            JOptionPane.showMessageDialog(this, "Preencha todos os campos.", "Atencao", JOptionPane.WARNING_MESSAGE);
            return;
        }

        AppDados.adicionarAgendamento(new AppDados.Agendamento(
                cliente.getText().trim(),
                pet.getText().trim(),
                servicoSelecionado(),
                data.getText().trim(),
                horario.getText().trim(),
                comboStatus.getSelectedItem().toString()
        ));

        JOptionPane.showMessageDialog(this, "Agendamento salvo com sucesso!");
        Navegacao.abrir(this, new AgendamentosTela());
    }

    private boolean campoVazio(JTextField campo) {
        return campo.getText().trim().isEmpty();
    }

    private String servicoSelecionado() {
        String valor = comboServico.getSelectedItem().toString();
        if ("Avulso".equals(valor)) {
            return "Banho";
        }
        return valor;
    }

    private void adicionarLabel(javax.swing.JPanel pai, String texto, int x, int y) {
        JLabel label = new JLabel(texto);
        label.setFont(new Font("Segoe UI", Font.BOLD, 15));
        label.setBounds(x, y, 220, 20);
        pai.add(label);
    }

    private JTextField criarCampo(int x, int y, int largura) {
        JTextField campo = new JTextField();
        campo.setBounds(x, y, largura, 35);
        campo.setBackground(Navegacao.CINZA);
        campo.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));
        return campo;
    }

    public static void main(String[] args) {
        java.awt.EventQueue.invokeLater(() -> new NovoAgendamentoTela().setVisible(true));
    }
}
