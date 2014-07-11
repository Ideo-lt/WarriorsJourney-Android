package lt.warriorsJourney.common.database;

public class Score
{
	private long id;
	private float score;
	private int difficulty;
	private int type;
	
	public Score(long id,float score,int diff,int type)
	{
		this.id = id;
		this.score = score;
		difficulty = diff;
		this.setType(type);
	}
	
	public int getDifficulty()
	{
		return difficulty;
	}
	
	public long getId()
	{
		return id;
	}

	public float getScore()
	{
		return score;
	}

	public int getType()
	{
		return type;
	}

	public void setType(int type)
	{
		this.type = type;
	}
}
