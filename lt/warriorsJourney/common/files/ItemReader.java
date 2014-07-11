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
public class ItemReader
{
	private static final String ns = null;
	public Map<Integer, Item> items = new HashMap<Integer, Item>();
	
	public ItemReader(Game game)
	{
		InputStream is = game.getContext().getResources().openRawResource(R.raw.items);
		 
		try
		{
			items = parse(is);
		} catch (XmlPullParserException e)
		{
			e.printStackTrace();
		} catch (IOException e)
		{
			e.printStackTrace();
		}
	}
	
	public Map<Integer,Item> parse(InputStream in) throws XmlPullParserException, IOException
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
	
	private Map<Integer,Item> readFeed(XmlPullParser parser) throws XmlPullParserException, IOException 
	{
	    Map <Integer,Item> entries = new HashMap<Integer,Item>();
	    
	    parser.require(XmlPullParser.START_TAG, ns, "items");

	    while (parser.next() != XmlPullParser.END_TAG) 
	    {
	        if (parser.getEventType() != XmlPullParser.START_TAG)
	        {
	        	continue;
	        }
	        
	        String name = parser.getName();

	        if (name.equals("item")) {
	            entries.put(entries.size(),readEntry(parser));
	        } else {
	            skip(parser);
	        }
	    }  
	    return entries;
	}
	 
	private Item readEntry(XmlPullParser parser) throws XmlPullParserException, IOException
	{
	    parser.require(XmlPullParser.START_TAG, ns, "item");
	    
	    int id = 0;
		String itemName = "";
		String description = "";
		int price = 0;
		
		int bonusSTR = 0;
		int bonusSTA = 0;
		int bonusDEX = 0;
		int bonusAGI = 0;
		int bonusCON = 0;
		int bonusINT = 0;

		int effectType = 0;
	    
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
	        else if (name.equals("name")) 
	        {
	        	itemName = readText(parser,"name");
	        } 
	        else if (name.equals("description")) 
	        {
	        	description = readText(parser,"description");
	        }
	        else if (name.equals("price")) 
	        {
	        	price = Integer.parseInt(readText(parser,"price"));
	        }
	        else if (name.equals("bonusSTR")) 
	        {
	            bonusSTR = Integer.parseInt(readText(parser,"bonusSTR"));
	        }
	        else if (name.equals("bonusSTA")) 
	        {
	            bonusSTA = Integer.parseInt(readText(parser,"bonusSTA"));
	        }
	        else if (name.equals("bonusDEX")) 
	        {
	            bonusDEX = Integer.parseInt(readText(parser,"bonusDEX"));
	        }
	        else if (name.equals("bonusAGI")) 
	        {
	            bonusAGI = Integer.parseInt(readText(parser,"bonusAGI"));
	        }
	        else if (name.equals("bonusCON")) 
	        {
	            bonusCON = Integer.parseInt(readText(parser,"bonusCON"));
	        }
	        else if (name.equals("bonusINT")) 
	        {
	            bonusINT = Integer.parseInt(readText(parser,"bonusINT"));
	        }
	        else if (name.equals("effectType")) 
	        {
	            effectType = Integer.parseInt(readText(parser,"effectType"));
	        }
	        else 
	        {
	            skip(parser);
	        }
	    }
	    return new Item(id, itemName, description, price, bonusSTR, bonusSTA, bonusDEX, bonusAGI, bonusCON, bonusINT, effectType);
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
