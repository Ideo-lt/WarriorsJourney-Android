package lt.warriorsJourney.common.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DataBaseHelper extends SQLiteOpenHelper {

		  public static final String TABLE_SCORE = "score";
		  public static final String COLUMN_ID = "id";
		  public static final String COLUMN_SCORE = "score";
		  public static final String COLUMN_DIFF = "difficulty";
		  public static final String COLUMN_GAME_TYPE = "type";
		  
		  public static final String TABLE_PLAYER = "player";
		  public static final String COLUMN_PLAYERID = "id";
		  public static final String COLUMN_LEVEL = "level";
		  public static final String COLUMN_EXPERIENCE = "experience";
		  public static final String COLUMN_RANK = "rank";
		  public static final String COLUMN_STRENGTH = "strength";
		  public static final String COLUMN_STAMINA = "stamina";
		  public static final String COLUMN_AGILITY = "agility";
		  public static final String COLUMN_DEXTERITY = "dexterity";
		  public static final String COLUMN_CONCENTRATION = "concentration";
		  public static final String COLUMN_INTELLIGENCE = "intelligence";
		  public static final String COLUMN_UNSPENT_POINTS = "unspent_points";
		  public static final String COLUMN_BALANCE = "balance";
		  
		  public static final String TABLE_PLAYER_INVENTORY = "inventory";
		  public static final String COLUMN_ITEM_PLACE = "place";
		  public static final String COLUMN_ITEM_ID = "id";
		  public static final String COLUMN_ITEM_EQUIPED = "equiped";
		  
		  private static final String DATABASE_NAME = "warriorsJoutney.db";
		  private static final int DATABASE_VERSION = 2;

		  // Database creation sql statement
		  
		  private static final String CREATE_TABLE_SCORE = "CREATE TABLE "
		      + TABLE_SCORE + "('" + COLUMN_ID
		      + "' INTEGER PRIMARY KEY AUTOINCREMENT, '"+COLUMN_SCORE+"' SINGLE,'"+COLUMN_DIFF+"' INTEGER,'"+COLUMN_GAME_TYPE+"'INTEGER);"; 

		  public static final String CREATE_TABLE_PLAYER = "CREATE TABLE "
			  + TABLE_PLAYER + "('"+COLUMN_PLAYERID+"' INTEGER,'"
			  + COLUMN_LEVEL +"' INTEGER, '"
			  + COLUMN_EXPERIENCE +"' BIGINT UNSIGNED, '"
			  + COLUMN_RANK +"' VARCHAR(15), '"
			  + COLUMN_STRENGTH +"' INTEGER, '"
  			  + COLUMN_STAMINA +"' INTEGER, '"
			  + COLUMN_AGILITY +"' INTEGER, '"
			  + COLUMN_DEXTERITY +"' INTEGER, '"
			  + COLUMN_CONCENTRATION + "' INTEGER, '"
			  + COLUMN_INTELLIGENCE +"' INTEGER, '"
  			  + COLUMN_UNSPENT_POINTS + "' INTEGER, '"
  			  + COLUMN_BALANCE +"' BIGINT UNSIGNED);";
		  
		  public static final String CREATE_TABLE_INVENTORY = "CREATE TABLE "
			  + TABLE_PLAYER_INVENTORY + "('"+ COLUMN_ITEM_PLACE +"' INTEGER, '"
			  + COLUMN_ITEM_ID +"' INTEGER, '"+COLUMN_ITEM_EQUIPED+"' INTEGER );";  
		  
		  public static final String CREATE_PLAYER = "INSERT INTO '"+TABLE_PLAYER+"' ('"+COLUMN_PLAYERID+"','"+COLUMN_LEVEL+"','"+COLUMN_EXPERIENCE+"','"+COLUMN_RANK+"','"
		  		+COLUMN_STRENGTH+"','"+COLUMN_STAMINA+"','"+COLUMN_AGILITY+"','"+COLUMN_DEXTERITY+"','"+COLUMN_CONCENTRATION+"','"+COLUMN_INTELLIGENCE+"','"+COLUMN_UNSPENT_POINTS+"','"+COLUMN_BALANCE+"') VALUES (1,1,0,'Beginner',1,1,1,1,1,1,0,0);";
		  
		  public DataBaseHelper(Context context) {
		    super(context, DATABASE_NAME, null, DATABASE_VERSION);
		  }
		  
		  

		  @Override
		  public void onCreate(SQLiteDatabase database) {
		    database.execSQL(CREATE_TABLE_SCORE);
		    database.execSQL(CREATE_TABLE_PLAYER);
		    database.execSQL(CREATE_PLAYER);
		    database.execSQL(CREATE_TABLE_INVENTORY);
		  }

		  @Override//turetu perdaryt o ne dropint...
		  public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		    Log.w(DataBaseHelper.class.getName(),
		        "Upgrading database from version " + oldVersion + " to "
		            + newVersion + ", which will destroy all old data");
		    db.execSQL("DROP TABLE IF EXISTS " + TABLE_SCORE);
		    onCreate(db);
		  }

} 
