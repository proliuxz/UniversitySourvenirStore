package sg.edu.nus.iss.usstore.test;

import static org.junit.Assert.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

import org.junit.Test;

import sg.edu.nus.iss.usstore.domain.Customer;
import sg.edu.nus.iss.usstore.domain.Discount;
import sg.edu.nus.iss.usstore.domain.DiscountMgr;
import sg.edu.nus.iss.usstore.domain.Member;
import sg.edu.nus.iss.usstore.domain.MemberDiscount;
import sg.edu.nus.iss.usstore.domain.OcassionalDiscount;
import sg.edu.nus.iss.usstore.exception.DataFileException;

public class DiscountMgrTest {

	ArrayList<Discount> discountList;
	MemberDiscount memDisc;
	OcassionalDiscount occDisc;
	DiscountMgr discMgr;
	Member mem;
	public DiscountMgrTest() {
		// TODO Auto-generated constructor stub
		discountList = new ArrayList<>();
		memDisc = new MemberDiscount("MEMBER_SECOND", "Second Purchase by Member", 15, "M");
		occDisc = new OcassionalDiscount("SPECIAL_OFFER", "Specail offer only today", new Date(), 1, 25, "A");
		discMgr = new DiscountMgr();
		mem = new Member("john", "q321456", -1);
	}
		
	@Test
	public void testGetDiscountlist() {
		assertEquals(discMgr.getDiscountlist().size(), 0);
		discMgr.registerDiscount("MEMBER_SECOND", "Second Purchase by Member", 15, "M");
		assertEquals(discMgr.getDiscountlist().size(), 1);
	}
	
	@Test
	public void testRegisterDiscount() {
		assertEquals(discMgr.getDiscountlist().size(), 0);
		discMgr.registerDiscount("MEMBER_SECOND", "Second Purchase by Member", 15, "M");
		assertEquals(discMgr.getDiscountlist().size(), 1);
		assertTrue(memDisc.equals(discMgr.getDiscountByCode("MEMBER_SECOND")));
	}
	
	@Test
	public void testAddDiscount() {
		assertEquals(discMgr.getDiscountlist().size(), 0);
		discMgr.addDiscount("SPECIAL_OFFER", "Specail offer only today", new Date(), 1, 25, "A");
		assertEquals(discMgr.getDiscountlist().size(), 1);
		assertTrue(occDisc.equals(discMgr.getDiscountByCode("SPECIAL_OFFER")));
	}
	
	@Test
	public void testGetMaxDiscount() {
		discMgr.registerDiscount("MEMBER_SECOND", "Second Purchase by Member", 15, "M");
		discMgr.addDiscount("SPECIAL_OFFER", "Specail offer only today", new Date(), 1, 25, "A");
		assertTrue(occDisc.equals(discMgr.getMaxDiscount(mem)));
	}

	@Test
	public void testModifyMemberDiscount() {
		assertEquals(discMgr.getDiscountlist().size(), 0);
		discMgr.registerDiscount("MEMBER_SECOND", "Second Purchase by Member", 15, "M");
		assertEquals(discMgr.getDiscountlist().size(), 1);
		MemberDiscount newMemdisc = new MemberDiscount("MEMBER_SECOND", "Second Purchase by Anyone", 10 , "M");
		discMgr.modifyMemberDiscount("MEMBER_SECOND", "Second Purchase by Anyone", 10);
		assertTrue(newMemdisc.equals(discMgr.getDiscountByCode("MEMBER_SECOND")));
	}

	@Test
	public void testModifyOcassionalDiscount() {
		assertEquals(discMgr.getDiscountlist().size(), 0);
		discMgr.addDiscount("SPECIAL_OFFER", "Specail offer only today", new Date(), 1, 25, "A");
		assertEquals(discMgr.getDiscountlist().size(), 1);
		OcassionalDiscount newOccDisc = new OcassionalDiscount("SPECIAL_OFFER", "Specail offer only for you today", new Date(), 2, 15, "A");
		discMgr.modifyOcassionalDiscount("SPECIAL_OFFER", "Specail offer only for you today", new Date(), 2, 15);
		assertTrue(newOccDisc.equals(discMgr.getDiscountByCode("SPECIAL_OFFER")));
	}

	@Test
	public void testDeleteDiscount() {
		assertEquals(discMgr.getDiscountlist().size(), 0);
		discMgr.registerDiscount("MEMBER_SECOND", "Second Purchase by Member", 15, "A");
		discMgr.addDiscount("SPECIAL_OFFER", "Specail offer only today", new Date(), 1, 25, "A");
		assertEquals(discMgr.getDiscountlist().size(), 2);
		discMgr.deleteDiscount("MEMBER_SECOND");
		assertEquals(discMgr.getDiscountlist().size(), 1);
		discMgr.deleteDiscount("SPECIAL_OFFER");
		assertEquals(discMgr.getDiscountlist().size(), 0);
		discMgr.deleteDiscount("NO ENTRY");
		assertEquals(discMgr.getDiscountlist().size(), 0);
	}

	@Test
	public void testGetDiscountByCode() {
		assertEquals(discMgr.getDiscountlist().size(), 0);
		discMgr.registerDiscount("MEMBER_SECOND", "Second Purchase by Member", 15, "M");
		discMgr.addDiscount("SPECIAL_OFFER", "Specail offer only today", new Date(), 1, 25, "A");
		assertEquals(discMgr.getDiscountlist().size(), 2);
		assertTrue(memDisc.equals(discMgr.getDiscountByCode("MEMBER_SECOND")));
		assertTrue(occDisc.equals(discMgr.getDiscountByCode("SPECIAL_OFFER")));
	}
}
