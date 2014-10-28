package ee.ut.math.tvt.salessystem.domain.data;

import java.util.List;

public class HistoryItem implements Cloneable, DisplayableItem {
    private List<SoldItem> soldItems;
    private Long id;
    private StockItem stockI;
    private double price;
	private String date;
	private String time;
	private double sum;
	private int NumberOfElements;
	
	public HistoryItem(List<SoldItem> listsold, String date, String time, Long id) {
		super();
		this.stockI=listsold.get(0).getStockItem();
		this.NumberOfElements=listsold.size();
		this.soldItems = listsold;
		this.id = id;
		this.date=date;
		this.time=time;
		this.price=stockI.getPrice();
		this.sum=totalSum();
	}




	public double getPrice() {
		return price;
	}


	public void setPrice(double price) {
		this.price = price;
	}


	public String getDate() {
		return date;
	}


	public void setDate(String date) {
		this.date = date;
	}


	public String getTime() {
		return time;
	}


	public void setTime(String time) {
		this.time = time;
	}


	@Override
	public Long getId() {
		// TODO Auto-generated method stub
		return id;
	}




	public List<SoldItem> getSoldItems() {
		return soldItems;
	}




	public void setSoldItems(List<SoldItem> soldItems) {
		this.soldItems = soldItems;
	}




	public StockItem getstockI() {
		return stockI;
	}




	public void setstockI(StockItem stockI) {
		stockI = stockI;
	}

	public double totalSum(){
		sum=0;
		StockItem stocki;
		
		for(int i=0;i<soldItems.size();i++){
			stocki= soldItems.get(i).getStockItem();
			sum+=stocki.getPrice();
		}
		return sum;
	}




	public double getSum() {
		return sum;
	}




	public void setSum(double sum) {
		this.sum = sum;
	}




	public void setId(Long id) {
		this.id = id;
	}




	public int getNumberOfElements() {
		return NumberOfElements;
	}
 
	public void setNumberOfElements(int numberOfElements) {
		NumberOfElements = numberOfElements;
	}







}
	