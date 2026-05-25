package com.petsync.telas;

import java.awt.Color;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class AgendamentosTela extends JFrame {

    public AgendamentosTela() {
        JPanel page = Navegacao.page(this, "Agendamentos", "Agendamentos", "Gerencie todos os agendamentos.");

        JButton novo = Navegacao.botaoPrimario("+ Novo Agendamento");
        novo.setBounds(820, 28, 150, 34);
        novo.addActionListener(e -> new NovoAgendamentoTela().setVisible(true));
        page.add(novo);

        JPanel tabela = Navegacao.card();
        tabela.setBounds(28, 105, 944, 490);
        page.add(tabela);

        JTextField busca = Navegacao.campo("Buscar agendamento...");
        busca.setBounds(610, 28, 300, 36);
        tabela.add(busca);

        JComboBox<String> filtro = new JComboBox<>(new String[]{"Todos os status", "Confirmado", "Pendente", "Cancelado"});
        filtro.setBounds(385, 28, 190, 36);
        tabela.add(filtro);

        JTextField data = Navegacao.campo("20/05/2026");
        data.setBounds(20, 28, 160, 36);
        tabela.add(data);

        cabecalho(tabela);

        int y = 112;
        for (AppDados.Agendamento a : AppDados.agendamentos) {
            linha(tabela, a, y);
            y += 56;
        }

        JButton ant = Navegacao.botaoSecundario("<");
        ant.setBounds(420, 440, 36, 34);
        tabela.add(ant);

        JButton p1 = Navegacao.botaoPrimario("1");
        p1.setBounds(462, 440, 36, 34);
        tabela.add(p1);

        JButton p2 = Navegacao.botaoSecundario("2");
        p2.setBounds(504, 440, 36, 34);
        tabela.add(p2);

        JButton prox = Navegacao.botaoSecundario(">");
        prox.setBounds(546, 440, 36, 34);
        tabela.add(prox);
    }

    private void cabecalho(JPanel tabela) {
        String[] colunas = {"Horario", "Pet", "Servico", "Cliente", "Status", "Acoes"};
        int[] xs = {35, 150, 285, 460, 645, 820};
        for (int i = 0; i < colunas.length; i++) {
            JLabel c = Navegacao.label(colunas[i], 11, Font.BOLD, Navegacao.TEXTO);
            c.setBounds(xs[i], 82, 110, 20);
            tabela.add(c);
        }
    }

    private void linha(JPanel tabela, AppDados.Agendamento a, int y) {
        tabela.add(text(a.horario, 35, y, 70));
        JLabel avatar = Navegacao.circle(a.pet.substring(0, 1).toUpperCase(), 12, Color.WHITE, Navegacao.AZUL_2);
        avatar.setBounds(150, y - 8, 32, 32);
        tabela.add(avatar);
        tabela.add(text(a.pet, 190, y, 80));
        tabela.add(text(a.servico, 285, y, 130));
        tabela.add(text(a.cliente, 460, y, 130));
        JPanel status = status(a.status);
        status.setBounds(645, y - 4, 82, 24);
        tabela.add(status);
        tabela.add(text("Editar", 820, y, 50));
        tabela.add(text("Excluir", 875, y, 55));
    }

    private JLabel text(String texto, int x, int y, int w) {
        JLabel label = Navegacao.label(texto, 11, Font.BOLD, Navegacao.TEXTO);
        label.setBounds(x, y, w, 18);
        return label;
    }

    private JPanel status(String status) {
        if ("Pendente".equals(status)) {
            return Navegacao.badge("Pendente", new Color(255, 240, 214), Navegacao.LARANJA);
        }
        if ("Cancelado".equals(status)) {
            return Navegacao.badge("Cancelado", new Color(255, 230, 225), Color.RED);
        }
        return Navegacao.badge("Confirmado", new Color(220, 248, 232), Navegacao.VERDE);
    }

    public static void main(String[] args) {
        java.awt.EventQueue.invokeLater(() -> new AgendamentosTela().setVisible(true));
    }
}
