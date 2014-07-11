package lt.warriorsJourney.common.world;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import lt.warriorsJourney.common.screens.GameScreen;
import lt.warriorsJourney.common.world.particles.Coin;

public class WorldGen
{
	
	Random rnd;
	boolean isStart = true;
	private List<Tile> generatedMap = new ArrayList<Tile>();
	private List<Tile> savedPart = new ArrayList<Tile>();
	World world;
	
	public WorldGen()
	{
		rnd = new Random();
		world = GameScreen.getworld();
	}
	
	private List<Tile> generateTileSet(int difficulty,int type)
	{
		if(!isStart)
		{	
			for(int i = 0;i<8;i++)
				savedPart.add(generatedMap.get(generatedMap.size()-8+i));
			
			generatedMap.clear();
		}
		
		if(isStart)
		{	
			if(type == 1)
				for(int i = 0;i<8;i++)
					generatedMap.add(new Tile(i,1,1,true));
			else
				for(int i =0;i<8;i++)
					generatedMap.add(new Tile(i,1,3,true));			
			isStart = false;
			
			return generatedMap;
		}
		else
		{
			for(int i = 0;i<8;i++)
				generatedMap.add(savedPart.get(savedPart.size()-8+i));
			
			for(int i = 0;i<5;i++)
				addObsticle(rnd.nextInt(10),generatedMap,difficulty,type);			
		}
		return generatedMap;
	}
	
	public List<Tile> getNewMap(int difficulty,int type)
	{
		return generateTileSet(difficulty,type);
	}
	
