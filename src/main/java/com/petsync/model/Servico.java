package com.petsync.model;

public class Servico {

    private int id;
    private String nome;
    private double preco;
    private int duracaoMinutos;

    public Servico() {
    }

    public Servico(int id, String nome,
                    double preco, int duracaoMinutos) {

        this.id = id;
        this.nome = nome;
        this.preco = preco;
        this.duracaoMinutos = duracaoMinutos;
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

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }

    public int getDuracaoMinutos() {
        return duracaoMinutos;
    }

    public void setDuracaoMinutos(int duracaoMinutos) {
        this.duracaoMinutos = duracaoMinutos;
    }
}