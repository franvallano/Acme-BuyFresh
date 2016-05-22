package domain;
import java.util.Collection;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.validation.Valid;

@Entity
@Access(AccessType.PROPERTY)
public class Clerk extends Actor{


	//Attributes --------------------------------------------------------------------------------
	
	//Constructor -------------------------------------------------------------------------------
	public Clerk() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	//Getters and setter ------------------------------------------------------------------------
	private boolean deleted;
	
	public boolean isDeleted() {
		return deleted;
	}
	public void setDeleted(boolean deleted) {
		this.deleted = deleted;
	}

	
	
	//Relationships -----------------------------------------------------------------------------
	
	private Collection<Order> orders;

	@Valid
	@OneToMany(mappedBy = "clerk")
	public Collection<Order> getOrders() {
		return orders;
	}
	public void setOrders(Collection<Order> orders) {
		this.orders = orders;
	}
	
	

}
