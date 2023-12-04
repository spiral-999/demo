package com.example.demo.entities;

public class Telegrama extends Correios {
    int ano;
    public Telegrama(String nome, double data, int ano) {
        super(nome, data);
        this.ano = ano;
    }
    @Override
    public String toString() {
        return "Telegrama de : " + nome + " - " + ano + " - " + data;
    }
    public int getAno() {
        return ano;
    }
    public void setAno(int ano) {
        this.ano = ano;
    }
}
