package pl.mysite.Library.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import pl.mysite.Library.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {
	User findByLoginAndPassword(String login, String password);
	User findByLogin(String login);
	User findById(long id);
	//@Query(nativeQuery=true, value="SELECT * FROM users WHERE email= :email ;")
	User findByEmail(/*@Param("email")*/ String email);
}
