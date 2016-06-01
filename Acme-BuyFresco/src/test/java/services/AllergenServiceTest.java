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
import domain.Allergen;
import domain.Ingredient;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
		"classpath:spring/datasource.xml",
		"classpath:spring/config/packages.xml"})
@Transactional
@TransactionConfiguration(defaultRollback = true)
public class AllergenServiceTest extends AbstractTest{

	@Autowired
	private AllergenService allergenService;
	
	@Autowired
	private IngredientService ingredientService;
	
	
	/* 								TEST POSITIVO 						*/
	
	// Test crear un Allergen 
	@Test
	public void testCreateAllergen() {
		authenticate("admin");
		
		Collection<Ingredient> subs;
		Collection<Ingredient> ing;
		Ingredient a = ingredientService.findOne(14);
		Ingredient b = ingredientService.findOne(15);
		
		Allergen allergen = allergenService.create();
		int allergens = allergenService.findAll().size();
		
		allergen.setName("allergenPrueba");
		
		ing = allergen.getAllergenIngredients();
		ing.add(a);
		allergen.setAllergenIngredients(ing);
		
		subs = allergen.getAllergenIngredients();
		subs.add(b);
		allergen.setAllergenIngredients(subs);
		
		
		allergenService.save(allergen);
		
		Assert.isTrue(allergens + 1  == allergenService.findAll().size());
		
		unauthenticate();
	}
	
	
	// Test editar un Allergen 
	@Test
	public void testEditAllergen() {
		authenticate("admin");
		
		Allergen allergen = allergenService.findOne(11);
		
		String name = allergen.getName();
		
		allergen.setName("allergenPrueba");		
		
		allergenService.save(allergen);
		
		Assert.isTrue(name != allergen.getName());
		
		unauthenticate();
	}
	
	
	
	/* 								TEST NEGATIVO 						*/
	
	
	// Test crear un Allergen 
	// Error: Nombre vacío
	@Test(expected = IllegalArgumentException.class)
	public void testCreateAllergenNoName() {
		authenticate("admin");
		
		Collection<Ingredient> subs;
		Collection<Ingredient> ing;
		Ingredient a = ingredientService.findOne(14);
		Ingredient b = ingredientService.findOne(15);
		
		Allergen allergen = allergenService.create();
		
		
		ing = allergen.getAllergenIngredients();
		ing.add(a);
		allergen.setAllergenIngredients(ing);
		
		subs = allergen.getAllergenIngredients();
		subs.add(b);
		allergen.setAllergenIngredients(subs);

		allergenService.save(allergen);
		
		unauthenticate();
	}
	
	// Test crear un Allergen 
	// Error: Logueado como Clerk
	@Test(expected = IllegalArgumentException.class)
	public void testCreateAllergenClerk() {
		authenticate("clerk");
		
		Collection<Ingredient> subs;
		Collection<Ingredient> ing;
		Ingredient a = ingredientService.findOne(14);
		Ingredient b = ingredientService.findOne(15);
		
		Allergen allergen = allergenService.create();
		
		allergen.setName("allergenPrueba");
		
		ing = allergen.getAllergenIngredients();
		ing.add(a);
		allergen.setAllergenIngredients(ing);
		
		subs = allergen.getAllergenIngredients();
		subs.add(b);
		allergen.setAllergenIngredients(subs);

		allergenService.save(allergen);
		
		unauthenticate();
	}
	
	
	// Test editar un Allergen 
	// Error: Añadido ingrediente nulo
	@Test(expected = NullPointerException.class)
	public void testEditAllergenNullIngredient() {
		authenticate("admin");

		Collection<Ingredient> ing;
		Allergen allergen = allergenService.findOne(11);
		
		allergen.setName("allergenPrueba");
		
		ing = allergen.getAllergenIngredients();
		ing.add(null);
		allergen.setAllergenIngredients(ing);

		allergenService.save(allergen);
		
		unauthenticate();
	}
	
	
	// Test editar un Allergen 
	// Error: No autenticado
	@Test(expected = IllegalArgumentException.class)
	public void testEditAllergenNullName() {
		authenticate("admin");

		Allergen allergen = allergenService.findOne(11);
		
		allergen.setName(null);
		
		allergenService.save(allergen);
		
		unauthenticate();
	}
	
}
