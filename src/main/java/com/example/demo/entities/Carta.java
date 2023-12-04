package com.example.demo.entities;

public class Carta extends Correios {
    String nome;
    int selos;
    public Carta(String nome, double data, int selos) {
        super(nome, data);
        this.selos = selos;
    }
    @Override
    public String toString() {
        return "Carta de : " + nome + " - " + selos + " - " + data;
    }
    public int getSelos() {
        return selos;
    }
    public void setSelos(int selos) {
        this.selos = selos;
    }
}
