package com.infy.CoachMs.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.infy.CoachMs.entity.CoachEntity;

public interface CoachRepository extends JpaRepository<CoachEntity, String> {
	Optional<CoachEntity> findByCoachId(String coachId);
}
