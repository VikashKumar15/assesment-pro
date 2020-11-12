package com.infy.UserMs.service;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.infy.UserMs.dto.LoginDTO;
import com.infy.UserMs.dto.UserDTO;
import com.infy.UserMs.entity.UserEntity;
import com.infy.UserMs.exception.ExceptionConstants;
import com.infy.UserMs.exception.WeCareException;
import com.infy.UserMs.repository.UserRepository;

@Service
public class UserService implements UserInterface {
	@Autowired
	private UserRepository userRepository;
	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Override
	public String createUser(UserDTO userDTO) {
		logger.info("Inside createUser() method of {}", this.getClass());
		UserEntity userEntity = userRepository.save(userDTO.createEntity());
		logger.info("UserID: " + userEntity.getUserId() + " created");
		return userEntity.getUserId();
	}

	@Override
	public boolean loginUser(LoginDTO loginDTO) throws WeCareException {
		String response;
		logger.info("Inside loginUser() method of {}", this.getClass());
		Optional<UserEntity> optional = userRepository.findByUserId(loginDTO.getId());
		if (optional.isEmpty()) {
			response = ExceptionConstants.USER_NOT_FOUND.toString();
			logger.error("Error: " + response);
			throw new WeCareException(response);
		}
		UserDTO userDTO = UserDTO.valueOf(optional.get());
		if (userDTO.getPassword().equals(loginDTO.getPassword())) {
			logger.info("Success: Loggedin");
			return true;
		}
		logger.info("Failed: Incorrect password");
		return false;
	}

	@Override
	public UserDTO getUserProfile(String userId) {
		logger.info("Inside getUserProfile() method of {}", this.getClass());
		Optional<UserEntity> optional = userRepository.findByUserId(userId);
		if (optional.isEmpty())
			return null;
		return UserDTO.valueOf(optional.get());
	}
	
}
