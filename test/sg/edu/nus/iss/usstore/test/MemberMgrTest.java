package sg.edu.nus.iss.usstore.test;

import static org.junit.Assert.*;

import java.io.IOException;
import java.util.ArrayList;

import org.junit.Test;

import sg.edu.nus.iss.usstore.domain.Member;
import sg.edu.nus.iss.usstore.domain.MemberMgr;
import sg.edu.nus.iss.usstore.exception.DataFileException;

/**
 * @author Achyut Suresh Rao
 */

public class MemberMgrTest {

	MemberMgr memMgr;

	public MemberMgrTest() throws IOException, DataFileException {
		memMgr = new MemberMgr();
	}

	@Test
	public void testMemberMgr() throws IOException, DataFileException {
		MemberMgr memMgr1 = new MemberMgr();
		assertFalse(memMgr1.toString() == null);
		assertTrue(memMgr.toString() != null);
	}

	@Test
	public void testRegisterMember() {
		memMgr = new MemberMgr();
		ArrayList<Member> member = new ArrayList<>();
		member.add(new Member("sam", "a0123654", 100));
		memMgr.setMemberList(member);
		assertFalse(member.isEmpty());

	}

	@Test
	public void testGetMemberList() {
		memMgr= new MemberMgr();
		ArrayList<Member> member1 = new ArrayList<>();
		Member mem = new Member("Suraj", "X437F35", 250);
		member1.add(mem);
		memMgr.setMemberList(member1);
		assertEquals("Suraj,X437F35,250", memMgr.getMemberList().get(0).toString());
	}

	@Test
	public void testGetMemberByID() {
		memMgr = new MemberMgr();
		ArrayList<Member> member = new ArrayList<>();
		member.add(new Member("Suraj Sharma", "X437F356", 250));
		memMgr.setMemberList(member);
		assertEquals("Suraj Sharma,X437F356,250",memMgr.getMemberByID("X437F356").toString());
		
	}
	
	@Test
	public void testModifyMember(){
		memMgr = new MemberMgr();
		ArrayList<Member> member = new ArrayList<>();
		member.add(new Member("Suraj Sharma", "X437F356", 250));
		memMgr.setMemberList(member);
		assertEquals("Suraj Sharma,X437F356,250",memMgr.getMemberByID("X437F356").toString());
		memMgr.modifyMember("Suraj Sharma", "X437F356",300, 0);
		assertNotEquals("Suraj Sharma,X437F356,250",memMgr.getMemberByID("X437F356").toString());
	}

	
	  //@Test public void testWriteFile() {fail("Not yet implemented");}
	  
	 // @Test public void testReadFile() { fail("Not yet implemented"); }
	 

}
