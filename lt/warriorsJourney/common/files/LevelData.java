package lt.warriorsJourney.common.files;

public class LevelData
{
	private int id;
	private int expNeeded;
	private int statBonus;
	private int coinBonus;
	private String rank;
	
	public LevelData(int id,int exp,int bonusStats,int bonusCoins,String rank)
	{
		setId(id);
		setExpNeeded(exp);
		setStatBonus(bonusStats);
		setCoinBonus(bonusCoins);
		setRank(rank);
	}

	public int getId()
	{
		return id;
	}

	public void setId(int id)
	{
		this.id = id;
	}

	public int getExpNeeded()
	{
		return expNeeded;
	}

	public void setExpNeeded(int expNeeded)
	{
		this.expNeeded = expNeeded;
	}

	public int getStatBonus()
	{
		return statBonus;
	}

	public void setStatBonus(int statBonus)
	{
		this.statBonus = statBonus;
	}

	public int getCoinBonus()
	{
		return coinBonus;
	}

	public void setCoinBonus(int coinBonus)
	{
		this.coinBonus = coinBonus;
	}

	public String getRank()
	{
		return rank;
	}

	public void setRank(String rank)
	{
		this.rank = rank;
	}
}
