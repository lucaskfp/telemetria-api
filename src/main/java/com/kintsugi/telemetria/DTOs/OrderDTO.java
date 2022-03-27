package com.kintsugi.telemetria.DTOs;

import java.util.Optional;

import com.kintsugi.telemetria.models.Client;
import com.kintsugi.telemetria.models.Order;
import com.kintsugi.telemetria.utils.OrderStatusEnum;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class OrderDTO {
    private Integer id;
    private Double value;
    private String timestamp;
    private Integer status;
    private Optional<OrderStatusEnum> statusName;
    private Integer driverId;
    private Client client;

    public OrderDTO(Order order) {

        this.id = order.getId();
        this.value = order.getValue();
        this.timestamp = order.getTimestamp();
        this.status = order.getStatus();
        this.statusName = OrderStatusEnum.getKey(this.status);

        if (order.getDriver() != null) {
            this.driverId = order.getDriver().getId();
        }

        if (order.getClient() != null) {
            this.client = order.getClient();
        }

    }
}
