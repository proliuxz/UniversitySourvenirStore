package sg.edu.nus.iss.usstore.domain;

/**
 * 
 * @author tanuj
 *
 */

public abstract class Discount {

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Discount other = (Discount) obj;
		if (Applicable == null) {
			if (other.Applicable != null)
				return false;
		} else if (!Applicable.equals(other.Applicable))
			return false;
		if (discountCode == null) {
			if (other.discountCode != null)
				return false;
		} else if (!discountCode.equals(other.discountCode))
			return false;
		if (discountDescription == null) {
			if (other.discountDescription != null)
				return false;
		} else if (!discountDescription.equals(other.discountDescription))
			return false;
		if (percent != other.percent)
			return false;
		return true;
	}

	private String discountCode;
	private String discountDescription;
	//private String startDate;
	private int percent;
	private String Applicable;

	public Discount(String discountCode, String discountDescription,
			 int percent, String Applicable) {
			this.discountCode = discountCode;
			this.discountDescription = discountDescription;
        
			this.percent=percent;
			this.Applicable=Applicable;
        
		}

	public String getDiscountcode() {
		// TODO Auto-generated method stub
		return discountCode;
	}

	public String getDiscountDescription() {
		// TODO Auto-generated method stub
		return discountDescription;
	}
	
	/**
	 * @return the discountCode
	 */
	public String getDiscountCode() {
		return discountCode;
	}

	/**
	 * @param discountCode the discountCode to set
	 */
	public void setDiscountCode(String discountCode) {
		this.discountCode = discountCode;
	}

	/**
	 * @param discountDescription the discountDescription to set
	 */
	public void setDiscountDescription(String discountDescription) {
		this.discountDescription = discountDescription;
	}

	/**
	 * @param percent the percent to set
	 */
	public void setPercent(int percent) {
		this.percent = percent;
	}

	/**
	 * @param applicable the applicable to set
	 */
	public void setApplicable(String applicable) {
		Applicable = applicable;
	}

	public int getPercent() {
		return percent;
	}

	public String getApplicable() {
		return Applicable;
	}
	
	/**
	 * compare  percent with another discount, return the higher one
	 * 
	 * @param discount
	 * @return higher discount
	 */
	public Discount getHigherDiscount(Discount discount){
		if ((discount == null) || (this.getPercent() > discount.getPercent())) 
			return this;
		else 
			return discount;
	}

}
