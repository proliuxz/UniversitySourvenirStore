package sg.edu.nus.iss.usstore.domain;


import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import sg.edu.nus.iss.usstore.dao.DiscountDao;
import sg.edu.nus.iss.usstore.exception.DataFileException;
import sg.edu.nus.iss.usstore.util.Util;

/*
 * 
 * 
 * @ tanuj
 */
public class DiscountMgr {
	private ArrayList<Discount> discountList;
	private DiscountDao discountDao;
	
	public DiscountMgr(){
		discountDao = new DiscountDao();
		discountList = new ArrayList<>();
	}

	public void loadData() throws IOException, DataFileException{
		discountList = discountDao.loadDataFromFile();
	}
	
	
	public void saveData() throws IOException{
		discountDao.saveDataToFile(discountList);
		
	}

	public ArrayList<Discount> getDiscountlist(){
		return this.discountList;
	}


	
	
	/**
	 * 
	 * @param discountCode
	 * @param discountDescription
	 * @param startDate
	 * @param period
	 * @param percent
	 * @param Applicable
	 * @return
	 */
	public ArrayList<Discount> registerDiscount(String discountCode, String discountDescription,
			int percent, String Applicable) {

		discountList.add(new MemberDiscount(discountCode, discountDescription, percent, Applicable));
		return discountList;
	}

	public void writeFile() throws IOException {
		discountDao.saveDataToFile(discountList);
	}

	public void readFile() throws IOException, DataFileException {
		discountList = discountDao.loadDataFromFile();
	}

	
	/**
	 * according to customer's type, return the applicable and highest discount
	 * @tanuj
	 * @param customer
	 * @return highest discount (may be null)
	 */
	public Discount getMaxDiscount(Customer customer){
		boolean isMember = false;
		boolean hasTransaction=false;
		Discount maxDiscount = null;
	    Date currentDate= new Date();
		
		if(customer instanceof Member){
			isMember=true;
			if(((Member) customer).getLoyaltyPoint() != -1){
				hasTransaction=true;
			}
		}
		
		for(Discount discount:this.discountList){
			if (discount instanceof MemberDiscount && isMember){
			    // discount for member only && customer is a member 
				
				if(!hasTransaction && discount.getDiscountcode().equalsIgnoreCase("MEMBER_FIRST")){
					// discount for member's first purchase only
					maxDiscount = discount.getHigherDiscount(maxDiscount);
				}
				else if(!discount.getDiscountcode().equalsIgnoreCase("MEMBER_FIRST")){
					// discount for member's subseq purchase
					maxDiscount = discount.getHigherDiscount(maxDiscount);
				}
			}
			else if(discount instanceof OcassionalDiscount) {
				OcassionalDiscount od = (OcassionalDiscount)discount;
				if(od.getStartDate().compareTo(currentDate) <= 0 && 
					Util.addDays(od.getStartDate(), od.getPeriod()).compareTo(currentDate) >=0 ) {
					// occasional discount is valid for current date 
					maxDiscount = discount.getHigherDiscount(maxDiscount);
				}
			}
		}
		
		return maxDiscount;
	}

	public void addDiscount(String discountCode, String discountDescription,
			Date startDate, int period, int percent, String Applicable){
		
		Discount newDiscount = new OcassionalDiscount(discountCode,  discountDescription,
				 startDate,  period,  percent,  Applicable);
		
		this.discountList.add(newDiscount);
		
	}
	

	public void modifyMemberDiscount(String discountCode, String discountDescription,
			 int percent) {
		Discount result = getDiscountByCode(discountCode);
		result.setDiscountDescription(discountDescription);
		result.setPercent(percent);
	}
	
	public void modifyOcassionalDiscount(String discountCode, String discountDescription,
			Date startDate, int period, int percent) {
		Discount result = getDiscountByCode(discountCode);

		OcassionalDiscount od = (OcassionalDiscount) result;
		od.setDiscountDescription(discountDescription);
		od.setStartDate(startDate);
		od.setPeriod(period);
		od.setPercent(percent);
	}

	public void deleteDiscount(String code) {
		discountList.remove(getDiscountByCode(code));	
	} 

	public Discount getDiscountByCode(String code){
		Discount result = null;
		for(Discount d:discountList){
			if (code.equals(d.getDiscountcode())){
				result = d;
				break;
			}
		}
		return result;
	}

}
