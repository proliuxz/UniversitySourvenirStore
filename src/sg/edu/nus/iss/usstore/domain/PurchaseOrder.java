package sg.edu.nus.iss.usstore.domain;

import java.util.Date;
import java.util.HashMap;

public class PurchaseOrder {
	private Date orderDate;
	private HashMap<Product, Vendor> orderList;
	
	/**
	 * @return the orderDate
	 */
	public Date getOrderDate() {
		return orderDate;
	}
	/**
	 * @param orderDate the orderDate to set
	 */
	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}
	/**
	 * @return the orderList
	 */
	public HashMap<Product, Vendor> getOrderList() {
		return orderList;
	}
	/**
	 * @param orderList the orderList to set
	 */
	public void setOrderList(HashMap<Product, Vendor> orderList) {
		this.orderList = orderList;
	}
	
	
	
}
