package sg.edu.nus.iss.usstore.test;

import static org.junit.Assert.*;

import org.junit.Test;

import sg.edu.nus.iss.usstore.domain.Member;

/**
 * @author Achyut Suresh Rao
 */

public class MemberTest {
	
	Member mem1 = new Member("sam", "a0123654", 10);
	Member mem2 = new Member("john", "q321456", -1);

	@Test
	public void testMember() {
		assertFalse(mem1 == mem2);
		assertFalse(mem1.equals(mem2));
	}

	@Test
	public void testGetName() {
		assertEquals("john", mem2.getName());
		assertFalse(mem1.getName() == mem2.getName());
	}

	@Test
	public void testGetMemberID() {
		assertEquals("a0123654", mem1.getMemberID());
	}

	@Test
	public void testSetMemberID() {
		mem1.setMemberID("z0123654");
		assertEquals("z0123654", mem1.getMemberID());
	}

	@Test
	public void testGetLoyaltyPoint() {
		assertEquals(-1,mem2.getLoyaltyPoint());
	}

	@Test
	public void testSetLoyaltyPoint() {
		mem2.setLoyaltyPoint(100);
		assertEquals(100,mem2.getLoyaltyPoint());
	}

	@Test
	public void testToString() {
		String member = "sam,a0123654,10";
		assertEquals(member, mem1.toString());
	}

}
