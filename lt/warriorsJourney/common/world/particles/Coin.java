package lt.warriorsJourney.common.world.particles;

import java.util.Iterator;
import java.util.Map;

import lt.warriorsJourney.common.assets.Assets;
import lt.warriorsJourney.common.assets.Background;
import lt.warriorsJourney.common.entity.Entity;
import lt.warriorsJourney.common.entity.Player;
import lt.warriorsJourney.common.framework.Graphics;
import lt.warriorsJourney.common.framework.Image;
import lt.warriorsJourney.common.screens.GameScreen;
import android.graphics.Rect;

public class Coin extends Particle
{
	private Background bg = GameScreen.getBg1();
	private boolean collected = false;
	Rect body = new Rect(0,0,0,0);
	
	public Coin(int x,int y)
	{
		centerX = x -32;
		centerY = y -32;
	}
	
	@Override
	public void update()
	{
		body.set(centerX-30, centerY-30, centerX+30, centerY+30);
		
		speedX = bg.getSpeedX();
		centerX += speedX;
		
		checkCollisions();
		
	}
	
	@Override
	public void drow(Graphics g)
	{
		g.drawScaledImage(Assets.coin, centerX-32, centerY-32, 64, 64, 0, 0, 32, 32);
	}
	
	public void checkCollisions()
	{
		Map<Integer,Entity> entitys = GameScreen.getworld().getEntitys();
		Iterator<Integer> idIt = entitys.keySet().iterator();
		
		for(int i= 0 ;i<entitys.size();i++)
		{
			int id = idIt.next();
			Entity entity = entitys.get(id);
			
			if(entity instanceof Player)
			{	
				if(Rect.intersects(body, entity.getBodyRect()))
				{
					collected = true;
					((Player) entity).playerData.increaseBalance(1);
				}
			}
		}
	}
	
	@Override
	public boolean needsUnregister()
	{
		return collected;
	}
}
