package com.example.productcontroller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.model.Message;
import com.example.model.Orders;
import com.example.model.Product;
import com.example.service.ProductOrderService;

@RestController
@RequestMapping("/products")
public class ProductOrdersController {
	
	@Autowired
	private ProductOrderService productservice;
	
	// order controller mappings
	@PostMapping("/createorder")
	public Orders createOrder(@RequestBody Orders Orders) {
		return productservice.createOrder(Orders);
	}
	@GetMapping("/rest/user_order/list")
	 public Iterable<Orders> getAllOrders() {
		 return productservice.getAllOrders();
	 }
	 @GetMapping("/rest/orders/{oid}")
	 public Optional<Orders> getByOrderId(@PathVariable int oid) {
		 return productservice.getByOrderId(oid);
	 }
	
	// product controller mappings
	@PostMapping("/create")
	public Product createProduct(@RequestBody Product product) {
		return productservice.createProduct(product);
	}
	
	@GetMapping("/rest/product/list")
	public Iterable<Product> getAllProducts() {
		return productservice.getAllProducts();
	}
	
	@GetMapping("/rest/product/{pid}") 
	public Optional<Product> displayProduct(@PathVariable int pid) {
		return productservice.getByProductId(pid);
	}
	
	@DeleteMapping("deleteById/{id}")
	public void deleteProduct(@PathVariable int pid) {
		productservice.deleteProductById(pid);
	}
	@GetMapping("/order/place")
	public Message placeOrder(@RequestParam("pid") Integer id, @RequestParam("quantity") Integer quantity) {
		Message msg = getMsgObj();
		try {
			Orders order = new Orders();
			int code = productservice.placeOrder(id, quantity, order);
			if(code == 0) {
				msg.setCode(0);
				msg.setMessage("Success");
			}
			else if(code == 1) {
				msg.setCode(1);
				msg.setMessage("Invalid");
			}
			return msg;
		} catch (Exception e) {
			return msg;
		}
	}
	@GetMapping("/order/update")
	public Message updateOrder(@RequestParam("id") Integer id, @RequestParam("quantity") Integer quantity) {
		Message msg = getMsgObj();
		try {
			int code = productservice.updateOrder(id, quantity);
			if(code == 0) {
				msg.setCode(0);
				msg.setMessage("Success");
			}
			else if(code == 1) {
				msg.setCode(1);
				msg.setMessage("Invalid");
			}
			return msg;
		} catch (Exception e) {
			return msg;
		}
	}
	
	private Message getMsgObj() {
		// TODO Auto-generated method stub
		return new Message();
	}
	
}
