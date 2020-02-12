package pl.mysite.Library.repository;

import java.sql.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import pl.mysite.Library.entity.Book;

public interface BookRepository extends JpaRepository<Book, Long> {
	public List <Book> findById(long id);
	public List <Book> findByTitle(String title);
	public List <Book> findByAuthor(String author);
	public List <Book> findByDateOfAcquisition(Date dateOfAcquisition);
	public List <Book> findByIsBorrowed(Boolean isBorrowed);
}