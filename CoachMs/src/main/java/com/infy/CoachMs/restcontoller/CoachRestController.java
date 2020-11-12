package com.infy.CoachMs.restcontoller;

import java.util.ArrayList;
import java.util.Arrays;
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

import com.infy.CoachMs.configuration.CoachRibbonConfig;
import com.infy.CoachMs.dto.BookingDTO;
import com.infy.CoachMs.dto.CoachDTO;
import com.infy.CoachMs.dto.LoginDTO;
import com.infy.CoachMs.exception.WeCareException;
//import com.infy.CoachMs.service.BookInterface;
import com.infy.CoachMs.service.CoachInterface;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

@RestController
@RequestMapping("/coaches")
@EnableAutoConfiguration
@RibbonClient(name = "coachRibbon" ,configuration=CoachRibbonConfig.class)
public class CoachRestController {
	@Autowired
	private CoachInterface coachService;
	
	@Autowired
	RestTemplate template;
	
//	@Autowired
//	private BookInterface bookService;
//	@Value("${book.uri}")
//	String bookuri;
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@PostMapping
	public ResponseEntity<String> createCoach(@Valid @RequestBody CoachDTO coachDTO, Errors error) throws WeCareException {
		String response;
		logger.info("Inside createCoach() method of {}", this.getClass());
		if (error.hasErrors()) {
			response = error.getAllErrors().stream()
					.map(ObjectError::getDefaultMessage)
					.collect(Collectors.joining(","));
			logger.info("Error: " + response );
			return ResponseEntity.ok(response);
		}
		response = coachService.createCoach(coachDTO);
		logger.info("Success: CoachId " + response + " added");
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
	
	@PostMapping("/login")
	public ResponseEntity<Boolean> loginCoach(@RequestBody LoginDTO loginDTO) throws WeCareException {
		logger.info("Inside loginCoach() method of {}", this.getClass());
		return new ResponseEntity<Boolean>(coachService.loginCoach(loginDTO), HttpStatus.OK);
	}

	
	@GetMapping("/{coachId}")
	public ResponseEntity<CoachDTO> getCoachProfile(@PathVariable String coachId){
		logger.info("Inside getCoachProfile() method of {}", this.getClass());
		return new ResponseEntity<CoachDTO>(coachService.getCoachProfile(coachId), HttpStatus.OK);
	}
	
	@GetMapping("/all")
	public List<CoachDTO> showAllCoaches(){
		logger.info("Inside showAllCoaches() method of {}", this.getClass());
		return coachService.showAllCoaches();
	}
//	@SuppressWarnings("unchecked")
	@GetMapping("/booking/{coachId}")
	@HystrixCommand(fallbackMethod="showMyAppointmentsFallback")
	public List<BookingDTO> showMyAppointments(@PathVariable String coachId) {
		System.out.println("#########################INSIDE SHOW MY APPOINTMENT###############");

		logger.info("Inside showMyAppointment() method of {}", this.getClass());
		return  Arrays.asList(template.getForObject("http://BOOKINGMS"+"/wecare/booking/coach/"+coachId,BookingDTO[].class));
	}
	
	public List<BookingDTO> showMyAppointmentsFallback(String coachId) {
		System.out.println("#########################INSIDE FALLBACK###############");
		logger.info("Inside showMyAppointment() FALLBACK");
		return new ArrayList<BookingDTO>();
		
	}
//	
//	@GetMapping("/booking/{coachId}")
//	public List<BookingDTO> showMySchedule(@PathVariable String coachId) {
//		logger.info("Inside showMySchedule() method of {}", this.getClass());
//		return bookService.findBookingByCoachId(coachId);
//	}
}
