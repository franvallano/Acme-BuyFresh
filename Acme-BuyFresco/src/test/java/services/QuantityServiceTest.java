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

import domain.Ingredient;
import domain.Allergen;
import domain.Quantity;
import domain.Recipe;
import domain.User;
import domain.Administrator;

import utilities.AbstractTest;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
		"classpath:spring/datasource.xml",
		"classpath:spring/config/packages.xml"})
@Transactional
@TransactionConfiguration(defaultRollback = true)
public class QuantityServiceTest extends AbstractTest{
	
	// Service under test ----------------------------------------
		
	@Autowired
	private RecipeService recipeService;
	
	@Autowired
	private IngredientService ingredientService;
	
	@Autowired
	private QuantityService quantityService;
		
	
	// Test -----------------------------------------------------
	
	/**
	 * Requirement:
	 * Un usuario logueado como Administrador podrá:
	 * -	Gestionar las recetas de un determinado menú. Al crear una receta se deben
	 * 		añadir los ingredientes a usar.
	 * 
	 * Test: Crear cantidad para receta. 
	 */
	@Test
	public void createIngredient1(){
		
		authenticate("admin");
		
		Collection<Quantity> before = quantityService.findAll();
				
		Recipe recipe = recipeService.findOne(9);
		Ingredient ingredient = ingredientService.findOne(18);
		
		Quantity quantity = quantityService.create(ingredient,recipe);
		
		quantity.setValue(300);	
		
		quantityService.save(quantity);
		
		Collection<Quantity> after = quantityService.findAll();
		
		Assert.isTrue(before.size()+1 == after.size());
		
		unauthenticate();		
	}
	
	/**
	 * Requirement:
	 * Un usuario logueado como Administrador podrá:
	 * -	Gestionar las recetas de un determinado menú. Al crear una receta se deben
	 * 		añadir los ingredientes a usar.
	 * 
	 * Test: Crear cantidad para receta como user. 
	 */
	@Test(expected = IllegalArgumentException.class)
	public void createIngredient2(){
		
		authenticate("user1");
						
		Recipe recipe = recipeService.findOne(9);
		Ingredient ingredient = ingredientService.findOne(18);
		
		Quantity quantity = quantityService.create(ingredient,recipe);
		
		quantity.setValue(300);	
		
		quantityService.save(quantity);
		
		unauthenticate();		
	}
	
	/**
	 * Requirement:
	 * Un usuario logueado como Administrador podrá:
	 * -	Gestionar las recetas de un determinado menú. Al crear una receta se deben
	 * 		añadir los ingredientes a usar.
	 * 
	 * Test: Crear cantidad para receta como clerk. 
	 */
	@Test(expected = IllegalArgumentException.class)
	public void createIngredient3(){
		
		authenticate("clerk1");
						
		Recipe recipe = recipeService.findOne(9);
		Ingredient ingredient = ingredientService.findOne(18);
		
		Quantity quantity = quantityService.create(ingredient,recipe);
		
		quantity.setValue(300);	
		
		quantityService.save(quantity);
		
		unauthenticate();		
	}
	
	/**
	 * Requirement:
	 * Un usuario logueado como Administrador podrá:
	 * -	Gestionar las recetas de un determinado menú. Al crear una receta se deben
	 * 		añadir los ingredientes a usar.
	 * 
	 * Test: Crear cantidad para receta a null. 
	 */
	@Test(expected = IllegalArgumentException.class)
	public void createIngredient4(){
		
		authenticate("admin");
		
		quantityService.save(null);
		
		unauthenticate();		
	}
	
	
	
}
