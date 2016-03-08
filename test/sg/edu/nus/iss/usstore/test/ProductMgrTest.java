package sg.edu.nus.iss.usstore.test;

import java.io.IOException;
import java.util.ArrayList;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import sg.edu.nus.iss.usstore.domain.Category;
import sg.edu.nus.iss.usstore.domain.Product;
import sg.edu.nus.iss.usstore.domain.ProductMgr;
import sg.edu.nus.iss.usstore.domain.Store;
import sg.edu.nus.iss.usstore.exception.DataFileException;
import static org.junit.Assert.*;

/**
 *
 * @author XIE JIABAO
 */
public class ProductMgrTest {
    
	private Store testStore;
	private ProductMgr pm;
	
    public ProductMgrTest() {
    }
    
    @Before
    public void setUp() throws IOException, DataFileException {
    	testStore = new Store();
    	testStore.loadData();
    	pm = new ProductMgr(testStore);
    	pm.loadData();
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of loadData method, of class ProductMgr.
     */
    @Test
    public void testLoadData() throws Exception {
    	// this method can not be tested alone
    	// it needs category data first, will be tested in 
    	// store class's test case
    }

    /**
     * Test of saveData method, of class ProductMgr.
     */
    @Test
    public void testSaveData(){
        try {
			pm.saveData();
		} catch (IOException e) {
			fail("IOException occurs when save product data");
			e.printStackTrace();
		}
    }

    /**
     * Test of checkInventory method, of class ProductMgr.
     */
    @Test
    public void testCheckInventory() {
        System.out.println("checkInventory");

        ArrayList<Product> result = pm.checkInventory();
        
        assertNotEquals(null, result);
        
        //according to datafile, there 2 products quantity is less than threshold
        assertEquals(result.size(), 2);
    }

    /**
     * Test of getNewProductIdByCategory method, of class ProductMgr.
     */
    @Test
    public void testGetNewProductIdByCategory() {
        System.out.println("getNewProductIdByCategory");
        String categoryCode = "CLO";
      
        String expResult = "CLO/5";
        String result = pm.getNewProductIdByCategory(categoryCode);
        
        //according to datafile, the max id of CLO is CLO/4
        assertEquals(expResult, result);
        
    }

    /**
     * Test of addProduct method, of class ProductMgr.
     */
	@Test
    public void testAddProduct() {
        System.out.println("addProduct");
        
        String id = "CLO/5";
        String name = "New Adidas Sports Shirt";
        Category category = testStore.getCategoryByCode("CLO");
        String briefDescription = "new shirt";
        int quantityAvailable = 100;
        double price = 15.2;
        String barCode = "5566";
        int threshold = 30;
        int orderQuantity = 50;
       
        // before addition
        Product result = pm.getProductById(id);
        //there is no such a product which id is CLO/5
        assertEquals(null, result);
        //product list have 10 products
        assertEquals(10, pm.getProductList().size());
        
        pm.addProduct(id, name, category, briefDescription, quantityAvailable, price, barCode, threshold, orderQuantity);
        
        // after adding
        result = pm.getProductById(id);
        //there is a product which id is CLO/5
        assertNotEquals(null, result);
        //product list have 11 products
        assertEquals(11, pm.getProductList().size());
        
        //all attributes are same as set
        assertEquals(id,result.getProductId());
        assertEquals(name,result.getName());
        assertEquals(category,result.getCategory());
        assertEquals(briefDescription,result.getBriefDescription());
        assertEquals(quantityAvailable,result.getQuantityAvailable());
        assertEquals(price,result.getPrice(),0.01);
        assertEquals(barCode,result.getBarCodeNumber());
        assertEquals(threshold,result.getReorderQuantity());
        assertEquals(orderQuantity,result.getOrderQuantity());
        
    }

    /**
     * Test of modifyProduct method, of class ProductMgr.
     */
    @Test
    public void testModifyProduct() {
        System.out.println("modifyProduct");
        
        String id = "CLO/5";
        String name = "New Adidas Sports Shirt";
        Category category = testStore.getCategoryByCode("CLO");
        String briefDescription = "new shirt";
        int quantityAvailable = 100;
        double price = 15.2;
        String barCode = "5566";
        int threshold = 30;
        int orderQuantity = 50;
       
        // add new product first
        pm.addProduct(id, name, category, briefDescription, quantityAvailable, price, barCode, threshold, orderQuantity);
        
        // before modification
        Product result = pm.getProductById(id);
        //product list have 11 products
        assertEquals(11, pm.getProductList().size());
       
        //all attributes are same as set
        assertEquals(id,result.getProductId());
        assertEquals(name,result.getName());
        assertEquals(category,result.getCategory());
        assertEquals(briefDescription,result.getBriefDescription());
        assertEquals(quantityAvailable,result.getQuantityAvailable());
        assertEquals(price,result.getPrice(),0.01);
        assertEquals(barCode,result.getBarCodeNumber());
        assertEquals(threshold,result.getReorderQuantity());
        assertEquals(orderQuantity,result.getOrderQuantity());
        
        String new_name = "MODI";
        Category new_category = testStore.getCategoryByCode("MUG");
        String new_briefDescription = "modi";
        int new_quantityAvailable = 1001;
        double new_price = 25.2;
        String new_barCode = "225566";
        int new_threshold = 230;
        int new_orderQuantity = 250;
        
        pm.modifyProduct(id, new_name, new_category, new_briefDescription, new_quantityAvailable, new_price, new_barCode, new_threshold, new_orderQuantity);
        
        // after modification
        result = pm.getProductById(id);
        //there is a product which id is CLO/5
        assertNotEquals(null, result);
        //product list have 11 products
        assertEquals(11, pm.getProductList().size());
        
        //all attributes are same as set
        assertEquals(id,result.getProductId());
        assertEquals(new_name,result.getName());
        
        assertEquals(new_briefDescription,result.getBriefDescription());
        assertEquals(new_quantityAvailable,result.getQuantityAvailable());
        assertEquals(new_price,result.getPrice(),0.01);
        assertEquals(new_barCode,result.getBarCodeNumber());
        assertEquals(new_threshold,result.getReorderQuantity());
        assertEquals(new_orderQuantity,result.getOrderQuantity());
        
        // category should not be changed since it relates to id
        assertNotEquals(new_category,result.getCategory());
        assertEquals(category,result.getCategory());
    }

    /**
     * Test of deleteProduct method, of class ProductMgr.
     */
    @Test
    public void testDeleteProduct() {
        System.out.println("deleteProduct");
        String id = "CLO/1";
        
        // before deletion
        Product result = pm.getProductById(id);
        // product exists
        assertNotEquals(null,result);
        //product list have 10 products
        assertEquals(10, pm.getProductList().size());
        
        pm.deleteProduct(id);
        
        // before deletion
        result = pm.getProductById(id);
        //product list have 9 products
        assertEquals(9, pm.getProductList().size());
        
        // product not exists in pm any more
        assertEquals(null,result);
       
    }

    /**
     * Test of changeProductQty method, of class ProductMgr.
     */
    @Test
    public void testChangeProductQty() {
        System.out.println("changeProductQty");
        
        String id = "CLO/1";
        int qty = 0;
        
        // before change
        Product result = pm.getProductById(id);
        assertNotEquals(null,result);
        assertNotEquals(qty,result.getQuantityAvailable());
        
        // change 
        pm.changeProductQty(result, qty);
       
        assertEquals(qty,result.getQuantityAvailable());
        
    }

    /**
     * Test of getProductById method, of class ProductMgr.
     */
    @Test
    public void testGetProductById() {
        System.out.println("getProductByBarCode");
        String id = "";
        
        Product result = pm.getProductById(id);
        
        // can not find a product that id not exists in datafile, return null
        assertEquals(null, result);
        
        id = "MUG/1";
        result = pm.getProductById(id);
        assertNotEquals(null, result);
        // id of this product is CLO/1
        assertEquals("9876", result.getBarCodeNumber());

    }

    /**
     * Test of getProductByBarCode method, of class ProductMgr.
     */
    @Test
    public void testGetProductByBarCode() {
        System.out.println("getProductByBarCode");
        String barCode = "";
        
        Product result = pm.getProductByBarCode(barCode);
        
        // can not find a product that not exists in datafile, return null
        assertEquals(null, result);
        
        
        barCode = "1234";
        result = pm.getProductByBarCode(barCode);
        assertNotEquals(null, result);
        // id of this product is CLO/1
        assertEquals("CLO/1", result.getProductId());

    }

    /**
     * Test of getProductList method, of class ProductMgr.
     */
    @Test
    public void testGetProductList() {
        System.out.println("getProductList");
        
        ArrayList<Product> result = pm.getProductList();
        assertNotEquals(result, null);
        // there are 10 product data in data files
        assertEquals(result.size(), 10);
    }
    
}
