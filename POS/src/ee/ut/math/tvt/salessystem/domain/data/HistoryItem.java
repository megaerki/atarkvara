package ee.ut.math.tvt.salessystem.domain.data;

import java.util.List;

public class HistoryItem implements Cloneable, DisplayableItem {
    private List<SoldItem> soldItems;
    private Long id;
	private String date;
	private String time;
	private double sum;
	private int NumberOfElements;
	
	public HistoryItem(List<SoldItem> listsold, String date, String time, Long id) {
		this.NumberOfElements=listsold.size();
		this.soldItems = listsold;
		this.id = id;
		this.date=date;
		this.time=time;
		this.sum=totalSum();
	}

	public String getDate() {
		return date;
	}

	public String getTime() {
		return time;
	}

	@Override
	public Long getId() {
		// TODO Auto-generated method stub
		return id;
	}

	public List<SoldItem> getSoldItems() {
		return soldItems;
	}

	public double totalSum(){
		double sum=0;
		SoldItem stocki;
		
		for(int i=0;i<soldItems.size();i++){
			stocki= soldItems.get(i);
			sum+=stocki.getSum();
		}
		return sum;
	}

	public double getSum() {
		return sum;
	}

	public int getNumberOfElements() {
		return NumberOfElements;
	}

}
	