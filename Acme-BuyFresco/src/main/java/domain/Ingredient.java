package domain;

import java.util.Collection;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.validation.Valid;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.URL;

@Entity
@Access(AccessType.PROPERTY)
public class Ingredient extends DomainEntity{
	
	//Attributes --------------------------------------------------------------------------------
	private String name, description, picture, metricUnit;
	private boolean deleted;

	
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
	@NotBlank
	public String getPicture() {
		return picture;
	}
	public void setPicture(String picture) {
		this.picture = picture;
	}

	
	@NotBlank
	public String getMetricUnit() {
		return metricUnit;
	}
	public void setMetricUnit(String metricUnit) {
		this.metricUnit = metricUnit;
	}


	public boolean getDeleted() {
		return deleted;
	}
	public void setDeleted(boolean deleted) {
		this.deleted = deleted;
	}
	
	//Relationships -----------------------------------------------------------------------------

	private Collection<Quantity> quantities;
	private Collection<Allergen> allergens;
	private Collection<Allergen> replaceables;


	@Valid
	@ManyToMany
	public Collection<Quantity> getQuantities() {
		return quantities;
	}
	public void setQuantities(Collection<Quantity> quantities) {
		this.quantities = quantities;
	}

	@Valid
	@ManyToMany
	@JoinTable(name="ingredients_allergens", joinColumns=@JoinColumn(name="allergen_id"), inverseJoinColumns=@JoinColumn(name="ingredient_id")) 
	public Collection<Allergen> getAllergens() {
		return allergens;
	}
	public void setAllergens(Collection<Allergen> allergens) {
		this.allergens = allergens;
	}

	@Valid
	@ManyToMany
	@JoinTable(name="ingredients_replaceables", joinColumns=@JoinColumn(name="allergen_id"), inverseJoinColumns=@JoinColumn(name="ingredient_id"))
	public Collection<Allergen> getReplaceables() {
		return replaceables;
	}
	public void setReplaceables(Collection<Allergen> replaceables) {
		this.replaceables = replaceables;
	}
	
}
