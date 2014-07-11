package lt.warriorsJourney.common.database;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import android.annotation.SuppressLint;
import android.content.Context;

public class InventoryData
{
	@SuppressLint("UseSparseArrays")
	private Map<Integer,InventoryItem> inventory = new HashMap<Integer,InventoryItem>();
	private InventoryHandler inventoryData;
	
	public InventoryData(Context context)
	{
		inventoryData = new InventoryHandler(context);
		loadInventory();
	}
	
	public void loadInventory()
	{
		inventoryData.loadPlayerInventory(this);
	}
	
	public void saveInventory()
	{
		inventoryData.savePlayerInventory(this);
	}
	
	public void addInventoryItem(InventoryItem invItem)
	{
		inventory.put(invItem.getPlace(), invItem);
	}
	
	public void setItemByPlace(int place,InventoryItem invItem)
	{
		inventory.put(place, invItem);
	}
	
	public InventoryItem getItemByPlace(int place)
	{
		Iterator<Integer> invIter = inventory.keySet().iterator();
		
		for(int i= 0 ;i<inventory.size();i++)
		{
			int id = invIter.next();
			
			if(inventory.get(id).getPlace() == place)
				return inventory.get(id);
	
		}
		
		return null;
	}
	
	public Map<Integer,InventoryItem> getInventory()
	{
		return inventory;
	}
}
