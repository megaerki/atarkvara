package ee.ut.math.tvt.salessystem.ui.model;

import org.junit.Test;
import ee.ut.math.tvt.salessystem.ui.model.StockTableModel;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Before;

import ee.ut.math.tvt.salessystem.domain.data.StockItem;

public class StockTableModelTest {
	
	private StockItem stockitem;
	@Before
	  public void setUp() {
		  stockitem=new StockItem((long) 0, "Laua Viin", "viin", 5, 1);
		
	    
	     
	  }
	
	
	
	@Test
    public void testValidateNameUniqueness(){
    	
    }
	@Test
    public void testHasEnoughInStock(){
		
		assertTrue(stockitem.getQuantity()>0);

    	
    }
	@Test
    public void testGetItemByIdWhenItemExists(){
    	
    }
	@Test
    public void testGetItemByIdWhenThrowsException() {
    	
    }

}
