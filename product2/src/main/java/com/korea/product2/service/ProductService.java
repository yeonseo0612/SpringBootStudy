package com.korea.product2.service;

import java.io.Console;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.korea.product2.model.ProductDTO;
import com.korea.product2.model.ProductEntity;
import com.korea.product2.persistance.ProductRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class ProductService {
	
	private final ProductRepository productRepository;
	
	//추가
	public int createProduct(ProductDTO dto) {
		
		ProductEntity entity;
		int result = 0;

		try {
				entity =  new ProductEntity();
			
				entity.setProCount(dto.getProCount());
				entity.setProName(dto.getProName());
				entity.setProPrice(dto.getProPrice());
				
				if(entity.getRegDate() == null) {
					entity.setRegDate(LocalDateTime.now());
					entity.setUpDate(null);
				}else {
					entity.setRegDate(null);
					entity.setUpDate(LocalDateTime.now());
				}
				
				productRepository.save(entity);
				
				result = 1;
				
		} catch (Exception e) {
				result = -1;
				e.printStackTrace();
			// TODO: handle exception
		}
		return result;
	}
	
	
	//조회
	public List<ProductDTO> getProduct() {
		// TODO Auto-generated method stub
		
		List<ProductEntity> productList = productRepository.findAll();

		List<ProductDTO> dtoList = new ArrayList<>();
		
		if (!productList.isEmpty()) {
		    
			for(ProductEntity entity : productList) {
				ProductDTO dto = new ProductDTO();
				
				dto.setId(entity.getId());
				dto.setProCount(entity.getProCount());
				dto.setProName(entity.getProName());
				dto.setProPrice(entity.getProPrice());
				dto.setRegDate(entity.getRegDate());
				dto.setUpDate(entity.getUpDate());
				
				dtoList.add(dto);
			}
			
		} else {
		    System.out.println("데이터 없음");
		}
		
		
		return dtoList;
	}
	
	//삭제
	
	public int deletePro(Long id) {
		
		int result = 0;
		
		Optional<ProductEntity> productEntity = productRepository.findById(id);
		
		if (productEntity.isPresent()) {
		    productRepository.deleteById(id); // ✅ 존재할 때 삭제
		    result = 1;
		} else {
		    result = -1; // ❌ 존재하지 않음
		}
		
		
		return result;
	}
	
	
	
	
	
	
	
	
	//수정

}

