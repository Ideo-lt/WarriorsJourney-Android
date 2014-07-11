package lt.warriorsJourney.common.database;

public class InventoryItem
{
	private int place;
	private int id;
	
	public InventoryItem(int place,int id)
	{
		this.place = place;
		this.id = id;
	}
	
	public void setId(int id)
	{
		this.id = id;
	}
	
	public int getId()
	{
		return id;
	}
	
	public void setPlace(int place)
	{
		this.place = place;
	}
	
	public int getPlace()
	{
		return place;
	}
	
}