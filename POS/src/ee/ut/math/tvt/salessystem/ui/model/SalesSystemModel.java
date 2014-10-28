package ee.ut.math.tvt.salessystem.ui.model;

import javax.swing.table.TableModel;

import org.apache.log4j.Logger;

import ee.ut.math.tvt.salessystem.domain.controller.SalesDomainController;
 

/**
 * Main model. Holds all the other models.
 */
public class SalesSystemModel {
    
    private static final Logger log = Logger.getLogger(SalesSystemModel.class);

    // Warehouse model
    private StockTableModel warehouseTableModel;
    private HistoryItemDetailsTabModel historydetailtablemodel;
    //History model 
    private HistoryTableModel historyTableModel;
    // Current shopping cart model
    private PurchaseInfoTableModel currentPurchaseTableModel;

    private final SalesDomainController domainController;

    /**
     * Construct application model.
     * @param domainController Sales domain controller.
     */
    public SalesSystemModel(SalesDomainController domainController) {
        this.domainController = domainController;
        
        warehouseTableModel = new StockTableModel();
        currentPurchaseTableModel = new PurchaseInfoTableModel();
        historydetailtablemodel = new HistoryItemDetailsTabModel();
        historyTableModel= new HistoryTableModel();
        // populate stock model with data from the warehouse
        warehouseTableModel.populateWithData(domainController.loadWarehouseState());
        historyTableModel.populateWithData(domainController.loadHistoryState());
    }

    public StockTableModel getWarehouseTableModel() {
        return warehouseTableModel;
    }

    public PurchaseInfoTableModel getCurrentPurchaseTableModel() {
        return currentPurchaseTableModel;
    }

	public HistoryTableModel getHistoryTableModel() {
		return historyTableModel;
	}

	public TableModel getHistoryItemDetailsTabModel() {
		return historydetailtablemodel;
	}
   
}
