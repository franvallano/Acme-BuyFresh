/* 
* Autogenerated service code 
* Variables (text between %) must have been replaced when code is autogenerated
*/

package services;

import java.util.Collection;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.SubscriptionRepository;
import domain.Subscription;
import domain.User;

@Service
@Transactional
public class SubscriptionService {

	// Managed repository -----------------------------------------------------
	@Autowired
	private SubscriptionRepository subscriptionRepository;

	// Ancillary services -----------------------------------------------------
	@Autowired
	private UserService userService;
	
	
	// Constructor ------------------------------------------------------------
	public SubscriptionService(){
		super();
	}

	// Simple CRUD methods ----------------------------------------------------
	@SuppressWarnings("deprecation")
	public Subscription create(){
		Subscription newbye;
		Date date;
		Date finishDate;
		date = new Date(System.currentTimeMillis()-1);
		
		finishDate = date;
		
		finishDate.setDate(date.getDate()+7);
		
		User user = userService.findByPrincipal();
		
		newbye = new Subscription();
		
		newbye.setUser(user);
		newbye.setCreationMoment(date);
		newbye.setFinishMoment(finishDate);
		newbye.setRenewal(false);
		
		return newbye;
	}

	public void save(Subscription entity){
		Assert.notNull(entity);
		
		this.subscriptionRepository.save(entity);
	}

	public Subscription findOne(int id){
		Assert.isTrue(id != 0);
		
		Subscription res;
		
		res = this.subscriptionRepository.findOne(id);
		
		return res;
	}

	public Collection<Subscription> findAll(){
		Collection<Subscription> res;
		
		res = subscriptionRepository.findAll();
		
		return res;
	}

	// Other business methods -------------------------------------------------

	public void renewal(Subscription entity){
		Assert.notNull(entity);
		
		entity.setRenewal(true);
		
		this.save(entity);
	}
	
	// Ancillary methods ------------------------------------------------------

}
