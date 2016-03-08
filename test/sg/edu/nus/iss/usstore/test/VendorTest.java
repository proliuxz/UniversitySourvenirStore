package sg.edu.nus.iss.usstore.test;

import static org.junit.Assert.*;

import org.junit.Test;

import sg.edu.nus.iss.usstore.domain.Vendor;

public class VendorTest
{	
	
	@Test
	public void testCompareTo() {
		Vendor v1 = new Vendor("LG", "Life is Good", 1);
		Vendor v2 = new Vendor("SAMSUNG", "What's next", 2);
		assertEquals(-1, v1.compareTo(v1));
		assertEquals(1, v2.compareTo(v1));
		assertEquals(-1, v1.compareTo(v2));
	}

}
