package domain;
import java.util.Collection;
import java.util.Date;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

import org.hibernate.validator.constraints.NotBlank;

@Entity
@Access(AccessType.PROPERTY)
public class Menu extends DomainEntity{
	
	//Attributes --------------------------------------------------------------------------------
	
	public String name, type;
	public Date creationMoment;
	public Integer duration, people;

	
	//Constructor -------------------------------------------------------------------------------
	public Menu() {
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


	public Date getCreationMoment() {
		return creationMoment;
	}


	public void setCreationMoment(Date creationMoment) {
		this.creationMoment = creationMoment;
	}


	public Integer getDuration() {
		return duration;
	}


	public void setDuration(Integer duration) {
		this.duration = duration;
	}
	
	public String getType() {
		return type;
	}
	
	@NotBlank
	public void setType(String type) {
		this.type = type;
	}

	public Integer getPeople() {
		return people;
	}

	public void setPeople(Integer people) {
		this.people = people;
	}
	
	
	//Relationships -----------------------------------------------------------------------------

	public Collection<Recipe> recipes;
	public Collection<Order> orders;
	public Collection<Ingredient> ingredients;

	@ManyToMany(mappedBy = "menus")
	public Collection<Recipe> getRecipes() {
		return recipes;
	}

	public void setRecipes(Collection<Recipe> recipes) {
		this.recipes = recipes;
	}
	
	@OneToMany(mappedBy = "menu")
	public Collection<Order> getOrders() {
		return orders;
	}

	public void setOrders(Collection<Order> orders) {
		this.orders = orders;
	}

	@ManyToMany(mappedBy = "menus")
	public Collection<Ingredient> getIngredients() {
		return ingredients;
	}

	public void setIngredients(Collection<Ingredient> ingredients) {
		this.ingredients = ingredients;
	}
	
	

}
