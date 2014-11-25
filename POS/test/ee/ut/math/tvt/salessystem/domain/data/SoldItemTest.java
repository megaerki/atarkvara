package ee.ut.math.tvt.salessystem.domain.data;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

public class SoldItemTest {
	
	private StockItem stockitem;

	@Before
	public void setUp() {
		stockitem = new StockItem((long) 0, "Laua Viin", "viin", 5, 10);
	}
	
	@Test
    public void testGetSum(){
		for (int i = 0; i < 10; i += 2){
			SoldItem s = new SoldItem(stockitem, 1 * i);
			assertEquals("", s.getSum(), 5.0 * i, 0.0001);
		}
    }
	
	@Test
    public void testGetSumWithZeroQuantity(){
		SoldItem s = new SoldItem(stockitem, 0);
		assertEquals("Sum should be zero", s.getSum(), 0.0, 0.0001);
    }
}
