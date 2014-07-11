package lt.warriorsJourney.common.entity;

import lt.warriorsJourney.common.assets.Animation;
import lt.warriorsJourney.common.assets.Assets;
import lt.warriorsJourney.common.assets.Background;
import lt.warriorsJourney.common.database.PlayerData;
import lt.warriorsJourney.common.entity.skills.Projectile;
import lt.warriorsJourney.common.framework.Graphics;
import lt.warriorsJourney.common.framework.Image;
import lt.warriorsJourney.common.screens.GameScreen;
import lt.warriorsJourney.common.screens.GameScreen.GameState;
import android.graphics.Rect;

public class Player extends Entity
{
	public PlayerData playerData;
	private Background bg = GameScreen.getBg1();
	private GameScreen game;
	
	private boolean running = false;
	private boolean jumping = false;
	private boolean falling = false;
	private boolean attacking = false;
	
	private boolean inAir = false;
	private boolean stoped = false;
	private int maxLifes;
	private int lifesLeft;
	
	public int jumpCount = 0;
	
	public Rect head = new Rect(0,0,0,0);
	public Rect feet = new Rect(0,0,0,0);
	public Rect leftSide = new Rect(0,0,0,0);
	public Rect rightSide = new Rect(0,0,0,0);

	private Animation animRun,animAtk;
	private Image currentSprite;
	private int usingAnim = 1;
	
	public int lastAction = 0;
	public float distance = 0;
	private float extraSpeedX = 0;
	private boolean fired = false;
	
	public int collectedCoins = 0;
	
	public Player(GameScreen gameScreen)
	{
		playerData = new PlayerData(gameScreen.getGame().getContext());
		
		centerX = 250;
		centerY = Assets.midScreenY*2-230; 
		
		maxLifes = 100+(playerData.getStamina()*20-20);
		lifesLeft = maxLifes;
		
		inAir = true;
		game = gameScreen;
		
		animRun = new Animation();
		animRun.addFrame(Assets.pcRun1, 100);
		animRun.addFrame(Assets.pcRun2, 100);
		animRun.addFrame(Assets.pcRun3, 100);
		animRun.addFrame(Assets.pcRun4, 100);
		animRun.addFrame(Assets.pcRun5, 100);
		animRun.addFrame(Assets.pcRun6, 100);
		animRun.addFrame(Assets.pcRun7, 100);
		animRun.addFrame(Assets.pcRun8, 100);

		animAtk = new Animation();
		animAtk.addFrame(Assets.pcAtk1, 60);
		animAtk.addFrame(Assets.pcAtk2, 60);
		animAtk.addFrame(Assets.pcAtk3, 60);
		animAtk.addFrame(Assets.pcAtk4, 60);
		animAtk.addFrame(Assets.pcAtk5, 60);
		animAtk.addFrame(Assets.pcAtk6, 60);
	}
	
	@Override
	public void update()
	{
		//===== Movement/Impact calculation ======
		
		body.set(centerX-56,centerY-76,centerX+56,centerY+76);
		head.set(centerX-10, centerY-76, centerX+10, centerY);
		feet.set(centerX-10, centerY, centerX+10, centerY+76);
		leftSide.set(centerX-56,centerY-10,centerX,centerY+10);
		rightSide.set(centerX,centerY-10,centerX+56,centerY+10);
		
		speedX = bg.getSpeedX();
		
		if(stoped)
			centerX += speedX;
		else if(centerX <250)
			centerX += extraSpeedX;

		
		
		if (speedY > 0)
			inAir = true;

		if(stoped)
			speedX -= 4;
		else if(centerX < 250)
			extraSpeedX += 0.005+(playerData.getDexterity()*0.001-0.001);
		else
			extraSpeedX = 0;
			
		if(inAir)
		{
			jumping = true;
			running = false;
			
			if(speedY < 0)
			{
				jumping = false;
				falling = true;
			}
			
			this.speedY -= 2;
			this.centerY -= this.speedY;
		}
		
		// ===== Attack calculations =====
		
		if(attackCooldown >= 110-((playerData.getDexterity()*3)-3)&&!fired)
		{
			GameScreen.getworld().registerProjectile(new Projectile(30+((playerData.getStrenght()-1)*15),0,this));
			fired = true;
		}
		if(attacking && attackCooldown < 150-(playerData.getDexterity()*3-3))
			attackCooldown += 10;
		else 
		{
			attackCooldown = 0;
			attacking = false;
			fired = false;
		}
		
		//======	Death Events	=====
		
		if(Assets.midScreenY*6 - centerY*2 < Assets.midScreenY*2 || centerX < -100)
			game.setGameState(GameState.GAMEOVER);
		
		if(lifesLeft <= 0)
			game.setGameState(GameState.GAMEOVER);
		
		//======	Animation Handling	=====
		if(jumping)
			currentSprite = Assets.pcJump;
		
		if(falling)
			currentSprite = Assets.pcFall;
		
		if(running && !attacking)
		{
			usingAnim = 1;
			currentSprite = animRun.getImage();
			animate(usingAnim);
		}
		
		if(attacking)
		{
			usingAnim = 2;
			currentSprite = animAtk.getImage();
			animate(usingAnim);
		}
		
		if(lifesLeft < maxLifes)
		lifesLeft += 0.003+((playerData.getIntelligence()-1)*0.003);
	}
	
	@Override
	public void drow(Graphics g)
	{
		if(!attacking)
			g.drawScaledImage(currentSprite,centerX-100,centerY-92,200,192,0,0,50,46);
		else
			g.drawScaledImage(currentSprite, centerX-100, centerY-92, 320, 192, 0, 0, 80, 46);
	}
	
	public boolean isJumping()
	{
		return jumping;
	}
	
	public void setJumping(boolean value)
	{
		jumping = value;
	}
	
	public boolean isRunning()
	{
		return running;
	}
	
	public void setRunning(boolean value)
	{
		running = value;
	}
	
	public boolean isAttacking()
	{
		return attacking;
	}
	
	public void setAttacking(boolean value)
	{
		attacking = value;
	}
	
	public boolean isFalling()
	{
		return falling;
	}
	
	public void setFalling(boolean value)
	{
		falling = value;
	}
	
	public void setInAir(boolean value)
	{
		inAir = value;
	}
	
	public boolean isInAir()
	{
		return inAir;
	}
	
	public boolean isStoped()
	{
		return stoped;
	}
	
	public void setStoped(boolean value)
	{
		stoped = value;
	}

	public void removeLifes(int i)
	{
		lifesLeft -= i;
	}
	
	public void addLifes(int i)
	{
		lifesLeft += i;
	}
	
	public void animate(int using) {
		
		switch(using)
		{
			case 1 :
			{
				animRun.update(20);
				break;
			}
			case 2	:
			{
				animAtk.update(12);
				break;
			}
		}
	}
	
	public Image getCurrentSprite()
	{
		return currentSprite;
	}
}
