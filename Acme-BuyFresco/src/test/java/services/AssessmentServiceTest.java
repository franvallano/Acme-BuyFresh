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
import domain.Assessment;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
		"classpath:spring/datasource.xml",
		"classpath:spring/config/packages.xml"})
@Transactional
@TransactionConfiguration(defaultRollback = true)
public class AssessmentServiceTest extends AbstractTest{

	@Autowired
	private AssessmentService assessmentService;
	
	@Autowired
	private UserService userService;
	
	/* 								TEST POSITIVO 						*/
	
	// Test crear un Assessment 
	@Test
	public void testCreateAssessmentUser() {
		authenticate("user1");
		
		Assessment assess = assessmentService.create();
		int size_assess_before = assessmentService.findAll().size();
		
		assess.setText("legal text");
		assessmentService.save(assess);
		
		Assert.isTrue(size_assess_before + 1  == assessmentService.findAll().size());
		
		unauthenticate();
	}
	
	
	// Test eliminar un assessment 
	@Test
	public void testDeleteAssessmentAdmin() {
		authenticate("admin");
		
		Assessment assess = assessmentService.findOne(25);
		
		assessmentService.delete(assess);
		
		Assert.isTrue(assess.getDeleted()==true);
		
		unauthenticate();
	}
	
	// Test listar los Assessment sin estar autenticado 
	@Test
	public void testListAssessmentNoAuth() {
		unauthenticate();
		
		Collection<Assessment> assess = assessmentService.findAllWithoutDeleted();
		
		Assert.isTrue(assess.size()==1);
	}	
	
	
	/* 								TEST NEGATIVO 						*/
	
	// Test crear un assessment 
	//ERROR: Crear un assessment logueado como admin. 
	@Test(expected = IllegalArgumentException.class)
	public void testCreateAssessmentAdmin() {
		authenticate("admin");
		
		Assessment assess = assessmentService.create();
		
		assess.setText("Text");
		assess.setRating(5);
		
		assessmentService.save(assess);
		
		unauthenticate();
	}
	
	
	// Test crear un assessment 
	//ERROR: Crear un assessment sin añadirle un text. 
	@Test(expected = IllegalArgumentException.class)
	public void testCreateAssessmentWithOutText() {
		authenticate("user1");
		
		Assessment assess = assessmentService.create();
		
		assess.setRating(3);
		
		assessmentService.save(assess);
		
		unauthenticate();
	}
	
	
	// Test crear un assessment 
	//ERROR: Eliminar un assessment logueado como clerk.
	@Test(expected = IllegalArgumentException.class)
	public void testDeleteAssessmentClerk() {
		authenticate("clerk1");
		
		Assessment assess = assessmentService.findOne(25);
		
		assessmentService.delete(assess);
		
		unauthenticate();
	}
	
	
	// Test crear un assessment 
	//ERROR: Eliminar un assessment sin estar logueado en el sistema.
	@Test(expected = IllegalArgumentException.class)
	public void testDeleteAssessment() {
		
		unauthenticate();
		
		Assessment assess = assessmentService.findOne(25);
		
		assessmentService.delete(assess);

	}
}
