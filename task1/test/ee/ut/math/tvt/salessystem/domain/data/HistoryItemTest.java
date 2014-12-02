package ee.ut.math.tvt.salessystem.domain.data;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import ee.ut.math.tvt.salessystem.ui.model.PurchaseInfoTableModel;

public class HistoryItemTest {
	
	SoldItem[] soldItems = new SoldItem[3];
	private List<SoldItem> sold;
	
	@Before
	public void init() {
		soldItems[0] = new SoldItem(new StockItem((long) 0, "Laua Viin", "viin", 5, 3)		, 1);
		soldItems[1] = new SoldItem(new StockItem((long) 1, "Saaremaa Viin", "viin", 3, 1)	, 2);
		soldItems[2] = new SoldItem(new StockItem((long) 2, "YX Viin", "viin", 4, 1)		, 3);
	}
	
	@Test
    public void testTotalSumWithNoItems(){
		HistoryItem hi = new HistoryItem();
		assertEquals(hi.getSum(), 0.0, 0.0001);
    }
	
	@Test
    public void testTotalSumWithOneItem(){
		PurchaseInfoTableModel table = new PurchaseInfoTableModel();
		table.addItem(soldItems[0]);
		HistoryItem hi = new HistoryItem(table.getTableRows(), "12.12.2014", "00:00:00", (long)0);
		assertEquals(hi.getSum(), 5.0, 0.0001);
    }
	
	@Test
    public void testTotalSumWithMultipleItems(){
		PurchaseInfoTableModel table = new PurchaseInfoTableModel();
		table.addItem(soldItems[0]);
		table.addItem(soldItems[1]);
		table.addItem(soldItems[2]);
		HistoryItem hi = new HistoryItem(table.getTableRows(), "12.12.2014", "00:00:00", (long)0);
		assertEquals(hi.getSum(), 23.0, 0.0001);
    }

}
