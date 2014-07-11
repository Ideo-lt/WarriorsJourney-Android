package lt.warriorsJourney.common.entity.skills;

import java.util.Iterator;
import java.util.Map;

import lt.warriorsJourney.common.assets.Animation;
import lt.warriorsJourney.common.assets.Assets;
import lt.warriorsJourney.common.entity.Entity;
import lt.warriorsJourney.common.framework.Graphics;
import lt.warriorsJourney.common.framework.Image;
import lt.warriorsJourney.common.screens.GameScreen;
import android.graphics.Rect;

public class Projectile
{
	private int speedX;
	private int speedY;
	private int centerX;
	private int centerY;
	private Entity owner;
	private Animation explode = new Animation();
	private Image currentSprite;//galima bus sudet animacijas svitejimo etc..
	
	public boolean impact = false;
	public int destrTimer = 0;

	public int damage;
	public int cost;
	
	public Rect body = new Rect(0,0,0,0);
	
	private int id;
	
	public Projectile(int damage,int cost,Entity owner)
	{
		this.damage = damage;
		this.cost = cost;
		this.owner = owner;
		
		centerX = owner.getCenterX()+30;
		centerY = owner.getCenterY();
		
		speedY = 0;
		speedX = -(GameScreen.getBg1().getSpeedX()-15);
		
		currentSprite = Assets.projectile;
		
		//===== Animation adding =====
		explode.addFrame(Assets.proExpl1, 60);
		explode.addFrame(Assets.proExpl2, 60);
		explode.addFrame(Assets.proExpl3, 60);
		explode.addFrame(Assets.proExpl4, 60);
		explode.addFrame(Assets.proExpl5, 60);
	}
	
	public void update()
	{
		body.set(centerX-35, centerY-24, centerX+35, centerY+24);
		
		if(!impact)
			centerX += speedX;
	
		if(impact)
		{
			if(destrTimer == 0)
				centerX = centerX -35;
			else
				centerX += GameScreen.getBg1().getSpeedX(); 
			
			animate();			
			currentSprite = explode.getImage();
			
			destrTimer += 15;
		}
		else
			currentSprite = Assets.projectile;
		
		if(centerX-35 > Assets.midScreenX*2)
			destrTimer = 300;
					
	}
	
	public void drow(Graphics g)
	{
		g.drawScaledImage(currentSprite, centerX-35, centerY-24,80,60,0,0,36,25);
	}
	
	public void checkEntityColisions()
	{
		Map<Integer,Entity> entitys = GameScreen.getworld().getEntitys();
		Iterator<Integer> idIt = entitys.keySet().iterator();
		
		for(int i = 0;i<entitys.size();i++)
		{
			int id = idIt.next();
			
			if(Rect.intersects(entitys.get(id).getBodyRect(), body) &&(entitys.get(id) != owner))
			{
				System.out.println("Boom bitch! :D");
			}
		}
	}
	
	public void setSpeedY(int speed)
	{
		speedY = speed;
	}
	
	public void setSpeedX(int speed)
	{
		speedX = speed;
	}
	
	public int getSpeedX()
	{
		return speedX;
	}
	
	public int getSpeedY()
	{
		return speedY;
	}
	
	public int getCoordX()
	{
		return centerX;
	}
	
	public int getCoordY()
	{
		return centerY;
	}
	
	public Image getCurrentSprite()
	{
		return currentSprite;
	}

	public void setId(int Id)
	{
		id = Id;
	}
	
	public int getId()
	{
		return id;
	}
	
	public void animate()
	{
		explode.update(15);
	}
	
	public boolean needsUnregister()
	{
		if(destrTimer >= 300)
			return true;
		else
			return false;
	}
	
}
