//TransactionTest.java
package sg.edu.nus.iss.usstore.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

import org.junit.Test;

import sg.edu.nus.iss.usstore.domain.Category;
import sg.edu.nus.iss.usstore.domain.Discount;
import sg.edu.nus.iss.usstore.domain.Member;
import sg.edu.nus.iss.usstore.domain.MemberDiscount;
import sg.edu.nus.iss.usstore.domain.OcassionalDiscount;
import sg.edu.nus.iss.usstore.domain.Product;
import sg.edu.nus.iss.usstore.domain.Transaction;
import sg.edu.nus.iss.usstore.domain.TransactionItem;
import sg.edu.nus.iss.usstore.exception.DataFileException;
import sg.edu.nus.iss.usstore.exception.DataInputException;
import sg.edu.nus.iss.usstore.util.CalcUtil;

public class TransactionTest extends Transaction
{
	/**
	 * Unit Test of Transaction
	 * 
	 * @author Liu Xinzhuo
	 * @author A0136010A
	 * @version 1.0
	 */

	Date date = new Date();
	MemberDiscount discount = new MemberDiscount("1", "2",34,"5");
	Member customer = new Member("1", "2", 3);
	Transaction t = new Transaction(1,customer,date);
	Product product1 = new Product(new Category(),"1","2",3,4.5,"6",7,8);
	Product product2 = new Product(new Category(),"11","12",13,14.15,"16",17,18);
	@Test
	public void testTransaction()
	{
		Transaction t1 = new Transaction();
		assertFalse(t1.toString()==null);
	}

	@Test
	public void testTransactionIntStringDate()
	{
		Date date = new Date();
		Transaction t2 = new Transaction(1,customer,date);
		assertEquals(1,t2.getId());
		assertEquals(customer,t2.getCustomer());
		assertEquals(date,t2.getDate());	
	}

	@Test
	public void testGetId()
	{
		assertFalse(t.getId()==0);
		assertEquals(1,t.getId());
	}

	@Test
	public void testSetId()
	{
		assertFalse(t.getId()==2);
		t.setId(2);
		assertEquals(2,t.getId());
	}

	@Test
	public void testGetDate()
	{
		assertEquals(date,t.getDate());
	}

	@Test
	public void testSetDate()
	{
		Date date2 = new Date();
		t.setDate(date2);
		assertEquals(date2,t.getDate());
	}
	
	@Test
	public void testGetCostomerID()
	{
		t.setCustomer(customer);
		assertEquals(customer,t.getCustomer());
	}

	@Test
	public void testSetCostomerID()
	{
		t.setCustomer(customer);
		assertEquals(customer,t.getCustomer());
	}
	
	@Test
	public void testSetDiscount()
	{
		Discount discount1 = new OcassionalDiscount("1","2",new Date(),3,4,"5");
		Discount discount2 = new MemberDiscount("6","7",8,"9");
		t.setDiscount(discount1);
		assertEquals(t.getDiscount(),discount1);
		t.setDiscount(discount2);
		assertEquals(t.getDiscount(),discount2);
	}
	
	@Test
	public void testgetDiscount()
	{
		Discount discount1 = new OcassionalDiscount("1","2",new Date(),3,4,"5");
		Discount discount2 = new MemberDiscount("6","7",8,"9");
		t.setDiscount(discount1);
		Discount discount3 = t.getDiscount();
		assertEquals(discount1,discount3);
		t.setDiscount(discount2);
		Discount discount4 = t.getDiscount();
		assertEquals(discount2,discount4);
	}
	
