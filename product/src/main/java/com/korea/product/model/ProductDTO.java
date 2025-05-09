package com.korea.product.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class ProductDTO {
	
	private Long id;
	
	private String name;
	
	private String description;
	
	private int price;
	
	public ProductDTO(ProductEntity proEntity) {
		this.id = proEntity.getId();
		this.name = proEntity.getName();
		this.name = proEntity.getDescription();
		this.description = proEntity.getDescription();
	}
	
}
