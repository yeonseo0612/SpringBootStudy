package com.korea.product.persistance;

import org.springframework.data.jpa.repository.JpaRepository;

import com.korea.product.model.ProductEntity;

public interface ProductRepository extends JpaRepository<ProductEntity, Long>{

}
