package domain;

import java.util.Collection;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.validation.Valid;

import org.hibernate.validator.constraints.NotBlank;

@Entity
@Access(AccessType.PROPERTY)
public class Allergen extends DomainEntity{

	//Attributes --------------------------------------------------------------------------------
	private String name;


	
	//Constructor -------------------------------------------------------------------------------
	
	public Allergen() {
		super();
		// TODO Auto-generated constructor stub
	}

	
	//Getters and setter ------------------------------------------------------------------------
	@NotBlank
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	//Relationships -----------------------------------------------------------------------------
	
	private Collection<Ingredient> substitutes;
	private Collection<Ingredient> ingredients;
	private Collection<Recipe> recipes;
	private Collection<User> users;

	@Valid
	@ManyToMany
	public Collection<Ingredient> getSubtitutes() {
		return substitutes;
	}
	public void setSubtitutes(Collection<Ingredient> substitutes) {
		this.substitutes = substitutes;
	}
	
	@Valid
	@ManyToMany
	public Collection<Ingredient> getIngredients() {
		return ingredients;
	}
	public void setIngredients(Collection<Ingredient> ingredients) {
		this.ingredients = ingredients;
	}
	
	@Valid
	@ManyToMany
	public Collection<Recipe> getRecipes() {
		return recipes;
	}
	public void setRecipes(Collection<Recipe> recipes) {
		this.recipes = recipes;
	}


	@Valid
	@ManyToMany(mappedBy = "allergens")
	public Collection<User> getUsers() {
		return users;
	}
	public void setUsers(Collection<User> users) {
		this.users = users;
	}
	
	
}
