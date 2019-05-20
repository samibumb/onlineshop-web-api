package onlineshopapi;

import org.fasttrackit.onlineshopapi.service.ProductService;
import org.fasttrackit.onlineshopapi.transfer.CreateProductRequest;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductServiceIntegrationTests {

	@Autowired
	private ProductService productService;

	@Test
	public void testCreateProduct_whenValidRequest_thenReturnCreatedProduct() {

		CreateProductRequest request = new CreateProductRequest();
		request.setName("Nivea");
		request.setPrice(99.9);
		request.setQuantity(10);

		productService.createProduct(request);

	}

}
