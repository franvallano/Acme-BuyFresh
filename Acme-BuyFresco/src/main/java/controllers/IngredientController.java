package controllers;

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
@RequestMapping("/ingredient")
public class IngredientController extends AbstractController {
		
	// Supporting services ----------------------------------------------------

	@Autowired
	private IngredientService ingredientService;
	@Autowired
	private AllergenService allergenService;
	
	// Constructors -----------------------------------------------------------
	
	public IngredientController() {
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
	
	
	// Edition ----------------------------------------------------------------
	
	
	// Ancillary methods ------------------------------------------------------	

	
}