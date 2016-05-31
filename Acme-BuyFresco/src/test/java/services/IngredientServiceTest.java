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
public class IngredientServiceTest extends AbstractTest{
	
	// Service under test ----------------------------------------
		
	@Autowired
	private AdministratorService administratorService;
	
	@Autowired
	private IngredientService ingredientService;
	
	@Autowired
	private AllergenService allergenService;
		
	
	// Test -----------------------------------------------------
	
	/**
	 * Requirement:
	 * Un usuario logueado como Administrador podrá:
	 * -	Gestionar las recetas de un determinado menú. Al crear una receta se deben
	 * 		añadir los ingredientes a usar.
	 * 
	 * Test: Crear ingrediente. 
	 */
	@Test
	public void createIngredient1(){
		
		authenticate("admin");
		
		Collection<Ingredient> before = ingredientService.findAll();
				
		Allergen a1 = allergenService.findOne(10);
		
		Ingredient ingredient = ingredientService.create();
		
		ingredient.setName("namePrueba");
		ingredient.setDescription("descriptionPrueba");
		ingredient.setPicture("http://maby.snarvaez.com.ar/salud/files/2012/08/cebolla.jpg");
		ingredient.setMetricUnit("kilos");
		ingredient.getAllergens().add(a1);		
		
		ingredientService.save(ingredient);
		
		Collection<Ingredient> after = ingredientService.findAll();
		
		Assert.isTrue(before.size()+1 == after.size());
		
		unauthenticate();		
	}
	
	/**
	 * Requirement:
	 * Un usuario logueado como Administrador podrá:
	 * -	Gestionar las recetas de un determinado menú. Al crear una receta se deben
	 * 		añadir los ingredientes a usar.
	 * 
	 * Test: Crear ingrediente sin loguearse. 
	 */
	@Test(expected = IllegalArgumentException.class)
	public void createIngredient2(){
				
		Allergen a1 = allergenService.findOne(10);
		
		Ingredient ingredient = ingredientService.create();
		
		ingredient.setName("namePrueba");
		ingredient.setDescription("descriptionPrueba");
		ingredient.setPicture("http://maby.snarvaez.com.ar/salud/files/2012/08/cebolla.jpg");
		ingredient.setMetricUnit("kilos");
		ingredient.getAllergens().add(a1);		
		
		ingredientService.save(ingredient);
			
	}
	
	/**
	 * Requirement:
	 * Un usuario logueado como Administrador podrá:
	 * -	Gestionar las recetas de un determinado menú. Al crear una receta se deben
	 * 		añadir los ingredientes a usar.
	 * 
	 * Test: Crear ingrediente como user. 
	 */
	@Test(expected = IllegalArgumentException.class)
	public void createIngredient3(){
		
		authenticate("user1");
		
		Allergen a1 = allergenService.findOne(10);
		
		Ingredient ingredient = ingredientService.create();
		
		ingredient.setName("namePrueba");
		ingredient.setDescription("descriptionPrueba");
		ingredient.setPicture("http://maby.snarvaez.com.ar/salud/files/2012/08/cebolla.jpg");
		ingredient.setMetricUnit("kilos");
		ingredient.getAllergens().add(a1);		
		
		ingredientService.save(ingredient);
		
		unauthenticate();			
	}
	
	/**
	 * Requirement:
	 * Un usuario logueado como Administrador podrá:
	 * -	Gestionar las recetas de un determinado menú. Al crear una receta se deben
	 * 		añadir los ingredientes a usar.
	 * 
	 * Test: Crear ingrediente como clerk. 
	 */
	@Test(expected = IllegalArgumentException.class)
	public void createIngredient4(){
		
		authenticate("clerk1");
		
		Allergen a1 = allergenService.findOne(10);
		
		Ingredient ingredient = ingredientService.create();
		
		ingredient.setName("namePrueba");
		ingredient.setDescription("descriptionPrueba");
		ingredient.setPicture("http://maby.snarvaez.com.ar/salud/files/2012/08/cebolla.jpg");
		ingredient.setMetricUnit("kilos");
		ingredient.getAllergens().add(a1);		
		
		ingredientService.save(ingredient);
		
		unauthenticate();			
	}
	
	/**
	 * Requirement:
	 * Un usuario logueado como Administrador podrá:
	 * -	Gestionar las recetas de un determinado menú. Al crear una receta se deben
	 * 		añadir los ingredientes a usar.
	 * 
	 * Test: Crear ingrediente nulo. 
	 */
	@Test(expected = IllegalArgumentException.class)
	public void createIngredient5(){
		
		authenticate("admin");
		
		ingredientService.save(null);
		
		unauthenticate();			
	}
	
	/**
	 * Requirement:
	 * Un usuario logueado como Administrador podrá:
	 * -	Gestionar las recetas de un determinado menú. Al crear una receta se deben
	 * 		añadir los ingredientes a usar.
	 * 
	 * Test: Eliminar ingrediente. 
	 */
	@Test
	public void deleteIngredient1(){
		
		authenticate("admin");
		
		Ingredient ingredient = ingredientService.findOne(16);
		
		ingredientService.delete(ingredient);
		
		Assert.isTrue(ingredient.getDeleted());
		
		unauthenticate();		
	}
	
	/**
	 * Requirement:
	 * Un usuario logueado como Administrador podrá:
	 * -	Gestionar las recetas de un determinado menú. Al crear una receta se deben
	 * 		añadir los ingredientes a usar.
	 * 
	 * Test: Eliminar ingrediente como user. 
	 */
	@Test(expected = IllegalArgumentException.class)
	public void deleteIngredient2(){
		
		authenticate("user1");
		
		Ingredient ingredient = ingredientService.findOne(16);
		
		ingredientService.delete(ingredient);
		
		Assert.isTrue(!ingredient.getDeleted());
		
		unauthenticate();		
	}
	
	/**
	 * Requirement:
	 * Un usuario logueado como Administrador podrá:
	 * -	Gestionar las recetas de un determinado menú. Al crear una receta se deben
	 * 		añadir los ingredientes a usar.
	 * 
	 * Test: Eliminar ingrediente como clerk. 
	 */
	@Test(expected = IllegalArgumentException.class)
	public void deleteIngredient3(){
		
		authenticate("clerk1");
		
		Ingredient ingredient = ingredientService.findOne(16);
		
		ingredientService.delete(ingredient);
		
		Assert.isTrue(!ingredient.getDeleted());
		
		unauthenticate();		
	}
	
	/**
	 * Requirement:
	 * Un usuario logueado como Administrador podrá:
	 * -	Gestionar las recetas de un determinado menú. Al crear una receta se deben
	 * 		añadir los ingredientes a usar.
	 * 
	 * Test: Eliminar ingrediente ya borrado. 
	 */
	@Test(expected = IllegalArgumentException.class)
	public void deleteIngredient4(){
		
		authenticate("admin");
		
		Ingredient ingredient = ingredientService.findOne(16);
		
		ingredientService.delete(ingredient);
		
		ingredientService.delete(ingredient);
		
		unauthenticate();		
	}
	
	
}
