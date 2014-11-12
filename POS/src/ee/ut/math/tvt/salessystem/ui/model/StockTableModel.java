package ee.ut.math.tvt.salessystem.ui.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import org.apache.log4j.Logger;
import org.hsqldb.Session;

import ee.ut.math.tvt.salessystem.domain.controller.impl.SalesDomainControllerImpl;
import ee.ut.math.tvt.salessystem.domain.data.StockItem;
import ee.ut.math.tvt.salessystem.util.HibernateUtil;

/**
 * Stock item table model.
 */
public class StockTableModel extends SalesSystemTableModel<StockItem> {
	private static final long serialVersionUID = 1L;

	private static final Logger log = Logger.getLogger(StockTableModel.class);
	private List<StockItem> stockItems;
	public StockTableModel() {
		super(new String[] {"Id", "Name", "Price", "Quantity"});
		stockItems = new ArrayList<StockItem>();
	}

	@Override
	protected Object getColumnValue(StockItem item, int columnIndex) {
		switch (columnIndex) {
		case 0:
			return item.getId();
		case 1:
			return item.getName();
		case 2:
			return item.getPrice();
		case 3:
			return item.getQuantity();

		}
		throw new IllegalArgumentException("Column index out of range");
	}

	/**
	 * Add new stock item to table. If there already is a stock item with
	 * same id, then existing item's quantity will be increased.
	 * @param stockItem
	 */
	public void addItem(final StockItem stockItem) {
		
		//Salvesta uus stockItem andmebaasi
		try {
			Class.forName("org.hsqldb.jdbc.JDBCDriver");
			Connection con = DriverManager.getConnection("jdbc:hsqldb:hsql://localhost/POS","SA","");
			Statement stmt = (Statement) con.createStatement();
			String insert = "SELECT 1 from stockitem where id = "+stockItem.getId();
			ResultSet set = stmt.executeQuery(insert);
			if (set.next()){
				insert = "UPDATE STOCKITEM SET quantity = (quantity +"+stockItem.getQuantity()+") where id = "+stockItem.getId();
			}
			else{
				insert = "INSERT INTO STOCKITEM(id,name,price,quantity,description) VALUES('"+stockItem.getId()+"','"+stockItem.getName()+"','"+stockItem.getPrice()+"','"+stockItem.getQuantity()+"','"+stockItem.getDescription()+"')";
			}
			stmt.executeUpdate(insert);	
				
		} catch (ClassNotFoundException e) {
			log.debug(e);
		} catch (SQLException e) {
			log.debug(e);
		}
	   
		
		try {
			StockItem item = getItemById(stockItem.getId());
			item.setQuantity(item.getQuantity() + stockItem.getQuantity());
			log.debug("Found existing item " + stockItem.getName()
					+ " increased quantity by " + stockItem.getQuantity());
		}
		catch (NoSuchElementException e) {
			rows.add(stockItem);
			log.debug("Added " + stockItem.getName()
					+ " quantity of " + stockItem.getQuantity());
		} 
		fireTableDataChanged();
	}

	 
	
	@Override
	public String toString() {
		final StringBuffer buffer = new StringBuffer();

		for (int i = 0; i < headers.length; i++)
			buffer.append(headers[i] + "\t");
		buffer.append("\n");

		for (final StockItem stockItem : rows) {
			buffer.append(stockItem.getId() + "\t");
			buffer.append(stockItem.getName() + "\t");
			buffer.append(stockItem.getPrice() + "\t");
			buffer.append(stockItem.getQuantity() + "\t");
			buffer.append("\n");
		}

		return buffer.toString();
	}
	public List<StockItem> getRows() {
        return stockItems;
	}
}
