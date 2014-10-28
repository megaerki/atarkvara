package ee.ut.math.tvt.salessystem.ui.panels;

import ee.ut.math.tvt.salessystem.domain.data.SoldItem;
import ee.ut.math.tvt.salessystem.domain.data.StockItem;
import ee.ut.math.tvt.salessystem.domain.exception.SalesSystemException;
import ee.ut.math.tvt.salessystem.ui.model.SalesSystemModel;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.NoSuchElementException;

import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

import org.apache.log4j.Logger;

/**
 * Purchase pane + shopping cart tabel UI.
 */
public class PurchaseItemPanel extends JPanel {
	
	public static  boolean addCart=false;

    private static final long serialVersionUID = 1L;
    
    private static final Logger logger = Logger.getLogger(PurchaseItemPanel.class);
    // Text field on the dialogPane
   
    private JComboBox<StockItem> itemSelector;
    private JTextField quantityField;
    //private JTextField nameField;
    private JTextField priceField;
    private JTextField barCodeField;
    private JButton addItemButton;
    ///static List<StockItem> dataset = new ArrayList<StockItem>();
    
    // ??? String[] midagi;
    
    // Warehouse model
    private SalesSystemModel model;
    public static double sum;

    /**
     * Constructs new purchase item panel.
     * 
     * @param model
     *            composite model of the warehouse and the shopping cart.
     */
    public PurchaseItemPanel(SalesSystemModel model) {
        this.model = model;

        setLayout(new GridBagLayout());

        add(drawDialogPane(), getDialogPaneConstraints());
        add(drawBasketPane(), getBasketPaneConstraints());

        setEnabled(false);
    }

    public PurchaseItemPanel() {
		
	}

	// shopping cart pane
    private JComponent drawBasketPane() {

        // Create the basketPane
        JPanel basketPane = new JPanel();
        basketPane.setLayout(new GridBagLayout());
        basketPane.setBorder(BorderFactory.createTitledBorder("Shopping cart"));

        // Create the table, put it inside a scollPane,
        // and add the scrollPane to the basketPanel.
        JTable table = new JTable(model.getCurrentPurchaseTableModel());
        JScrollPane scrollPane = new JScrollPane(table);

        basketPane.add(scrollPane, getBacketScrollPaneConstraints());

        return basketPane;
    }

    // purchase dialog
    private JComponent drawDialogPane() {

        // Create the panel
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(5, 2));
        panel.setBorder(BorderFactory.createTitledBorder("Product"));

        // Initialize the textfields
        
        itemSelector = new JComboBox<StockItem>();
        //SalesDomainControllerImpl salesDomain= new SalesDomainControllerImpl();    
        //dataset=salesDomain.getList();
        barCodeField = new JTextField();
        quantityField = new JTextField("1");
        //nameField = new JTextField();
        priceField = new JTextField();

        //Changes the price and qty fields if a new item is chosen
        itemSelector.addItemListener(new ItemListener(){
        	public void itemStateChanged(ItemEvent e){
        		if (e.getStateChange() == ItemEvent.SELECTED){
        			fillDialogFields();
        		}
        		//logger.debug("Dialog fields were populated.");
        	}
        });
        
        // Fill the dialog fields if the bar code text field loses focus
        /*barCodeField.addFocusListener(new FocusListener() {
            public void focusGained(FocusEvent e) {
            }

            public void focusLost(FocusEvent e) {
                fillDialogFields();
            }
        });
        barCodeField.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e) {

                
            }
            
        });
        */
        

        //nameField.setEditable(false);
        priceField.setEditable(false);
        barCodeField.setEditable(false);
        
        // == Add components to the panel

        // - item
        panel.add(new JLabel("Item:"));
        panel.add(itemSelector);
        
        // - bar code
        panel.add(new JLabel("Bar code:"));
        panel.add(barCodeField);

        // - amount
        panel.add(new JLabel("Amount:"));
        panel.add(quantityField);

        // - name - Not needed with the selector.
        //panel.add(new JLabel("Name:"));
        //panel.add(nameField);

        // - price
        panel.add(new JLabel("Price:"));
        panel.add(priceField);

