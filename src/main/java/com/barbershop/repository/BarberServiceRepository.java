package com.barbershop.repository;

import com.barbershop.entity.BarberService;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BarberServiceRepository extends JpaRepository<BarberService, Long> {
    boolean existsByName(String name);
}
