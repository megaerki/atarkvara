package ee.ut.math.tvt.salessystem.domain.data;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "HISTORYITEM")
public class HistoryItem implements Cloneable, DisplayableItem {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	
	@Column(name = "sale_date")
	private String date;
	
	@Column(name = "sale_time")
	private String time;
	
	@Column(name = "cost")
	private double sum;
	
	@Column(name = "elements")
	private int NumberOfElements;
	
	@OneToMany(mappedBy = "historyItem")
	private List<SoldItem> soldItems;
	
	public HistoryItem() {
		
	}
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

	public Long getId() {
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
	