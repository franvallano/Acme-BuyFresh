package controllers;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import services.MenuService;
import domain.Menu;

@Controller
@RequestMapping("/menu")
public class MenuController extends AbstractController{
	
	// Supporting services ----------------------------------------------------
	@Autowired
	private MenuService menuService;

		
	// Constructors -----------------------------------------------------------

	public MenuController() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	// Listing ----------------------------------------------------------------
	
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list() {
		ModelAndView result;
		Menu menu;
		String requestURI;
		
		menu = menuService.findNextWeek();		
		requestURI = "menu/administrator/menuNextWeek.do";		
			
		result = new ModelAndView("menu/list");
		result.addObject("menus", menu);
		result.addObject("requestURI", requestURI);
		

		return result;
	}

}
