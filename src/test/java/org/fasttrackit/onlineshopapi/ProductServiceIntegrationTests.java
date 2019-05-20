package org.fasttrackit.onlineshopapi;

import org.fasttrackit.onlineshopapi.domain.Product;
import org.fasttrackit.onlineshopapi.service.ProductService;
import org.fasttrackit.onlineshopapi.transfer.CreateProductRequest;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.validation.ConstraintViolationException;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.core.IsNull.notNullValue;

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

		Product createdProduct = productService.createProduct(request);


		//import static methods
		assertThat(createdProduct,notNullValue());
		assertThat(createdProduct.getId(),greaterThan(new Long(0)));
		assertThat(createdProduct.getName(),is(request.getName()));
		assertThat(createdProduct.getPrice(),is(request.getPrice()));
		assertThat(createdProduct.getQuantity(),is(request.getQuantity()));
	}

	@Test(expected = ConstraintViolationException.class)
	public void testCreateProduct_whenMissingMandatoryProperties_thenThrowException(){
		CreateProductRequest request = new CreateProductRequest();

		productService.createProduct(request);

	}

}
