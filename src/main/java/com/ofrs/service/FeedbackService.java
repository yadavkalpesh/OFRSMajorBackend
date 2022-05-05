package com.ofrs.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ofrs.model.Feedback;
import com.ofrs.repository.FeedbackRepository;

import ch.qos.logback.classic.Logger;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@Transactional
public class FeedbackService {

	Logger logger = (Logger) org.slf4j.LoggerFactory.getLogger(FeedbackService.class);
	
	@Autowired
	FeedbackRepository feedbackRepository;
	
	public List<Feedback> getAllFeedback() {
		logger.info("***List feedback service is called***");
		return feedbackRepository.findAll();
	}

	public Feedback getFeedback(int id) {
		logger.info("***Get user service is called***");
		return feedbackRepository.getById(id);
	}

	public void deleteFeedback(int id) {
		logger.info("***Delete feedback service is called***");
		feedbackRepository.deleteById(id);
	}

	public void updateFeedback(int id, Feedback feedback) {
		logger.info("***Update feedback service is called***");
		feedbackRepository.save(feedback);
	}

	public Feedback addFeedback(Feedback feedback) {
		logger.info("***Add feedback service is called***");
		return feedbackRepository.save(feedback);
	}
}