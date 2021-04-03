package com.example.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.model.Orders;
import com.example.model.Product;
import com.example.productdao.OrderDao;
import com.example.productdao.ProductDao;

@Service
public class ProductOrderService {

	@Autowired
	private ProductDao productdao;
	@Autowired
	private OrderDao orderdao;
	
   // Orders related service methods
	public Orders createOrder(Orders order) {
		return orderdao.save(order);
	}

	public Iterable<Orders> getAllOrders() {
		return orderdao.findAll();
	}

	public Optional<Orders> getByOrderId(int oid) {
		return orderdao.findById(oid);
	}
    //product related service methods
	public Product createProduct(Product product) {
		return productdao.save(product);
	}

	public Iterable<Product> getAllProducts() {
		return productdao.findAll();
	}

	public Optional<Product> getByProductId(int pid) {
		return productdao.findById(pid);
	}

	public void deleteProductById(Integer pid) {
		productdao.deleteById(pid);
	}
	
	public int placeOrder(Integer pid, Integer quantity, Orders order) {
		int result = 1;
		Product product = productdao.findById(pid).orElse(new Product());
		if(product == null)
			return result;
		order.setQuantity(quantity);
		
		order.setProduct(product);
		orderdao.save(order);
		result = 0;
		return result;
		
	}
	public int updateOrder(Integer id, Integer quantity) {
		int result = 1;
		Orders order = orderdao.findById(id).orElse(new Orders());
		if(order.getOid() == 0) {
			return result;
		}
		
		order.setQuantity(quantity);
		orderdao.save(order); 
		result = 0;
		return result;
	}
}
