package jkt.centre.dao;

import org.springframework.data.repository.CrudRepository;

import jkt.centre.model.News;

public interface NewsDao extends CrudRepository<News, Long> {
}
