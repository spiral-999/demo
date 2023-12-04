package com.example.demo.entities;

public class Encomenda extends Correios {
    String empresa;
    public Encomenda(String nome, double data, String empresa) {
        super(nome, data);
        this.empresa = empresa;
    }
    @Override
    public String toString() {
        return "Encomenda de : " + nome + " - " + empresa + " - " + data;
    }
    public String getEmpresa() {
        return empresa;
    }
    public void setEmpresa(String empresa) {
        this.empresa = empresa;
    }
}