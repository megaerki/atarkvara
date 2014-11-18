package ee.ut.math.tvt.salessystem.ui.tabs;

import ee.ut.math.tvt.salessystem.domain.data.SoldItem;
import ee.ut.math.tvt.salessystem.domain.data.StockItem;
import ee.ut.math.tvt.salessystem.domain.exception.VerificationFailedException;
import ee.ut.math.tvt.salessystem.domain.controller.SalesDomainController;
import ee.ut.math.tvt.salessystem.ui.model.HistoryTableModel;
import ee.ut.math.tvt.salessystem.ui.model.PurchaseInfoTableModel;
import ee.ut.math.tvt.salessystem.ui.model.SalesSystemModel;
import ee.ut.math.tvt.salessystem.ui.panels.PurchaseItemPanel;

import java.awt.Color;
import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.WindowConstants;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import org.apache.log4j.Logger;

/**
 * Encapsulates everything that has to do with the purchase tab (the tab
 * labelled "Point-of-sale" in the menu).
 */
public class PurchaseTab {

  private static final Logger log = Logger.getLogger(PurchaseTab.class);

  private final SalesDomainController domainController;

  private JButton newPurchase;

  private JButton submitPurchase;

  private JButton cancelPurchase;

  private PurchaseItemPanel purchasePane;
  
  private StockItem stock;
 
  
  private PurchaseInfoTableModel pur;
  private SoldItem sold;

  private SalesSystemModel model;
  private SalesSystemModel model2;
  public   boolean addCart;
  private  static  double changeMoney;
  private static String chanMoney;
  List DB = new ArrayList();
  
  
  
  public PurchaseTab(SalesDomainController controller,
      SalesSystemModel model)
  {
    this.domainController = controller;
    this.model = model;
  }


  /**
   * The purchase tab. Consists of the purchase menu, current purchase dialog and
   * shopping cart table.
   */
  public Component draw() {
    JPanel panel = new JPanel();

    // Layout
    panel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
    panel.setLayout(new GridBagLayout());

    // Add the purchase menu
    panel.add(getPurchaseMenuPane(), getConstraintsForPurchaseMenu());

    // Add the main purchase-panel
    purchasePane = new PurchaseItemPanel(model);
    panel.add(purchasePane, getConstraintsForPurchasePanel());

    return panel;
  }




  // The purchase menu. Contains buttons "New purchase", "Submit", "Cancel".
  private Component getPurchaseMenuPane() {
    JPanel panel = new JPanel();

    // Initialize layout
    panel.setLayout(new GridBagLayout());
    GridBagConstraints gc = getConstraintsForMenuButtons();

    // Initialize the buttons
    newPurchase = createNewPurchaseButton();
    submitPurchase = createConfirmButton();
    cancelPurchase = createCancelButton();

    // Add the buttons to the panel, using GridBagConstraints we defined above
    panel.add(newPurchase, gc);
    panel.add(submitPurchase, gc);
    panel.add(cancelPurchase, gc);

    return panel;
  }


  // Creates the button "New purchase"
  private JButton createNewPurchaseButton() {
    JButton b = new JButton("New purchase");
    b.addActionListener(new ActionListener() {
      @Override
	public void actionPerformed(ActionEvent e) {
        newPurchaseButtonClicked();
      }
    });

    return b;
  }

private void getBoolean(){
	
	addCart=PurchaseItemPanel.getBoolean();
}

  
  // Creates the "Confirm" button
  private JButton createConfirmButton() {
    JButton b = new JButton("Confirm");
    b.addActionListener(new ActionListener() {
      @Override
	public void actionPerformed(ActionEvent e) {
        //submitPurchaseButtonClicked();
        getBoolean();
        if (addCart){ confirmOrder();}
        
      }
    });
    b.setEnabled(false);

    return b;
  }


