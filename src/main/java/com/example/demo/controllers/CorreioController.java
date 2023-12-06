package com.example.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.demo.entities.Correios;
import com.example.demo.service.CorreiosService;

@RestController
@RequestMapping("/correios")
public class CorreioController {
    @Autowired
    private CorreiosService produtoService;
    @GetMapping("/listar")
    public ResponseEntity<Correios[]> getProdutos() {
        Correios[] produtos = produtoService.getProdutos();
        return ResponseEntity.ok(produtos);
    }
    @PostMapping("/adicionar")
    public ResponseEntity<Correios> createProduto(@RequestBody Correios produto) {
        Correios novoProduto = produtoService.addProduto(produto);
        return ResponseEntity.status(HttpStatus.CREATED).body(novoProduto);
    }
    @DeleteMapping("/deletar/{id}")
    public ResponseEntity<Void> deleteProduto(@PathVariable(name = "id") long id) {
        boolean deleted = produtoService.deletarProduto(id);
        if (deleted) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    @PutMapping("/update/{id}")
    public ResponseEntity<Correios> updateProduto(@PathVariable(name = "id") long id, @RequestBody Correios produto) {
        Correios produtoTrocado = produtoService.updateProduto(id, produto);
        if (produtoTrocado != null) {
            return ResponseEntity.ok(produtoTrocado);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}