package lt.warriorsJourney.common.framework.implementation;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

public class AndroidFastRenderView extends SurfaceView implements Runnable {
    AndroidGame game;
    Bitmap framebuffer;
    Thread renderThread = null;
    SurfaceHolder holder;
    volatile boolean running = false;
    
    public AndroidFastRenderView(AndroidGame androidGame, Bitmap framebuffer) {
        super(androidGame);
        this.game = androidGame;
        this.framebuffer = framebuffer;
        this.holder = getHolder();
    }

	public void resume() { 
        running = true;
        renderThread = new Thread(this);
        renderThread.start();   

    }      
    
    public void run() {
        Rect dstRect = new Rect();
        long lastTime = System.nanoTime();
        final double ns = 1000000000.0/50.0;
        float deltaTime = 0;
        
        while(running) {  
        	       	
            if(!holder.getSurface().isValid())
                continue;           
            
            long now = System.nanoTime();
            deltaTime += (now - lastTime) / ns;
            lastTime = now;
            
           while(deltaTime >= 1)
           {
        	   game.getCurrentScreen().update(deltaTime);
        	   game.updates++;
        	   deltaTime--;
           }
     
            if(game.getCurrentScreen().getDrowRate() != 0)
        		continue;
            
            game.getCurrentScreen().paint(deltaTime);
            game.frames++;
          
            
            
            Canvas canvas = holder.lockCanvas();
            canvas.getClipBounds(dstRect);
            canvas.drawBitmap(framebuffer, null, dstRect, null);                           
            holder.unlockCanvasAndPost(canvas);
            
            
        }
    }

    public void pause() {                        
        running = false;                        
        while(true) {
            try {
                renderThread.join();
                break;
            } catch (InterruptedException e) {
                // retry
            }
            
        }
    }     
    
  
}