/* 
* Autogenerated service code 
* Variables (text between %) must have been replaced when code is autogenerated
*/

package services;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.ClerkRepository;
import security.Authority;
import security.LoginService;
import security.UserAccount;
import domain.Clerk;
import domain.SalesOrder;
import forms.ClerkForm;

@Service
@Transactional
public class ClerkService {

	// Managed repository -----------------------------------------------------
	@Autowired
	private ClerkRepository clerkRepository;

	// Ancillary services -----------------------------------------------------

	@Autowired
	private AdministratorService administratorService;
	
	// Constructor ------------------------------------------------------------
	public ClerkService(){
		super();
	}

	// Simple CRUD methods ----------------------------------------------------
	public Clerk create(){
		Clerk newbye;
		Collection<SalesOrder>sales = new ArrayList<SalesOrder>();
		
		newbye = new Clerk();
		
		newbye.setOrders(sales);
		newbye.setDeleted(false);
		newbye.setUserAccount(this.createUserAccount());
		
		return newbye;
	}

	public void save(Clerk clerk, String rPass){
		String pass;
		Md5PasswordEncoder encoder;
		encoder = new Md5PasswordEncoder();
		
		Assert.notNull(clerk);
		Assert.isTrue(clerk.getUserAccount().getPassword().equals(rPass));
		
		pass = clerk.getUserAccount().getPassword();
		pass = encoder.encodePassword(pass, null);
		clerk.getUserAccount().setPassword(pass);
		
		clerkRepository.save(clerk);
	}
	
	
	public void save(Clerk entity){
		Assert.notNull(entity);
		
		this.clerkRepository.save(entity);
	}

	public void delete(Clerk entity){
		Assert.isTrue(entity.getId()!=0);
		Assert.isTrue(this.clerkRepository.exists(entity.getId()));
		
		entity.setDeleted(true);
		
		this.save(entity);
	}

	public Clerk findOne(int id){
		Assert.isTrue(id != 0);
		
		Clerk res;
		
		res = this.clerkRepository.findOne(id);
		
		return res;
	}

	public Collection<Clerk> findAll(){
		Collection<Clerk> res;
		
		res = clerkRepository.findAll();
		
		return res;
	}
	
	public Clerk findByUserName(String userName){
		Clerk res;
		
		res = clerkRepository.findByUserName(userName);
		
		return res;
	}

	// Other business methods -------------------------------------------------

	public Clerk findByPrincipal() {
	 	Clerk clerk;
	 	UserAccount userAccount;
	 	
	 	userAccount = LoginService.getPrincipal();
	 	clerk = clerkRepository.findByPrincipal(userAccount.getId());
	 	
	 	Assert.notNull(clerk);
	 	
	 	return clerk;
	}
	
	public UserAccount createUserAccount() {
		UserAccount result;
		Collection<Authority> authorities;
		Authority authority;
		
		authority = new Authority();
		authority.setAuthority(Authority.CLERK);
		
		authorities = new ArrayList<Authority>();
		authorities.add(authority);
		
		result = new UserAccount();
		result.setAuthorities(authorities);
		
		return result;
	}
	
	public Collection<Clerk> getClerksWithMoreOrders(){
		Collection<Clerk> clerks;
		
		clerks = clerkRepository.getClerksWithMoreOrders();
		
		return clerks;
	}
	
	public Collection<Clerk> getClerksWithLessOrders(){
		Collection<Clerk> clerks;
		
		clerks = clerkRepository.getClerksWithLessOrders();
		
		return clerks;
	}
	
	public Collection<Clerk> getClerkWithOrdersSentLastMonth(){
		Collection<Clerk> clerks;
		
		clerks = clerkRepository.getClerkWithOrdersSentLastMonth();
		
		return clerks;
		
	}
	
	// Ancillary methods ------------------------------------------------------

	public Clerk reconstruct(ClerkForm clerkForm) {
		Assert.notNull(clerkForm);
		Clerk clerk;
		Calendar calendar = Calendar.getInstance();
		
		clerk = create();

		calendar.setTimeInMillis(System.currentTimeMillis());
		
		clerk.getUserAccount().setUsername(clerkForm.getUsername());
		clerk.getUserAccount().setPassword(clerkForm.getPassword());
		
		clerk.setName(clerkForm.getName());
		clerk.setSurname(clerkForm.getSurname());
		clerk.setEmail(clerkForm.getEmail());
		clerk.setPhone(clerkForm.getPhone());
		
		return clerk;
	}
	
	
	public ClerkForm desreconstruct(Clerk clerk) {
		Assert.notNull(clerk);
		ClerkForm clerkForm;
		
		clerkForm = new ClerkForm();
		
		clerkForm.setUsername(clerk.getUserAccount().getUsername());
		clerkForm.setPassword(clerk.getUserAccount().getPassword());
		clerkForm.setRepeatedPass(clerk.getUserAccount().getPassword());
		clerkForm.setName(clerk.getName());
		clerkForm.setSurname(clerk.getSurname());
		clerkForm.setEmail(clerk.getEmail());
		clerkForm.setPhone(clerk.getPhone());
		
		return clerkForm;
	}
	
	
	public boolean rPassword(ClerkForm clerkForm) {
		boolean result;
		String pass;
		String rpass;
		
		pass = clerkForm.getPassword();
		rpass = clerkForm.getRepeatedPass();
		result = pass.equals(rpass);
		
		return result;
	}
	
	public void deactivate(Clerk clerk){
		Assert.notNull(clerk);
		
		administratorService.findByPrincipal();
		
		clerk.setDeleted(false);;
		
		this.save(clerk);
	}
	
	public void activate(Clerk clerk){
		Assert.notNull(clerk);
		
		administratorService.findByPrincipal();
		
		clerk.setDeleted(true);
		
		this.save(clerk);
	}
	
}
