package ee.ut.math.tvt.salessystem.domain.controller;

import java.util.List;
import org.hibernate.Session;
import ee.ut.math.tvt.salessystem.domain.data.HistoryItem;
import ee.ut.math.tvt.salessystem.domain.data.SoldItem;
import ee.ut.math.tvt.salessystem.domain.data.StockItem;
import ee.ut.math.tvt.salessystem.domain.exception.VerificationFailedException;
import ee.ut.math.tvt.salessystem.ui.model.SalesSystemModel;

/**
 * Sales domain controller is responsible for the domain specific business
 * processes.
 */
public interface SalesDomainController {

    /**
     * Load the current state of the warehouse.
     * 
     * @return List of ${link
     *         ee.ut.math.tvt.salessystem.domain.data.StockItem}s.
     */
    //public List<StockItem> loadWarehouseState();
	public List<StockItem> getAllStockItems();
    public List<HistoryItem> loadDetailedDeatailHistoryState();
    //public List<HistoryItem> loadHistoryState();
    public List<HistoryItem> getAllHistoryItems();
    public List<SoldItem> loadDetailedHistoryState();
    // business processes
    /**
     * Initiate new business transaction - purchase of the goods.
     * 
     * @throws VerificationFailedException
     */
    public void startNewPurchase() throws VerificationFailedException;

    /**
     * Rollback business transaction - purchase of goods.
     * 
     * @throws VerificationFailedException
     */
    public void cancelCurrentPurchase() throws VerificationFailedException;

    /**
     * Commit business transaction - purchsae of goods.
     * 
     * @param goods
     *            Goods that the buyer has chosen to buy.
     * @return 
     * @throws VerificationFailedException
     */
    public void submitCurrentPurchase(List<SoldItem> goods)
            throws VerificationFailedException;

	public void saveHistoryState(List<SoldItem> tableRows,
			SalesSystemModel model);
	
	public void endSession();

	public Session getSession();
	public void setModel(SalesSystemModel model);
    
}
