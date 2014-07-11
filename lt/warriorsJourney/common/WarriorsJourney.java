package lt.warriorsJourney.common;

import lt.warriorsJourney.common.framework.Screen;
import lt.warriorsJourney.common.framework.implementation.AndroidGame;
import lt.warriorsJourney.common.screens.LoadingScreen;

public class WarriorsJourney extends AndroidGame
{

	@Override
	public Screen getInitScreen()
	{
		return new LoadingScreen(this);
	}

}
