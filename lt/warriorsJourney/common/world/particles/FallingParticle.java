package lt.warriorsJourney.common.world.particles;

import lt.warriorsJourney.common.assets.Assets;
import lt.warriorsJourney.common.framework.Graphics;
import lt.warriorsJourney.common.screens.GameScreen;

public class FallingParticle extends Particle
{
	public FallingParticle(int x,int y,int type,int part)
	{
		this.type = type;
		
		if(type == 1)
			currentSprite = Assets.groundTileBreak;
		else if(type == 2)
			currentSprite = Assets.spikeTileBreak;
		
		
		centerX = x+86*(part-1);
		centerY = y-64;
		
		timeToFall = part*4;
	}
	
	@Override
	public void update()
	{
		speedX = GameScreen.getBg1().getSpeedX();
		centerX += speedX;
		
		if(timeToFall == 0)
		{
			centerY += speedY;
			speedY += 1;
		}
		else
			timeToFall -=0.05;
		
		if(centerY+currentSprite.getHeight()/2 > Assets.midScreenY*3)
			unregister = true;
		
	}
	
	@Override
	public void drow(Graphics g)
	{
		g.drawImage(currentSprite, centerX,centerY+64);
	}
	
	@Override
	public boolean needsUnregister()
	{
		return unregister;
	}
}
