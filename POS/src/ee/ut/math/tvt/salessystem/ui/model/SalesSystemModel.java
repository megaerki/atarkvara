package ee.ut.math.tvt.salessystem.ui.model;

import java.util.List;

import ee.ut.math.tvt.salessystem.domain.controller.SalesDomainController;
import ee.ut.math.tvt.salessystem.domain.data.StockItem;
 
public class SalesSystemModel {
    
//    private static final Logger log = Logger.getLogger(SalesSystemModel.class);

    // Warehouse model
    private StockTableModel warehouseTableModel;
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
        historyTableModel= new HistoryTableModel();
        // populate stock model with data from the warehouse
        //warehouseTableModel.populateWithData(domainController.loadWarehouseState());
        regetWarehouseTableModel();
      
        //historyTableModel.populateWithData(domainController.loadHistoryState());
        historyTableModel.populateWithData(domainController.getAllHistoryItems());
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

	public SalesDomainController getDomainController() {
		return domainController;
	}
	public void regetWarehouseTableModel() {
        List<StockItem> stockItems = domainController.getAllStockItems();
        warehouseTableModel.populateWithData(stockItems);
}
}
