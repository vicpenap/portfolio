package controllers;

import java.io.IOException;
import java.text.ParseException;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;

import models.contents.flickr.Flickr;
import models.contents.flickr.FlickrCollection;
import models.contents.flickr.FlickrPhoto;
import models.contents.flickr.FlickrSet;

import org.xml.sax.SAXException;

import play.mvc.Controller;

public class Photos extends Controller {
	public static void index(int page, int size) throws SAXException,
			IOException, ParserConfigurationException, ParseException {
		List<FlickrPhoto> photos = Flickr.getLastPics(page, size);
		render(photos);
	}

	public static void interesting(int page, int size) throws SAXException,
			IOException, ParseException {
		List<FlickrPhoto> photos = Flickr.getInterestingPics(page, size);
		render(photos);
	}
	
	public static void albums() throws SAXException, IOException {
		List<FlickrCollection> collections = Flickr.getCollectionTree();
		
		render(collections);
	}
	
	public static void viewAlbum(String id, int page, int size) throws SAXException, IOException, ParseException {
		FlickrSet set = Flickr.getSet(id, page, size);
		
		render(set);
	}
}
