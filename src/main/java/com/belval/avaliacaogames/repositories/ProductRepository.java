package com.belval.avaliacaogames.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.belval.avaliacaogames.entities.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {

}
