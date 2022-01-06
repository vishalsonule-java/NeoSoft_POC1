package com.neosoft.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;
import java.util.ArrayList;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.neosoft.config.PropConfig;
import com.neosoft.entity.UserInfo;
import com.neosoft.service.UserManagementService;

@WebMvcTest(UserController.class)
public class UserControllerTest {
	
	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private UserManagementService service;
	
	@MockBean
	private PropConfig appProperties;
	
	private static UserInfo user;
	
	private static ObjectMapper mapper;
	
	@BeforeAll
	public static void init() {
		user = new UserInfo();
		user.setCity("nanded");
		user.setDob(LocalDate.now());
		user.setDoj(LocalDate.now());
		user.setEmail("vishal2@gmail.com");
		user.setFirstName("vishal");
		user.setLastName("sonule");
		user.setIsActive(true);
		user.setMobileNo(9011349907l);
		user.setPincode("431505");
		
		mapper=new ObjectMapper();
		mapper.registerModule(new JavaTimeModule());
		
	}
	
	@Test
	public void registerUserTest_01() throws Exception {
				
		Mockito.doNothing().when(service).saveUser(Mockito.any(UserInfo.class));
		MockHttpServletRequestBuilder requestBuilder= MockMvcRequestBuilders.post("/user/save")
																			.contentType(MediaType.APPLICATION_JSON)
																			.content(mapper.writeValueAsString(user));
		MvcResult mvcResult = mockMvc.perform(requestBuilder).andReturn();
		int status = mvcResult.getResponse().getStatus();
		assertEquals(201, status);
	}
	
	@Test
	public void updateUserTest_01() throws Exception {
		
		Mockito.doNothing().when(service).updateUser(Mockito.any(UserInfo.class));
		MockHttpServletRequestBuilder requestBuilder= MockMvcRequestBuilders.put("/user/update")
																			.contentType(MediaType.APPLICATION_JSON)
																			.content(mapper.writeValueAsString(user));
		MvcResult mvcResult = mockMvc.perform(requestBuilder).andReturn();
		int status = mvcResult.getResponse().getStatus();
		assertEquals(200, status);
	}

	@Test
	public void findByTest_01() throws Exception {
		Mockito.when(service.getUsersByNameOrSurNameOrPincode(Mockito.anyString(), Mockito.anyString(),Mockito.anyString()))
																					.thenReturn(new ArrayList<>());
		MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/user/findBy")
																				.param("firstName", "")
																				.param("lastName","")
																				.param("pincode", "");
		MvcResult mvcResult = mockMvc.perform(requestBuilder).andReturn();
		int status = mvcResult.getResponse().getStatus();
		assertEquals(200, status);
		
	}
	
	@Test
	public void findAllUsersTest_01() throws Exception {
		Mockito.when(service.getAllUsers()).thenReturn(new ArrayList<>());
		MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/user/findAllUsers");
		MvcResult mvcResult = mockMvc.perform(requestBuilder).andReturn();
		int status = mvcResult.getResponse().getStatus();
		assertEquals(200, status);
		
	}
	
	@Test
	public void deleteUserTest_01() throws Exception {
		Mockito.doNothing().when(service).deleteUserById(Mockito.anyInt());
		String url="/user/delete/{id}";
		MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.delete(url,Mockito.anyInt());
		MvcResult mvcResult = mockMvc.perform(requestBuilder).andReturn();
		int status = mvcResult.getResponse().getStatus();
		assertEquals(200, status);
		
	}
	
	@Test
	public void softdeleteUserTest_01() throws Exception {
		Mockito.doNothing().when(service).softDeleteUserById(Mockito.anyInt());
		MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/user/softDelete/{id}",Mockito.anyInt());
		MvcResult mvcResult = mockMvc.perform(requestBuilder).andReturn();
		int status = mvcResult.getResponse().getStatus();
		assertEquals(200, status);
		
	}
}
