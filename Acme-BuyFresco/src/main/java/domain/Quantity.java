package domain;

import java.util.Collection;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.validation.Valid;
import javax.validation.constraints.Min;

@Entity
@Access(AccessType.PROPERTY)
public class Quantity extends DomainEntity{
	
	//Attributes --------------------------------------------------------------------------------
	private int value;
	
	//Constructor -------------------------------------------------------------------------------

	public Quantity() {
		super();
		// TODO Auto-generated constructor stub
	}


	//Getters and setter ------------------------------------------------------------------------
	
	@Min(0)
	public int getValue() {
		return value;
	}
	public void setValue(int value) {
		this.value = value;
	}
	
	//Relationships -----------------------------------------------------------------------------
	
	private Collection<Recipe> recipes;
	private Collection<Ingredient> ingredients;

	@Valid
	@ManyToMany
	public Collection<Recipe> getRecipes() {
		return recipes;
	}
	public void setRecipes(Collection<Recipe> recipes) {
		this.recipes = recipes;
	}

	@Valid
	@ManyToMany(mappedBy = "quantities")
	public Collection<Ingredient> getIngredients() {
		return ingredients;
	}
	public void setIngredients(Collection<Ingredient> ingredients) {
		this.ingredients = ingredients;
	}


}
