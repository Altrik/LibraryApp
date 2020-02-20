package pl.mysite.Library.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import pl.mysite.Library.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {
	User findByLoginAndPassword(String login, String password);
}
