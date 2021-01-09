package com.devsuperior.dsdelivery.repositories;

import com.devsuperior.dsdelivery.entities.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {

	@Query("SELECT DISTINCT o FROM Order o JOIN FETCH o.products " +
			" WHERE o.status = 0 ORDER BY o.moment ASC")
	List<Order> findOrdersWithProducts();
}
