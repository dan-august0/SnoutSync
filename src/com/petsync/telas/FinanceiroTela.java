package com.petsync.telas;

import java.awt.Color;
import java.awt.Font;
import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class FinanceiroTela extends JFrame {

    public FinanceiroTela() {
        Navegacao.configurarJanela(this, "PetSync - Financeiro");
        add(Navegacao.criarSidebar(this, "Financeiro"));
        Navegacao.adicionarTopo(this, "Financeiro");

        JPanel faturamento = criarCard("Faturamento por mes", "R$" + AppDados.faturamentoEstimado(), AppDados.agendamentos.size() + " atendimentos");
        faturamento.setBounds(320, 125, 140, 80);
        add(faturamento);

        JPanel planos = criarCard("Planos ativos", "R$ " + (AppDados.totalPlanos() * 90), AppDados.totalPlanos() + " planos");
        planos.setBounds(555, 125, 140, 80);
        add(planos);

        JPanel pendentes = criarCard("Pagamentos pendentes", "R$160", "2 atendimentos");
        pendentes.setBounds(795, 125, 140, 80);
        add(pendentes);

        JLabel lancamentos = new JLabel("Lancamentos Recentes");
        lancamentos.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        lancamentos.setForeground(Navegacao.TEXTO);
        lancamentos.setBounds(320, 235, 200, 22);
        add(lancamentos);

        JPanel tabela = new JPanel();
        tabela.setLayout(null);
        tabela.setBackground(Navegacao.CINZA);
        tabela.setBounds(320, 265, 615, 155);
        add(tabela);

        adicionarCabecalho(tabela);

        int y = 34;
        for (AppDados.Agendamento agendamento : AppDados.agendamentos) {
            adicionarLinha(tabela, agendamento, y);
            y += 24;
        }

        javax.swing.JButton relatorio = Navegacao.botaoAzul("Gerar relatorio financeiro", 12);
        relatorio.setBounds(570, 470, 180, 38);
        add(relatorio);
    }

    private int valor(String servico) {
        if ("Banho + Tosa".equals(servico)) {
            return 120;
        }
        if ("Banho".equals(servico)) {
            return 70;
        }
        return 55;
    }

    private JPanel criarCard(String titulo, String valor, String detalhe) {
        JPanel card = Navegacao.card(16);

        JLabel tituloLabel = new JLabel(titulo);
        tituloLabel.setFont(new Font("Segoe UI", Font.PLAIN, 10));
        tituloLabel.setForeground(Navegacao.TEXTO);
        tituloLabel.setBounds(15, 10, 115, 18);
        card.add(tituloLabel);

        JLabel valorLabel = new JLabel(valor);
        valorLabel.setFont(new Font("Segoe UI", Font.BOLD, 13));
        valorLabel.setBounds(15, 32, 110, 20);
        card.add(valorLabel);

        JLabel detalheLabel = new JLabel(detalhe);
        detalheLabel.setFont(new Font("Segoe UI", Font.PLAIN, 9));
        detalheLabel.setBounds(15, 52, 110, 16);
        card.add(detalheLabel);

        return card;
    }

    private void adicionarCabecalho(JPanel tabela) {
        String[] colunas = {"Data", "Cliente", "Pet", "Servico", "Tipo", "Forma de pagto", "Valor", "Status"};
        int[] x = {12, 75, 160, 225, 330, 395, 505, 555};
        for (int i = 0; i < colunas.length; i++) {
            JLabel label = new JLabel(colunas[i]);
            label.setFont(new Font("Segoe UI", Font.BOLD, 8));
            label.setBounds(x[i], 10, 80, 14);
            tabela.add(label);
        }
    }

    private void adicionarLinha(JPanel tabela, AppDados.Agendamento agendamento, int y) {
        adicionarTexto(tabela, agendamento.data, 12, y, 60, Color.BLACK);
        adicionarTexto(tabela, agendamento.cliente, 75, y, 85, Color.BLACK);
        adicionarTexto(tabela, agendamento.pet, 160, y, 55, Color.BLACK);
        adicionarTexto(tabela, agendamento.servico, 225, y, 95, Color.BLACK);
        adicionarTexto(tabela, tipoCliente(agendamento.cliente), 330, y, 55, Color.BLACK);
        adicionarTexto(tabela, "Pix", 395, y, 75, Color.BLACK);
        adicionarTexto(tabela, "R$" + valor(agendamento.servico), 505, y, 45, new Color(65, 170, 40));
        adicionarTexto(tabela, agendamento.status, 555, y, 65, corStatus(agendamento.status));
    }

    private void adicionarTexto(JPanel tabela, String texto, int x, int y, int largura, Color cor) {
        JLabel label = new JLabel(texto);
        label.setFont(new Font("Segoe UI", Font.PLAIN, 8));
        label.setForeground(cor);
        label.setBounds(x, y, largura, 16);
        tabela.add(label);
    }

    private String tipoCliente(String tutor) {
        for (AppDados.Cliente cliente : AppDados.clientes) {
            if (cliente.tutor.equals(tutor)) {
                return cliente.tipo;
            }
        }
        return "Avulso";
    }

    private Color corStatus(String status) {
        if ("Confirmado".equals(status)) {
            return new Color(65, 170, 40);
        }
        if ("Cancelado".equals(status)) {
            return new Color(225, 130, 35);
        }
        return Navegacao.AZUL;
    }

    public static void main(String[] args) {
        java.awt.EventQueue.invokeLater(() -> new FinanceiroTela().setVisible(true));
    }
}
