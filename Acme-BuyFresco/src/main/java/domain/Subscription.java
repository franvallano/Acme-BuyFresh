package domain;

import java.util.Collection;
import java.util.Date;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.Valid;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;

import org.hibernate.validator.constraints.Range;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Access(AccessType.PROPERTY)
public class Subscription extends DomainEntity{
	
	//Attributes --------------------------------------------------------------------------------
	private Date creationMoment, finishMoment;
	private Double price;
	private boolean renewal;

	
	//Constructor -------------------------------------------------------------------------------
	
	public Subscription() {
		super();
		// TODO Auto-generated constructor stub
	}



	
	//Getters and setter ------------------------------------------------------------------------
	
	@Past
	@DateTimeFormat(pattern = "dd/MM/yyyy HH:mm")
	@Temporal(TemporalType.TIMESTAMP)
	public Date getCreationMoment() {
		return creationMoment;
	}
	public void setCreationMoment(Date creationMoment) {
		this.creationMoment = creationMoment;
	}

	@DateTimeFormat(pattern = "dd/MM/yyyy HH:mm")
	@Temporal(TemporalType.TIMESTAMP)
	public Date getFinishMoment() {
		return finishMoment;
	}
	public void setFinishMoment(Date finishMoment) {
		this.finishMoment = finishMoment;
	}


	@NotNull
	@Digits(integer = 2, fraction = 2)
	public Double getPrice() {
		return price;
	}
	public void setPrice(Double price) {
		this.price = price;
	}


	public boolean getRenewal() {
		return renewal;
	}
	public void setRenewal(boolean renewal) {
		this.renewal = renewal;
	}
	
	
	//Relationships -----------------------------------------------------------------------------

	private User user;
	private Collection<SalesOrder> orders;

	@Valid
	@ManyToOne(optional = false)
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}

	@Valid
	@OneToMany(mappedBy="subscription")
	public Collection<SalesOrder> getOrders() {
		return orders;
	}
	public void setOrders(Collection<SalesOrder> orders) {
		this.orders = orders;
	}
	

}
