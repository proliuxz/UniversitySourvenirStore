package sg.edu.nus.iss.usstore.test;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

import sg.edu.nus.iss.usstore.domain.Category;
import sg.edu.nus.iss.usstore.domain.Product;
import sg.edu.nus.iss.usstore.domain.Vendor;

public class ProductTest {

	private Product product1;
	private Product product2;
	private Category category;
	
	public ProductTest(){
		Vendor vendor1 = new Vendor("vendor1", "the first vendor", 1);
		Vendor vendor2 = new Vendor("vendor2", "the sencond vendor", 0);
		ArrayList<Vendor> vendorList = new ArrayList<Vendor>();
		vendorList.add(vendor1);
		vendorList.add(vendor2);
		category = new Category("CGY","category1",vendorList);
		product1 = new Product(category,"product1","description of product",300,10.09,"barcode",200,300);
		product2 = new Product("CGY/2",category,"product2","description of product",100,10.09,"barcode",200,300);
		
	}
	
	@Test
	public void testProductCategoryStringStringIntDoubleStringIntInt() {
		
		assertEquals(category, product1.getCategory());
	}

	@Test
	public void testProductStringCategoryStringStringIntDoubleStringIntInt() {
		
		assertEquals(category, product2.getCategory());
		assertEquals("CGY/2", product2.getProductId());
	}

	@Test
	public void testCheckInventoryLevel() {
		
		
		assertTrue(product1.checkInventoryLevel());
		product1.setQuantityAvailable(199);
		assertFalse(product1.checkInventoryLevel());
	}

	@Test
	public void testCompare() {
		assertFalse(product1.compare(product2));
	}

	@Test
	public void testAddQuantity() {
		product1.addQuantity(100);
		assertEquals(400,product1.getQuantityAvailable());
	}

	@Test
	public void testGetProductId() {
		assertEquals("CGY/2", product2.getProductId());
	}

	@Test
	public void testSetProductId() {
		product1.setProductId("CGY/3");
		assertEquals("CGY/3", product1.getProductId());
	}

	@Test
	public void testGetCategory() {
		assertEquals(category, product1.getCategory());
	}

	@Test
	public void testSetCategory() {
		ArrayList<Vendor> list = new ArrayList<Vendor>();
		list.add(new Vendor("vendor3","dpt",1));
		Category c = new Category("CGY/3","category3",list);
		product1.setCategory(c);
		assertEquals(c, product1.getCategory());
	}

	@Test
	public void testGetName() {
		assertEquals("product1", product1.getName());
	}

	@Test
	public void testSetName() {
		product1.setName("new product");
		assertEquals("new product", product1.getName());
	}

	@Test
	public void testGetBriefDescription() {
		assertEquals("description of product", product1.getBriefDescription());
	}

	@Test
	public void testSetBriefDescription() {
		product1.setBriefDescription("description");
		assertEquals("description", product1.getBriefDescription());
	}

	@Test
	public void testGetQuantityAvailable() {
		assertEquals(300, product1.getQuantityAvailable());
	}

	@Test
	public void testSetQuantityAvailable() {
		product1.setQuantityAvailable(300);
		assertEquals(300, product1.getQuantityAvailable());
	}

	@Test
	public void testGetPrice() {
		assertTrue(10.09==product1.getPrice());
	}

	@Test
	public void testSetPrice() {
		product1.setPrice(10.00);
		assertTrue(10.00==product1.getPrice());
	}

	@Test
	public void testGetBarCodeNumber() {
		assertEquals("barcode", product1.getBarCodeNumber());
	}

	@Test
	public void testSetBarCodeNumber() {
		product1.setBarCodeNumber("barCodeNumber");
		assertEquals("barCodeNumber", product1.getBarCodeNumber());
	}

	@Test
	public void testGetReorderQuantity() {
		assertEquals(200, product1.getReorderQuantity());
	}

	@Test
	public void testSetRecorderQuantity() {
		product1.setRecorderQuantity(100);
		assertEquals(100, product1.getReorderQuantity());
	}

	@Test
	public void testGetOrderQuantity() {
		System.out.println(product1.getOrderQuantity());
		assertEquals(300, product1.getOrderQuantity());
	}

	@Test
	public void testSetOrderQuantity() {
		product1.setOrderQuantity(200);
		assertEquals(200, product1.getOrderQuantity());
	}

}
