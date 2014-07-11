package lt.warriorsJourney.common.screens;

import java.text.DecimalFormat;
import java.util.List;

import lt.warriorsJourney.common.assets.Assets;
import lt.warriorsJourney.common.database.PlayerData;
import lt.warriorsJourney.common.database.Score;
import lt.warriorsJourney.common.database.ScoreData;
import lt.warriorsJourney.common.entity.Player;
import lt.warriorsJourney.common.files.Item;
import lt.warriorsJourney.common.framework.Game;
import lt.warriorsJourney.common.framework.Graphics;
import lt.warriorsJourney.common.framework.Image;
import lt.warriorsJourney.common.world.World;
import android.graphics.Paint;

public class GuiHandler
{
	ScoreData score;
	Player player;
	World world;
	Game game;
	int fps,ups = 0;
	long timer = System.currentTimeMillis();
	PlayerData playerData;
	
	public GuiHandler(Game game)
	{
		score = new ScoreData(game.getContext());
		player = GameScreen.getPlayer();
		world = GameScreen.getworld();
		this.game = game;
		playerData = new PlayerData(game.getContext());
		
	}
	
	public void drowMainMenu(Graphics g)
	{
		g.drawImage(Assets.menuShader,Assets.midScreenX-521,Assets.midScreenY-320);
		
		//Start Game mygtukas 
		g.drawImage(Assets.button,Assets.midScreenX-224,Assets.midScreenY-248);
		g.drawString(Assets.mMButton1, Assets.midScreenX-95, Assets.midScreenY-194,Assets.mainMenu);
		
		//Score mygtukas
		g.drawImage(Assets.button, Assets.midScreenX-458, Assets.midScreenY-148);
		g.drawString(Assets.mMButton5, Assets.midScreenX-289, Assets.midScreenY-93, Assets.mainMenu);
		
		//Stats mygtukas
		g.drawImage(Assets.button, Assets.midScreenX+10, Assets.midScreenY-148);
		g.drawString(Assets.mMButton6, Assets.midScreenX+199, Assets.midScreenY-93, Assets.mainMenu);
		
		//invenotry mygtukas
		g.drawImage(Assets.button, Assets.midScreenX-458, Assets.midScreenY-48);
		g.drawString(Assets.mMButton7, Assets.midScreenX-315, Assets.midScreenY+7, Assets.mainMenu);
		
		//Shop mygtukas
		g.drawImage(Assets.button, Assets.midScreenX+10, Assets.midScreenY-48);
		g.drawString(Assets.mMButton8, Assets.midScreenX+199, Assets.midScreenY+7, Assets.mainMenu);
		
		//Options mygtukas
		g.drawImage(Assets.button,Assets.midScreenX-458,Assets.midScreenY+49);
		g.drawString(Assets.mMButton2, Assets.midScreenX-305, Assets.midScreenY+107,Assets.mainMenu);
				
		//Credits mygtukas
		g.drawImage(Assets.button,Assets.midScreenX+10,Assets.midScreenY+49);
		g.drawString(Assets.mMButton3, Assets.midScreenX+185, Assets.midScreenY+107,Assets.mainMenu);
				
		//Exit mygtukas
		g.drawImage(Assets.button,Assets.midScreenX-224,Assets.midScreenY+158);
		g.drawString(Assets.mMButton4, Assets.midScreenX-35, Assets.midScreenY+213,Assets.mainMenu);
		
	}
	
	public void drowStartGame(Graphics g)
	{
		g.drawImage(Assets.menuShader,Assets.midScreenX-521,Assets.midScreenY-320);
		g.drawScaledImage(Assets.backButton,Assets.midScreenX-90,Assets.midScreenY+180,448,86,0,0,80,16);
		
		//tutorial
		g.drawImage(Assets.button,Assets.midScreenX-224,Assets.midScreenY-248);
		g.drawString("Tutorial", Assets.midScreenX-63, Assets.midScreenY-194,Assets.mainMenu);
		
		
		g.drawImage(Assets.button,Assets.midScreenX-224,Assets.midScreenY-148);
		g.drawString(Assets.easy, Assets.midScreenX-40, Assets.midScreenY-90,Assets.mainMenu);

		g.drawImage(Assets.button,Assets.midScreenX-224,Assets.midScreenY-48);
		g.drawString(Assets.medium, Assets.midScreenX-63, Assets.midScreenY+10,Assets.mainMenu);
	
	}
	
