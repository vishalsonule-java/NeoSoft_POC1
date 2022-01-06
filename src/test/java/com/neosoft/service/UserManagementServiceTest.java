package com.neosoft.service;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Sort;

import com.neosoft.config.PropConfig;
import com.neosoft.entity.UserInfo;
import com.neosoft.exception.UserNotFoundException;
import com.neosoft.repo.UserRepository;

@SpringBootTest
public class UserManagementServiceTest {
	
	@MockBean
	private UserRepository repo;
	
	@MockBean
	private PropConfig appProperties;
		
	@Autowired
	private UserManagementService service;
	
	private static UserInfo user;
	
	private static Optional<UserInfo> userOptional;
	
	private static List<UserInfo> list;
	
	@BeforeAll
	private static void init() {
		user= new UserInfo();
		userOptional = Optional.of(user);
		
		list= new ArrayList<>();
		list.add(new UserInfo());
	}
	
	
	@Test
	public void saveUserTest() {
		Mockito.when(repo.save(Mockito.any(UserInfo.class))).thenReturn(Mockito.any(UserInfo.class));
		service.saveUser(user);
		Mockito.verify(repo).save(Mockito.any(UserInfo.class));
		
	}
	
	@Test
	public void updateUserTest() {
		Mockito.when(repo.save(Mockito.any(UserInfo.class))).thenReturn(Mockito.any(UserInfo.class));
		service.updateUser(user);
		Mockito.verify(repo).save(Mockito.any(UserInfo.class));
		
	}
	
	@Test
	public void getUserByIdTest_01() {
		Mockito.when(repo.findById(Mockito.anyInt())).thenReturn(userOptional);
		UserInfo userById = service.getUserById(Mockito.anyInt());
		assertNotNull(userById);
	}
	
	@Test
	public void getUserByIdTest_02() {
		Optional<UserInfo> userOptional = Optional.empty();
		Mockito.when(repo.findById(Mockito.anyInt())).thenReturn(userOptional);
		assertThrows(UserNotFoundException.class, ()->{service.getUserById(Mockito.anyInt());});
	}
	
	@Test
	public void getUsersByNameOrSurNameOrPincodeTest_01() {
		
		Mockito.when(repo.findByFirstNameOrLastNameOrPincode(Mockito.anyString(),Mockito.anyString(),Mockito.anyString())).thenReturn(list);
		List<UserInfo> userList = service.getUsersByNameOrSurNameOrPincode(Mockito.anyString(),Mockito.anyString(), Mockito.anyString());
		assertNotNull(userList);
	}
	
	@Test
	public void getUsersByNameOrSurNameOrPincodeTest_02() {
		
		assertThrows(UserNotFoundException.class, ()->{
			service.getUsersByNameOrSurNameOrPincode(Mockito.anyString(), Mockito.anyString(),Mockito.anyString());});
	}
	
	@Test
	public void deleteUserByIdTest() {
		Mockito.doNothing().when(repo).deleteById(Mockito.anyInt());
		repo.deleteById(101);
		Mockito.verify(repo).deleteById(Mockito.anyInt());
		//service.deleteUserById(Mockito.anyInt());
		
	}
	
	@Test
	public void softDeleteUserByIdTest_01() {
		user.setId(101);
		Optional<UserInfo> userOptional = Optional.of(user);
		Mockito.when(repo.save(Mockito.any(UserInfo.class))).thenReturn(user);
		Mockito.when(repo.findById(Mockito.anyInt())).thenReturn(userOptional);
		service.softDeleteUserById(Mockito.anyInt());
		Mockito.verify(repo).save(Mockito.any(UserInfo.class));
		
	}
	
	@Test
	public void softDeleteUserByIdTest_02() {
		userOptional = Optional.empty();
		Mockito.when(repo.save(Mockito.any(UserInfo.class))).thenReturn(user);
		Mockito.when(repo.findById(Mockito.anyInt())).thenReturn(userOptional);
		assertThrows(UserNotFoundException.class, ()->{service.softDeleteUserById(Mockito.anyInt());});
		
	}
	
	@Test
	public void getAllUsersTest_01() {
		Mockito.when(repo.findAll()).thenReturn(Mockito.anyList());
		assertThrows(UserNotFoundException.class, ()->{service.getAllUsers();});
		
	}
	
	@Test
	public void getAllUsersTest_02() {
		Mockito.when(repo.findAll(Sort.by("dob","doj"))).thenReturn(list);
		List<UserInfo> allUsers = service.getAllUsers();
		assertNotNull(allUsers);
		
	}

}
