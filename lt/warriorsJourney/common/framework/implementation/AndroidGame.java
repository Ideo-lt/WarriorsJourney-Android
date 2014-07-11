package lt.warriorsJourney.common.framework.implementation;

import java.util.Timer;
import java.util.TimerTask;

import lt.warriorsJourney.common.framework.Audio;
import lt.warriorsJourney.common.framework.FileIO;
import lt.warriorsJourney.common.framework.Game;
import lt.warriorsJourney.common.framework.Graphics;
import lt.warriorsJourney.common.framework.Input;
import lt.warriorsJourney.common.framework.Screen;
import android.app.Activity;
import android.content.Context;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.os.Bundle;
import android.os.Handler;
import android.os.PowerManager;
import android.os.PowerManager.WakeLock;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.RelativeLayout;

import com.google.ads.AdRequest;
import com.google.ads.AdSize;
import com.google.ads.AdView;
import com.google.analytics.tracking.android.EasyTracker;

public abstract class AndroidGame extends Activity implements Game
{
	AndroidFastRenderView renderView;
	Graphics graphics;
	Audio audio;
	Input input;
	FileIO fileIO;
	Screen screen;
	WakeLock wakeLock;
	public int updates = 0;
	public int frames = 0;
	RelativeLayout layout;
    AdView adView;
    
    Timer viewChanger;
	boolean adAdded = false;
    
    Handler viewHandler;
    
	@SuppressWarnings("deprecation")
	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);

		requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);
		
		boolean isPortrait = getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT;
		
		int frameBufferWidth = isPortrait ? 800 : 1280;
		int frameBufferHeight = isPortrait ? 1280 : 800;
		Bitmap frameBuffer = Bitmap.createBitmap(frameBufferWidth,
				frameBufferHeight, Config.RGB_565);

		float scaleX = (float) frameBufferWidth
				/ getWindowManager().getDefaultDisplay().getWidth();
		float scaleY = (float) frameBufferHeight
				/ getWindowManager().getDefaultDisplay().getHeight();

		renderView = new AndroidFastRenderView(this, frameBuffer);
		graphics = new AndroidGraphics(getAssets(), frameBuffer);
		fileIO = new AndroidFileIO(this);
		audio = new AndroidAudio(this);
		input = new AndroidInput(this, renderView, scaleX, scaleY);
		adView = new AdView(this, AdSize.SMART_BANNER, "ca-app-pub-8003877202155969/2139115330");
		screen = getInitScreen();
		
		viewHandler = new Handler();
		
		layout = new RelativeLayout(this);
		layout.addView(renderView);
		layout.addView(adView);
		
        setContentView(layout);
 
        viewChanger = new Timer();
        viewChanger.schedule(new TimerTask() {@Override public void run() {handlerWork(screen);}}, 0,100);
        
        adView.loadAd(new AdRequest().addTestDevice(AdRequest.TEST_EMULATOR).addTestDevice("51EC2C0C3610C86B15F48D742804075E"));
		PowerManager powerManager = (PowerManager) getSystemService(Context.POWER_SERVICE);
		wakeLock = powerManager.newWakeLock(PowerManager.FULL_WAKE_LOCK,"Warriors Journey");
		
		EasyTracker.getInstance(this).activityStart(this);
	}

    private void handlerWork(Screen screen)
    {
    	changeView(screen);
    } 
        
	@Override
	public void onResume()
	{
		super.onResume();
		wakeLock.acquire();
		screen.resume();
		renderView.resume();
	}

	@Override
	public void onPause()
	{
		super.onPause();
		wakeLock.release();
		renderView.pause();
		screen.pause();

		if (isFinishing())
			screen.dispose();
		
		EasyTracker.getInstance(this).activityStop(this);
	}

	@Override
	public Input getInput()
	{
		return input;
	}

	@Override
	public FileIO getFileIO()
	{
		return fileIO;
	}

	@Override
	public Graphics getGraphics()
	{
		return graphics;
	}

	@Override
	public Audio getAudio()
	{
		return audio;
	}

	@Override
	public void setScreen(Screen screen)
	{
		if (screen == null)
			throw new IllegalArgumentException("Screen must not be null");
		this.screen.pause();
		this.screen.dispose();
		screen.resume();
		screen.update(0);
		this.screen = screen;
	}

	public Screen getCurrentScreen()
	{
		return screen;
	}
	@Override
	public int getFrames()
	{
		return frames;
	}
	
	@Override
	public int getUpdates()
	{
		return updates;
	}
	
	@Override
	public void resetCounters()
	{
		frames = 0;
		updates = 0;
	}
	
	@Override
	public Context getContext()
	{
		return this;
	}
	
	public void changeView(final Screen screen)
	{
		viewHandler.post(new Runnable(){
			
			@Override
			public void run() {
				
				if(screen.gerId() == 1 && !adAdded)
				{
					layout.getChildAt(1).setVisibility(View.VISIBLE);
					adAdded = true;
				}
				
				if(screen.gerId() !=1)
				{
					layout.getChildAt(1).setVisibility(View.GONE);
					adAdded = false;
				}	
			};
		});	
	}
	
	   
	@Override
	public RelativeLayout getLayout()
	{
		return layout;
	}
}