package com.ibm.demo.services;

import java.util.List;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.ibm.demo.entity.Order;
import com.ibm.demo.repo.OrderRepository;

@Service
public class OrderService {
	@Autowired
	OrderRepository orderrepository;
	@Autowired
	RestTemplate getTaxesTemplate;

	public String createOrder(Order order) {
		Float response = getTaxesTemplate.getForObject("http://localhost:8080/getTaxes", Float.class, order.getPrice());
		System.out.println(response);
		Order savedOrder = orderrepository.save(order);
		return savedOrder.getId();
	}

	public List<Order> getOrders() {
		return orderrepository.findAll();
	}

	public void updateOrder(Order order) {
		orderrepository.save(order);
	}

	public void deleteOrder(String orderId) {
		orderrepository.deleteById(orderId);
		;
	}

	public Optional<Order> getOrder(String orderId) {
		return orderrepository.findById(orderId);
	}

}
