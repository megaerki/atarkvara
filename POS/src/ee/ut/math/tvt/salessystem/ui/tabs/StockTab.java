package ee.ut.math.tvt.salessystem.ui.tabs;

import ee.ut.math.tvt.salessystem.domain.data.StockItem;
import ee.ut.math.tvt.salessystem.ui.model.SalesSystemModel;
import ee.ut.math.tvt.salessystem.ui.panels.PurchaseItemPanel;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;



import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.JTableHeader;
import javax.swing.JOptionPane;

public class StockTab {

  private JButton addItem;
  private JTextField newItemID;
  private JTextField newItemName;
  private JTextField newItemPrice;
  private JTextField newItemQuantity;
  private JTextField newItemDescription;
  
  private SalesSystemModel model;

  public StockTab(SalesSystemModel model) {
    this.model = model;
  }

  // warehouse stock tab - consists of a menu and a table
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

    panel.add(drawStockMenuPane(), gc);

    gc.weighty = 1.0;
    gc.fill = GridBagConstraints.BOTH;
    panel.add(drawStockMainPane(), gc);
    return panel;
  }

  // warehouse menu
  private Component drawStockMenuPane() {
    JPanel panel = new JPanel();

    GridBagConstraints gc = new GridBagConstraints();
    GridBagLayout gb = new GridBagLayout();

    panel.setLayout(gb);

    gc.anchor = GridBagConstraints.NORTHWEST;
    gc.weightx = 0;

    newItemID = new JTextField("ID");
    newItemName = new JTextField("Name");
    newItemPrice = new JTextField("Price");
    newItemQuantity = new JTextField("Quantity");
    newItemDescription = new JTextField("Description");
    addItem = new JButton("Add");
    gc.gridwidth = GridBagConstraints.RELATIVE;
    gc.weightx = 1.0;
    
  //Add button listener to insert new items to "database"
    addItem.addActionListener(new ActionListener(){
  	  public void actionPerformed(ActionEvent e){
  		  AddButtonClicked();}
  	  
  	  private void AddButtonClicked(){
  		  String NewID = newItemID.getText();
  		  String NewName = newItemName.getText();
  		  String NewPrice = newItemPrice.getText();
  		  String NewQuantity = newItemQuantity.getText();
  		  String NewDescription = newItemDescription.getText();
  		  
  		  try {
  			  model.getWarehouseTableModel().addItem(new StockItem(
  					  Long.parseLong(NewID), 
  					  NewName, 
  					  NewDescription,
  					  Double.parseDouble(NewPrice), 
  					  Integer.parseInt(NewQuantity)));
  			  drawStockMainPane();}
  		  catch (NumberFormatException e){
  			  JOptionPane.showMessageDialog(null, "Please insert correct data!","Error", JOptionPane.ERROR_MESSAGE);
  		  }
  		  new PurchaseItemPanel(model);
  		  }
  		  });
    //Set size for new items. For some reason if you decrease width, then all of the fields are 1px wide. 
    newItemID.setPreferredSize(new Dimension(50,20));
    newItemName.setPreferredSize(new Dimension(150,20));
    newItemPrice.setPreferredSize(new Dimension(50,20));
    newItemQuantity.setPreferredSize(new Dimension(100,20));
    newItemDescription.setPreferredSize(new Dimension(120,20));
    //Add new items to the panel
    panel.add(newItemID);
    panel.add(newItemName);
    panel.add(newItemPrice);
    panel.add(newItemQuantity);
    panel.add(newItemDescription);
    panel.add(addItem, gc);

    panel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
    return panel;
  }


  // table of the wareshouse stock
  private Component drawStockMainPane() {
    JPanel panel = new JPanel();

    JTable table = new JTable(model.getWarehouseTableModel());

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

    panel.setBorder(BorderFactory.createTitledBorder("Warehouse status"));
    return panel;
  }

}