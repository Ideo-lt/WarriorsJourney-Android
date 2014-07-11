package lt.warriorsJourney.common.entity;

import lt.warriorsJourney.common.framework.Graphics;
import android.graphics.Rect;

public class Entity
{
	int centerX;
	int centerY;
	float speedX;
	int speedY;
	
	int id;
	int attackCooldown = 0;
	
	boolean dead = false;
	
	Rect body = new Rect(0,0,0,0);
	
	public void update()
	{
	}
	
	public void drow(Graphics g)
	{		
	}
	
	public int getCenterX()
	{
		return centerX;
	}
	
	public void setCenterX(int value)
	{
		centerX = value;
	}
	
	public int getCenterY()
	{
		return centerY;
	}
	
	public void setCenterY(int value)
	{
		centerY = value;
	}
	
	public int getSpeedX()
	{
		return centerX;
	}
	
	public void setSpeedX(int value)
	{
		speedX = value;
	}
	
	public int getSpeedY()
	{
		return centerX;
	}
	
	public void setSpeedY(int value)
	{
		speedY = value;
	}
	
	public int getId()
	{
		return id;
	}
	
	public void setId(int Id)
	{
		id = Id;
	}
	
	public int getAttackCooldown()
	{
		return attackCooldown;
	}
	
	public Rect getBodyRect()
	{
		return body;
	}
	
	public boolean isDead()
	{
		return dead;
	}
}
