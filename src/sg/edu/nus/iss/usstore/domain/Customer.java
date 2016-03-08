package sg.edu.nus.iss.usstore.domain;

import java.io.Serializable;

/**
 * @author Achyut Suresh Rao
 */

public abstract class Customer implements Serializable {

	private static final long serialVersionUID = 1L;
	public String name;

	// public List<Discount> getDiscount();

	public Customer() {

	}
	
	public Customer(String name) {
		this.name = name;
	}
	
	public abstract String getID();

}
