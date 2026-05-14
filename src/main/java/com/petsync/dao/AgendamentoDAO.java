package com.petsync.dao;

import com.petsync.model.Agendamento;

import java.util.ArrayList;
import java.util.List;

public class AgendamentoDAO {

    private List<Agendamento> agendamentos =
            new ArrayList<>();

    public void salvar(Agendamento agendamento) {

        agendamentos.add(agendamento);
    }

    public List<Agendamento> listarTodos() {

        return agendamentos;
    }

    public Agendamento buscarPorId(int id) {

        for (Agendamento agendamento : agendamentos) {

            if (agendamento.getId() == id) {

                return agendamento;
            }
        }

        return null;
    }
}