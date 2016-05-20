package domain;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;

import org.hibernate.validator.constraints.NotBlank;

@Entity
@Access(AccessType.PROPERTY)
public class Substitutes extends DomainEntity{
	
	//Attributes --------------------------------------------------------------------------------
	
	public String name, metricUnit, recipeName;
	public Integer quantity;

	
	//Constructor -------------------------------------------------------------------------------
	
	public Substitutes() {
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


	public String getMetricUnit() {
		return metricUnit;
	}


	public void setMetricUnit(String metricUnit) {
		this.metricUnit = metricUnit;
	}

	@NotBlank
	public String getRecipeName() {
		return recipeName;
	}


	public void setRecipeName(String recipeName) {
		this.recipeName = recipeName;
	}


	public Integer getQuantity() {
		return quantity;
	}


	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}
	
	
	//Relationships -----------------------------------------------------------------------------
	
	public Order order;

	@ManyToOne(optional = false)
	public Order getOrder() {
		return order;
	}


	public void setOrder(Order order) {
		this.order = order;
	}
	
	

}
