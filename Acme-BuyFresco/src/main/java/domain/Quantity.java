package domain;

import java.util.Collection;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;

@Entity
@Access(AccessType.PROPERTY)
public class Quantity extends DomainEntity{
	
	//Attributes --------------------------------------------------------------------------------
	public int value;
	
	//Constructor -------------------------------------------------------------------------------

	public Quantity() {
		super();
		// TODO Auto-generated constructor stub
	}


	
	//Getters and setter ------------------------------------------------------------------------
	
	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}
	
	//Relationships -----------------------------------------------------------------------------
	
	public Collection<Recipe> recipes;
	public Collection<Ingredient> ingredients;

	@ManyToMany(mappedBy = "quantities" )
	public Collection<Recipe> getRecipes() {
		return recipes;
	}


	public void setRecipes(Collection<Recipe> recipes) {
		this.recipes = recipes;
	}

	@ManyToMany(mappedBy = "quantities")
	public Collection<Ingredient> getIngredients() {
		return ingredients;
	}


	public void setIngredients(Collection<Ingredient> ingredients) {
		this.ingredients = ingredients;
	}


	
	
	//Attributes --------------------------------------------------------------------------------
	
	//Constructor -------------------------------------------------------------------------------
	
	//Getters and setter ------------------------------------------------------------------------
	
	//Relationships -----------------------------------------------------------------------------

}
