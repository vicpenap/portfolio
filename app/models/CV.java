package models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Lob;

import play.data.validation.MaxSize;
import play.data.validation.Required;

@Entity
public class CV extends Model {
	@Required
    @Column(columnDefinition="CLOB")
    @Lob
    @MaxSize(50000)
	public String content;
}
