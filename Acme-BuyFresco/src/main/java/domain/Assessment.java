package domain;


import java.util.Date;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Past;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.Range;

@Entity
@Access(AccessType.PROPERTY)
public class Assessment extends DomainEntity{
	
	//Attributes --------------------------------------------------------------------------------
	
	public Date moment;
	public Integer generalRating, locationRating, serviceRating, qualityPriceRating;
	public String text;
	public Boolean deleted;

	
	//Constructor -------------------------------------------------------------------------------
	
	public Assessment() {
		super();
		// TODO Auto-generated constructor stub
	}

	
	//Getters and setter ------------------------------------------------------------------------

	@Past
	public Date getMoment() {
		return moment;
	}


	public void setMoment(Date moment) {
		this.moment = moment;
	}

	@Range(min = 0, max = 5)
	public Integer getGeneralRating() {
		return generalRating;
	}


	public void setGeneralRating(Integer generalRating) {
		this.generalRating = generalRating;
	}

	@Range(min = 0, max = 5)
	public Integer getLocationRating() {
		return locationRating;
	}


	public void setLocationRAting(Integer locationRating) {
		this.locationRating = locationRating;
	}

	@Range(min = 0, max = 5)
	public Integer getServiceRating() {
		return serviceRating;
	}


	public void setServiceRating(Integer serviceRating) {
		this.serviceRating = serviceRating;
	}

	@Range(min = 0, max = 5)
	public Integer getQualityPriceRating() {
		return qualityPriceRating;
	}


	public void setQualityPriceRating(Integer qualityPriceRating) {
		this.qualityPriceRating = qualityPriceRating;
	}

	@NotBlank
	public String getText() {
		return text;
	}


	public void setText(String text) {
		this.text = text;
	}


	public Boolean getDeleted() {
		return deleted;
	}


	public void setDeleted(Boolean deleted) {
		this.deleted = deleted;
	}
	
	
	//Relationships -----------------------------------------------------------------------------
	
	public User user;

	@ManyToOne(optional = false)
	public User getUser() {
		return user;
	}


	public void setUser(User user) {
		this.user = user;
	}
	
	

}
