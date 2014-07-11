package lt.warriorsJourney.common.entity;

import lt.warriorsJourney.common.assets.Animation;
import lt.warriorsJourney.common.assets.Assets;
import lt.warriorsJourney.common.assets.Background;
import lt.warriorsJourney.common.framework.Graphics;
import lt.warriorsJourney.common.framework.Image;
import lt.warriorsJourney.common.screens.GameScreen;
import lt.warriorsJourney.common.world.World;
import android.graphics.Rect;

public class Bat extends Entity
{
	/*private boolean isAttacking = false;
	private boolean isStanding = false;
	private boolean isHit = false;
	
	private int expDrop;*/
	
	public Rect body = new Rect(0,0,0,0);
	
	public Animation animStand,animAttack;
	private Image currentSprite;

	Background bg =	GameScreen.getBg1();
	World world = GameScreen.getworld();
	Player player = GameScreen.getPlayer();
	
	public Bat()
	{
		//expDrop = world.getDifficulty()*3;
		
		animStand = new Animation();
		animAttack = new Animation();
		
		/*animStand.addFrame(Assets.batStand1, 100);
		animStand.addFrame(Assets.batStand2, 100);
		animStand.addFrame(Assets.batStand3, 100);
		animStand.addFrame(Assets.batStand4, 100);
						
		animAttack.addFrame(Assets.batAtk1, 60);
		animAttack.addFrame(Assets.batAtk2, 60);
		animAttack.addFrame(Assets.batAtk3, 60);
		animAttack.addFrame(Assets.batAtk4, 60);
		animAttack.addFrame(Assets.batAtk5, 60);
		animAttack.addFrame(Assets.batAtk6, 60);
*/
	}
	
	@Override
	public void update()
	{
		animate(2);
		currentSprite = animAttack.getImage();
	}

	@Override
	public void drow(Graphics g)
	{
		g.drawScaledImage(currentSprite, Assets.midScreenX, Assets.midScreenY, 124, 124, 0, 0, 31, 31);
	}

	public Image getCurrentSprite()
	{
		return currentSprite;
	}
	
	public void animate(int using)
	{
		if(using == 1)
			animStand.update(15);
		else
			animAttack.update(8);
	}
}