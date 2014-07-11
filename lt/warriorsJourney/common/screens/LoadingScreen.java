package lt.warriorsJourney.common.screens;

import lt.warriorsJourney.common.assets.Assets;
import lt.warriorsJourney.common.files.ItemReader;
import lt.warriorsJourney.common.files.LevelReader;
import lt.warriorsJourney.common.framework.Game;
import lt.warriorsJourney.common.framework.Graphics;
import lt.warriorsJourney.common.framework.Graphics.ImageFormat;
import lt.warriorsJourney.common.framework.Screen;
import android.graphics.Paint;

public class LoadingScreen extends Screen
{

	public LoadingScreen(Game game)
	{
		super(game);
		Assets.levels = new LevelReader(game);
		Assets.items = new ItemReader(game);
	}

	@Override
	public void update(float deltaTime)
	{
		Graphics g = game.getGraphics();
		
		Assets.midScreenX = g.getWidth()/2;
		Assets.midScreenY = g.getHeight()/2;
		
		Assets.mainMenuBg = g.newImage("MenuBg.png", ImageFormat.ARGB4444);
		Assets.pauseButton = g.newImage("pause.png", ImageFormat.ARGB4444);
		Assets.smallButton = g.newImage("smallButton.png", ImageFormat.ARGB4444);
		Assets.smallButtonOff = g.newImage("smallButtonOff.png", ImageFormat.ARGB4444);
		Assets.addStatOn = g.newImage("AddStatOn.png", ImageFormat.ARGB4444);
		Assets.addStatOff = g.newImage("AddStatOff.png", ImageFormat.ARGB4444);
		Assets.button = g.newImage("Button.png", ImageFormat.ARGB8888);
		Assets.menuShader = g.newImage("MenuShader.png", ImageFormat.ARGB8888);
		Assets.backButton = g.newImage("BackButton.png", ImageFormat.ARGB8888);

		Assets.InvSlotOn = g.newImage("InvSlotON.png", ImageFormat.ARGB8888);
		Assets.InvSlotOff = g.newImage("InvSlotOff.png", ImageFormat.ARGB8888);
		Assets.InvArrowUp = g.newImage("arrowUp.png", ImageFormat.ARGB8888);
		Assets.InvArrowDown = g.newImage("arrowDown.png", ImageFormat.ARGB8888);
		Assets.InvDescription = g.newImage("description.png", ImageFormat.ARGB8888);
		
		Assets.groundTile = g.newImage("groundTile.png", ImageFormat.ARGB4444);
		Assets.spikeTile = g.newImage("spikeTile.png", ImageFormat.ARGB4444);
		Assets.unbreakableTile = g.newImage("unreakableTile.png", ImageFormat.ARGB4444);

		Assets.spikeTileBreak = g.newImage("spikeTileBreak.png", ImageFormat.ARGB4444);
		Assets.groundTileBreak = g.newImage("groundTileBreak.png", ImageFormat.ARGB4444);
		Assets.projectile = g.newImage("projectile.png", ImageFormat.ARGB4444);
		
		Assets.proExpl1 = g.newImage("Pro_1_1.png", ImageFormat.ARGB4444);
		Assets.proExpl2 = g.newImage("Pro_1_2.png", ImageFormat.ARGB4444);
		Assets.proExpl3 = g.newImage("Pro_1_3.png", ImageFormat.ARGB4444);
		Assets.proExpl4 = g.newImage("Pro_1_4.png", ImageFormat.ARGB4444);
		Assets.proExpl5 = g.newImage("Pro_1_5.png", ImageFormat.ARGB4444);
		
		Assets.pcRun1 = g.newImage("PC_1_1.png", ImageFormat.ARGB4444);
		Assets.pcRun2 = g.newImage("PC_1_2.png", ImageFormat.ARGB4444);
		Assets.pcRun3 = g.newImage("PC_1_3.png", ImageFormat.ARGB4444);
		Assets.pcRun4 = g.newImage("PC_1_4.png", ImageFormat.ARGB4444);
		Assets.pcRun5 = g.newImage("PC_1_5.png", ImageFormat.ARGB4444);
		Assets.pcRun6 = g.newImage("PC_1_6.png", ImageFormat.ARGB4444);
		Assets.pcRun7 = g.newImage("PC_1_7.png", ImageFormat.ARGB4444);
		Assets.pcRun8 = g.newImage("PC_1_8.png", ImageFormat.ARGB4444);

		Assets.pcJump = g.newImage("PC_2_3.png", ImageFormat.ARGB4444);
		Assets.pcFall = g.newImage("PC_2_4.png", ImageFormat.ARGB4444);

		Assets.pcAtk1 = g.newImage("PC_4_1.png", ImageFormat.ARGB4444);
		Assets.pcAtk2 = g.newImage("PC_4_2.png", ImageFormat.ARGB4444);
		Assets.pcAtk3 = g.newImage("PC_4_3.png", ImageFormat.ARGB4444);
		Assets.pcAtk4 = g.newImage("PC_4_4.png", ImageFormat.ARGB4444);
		Assets.pcAtk5 = g.newImage("PC_4_5.png", ImageFormat.ARGB4444);
		Assets.pcAtk6 = g.newImage("PC_4_6.png", ImageFormat.ARGB4444);
		
		Assets.coin = g.newImage("coin.png", ImageFormat.ARGB4444);
		
		/*Assets.batStand1 = g.newImage("BAT_1_1.png", ImageFormat.ARGB4444);
		Assets.batStand2 = g.newImage("BAT_1_2.png", ImageFormat.ARGB4444);
		Assets.batStand3 = g.newImage("BAT_1_3.png", ImageFormat.ARGB4444);
		Assets.batStand4 = g.newImage("BAT_1_4.png", ImageFormat.ARGB4444);
		
		Assets.batAtk1 = g.newImage("BAT_2_1.png", ImageFormat.ARGB4444);
		Assets.batAtk2 = g.newImage("BAT_2_2.png", ImageFormat.ARGB4444);
		Assets.batAtk3 = g.newImage("BAT_2_3.png", ImageFormat.ARGB4444);
		Assets.batAtk4 = g.newImage("BAT_2_4.png", ImageFormat.ARGB4444);
		Assets.batAtk5 = g.newImage("BAT_2_5.png", ImageFormat.ARGB4444);
		Assets.batAtk6 = g.newImage("BAT_2_6.png", ImageFormat.ARGB4444);*/

				
		Assets.mMButton1 = "Start Game";
		Assets.mMButton2 = "Options";
		Assets.mMButton3 = "Credits";
		Assets.mMButton4 = "Exit";
		Assets.mMButton5 = "Score";
		Assets.mMButton6 = "Stats";
		Assets.mMButton7 = "Inventory";
		Assets.mMButton8 = "Shop";
		Assets.credits1 = " DEVELOPERS:";
		Assets.credits2 = "Ideo and Honor";
		Assets.credits3 = "MOST GRAPHIC:";
	    Assets.credits4 = "Jetrel at:";
	    Assets.credits5 = "openGameArt.org";
	    Assets.credits6 = "AUDIO:";
	    Assets.credits7 = "'Overworld' by ";
	    Assets.credits8 = "knarmahfox at:";
	    Assets.credits9 = "freesound.org";
	    Assets.easy = "Easy";
	    Assets.medium = "Medium";
	    Assets.exit1 = "Do you really want to exit ?";
	    Assets.exit2 = "Yes                       NO";
 	
		setPaint();
		
		game.setScreen(new MainMenuScreen(game));
	}
	
	@Override
	public void paint(float deltaTime)
	{
		
	}
	
	public void setPaint()
	{
		Paint paint = new Paint();
		paint.setTextSize(40);
		paint.setColor(0xff331f1f);
		
		Assets.mainMenu = paint;
		
		Paint paint2 = new Paint();
		paint2.setColor(0);
		paint2.setTextSize(20);
		
		Assets.counter = paint2;
	}

	@Override
	public void pause()
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void resume()
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void dispose()
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void backButton()
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public int getDrowRate()
	{
		return 0;
	}

	@Override
	public int gerId()
	{
		return 1;
	}

}
