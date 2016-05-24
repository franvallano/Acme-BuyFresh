package controllers.user;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import services.UserService;
import controllers.AbstractController;
import domain.User;
import forms.PasswordForm;
import forms.UserProfileForm;
 
@Controller
@RequestMapping("/profile/user")
public class ProfileUserController extends AbstractController{
	
	@Autowired
	private UserService userService;
	
	public ProfileUserController(){
		super();
	}
	
	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public ModelAndView editProfile() {
		ModelAndView result;
		UserProfileForm userProfileForm;
		User user;
		
		user = userService.findByPrincipal();

		userProfileForm = userService.desreconstructProfile(user);
		
		result = createEditModelAndView(userProfileForm);
		
		return result;
	}
	
	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
	public ModelAndView save(@Valid UserProfileForm userProfileForm, BindingResult binding){
		ModelAndView result;
		User user;
		
		if(binding.hasErrors()){
			result = createEditModelAndView(userProfileForm);
		}else{
			try{
				user = userService.reconstructProfile(userProfileForm);
				userService.saveProfile(user);
				result = new ModelAndView("redirect:/welcome/index.do");
			}catch (Throwable oops){
				
				
				if(oops instanceof DataIntegrityViolationException)
					result = createEditModelAndView(userProfileForm, "commit.duplicatedUser");
				else
					result = createEditModelAndView(userProfileForm, "commit.error");
				
				result.addObject("checkBoxCreditCard", userProfileForm.isCheckBoxCreditCard());
			}
		}
		
		return result;
	}
	
	@RequestMapping(value = "/changePassword", method = RequestMethod.GET)
	public ModelAndView changePassword() {
		ModelAndView result;
		PasswordForm passwordForm;
		
		passwordForm = new PasswordForm();
		
		result = createEditModelAndView(passwordForm);
		
		return result;
	}
	
	@RequestMapping(value = "/changePassword", method = RequestMethod.POST, params = "save")
	public ModelAndView save(@Valid PasswordForm passwordForm, BindingResult binding){
		ModelAndView result;
		User user;
		
		if(binding.hasErrors()){
			result = createEditModelAndView(passwordForm);
		}else{
			try{
				user = userService.reconstructPassword(passwordForm);
				userService.saveProfile(user);
				result = new ModelAndView("redirect:/welcome/index.do");
			}catch (Throwable oops){
				result = createEditModelAndView(passwordForm, "password.commit.error");
			}
		}
		
		return result;
	}
	
	//Ancillary methods
	
	protected ModelAndView createEditModelAndView(UserProfileForm userProfileForm){
		ModelAndView result;
		
		result = createEditModelAndView(userProfileForm, null);
		result.addObject("checkBoxCreditCard", userProfileForm.isCheckBoxCreditCard());
		
		return result;
	}
	
	protected ModelAndView createEditModelAndView(UserProfileForm userProfileForm, String message){
		ModelAndView result;
		
		result = new ModelAndView("profile/edit");
		result.addObject("userProfileForm", userProfileForm);
		result.addObject("userForm", "userProfileForm");
		result.addObject("message", message);
		result.addObject("edit", true);
		result.addObject("url", "profile/user/edit.do");
		
		return result;
	}
	
	protected ModelAndView createEditModelAndView(PasswordForm passwordForm){
		ModelAndView result;
		
		result = createEditModelAndView(passwordForm, null);
		
		return result;
	}
	
	protected ModelAndView createEditModelAndView(PasswordForm passwordForm, String message){
		ModelAndView result;
		
		result = new ModelAndView("profile/changePassword");
		result.addObject("passwordForm", passwordForm);
		result.addObject("passForm", "passwordForm");
		result.addObject("message", message);
		result.addObject("url", "profile/user/changePassword.do");
		
		return result;
	}
}
