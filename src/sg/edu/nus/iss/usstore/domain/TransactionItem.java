//TransactionItem.java
package sg.edu.nus.iss.usstore.domain;

import sg.edu.nus.iss.usstore.util.CalcUtil;

public class TransactionItem
{
	/**
	 * TransactionItem Class
	 * 
	 * @author Liu Xinzhuo
	 * @author A0136010A
	 * @version 1.0
	 */
	private Product product = null;
	private double price =0;
	private int qty = 0;
	
	public TransactionItem()
	{
	}
	
	public TransactionItem(Product product,double price,int qty)
	{
		this.product = product;
		this.price = price;
		this.qty = qty;
	}
	//seters & geters
	public Product getProduct()
	{
		return product;
	}
	//seters & geters
	public void setProduct(Product product)
	{
		this.product = product;
	}
	//seters & geters
	public double getPrice()
	{
		return price;
	}
	//seters & geters
	public void setPrice(double price)
	{
		this.price = price;
	}
	//seters & geters
	public int getQty()
	{
		return qty;
	}
	//seters & geters
	public void setQty(int qty)
	{
		this.qty = qty;
	}
	/**
	 * calculate the TransactionItem's worth
	 * @return Double
	 */
	public double calculateAmount()
	{
		return CalcUtil.mul(this.price, this.qty);
	}
	
}///~
