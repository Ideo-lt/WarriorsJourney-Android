package lt.warriorsJourney.common.files;

public class Item
{
	private int id;
	private String name;
	private String description;
	private int price;
	
	private int bonusSTR;
	private int bonusSTA;
	private int bonusDEX;
	private int bonusAGI;
	private int bonusCON;
	private int bonusINT;
	private int effectType;
	
	public Item(int id,String name,String desc,int price,int bonusStr,int bonusSta,
				int bonusDex,int bonusAgi,int bonusCon,int bonusInt,int effectType)
	{
		this.id = id;
		this.name = name;
		this.description = desc;
		this.price = price;
		this.bonusSTR = bonusStr;
		this.bonusSTA = bonusSta;
		this.bonusDEX = bonusDex;
		this.bonusAGI = bonusAgi;
		this.bonusCON = bonusCon;
		this.bonusINT = bonusInt;
		this.effectType = effectType;
	}
	
	public void setId(int id)
	{
		this.id = id;
	}
	
	public int getId()
	{
		return id;
	}
	
	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public String getDescription()
	{
		return description;
	}

	public void setDescription(String description)
	{
		this.description = description;
	}

	public int getPrice()
	{
		return price;
	}

	public void setPrice(int price)
	{
		this.price = price;
	}

	public int getBonusSTR()
	{
		return bonusSTR;
	}

	public void setBonusSTR(int bonusSTR)
	{
		this.bonusSTR = bonusSTR;
	}

	public int getBonusSTA()
	{
		return bonusSTA;
	}

	public void setBonusSTA(int bonusSTA)
	{
		this.bonusSTA = bonusSTA;
	}

	public int getBonusDEX()
	{
		return bonusDEX;
	}

	public void setBonusDEX(int bonusDEX)
	{
		this.bonusDEX = bonusDEX;
	}

	public int getBonusAGI()
	{
		return bonusAGI;
	}

	public void setBonusAGI(int bonusAGI)
	{
		this.bonusAGI = bonusAGI;
	}

	public int getBonusCON()
	{
		return bonusCON;
	}

	public void setBonusCON(int bonusCON)
	{
		this.bonusCON = bonusCON;
	}

	public int getBonusINT()
	{
		return bonusINT;
	}

	public void setBonusINT(int bonusINT)
	{
		this.bonusINT = bonusINT;
	}

	public int getEffectType()
	{
		return effectType;
	}

	public void setEffectType(int effectType)
	{
		this.effectType = effectType;
	}
}