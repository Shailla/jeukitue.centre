package jkt.centre.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class News {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="NEW_ID")
	private long id;
	
	@Column(name="NEW_HORODATAGE")
	private Date horodatage;
	
	@Column(name="NEW_TEXT")
	private String text;

	public News() {
	}

	public News(long id, Date horodatage, String text) {
		this.id = id;
		this.horodatage = horodatage;
		this.text = text;
	}

	public long getId() {
		return this.id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Date getHorodatage() {
		return this.horodatage;
	}

	public void setHorodatage(Date horodatage) {
		this.horodatage = horodatage;
	}

	public String getText() {
		return this.text;
	}

	public void setText(String text) {
		this.text = text;
	}
}
