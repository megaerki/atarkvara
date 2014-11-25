package ee.ut.math.tvt.salessystem.domain.data;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class StockItemTest {
	
	private StockItem stockitem;
	
	@Before
	public void setUp() {
		stockitem=new StockItem((long) 0, "Laua Viin", "viin", 5, 1);
	}
	
	@Test
    public void testClone() {
		StockItem cloneItem = (StockItem) stockitem.clone();
		assertNotSame("Variables refer to the same object", cloneItem, stockitem);
        assertEquals("ID not equal", stockitem.getId(), cloneItem.getId());
        assertEquals("Name not equal",stockitem.getName(), cloneItem.getName());
        assertEquals("Description not equal",stockitem.getDescription(), cloneItem.getDescription());
        assertEquals("Quantity not equal",stockitem.getQuantity(), cloneItem.getQuantity());	
    	
    }
	@Test
    public void testGetColumn(){
		assertEquals(stockitem.getColumn(1),"Laua Viin");
		assertEquals(stockitem.getColumn(2),5.0);
    	assertEquals(stockitem.getColumn(3),1);	
    }
}
