package com.petsync.telas;

import java.awt.Color;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class DashboardTela extends JFrame {

    public DashboardTela() {
        JPanel page = Navegacao.page(this, "Dashboard", "Ola, Leonardo!", "Bem-vindo(a) de volta ao SnoutSync.");

        int agendamentos = AppDados.agendamentosHoje();
        int clientes = AppDados.clientes.size();
        int servicos = AppDados.agendamentos.size() * 2;
        int faturamento = AppDados.faturamentoEstimado();

        page.add(metric("Agendamentos hoje", String.valueOf(agendamentos), "+12% vs ontem", "1", 28, 105));
        page.add(metric("Clientes cadastrados", String.valueOf(clientes), "+5% este mes", "2", 238, 105));
        page.add(metric("Servicos realizados", String.valueOf(servicos), "+8% este mes", "3", 448, 105));
        page.add(metric("Faturamento (mes)", "R$ " + faturamento + ",00", "+15% este mes", "4", 658, 105));

        JPanel agenda = Navegacao.card();
        agenda.setBounds(28, 230, 520, 185);
        page.add(agenda);

        JLabel agendaTitulo = Navegacao.label("Agendamentos de Hoje", 15, Font.BOLD, Navegacao.TEXTO);
        agendaTitulo.setBounds(16, 14, 220, 22);
        agenda.add(agendaTitulo);

        JLabel verTodos = Navegacao.label("Ver todos", 11, Font.BOLD, Navegacao.AZUL);
        verTodos.setBounds(450, 14, 60, 22);
        agenda.add(verTodos);

        int y = 52;
        int totalLinhas = Math.min(4, AppDados.agendamentos.size());
        for (int i = 0; i < totalLinhas; i++) {
            AppDados.Agendamento a = AppDados.agendamentos.get(i);
            addAgendaRow(agenda, y, a);
            y += 34;
        }

        JPanel resumo = Navegacao.card();
        resumo.setBounds(562, 230, 410, 185);
        page.add(resumo);

        JLabel resumoTitulo = Navegacao.label("Resumo Financeiro", 15, Font.BOLD, Navegacao.TEXTO);
        resumoTitulo.setBounds(16, 14, 200, 22);
        resumo.add(resumoTitulo);

        JLabel valor = Navegacao.label("R$ " + faturamento + ",00", 22, Font.BOLD, Navegacao.TEXTO);
        valor.setBounds(16, 62, 220, 30);
        resumo.add(valor);

        JLabel anterior = Navegacao.label("+15% vs mes anterior", 12, Font.BOLD, Navegacao.VERDE);
        anterior.setBounds(250, 46, 140, 20);
        resumo.add(anterior);

        int[] pontos = {110, 100, 92, 82, 86, 72, 64, 48, 55, 40};
        for (int i = 0; i < pontos.length - 1; i++) {
            JPanel linha = new JPanel();
            linha.setBackground(Navegacao.AZUL);
            linha.setBounds(35 + (i * 34), pontos[i], 32, 2);
            resumo.add(linha);
        }

        page.add(action("Novo Agendamento", "Agendar um servico", 28, 440, () -> new NovoAgendamentoTela().setVisible(true)));
        page.add(action("Novo Cliente", "Cadastrar cliente", 238, 440, () -> new NovoClienteTela().setVisible(true)));
        page.add(action("Novo Servico", "Adicionar servico", 448, 440, () -> Navegacao.mensagem(this, "Cadastro de servico em desenvolvimento.")));
        page.add(action("Lancamento Financeiro", "Adicionar receita/despesa", 658, 440, () -> new FinanceiroTela().setVisible(true)));
    }

    private JPanel metric(String titulo, String valor, String detalhe, String icon, int x, int y) {
        JPanel card = Navegacao.card();
        card.setBounds(x, y, 190, 88);

        JLabel bola = Navegacao.circle(icon, 14, Navegacao.AZUL, Navegacao.AZUL_CLARO);
        bola.setBounds(18, 24, 38, 38);
        card.add(bola);

        JLabel t = Navegacao.label(titulo, 11, Font.BOLD, Navegacao.TEXTO_SUAVE);
        t.setBounds(72, 18, 110, 18);
        card.add(t);

        JLabel v = Navegacao.label(valor, valor.startsWith("R$") ? 18 : 24, Font.BOLD, Navegacao.TEXTO);
        v.setBounds(72, 38, 120, 28);
        card.add(v);

        JLabel d = Navegacao.label(detalhe, 10, Font.BOLD, Navegacao.VERDE);
        d.setBounds(72, 64, 110, 16);
        card.add(d);

        return card;
    }

    private void addAgendaRow(JPanel panel, int y, AppDados.Agendamento a) {
        panel.add(text(a.horario, 16, y, 50));
        JLabel pet = Navegacao.circle(a.pet.substring(0, 1).toUpperCase(), 11, Color.WHITE, Navegacao.AZUL_2);
        pet.setBounds(72, y - 4, 26, 26);
        panel.add(pet);
        panel.add(text(a.pet, 112, y, 70));
        panel.add(text(a.servico, 205, y, 100));
        panel.add(text(a.cliente, 322, y, 90));
        JPanel badge = status(a.status);
        badge.setBounds(430, y - 1, 76, 22);
        panel.add(badge);
    }

    private JLabel text(String value, int x, int y, int w) {
        JLabel label = Navegacao.label(value, 11, Font.BOLD, Navegacao.TEXTO);
        label.setBounds(x, y, w, 18);
        return label;
    }

    private JPanel status(String status) {
        if ("Cancelado".equals(status)) {
            return Navegacao.badge("Cancelado", new Color(255, 230, 225), Color.RED);
        }
        if ("Pendente".equals(status)) {
            return Navegacao.badge("Pendente", new Color(255, 240, 214), Navegacao.LARANJA);
        }
        return Navegacao.badge("Confirmado", new Color(220, 248, 232), Navegacao.VERDE);
    }

    private JPanel action(String titulo, String subtitulo, int x, int y, Runnable run) {
        JPanel card = Navegacao.card();
        card.setBounds(x, y, 190, 72);
        card.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        card.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent e) {
                run.run();
            }
        });

        JLabel icon = Navegacao.circle("+", 20, Navegacao.AZUL, Navegacao.AZUL_CLARO);
        icon.setBounds(18, 18, 36, 36);
        card.add(icon);

        JLabel t = Navegacao.label(titulo, 13, Font.BOLD, Navegacao.AZUL);
        t.setBounds(68, 16, 120, 20);
        card.add(t);

        JLabel s = Navegacao.label(subtitulo, 11, Font.PLAIN, Navegacao.TEXTO_SUAVE);
        s.setBounds(68, 36, 120, 18);
        card.add(s);

        return card;
    }

    public static void main(String[] args) {
        java.awt.EventQueue.invokeLater(() -> new DashboardTela().setVisible(true));
    }
}
