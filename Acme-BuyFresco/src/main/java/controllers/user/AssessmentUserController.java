package controllers.user;

import java.util.Collection;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
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
		
		
		@RequestMapping(value = "/create", method = RequestMethod.GET)
		public ModelAndView create(@RequestParam (required=false, defaultValue="0") String messageError) {
			
			ModelAndView result;
			Assessment r;
			
			r = assessmentService.create();
			
			result = creationModelAndView(r);
			result.addObject("assessment", r);
			
			if(messageError.equals("commit.error")){
				result.addObject("messageError",messageError);
			}
			
			return result;
			
		}
		
		@RequestMapping(value = "/create", method = RequestMethod.POST, params = "save")
		public ModelAndView save(@Valid Assessment b, BindingResult bindingResult) {
			ModelAndView result;

			if (bindingResult.hasErrors()){
				result = creationModelAndView(b, "commit.error");
				System.out.println(bindingResult);
			}
			else {
				try {
					assessmentService.save(b);
					result = new ModelAndView("redirect:/assessment/user/list.do");
					
					 
				} catch (Throwable oops) {
					result = creationModelAndView(b, "commit.error");
				}
			}

			return result;
		}
		
		
		public ModelAndView creationModelAndView(Assessment b){
			return creationModelAndView(b, null);
		}
		
		public ModelAndView creationModelAndView(Assessment b, String message){
			ModelAndView res;
			
			res = new ModelAndView("assessment/create");
			res.addObject("assessment", b);
			res.addObject("messageError", message);
			res.addObject("requestURI", "assessment/user/create.do");	

			return res;
		}

}
