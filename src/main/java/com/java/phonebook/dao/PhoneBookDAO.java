package com.java.phonebook.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.java.phonebook.entity.Contact;

@Repository
public interface PhoneBookDAO extends JpaRepository<Contact, Integer> {

}
