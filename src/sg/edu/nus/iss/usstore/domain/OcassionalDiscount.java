package sg.edu.nus.iss.usstore.domain;

import java.util.Date;
/**
 * 
 * @author tanuj
 *
 */
public class OcassionalDiscount extends Discount {

	private Date startDate;
	private int period;
	
	public OcassionalDiscount(String discountCode, String discountDescription,
			Date startDate, int period, int percent, String Applicable) {
		super(discountCode, discountDescription,percent, Applicable);
		// TODO Auto-generated constructor stub
		
		this.startDate = startDate;
		this.period=period;
	}

	/**
	 * @param startDate the startDate to set
	 */
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	/**
	 * @param period the period to set
	 */
	public void setPeriod(int period) {
		this.period = period;
	}

	public Date getStartDate() {
		// TODO Auto-generated method stub
		return startDate;
	}

	public int getPeriod(){
		return period;
	}
}