  // Creates the "Cancel" button
  private JButton createCancelButton() {
    JButton b = new JButton("Cancel");
    b.addActionListener(new ActionListener() {
      @Override
	public void actionPerformed(ActionEvent e) {
        cancelPurchaseButtonClicked();
        
      }
    });
    b.setEnabled(false);

    return b;
  }





  /* === Event handlers for the menu buttons
   *     (get executed when the buttons are clicked)
   */


  /** Event handler for the <code>new purchase</code> event. */
  protected void newPurchaseButtonClicked() {
    log.info("New sale process started");
    try {
      domainController.startNewPurchase();
      startNewSale();
    } catch (VerificationFailedException e1) {
      log.error(e1.getMessage());
    }
  }


  /**  Event handler for the <code>cancel purchase</code> event. */
  protected void cancelPurchaseButtonClicked() {
    log.info("Sale cancelled");
    
    try {
      domainController.cancelCurrentPurchase();
      endSale();
      model.getCurrentPurchaseTableModel().clear();
    } catch (VerificationFailedException e1) {
      log.error(e1.getMessage());
      
    }
  }


  public void confirmOrder(){

	  final JFrame confirm = new JFrame("Confirm sale");
	  confirm.setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
	  //confirm.getContentPane().add(emptyLabel, BorderLayout.CENTER);
	  JPanel panel = new JPanel();
	  panel.setLayout(new GridLayout(4, 3));
	  confirm.add(panel);
	  confirm.setSize(300,150);
	  confirm.setLocation(200, 200);
	  confirm.setVisible(true);

	  JLabel sum=new JLabel("The sum of order:");
	  String sum1=String.valueOf(PurchaseItemPanel.getSum());///pooleli
	  JTextField orderSum=new JTextField(sum1);
	  orderSum.setEditable(false);
	  
	  JLabel pay=new JLabel("Payment amount:");
	  final JTextField pay1=new JTextField();
	  
	  JLabel cha=new JLabel("Change amount:");
	  final JTextField cha1=new JTextField();
	  JButton acc = new JButton("Accept");
	  JButton can= new JButton("Cancel");
	  
	  panel.add(sum);
	  panel.add(orderSum);
	  panel.add(pay);
	  panel.add(pay1);
	  panel.add(cha);
	  panel.add(cha1);
	  panel.add(acc);
	  panel.add(can);
	  
	  
	  can.addActionListener(new ActionListener() {
          @Override
		public void actionPerformed(ActionEvent e) {     		
        	confirm.setVisible(false);
              
              
              
          }
      });
	  acc.addActionListener(new ActionListener() {
          @Override
		public void actionPerformed(ActionEvent e) {     		
        	//saves order on click
        	if (isDouble(cha1.getText())){
        		submitPurchaseButtonClicked();
        		//int q= purchasePane.getQuanty();
        		//stock=purchasePane.getStockItemByBarcode();
        		
        		//try{
        		//pur.addItemDB(new SoldItem(stock, q));
        		//}catch(NullPointerException r){
        			
        		//}
        		
            	confirm.setVisible(false);
        	}
        	else{
        		JOptionPane.showMessageDialog(null, "Payment amount too small to pay the pill!", "Attention", JOptionPane.WARNING_MESSAGE);
        	  	log.debug("Not enough funds to pay the pill");
        	}
        	              
          }
      });
	  

	  
	 pay1.getDocument().addDocumentListener(new DocumentListener() {

		@Override
		public void changedUpdate(DocumentEvent arg0) {
			money();
			
		}

		@Override
		public void insertUpdate(DocumentEvent arg0) {
			money();
			
		}

		@Override
		public void removeUpdate(DocumentEvent arg0) {
			money();
			
		}
		
		    public void money(){
		    	try{
		    	if(pay1.getText().equals("") || pay1.getText().contains("[a-zA-Z,]+") ){
		    		
		    	}
		    	else{
		    	changeMoney=Double.parseDouble(pay1.getText());
		    	}
		    	
		    	
		    	double sum = PurchaseItemPanel.getSum();
		    	
		    	chanMoney=String.valueOf(Math.round((sum-changeMoney)*100.0)/100.0);
		    	String chanMoney2= chanMoney.replaceAll("-","");
		    	
		    	if(sum>changeMoney){
		    		cha1.setText("Payment to small");
		    	}
		    	
		    	else{
		    	cha1.setText(chanMoney2);
		    	}
		    	}
		    	
		   
		    
		    
		    catch(NumberFormatException ex){
				WrongInput();
				
				}
	 
		    } });
  }
  
