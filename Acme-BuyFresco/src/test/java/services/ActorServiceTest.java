package services;

import javax.transaction.Transactional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.util.Assert;

import utilities.AbstractTest;
import domain.Actor;
import domain.Clerk;
import domain.User;
import forms.ClerkForm;
import forms.PasswordForm;
import forms.ProfileForm;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
		"classpath:spring/datasource.xml",
		"classpath:spring/config/packages.xml"})
@Transactional
@TransactionConfiguration(defaultRollback = true)
public class ActorServiceTest extends AbstractTest {

	
	@Autowired
	private AdministratorService administratorService;
	
	@Autowired
	private ActorService actorService;
	
	@Autowired
	private ClerkService clerkService;
	
	@Autowired
	private UserService userService;
	
	/* 								TEST POSITIVO 						*/
	
	
	// Test cambiar clave Actor
	// Autenticado como Administrator
	@Test
	public void testChangePassword() {
		authenticate("admin");
		
		Actor administrator;
		PasswordForm passwordForm;
		Md5PasswordEncoder encoder;
		String pass = "12345678";
		
		encoder = new Md5PasswordEncoder();
		passwordForm = new PasswordForm();
		
		passwordForm.setActualPassword("admin");
		passwordForm.setNewPassword(pass);
		passwordForm.setRepeatNewPassword(pass);
		
		administrator = actorService.reconstructPassword(passwordForm);
		actorService.saveProfile(administrator);
		
		administrator = administratorService.findByPrincipal();
		
		Assert.isTrue(administrator.getUserAccount().getPassword().equals(encoder.encodePassword(pass, null)));
		
		unauthenticate();
	}
	
	
	
	// Test editar perfil Actor
	// Autenticado como Administrator
	@Test
	public void testEditProfile() {
		authenticate("admin");
		
		ProfileForm administratorProfileForm;
		Actor administrator;
		
		administrator = administratorService.findByPrincipal();
		
		administratorProfileForm = actorService.desreconstructProfile(administrator);
		
		administratorProfileForm.setName("Jaime");
		administratorProfileForm.setSurname("Guerrero");
		
		administrator = actorService.reconstructProfile(administratorProfileForm);
		actorService.saveProfile(administrator);
		
		administrator = administratorService.findByPrincipal();
		
		Assert.isTrue(administrator.getName().equals("Jaime") && administrator.getSurname().equals("Guerrero"));
		
		unauthenticate();
	}
	
	
	// Test para desactivar y activar la cuenta de un Clerk
	// Autenticado como Administrator
	@Test
	public void testActivateDeactivateClerk() {
		authenticate("admin");
		
		Clerk clerk;
		
		clerk = clerkService.findOne(29);
		
		// Desactivar
		clerkService.deactivate(clerk);
		
		clerk = clerkService.findOne(29);
		
		Assert.isTrue(!clerk.getDeleted());
		
		// Activar
		clerkService.activate(clerk);
		
		clerk = clerkService.findOne(29);
		
		Assert.isTrue(clerk.getDeleted());
		
		unauthenticate();
	}
	
	
	// Test registrar Clerk
	// Autenticado como Administrator
	@Test
	public void testCreateClerk() {
		authenticate("admin");
		
		ClerkForm clerkForm;
		Clerk clerk;
		Integer totalBefore;
		
		clerkForm = new ClerkForm();
		
		totalBefore = clerkService.findAll().size();
		
		clerkForm.setUsername("clerk4");
		clerkForm.setName("clerk4");
		clerkForm.setSurname("surclerk4");
		clerkForm.setEmail("clerk4@mail.com");
		clerkForm.setPassword("12345678");
		clerkForm.setRepeatedPass("12345678");
		clerkForm.setPhone("954121212");
		
		clerk = clerkService.reconstruct(clerkForm);
		clerkService.save(clerk, clerkForm.getRepeatedPass());
		
		Assert.isTrue(totalBefore + 1 == clerkService.findAll().size());
	
		unauthenticate();
	}
	
	
	
	/* 								TEST NEGATIVO 						*/
	
	
	// Test para desactivar un Clerk
	// Error: Clerk ya desactivado
	@Test(expected = IllegalArgumentException.class)
	public void testActivateClerkAlreadyDeactivate() {
		authenticate("admin");
			
		// Desactivar
		clerkService.deactivate(null);
			
		unauthenticate();
	}
	
	// Test para desactivar y activar un Customer
	// Error: No autenticado
	@Test(expected = IllegalArgumentException.class)
	public void testActivateDeactivateCustomerNoAuth() {
		unauthenticate();
			
		Clerk clerk;
			
		clerk = clerkService.findOne(28);
			
		Assert.isTrue(clerk.getDeleted());
			
		// Desactivar
		clerkService.deactivate(clerk);
			
		clerk = clerkService.findOne(28);
			
		Assert.isTrue(!clerk.getDeleted());
			
		// Activar
		clerkService.activate(clerk);
		
		clerk = clerkService.findOne(28);
			
		Assert.isTrue(clerk.getDeleted());
			
		
	}
	
