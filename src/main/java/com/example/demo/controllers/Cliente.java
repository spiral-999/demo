package com.example.demo.controllers;

import com.example.demo.entities.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.Buffer;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class Cliente {
    public static void main(String[] args) throws IOException {
        CorreioCliente produtoCliente = new CorreioCliente();
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String opcao;
        while (true) {
            System.out.println("Digite a Opcao : ");
            System.out.println("1 - Listar");
            System.out.println("2 - Adicionar");
            System.out.println("3 - Atualizar");
            System.out.println("4 - Enviar");
            System.out.println("5 - Sair");
            opcao = reader.readLine();
            switch (opcao) {
                case "1":
                    ResponseEntity<Correios[]> response = produtoCliente.getProdutos();
                    if (response.getStatusCode() == HttpStatus.OK) {
                        Correios[] produtos = response.getBody();
                        for (Correios p : produtos) {
                            System.out.println(p.toString());
                        }
                    } else {
                        System.out.println("Erro . Status: " + response.getStatusCodeValue());
                    }
                    break;
                case "2":
                    Correios produto = dadosProduto(reader);
                    produtoCliente.createProduto(produto);
                    break;
                case "3":
                    System.out.println("Digite o ID : ");
                    int id = Integer.parseInt(reader.readLine());
                    produto = dadosProduto(reader);
                    produtoCliente.updateProduto(id, produto);
                    break;
                case "4":
                    System.out.println("Digite o ID: ");
                    id = Integer.parseInt(reader.readLine());
                    produtoCliente.deleteProduto(id);
                    break;
                case "5":
                    System.out.println("Tchau !");
                    return;
                default:
                    System.out.println("Escolha novamente");
                    break;
            }
        }
    }
    public static Correios dadosProduto(BufferedReader reader) throws IOException {
        Correios produto = new Correios();
        System.out.println("Qual o nome do Remetente?");
        produto.setNome(reader.readLine());
        System.out.println("Qual a distancia entre o Produto e o Destinatario?");
        produto.setDistancia(Double.parseDouble(reader.readLine()));
        return produto;
    }
}