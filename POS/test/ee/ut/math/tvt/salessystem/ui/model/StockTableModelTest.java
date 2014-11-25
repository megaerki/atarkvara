package ee.ut.math.tvt.salessystem.ui.model;

import java.util.NoSuchElementException;

import org.junit.Test;

import ee.ut.math.tvt.salessystem.ui.model.StockTableModel;
import static org.junit.Assert.*;

import org.junit.Before;

import ee.ut.math.tvt.salessystem.domain.data.StockItem;

public class StockTableModelTest {
	
	StockTableModel table = new StockTableModel();
	StockItem stockitem[] = new StockItem[4];
	
	@Before
	public void setUp() {
		stockitem[0]=new StockItem((long) 0, "Laua Viin", "viin", 5, 1);
		stockitem[1]=new StockItem((long) 1, "Saaremaa Viin", "viin", 3, 1);
		stockitem[2]=new StockItem((long) 2, "YX Viin", "viin", 4, 1);
		stockitem[3]=new StockItem((long) 3, "Smirnoff Viin", "viin", 6, 5);
		for (int i = 0; i < stockitem.length; i++){
			table.addItem(stockitem[i]);
		}
	}
	
	@Test
    public void testValidateNameUniqueness(){
		for (int i = 0; i < stockitem.length - 1; i++){
			for (int j = i + 1; j < stockitem.length; j++){
				assertFalse(stockitem[i].getName().equals(stockitem[j].getName()));
			}
		}
	}
	
	@Test
    public void testHasEnoughInStock(){
		assertTrue(stockitem[0].getQuantity()>0);
		assertTrue(stockitem[1].getQuantity()>0);
		assertTrue(stockitem[2].getQuantity()>0);
		assertFalse(stockitem[3].getQuantity()>7);
    }
	
	@Test
    public void testGetItemByIdWhenItemExists(){
		for (int i = 0; i < stockitem.length; i++){
			assertEquals(table.getItemById((long)i), stockitem[i]);
		}
    }
	
	@Test (expected = NoSuchElementException.class) 
	public void testGetItemByIdWhenThrowsException(){
		table.getItemById(9999);
    }
	
}