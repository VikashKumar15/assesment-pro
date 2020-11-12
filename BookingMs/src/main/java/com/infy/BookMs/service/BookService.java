package com.infy.BookMs.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.infy.BookMs.dto.BookingDTO;
import com.infy.BookMs.entity.BookingEntity;
import com.infy.BookMs.exception.ExceptionConstants;
import com.infy.BookMs.exception.WeCareException;
import com.infy.BookMs.repository.BookRepository;
//import com.infy.BookMs.repository.CoachRepository;
//import com.infy.BookMs.repository.UserRepository;

@Service
public class BookService implements BookInterface{
	
	@Autowired
	private BookRepository bookRepository;
//	@Autowired
//	private UserRepository userRepository;
//	@Autowired
//	private CoachRepository coachRepository;
//	@Autowired
	private JavaMailSender javaMailSender;
	public static int noOfQuickServiceThreads = 20;
	private ScheduledExecutorService quickService = Executors.newScheduledThreadPool(noOfQuickServiceThreads);
	private Logger logger = LoggerFactory.getLogger(this.getClass());
		
	@Override
	public boolean bookAppointment(String userId, String coachId, LocalDate appointmentDate, String slot) throws WeCareException {
		logger.info("Inside bookingAppointment() method of {}", this.getClass());
		BookingEntity booking = bookRepository.findAllBookings(userId, appointmentDate, slot);
		if (booking == null) {
			booking = new BookingEntity();
			booking.setUserId(userId);
			booking.setCoachId(coachId);
			booking.setAppointmentDate(appointmentDate);
			booking.setSlot(slot);
			bookRepository.save(booking);
			logger.info("Success: Booking details added successfully");
			//sendSchedulingEmail(BookingDTO.valueOf(booking));
			return true;
		}
		logger.error("Exception: " + ExceptionConstants.BOOKING_ALREADY_EXISTS);
		throw new WeCareException(ExceptionConstants.BOOKING_ALREADY_EXISTS.toString());
	}

	@Override
	public boolean rescheduleAppointment(Integer bookingId, LocalDate appointmentDate, String slot) throws WeCareException {
		logger.info("Inside rescheduleAppointment() method of {}", this.getClass());
		BookingEntity bookingById = bookRepository.getOne(bookingId);
		BookingEntity bookingBySlot = bookRepository.findAllBookings(bookingById.getUserId(), appointmentDate, slot);
		if (bookingBySlot == null) {
			bookingById.setAppointmentDate(appointmentDate);
			bookingById.setSlot(slot);
			bookRepository.save(bookingById);
			logger.info("success: Appointment rescheduled");
			//sendReschedulingEmail(BookingDTO.valueOf(bookingById));
			return true;
		}
		logger.error("Error: " + ExceptionConstants.BOOKING_ALREADY_EXISTS);
		throw new WeCareException(ExceptionConstants.BOOKING_ALREADY_EXISTS.toString());
	}

	@Override
	public void cancelAppointment(Integer bookingId) {
		logger.info("Inside cancelAppointment() method of {}", this.getClass());
		BookingDTO bookingDTO = findByBookingId(bookingId);
		if (bookingDTO != null) {
			bookRepository.deleteById(bookingDTO.getBookingId());
			logger.info("Success: Deleted apppointment details");
			//sendCancellingEmail(bookingDTO);
		}
	}

	@Override
	public BookingDTO findByBookingId(Integer bookingId) {
		logger.info("Inside findByBookingId() method of {}", this.getClass());
		return BookingDTO.valueOf(bookRepository.getOne(bookingId));
	}

	@Override
	public List<BookingDTO> findBookingByUserId(String userId) {
		logger.info("Inside findBookingByUserId() method of {} and id is{}tttt", this.getClass(),userId);
		
		List<BookingEntity> bookingEntities = bookRepository.findBookingByUserId(userId, LocalDate.now());
//		List<BookingEntity> bookingEntities = bookRepository.findBookingByUserId(userId);
		List<BookingDTO> bookingDTOs = new ArrayList<>();
		for (BookingEntity book : bookingEntities)
			bookingDTOs.add(BookingDTO.valueOf(book));
		return bookingDTOs;
	}

