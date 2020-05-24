package pl.mysite.Library.repository;

import java.sql.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import pl.mysite.Library.entity.Book;

public interface BookRepository extends JpaRepository<Book, Long> {
	public Book findById(long id);
	public List <Book> findByTitle(String title);
	public List <Book> findByAuthor(String author);
	public List <Book> findByDateOfAcquisition(Date dateOfAcquisition);
	public List <Book> findByIsBorrowed(Boolean isBorrowed);
	public List <Book> findByReleaseDate(int releaseDate);
	/*@Query(nativeQuery=true, value="SELECT * FROM books WHERE borrowedTo_id = :id ;")
	public List <Book> findBorrowedByUser(@Param("id") Long id);*/
}