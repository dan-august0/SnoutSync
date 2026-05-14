package com.petsync;

import com.petsync.model.*;
import com.petsync.service.AgendamentoService;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {

        Cliente cliente = new Cliente(
                1,
                "Danillo",
                "12999999999",
                "danillo@email.com"
        );

        Pet pet = new Pet(
                1,
                "Thor",
                "Cachorro",
                "Shih-tzu",
                3,
                cliente
        );

        Servico banho = new Servico(
                1,
                "Banho",
                50.0,
                60
        );

        Servico tosa = new Servico(
                2,
                "Tosa",
                40.0,
                45
        );

        List<Servico> servicos =
                new ArrayList<>();

        servicos.add(banho);
        servicos.add(tosa);

        Agendamento agendamento =
                new Agendamento(
                        1,
                        pet,
                        servicos,
                        LocalDate.now(),
                        LocalTime.now(),
                        "AGENDADO",
                        "Cliente prefere perfume suave"
                );

        AgendamentoService service =
                new AgendamentoService();

        service.agendar(agendamento);

        System.out.println("\nLISTA DE AGENDAMENTOS:");

        for (Agendamento a :
                service.listarAgendamentos()) {

            System.out.println(
                    a.getPet().getNome()
            );

            System.out.println(
                    a.getStatus()
            );
        }

        service.concluirAgendamento(1);

        System.out.println("\nSTATUS ATUALIZADO:");

        for (Agendamento a :
                service.listarAgendamentos()) {

            System.out.println(
                    a.getStatus()
            );
        }
    }
}