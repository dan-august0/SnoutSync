package com.petsync.model;

public class Pet {

    private int id;
    private int clienteId;
    private String nome;
    private String especie;
    private String raca;
    private String porte;

    public Pet() {
    }

    public Pet(int id, int clienteId, String nome, String especie, String raca, String porte) {
        this.id = id;
        this.clienteId = clienteId;
        this.nome = nome;
        this.especie = especie;
        this.raca = raca;
        this.porte = porte;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getClienteId() {
        return clienteId;
    }

    public void setClienteId(int clienteId) {
        this.clienteId = clienteId;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEspecie() {
        return especie;
    }

    public void setEspecie(String especie) {
        this.especie = especie;
    }

    public String getRaca() {
        return raca;
    }

    public void setRaca(String raca) {
        this.raca = raca;
    }

    public String getPorte() {
        return porte;
    }

    public void setPorte(String porte) {
        this.porte = porte;
    }
}
