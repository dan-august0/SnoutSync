package com.petsync.telas;

import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class ClientesTela extends JFrame {

    public ClientesTela() {
        JPanel page = Navegacao.page(this, "Clientes e Pets", "Clientes e Pets", "Gerencie seus clientes e seus pets.");

        JButton novo = Navegacao.botaoPrimario("+ Novo Cliente");
        novo.setBounds(820, 28, 150, 34);
        novo.addActionListener(e -> new NovoClienteTela().setVisible(true));
        page.add(novo);

        JPanel tabela = Navegacao.card();
        tabela.setBounds(28, 105, 944, 490);
        page.add(tabela);

        JTextField busca = Navegacao.campo("Buscar cliente ou pet...");
        busca.setBounds(20, 28, 430, 36);
        tabela.add(busca);

        cabecalho(tabela);

        int y = 118;
        for (AppDados.Cliente c : AppDados.clientes) {
            linha(tabela, c, y);
            y += 54;
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
        String[] colunas = {"Cliente", "Pets", "Contato", "Tipo", "Acoes"};
        int[] xs = {35, 280, 450, 640, 820};
        for (int i = 0; i < colunas.length; i++) {
            JLabel c = Navegacao.label(colunas[i], 11, Font.BOLD, Navegacao.TEXTO);
            c.setBounds(xs[i], 82, 120, 20);
            tabela.add(c);
        }
    }

    private void linha(JPanel tabela, AppDados.Cliente c, int y) {
        tabela.add(text(c.tutor, 35, y, 180));
        tabela.add(text("1 pet", 280, y, 80));
        tabela.add(text(c.telefone, 450, y, 140));
        tabela.add(text("Plano".equals(c.tipo) ? "Plano mensal" : "Avulso", 640, y, 120));
        tabela.add(text("Ver", 820, y, 40));
        tabela.add(text("Editar", 865, y, 60));
    }

    private JLabel text(String texto, int x, int y, int w) {
        JLabel label = Navegacao.label(texto, 11, Font.BOLD, Navegacao.TEXTO);
        label.setBounds(x, y, w, 18);
        return label;
    }

    public static void main(String[] args) {
        java.awt.EventQueue.invokeLater(() -> new ClientesTela().setVisible(true));
    }
}