  public static boolean isDouble(String s){
		try{
			Double.parseDouble(s);
		}catch(NumberFormatException e){
			return false;
		}
		return true;
  }
  
  private void WrongInput() {
  	JOptionPane.showMessageDialog(null, "Please enter a positive number for payment amount.", "Attention", JOptionPane.WARNING_MESSAGE);
  	log.debug("User did not enter a valid number as a payment amount");
  }
  
  
  /** Event handler for the <code>submit purchase</code> event. */
  protected void submitPurchaseButtonClicked() {
    log.info("Sale complete");
    
    //try {
      log.debug("Contents of the current basket:\n" + model.getCurrentPurchaseTableModel());
      //domainController.submitCurrentPurchase(model.getCurrentPurchaseTableModel().getTableRows());
      domainController.saveHistoryState(model.getCurrentPurchaseTableModel().getTableRows(),model);
      
      List<SoldItem> k=model.getCurrentPurchaseTableModel().getTableRows();
      DB.add(domainController.getAllHistoryItems().toArray());
      
      endSale();   
      //System.out.println(model.getHistoryTableModel().getRowCount());

      model.getCurrentPurchaseTableModel().clear();
    //} catch (VerificationFailedException e1) {
    //  log.error(e1.getMessage());
    //}
  }

  


  /* === Helper methods that bring the whole purchase-tab to a certain state
   *     when called.
   */

  // switch UI to the state that allows to proceed with the purchase
  private void startNewSale() {
    purchasePane.reset();

    purchasePane.setEnabled(true);
    submitPurchase.setEnabled(true);
    cancelPurchase.setEnabled(true);
    newPurchase.setEnabled(false);
  }

  // switch UI to the state that allows to initiate new purchase
  private void endSale() {
    purchasePane.reset();

    cancelPurchase.setEnabled(false);
    submitPurchase.setEnabled(false);
    newPurchase.setEnabled(true);
    purchasePane.setEnabled(false);
  }




  /* === Next methods just create the layout constraints objects that control the
   *     the layout of different elements in the purchase tab. These definitions are
   *     brought out here to separate contents from layout, and keep the methods
   *     that actually create the components shorter and cleaner.
   */

  private GridBagConstraints getConstraintsForPurchaseMenu() {
    GridBagConstraints gc = new GridBagConstraints();

    gc.fill = GridBagConstraints.HORIZONTAL;
    gc.anchor = GridBagConstraints.NORTH;
    gc.gridwidth = GridBagConstraints.REMAINDER;
    gc.weightx = 1.0d;
    gc.weighty = 0d;

    return gc;
  }


  private GridBagConstraints getConstraintsForPurchasePanel() {
    GridBagConstraints gc = new GridBagConstraints();

    gc.fill = GridBagConstraints.BOTH;
    gc.anchor = GridBagConstraints.NORTH;
    gc.gridwidth = GridBagConstraints.REMAINDER;
    gc.weightx = 1.0d;
    gc.weighty = 1.0;

    return gc;
  }


  // The constraints that control the layout of the buttons in the purchase menu
  private GridBagConstraints getConstraintsForMenuButtons() {
    GridBagConstraints gc = new GridBagConstraints();

    gc.weightx = 0;
    gc.anchor = GridBagConstraints.CENTER;
    gc.gridwidth = GridBagConstraints.RELATIVE;

    return gc;
  }

}
