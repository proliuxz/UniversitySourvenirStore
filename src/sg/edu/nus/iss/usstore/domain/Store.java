package sg.edu.nus.iss.usstore.domain;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

import sg.edu.nus.iss.usstore.exception.DataFileException;

/**
 * 
 * @author Xu Minsheng
 *
 */
public class Store {

	private StoreKeeperMgr storekeeperMgr;	
	private MemberMgr memberMgr;
	private TransactionMgr transactionMgr;
	private ProductMgr productMgr;
	private CategoryMgr categoryMgr;
	private DiscountMgr discountMgr;
	
	/**
	 * Instantiate Mgr
	 *
	 */
	public Store(){
		storekeeperMgr = new StoreKeeperMgr();
		categoryMgr = new CategoryMgr();
		memberMgr = new MemberMgr();
		discountMgr = new DiscountMgr();
		productMgr = new ProductMgr(this);
		transactionMgr = new TransactionMgr(this);
	}
	
	/**
	 * load data from files
	 * 
	 * @throws IOException
	 * @throws DataFileException
	 */
	public void loadData() throws IOException, DataFileException{
		storekeeperMgr.loadData();
		categoryMgr.loadData();
		memberMgr.readFile();
		discountMgr.loadData();
		productMgr.loadData();
		transactionMgr.loadData();
	}
	
	/**
	 * save all data to files
	 * 
	 * @throws IOException
	 */
	public void saveData() throws IOException{
		memberMgr.writeFile();
		transactionMgr.save();
		productMgr.saveData();
		categoryMgr.saveData();
		discountMgr.saveData();
	}
	
	/**
	 * 
	 * @param username
	 * @param password
	 * @return
	 */
	public boolean login(String username, String password){
		return storekeeperMgr.checkAuthority(username, password);
	}
	
//  -------------------- transaction related methods	-------------------

	/**
	 * create a new transaction instance with public customer
	 * 
	 * @return new transaction
	 */
	public Transaction checkout(){
		Transaction transaction = new Transaction();
		Customer newCustomer = new Public();
		transaction.setCustomer(newCustomer);
		Discount discount = discountMgr.getMaxDiscount(newCustomer);
		transaction.setDiscount(discount);
		return transaction;
	}
	
	/**
	 * set Customer info. and according to it, get Highest Discount 
	 * 
	 * @param transaction
	 * @param memberId
	 * @return
	 */
	public Transaction setBillCustomer(Transaction transaction, String memberId){
		
		Customer customer;
		Discount discount;
		
		// get customer info
		if (memberId==null || memberId.equals("")){
			customer = new Public();
		}else{
			customer = memberMgr.getMemberByID(memberId);
		}
		
		// get max discount
		discount = discountMgr.getMaxDiscount(customer);
		
		//set customer and discount to transaction
		transaction.setCustomer(customer);
		transaction.setDiscount(discount);
		
		return transaction;
	}
	
	/**
	 * 
	 */
	public Transaction confirmPayment(Transaction transaction){
	
		// add to transaction list
		transaction.setId(transactionMgr.getMaxId() + 1);
		transaction.setDate(new Date());
		transactionMgr.addTransaction(transaction);
		
		// update product's quantity
		ArrayList<TransactionItem> itemList = transaction.getItemList();
		for(TransactionItem item : itemList){
			productMgr.changeProductQty(item.getProduct(), item.getProduct().getQuantityAvailable() - item.getQty());
		}
		
		// update Member's loyalty point
		Customer customer = transaction.getCustomer();
		if (customer instanceof Member){
			Member member = (Member) customer;
			int originalPoint = member.getLoyaltyPoint();
			int currentPoint = originalPoint
					- transaction.getRedeemedLoyaltyPoint() + transaction.calcGainedPoint();
			member.setLoyaltyPoint(currentPoint);
		}

		return transaction;
	}
	
