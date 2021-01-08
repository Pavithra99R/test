package com.lti.test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.lti.dao.GenericDao;
import com.lti.entity.LineItem;
import com.lti.entity.Order;
import com.lti.entity.Product;

public class OrderProductTest {

	@Test
	public void addProduct() {

		Product product = new Product();
		product.setName("Boat Headphones");
		product.setPrice(2600);
		GenericDao dao = new GenericDao();
		dao.save(product);
	}

	@Test
	public void placeOrder() {

		GenericDao dao = new GenericDao();
		Product product = (Product) dao.fetch(Product.class, 141);
		Product product1 = (Product) dao.fetch(Product.class, 140);

		Order order = new Order();

		LineItem item = new LineItem();
		item.setQuantity(1);
		item.setProduct(product);
		item.setOrder(order);
		item.updateTotal();

		LineItem item1 = new LineItem();
		item1.setQuantity(3);
		item1.setProduct(product1);
		item1.setOrder(order);
		item.updateTotal();
		// double total = item.getQuantity()*product.getPrice()+
		// product1.getPrice()*item1.getQuantity();

		List<LineItem> lineItems = new ArrayList<LineItem>();
		lineItems.add(item);
		lineItems.add(item1);

		order.setOrderDate(LocalDate.now());
		order.setLineItems(lineItems);
		dao.save(order);
	}

	@Test
	public void fetchOrder() {

		GenericDao dao = new GenericDao();
		Order order = (Order) dao.fetch(Order.class, 164);
		System.out.println("Order Total " + order.getTotal());
		System.out.println("Order Date: " + order.getOrderDate());
		
		for(LineItem li: order.getLineItems()) {
			System.out.println("Quantity: "+li.getQuantity());
			System.out.println("Name: "+li.getProduct().getName());
			System.out.println("Price: "+li.getProduct().getPrice());
		}
		
	}
}
