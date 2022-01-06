package com.neosoft.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.neosoft.entity.UserInfo;

public interface UserRepository extends JpaRepository<UserInfo, Integer> {
	
	public List<UserInfo> findByFirstNameOrLastNameOrPincode(String firstName, String lastName, String pincode);

}
