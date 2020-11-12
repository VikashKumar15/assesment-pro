package com.infy.BookMs.restcontoller;

import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import org.slf4j.Logger;

import com.infy.BookMs.dto.BookingDTO;
import com.infy.BookMs.exception.WeCareException;
import com.infy.BookMs.service.BookInterface;

@RestController
@RequestMapping("/booking")


public class BookRestController {
	
	
	
	@Autowired
	private BookInterface bookService;
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@GetMapping(value = "/user/{userId}")
	public List<BookingDTO> showBookingOFfUser(@PathVariable("userId") String userId){
		return bookService.findBookingByUserId(userId);
	}
	@GetMapping(value = "/coach/{coachId}")
	public List<BookingDTO> showBookingOfCoach(@PathVariable("coachId") String coachId){
		if(coachId.equals("LC0000000000")) {
			throw new RuntimeException();
		}
		return bookService.findBookingByCoachId(coachId);
	}
	
	@PostMapping
	public ResponseEntity<Boolean> bookAppointment(@RequestBody BookingDTO bookingDTO) throws WeCareException {
		logger.info("Inside bookingAppointment() method of {}", this.getClass());
		boolean response = bookService.bookAppointment(bookingDTO.getUserId(), 
				bookingDTO.getCoachId(), bookingDTO.getAppointmentDate(), bookingDTO.getSlot());
		if (response)
			return new ResponseEntity<>(response, HttpStatus.OK);
		return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
	}
	
	@PutMapping("/{bookingId}")
	public ResponseEntity<Boolean> rescheduleAppointment(@PathVariable Integer bookingId, @RequestBody BookingDTO bookingDTO)
	throws WeCareException {
		logger.info("Inside rescheduleAppointment() method of {}", this.getClass());
		boolean response = bookService.rescheduleAppointment(bookingId, bookingDTO.getAppointmentDate(), bookingDTO.getSlot());
		if (response)
			return new ResponseEntity<>(response, HttpStatus.OK);
		return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
	}
	
	@DeleteMapping("/{bookingId}")
	public ResponseEntity<?> cancelAppointment(@PathVariable Integer bookingId) {
		logger.info("Inside cancelAppointment() method of {}", this.getClass());
		bookService.cancelAppointment(bookingId);
		return new ResponseEntity<>(HttpStatus.OK);
	}
}
