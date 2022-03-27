package com.kintsugi.telemetria.controllers;

import com.kintsugi.telemetria.models.Driver;
import com.kintsugi.telemetria.repositorys.DriverRepository;
import com.kintsugi.telemetria.services.DriverService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/drivers")
public class DriverController {

    @Autowired
    DriverRepository driverRepository;

    @Autowired
    DriverService driverService;

    // Retorna todos os drivers ou [] vazio com status 200
    @GetMapping("")
    public ResponseEntity<?> listAllDrivers() {
        return driverService.listAllDrivers();
    }

    // Retorna um driver pelo id com status 200 ou "NO BODY" com status 404
    @GetMapping("/{id}")
    public ResponseEntity<?> getDriverById(@PathVariable("id") Integer id) {
        return driverService.getDriverById(id);
    }

    // Cria um driver e retorna ele com status 201
    @PostMapping("")
    public ResponseEntity<?> createDriver(@RequestBody Driver body) {
        return driverService.createDriver(body);
    }

    // Atualiza um driver e retorna ele com status 200
    @PutMapping("/{id}")
    public ResponseEntity<?> updateDriverbyId(@PathVariable("id") Integer id, @RequestBody Driver body) {
        return driverService.updateDriverById(id, body);
    }

    // Deleta um driver e retorna status 200
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteDriver(@PathVariable("id") Integer id) {
        return driverService.deleteDriverById(id);
    }

}
