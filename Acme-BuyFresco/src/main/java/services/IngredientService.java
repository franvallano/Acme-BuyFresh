/* 
* Autogenerated service code 
* Variables (text between %) must have been replaced when code is autogenerated
*/

package services;

import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.IngredientRepository;
import domain.Ingredient;

@Service
@Transactional
public class IngredientService {

	// Managed repository -----------------------------------------------------
	@Autowired
	private IngredientRepository ingredientRepository;

	// Ancillary services -----------------------------------------------------

	// Constructor ------------------------------------------------------------
	public IngredientService(){
		super();
	}

	// Simple CRUD methods ----------------------------------------------------
	public Ingredient create(){
		Ingredient newbye;
		
		newbye = new Ingredient();
		
		return newbye;
	}

	public void save(Ingredient entity){
		Assert.notNull(entity);
		
		this.ingredientRepository.save(entity);
	}

	public void delete(Ingredient entity){
		Assert.isTrue(entity.getId()!=0);
		Assert.isTrue(this.ingredientRepository.exists(entity.getId()));
		
		this.ingredientRepository.delete( entity );
		
		Assert.isTrue(!this.ingredientRepository.exists(entity.getId()));
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
	
	// Ancillary methods ------------------------------------------------------

}
