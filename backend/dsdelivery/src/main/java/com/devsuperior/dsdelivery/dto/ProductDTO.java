package com.devsuperior.dsdelivery.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class ProductDTO implements Serializable {
	@EqualsAndHashCode.Include()
	private Long id;
	private String name;
	private Double price;
	private String description;
	private String imageUri;
}
