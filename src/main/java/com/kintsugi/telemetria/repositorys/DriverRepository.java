package com.kintsugi.telemetria.repositorys;

import com.kintsugi.telemetria.models.Driver;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DriverRepository extends JpaRepository<Driver, Integer> {

    public Driver findByEmailEquals(String email);

}
