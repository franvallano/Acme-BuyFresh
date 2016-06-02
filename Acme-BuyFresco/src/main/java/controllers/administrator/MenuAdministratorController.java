package controllers.administrator;

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
@RequestMapping("/menu/administrator")
public class MenuAdministratorController extends AbstractController {
	
	
	// Supporting services ----------------------------------------------------
	@Autowired
	private MenuService menuService;
	
	@Autowired
	private RecipeService recipeService;
	
	// Constructors -----------------------------------------------------------

	public MenuAdministratorController() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	// Listing ----------------------------------------------------------------
	
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list() {
		ModelAndView result;
		Collection<Menu> menus;
		String requestURI;
		
		menus = menuService.findAll();		
		requestURI = "menu/administrator/list.do";		
			
		result = new ModelAndView("menu/list");
		result.addObject("menu", menus);
		result.addObject("requestURI", requestURI);
		

		return result;
	}
	
	// Creation ---------------------------------------------------------------
	
	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ModelAndView create() {
		ModelAndView result;
		Menu menu;
		Collection<Recipe> recipes;
		recipes = recipeService.findAll();
		menu = menuService.create();
		
		result = createModelAndView(menu);
		result.addObject("recipes", recipes);
		
		return result;
	}
	
	@RequestMapping(value = "/create", method = RequestMethod.POST, params = "save")
	public ModelAndView save(@Valid Menu menu, BindingResult binding) {
		ModelAndView result;

		if (binding.hasErrors()) {
			result = createModelAndView(menu, "menu.commit.error");
			Collection<Recipe> recipes;
			recipes = recipeService.findAll();
			result.addObject("recipes", recipes);
		} else {
			try {
				Menu m = menuService.save(menu);
				
				for(Recipe r: m.getRecipes()){
					Collection<Menu> menus = r.getMenus();
					menus.add(m);
					r.setMenus(menus);
					recipeService.save(r);
				}
				result = new ModelAndView("redirect:/menu/administrator/list.do");
			} catch (Throwable oops) {
				result = createModelAndView(menu, "menu.commit.error");
				Collection<Recipe> recipes;
				recipes = recipeService.findAll();
				result.addObject("recipes", recipes);
			}
		}
		
		return result;
	}
	
	// Edit ---------------------------------------------------------------
	
	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public ModelAndView edit(@RequestParam int menuId) {
		ModelAndView result;
		Menu menu;
		Collection<Recipe> recipes;
		recipes = recipeService.findAll();
		menu = menuService.findOne(menuId);
		
		result = editModelAndView(menu);
		result.addObject("recipes", recipes);
		
		return result;
	}
	
	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "update")
	public ModelAndView edit(@Valid Menu menu, BindingResult binding) {
		ModelAndView result;
		
		if (binding.hasErrors()) {
			result = editModelAndView(menu, "menu.commit.error");
			Collection<Recipe> recipes;
			recipes = recipeService.findAll();
			result.addObject("recipes", recipes);
		} else {
			try {
				for(Recipe r: menu.getRecipes()){
					System.out.println(r.getName());
				}
				Menu m = menuService.save(menu);
				for(Recipe r: m.getRecipes()){
					
					Collection<Menu> menus = r.getMenus();
					if(!menus.contains(m)){
						menus.add(m);
						r.setMenus(menus);
						recipeService.save(r);
					}
				}				
				result = new ModelAndView("redirect:/menu/administrator/list.do");
			} catch (Throwable oops) {
				result = editModelAndView(menu, "menu.commit.error");
				Collection<Recipe> recipes;
				recipes = recipeService.findAll();
				result.addObject("recipes", recipes);
			}
		}
		
		return result;
	}
		
	// Delete ---------------------------------------------------------------
		
	@RequestMapping(value = "/delete", method = RequestMethod.GET)
	public ModelAndView delete(@RequestParam int menuId) {
		ModelAndView result;
		Menu menu = menuService.findOne(menuId);
		menuService.delete(menu);
		result = new ModelAndView("redirect:/menu/administrator/list.do");
		
		
		return result;
	}
			
	
	// Ancillary methods ------------------------------------------------------	
	
	public ModelAndView createModelAndView(Menu menu){
		return createModelAndView(menu, null);
	}
	
	public ModelAndView createModelAndView(Menu menu, String message){
		ModelAndView res;
		
		res = new ModelAndView("menu/create");
		res.addObject("menu", menu);
		res.addObject("message", message);
		res.addObject("requestURI", "menu/administrator/create.do");	

		return res;
	}
	
	
	public ModelAndView editModelAndView(Menu menu){
		return editModelAndView(menu, null);
	}
	
	public ModelAndView editModelAndView(Menu menu, String message){
		ModelAndView res;
		
		res = new ModelAndView("menu/edit");
		res.addObject("menu", menu);
		res.addObject("message", message);
		res.addObject("requestURI", "menu/administrator/edit.do");	

		return res;
	}
}
