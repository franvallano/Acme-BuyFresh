package domain;

import java.util.Collection;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;

import org.hibernate.validator.constraints.NotBlank;

@Entity
@Access(AccessType.PROPERTY)
public class Recipe extends DomainEntity{
	
	//Attributes --------------------------------------------------------------------------------
	public String name, elaboration;
	public Integer time;

	
	//Constructor -------------------------------------------------------------------------------
	public Recipe() {
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

	@NotBlank
	public String getElaboration() {
		return elaboration;
	}


	public void setElaboration(String elaboration) {
		this.elaboration = elaboration;
	}


	public Integer getTime() {
		return time;
	}


	public void setTime(Integer time) {
		this.time = time;
	}
	
	
	
	//Relationships -----------------------------------------------------------------------------

	public Collection<Menu> menus;
	public Collection<Quantity> quantities;
	public Collection<Allergen> allergens;


	@ManyToMany()
	public Collection<Menu> getMenus() {
		return menus;
	}
	
	public void setMenus(Collection<Menu> menus) {
		this.menus = menus;
	}

	public Collection<Quantity> getQuantities() {
		return quantities;
	}

	public void setQuantities(Collection<Quantity> quantities) {
		this.quantities = quantities;
	}

	@ManyToMany(mappedBy = "recipes")
	public Collection<Allergen> getAllergens() {
		return allergens;
	}

	public void setAllergens(Collection<Allergen> allergens) {
		this.allergens = allergens;
	}
	
	
}
