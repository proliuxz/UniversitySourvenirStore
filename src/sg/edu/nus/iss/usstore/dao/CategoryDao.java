package sg.edu.nus.iss.usstore.dao;

import java.io.IOException;
import java.util.ArrayList;

import sg.edu.nus.iss.usstore.domain.Category;
import sg.edu.nus.iss.usstore.exception.DataFileException;
import sg.edu.nus.iss.usstore.util.Util;

/**
 * 
 * @author Avishek Kar Deb Barman
 *
 */
public class CategoryDao extends BaseDao {

	// datafile name
	private static final String C_File_Name = "Category.dat";
	// determine if the No. of fields of a record is correct
	private static final int C_Field_No = 2;
	
	/**
	 * 
	 * @return
	 * @throws IOException
	 * @throws DataFileException
	 */
	public ArrayList<Category> loadDataFromFile() throws IOException, DataFileException {
		ArrayList<String> stringList = null;
		
		stringList = super.loadStringFromFile(super.getcDatafolderpath() + C_File_Name);
		
		ArrayList<Category> dataList = new ArrayList<Category>();
		
		StringBuffer errMsg = new StringBuffer();
		
		for(int lineNo = 0; lineNo < stringList.size() ; lineNo++){
			
			String line = stringList.get(lineNo);
			
			String[] fields = line.split(Util.C_Separator);
			
			// when the No. of fields of a record is less then C_Field_No, skip this record
			if (fields.length != C_Field_No) {
				errMsg.append("datafile[" + C_File_Name + "] LineNo:" + (lineNo + 1) + System.getProperty("line.separator"));
				continue;
			}
			
			String code = fields[0];
			String name = fields[1];
			
			Category category = new Category(code, name, null);
			
			dataList.add(category);
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
	public void saveDataToFile(ArrayList<Category> dataList) throws IOException {
		
		ArrayList<String> stringList = new ArrayList<String>();
		
		for(Category category : dataList){
			StringBuffer line;
			
			line = new StringBuffer(category.getCode() + Util.C_Separator);
			line.append(category.getName());
			
			stringList.add(line.toString());
		}
		
		super.saveStringToFile(super.getcDatafolderpath() + C_File_Name, stringList);
		
	}
	
}
