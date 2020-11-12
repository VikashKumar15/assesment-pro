package com.infy.CoachMs.dto;

import java.time.LocalDate;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.infy.CoachMs.entity.CoachEntity;

public class CoachDTO {
	private String coachId;
	@NotNull(message = "password cannot be null")
	@Size(min = 5, max = 10, message = "password size should be between 5 and 10")
	private String password;
	@NotNull(message = "name cannot be null")
	@Size(min = 3, max = 50, message = "name size should be between 5 and 50")
	private String name;
	private LocalDate dateOfBirth;
	//@NotBlank(message = "Gender cannot be blank")
	private char gender;
	@Min(value = 1000000000l, message = "mobileNumber cannot be null and should be 10 digits")
	@Max(value = 9999999999l, message = "mobileNumber cannot be null and should be 10 digits")
	private long mobileNumber;
	@NotNull(message = "speciality cannot be null")
	@Size(min = 3, max = 50, message = "name size should be between 5 and 50")
	private String speciality;
	
	public String getCoachId() {
		return coachId;
	}
	public void setCoachId(String coachId) {
		this.coachId = coachId;
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
	public String getSpeciality() {
		return speciality;
	}
	public void setSpeciality(String speciality) {
		this.speciality = speciality;
	}
	
	public static CoachDTO valueOf(CoachEntity coachEntity) {
		CoachDTO coachDTO = new CoachDTO();
		
		coachDTO.setCoachId(coachEntity.getCoachId());
		coachDTO.setPassword(coachEntity.getPassword());
		coachDTO.setName(coachEntity.getName());
		coachDTO.setDateOfBirth(coachEntity.getDateOfBirth());
		coachDTO.setGender(coachEntity.getGender());
		coachDTO.setMobileNumber(coachEntity.getMobileNumber());
		coachDTO.setSpeciality(coachEntity.getSpeciality());
		
		return coachDTO;
	}
	
	public CoachEntity createEntity() {
		CoachEntity coachEntity = new CoachEntity();
		coachEntity.setPassword(this.password);
		coachEntity.setName(this.name);
		coachEntity.setDateOfBirth(this.dateOfBirth);
		coachEntity.setGender(this.gender);
		coachEntity.setMobileNumber(this.mobileNumber);
		coachEntity.setSpeciality(this.speciality);
		return coachEntity;
	}
}
