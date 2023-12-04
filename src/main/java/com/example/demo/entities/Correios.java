package com.example.demo.entities;

import java.io.Serializable;
public class Correios implements Serializable {
    protected String nome;
    protected double data;
    private long id;
    public Correios() {
        this.nome = "";
        this.data = 0;
    }
    public Correios(String nome, double data) {
        this.nome = nome;
        this.data = data;
    }
    @Override
    public String toString() {
        return id + " - " + nome + " - " + data;
    }
    public static void main(String[] args) {
    }
    public String getNome() {
        return nome;
    }
    public double getData() {
        return data;
    }
    public long getId() {
        return id;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }
    public void setData(double data) {
        this.data = data;
    }
    public void setId(long id) {
        this.id = id;
    }
}