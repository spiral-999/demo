package com.example.demo.controllers;

import java.util.HashMap;
import java.util.Map;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import com.example.demo.entities.Correios;

public class ClienteCorreios {
    private final String URL = "http://localhost:8080";
    private final RestTemplate restTemplate;
    public ClienteCorreios() {
        this.restTemplate = new RestTemplate();
    }
    public ResponseEntity<Correios[]> getCorreios() {
        String url = URL + "/correios/listar";
        return restTemplate.getForEntity(url, Correios[].class);
    }
    public ResponseEntity<Correios> createCorreios(Correios correio) {
        String url = URL + "/correios/adicionar";
        return restTemplate.postForEntity(url, correio, Correios.class);
    }
    public ResponseEntity<Void> enviarCorreios(long id) {
        String url = URL + "/correios/enviar/" + id;
        return restTemplate.exchange(url, HttpMethod.DELETE, null, Void.class);
    }
    public ResponseEntity<Correios> atualizarCorreio(long id, Correios correio) {
        String url = URL + "/correios/update/{id}";
        Map<String, Long> urlVariables = new HashMap<>();
        urlVariables.put("id", id);
        HttpEntity<Correios> request = new HttpEntity<>(correio);
        return restTemplate.exchange(url, HttpMethod.PUT, request, Correios.class, urlVariables);
    }

}