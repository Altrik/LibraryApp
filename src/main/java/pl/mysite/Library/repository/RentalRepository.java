package pl.mysite.Library.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import pl.mysite.Library.entity.Book;
import pl.mysite.Library.entity.Rental;
import pl.mysite.Library.entity.User;

import java.util.List;

public interface RentalRepository extends JpaRepository<Rental, Long> {

	public List <Rental> findById (long id);
	public List <Rental> findByUser (User user);
	public Rental findByBook (Book book);
	
}
