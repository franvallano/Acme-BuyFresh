package controllers.user;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
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
}
