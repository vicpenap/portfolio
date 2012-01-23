package models.contents.flickr;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import play.Play;
import utilities.XMLReader;

import com.sun.org.apache.xerces.internal.dom.DeferredElementImpl;
import com.sun.org.apache.xerces.internal.dom.DeferredTextImpl;

public class Flickr {
	private static String flickrURL = (String) Play.configuration
			.get("flickr.api_url");
	private static String myFlickrID = (String) Play.configuration
			.get("flickr.user_id");

	private static FlickrSet getInfoSet(String id) throws SAXException, IOException {
		String method = "flickr.photosets.getInfo";

		String URL = String.format(flickrURL, method).concat(
				"&photoset_id=" + id);


		Document doc = XMLReader.parse(URL);

		FlickrSet s = new FlickrSet();
		s.id = id;
		NodeList nodes = doc.getElementsByTagName("photoset");
		Element e = (Element) nodes.item(0);
		s.totalSize = new Integer(e.getAttribute("photos"));
		s.title = XMLReader.getTagValue("title", e);
		
		return s;	
	}
	
	public static FlickrSet getSet(String id, int page, int size) throws SAXException, IOException, ParseException {
		String photoSize = "s";

		String method = "flickr.photosets.getPhotos";

		String URL = String.format(flickrURL, method).concat(
				"&photoset_id=" + id + "&per_page=" + size + "&page="
						+ page + "&extras=date_taken,url_z,url_" + photoSize);

		ArrayList<FlickrPhoto> list = new ArrayList<FlickrPhoto>();

		Document doc = XMLReader.parse(URL);
		FlickrSet s = getInfoSet(id);

		NodeList nodes = doc.getElementsByTagName("photo");

		DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		for (int i = 0; i < nodes.getLength(); i++) {
			Node node = nodes.item(i);
			Element child = (Element) node;

			FlickrPhoto f = new FlickrPhoto();
			f.title = child.getAttribute("title");
			f.photoURL = child.getAttribute("url_" + photoSize);
			f.largePhotoURL = child.getAttribute("url_z");
			f.height = Integer.parseInt(child.getAttribute("height_"
					+ photoSize));
			f.width = Integer
					.parseInt(child.getAttribute("width_" + photoSize));
			f.publishedDate = formatter.parse(child.getAttribute("datetaken"));

			list.add(f);
		}
		s.photos = list;
		s.page = page;
		s.pageSize = size;
		
		return s;
	}
	
	
	public static List<FlickrPhoto> getLastPics(int page, int size)
			throws SAXException, IOException, ParserConfigurationException, ParseException {
		String photoSize = (String) Play.configuration.get("flickr.photo_size");

		String method = "flickr.people.getPublicPhotos";

		String URL = String.format(flickrURL, method).concat(
				"&user_id=" + myFlickrID + "&per_page=" + size + "&page="
						+ page + "&extras=date_taken,url_z,url_" + photoSize);

		ArrayList<FlickrPhoto> list = new ArrayList<FlickrPhoto>();

		Document doc = XMLReader.parse(URL);

		NodeList nodes = doc.getElementsByTagName("photo");

		DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		for (int i = 0; i < nodes.getLength(); i++) {
			Node node = nodes.item(i);
			Element child = (Element) node;

			FlickrPhoto f = new FlickrPhoto();
			f.title = child.getAttribute("title");
			f.photoURL = child.getAttribute("url_" + photoSize);
			f.largePhotoURL = child.getAttribute("url_z");
			f.height = Integer.parseInt(child.getAttribute("height_"
					+ photoSize));
			f.width = Integer
					.parseInt(child.getAttribute("width_" + photoSize));
			f.publishedDate = formatter.parse(child.getAttribute("datetaken"));

			list.add(f);
		}

		return list;
	}
	
	public static List<FlickrPhoto> getInterestingPics(int page, int size) throws SAXException, IOException, ParseException {
		String photoSize = (String) Play.configuration.get("flickr.photo_size");

		String method = "flickr.photos.search";

		String URL = String.format(flickrURL, method).concat(
				"&user_id=" + myFlickrID + "&per_page=" + size + "&page="
						+ page + "&sort=interestingness-desc&extras=date_taken,url_z,url_" + photoSize);

		ArrayList<FlickrPhoto> list = new ArrayList<FlickrPhoto>();

		Document doc = XMLReader.parse(URL);

		NodeList nodes = doc.getElementsByTagName("photo");

		DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		for (int i = 0; i < nodes.getLength(); i++) {
			Node node = nodes.item(i);
			Element child = (Element) node;

			FlickrPhoto f = new FlickrPhoto();
			f.title = child.getAttribute("title");
			f.photoURL = child.getAttribute("url_" + photoSize);
			f.largePhotoURL = child.getAttribute("url_z");
			f.height = Integer.parseInt(child.getAttribute("height_"
					+ photoSize));
			f.width = Integer
					.parseInt(child.getAttribute("width_" + photoSize));
			f.publishedDate = formatter.parse(child.getAttribute("datetaken"));

			list.add(f);
		}

		return list;
	}
	
	private static List<FlickrCollection> getCollectionList(NodeList nodes) {
		List<FlickrCollection> list = new ArrayList<FlickrCollection>();
		FlickrCollection c;
		for (int i = 0; i < nodes.getLength(); i++) {
			Node node = nodes.item(i);
			if (node.getNodeType() != node.ELEMENT_NODE) {
				continue;
			}
			Element child = (Element) node;
			
			c = new FlickrCollection();
			c.title = child.getAttribute("title");
			c.id = child.getAttribute("id");
			c.thumbnail = child.getAttribute("iconlarge");

			
			NodeList childs;
			if ((childs = child.getChildNodes()) != null) {
				String nodeType = null;
				for (int k = 0; k < childs.getLength(); k++) {
					nodeType = childs.item(k).getNodeName();
					if ("collection".equals(nodeType) || "set".equals(nodeType)) {
						break;
					}
				}
				if ("collection".equals(nodeType)) {
					c.collections = getCollectionList(childs);
				}
				else if ("set".equals(nodeType)) {
					// they are sets
					List<FlickrSet> sets = new ArrayList<FlickrSet>();
					for (int j = 0; j < childs.getLength(); j++) {
						node = childs.item(j);
						if (node.getNodeType() != node.ELEMENT_NODE) {
							continue;
						}
						Element set = (Element) node;
						FlickrSet s = new FlickrSet();
						s.title = set.getAttribute("title");
						s.id = set.getAttribute("id");
						sets.add(s);
					}
					c.sets = sets;
				}
			}
			
			list.add(c);
		}
		
		return list;
	}
	
	
	public static List<FlickrCollection> getCollectionTree() throws SAXException, IOException {
		String method = "flickr.collections.getTree";

		String URL = String.format(flickrURL, method).concat(
				"&user_id=" + myFlickrID);

		Document doc = XMLReader.parse(URL);

		Node n = doc.getElementsByTagName("collections").item(0);
		Element e = (Element) n;
		NodeList nodes = e.getChildNodes();
		
		return getCollectionList(nodes);
	}
}
