/* 
* Autogenerated service code 
* Variables (text between %) must have been replaced when code is autogenerated
*/

package services;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.RecipeRepository;
import domain.Allergen;
import domain.Ingredient;
import domain.Menu;
import domain.Quantity;
import domain.Recipe;

@Service
@Transactional
public class RecipeService {

	// Managed repository -----------------------------------------------------
	@Autowired
	private RecipeRepository recipeRepository;

	// Ancillary services -----------------------------------------------------

	// Constructor ------------------------------------------------------------
	public RecipeService(){
		super();
	}

	// Simple CRUD methods ----------------------------------------------------
	public Recipe create(){
		Recipe newbye;
		Collection<Quantity> quantities = new ArrayList<Quantity>();
		Collection<Menu> menus = new ArrayList<Menu>();
		Collection<Allergen> allergens = new ArrayList<Allergen>();
		
		newbye = new Recipe();
		newbye.setQuantities(quantities);
		newbye.setMenus(menus);
		newbye.setAllergens(allergens);
		
		return newbye;
	}

	public Recipe save(Recipe entity){
		Assert.notNull(entity);
		
		Recipe result = this.recipeRepository.save(entity);
		
		return result;
	}

	public void delete(Recipe entity){
		Assert.isTrue(entity.getId()!=0);
		Assert.isTrue(this.recipeRepository.exists(entity.getId()));
		
		this.recipeRepository.delete( entity );
		
		Assert.isTrue(!this.recipeRepository.exists(entity.getId()));
	}

	public Recipe findOne(int id){
		Assert.isTrue(id != 0);
		
		Recipe res;
		
		res = this.recipeRepository.findOne(id);
		
		return res;
	}

	public Collection<Recipe> findAll(){
		Collection<Recipe> res;
		
		res = recipeRepository.findAll();
		
		return res;
	}

	// Other business methods -------------------------------------------------

	
	public Collection<Recipe> getRecipesByIngredients(String nameIngredient){
		Collection<Recipe> res;
		
		res = recipeRepository.getRecipesByIngredients(nameIngredient);
		
		return res;
	}
	// Ancillary methods ------------------------------------------------------

}
