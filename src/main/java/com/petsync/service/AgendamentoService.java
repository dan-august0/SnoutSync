package com.petsync.service;

import com.petsync.model.Agendamento;

import java.util.ArrayList;
import java.util.List;

public class AgendamentoService {

    private List<Agendamento> agendamentos =
            new ArrayList<>();

    public void agendar(Agendamento agendamento) {

        agendamentos.add(agendamento);

        System.out.println("Agendamento realizado com sucesso!");
    }

    public List<Agendamento> listarAgendamentos() {

        return agendamentos;
    }

    public void cancelarAgendamento(int id) {

        for (Agendamento agendamento : agendamentos) {

            if (agendamento.getId() == id) {

                agendamento.setStatus("CANCELADO");

                System.out.println("Agendamento cancelado!");

                return;
            }
        }

        System.out.println("Agendamento não encontrado.");
    }

    public void concluirAgendamento(int id) {

        for (Agendamento agendamento : agendamentos) {

            if (agendamento.getId() == id) {

                agendamento.setStatus("CONCLUIDO");

                System.out.println("Agendamento concluído!");

                return;
            }
        }

        System.out.println("Agendamento não encontrado.");
    }
}