package domain;
import java.util.Collection;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;

import org.hibernate.validator.constraints.NotBlank;

@Entity
@Access(AccessType.PROPERTY)
public class Category extends DomainEntity{
	
	//Attributes --------------------------------------------------------------------------------
	public String name;
	
	//Constructor -------------------------------------------------------------------------------
	
	public Category() {
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

	public Collection<Allergen> allergens;


	@OneToMany(mappedBy = "category")
	public Collection<Allergen> getAllergens() {
		return allergens;
	}


	public void setAllergens(Collection<Allergen> allergens) {
		this.allergens = allergens;
	}
	
	
}
