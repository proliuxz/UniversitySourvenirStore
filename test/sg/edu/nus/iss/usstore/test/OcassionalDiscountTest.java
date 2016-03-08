
package sg.edu.nus.iss.usstore.test;

import static org.junit.Assert.assertFalse;

import java.util.Date;

import org.junit.Test;

import sg.edu.nus.iss.usstore.domain.OcassionalDiscount;
import sg.edu.nus.iss.usstore.util.Util;
/**
 * 
 * @author tanuj
 *
 */
public class OcassionalDiscountTest {

	
	OcassionalDiscount ocaDisc1;
	OcassionalDiscount ocaDisc2;

	@org.junit.Before
	public void setUp() throws Exception {
		Date date = Util.castDate("2014-05-19");
		ocaDisc1 = new OcassionalDiscount("CENTENARY","Centenary Celebration in 2014", date,365,15,"A");
		ocaDisc2 = new OcassionalDiscount("ORIENTATION_DAY","ORIENTATION_DAY",date,1,50,"A");
	}
	
	@Test
	public void testDiscountCode() {
		assertFalse(ocaDisc1 == ocaDisc2);
		assertFalse(ocaDisc1.equals(ocaDisc2));
	}

		
}


