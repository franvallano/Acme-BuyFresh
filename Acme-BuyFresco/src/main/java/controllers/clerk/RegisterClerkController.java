package controllers.clerk;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import services.ClerkService;
import controllers.AbstractController;
import domain.Clerk;
import forms.ClerkForm;

@Controller
@RequestMapping("/clerk")
public class RegisterClerkController extends AbstractController{
	
	// Services ---------------------------------------------------------------
	@Autowired
	private ClerkService clerkService;
	
	// Constructors -----------------------------------------------------------
	
	public RegisterClerkController() {
		super();
	}
	
	// Listing ----------------------------------------------------------------
	
	// Creation ---------------------------------------------------------------
	
	// Edition ----------------------------------------------------------------	
	
	// Register ---------------------------------------------------------------

	@RequestMapping(value = "/register", method = RequestMethod.GET)
	public ModelAndView registerBoss() {
		ModelAndView result;
		ClerkForm clerkForm;
		
		clerkForm = new ClerkForm();

		result = createEditModelAndView(clerkForm, true);
		result.addObject("action", "register/clerk/register.do");
		
		return result;
	}
	
	
	@RequestMapping(value = "/register", method = RequestMethod.POST, params = "save")
	public ModelAndView registerBoss(@Valid ClerkForm clerkForm, BindingResult binding) {
		ModelAndView result;
		String repeatedPass;
		boolean duplicate;
		Clerk clerk;
		
		repeatedPass = clerkForm.getRepeatedPass();

		if (binding.hasErrors()) {
			result = createEditModelAndView(clerkForm, false);
		} else { 
			try {
				
				clerk = clerkService.reconstruct(clerkForm);
				clerkService.save(clerk, repeatedPass);
				
				result = new ModelAndView("redirect:/welcome/index.do");
				
			} catch (Throwable oops) {

				if(oops instanceof DataIntegrityViolationException)
					result = createEditModelAndView(clerkForm, "commit.duplicatedUser");
				else
					result = createEditModelAndView(clerkForm, "commit.error");

				
				duplicate = clerkService.rPassword(clerkForm);
				result.addObject("duplicate", duplicate);
			}
		}

		return result;
	}

	
	// Ancillary methods ------------------------------------------------------
	
	protected ModelAndView createEditModelAndView(ClerkForm clerkForm, boolean isDeliveryMan) {
		ModelAndView result;

		result = createEditModelAndView(clerkForm, null);

		return result;
	}

	protected ModelAndView createEditModelAndView(ClerkForm clerkForm, String message) {
		ModelAndView result;

		result = new ModelAndView("register/register");
		result.addObject("clerkForm", clerkForm);
		result.addObject("userForm", "clerkForm");
		result.addObject("message", message);
		
		return result;
	}

}
