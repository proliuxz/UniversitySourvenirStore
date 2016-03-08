package sg.edu.nus.iss.usstore.test;

import static org.junit.Assert.*;

import java.io.IOException;

import org.junit.Test;

import sg.edu.nus.iss.usstore.domain.StoreKeeperMgr;

public class StoreKeeperMgrTest
{	
	
	@Test
	public void testCheckAuthority() {
		StoreKeeperMgr mgr = null;
		try {
			mgr = new  StoreKeeperMgr();
			mgr.loadData();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		assertFalse(mgr.checkAuthority("",""));
		assertFalse(mgr.checkAuthority(" ","  "));
		assertFalse(mgr.checkAuthority(null,null));
		assertFalse(mgr.checkAuthority("admin",null));
		assertFalse(mgr.checkAuthority(null,"admin"));
		assertFalse(mgr.checkAuthority("admin",""));
		assertFalse(mgr.checkAuthority("","admin"));
		assertFalse(mgr.checkAuthority("admin","   "));
		assertFalse(mgr.checkAuthority("   ","admin"));
		assertFalse(mgr.checkAuthority("admin","noentry"));
		assertFalse(mgr.checkAuthority("noentry","admin"));
		assertTrue(mgr.checkAuthority("admin","admin"));
	}

}
