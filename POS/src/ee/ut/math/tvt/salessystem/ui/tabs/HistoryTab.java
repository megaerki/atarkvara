package ee.ut.math.tvt.salessystem.ui.tabs;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableModel;

import org.apache.log4j.Logger;

import ee.ut.math.tvt.salessystem.domain.controller.impl.SalesDomainControllerImpl;
import ee.ut.math.tvt.salessystem.domain.data.HistoryItem;
import ee.ut.math.tvt.salessystem.ui.model.PurchaseHistoryTableModel;
import ee.ut.math.tvt.salessystem.ui.model.SalesSystemModel;
import ee.ut.math.tvt.salessystem.util.DatabaseUtil;
 
/**
 * Encapsulates everything that has to do with the purchase tab (the tab
 * labelled "History" in the menu).
 */
public class HistoryTab {
	
	private static final Logger log = Logger.getLogger(HistoryTab.class);
    
	 private SalesSystemModel model;

	  public HistoryTab(SalesSystemModel model) {
	    this.model = model;
	  }

    public HistoryTab() {} 
    
    public Component draw() {
        JPanel panel = new JPanel();
        panel.setBorder(BorderFactory.createLineBorder(Color.BLACK));

        GridBagLayout gb = new GridBagLayout();
        GridBagConstraints gc = new GridBagConstraints();
        panel.setLayout(gb);

        gc.fill = GridBagConstraints.HORIZONTAL;
        gc.anchor = GridBagConstraints.NORTH;
        gc.gridwidth = GridBagConstraints.REMAINDER;
        gc.weightx = 1.0d;
        gc.weighty = 0d;


        gc.weighty = 1.0;
        gc.fill = GridBagConstraints.BOTH;
        panel.add(drawHistoryMainPane(), gc);
        return panel;
    }
    private Component drawHistoryMainPane() {
        JPanel panel = new JPanel();
        final JTable table = new JTable(model.getHistoryTableModel());
        
        
        JTableHeader header = table.getTableHeader();
        header.setReorderingAllowed(false);

        JScrollPane scrollPane = new JScrollPane(table);

        GridBagConstraints gc = new GridBagConstraints();
        GridBagLayout gb = new GridBagLayout();
        gc.fill = GridBagConstraints.BOTH;
        gc.weightx = 1.0;
        gc.weighty = 1.0;

        panel.setLayout(gb);
        panel.add(scrollPane, gc);

        panel.setBorder(BorderFactory.createTitledBorder("History of sales"));
        
        table.addMouseListener(new MouseAdapter() {
        	@Override
			public void mouseClicked(MouseEvent event){
        		Long id = Long.parseLong(table.getValueAt(table.getSelectedRow(), 0).toString());
        		mouseClickedActionHandler(id);
        	}
		});
        
        return panel;
      }
    
    protected void mouseClickedActionHandler(long id){
		
    	ResultSet set;
    	TableModel tablemodel = null;
	    try {
			Class.forName("org.hsqldb.jdbc.JDBCDriver");
			Connection con = DriverManager.getConnection("jdbc:hsqldb:hsql://localhost/POS","SA","");
		    Statement stmt = (Statement) con.createStatement();
		    String insert = "SELECT STOCKITEM_ID, NAME, QUANTITY, ITEMPRICE, (QUANTITY * ITEMPRICE) AS TOTAL FROM SOLDITEM WHERE SALE_ID = "+id;
		    stmt.execute(insert);
		    set = stmt.getResultSet();
		    stmt.close();
		    
		    tablemodel = DatabaseUtil.resultSetToTableModel(set);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			log.debug("historyTab: " + e);
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			log.debug("historyTab: " + e);
		}
	    
		log.info("History tab row " + id + " opened");
		
		JFrame frame = new JFrame("Selected Purchase");
		JTable table = new JTable (tablemodel);
		frame.getContentPane().add(table, BorderLayout.CENTER);
		frame.pack();
		frame.setVisible(true);
    }
}

