package com.kintsugi.telemetria.DTOs;

import com.kintsugi.telemetria.models.Driver;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class DriverDTO {
    private Integer id;
    private String name;
    private String phoneNumber;
    private String email;
    private Double latitude;
    private Double longitude;

    public DriverDTO(Driver driver) {
        super();
        this.id = driver.getId();
        this.name = driver.getName();
        this.phoneNumber = driver.getPhoneNumber();
        this.email = driver.getEmail();
        this.latitude = driver.getLatitude();
        this.longitude = driver.getLongitude();
    }
}
