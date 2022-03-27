package com.kintsugi.telemetria.repositorys;

import java.util.List;

import com.kintsugi.telemetria.models.Order;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface OrderRepository extends JpaRepository<Order, Integer> {

    // retona orders com status em aberto
    @Query(value = "SELECT * FROM orders o " +
            "WHERE o.status = 1 OR " +
            "o.driver_id = :driverid AND o.status = 2", nativeQuery = true)
    public List<Order> findAllOpenedAndInTransit(Integer driverid);

}
