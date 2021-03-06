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

import repositories.UserRepository;
import security.Authority;
import security.LoginService;
import security.UserAccount;
import domain.Allergen;
import domain.Assessment;
import domain.Subscription;
import domain.User;
import forms.PasswordForm;
import forms.UserForm;
import forms.UserProfileForm;

@Service
@Transactional
public class UserService {

	// Managed repository -----------------------------------------------------
	@Autowired
	private UserRepository userRepository;

	// Ancillary services -----------------------------------------------------

	// Constructor ------------------------------------------------------------
	public UserService(){
		super();
	}

	// Simple CRUD methods ----------------------------------------------------
	public User create(){
		User newbye;
		Collection<Assessment> assess = new ArrayList<Assessment>();
		Collection<Subscription> subs = new ArrayList<Subscription>();
		Collection<Allergen> allergens = new ArrayList<Allergen>();
		
		newbye = new User();
		
		newbye.setAllergens(allergens);
		newbye.setAssessments(assess);
		newbye.setSubcriptions(subs);
		newbye.setDeleted(false);
		newbye.setUserAccount(this.createUserAccount());
		
		return newbye;
	}

	public void save(User user, String rPass, Boolean agree){
		String pass;
		Md5PasswordEncoder encoder;
		encoder = new Md5PasswordEncoder();
		
		Assert.notNull(user);
		Assert.isTrue(user.getUserAccount().getPassword().equals(rPass));
		Assert.isTrue(agree);
		
		pass = user.getUserAccount().getPassword();
		pass = encoder.encodePassword(pass, null);
		user.getUserAccount().setPassword(pass);
		
		userRepository.save(user);
	}
		
	
	public void save(User entity){
		Assert.notNull(entity);
		
		this.userRepository.save(entity);
	}

	public void delete(User entity){
		Assert.isTrue(entity.getId()!=0);
		Assert.isTrue(this.userRepository.exists(entity.getId()));
		
		entity.setDeleted(true);
		
		this.save(entity);
	}

	public User findOne(int id){
		Assert.isTrue(id != 0);
		
		User res;
		
		res = this.userRepository.findOne(id);
		
		return res;
	}

	public Collection<User> findAll(){
		Collection<User> res;
		
		res = userRepository.findAll();
		
		return res;
	}

	// Other business methods -------------------------------------------------

	public User findByPrincipal() {
	 	User user;
	 	UserAccount userAccount;
	 	
	 	userAccount = LoginService.getPrincipal();
	 	user = userRepository.findByPrincipal(userAccount.getId());
	 	
	 	Assert.notNull(user);
	 	
	 	return user;
	}
	
	public Integer getNumberOfUser(){
		Integer number = userRepository.getNumberOfUser();
		
		return number;
	}
	
	public Collection<User> userMoreAssessments(){
		Collection<User> res;
		
		res = userRepository.UserMoreAssessments();
		
		return res;
	}
	
	public Collection<User> userMoreSubscriptions(){
		Collection<User> res;
		
		res = userRepository.UserMoreSubscriptions();
		
		return res;
	}
	
	// Ancillary methods ------------------------------------------------------

	public UserAccount createUserAccount() {
		UserAccount result;
		Collection<Authority> authorities;
		Authority authority;
		
		authority = new Authority();
		authority.setAuthority(Authority.USER);
		
		authorities = new ArrayList<Authority>();
		authorities.add(authority);
		
		result = new UserAccount();
		result.setAuthorities(authorities);
		
		return result;
	}
	