	public void drowScore(Graphics g,int dispDiff,int type)
	{
		int i = 0;
		
		Paint paint = new Paint();
		paint.setColor(0xff331f1f);
		paint.setTextSize(45);
		
		g.drawImage(Assets.menuShader, Assets.midScreenX-521, Assets.midScreenY-320);
		
		if(dispDiff == 1)
		{
			g.drawImage(Assets.smallButton, Assets.midScreenX+180, Assets.midScreenY-256);
			g.drawString("Easy", Assets.midScreenX+240, Assets.midScreenY-196, paint);
			g.drawImage(Assets.smallButtonOff, Assets.midScreenX+180, Assets.midScreenY-162);
			g.drawString("Medium", Assets.midScreenX+210, Assets.midScreenY-102, paint);
		}
		else
		{
			g.drawImage(Assets.smallButtonOff, Assets.midScreenX+180, Assets.midScreenY-256);
			g.drawString("Easy", Assets.midScreenX+240, Assets.midScreenY-196, paint);
			g.drawImage(Assets.smallButton, Assets.midScreenX+180, Assets.midScreenY-162);
			g.drawString("Medium", Assets.midScreenX+210, Assets.midScreenY-102, paint);
		}
		
		g.drawScaledImage(Assets.backButton,Assets.midScreenX-90,Assets.midScreenY+180,448,86,0,0,80,16);
		
		List<Score> bestScores = score.getBestScores(dispDiff,type);
		
		if(bestScores.size() == 0)
		{
			for(i = 0;i<9;i++)
				g.drawString(i+1+"...............00 m", Assets.midScreenX-160, Assets.midScreenY-220+(i*45), paint);
		}
		else
		{
			for(i = 0;i<bestScores.size();i++)
			{
				String prefix = i+1+"";
						
				if(bestScores.get(i).getScore()>10000)
					prefix += "........";
				else if(bestScores.get(i).getScore() > 1000)
					prefix += "..........";
				else if(bestScores.get(i).getScore() > 100)
					prefix += ".............";
				
				DecimalFormat df = new DecimalFormat();
				df.setMaximumFractionDigits(0);
				g.drawString(prefix+df.format(bestScores.get(i).getScore())+" m", Assets.midScreenX-160, Assets.midScreenY-220+(i*45), paint);
			}
			if(i != 9)
				for(int x = i;x<9;x++)
				{
					g.drawString(x+1+"...............00 m", Assets.midScreenX-160, Assets.midScreenY-220+(x*45), paint);
				}
		}
	}
	
