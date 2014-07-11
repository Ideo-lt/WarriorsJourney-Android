package lt.warriorsJourney.common.screens;

import java.util.List;

import lt.warriorsJourney.common.assets.Assets;
import lt.warriorsJourney.common.database.InventoryData;
import lt.warriorsJourney.common.files.Item;
import lt.warriorsJourney.common.framework.Game;
import lt.warriorsJourney.common.framework.Graphics;
import lt.warriorsJourney.common.framework.Input.TouchEvent;
import lt.warriorsJourney.common.framework.Screen;

public class MainMenuScreen extends Screen
{
	enum Drow
	{
		MMENU,CREDITS,OPTIONS,EXIT,START,SCORE,TUTORIAL,STATS,STATINFO,INVENTORY,SHOP
	}
	
	Drow shouldDrow;
	boolean reDrow = true;
	int dispDiff,type;
	//	Y				X					kelintas stulpelis virsui
	int selectedSlotRow,selectedSlotColumn,topRow;
	Item selectedItem = null;
	
	GuiHandler gui;
	InventoryData inventory;
	
	public MainMenuScreen(Game game)
	{
		super(game);
		shouldDrow = Drow.MMENU;
		
		//used in score display
		dispDiff = 1;
		type = 1;
		
		//used in inventory display
		selectedSlotRow = 1;
		selectedSlotColumn = 1;
		topRow = 1;
		
		gui = new GuiHandler(game);
		
		inventory = new InventoryData(game.getContext());
		
		if(inventory.getItemByPlace(1) != null)
			selectedItem = Assets.items.items.get(inventory.getItemByPlace(1).getId());
	}