	public void saveProfile(User actor){
		Assert.notNull(actor);
	
		userRepository.save(actor);
	}
	
	
	//Metodo que recibe un objeto formulario y reconstruye un objeto de dominio
	public User reconstruct(UserForm userForm) {
		Assert.notNull(userForm);
		Assert.notNull(userForm.getUsername());
		User user;
		Calendar calendar = Calendar.getInstance();
		
		user = create();

		calendar.setTimeInMillis(System.currentTimeMillis());
		
		user.getUserAccount().setUsername(userForm.getUsername());
		user.getUserAccount().setPassword(userForm.getPassword());
		
		user.setAllergens(userForm.getAllergens());
		
		user.setName(userForm.getName());
		user.setSurname(userForm.getSurname());
		user.setEmail(userForm.getEmail());
		if(userForm.getCreditCard() != null) {
			user.setCreditCard(userForm.getCreditCard());
			
			Assert.isTrue(userForm.getCreditCard().getExpirationYear() >= calendar.get(Calendar.YEAR));
			
			if(userForm.getCreditCard().getExpirationYear() == calendar.get(Calendar.YEAR))
				Assert.isTrue(userForm.getCreditCard().getExpirationMonth() >= (calendar.get(Calendar.MONTH) + 1));
		}
		user.setPhone(userForm.getPhone());
		user.setAddress(userForm.getAddress());
		
		return user;
	}
	
	
	public User reconstructProfile(UserProfileForm userProfileForm) {
		Assert.notNull(userProfileForm);
		Assert.notNull(userProfileForm.getAllergens());
		User user;
		Calendar calendar = Calendar.getInstance();
		
		user = findByPrincipal();
		
		Assert.isTrue(user.getUserAccount().getUsername().equals(userProfileForm.getUsername()));
		
		calendar.setTimeInMillis(System.currentTimeMillis());
		
		user.setName(userProfileForm.getName());
		user.setSurname(userProfileForm.getSurname());
		user.setEmail(userProfileForm.getEmail());
		
		user.setAllergens(userProfileForm.getAllergens());
		
		if(userProfileForm.getCreditCard() != null) {
			Assert.isTrue(userProfileForm.getCreditCard().getExpirationYear() >= calendar.get(Calendar.YEAR));
			
			if(userProfileForm.getCreditCard().getExpirationYear() == calendar.get(Calendar.YEAR))
				Assert.isTrue(userProfileForm.getCreditCard().getExpirationMonth() >= (calendar.get(Calendar.MONTH) + 1));
			
			user.setCreditCard(userProfileForm.getCreditCard());
		} else
			user.setCreditCard(null);
		user.setPhone(userProfileForm.getPhone());
		user.setAddress(userProfileForm.getAddress());
		
		return user;
	}
	
	public User reconstructPassword(PasswordForm passwordForm) {
		Assert.notNull(passwordForm);
		Assert.isTrue(passwordForm.getNewPassword().equals(passwordForm.getRepeatNewPassword()));
		Assert.isTrue(!passwordForm.getNewPassword().equals("") && !passwordForm.getRepeatNewPassword().equals(""));
		Assert.isTrue(passwordForm.getNewPassword().length() >= 5 && passwordForm.getRepeatNewPassword().length() >= 5);
		User user;
		Md5PasswordEncoder encoder;

		user = findByPrincipal();
		
		encoder = new Md5PasswordEncoder();
		
		Assert.isTrue(user.getUserAccount().getPassword().equals(encoder.encodePassword(passwordForm.getActualPassword(), null)));
		
		user.getUserAccount().setPassword(encoder.encodePassword(passwordForm.getNewPassword(), null));
		
		return user;
	}
	
	public UserForm desreconstruct(User user) {
		Assert.notNull(user);
		UserForm userForm;
		
		userForm = new UserForm();
		
		userForm.setUsername(user.getUserAccount().getUsername());
		userForm.setPassword(user.getUserAccount().getPassword());
		userForm.setRepeatedPass(user.getUserAccount().getPassword());
		userForm.setName(user.getName());
		userForm.setSurname(user.getSurname());
		userForm.setEmail(user.getEmail());
		userForm.setPhone(user.getPhone());
		userForm.setAddress(user.getAddress());
		userForm.setAgree(true);
		
		userForm.setAllergens(user.getAllergens());
		
		if(user.getCreditCard() != null) {
			userForm.setCheckBoxCreditCard(true);
			userForm.setCreditCard(user.getCreditCard());
		} else {
			userForm.setCheckBoxCreditCard(false);
		}
		
		return userForm;
	}
	
	
	public UserProfileForm desreconstructProfile(User user) {
		Assert.notNull(user);
		UserProfileForm userProfileForm;
		
		userProfileForm = new UserProfileForm();
		
		userProfileForm.setUsername(user.getUserAccount().getUsername());
		userProfileForm.setName(user.getName());
		userProfileForm.setSurname(user.getSurname());
		userProfileForm.setEmail(user.getEmail());
		userProfileForm.setPhone(user.getPhone());
		userProfileForm.setAddress(user.getAddress());
		
		userProfileForm.setAllergens(user.getAllergens());
		
		if(user.getCreditCard() != null) {
			userProfileForm.setCheckBoxCreditCard(true);
			userProfileForm.setCreditCard(user.getCreditCard());
		} else {
			userProfileForm.setCheckBoxCreditCard(false);
		}
		
		return userProfileForm;
	}
	
	
	public boolean rPassword(UserForm userForm) {
		boolean result;
		String pass;
		String rpass;
		
		pass = userForm.getPassword();
		rpass = userForm.getRepeatedPass();
		result = pass.equals(rpass);
		
		return result;
	}
	
}