	/**
	 * 
	 * @param date
	 * @return TransactionList
	 */
	public ArrayList<Transaction> getTransactionList(){
		return transactionMgr.getTransactionList();
	}
	

	/**
	 * 
	 * @param date
	 * @return TransactionList
	 */
	public ArrayList<Transaction> getTransactionListByDate(Date date){
		return transactionMgr.getTransactionListByDate(date);
	}
	
	

//  -------------------- member related methods	-------------------
	/**
	 * 
	 * @param name
	 * @param memberId
	 */	
	public void registerMember(String name, String memberId,int loyalty){
		memberMgr.registerMember(name, memberId,loyalty);
	}
	
	/**
	 * 
	 * @param memberId
	 * @return
	 */
	public Member getMemberById(String memberId){
		return memberMgr.getMemberByID(memberId);
	}
	
	/**
	 * 
	 * @return
	 */
	public ArrayList<Member> getMemberList(){
		return memberMgr.getMemberList();
	}
	
	public void removeMember(String memberID){
		 memberMgr.removeMember(memberID);
	}
	
	public void modifyMember(String name,String memID,int loyaltyPoint,int index){
		memberMgr.modifyMember(name, memID, loyaltyPoint, index);
	}
	
	public Member getMemberByID(String memID){
		return memberMgr.getMemberByID(memID);
	}
	
//  -------------------- discount related methods	-------------------
	public ArrayList<Discount> registerDiscount(String discountCode, String discountDescription,
			int percent, String Applicable){
		return discountMgr.registerDiscount(discountCode, discountDescription, percent, Applicable);
	}
	
//  -------------------- product related methods	-------------------
	
	/**
	 * according to given Category code, return a new product id
	 * 
	 * @param code
	 * @return
	 */
	public String getNewProductIdByCategory(String code){
		return productMgr.getNewProductIdByCategory(code);
	}

	
	/**
	 * 
	 * @param id
	 * @param name
	 * @param categoryCode
	 * @param briefDescription
	 * @param quantityAvailable
	 * @param price
	 * @param barCode
	 * @param threshold
	 * @param orderQuantity
	 */
	public void addProduct(String id,String name, String categoryCode, String briefDescription, 
			int quantityAvailable, double price, String barCode, int threshold, int orderQuantity){
		
		productMgr.addProduct(id,name, categoryMgr.getCategoryByCode(categoryCode),
				briefDescription, quantityAvailable, price, barCode, threshold, orderQuantity);
	}
	
	/**
	 * 
	 * @param id
	 * @param name
	 * @param categoryCode
	 * @param briefDescription
	 * @param quantityAvailable
	 * @param price
	 * @param barCode
	 * @param threshold
	 * @param orderQuantity
	 */
	public void modifyProduct(String id,String name, String categoryCode, String briefDescription, 
			int quantityAvailable, double price, String barCode, int threshold, int orderQuantity){
		productMgr.modifyProduct(id,name, categoryMgr.getCategoryByCode(categoryCode), briefDescription
				, quantityAvailable, price, barCode, threshold, orderQuantity);
		
	}
	/**
	 * 
	 * @param index
	 */
	public void deleteProduct(String id){
		productMgr.deleteProduct(id);
	}
	
	/**
	 * 
	 * @return
	 */
	public ArrayList<Product> getProductList(){
		return productMgr.getProductList();
	}
	
	/**
	 * 
	 * @return
	 */
	public Product getProductById(String productId){
		return productMgr.getProductById(productId);
	}
	
	/**
	 * 
	 * @param barCode
	 * @return
	 */
	public Product getProductByBarCode(String barCode){
		return productMgr.getProductByBarCode(barCode);
	}
	
	public ArrayList<Product> checkInventory(){
		return productMgr.checkInventory();
	}
	
