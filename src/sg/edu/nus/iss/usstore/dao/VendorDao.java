package sg.edu.nus.iss.usstore.dao;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

import sg.edu.nus.iss.usstore.domain.Category;
import sg.edu.nus.iss.usstore.domain.Vendor;
import sg.edu.nus.iss.usstore.exception.DataFileException;
import sg.edu.nus.iss.usstore.util.Util;
/**
 * 
 * @author Avishek Kar Deb Barman
 *
 */
public class VendorDao extends BaseDao {
	// datafile name
	private static final String C_File_Name_Prxfix = "Vendors";
	private static final String C_File_Name_Suffix = ".dat";
	
	// determine if the No. of fields of a record is correct
	private static final int C_Field_No = 2;
	
	

	/**
	 * 
	 * @return
	 * @throws IOException
	 * @throws DataFileException
	 */
	public void loadDataFromFile(ArrayList<Category> categoryList) throws IOException, DataFileException {
		
		for(Category category : categoryList){
			
			// file name example: vendors MUG .dat
			String filename = C_File_Name_Prxfix + category.getCode() + C_File_Name_Suffix;
			
			ArrayList<String> stringList = null;
			
			stringList = super.loadStringFromFile(super.getcDatafolderpath() + filename);
			
			ArrayList<Vendor> vendorOfCategoryList = new ArrayList<Vendor>();
			
			StringBuffer errMsg = new StringBuffer();
			
			for(int lineNo = 0; lineNo < stringList.size() ; lineNo++){
				
				String line = stringList.get(lineNo);
				
				String[] fields = line.split(Util.C_Separator);
				
				// when the No. of fields of a record is less then C_Field_No, skip this record
				if (fields.length != C_Field_No) {
					errMsg.append("datafile[" + filename + "] LineNo:" + (lineNo + 1) + System.getProperty("line.separator"));
					continue;
				}
				
				String name = fields[0];
				String description = fields[1];
				int preference = lineNo + 1;
				
				Vendor vendor = new Vendor(name,description,preference);
				
				//add to categories' VendorList
				vendorOfCategoryList.add(vendor);
				
			}
			
			// set VendorList to related category
			category.setVendorList(vendorOfCategoryList);
			
			if (errMsg.length() > 0){
				String exceptionMsg = "Following data in file is not correct:" + System.getProperty("line.separator") + errMsg;
				throw new DataFileException(exceptionMsg);
			}
			
		}
		
	}

	/**
	 * get Vendors from category and save them to file
	 * 
	 * @param dataList
	 * @throws IOException 
	 */
	public void saveDataToFile(ArrayList<Category> categoryList) throws IOException {
		
		for(Category category : categoryList){
			
			String filename = C_File_Name_Prxfix + category.getCode() + C_File_Name_Suffix;
			
			ArrayList<String> stringList = new ArrayList<String>();
			ArrayList<Vendor> sortedVendorList = category.getVendorList();
			Collections.sort(sortedVendorList);
			for(Vendor vendor : sortedVendorList){
				StringBuffer line;
				
				line = new StringBuffer(vendor.getName() + Util.C_Separator);
				line.append(vendor.getDescription());
				
				stringList.add(line.toString());
			}
			
			super.saveStringToFile(super.getcDatafolderpath() + filename, stringList);
		}
	}
	
	
	
}
