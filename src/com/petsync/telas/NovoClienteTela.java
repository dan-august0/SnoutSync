package com.petsync.telas;

import java.awt.Color;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class NovoClienteTela extends JFrame {

    private JTextField nome;
    private JTextField telefone;
    private JTextField email;
    private JTextField endereco;
    private JTextField pet;
    private JTextField raca;
    private JComboBox<String> especie;
    private JComboBox<String> sexo;

    public NovoClienteTela() {
        Navegacao.configurarModal(this, "SnoutSync - Novo Cliente");

        JPanel page = new JPanel(null);
        page.setOpaque(false);
        page.setBounds(0, 0, Navegacao.APP_W, Navegacao.APP_H);
        add(page);

        JLabel titulo = Navegacao.label("Novo Cliente", 24, Font.BOLD, Navegacao.TEXTO);
        titulo.setBounds(45, 32, 250, 30);
        page.add(titulo);

        JLabel sub = Navegacao.label("Cadastre um novo cliente.", 12, Font.PLAIN, Navegacao.TEXTO_SUAVE);
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

        JPanel dados = Navegacao.card();
        dados.setBounds(45, 115, 510, 470);
        page.add(dados);

        JLabel dt = Navegacao.label("Dados do Cliente", 14, Font.BOLD, Navegacao.TEXTO);
        dt.setBounds(22, 20, 200, 22);
        dados.add(dt);

        nome = campoComLabel(dados, "Nome completo", "Digite o nome completo", 22, 68, 450);
        telefone = campoComLabel(dados, "Telefone", "(11) 90000-0000", 22, 160, 210);
        email = campoComLabel(dados, "E-mail", "email@exemplo.com", 260, 160, 210);
        endereco = campoComLabel(dados, "Endereco", "Rua, numero, bairro, cidade - UF", 22, 252, 450);
        JTextField obs = campoComLabel(dados, "Observacoes", "Observacoes sobre o cliente (opcional)", 22, 344, 450);

        JPanel pets = Navegacao.card();
        pets.setBounds(575, 115, 580, 470);
        page.add(pets);

        JLabel pt = Navegacao.label("Pets", 14, Font.BOLD, Navegacao.TEXTO);
        pt.setBounds(22, 20, 120, 22);
        pets.add(pt);

        JButton addPet = Navegacao.botaoPrimario("+ Adicionar Pet");
        addPet.setBounds(420, 18, 135, 32);
        pets.add(addPet);

        JLabel avatar = Navegacao.circle("P", 34, Color.WHITE, Navegacao.AZUL_2);
        avatar.setBounds(28, 82, 70, 70);
        pets.add(avatar);

        pet = campoComLabel(pets, "Nome do pet", "Ex: Rex", 125, 70, 400);

        JLabel especieLabel = Navegacao.label("Especie", 12, Font.BOLD, Navegacao.TEXTO);
        especieLabel.setBounds(125, 160, 140, 18);
        pets.add(especieLabel);
        especie = new JComboBox<>(new String[]{"Cachorro", "Gato", "Outro"});
        especie.setBounds(125, 184, 400, 38);
        pets.add(especie);

        raca = campoComLabel(pets, "Raca", "Ex: Golden Retriever", 125, 250, 400);

        JTextField nascimento = campoComLabel(pets, "Data de nascimento", "dd/mm/aaaa", 125, 340, 190);
        JLabel sexoLabel = Navegacao.label("Sexo", 12, Font.BOLD, Navegacao.TEXTO);
        sexoLabel.setBounds(335, 340, 120, 18);
        pets.add(sexoLabel);
        sexo = new JComboBox<>(new String[]{"Selecione", "Macho", "Femea"});
        sexo.setBounds(335, 364, 190, 38);
        pets.add(sexo);

        JButton cancelar = Navegacao.botaoSecundario("Cancelar");
        cancelar.setBounds(785, 625, 135, 38);
        cancelar.addActionListener(e -> dispose());
        page.add(cancelar);

        JButton salvar = Navegacao.botaoPrimario("Salvar Cliente");
        salvar.setBounds(940, 625, 180, 38);
        salvar.addActionListener(e -> salvar());
        page.add(salvar);
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
        if (nome.getText().trim().isEmpty() || pet.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Preencha nome do cliente e nome do pet.");
            return;
        }

        AppDados.adicionarCliente(new AppDados.Cliente(
                nome.getText().trim(),
                telefone.getText().trim(),
                pet.getText().trim(),
                raca.getText().trim(),
                "Plano"
        ));

        JOptionPane.showMessageDialog(this, "Cliente salvo com sucesso.");
        Navegacao.abrir(this, new ClientesTela());
    }

    public static void main(String[] args) {
        java.awt.EventQueue.invokeLater(() -> new NovoClienteTela().setVisible(true));
    }
}
