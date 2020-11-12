package com.infy.CoachMs.service;

import java.util.List;

import com.infy.CoachMs.dto.CoachDTO;
import com.infy.CoachMs.dto.LoginDTO;
import com.infy.CoachMs.exception.WeCareException;

public interface CoachInterface {
	String createCoach(CoachDTO coachDTO);
	
	boolean loginCoach(LoginDTO loginDTO) throws WeCareException;
	
	CoachDTO getCoachProfile(String coachId);
	
	List<CoachDTO> showAllCoaches();
}
