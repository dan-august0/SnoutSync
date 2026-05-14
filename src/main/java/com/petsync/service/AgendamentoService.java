package com.petsync.service;

import com.petsync.dao.AgendamentoDAO;
import com.petsync.model.Agendamento;

import java.util.List;

public class AgendamentoService {

    private AgendamentoDAO agendamentoDAO = new AgendamentoDAO();

    public void agendar(Agendamento agendamento) {

        agendamentoDAO.salvar(agendamento);

        System.out.println("Agendamento realizado com sucesso!");
    }

    public List<Agendamento> listarAgendamentos() {

        return agendamentoDAO.listarTodos();
    }

    public void cancelarAgendamento(int id) {

        Agendamento agendamento = agendamentoDAO.buscarPorId(id);

        if (agendamento != null) {

            agendamento.setStatus("CANCELADO");

            System.out.println("Agendamento cancelado!");

        } else {

            System.out.println("Agendamento não encontrado.");
        }
    }

    public void concluirAgendamento(int id) {

        Agendamento agendamento = agendamentoDAO.buscarPorId(id);

        if (agendamento != null) {

            agendamento.setStatus("CONCLUIDO");

            System.out.println("Agendamento concluído!");

        } else {

            System.out.println("Agendamento não encontrado.");
        }
    }
}