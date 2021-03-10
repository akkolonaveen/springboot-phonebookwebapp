package com.java.phonebook.controller;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import java.util.Collections;

import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.java.phonebook.entity.Contact;
import com.java.phonebook.props.AppProperties;
import com.java.phonebook.service.PhoneService;

@WebMvcTest(value = PhoneBookController.class)
public class PhonebookRestControllerTest {

	@MockBean
	private PhoneService service;
	
	@Autowired
	private AppProperties props;

	@Autowired
	private MockMvc mockMvc;

	@Test
	public void test_saveContact_1() throws Exception {
		Contact c = new Contact(101, "ashok", "ashokit.in", (long) 707525281);
		when(service.saveContact(Mockito.any())).thenReturn(true);
		
		ObjectMapper mapper = new ObjectMapper();
		String json = null;
		try {
			json = mapper.writeValueAsString(c);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}

		MockHttpServletRequestBuilder reqBuilder = MockMvcRequestBuilders.post("/api/contact")
				.contentType("application/json").content(json);
		MvcResult mvcResult = mockMvc.perform(reqBuilder).andReturn();

		MockHttpServletResponse response = mvcResult.getResponse();
		int status = response.getStatus();
		assertEquals(201, status);

	}

	@Test
	public void test_saveContact_2() throws Exception {
		when(service.saveContact(Mockito.any())).thenReturn(false);
		Contact c = new Contact(101, "ashok", "ashokit.in", (long) 707525281);
		ObjectMapper mapper = new ObjectMapper();
		String json = null;
		try {
			json = mapper.writeValueAsString(c);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}

		MockHttpServletRequestBuilder reqBuilder = MockMvcRequestBuilders.post("/api/contact")
				.contentType("application/json").content(json);
		MvcResult mvcResult = mockMvc.perform(reqBuilder).andReturn();

		MockHttpServletResponse response = mvcResult.getResponse();
		int status = response.getStatus();
		assertEquals(500, status);

	}

	@Test
	public void test_saveContact_3() throws Exception {
		when(service.saveContact(Mockito.any())).thenThrow(RuntimeException.class);
		Contact c = new Contact(101, "ashok", "ashokit.in", (long) 707525281);
		ObjectMapper mapper = new ObjectMapper();
		String json = null;
		try {
			json = mapper.writeValueAsString(c);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}

		MockHttpServletRequestBuilder reqBuilder = MockMvcRequestBuilders.post("/api/contact")
				.contentType("application/json").content(json);
		MvcResult mvcResult = mockMvc.perform(reqBuilder).andReturn();

		MockHttpServletResponse response = mvcResult.getResponse();
		int status = response.getStatus();
		assertEquals(500, status);

	}

	@Test
	public void getAllContacts_test_1() throws Exception {
		when(service.getAllContacts()).thenReturn(Collections.emptyList());

		MockHttpServletRequestBuilder reqBuilder = MockMvcRequestBuilders.get("/api/contact");
		MvcResult mvcResult = mockMvc.perform(reqBuilder).andReturn();
		MockHttpServletResponse response = mvcResult.getResponse();
		int status = response.getStatus();
		assertEquals(200, status);

	}

	@Test
	public void getAllContacts_test_2() throws Exception {
		when(service.getAllContacts()).thenThrow(RuntimeException.class);

		MockHttpServletRequestBuilder reqBuilder = MockMvcRequestBuilders.get("/api/contact");
		MvcResult mvcResult = mockMvc.perform(reqBuilder).andReturn();
		MockHttpServletResponse response = mvcResult.getResponse();
		int status = response.getStatus();
		assertEquals(200, status);

	}

	@Test
	public void getContactById_test_1() throws Exception {
		Contact c = new Contact(101, "ashok", "ashokit.in", (long) 707525281);
		when(service.getContactById(101)).thenReturn(c);

		MockHttpServletRequestBuilder reqBuilder = MockMvcRequestBuilders.get("/api/contact/101");
		MvcResult mvcResult = mockMvc.perform(reqBuilder).andReturn();
		MockHttpServletResponse response = mvcResult.getResponse();
		int status = response.getStatus();
		assertEquals(200, status);

	}

	@Test
	public void getContactById_test_2() throws Exception {

		when(service.getContactById(101)).thenThrow(RuntimeException.class);

		MockHttpServletRequestBuilder reqBuilder = MockMvcRequestBuilders.get("/api/contact/101");
		MvcResult mvcResult = mockMvc.perform(reqBuilder).andReturn();
		MockHttpServletResponse response = mvcResult.getResponse();
		int status = response.getStatus();
		assertEquals(500, status);

	}

	@Test
	public void deleteContact_test_01() throws Exception {
		Contact c = new Contact(101, "ashok", "ashokit.in", (long) 707525281);

		when(service.deleteContactById(101)).thenReturn(true);

		MockHttpServletRequestBuilder reqBuilder = MockMvcRequestBuilders.get("/api/contact/101");
		MvcResult mvcResult = mockMvc.perform(reqBuilder).andReturn();
		MockHttpServletResponse response = mvcResult.getResponse();
		int status = response.getStatus();
		assertEquals(200, status);

	}

	@Test
	public void deleteContact_test_02() throws Exception {

		when(service.deleteContactById(101)).thenReturn(false);

		MockHttpServletRequestBuilder reqBuilder = MockMvcRequestBuilders.get("/api/contact/101");
		MvcResult mvcResult = mockMvc.perform(reqBuilder).andReturn();
		MockHttpServletResponse response = mvcResult.getResponse();
		int status = response.getStatus();
		assertEquals(500, status);

	}

	@Test
	public void deleteContact_test_03() throws Exception {

		when(service.deleteContactById(101)).thenThrow(RuntimeException.class);

		MockHttpServletRequestBuilder reqBuilder = MockMvcRequestBuilders.get("/api/contact/101");
		MvcResult mvcResult = mockMvc.perform(reqBuilder).andReturn();
		MockHttpServletResponse response = mvcResult.getResponse();
		int status = response.getStatus();
		assertEquals(500, status);

	}

}
