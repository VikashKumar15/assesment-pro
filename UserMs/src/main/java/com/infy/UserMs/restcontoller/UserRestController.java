package com.infy.UserMs.restcontoller;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.infy.UserMs.configuration.UserRibbonConfig;
import com.infy.UserMs.dto.BookingDTO;
import com.infy.UserMs.dto.LoginDTO;
import com.infy.UserMs.dto.UserDTO;
import com.infy.UserMs.exception.WeCareException;
//import com.infy.UserMs.service.BookInterface;
import com.infy.UserMs.service.UserInterface;

@RestController
@RequestMapping("/users")
@EnableAutoConfiguration
@RibbonClient(name = "userRibbon" )
public class UserRestController {
	@Autowired
	private UserInterface userService;
	
	@Autowired
	RestTemplate template;
//	
//	@Value("${book.uri}")
//	String bookuri;
//	@Autowired
//	private BookInterface bookService;
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@PostMapping
	public ResponseEntity<String> createUser(@Valid @RequestBody UserDTO userDTO, Errors error) {
		String response;
		logger.info("Inside createUser() method of {}", this.getClass());
		if (error.hasErrors()) {
			response = error.getAllErrors().stream()
					.map(ObjectError::getDefaultMessage)
					.collect(Collectors.joining(","));
			logger.info("Error: " + response );
			return ResponseEntity.ok(response);
		}
		response = userService.createUser(userDTO);
		logger.info("Success: UserId " + response + " added");
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
	
	@PostMapping("/login")
	public ResponseEntity<Boolean> loginUser(@RequestBody LoginDTO loginDTO) throws WeCareException {
		logger.info("Inside loginUser() method of {}", this.getClass());
		return new ResponseEntity<Boolean>(userService.loginUser(loginDTO), HttpStatus.OK);
	}
	
	@GetMapping("/{userId}")
	public ResponseEntity<UserDTO> getUserProfile(@PathVariable String userId){
		logger.info("Inside getUserProfile() method of {}", this.getClass());
		return new ResponseEntity<UserDTO>(userService.getUserProfile(userId), HttpStatus.OK);
	}
//	
	@SuppressWarnings("unchecked")
	@GetMapping("/booking/{userId}")
	public List<BookingDTO> showMyAppointments(@PathVariable String userId) {
		logger.info("Inside showMyAppointment() method of {}", this.getClass());
		return (List<BookingDTO>) template.getForObject("http://BOOKINGMS"+"/wecare/booking/user/"+userId,List.class);
		
	}
}
