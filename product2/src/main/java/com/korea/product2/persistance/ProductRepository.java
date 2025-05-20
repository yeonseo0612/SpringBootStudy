package com.korea.product2.persistance;

import org.springframework.data.jpa.repository.JpaRepository;

import com.korea.product2.model.ProductEntity;

public interface ProductRepository extends JpaRepository<ProductEntity, Long> {

}
