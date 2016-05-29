package controllers.administrator;

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
import services.IngredientService;
import controllers.AbstractController;
import domain.Allergen;
import domain.Ingredient;

@Controller
@RequestMapping("/ingredient/administrator")
public class IngredientAdministratorController extends AbstractController {
		
	// Supporting services ----------------------------------------------------

	@Autowired
	private IngredientService ingredientService;
	@Autowired
	private AllergenService allergenService;
	
	// Constructors -----------------------------------------------------------
	
	public IngredientAdministratorController() {
		super();
	}
	
	// Listing ----------------------------------------------------------------
	
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list(@RequestParam (required=false, defaultValue="0") String messageError) {
		ModelAndView result;
		Collection<Ingredient> ingredients;
		
		ingredients = ingredientService.findAll();
		
		result = new ModelAndView("ingredient/list");
		result.addObject("requestURI", "ingredient/administrator/list.do");
		result.addObject("ingredients", ingredients);
		
		
		if(messageError.equals("ingredient.commit.error")){
			result.addObject("messageError",messageError);
		}	
		
		return result;
	}
	
	@RequestMapping(value = "/details", method = RequestMethod.GET)
	public ModelAndView details(@RequestParam int ingredientId) {
		ModelAndView result;
		Ingredient ingredient;
		
		ingredient = ingredientService.findOne(ingredientId);
		Collection<Allergen> allergens = allergenService.findAllergensByIngredientId(ingredientId); 
		result = new ModelAndView("ingredient/details");
		result.addObject("ingredient", ingredient);
		result.addObject("allergens", allergens);
		
		return result;
	}
	
	// Creation ---------------------------------------------------------------
	
	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ModelAndView create(@RequestParam (required=false, defaultValue="0") String messageError) {
		
		ModelAndView result;
		Ingredient ingredient;
		Collection<Allergen> allergens;
		
		ingredient = ingredientService.create();
		allergens = allergenService.findAll();
		
		result = creationModelAndView(ingredient);
		result.addObject("ingredient", ingredient);
		result.addObject("allergens", allergens);
		
		if(messageError.equals("ingredient.commit.error")){
			result.addObject("messageError",messageError);
		}
		
		return result;
		
	}
	
	@RequestMapping(value = "/create", method = RequestMethod.POST, params = "save")
	public ModelAndView save(@Valid Ingredient ingredient, BindingResult bindingResult) {
		ModelAndView result;
		Collection<Allergen> allergens;
		allergens = allergenService.findAll();

		if (bindingResult.hasErrors()){
			result = creationModelAndView(ingredient, "ingredient.commit.error");
			System.out.println(bindingResult);
			result.addObject("allergens", allergens);
		}
		else {
			try {
				ingredientService.save(ingredient);
				result = new ModelAndView("redirect:/ingredient/administrator/list.do");
				
				 
			} catch (Throwable oops) {
				result = creationModelAndView(ingredient, "ingredient.commit.error");
				result.addObject("allergens", allergens);
			}
		}

		return result;
	}

	// Edition ----------------------------------------------------------------
	
	@RequestMapping(value="/delete", method = RequestMethod.GET)
	public ModelAndView delete(@RequestParam int ingredientId){
		ModelAndView result;
		Ingredient ingredient;
		try{
			ingredient = ingredientService.findOne(ingredientId);
			ingredientService.delete(ingredient);
			result = new ModelAndView("redirect:list.do");
		}catch(Throwable oops){
			result = new ModelAndView("redirect:list.do");
			result.addObject("messageError","ingredient.commit.error");
		}
		
		return result;
	}
	
	
	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public ModelAndView edit(@RequestParam int ingredientId) {
		
		ModelAndView result;
		Ingredient ingredient;
		Collection<Allergen> allergens;		
		
		ingredient = ingredientService.findOne(ingredientId);
		allergens = allergenService.findAll();
		
		result = editModelAndView(ingredient);
		result.addObject("ingredient", ingredient);
		result.addObject("allergens", allergens);
		result.addObject("edit", true);
		
		return result;
		
	}
	
	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "update")
	public ModelAndView update(@Valid Ingredient ingredient, BindingResult bindingResult) {
		ModelAndView result;
		Collection<Allergen> allergens;
		allergens = allergenService.findAll();

		if (bindingResult.hasErrors()){
			result = editModelAndView(ingredient, "ingredient.commit.error");
			System.out.println(bindingResult);
			result.addObject("allergens", allergens);
		}
		else {
			try {
				ingredientService.save(ingredient);
				result = new ModelAndView("redirect:/ingredient/administrator/list.do");
				
				 
			} catch (Throwable oops) {
				result = editModelAndView(ingredient, "ingredient.commit.error");
				result.addObject("allergens", allergens);
			}
		}

		return result;
	}
	
	// Ancillary methods ------------------------------------------------------	

	public ModelAndView creationModelAndView(Ingredient ingredient){
		return creationModelAndView(ingredient, null);
	}
	
	public ModelAndView creationModelAndView(Ingredient ingredient, String message){
		ModelAndView res;
		
		res = new ModelAndView("ingredient/edit");
		res.addObject("ingredient", ingredient);
		res.addObject("messageError", message);
		res.addObject("requestURI", "ingredient/administrator/create.do");	

		return res;
	}

	public ModelAndView editModelAndView(Ingredient ingredient){
		return editModelAndView(ingredient, null);
	}
	
	public ModelAndView editModelAndView(Ingredient ingredient, String message){
		ModelAndView res;
		
		res = new ModelAndView("ingredient/edit");
		res.addObject("ingredient", ingredient);
		res.addObject("messageError", message);
		res.addObject("requestURI", "ingredient/administrator/edit.do");
		res.addObject("edit", true);
		return res;
	}
}