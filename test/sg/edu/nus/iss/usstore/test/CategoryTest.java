package sg.edu.nus.iss.usstore.test;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

import sg.edu.nus.iss.usstore.domain.Category;
import sg.edu.nus.iss.usstore.domain.Vendor;

public class CategoryTest
{	
	
	private ArrayList<Vendor> vendorList;
	private Vendor v1;
	private Vendor v2;
	private Category c1;
	
	public CategoryTest() {
		// TODO Auto-generated constructor stub
		v1 = new Vendor("LG", "Life is Good", 1);
		v2 = new Vendor("SAMSUNG", "What's next", 2);
		vendorList = new ArrayList<Vendor>();
		vendorList.add(v1);
		vendorList.add(v2);
		c1 = new Category("MOB", "Mobile Phone", vendorList);
	}
	
	@Test
	public void testGetVendorList() {
		assertEquals(2, c1.getVendorList().size());
		assertEquals(v1, c1.getVendorList().get(0));
		assertEquals(v2, c1.getVendorList().get(1));
		assertEquals(v1, c1.getPreferenceVendor());
	}
	
	@Test
	public void testGetPreferenceVendor() {
		assertEquals(v1, c1.getPreferenceVendor());
	}
	
	@Test
	public void testNewVendor() {
		assertEquals(2, c1.getVendorList().size());
		c1.newVendor("APPLE", "Think Different");
		assertEquals(3, c1.getVendorList().size());
	}
	
	@Test
	public void testDelVendor() {
		assertEquals(2, c1.getVendorList().size());
		c1.delVendor("SAMSUNG");
		assertEquals(1, c1.getVendorList().size());
		c1.delVendor("LG");
		assertEquals(0, c1.getVendorList().size());
		try
		{
			c1.delVendor("LG");
			assertEquals(0, c1.getVendorList().size());
		}
		catch (Exception e)
		{
			fail();
		}
		
	}
	
	@Test
	public void testUpdVendor() {
		assertEquals(2,c1.getVendorList().size());
		assertEquals(v2,c1.getVendorList().get(1));
		c1.updVendor("SAMSUNG", "APPLE", "Think Different");
		assertEquals("APPLE",c1.getVendorList().get(1).getName());
		assertEquals("Think Different",c1.getVendorList().get(1).getDescription());
	}
	
	@Test
	public void testSwitchVendorPref() {
		int v1Pref = v1.getPreference();
		int v2Pref = v2.getPreference();
		assertEquals(v1,c1.getPreferenceVendor());
		c1.switchVendorPref("LG", "SAMSUNG");
		assertEquals(v2,c1.getPreferenceVendor());
		assertEquals(v1Pref,v2.getPreference());
		assertEquals(v2Pref,v1.getPreference());
	}

}
