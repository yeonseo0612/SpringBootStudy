package com.korea.product2.model;

import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class ProductDTO {
	
	private int id;
	
	private String proName;
	
	private int proCount;
	
	private int proPrice;
	
	private LocalDateTime regDate;
	
	private LocalDateTime upDate;
	
	
	public ProductDTO(ProductEntity entity) {
		this.id = entity.getId();
		this.proName = entity.getProName();
		this.proCount = entity.getProCount();
		this.proPrice = entity.getProPrice();
		this.regDate = entity.getRegDate();
		this.upDate = entity.getUpDate();
	}
}
