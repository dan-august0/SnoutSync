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
        public String tutor;
        public String telefone;
        public String pet;
        public String raca;
        public String tipo;

        public Cliente(String tutor, String telefone, String pet, String raca, String tipo) {
            this.tutor = tutor;
            this.telefone = telefone;
            this.pet = pet;
            this.raca = raca;
            this.tipo = tipo;
        }
    }

    public static class Agendamento {
        public String cliente;
        public String pet;
        public String servico;
        public String data;
        public String horario;
        public String status;

        public Agendamento(String cliente, String pet, String servico, String data, String horario, String status) {
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
                        cliente.getNome(),
                        cliente.getTelefone(),
                        "Pet cadastrado",
                        "Nao informado",
                        tipoTela(cliente.getTipo())
                ));
            }

            for (com.petsync.model.Agendamento agendamento : service.listarAgendamentos()) {
                agendamentos.add(new Agendamento(
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

    public static int faturamentoEstimado() {
        int total = 0;
        for (Agendamento agendamento : agendamentos) {
            if ("Cancelado".equals(agendamento.status)) {
                continue;
            }
            if ("Banho + Tosa".equals(agendamento.servico)) {
                total += 120;
            } else if ("Banho".equals(agendamento.servico)) {
                total += 70;
            } else {
                total += 55;
            }
        }
        return total;
    }

    private static void carregarExemplos() {
        clientes.add(new Cliente("Joao Silva", "(11) 99999-1111", "Thor", "Golden Retriever", "Plano"));
        clientes.add(new Cliente("Maria Souza", "(11) 99999-2222", "Luna", "Shih-tzu", "Avulso"));
        clientes.add(new Cliente("Carlos Lima", "(11) 99999-3333", "Mel", "Poodle", "Plano"));

        agendamentos.add(new Agendamento("Joao Silva", "Thor", "Banho", "19/05/2026", "10:00", "Confirmado"));
        agendamentos.add(new Agendamento("Maria Souza", "Luna", "Banho + Tosa", "19/05/2026", "11:30", "Pendente"));
        agendamentos.add(new Agendamento("Carlos Lima", "Mel", "Tosa", "19/05/2026", "14:00", "Cancelado"));
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
            return "AGENDADO";
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
}