        // Create and add the button
        addItemButton = new JButton("Add to cart");
        addItemButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {     		
                addItemEventHandler();
                
            }
        });

        panel.add(addItemButton);

        return panel;
    }
    
    public static boolean getBoolean(){
    	return addCart;
    }
    private void setBooleanTrue(){
    	addCart=true;
    }
    private void setBooleanFalse(){
    	addCart=false;
    }
    public static double getSum(){
	 return sum;
    }
    public static void setSum(double price,int quantity){
    	sum=sum+(price*quantity);
    }
    // Fill dialog with data from the "database".
    private void fillDialogFields() {
        //StockItem stockItem = getStockItemByBarcode();
    	StockItem stockItem = (StockItem)
    	itemSelector.getSelectedItem();
    	
    	if (stockItem != null) {
            //nameField.setText(stockItem.getName());

            String priceString = String.valueOf(stockItem.getPrice());
            priceField.setText(priceString);
            String barCodeString = String.valueOf(stockItem.getId());
            barCodeField.setText(barCodeString);
        } else {
            reset();
        }
    }

    // Search the warehouse for a StockItem with the bar code entered
    // to the barCode textfield.
    public StockItem getStockItemByBarcode() {
        try {
            int code = Integer.parseInt(barCodeField.getText());
            return model.getWarehouseTableModel().getItemById(code);
        } catch (NumberFormatException ex) {
            return null;
        } catch (NoSuchElementException ex) {
            return null;
        }
    }

    /**
     * Add new item to the cart.
     */
    public void addItemEventHandler()  {
    	setBooleanTrue();
    	
        // add chosen item to the shopping cart.
        StockItem stockItem = getStockItemByBarcode();
		
		double price = stockItem.getPrice();
		try{
		int quantity1=Integer.parseInt(quantityField.getText());
		setSum(price,quantity1);
		}
		catch(NumberFormatException ex){
			
			WrongInput();
		}
		
        if (stockItem != null) {
            int quantity;
          
            try {
                quantity = Integer.parseInt(quantityField.getText());
                if (quantity > stockItem.getQuantity()) {
                	throw new SalesSystemException();
				}
                else{
                	stockItem.setQuantity(stockItem.getQuantity() - quantity);
                	model.getCurrentPurchaseTableModel().addItem(new SoldItem(stockItem, quantity));    				
                }	
            }
            catch (NumberFormatException ex) {
            	quantity = 1;
            }
            
            catch (SalesSystemException e) {
            	notEnoughItemsWarning();
            }
        }        
        
    }
    
    private void notEnoughItemsWarning() {
    	JOptionPane.showMessageDialog(this, "There not enough of this item in stock", "Attention", JOptionPane.WARNING_MESSAGE);
    	logger.debug("User tried to add item, there was not enough of this item");
    }
    
    
    private void WrongInput() {
    	JOptionPane.showMessageDialog(this, "Please enter a positive number for amount.", "Attention", JOptionPane.WARNING_MESSAGE);
    	logger.debug("User did not enter a valid number as a amount");
    }
    
    @Override
    public void setEnabled(boolean enabled) {
    	this.addItemButton.setEnabled(enabled);
        this.barCodeField.setEnabled(enabled);
        this.quantityField.setEnabled(enabled);
        this.itemSelector.setEnabled(enabled);
        
        fillDialogFields();
    }

    public void reset() {
    	setBooleanFalse();
    	sum=0;
    	
    	((DefaultComboBoxModel<StockItem>)itemSelector.getModel()).removeAllElements();
    		for(StockItem stockItem : model.getWarehouseTableModel().getTableRows()) {
    			((DefaultComboBoxModel<StockItem>)itemSelector.getModel()).addElement(stockItem);
    	}
    	barCodeField.setText("");
        quantityField.setText("1");
        priceField.setText("");
    }

    /*
     * === Ideally, UI's layout and behavior should be kept as separated as
     * possible. If you work on the behavior of the application, you don't want
     * the layout details to get on your way all the time, and vice versa. This
     * separation leads to cleaner, more readable and better maintainable code.
     * 
     * In a Swing application, the layout is also defined as Java code and this
     * separation is more difficult to make. One thing that can still be done is
     * moving the layout-defining code out into separate methods, leaving the
     * more important methods unburdened of the messy layout code. This is done
     * in the following methods.
     */

    // Formatting constraints for the dialogPane
    private GridBagConstraints getDialogPaneConstraints() {
        GridBagConstraints gc = new GridBagConstraints();

        gc.anchor = GridBagConstraints.WEST;
        gc.weightx = 0.2;
        gc.weighty = 0d;
        gc.gridwidth = GridBagConstraints.REMAINDER;
        gc.fill = GridBagConstraints.NONE;

        return gc;
    }

    // Formatting constraints for the basketPane
    private GridBagConstraints getBasketPaneConstraints() {
        GridBagConstraints gc = new GridBagConstraints();

        gc.anchor = GridBagConstraints.WEST;
        gc.weightx = 0.2;
        gc.weighty = 1.0;
        gc.gridwidth = GridBagConstraints.REMAINDER;
        gc.fill = GridBagConstraints.BOTH;

        return gc;
    }

    private GridBagConstraints getBacketScrollPaneConstraints() {
        GridBagConstraints gc = new GridBagConstraints();

        gc.fill = GridBagConstraints.BOTH;
        gc.weightx = 1.0;
        gc.weighty = 1.0;

        return gc;
    }

}
