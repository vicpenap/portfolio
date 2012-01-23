package models.contents;

import java.util.Date;

import models.Model;
import play.data.validation.Required;


public abstract class GenericContent extends Model {

	@Required
	public String title;
	
	@Required
	public Date publishedDate;


	
	public String toString() {
		return title + publishedDate.toString();
	}
}
