package com.korea.product2.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.korea.product2.model.ProductDTO;
import com.korea.product2.service.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/api/product")
@RequiredArgsConstructor
@Slf4j
public class ProductController {
	
	private final ProductService productService;
	
	@PostMapping("/createPro")
	public ResponseEntity<?> createPro(@RequestBody ProductDTO dto){
		
		int result = productService.createProduct(dto);
		
		if(result == 1) {
			return ResponseEntity.ok("상품 추가 성공!");
		}else{
			return ResponseEntity.ok("상품 추가 실패!");
		}
		
	}
	
	@GetMapping("/viewPro")
	public ResponseEntity<?> viewPro(){
		
		List<ProductDTO> dtoList = productService.getProduct();
		
		if(dtoList == null) {
			return ResponseEntity.ok("데이터가 없습니다.");
		}else {
			return ResponseEntity.ok(dtoList);
		}
		
	}
	
	@DeleteMapping("/deletePro/{id}")
	public ResponseEntity<?> deletePro(@PathVariable(name = "id") Long id){
		
		int result = productService.deletePro(id);
		
		if(result == 1){
				return ResponseEntity.ok("상품 삭제 성공!");	
			}else{	
				return ResponseEntity.ok("상품 삭제 실패!");
			}		
	}
	
}
