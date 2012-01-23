package models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.Lob;


import play.data.validation.MaxSize;
import play.data.validation.Required;

@Entity
public class Project extends Model {
	@Required
	public String title;
	
	@Required
	@Enumerated
	public ProjectType type;
	
    @Column(columnDefinition="CLOB")
    @Lob
    @MaxSize(10000)
	public String description;
	
    @play.data.validation.URL
    public String URL;

    @play.data.validation.URL
    public String thumbnailURL;

    
	public String toString() {
		return title;
	}	
}
