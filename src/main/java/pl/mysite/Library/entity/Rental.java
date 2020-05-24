package pl.mysite.Library.entity;

import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;

@Entity
@Table(name="Rentals")
public class Rental {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	@OneToOne
	private Book book;
	@ManyToOne
	private User user;
	@CreationTimestamp
	private Date dayOfRental;
	
	public Rental () {}
	
	public Rental (Book book, User user) {
		this.book = book;
		this.user = user;
	}
	
	
	public Book getBook() {
		return book;
	}

	public void setBook(Book book) {
		this.book = book;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Date getDayOfRental() {
		return dayOfRental;
	}
	
	public void setDayOfRental(Date dayOfRental) {
		this.dayOfRental = dayOfRental;
	}
	
	public long getId() {
		return id;
	}
	
	
}
