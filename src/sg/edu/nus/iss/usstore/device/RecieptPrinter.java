package sg.edu.nus.iss.usstore.device;

import sg.edu.nus.iss.usstore.domain.Transaction;
import sg.edu.nus.iss.usstore.domain.TransactionItem;

/**
 * 
 * @author tanuj
 *
 */
public class RecieptPrinter {

	private Transaction transaction;
	public RecieptPrinter(Transaction transaction) {
		// TODO Auto-generated constructor stub
		this.transaction =  transaction;
	}
	
	public void print()
	{		
		/**
	 * 	writes on the console the transaction in the below given format
	 * after finishing of the transaction
	 */
		System.out.println("##################################################################################");
		System.out.print(String.format("%1$-" + 30 + "s", "Tran ID : "+transaction.getId()));
		System.out.println(String.format("%0$"+10+"s", "Date : "+transaction.getDate()));
		System.out.print(String.format("%1$-" + 30 + "s", "Member ID : "+transaction.getCustomer().getID()));
		System.out.println(String.format("%0$"+10+"s", "Loyalty Points : "+transaction.getRedeemedLoyaltyPoint()));
		System.out.println("==================================================================================");
		System.out.print(String.format("%1$-" + 50 + "s", "Product"));
		System.out.print(String.format("%0$"+10+"s", String.format("%1$-" + 5 + "s", "Quantity")));
		System.out.print(String.format("%0$"+10+"s", String.format("%1$-" + 5 + "s", "Price")));
		System.out.println(String.format("%0$"+14+"s", String.format("%1$-" + 5 + "s", "Total Price")));
		System.out.println("===================================================================================");
		for(TransactionItem item:transaction.getItemList()){
		System.out.print(String.format("%1$-" + 50 + "s", item.getProduct().getBriefDescription()).substring(0, 50));
		System.out.print(String.format("%0$"+7+"s", String.format("%1$-" + 5 + "s", item.getQty())));
		System.out.print(String.format("%0$"+12+"s", String.format("%1$-" + 5 + "s", item.getPrice())));
		System.out.println(String.format("%0$"+12+"s", String.format("%1$-" + 5 + "s", item.calculateAmount())));
		System.out.println("-------------------------------------------------------------------------------------");
		}
		System.out.print(String.format("%0$"+70+"s", "Total Price   :S$ "));
		System.out.println(String.format("%0$"+5+"s", String.format("%1$-" + 5 + "s", Math.round(transaction.calcTotalPrice()))));
		System.out.print(String.format("%0$"+70+"s", "Discount (-)  :S$ "));
		System.out.println(String.format("%0$"+5+"s", String.format("%1$-" + 5 + "s", Math.round((transaction.calcTotalPrice()-transaction.calcDiscountPrice())))));
		System.out.print(String.format("%0$"+70+"s", "Final Price   :S$ "));
		System.out.println(String.format("%0$"+5+"s", String.format("%1$-" + 5 + "s",  Math.round( transaction.calcDiscountPrice()))));
		System.out.println("#####################################################################################");
	}

}

