package com.infy.BookMs.repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.infy.BookMs.entity.BookingEntity;

public interface BookRepository extends JpaRepository<BookingEntity, Integer> {
	Optional<BookingEntity> findByUserId(String userId);
//	List<BookingEntity> findBookingByUserId(String userId);
	@Query(value = "SELECT b FROM BookingEntity b WHERE  b.userId=?1 AND b.appointmentDate >= ?2")
	List<BookingEntity> findBookingByUserId(String userId, LocalDate today);
	@Query(value = "SELECT b FROM BookingEntity b WHERE  b.coachId=?1 AND b.appointmentDate >= ?2")
	List<BookingEntity> findBookingByCoachId(String coachId, LocalDate today);
	@Query(value = "SELECT b FROM BookingEntity b WHERE  b.userId=?1 AND b.appointmentDate = ?2 AND b.slot = ?3")
	BookingEntity findAllBookings(String userId, LocalDate appointmentDate, String slot);
}
