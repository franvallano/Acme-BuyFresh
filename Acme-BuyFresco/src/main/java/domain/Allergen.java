package domain;

import java.util.Collection;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.Transient;
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
	
	@Transient
	public String getSubstitutesFormated(){
		String result = "";
		int i = 0;
		for(Ingredient b : substitutes){
			if(i < substitutes.size()){
				result = result + b.getName() + ", " ; 
				i++;
			}else{
				result = result + b.getName();
			}
		}
		return result;
	}
	
	@Transient
	public String getIngredientsFormated(){
		String result = "";
		int i = 0;
		for(Ingredient b : allergenIngredients){
			if(i < allergenIngredients.size()){
				result = result + b.getName() + ", " ; 
				i++;
			}else{
				result = result + b.getName();
			}
		}
		return result;
	}
	
	//Relationships -----------------------------------------------------------------------------
	
	private Collection<Ingredient> substitutes;
	private Collection<Ingredient> allergenIngredients;
	private Collection<Recipe> recipes;
	private Collection<User> users;

	
	@ManyToMany(mappedBy="allergens")
	public Collection<Ingredient> getSubstitutes() {
		return substitutes;
	}
	public void setSubstitutes(Collection<Ingredient> substitutes) {
		this.substitutes = substitutes;
	}
	
	
	@ManyToMany(mappedBy="replaceables")
	public Collection<Ingredient> getAllergenIngredients() {
		return allergenIngredients;
	}
	public void setAllergenIngredients(Collection<Ingredient> ingredients) {
		this.allergenIngredients = ingredients;
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
