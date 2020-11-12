package com.infy.CoachMs.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.infy.CoachMs.dto.CoachDTO;
import com.infy.CoachMs.dto.LoginDTO;
import com.infy.CoachMs.entity.CoachEntity;
import com.infy.CoachMs.exception.ExceptionConstants;
import com.infy.CoachMs.exception.WeCareException;
import com.infy.CoachMs.repository.CoachRepository;

@Service
public class CoachService implements CoachInterface {
	@Autowired
	private CoachRepository coachRepository;
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Override
	public String createCoach(CoachDTO coachDTO) {
		logger.info("Inside createCoach() method of {}", this.getClass());
		CoachEntity coachEntity = coachRepository.save(coachDTO.createEntity());
		logger.info("CoachID: " + coachEntity.getCoachId() + " created");
		return coachEntity.getCoachId();
	}

//	@Override
//	public boolean loginCoach(LoginDTO loginDTO) throws WeCareException {
//		String response;
//		logger.info("Inside loginCoach() method of {}", this.getClass());
//		Optional<CoachEntity> optional = coachRepository.findByCoachId(loginDTO.getId());
//		if (optional.isEmpty()) {
//			response = ExceptionConstants.COACH_NOT_FOUND.toString();
//			logger.error("Error: " + response);
//			throw new WeCareException(response);
//		}
//		CoachDTO coachDTO = CoachDTO.valueOf(optional.get());
//		if (coachDTO.getPassword().equals(loginDTO.getPassword())) {
//			logger.info("Success: Loggedin");
//			return true;
//		}
//		logger.info("Failed: Incorrect password");
//		return false;
//	}
	
	
	@Override
	public boolean loginCoach(LoginDTO loginDTO) throws WeCareException {
		// TODO Auto-generated method stub
		logger.info("this is loging id="+loginDTO.getId());
		Optional<CoachEntity> coOptional = coachRepository.findByCoachId(loginDTO.getId());
		if(coOptional.isEmpty())
		{ 
			// have to read from exception constant enum here 
			throw new WeCareException(ExceptionConstants.COACH_NOT_FOUND.toString());
		}
		else
		{
			CoachEntity coachEntity = coOptional.get();
			if(coachEntity.getPassword().equals(loginDTO.getPassword()))
			{
				return true;
			}
			return false;
		}
		
	}
	
	

	@Override
	public CoachDTO getCoachProfile(String coachId) {
		Optional<CoachEntity> optional = coachRepository.findByCoachId(coachId);
		if (optional.isEmpty())
			return null;
		return CoachDTO.valueOf(optional.get());
	}

	@Override
	public List<CoachDTO> showAllCoaches() {
		logger.info("Inside showAllCoaches() method of {}", this.getClass());
		List<CoachDTO> coachDTOs = new ArrayList<>();
		List<CoachEntity> coaches = coachRepository.findAll();
		for (CoachEntity coach : coaches)
			coachDTOs.add(CoachDTO.valueOf(coach));
		return coachDTOs;
	}

}
