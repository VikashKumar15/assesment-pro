package com.infy.CoachMs.entity;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name="coach")
@GenericGenerator(name = "coach_id_gen", strategy = "com.infy.CoachMs.generator.CoachIdGenerator")
public class CoachEntity {
	@Id
	@GeneratedValue(generator = "coach_id_gen")
	@Column(name="coach_id")
	private String coachId;
	private String name;
	private char gender;
	//@Temporal(TemporalType.DATE)
	@Column(name="date_of_birth")
	private LocalDate dateOfBirth;
	private String password;
	@Column(name="mobile_number")
	private long mobileNumber;
	private String speciality;
	
	public String getCoachId() {
		return coachId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public char getGender() {
		return gender;
	}
	public void setGender(char gender) {
		this.gender = gender;
	}
	public LocalDate getDateOfBirth() {
		return dateOfBirth;
	}
	public void setDateOfBirth(LocalDate dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
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
	
}
