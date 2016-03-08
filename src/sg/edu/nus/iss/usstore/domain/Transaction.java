//Transaction.java
package sg.edu.nus.iss.usstore.domain;

import java.util.ArrayList;
import java.util.Date;

import sg.edu.nus.iss.usstore.util.CalcUtil;

public class Transaction
{
	/**
	 * Transaction Class
	 * 
	 * @author Liu Xinzhuo
	 * @author A0136010A
	 * @version 1.0
	 */
	private int id = 0;
	private int redeemedLoyaltyPoint;
	private Date date;
	private Customer customer = new Public();
	private Discount discount = new DiscountMgr().getMaxDiscount(customer);
	private ArrayList<TransactionItem> itemList = new ArrayList<TransactionItem>();
	
	private double cashAmount = 0;
	private static double totalPrice = 0;
	private static double discountedPrice = 0;
	private int gainedPoint = 0;
	private double rest = 0;
	private double change = 0;
	
	private static final double DOLLAR_TO_POINT = 0.1;
	private static final double POINTS_TO_DOLLAR = 0.05;

	public Transaction()
	{
		this.customer = new Public();
		this.discount = new DiscountMgr().getMaxDiscount(customer);
	}
	
	public Transaction(int id,Customer customer, Date date)
	{
		this.id = id;
		this.customer=customer;
		this.date = date;
		this.customer = new Public();
		this.discount = new DiscountMgr().getMaxDiscount(customer);
	}
	//seters & geters
	public int getId()
	{
		return id;
	}
	//seters & geters
	public void setId(int id)
	{
		this.id = id;
	}
	//seters & geters
	public Date getDate()
	{
		return date;
	}
	//seters & geters
	public void setDate(Date date)
	{
		this.date = date;
	}
	//seters & geters
	public Customer getCustomer()
	{
		return customer;
	}
	//seters & geters
	public void setCustomer(Customer customer)
	{
		this.customer = customer;
	}
	//seters & geters
	public Discount getDiscount()
	{
		return discount;
	}
	//seters & geters
	public void setDiscount(Discount discount)
	{
		this.discount = discount;
	}
	//seters & geters
	public ArrayList<TransactionItem> getItemList()
	{
		return itemList;
	}
	//seters & geters
	public void setItemList(ArrayList<TransactionItem> itemList)
	{
		this.itemList = itemList;
	}
	//seters & geters
	public double getCashAmount()
	{
		return cashAmount;
	}
	//seters & geters
	public void setCashAmount(double cashAmount)
	{
		this.cashAmount = cashAmount;
	}
	//seters & geters
	public int getRedeemedLoyaltyPoint()
	{
		return redeemedLoyaltyPoint;
	}
	//seters & geters
	public void setRedeemedLoyaltyPoint(int redeemedLoyaltyPoint)
	{
		this.redeemedLoyaltyPoint = redeemedLoyaltyPoint;
	}
	/**
	 * add TransactionItem to the TransactionList and set the price automized automaticlly.
	 * @param product
	 * @param qty
	 */
	public void addItem(Product product,int qty)
	{
		TransactionItem transactionitem = new TransactionItem(product,product.getPrice(),qty);
		if (itemList.contains(transactionitem))
		{
			
		} else
		{
			itemList.add(transactionitem);
		}
	}
	/**
	 * add TransactionItem to the TransactionList and set the price from parameter.
	 * @param product
	 * @param price
	 * @param qty
	 */
	public void addItem(Product product,double price,int qty)
	{
		TransactionItem transactionitem = new TransactionItem(product,price,qty);
		if (itemList.contains(transactionitem))
		{
			
		} else
		{
			itemList.add(transactionitem);
		}
	}
	/**
	 * remove TransactionItem from  TransactionList
	 * @param product
	 */
	public void removeItem(Product product)
	{
		for(int i = 0; i<itemList.size();i++)
		{
			if (itemList.get(i).getProduct()==product)
				itemList.remove(i);
		}
	}
	/**
	 * calculate the Total Price
	 * @return Double
	 */
	public double calcTotalPrice()
	{
		double sum = 0;
		for (int i = 0; i < itemList.size(); i++)
		{
			TransactionItem it = (TransactionItem) itemList.get(i);
			sum = CalcUtil.add(sum,it.calculateAmount());
		}
		totalPrice = sum;
		return totalPrice;
	}
	/**
	 * calculate the Discounted Price
	 * @return Double
	 */
	public double calcDiscountPrice()
	{
		if (discount==null)
			return calcTotalPrice();
		discountedPrice = CalcUtil.div((CalcUtil.mul(calcTotalPrice(),(100 - discount.getPercent()))),100);
		return discountedPrice;
	}
	/**
	 * calculate the Change
	 * @return Double
	 */
	public double calcChange()
	{
		change =  CalcUtil.sub(cashAmount,calcRest());
		return change;
	}
	/**
	 * calculate the Gained Point
	 * @return Integer
	 */
	public int calcGainedPoint()
	{
		gainedPoint = (int) CalcUtil.mul(calcRest(),(double)DOLLAR_TO_POINT);
		return gainedPoint;
	}
	/**
	 * calculate the Rest Money the customer need to pay
	 * @return Double
	 */
	public double calcRest()
	{
		rest = calcDiscountPrice() - CalcUtil.mul(redeemedLoyaltyPoint, POINTS_TO_DOLLAR);
		return rest;
	}
}// /~