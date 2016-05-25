package controllers;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import services.AssessmentService;
import domain.Assessment;


@Controller
@RequestMapping("/assessment")
public class AssessmentController {

	
	// Supporting services ----------------------------------------------------
		@Autowired
		private AssessmentService assessmentService;

			
		// Constructors -----------------------------------------------------------

		public AssessmentController() {
			super();
		}
		
		// Listing ----------------------------------------------------------------
		
		@RequestMapping(value = "/list", method = RequestMethod.GET)
		public ModelAndView list() {
			ModelAndView result;
			Collection<Assessment> assessment;
			String requestURI;
			
			assessment = assessmentService.findAllWithoutDeleted();		
			requestURI = "assessment/list.do";		
				
			result = new ModelAndView("assessment/list");
			result.addObject("assessments", assessment);
			result.addObject("requestURI", requestURI);
			

			return result;
		}

}
