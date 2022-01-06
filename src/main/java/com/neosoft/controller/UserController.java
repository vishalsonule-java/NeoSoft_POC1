package com.neosoft.controller;

import java.time.LocalDate;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.neosoft.config.PropConfig;
import com.neosoft.constant.AppConstant;
import com.neosoft.entity.UserInfo;
import com.neosoft.service.UserManagementService;

@RestController
@RequestMapping("/user")
public class UserController {

	private UserManagementService service;
	private PropConfig appProperties;
	
	public UserController(UserManagementService service,PropConfig appProperties) {
		this.service=service;
		this.appProperties=appProperties;
	}

	@PostMapping("/save")
	public ResponseEntity<String> RegisterUser(@Valid @RequestBody UserInfo user) {
		service.saveUser(user);
		return new ResponseEntity<String>(appProperties.getProperties().get(AppConstant.USER_SAVE), HttpStatus.CREATED);

	}

	@PutMapping("/update")
	public ResponseEntity<String> UpdateUser(@Valid @RequestBody UserInfo user) {

		service.updateUser(user);
		return new ResponseEntity<String>(appProperties.getProperties().get(AppConstant.USER_UPDATE), HttpStatus.OK);
	}

	@GetMapping("/findBy")
	public ResponseEntity<List<UserInfo>> getUserByProperties(@RequestParam(name = "firstName",required = false) String firstName,
															@RequestParam(name = "lastName",required = false) String lastName,
															@RequestParam(name = "pincode",required = false) String pincode) {

		List<UserInfo> usersList = service.getUsersByNameOrSurNameOrPincode(firstName, lastName, pincode);
		return new ResponseEntity<List<UserInfo>>(usersList, HttpStatus.OK);

	}
	
	@GetMapping("/findAllUsers")
	public ResponseEntity<List<UserInfo>> getAllUser() {
		
		List<UserInfo> allUsers = service.getAllUsers();
		return new ResponseEntity<List<UserInfo>>(allUsers, HttpStatus.OK);

	}
	
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<String> deleteUser(@PathVariable(name="id") Integer id){
		
		service.deleteUserById(id);
		return new ResponseEntity<String>(appProperties.getProperties().get(AppConstant.USER_DELETE), HttpStatus.OK);
	}
	
	@DeleteMapping("/softDelete/{id}")
	public ResponseEntity<String> softdeleteUser(@PathVariable Integer id){
		
		service.softDeleteUserById(id);
		return new ResponseEntity<String>(appProperties.getProperties().get(AppConstant.USER_DELETE), HttpStatus.OK);
	}
	
	@GetMapping("/test")
	public ResponseEntity<UserInfo> test(){
		
		UserInfo user= new UserInfo();
		user.setFirstName("vishal");
		user.setLastName("sonule");
		user.setCity("Nanded");
		user.setDob(LocalDate.now());
		user.setDoj(LocalDate.now());
		user.setEmail("vishalsonule2@gmail.com");
		user.setIsActive(true);
		user.setMobileNo(9011349907l);
		user.setPincode("431605");
		return new ResponseEntity<UserInfo>(user,HttpStatus.OK);
		
	}
}
