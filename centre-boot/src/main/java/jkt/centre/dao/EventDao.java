package jkt.centre.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

import jkt.centre.model.Event;

public interface EventDao extends CrudRepository<Event, Long> {
	Page<Event> findAll(Pageable pageable);
}
