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
import services.QuantityService;
import services.RecipeService;
import controllers.AbstractController;
import domain.Allergen;
import domain.Ingredient;
import domain.Quantity;
import domain.Recipe;

@Controller
@RequestMapping("/recipe/administrator")
public class RecipeAdministratorController extends AbstractController {
		
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
	
	public RecipeAdministratorController() {
		super();
	}
	
	// Listing ----------------------------------------------------------------
	
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list(@RequestParam (required=false, defaultValue="0") String messageError) {
		ModelAndView result;
		Collection<Recipe> recipes;
		
		recipes = recipeService.findAll();
		
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
		Collection<Allergen> allergens;
		Recipe recipeAct;
		
		ingredients = ingredientService.findAllWithoutDelete();
		allergens = allergenService.findAll();
		recipeAct = recipeService.findOne(recipeId); 
		
		result = new ModelAndView("recipe/listIngredients");
		result.addObject("requestURI", "recipe/administrator/listIngredients.do");
		result.addObject("ingredients", ingredients);
		result.addObject("allergens", allergens);
		result.addObject("recipeId", recipeId);
		result.addObject("recipeAct", recipeAct);
		
		
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
		Collection<Allergen> allergens;
		
		recipe = recipeService.findOne(recipeId);
		ingredients = ingredientService.findIngredientsWithQuantities(recipeId);
		allergens = allergenService.findAllergenByRecipeId(recipeId); 
				
		result = new ModelAndView("recipe/details");
		result.addObject("recipe", recipe);
		result.addObject("ingredients", ingredients);
		result.addObject("allergens", allergens);		
		
		return result;
	}
	
	// Creation ---------------------------------------------------------------
	
	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ModelAndView create(@RequestParam (required=false, defaultValue="0") String messageError) {
		
		ModelAndView result;
		Recipe recipe;
		Collection<Allergen> allergenList;
		
		recipe = recipeService.create();
		allergenList = allergenService.findAll();
		
		result = creationModelAndView(recipe);
		result.addObject("recipe", recipe);
		result.addObject("allergenList", allergenList);
		
		if(messageError.equals("recipe.commit.error")){
			result.addObject("messageError",messageError);
		}
		
		return result;
		
	}
	
	@RequestMapping(value = "/create", method = RequestMethod.POST, params = "save")
	public ModelAndView save(@Valid Recipe recipe, BindingResult bindingResult) {
		ModelAndView result;
		Collection<Allergen> allergenList;
		allergenList = allergenService.findAll();

		if (bindingResult.hasErrors()){
			result = creationModelAndView(recipe, "recipe.commit.error");
			System.out.println(bindingResult);
			result.addObject("allergenList", allergenList);
		}
		else {
			try {
				
				Recipe saved = recipeService.save(recipe);
				
				result = new ModelAndView("redirect:/recipe/administrator/listIngredients.do?recipeId=" + saved.getId());
				
				 
			} catch (Throwable oops) {
				result = creationModelAndView(recipe, "recipe.commit.error");
				result.addObject("allergenList", allergenList);
			}
		}

		return result;
	}