	private void addObsticle(int i,List<Tile> map,int difficulty,int type)
	{
		if(type ==1)
		{
			if(difficulty == 1)
			{	
				if(i == 0)
				{
					map.add(new Tile(map.size(),1,1,true));
					GameScreen.getworld().registerParticle(new Coin(map.size()*256+43,2*128));
					GameScreen.getworld().registerParticle(new Coin(map.size()*256+129,2*128));
					GameScreen.getworld().registerParticle(new Coin(map.size()*256+215,2*128));
					map.add(new Tile(map.size(),2,0,true));
					map.add(new Tile(map.size(),2,1,true));
					GameScreen.getworld().registerParticle(new Coin(map.size()*256+43,2*128));
					GameScreen.getworld().registerParticle(new Coin(map.size()*256+129,2*128));
					GameScreen.getworld().registerParticle(new Coin(map.size()*256+215,2*128));
					map.add(new Tile(map.size(),2,0,true));
					map.add(new Tile(map.size(),2,1,true));
					GameScreen.getworld().registerParticle(new Coin(map.size()*256+43,2*128));
					GameScreen.getworld().registerParticle(new Coin(map.size()*256+129,2*128));
					GameScreen.getworld().registerParticle(new Coin(map.size()*256+215,2*128));
					map.add(new Tile(map.size(),2,0,true));
					map.add(new Tile(map.size(),1,1,true));
				}
				if(i == 1)
				{
					map.add(new Tile(map.size(),1,1,true));
					GameScreen.getworld().registerParticle(new Coin(map.size()*256+43,2*128));
					GameScreen.getworld().registerParticle(new Coin(map.size()*256+129,2*128));
					GameScreen.getworld().registerParticle(new Coin(map.size()*256+215,2*128));
					map.add(new Tile(map.size(),2,2,true));
					map.add(new Tile(map.size(),1,1,true));
					GameScreen.getworld().registerParticle(new Coin(map.size()*256+43,2*128));
					GameScreen.getworld().registerParticle(new Coin(map.size()*256+129,2*128));
					GameScreen.getworld().registerParticle(new Coin(map.size()*256+215,2*128));
					map.add(new Tile(map.size(),2,2,true));
					map.add(new Tile(map.size(),1,1,true));
				}
				if(i == 2)
				{
					map.add(new Tile(map.size(),1,1,true));
					GameScreen.getworld().registerParticle(new Coin(map.size()*256+43,2*128));
					GameScreen.getworld().registerParticle(new Coin(map.size()*256+129,2*128));
					GameScreen.getworld().registerParticle(new Coin(map.size()*256+215,2*128));
					map.add(new Tile(map.size(),2,0,true));
					GameScreen.getworld().registerParticle(new Coin(map.size()*256+43,2*128));
					GameScreen.getworld().registerParticle(new Coin(map.size()*256+129,2*128));
					GameScreen.getworld().registerParticle(new Coin(map.size()*256+215,2*128));
					map.add(new Tile(map.size(),2,0,true));
					map.add(new Tile(map.size(),2,1,true));
					GameScreen.getworld().registerParticle(new Coin(map.size()*256+43,2*128));
					GameScreen.getworld().registerParticle(new Coin(map.size()*256+129,2*128));
					GameScreen.getworld().registerParticle(new Coin(map.size()*256+215,2*128));
					map.add(new Tile(map.size(),2,0,true));
					GameScreen.getworld().registerParticle(new Coin(map.size()*256+43,2*128));
					GameScreen.getworld().registerParticle(new Coin(map.size()*256+129,2*128));
					GameScreen.getworld().registerParticle(new Coin(map.size()*256+215,2*128));
					map.add(new Tile(map.size(),2,0,true));
					map.add(new Tile(map.size(),1,1,true));
				}
				if(i == 3)
				{
					map.add(new Tile(map.size(),1,1,true));
					GameScreen.getworld().registerParticle(new Coin(map.size()*256+43,1*128));
					GameScreen.getworld().registerParticle(new Coin(map.size()*256+129,1*128));
					GameScreen.getworld().registerParticle(new Coin(map.size()*256+215,1*128));
					map.add(new Tile(map.size(),1,0,true));
					map.add(new Tile(map.size(),2,2,true));
					GameScreen.getworld().registerParticle(new Coin(map.size()*256+43,1*128));
					GameScreen.getworld().registerParticle(new Coin(map.size()*256+129,1*128));
					GameScreen.getworld().registerParticle(new Coin(map.size()*256+215,1*128));
					map.add(new Tile(map.size(),1,0,true));
					map.add(new Tile(map.size(),1,1,true));
				}
				if(i == 4)
				{
					map.add(new Tile(map.size(),1,1,true));
					map.add(new Tile(map.size(),2,1,true));
					map.add(new Tile(map.size(),3,1,true));
					GameScreen.getworld().registerParticle(new Coin(map.size()*256+43,3*128));
					GameScreen.getworld().registerParticle(new Coin(map.size()*256+129,3*128));
					GameScreen.getworld().registerParticle(new Coin(map.size()*256+215,3*128));
					map.add(new Tile(map.size(),3,0,true));
					map.add(new Tile(map.size(),1,1,true));
				}
				if(i == 5)
				{
					map.add(new Tile(map.size(),1,1,true));
					GameScreen.getworld().registerParticle(new Coin(map.size()*256+43,2*128));
					GameScreen.getworld().registerParticle(new Coin(map.size()*256+129,2*128));
					GameScreen.getworld().registerParticle(new Coin(map.size()*256+215,2*128));
					map.add(new Tile(map.size(),2,0,true));
					map.add(new Tile(map.size(),2,1,true));
					map.add(new Tile(map.size(),2,2,true));
					map.add(new Tile(map.size(),2,1,true));
					GameScreen.getworld().registerParticle(new Coin(map.size()*256+43,2*128));
					GameScreen.getworld().registerParticle(new Coin(map.size()*256+129,2*128));
					GameScreen.getworld().registerParticle(new Coin(map.size()*256+215,2*128));
					map.add(new Tile(map.size(),2,0,true));
					map.add(new Tile(map.size(),1,1,true));
				}
				if(i == 6)
				{
					map.add(new Tile(map.size(),1,1,true));
					GameScreen.getworld().registerParticle(new Coin(map.size()*256+43,2*128));
					GameScreen.getworld().registerParticle(new Coin(map.size()*256+129,2*128));
					GameScreen.getworld().registerParticle(new Coin(map.size()*256+215,2*128));
					map.add(new Tile(map.size(),2,0,true));
					map.add(new Tile(map.size(),2,1,true));
					GameScreen.getworld().registerParticle(new Coin(map.size()*256+43,3*128));
					GameScreen.getworld().registerParticle(new Coin(map.size()*256+129,3*128));
					GameScreen.getworld().registerParticle(new Coin(map.size()*256+215,3*128));
					map.add(new Tile(map.size(),3,2,true));
					GameScreen.getworld().registerParticle(new Coin(map.size()*256+43,2*128));
					GameScreen.getworld().registerParticle(new Coin(map.size()*256+129,2*128));
					GameScreen.getworld().registerParticle(new Coin(map.size()*256+215,2*128));
					map.add(new Tile(map.size(),2,2,true));
					GameScreen.getworld().registerParticle(new Coin(map.size()*256+43,2*128));
					GameScreen.getworld().registerParticle(new Coin(map.size()*256+129,2*128));
					GameScreen.getworld().registerParticle(new Coin(map.size()*256+215,2*128));
					map.add(new Tile(map.size(),2,0,true));
					map.add(new Tile(map.size(),1,1,true));
				}
				if(i == 7)
				{
					map.add(new Tile(map.size(),1,1,true));
					GameScreen.getworld().registerParticle(new Coin(map.size()*256+43,2*128));
					GameScreen.getworld().registerParticle(new Coin(map.size()*256+129,2*128));
					GameScreen.getworld().registerParticle(new Coin(map.size()*256+215,2*128));
					map.add(new Tile(map.size(),2,0,true));
					map.add(new Tile(map.size(),2,1,true));
					map.add(new Tile(map.size(),3,2,true));
					map.add(new Tile(map.size(),2,1,true));
					GameScreen.getworld().registerParticle(new Coin(map.size()*256+43,2*128));
					GameScreen.getworld().registerParticle(new Coin(map.size()*256+129,2*128));
					GameScreen.getworld().registerParticle(new Coin(map.size()*256+215,2*128));
					map.add(new Tile(map.size(),2,0,true));
					map.add(new Tile(map.size(),1,1,true));
				}
				if(i == 8)
				{
					map.add(new Tile(map.size(),1,1,true));
					GameScreen.getworld().registerParticle(new Coin(map.size()*256+43,1*128));
					GameScreen.getworld().registerParticle(new Coin(map.size()*256+129,1*128));
					GameScreen.getworld().registerParticle(new Coin(map.size()*256+215,1*128));
					map.add(new Tile(map.size(),1,2,true));
					GameScreen.getworld().registerParticle(new Coin(map.size()*256+43,1*128));
					GameScreen.getworld().registerParticle(new Coin(map.size()*256+129,1*128));
					GameScreen.getworld().registerParticle(new Coin(map.size()*256+215,1*128));
					map.add(new Tile(map.size(),1,2,true));
					map.add(new Tile(map.size(),1,1,true));
					GameScreen.getworld().registerParticle(new Coin(map.size()*256+43,1*128));
					GameScreen.getworld().registerParticle(new Coin(map.size()*256+129,1*128));
					GameScreen.getworld().registerParticle(new Coin(map.size()*256+215,1*128));
					map.add(new Tile(map.size(),1,2,true));
					GameScreen.getworld().registerParticle(new Coin(map.size()*256+43,1*128));
					GameScreen.getworld().registerParticle(new Coin(map.size()*256+129,1*128));
					GameScreen.getworld().registerParticle(new Coin(map.size()*256+215,1*128));
					map.add(new Tile(map.size(),1,2,true));
					map.add(new Tile(map.size(),1,1,true));
				}
				if(i == 9)
				{
					map.add(new Tile(map.size(),1,1,true));
					GameScreen.getworld().registerParticle(new Coin(map.size()*256+43,2*128));
					GameScreen.getworld().registerParticle(new Coin(map.size()*256+129,2*128));
					GameScreen.getworld().registerParticle(new Coin(map.size()*256+215,2*128));
					map.add(new Tile(map.size(),2,2,true));
					map.add(new Tile(map.size(),1,1,true));
					map.add(new Tile(map.size(),2,1,true));
					GameScreen.getworld().registerParticle(new Coin(map.size()*256+43,2*128));
					GameScreen.getworld().registerParticle(new Coin(map.size()*256+129,2*128));
					GameScreen.getworld().registerParticle(new Coin(map.size()*256+215,2*128));
					map.add(new Tile(map.size(),2,0,true));
					map.add(new Tile(map.size(),2,1,true));
					GameScreen.getworld().registerParticle(new Coin(map.size()*256+43,2*128));
					GameScreen.getworld().registerParticle(new Coin(map.size()*256+129,2*128));
					GameScreen.getworld().registerParticle(new Coin(map.size()*256+215,2*128));
					map.add(new Tile(map.size(),2,2,true));
					map.add(new Tile(map.size(),1,1,true));
				}
			}
			else if(difficulty == 2)
			{
				if(i == 0)
				{
					map.add(new Tile(map.size(),1,1,true));
					GameScreen.getworld().registerParticle(new Coin(map.size()*256+43,2*128));
					GameScreen.getworld().registerParticle(new Coin(map.size()*256+129,2*128));
					GameScreen.getworld().registerParticle(new Coin(map.size()*256+215,2*128));
					map.add(new Tile(map.size(),2,0,true));
					map.add(new Tile(map.size(),2,1,true));
					GameScreen.getworld().registerParticle(new Coin(map.size()*256+43,2*128));
					GameScreen.getworld().registerParticle(new Coin(map.size()*256+129,2*128));
					GameScreen.getworld().registerParticle(new Coin(map.size()*256+215,2*128));
					map.add(new Tile(map.size(),2,0,true));
					GameScreen.getworld().registerParticle(new Coin(map.size()*256+43,2*128));
					GameScreen.getworld().registerParticle(new Coin(map.size()*256+129,2*128));
					GameScreen.getworld().registerParticle(new Coin(map.size()*256+215,2*128));
					map.add(new Tile(map.size(),2,2,true));
					GameScreen.getworld().registerParticle(new Coin(map.size()*256+43,2*128));
					GameScreen.getworld().registerParticle(new Coin(map.size()*256+129,2*128));
					GameScreen.getworld().registerParticle(new Coin(map.size()*256+215,2*128));
					map.add(new Tile(map.size(),2,0,true));
					map.add(new Tile(map.size(),2,1,true));
					GameScreen.getworld().registerParticle(new Coin(map.size()*256+43,2*128));
					GameScreen.getworld().registerParticle(new Coin(map.size()*256+129,2*128));
					GameScreen.getworld().registerParticle(new Coin(map.size()*256+215,2*128));
					map.add(new Tile(map.size(),2,0,true));
					map.add(new Tile(map.size(),1,1,true));
				}
				if(i == 1)
				{
					map.add(new Tile(map.size(),1,1,true));
					GameScreen.getworld().registerParticle(new Coin(map.size()*256+43,2*128));
					GameScreen.getworld().registerParticle(new Coin(map.size()*256+129,2*128));
					GameScreen.getworld().registerParticle(new Coin(map.size()*256+215,2*128));
					map.add(new Tile(map.size(),2,0,true));
					map.add(new Tile(map.size(),2,1,true));
					GameScreen.getworld().registerParticle(new Coin(map.size()*256+43,2*128));
					GameScreen.getworld().registerParticle(new Coin(map.size()*256+129,2*128));
					GameScreen.getworld().registerParticle(new Coin(map.size()*256+215,2*128));
					map.add(new Tile(map.size(),2,2,true));
					map.add(new Tile(map.size(),2,1,true));
					GameScreen.getworld().registerParticle(new Coin(map.size()*256+43,2*128));
					GameScreen.getworld().registerParticle(new Coin(map.size()*256+129,2*128));
					GameScreen.getworld().registerParticle(new Coin(map.size()*256+215,2*128));
					map.add(new Tile(map.size(),2,0,true));
					map.add(new Tile(map.size(),1,1,true));
				}
				if(i == 2)
				{
					map.add(new Tile(map.size(),1,1,true));
					GameScreen.getworld().registerParticle(new Coin(map.size()*256+43,2*128));
					GameScreen.getworld().registerParticle(new Coin(map.size()*256+129,2*128));
					GameScreen.getworld().registerParticle(new Coin(map.size()*256+215,2*128));
					map.add(new Tile(map.size(),2,0,true));
					GameScreen.getworld().registerParticle(new Coin(map.size()*256+43,2*128));
					GameScreen.getworld().registerParticle(new Coin(map.size()*256+129,2*128));
					GameScreen.getworld().registerParticle(new Coin(map.size()*256+215,2*128));
					map.add(new Tile(map.size(),2,0,true));
					map.add(new Tile(map.size(),2,1,true));
					GameScreen.getworld().registerParticle(new Coin(map.size()*256+43,2*128));
					GameScreen.getworld().registerParticle(new Coin(map.size()*256+129,2*128));
					GameScreen.getworld().registerParticle(new Coin(map.size()*256+215,2*128));
					map.add(new Tile(map.size(),2,0,true));
					GameScreen.getworld().registerParticle(new Coin(map.size()*256+43,2*128));
					GameScreen.getworld().registerParticle(new Coin(map.size()*256+129,2*128));
					GameScreen.getworld().registerParticle(new Coin(map.size()*256+215,2*128));
					map.add(new Tile(map.size(),2,0,true));
					map.add(new Tile(map.size(),1,1,true));
				}
				if(i == 3)
				{
					map.add(new Tile(map.size(),1,1,true));
					map.add(new Tile(map.size(),2,1,true));
					GameScreen.getworld().registerParticle(new Coin(map.size()*256+43,1*128));
					GameScreen.getworld().registerParticle(new Coin(map.size()*256+129,1*128));
					GameScreen.getworld().registerParticle(new Coin(map.size()*256+215,1*128));
					map.add(new Tile(map.size(),1,2,true));
					GameScreen.getworld().registerParticle(new Coin(map.size()*256+43,1*128));
					GameScreen.getworld().registerParticle(new Coin(map.size()*256+129,1*128));
					GameScreen.getworld().registerParticle(new Coin(map.size()*256+215,1*128));
					map.add(new Tile(map.size(),1,2,true));
					GameScreen.getworld().registerParticle(new Coin(map.size()*256+43,1*128));
					GameScreen.getworld().registerParticle(new Coin(map.size()*256+129,1*128));
					GameScreen.getworld().registerParticle(new Coin(map.size()*256+215,1*128));
					map.add(new Tile(map.size(),1,2,true));
					map.add(new Tile(map.size(),2,1,true));
					map.add(new Tile(map.size(),1,1,true));
				}
				if(i == 4)
				{
					map.add(new Tile(map.size(),1,1,true));
					GameScreen.getworld().registerParticle(new Coin(map.size()*256+43,2*128));
					GameScreen.getworld().registerParticle(new Coin(map.size()*256+129,2*128));
					GameScreen.getworld().registerParticle(new Coin(map.size()*256+215,2*128));
					map.add(new Tile(map.size(),2,2,true));
					map.add(new Tile(map.size(),1,1,true));
					map.add(new Tile(map.size(),1,1,true));
					GameScreen.getworld().registerParticle(new Coin(map.size()*256+43,2*128));
					GameScreen.getworld().registerParticle(new Coin(map.size()*256+129,2*128));
					GameScreen.getworld().registerParticle(new Coin(map.size()*256+215,2*128));
					map.add(new Tile(map.size(),2,2,true));
					map.add(new Tile(map.size(),1,1,true));
				}
				if(i == 5)
				{
					map.add(new Tile(map.size(),1,1,true));
					GameScreen.getworld().registerParticle(new Coin(map.size()*256+43,2*128));
					GameScreen.getworld().registerParticle(new Coin(map.size()*256+129,2*128));
					GameScreen.getworld().registerParticle(new Coin(map.size()*256+215,2*128));
					map.add(new Tile(map.size(),2,0,true));
					map.add(new Tile(map.size(),2,1,true));
					GameScreen.getworld().registerParticle(new Coin(map.size()*256+43,1*128));
					GameScreen.getworld().registerParticle(new Coin(map.size()*256+129,1*128));
					GameScreen.getworld().registerParticle(new Coin(map.size()*256+215,1*128));
					map.add(new Tile(map.size(),1,2,true));
					GameScreen.getworld().registerParticle(new Coin(map.size()*256+43,1*128));
					GameScreen.getworld().registerParticle(new Coin(map.size()*256+129,1*128));
					GameScreen.getworld().registerParticle(new Coin(map.size()*256+215,1*128));
					map.add(new Tile(map.size(),1,2,true));
					map.add(new Tile(map.size(),2,1,true));
					GameScreen.getworld().registerParticle(new Coin(map.size()*256+43,2*128));
					GameScreen.getworld().registerParticle(new Coin(map.size()*256+129,2*128));
					GameScreen.getworld().registerParticle(new Coin(map.size()*256+215,2*128));
					map.add(new Tile(map.size(),2,0,true));
					map.add(new Tile(map.size(),1,1,true));
				}
				if(i == 6)
				{
					map.add(new Tile(map.size(),1,1,true));
					GameScreen.getworld().registerParticle(new Coin(map.size()*256+43,2*128));
					GameScreen.getworld().registerParticle(new Coin(map.size()*256+129,2*128));
					GameScreen.getworld().registerParticle(new Coin(map.size()*256+215,2*128));
					map.add(new Tile(map.size(),2,0,true));
					map.add(new Tile(map.size(),2,1,true));
					GameScreen.getworld().registerParticle(new Coin(map.size()*256+43,2*128));
					GameScreen.getworld().registerParticle(new Coin(map.size()*256+129,2*128));
					GameScreen.getworld().registerParticle(new Coin(map.size()*256+215,2*128));
					map.add(new Tile(map.size(),2,0,true));
					GameScreen.getworld().registerParticle(new Coin(map.size()*256+43,1*128));
					GameScreen.getworld().registerParticle(new Coin(map.size()*256+129,1*128));
					GameScreen.getworld().registerParticle(new Coin(map.size()*256+215,1*128));
					map.add(new Tile(map.size(),1,2,true));
					GameScreen.getworld().registerParticle(new Coin(map.size()*256+43,2*128));
					GameScreen.getworld().registerParticle(new Coin(map.size()*256+129,2*128));
					GameScreen.getworld().registerParticle(new Coin(map.size()*256+215,2*128));
					map.add(new Tile(map.size(),2,0,true));
					map.add(new Tile(map.size(),2,1,true));
					GameScreen.getworld().registerParticle(new Coin(map.size()*256+43,3*128));
					GameScreen.getworld().registerParticle(new Coin(map.size()*256+129,2*128));
					GameScreen.getworld().registerParticle(new Coin(map.size()*256+215,2*128));
					map.add(new Tile(map.size(),2,0,true));
					map.add(new Tile(map.size(),1,1,true));
				}
				if(i == 7)
				{
					map.add(new Tile(map.size(),1,1,true));
					GameScreen.getworld().registerParticle(new Coin(map.size()*256+43,1*128));
					GameScreen.getworld().registerParticle(new Coin(map.size()*256+129,1*128));
					GameScreen.getworld().registerParticle(new Coin(map.size()*256+215,1*128));
					map.add(new Tile(map.size(),1,0,true));
					map.add(new Tile(map.size(),2,1,true));
					GameScreen.getworld().registerParticle(new Coin(map.size()*256+43,2*128));
					GameScreen.getworld().registerParticle(new Coin(map.size()*256+129,2*128));
					GameScreen.getworld().registerParticle(new Coin(map.size()*256+215,2*128));
					map.add(new Tile(map.size(),2,0,true));
					map.add(new Tile(map.size(),1,1,true));
					GameScreen.getworld().registerParticle(new Coin(map.size()*256+43,2*128));
					GameScreen.getworld().registerParticle(new Coin(map.size()*256+129,2*128));
					GameScreen.getworld().registerParticle(new Coin(map.size()*256+215,2*128));
					map.add(new Tile(map.size(),2,0,true));
					map.add(new Tile(map.size(),2,1,true));
					GameScreen.getworld().registerParticle(new Coin(map.size()*256+43,2*128));
					GameScreen.getworld().registerParticle(new Coin(map.size()*256+129,2*128));
					GameScreen.getworld().registerParticle(new Coin(map.size()*256+215,2*128));
					map.add(new Tile(map.size(),2,0,true));
					map.add(new Tile(map.size(),1,1,true));
				}
				if(i == 8)
				{
					map.add(new Tile(map.size(),1,1,true));
					GameScreen.getworld().registerParticle(new Coin(map.size()*256+43,1*128));
					GameScreen.getworld().registerParticle(new Coin(map.size()*256+129,1*128));
					GameScreen.getworld().registerParticle(new Coin(map.size()*256+215,1*128));
					map.add(new Tile(map.size(),1,2,true));
					GameScreen.getworld().registerParticle(new Coin(map.size()*256+43,1*128));
					GameScreen.getworld().registerParticle(new Coin(map.size()*256+129,1*128));
					GameScreen.getworld().registerParticle(new Coin(map.size()*256+215,1*128));
					map.add(new Tile(map.size(),1,2,true));
					map.add(new Tile(map.size(),1,1,true));
					GameScreen.getworld().registerParticle(new Coin(map.size()*256+43,1*128));
					GameScreen.getworld().registerParticle(new Coin(map.size()*256+129,1*128));
					GameScreen.getworld().registerParticle(new Coin(map.size()*256+215,1*128));
					map.add(new Tile(map.size(),1,2,true));
					GameScreen.getworld().registerParticle(new Coin(map.size()*256+43,1*128));
					GameScreen.getworld().registerParticle(new Coin(map.size()*256+129,1*128));
					GameScreen.getworld().registerParticle(new Coin(map.size()*256+215,1*128));
					map.add(new Tile(map.size(),1,2,true));
					map.add(new Tile(map.size(),1,1,true));
				}
				if(i == 9)
				{
					map.add(new Tile(map.size(),1,1,true));
					GameScreen.getworld().registerParticle(new Coin(map.size()*256+43,2*128));
					GameScreen.getworld().registerParticle(new Coin(map.size()*256+129,2*128));
					GameScreen.getworld().registerParticle(new Coin(map.size()*256+215,2*128));
					map.add(new Tile(map.size(),2,2,true));
					map.add(new Tile(map.size(),1,1,true));
					GameScreen.getworld().registerParticle(new Coin(map.size()*256+43,2*128));
					GameScreen.getworld().registerParticle(new Coin(map.size()*256+129,2*128));
					GameScreen.getworld().registerParticle(new Coin(map.size()*256+215,2*128));
					map.add(new Tile(map.size(),2,2,true));
					map.add(new Tile(map.size(),1,1,true));
					map.add(new Tile(map.size(),2,1,true));
					GameScreen.getworld().registerParticle(new Coin(map.size()*256+43,2*128));
					GameScreen.getworld().registerParticle(new Coin(map.size()*256+129,2*128));
					GameScreen.getworld().registerParticle(new Coin(map.size()*256+215,2*128));
					map.add(new Tile(map.size(),2,2,true));
					map.add(new Tile(map.size(),2,1,true));
					GameScreen.getworld().registerParticle(new Coin(map.size()*256+43,2*128));
					GameScreen.getworld().registerParticle(new Coin(map.size()*256+129,2*128));
					GameScreen.getworld().registerParticle(new Coin(map.size()*256+215,2*128));
					map.add(new Tile(map.size(),2,2,true));
					map.add(new Tile(map.size(),1,1,true));
				}
			}
		}
		else if (type == 2)
		{
			if(difficulty == 1)
			{	
				if(i == 0)
				{
					map.add(new Tile(map.size(),1,3,true));
					map.add(new Tile(map.size(),2,0,true));
					map.add(new Tile(map.size(),2,3,true));
					map.add(new Tile(map.size(),2,0,true));
					map.add(new Tile(map.size(),2,3,true));
					map.add(new Tile(map.size(),2,0,true));
					map.add(new Tile(map.size(),1,3,true));
				}
				if(i == 1)
				{
					map.add(new Tile(map.size(),1,3,true));
					map.add(new Tile(map.size(),2,2,true));
					map.add(new Tile(map.size(),1,3,true));
					map.add(new Tile(map.size(),2,2,true));
					map.add(new Tile(map.size(),1,3,true));
				}
				if(i == 2)
				{
					map.add(new Tile(map.size(),1,3,true));
					map.add(new Tile(map.size(),2,0,true));
					map.add(new Tile(map.size(),2,0,true));
					map.add(new Tile(map.size(),2,3,true));
					map.add(new Tile(map.size(),2,0,true));
					map.add(new Tile(map.size(),2,0,true));
					map.add(new Tile(map.size(),1,3,true));
				}
				if(i == 3)
				{
					map.add(new Tile(map.size(),1,3,true));
					map.add(new Tile(map.size(),1,0,true));
					map.add(new Tile(map.size(),2,2,true));
					map.add(new Tile(map.size(),1,0,true));
					map.add(new Tile(map.size(),1,3,true));
				}
				if(i == 4)
				{
					map.add(new Tile(map.size(),1,3,true));
					map.add(new Tile(map.size(),2,3,true));
					map.add(new Tile(map.size(),3,3,true));
					map.add(new Tile(map.size(),3,0,true));
					map.add(new Tile(map.size(),1,3,true));
				}
				if(i == 5)
				{
					map.add(new Tile(map.size(),1,3,true));
					map.add(new Tile(map.size(),2,0,true));
					map.add(new Tile(map.size(),2,3,true));
					map.add(new Tile(map.size(),2,2,true));
					map.add(new Tile(map.size(),2,3,true));
					map.add(new Tile(map.size(),2,0,true));
					map.add(new Tile(map.size(),1,3,true));
				}
				if(i == 6)
				{
					map.add(new Tile(map.size(),1,3,true));
					map.add(new Tile(map.size(),2,0,true));
					map.add(new Tile(map.size(),2,3,true));
					map.add(new Tile(map.size(),3,2,true));
					map.add(new Tile(map.size(),2,2,true));
					map.add(new Tile(map.size(),2,0,true));
					map.add(new Tile(map.size(),1,3,true));
				}
				if(i == 7)
				{
					map.add(new Tile(map.size(),1,3,true));
					map.add(new Tile(map.size(),2,0,true));
					map.add(new Tile(map.size(),2,3,true));
					map.add(new Tile(map.size(),3,2,true));
					map.add(new Tile(map.size(),2,3,true));
					map.add(new Tile(map.size(),2,0,true));
					map.add(new Tile(map.size(),1,3,true));
				}
				if(i == 8)
				{
					map.add(new Tile(map.size(),1,3,true));
					map.add(new Tile(map.size(),1,2,true));
					map.add(new Tile(map.size(),1,2,true));
					map.add(new Tile(map.size(),1,3,true));
					map.add(new Tile(map.size(),1,2,true));
					map.add(new Tile(map.size(),1,2,true));
					map.add(new Tile(map.size(),1,3,true));
				}
				if(i == 9)
				{
					map.add(new Tile(map.size(),1,3,true));
					map.add(new Tile(map.size(),2,2,true));
					map.add(new Tile(map.size(),1,3,true));
					map.add(new Tile(map.size(),2,3,true));
					map.add(new Tile(map.size(),2,0,true));
					map.add(new Tile(map.size(),2,3,true));
					map.add(new Tile(map.size(),2,2,true));
					map.add(new Tile(map.size(),1,3,true));
				}
			}
			else if(difficulty == 2)
			{
				if(i == 0)
				{
					map.add(new Tile(map.size(),1,3,true));
					map.add(new Tile(map.size(),2,0,true));
					map.add(new Tile(map.size(),2,3,true));
					map.add(new Tile(map.size(),2,0,true));
					map.add(new Tile(map.size(),2,2,true));
					map.add(new Tile(map.size(),2,0,true));
					map.add(new Tile(map.size(),2,3,true));
					map.add(new Tile(map.size(),2,0,true));
					map.add(new Tile(map.size(),1,3,true));
				}
				if(i == 1)
				{
					map.add(new Tile(map.size(),1,3,true));
					map.add(new Tile(map.size(),2,0,true));
					map.add(new Tile(map.size(),2,3,true));
					map.add(new Tile(map.size(),2,2,true));
					map.add(new Tile(map.size(),2,3,true));
					map.add(new Tile(map.size(),2,0,true));
					map.add(new Tile(map.size(),1,3,true));
				}
				if(i == 2)
				{
					map.add(new Tile(map.size(),1,3,true));
					map.add(new Tile(map.size(),2,0,true));
					map.add(new Tile(map.size(),2,0,true));
					map.add(new Tile(map.size(),2,3,true));
					map.add(new Tile(map.size(),2,0,true));
					map.add(new Tile(map.size(),2,0,true));
					map.add(new Tile(map.size(),1,3,true));
				}
				if(i == 3)
				{
					map.add(new Tile(map.size(),1,3,true));
					map.add(new Tile(map.size(),2,3,true));
					map.add(new Tile(map.size(),1,2,true));
					map.add(new Tile(map.size(),1,2,true));
					map.add(new Tile(map.size(),1,2,true));
					map.add(new Tile(map.size(),2,3,true));
					map.add(new Tile(map.size(),1,3,true));
				}
				if(i == 4)
				{
					map.add(new Tile(map.size(),1,3,true));
					map.add(new Tile(map.size(),2,2,true));
					map.add(new Tile(map.size(),1,3,true));
					map.add(new Tile(map.size(),1,3,true));
					map.add(new Tile(map.size(),2,2,true));
					map.add(new Tile(map.size(),1,3,true));
				}
				if(i == 5)
				{
					map.add(new Tile(map.size(),1,3,true));
					map.add(new Tile(map.size(),2,0,true));
					map.add(new Tile(map.size(),2,3,true));
					map.add(new Tile(map.size(),1,2,true));
					map.add(new Tile(map.size(),1,2,true));
					map.add(new Tile(map.size(),2,3,true));
					map.add(new Tile(map.size(),2,0,true));
					map.add(new Tile(map.size(),1,3,true));
				}
				if(i == 6)
				{
					map.add(new Tile(map.size(),1,3,true));
					map.add(new Tile(map.size(),2,0,true));
					map.add(new Tile(map.size(),2,3,true));
					map.add(new Tile(map.size(),2,0,true));
					map.add(new Tile(map.size(),1,2,true));
					map.add(new Tile(map.size(),2,0,true));
					map.add(new Tile(map.size(),2,3,true));
					map.add(new Tile(map.size(),2,0,true));
					map.add(new Tile(map.size(),1,3,true));
				}
				if(i == 7)
				{
					map.add(new Tile(map.size(),1,3,true));
					map.add(new Tile(map.size(),1,0,true));
					map.add(new Tile(map.size(),2,3,true));
					map.add(new Tile(map.size(),2,0,true));
					map.add(new Tile(map.size(),1,3,true));
					map.add(new Tile(map.size(),2,0,true));
					map.add(new Tile(map.size(),2,3,true));
					map.add(new Tile(map.size(),2,0,true));
					map.add(new Tile(map.size(),1,3,true));
				}
				if(i == 8)
				{
					map.add(new Tile(map.size(),1,3,true));
					map.add(new Tile(map.size(),1,2,true));
					map.add(new Tile(map.size(),1,2,true));
					map.add(new Tile(map.size(),1,3,true));
					map.add(new Tile(map.size(),1,2,true));
					map.add(new Tile(map.size(),1,2,true));
					map.add(new Tile(map.size(),1,3,true));
				}
				if(i == 9)
				{
					map.add(new Tile(map.size(),1,3,true));
					map.add(new Tile(map.size(),2,2,true));
					map.add(new Tile(map.size(),1,3,true));
					map.add(new Tile(map.size(),2,2,true));
					map.add(new Tile(map.size(),1,3,true));
					map.add(new Tile(map.size(),2,3,true));
					map.add(new Tile(map.size(),2,2,true));
					map.add(new Tile(map.size(),2,3,true));
					map.add(new Tile(map.size(),2,2,true));
					map.add(new Tile(map.size(),1,3,true));
				}
			}
		}
	}
}