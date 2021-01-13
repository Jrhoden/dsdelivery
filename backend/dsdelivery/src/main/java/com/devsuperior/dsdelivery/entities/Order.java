package com.devsuperior.dsdelivery.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.time.Instant;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "tb_order")
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Order implements Serializable {

	@EqualsAndHashCode.Include()
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String address;
	private Double latitude;
	private Double longitude;
	private Instant moment;
	private OrderStatus status;
	//a ideia de colocar final aqui é que talvez o lombok não adicione ao construtor
	@ManyToMany
	@JoinTable(name = "tb_order_product",
		joinColumns = @JoinColumn(name = "order_id"),
		inverseJoinColumns = @JoinColumn(name = "product_id"))
	private final Set<Product> products = new HashSet<>();

	public Double getTotal(){
		double sum = 0.0;
		for(Product product : products){
			sum += product.getPrice();
		}
		return sum;
	}
}
