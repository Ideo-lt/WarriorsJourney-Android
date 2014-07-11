package lt.warriorsJourney.common.framework;

import android.content.Context;
import android.widget.RelativeLayout;

public interface Game {
    
	public Audio getAudio();

    public Input getInput();

    public FileIO getFileIO();

    public Graphics getGraphics();

    public void setScreen(Screen screen);

    public Screen getCurrentScreen();

    public Screen getInitScreen();
    
    public int getFrames();
    
    public int getUpdates();
    
    public void resetCounters();
    
    public Context getContext();
    
    public RelativeLayout getLayout();
}
