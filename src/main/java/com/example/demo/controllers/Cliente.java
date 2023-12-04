package com.example.demo.controllers;

import com.example.demo.entities.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class Cliente {
    public static void main(String[] args) throws IOException {
        ClienteCorreios correioCliente = new ClienteCorreios();
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String opcao;
        while (true) {
            System.out.println("Digite a opção desejada: ");
            System.out.println("1 - Listar");
            System.out.println("2 - Cadastrar");
            System.out.println("3 - Trocar");
            System.out.println("4 - Enviar");
            System.out.println("5 - Sair");
            opcao = reader.readLine();
            switch (opcao) {
                case "1":
                    ResponseEntity<Correios[]> response = correioCliente.getCorreios();
                    if (response.getStatusCode() == HttpStatus.OK) {
                        Correios[] correio = response.getBody();
                        for (Correios p : correio) {
                            System.out.println(p.toString());
                        }
                    } else {
                        System.out.println("Erro");
                    }
                    break;
                case "2":
                    Correios correio = dadosCorreio(reader);
                    correioCliente.createCorreios(correio);
                    break;
                case "3":
                    System.out.println("Digite o id : ");
                    int id = Integer.parseInt(reader.readLine());
                    correio = dadosCorreio(reader);
                    correioCliente.atualizarCorreio(id, correio);
                    break;
                case "4":
                    System.out.println("Digite o id : ");
                    id = Integer.parseInt(reader.readLine());
                    correioCliente.enviarCorreios(id);
                    break;
                case "5":
                    System.out.println("Tchau !");
                    return;
                default:
                    System.out.println("invalido");
                    break;
            }
        }
    }
    public static Correios dadosCorreio(BufferedReader reader) throws IOException {
        Correios correios = new Correios();
        System.out.println("Digite o nome : ");
        correios.setNome(reader.readLine());
        System.out.println("Digite a data : ");
        correios.setData(Double.parseDouble(reader.readLine()));
        return correios;
    }
}
