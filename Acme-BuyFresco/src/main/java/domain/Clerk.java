package domain;
import java.util.Collection;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;

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
	
	//Relationships -----------------------------------------------------------------------------
	
	public Collection<Order> orders;

	@OneToMany(mappedBy = "clerk")
	public Collection<Order> getOrders() {
		return orders;
	}

	public void setOrders(Collection<Order> orders) {
		this.orders = orders;
	}
	
	

}
