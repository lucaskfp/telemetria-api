package com.kintsugi.telemetria.controllers;

import javax.websocket.server.PathParam;

import com.kintsugi.telemetria.models.Order;
import com.kintsugi.telemetria.services.OrderService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/orders")
public class OrderController {

    @Autowired
    private OrderService orderService;

    // Retorna todas as orders ou [] vazio com status 200
    @GetMapping("")
    public ResponseEntity<?> listAllOrders() {
        return orderService.listAllOrders();
    }

    @GetMapping("/list-orders-home")
    public ResponseEntity<?> findAllOpenedAndInTransit(@RequestParam("driver_id") Integer driverId) {
        return orderService.findAllOpenedAndInTransit(driverId);
    }

    // Retorna uma order pelo id com status 200 ou "NO BODY" com status 404
    @GetMapping("/{id}")
    public ResponseEntity<?> getOrderById(@PathVariable("id") Integer id) {
        return orderService.getOrderById(id);
    }

    // Cria uma order e retorna ela com status 201
    @PostMapping("")
    public ResponseEntity<?> createOrder(@RequestBody Order order) {
        return orderService.createOrder(order);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateOrderByid(@PathVariable("id") Integer id) {
        return orderService.updateOrderByid(id);
    }

    // Deleta uma order pelo id e retorna status 200 com "NO BODY"
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteOrder(@PathVariable("id") Integer id) {
        return orderService.deleteOrderById(id);
    }

}
