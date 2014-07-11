package lt.warriorsJourney.common.database;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

public class ScoreData {

	  // Database fields
	  private SQLiteDatabase database;
	  private DataBaseHelper dbHelper;
	  private String[] allColumns = { DataBaseHelper.COLUMN_ID,
			  DataBaseHelper.COLUMN_SCORE,DataBaseHelper.COLUMN_DIFF,DataBaseHelper.COLUMN_GAME_TYPE};

	  public ScoreData(Context context)
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

	  public Score addScore(float score,int difficulty,int type)
	  {
		open();
		
	    ContentValues values = new ContentValues();
	    
	    values.put(DataBaseHelper.COLUMN_SCORE, score);
	    values.put(DataBaseHelper.COLUMN_DIFF, difficulty);
	    values.put(DataBaseHelper.COLUMN_GAME_TYPE, type);
	    	    
	    long insertId = database.insert(DataBaseHelper.TABLE_SCORE, null, values);
	    
	    Cursor cursor = database.query(DataBaseHelper.TABLE_SCORE,
	        allColumns, DataBaseHelper.COLUMN_ID + " = " + insertId, null,
	        null, null, null);
	    
	    cursor.moveToFirst();
	    
	    Score newScore = cursorToScore(cursor);
	    
	    cursor.close();
	    close();
	    
	    return newScore;
	  }

	  public void deleteScore(Score score)
	  {
		open();
		
	    long id = score.getId();
	    
	    System.out.println("Comment deleted with id: " + id);
	    
	    database.delete(DataBaseHelper.TABLE_SCORE, DataBaseHelper.COLUMN_ID
	        + " = " + id, null);
	    close();
	  }

	  public List<Score> getAllScoresByDiffAndType(int difficulty,int type)
	  {
		open();  
		
		List<Score> scores = new ArrayList<Score>();
		
		Cursor cursor = database.query(DataBaseHelper.TABLE_SCORE,
					allColumns, null, null, null, null, null);

		cursor.moveToFirst();
	    
	   	while (!cursor.isAfterLast())
	   	{
	   		Score score = cursorToScore(cursor);
	   		if(score.getDifficulty() == difficulty && score.getType() == type)
	   			scores.add(score);
	    	cursor.moveToNext();
	    }
	    // make sure to close the cursor
	   	
	   	cursor.close();
		
	    close();
	    return scores;
	  }

	  private Score cursorToScore(Cursor cursor)
	  {	  
		  //id place score name
		  Score score = new Score(cursor.getLong(0),cursor.getFloat(1),cursor.getInt(2),cursor.getInt(3));
		  return score;
	  }
	  
	  public List<Score> getBestScores(int difficulty,int type)
	  {
		  List<Score> bestScores = new ArrayList<Score>();
		  List<Score> allScores = getAllScoresByDiffAndType(difficulty,type);
		  
		  allScores = sortScores(allScores);
		  
		  if(allScores.size() < 9)
			  return allScores;
		  else
			  for(int i = 0;i < 9;i++)
			  	  bestScores.add(allScores.get(i));
		
		  return bestScores;
	  }
	  
	  public boolean isBest(float score,int difficulty,int type)
	  {
		  if(getBestScores(difficulty,type).size() == 0)
			  return true;
		  
		  if(getBestScores(difficulty,type).get(0).getScore() < score)
			  return true;
		  else
			  return false;
	  }
	  
	  public List<Score> sortScores(List<Score> scores)
	  {
		  Score tempScore;
		  
		  if(scores.size() == 1)
			  return scores;
		  else
			  for(int i = 0;i < scores.size()-1;i++)
				  for(int x  = 0 ;x < scores.size()-1-i;x++)
				  { 
					  if(scores.get(x).getScore() < scores.get(x+1).getScore())
					  {
						  tempScore = scores.get(x);
						  scores.set(x, scores.get(x+1));
						  scores.set(x+1, tempScore);
					  }
				  }
		  
		  return scores;
	  }
} 
