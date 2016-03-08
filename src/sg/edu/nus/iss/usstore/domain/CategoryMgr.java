package sg.edu.nus.iss.usstore.domain;

import java.io.IOException;
import java.util.ArrayList;
import sg.edu.nus.iss.usstore.dao.CategoryDao;
import sg.edu.nus.iss.usstore.dao.VendorDao;
import sg.edu.nus.iss.usstore.exception.DataFileException;

/**
 * 
 * @author Avishek Kar Deb Barman
 *
 */

public class CategoryMgr {
	private ArrayList<Category> categoryList;
	// this VendorList only exist for maintain data consistency
	// for example, if CLO and MUG share one vendor Nancy's , 
	// then in CLO and MUG, their vendors should reference to same instance of vendor  
	//private ArrayList<Vendor> vendorList;
	
	private CategoryDao categoryDao;
	private VendorDao vendorDao;
	
	/**
	 * 
	 */
	public CategoryMgr(){
		categoryDao = new CategoryDao();
		vendorDao = new VendorDao();
	}
	
	/**
	 * load data from file
	 * 
	 * @throws IOException
	 * @throws DataFileException
	 */
	public void loadData() throws IOException, DataFileException{
		// load category basic info.
		categoryList = categoryDao.loadDataFromFile();
		// load vendors and set to category
		vendorDao.loadDataFromFile(categoryList);
	}
	
	/**
	 * save data to file
	 * 
	 * @throws IOException
	 */
	public void saveData() throws IOException{
		categoryDao.saveDataToFile(categoryList);
		vendorDao.saveDataToFile(categoryList);
	}
	
	
	public void setCategoryList(ArrayList<Category> categoryList) {
        this.categoryList = categoryList;
    }
	
	/**
	 * 
	 * @param code
	 * @return
	 */
	public Category getCategoryByCode(String code){
		for(Category category : this.categoryList){
			if(code.equals(category.getCode())){
				return category;
			}
		}
		return null;
	}
	
	/**
	 * 
	 * @return
	 */
	public ArrayList<Category> getCategoryList(){
		return this.categoryList;
	}
	

	
	/**
	 * 
	 * @param code
	 * @param name
	 * @param vendorList
	 */
	public void addCategory(String code, String name, ArrayList<Vendor> vendorList){
		Category category = new Category(code, name, vendorList);
		this.categoryList.add(category);
		
	}
	
	/**
	 * 
	 * @param code
	 * @param name
	 * @param vendorList
	 */
	public void updCategory(String code, String name){
		Category category = this.getCategoryByCode(code);
		category.setName(name);
		
	}
	
	/**
	 * 
	 * @param code
	 */
	public void delCategoryByCode(String code){
		Category category = this.getCategoryByCode(code);
		this.categoryList.remove(category);
	}

	
	/**
	 * 
	 * @param categoryCode
	 * @param vendorName
	 * @param vendorDesc
	 */
	public void addVendorForCategory(String categoryCode, String vendorName, String vendorDesc){
		Category category = getCategoryByCode(categoryCode);
		category.newVendor(vendorName, vendorDesc);
	}
	
	/**
	 * 
	 * @param categoryCode
	 * @param vendorName
	 */
	public void delVendorForCategory(String categoryCode, String vendorName){
		Category category = getCategoryByCode(categoryCode);
		category.delVendor(vendorName);
	}
	
	/**
	 * 
	 * @param categoryCode
	 * @param oldName
	 * @param newName
	 * @param newDesc
	 */
	public void updVendorForCategory(String categoryCode, String oldName, String newName, String newDesc){
		Category category = getCategoryByCode(categoryCode);
		category.updVendor(oldName, newName, newDesc);
	}
	
	/**
	 * 
	 * @param categoryCode
	 * @param upVendorName
	 * @param downVendorName
	 */
	public void switchVendorPrefForCategory(String categoryCode, String upVendorName, String downVendorName){
		Category category = getCategoryByCode(categoryCode);
		category.switchVendorPref(upVendorName, downVendorName);
	
	}
	
	
	
}
