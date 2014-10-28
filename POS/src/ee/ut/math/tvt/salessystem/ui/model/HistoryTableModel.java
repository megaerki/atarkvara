package ee.ut.math.tvt.salessystem.ui.model;

import ee.ut.math.tvt.salessystem.domain.data.HistoryItem;

/**
 * History table model.
 */

public class HistoryTableModel extends SalesSystemTableModel<HistoryItem> {
	private static final long serialVersionUID = 1L;

//	private static final Logger log = Logger.getLogger(PurchaseInfoTableModel.class);
	
	public HistoryTableModel() {
		super(new String[] {"Id","Date", "Time", "Sum"});
	}

	@Override
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
	 public void addItem(final HistoryItem historyIt) {
		 rows.add(historyIt);
		 fireTableDataChanged();
	 }
 
}


