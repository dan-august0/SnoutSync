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

public class NovoClienteTela extends JFrame {

    private JTextField tutor;
    private JTextField telefone;
    private JTextField pet;
    private JTextField raca;
    private JComboBox<String> comboTipo;

    public NovoClienteTela() {
        setTitle("PetSync - Novo Cliente");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(null);
        setResizable(false);
        getContentPane().setBackground(Navegacao.FUNDO);
        getContentPane().setPreferredSize(new java.awt.Dimension(1000, 600));
        pack();
        setLocationRelativeTo(null);

        JButton novo = Navegacao.botaoAzul("+ Novo cliente", 12);
        novo.setBounds(410, 48, 170, 40);
        add(novo);

        JPanel card = Navegacao.card(18);
        card.setBounds(180, 115, 650, 420);
        add(card);

        JLabel dadosTutor = tituloSecao("Dados do tutor");
        dadosTutor.setBounds(25, 18, 250, 24);
        card.add(dadosTutor);

        adicionarLabel(card, "Nome completo", 25, 65);
        tutor = criarCampo(25, 88, 625);
        card.add(tutor);

        adicionarLabel(card, "Telefone (WhatsApp)", 25, 145);
        telefone = criarCampo(25, 168, 220);
        telefone.setText("(00) 00000-0000");
        card.add(telefone);

        adicionarLabel(card, "Tipo de agendamento", 300, 145);
        comboTipo = new JComboBox<>(new String[]{"Avulso", "Plano"});
        comboTipo.setBounds(300, 168, 170, 40);
        card.add(comboTipo);

        JPanel plano = Navegacao.card(8);
        plano.setBackground(Navegacao.AZUL_CLARO);
        plano.setBounds(480, 168, 145, 55);
        JLabel planoTexto = new JLabel("<html>Plano mensal<br>4 banhos/mes</html>");
        planoTexto.setFont(new Font("Segoe UI", Font.PLAIN, 10));
        planoTexto.setForeground(Color.WHITE);
        planoTexto.setBounds(12, 8, 125, 40);
        plano.add(planoTexto);
        card.add(plano);

        JLabel dadosPet = tituloSecao("Dados do pet");
        dadosPet.setBounds(25, 245, 250, 24);
        card.add(dadosPet);

        adicionarLabel(card, "Nome Pet", 25, 290);
        pet = criarCampo(25, 313, 220);
        pet.setText("EX: Rex");
        card.add(pet);

        adicionarLabel(card, "Especie", 300, 290);
        raca = criarCampo(300, 313, 220);
        raca.setText("cao");
        card.add(raca);

        adicionarLabel(card, "Observacoes", 25, 355);
        JTextField obs = criarCampo(25, 378, 330);
        obs.setText("EX: Nervoso com estranhos, alergias...");
        card.add(obs);

        JButton novoPet = Navegacao.botaoAzul("+ Novo pet", 11);
        novoPet.setBounds(370, 378, 105, 34);
        card.add(novoPet);

        JButton voltar = Navegacao.botaoCinza("Fechar");
        voltar.setBounds(485, 378, 70, 35);
        voltar.addActionListener(e -> dispose());
        card.add(voltar);

        JButton salvar = new JButton("Cadastrar");
        salvar.setBounds(565, 378, 70, 35);
        salvar.setBackground(Navegacao.AZUL);
        salvar.setForeground(Color.WHITE);
        salvar.setBorderPainted(false);
        salvar.setFocusPainted(false);
        salvar.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        salvar.addActionListener(e -> salvarCliente());
        card.add(salvar);
    }

    private void salvarCliente() {
        if (campoVazio(tutor) || campoVazio(telefone) || campoVazio(pet) || campoVazio(raca)) {
            JOptionPane.showMessageDialog(this, "Preencha todos os campos.", "Atencao", JOptionPane.WARNING_MESSAGE);
            return;
        }

        AppDados.adicionarCliente(new AppDados.Cliente(
                tutor.getText().trim(),
                telefone.getText().trim(),
                pet.getText().trim(),
                raca.getText().trim(),
                comboTipo.getSelectedItem().toString()
        ));

        JOptionPane.showMessageDialog(this, "Cliente cadastrado com sucesso!");
        Navegacao.abrir(this, new ClientesTela());
    }

    private boolean campoVazio(JTextField campo) {
        return campo.getText().trim().isEmpty();
    }

    private JLabel tituloSecao(String texto) {
        JLabel label = new JLabel(texto);
        label.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        label.setForeground(Navegacao.AZUL);
        return label;
    }

    private void adicionarLabel(JPanel pai, String texto, int x, int y) {
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
        java.awt.EventQueue.invokeLater(() -> new NovoClienteTela().setVisible(true));
    }
}
