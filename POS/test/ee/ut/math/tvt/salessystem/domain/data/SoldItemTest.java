package ee.ut.math.tvt.salessystem.domain.data;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;



public class SoldItemTest {
	
	
	private StockItem stockitem;
	
	  @Before
	  public void setUp() {
		  stockitem=new StockItem((long) 0, "Laua Viin", "viin", 5, 1);
		
	    
	     
	  }
	
	
	
	@Test
    public void testGetSum(){
		SoldItem s= new SoldItem(stockitem,1);
		
		assertEquals(s.getSum(),5.0,0.0001);
		
    	
    }
	//@Test
    public void testGetSumWithZeroQuantity(){
		SoldItem s= new SoldItem(stockitem,0);
		
		assertEquals(s.getSum(),0.0,0.0001);
    	
    }

}
