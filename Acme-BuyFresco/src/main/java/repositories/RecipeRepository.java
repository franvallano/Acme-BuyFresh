/* 
* Autogenerated interface code 
* Variables (text between %) must have been replaced when code is autogenerated
*/
package repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Recipe;

@Repository
public interface RecipeRepository extends JpaRepository<Recipe, Integer>{
	
	
	@Query("select r from Recipe r join r.quantities rq where rq.ingredient.name = ?1")
	Collection<Recipe> getRecipesByIngredients(String nameIngredient);
	
	@Query("select r from Recipe r join r.menus m where m.id = ?1")
	Collection<Recipe> findRecipesByMenuId(int menuId);
}
