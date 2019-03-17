package jkt.centre.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

import jkt.centre.model.User;

public interface UserDao extends CrudRepository<User, Long> {
	long count();
	User findByLogin(String login);
	Page<User> findAll(Pageable pageable);
	boolean existsByLogin(String login);
	boolean existsByMail(String login);
}
