package com.infy.BookingMs.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class LoginDTO {
	@NotBlank(message = "User Id must not be null")
	private String id;
	@NotNull(message = "password cannot be null")
	@Size(min = 5, max = 10, message = "password size should be between 5 and 10")
	private String password;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
}
