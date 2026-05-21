package com.petsync.model;

public class Servico {

    private int id;
    private String nome;
    private double precoPequeno;
    private double precoMedio;
    private double precoGrande;

    public Servico() {
    }

    public Servico(int id, String nome, double precoPequeno, double precoMedio, double precoGrande) {
        this.id = id;
        this.nome = nome;
        this.precoPequeno = precoPequeno;
        this.precoMedio = precoMedio;
        this.precoGrande = precoGrande;
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

    public double getPrecoPequeno() {
        return precoPequeno;
    }

    public void setPrecoPequeno(double precoPequeno) {
        this.precoPequeno = precoPequeno;
    }

    public double getPrecoMedio() {
        return precoMedio;
    }

    public void setPrecoMedio(double precoMedio) {
        this.precoMedio = precoMedio;
    }

    public double getPrecoGrande() {
        return precoGrande;
    }

    public void setPrecoGrande(double precoGrande) {
        this.precoGrande = precoGrande;
    }
}
