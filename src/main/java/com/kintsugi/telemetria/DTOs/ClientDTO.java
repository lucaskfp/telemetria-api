package com.kintsugi.telemetria.DTOs;

import com.kintsugi.telemetria.models.Client;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ClientDTO {
    private String name;
    private String address;

    public ClientDTO(Client client) {
        this.name = client.getName();
        this.address = client.getAddress();
    }
}
