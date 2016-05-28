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

import services.AllergenService;
import services.UserService;
import controllers.AbstractController;
import domain.Allergen;
import domain.User;


@Controller
@RequestMapping("/allergen/user")
public class AllergenUserController extends AbstractController {
		
	// Supporting services ----------------------------------------------------

	@Autowired
	private AllergenService allergenService;
	
	
	@Autowired
	private UserService userService;
	
	// Constructors -----------------------------------------------------------
	
	public AllergenUserController() {
		super();
	}
	
	// Listing ----------------------------------------------------------------
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list(@RequestParam (required=false, defaultValue="0") String messageError) {
		ModelAndView result;
		Collection<Allergen> ls;
		
		ls = allergenService.findAll();
		
		result = new ModelAndView("allergen/list");
		result.addObject("requestURI", "allergen/user/list.do");
		result.addObject("allergens", ls);
		
		
		if(messageError.equals("commit.error")){
			result.addObject("messageError",messageError);
		}	
		
		return result;
	}
	
	
	@RequestMapping(value = "/listMy", method = RequestMethod.GET)
	public ModelAndView listMy(@RequestParam (required=false, defaultValue="0") String messageError) {
		ModelAndView result;
		Collection<Allergen> ls;
		
		User user = userService.findByPrincipal();
		
		ls = user.getAllergens();
		
		result = new ModelAndView("allergen/list");
		result.addObject("requestURI", "allergen/user/list.do");
		result.addObject("allergens", ls);
		
		
		if(messageError.equals("commit.error")){
			result.addObject("messageError",messageError);
		}
		
		return result;
	}
	
	
	
	
	//---------------------------------------------------------------
	
	
	@RequestMapping(value = "/select", method = RequestMethod.GET)
	public ModelAndView edit() {
		
		ModelAndView result;
		Collection<Allergen> ls;
		User user  = userService.findByPrincipal();	
		
		ls = allergenService.findAll();
		
		
		result = editModelAndView(user);
		result.addObject("user", user);
		result.addObject("allergens", ls);
		
		return result;
		
	}
	
	@RequestMapping(value = "/select", method = RequestMethod.POST, params = "update")
	public ModelAndView update(@Valid User user, BindingResult bindingResult) {
		ModelAndView result;
		Collection<Allergen> ls;
		
		ls = allergenService.findAll();

		if (bindingResult.hasErrors()){
			result = editModelAndView(user, "commit.error");
			result.addObject("allergens", ls);
			
			System.out.println(bindingResult);
		}
		else {
			try {
				userService.save(user);
				
				result = new ModelAndView("redirect:/allergen/user/listMy.do");
				result.addObject("allergens", ls);
				
				 
			} catch (Throwable oops) {
				result = editModelAndView(user, "commit.error");
				result.addObject("allergens", ls);
			}
		}

		return result;
	}
	
	
	
	public ModelAndView editModelAndView(User user){
		return editModelAndView(user, null);
	}
	
	public ModelAndView editModelAndView(User user, String message){
		ModelAndView res;
		
		res = new ModelAndView("allergen/select");
		res.addObject("user", user);
		res.addObject("messageError", message);
		res.addObject("requestURI", "allergen/user/select.do");
		return res;
	}
	
}
