package com.petsync.model;

public class Cliente {

    private int id;
    private String nome;
    private String telefone;
    private String tipo;

    public Cliente() {
    }

    public Cliente(int id, String nome, String telefone, String tipo) {
        this.id = id;
        this.nome = nome;
        this.telefone = telefone;
        this.tipo = tipo;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
}