	public void drowStats(Graphics g)
	{
		g.drawImage(Assets.menuShader, Assets.midScreenX-521, Assets.midScreenY-320);
		g.drawString("Level: "+playerData.getLevel(), Assets.midScreenX-448,Assets.midScreenY-220, Assets.mainMenu);
		g.drawString("EXP: "+playerData.getExp(),Assets.midScreenX-448 , Assets.midScreenY-170, Assets.mainMenu);
		g.drawString("Points remaining: "+playerData.getUnspentPoints(), Assets.midScreenX-180, Assets.midScreenY-140, Assets.mainMenu);
		g.drawString("STR: "+playerData.getStrenght(), Assets.midScreenX-318, Assets.midScreenY-40, Assets.mainMenu);
		g.drawString("STA: "+playerData.getStamina(), Assets.midScreenX-318, Assets.midScreenY+50, Assets.mainMenu);
		g.drawString("DEX: "+playerData.getDexterity(), Assets.midScreenX-318, Assets.midScreenY+140, Assets.mainMenu);
		g.drawString("AGI: "+playerData.getAgility(), Assets.midScreenX+170, Assets.midScreenY-40, Assets.mainMenu);
		g.drawString("CON: "+playerData.getConcentration(), Assets.midScreenX+170, Assets.midScreenY+50, Assets.mainMenu);
		g.drawString("INT: "+playerData.getIntelligence(), Assets.midScreenX+170, Assets.midScreenY+140, Assets.mainMenu);
	
		if(playerData.getUnspentPoints() == 0)
		{
			g.drawImage(Assets.addStatOff, Assets.midScreenX-180, Assets.midScreenY-85);
			g.drawImage(Assets.addStatOff, Assets.midScreenX-180, Assets.midScreenY+5);
			g.drawImage(Assets.addStatOff, Assets.midScreenX-180, Assets.midScreenY+95);

			g.drawImage(Assets.addStatOff, Assets.midScreenX+310, Assets.midScreenY-85);
			g.drawImage(Assets.addStatOff, Assets.midScreenX+310, Assets.midScreenY+5);
			g.drawImage(Assets.addStatOff, Assets.midScreenX+310, Assets.midScreenY+95);
		}
		else
		{
			g.drawImage(Assets.addStatOn, Assets.midScreenX-180, Assets.midScreenY-85);
			g.drawImage(Assets.addStatOn, Assets.midScreenX-180, Assets.midScreenY+5);
			g.drawImage(Assets.addStatOn, Assets.midScreenX-180, Assets.midScreenY+95);

			g.drawImage(Assets.addStatOn, Assets.midScreenX+310, Assets.midScreenY-85);
			g.drawImage(Assets.addStatOn, Assets.midScreenX+310, Assets.midScreenY+5);
			g.drawImage(Assets.addStatOn, Assets.midScreenX+310, Assets.midScreenY+95);
		}
		
		g.drawImage(Assets.smallButton, Assets.midScreenX-362, Assets.midScreenY+200);
		g.drawString("Reset", Assets.midScreenX-300, Assets.midScreenY+260,Assets.mainMenu);
		g.drawImage(Assets.smallButton,Assets.midScreenX+140, Assets.midScreenY+200);
		g.drawString("Back", Assets.midScreenX+205, Assets.midScreenY+260, Assets.mainMenu);
		
		g.drawImage(Assets.smallButton, Assets.midScreenX+230, Assets.midScreenY-280);
		g.drawString("Info", Assets.midScreenX+310, Assets.midScreenY-220, Assets.mainMenu);

	}

	public void drowStatsInfo(Graphics g)
	{
		g.drawImage(Assets.menuShader, Assets.midScreenX-521, Assets.midScreenY-320);
		
		Paint paint = new Paint();
		paint.setColor(-1);
		paint.setTextSize(30);
		
		g.drawString("STR - Increases the damage you deal with skills", Assets.midScreenX-400, Assets.midScreenY-230, paint);
		g.drawString("STA - Increases your hit points", Assets.midScreenX-400, Assets.midScreenY-150, paint);
		g.drawString("DEX - Increases the speed you go back [ after being stoped ]", Assets.midScreenX-400, Assets.midScreenY-70, paint);
		g.drawString("AGI - Increases your jump", Assets.midScreenX-400, Assets.midScreenY+10, paint);
		g.drawString("CON - Decreases your attack reuse time", Assets.midScreenX-400, Assets.midScreenY+90, paint);
		g.drawString("INT - Increases your hit point regeneration", Assets.midScreenX-400, Assets.midScreenY+160, paint);
	}
	
