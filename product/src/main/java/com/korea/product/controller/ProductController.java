package com.korea.product.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.korea.product.model.ProductDTO;
import com.korea.product.service.ProductService;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/products")
@RequiredArgsConstructor
public class ProductController {
	

	private final ProductService productService;
	
	
	@PostMapping("/addPro")
	public ResponseEntity<String> createProduct(@RequestBody ProductDTO product) {
	    
		
		//매개변수로 넘어온 데이터를 service로 넘겨야겠다.
		//추가를 하고 전체조회를 하니까 ProductDTO타입의 리스트에 담아야겠따.
		try {
			int result = productService.createPro(product);
			
			if(result == 1) {
				 return ResponseEntity.ok("저장 완료"); //post맨에 응답
			}else {
				 return ResponseEntity.ok("저장 실패"); //post맨에 응답
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.getMessage();
		}
		return null;
	
		
	}
	
	/*
	 * 
	 * 		@PostMapping("/addPro")
			public ResponseEntity<?> addProduct(@RequestBody ProductDTO product) {
	    			List<ProductDTO> response = productService.addProduct(dto)
	    			return ResponseEntity.ok(response);

			}
			
			추가 기능을 만들어야겠다. -> @PostMapping을 갖는 메서드를 만들어야겠다.
			사용자가 인터페이스(리액트)에서 데이터가 넘어오겠다. -> 매개변수로 받아야겠다.
			//Json으로 넘어오늘걸 자바객체로 받아야하네? -> @RequestBody
			//사용자로부터 데이터 받을 때는 DTO를 써야한다.
	
	 * */
	
	
	//모든 상품의 조회
	
	@GetMapping("/viewPro")
	public ResponseEntity<List<ProductDTO>> viewPro(@RequestParam Double minPrice) {
        try {
            List<ProductDTO> response = productService.getfindAllPro(minPrice);
            return ResponseEntity.ok(response); // 성공적으로 조회된 상품 목록 반환
        } catch (Exception e) {
            // 예외 발생 시 응답
            return ResponseEntity.status(500).body(null); // 서버 오류
        }
    }
	
	@PutMapping("/{id}")
	public ResponseEntity<?> updateProduct(@PathVariable Long id, @RequestBody ProductDTO product){
		
		
		try {
			int result = productService.updatePro(id, product);
			
			if(result == 1) {
				 return ResponseEntity.ok("수정 완료"); //post맨에 응답
			}else {
				 return ResponseEntity.ok("수정 실패"); //post맨에 응답
			}
					
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		return null; 
	}
	
	
	@PostMapping("/deletePro/{id}")
	public ResponseEntity<?> deletePro(@PathVariable Long id){
		try {
			int result = productService.deletePro(id);
			
			if(result == 1) {
				 return ResponseEntity.ok("삭제 완료"); //post맨에 응답
			}else {
				 return ResponseEntity.ok("삭제 실패"); //post맨에 응답
			}
					
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		return null; 
	}
	
	/*
	 * 
	 * 		@DeleteMapping("/{id}")
			public ResponseEntity<?> deleteProduct(@PathVariable Long id) {
	    		boolean isDeleted = productService.deleteProduct(id);
	    		
	    		//삭제가 정상 처리되면 본문 없이 204 No Content 응답
	    		
	    		if(Deleted){
	    			return ResponseEntity.noContent().build();
	    		}
	    		// 삭제 실패시 404 Not Found 응답
	    			return ResponseEntity.notFound().build();

			}
			
			추가 기능을 만들어야겠다. -> @PostMapping을 갖는 메서드를 만들어야겠다.
			사용자가 인터페이스(리액트)에서 데이터가 넘어오겠다. -> 매개변수로 받아야겠다.
			//Json으로 넘어오늘걸 자바객체로 받아야하네? -> @RequestBody
			//사용자로부터 데이터 받을 때는 DTO를 써야한다.
	
	 * */
	
	
}
