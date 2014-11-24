package ee.ut.math.tvt.salessystem.ui.model;


import java.util.NoSuchElementException;
import org.junit.Test;
import ee.ut.math.tvt.salessystem.ui.model.StockTableModel;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;

import org.junit.Before;

import ee.ut.math.tvt.salessystem.domain.data.StockItem;

public class StockTableModelTest {
	
	StockTableModel table = new StockTableModel();
	StockItem stockitem;
	StockItem stockitem1;
	StockItem stockitem2;
	StockItem stockitem3;
	
	@Before
	  public void setUp() {
	
		stockitem=new StockItem((long) 0, "Laua Viin", "viin", 5, 1);
		stockitem1=new StockItem((long) 1, "Saaremaa Viin", "viin", 3, 1);
		stockitem2=new StockItem((long) 2, "YX Viin", "viin", 4, 1);
		stockitem3=new StockItem((long) 3, "Smirnoff Viin", "viin", 6, 5);
		table.addItem(stockitem);
		table.addItem(stockitem1);
		table.addItem(stockitem2);
		table.addItem(stockitem3);
		
	}
	
	
	@Test
    public void testValidateNameUniqueness(){
		
		// peab koik labi vaatama voi on mingi parem viis?
		//
	}
	@Test
    public void testHasEnoughInStock(){
		assertTrue(stockitem.getQuantity()>0);
		assertTrue(stockitem1.getQuantity()>0);
		assertTrue(stockitem2.getQuantity()>0);
		assertFalse(stockitem3.getQuantity()>7);
		

    	
    }
	@Test
    public void testGetItemByIdWhenItemExists(){
		table.addItem(stockitem);
		assertEquals(table.getItemById(stockitem.getId()), stockitem);
		 
    }
	@Test (expected = NoSuchElementException.class) 
	public void testGetItemByIdWhenThrowsException(){
		table.getItemById(9999);
    }

}