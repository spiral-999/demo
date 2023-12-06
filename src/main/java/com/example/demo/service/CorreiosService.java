package com.example.demo.service;

import com.example.demo.entities.Correios;
import java.io.*;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class CorreiosService {
    private Map<Long, Correios> produtosMapa; 
    private AtomicLong proximoId; 
    private ArrayList<Correios> produtos;
    public CorreiosService() {
        produtos = new ArrayList<Correios>();
        produtosMapa = new HashMap<>();
        proximoId = new AtomicLong(1);
        if (!fileExists("correios.dat")) {
            createFile("correios.dat");
        }
        loadFromFile();
    }
    private void loadFromFile() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("correios.dat"))) {
            Object obj;
            while ((obj = ois.readObject()) != null) {
                if (obj instanceof Correios) {
                    Correios produto = (Correios) obj;
                    produtos.add(produto);
                }
            }
        } catch (EOFException e) {
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
    private void createFile(String filename) {
        try {
            File file = new File(filename);
            file.createNewFile();
            System.out.println("Arquivo criado: " + file.getAbsolutePath());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private boolean fileExists(String filename) {
        File file = new File(filename);
        return file.exists();
    }
    public Correios[] getProdutos() {
        return produtos.toArray(new Correios[produtos.size()]);
    }
    public Correios addProduto(Correios produto) {
        long id = proximoId.getAndIncrement();
        produto.setId(id);
        produtosMapa.put(id, produto);
        produtos.add(produto);
        recriarMapa();
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("correios.dat"))) {
            oos.writeObject(produtos);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return produto;
    }
    public boolean deletarProduto(long id) {
        Iterator<Correios> iterator = produtos.iterator();
        while (iterator.hasNext()) {
            Correios p = iterator.next();
            if (p.getId() == id) {
                iterator.remove();
                recriarMapa();
                saveToFile();
                return true;
            }
        }
        return false;
    }
    private void recriarMapa() {
        Map<Long, Correios> novoMapa = new HashMap<>();
        AtomicLong novoId = new AtomicLong(1);
        for (Correios p : produtos) {
            long id = novoId.getAndIncrement();
            p.setId(id);
            novoMapa.put(id, p);
        }
        produtosMapa = novoMapa;
    }
    private void saveToFile() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("correios.dat"))) {
            for (Correios p : produtos) {
                oos.writeObject(p);
            }
        } catch (IOException e) {
        }
    }
    public Correios updateProduto(long id, Correios produtoAtualizado) {
        for (Correios produto : produtos) {
            if (produto.getId() == id) {
                produto.setNome(produtoAtualizado.getNome());
                produto.setDistancia(produtoAtualizado.getDistancia());
                saveToFile();
                return produto;
            }
        }
        return null;
    }
}