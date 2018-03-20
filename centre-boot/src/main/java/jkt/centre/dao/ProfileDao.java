package jkt.centre.dao;

import org.springframework.data.repository.CrudRepository;

import jkt.centre.model.Profile;

public interface ProfileDao extends CrudRepository<Profile, String> {
}
