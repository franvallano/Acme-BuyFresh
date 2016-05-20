package domain;

import java.util.Collection;
import java.util.Date;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;

import org.hibernate.validator.constraints.Range;

@Entity
@Access(AccessType.PROPERTY)
public class Subscription extends DomainEntity{
	
	//Attributes --------------------------------------------------------------------------------
	public Date creationMoment, finishMoment;
	public Integer people;
	public Double price;
	public Boolean renewal;

	
	//Constructor -------------------------------------------------------------------------------
	
	public Subscription() {
		super();
		// TODO Auto-generated constructor stub
	}



	
	//Getters and setter ------------------------------------------------------------------------
	
	@Past
	public Date getCreationMoment() {
		return creationMoment;
	}


	public void setCreationMoment(Date creationMoment) {
		this.creationMoment = creationMoment;
	}


	public Date getFinishMoment() {
		return finishMoment;
	}


	public void setFinishMoment(Date finishMoment) {
		this.finishMoment = finishMoment;
	}


	public Integer getPeople() {
		return people;
	}


	public void setPeople(Integer people) {
		this.people = people;
	}

	@NotNull
	@Range(min = 0, max = 4)
	@Digits(integer = 2, fraction = 2)
	public Double getPrice() {
		return price;
	}


	public void setPrice(Double price) {
		this.price = price;
	}


	public Boolean getRenewal() {
		return renewal;
	}


	public void setRenewal(Boolean renewal) {
		this.renewal = renewal;
	}
	
	
	//Relationships -----------------------------------------------------------------------------

	public User user;
	public Order order;


	@ManyToOne(optional = false)
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@ManyToOne(optional = false)
	public Order getOrder() {
		return order;
	}
	
	public void setOrder(Order order) {
		this.order = order;
	}
	
	
	

}
