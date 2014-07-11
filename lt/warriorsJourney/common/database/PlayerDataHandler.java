package lt.warriorsJourney.common.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

public class PlayerDataHandler
{
	  private SQLiteDatabase database;
	  private DataBaseHelper dbHelper;
	  private String[] allColumns = { DataBaseHelper.COLUMN_PLAYERID,
			  DataBaseHelper.COLUMN_LEVEL,
			  DataBaseHelper.COLUMN_EXPERIENCE,DataBaseHelper.COLUMN_RANK,
			  DataBaseHelper.COLUMN_STRENGTH,DataBaseHelper.COLUMN_STAMINA,
			  DataBaseHelper.COLUMN_AGILITY,DataBaseHelper.COLUMN_DEXTERITY,
			  DataBaseHelper.COLUMN_CONCENTRATION,DataBaseHelper.COLUMN_INTELLIGENCE,
			  DataBaseHelper.COLUMN_UNSPENT_POINTS,DataBaseHelper.COLUMN_BALANCE};

	  public PlayerDataHandler(Context context)
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
	  
	  public void savePlayerStats(PlayerData data)
	  {
			open();
			
		    ContentValues values = new ContentValues();
		    
		    values.put(DataBaseHelper.COLUMN_LEVEL, data.getLevel());
		    values.put(DataBaseHelper.COLUMN_EXPERIENCE, data.getExp());
		    values.put(DataBaseHelper.COLUMN_RANK, data.getRankl());
		    values.put(DataBaseHelper.COLUMN_STRENGTH, data.getStrenght());
		    values.put(DataBaseHelper.COLUMN_STAMINA, data.getStamina());
		    values.put(DataBaseHelper.COLUMN_AGILITY, data.getAgility());
		    values.put(DataBaseHelper.COLUMN_DEXTERITY, data.getDexterity());
		    values.put(DataBaseHelper.COLUMN_CONCENTRATION, data.getConcentration());
		    values.put(DataBaseHelper.COLUMN_INTELLIGENCE, data.getIntelligence());
		    values.put(DataBaseHelper.COLUMN_UNSPENT_POINTS, data.getUnspentPoints());
		    values.put(DataBaseHelper.COLUMN_BALANCE, data.getBalance());
		    
		    database.update(DataBaseHelper.TABLE_PLAYER,values, DataBaseHelper.COLUMN_PLAYERID + "= 1",null);

		    close(); 
	  }
	  
	  public void loadPlayerStats(PlayerData data)
	  {
		 open();
		 
		 Cursor cursor = database.query(DataBaseHelper.TABLE_PLAYER,
					allColumns, null, null, null, null, null);

		 cursor.moveToFirst();
		    
		 data.setLevel(cursor.getInt(1));
		 data.setExp(cursor.getLong(2));
		 data.setRank(cursor.getString(3));
		 data.setStamina(cursor.getInt(4));
		 data.setStrenght(cursor.getInt(5));
		 data.setAgility(cursor.getInt(6));
		 data.setDexterity(cursor.getInt(7));
		 data.setConcentration(cursor.getInt(8));
		 data.setIntelligence(cursor.getInt(9));
		 data.setUnspentPoints(cursor.getInt(10));
		 data.increaseBalance(cursor.getInt(11));
				 
		 cursor.close();
		 close();
	  }
}
