package models.contents.flickr;

import java.util.List;

public class FlickrCollection {

	public String title;
	public String id;
	public String thumbnail;
	
	public List<FlickrCollection> collections;
	public List<FlickrSet> sets;

	public String toString() {
		String str = title;
		if (sets != null) {
			str += " -> ";
			str += sets.toString() + " ";
		}
		if (collections != null) {
			str += " { ";
			for (FlickrCollection c : collections) {
				str += c.toString() + " ";
			}
			str += " } ";
		}
		return str;
	}
}
