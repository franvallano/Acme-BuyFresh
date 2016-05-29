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
import services.QuantityService;
import services.RecipeService;
import controllers.AbstractController;
import domain.Allergen;
import domain.Ingredient;
import domain.Quantity;
import domain.Recipe;

@Controller
@RequestMapping("/recipe")
public class RecipeController extends AbstractController {
		
	// Supporting services ----------------------------------------------------

	@Autowired
	private IngredientService ingredientService;
	@Autowired
	private AllergenService allergenService;
	@Autowired
	private RecipeService recipeService;
	@Autowired
	private QuantityService quantityService;
	
	// Constructors -----------------------------------------------------------
	
	public RecipeController() {
		super();
	}
	
	// Listing ----------------------------------------------------------------
	
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list(@RequestParam (required=false, defaultValue="0") String messageError, @RequestParam (required=false, defaultValue="0") int menuId) {
		ModelAndView result;
		Collection<Recipe> recipes;
		
		if(menuId == 0 ){
			recipes = recipeService.findAll();
		}else{
			recipes = recipeService.findRecipesByMenuId(menuId);
		}
		
		result = new ModelAndView("recipe/list");
		result.addObject("requestURI", "recipe/administrator/list.do");
		result.addObject("recipes", recipes);
		
		
		if(messageError.equals("recipe.commit.error")){
			result.addObject("messageError",messageError);
		}	
		
		return result;
	}
	
	@RequestMapping(value = "/listIngredients", method = RequestMethod.GET)
	public ModelAndView listIngredients(@RequestParam (required=false, defaultValue="0") String messageError, @RequestParam int recipeId) {
		ModelAndView result;
		Collection<Ingredient> ingredients;
		
		ingredients = ingredientService.findAllWithoutDelete();
		
		
		result = new ModelAndView("recipe/listIngredients");
		result.addObject("requestURI", "recipe/administrator/listIngredients.do");
		result.addObject("ingredients", ingredients);
		result.addObject("recipeId", recipeId);
		
		
		if(messageError.equals("ingredient.commit.error")){
			result.addObject("messageError",messageError);
		}	
		
		return result;
	}
	
	@RequestMapping(value = "/details", method = RequestMethod.GET)
	public ModelAndView details(@RequestParam int recipeId) {
		ModelAndView result;
		Recipe recipe;
		Collection<Object[]> ingredients;
		
		recipe = recipeService.findOne(recipeId);
		ingredients = ingredientService.findIngredientsWithQuantities(recipeId);
		result = new ModelAndView("recipe/details");
		result.addObject("recipe", recipe);
		result.addObject("ingredients", ingredients);
		
		
		
		return result;
	}
	
	// Creation ---------------------------------------------------------------
	
	
	// Edition ----------------------------------------------------------------

		
	// Ancillary methods ------------------------------------------------------	

	
}