	public void drowInventory(Graphics g,int selectedSlotRow,int selectedSlotColumn,int topRow,Item item)
	{
		int maxSlotsInRow = 3;
		int maxVisibleRows = 3;
		
		g.drawImage(Assets.menuShader, Assets.midScreenX-521, Assets.midScreenY-320);
		
		if(topRow !=1)
			selectedSlotRow -= topRow-1;
		
		for(int y = 1;y <= maxVisibleRows;y++)
		{
			for(int x = 1;x <= maxSlotsInRow;x++)
			{
							
				if(y == selectedSlotRow && x == selectedSlotColumn)
				{
					g.drawImage(Assets.InvSlotOn, Assets.midScreenX-435+((x-1)*128+2), Assets.midScreenY-200+((y-1)*128+2));
					System.out.println("selected "+selectedSlotRow);
				}
				else
					g.drawImage(Assets.InvSlotOff, Assets.midScreenX-435+((x-1)*128+2), Assets.midScreenY-200+((y-1)*128+2));
			}
		}
		
		
		g.drawScaledImage(Assets.InvArrowUp, Assets.midScreenX-35,Assets.midScreenY-198, 64, 128, 0, 0, 16, 32);
		g.drawScaledImage(Assets.InvArrowDown, Assets.midScreenX-35,Assets.midScreenY+56, 64, 128, 0, 0, 16, 32);
		
		g.drawImage(Assets.InvDescription, Assets.midScreenX+45, Assets.midScreenY-198);

		if(item != null)
		{
			Paint paint = new Paint();
			paint.setColor(0xff331f1f);
			paint.setTextSize(28);
			
			addItemName(g,item,Assets.InvDescription,-148,65,paint);
			addItemDescription(g,item,Assets.InvDescription,-98,65,paint);
		}
	}

	public void drowShop(Graphics g)
	{
		g.drawImage(Assets.menuShader, Assets.midScreenX-521, Assets.midScreenY-320);
		g.drawString("Coming soon, but", Assets.midScreenX-170, Assets.midScreenY-130, Assets.mainMenu);
		g.drawString("Collect those coins", Assets.midScreenX-180, Assets.midScreenY-70, Assets.mainMenu);
		g.drawString("Nice stuff will be available", Assets.midScreenX-230, Assets.midScreenY-10, Assets.mainMenu);
		g.drawString("Balance: "+playerData.getBalance(), Assets.midScreenX-170, Assets.midScreenY+50, Assets.mainMenu);
	}
	
	public void drowOptions(Graphics g)
	{
		g.drawImage(Assets.menuShader,Assets.midScreenX-521,Assets.midScreenY-320);
		g.drawScaledImage(Assets.backButton,Assets.midScreenX-90,Assets.midScreenY+180,448,86,0,0,80,16);
		g.drawString("None for now...", Assets.midScreenX-150, Assets.midScreenY-20, Assets.mainMenu);
	
	}
	
	public void drowCredits(Graphics g)
	{
		g.drawImage(Assets.menuShader,Assets.midScreenX-521,Assets.midScreenY-320);		
		g.drawScaledImage(Assets.backButton,Assets.midScreenX-90,Assets.midScreenY+180,448,86,0,0,80,16);
		g.drawString(Assets.credits1, Assets.midScreenX-130, Assets.midScreenY-240,Assets.mainMenu);
		g.drawString(Assets.credits2, Assets.midScreenX-150, Assets.midScreenY-200,Assets.mainMenu);
		g.drawString(Assets.credits3, Assets.midScreenX-155, Assets.midScreenY-130,Assets.mainMenu);
		g.drawString(Assets.credits4, Assets.midScreenX-100, Assets.midScreenY-90,Assets.mainMenu);
		g.drawString(Assets.credits5, Assets.midScreenX-165, Assets.midScreenY-55,Assets.mainMenu);
		g.drawString(Assets.credits6, Assets.midScreenX-70, Assets.midScreenY,Assets.mainMenu);
		g.drawString(Assets.credits7, Assets.midScreenX-135, Assets.midScreenY+40,Assets.mainMenu);
		g.drawString(Assets.credits8, Assets.midScreenX-135, Assets.midScreenY+80,Assets.mainMenu);
		g.drawString(Assets.credits9, Assets.midScreenX-135, Assets.midScreenY+120,Assets.mainMenu);

	}
		
	public void drowExit(Graphics g)
	{
		g.drawImage(Assets.menuShader,Assets.midScreenX-521,Assets.midScreenY-320);
		g.drawString(Assets.exit1, Assets.midScreenX-240, Assets.midScreenY-150,Assets.mainMenu);
		g.drawScaledImage(Assets.button,Assets.midScreenX-200,Assets.midScreenY-50,112,86,0,0,448,86);
		g.drawScaledImage(Assets.button,Assets.midScreenX+90,Assets.midScreenY-50,112,86,0,0,448,86);
		g.drawString(Assets.exit2, Assets.midScreenX-180, Assets.midScreenY+2,Assets.mainMenu);
	}
	
