package sg.edu.nus.iss.usstore.domain;

public class Product {

	private String productId;
	private Category category;
	private String name;
	private String briefDescription;
	private int quantityAvailable;
	private double price;
	private String barCodeNumber;
	private int reorderQuantity;
	private int orderQuantity;	
	
	public Product(Category category, String name,
			String briefDescription, int quantityAvailable, double price,
			String barCodeNumber, int recorderQuantity, int orderQuantity) {
		super();
		
		this.category = category;
		this.name = name;
		this.briefDescription = briefDescription;
		this.quantityAvailable = quantityAvailable;
		this.price = price;
		this.barCodeNumber = barCodeNumber;
		this.reorderQuantity = recorderQuantity;
		this.orderQuantity = orderQuantity;
	}
	
	public Product(String productId, Category category, String name,
			String briefDescription, int quantityAvailable, double price,
			String barCodeNumber, int recorderQuantity, int orderQuantity) {
		super();
		this.productId = productId;
		this.category = category;
		this.name = name;
		this.briefDescription = briefDescription;
		this.quantityAvailable = quantityAvailable;
		this.price = price;
		this.barCodeNumber = barCodeNumber;
		this.reorderQuantity = recorderQuantity;
		this.orderQuantity = orderQuantity;
	}

	public boolean checkInventoryLevel(){
		if(this.quantityAvailable <= this.reorderQuantity){
			return false;
		}
		return true;
	}
	
	public boolean compare(Product p){
		if(name.equals(p.getName())&&briefDescription.equals(p.getBriefDescription())&&category.equals(p.getCategory())
				&&price==p.getPrice()&&barCodeNumber.equals(p.getBarCodeNumber())&&reorderQuantity==p.getReorderQuantity()
				&&orderQuantity==p.getOrderQuantity()){
			return true;
		}
		return false;
	}

	public void addQuantity(int add){
		this.quantityAvailable = this.quantityAvailable + add;
	}
	
	public String getProductId() {
		return productId;
	}

	public void setProductId(String productId) {
		this.productId = productId;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getBriefDescription() {
		return briefDescription;
	}

	public void setBriefDescription(String briefDescription) {
		this.briefDescription = briefDescription;
	}

	public int getQuantityAvailable() {
		return quantityAvailable;
	}

	public void setQuantityAvailable(int quantityAvailable) {
		this.quantityAvailable = quantityAvailable;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public String getBarCodeNumber() {
		return barCodeNumber;
	}

	public void setBarCodeNumber(String barCodeNumber) {
		this.barCodeNumber = barCodeNumber;
	}

	public int getReorderQuantity() {
		return reorderQuantity;
	}

	public void setRecorderQuantity(int recorderQuantity) {
		this.reorderQuantity = recorderQuantity;
	}

	public int getOrderQuantity() {
		return orderQuantity;
	}

	public void setOrderQuantity(int orderQuantity) {
		this.orderQuantity = orderQuantity;
	}
	
}
