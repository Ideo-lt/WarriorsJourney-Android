package lt.warriorsJourney.common.files;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import lt.warriorsJourney.common.R;
import lt.warriorsJourney.common.framework.Game;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import android.annotation.SuppressLint;
import android.util.Xml;

@SuppressLint("UseSparseArrays")
public class LevelReader
{
    private static final String ns = null;
	public Map<Integer,LevelData> levels = new HashMap<Integer,LevelData>();
	
	public LevelReader(Game game)
	{
		InputStream is = game.getContext().getResources().openRawResource(R.raw.levels);
		
		try
		{
			levels = parse(is);
		} catch (XmlPullParserException e)
		{
			e.printStackTrace();
		} catch (IOException e)
		{
			e.printStackTrace();
		}
	}
	
	public Map<Integer,LevelData> parse(InputStream in) throws XmlPullParserException, IOException
	{
        try
        {
            XmlPullParser parser = Xml.newPullParser();
            parser.setFeature(XmlPullParser.FEATURE_PROCESS_NAMESPACES, false);
            parser.setInput(in, null);
            parser.nextTag();
            return readFeed(parser);
        }
        finally 
        {
            in.close();
        }
    }
	
	private Map<Integer,LevelData> readFeed(XmlPullParser parser) throws XmlPullParserException, IOException 
	{
	    Map <Integer,LevelData> entries = new HashMap<Integer,LevelData>();
	    
	    parser.require(XmlPullParser.START_TAG, ns, "levels");

	    while (parser.next() != XmlPullParser.END_TAG) 
	    {
	        if (parser.getEventType() != XmlPullParser.START_TAG)
	        {
	        	continue;
	        }
	        
	        String name = parser.getName();

	        if (name.equals("level")) {
	            entries.put(entries.size()+1,readEntry(parser));
	        } else {
	            skip(parser);
	        }
	    }  
	    return entries;
	}
	 
	private LevelData readEntry(XmlPullParser parser) throws XmlPullParserException, IOException
	{
	    parser.require(XmlPullParser.START_TAG, ns, "level");
	    int id = 0;
	    int exp = 0;
	    int bonusStats = 0;
	    int bonusCoins = 0;
	    String rank = null;
	    
	    while (parser.next() != XmlPullParser.END_TAG) 
	    {
	        if (parser.getEventType() != XmlPullParser.START_TAG) 
	        {
	            continue;
	        }
	        String name = parser.getName();
	        if (name.equals("id")) 
	        {
	            id = Integer.parseInt(readText(parser,"id"));
	        } 
	        else if (name.equals("exp")) 
	        {
	        	exp = Integer.parseInt(readText(parser,"exp"));
	        } 
	        else if (name.equals("bonusStats")) 
	        {
	        	bonusStats = Integer.parseInt(readText(parser,"bonusStats"));
	        }
	        else if (name.equals("bonusCoins")) 
	        {
	        	bonusCoins = Integer.parseInt(readText(parser,"bonusCoins"));
	        }
	        else if (name.equals("rank")) 
	        {
	            rank = readText(parser,"rank");
	        }
	        else 
	        {
	            skip(parser);
	        }
	    }
	    return new LevelData(id, exp, bonusStats,bonusCoins,rank);
	}

	private String readText(XmlPullParser parser,String tag) throws IOException, XmlPullParserException {
	   
		parser.require(XmlPullParser.START_TAG, ns, tag);
		String result = "";
	    if (parser.next() == XmlPullParser.TEXT) {
	        result = parser.getText();
	        parser.nextTag();
	    }
	    
	    parser.require(XmlPullParser.END_TAG, ns, tag);
	    return result;
	}

	private void skip(XmlPullParser parser) throws XmlPullParserException, IOException 
	{
	    if (parser.getEventType() != XmlPullParser.START_TAG) 
	    {
	        throw new IllegalStateException();
	    }
	    
	    int depth = 1;
	    while (depth != 0) 
	    {
	        switch (parser.next()) 
	        {
	        case XmlPullParser.END_TAG:
	            depth--;
	            break;
	        case XmlPullParser.START_TAG:
	            depth++;
	            break;
	        }
	    }
	 }
}
