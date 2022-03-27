package com.kintsugi.telemetria.services;

import java.util.List;

import com.kintsugi.telemetria.DTOs.OrderDTO;
import com.kintsugi.telemetria.models.Order;
import com.kintsugi.telemetria.repositorys.OrderRepository;
import com.kintsugi.telemetria.utils.ErrorMessage;
import com.kintsugi.telemetria.utils.OrderStatusEnum;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class OrderService {

    @Autowired
    OrderRepository orderRepository;

    // Retorna todas as Orders ou [] com status 200
    public ResponseEntity<?> listAllOrders() {
        try {
            List<Order> list = orderRepository.findAll();
            return ResponseEntity.status(200).body(list.stream().map(o -> new OrderDTO(o)));
        } catch (RuntimeException ex) {
            return ResponseEntity.status(500).body(new ErrorMessage("Erro no servidor"));
        }
    }

    // Lista as orders pelo status ou pelo driver id
    public ResponseEntity<?> findAllOpenedAndInTransit(Integer driverId) {
        try {
            List<Order> list = orderRepository.findAllOpenedAndInTransit(driverId);
            return ResponseEntity.status(200)
                    .body(list.stream().map(o -> new OrderDTO(o)));

        } catch (RuntimeException ex) {
            return ResponseEntity.status(500).body(new ErrorMessage("Erro no servidor"));
        }
    }

    // Retorna uma order pelo id com status 200 ou "NO BODY" com status 404
    public ResponseEntity<?> getOrderById(Integer id) {
        try {
            Order order = orderRepository.findById(id).orElse(null);

            if (order != null) {
                return ResponseEntity.status(200).body(new OrderDTO(order));
            }

            return ResponseEntity.status(404).body(new ErrorMessage("Pedido não encotrado"));
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            return ResponseEntity.status(500).body(new ErrorMessage("Erro no servidor"));
        }
    }

    // Cria uma order e retorna ela com status 201
    public ResponseEntity<?> createOrder(Order order) {
        try {
            if (order.getStatus() < 1 || order.getStatus() > OrderStatusEnum.values().length) {
                return ResponseEntity.status(400).body(new ErrorMessage("Código de status inválido"));
            }

            return ResponseEntity.status(201).body(new OrderDTO(orderRepository.save(order)));
        } catch (RuntimeException ex) {
            return ResponseEntity.status(500).body(new ErrorMessage("Erro no servidor"));
        }
    }

    public ResponseEntity<?> updateOrderByid(Integer order) {
        try {
            return null;
        } catch (RuntimeException ex) {
            return ResponseEntity.status(500).body(new ErrorMessage("Erro no servidor"));
        }
    }

    // Deleta uma order pelo id
    public ResponseEntity<?> deleteOrderById(Integer id) {
        try {
            Order driver = orderRepository.findById(id).orElse(null);

            if (driver != null) {
                orderRepository.deleteById(id);
                return ResponseEntity.status(200).build();
            }

            return ResponseEntity.status(404).body(new ErrorMessage("Pedido não encontrado"));
        } catch (RuntimeException ex) {
            return ResponseEntity.status(500).body(new ErrorMessage("Erro no servidor"));
        }
    }

}
