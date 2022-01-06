package com.neosoft.service;

import java.util.List;

import com.neosoft.entity.UserInfo;

public interface UserManagementService {
	
	public void saveUser(UserInfo user);
	public void updateUser(UserInfo user);
	public UserInfo getUserById(Integer id);
	public List<UserInfo> getUsersByNameOrSurNameOrPincode(String firstName, String LastName, String pincode);
	public void deleteUserById(Integer id);
	public void softDeleteUserById(Integer id);
	public List<UserInfo> getAllUsers();

}
