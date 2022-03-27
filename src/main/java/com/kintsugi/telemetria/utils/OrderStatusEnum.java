package com.kintsugi.telemetria.utils;

import java.util.Arrays;
import java.util.Optional;

public enum OrderStatusEnum {
    EM_ESPERA(1),
    EM_TRANSITO(2),
    CONCLUIDO(3),
    CANCELADO(4);

    private int status;

    OrderStatusEnum(int status) {
        this.status = status;
    }

    public int getStatus() {
        return this.status;
    }

    public static Optional<OrderStatusEnum> getKey(int status) {
        return Arrays.stream(OrderStatusEnum.values())
                .filter(env -> env.status == status)
                .findFirst();
    }

}
