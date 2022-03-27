package com.kintsugi.telemetria.services;

import java.util.List;

import com.kintsugi.telemetria.DTOs.DriverDTO;
import com.kintsugi.telemetria.models.Driver;
import com.kintsugi.telemetria.repositorys.DriverRepository;
import com.kintsugi.telemetria.utils.ErrorMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class DriverService {

    @Autowired
    DriverRepository driverRepository;

    // Retorna todos os drivers ou [] vazio com status 200
    public ResponseEntity<?> listAllDrivers() {
        try {
            List<Driver> list = driverRepository.findAll();

            return ResponseEntity.status(200).body(list.stream().map(d -> new DriverDTO(d)));
        } catch (RuntimeException ex) {
            return ResponseEntity.status(500).body(new ErrorMessage("Erro no servidor"));
        }
    }

    // Retorna um driver pelo id com status 200 ou "NO BODY" com status 404
    public ResponseEntity<?> getDriverById(Integer id) {
        try {
            Driver driver = this.driverRepository.findById(id).orElse(null);

            if (driver != null) {
                return ResponseEntity.status(200).body(new DriverDTO(driver));
            }

            return ResponseEntity.status(404).body(new ErrorMessage("Driver não encontrado"));
        } catch (RuntimeException ex) {
            return ResponseEntity.status(500).body(new ErrorMessage("Erro no servidor"));
        }
    }

    // Cria um driver e retorna ele com status 201
    public ResponseEntity<?> createDriver(Driver driver) {
        try {
            Driver findEmail = driverRepository.findByEmailEquals(driver.getEmail());

            if (findEmail != null) {
                return ResponseEntity.status(400).body(new ErrorMessage("O email informado não está disponível"));
            }

            driverRepository.save(driver);

            return ResponseEntity.status(201).body(new DriverDTO(driver));
        } catch (RuntimeException ex) {
            return ResponseEntity.status(500).body(new ErrorMessage("Erro no servidor"));
        }
    }

    // Atualiza um driver e retorna ele com status 200
    public ResponseEntity<?> updateDriverById(Integer driverID, Driver body) {
        try {
            Driver driver = driverRepository.findById(driverID).orElse(null);

            if (driver == null) {
                return ResponseEntity.status(404).body(new ErrorMessage("Driver não encontrado"));
            }

            Driver findEmail = driverRepository.findByEmailEquals(body.getEmail());

            // verifica se o email ja existe no banco e se é diferente do id do driver
            // se true retorna um erro
            if (findEmail != null && findEmail.getId() != driverID) {
                return ResponseEntity.status(400).body(new ErrorMessage("O email informado não está disponível"));
            } else {
                if (body.getEmail() != null) {
                    driver.setEmail(body.getEmail());
                }
            }

            if (body.getName() != null) {
                driver.setName(body.getName());
            }
            if (body.getPhoneNumber() != null) {
                driver.setPhoneNumber(body.getPhoneNumber());
            }
            if (body.getPassword() != null) {
                driver.setPassword(body.getPassword());
            }
            if (body.getLatitude() != null) {
                System.out.println(body.getLatitude() + " oxi");
                driver.setLatitude(body.getLatitude());
            }
            if (body.getLongitude() != null) {
                driver.setLongitude(body.getLongitude());
            }

            // o metodo save serve tanto para criar quanto para atualizar
            driverRepository.save(driver);

            return ResponseEntity.status(200).body(new DriverDTO(driver));
        } catch (RuntimeException ex) {
            return ResponseEntity.status(500).body(new ErrorMessage("Erro no servidor"));
        }
    }

    // Deleta um driver e retorna status 200
    public ResponseEntity<?> deleteDriverById(Integer id) {
        try {
            Driver driver = this.driverRepository.findById(id).orElse(null);

            if (driver != null) {
                driverRepository.deleteById(id);
                return ResponseEntity.status(200).build();
            }

            return ResponseEntity.status(404).body(new ErrorMessage("Driver não encontrado"));
        } catch (RuntimeException ex) {
            return ResponseEntity.status(500).body(new ErrorMessage("Erro no servidor"));
        }
    }

}
