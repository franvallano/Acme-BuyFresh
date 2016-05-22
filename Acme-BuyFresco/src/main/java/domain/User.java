package domain;

import java.util.Collection;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.validation.Valid;

import org.hibernate.validator.constraints.NotBlank;

import datatypes.CreditCard;

@Entity
@Access(AccessType.PROPERTY)
public class User extends Actor{

	//Attributes --------------------------------------------------------------------------------
	private String address;
	private boolean deleted;
	private CreditCard creditCard;

	//Constructor -------------------------------------------------------------------------------
	public User() {
		super();
		// TODO Auto-generated constructor stub
	}


	//Getters and setter ------------------------------------------------------------------------
	
	@NotBlank
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}

	public boolean getDeleted() {
		return deleted;
	}
	public void setDeleted(boolean deleted) {
		this.deleted = deleted;
	}

	public CreditCard getCreditCard() {
		return creditCard;
	}
	public void setCreditCard(CreditCard creditCard) {
		this.creditCard = creditCard;
	}
	
	//Relationships -----------------------------------------------------------------------------

	
	private Collection<Assessment> assessments;
	private Collection<Subscription> subcriptions;
	private Collection<Allergen> allergens;

	@Valid
	@OneToMany(mappedBy = "user")
	public Collection<Assessment> getAssessments() {
		return assessments;
	}
	public void setAssessments(Collection<Assessment> assessments) {
		this.assessments = assessments;
	}

	@Valid
	@OneToMany(mappedBy = "user")
	public Collection<Subscription> getSubcriptions() {
		return subcriptions;
	}
	public void setSubcriptions(Collection<Subscription> subcriptions) {
		this.subcriptions = subcriptions;
	}

	@Valid
	@ManyToMany
	public Collection<Allergen> getAllergens() {
		return allergens;
	}
	public void setAllergens(Collection<Allergen> allergens) {
		this.allergens = allergens;
	}
	
}