	@Test
	public void testSetItemList() throws IOException, DataFileException, DataInputException
	{
		TransactionItem ti1 = new TransactionItem(product1,2.3,4);
		TransactionItem ti2 = new TransactionItem(product2,5.6,7);
		ArrayList<TransactionItem> al1 = new ArrayList<TransactionItem>();
		al1.add(ti1);
		al1.add(ti2);
		t.setItemList(al1);
		ArrayList<TransactionItem> al2 =t.getItemList();
		assertEquals(al1,al2);
	}
	@Test
	public void testGetItemList() throws IOException, DataFileException
	{
		TransactionItem ti1 = new TransactionItem(product1,2.3,4);
		TransactionItem ti2 = new TransactionItem(product2,5.6,7);
		ArrayList<TransactionItem> al1 = new ArrayList<TransactionItem>();
		al1.add(ti1);
		al1.add(ti2);
		t.setItemList(al1);
		ArrayList<TransactionItem> al2 =t.getItemList();
		assertEquals(al1,al2);
	}
	

	
	@Test
	public void testSetCashAmount()
	{
		t.setCashAmount(100.11);
		double CashAmount = t.getCashAmount();
		assertTrue(100.11==CashAmount);
	}
	
	@Test
	public void testGetCashAmount()
	{
		t.setCashAmount(100.11);
		double CashAmount = t.getCashAmount();
		assertTrue(100.11==CashAmount);
	}

	@Test
	public void testSetRedeemedLoyaltyPoint()
	{
		t.setRedeemedLoyaltyPoint(100);
		int redeemedLoyaltyPoint = t.getRedeemedLoyaltyPoint();
		assertEquals(100,redeemedLoyaltyPoint);
	}
	
	@Test
	public void testGetRedeemedLoyaltyPoint()
	{
		t.setRedeemedLoyaltyPoint(100);
		int redeemedLoyaltyPoint = t.getRedeemedLoyaltyPoint();
		assertEquals(100,redeemedLoyaltyPoint);
	}

	@Test
	public void testAddItemProductInt() throws IOException, DataFileException
	{
		t.addItem(product1,3.4,5);
		assertEquals(t.getItemList().get(0).getProduct(),product1);
	}

	@Test
	public void testAddItemProductDoubleInt() throws IOException, DataFileException
	{
		t.addItem(product1,40.41,42);
		Product product2 = t.getItemList().get(0).getProduct();
		assertEquals(product1,product2);
	}

	@Test
	public void testRemoveItem() throws IOException, DataFileException
	{
		t.addItem(product1,40.41,42);
		t.removeItem(product1);
		assertEquals(0,t.getItemList().size());
	}

	@Test
	public void testCalcTotalPrice()
	{
		t.addItem(product1,1.2,3);
		t.addItem(product2,4.5,6);
		assertTrue(1.2*3+4.5*6==t.calcTotalPrice());
	}

	@Test
	public void testCalcDiscountPrice()
	{
		t = new Transaction();
		t.addItem(product1,1.2,3);
		t.addItem(product2,4.5,6);
		t.setDiscount(discount);
		assertTrue((1.2*3+4.5*6)* (100 - discount.getPercent())/100==t.calcDiscountPrice());
	}

	@Test
	public void testCalcChange()
	{
		t = new Transaction();
		t.setCashAmount(500.20);
		t.addItem(product1,1.2,3);
		t.addItem(product2,4.5,6);
		t.setDiscount(discount);
		assertTrue(CalcUtil.sub(500.2,CalcUtil.div((CalcUtil.mul((1.2*3+4.5*6),(100 - discount.getPercent()))),100))==t.calcChange());
	}

	@Test
	public void testCalcGainedPoint()
	{
		t.addItem(product1,1.2,3);
		t.addItem(product2,4.5,6);
		t.setDiscount(discount);
		assertTrue((int)(t.calcRest()*0.1)==t.calcGainedPoint());		
	}

	@Test
	public void testCalcRest()
	{
		t = new Transaction();
		t.addItem(product1,1.2,3);
		t.addItem(product2,4.5,6);
		t.setDiscount(discount);
		t.setRedeemedLoyaltyPoint(5);
		assertTrue(t.calcDiscountPrice()-CalcUtil.mul(5,0.05)==t.calcRest());
	}
}// /~
