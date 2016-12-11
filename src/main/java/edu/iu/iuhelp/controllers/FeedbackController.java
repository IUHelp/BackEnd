package edu.iu.iuhelp.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import edu.iu.iuhelp.models.Feedback;
import edu.iu.iuhelp.Repository.FeedbackRepository;

@Controller
public class FeedbackController {
	
	
	 @Autowired
	    FeedbackRepository feedbackRepository;
		
		
		 @RequestMapping(value = "/addFeedback", method = RequestMethod.POST)
		    public String addCar(@ModelAttribute Feedback feedback) {
		        feedbackRepository.save(feedback);
		        return "redirect:home";
		    }

}
