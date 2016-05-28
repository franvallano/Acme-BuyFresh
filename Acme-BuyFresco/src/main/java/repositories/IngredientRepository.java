/* 
* Autogenerated interface code 
* Variables (text between %) must have been replaced when code is autogenerated
*/
package repositories;

import java.util.Collection;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Ingredient;

@Repository
public interface IngredientRepository extends JpaRepository<Ingredient, Integer>{
	

	@Query("select i from Ingredient i join i.quantities iq join iq.recipe.menus iqrm where iqrm.id = ?1")
	Collection<Ingredient> getIngredientsByMenu(int menuId);
	

	//Con esta query obtienes los alergenos de un usuario, los ingredientes y las cantidades correspondientes a las recetas de un menu dado.
	@Query("select i, iq.recipe, ialler, iq from Ingredient i join i.quantities iq join iq.recipe.menus iqrm join i.allergens ialler where iqrm.id = ?1 and ialler.id = (select alle from Allergen alle join alle.users alleuser where alleuser.id = ?2)")
	List<Object[]> getAllergenIngredientsByUserPerMenu(int menuId, int userId);

	@Query("select i from Ingredient i where i.deleted = false")
	Collection<Ingredient> findAllWithoutDelete();
	
	@Query("select i from Ingredient i join i.quantities q where q.recipe.id = ?1")
	Collection<Ingredient> findIngredientsByRecipeId(int recipeId);
}
