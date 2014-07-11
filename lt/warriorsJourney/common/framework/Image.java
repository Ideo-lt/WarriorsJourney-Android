package lt.warriorsJourney.common.framework;

import lt.warriorsJourney.common.framework.Graphics.ImageFormat;

public interface Image {
    public int getWidth();
    public int getHeight();
    public ImageFormat getFormat();
    public void dispose();
}
