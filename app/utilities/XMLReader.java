package utilities;

import java.io.IOException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import play.cache.Cache;

import com.sun.org.apache.xerces.internal.parsers.DOMParser;


public class XMLReader {

	public static Document parse(String uri) throws SAXException, IOException {
		Document doc;
		
		if ((doc = (Document) Cache.get(uri)) != null) {
			return doc;
		}
		
		DOMParser parser = new DOMParser();
		parser.parse(uri);
		doc = parser.getDocument();

		Cache.set(uri,doc,"5mn");
		
		return doc;
	}

	public static String getTagValue(String sTag, Element eElement) {
		NodeList nlList = eElement.getElementsByTagName(sTag).item(0)
				.getChildNodes();

		Node nValue = (Node) nlList.item(0);

		return nValue.getNodeValue();
	}
}