	public void drowTutorial(Graphics g)
	{
		g.drawImage(Assets.menuShader,Assets.midScreenX-521,Assets.midScreenY-320);
		
		Paint paint = new Paint();
		paint.setTextSize(37);
		paint.setColor(-1);
		g.drawString("The point of this game is to run ",Assets.midScreenX-330,Assets.midScreenY-250,paint);
		g.drawString("as far as posible.", Assets.midScreenX-330, Assets.midScreenY-220, paint);
		g.drawString("Don't get stabed by spikes",Assets.midScreenX-330,Assets.midScreenY-160,paint);
		g.drawString("and don't leave the screen.", Assets.midScreenX-330 , Assets.midScreenY-120, paint);
		g.drawString("The map is made up from obsticles",Assets.midScreenX-330,Assets.midScreenY-60,paint);
		g.drawString("that are recreated randomly.", Assets.midScreenX-330, Assets.midScreenY-20, paint);
		g.drawString("So the map is random each time.", Assets.midScreenX-330, Assets.midScreenY+20, paint);
		g.drawString("Tap the left side of the screan",Assets.midScreenX-330,Assets.midScreenY+80,paint);
		g.drawString("for jumping (you can double jump)", Assets.midScreenX-330, Assets.midScreenY+120, paint);
		g.drawString("Tap the right side of the screen",Assets.midScreenX-330,Assets.midScreenY+180,paint);
		g.drawString("for attacks", Assets.midScreenX-330, Assets.midScreenY+220, paint);
		g.drawString("Have Fun! [Developers]", Assets.midScreenX-330, Assets.midScreenY+270, paint);
	
	}
	
	public void drowGameOver(Graphics g)
	{
		g.drawARGB(150, 0, 0, 0);

		g.drawImage(Assets.menuShader,Assets.midScreenX-521,Assets.midScreenY-320);
		
		score.addScore(player.distance,world.getDifficulty(),world.getType());
		
		Paint paint = new Paint();
		paint.setColor(-1);
		paint.setTextSize(65);
		
		g.drawString("Game Over", Assets.midScreenX-160, Assets.midScreenY-230, paint);
		
		paint.setTextSize(60);
		g.drawString("Well...", Assets.midScreenX-75, Assets.midScreenY-155, paint);
		g.drawString("That's sad...", Assets.midScreenX-170,Assets.midScreenY-105,paint);
				
		/*if(score.isBest(player.distance,difficulty))
		{
			paint.setTextSize(30);
			g.drawString("New High Score!", Assets.midScreenX-115, Assets.midScreenY-30, paint);
		}*/
		
		paint.setTextSize(60);
	
		DecimalFormat df = new DecimalFormat();
		df.setMaximumFractionDigits(0);
		g.drawString("Distance: ", Assets.midScreenX-130, Assets.midScreenY+40, paint);
		g.drawString(df.format(player.distance)+" m", Assets.midScreenX-90, Assets.midScreenY+100, paint);
	
		g.drawImage(Assets.button, Assets.midScreenX-458, Assets.midScreenY+190);
		g.drawString("Retry", Assets.midScreenX-284, Assets.midScreenY+248, Assets.mainMenu);
		g.drawImage(Assets.button, Assets.midScreenX+10, Assets.midScreenY+190);
		g.drawString("Main Menu", Assets.midScreenX+140, Assets.midScreenY+248, Assets.mainMenu);		
	}
	
