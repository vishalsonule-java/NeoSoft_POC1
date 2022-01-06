package com.neosoft.entity;

import java.io.Serializable;
import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.neosoft.constant.AppConstant;

import lombok.Data;


@Data
@Entity
@Table(name="USER_TABLE")
public class UserInfo implements Serializable {

	/**
	 * default serial id
	 */
	private static final long serialVersionUID = 1L;
		
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	
	@NotEmpty(message = AppConstant.FIRST_NAME_NOT_EMTPY)
	@Pattern(regexp = AppConstant.REGUALR_EX_FOR_NAME,message = AppConstant.FIRST_NAME_NOT_VALID_PATTERN)
	private String firstName;
	
	@NotEmpty(message = AppConstant.LAST_NAME_NOT_EMTPY)
	@Pattern(regexp = AppConstant.REGUALR_EX_FOR_NAME,message = AppConstant.LAST_NAME_NOT_VALID_PATTERN)
	private String lastName;
	
	@NotNull(message = AppConstant.DOB_NOT_NULL)
	
	private LocalDate dob;
	
	@NotNull(message = AppConstant.DOJ_NOT_NULL)
	private LocalDate doj;
	
	@NotEmpty(message =AppConstant.CITY_NOT_EMPTY)
	private String city;
	private String pincode;
	
	private Long mobileNo;
	
	@Email(message = AppConstant.EMAIL_NOT_VALID, regexp = AppConstant.REGUALR_EX_FOR_EMAIL)
	@NotEmpty(message = AppConstant.EMAIL_NOT_EMPTY)
	private String email;
	private Boolean isActive;
}
