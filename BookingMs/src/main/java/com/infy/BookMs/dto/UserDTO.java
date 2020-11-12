package com.infy.BookMs.dto;

import java.time.LocalDate;

import javax.validation.constraints.Digits;
import javax.validation.constraints.Email;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

//import com.lex.entity.UserEntity;

public class UserDTO {
	private String userId;
	@NotNull(message = "password cannot be null")
	@Size(min = 5, max = 10, message = "password size should be between 5 and 10")
	private String password;
	@NotNull(message = "name cannot be null")
	@Size(min = 3, max = 50, message = "name size should be between 5 and 50")
	private String name;
	private LocalDate dateOfBirth;
//	@NotBlank(message = "Gender cannot be blank")
	private char gender;
	@Min(value = 1000000000l, message = "mobileNumber cannot be null and should be 10 digits")
	@Max(value = 9999999999l, message = "mobileNumber cannot be null and should be 10 digits")
	@Digits(integer = 10 , fraction = 0)
	private long mobileNumber;
	@Email(message = "email is not valid")
	private String email;
	@Min(value = 100000, message = "pincode cannot be null and should be 6 digits")
	@Max(value = 999999, message = "pincode cannot be null and should be 10 digits")
	private int pincode;
	@NotNull(message = "city cannot be null")
	@Size(min = 3, max = 20, message = "city size should be between 3 and 20")
	private String city;
	@NotNull(message = "state cannot be null")
	@Size(min = 3, max = 20, message = "state size should be between 3 and 20")
	private String state;
	@NotNull(message = "country cannot be null")
	@Size(min = 3, max = 20, message = "country size should be between 3 and 20")
	private String country;
	
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public LocalDate getDateOfBirth() {
		return dateOfBirth;
	}
	public void setDateOfBirth(LocalDate dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}
	public char getGender() {
		return gender;
	}
	public void setGender(char gender) {
		this.gender = gender;
	}
	public long getMobileNumber() {
		return mobileNumber;
	}
	public void setMobileNumber(long mobileNumber) {
		this.mobileNumber = mobileNumber;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public int getPincode() {
		return pincode;
	}
	public void setPincode(int pincode) {
		this.pincode = pincode;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	
	
}
