package lt.warriorsJourney.common.world;

import java.util.Iterator;
import java.util.Map;

import lt.warriorsJourney.common.assets.Assets;
import lt.warriorsJourney.common.assets.Background;
import lt.warriorsJourney.common.entity.Player;
import lt.warriorsJourney.common.entity.skills.Projectile;
import lt.warriorsJourney.common.framework.Image;
import lt.warriorsJourney.common.screens.GameScreen;
import lt.warriorsJourney.common.world.particles.Coin;
import lt.warriorsJourney.common.world.particles.FallingParticle;
import android.graphics.Rect;

public class Tile
{
	private int tileX, tileY, speedX;
	public int type; //0 Air/1 ground/2 Spike/
	public Image tileImage;

	private Player player = GameScreen.getPlayer();
	private Background bg = GameScreen.getBg1();
	
	private Rect rOut,rIn;//for outer and inner checks (ex.: spikes needs to touch there middle to get impailed, but only the top to start falling)
	private boolean speedReset = false;
	private boolean destroyable;
	private int lifes = 100;
	
	public Tile(int x, int y, int typeInt,boolean destroyable) 
	{	
		tileX = x * 256;
		tileY = Assets.midScreenY*2 - y * 128;
		type = typeInt;
		rOut = new Rect();
		rIn = new Rect();
		
		switch(type)
		{
			case 0:
				break;
			case 1:
				tileImage = Assets.groundTile;
				break;
			case 2:
				tileImage = Assets.spikeTile;
				break;
			case 3:
				tileImage = Assets.unbreakableTile;
		}
		
		this.destroyable = destroyable;		
	
		if(type == 3)
			destroyable = false;
	}

	public void update() 
	{
		speedX = bg.getSpeedX();
		tileX += speedX;
		
		if(type == 0||type == 2)
			rOut.set(tileX, tileY-20, tileX+256,tileY+128);
		else
			rOut.set(tileX, tileY, tileX+256,tileY+128);
		
		if(type == 2)
			rIn.set(tileX,tileY+40,tileX+256,tileY+128);

		checkPlayer(player.head,player.feet,player.leftSide,player.rightSide);
		player.distance += 0.03;
		
		if(GameScreen.getworld().speedUpTimer > 1000 && GameScreen.getworld().speedUp < 1)
		{
			GameScreen.getworld().speedUpTimer = 0;
			GameScreen.getworld().speedUp -=1;
		}
		else
			GameScreen.getworld().speedUpTimer += 0.03;
	
		Map<Integer,Projectile> projectiles = GameScreen.getworld().getProjectiles();
		Iterator<Integer> idIt =  projectiles.keySet().iterator();
		
		for(int i = 0;i < projectiles.size();i++)
			checkProjectile(projectiles.get(idIt.next()));
	}

	public int getTileX()
	{
		return tileX;
	}

	public void setTileX(int tileX)
	{
		this.tileX = tileX;
	}

	public int getTileY() {
		return tileY;
	}

	public void setTileY(int tileY) {
		this.tileY = tileY;
	}

	public Image getTileImage() {
		return tileImage;
	}

	public void setTileImage(Image tileImage) {
		this.tileImage = tileImage;
	}

	public void checkPlayer(Rect body,Rect feet,Rect left,Rect right)
	{
		if(Rect.intersects(feet, rOut) && (type == 0||type == 2)&& !player.isInAir())
		{
			player.setInAir(true);
			
			if(!speedReset)
			{
				player.setSpeedY(-2);
				speedReset = true;
			}
		}
		
		if ((Rect.intersects(feet, rOut) && !Rect.intersects(right, rOut))&& (type == 1||type ==3))
		{
			speedReset = false;
			player.jumpCount = 0;
			player.setInAir(false);
			player.setFalling(false);
			player.setRunning(true);
			player.setSpeedY(0);
			player.setCenterY(tileY-92);
		}
		
		if(Rect.intersects(right, rOut)&& type == 1)
			player.setStoped(true);
				
		if(Rect.intersects(body, rIn)&& type == 2)
			player.removeLifes(2);
	}
	
	public void checkProjectile(Projectile proj)
	{
		if(Rect.intersects(proj.body, rOut) && (type != 0))
		{
			if(GameScreen.getworld().getType() !=2)
			{
				proj.impact = true;
				lifes -= proj.damage;
						
				if(destroyable&&lifes <=100)
				{
					if(GameScreen.getworld().getType() !=2)
						callDestroy(true);
					else
						callDestroy(false);
				}
			}
			else
			{
				proj.impact = true;

				if(destroyable && type !=3)
				{
					callDestroy(false);
				}
			}
		}
	}
	
	public void callDestroy(boolean dropCoins)
	{
		if(dropCoins)
		{
			GameScreen.getworld().registerParticle(new Coin(tileX+43,tileY));
			GameScreen.getworld().registerParticle(new Coin(tileX+129,tileY));
			GameScreen.getworld().registerParticle(new Coin(tileX+215,tileY));
		}
		GameScreen.getworld().registerParticle(new FallingParticle(tileX,tileY,type,1));
		GameScreen.getworld().registerParticle(new FallingParticle(tileX,tileY,type,2));
		GameScreen.getworld().registerParticle(new FallingParticle(tileX,tileY,type,3));
		
		player.playerData.increaseExp(15*type);
		
		type = 0;
	}
	
}


