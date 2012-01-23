package models;

import java.util.Date;

import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;

import play.data.validation.Required;

@MappedSuperclass
public abstract class Model extends play.db.jpa.Model {
	@Required
	public int weight;
	
	public Date createdAt;

    public Date updatedAt;

    @PrePersist
    void onPrePersist() {
        if (this.createdAt == null) {
            this.createdAt = new Date();
        }
        this.updatedAt = this.createdAt;
    }

    @PreUpdate
    void onPreUpdate() {
        this.updatedAt = new Date();
    }
}
