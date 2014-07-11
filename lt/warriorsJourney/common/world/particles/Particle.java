package lt.warriorsJourney.common.world.particles;

import lt.warriorsJourney.common.framework.Graphics;
import lt.warriorsJourney.common.framework.Image;

public class Particle
{
	int id;
	int centerX;
	int centerY;
	int speedX;
	int speedY;
	int type;	
	boolean unregister = false;
	int timeToFall = 0;
	Image currentSprite;
	
	public void update()
	{
		
	}
	
	public void drow(Graphics g)
	{
		
	}
	
	public boolean needsUnregister()
	{
		return true;
	}
	
	public int getCenterY()
	{
		return centerY;
	}
	
	public void setCenterY(int centerY)
	{
		this.centerY = centerY;
	}
	
	public int getCenterX()
	{
		return centerX;
	}
	
	public void setCenterX(int centerX)
	{
		this.centerX = centerX;
	}
	
	public int getSpeedX()
	{
		return speedX;
	}
	
	public void setSpeedX(int speedX)
	{
		this.speedX = speedX;
	}
	
	public int getSpeedY()
	{
		return speedY;
	}
	
	public void setSpeedY(int seedY)
	{
		this.speedY = seedY;
	}
	
	public void setId(int id)
	{
		this.id = id;
	}
	
	public int getId()
	{
		return id;
	}
}
