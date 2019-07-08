package org.fasttrackit.onlineshopapi;

import org.fasttrackit.onlineshopapi.domain.Product;
import org.fasttrackit.onlineshopapi.dto.UpdateProductRequest;
import org.fasttrackit.onlineshopapi.exception.ResourceNotFoundException;
import org.fasttrackit.onlineshopapi.service.ProductService;
import org.fasttrackit.onlineshopapi.dto.CreateProductRequest;
import org.fasttrackit.onlineshopapi.steps.ProductSteps;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.validation.ConstraintViolationException;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.core.IsNull.notNullValue;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductServiceIntegrationTests {


	@Autowired
	private ProductService productService;

	private ProductSteps productSteps;
	@Test
	public void testCreateProduct_whenValidRequest_thenReturnCreatedProduct() {

		productSteps.createProduct();
	}

	private Product createProduct() {
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

		return createdProduct;
	}

	@Test(expected = ConstraintViolationException.class)
	public void testCreateProduct_whenMissingMandatoryProperties_thenThrowException(){
		CreateProductRequest request = new CreateProductRequest();

		productService.createProduct(request);

	}

	@Test
	public void findProductById_whenExist_thenReturnProduct() throws ResourceNotFoundException {

		Product createdProduct = createProduct();
		Product product = productService.getProduct(createdProduct.getId());

		assertThat(product,notNullValue());
		assertThat(product.getId(),is(createdProduct.getId()));

		System.out.println(createdProduct);

	}
	@Test(expected = ResourceNotFoundException.class)
	public void TestProduct_whenNoExistingId_thenThrowReseourceNotFoundException(){

		try {
			productService.getProduct(9999L);
		} catch (ResourceNotFoundException e) {
			System.out.println(e.getMessage());
		}

	}

	@Test
	public void testUpdateProduct() throws ResourceNotFoundException {

	   Product product = createProduct();

        UpdateProductRequest updateProductRequest = new UpdateProductRequest();
        updateProductRequest.setName(product.getName()+" Updated");
	    updateProductRequest.setPrice(product.getPrice()+10);
	    updateProductRequest.setQuantity(product.getQuantity()+10);

        Product updatedProduct = productService.updateProduct(product.getId(),updateProductRequest);

        assertThat(updatedProduct.getId(),notNullValue());
        assertThat(updatedProduct.getId(),is(product.getId()));

        assertThat(updatedProduct.getQuantity(),not(is(product.getQuantity())));
        assertThat(updatedProduct.getQuantity(),is(updateProductRequest.getQuantity()));

        assertThat(updatedProduct.getPrice(),not(is(product.getPrice())));
        assertThat(updatedProduct.getPrice(),is(updateProductRequest.getPrice()));

        assertThat(updatedProduct.getName(),not(is(product.getName())));
        assertThat(updatedProduct.getName(),is(updateProductRequest.getName()));


        System.out.println(product);
        System.out.println("Updated product :\n"+updateProductRequest);

	}

	@Test
    public void deleteProductTest(){

	    Product product = createProduct();

	    productService.deleteProduct(product.getId());

    }

}
