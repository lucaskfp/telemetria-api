package com.kintsugi.telemetria.services;

import com.kintsugi.telemetria.models.Client;
import com.kintsugi.telemetria.repositorys.ClientRepository;
import com.kintsugi.telemetria.utils.ErrorMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class ClientService {

    @Autowired
    ClientRepository clientRepository;

    public ResponseEntity<?> listAllClients() {
        try {
            return ResponseEntity.status(200).body(clientRepository.findAll());
        } catch (RuntimeException ex) {
            return ResponseEntity.status(500).body(new ErrorMessage("Erro no servidor"));
        }
    }

    public ResponseEntity<?> createClient(Client client) {
        try {
            return ResponseEntity.status(201).body(clientRepository.save(client));

        } catch (RuntimeException ex) {
            System.out.println(ex.getMessage());
            return ResponseEntity.status(500).body(new ErrorMessage("Erro no servidor"));
        }
    }
}
