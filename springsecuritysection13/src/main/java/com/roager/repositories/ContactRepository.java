package com.roager.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.roager.models.Contact;

@Repository
public interface ContactRepository extends CrudRepository<Contact, Long> {
	
	
}
