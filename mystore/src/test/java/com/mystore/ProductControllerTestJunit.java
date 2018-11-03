package com.mystore;

import static org.hamcrest.Matchers.hasSize;

import java.time.LocalDateTime;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.mystore.model.Product;
import com.mystore.repository.ProductRepository;


@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class ProductControllerTestJunit {

	
	protected MockMvc mvc;
	
	@Autowired
	private ProductRepository productRepository;

	@Autowired
	private WebApplicationContext webApplicationContext;
    
    
    @Before
    public void setUp() throws Exception {
    	
    	mvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
 	
    	if ( productRepository.findById(new Long(1)).get() != null ) {
    		//delete the id 1 to guarantee the service return
    		productRepository.deleteById(new Long(1));
        	Product prod = new Product();
        	prod.setId(new Long(1));
        	prod.setName("TMV");
        	prod.setProductDate(null);
        	prod.setProductDate(LocalDateTime.now());
        	productRepository.save(prod);
    	}
    }

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testGetProducts() throws Exception {
		ResultActions mvcResult = mvc.perform(MockMvcRequestBuilders.get("/list"));
		
		mvcResult.andExpect(MockMvcResultMatchers.status().isOk())
			.andExpect(MockMvcResultMatchers.jsonPath("$", hasSize(6))); //need to contains at least 5 elements
			
	}

	@Test
	public void testGetProductById()  throws Exception {
		
		ResultActions mvcResult = mvc.perform(MockMvcRequestBuilders.get("/list/1"));// .accept(mediaTypes);
		
		mvcResult.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.jsonPath("$.product.id").value("1"))
				.andExpect(MockMvcResultMatchers.jsonPath("$.product.name").value("TMV"));		
	}
	
}
