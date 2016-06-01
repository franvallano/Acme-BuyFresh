package services;

import java.util.Collection;

import javax.transaction.Transactional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.util.Assert;

import utilities.AbstractTest;
import datatypes.CreditCard;
import domain.Allergen;
import domain.User;
import forms.UserForm;
import forms.UserProfileForm;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
		"classpath:spring/datasource.xml",
		"classpath:spring/config/packages.xml"})
@Transactional
@TransactionConfiguration(defaultRollback = true)
public class UserServiceTest extends AbstractTest{

	@Autowired
	private UserService userService;
	
	@Autowired
	private AllergenService allergenService;
	
	/* 								TEST POSITIVO 						*/
	
	// Test registrarse como usuario
	@Test
	public void testCreateUser() {
		UserForm userForm;
		User user;
		Integer totalBefore;
		CreditCard creditCard;

		userForm = new UserForm();
		creditCard = new CreditCard();
		
		totalBefore = userService.findAll().size();
		
		userForm.setUsername("user3");
		userForm.setName("user3");
		userForm.setSurname("suruser3");
		userForm.setEmail("user3@mail.com");
		userForm.setPassword("12345678");
		userForm.setRepeatedPass("12345678");
		
		userForm.setPhone("954121212");
		userForm.setAddress("Calle de la Juana");
		
		userForm.setAgree(true);
		
		creditCard.setBrandName("Visa");
		creditCard.setCvv(333);
		creditCard.setExpirationMonth(2);
		creditCard.setExpirationYear(2017);
		creditCard.setHolderName("user3");
		creditCard.setNumber("4303017070539663");
		
		userForm.setCreditCard(creditCard);
		
		user = userService.reconstruct(userForm);
		userService.save(user, userForm.getRepeatedPass(), userForm.isAgree());
		
		Assert.isTrue(totalBefore + 1 == userService.findAll().size());
	}
	
	
	
	// Test editar perfil y añadir alergenos para un usuario
	@Test
	public void testProfileAddAllergens() {
		authenticate("user1");
		
		UserProfileForm userForm;
		User user = userService.findByPrincipal();
		Integer totalBefore;
		Allergen allergen;

		allergen = allergenService.findOne(11);
		userForm = userService.desreconstructProfile(user);
		
		totalBefore = user.getAllergens().size();
		
		Collection<Allergen> a = userForm.getAllergens();
		
		a.add(allergen);
		
		userForm.setAllergens(a);
		
		user = userService.reconstructProfile(userForm);
		userService.saveProfile(user);;
		
		Assert.isTrue(totalBefore + 1 == user.getAllergens().size());
		
		unauthenticate();
	}
	
	
	/* 								TEST NEGATIVO 						*/
	
	// Test registrarse como user
	// Error: No introducido el userName
	@Test(expected = IllegalArgumentException.class)
	public void testCreateUserNoUserName() {
		UserForm userForm;
		User user;
		CreditCard creditCard;

		userForm = new UserForm();
		creditCard = new CreditCard();
		
		
		userForm.setName("user3");
		userForm.setSurname("suruser3");
		userForm.setEmail("user3@mail.com");
		userForm.setPassword("12345678");
		userForm.setRepeatedPass("12345678");
		
		userForm.setPhone("954121212");
		userForm.setAddress("Calle de la Juana");
		
		userForm.setAgree(true);
		
		creditCard.setBrandName("Visa");
		creditCard.setCvv(333);
		creditCard.setExpirationMonth(2);
		creditCard.setExpirationYear(2017);
		creditCard.setHolderName("user3");
		creditCard.setNumber("4303017070539663");
		
		userForm.setCreditCard(creditCard);
		
		user = userService.reconstruct(userForm);
		userService.save(user, userForm.getRepeatedPass(), userForm.isAgree());
		
	}
	
	// Test registrarse como user
	// Error: Las contraseñas no son iguales
	@Test(expected = IllegalArgumentException.class)
	public void testCreateUserDifferentPass() {
		UserForm userForm;
		User user;
		CreditCard creditCard;

		userForm = new UserForm();
		creditCard = new CreditCard();
		
		userForm.setUsername("");
		userForm.setName("user3");
		userForm.setSurname("suruser3");
		userForm.setEmail("user3@mail.com");
		userForm.setPassword("12345678");
		userForm.setRepeatedPass("87654321");
		
		userForm.setPhone("954121212");
		userForm.setAddress("Calle de la Juana");
		
		userForm.setAgree(true);
		
		creditCard.setBrandName("Visa");
		creditCard.setCvv(333);
		creditCard.setExpirationMonth(2);
		creditCard.setExpirationYear(2017);
		creditCard.setHolderName("user3");
		creditCard.setNumber("4303017070539663");
		
		userForm.setCreditCard(creditCard);
		
		user = userService.reconstruct(userForm);
		userService.save(user, userForm.getRepeatedPass(), userForm.isAgree());
		
	}
	
	// Test editar perfil y añadir alergenos para un usuario
	// Error: No autenticado
	@Test(expected = IllegalArgumentException.class)
	public void testProfileAddAllergensNoAuth() {

		unauthenticate();
		
		UserProfileForm userForm;
		User user = userService.findByPrincipal();
		Allergen allergen;

		allergen = allergenService.findOne(11);
		userForm = userService.desreconstructProfile(user);
		
		
		Collection<Allergen> a = userForm.getAllergens();
		
		a.add(allergen);
		
		userForm.setAllergens(a);
		
		user = userService.reconstructProfile(userForm);
		userService.saveProfile(user);;
		
		
	}
	
	
	// Test editar perfil y añadir alergenos para un usuario
	// Error: Añadido null como alergeno
	@Test(expected = IllegalArgumentException.class)
	public void testProfileAddAllergensNull() {

		authenticate("user1");

		UserProfileForm userForm;
		User user = userService.findByPrincipal();
		Allergen allergen;

		allergen = allergenService.findOne(11);
		userForm = userService.desreconstructProfile(user);
		
		
		Collection<Allergen> a = userForm.getAllergens();
		
		a.add(allergen);
		
		userForm.setAllergens(null);
		
		user = userService.reconstructProfile(userForm);
		userService.saveProfile(user);;
		
		unauthenticate();
	}
	
	
}
