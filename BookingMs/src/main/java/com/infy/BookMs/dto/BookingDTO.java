package com.infy.BookMs.dto;

import java.time.LocalDate;

import com.infy.BookMs.entity.BookingEntity;

public class BookingDTO {
	private Integer bookingId;
	private String userId;
	private String coachId;
	private String slot;
	private LocalDate appointmentDate;
	public Integer getBookingId() {
		return bookingId;
	}
	public void setBookingId(Integer bookingId) {
		this.bookingId = bookingId;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getCoachId() {
		return coachId;
	}
	public void setCoachId(String coachId) {
		this.coachId = coachId;
	}
	public String getSlot() {
		return slot;
	}
	public void setSlot(String slot) {
		this.slot = slot;
	}
	public LocalDate getAppointmentDate() {
		return appointmentDate;
	}
	public void setAppointmentDate(LocalDate appointmentDate) {
		this.appointmentDate = appointmentDate;
	}
	
	public static BookingDTO valueOf(BookingEntity bookingEntity) {
		BookingDTO bookingDTO = new BookingDTO();
		
		bookingDTO.setBookingId(bookingEntity.getBookingId());
		bookingDTO.setUserId(bookingEntity.getUserId());
		bookingDTO.setCoachId(bookingEntity.getCoachId());
		bookingDTO.setSlot(bookingEntity.getSlot());
		bookingDTO.setAppointmentDate(bookingEntity.getAppointmentDate());
		
		return bookingDTO;
	}
}
