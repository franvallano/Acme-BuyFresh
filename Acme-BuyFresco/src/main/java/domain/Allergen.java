package domain;

import java.util.Collection;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
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
	
	private Collection<Ingredient> replaceables;
	private Collection<Ingredient> allergens;
	private Collection<Recipe> recipes;
	private Category category;
	private Collection<User> users;

	@Valid
	@ManyToMany
	public Collection<Ingredient> getReplaceables() {
		return replaceables;
	}
	public void setReplaceables(Collection<Ingredient> replaceables) {
		this.replaceables = replaceables;
	}
	
	@Valid
	@ManyToMany
	public Collection<Ingredient> getAllergens() {
		return allergens;
	}
	public void setAllergens(Collection<Ingredient> allergens) {
		this.allergens = allergens;
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
	@ManyToOne(optional = false)
	public Category getCategory() {
		return category;
	}
	public void setCategory(Category category) {
		this.category = category;
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
