package com.infy.BookingMs.entity;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "booking")
public class BookingEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "booking_id")
	private Integer bookingId;
	@Column(name = "user_id")
	private String userId;
	@Column(name = "coach_id")
	private String coachId;
	private String slot;
	@Column(name = "appointment_date")
	private LocalDate appointmentDate;
	
	public Integer getBookingId() {
		return bookingId;
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
	
}
