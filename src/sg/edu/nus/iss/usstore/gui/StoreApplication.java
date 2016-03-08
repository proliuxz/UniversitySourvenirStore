package sg.edu.nus.iss.usstore.gui;


import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

import sg.edu.nus.iss.usstore.domain.Category;
import sg.edu.nus.iss.usstore.domain.Discount;
import sg.edu.nus.iss.usstore.domain.Member;
import sg.edu.nus.iss.usstore.domain.Product;
import sg.edu.nus.iss.usstore.domain.PurchaseOrder;
import sg.edu.nus.iss.usstore.domain.Store;
import sg.edu.nus.iss.usstore.domain.Transaction;
import sg.edu.nus.iss.usstore.domain.Vendor;
import sg.edu.nus.iss.usstore.exception.DataFileException;

/**
 * Main method here
 * @author Xu Minsheng
 *
 */
public class StoreApplication {

	private Store store;
	private LoginScreen loginScreen;
	private StoreWindow storeWindow;
	
	public StoreApplication(){
		// instantiate store
		store = new Store();
		
	}
	
	/**
	 * 
	 */
	public void startup(){
		
		try {
			// load date
			store.loadData();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (DataFileException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// show login screen
		loginScreen = new LoginScreen(this);
		loginScreen.setLocationRelativeTo(null);
		loginScreen.setVisible(true);
	}
	
	/**
	 * 
	 */
	public void shutdown(){
		try {
			store.saveData();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.exit(1);
		}
		System.exit(0);
	}
	
	/**
	 * 
	 * @param username
	 * @param password
	 * @return 
	 */
	public boolean login(String username, String password){
		// authority check
		if (store.login(username, password) == true)
		{
			// close login screen 
			loginScreen.dispose();
			// show main menu
			storeWindow = new StoreWindow(this);
			storeWindow.setVisible(true);
			return true;
		}
		else
			return false;
	}
	
	public void showMenuScreen(){
		storeWindow.changeCard("mainScreen");
	}
		
	public Transaction checkOut(){
		return store.checkout();
	}
	
	public Transaction setBillCustomer(Transaction transaction, String memberId){
		return store.setBillCustomer(transaction, memberId);
	}
	
	public Transaction confirmPayment(Transaction transaction){
		return store.confirmPayment(transaction);
	}
	
	/**
	 * 
	 * @param product
	 * @param indenx
	 */
	public String getNewProductIdByCategory(String code){
		return store.getNewProductIdByCategory(code);
	}
	
	public void addProduct(String id,String name, String categoryCode, String briefDescription, 
			int quantityAvailable, double price, String barCode, int threshold, int orderQuantity){
		store.addProduct(id,name, categoryCode, briefDescription, quantityAvailable, price, barCode, threshold, orderQuantity);	
	}
	
	public void modifyProduct(String id,String name, String categoryCode, String briefDescription, 
			int quantityAvailable, double price, String barCode, int threshold, int orderQuantity){
		store.modifyProduct(id,name, categoryCode, briefDescription, quantityAvailable, price, barCode, threshold, orderQuantity);
	}
	
	public ArrayList<Product> checkInventory(){
		return store.checkInventory();
	}
	
	public PurchaseOrder getPurchaseOrder(){
		return store.getPurchaseOrder();
	}
	
	/**
	 * 
	 * @param index
	 */
	public void deleteProduct(String id){
		store.deleteProduct(id);
	}
	
	/**
	 * 
	 * @return
	 */
	public ArrayList<Product> getProductList(){
		return store.getProductList();
	}
	
	/**
	 * 
	 * @param productId
	 * @return
	 */
	public Product getProductById(String productId){
		return store.getProductById(productId);
	}
	
	/**
	 * 
	 * @param barCode
	 * @return
	 */
	public Product getProductByBarCode(String barCode){
		return store.getProductByBarCode(barCode);
	}
	
	
	/**
	 * 
	 * @param memberId
	 * @return
	 */
	public Member getMemberById(String memberId){
		return store.getMemberById(memberId);
	}
	
	/**
	 * 
	 * @return
	 */
	public ArrayList<Member> getMemberList(){
		return store.getMemberList();
	}
	
	/**
	 * 
	 * @return
	 */
	public void removeMember(String memberID){
		store.removeMember(memberID);
	}
	
	public void registerMember(String name,String id,int loyalty){
		store.registerMember(name, id,loyalty);
	}
	
	public void modifyMember(String name,String memID,int loyaltyPoint,int index){
		store.modifyMember(name, memID, loyaltyPoint, index);
	}
	
	public ArrayList<Category> getCategoryList(){
		return store.getCategoryList();
	}
	
	/**
	 * 
	 * @return
	 */
	public Category getCategoryByCode(String code){
		return store.getCategoryByCode(code);
	}
	
	/**
	 * 
	 * @param code
	 * @param name
	 * @param vendorList
	 */
	public void addCategory(String code, String name, ArrayList<Vendor> vendorList){
		store.addCategory(code, name, vendorList);
	}
	
	/**
	 * 
	 * @param code
	 * @param name
	 * @param vendorList
	 */
	public void updCategory(String code, String name){
		store.updCategory(code, name);
	}
	
	
	/**
	 * 
	 * @param code
	 */
	public void deleteCategoryByCode(String code){
		store.delCategoryByCode(code);
	}
	
	
	/**
	 * 
	 * @param categoryCode
	 * @param vendorName
	 * @param vendorDesc
	 */
	public void addVendorForCategory(String categoryCode, String vendorName, String vendorDesc){
		store.addVendorForCategory(categoryCode, vendorName, vendorDesc);
	}
	
	/**
	 * 
	 * @param categoryCode
	 * @param vendorName
	 */
	public void delVendorForCategory(String categoryCode, String vendorName){
		store.delVendorForCategory(categoryCode, vendorName);
	}
	
	/**
	 * 
	 * @param categoryCode
	 * @param oldName
	 * @param newName
	 * @param newDesc
	 */
	public void updVendorForCategory(String categoryCode, String oldName, String newName, String newDesc){
		store.updVendorForCategory(categoryCode, oldName, newName, newDesc);
	}
	
	/**
	 * 
	 * @param categoryCode
	 * @param upVendorName
	 * @param downVendorName
	 */
	public void switchVendorPrefForCategory(String categoryCode, String upVendorName, String downVendorName){
		store.switchVendorPrefForCategory(categoryCode, upVendorName, downVendorName);
	}
	
	public ArrayList<Discount> getDiscountList(){
		return store.getDiscountList();
	
	}
	
	/**
	 * 
	 * @param date
	 * @return TransactionList
	 */
	public ArrayList<Transaction> getTransactionList(){
		return store.getTransactionList();
	}
	
	/**
	 * 
	 * @param date
	 * @return TransactionList
	 */
	public ArrayList<Transaction> getTransactionListByDate(Date date){
		return store.getTransactionListByDate(date);
	}				
	
	
	public static void main(String[] args) {
		StoreApplication manager = new StoreApplication();
		manager.startup();	
		
	}

	public StoreWindow getStoreWindow() {
		return storeWindow;
	}

	/**
	 * 
	 * @param discountCode
	 * @param discountDescription
	 * @param startDate
	 * @param period
	 * @param percent
	 * @param Applicable
	 */
	public void addMemberDiscount(String discountCode, String discountDescription,
			int percent, String Applicable ){
		store.registerDiscount(discountCode, discountDescription, percent, Applicable);
	}
	
	public void addOcassionalDiscount(String discountCode, String discountDescription,
			Date startDate, int period, int percent, String Applicable ){
		store.addDiscount(discountCode, discountDescription, startDate, period, percent, Applicable);
	}
	/**
	 * 
	 * @param discountCode
	 * @param discountDescription
	 * @param percent
	 */
	public void modifyMemberDiscount(String discountCode, String discountDescription,
			int percent){
		store.modifyMemberDiscount(discountCode, discountDescription, percent);
	}
	
	/**
	 * 
	 * @param discountCode
	 * @param discountDescription
	 * @param startDate
	 * @param period
	 * @param percent
	 */
	public void modifyOcassionalDiscount(String discountCode, String discountDescription,
			Date startDate, int period, int percent){
		store.modifyOcassionalDiscount(discountCode, discountDescription, startDate, period, percent);
	}
	
	/**
	 * 
	 * @param code
	 */
	public void deleteDiscount(String code){
		store.deleteDiscount(code);
	}
	
	public Discount getDiscountByCode(String code){
		return store.getDiscountByCode(code);
	}

}
