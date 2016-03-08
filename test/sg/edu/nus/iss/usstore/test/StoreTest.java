package sg.edu.nus.iss.usstore.test;

import java.io.IOException;
import java.util.Date;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import sg.edu.nus.iss.usstore.domain.Member;
import sg.edu.nus.iss.usstore.domain.Product;
import sg.edu.nus.iss.usstore.domain.Public;
import sg.edu.nus.iss.usstore.domain.PurchaseOrder;
import sg.edu.nus.iss.usstore.domain.Store;
import sg.edu.nus.iss.usstore.domain.Transaction;
import sg.edu.nus.iss.usstore.exception.DataFileException;
import sg.edu.nus.iss.usstore.util.Util;
import static org.junit.Assert.*;

/**
 * test case for methods that involve with logic
 * 
 * @author Xu Minsheng
 */
public class StoreTest {

	public StoreTest() {
	}

	@BeforeClass
	public static void setUpClass() {
	}

	@AfterClass
	public static void tearDownClass() {
	}

	@Before
	public void setUp() {
	}

	@After
	public void tearDown() {
	}

	/**
	 * Test of loadData method, of class Store.
	 */
	@Test
	public void testLoadData() {
		System.out.println("loadData");
		Store instance = new Store();
		try {
			instance.loadData();
		} catch (IOException e) {
			fail("data file is missing");
		} catch (DataFileException e) {
			fail("data file contains error data");
		}

	}

	/**
	 * Test of saveData method, of class Store.
	 * 
	 * @throws DataFileException
	 * @throws IOException
	 */
	@Test
	public void testSaveData() throws IOException, DataFileException {

		System.out.println("saveData");
		Store instance = new Store();
		instance.loadData();

		try {
			instance.saveData();
		} catch (IOException e) {

			e.printStackTrace();
			fail("save data method throws a unexpected exception");
		}
	}

	/**
	 * Test of login method, of class Store.
	 * 
	 * @throws DataFileException
	 * @throws IOException
	 */
	@Test
	public void testLogin() throws IOException, DataFileException {
		System.out.println("login");

		Store instance = new Store();

		// load storekeeper data from file
		instance.loadData();

		assertFalse(instance.login("", ""));
		assertFalse(instance.login(" ", "  "));
		assertFalse(instance.login(null, null));
		assertFalse(instance.login("admin", null));
		assertFalse(instance.login(null, "admin"));
		assertFalse(instance.login("admin", ""));
		assertFalse(instance.login("", "admin"));
		assertFalse(instance.login("admin", "   "));
		assertFalse(instance.login("   ", "admin"));
		assertFalse(instance.login("admin", "noentry"));
		assertFalse(instance.login("noentry", "admin"));
		assertTrue(instance.login("admin", "admin"));

	}

	/**
	 * Test of checkout method, of class Store.
	 */
	@Test
	public void testCheckout() {
		System.out.println("checkout");
		Store instance = new Store();

		Transaction result = instance.checkout();

		// result is a new transaction without empty item list
		assertEquals(result.getItemList().size(), 0);

		// transaction which customer is public customer
		assertEquals(result.getCustomer().getClass(), Public.class);

		// since did not load data from file, discount should be null
		assertEquals(result.getDiscount(), null);
	}

	/**
	 * Test of setBillCustomer method, of class Store.
	 * 
	 * @throws DataFileException
	 * @throws IOException
	 */
	@Test
	public void testSetBillCustomer() throws IOException, DataFileException {
		System.out.println("setBillCustomer");
		Transaction transaction = null;
		String memberId = "X437F356";
		Store instance = new Store();

		// get new transaction
		transaction = instance.checkout();

		Transaction result = null;
		result = instance.setBillCustomer(transaction, memberId);
		// result not null
		assertNotEquals(result, null);
		// customer is public
		result = instance.setBillCustomer(transaction,"");
		assertEquals(result.getCustomer().getClass(), Public.class);

		// load data from file
		instance.loadData();

		result = instance.setBillCustomer(transaction, memberId);

		// result not null
		assertNotEquals(result, null);
		// customer is a member
		assertEquals(result.getCustomer().getClass(), Member.class);

	}

	/**
	 * Test of confirmPayment method, of class Store.
	 * 
	 * @throws DataFileException
	 * @throws IOException
	 */
	@Test
	public void testConfirmPayment() throws IOException, DataFileException {
		System.out.println("confirmPayment");
		Transaction transaction = null;
		Store instance = new Store();

		instance.loadData();

		// new transaction
		transaction = instance.checkout();

		transaction = instance.setBillCustomer(transaction, "");
		Product p1 = instance.getProductByBarCode("1234");
		transaction.addItem(p1, p1.getPrice(), 5);
		
		// before confirm
		int transactionNo_old = instance.getTransactionList().size();
		
		Transaction result = instance.confirmPayment(transaction);
		assertNotEquals(result.getCustomer(), null);
		assertNotEquals(result.getDiscount(), null);
		
		// after confirm
		int transactionNo_new = instance.getTransactionList().size();
		// new transaction has been added to list
		assertEquals(transactionNo_old+1, transactionNo_new);
	}

	 /**
	 * Test of getPurchaseOrder method, of class Store.
	 * @throws DataFileException 
	 * @throws IOException 
	 */
	 @Test
	 public void testGetPurchaseOrder() throws IOException, DataFileException {
		 System.out.println("getPurchaseOrder");
		 Store instance = new Store();

		 instance.loadData();
		
		 PurchaseOrder result = instance.getPurchaseOrder();
		 
		 // purchase is not null
		 assertNotEquals(result, null);
		 
		 // the date of purchase should be same as today
		 assertEquals(Util.dateToString(result.getOrderDate()),Util.dateToString(new Date()));
		 
		 // list of this purchase order should not be null
		 assertNotEquals(result.getOrderList(), null);
	 }
	

}
