package com.petsync.telas;

import java.awt.Color;
import java.awt.Font;
import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class DashboardTela extends JFrame {

    public DashboardTela() {
        Navegacao.configurarJanela(this, "PetSync - Dashboard");
        add(Navegacao.criarSidebar(this, "Dashboard"));
        Navegacao.adicionarTopo(this, "Dashboard");

        JPanel card1 = criarCard("Agendamentos Hoje", String.valueOf(AppDados.agendamentosHoje()));
        card1.setBounds(310, 115, 120, 68);
        add(card1);

        JPanel card2 = criarCard("Clientes Ativos", String.valueOf(AppDados.clientes.size()));
        card2.setBounds(455, 115, 120, 68);
        add(card2);

        JPanel card3 = criarCard("Planos Mensais", String.valueOf(AppDados.totalPlanos()));
        card3.setBounds(600, 115, 120, 68);
        add(card3);

        JPanel agendaPainel = Navegacao.card(18);
        agendaPainel.setBounds(310, 230, 280, 250);
        add(agendaPainel);

        JLabel agendaTitulo = new JLabel("Agenda de hoje");
        agendaTitulo.setFont(new Font("Segoe UI", Font.BOLD, 20));
        agendaTitulo.setForeground(Navegacao.AZUL);
        agendaTitulo.setBounds(28, 18, 220, 30);
        agendaPainel.add(agendaTitulo);

        JPanel canceladosPainel = Navegacao.card(18);
        canceladosPainel.setBounds(650, 230, 280, 250);
        add(canceladosPainel);

        JLabel cancelados = new JLabel("Agendamentos cancelados");
        cancelados.setFont(new Font("Segoe UI", Font.BOLD, 18));
        cancelados.setForeground(Navegacao.AZUL);
        cancelados.setBounds(28, 18, 230, 30);
        canceladosPainel.add(cancelados);

        int yAgenda = 65;
        int yCancelado = 65;
        for (AppDados.Agendamento agendamento : AppDados.agendamentos) {
            JPanel item = criarAgendamento(
                    agendamento.horario + " - " + agendamento.pet,
                    agendamento.cliente,
                    agendamento.servico,
                    agendamento.status
            );

            if ("Cancelado".equals(agendamento.status)) {
                item.setBounds(20, yCancelado, 240, 62);
                yCancelado += 78;
                canceladosPainel.add(item);
            } else {
                item.setBounds(20, yAgenda, 240, 62);
                yAgenda += 78;
                agendaPainel.add(item);
            }
        }
    }

    private JPanel criarCard(String titulo, String valor) {
        JPanel card = Navegacao.card(16);

        JLabel tituloLabel = new JLabel(titulo);
        tituloLabel.setForeground(Navegacao.AZUL);
        tituloLabel.setFont(new Font("Segoe UI", Font.PLAIN, 11));
        tituloLabel.setBounds(14, 10, 110, 17);
        card.add(tituloLabel);

        JLabel valorLabel = new JLabel(valor);
        valorLabel.setFont(new Font("Segoe UI", Font.BOLD, 21));
        valorLabel.setForeground(Navegacao.AZUL);
        valorLabel.setBounds(14, 30, 45, 28);
        card.add(valorLabel);

        JLabel sub = new JLabel(titulo.startsWith("Agendamentos") ? "3 Concluido" : titulo.startsWith("Clientes") ? "+2 esse mes" : "Ativos");
        sub.setFont(new Font("Segoe UI", Font.PLAIN, 9));
        sub.setForeground(new Color(75, 160, 80));
        sub.setBounds(50, 36, 75, 18);
        card.add(sub);

        return card;
    }

    private JPanel criarAgendamento(String horario, String tutor, String servico, String status) {
        JPanel painel = Navegacao.card(14);
        painel.setBackground(Navegacao.AZUL_CLARO);

        JLabel horarioLabel = new JLabel(horario);
        horarioLabel.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        horarioLabel.setBounds(15, 8, 125, 18);
        painel.add(horarioLabel);

        JLabel tutorLabel = new JLabel(tutor);
        tutorLabel.setFont(new Font("Segoe UI", Font.PLAIN, 9));
        tutorLabel.setForeground(new Color(90, 90, 100));
        tutorLabel.setBounds(68, 26, 120, 14);
        painel.add(tutorLabel);

        JLabel servicoLabel = new JLabel(servico);
        servicoLabel.setForeground(new Color(90, 90, 100));
        servicoLabel.setFont(new Font("Segoe UI", Font.PLAIN, 9));
        servicoLabel.setBounds(68, 40, 120, 14);
        painel.add(servicoLabel);

        JLabel statusLabel = new JLabel(status);
        statusLabel.setFont(new Font("Segoe UI", Font.PLAIN, 9));
        statusLabel.setForeground("Cancelado".equals(status) ? Color.RED : "Pendente".equals(status) ? new Color(160, 125, 0) : new Color(30, 150, 70));
        statusLabel.setBounds(180, 22, 58, 15);
        painel.add(statusLabel);

        return painel;
    }

    public static void main(String[] args) {
        java.awt.EventQueue.invokeLater(() -> new DashboardTela().setVisible(true));
    }
}
