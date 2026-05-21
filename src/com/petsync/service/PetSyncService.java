package com.petsync.service;

import com.petsync.dao.AgendamentoDAO;
import com.petsync.dao.ClienteDAO;
import com.petsync.dao.PetDAO;
import com.petsync.dao.ServicoDAO;
import com.petsync.model.Agendamento;
import com.petsync.model.Cliente;
import com.petsync.model.Pet;
import com.petsync.model.Servico;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public class PetSyncService {

    private final ClienteDAO clienteDAO = new ClienteDAO();
    private final PetDAO petDAO = new PetDAO();
    private final ServicoDAO servicoDAO = new ServicoDAO();
    private final AgendamentoDAO agendamentoDAO = new AgendamentoDAO();

    public void cadastrarClienteComPet(String nomeTutor, String telefone, String nomePet, String raca, String tipo) throws SQLException {
        validarObrigatorio(nomeTutor, "Nome do tutor");
        validarObrigatorio(nomePet, "Nome do pet");

        Cliente cliente = new Cliente();
        cliente.setNome(nomeTutor);
        cliente.setTelefone(telefone);
        cliente.setTipo(tipo);

        int clienteId = clienteDAO.salvar(cliente);

        Pet pet = new Pet();
        pet.setClienteId(clienteId);
        pet.setNome(nomePet);
        pet.setEspecie("Cachorro");
        pet.setRaca(raca);
        pet.setPorte("M");

        petDAO.salvar(pet);
    }

    public void cadastrarAgendamento(String nomeCliente, String nomePet, String nomeServico,
                                     LocalDate data, LocalTime hora, String status) throws SQLException {
        validarObrigatorio(nomeCliente, "Cliente");
        validarObrigatorio(nomePet, "Pet");
        validarObrigatorio(nomeServico, "Servico");

        Integer petId = petDAO.buscarIdPorClienteEPet(nomeCliente, nomePet);
        if (petId == null) {
            Cliente cliente = clienteDAO.buscarPorNome(nomeCliente);
            int clienteId;

            if (cliente == null) {
                cliente = new Cliente();
                cliente.setNome(nomeCliente);
                cliente.setTelefone("");
                cliente.setTipo("AVULSO");
                clienteId = clienteDAO.salvar(cliente);
            } else {
                clienteId = cliente.getId();
            }

            Pet pet = new Pet();
            pet.setClienteId(clienteId);
            pet.setNome(nomePet);
            pet.setEspecie("Cachorro");
            pet.setRaca("Nao informado");
            pet.setPorte("M");
            petId = petDAO.salvar(pet);
        }

        Integer servicoId = servicoDAO.buscarIdPorNome(nomeServico);
        if (servicoId == null) {
            Servico servico = new Servico();
            servico.setNome(nomeServico);
            servico.setPrecoPequeno(55);
            servico.setPrecoMedio(70);
            servico.setPrecoGrande(120);
            servicoId = servicoDAO.salvar(servico);
        }

        Agendamento agendamento = new Agendamento();
        agendamento.setPetId(petId);
        agendamento.setServicoId(servicoId);
        agendamento.setData(data);
        agendamento.setHora(hora);
        agendamento.setStatus(status);
        agendamento.setObservacoes("");

        agendamentoDAO.salvar(agendamento);
    }

    public List<Cliente> listarClientes() throws SQLException {
        return clienteDAO.listarTodos();
    }

    public List<Agendamento> listarAgendamentos() throws SQLException {
        return agendamentoDAO.listarTodos();
    }

    public void cancelarAgendamento(int id) throws SQLException {
        agendamentoDAO.atualizarStatus(id, "CANCELADO");
    }

    public void concluirAgendamento(int id) throws SQLException {
        agendamentoDAO.atualizarStatus(id, "CONCLUIDO");
    }

    private void validarObrigatorio(String valor, String campo) {
        if (valor == null || valor.trim().isEmpty()) {
            throw new IllegalArgumentException(campo + " e obrigatorio.");
        }
    }
}
