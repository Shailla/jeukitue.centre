package jkt.centre.dao;

import org.springframework.data.repository.CrudRepository;

import jkt.centre.model.User;

public interface UserDao extends CrudRepository<User, Long> {
	User findByLogin(String login);
}