	// Edition ----------------------------------------------------------------

		
	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public ModelAndView edit(@RequestParam int recipeId) {
		
		ModelAndView result;
		Recipe recipe;	
		Collection<Allergen> allergenList;
		allergenList = allergenService.findAll();
		
		recipe = recipeService.findOne(recipeId);
		
		result = editModelAndView(recipe);
		result.addObject("recipe", recipe);
		result.addObject("allergenList", allergenList);
		result.addObject("edit", true);
		
		return result;
		
	}
	
	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "update")
	public ModelAndView update(@Valid Recipe recipe, BindingResult bindingResult) {
		ModelAndView result;
		Collection<Allergen> allergenList;
		allergenList = allergenService.findAll();

		if (bindingResult.hasErrors()){
			result = editModelAndView(recipe, "recipe.commit.error");
			System.out.println(bindingResult);
			result.addObject("allergenList", allergenList);
		}
		else {
			try {
				Recipe saved = recipeService.save(recipe);
				
				result = new ModelAndView("redirect:/recipe/administrator/listIngredients.do?recipeId=" + saved.getId());
				
				 
			} catch (Throwable oops) {
				result = editModelAndView(recipe, "recipe.commit.error");
				result.addObject("allergenList", allergenList);
			}
		}

		return result;
	}
	
	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public ModelAndView add(@RequestParam int ingredientId, @RequestParam int recipeId){
		ModelAndView result;
		Ingredient ingredient = ingredientService.findOne(ingredientId);
		Recipe recipe = recipeService.findOne(recipeId);
		
		
		Quantity quantity = quantityService.create(ingredient,recipe);
		
		result = addModelAndView(quantity);
		result.addObject("ingredient", ingredient);
		result.addObject("recipe", recipe);
		
//		result = new ModelAndView("redirect:/ingredient/administrator/listIngredients.do?recipeId" + recipeId);
		
		return result;
	}
	
	@RequestMapping(value = "/add", method = RequestMethod.POST, params = "save")
	public ModelAndView saveAdd(@Valid Quantity quantity, BindingResult bindingResult) {
		ModelAndView result;

		if (bindingResult.hasErrors()){
			result = addModelAndView(quantity, "quantity.commit.error");
			System.out.println(bindingResult);
		}
		else {
			try {
				quantityService.save(quantity);
				result = new ModelAndView("redirect:/recipe/administrator/listIngredients.do?recipeId=" + quantity.getRecipe().getId());
				
				 
			} catch (Throwable oops) {
				result = addModelAndView(quantity, "quantity.commit.error");
			}
		}

		return result;
	}
	
	@RequestMapping(value = "/addAllergen", method = RequestMethod.GET)
	public ModelAndView addAllergen(@RequestParam int allergenId, @RequestParam int recipeId){
		ModelAndView result;
		Allergen allergen = allergenService.findOne(allergenId);
		Recipe recipe = recipeService.findOne(recipeId);
		
		try{
			recipeService.addAllergen(allergen,recipe);
			result = new ModelAndView("redirect:/recipe/administrator/listIngredients.do?recipeId=" + recipeId);
		}catch(Throwable oops){
			result = new ModelAndView("redirect:/recipe/administrator/listIngredients.do?recipeId=" + recipeId);
			result.addObject("messageError","recipe.commit.error");
		}
				
		return result;
	}
	
	@RequestMapping(value = "/deleteAllergen", method = RequestMethod.GET)
	public ModelAndView deleteAllergen(@RequestParam int allergenId, @RequestParam int recipeId){
		ModelAndView result;
		Allergen allergen = allergenService.findOne(allergenId);
		Recipe recipe = recipeService.findOne(recipeId);
		
		try{
			recipeService.deleteAllergen(allergen,recipe);
			result = new ModelAndView("redirect:/recipe/administrator/listIngredients.do?recipeId=" + recipeId);
		}catch(Throwable oops){
			result = new ModelAndView("redirect:/recipe/administrator/listIngredients.do?recipeId=" + recipeId);
			result.addObject("messageError","recipe.commit.error");
		}
				
		return result;
	}
	
	
	// Ancillary methods ------------------------------------------------------	

	public ModelAndView creationModelAndView(Recipe recipe){
		return creationModelAndView(recipe, null);
	}
	
	public ModelAndView creationModelAndView(Recipe recipe, String message){
		ModelAndView res;
		
		res = new ModelAndView("recipe/edit");
		res.addObject("recipe", recipe);
		res.addObject("messageError", message);
		res.addObject("requestURI", "recipe/administrator/create.do");	

		return res;
	}
	
	public ModelAndView addModelAndView(Quantity quantity){
		return addModelAndView(quantity, null);
	}
	
	public ModelAndView addModelAndView(Quantity quantity, String message){
		ModelAndView res;
		
		res = new ModelAndView("recipe/add");
		res.addObject("quantity", quantity);
		res.addObject("messageError", message);
		res.addObject("requestURI", "recipe/administrator/add.do");
		return res;
	}

	public ModelAndView editModelAndView(Recipe recipe){
		return editModelAndView(recipe, null);
	}
	
	public ModelAndView editModelAndView(Recipe recipe, String message){
		ModelAndView res;
		
		res = new ModelAndView("recipe/edit");
		res.addObject("recipe", recipe);
		res.addObject("messageError", message);
		res.addObject("requestURI", "recipe/administrator/edit.do");
		res.addObject("edit", true);
		return res;
	}
}