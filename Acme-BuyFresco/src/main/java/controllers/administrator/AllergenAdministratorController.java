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
@RequestMapping("/allergen/administrator")
public class AllergenAdministratorController extends AbstractController {
		
	// Supporting services ----------------------------------------------------

	@Autowired
	private AllergenService allergenService;
	
	
	@Autowired
	private IngredientService ingredientService;
	
	// Constructors -----------------------------------------------------------
	
	public AllergenAdministratorController() {
		super();
	}
	
	// Listing ----------------------------------------------------------------
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list(@RequestParam (required=false, defaultValue="0") String messageError) {
		ModelAndView result;
		Collection<Allergen> ls;
		
		ls = allergenService.findAll();
		
		result = new ModelAndView("allergen/list");
		result.addObject("requestURI", "allergen/administrator/list.do");
		result.addObject("allergens", ls);
		
		
		if(messageError.equals("commit.error")){
			result.addObject("messageError",messageError);
		}	
		
		return result;
	}
	
	// Creation ---------------------------------------------------------------
	
	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ModelAndView create(@RequestParam (required=false, defaultValue="0") String messageError) {
		
		ModelAndView result;
		Allergen r;
		
		r = allergenService.create();
		Collection<Ingredient> ing = ingredientService.findAllWithoutDelete();
		
		result = creationModelAndView(r);
		result.addObject("allergen", r);
		result.addObject("substitutes", ing);
		result.addObject("ingredients", ing);
		
		if(messageError.equals("commit.error")){
			result.addObject("messageError",messageError);
		}
		
		return result;
		
	}
	
	@RequestMapping(value = "/create", method = RequestMethod.POST, params = "save")
	public ModelAndView save(@Valid Allergen b, BindingResult bindingResult) {
		ModelAndView result;
		Collection<Ingredient> ing = ingredientService.findAllWithoutDelete();

		if (bindingResult.hasErrors()){
			result = creationModelAndView(b, "commit.error");
			result.addObject("substitutes", ing);
			result.addObject("ingredients", ing);
			
			System.out.println(bindingResult);
		}
		else {
			try {
				allergenService.save(b);
				
				Allergen a = allergenService.findAllergenByName(b.getName());
				
				Collection<Ingredient> ingre = a.getAllergenIngredients();
				Collection<Ingredient> subs = a.getSubstitutes();
				
				for(Ingredient i : ingre){
					Collection<Allergen> r = i.getAllergens();
					r.add(a);
					i.setAllergens(r);
					
					ingredientService.save2(i);
				}
				
				for(Ingredient i : subs){
					Collection<Allergen> r = i.getReplaceables();
					r.add(a);
					i.setReplaceables(r);
					
					ingredientService.save2(i);
				}
				
				result = new ModelAndView("redirect:/allergen/administrator/list.do");
				
				 
			} catch (Throwable oops) {
				result = creationModelAndView(b, "commit.error");
				result.addObject("substitutes", ing);
				result.addObject("ingredients", ing);
				System.out.println(oops.toString());
			}
		}

		return result;
	}

	// Edition ----------------------------------------------------------------

//	@RequestMapping(value = "/delete", method = RequestMethod.GET)
//	public ModelAndView delete(@RequestParam int allergenId){
//		ModelAndView result;
//		Allergen allergen = allergenService.findOne(allergenId);
//		
//		allergenService.delete(allergen);
//
//		result = new ModelAndView("redirect:/allergen/administrator/list.do");
//		
//		return result;
//	}
	
	
	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public ModelAndView edit(@RequestParam int allergenId) {
		
		Collection<Ingredient> ing = ingredientService.findAllWithoutDelete();
		ModelAndView result;
		Allergen r;
		
		r = allergenService.findOne(allergenId);

		
		result = editModelAndView(r);
		result.addObject("allergen", r);
		result.addObject("substitutes", ing);
		result.addObject("ingredients", ing);
		result.addObject("edit", true);
		
		return result;
		
	}
	
	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "update")
	public ModelAndView update(@Valid Allergen b, BindingResult bindingResult) {
		ModelAndView result;
		
		Collection<Ingredient> ing = ingredientService.findAllWithoutDelete();

		if (bindingResult.hasErrors()){
			result = editModelAndView(b, "commit.error");
			result.addObject("substitutes", ing);
			result.addObject("ingredients", ing);
			
			System.out.println(bindingResult);
		}
		else {
			try {
				
				Allergen a = allergenService.findOne(b.getId());

				allergenService.save(b);
				
				if (a.getAllergenIngredients()!=b.getAllergenIngredients()){
					Collection<Ingredient> ingre = b.getAllergenIngredients();
					Collection<Ingredient> ingredi = a.getAllergenIngredients();
					
					//Añadir
					for(Ingredient i : ingre){
						if(!ingredi.contains(i)){
							Collection<Allergen> r = i.getReplaceables();
							r.add(b);
							i.setReplaceables(r);
							
							ingredientService.save2(i);
						}
					}
					
					//Eliminar
					for(Ingredient i : ingredi){
						if(!ingre.contains(i)){
							Collection<Allergen> r = i.getReplaceables();
							r.remove(b);
							i.setReplaceables(r);
							ingredientService.save2(i);
						}
					}
				}
				
				if (a.getSubstitutes()!=b.getSubstitutes()){
				
					Collection<Ingredient> subs = b.getSubstitutes();
					Collection<Ingredient> su = a.getSubstitutes();
					
					//Añadir
					for(Ingredient i : subs){
						if(!su.contains(i)){
							Collection<Allergen> r = i.getAllergens();
							r.add(b);
							i.setAllergens(r);
							ingredientService.save2(i);
						}
					}
					
					//Eliminar
					for(Ingredient i : su){				
						if(!subs.contains(i)){
							Collection<Allergen> r = i.getAllergens();
							r.remove(b);
							i.setAllergens(r);
							ingredientService.save2(i);
						}
					}
				}
			
				
				result = new ModelAndView("redirect:/allergen/administrator/list.do");
				
				 
			} catch (Throwable oops) {
				result = editModelAndView(b, "commit.error");
				result.addObject("substitutes", ing);
				result.addObject("ingredients", ing);
				System.out.println(oops.toString());
				
			}
		}

		return result;
	}
	
	// Ancillary methods ------------------------------------------------------	

	public ModelAndView creationModelAndView(Allergen b){
		return creationModelAndView(b, null);
	}
	
	public ModelAndView creationModelAndView(Allergen b, String message){
		ModelAndView res;
		
		res = new ModelAndView("allergen/edit");
		res.addObject("allergen", b);
		res.addObject("messageError", message);
		res.addObject("requestURI", "allergen/administrator/create.do");	

		return res;
	}

	public ModelAndView editModelAndView(Allergen ap){
		return editModelAndView(ap, null);
	}
	
	public ModelAndView editModelAndView(Allergen ap, String message){
		ModelAndView res;
		
		res = new ModelAndView("allergen/edit");
		res.addObject("allergen", ap);
		res.addObject("messageError", message);
		res.addObject("requestURI", "allergen/administrator/edit.do");
		res.addObject("edit", true);
		return res;
	}
}