	@Override
	public void update(float deltaTime)
	{
		List<TouchEvent> touchEvents = game.getInput().getTouchEvents();
		TouchEvent event;
		
		for(int i=0;i < touchEvents.size();i++)
		{
			 event = touchEvents.get(i);
			
			if((event.type == TouchEvent.TOUCH_DOWN))
			{
				switch (shouldDrow)
				{	
					case MMENU:
					{
						if(inBounds(event,Assets.midScreenX-224,Assets.midScreenY-248,448,86))
						{
							shouldDrow = Drow.START;
							reDrow = true;
						}
						
						if(inBounds(event,Assets.midScreenX-458,Assets.midScreenY-148,448,86))
						{
							shouldDrow = Drow.SCORE;
							reDrow = true;
						}
						
						if(inBounds(event,Assets.midScreenX+10,Assets.midScreenY-148,448,86))
						{
							shouldDrow = Drow.STATS;
							reDrow = true;
						}
						
						if(inBounds(event,Assets.midScreenX-458,Assets.midScreenY-48,448,86))
						{
							shouldDrow = Drow.INVENTORY;
							reDrow = true;
						}
						
						if(inBounds(event,Assets.midScreenX+10,Assets.midScreenY-48,448,86))
						{
							shouldDrow = Drow.SHOP;
							reDrow = true;
						}
						
						if(inBounds(event,Assets.midScreenX-458,Assets.midScreenY+49,448,86))
						{
							shouldDrow = Drow.OPTIONS;
							reDrow = true;
						}
				
						if(inBounds(event,Assets.midScreenX+10,Assets.midScreenY+49,448,86))
						{
							shouldDrow = Drow.CREDITS;
							reDrow = true;
						}
				
						if(inBounds(event,Assets.midScreenX-224,Assets.midScreenY+158,448,86))
						{
							shouldDrow = Drow.EXIT;
							reDrow = true;

						}
						break;
					}
					
					case START:
					{
						
						if(inBounds(event,Assets.midScreenX-224,Assets.midScreenY-248,448,86))
						{
							shouldDrow = Drow.TUTORIAL;
							reDrow = true;
						}
						
						if(inBounds(event,Assets.midScreenX-224,Assets.midScreenY+180,448,86))
						{
							shouldDrow = Drow.MMENU;
						    reDrow = true;
						}	
						
						if(inBounds(event,Assets.midScreenX-224,Assets.midScreenY-148,448,86))
							game.setScreen(new GameScreen(game,1,1));
												
						if(inBounds(event,Assets.midScreenX-224,Assets.midScreenY-48,448,86))
							game.setScreen(new GameScreen(game,2,1));
						
					/*  if(inBounds(event,Assets.midScreenX-224,Assets.midScreenY+62,448,86))
							game.setScreen(new GameScreen(game,1,2));*/
												
						break;
					}
					case SCORE:
					{
						if(inBounds(event,Assets.midScreenX-224,Assets.midScreenY+180,448,86))
						{
							shouldDrow = Drow.MMENU;
							reDrow = true;
						}
						
						if(inBounds(event,Assets.midScreenX+210, Assets.midScreenY-256,224,86))
						{
							dispDiff = 1;
							reDrow = true;
						}
						
						if(inBounds(event,Assets.midScreenX+210, Assets.midScreenY-162,224,86))
						{
							dispDiff = 2;
							reDrow = true;
						}
						break;
					}
					case STATS:
					{
						if(inBounds(event,Assets.midScreenX-362, Assets.midScreenY+200,224,84))
						{
							gui.playerData.resetStats();
							gui.playerData.saveData();
							reDrow = true;
						}
						
						if(inBounds(event,Assets.midScreenX+140, Assets.midScreenY+200,224,84))
						{
							shouldDrow = Drow.MMENU;
							reDrow = true;
						}
						
						if(inBounds(event,Assets.midScreenX+230, Assets.midScreenY-280,224,84))
						{
							shouldDrow = Drow.STATINFO;
							reDrow = true;
						}
						
						if(gui.playerData.getUnspentPoints() >= 1)
						{
							//dadeda ko praso
							if(inBounds(event,Assets.midScreenX-180, Assets.midScreenY-85,64,64))
							{
								gui.playerData.increaseStrenght(1);
								gui.playerData.saveData();
								reDrow = true;
							}
							if(inBounds(event,Assets.midScreenX-180, Assets.midScreenY+5,64,64))
							{
								gui.playerData.increaseStamina(1);
								gui.playerData.saveData();

								reDrow = true;
							}
							if(inBounds(event,Assets.midScreenX-180, Assets.midScreenY+98,64,64))
							{
								gui.playerData.increaseDexterity(1);
								gui.playerData.saveData();
								reDrow = true;
							}
							
							
							
							if(inBounds(event,Assets.midScreenX+310, Assets.midScreenY-85,64,64))
							{
								gui.playerData.increaseAgility(1);
								gui.playerData.saveData();
								reDrow = true;
							}
							if(inBounds(event,Assets.midScreenX+310, Assets.midScreenY+5,64,64))
							{
								gui.playerData.increaseConcentration(1);
								gui.playerData.saveData();
								reDrow = true;
							}
							if(inBounds(event,Assets.midScreenX+310, Assets.midScreenY+95,64,64))
							{
								gui.playerData.increaseIntelligence(1);
								gui.playerData.saveData();
								reDrow = true;
							}
						}
						
						break;
					}
					case STATINFO:
					{
						if(inBounds(event,0,0,Assets.midScreenX*2,Assets.midScreenY*2))
						{
							gui.playerData.increaseBalance(99999999);
							shouldDrow = Drow.STATS;
							reDrow = true;
						}
						break;
					}
					case INVENTORY:
					{
						//First Row
						if(inBounds(event,Assets.midScreenX-433,Assets.midScreenY-198,128,128))
						{
							selectedSlotRow = topRow;
							selectedSlotColumn = 1;
							reDrow = true;
						}
						
						if(inBounds(event,Assets.midScreenX-305,Assets.midScreenY-198,128,128))
						{
							selectedSlotRow = topRow;
							selectedSlotColumn = 2;
							reDrow = true;
						}
						
						if(inBounds(event,Assets.midScreenX-177,Assets.midScreenY-198,128,128))
						{
							selectedSlotRow = topRow;
							selectedSlotColumn = 3;
							System.out.println("selected "+selectedSlotRow);

							reDrow = true;
						}
						
						//Second Row					
						if(inBounds(event,Assets.midScreenX-433,Assets.midScreenY-70,128,128))
						{
							selectedSlotRow = topRow+1;
							selectedSlotColumn = 1;
							reDrow = true;
						}
						
						if(inBounds(event,Assets.midScreenX-305,Assets.midScreenY-70,128,128))
						{
							selectedSlotRow = topRow+1;
							selectedSlotColumn = 2;
							reDrow = true;
						}
						
						if(inBounds(event,Assets.midScreenX-177,Assets.midScreenY-70,128,128))
						{
							selectedSlotRow = topRow+1;
							selectedSlotColumn = 3;
							System.out.println("selected "+selectedSlotRow);
							reDrow = true;
						}
						
						//Third Row
						if(inBounds(event,Assets.midScreenX-433,Assets.midScreenY+58,128,128))
						{
							selectedSlotRow = topRow+2;
							selectedSlotColumn = 1;
							reDrow = true;
						}
						
						if(inBounds(event,Assets.midScreenX-305,Assets.midScreenY+58,128,128))
						{
							selectedSlotRow = topRow+2;
							selectedSlotColumn = 2;
							reDrow = true;
						}
						
						if(inBounds(event,Assets.midScreenX-177,Assets.midScreenY+58,128,128))
						{
							selectedSlotRow = topRow+2;
							selectedSlotColumn = 3;
							System.out.println("selected "+selectedSlotRow);

							reDrow = true;
						}
						
						if(inBounds(event,Assets.midScreenX-35,Assets.midScreenY-198, 64, 128))
						{
							topRow -=1;
							
							if(topRow < 1)
								topRow = 1;
						
							reDrow = true;
						}
						
						if(inBounds(event,Assets.midScreenX-35,Assets.midScreenY+56, 64, 128))
						{
							topRow +=1;
							
							if(topRow > 3)
								topRow = 3;
												
							reDrow = true;
						}
						
						break;
					}
					case SHOP:
					{
						if(inBounds(event,0,0,Assets.midScreenX*2,Assets.midScreenY*2))
						{
							shouldDrow = Drow.MMENU;
							reDrow = true;
						}
						break;
					}
					case OPTIONS:
					{
						
						if(inBounds(event,Assets.midScreenX-224,Assets.midScreenY+180,448,86))
						{
							shouldDrow = Drow.MMENU;
						    reDrow = true;
						}	
						break;
					}
					case CREDITS:
					{
						
						if(inBounds(event,Assets.midScreenX-224,Assets.midScreenY+180,448,86))
						{
							shouldDrow = Drow.MMENU;
						    reDrow = true;
						}	
						break;
					}
					case EXIT:
					{
						
						if(inBounds(event,Assets.midScreenX+46,Assets.midScreenY-50,448,86))
						{
							shouldDrow = Drow.MMENU;
						    reDrow = true;
						}	
						
						if(inBounds(event,Assets.midScreenX-400,Assets.midScreenY-50,448,86))
						{
					        android.os.Process.killProcess(android.os.Process.myPid());
						}	
						
						break;
					}
					case TUTORIAL:
					{
						if(inBounds(event,0,0,Assets.midScreenX*2,Assets.midScreenY*2))
						{
							shouldDrow = Drow.START;
							reDrow = true;
						}
					}
				default:
					break;
			
				}
			}	
		}
	}

