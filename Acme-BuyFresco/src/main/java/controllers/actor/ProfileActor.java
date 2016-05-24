package controllers.actor;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import services.ActorService;
import controllers.AbstractController;
import domain.Actor;
import forms.PasswordForm;
import forms.ProfileForm;
 
@Controller
@RequestMapping("/profile/actor")
public class ProfileActor extends AbstractController{
	
	@Autowired
	private ActorService actorService;
	
	public ProfileActor(){
		super();
	}
	
	
	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public ModelAndView editProfile() {
		ModelAndView result;
		ProfileForm profileForm;
		Actor actor;
		
		actor = actorService.findByPrincipal();

		profileForm = actorService.desreconstructProfile(actor);
		
		result = createEditModelAndView(profileForm);
		result.addObject("edit", true);
		
		return result;
	}
	
	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
	public ModelAndView save(@Valid ProfileForm profileForm, BindingResult binding){
		ModelAndView result;
		Actor actor;
		
		if(binding.hasErrors()){
			
			result = createEditModelAndView(profileForm);
			result.addObject("edit", true);
			System.out.println(binding.hasErrors());
			
		}else{
			try{
				actor = actorService.reconstructProfile(profileForm);
				actorService.saveProfile(actor);
				result = new ModelAndView("redirect:/welcome/index.do");
				
			}catch (Throwable oops){
				if(oops instanceof DataIntegrityViolationException){
					result = createEditModelAndView(profileForm, "commit.duplicatedUser");
					result.addObject("edit", true);}
				else{
					result = createEditModelAndView(profileForm, "commit.error");
					result.addObject("edit", true);}
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
		Actor actor;
		
		if(binding.hasErrors()){
			result = createEditModelAndView(passwordForm);
		}else{
			try{
				actor = actorService.reconstructPassword(passwordForm);
				actorService.saveProfile(actor);
				result = new ModelAndView("redirect:/welcome/index.do");
			}catch (Throwable oops){
				result = createEditModelAndView(passwordForm, "password.commit.error");
			}
		}
		
		return result;
	}
	
	//Ancillary methods
	
	protected ModelAndView createEditModelAndView(ProfileForm profileForm){
		ModelAndView result;
		
		result = createEditModelAndView(profileForm, null);
		
		return result;
	}
	
	protected ModelAndView createEditModelAndView(ProfileForm profileForm, String message){
		ModelAndView result;
		
		result = new ModelAndView("profile/edit");
		result.addObject("profileForm", profileForm);
		result.addObject("userForm", "profileForm");
		result.addObject("messageError", message);
		result.addObject("edit", true);
		result.addObject("RequestURI", "profile/edit.do");
		
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
		result.addObject("messageError", message);
		result.addObject("RequestURI", "profile/changePassword.do");
		
		return result;
	}
}
