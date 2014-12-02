package ee.ut.math.tvt.salessystem.ui.panels;

import org.apache.log4j.Logger;
import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import ee.ut.math.tvt.salessystem.domain.data.SoldItem;
import ee.ut.math.tvt.salessystem.domain.data.StockItem;
import ee.ut.math.tvt.salessystem.ui.model.PurchaseInfoTableModel;

public class PurchaseItemPanelTest {
	
	private static final Logger log = Logger.getLogger(PurchaseItemPanelTest.class);
	
	SoldItem[] soldItems = new SoldItem[3];
	
	@Before
	public void initialize(){
		soldItems[0] = new SoldItem(new StockItem((long) 0, "Laua Viin", "viin", 5, 3)		, 1);
		soldItems[1] = new SoldItem(new StockItem((long) 1, "Saaremaa Viin", "viin", 3, 1)	, 2);
		soldItems[2] = new SoldItem(new StockItem((long) 2, "YX Viin", "viin", 4, 1)		, 3);
	}
	
	@Test
    public void testAddSoldItem(){
		PurchaseInfoTableModel table = new PurchaseInfoTableModel();
		table.addItem(soldItems[0]);
    	assertEquals(table.getRowCount(), 1);
    }
	@Test
    public void testGetSumWithNoItems(){
		PurchaseInfoTableModel table = new PurchaseInfoTableModel();
    	assertEquals(table.getSum(), 0.0, 0.0001);
    }
	@Test
    public void testGetSumWithOneItem(){
		PurchaseInfoTableModel table = new PurchaseInfoTableModel();
		table.addItem(soldItems[0]);
    	assertEquals(table.getSum(), 5.0, 0.0001);
    }
	@Test
    public void testGetSumWithMultipleItems() {
		PurchaseInfoTableModel table = new PurchaseInfoTableModel();
		table.addItem(soldItems[0]);
		table.addItem(soldItems[1]);
		table.addItem(soldItems[2]);
    	assertEquals(table.getSum(), 23.0, 0.0001);
    }
	

}
