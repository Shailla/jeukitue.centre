package jkt.centre.dao;

import org.springframework.data.repository.CrudRepository;

import jkt.centre.model.Download;

public interface DownloadDao extends CrudRepository<Download, Long> {
}
