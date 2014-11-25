package ee.ut.math.tvt.salessystem.ui.model;

import java.sql.SQLException;

import org.apache.log4j.Logger;

import ee.ut.math.tvt.salessystem.domain.data.HistoryItem;

public class HistoryTableModel extends SalesSystemTableModel<HistoryItem> {
	private static final long serialVersionUID = 1L;

	@SuppressWarnings("unused")
	private static final Logger log = Logger.getLogger(PurchaseInfoTableModel.class);
	
	public HistoryTableModel() {
		super(new String[] {"Id","Date", "Time", "Sum"});
	}

	protected Object getColumnValue(HistoryItem item, int columnIndex) {
		switch (columnIndex) {
		case 0:
			return item.getId();
		case 1:
			return item.getDate();
		case 2:
			return item.getTime();
		case 3:
			return item.getSum();
		}
		throw new IllegalArgumentException("Column index error");
	}
	
	 public void addItem(final HistoryItem historyIt) throws SQLException, ClassNotFoundException {
		 rows.add(historyIt);
		 fireTableDataChanged();		
	 }
 
}


