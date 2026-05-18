package com.petsync.telas;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JButton;


public class DashboardTela extends JFrame {

    public DashboardTela() {

        setTitle("PetSync Dashboard");

        setSize(1000,600);

        setLocationRelativeTo(null);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setLayout(null);

        setResizable(false);

        getContentPane().setBackground(
                new Color(245,247,255)
        );

        // SIDEBAR

        JPanel sidebar = new JPanel();

        sidebar.setLayout(null);

        sidebar.setBackground(
                new Color(168,177,255)
        );

        sidebar.setBounds(0,0,220,600);

        add(sidebar);

        JLabel logo = new JLabel("PETSYNC");

        logo.setForeground(Color.WHITE);

        logo.setFont(
                new Font("Segoe UI", Font.BOLD, 28)
        );

        logo.setBounds(35,30,200,40);

        sidebar.add(logo);

        JLabel subtitulo = new JLabel("Pet e Shop");

        subtitulo.setForeground(Color.WHITE);

        subtitulo.setBounds(70,65,100,20);

        sidebar.add(subtitulo);

        JLabel dashboard =
                criarMenu("Dashboard",140);

        sidebar.add(dashboard);

        JLabel agendamentos =
                criarMenu("Agendamentos",210);
        agendamentos.addMouseListener(new MouseAdapter() {

    public void mouseClicked(MouseEvent e) {

        new AgendamentosTela().setVisible(true);

        dispose();
    }
});

        sidebar.add(agendamentos);

        JLabel clientes =
                criarMenu("Clientes & Pets",280);
        clientes.addMouseListener(new MouseAdapter() {

    public void mouseClicked(MouseEvent e) {

        new ClientesTela().setVisible(true);

        dispose();
    }
});

        sidebar.add(clientes);

        JLabel financeiro =
                criarMenu("Financeiro",350);
        financeiro.addMouseListener(new MouseAdapter() {

    public void mouseClicked(MouseEvent e) {

        new FinanceiroTela().setVisible(true);

        dispose();
    }
});

        sidebar.add(financeiro);

        JLabel novoCliente =
                criarMenu("+ Novo Cliente",500);
        novoCliente.addMouseListener(new MouseAdapter() {

    public void mouseClicked(MouseEvent e) {

        new NovoClienteTela().setVisible(true);

    }
});

        sidebar.add(novoCliente);

        // TITULO

        JLabel titulo = new JLabel("Dashboard");

        titulo.setFont(
                new Font("Segoe UI", Font.BOLD, 30)
        );

        titulo.setForeground(
                new Color(74,99,255)
        );

        titulo.setBounds(260,30,300,40);

        add(titulo);

        // CARDS

        JPanel card1 = criarCard(
                "Agendamentos Hoje",
                "12"
        );

        card1.setBounds(260,110,200,110);

        add(card1);

        JPanel card2 = criarCard(
                "Clientes Ativos",
                "48"
        );

        card2.setBounds(500,110,200,110);

        add(card2);

        JPanel card3 = criarCard(
                "Planos Mensais",
                "22"
        );

        card3.setBounds(740,110,200,110);

        add(card3);

        // AGENDA

        JLabel agendaTitulo =
                new JLabel("Agenda de Hoje");

        agendaTitulo.setFont(
                new Font("Segoe UI", Font.BOLD, 20)
        );

        agendaTitulo.setForeground(
                new Color(74,99,255)
        );

        agendaTitulo.setBounds(260,270,220,30);

        add(agendaTitulo);

        JPanel agenda1 = criarAgendamento(
                "09:00 - Thor",
                "Banho"
        );

        agenda1.setBounds(260,320,300,70);

        add(agenda1);

        JPanel agenda2 = criarAgendamento(
                "11:00 - Luna",
                "Banho + Tosa"
        );

        agenda2.setBounds(260,410,300,70);

        add(agenda2);

        // CANCELADOS

        JLabel cancelados =
                new JLabel("Cancelados");

        cancelados.setFont(
                new Font("Segoe UI", Font.BOLD, 20)
        );

        cancelados.setForeground(
                new Color(74,99,255)
        );

        cancelados.setBounds(650,270,200,30);

        add(cancelados);

        JPanel cancelado =
                criarAgendamento(
                        "14:00 - Mel",
                        "Tosa"
                );

        cancelado.setBounds(650,320,300,70);

        add(cancelado);

        // NAVEGACAO

        dashboard.addMouseListener(
                new MouseAdapter() {

            public void mouseClicked(MouseEvent e) {

                new DashboardTela()
                        .setVisible(true);

                dispose();
            }
        });

        agendamentos.addMouseListener(
                new MouseAdapter() {

            public void mouseClicked(MouseEvent e) {

                try {

                    new AgendamentosTela()
                            .setVisible(true);

                    dispose();

                } catch (Exception ex) {

                    System.out.println(
                            "Tela Agendamentos não encontrada"
                    );
                }
            }
        });

        clientes.addMouseListener(
                new MouseAdapter() {

            public void mouseClicked(MouseEvent e) {

                try {

                    new ClientesTela()
                            .setVisible(true);

                    dispose();

                } catch (Exception ex) {

                    System.out.println(
                            "Tela Clientes não encontrada"
                    );
                }
            }
        });

        financeiro.addMouseListener(
                new MouseAdapter() {

            public void mouseClicked(MouseEvent e) {

                try {

                    new FinanceiroTela()
                            .setVisible(true);

                    dispose();

                } catch (Exception ex) {

                    System.out.println(
                            "Tela Financeiro não encontrada"
                    );
                }
            }
        });

        novoCliente.addMouseListener(
                new MouseAdapter() {

            public void mouseClicked(MouseEvent e) {

                try {

                    new NovoClienteTela()
                            .setVisible(true);

                } catch (Exception ex) {

                    System.out.println(
                            "Tela Novo Cliente não encontrada"
                    );
                }
            }
        });
    }

