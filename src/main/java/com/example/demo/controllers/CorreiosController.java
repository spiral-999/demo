package com.example.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.demo.entities.Correios;
import com.example.demo.service.CorreiosService;
@RestController
@RequestMapping("/correios")
public class CorreiosController {
    @Autowired
    private CorreiosService correiosService;
    @GetMapping("/listar")
    public ResponseEntity<Correios[]> getCorreios() {
        Correios[] correios = correiosService.getCorreios();
        return ResponseEntity.ok(correios);
    }
    @PostMapping("/adicionar")
    public ResponseEntity<Correios> createCorreios(@RequestBody Correios correios) {
        Correios novo = correiosService.addCorreios(correios);
        return ResponseEntity.status(HttpStatus.CREATED).body(novo);
    }
    @DeleteMapping("/enviar/{id}")
    public ResponseEntity<Void> enviarCorreios(@PathVariable(name = "id") long id) {
        boolean enviado = correiosService.enviarCorreios(id);
        if (enviado) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    @PutMapping("/update/{id}")
    public ResponseEntity<Correios> updateCorreios(@PathVariable(name = "id") long id, @RequestBody Correios correios) {
        Correios correioNovo = correiosService.atualizarCorreios(id, correios);
        if (correioNovo != null) {
            return ResponseEntity.ok(correioNovo);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}