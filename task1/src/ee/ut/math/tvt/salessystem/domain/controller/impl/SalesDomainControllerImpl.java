package ee.ut.math.tvt.salessystem.domain.controller.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hibernate.Session;

import ee.ut.math.tvt.salessystem.util.HibernateUtil;
import ee.ut.math.tvt.salessystem.domain.exception.VerificationFailedException;
import ee.ut.math.tvt.salessystem.domain.data.HistoryItem;
import ee.ut.math.tvt.salessystem.domain.controller.SalesDomainController;
import ee.ut.math.tvt.salessystem.domain.data.SoldItem;
import ee.ut.math.tvt.salessystem.domain.data.StockItem;
import ee.ut.math.tvt.salessystem.ui.model.SalesSystemModel;

public class SalesDomainControllerImpl implements SalesDomainController {
	 
	public static List<HistoryItem> historydataset = new ArrayList<HistoryItem>();
	@SuppressWarnings("unused")
	private SalesSystemModel model;
	
	private Session session = HibernateUtil.currentSession();
	public List<StockItem> getAllStockItems() {
		@SuppressWarnings("unchecked")
		List<StockItem> result = session.createQuery("from StockItem").list();
		return result;
	}
	
	public List<HistoryItem> getAllHistoryItems() {
		@SuppressWarnings("unchecked")
		List<HistoryItem> result = session.createQuery("from HistoryItem").list();
		return result;
	}

	public void submitHistory(List<SoldItem> goods, SalesSystemModel model) {
		Date date = new Date();
	    String[] parts = date.toString().split(" ");
	    Long id= (long) historydataset.size();
		HistoryItem newElem= new HistoryItem(model.getCurrentPurchaseTableModel().getTableRows(), parts[1]+" "+parts[2]+" "+parts[5], parts[3],id);
		try {
			model.getHistoryTableModel().addItem(newElem);

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		 
	}

	public void saveHistoryState(List<SoldItem> tableRows,
			SalesSystemModel model) {
		Date date = new Date();
	    String[] parts = date.toString().split(" ");
	    Long id= (long) historydataset.size();
		HistoryItem newElem= new HistoryItem(model.getCurrentPurchaseTableModel().getTableRows(), parts[1]+" "+parts[2]+" "+parts[5], parts[3],id);
		historydataset.add(newElem);
		try {
			model.getHistoryTableModel().addItem(newElem);
			
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void endSession() {
	    HibernateUtil.closeSession();
	}
	
	public Session getSession(){
		return session;
	}

	public void setModel(SalesSystemModel model) {
		this.model = model;
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
	public void startNewPurchase() throws VerificationFailedException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void cancelCurrentPurchase() throws VerificationFailedException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void submitCurrentPurchase(List<SoldItem> goods)
			throws VerificationFailedException {
		// TODO Auto-generated method stub
		
	}

}
