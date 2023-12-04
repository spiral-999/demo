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
    private Map<Long, Correios> correiosMapa; 
    private AtomicLong proximoId; 
    private ArrayList<Correios> correios;
    public CorreiosService() {
        correios = new ArrayList<Correios>();
        correiosMapa = new HashMap<>();
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
                    Correios correio = (Correios) obj;
                    correios.add(correio);
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
    public Correios[] getCorreios() {
        return correios.toArray(new Correios[correios.size()]);
    }
    public Correios addCorreios(Correios correio) {
        long id = proximoId.getAndIncrement();
        correio.setId(id);
        correiosMapa.put(id, correio);
        correios.add(correio);
        recriarMapa();
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("correios.dat"))) {
            oos.writeObject(correios);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return correio;
    }
    public boolean enviarCorreios(long id) {
        Iterator<Correios> iterator = correios.iterator();
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
        for (Correios p : correios) {
            long id = novoId.getAndIncrement();
            p.setId(id);
            novoMapa.put(id, p);
        }
        correiosMapa = novoMapa;
    }
    private void saveToFile() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("correios.dat"))) {
            for (Correios p : correios) {
                oos.writeObject(p);
            }
        } catch (IOException e) {
        }
    }
    public Correios atualizarCorreios(long id, Correios novo) {
        for (Correios correio : correios) {
            if (correio.getId() == id) {
                correio.setNome(novo.getNome());
                correio.setData(novo.getData());
                saveToFile();
                return correio;
            }
        }
        return null;
    }
}