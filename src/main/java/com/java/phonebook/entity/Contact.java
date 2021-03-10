package com.java.phonebook.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;


import lombok.Data;


@Entity
@Data
@Table
public class Contact {
	@Id
	@GeneratedValue
	@Column(name = "contactid")
	private Integer contactId;
	@Column(name = "name")
	private String name;
	@Column(name = "email")
	private String email;
	@Column(name = "phoneNo")
	private Long phoneNo;
	

	public Contact() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Contact(int contactId, String name, String email, Long phoneNo) {
		super();
		this.contactId = contactId;
		this.name = name;
		this.email = email;
		this.phoneNo = phoneNo;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Long getPhoneNo() {
		return phoneNo;
	}

	public void setPhoneNo(Long phoneNo) {
		this.phoneNo = phoneNo;
	}

	public void setContactId(int contactId) {
		this.contactId = contactId;
	}

	public Object getContactId() {
		// TODO Auto-generated method stub
		return contactId;
	}

}
