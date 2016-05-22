package domain;
import java.util.Collection;
import java.util.Date;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.Past;

import org.hibernate.validator.constraints.NotBlank;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Access(AccessType.PROPERTY)
public class Menu extends DomainEntity{
	
	//Attributes --------------------------------------------------------------------------------
	
	private String name, type;
	private Date creationMoment;
	private Integer duration, people;
	private boolean deleted;

	
	//Constructor -------------------------------------------------------------------------------
	public Menu() {
		super();
		// TODO Auto-generated constructor stub
	}
	//Getters and setter ------------------------------------------------------------------------

	public boolean isDeleted() {
		return deleted;
	}

	public void setDeleted(boolean deleted) {
		this.deleted = deleted;
	}
	
	
	@NotBlank
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	@Past
	@DateTimeFormat(pattern = "dd/MM/yyyy HH:mm")
	@Temporal(TemporalType.TIMESTAMP)
	public Date getCreationMoment() {
		return creationMoment;
	}
	public void setCreationMoment(Date creationMoment) {
		this.creationMoment = creationMoment;
	}


	@Min(0)
	public Integer getDuration() {
		return duration;
	}
	public void setDuration(Integer duration) {
		this.duration = duration;
	}
	
	//Es un campo opcional que se escribe al ser una o varias recetas alteradas por los alergenos.
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}

	@Min(0)
	public Integer getPeople() {
		return people;
	}
	public void setPeople(Integer people) {
		this.people = people;
	}
	
	
	//Relationships -----------------------------------------------------------------------------

	private Collection<Recipe> recipes;
	private Collection<Order> orders;
	private Collection<Ingredient> ingredients;

	@Valid
	@ManyToMany(mappedBy = "menus")
	public Collection<Recipe> getRecipes() {
		return recipes;
	}
	public void setRecipes(Collection<Recipe> recipes) {
		this.recipes = recipes;
	}
	
	@Valid
	@OneToMany(mappedBy = "menu")
	public Collection<Order> getOrders() {
		return orders;
	}
	public void setOrders(Collection<Order> orders) {
		this.orders = orders;
	}

	@Valid
	@ManyToMany(mappedBy = "menus")
	public Collection<Ingredient> getIngredients() {
		return ingredients;
	}
	public void setIngredients(Collection<Ingredient> ingredients) {
		this.ingredients = ingredients;
	}
	
	

}
