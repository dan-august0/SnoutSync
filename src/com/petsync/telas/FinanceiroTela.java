package com.petsync.telas;

import java.awt.Color;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class FinanceiroTela extends JFrame {

    public FinanceiroTela() {
        JPanel page = Navegacao.page(this, "Financeiro", "Financeiro", "Acompanhe receitas, despesas e relatorios.");

        JButton relatorio = Navegacao.botaoSecundario("Relatorio");
        relatorio.setBounds(720, 28, 105, 34);
        relatorio.addActionListener(e -> mostrarRelatorio());
        page.add(relatorio);

        JButton novo = Navegacao.botaoPrimario("+ Novo Lancamento");
        novo.setBounds(835, 28, 138, 34);
        novo.addActionListener(e -> Navegacao.mensagem(this, "Os lancamentos sao gerados automaticamente pelos agendamentos cadastrados."));
        page.add(novo);

        int receitas = AppDados.faturamentoEstimado();
        int despesas = AppDados.despesasEstimadas();
        int lucro = AppDados.lucroEstimado();
        int aberto = AppDados.totalEmAberto();

        page.add(metric("Receitas (mes)", moeda(receitas), AppDados.servicosRealizados() + " servicos", Navegacao.VERDE, 28, 105));
        page.add(metric("Despesas (mes)", moeda(despesas), "Estimativa operacional", Color.RED, 238, 105));
        page.add(metric("Lucro (mes)", moeda(lucro), lucro >= 0 ? "Resultado positivo" : "Resultado negativo", lucro >= 0 ? Navegacao.VERDE : Color.RED, 448, 105));
        page.add(metric("Total em aberto", moeda(aberto), AppDados.totalPorStatus("Pendente") + " pendentes", Navegacao.LARANJA, 658, 105));

        JPanel fluxo = Navegacao.card();
        fluxo.setBounds(28, 220, 420, 220);
        page.add(fluxo);
        JLabel ft = Navegacao.label("Fluxo de Caixa (mes)", 14, Font.BOLD, Navegacao.TEXTO);
        ft.setBounds(18, 18, 220, 22);
        fluxo.add(ft);
        for (int i = 0; i < 8; i++) {
            JPanel barra = new JPanel();
            barra.setBackground(Navegacao.VERDE);
            int alturaReceita = Math.max(18, (receitas / 20) % 95 + (i % 3) * 8);
            barra.setBounds(35 + i * 45, 185 - alturaReceita, 18, alturaReceita);
            fluxo.add(barra);

            JPanel despesa = new JPanel();
            despesa.setBackground(new Color(255, 111, 86));
            int alturaDespesa = Math.max(12, (despesas / 25) % 55 + (i % 2) * 5);
            despesa.setBounds(58 + i * 45, 185 - alturaDespesa, 10, alturaDespesa);
            fluxo.add(despesa);
        }

        JPanel categorias = Navegacao.card();
        categorias.setBounds(470, 220, 250, 220);
        page.add(categorias);
        JLabel ct = Navegacao.label("Categorias de Despesas", 14, Font.BOLD, Navegacao.TEXTO);
        ct.setBounds(18, 18, 220, 22);
        categorias.add(ct);
        JLabel donut = Navegacao.circle(percentualDespesas(receitas, despesas) + "%", 24, Navegacao.AZUL, Navegacao.AZUL_CLARO);
        donut.setBounds(65, 65, 95, 95);
        categorias.add(donut);
        categorias.add(info("Operacional", "45%", 165, 70, Navegacao.AZUL));
        categorias.add(info("Produtos", "30%", 165, 96, Navegacao.VERDE));
        categorias.add(info("Aluguel", "15%", 165, 122, Navegacao.LARANJA));
        categorias.add(info("Outros", "10%", 165, 148, Navegacao.TEXTO_SUAVE));

        JPanel tabela = Navegacao.card();
        tabela.setBounds(28, 465, 944, 210);
        page.add(tabela);
        JLabel titulo = Navegacao.label("Lancamentos", 15, Font.BOLD, Navegacao.TEXTO);
        titulo.setBounds(18, 16, 160, 22);
        tabela.add(titulo);
        JTextField busca = Navegacao.campo("Buscar lancamento...");
        busca.setBounds(640, 16, 280, 34);
        tabela.add(busca);
        JComboBox<String> cat = new JComboBox<>(new String[]{"Todas as categorias", "Servicos", "Produtos", "Aluguel"});
        cat.setBounds(420, 16, 190, 34);
        tabela.add(cat);

        cabecalho(tabela);
        int y = 96;
        int limite = Math.min(4, AppDados.agendamentos.size());
        for (int i = 0; i < limite; i++) {
            AppDados.Agendamento agendamento = AppDados.agendamentos.get(i);
            linha(tabela,
                    agendamento.data,
                    agendamento.servico + " - " + agendamento.pet,
                    "Servicos",
                    "Cancelado".equals(agendamento.status) ? "Sem receita" : "Receita",
                    moeda("Cancelado".equals(agendamento.status) ? 0 : AppDados.valorServico(agendamento.servico)),
                    "Pendente".equals(agendamento.status) ? "Aberto" : "Confirmado".equals(agendamento.status) ? "Pago" : "Cancelado",
                    y);
            y += 30;
        }
    }

    private JPanel metric(String titulo, String valor, String detalhe, Color cor, int x, int y) {
        JPanel card = Navegacao.card();
        card.setBounds(x, y, 190, 88);
        JLabel t = Navegacao.label(titulo, 11, Font.BOLD, Navegacao.TEXTO_SUAVE);
        t.setBounds(18, 16, 150, 18);
        card.add(t);
        JLabel v = Navegacao.label(valor, 17, Font.BOLD, Navegacao.TEXTO);
        v.setBounds(18, 38, 160, 24);
        card.add(v);
        JLabel d = Navegacao.label(detalhe, 10, Font.BOLD, cor);
        d.setBounds(18, 62, 160, 16);
        card.add(d);
        return card;
    }

    private JLabel info(String nome, String valor, int x, int y, Color cor) {
        JLabel label = Navegacao.label(nome + "    " + valor, 11, Font.BOLD, cor);
        label.setBounds(x, y, 80, 18);
        return label;
    }

    private void cabecalho(JPanel tabela) {
        String[] colunas = {"Data", "Descricao", "Categoria", "Tipo", "Valor", "Status", "Acoes"};
        int[] xs = {20, 120, 360, 520, 640, 760, 865};
        for (int i = 0; i < colunas.length; i++) {
            JLabel c = Navegacao.label(colunas[i], 11, Font.BOLD, Navegacao.TEXTO);
            c.setBounds(xs[i], 65, 120, 18);
            tabela.add(c);
        }
    }

    private void linha(JPanel tabela, String data, String desc, String categoria, String tipo, String valor, String status, int y) {
        tabela.add(text(data, 20, y, 85));
        tabela.add(text(desc, 120, y, 190));
        tabela.add(text(categoria, 360, y, 100));
        tabela.add(text(tipo, 520, y, 85));
        tabela.add(text(valor, 640, y, 100));
        JPanel b = Navegacao.badge(status, new Color(220, 248, 232), Navegacao.VERDE);
        b.setBounds(760, y - 3, 70, 24);
        tabela.add(b);
        tabela.add(text("Editar", 865, y, 50));
    }

    private JLabel text(String texto, int x, int y, int w) {
        JLabel label = Navegacao.label(texto, 11, Font.BOLD, Navegacao.TEXTO);
        label.setBounds(x, y, w, 18);
        return label;
    }

    private String moeda(int valor) {
        return "R$ " + valor + ",00";
    }

    private int percentualDespesas(int receitas, int despesas) {
        if (receitas <= 0) {
            return 0;
        }
        return Math.min(99, (int) Math.round((despesas * 100.0) / receitas));
    }

    private void mostrarRelatorio() {
        JTextArea area = new JTextArea(AppDados.relatorioFinanceiro());
        area.setEditable(false);
        area.setFont(new Font("Consolas", Font.PLAIN, 12));
        JScrollPane scroll = new JScrollPane(area);
        scroll.setPreferredSize(new java.awt.Dimension(560, 360));
        JOptionPane.showMessageDialog(this, scroll, "Relatorio Financeiro", JOptionPane.INFORMATION_MESSAGE);
    }

    public static void main(String[] args) {
        java.awt.EventQueue.invokeLater(() -> new FinanceiroTela().setVisible(true));
    }
}
