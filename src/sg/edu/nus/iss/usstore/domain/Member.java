package sg.edu.nus.iss.usstore.domain;

/**
 * @author Achyut Suresh Rao
 */

public class Member extends Customer {

	private static final long serialVersionUID = 1L;
	private String memberID;
	private int loyaltyPoint;

	public Member(String name, String memberID, int loyaltyPoint) {
		super(name);
		this.memberID = memberID;
		this.loyaltyPoint = loyaltyPoint;
	}
	
	public String getName(){
		return name;
	}

	public String getMemberID() {
		return memberID;
	}

	public void setMemberID(String memberID) {
		this.memberID = memberID;
	}

	public int getLoyaltyPoint() {
		return loyaltyPoint;
	}

	public void setLoyaltyPoint(int loyaltyPoint) {
		this.loyaltyPoint = loyaltyPoint;
	}

	public String toString() {
		return name + "," + memberID + "," + loyaltyPoint;
	}

	@Override
	public String getID() {
		return memberID;
	}
}
