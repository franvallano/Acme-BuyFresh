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
public class RecipeServiceTest extends AbstractTest{
	
	// Service under test ----------------------------------------
	
	@Autowired
	private RecipeService recipeService;
	
	@Autowired
	private AllergenService allergenService;
	
	// Test -----------------------------------------------------
	
	/**
	 * Requirement:
	 * Un usuario logueado como Administrador podrá:
	 * -	Gestionar las recetas de un determinado menú. Al crear una receta se deben
	 * 		añadir los ingredientes a usar.
	 * 
	 * Test: Crear receta. 
	 */
	@Test
	public void createRecipe1(){
		
		authenticate("admin");
		
		Collection<Recipe> before = recipeService.findAll();
				
		Allergen a1 = allergenService.findOne(10);
		
		Recipe recipe = recipeService.create();
		recipe.setName("namePrueba");
		recipe.setElaboration("elaborationPrueba");
		recipe.setTime(33);
		
		recipe.getAllergens().add(a1);
		
		recipeService.save(recipe);
		
		Collection<Recipe> after = recipeService.findAll();
		
		Assert.isTrue(before.size()+1 == after.size());
		
		unauthenticate();		
	}
	
	/**
	 * Requirement:
	 * Un usuario logueado como Administrador podrá:
	 * -	Gestionar las recetas de un determinado menú. Al crear una receta se deben
	 * 		añadir los ingredientes a usar.
	 * 
	 * Test: Crear receta sin loguearse. 
	 */
	@Test(expected = IllegalArgumentException.class)
	public void createRecipe2(){
				
		Collection<Recipe> before = recipeService.findAll();
				
		Allergen a1 = allergenService.findOne(10);
		
		Recipe recipe = recipeService.create();
		recipe.setName("namePrueba");
		recipe.setElaboration("elaborationPrueba");
		recipe.setTime(33);
		
		recipe.getAllergens().add(a1);
		
		recipeService.save(recipe);
		
		Collection<Recipe> after = recipeService.findAll();
			
	}
	
	/**
	 * Requirement:
	 * Un usuario logueado como Administrador podrá:
	 * -	Gestionar las recetas de un determinado menú. Al crear una receta se deben
	 * 		añadir los ingredientes a usar.
	 * 
	 * Test: Crear receta como user. 
	 */
	@Test(expected = IllegalArgumentException.class)
	public void createRecipe3(){
		
		authenticate("user1");
		
		Collection<Recipe> before = recipeService.findAll();
				
		Allergen a1 = allergenService.findOne(10);
		
		Recipe recipe = recipeService.create();
		recipe.setName("namePrueba");
		recipe.setElaboration("elaborationPrueba");
		recipe.setTime(33);
		
		recipe.getAllergens().add(a1);
		
		recipeService.save(recipe);
		
		Collection<Recipe> after = recipeService.findAll();
		
		unauthenticate();			
	}
	
	/**
	 * Requirement:
	 * Un usuario logueado como Administrador podrá:
	 * -	Gestionar las recetas de un determinado menú. Al crear una receta se deben
	 * 		añadir los ingredientes a usar.
	 * 
	 * Test: Crear receta como clerk. 
	 */
	@Test(expected = IllegalArgumentException.class)
	public void createRecipe4(){
		
		authenticate("clerk1");
		
		Collection<Recipe> before = recipeService.findAll();
				
		Allergen a1 = allergenService.findOne(10);
		
		Recipe recipe = recipeService.create();
		recipe.setName("namePrueba");
		recipe.setElaboration("elaborationPrueba");
		recipe.setTime(33);
		
		recipe.getAllergens().add(a1);
		
		recipeService.save(recipe);
		
		Collection<Recipe> after = recipeService.findAll();
		
		unauthenticate();			
	}
	
	/**
	 * Requirement:
	 * Un usuario logueado como Administrador podrá:
	 * -	Gestionar las recetas de un determinado menú. Al crear una receta se deben
	 * 		añadir los ingredientes a usar.
	 * 
	 * Test: Crear receta nula. 
	 */
	@Test(expected = IllegalArgumentException.class)
	public void createRecipe5(){
		
		authenticate("admin");
		
		recipeService.save(null);
		
		unauthenticate();			
	}
	
	/**
	 * Requirement:
	 * Un usuario logueado como Administrador podrá:
	 * -	Gestionar las recetas de un determinado menú. Al crear una receta se deben
	 * 		añadir los ingredientes a usar.
	 * 
	 * Test: Editar receta. 
	 */
	@Test
	public void editRecipe1(){
		
		authenticate("admin");
		
		Recipe recipe = recipeService.findOne(9);
		
		recipe.setTime(100);
		
		recipeService.save(recipe);
		
		Assert.isTrue(recipe.getTime() == 100);
		
		unauthenticate();		
	}
	
	
}