	public void drowPause(Graphics g)
	{
		g.drawARGB(150, 0, 0, 0);
		
		g.drawImage(Assets.menuShader,Assets.midScreenX-521,Assets.midScreenY-320);
		
		Paint paint = new Paint();
		paint.setColor(-1);
		paint.setTextSize(65);
		
		g.drawString("Paused", Assets.midScreenX-130, Assets.midScreenY-230, paint);
		
		paint.setTextSize(60);

		g.drawString("And now...", Assets.midScreenX-140, Assets.midScreenY-155, paint);
		g.drawString("We wait...",Assets.midScreenX-130, Assets.midScreenY-105, paint);
		
		DecimalFormat df = new DecimalFormat();
		df.setMaximumFractionDigits(0);
		g.drawString("Distance: ", Assets.midScreenX-130, Assets.midScreenY+15, paint);
		g.drawString(df.format(player.distance)+" m", Assets.midScreenX-90, Assets.midScreenY+75, paint);
	
		
		g.drawImage(Assets.button, Assets.midScreenX-458, Assets.midScreenY+190);
		g.drawString("Continue", Assets.midScreenX-314, Assets.midScreenY+248, Assets.mainMenu);
		g.drawImage(Assets.button, Assets.midScreenX+10, Assets.midScreenY+190);
		g.drawString("Main Menu", Assets.midScreenX+134, Assets.midScreenY+248, Assets.mainMenu);
	}
	
	public void drowGUI(Graphics g)
	{
		//mygtukai
		//g.drawImage(Assets.jumpButtonON, 30, 30);
		//g.drawImage(Assets.slideButtonOFF, 188, 10);
		//g.drawImage(Assets.attackButtonOFF, g.getWidth()-168, 30);
		//g.drawImage(Assets.deffendButtonOFF, g.getWidth()-148, 10);
		g.drawScaledImage(Assets.pauseButton, Assets.midScreenX-64, 10, 128, 64, 0, 0, 32, 16);
	
		DecimalFormat df = new DecimalFormat();
		df.setMaximumFractionDigits(1);
		
		//g.drawString("UPS "+ups, Assets.midScreenX, Assets.midScreenY+20, Assets.mainMenu);
		//g.drawString("FPS "+fps, Assets.midScreenX, Assets.midScreenY-20, Assets.mainMenu);
		g.drawString("Distance "+df.format(player.distance)+" m", Assets.midScreenX-145,110, Assets.mainMenu);
		
		if(System.currentTimeMillis() - timer >1000)
		{
			timer +=1000;
			updateCounters();
		}
	}
	
	private void addItemName(Graphics g,Item item,Image image, int y,int imageX, Paint paint)
	{
		//fiting to screen
		int x = 0;
		
		int maxWidth = image.getWidth()-40;
		int textWidth = (int) paint.measureText(item.getName());
		
		x = Assets.midScreenX+65+((maxWidth-textWidth)/2);
		g.drawString(item.getName(), x, Assets.midScreenY+y, paint);
	}

	private void addItemDescription(Graphics g,Item item,Image image, int y, int imageX, Paint paint)
	{
		int x = 0;
		
		int maxLineWidth = image.getWidth()-40;
		int textWidth = (int) paint.measureText(item.getDescription());
		
		int linesNeeded = textWidth/maxLineWidth;
		int lastChar = 0;

		for(int i = 0;i<=linesNeeded;i++)
		{
			boolean lineFormed = false;
			String line = "";
			while(!lineFormed)
			{
				if(lastChar == item.getDescription().length()-1 || !shouldStop(item.getDescription(),line,maxLineWidth,lastChar,paint))
					lineFormed = true;
				else
				{
					line += item.getDescription().charAt(lastChar);
					lastChar++;
				}
			}
			
			x = (int) (Assets.midScreenX+65+((maxLineWidth-paint.measureText(line))/2));
			g.drawString(line, x, Assets.midScreenY+y+(i*40), paint);
		}
	}
	
	private boolean shouldStop(String description, String line,int maxLenght,int start,Paint paint)
	{		
		int wordEndIndex = description.indexOf(' ',start);
		int finalLenght = 0;
		
		
		for(int i = start;i<wordEndIndex;i++)
			line += description.charAt(i);
			
		finalLenght = (int)paint.measureText(line);
		
		if(finalLenght >= maxLenght)
			return false;
		else
			return true;
	}
	
	
	public void updateCounters()
	{
		fps = game.getFrames();
		ups = game.getUpdates();
		game.resetCounters();
	}
}