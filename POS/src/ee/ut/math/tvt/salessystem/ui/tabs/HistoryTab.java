package ee.ut.math.tvt.salessystem.ui.tabs;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.JTableHeader;

import org.apache.log4j.Logger;

import ee.ut.math.tvt.salessystem.domain.controller.impl.SalesDomainControllerImpl;
import ee.ut.math.tvt.salessystem.domain.data.HistoryItem;
import ee.ut.math.tvt.salessystem.ui.model.PurchaseHistoryTableModel;
import ee.ut.math.tvt.salessystem.ui.model.SalesSystemModel;
 
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
        		int rowNr = table.getSelectedRow();
        		mouseClickedActionHandler(rowNr);
        	}
		});
        
        return panel;
      }
    
    protected void mouseClickedActionHandler(int rowNr){
		List<HistoryItem> historyList = SalesDomainControllerImpl.historydataset;
		HistoryItem b = historyList.get(rowNr);
		PurchaseHistoryTableModel history = new PurchaseHistoryTableModel();
		history.populateWithData(b.getSoldItems());
		
		log.info("History tab row " + rowNr + " opened");
		
		JFrame frame = new JFrame("Selected Purchase");
		JTable table = new JTable (history);
		frame.getContentPane().add(table, BorderLayout.CENTER);
		frame.pack();
		frame.setVisible(true);
    }
    
}

