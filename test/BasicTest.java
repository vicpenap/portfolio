import java.io.IOException;
import java.text.ParseException;

import javax.xml.parsers.ParserConfigurationException;

import models.contents.Twitter;
import models.contents.flickr.Flickr;

import org.junit.Test;
import org.xml.sax.SAXException;

import play.test.UnitTest;

public class BasicTest extends UnitTest {


//    @Test
//    public void flickrTest() throws SAXException, IOException, ParserConfigurationException, ParseException {
//    	System.out.println(Flickr.getLastPics(1, 5));
//    	
//    	assertTrue(true);
//    }
    
    @Test
    public void flickrCollectionTest() throws SAXException, IOException, ParserConfigurationException, ParseException {
    	System.out.println(Flickr.getCollectionTree());
    	
    	assertTrue(true);
    }    
    
//    @Test
//    public void twitterTest() throws SAXException, IOException, ParserConfigurationException, ParseException {
//    	System.out.println(Twitter.getLastTweets(1, 5));
//    	
//    	assertTrue(true);
//    }
}
