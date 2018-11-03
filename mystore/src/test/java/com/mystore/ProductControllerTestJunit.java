package com.mystore;

import static org.hamcrest.Matchers.hasSize;

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


@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductControllerTestJunit {

	
	protected MockMvc mvc;

	@Autowired
	private WebApplicationContext webApplicationContext;
    
    
    @Before
    public void setUp() throws Exception {

    	try {
	    	mvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    	} catch(Exception e) {
    		e.printStackTrace();
    		throw e;
    	}
    }

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testGetProducts() throws Exception {
		try {
			ResultActions mvcResult = mvc.perform(MockMvcRequestBuilders.get("/product/list"));
			mvcResult.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.jsonPath("$", hasSize(6))); //need to contains at least 5 elements
		}catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}

	@Test
	public void testGetProductById()  throws Exception {
		
		ResultActions mvcResult = mvc.perform(MockMvcRequestBuilders.get("/product/by-id/1"));// .accept(mediaTypes);
		mvcResult.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.jsonPath("$.id").value("1"))
				.andExpect(MockMvcResultMatchers.jsonPath("$.name").value("hybris Munich, Germany"));		
	}
	
}
