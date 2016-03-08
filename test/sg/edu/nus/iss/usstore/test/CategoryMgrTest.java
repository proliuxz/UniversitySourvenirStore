package sg.edu.nus.iss.usstore.test;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

import sg.edu.nus.iss.usstore.domain.Category;
import sg.edu.nus.iss.usstore.domain.CategoryMgr;
import sg.edu.nus.iss.usstore.domain.Vendor;

public class CategoryMgrTest
{	
	
	private ArrayList<Vendor> vendorList;
	private ArrayList<Vendor> vendorList1;
	private ArrayList<Category> categoryList;
	private Vendor v1;
	private Vendor v2;
	private Vendor v3;
	private Category c1;
	private Category c2;
	private CategoryMgr cmgr;
	
	public CategoryMgrTest() {
		// TODO Auto-generated constructor stub
		v1 = new Vendor("LG", "Life is Good", 1);
		v2 = new Vendor("SAMSUNG", "What's next", 2);
		v3 = new Vendor("APPLE", "Think Different", 2);
		vendorList = new ArrayList<Vendor>();
		vendorList.add(v1);
		vendorList.add(v3);
		vendorList1 = new ArrayList<Vendor>();
		vendorList1.add(v1);
		vendorList1.add(v2);
		c1 = new Category("MOB", "Mobile Phone", vendorList);
		c2 = new Category("TV", "TeleVision", vendorList1);
		
		categoryList = new ArrayList<Category>();
		categoryList.add(c1);
		categoryList.add(c2);
		cmgr=new CategoryMgr();
		cmgr.setCategoryList(categoryList);
	}
	
	@Test
	public void testGetCategoryList() {
		assertEquals(2, cmgr.getCategoryList().size());
		assertEquals(c1, cmgr.getCategoryList().get(0));
		assertEquals(c2, cmgr.getCategoryList().get(1));
	}
	
	@Test
	public void testGetPreferenceVendor() {
		assertEquals(v1, c1.getPreferenceVendor());
	}
	
	@Test
	public void testAddCategory() {
		assertEquals(2, cmgr.getCategoryList().size());
		cmgr.addCategory("STM", "STAMP", null);
		assertEquals(3, cmgr.getCategoryList().size());
	}
	
	@Test
	public void testDelCategoryByCode() {
		assertEquals(2, cmgr.getCategoryList().size());
		cmgr.delCategoryByCode("TV");
		assertEquals(1, cmgr.getCategoryList().size());
	}
	
	@Test
	public void testupdCategory() {
		assertEquals(2, cmgr.getCategoryList().size());
		assertEquals(c1,cmgr.getCategoryList().get(0));
		cmgr.updCategory("MOB", "Mobile");
		assertEquals("MOB",cmgr.getCategoryList().get(0).getCode());
		assertEquals("Mobile",cmgr.getCategoryList().get(0).getName());
	}
	
	@Test
	public void testAddVendorForCategory() {
		assertEquals(2, c1.getVendorList().size());
		cmgr.addVendorForCategory(c1.getCode(), "Nokia", "Connecting People");
		assertEquals(3, c1.getVendorList().size());
		assertEquals("Nokia",c1.getVendorList().get(2).getName());
		assertEquals("Connecting People",c1.getVendorList().get(2).getDescription());
	}
	
	@Test
	public void testDelVendorForCategory() {
		assertEquals(2, c1.getVendorList().size());
		cmgr.delVendorForCategory("MOB", "LG");
		assertEquals(1, c1.getVendorList().size());
	}
	
	@Test
	public void testUpdVendorForCategory() {
		assertEquals(2, c1.getVendorList().size());
		cmgr.updVendorForCategory(c1.getCode(), "LG", "Nokia", "Connecting People");
		assertEquals(2, c1.getVendorList().size());
		assertEquals("Nokia",c1.getVendorList().get(0).getName());
		assertEquals("Connecting People",c1.getVendorList().get(0).getDescription());
	}
	
	@Test
	public void testSwitchVendorPrefForCategory() {
		int v1Pref = v1.getPreference();
		int v3Pref = v3.getPreference();
		assertEquals(v1,c1.getPreferenceVendor());
		cmgr.switchVendorPrefForCategory(c1.getCode(),"LG", "APPLE");
		assertEquals(v3,c1.getPreferenceVendor());
		assertEquals(v1Pref,v3.getPreference());
		assertEquals(v3Pref,v1.getPreference());
	}
}
