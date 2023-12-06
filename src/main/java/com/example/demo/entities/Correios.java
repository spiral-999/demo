package com.example.demo.entities;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

public class Correios implements Serializable {
    private String nome;
    private double distancia;
    private long id;
    public Correios() {
        this.nome = "";
        this.distancia = 0;
    }
    public Correios(String nome, double distancia) {
        this.nome = nome;
        this.distancia = distancia;
    }
    @Override
    public String toString() {
        return "ID : " + id + " --- " + "NOME : " + nome + " - " + "RASTREADOR DE ENTREGA(KM) : " + distancia;
    }
    public static void main(String[] args) {

    }
    public String getNome() {
        return nome;
    }
    public double getDistancia() {
        return distancia;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }
    public void setDistancia(double distancia) {
        this.distancia = distancia;
    }
    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }
}