package sg.edu.nus.iss.usstore.domain;

import java.util.ArrayList;
import java.util.Collections;

/**
 * 
 * @author Avishek Kar Deb Barman
 *
 */
public class Category {
	private String code;
	private String name;
	private ArrayList<Vendor> vendorList;
	
	public Category(){
		this.code = "";
		this.name = "";
	}
	
	
	public Category(String code, String name, ArrayList<Vendor> vendorList) {
		super();
		this.code = code;
		this.name = name;
		this.vendorList = vendorList;
	}
	
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public ArrayList<Vendor> getVendorList() {
		return vendorList;
	}
	public void setVendorList(ArrayList<Vendor> vendorList) {
		this.vendorList = vendorList;
	}
	
	/**
	 * 
	 * @return most preference vendor
	 */
	public Vendor getPreferenceVendor(){
		Vendor result = null;
		
		// has vendor
		if (!this.vendorList.isEmpty()){
			Collections.sort(this.vendorList);
			result = this.vendorList.get(0);
		}
		
		return result;
	}

	public void newVendor(String vendorName, String vendorDesc){
		Vendor newVendor = new Vendor(vendorName, vendorDesc, this.vendorList.size() + 1);
		this.vendorList.add(newVendor );
	}
	
	/**
	 * 
	 * @param vendorName
	 */
	public void delVendor(String vendorName){
		this.vendorList.remove(getVendorByName(vendorName));
		
		//resort the whole vendor list
		Collections.sort(this.vendorList);
		
		//reset preference order
		for(int i = 0 ; i<this.vendorList.size();i++){
			this.vendorList.get(i).setPreference(i+1);
		}
	}
	
	/**
	 * 
	 * @param oldName
	 * @param newName
	 * @param newDesc
	 */
	public void updVendor(String oldName, String newName, String newDesc){
		Vendor vendor = getVendorByName(oldName);
		vendor.setName(newName);
		vendor.setDescription(newDesc);
	}
	
	/**
	 * 
	 * @param upVendorName
	 * @param downVendorName
	 */
	public void switchVendorPref(String upVendorName, String downVendorName){
		Vendor upVendor = getVendorByName(upVendorName);
		Vendor downVendor = getVendorByName(downVendorName);
		int upPref = upVendor.getPreference();
		upVendor.setPreference(downVendor.getPreference());
		downVendor.setPreference(upPref);
		
		Collections.sort(this.vendorList);
	}
	
	/**
	 * 
	 * @param vendorName
	 * @return
	 */
	private Vendor getVendorByName(String vendorName){
		Vendor result = null;
		for(Vendor vendor : this.vendorList){
			if(vendor.getName().equals(vendorName)){
				result = vendor;
			}
		}
		return result;
	}
}
