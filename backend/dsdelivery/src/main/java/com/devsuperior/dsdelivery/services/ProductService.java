package com.devsuperior.dsdelivery.services;

import com.devsuperior.dsdelivery.dto.ProductDTO;
import com.devsuperior.dsdelivery.repositories.ProductRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductService {

	@Autowired
	private ProductRepository productRepository;

	@Transactional(readOnly = true)
	public List<ProductDTO> findAll(){
		return productRepository.findAllByOrderByNameAsc().stream().map(p-> new ModelMapper().map(p, ProductDTO.class)).collect(Collectors.toList());
	}
}
