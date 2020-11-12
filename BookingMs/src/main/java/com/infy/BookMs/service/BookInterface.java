package com.infy.BookMs.service;

import java.time.LocalDate;
import java.util.List;

import com.infy.BookMs.dto.BookingDTO;
import com.infy.BookMs.exception.WeCareException;

public interface BookInterface {
	
	boolean bookAppointment(String userId, String coachId, LocalDate appointmentDate, String slot) throws WeCareException;
	
	boolean rescheduleAppointment(Integer bookingId, LocalDate appointmentDate, String slot) throws WeCareException;
	
	void cancelAppointment(Integer bookingId);
	
	BookingDTO findByBookingId(Integer bookingId);
	
	List<BookingDTO> findBookingByUserId(String userId);
	
	List<BookingDTO> findBookingByCoachId(String coachId);
	
	void sendSchedulingEmail(BookingDTO bookingDTO);
	
	void sendCancellingEmail(BookingDTO bookingDTO);
	
	void sendReschedulingEmail(BookingDTO bookingDTO);
}
