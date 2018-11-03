package mystore;

import java.io.IOException;
import java.util.List;

import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mystore.model.Product;
import com.mystore.repository.ProductRepository;

import junit.framework.Assert;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class ProductControllerTest {

	final String BASE_PATH = "http://localhost:8080/product";
	
	@Autowired
	private ProductRepository productRepository;
	
    private RestTemplate restTemplate;
    
    private ObjectMapper MAPPER = new ObjectMapper();	
	
    @Before
    public void setUp() throws Exception {
    	
    	Product prod = new Product();
    	prod.setId(new Long(1));
    	prod.setName("TMV");
    	prod.setProductDate(null);
    	Product prod2 = new Product();
    	prod2.setId(new Long(2));
    	prod2.setName("Ocr Global");
    	prod2.setProductDate(null);    	
    	
    	//Deleta todos do banco para teste
    	productRepository.deleteAll();
    	
    	productRepository.save(prod);
    	  	
        restTemplate = new RestTemplate();
    }
    
    
    
	@Test
	void testGetProducts() throws IOException {
    	String response = restTemplate.getForObject(BASE_PATH + "/list", String.class);
        List<Product> productList = MAPPER.readValue(response, MAPPER.getTypeFactory().constructCollectionType(List.class, Product.class));
    	Assert.assertTrue(productList.size()>5);
	}

	@Test
	void testGetProductById()  throws IOException {
    	String response = restTemplate.getForObject(BASE_PATH + "/by-id/1", String.class);
    	List<Product> productList = MAPPER.readValue(response, MAPPER.getTypeFactory().constructCollectionType(List.class, Product.class));
        Assert.assertNotNull(productList);
    	Assert.assertEquals(new Long(1), productList.get(0).getId());
    	Assert.assertEquals("TMV", productList.get(0).getName());
	}

}
