package lt.warriorsJourney.common.screens;

import java.util.List;

import lt.warriorsJourney.common.assets.Assets;
import lt.warriorsJourney.common.assets.Background;
import lt.warriorsJourney.common.entity.Player;
import lt.warriorsJourney.common.framework.Game;
import lt.warriorsJourney.common.framework.Graphics;
import lt.warriorsJourney.common.framework.Input.TouchEvent;
import lt.warriorsJourney.common.framework.Screen;
import lt.warriorsJourney.common.world.World;
import lt.warriorsJourney.common.world.particles.Coin;

public class GameScreen extends Screen
{	
	public enum GameState
	{
		RUNNING,PAUSED,GAMEOVER
	}
	
	private static World world;
	private static Background bg1;
	private static Background bg2;
	private static Player player;
	private static GuiHandler gui;
	private int difficulty;
	private int type;
	
	public GameState gameState;
	int counter = 0;
	
	public GameScreen(Game game,int difficulty,int type)
	{
		super(game);
		bg1 = new Background(0, 0);
		bg2 = new Background(game.getGraphics().getWidth(), 0);
		player = new Player(this);
		world = new World(difficulty,type);
		gui = new GuiHandler(game);
			
		this.difficulty = difficulty;
		this.type = type;
		
		world.registerEntity(player);
		world.registerParticle(new Coin(5*256,4*128));
		
		gameState = GameState.RUNNING;
	}

	@Override
	public void update(float deltaTime)
	{
		
		if(gameState == GameState.RUNNING)
			updateRunning();
		else if(gameState == GameState.GAMEOVER)
			updateGameOver();
		else
			updatePaused();
	}

	public void updateRunning()
	{
		
		List<TouchEvent> touchEvents = game.getInput().getTouchEvents();
		TouchEvent event;
		
		for(int i=0;i < touchEvents.size();i++)
		{
			 event = touchEvents.get(i);
			
			 if(event.type == TouchEvent.TOUCH_DOWN)
				{
					if(inBounds(event,0,0,Assets.midScreenX-150,Assets.midScreenY*2)&&(!player.isInAir()||player.jumpCount < 2))
					{
						player.setJumping(true);
						player.setSpeedY(35+((player.playerData.getAgility()-1)/2));
						player.jumpCount += 1;
						
						if(player.isStoped())
							player.setStoped(false);
					}
					
					if(inBounds(event,Assets.midScreenX-64, 0, 128, 64))
						gameState = GameState.PAUSED;
					
					if(inBounds(event,Assets.midScreenX+64,0,Assets.midScreenX*2-Assets.midScreenX+64,Assets.midScreenY*2)&&player.getAttackCooldown() == 0 )
						player.setAttacking(true);
				}
		}
		
		world.updateWorld();
	}
	
	public void updatePaused()
	{
		List<TouchEvent> touchEvents = game.getInput().getTouchEvents();
		TouchEvent event;
		
		for(int i=0;i < touchEvents.size();i++)
		{
			 event = touchEvents.get(i);
			
			if(event.type == TouchEvent.TOUCH_DOWN)
			{
				if(inBounds(event,Assets.midScreenX-458, Assets.midScreenY+190,448,86))
					gameState = GameState.RUNNING;
					
				if(inBounds(event,Assets.midScreenX+10, Assets.midScreenY+190,448,86))
				{
					game.setScreen(new MainMenuScreen(game));
				}
			}
			
		}
	}
	
	public void updateGameOver()
	{
		List<TouchEvent> touchEvents = game.getInput().getTouchEvents();
		TouchEvent event;
		
		for(int i=0;i < touchEvents.size();i++)
		{
			 event = touchEvents.get(i);
			
			if(event.type == TouchEvent.TOUCH_DOWN)
			{
				if(inBounds(event,Assets.midScreenX-458, Assets.midScreenY+190,448,86))
					game.setScreen(new GameScreen(game,difficulty,type));
						
				if(inBounds(event,Assets.midScreenX+10, Assets.midScreenY+190,448,86))
					game.setScreen(new MainMenuScreen(game));
			}
			
		}
	}
	
	@Override
	public void paint(float deltaTime)
	{
		Graphics g = game.getGraphics();
		
		if(gameState == GameState.RUNNING)
		{
			world.paintWorld(g);
			gui.drowGUI(g);
		}
		else if(gameState == GameState.GAMEOVER)
		{
			gui.drowGameOver(g);
			player.playerData.setExp(player.playerData.getExp()+ solveExpAdd());
			player.playerData.isLevelUp();
			player.playerData.saveData();
		}
		else
			gui.drowPause(g);
	}
	
	private long solveExpAdd()
	{
		long exp = 0;
		for(long i = (long) player.distance;i > 0; )
		{
			if(i - 1000 > 0)
				exp += 50*difficulty;
			
			i= i-1000;
		}
		return exp;
	}

	private boolean inBounds(TouchEvent event, int x, int y, int width, int height)
	{
        if (event.x > x && event.x < x + width - 1 && event.y > y&& event.y < y + height - 1)
            return true;
        else
            return false;
    }
	
	@Override
	public void pause()
	{
	}

	@Override
	public void resume()
	{
	}

	@Override
	public void dispose()
	{
	}

	@Override
	public void backButton()
	{
	}
	
	public static Background getBg1()
	{
		return bg1;
	}
	
	public static Background getBg2()
	{
		return bg2;
	}
	
	public static Player getPlayer()
	{
		return player;
	}

	@Override
	public int getDrowRate()
	{
		if(gameState != GameState.RUNNING && counter == 1)
			return -1;
		else if (gameState != GameState.RUNNING)
		 	counter++;
		
		if(gameState == GameState.RUNNING)
			counter = 0;
		
		return 0;
	}
	

	
	public void setGameState(GameState state)
	{
		gameState = state;
	}
	
	public static World getworld()
	{
		return world;
	}
	
	public Game getGame()
	{
		return game;
	}

	@Override
	public int gerId()
	{
		return 2;
	}
}
