package controllers.user;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import services.AssessmentService;
import services.UserService;
import domain.Assessment;
import domain.User;


@Controller
@RequestMapping("/assessment/user")
public class AssessmentUserController {

	
	// Supporting services ----------------------------------------------------
		@Autowired
		private AssessmentService assessmentService;
		
		@Autowired
		private UserService userService;

			
		// Constructors -----------------------------------------------------------

		public AssessmentUserController() {
			super();
		}
		
		// Listing ----------------------------------------------------------------
		
		@RequestMapping(value = "/list", method = RequestMethod.GET)
		public ModelAndView list() {
			ModelAndView result;
			Collection<Assessment> assessment;
			String requestURI;
			
			User u = userService.findByPrincipal();
			
			assessment = u.getAssessments();		
			requestURI = "assessment/user/list.do";		
				
			result = new ModelAndView("assessment/list");
			result.addObject("assessments", assessment);
			result.addObject("requestURI", requestURI);
			

			return result;
		}

}
