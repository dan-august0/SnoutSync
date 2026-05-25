package com.petsync.telas;

import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class NovoAgendamentoTela extends JFrame {

    private JComboBox<String> cliente;
    private JComboBox<String> pet;
    private JComboBox<String> servico;
    private JComboBox<String> profissional;
    private JTextField data;
    private JTextField hora;
    private JTextField valor;
    private JTextField obs;

    public NovoAgendamentoTela() {
        Navegacao.configurarModal(this, "SnoutSync - Novo Agendamento");

        JPanel page = new JPanel(null);
        page.setOpaque(false);
        page.setBounds(0, 0, Navegacao.APP_W, Navegacao.APP_H);
        add(page);

        JLabel titulo = Navegacao.label("Novo Agendamento", 24, Font.BOLD, Navegacao.TEXTO);
        titulo.setBounds(45, 32, 300, 30);
        page.add(titulo);

        JLabel sub = Navegacao.label("Agende um novo servico.", 12, Font.PLAIN, Navegacao.TEXTO_SUAVE);
        sub.setBounds(45, 64, 250, 18);
        page.add(sub);

        JLabel fechar = Navegacao.label("X", 20, Font.BOLD, Navegacao.TEXTO);
        fechar.setBounds(1138, 32, 28, 28);
        fechar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        fechar.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent e) {
                dispose();
            }
        });
        page.add(fechar);

        JPanel esquerda = Navegacao.card();
        esquerda.setBounds(45, 115, 545, 420);
        page.add(esquerda);

        cliente = comboComLabel(esquerda, "Cliente", clientesCombo(), 22, 45, 490);
        pet = comboComLabel(esquerda, "Pet", petsCombo(), 22, 128, 490);
        servico = comboComLabel(esquerda, "Servico", new String[]{"Banho", "Tosa", "Banho + Tosa", "Consulta", "Vacinacao"}, 22, 211, 490);
        obs = campoComLabel(esquerda, "Observacoes", "Observacoes sobre o agendamento (opcional)", 22, 294, 490);

        JPanel direita = Navegacao.card();
        direita.setBounds(610, 115, 545, 420);
        page.add(direita);

        data = campoComLabel(direita, "Data", "20/05/2026", 22, 45, 230);
        hora = campoComLabel(direita, "Hora", "10:00", 282, 45, 230);
        profissional = comboComLabel(direita, "Profissional", new String[]{"Selecione o profissional", "Leonardo", "Maria", "Carlos"}, 22, 128, 490);
        valor = campoComLabel(direita, "Valor", "R$ 0,00", 22, 211, 490);

        JButton cancelar = Navegacao.botaoSecundario("Cancelar");
        cancelar.setBounds(785, 625, 135, 38);
        cancelar.addActionListener(e -> dispose());
        page.add(cancelar);

        JButton salvar = Navegacao.botaoPrimario("Salvar Agendamento");
        salvar.setBounds(940, 625, 180, 38);
        salvar.addActionListener(e -> salvar());
        page.add(salvar);
    }

    private String[] clientesCombo() {
        if (AppDados.clientes.isEmpty()) {
            return new String[]{"Selecione o cliente"};
        }
        String[] nomes = new String[AppDados.clientes.size()];
        for (int i = 0; i < AppDados.clientes.size(); i++) {
            nomes[i] = AppDados.clientes.get(i).tutor;
        }
        return nomes;
    }

    private String[] petsCombo() {
        if (AppDados.clientes.isEmpty()) {
            return new String[]{"Selecione o pet"};
        }
        String[] pets = new String[AppDados.clientes.size()];
        for (int i = 0; i < AppDados.clientes.size(); i++) {
            pets[i] = AppDados.clientes.get(i).pet;
        }
        return pets;
    }

    private JComboBox<String> comboComLabel(JPanel panel, String label, String[] itens, int x, int y, int w) {
        JLabel l = Navegacao.label(label, 12, Font.BOLD, Navegacao.TEXTO);
        l.setBounds(x, y, w, 18);
        panel.add(l);
        JComboBox<String> combo = new JComboBox<>(itens);
        combo.setBounds(x, y + 24, w, 38);
        panel.add(combo);
        return combo;
    }

    private JTextField campoComLabel(JPanel panel, String label, String placeholder, int x, int y, int w) {
        JLabel l = Navegacao.label(label, 12, Font.BOLD, Navegacao.TEXTO);
        l.setBounds(x, y, w, 18);
        panel.add(l);
        JTextField campo = Navegacao.campo(placeholder);
        campo.setBounds(x, y + 24, w, 38);
        panel.add(campo);
        return campo;
    }

    private void salvar() {
        String clienteSelecionado = cliente.getSelectedItem().toString();
        String petSelecionado = pet.getSelectedItem().toString();

        if (clienteSelecionado.startsWith("Selecione") || petSelecionado.startsWith("Selecione")) {
            JOptionPane.showMessageDialog(this, "Selecione cliente e pet.");
            return;
        }

        AppDados.adicionarAgendamento(new AppDados.Agendamento(
                clienteSelecionado,
                petSelecionado,
                servico.getSelectedItem().toString(),
                data.getText().trim(),
                hora.getText().trim(),
                "Confirmado"
        ));

        JOptionPane.showMessageDialog(this, "Agendamento salvo com sucesso.");
        Navegacao.abrir(this, new AgendamentosTela());
    }

    public static void main(String[] args) {
        java.awt.EventQueue.invokeLater(() -> new NovoAgendamentoTela().setVisible(true));
    }
}
