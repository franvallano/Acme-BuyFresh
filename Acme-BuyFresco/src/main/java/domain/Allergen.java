package domain;

import java.util.Collection;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

import org.hibernate.validator.constraints.NotBlank;

@Entity
@Access(AccessType.PROPERTY)
public class Allergen extends DomainEntity{

	//Attributes --------------------------------------------------------------------------------
	public String name;


	
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
	
	public Collection<Ingredient> replaceables;
	public Collection<Ingredient> allergens;
	public Collection<Recipe> recipes;
	public Category category;
	public Collection<User> users;


	@ManyToMany
	public Collection<Ingredient> getReplaceables() {
		return replaceables;
	}


	public void setReplaceables(Collection<Ingredient> replaceables) {
		this.replaceables = replaceables;
	}
	
	@ManyToMany
	public Collection<Ingredient> getAllergens() {
		return allergens;
	}


	public void setAllergens(Collection<Ingredient> allergens) {
		this.allergens = allergens;
	}

	@ManyToMany
	public Collection<Recipe> getRecipes() {
		return recipes;
	}

	public void setRecipes(Collection<Recipe> recipes) {
		this.recipes = recipes;
	}

	@ManyToOne(optional = false)
	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	@ManyToMany(mappedBy = "allergens")
	public Collection<User> getUsers() {
		return users;
	}


	public void setUsers(Collection<User> users) {
		this.users = users;
	}
	
	
	
	
}
