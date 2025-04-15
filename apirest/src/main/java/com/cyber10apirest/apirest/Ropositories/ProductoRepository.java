package com.cyber10apirest.apirest.Ropositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cyber10apirest.apirest.Entities.Producto;

public interface ProductoRepository extends JpaRepository<Producto, Long> {

    
}
