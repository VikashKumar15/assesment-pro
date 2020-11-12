package com.infy.UserMs.service;

import com.infy.UserMs.dto.LoginDTO;
import com.infy.UserMs.dto.UserDTO;
import com.infy.UserMs.exception.WeCareException;

public interface UserInterface {
	String createUser(UserDTO userDTO);
	
	boolean loginUser(LoginDTO loginDTO) throws WeCareException;
	
	UserDTO getUserProfile(String userId);
}
