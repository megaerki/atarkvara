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
    public void testClone() throws CloneNotSupportedException{//failib
		clone();
		
		
		
	
    	
    }
	@Test
    public void testGetColumn(){
		assertEquals(stockitem.getColumn(1),"Laua Viin");
    	
    }

}
