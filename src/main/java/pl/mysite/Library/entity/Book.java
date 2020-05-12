package pl.mysite.Library.entity;

import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
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
	private String author;
	private int releaseDate;
	@CreationTimestamp
	private Date dateOfAcquisition;
	private Boolean isBorrowed;
	@ManyToOne
	private User borrowedTo;
	
	public Book () {}
	public Book (String title, String author, int releaseDate) {
		this.title = title;
		this.author = author;
		this.releaseDate = releaseDate;
		this.isBorrowed = false;
	}
	public Book (String title, String author, int releaseDate, Date dateOfAcquisition) {
		this.title = title;
		this.author = author;
		this.releaseDate = releaseDate;
		this.dateOfAcquisition = dateOfAcquisition;
		this.isBorrowed = false;
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
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
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
	public User getBorrowedTo() {
		return borrowedTo;
	}
	public void setBorrowedTo(User borrowedTo) {
		this.borrowedTo = borrowedTo;
	}
	public int getReleaseDate() {
		return releaseDate;
	}
	public void setReleaseDate(int releaseDate) {
		this.releaseDate = releaseDate;
	}
	
}
