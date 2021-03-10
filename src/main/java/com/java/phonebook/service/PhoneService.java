package com.java.phonebook.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.java.phonebook.entity.Contact;



public interface PhoneService {
	
	
	public boolean saveContact(Contact contact);
	
	public List<Contact> getAllContacts();
	
	public Contact getContactById(Integer contactId);
	
	//public boolean updateContact(Contact contact);
	
	
	public boolean deleteContactById(Integer contactId);

	
	

}
