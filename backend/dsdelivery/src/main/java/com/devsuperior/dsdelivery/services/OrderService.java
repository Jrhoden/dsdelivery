package com.devsuperior.dsdelivery.services;

import com.devsuperior.dsdelivery.dto.OrderDTO;
import com.devsuperior.dsdelivery.dto.ProductDTO;
import com.devsuperior.dsdelivery.entities.Order;
import com.devsuperior.dsdelivery.entities.OrderStatus;
import com.devsuperior.dsdelivery.entities.Product;
import com.devsuperior.dsdelivery.repositories.OrderRepository;
import com.devsuperior.dsdelivery.repositories.ProductRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderService {

	@Autowired
	private OrderRepository repository;

	@Autowired
	private ProductRepository productRepository;

	@Transactional(readOnly = true)
	public List<OrderDTO> findAll(){
		return repository.findOrdersWithProducts().stream().map(p-> new ModelMapper().map(p, OrderDTO.class)).collect(Collectors.toList());
	}

	@Transactional
	public OrderDTO insert(OrderDTO orderDTO){
		orderDTO.setMoment(Instant.now());
		orderDTO.setStatus(OrderStatus.PENDING);
		Order order = new ModelMapper().map(orderDTO, Order.class);
		//será que isso não poderia ser feito pelo proprio mapper, PIOR QUE NÃO HAHAH
		for (ProductDTO p :orderDTO.getProducts()) {
			Product product = productRepository.getOne(p.getId());
			order.getProducts().add(product);
		}
		order = repository.save(order);
		return new ModelMapper().map(order, OrderDTO.class);
	}

	@Transactional
	public OrderDTO setDelivered(Long id){
		Order order = repository.getOne(id);
		order.setStatus(OrderStatus.DELIVERED);
		order = repository.save(order);
		return new ModelMapper().map(order, OrderDTO.class);
	}
}
