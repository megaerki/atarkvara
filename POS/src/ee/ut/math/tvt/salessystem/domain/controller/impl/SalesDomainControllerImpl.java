package ee.ut.math.tvt.salessystem.domain.controller.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import ee.ut.math.tvt.salessystem.domain.exception.VerificationFailedException;
import ee.ut.math.tvt.salessystem.domain.data.HistoryItem;
import ee.ut.math.tvt.salessystem.domain.controller.SalesDomainController;
import ee.ut.math.tvt.salessystem.domain.data.SoldItem;
import ee.ut.math.tvt.salessystem.domain.data.StockItem;
import ee.ut.math.tvt.salessystem.ui.model.SalesSystemModel;

/**
 * Implementation of the sales domain controller.
 */
public class SalesDomainControllerImpl implements SalesDomainController {
	static List<StockItem> dataset = new ArrayList<StockItem>();
	static List<HistoryItem> historydataset = new ArrayList<HistoryItem>();
	
	public void submitCurrentPurchase(List<SoldItem> goods) throws VerificationFailedException {
		// Let's assume we have checked and found out that the buyer is underaged and
		// cannot buy chupa-chups
		//  throw new VerificationFailedException("Underaged!");
		// XXX - Save purchase
		
	 
	
		 
	}

	public void submitHistory(List<SoldItem> goods, SalesSystemModel model) {
		Date date = new Date();
	    String[] parts = date.toString().split(" ");
	    Long id= (long) historydataset.size();
		HistoryItem newElem= new HistoryItem(model.getCurrentPurchaseTableModel().getTableRows(), parts[1]+" "+parts[2]+" "+parts[5], parts[3],id);
		model.getHistoryTableModel().addItem(newElem);
		 
	}

	
	public void cancelCurrentPurchase() throws VerificationFailedException {				
		// XXX - Cancel current purchase
	}
	

	public void startNewPurchase() throws VerificationFailedException {
		// XXX - Start new purchase
	}
	public List<StockItem> getList(){
		return dataset;
	}

	public List<StockItem> loadWarehouseState() {
		// XXX mock implementation
		//List<StockItem> dataset = new ArrayList<StockItem>();

		StockItem chips = new StockItem(1l, "Lays chips", "Potato chips", 11.0, 5);
		StockItem chupaChups = new StockItem(2l, "Chupa-chups", "Sweets", 8.0, 8);
	    StockItem frankfurters = new StockItem(3l, "Frankfurters", "Beer sauseges", 15.0, 12);
	    StockItem beer = new StockItem(4l, "Free Beer", "Student's delight", 0.0, 100);

		dataset.add(chips);
		dataset.add(chupaChups);
		dataset.add(frankfurters);
		dataset.add(beer);
		
		return dataset;
	}
	
	public List<HistoryItem> loadHistoryState() {
		StockItem chips = new StockItem(1l, "Lays chips", "Potato chips", 11.0, 5);
	
		SoldItem sold1 =new SoldItem(chips, 1);
		List<SoldItem> listsold=new ArrayList<SoldItem>();
		listsold.add(sold1);
		
		HistoryItem newElem= new HistoryItem(listsold, "Jan 01 2011","11:11:11",(long) 0);
		historydataset.add(newElem);
		System.out.println(historydataset);
		return historydataset;
	}

	@Override
	public List<HistoryItem> loadDetailedDeatailHistoryState() {
		// TODO Auto-generated method stub
		return null;
	}

 

	@Override
	public List<SoldItem> loadDetailedHistoryState() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void saveHistoryState(List<SoldItem> tableRows,
			SalesSystemModel model) {
		Date date = new Date();
	    String[] parts = date.toString().split(" ");
	    Long id= (long) historydataset.size();
		HistoryItem newElem= new HistoryItem(model.getCurrentPurchaseTableModel().getTableRows(), parts[1]+" "+parts[2]+" "+parts[5], parts[3],id);
		model.getHistoryTableModel().addItem(newElem);
		
		
	}
	public List<HistoryItem> getHistorydataset() {
		return historydataset;
	}

	public void setHistorydataset(List<HistoryItem> historydataset) {
		this.historydataset = historydataset;
	}

}
