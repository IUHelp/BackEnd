package edu.iu.iuhelp.controllers;
import java.util.List;

import javax.validation.Valid;
 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import edu.iu.iuhelp.models.Feedback;
import edu.iu.iuhelp.Repository.FeedbackRepository;


@RestController
@RequestMapping("/addFeedback")


public class FeedbackController {
	
	@Autowired
	FeedbackRepository feedbackRepository;
	
	@RequestMapping(method=RequestMethod.POST)
	public Feedback addFeedback(@Valid @RequestBody Feedback feedback) {
		System.out.println("qjjdql");
		//return feedbackRepository.save(feedback);
		return feedback;
	}

}
