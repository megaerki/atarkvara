package ee.ut.math.tvt.salessystem.domain.data;



/**
 * Already bought StockItem. SoldItem duplicates name and price for preserving history. 
 */
public class SoldItem implements Cloneable, DisplayableItem {

    private Long id;
    private StockItem stockItem;
    private String name;
    private  Integer quantity;
    private  double price;
    
    public SoldItem(StockItem stockItem, int quantity) {
        this.stockItem = stockItem;
        this.name = stockItem.getName();
        this.price = stockItem.getPrice();
        this.quantity = quantity;
        this.id = stockItem.getId();
        
    }
    
    public Long getId() {
        return id;
    }
    
    public String getName() {
        return name;
    }
    
    public double getPrice() {
        return price;
    }

    public Integer getQuantity() {
        return quantity;
    }
    
    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public  double getSum() {
        return price * ((double) quantity);
    }

    public StockItem getStockItem() {
        return stockItem;
    }
    
}
