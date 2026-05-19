package com.petsync.telas;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class AgendamentosTela extends JFrame {

    public AgendamentosTela() {
        Navegacao.configurarJanela(this, "PetSync - Agendamentos");
        add(Navegacao.criarSidebar(this, "Agendamentos"));
        Navegacao.adicionarTopo(this, "Agendamento");

        JLabel titulo = new JLabel("Lista de Agendamento");
        titulo.setFont(new Font("Segoe UI", Font.BOLD, 13));
        titulo.setForeground(Navegacao.AZUL);
        titulo.setBounds(310, 112, 180, 22);
        add(titulo);

        JButton botaoNovo = Navegacao.botaoCinza("+ Novo agendamento");
        botaoNovo.setBounds(470, 112, 150, 26);
        botaoNovo.addActionListener(e -> new NovoAgendamentoTela().setVisible(true));
        add(botaoNovo);

        JPanel painel = Navegacao.card(16);
        painel.setBounds(285, 150, 690, 405);
        add(painel);

        JTextField busca = Navegacao.campo("Buscar por pet ou cliente...");
        busca.setBounds(15, 20, 350, 28);
        painel.add(busca);

        JComboBox<String> status = new JComboBox<>(new String[]{"Todos os status", "Agendado", "Concluido", "Cancelado"});
        status.setBounds(375, 20, 130, 28);
        painel.add(status);

        JComboBox<String> periodo = new JComboBox<>(new String[]{"Hoje", "Esta semana", "Este mes"});
        periodo.setBounds(515, 20, 145, 28);
        painel.add(periodo);

        adicionarCabecalho(painel);

        int y = 98;
        for (AppDados.Agendamento agendamento : AppDados.agendamentos) {
            adicionarLinha(painel, agendamento, y);
            y += 45;
        }
    }

    private void adicionarCabecalho(JPanel painel) {
        String[] colunas = {"Horario", "Pets", "Tutor", "Servico", "Tipo"};
        int[] x = {35, 130, 250, 420, 585};
        for (int i = 0; i < colunas.length; i++) {
            JLabel label = new JLabel(colunas[i]);
            label.setFont(new Font("Segoe UI", Font.BOLD, 11));
            label.setBounds(x[i], 72, 90, 18);
            painel.add(label);
        }
    }

    private void adicionarLinha(JPanel painel, AppDados.Agendamento agendamento, int y) {
        adicionarTexto(painel, agendamento.horario, 35, y, 80, Color.BLACK);
        adicionarTexto(painel, agendamento.pet, 130, y, 100, Color.BLACK);
        adicionarTexto(painel, agendamento.cliente, 250, y, 145, Color.BLACK);
        adicionarTexto(painel, agendamento.servico, 420, y, 130, Color.BLACK);
        adicionarTexto(painel, tipoCliente(agendamento.cliente), 585, y, 80, corTipo(tipoCliente(agendamento.cliente)));
    }

    private void adicionarTexto(JPanel painel, String texto, int x, int y, int largura, Color cor) {
        JLabel label = new JLabel(texto);
        label.setFont(new Font("Segoe UI", Font.PLAIN, 11));
        label.setForeground(cor);
        label.setBounds(x, y, largura, 22);
        painel.add(label);
    }

    private String tipoCliente(String tutor) {
        for (AppDados.Cliente cliente : AppDados.clientes) {
            if (cliente.tutor.equals(tutor)) {
                return "Plano".equals(cliente.tipo) ? "Plano" : "Avulso";
            }
        }
        return "Avulso";
    }

    private Color corTipo(String tipo) {
        return "Plano".equals(tipo) ? Navegacao.AZUL : new Color(65, 170, 40);
    }

    public static void main(String[] args) {
        java.awt.EventQueue.invokeLater(() -> new AgendamentosTela().setVisible(true));
    }
}