	/**
	 * 
	 * @return
	 */
	public PurchaseOrder getPurchaseOrder(){
		
		PurchaseOrder purchaseOrder = new PurchaseOrder();
		HashMap<Product,Vendor> purchaseList = new HashMap<Product,Vendor>();
		ArrayList<Product> productList = null;
		productList = productMgr.checkInventory();

		for(Product p:productList){
			purchaseList.put(p,p.getCategory().getPreferenceVendor());
		}
		
		purchaseOrder.setOrderDate(new Date());
		purchaseOrder.setOrderList(purchaseList);
		
		return purchaseOrder;
	}
	
//  -------------------- category related methods	-------------------
	/**
	 * 
	 * @param code
	 * @param name
	 * @param vendorNameList
	 */
	public void addCategory(String code, String name, ArrayList<Vendor> vendorList){
		categoryMgr.addCategory(code, name, vendorList);
	}
	
	/**
	 * 
	 * @param code
	 * @param name
	 * @param vendorNameList
	 */
	public void updCategory(String code, String name){
		categoryMgr.updCategory(code, name);
	}
	
	/**
	 * 
	 * @param code
	 */
	public void delCategoryByCode(String code){
		categoryMgr.delCategoryByCode(code);
	}
	
	
	/**
	 * 
	 * @return
	 */
	public ArrayList<Category> getCategoryList(){
		return categoryMgr.getCategoryList();
	}
	
	/**
	 * 
	 * @return
	 */
	public Category getCategoryByCode(String code){
		if(getCategoryList().size()==0){
			return null;
		}
		return categoryMgr.getCategoryByCode(code);
	}
	

	/**
	 * 
	 * @param categoryCode
	 * @param vendorName
	 * @param vendorDesc
	 */
	public void addVendorForCategory(String categoryCode, String vendorName, String vendorDesc){
		categoryMgr.addVendorForCategory(categoryCode, vendorName, vendorDesc);
	}
	
	/**
	 * 
	 * @param categoryCode
	 * @param vendorName
	 */
	public void delVendorForCategory(String categoryCode, String vendorName){
		categoryMgr.delVendorForCategory(categoryCode, vendorName);
	}
	
	/**
	 * 
	 * @param categoryCode
	 * @param oldName
	 * @param newName
	 * @param newDesc
	 */
	public void updVendorForCategory(String categoryCode, String oldName, String newName, String newDesc){
		categoryMgr.updVendorForCategory(categoryCode, oldName, newName, newDesc);
	}
	
	/**
	 * 
	 * @param categoryCode
	 * @param upVendorName
	 * @param downVendorName
	 */
	public void switchVendorPrefForCategory(String categoryCode, String upVendorName, String downVendorName){
		categoryMgr.switchVendorPrefForCategory(categoryCode, upVendorName, downVendorName);
	}
	
	 // --------------------- discount related ----------------------------------
	/**
	 * 
	 * @param discountCode
	 * @param discountDescription
	 * @param startDate
	 * @param period
	 * @param percent
	 * @param Applicable
	 */
	public void addDiscount(String discountCode, String discountDescription,
			Date startDate, int period, int percent, String Applicable ){
		discountMgr.addDiscount(discountCode, discountDescription, startDate, period, percent, Applicable);
	}
	
	/**
	 * 
	 * @param discountCode
	 * @param discountDescription
	 * @param percent
	 */
	public void modifyMemberDiscount(String discountCode, String discountDescription,
			int percent){
		discountMgr.modifyMemberDiscount(discountCode, discountDescription, percent);
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
		discountMgr.modifyOcassionalDiscount(discountCode, discountDescription, startDate, period, percent);
	}
	
	/**
	 * 
	 * @param code
	 */
	public void deleteDiscount(String code){
		discountMgr.deleteDiscount(code);
	}

	/**
	 * 
	 * @return
	 */
	public ArrayList<Discount> getDiscountList(){
		return discountMgr.getDiscountlist();
	}

	public Discount getDiscountByCode(String code){
		return discountMgr.getDiscountByCode(code);
	}
}