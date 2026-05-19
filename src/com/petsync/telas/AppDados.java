package com.petsync.telas;

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

    static {
        clientes.add(new Cliente("Joao Silva", "(11) 99999-1111", "Thor", "Golden Retriever", "Plano"));
        clientes.add(new Cliente("Maria Souza", "(11) 99999-2222", "Luna", "Shih-tzu", "Avulso"));
        clientes.add(new Cliente("Carlos Lima", "(11) 99999-3333", "Mel", "Poodle", "Plano"));

        agendamentos.add(new Agendamento("Joao Silva", "Thor", "Banho", "19/05/2026", "10:00", "Confirmado"));
        agendamentos.add(new Agendamento("Maria Souza", "Luna", "Banho + Tosa", "19/05/2026", "11:30", "Pendente"));
        agendamentos.add(new Agendamento("Carlos Lima", "Mel", "Tosa", "19/05/2026", "14:00", "Cancelado"));
    }

    public static void adicionarCliente(Cliente cliente) {
        clientes.add(cliente);
    }

    public static void adicionarAgendamento(Agendamento agendamento) {
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
}
