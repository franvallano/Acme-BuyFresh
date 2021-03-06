/* 
* Autogenerated service code 
* Variables (text between %) must have been replaced when code is autogenerated
*/

package services;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.IngredientRepository;
import domain.Administrator;
import domain.Allergen;
import domain.Ingredient;
import domain.Menu;
import domain.Quantity;
import domain.Recipe;

@Service
@Transactional
public class IngredientService {

	// Managed repository -----------------------------------------------------
	@Autowired
	private IngredientRepository ingredientRepository;

	// Ancillary services -----------------------------------------------------

	@Autowired
	private AllergenService allergenService;
	
	@Autowired
	private AdministratorService administratorService;
	
	// Constructor ------------------------------------------------------------
	public IngredientService(){
		super();
	}

	// Simple CRUD methods ----------------------------------------------------
	public Ingredient create(){
		Ingredient newbye;
		
		Administrator administrator;
		
		administrator = administratorService.findByPrincipal();
		
		
		Collection<Quantity> quantities = new ArrayList<Quantity>();
		Collection<Allergen> replaceables = new ArrayList<Allergen>();
		Collection<Allergen> allergens = new ArrayList<Allergen>();
		Boolean deleted = false;
		
		newbye = new Ingredient();
		newbye.setQuantities(quantities);
		newbye.setAllergens(allergens);
		newbye.setReplaceables(replaceables);
		newbye.setDeleted(deleted);
		
		return newbye;
	}

	public void save(Ingredient entity){
		Assert.notNull(entity);
		
		for(Allergen a : entity.getAllergens()){
			a.getAllergenIngredients().add(entity);
			
			allergenService.save(a);
		}
				
		
		this.ingredientRepository.save(entity);
	}
	
	public void save2(Ingredient entity){
		Assert.notNull(entity);				
		
		this.ingredientRepository.save(entity);
	}
	
	public void delete(Ingredient entity){
		Administrator administrator;
		
		administrator = administratorService.findByPrincipal();
		Assert.isTrue(entity.getId()!=0);
		Assert.isTrue(!entity.getDeleted());
				
		boolean deleted = true;
		
		entity.setDeleted(deleted);
		
		this.ingredientRepository.save(entity);
	}

	public Ingredient findOne(int id){
		Assert.isTrue(id != 0);
		
		Ingredient res;
		
		res = this.ingredientRepository.findOne(id);
		
		return res;
	}

	public Collection<Ingredient> findAll(){
		Collection<Ingredient> res;
		
		res = ingredientRepository.findAll();
		
		return res;
	}

	// Other business methods -------------------------------------------------

	
	public Collection<Ingredient> getIngredientsByMenu(int menuId){
		Collection<Ingredient> ingredients;
		
		ingredients = ingredientRepository.getIngredientsByMenu(menuId);
		
		return ingredients;
	}
	
	
	public List<Object[]> getAllergenIngredientsByUserPerMenu(int menuId, int userId){
		List<Object[]> ingredients;
		
		ingredients = ingredientRepository.getAllergenIngredientsByUserPerMenu(menuId, userId);
		
		return ingredients;
	}
	
	public Collection<Ingredient> findAllWithoutDelete(){
		Collection<Ingredient> ingredients;
		
		ingredients = ingredientRepository.findAllWithoutDelete();
		
		return ingredients;
	}
	
	
	// Ancillary methods ------------------------------------------------------

	public Collection<Ingredient> findNoIngredients(Recipe recipe){
		Collection<Ingredient> result = new ArrayList<Ingredient>();
		Collection<Ingredient> all;
		Collection<Ingredient> filtrado;
		
		all = ingredientRepository.findAllWithoutDelete();
		filtrado = ingredientRepository.findIngredientsByRecipeId(recipe.getId());
		
		result.addAll(all);
		result.removeAll(filtrado);
		
		return result;
	}
	
	public Collection<Ingredient> findIngredientByRecipe(int recipeId){
		Collection<Ingredient> result;
		
		result = ingredientRepository.findIngredientsByRecipeId(recipeId);
				
		return result;
	}
	
	public Collection<Object[]> findIngredientsWithQuantities(int recipeId){
		Collection<Object[]> result;
		
		result = ingredientRepository.findIngredientsWithQuantities(recipeId);
		
		return result;
	}
	
}
