package com.example.demo.controllers;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import com.example.demo.entities.Correios;

public class CorreioCliente {
    private final String URL = "http://localhost:8080";
    private final RestTemplate restTemplate;
    public CorreioCliente() {
        this.restTemplate = new RestTemplate();
    }
    public ResponseEntity<Correios[]> getProdutos() {
        String url = URL + "/correios/listar";
        return restTemplate.getForEntity(url, Correios[].class);
    }
    public ResponseEntity<Correios> createProduto(Correios produto) {
        String url = URL + "/correios/adicionar";
        return restTemplate.postForEntity(url, produto, Correios.class);
    }
    public ResponseEntity<Void> deleteProduto(long id) {
        String url = URL + "/correios/deletar/" + id;
        return restTemplate.exchange(url, HttpMethod.DELETE, null, Void.class);
    }
    public ResponseEntity<Correios> updateProduto(long id, Correios produto) {
        String url = URL + "/correios/update/{id}";
        Map<String, Long> urlVariables = new HashMap<>();
        urlVariables.put("id", id);
        HttpEntity<Correios> request = new HttpEntity<>(produto);
        return restTemplate.exchange(url, HttpMethod.PUT, request, Correios.class, urlVariables);
    }
}