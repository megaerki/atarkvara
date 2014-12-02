package ee.ut.math.tvt.salessystem.ui.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import org.apache.log4j.Logger;

import ee.ut.math.tvt.salessystem.domain.data.SoldItem;

public class PurchaseInfoTableModel extends SalesSystemTableModel<SoldItem> {
	private static final long serialVersionUID = 1L;

	private static final Logger log = Logger.getLogger(PurchaseInfoTableModel.class);
	
	public PurchaseInfoTableModel() {
		super(new String[] { "Id", "Name", "Price", "Quantity","Sum"});
	}

	protected Object getColumnValue(SoldItem item, int columnIndex) {
		switch (columnIndex) {
		case 0:
			return item.getId();
		case 1:
			return item.getName();
		case 2:
			return item.getPrice();
		case 3:
			return item.getQuantity();
		case 4:
			return item.getSum();
		}
		throw new IllegalArgumentException("Column index out of range");
	}

	public String toString() {
		final StringBuffer buffer = new StringBuffer();

		for (int i = 0; i < headers.length; i++)
			buffer.append(headers[i] + "\t");
		buffer.append("\n");

		for (final SoldItem item : rows) {
			buffer.append(item.getId() + "\t");
			buffer.append(item.getName() + "\t");
			buffer.append(item.getPrice() + "\t");
			buffer.append(item.getQuantity() + "\t");
			buffer.append(item.getSum() + "\t");
			buffer.append("\n");
		}
		return buffer.toString();
	}
	
	public double getSum(){
		double result = 0.0;
		for (final SoldItem item : rows){
			result += item.getSum();
		}
		return result;
	}
		
    public void addItem(final SoldItem item) {
        /**
         * XXX In case such stockItem already exists increase the quantity of the
         * existing stock.
         */
    	
        rows.add(item);
        log.debug("Added " + item.getName() + " quantity of " + item.getQuantity());
        fireTableDataChanged();
		try{
		    Class.forName("org.hsqldb.jdbc.JDBCDriver");
		    Connection con = DriverManager.getConnection("jdbc:hsqldb:hsql://localhost/POS","SA","");
		    Statement stmt = (Statement) con.createStatement();
		    String insert = "INSERT INTO SOLDITEM(sale_id,name,quantity,itemprice)      VALUES ('"+item.getId()+"','"+item.getName()+"','"+item.getQuantity()+"','"+item.getPrice()+"');";
		    stmt.executeUpdate(insert);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
    }

}
