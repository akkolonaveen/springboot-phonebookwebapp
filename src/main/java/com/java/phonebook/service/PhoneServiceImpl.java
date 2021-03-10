package com.java.phonebook.service;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.java.phonebook.dao.PhoneBookDAO;
import com.java.phonebook.entity.Contact;
import com.java.phonebook.exception.NoDataFoundException;


@Service
public class PhoneServiceImpl implements PhoneService{
	
	@Autowired
	private PhoneBookDAO phoneDAO;

	@Override
	public boolean saveContact(Contact contact) {
		Contact savedContact=phoneDAO.save(contact);
		return savedContact.getContactId()!=null;
	}

	
	  @Override
	  public List<Contact> getAllContacts()
	  { // TODO Auto-generated
	   return phoneDAO.findAll();
	   }
	  
	

	@Override
	public Contact getContactById(Integer contactId) {
		Optional<Contact> findById=phoneDAO.findById(contactId);
		if(findById.isPresent())
		{
			return findById.get();
		}
		  return null;
	}



	@Override
	public boolean deleteContactById(Integer contactId) {
		try {
			phoneDAO.deleteById(contactId);
			
		}
		catch(Exception e)
		{
			throw new NoDataFoundException("No record found to delete");
		}
		
	return false;
	
	}

	
	
}
