package domain;

import java.util.Collection;
import java.util.Date;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.Valid;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Access(AccessType.PROPERTY)
public class SalesOrder extends DomainEntity{
	
	//Attributes --------------------------------------------------------------------------------
	private String address;
	private Date shippingDate, arrivalDate;
	private String notes;
	private Double totalPrice;
	private Boolean sent;

	
	//Constructor -------------------------------------------------------------------------------
	
	public SalesOrder() {
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

	@Column(nullable=true)
	@DateTimeFormat(pattern = "dd/MM/yyyy HH:mm")
	@Temporal(TemporalType.TIMESTAMP)
	public Date getShippingDate() {
		return shippingDate;
	}
	public void setShippingDate(Date shippingDate) {
		this.shippingDate = shippingDate;
	}

	@Column(nullable=true)
	@DateTimeFormat(pattern = "dd/MM/yyyy HH:mm")
	@Temporal(TemporalType.TIMESTAMP)
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
	
	public Boolean getSent() {
		return sent;
	}


	public void setSent(Boolean sent) {
		this.sent = sent;
	}
	
	@NotNull
	@Digits(integer = 2, fraction = 2)
	public Double getTotalPrice() {
		return totalPrice;
	}
	public void setTotalPrice(Double totalPrice) {
		this.totalPrice = totalPrice;
	}
	
	//Relationships -----------------------------------------------------------------------------

	private Menu menu;
	private Subscription subscription;
	private Collection<Substitutes> substitutes;
	private Clerk clerk;

	@Valid
	@ManyToOne(optional = false)
	public Menu getMenu() {
		return menu;
	}
	public void setMenu(Menu menu) {
		this.menu = menu;
	}

	@Valid
	@ManyToOne(optional = true)
	public Clerk getClerk() {
		return clerk;
	}
	public void setClerk(Clerk clerk) {
		this.clerk = clerk;
	}

	@Valid
	@ManyToOne(optional = false)
	public Subscription getSubscription() {
		return subscription;
	}
	public void setSubscription(Subscription subscription) {
		this.subscription = subscription;
	}

	@Valid
	@OneToMany(mappedBy = "order")
	public Collection<Substitutes> getSubstitutes() {
		return substitutes;
	}
	public void setSubstitutes(Collection<Substitutes> substitutes) {
		this.substitutes = substitutes;
	}
	

}