	@Override
	public List<BookingDTO> findBookingByCoachId(String coachId) {
		logger.info("Inside findBookingByCoachId() method of {}", this.getClass());
		List<BookingEntity> bookingEntities = bookRepository.findBookingByCoachId(coachId, LocalDate.now());
		List<BookingDTO> bookingDTOs = new ArrayList<>();
		for (BookingEntity book : bookingEntities)
			bookingDTOs.add(BookingDTO.valueOf(book));
		return bookingDTOs;
	}

	@Override
	public void sendSchedulingEmail(BookingDTO bookingDTO) {
//		logger.info("Inside sendEmail1() method of {}", this.getClass());
//		String mailTo = userRepository.getOne(bookingDTO.getUserId()).getEmail();
//		Integer bookingId = bookingDTO.getBookingId();
//		String userName = userRepository.getOne(bookingDTO.getUserId()).getName();
//		String coachName = coachRepository.getOne(bookingDTO.getCoachId()).getName();
//		String bookingSlot = bookingDTO.getSlot();
//		LocalDate appointmentDate = bookingDTO.getAppointmentDate();
//		quickService.submit(new Runnable() {
//			@Override
//			public void run() {
//				try {
//					SimpleMailMessage msg = new SimpleMailMessage();
//					msg.setTo(mailTo);
//					msg.setSubject(
//							"Your appointment with booking id " + bookingId + " has been successfully scheduled.");
//					msg.setText("Dear " + userName + " \nYour appointment with " + coachName
//							+ " has been scheduled successfully. \nYou can visit your coach any time from "
//							+ bookingSlot + " on " + appointmentDate + ". \n \n \nThanks and Regards \nTeam WeCARE");
//					javaMailSender.send(msg);
//					logger.info("Booking : " + bookingId + " has been actually sent");
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});
//		logger.info("Booking : " + bookingId + " has been sent");
	}

	@Override
	public void sendCancellingEmail(BookingDTO bookingDTO) {
//		logger.info("Inside sendEmail2() method of {}", this.getClass());
//		String mailTo = userRepository.getOne(bookingDTO.getUserId()).getEmail();
//		Integer bookingId = bookingDTO.getBookingId();
//		String userName = userRepository.getOne(bookingDTO.getUserId()).getName();
//		String coachName = coachRepository.getOne(bookingDTO.getCoachId()).getName();
//		String bookingSlot = bookingDTO.getSlot();
//		LocalDate appointmentDate = bookingDTO.getAppointmentDate();
//		quickService.submit(new Runnable() {
//			@Override
//			public void run() {
//				try {
//					SimpleMailMessage msg = new SimpleMailMessage();
//					msg.setTo(mailTo);
//					msg.setSubject(
//							"Your appointment with booking id " + bookingId + " has been successfully cancelled.");
//					msg.setText("Dear " + userName + " \nYour appointment with " + coachName + " from " + bookingSlot
//							+ " on " + appointmentDate
//							+ " has been cancelled successfully. \n \n \nThanks and Regards \nTeam WeCARE");
//					javaMailSender.send(msg);
//					logger.info("Cancel : " + bookingId + " has been actually sent");
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});
//		logger.info("Cancel : " + bookingId + " has been sent");
	}

	@Override
	public void sendReschedulingEmail(BookingDTO bookingDTO) {
//		logger.info("Inside sendEmail3() method of {}", this.getClass());
//		String mailTo = userRepository.getOne(bookingDTO.getUserId()).getEmail();
//		Integer bookingId = bookingDTO.getBookingId();
//		String userName = userRepository.getOne(bookingDTO.getUserId()).getName();
//		String coachName = coachRepository.getOne(bookingDTO.getCoachId()).getName();
//		String bookingSlot = bookingDTO.getSlot();
//		LocalDate appointmentDate = bookingDTO.getAppointmentDate();
//		quickService.submit(new Runnable() {
//			@Override
//			public void run() {
//				try {
//					SimpleMailMessage msg = new SimpleMailMessage();
//					msg.setTo(mailTo);
//					msg.setSubject(
//							"Your appointment with booking id " + bookingId + " has been successfully rescheduled.");
//					msg.setText("Dear " + userName + " \nYour appointment with " + coachName
//							+ " has been rescheduled successfully for " + bookingSlot + " on " + appointmentDate
//							+ ". \n \n \nThanks and Regards \nTeam WeCARE");
//					javaMailSender.send(msg);
//					logger.info("Reschedule : " + bookingId + " has been actually sent");
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});
//		logger.info("Reschedule : " + bookingId + " has been sent");
	}
	
}
