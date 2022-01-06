package com.neosoft.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.neosoft.config.PropConfig;
import com.neosoft.constant.AppConstant;
import com.neosoft.entity.UserInfo;
import com.neosoft.exception.UserNotFoundException;
import com.neosoft.repo.UserRepository;

@Service
public class UserManagementServiceImpl implements UserManagementService {

	private UserRepository userRepo;
	private PropConfig appProperties;
	
	public UserManagementServiceImpl(UserRepository userRepo,PropConfig appProperties) {
		this.userRepo=userRepo;
		this.appProperties=appProperties;
	}

	@Override
	public void saveUser(UserInfo user) {
		userRepo.save(user);
	}

	@Override
	public void updateUser(UserInfo user) {
		userRepo.save(user);
	}

	@Override
	public UserInfo getUserById(Integer id) {
		Optional<UserInfo> optional = userRepo.findById(id);
		if (optional.isPresent())
			return optional.get();
		else
			throw new UserNotFoundException(appProperties.getProperties().get(AppConstant.ID_NOT_FOUND));
	}

	@Override
	public List<UserInfo> getUsersByNameOrSurNameOrPincode(String firstName, String lastName, String pincode) {
		List<UserInfo> usersList = userRepo.findByFirstNameOrLastNameOrPincode(firstName, lastName, pincode);
		if (usersList != null && !usersList.isEmpty())
			return usersList;
		else
			throw new UserNotFoundException(appProperties.getProperties().get(AppConstant.USER_NOT_FOUND));
	}

	@Override
	public void deleteUserById(Integer id) {
		userRepo.deleteById(id);
	}

	@Override
	public void softDeleteUserById(Integer id) {
		UserInfo userInfo = null;
		Optional<UserInfo> optional = userRepo.findById(id);
		if (optional.isPresent()) {
			userInfo = optional.get();
			userInfo.setIsActive(false);
		} else {
			throw new UserNotFoundException(appProperties.getProperties().get(AppConstant.ID_NOT_FOUND));
		}
		userRepo.save(userInfo);
	}

	@Override
	public List<UserInfo> getAllUsers() {
		List<UserInfo> usersList = userRepo.findAll(Sort.by("dob", "doj"));
		if (usersList != null && !usersList.isEmpty())
			return usersList;
		else
			throw new UserNotFoundException(appProperties.getProperties().get(AppConstant.EMPTY_DATA_BASE));

	}

}
