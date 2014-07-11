package lt.warriorsJourney.common.world;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import lt.warriorsJourney.common.assets.Assets;
import lt.warriorsJourney.common.assets.Background;
import lt.warriorsJourney.common.entity.Entity;
import lt.warriorsJourney.common.entity.Player;
import lt.warriorsJourney.common.entity.skills.Projectile;
import lt.warriorsJourney.common.framework.Graphics;
import lt.warriorsJourney.common.screens.GameScreen;
import lt.warriorsJourney.common.world.particles.Particle;
import android.annotation.SuppressLint;

@SuppressLint("UseSparseArrays")
public class World
{
	private static WorldGen generator;
	private static Background bg1;
	private static Background bg2;
	private static Player player;
	
	
	private Map<Integer,Projectile> projectiles = new HashMap<Integer,Projectile>();
	private Map<Integer,Entity> entitys = new HashMap<Integer,Entity>();
	private Map<Integer,Particle> particles = new HashMap<Integer,Particle>();
	private List<Tile> map = new ArrayList<Tile>();

	private Set<Integer> removeProjIds = new HashSet<Integer>();
	private Set<Integer> removeEntityIds = new HashSet<Integer>();
	private Set<Integer> removeParticleIds = new HashSet<Integer>();

		
	private int difficulty;
	private int type;
	public int speedUp;
	public float speedUpTimer;
	
	public World(int difficulty,int type)
	{
		generator = new WorldGen();
		this.difficulty = difficulty;
		this.type = type;
		map = generator.getNewMap(difficulty,type);
		bg1 = GameScreen.getBg1();
		bg2 = GameScreen.getBg2();
		player = GameScreen.getPlayer();
		speedUp = 1;
	}
	
	public void updateWorld()
	{
		// ====== World Calculations ======
		
		bg1.update();
		bg2.update();
				
		if(player.isInAir())
		{
			bg1.setSpeedX(-12+speedUp);
			bg2.setSpeedX(-12+speedUp);
		}
		else
		{
			bg1.setSpeedX(-7+speedUp);
			bg2.setSpeedX(-7+speedUp);
		}
		
		if(map.get(map.size()-1).getTileX() < Assets.midScreenX*2+516)
			map = generator.getNewMap(difficulty,type);
			
		for(int i = 0;i< map.size();i++)
			map.get(i).update();
			
		//===== Entity Calculations ======
		
		Iterator<Integer> idIt = entitys.keySet().iterator();
		
		for(int i = 0;i<entitys.size();i++)	
		{
			int id = idIt.next();
			entitys.get(id).update();
			
			if(entitys.get(id).isDead())
				removeEntityIds.add(id);
		}
		
		if(removeEntityIds.size() < 0)
		{
			for(Iterator<Integer> idIter = removeEntityIds.iterator();idIter.hasNext(); )
			{
				int id = idIter.next();
				removeEntityById(entitys.get(id).getId());
				idIter.remove();
			}
		}
		
		removeEntityIds.clear();
	
		//===== Projectile Calculations =====
		
		idIt = projectiles.keySet().iterator();
		
		for(int i = 0;i<projectiles.size();i++)
		{
			int id = idIt.next();
			projectiles.get(id).update();
			
			if(projectiles.get(id).needsUnregister())
				removeProjIds.add(id);
		}
		
		if(removeProjIds.size() != 0)
		{
			for(Iterator<Integer> idIter = removeProjIds.iterator();idIter.hasNext(); )
			{
				int id = idIter.next();
				removeProjectileById(projectiles.get(id).getId());
				idIter.remove();
			}
		}
		
		removeProjIds.clear();
		
		// ===== Particle Calculations =====
		
		idIt = particles.keySet().iterator();
		
		for(int i = 0;i<particles.size();i++)
		{
			int id = idIt.next();
			particles.get(id).update();
			
			if(particles.get(id).needsUnregister())
				removeParticleIds.add(id);
		}
		
		if(removeParticleIds.size() != 0)
		{
			for(Iterator<Integer> idIter = removeParticleIds.iterator();idIter.hasNext(); )
			{
				int id = idIter.next();
				removeParticleById(particles.get(id).getId());
				idIter.remove();
			}
		}
		
		removeParticleIds.clear();
	}
	
	public void paintWorld(Graphics g) 
	{
		g.drawScaledImage(Assets.mainMenuBg, bg1.getBgX(), bg2.getBgY(),g.getWidth(),g.getHeight(),0,0,256,140);
		g.drawScaledImage(Assets.mainMenuBg, bg2.getBgX(),bg2.getBgY(),g.getWidth(),g.getHeight(),0,0,256,140);
		
		Iterator<Integer> idIt = entitys.keySet().iterator();
		
		for(int i = 0;i<entitys.size();i++)
			entitys.get(idIt.next()).drow(g);
		
		idIt = projectiles.keySet().iterator();

		for(int i = 0;i<projectiles.size();i++)
			projectiles.get(idIt.next()).drow(g);
			
		idIt = particles.keySet().iterator();
		
		for(int i= 0 ;i<particles.size();i++)
			particles.get(idIt.next()).drow(g);
		
		for (int i = 0; i < map.size(); i++) 
		{
			Tile t = map.get(i);
			if (t.type != 0) 
			{
				g.drawImage(t.getTileImage(), t.getTileX(), t.getTileY());
			}
		}
	}
	
	public void setBgSpeeds(int value)
	{
		bg1.setSpeedX(value);
		bg2.setSpeedX(value);
	}
	
	public int getDifficulty()
	{
		return difficulty;
	}
	
	public void registerProjectile(Projectile prod)
	{
		prod.setId(projectiles.size());
		projectiles.put(projectiles.size(),prod);
	}
	
	public void removeProjectileById(int id)
	{
		projectiles.remove(id);
	}
	
	public Map<Integer,Projectile> getProjectiles()
	{
		return projectiles;
	}
	
	public void registerEntity(Entity entity)
	{
		entity.setId(entitys.size());
		entitys.put(entitys.size(),entity);
	}
	
	public void removeEntityById(int Id)
	{
		entitys.remove(Id);
	}
	
	public Map<Integer,Entity> getEntitys()
	{
		return entitys;
	}
	
	public void registerParticle(Particle particle)
	{
		particle.setId(particles.size());
		particles.put(particles.size(),particle);
	}
	
	public void removeParticleById(int Id)
	{
		particles.remove(Id);
	}
	
	public Map<Integer,Particle> getParticles()
	{
		return particles;
	}

	public int getType()
	{
		return type;
	}
}
