package ee.ut.math.tvt.salessystem.domain.data;

import static org.junit.Assert.assertEquals;

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
        assertEquals(stockitem.getId(), cloneItem.getId());
        assertEquals(stockitem.getName(), cloneItem.getName());
        assertEquals(stockitem.getDescription(), cloneItem.getDescription());
        assertEquals(stockitem.getQuantity(), cloneItem.getQuantity());	
    	
    }
	@Test
    public void testGetColumn(){
		assertEquals(stockitem.getColumn(1),"Laua Viin"); //nimi
		assertEquals(stockitem.getColumn(2),5.0);		  //hind
    	assertEquals(stockitem.getColumn(3),1);			  //kogus
    }

}