	// Test editar perfil Administrator
	// Error: No autenticado como Administrator
	@Test(expected = IllegalArgumentException.class)
	public void testEditProfileNoAuth() {
		
		unauthenticate();
		
		ProfileForm administratorProfileForm;
		Actor administrator;
		
		administrator = administratorService.findByPrincipal();
		
		administratorProfileForm = actorService.desreconstructProfile(administrator);
		
		administratorProfileForm.setName("Jaime");
		administratorProfileForm.setSurname("Guerrero");
		
		administrator = actorService.reconstructProfile(administratorProfileForm);
		actorService.saveProfile(administrator);
		
		administrator = administratorService.findByPrincipal();
		
	}
	
	
	// Test editar perfil Administrator
	// Error: Nombre vacío
	@Test(expected = IllegalArgumentException.class)
	public void testEditProfileNoName() {
		authenticate("admin1");
		
		ProfileForm administratorProfileForm;
		Actor administrator;
		
		administrator = administratorService.findByPrincipal();
		
		administratorProfileForm = actorService.desreconstructProfile(administrator);
		
		administratorProfileForm.setName("");
		administratorProfileForm.setSurname("Guerrero");
		
		administrator = actorService.reconstructProfile(administratorProfileForm);
		actorService.saveProfile(administrator);
		
		administrator = administratorService.findByPrincipal();
		
		unauthenticate();
	}

	
	// Test cambiar clave User
	// Error: Clave demasiado corta
	@Test(expected = IllegalArgumentException.class)
	public void testChangePasswordUserShortPassword() {
		authenticate("user1");
					
		User user;
		PasswordForm passwordForm;
		Md5PasswordEncoder encoder;
		String pass = "";
						
		encoder = new Md5PasswordEncoder();
		passwordForm = new PasswordForm();
				
		passwordForm.setActualPassword("user1");
		passwordForm.setNewPassword(pass);
		passwordForm.setRepeatNewPassword(pass);
						
		user = userService.reconstructPassword(passwordForm);
		userService.saveProfile(user);
					
		user = userService.findByPrincipal();
				
		Assert.isTrue(user.getUserAccount().getPassword().equals(encoder.encodePassword(pass, null)));
			
		unauthenticate();
	}
	
	
	// Test cambiar clave User
	// Error: Clave actual no coincide
	@Test(expected = IllegalArgumentException.class)
	public void testChangePasswordUserDiffActualPassword() {
		authenticate("user1");
					
		User user;
		PasswordForm passwordForm;
		String pass = "";
						
		passwordForm = new PasswordForm();
				
		passwordForm.setActualPassword("claveErronea");
		passwordForm.setNewPassword(pass);
		passwordForm.setRepeatNewPassword(pass);
						
		user = userService.reconstructPassword(passwordForm);
		userService.saveProfile(user);
					
		user = userService.findByPrincipal();
			
		unauthenticate();
	}

	
	// Test registrar un clerk
	// Error: No email
	@Test(expected = IllegalArgumentException.class)
	public void testCreateClerkNoEmail() {
		authenticate("admin");
		
		ClerkForm clerkForm;
		Clerk clerk;
		
		clerkForm = new ClerkForm();
		
		clerkForm.setUsername("clerk4");
		clerkForm.setName("clerk4");
		clerkForm.setSurname("surclerk4");
		clerkForm.setPassword("12345678");
		clerkForm.setRepeatedPass("12345678");
		clerkForm.setPhone("954121212");
		
		clerk = clerkService.reconstruct(clerkForm);
		clerkService.save(clerk, clerkForm.getRepeatedPass());
		
		unauthenticate();
	}
	
	
	// Test registrar un clerk
	// Error: No autenticado
	@Test(expected = IllegalArgumentException.class)
	public void testCreateClerkNoAuth() {
		unauthenticate();
		
		ClerkForm clerkForm;
		Clerk clerk;
		
		clerkForm = new ClerkForm();
		
		clerkForm.setUsername("clerk4");
		clerkForm.setName("clerk4");
		clerkForm.setSurname("surclerk4");
		clerkForm.setEmail("clerk4@mail.com");
		clerkForm.setPassword("12345678");
		clerkForm.setRepeatedPass("12345678");
		clerkForm.setPhone("954121212");
		
		clerk = clerkService.reconstruct(clerkForm);
		clerkService.save(clerk, clerkForm.getRepeatedPass());
	}
	
}
