package domain;

import java.util.Collection;
import java.util.Date;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.hibernate.validator.constraints.NotBlank;

@Entity
@Access(AccessType.PROPERTY)
public class Order extends DomainEntity{
	
	//Attributes --------------------------------------------------------------------------------
	public String address;
	public Date shippingDAte, arrivalDate;
	public String notes;

	
	//Constructor -------------------------------------------------------------------------------
	
	public Order() {
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


	public Date getShippingDAte() {
		return shippingDAte;
	}


	public void setShippingDAte(Date shippingDAte) {
		this.shippingDAte = shippingDAte;
	}


	public Date getArrivalDate() {
		return arrivalDate;
	}


	public void setArrivalDate(Date arrivalDate) {
		this.arrivalDate = arrivalDate;
	}


	public String getNotes() {
		return notes;
	}


	public void setNotes(String notes) {
		this.notes = notes;
	}
	
	//Relationships -----------------------------------------------------------------------------

	public Menu menu;
	public Collection<Subscription> subscriptions;
	public Collection<Substitutes> substitutes;
	public Clerk clerk;

	@ManyToOne(optional = false)
	public Menu getMenu() {
		return menu;
	}


	public void setMenu(Menu menu) {
		this.menu = menu;
	}

	@ManyToOne(optional = true)
	public Clerk getClerk() {
		return clerk;
	}


	public void setClerk(Clerk clerk) {
		this.clerk = clerk;
	}

	@OneToMany(mappedBy = "order")
	public Collection<Subscription> getSubscriptions() {
		return subscriptions;
	}


	public void setSubscriptions(Collection<Subscription> subscriptions) {
		this.subscriptions = subscriptions;
	}

	@OneToMany(mappedBy = "order")
	public Collection<Substitutes> getSubstitutes() {
		return substitutes;
	}


	public void setSubstitutes(Collection<Substitutes> substitutes) {
		this.substitutes = substitutes;
	}
	
	
	
	
	

}
