package com.mystore.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.mystore.model.Product;
import com.mystore.repository.ProductRepository;
import com.mystore.service.ProductService;


@Controller
public class MainController {

	@Autowired
	private ProductRepository productRepository;
	
	@Autowired
	private ProductService productService;

	@GetMapping("/")
	public ModelAndView getAllList() {
		ModelAndView model = new ModelAndView();
		List<Product> list = productRepository.findAll();
		model.addObject("productList",list);
		model.setViewName("index"); 
				
		return model;
	}
	
	@RequestMapping(value="/product/{id}", method=RequestMethod.GET)
	public ModelAndView getById(@PathVariable Long id) {
		ModelAndView model = new ModelAndView();
		Product product = productRepository.findById(id).orElse(null);
		
		model.addObject("product",product);
		model.addObject("dateFormated", productService.dateFormat(product));
		model.setViewName("productView");
		return model;
	}
	
	@RequestMapping(value="/search{nameSearch}", method=RequestMethod.GET)
	public ModelAndView getById(@ModelAttribute(value="nameSearch") String nameSearch) {
		ModelAndView model = new ModelAndView();
		
		List<Product> listProduct = productRepository.findByNameContaining(nameSearch);
		model.addObject("nameSearch2",nameSearch);
		model.addObject("productList",listProduct);
		model.setViewName("index");
		return model;
	}	
	

}

