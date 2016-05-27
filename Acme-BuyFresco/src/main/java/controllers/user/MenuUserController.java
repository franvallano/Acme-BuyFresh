package controllers.user;

import java.util.Collection;

import javax.sound.midi.Receiver;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import services.MenuService;
import services.RecipeService;
import controllers.AbstractController;
import domain.Menu;
import domain.Recipe;

@Controller
@RequestMapping("/menu/user")
public class MenuUserController extends AbstractController {
	
	
	// Supporting services ----------------------------------------------------
	@Autowired
	private MenuService menuService;
	

	
	// Constructors -----------------------------------------------------------

	public MenuUserController() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	// Listing ----------------------------------------------------------------
	
	@RequestMapping(value = "/activeMenus", method = RequestMethod.GET)
	public ModelAndView list() {
		ModelAndView result;
		Collection<Menu> menus;
		String requestURI;
		
		menus = menuService.findActiveMenus();		
		requestURI = "menu/user/list.do";		
			
		result = new ModelAndView("menu/list");
		result.addObject("menu", menus);
		result.addObject("requestURI", requestURI);
		

		return result;
	}
	
	
}
