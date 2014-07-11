package lt.warriorsJourney.common.database;

import lt.warriorsJourney.common.assets.Assets;
import lt.warriorsJourney.common.files.LevelData;
import lt.warriorsJourney.common.files.LevelReader;
import android.content.Context;

public class PlayerData
{
	private int level;
	private long exp;
	private String rank;
	private int strenght;
	private int stamina;
	private int agility;
	private int dexterity;
	private int concentration;
	private int intelligence;
	private int unspentPoints;
	private int balance;
	
	private PlayerDataHandler dataBase;
	private LevelReader levels;
	
	public PlayerData(Context context)
	{
		dataBase = new PlayerDataHandler(context);
		levels = Assets.levels;		
		loadData();
	}
		
	public void loadData()
	{
		dataBase.loadPlayerStats(this);
	}
	
	public void saveData()
	{
		dataBase.savePlayerStats(this);
	}
	
	public int getLevel()
	{
		return level;
	}
	
	public void setLevel(int level)
	{
		this.level = level;
	}
	
	public long getExp()
	{
		return exp;
	}
	
	public void setExp(long exp)
	{
		this.exp = exp;
	}
	
	public String getRankl()
	{
		return rank;
	}
	
	public void setRank(String rank)
	{
		this.rank = rank;
	}

	public int getStrenght()
	{
		return strenght;
	}

	public void setStrenght(int strenght)
	{
		this.strenght = strenght;
	}

	public int getAgility()
	{
		return agility;
	}

	public void setAgility(int agility)
	{
		this.agility = agility;
	}

	public int getDexterity()
	{
		return dexterity;
	}

	public void setDexterity(int dexterity)
	{
		this.dexterity = dexterity;
	}

	public int getConcentration()
	{
		return concentration;
	}

	public void setConcentration(int concentration)
	{
		this.concentration = concentration;
	}

	public int getIntelligence()
	{
		return intelligence;
	}

	public void setIntelligence(int intelligence)
	{
		this.intelligence = intelligence;
	}

	public int getBalance()
	{
		return balance;
	}

	public void increaseBalance(int value)
	{
		balance += value;
	}
	
	public void decreaseBalance(int value)
	{
		balance -= value;
	}

	public int getStamina()
	{
		return stamina;
	}

	public void setStamina(int stamina)
	{
		this.stamina = stamina;
	}

	public int getUnspentPoints()
	{
		return unspentPoints;
	}

	public void setUnspentPoints(int unspentPoints)
	{
		this.unspentPoints = unspentPoints;
	}
	
	public void resetStats()
	{
		int points = 0;
		
		if(strenght > 1)
		{
			points += strenght -1;
			strenght = 1;
		}
		
		if(stamina > 1)
		{
			points += stamina -1;
			stamina = 1;
		}
		
		if(dexterity > 1)
		{
			points += dexterity -1;
			dexterity = 1;
		}
		
		if(agility > 1)
		{
			points += agility -1;
			agility = 1;
		}
		
		if(concentration > 1)
		{
			points += concentration -1;
			concentration = 1;
		}
		
		if(intelligence > 1)
		{
			points += intelligence -1;
			intelligence = 1;
		}
		
		unspentPoints += points;
	}
	
	public void increaseExp(int value)
	{
		exp += value;
	}
	
	public void increaseStrenght(int value)
	{
		strenght += value;
		unspentPoints -= value;
	}
	
	public void increaseStamina(int value)
	{
		stamina += value;
		unspentPoints -= value;
	}
	
	public void increaseDexterity(int value)
	{
		dexterity += value;
		unspentPoints -= value;
	}
	
	public void increaseAgility(int value)
	{
		agility += value;
		unspentPoints -= value;
	}
	
	public void increaseConcentration(int value)
	{
		concentration += value;
		unspentPoints -= value;
	}
	
	public void increaseIntelligence(int value)
	{
		intelligence += value;
		unspentPoints -= value;
	}
	
	public boolean isLevelUp()
	{
		LevelData levelUp = levels.levels.get(level+1);
		
		if(exp >= levelUp.getExpNeeded())
		{
			unspentPoints += 1*levelUp.getStatBonus();
			balance += 1*levelUp.getCoinBonus();
			rank = levelUp.getRank();
			level +=1;
			return true;
		}
		else
			return false;
	}
}
