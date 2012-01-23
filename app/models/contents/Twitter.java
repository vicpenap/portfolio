package models.contents;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import play.Play;

import utilities.XMLReader;

public class Twitter extends GenericContent {

	public String tweet;
	
	
	public String toString() {
		return "Twitter - " + super.toString() + " "+tweet;
	}
	
	public static List<Twitter> getLastTweets(int page, int size) throws SAXException, IOException, ParseException {

		String URL = String.format((String) Play.configuration
				.get("twitter.api_url"), (String) Play.configuration
				.get("twitter.username"),page,size);
		
		ArrayList<Twitter> list = new ArrayList<Twitter>();

		Document doc = XMLReader.parse(URL);

		NodeList nodes = doc.getElementsByTagName("status");

		SimpleDateFormat formatter = new SimpleDateFormat("EEE MMM d HH:mm:ss ZZZZ yyyy",Locale.ENGLISH);
		for (int i = 0; i < nodes.getLength(); i++) {
			Node node = nodes.item(i);
			Element child = (Element) node;

			Twitter t = new Twitter();
			t.tweet = XMLReader.getTagValue("text", child);
			String date = XMLReader.getTagValue("created_at", child);
			t.publishedDate = formatter.parse(date);
			// returns date formatted as Sat Dec 17 18:44:29 +0000 2011
			// EEE MMM d HH:mm:ss ZZZ yyyy

			list.add(t);
		}

		return list;
	}
}