    private JLabel criarMenu(
            String texto,
            int y
    ) {

        JLabel label = new JLabel(texto);

        label.setForeground(Color.WHITE);

        label.setFont(
                new Font("Segoe UI", Font.BOLD, 22)
        );

        label.setBounds(20,y,220,30);

        return label;
    }

    private JPanel criarCard(
            String titulo,
            String valor
    ) {

        JPanel card = new JPanel();

        card.setLayout(null);

        card.setBackground(Color.WHITE);

        card.setBorder(
                BorderFactory.createLineBorder(
                        new Color(220,220,220)
                )
        );

        JLabel tituloLabel =
                new JLabel(titulo);

        tituloLabel.setForeground(
                new Color(74,99,255)
        );

        tituloLabel.setFont(
                new Font("Segoe UI",
                        Font.BOLD,
                        16)
        );

        tituloLabel.setBounds(
                20,20,180,20
        );

        card.add(tituloLabel);

        JLabel valorLabel =
                new JLabel(valor);

        valorLabel.setFont(
                new Font("Segoe UI",
                        Font.BOLD,
                        32)
        );

        valorLabel.setBounds(
                20,50,100,40
        );

        card.add(valorLabel);

        return card;
    }

    private JPanel criarAgendamento(
            String horario,
            String servico
    ) {

        JPanel painel = new JPanel();

        painel.setLayout(null);

        painel.setBackground(Color.WHITE);

        painel.setBorder(
                BorderFactory.createLineBorder(
                        new Color(220,220,220)
                )
        );

        JLabel horarioLabel =
                new JLabel(horario);

        horarioLabel.setFont(
                new Font("Segoe UI",
                        Font.BOLD,
                        18)
        );

        horarioLabel.setBounds(
                20,10,220,25
        );

        painel.add(horarioLabel);

        JLabel servicoLabel =
                new JLabel(servico);

        servicoLabel.setForeground(
                new Color(74,99,255)
        );

        servicoLabel.setFont(
                new Font("Segoe UI",
                        Font.PLAIN,
                        16)
        );

        servicoLabel.setBounds(
                20,38,200,20
        );

        painel.add(servicoLabel);

        return painel;
    }

    public static void main(String[] args) {

        java.awt.EventQueue.invokeLater(() -> {

            new DashboardTela().setVisible(true);

        });
    }
}