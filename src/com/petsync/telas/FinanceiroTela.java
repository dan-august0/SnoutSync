package com.petsync.telas;

import java.awt.Color;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class FinanceiroTela extends JFrame {

    public FinanceiroTela() {
        JPanel page = Navegacao.page(this, "Financeiro", "Financeiro", "Acompanhe receitas, despesas e relatorios.");

        JButton relatorio = Navegacao.botaoSecundario("Relatorio");
        relatorio.setBounds(720, 28, 105, 34);
        page.add(relatorio);

        JButton novo = Navegacao.botaoPrimario("+ Novo Lancamento");
        novo.setBounds(835, 28, 138, 34);
        page.add(novo);

        int faturamento = AppDados.faturamentoEstimado();
        page.add(metric("Receitas (mes)", "R$ " + (faturamento + 2590) + ",00", "+10% vs mes anterior", Navegacao.VERDE, 28, 105));
        page.add(metric("Despesas (mes)", "R$ 2.590,00", "-6% vs mes anterior", Color.RED, 238, 105));
        page.add(metric("Lucro (mes)", "R$ " + faturamento + ",00", "+29% vs mes anterior", Navegacao.VERDE, 448, 105));
        page.add(metric("Total em aberto", "R$ 1.250,00", "3 titulos", Navegacao.LARANJA, 658, 105));

        JPanel fluxo = Navegacao.card();
        fluxo.setBounds(28, 220, 420, 220);
        page.add(fluxo);
        JLabel ft = Navegacao.label("Fluxo de Caixa (mes)", 14, Font.BOLD, Navegacao.TEXTO);
        ft.setBounds(18, 18, 220, 22);
        fluxo.add(ft);
        for (int i = 0; i < 8; i++) {
            JPanel barra = new JPanel();
            barra.setBackground(Navegacao.VERDE);
            barra.setBounds(35 + i * 45, 160 - (i % 4) * 22, 18, 35 + (i % 4) * 22);
            fluxo.add(barra);

            JPanel despesa = new JPanel();
            despesa.setBackground(new Color(255, 111, 86));
            despesa.setBounds(58 + i * 45, 175 - (i % 3) * 8, 10, 20 + (i % 3) * 8);
            fluxo.add(despesa);
        }

        JPanel categorias = Navegacao.card();
        categorias.setBounds(470, 220, 250, 220);
        page.add(categorias);
        JLabel ct = Navegacao.label("Categorias de Despesas", 14, Font.BOLD, Navegacao.TEXTO);
        ct.setBounds(18, 18, 220, 22);
        categorias.add(ct);
        JLabel donut = Navegacao.circle("40%", 26, Navegacao.AZUL, Navegacao.AZUL_CLARO);
        donut.setBounds(65, 65, 95, 95);
        categorias.add(donut);
        categorias.add(info("Produtos", "40%", 165, 70, Navegacao.AZUL));
        categorias.add(info("Salarios", "30%", 165, 96, Navegacao.VERDE));
        categorias.add(info("Aluguel", "15%", 165, 122, Navegacao.LARANJA));
        categorias.add(info("Outros", "15%", 165, 148, Navegacao.TEXTO_SUAVE));

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
        linha(tabela, "20/05/2026", "Banho e Tosa - Rex", "Servicos", "Receita", "R$ 120,00", "Pago", 96);
        linha(tabela, "20/05/2026", "Consulta - Luna", "Servicos", "Receita", "R$ 80,00", "Pago", 126);
        linha(tabela, "19/05/2026", "Aluguel", "Aluguel", "Despesa", "R$ 1.200,00", "Pago", 156);
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

    public static void main(String[] args) {
        java.awt.EventQueue.invokeLater(() -> new FinanceiroTela().setVisible(true));
    }
}
