//TransactionDao.java
package sg.edu.nus.iss.usstore.dao;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

import sg.edu.nus.iss.usstore.domain.Customer;
import sg.edu.nus.iss.usstore.domain.Product;
import sg.edu.nus.iss.usstore.domain.Public;
import sg.edu.nus.iss.usstore.domain.Store;
import sg.edu.nus.iss.usstore.domain.Transaction;
import sg.edu.nus.iss.usstore.exception.DataFileException;
import sg.edu.nus.iss.usstore.exception.DataInputException;
import sg.edu.nus.iss.usstore.util.Util;

/**
 * provide Data Access to file for Product entity
 * 
 * @author Liu Xinzhuo
 * @author A0136010A
 * @version 1.0
 */
public class TransactionDao extends BaseDao
{
	
	// datafile name
	private static final String C_File_Name = "Transactions.dat";
	// determine if the No. of fields of a record is correct
	private static final int C_Field_No = 6;
	// use store to get relevant product objects
	private Store store;
	
	public TransactionDao()
	{
		
	}
	
	public TransactionDao(Store store){
		this.store = store;
	}

	/** 
	 * @param no param
	 * @return ArrayList
	 * @exception DataInputException IOException DataFileException
	 * @throws DataInputException
	 * @throws IOException
	 * @throws DataFileException
	 */
	public ArrayList<Transaction> loadDataFromFile() throws IOException,
			DataFileException
	{
		ArrayList<String> stringList = null;
		int flag = 0;
		Transaction tflag = new Transaction();
		stringList = super.loadStringFromFile(super.getcDatafolderpath()
				+ C_File_Name);
		ArrayList<Transaction> dataList = new ArrayList<Transaction>();
		StringBuffer errMsg = new StringBuffer();
		for (int lineNo = 0; lineNo < stringList.size(); lineNo++)
		{
			String line = stringList.get(lineNo);
			String[] fields = line.split(Util.C_Separator);
			// when the No. of fields of a record is less then C_Field_No, skip
			// this record
			if (fields.length != C_Field_No)
			{
				errMsg.append("datafile[" + C_File_Name + "] LineNo:"
						+ (lineNo + 1) + System.getProperty("line.separator"));
				continue;
			}
			try
			{
				int id = Util.castInt(fields[0]);
				String productID = fields[1];
				String customerID = fields[2];
				int qty = Util.castInt(fields[3]);
				Date date = Util.castDate(fields[4]);
				double price = Util.castDouble(fields[5]);
				//System.out.println("Dao" + date);
				//ProductMgr pm = new ProductMgr();
				Product product = store.getProductById(productID);
				if (flag == id)
				{
					//TransactionItem ti = new TransactionItem(productID, qty);
					tflag.addItem(product,price,qty);
					// System.out.println("1");
				} else
				{
					Customer customer;
					if (customerID.equalsIgnoreCase("PUBLIC"))
						customer = new Public();
					else 
						customer= store.getMemberById(customerID);
					
					Transaction t = new Transaction(id, customer, date);
					t.addItem(product,price,qty);
					dataList.add(t);
					tflag = t;
					// System.out.println("2");
				}
				flag = id;
			} catch (DataInputException e)
			{
				errMsg.append("datafile[" + C_File_Name + "] LineNo:"
						+ (lineNo + 1) + System.getProperty("line.separator"));
			}
		}
		if (errMsg.length() > 0)
		{
			String exceptionMsg = "Following data in file is not correct:"
					+ System.getProperty("line.separator") + errMsg;
			throw new DataFileException(exceptionMsg);
		}
		// System.out.println(dataList.size());
		return dataList;
	}

	/**
	 * 
	 * @param dataList
	 * @throws IOException
	 */
	public void saveDataToFile(ArrayList<Transaction> dataList)
			throws IOException
			
	{
		ArrayList<String> stringList = new ArrayList<String>();
		for (int i = 0; i < dataList.size();i++)
		{
			for (int j = 0; j < dataList.get(i).getItemList().size();j++)
			{
				StringBuffer line;
				line = new StringBuffer(dataList.get(i).getId() + Util.C_Separator);
				line.append(dataList.get(i).getItemList().get(j).getProduct().getProductId() + Util.C_Separator);
				line.append(dataList.get(i).getCustomer().getID() + Util.C_Separator);
				line.append(dataList.get(i).getItemList().get(j).getQty() + Util.C_Separator);
				line.append(Util.dateToString(dataList.get(i).getDate()) + Util.C_Separator);
				line.append(dataList.get(i).getItemList().get(j).getPrice());
				stringList.add(line.toString());
			}
		}
		super.saveStringToFile(super.getcDatafolderpath() + C_File_Name,
				stringList);
	}
}///~
