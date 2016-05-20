package domain;

import java.util.Collection;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.URL;

@Entity
@Access(AccessType.PROPERTY)
public class Ingredient extends DomainEntity{
	
	//Attributes --------------------------------------------------------------------------------
	public String name, description, picture, metricUnit;
	public Boolean deleted;

	
	//Constructor -------------------------------------------------------------------------------
	public Ingredient() {
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
	public String getDescription() {
		return description;
	}


	public void setDescription(String description) {
		this.description = description;
	}

	@URL
	public String getPicture() {
		return picture;
	}


	public void setPicture(String picture) {
		this.picture = picture;
	}


	public String getMetricUnit() {
		return metricUnit;
	}


	public void setMetricUnit(String metricUnit) {
		this.metricUnit = metricUnit;
	}


	public Boolean getDeleted() {
		return deleted;
	}


	public void setDeleted(Boolean deleted) {
		this.deleted = deleted;
	}
	
	//Relationships -----------------------------------------------------------------------------

	public Collection<Menu> menus;
	public Collection<Quantity> quantities;
	public Collection<Allergen> substitute;
	public Collection<Allergen> ingredients;

	@ManyToMany
	public Collection<Menu> getMenus() {
		return menus;
	}



	public void setMenus(Collection<Menu> menus) {
		this.menus = menus;
	}


	@ManyToMany
	public Collection<Quantity> getQuantities() {
		return quantities;
	}



	public void setQuantities(Collection<Quantity> quantities) {
		this.quantities = quantities;
	}


	@ManyToMany(mappedBy = "replaceables")
	public Collection<Allergen> getSubstitute() {
		return substitute;
	}



	public void setSubstitute(Collection<Allergen> substitute) {
		this.substitute = substitute;
	}


	@ManyToMany(mappedBy = "allergens")
	public Collection<Allergen> getIngredients() {
		return ingredients;
	}



	public void setIngredients(Collection<Allergen> ingredients) {
		this.ingredients = ingredients;
	}
	
	
	
}
