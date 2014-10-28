package ee.ut.math.tvt.salessystem.ui.model;

import java.awt.Color;
import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.util.List;
import java.util.NoSuchElementException;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.JTableHeader;

import org.apache.log4j.Logger;

import ee.ut.math.tvt.salessystem.domain.data.HistoryItem;
import ee.ut.math.tvt.salessystem.domain.data.SoldItem;
import ee.ut.math.tvt.salessystem.domain.data.StockItem;
import ee.ut.math.tvt.salessystem.ui.tabs.HistoryItemDetailsTab;

/**
 * History table model.
 */

public class HistoryItemDetailsTabModel extends SalesSystemTableModel<HistoryItem> {
	private static final long serialVersionUID = 1L;

	private static final Logger log = Logger.getLogger(PurchaseInfoTableModel.class);
	private SalesSystemModel model;

	public HistoryItemDetailsTabModel() {
		super(new String[] {"Name","Price","Quantity","Sum"});
	}
	
	
	@Override
	protected Object getColumnValue(HistoryItem item, int columnIndex) {

		List<SoldItem> soldItems =item.getSoldItems();
		System.out.println(item.getNumberOfElements());
			if(item.getNumberOfElements()==1){
				System.out.println(item.getNumberOfElements());
				StockItem STockI =item.getstockI();
				switch (columnIndex) {
				case 0:
					return STockI.getName();
				case 1:
					return STockI.getPrice();
				case 2:
					return item.getSoldItems().get(1).getQuantity();
				case 3:
					return item.getSoldItems().get(1).getSum();
				}
				throw new IllegalArgumentException("Column index out of range");
				}
			else{
				
				return soldItems;
			}
		}
		//}
 
	public void addItem(final HistoryItem historyI) {
		rows.add(historyI);
		fireTableDataChanged() ;
	
	
}
		
}
	
	
