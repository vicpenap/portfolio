package models.contents.flickr;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.xml.parsers.ParserConfigurationException;

import models.contents.GenericContent;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import play.Play;
import utilities.XMLReader;

@Entity
public class FlickrPhoto extends GenericContent {

	public String photoURL;
	public String largePhotoURL;

	public int height;

	public int width;

	public String toString() {
		return "FlickrPhoto - " + super.toString() + " " + photoURL;
	}
}
