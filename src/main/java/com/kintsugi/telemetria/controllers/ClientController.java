package com.kintsugi.telemetria.controllers;

import com.kintsugi.telemetria.models.Client;
import com.kintsugi.telemetria.services.ClientService;
import com.kintsugi.telemetria.utils.ErrorMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/clients")
public class ClientController {

    @Autowired
    ClientService clientService;

    @GetMapping("")
    public ResponseEntity<?> listAllClients() {
        return clientService.listAllClients();
    }

    @PostMapping("")
    public ResponseEntity<?> createClient(@RequestBody Client client) {
        return clientService.createClient(client);
    }
}
