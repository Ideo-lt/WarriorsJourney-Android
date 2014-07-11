package lt.warriorsJourney.common.assets;

import lt.warriorsJourney.common.files.ItemReader;
import lt.warriorsJourney.common.files.LevelReader;
import lt.warriorsJourney.common.framework.Image;
import android.graphics.Paint;

public class Assets
{
	//GUI ELEMENTS
	public static Image 	mainMenuBg,button,menuShader,backButton,pauseButton,smallButton,
							smallButtonOff,addStatOn,addStatOff,InvSlotOn,InvSlotOff,InvArrowUp,InvArrowDown,InvDescription;
	
	//DECORATIONS
	public static Image 	groundTile,spikeTile,unbreakableTile,groundTileBreak,spikeTileBreak;
	
	//SKILL ANIMATIONS/IMAGES
	public static Image		projectile,proExpl1,proExpl2,proExpl3,proExpl4,proExpl5;
	
	//ITEM ANIMATIONS/IMAGES
	public static Image	coin;
	
	//ENTITY ANIMATION PARTS
	public static Image	pcRun1,pcRun2,pcRun3,pcRun4,pcRun5,pcRun6,pcRun7,pcRun8,
							pcJump,pcFall,pcAtk1,pcAtk2,pcAtk3,pcAtk4,pcAtk5,pcAtk6;						;
							/*batStand1,batStand2,batStand3,batStand4,
							batAtk1,batAtk2,batAtk3,batAtk4,batAtk5,batAtk6;*/
	
	//MENU TEXT
	public static String mMButton1,mMButton2,mMButton3,mMButton4,mMButton5,
						   mMButton6,mMButton7,mMButton8,
						   credits1,credits2,credits3,credits4,credits5,
						   credits6,credits7,credits8,credits9,exit1,exit2,easy,medium;
	
	//SCREEN PLACEMENT HELPERS
	public static int midScreenX,midScreenY;
	
	//DEFFAULT PAINT
	public static Paint mainMenu,counter;
	
	//XML READERS
	public static LevelReader levels;	
	public static ItemReader items;

}
