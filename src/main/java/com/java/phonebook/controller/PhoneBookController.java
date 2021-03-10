package com.java.phonebook.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.java.phonebook.constants.AppConstants;
import com.java.phonebook.entity.Contact;
import com.java.phonebook.exception.NoDataFoundException;
import com.java.phonebook.props.AppProperties;
import com.java.phonebook.service.PhoneService;

@RestController
@RequestMapping("/api/contact")
public class PhoneBookController {

	@Autowired
	private PhoneService phoneService;

	@Autowired
	private AppProperties appProps;

	Logger logger = LoggerFactory.getLogger(PhoneBookController.class);

	@PostMapping
	public ResponseEntity<String> saveContact(@RequestBody Contact contact) {

		logger.debug("**** savecontact() exceution started ****");
		try {
			boolean savedContact = phoneService.saveContact(contact);
			if (savedContact) {
				logger.info("**** saveContact() -contact saved ****");

				String succMsg = appProps.getMessages().get(AppConstants.SAVE_CONTACT_SUCCESS);
				return new ResponseEntity<>(succMsg, HttpStatus.CREATED);
			}
		} catch (Exception e) {
			logger.error("exception occured " + e.getMessage());

		}

		logger.info("***** saveContact() contact  Not saved ***");
		String failMsg = appProps.getMessages().get(AppConstants.SAVE_CONTACT_FAILED);

		return new ResponseEntity<>(failMsg, HttpStatus.INTERNAL_SERVER_ERROR);

	}

	@GetMapping
	public ResponseEntity<List<Contact>> getAllContacts() {
		logger.debug("**** getAllContacts() exceution started ****");
		List<Contact> allContacts = null;
		try {
			allContacts = phoneService.getAllContacts();
			if (allContacts.isEmpty())
				logger.info("*** getAllContacts() ** records not available ***");
		} catch (Exception e) {
			logger.error("exception occured " + e.getMessage());

		}
		logger.debug("**** getAllContacts() exceution ended ****");
		return new ResponseEntity<>(allContacts, HttpStatus.OK);

	}

	@GetMapping("/{contactId}")
	public ResponseEntity<Contact> getContactById(@PathVariable Integer contactId) {

		logger.debug("**** getContactById() exceution started * ***");

		Contact contact = null;
		try {
			contact=phoneService.getContactById(contactId);
			if (contact == null) {
				logger.info("*** getContactById() ** execution started ***");

			throw new NoDataFoundException("contact not found ");
			}

		} catch (Exception e) {
			logger.error("exception occured " + e.getMessage());

		}
		logger.debug("**** getContactById() exceution ended ****");
		return new ResponseEntity<Contact>(contact, HttpStatus.OK);
	}

	/*
	 * @PutMapping("/updatecontact/") public ResponseEntity<String>
	 * updateContact(@RequestBody Contact contact) {
	 * 
	 * if(contact.getContactId()!=null) { boolean
	 * savedContact=phoneService.saveContact(contact); if(savedContact) { return new
	 * ResponseEntity<>("contact updated succesfulluy",HttpStatus.CREATED); } else {
	 * return new
	 * ResponseEntity<>("some error occured",HttpStatus.INTERNAL_SERVER_ERROR); } }
	 * else { return new ResponseEntity<>("bad  request",HttpStatus.BAD_REQUEST); }
	 * }
	 */
	@DeleteMapping("/{contactId}")
	public ResponseEntity<String> deleteContanct(@PathVariable Integer contactId) {
		logger.debug("**** deleteContanct() exceution started ****");
		ResponseEntity<String> responseEntity = null;
		try {
			boolean isDeleted = phoneService.deleteContactById(contactId);
			if (isDeleted) {
				logger.info(" **  deleteContanctContact() record Deleted **");

				String succMsg = appProps.getMessages().get(AppConstants.DELETE_CONTACT_SUCCESS);
				responseEntity = new ResponseEntity<>(succMsg, HttpStatus.OK);
			}
		} catch (Exception e) {
			logger.error("exception occured " + e.getMessage());

		}

		logger.debug("**** deleteContanct() exceution ended ****");
		String failMsg = appProps.getMessages().get(AppConstants.DELETE_CONTACT_FAILED);

		responseEntity = new ResponseEntity<>(failMsg, HttpStatus.INTERNAL_SERVER_ERROR);
		return responseEntity;
	}

}
