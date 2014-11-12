package ee.ut.math.tvt.salessystem.ui.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import ee.ut.math.tvt.salessystem.domain.controller.SalesDomainController;
import ee.ut.math.tvt.salessystem.domain.controller.impl.SalesDomainControllerImpl;
import ee.ut.math.tvt.salessystem.domain.data.HistoryItem;
import ee.ut.math.tvt.salessystem.domain.data.SoldItem;

import java.util.List;

/**
 * History table model.
 */

public class HistoryTableModel extends SalesSystemTableModel<HistoryItem> {
	private static final long serialVersionUID = 1L;
	private HistoryItem sold;
	private SalesDomainController domainController;

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
	
	 public void addItem(final HistoryItem historyIt) throws SQLException, ClassNotFoundException {
		 
		 rows.add(historyIt);
		 fireTableDataChanged();
	
//		    Class.forName("org.hsqldb.jdbc.JDBCDriver");
//		    Connection con = DriverManager.getConnection("jdbc:hsqldb:hsql://localhost/POS","SA","");
//		    Statement stmt = (Statement) con.createStatement();
//		    String insert = "INSERT INTO SOLDITEM(sale_id,name)      VALUES ('100','name');";
//		    stmt.executeUpdate(insert);
		 
		
	 }
 
}


