package com.korea.product2.model;

import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Builder
@AllArgsConstructor
@Entity
@Table(name="product")
public class ProductEntity {
//	- int 상품id(primary key)
//	- String 상품이름
//	- int 상품 재고
//	- int 상품 가격
//	- LocalDateTime 등록 날짜 @CreationTimeStamp
//	- LocalDateTime수정 날짜 @UpdateTimeStamp
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	private String proName;
	
	private int proCount;
	
	private int proPrice;
	

	private LocalDateTime regDate;
	
	
	private LocalDateTime upDate;
	
}
