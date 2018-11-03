package com.mystore.service;

import java.time.format.DateTimeFormatter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mystore.model.Product;
import com.mystore.repository.ProductRepository;

@Service
public class ProductService {

	@Autowired
	private ProductRepository productRepository;
	
	public String dateFormat(Product product) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
		String dataFormated =productRepository.findById(product.getId()).get().getProductDate().format(formatter);
		return dataFormated;
		
	}
	
}
