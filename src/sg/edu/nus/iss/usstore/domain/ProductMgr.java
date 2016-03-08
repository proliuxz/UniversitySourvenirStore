package sg.edu.nus.iss.usstore.domain;

import java.io.IOException;
import java.util.ArrayList;

import sg.edu.nus.iss.usstore.dao.ProductDao;
import sg.edu.nus.iss.usstore.exception.DataFileException;

/*
 * ProductManager
 * @ XIE JIABAO
 */

public class ProductMgr {
	
	private ArrayList<Product> productList;
	private ProductDao productDao;
	
	public ProductMgr(Store store){
		productDao = new ProductDao(store);
	}

	//load data from file
	public void loadData() throws IOException, DataFileException{
		productList = productDao.loadDataFromFile();
	}
	
	//save data to file
	public void saveData() throws IOException{
		productDao.saveDataToFile(productList);
	}
	
	//check the products below thresholds and return list
	public ArrayList<Product> checkInventory(){
		ArrayList<Product> orderList = new ArrayList<Product>();
		for(int i=0;i<productList.size();i++){
			if(!productList.get(i).checkInventoryLevel()){
				orderList.add(productList.get(i));
			}
		}
		return orderList;
	}
	
	public String getNewProductIdByCategory(String categoryCode){
		String newId;
		int i = -1;
		int j = -1;
		for(Product p:this.productList){
			if(categoryCode.equals(p.getCategory().getCode())){
				if(i==-1){
					i = Integer.parseInt(p.getProductId().substring(4))+1;
				}else{
					j = Integer.parseInt(p.getProductId().substring(4))+1;
					if(j>i){
						i = j;
					}
				}
			}
		}
		if(i==-1){
			newId = categoryCode + "/1";
		}else{
			newId = categoryCode + "/" + Integer.toString(i);
		}
		return newId;
	}
	
	//add new product or implement product quantity if product exists
	public void addProduct(String id,String name, Category category, String briefDescription, 
			int quantityAvailable, double price, String barCode, int threshold, int orderQuantity){
		Product product = new Product(category, name, 
				briefDescription, quantityAvailable, price, barCode, threshold, orderQuantity);
		int i = 0;
		for(;i<this.productList.size();i++){
			if(product.compare(productList.get(i))){
				break;
			}
		}
		if(i>=productList.size()){
			//add new product
			//String code = category.getCode();
			product.setProductId(id);
			productList.add(product);
		}else{
			//add quantity of existed product
			productList.get(i).addQuantity(quantityAvailable);
		}
	}
	
	public void modifyProduct(String id, String name, Category category, String briefDescription, 
			int quantityAvailable, double price, String barCode, int threshold, int orderQuantity){
		
		Product product = getProductById(id);
		product.setName(name);
		product.setBriefDescription(briefDescription);
		product.setQuantityAvailable(quantityAvailable);
		product.setPrice(price);
		product.setBarCodeNumber(barCode);
		product.setRecorderQuantity(threshold);
		product.setOrderQuantity(orderQuantity);
		
	}
	
	public void deleteProduct(String id){
		for(int i =0;i<productList.size();i++){
			if(id.equals(productList.get(i).getProductId())){
				this.productList.remove(i);
				break;
			}
		}
	}
	
	//change quantity of product when checkout 
	public void changeProductQty(Product p, int qty){
		if(productList.contains(p)){
			int i = productList.indexOf(p);
			productList.get(i).setQuantityAvailable(qty);
		}
		
	}
	
	public Product getProductById(String id){
		for(int x=0;x<productList.size();x++){
			if(id.equals(productList.get(x).getProductId())){
				return productList.get(x);
			}
		}
		return null;
	}
	
	//return the bar code of product
	public Product getProductByBarCode(String barCode){
		for(int x=0;x<productList.size();x++){
			if(barCode.equals(productList.get(x).getBarCodeNumber())){
				return productList.get(x);
			}
		}
		return null;
	}

	public ArrayList<Product> getProductList(){
		return this.productList;
	}
	
}
