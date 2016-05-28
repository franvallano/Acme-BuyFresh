package domain;

import java.util.Collection;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
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
	
	private Recipe recipe;
	private Ingredient ingredient;

	@Valid
	@ManyToOne(optional=true)
	public Recipe getRecipe() {
		return recipe;
	}
	public void setRecipe(Recipe recipe) {
		this.recipe = recipe;
	}

	@Valid
	@ManyToOne(optional=true)
	public Ingredient getIngredient() {
		return ingredient;
	}
	public void setIngredient(Ingredient ingredient) {
		this.ingredient = ingredient;
	}


}
