package controllers.administrator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import services.AssessmentService;
import domain.Assessment;


@Controller
@RequestMapping("/assessment/administrator")
public class AssessmentAdministratorController {

	
	// Supporting services ----------------------------------------------------
		@Autowired
		private AssessmentService assessmentService;

			
		// Constructors -----------------------------------------------------------

		public AssessmentAdministratorController() {
			super();
		}
		
		// Listing ----------------------------------------------------------------
		
		@RequestMapping(value = "/delete", method = RequestMethod.GET)
		public ModelAndView delete(@RequestParam int assessmentId){
			ModelAndView result;
			Assessment assess = assessmentService.findOne(assessmentId);
			
			assessmentService.delete(assess);

			result = new ModelAndView("redirect:/assessment/list.do");
			
			return result;
		}

}