	@Override
	public void paint(float deltaTime)
	{
		if(!reDrow)
			return;
		
		Graphics g = game.getGraphics();
				
		/*	BG perdidelis reikia taisyt
		 * 	Pastaba: galima idet judanti bg (o gal nx :D)
		 */
		g.drawScaledImage(Assets.mainMenuBg, 0, 0,g.getWidth(),g.getHeight(),0,0,256,140);
		
		switch (shouldDrow)
		{ 	
			case MMENU:
			{
				gui.drowMainMenu(g);
				break;
			}
		
			case START:
			{
				gui.drowStartGame(g);
				break;
			}
			
			case SCORE:
			{	
				gui.drowScore(g, dispDiff,type);
				break;
			}
			
			case STATS:
			{
				gui.drowStats(g);
				break;
			}
			
			case STATINFO:
			{
				gui.drowStatsInfo(g);
				break;
			}
			
			case INVENTORY:
			{
				gui.drowInventory(g,selectedSlotRow,selectedSlotColumn,topRow,selectedItem);
				break;
			}
			
			case SHOP:
			{
				gui.drowShop(g);
				break;
			}
			
			case OPTIONS:
			{
				gui.drowOptions(g);
				break;
			}
			
			case CREDITS:
			{
				gui.drowCredits(g);
				break;
			}
			
			case EXIT:
			{
				gui.drowExit(g);
				break;
			}
			
			case TUTORIAL:
			{
				gui.drowTutorial(g);
				break;
			}
			default:
			
		}
		
		reDrow = false;
		/*
		 * pastaba(Versijai 0.2++):
		 * Turetu būt achievements mygtukas
		 * Turetu būt animacija paspaudus mygtuka
		 *
		 */
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
		//pff pas mus ir taip achujenas (nereikia perpiest nieko)
	}

	@Override
	public void resume()
	{
		//pff pas mus ir taip achujenas (nereikia perpiest nieko)
	}

	@Override
	public void dispose()
	{
		//nera kam to reiktu	
	}

	@Override
	public void backButton()
	{
		//nezinau kas cia turetu but, bet tikrai ne telefono back'as :D
	}

	@Override
	public int getDrowRate()
	{
		if(!reDrow)
			return -1;
		else
			return 0;
	}

	@Override
	public int gerId()
	{
		return 1;
	}

}
