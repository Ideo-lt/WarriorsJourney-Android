package lt.warriorsJourney.common.database;

import java.util.Iterator;
import java.util.Map;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

public class InventoryHandler
{
	private SQLiteDatabase database;
	private DataBaseHelper dbHelper;
	
	private String[] allColumns = { DataBaseHelper.COLUMN_ITEM_PLACE,
			  DataBaseHelper.COLUMN_ITEM_ID,DataBaseHelper.COLUMN_ITEM_EQUIPED};

	public InventoryHandler(Context context)
	{
		dbHelper = new DataBaseHelper(context);
	}

	public void open() throws SQLException
	{
		database = dbHelper.getWritableDatabase();
	}

	public void close()
	{
		dbHelper.close();
	}
	  
	public void savePlayerInventory(InventoryData data)
	{
		open();
		ContentValues values = new ContentValues();
		
		Map<Integer,InventoryItem> inv = data.getInventory();
		Iterator<Integer> invIter = inv.keySet().iterator();
				
		for(int i= 0 ;i<inv.size();i++)
		{
			int id = invIter.next();
			values.put(DataBaseHelper.COLUMN_ITEM_PLACE, inv.get(id).getPlace());
			values.put(DataBaseHelper.COLUMN_ITEM_ID, inv.get(id).getPlace());
			database.insert(DataBaseHelper.TABLE_PLAYER_INVENTORY, null, values);
			values.clear();
		}
		
		close(); 
	}
	  
	public void loadPlayerInventory(InventoryData data)
	{
		open();
		
		Cursor cursor = database.query(DataBaseHelper.TABLE_PLAYER_INVENTORY,
				allColumns, null, null, null, null, null);
		
		cursor.moveToFirst();
		
		while (!cursor.isAfterLast())
	   	{
	   		InventoryItem item = cursorToInventoryItem(cursor);
	   		data.addInventoryItem(item);
	    	cursor.moveToNext();
	    }
		
		cursor.close();
		close();
	}
	
	public InventoryItem cursorToInventoryItem(Cursor cursor)
	{
		return new InventoryItem(cursor.getInt(0),cursor.getInt(1));
	}
		
}
