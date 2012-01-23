package models;

import javax.persistence.Entity;

import play.data.validation.Required;
import play.data.validation.URL;

@Entity
public class SocialNetwork extends Model {

	@Required
	public String name;
	
	@Required
	@URL
	public String URL;
	
	@Required
	@URL
	public String thumbnailURL;
	
	
	
	public String toString() {
		return name;
	}
}
