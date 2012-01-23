package models.contents.flickr;

import java.util.List;

public class FlickrSet {

	public String title;
	public String id;
	
	public List<FlickrPhoto> photos;
	public int page;
	public int pageSize;
	public int totalSize;
	
	public String toString() {
		return title;
	}
}
