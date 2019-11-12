package pl.mysite.Library.controller;

import java.sql.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;

@Entity
@Table(name="Books")
public class Book {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	@NotEmpty
	private String title;
	@NotEmpty
	private List <Author> authors; //wiele do wielu dorobiæ
	@CreationTimestamp
	private Date dateOfAcquisition;
	@NotEmpty
	@ColumnDefault("false")
	private Boolean isBorrowed;
	
	public Book () {}
	public Book (String title, List<Author> authors, Date dateOfAcquisition) {
		this.title = title;
		this.authors = authors;
		this.dateOfAcquisition = dateOfAcquisition;
	}
	public Book (long id, String title, List<Author> authors, Date dateOfAcquisition) {
		this.id = id;
		this.title = title;
		this.authors = authors;
		this.dateOfAcquisition = dateOfAcquisition;
	}
	
	public long getId() {
		return id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public List<Author> getAuthors() {
		return authors;
	}
	public void setAuthors(List<Author> authors) {
		this.authors = authors;
	}
	public Date getDateOfAcquisition() {
		return dateOfAcquisition;
	}
	public void setDateOfAcquisition(Date dateOfAcquisition) {
		this.dateOfAcquisition = dateOfAcquisition;
	}
	public Boolean getIsBorrowed() {
		return isBorrowed;
	}
	public void setIsBorrowed(Boolean isBorrowed) {
		this.isBorrowed = isBorrowed;
	}
	
	
	
}
