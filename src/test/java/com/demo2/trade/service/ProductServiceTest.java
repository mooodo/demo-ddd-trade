/* 
 * create by 2020年1月22日 下午7:55:49
 */
package com.demo2.trade.service;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.demo2.trade.entity.Product;

/**
 * @author fangang
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductServiceTest {
	@Autowired
	private ProductService service;
	
	/**
	 * The test show:
	 * how to save, load, delete a value object, which set join key to null, to database.
	 */
	@Test
	public void testSaveAndDelete() {
		Product product = new Product();
		long id = 10010;
		product.setId(id);
		product.setName("Nodebook");
		product.setPrice((double)4000);
		product.setUnit("unit");
		service.saveProduct(product);
		
		Product actual = service.getProduct(id);
		assertThat(actual, equalTo(product));
		
		service.deleteProduct(id);
		Product deleted = service.getProduct(id);
		assertNull(deleted);
	}

	/**
	 * The test show:
	 * how to save, load, delete a value object with one-to-many relation to database.
	 * that is said:
	 * 1) from product to supplier have a many-to-one relation,
	 * 2) to save a customer has no business with supplier.
	 * 3) load a customer then load his supplier.
	 * 4) to delete a customer has no business with supplier.
	 */
	@Test
	public void testSaveAndDeleteWithSupplier() {
		Product product = new Product();
		long id = 10011;
		product.setId(id);
		product.setName("Computor");
		product.setPrice((double)8000);
		product.setUnit("unit");
		product.setSupplierId((long)20001);
		service.saveProduct(product);
		
		Product actual = service.getProduct(id);
		actual.setExclude(new String[] {"supplier"});
		assertThat("save or load failure! ", actual, equalTo(product));
		assertNotNull("load the supplier of the customer failure! ",actual.getSupplier());
		
		service.deleteProduct(id);
		Product deleted = service.getProduct(id);
		assertNull(deleted);
	}
	
	@Test
	public void testSaveAndDeleteForList() {
		List<Product> listOfProducts = new ArrayList<>();
		Product product1 = new Product();
		long id = 10010;
		product1.setId(id);
		product1.setName("Nodebook");
		product1.setPrice((double)4000);
		product1.setUnit("unit");
		product1.setSupplierId((long)20001);
		listOfProducts.add(product1);
		
		Product product2 = new Product();
		long id2 = 10011;
		product2.setId(id2);
		product2.setName("Computor");
		product2.setPrice((double)8000);
		product2.setUnit("unit");
		product2.setSupplierId((long)20001);
		listOfProducts.add(product2);
		service.saveProductList(listOfProducts);
		
		List<Long> ids = new ArrayList<>();
		ids.add(id);
		ids.add(id2);
		List<Product> listOfProducts1 = service.getProductList(ids);
		//assertThat(listOfProducts1, equalTo(listOfProducts));
		assertThat(listOfProducts1.size(), equalTo(2));
		
		service.deleteProductList(ids);
		List<Product> listOfProducts2 = service.getProductList(ids);
		assertTrue(listOfProducts2.isEmpty());
	}
}
