package controllers.user;

import java.util.Collection;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import services.AllergenService;
import services.UserService;
import controllers.AbstractController;
import domain.Allergen;
import domain.User;
import forms.UserForm;
 
@Controller
@RequestMapping("/register/user")
public class RegisterUserController extends AbstractController{
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private AllergenService allergenService;
	
	public RegisterUserController(){
		super();
	}
	
	@RequestMapping(value = "/register", method = RequestMethod.GET)
	public ModelAndView register() {
		ModelAndView result;
		UserForm userForm;
		Collection<Allergen> ls;
		
		ls = allergenService.findAll();

		userForm = new UserForm();
		result = createEditModelAndView(userForm);
		result.addObject("allergens", ls);
		
		return result;
	}
	
	@RequestMapping(value = "/register", method = RequestMethod.POST, params = "save")
	public ModelAndView save(@Valid UserForm userForm, BindingResult binding){
		ModelAndView result;
		User user;
		String repeatedPass;
		boolean duplicate;
		Collection<Allergen> ls;
		
		ls = allergenService.findAll();
		
		repeatedPass = userForm.getRepeatedPass();
		
		if(binding.hasErrors()){
			result = createEditModelAndView(userForm);
			result.addObject("allergens", ls);
		}else{
			try{
				user = userService.reconstruct(userForm);
				userService.save(user, repeatedPass, userForm.isAgree());
				result = new ModelAndView("redirect:/welcome/index.do");
			}catch (Throwable oops){
				
				
				if(oops instanceof DataIntegrityViolationException)
					result = createEditModelAndView(userForm, "commit.duplicatedUser");
				else
					result = createEditModelAndView(userForm, "commit.error");
				
				duplicate = userService.rPassword(userForm);
				result.addObject("duplicate", duplicate);
				result.addObject("agree", userForm.isAgree());
				result.addObject("allergens", ls);
				result.addObject("checkBoxCreditCard", userForm.isCheckBoxCreditCard());
			}
		}
		
		return result;
	}
	
	//Ancillary methods
	
	protected ModelAndView createEditModelAndView(UserForm userForm){
		ModelAndView result;
		
		result = createEditModelAndView(userForm, null);
		result.addObject("checkBoxCreditCard", userForm.isCheckBoxCreditCard());
		
		return result;
	}
	
	protected ModelAndView createEditModelAndView(UserForm userForm, String message){
		ModelAndView result;
		
		result = new ModelAndView("register/register");
		result.addObject("userForm", userForm);
		result.addObject("userFormModel", "userForm");
		result.addObject("message", message);
		result.addObject("url", "register/user/register.do");

		
		return result;
	}
	

}
