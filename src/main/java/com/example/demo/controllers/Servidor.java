package com.example.demo.controllers;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = { "com.example.demo", "com.example.demo.entities" })
public class Servidor {
    public static void main(String[] args) {
        SpringApplication.run(Servidor.class, args);
    }

}