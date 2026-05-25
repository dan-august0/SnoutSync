package com.petsync.telas;

import com.petsync.service.PetSyncService;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class AppDados {

    public static class Cliente {
        public int id;
        public int petId;
        public String tutor;
        public String telefone;
        public String pet;
        public String raca;
        public String tipo;

        public Cliente(String tutor, String telefone, String pet, String raca, String tipo) {
            this(0, 0, tutor, telefone, pet, raca, tipo);
        }

        public Cliente(int id, int petId, String tutor, String telefone, String pet, String raca, String tipo) {
            this.id = id;
            this.petId = petId;
            this.tutor = tutor;
            this.telefone = telefone;
            this.pet = pet;
            this.raca = raca;
            this.tipo = tipo;
        }
    }

    public static class Agendamento {
        public int id;
        public String cliente;
        public String pet;
        public String servico;
        public String data;
        public String horario;
        public String status;

        public Agendamento(String cliente, String pet, String servico, String data, String horario, String status) {
            this(0, cliente, pet, servico, data, horario, status);
        }

        public Agendamento(int id, String cliente, String pet, String servico, String data, String horario, String status) {
            this.id = id;
            this.cliente = cliente;
            this.pet = pet;
            this.servico = servico;
            this.data = data;
            this.horario = horario;
            this.status = status;
        }
    }

    public static final List<Cliente> clientes = new ArrayList<>();
    public static final List<Agendamento> agendamentos = new ArrayList<>();

    private static final PetSyncService service = new PetSyncService();
    private static boolean usandoBanco = false;

    static {
        recarregar();
    }

    public static void recarregar() {
        clientes.clear();
        agendamentos.clear();

        try {
            for (com.petsync.model.Cliente cliente : service.listarClientes()) {
                clientes.add(new Cliente(
                        cliente.getId(),
                        cliente.getPetId(),
                        cliente.getNome(),
                        cliente.getTelefone(),
                        valorOuPadrao(cliente.getPetNome(), "Pet cadastrado"),
                        valorOuPadrao(cliente.getPetRaca(), "Nao informado"),
                        tipoTela(cliente.getTipo())
                ));
            }

            for (com.petsync.model.Agendamento agendamento : service.listarAgendamentos()) {
                agendamentos.add(new Agendamento(
                        agendamento.getId(),
                        agendamento.getClienteNome(),
                        agendamento.getPetNome(),
                        agendamento.getServicoNome(),
                        agendamento.getData().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")),
                        agendamento.getHora().toString().substring(0, 5),
                        statusTela(agendamento.getStatus())
                ));
            }

            usandoBanco = true;
        } catch (SQLException ex) {
            usandoBanco = false;
            carregarExemplos();
            System.out.println("Banco indisponivel. Usando dados de exemplo: " + ex.getMessage());
        }
    }

    public static boolean estaUsandoBanco() {
        return usandoBanco;
    }

    public static void adicionarCliente(Cliente cliente) {
        if (usandoBanco) {
            try {
                service.cadastrarClienteComPet(
                        cliente.tutor,
                        cliente.telefone,
                        cliente.pet,
                        cliente.raca,
                        tipoBanco(cliente.tipo)
                );
                recarregar();
                return;
            } catch (SQLException ex) {
                System.out.println("Erro ao salvar cliente no banco: " + ex.getMessage());
            }
        }
        clientes.add(cliente);
    }

    public static void atualizarCliente(Cliente cliente) {
        if (usandoBanco) {
            try {
                service.atualizarClienteComPet(
                        cliente.id,
                        cliente.petId,
                        cliente.tutor,
                        cliente.telefone,
                        cliente.pet,
                        cliente.raca,
                        tipoBanco(cliente.tipo)
                );
                recarregar();
                return;
            } catch (SQLException ex) {
                System.out.println("Erro ao atualizar cliente no banco: " + ex.getMessage());
            }
        }

        for (int i = 0; i < clientes.size(); i++) {
            if (clientes.get(i).id == cliente.id) {
                clientes.set(i, cliente);
                return;
            }
        }
    }

    public static void excluirCliente(Cliente cliente) {
        if (usandoBanco) {
            try {
                service.excluirCliente(cliente.id);
                recarregar();
                return;
            } catch (SQLException ex) {
                System.out.println("Erro ao excluir cliente no banco: " + ex.getMessage());
            }
        }
        clientes.remove(cliente);
    }

    public static void adicionarAgendamento(Agendamento agendamento) {
        if (usandoBanco) {
            try {
                service.cadastrarAgendamento(
                        agendamento.cliente,
                        agendamento.pet,
                        agendamento.servico,
                        parseData(agendamento.data),
                        parseHora(agendamento.horario),
                        statusBanco(agendamento.status)
                );
                recarregar();
                return;
            } catch (SQLException ex) {
                System.out.println("Erro ao salvar agendamento no banco: " + ex.getMessage());
            }
        }
        agendamentos.add(agendamento);
    }

    public static void atualizarAgendamento(Agendamento agendamento) {
        if (usandoBanco) {
            try {
                service.atualizarAgendamento(
                        agendamento.id,
                        agendamento.cliente,
                        agendamento.pet,
                        agendamento.servico,
                        parseData(agendamento.data),
                        parseHora(agendamento.horario),
                        statusBanco(agendamento.status)
                );
                recarregar();
                return;
            } catch (SQLException ex) {
                System.out.println("Erro ao atualizar agendamento no banco: " + ex.getMessage());
            }
        }

        for (int i = 0; i < agendamentos.size(); i++) {
            if (agendamentos.get(i).id == agendamento.id) {
                agendamentos.set(i, agendamento);
                return;
            }
        }
    }

    public static void excluirAgendamento(Agendamento agendamento) {
        if (usandoBanco) {
            try {
                service.excluirAgendamento(agendamento.id);
                recarregar();
                return;
            } catch (SQLException ex) {
                System.out.println("Erro ao excluir agendamento no banco: " + ex.getMessage());
            }
        }
        agendamentos.remove(agendamento);
    }

    public static int totalPlanos() {
        int total = 0;
        for (Cliente cliente : clientes) {
            if ("Plano".equals(cliente.tipo)) {
                total++;
            }
        }
        return total;
    }

    public static int agendamentosHoje() {
        return agendamentos.size();
    }

    public static int servicosRealizados() {
        int total = 0;
        for (Agendamento agendamento : agendamentos) {
            if (!"Cancelado".equals(agendamento.status)) {
                total++;
            }
        }
        return total;
    }

    public static int faturamentoEstimado() {
        int total = 0;
        for (Agendamento agendamento : agendamentos) {
            if ("Cancelado".equals(agendamento.status)) {
                continue;
            }
            total += valorServico(agendamento.servico);
        }
        return total;
    }

    public static int despesasEstimadas() {
        int receitas = faturamentoEstimado();
        if (receitas == 0) {
            return 0;
        }
        return Math.max(120, (int) Math.round(receitas * 0.28));
    }

    public static int lucroEstimado() {
        return faturamentoEstimado() - despesasEstimadas();
    }

    public static int totalEmAberto() {
        int total = 0;
        for (Agendamento agendamento : agendamentos) {
            if ("Pendente".equals(agendamento.status)) {
                total += valorServico(agendamento.servico);
            }
        }
        return total;
    }

    public static int totalPorStatus(String status) {
        int total = 0;
        for (Agendamento agendamento : agendamentos) {
            if (status.equals(agendamento.status)) {
                total++;
            }
        }
        return total;
    }

    public static int valorServico(String servico) {
        if ("Banho + Tosa".equals(servico)) {
            return 120;
        }
        if ("Banho".equals(servico)) {
            return 70;
        }
        if ("Tosa".equals(servico)) {
            return 55;
        }
        return 0;
    }

    public static String relatorioFinanceiro() {
        StringBuilder texto = new StringBuilder();
        texto.append("RELATORIO FINANCEIRO - SNOUTSYNC\n\n");
        texto.append("Clientes cadastrados: ").append(clientes.size()).append("\n");
        texto.append("Agendamentos totais: ").append(agendamentos.size()).append("\n");
        texto.append("Confirmados: ").append(totalPorStatus("Confirmado")).append("\n");
        texto.append("Pendentes: ").append(totalPorStatus("Pendente")).append("\n");
        texto.append("Cancelados: ").append(totalPorStatus("Cancelado")).append("\n\n");
        texto.append("Receitas estimadas: R$ ").append(faturamentoEstimado()).append(",00\n");
        texto.append("Despesas estimadas: R$ ").append(despesasEstimadas()).append(",00\n");
        texto.append("Lucro estimado: R$ ").append(lucroEstimado()).append(",00\n");
        texto.append("Total em aberto: R$ ").append(totalEmAberto()).append(",00\n\n");
        texto.append("Lancamentos:\n");

        for (Agendamento agendamento : agendamentos) {
            texto.append("- ")
                    .append(agendamento.data).append(" | ")
                    .append(agendamento.servico).append(" - ")
                    .append(agendamento.pet).append(" | ")
                    .append(agendamento.status).append(" | R$ ")
                    .append(valorServico(agendamento.servico)).append(",00\n");
        }

        return texto.toString();
    }

    private static void carregarExemplos() {
        clientes.add(new Cliente(1, 1, "Joao Silva", "(11) 99999-1111", "Thor", "Golden Retriever", "Plano"));
        clientes.add(new Cliente(2, 2, "Maria Souza", "(11) 99999-2222", "Luna", "Shih-tzu", "Avulso"));
        clientes.add(new Cliente(3, 3, "Carlos Lima", "(11) 99999-3333", "Mel", "Poodle", "Plano"));

        agendamentos.add(new Agendamento(1, "Joao Silva", "Thor", "Banho", "19/05/2026", "10:00", "Confirmado"));
        agendamentos.add(new Agendamento(2, "Maria Souza", "Luna", "Banho + Tosa", "19/05/2026", "11:30", "Pendente"));
        agendamentos.add(new Agendamento(3, "Carlos Lima", "Mel", "Tosa", "19/05/2026", "14:00", "Cancelado"));
    }

    private static String tipoTela(String tipoBanco) {
        return "PLANO".equals(tipoBanco) ? "Plano" : "Avulso";
    }

    private static String tipoBanco(String tipoTela) {
        return "Plano".equals(tipoTela) ? "PLANO" : "AVULSO";
    }

    private static String statusTela(String statusBanco) {
        if ("CANCELADO".equals(statusBanco)) {
            return "Cancelado";
        }
        if ("CONCLUIDO".equals(statusBanco)) {
            return "Confirmado";
        }
        return "Pendente";
    }

    private static String statusBanco(String statusTela) {
        if ("Cancelado".equals(statusTela)) {
            return "CANCELADO";
        }
        if ("Confirmado".equals(statusTela)) {
            return "CONCLUIDO";
        }
        return "AGENDADO";
    }

    private static LocalDate parseData(String data) {
        if (data.length() == 8) {
            return LocalDate.parse(data, DateTimeFormatter.ofPattern("dd/MM/yy"));
        }
        return LocalDate.parse(data, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
    }

    private static LocalTime parseHora(String hora) {
        return LocalTime.parse(hora.length() == 5 ? hora : "00:00");
    }

    private static String valorOuPadrao(String valor, String padrao) {
        return valor == null || valor.trim().isEmpty() ? padrao : valor;
    }
}
