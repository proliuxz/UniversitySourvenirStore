package sg.edu.nus.iss.usstore.dao;

import java.io.IOException;
import java.util.ArrayList;

import sg.edu.nus.iss.usstore.domain.Category;
import sg.edu.nus.iss.usstore.domain.Product;
import sg.edu.nus.iss.usstore.domain.Store;
import sg.edu.nus.iss.usstore.exception.DataFileException;
import sg.edu.nus.iss.usstore.exception.DataInputException;
import sg.edu.nus.iss.usstore.util.Util;

/**
 * provide Data Access to file for Product entity
 * 
 * @author Xu Minsheng
 *
 */
public class ProductDao extends BaseDao{

	// datafile name
	private static final String C_File_Name = "Products.dat";
	// determine if the No. of fields of a record is correct
	private static final int C_Field_No = 8;
	// use store to get relevant category objects
	private Store store;
	
	/**
	 * 
	 */
	public ProductDao(Store store) {
		this.store = store;
	}

	/**
	 * 
	 * @return
	 * @throws DataInputException 
	 * @throws IOException 
	 * @throws DataFileException 
	 */
	public ArrayList<Product> loadDataFromFile() throws IOException, DataFileException {
		ArrayList<String> stringList = null;
		
		stringList = super.loadStringFromFile(super.getcDatafolderpath() + C_File_Name);
		
		ArrayList<Product> dataList = new ArrayList<Product>();
		
		StringBuffer errMsg = new StringBuffer();
		
		for(int lineNo = 0; lineNo < stringList.size() ; lineNo++){
			
			String line = stringList.get(lineNo);
			
			String[] fields = line.split(Util.C_Separator);
			
			// when the No. of fields of a record is less then C_Field_No, skip this record
			if (fields.length != C_Field_No) {
				errMsg.append("datafile[" + C_File_Name + "] LineNo:" + (lineNo + 1) + System.getProperty("line.separator"));
				continue;
			}
			
			try {
				String productId = fields[0];
				String categoryCode = productId.substring(0, 3);
				Category category = store.getCategoryByCode(categoryCode);
				
				String name = fields[1];
				String briefDescription = fields[2];
				int quantityAvaible = Util.castInt(fields[3]);
				double price = Util.castDouble(fields[4]);
				String barCodeNumber = fields[5];
				int reorderQuantity = Util.castInt(fields[6]);
				int orderQuantity = Util.castInt(fields[7]);
				
				Product product = new Product(productId, category, name, briefDescription, 
						quantityAvaible, price, barCodeNumber, reorderQuantity, orderQuantity);
				
				dataList.add(product);
			}catch(DataInputException e){
				errMsg.append("datafile[" + C_File_Name + "] LineNo:" + (lineNo + 1) + System.getProperty("line.separator"));
			}
		}
		
		if (errMsg.length() > 0){
			String exceptionMsg = "Following data in file is not correct:" + System.getProperty("line.separator") + errMsg;
			throw new DataFileException(exceptionMsg);
		}
		
		return dataList;
	}

	/**
	 * 
	 * @param dataList
	 * @throws IOException 
	 */
	public void saveDataToFile(ArrayList<Product> dataList) throws IOException {
		
		ArrayList<String> stringList = new ArrayList<String>();
		
		for(Product product : dataList){
			StringBuffer line;
			
			line = new StringBuffer(product.getProductId() + Util.C_Separator);
			line.append(product.getName() + Util.C_Separator);
			line.append(product.getBriefDescription() + Util.C_Separator);
			line.append(product.getQuantityAvailable() + Util.C_Separator);
			line.append(product.getPrice() + Util.C_Separator);
			line.append(product.getBarCodeNumber() + Util.C_Separator);
			line.append(product.getReorderQuantity() + Util.C_Separator);
			line.append(product.getOrderQuantity());
			
			stringList.add(line.toString());
		}
		
		super.saveStringToFile(super.getcDatafolderpath() + C_File_Name, stringList);
		
	}

	
}
