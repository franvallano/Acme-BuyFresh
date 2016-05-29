package services;

import java.util.Collection;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import utilities.AbstractTest;
import domain.Menu;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
		"classpath:spring/datasource.xml",
		"classpath:spring/config/packages.xml"})
@Transactional
@TransactionConfiguration(defaultRollback = true)
public class MenuServiceTest extends AbstractTest{
	
	@Autowired
	private MenuService menuService;
	
	/*------------------------------- TEST POSITIVO --------------------------------- */
	
	/**
	 *   +	Acceder a los menús activos para poder realizar un pedido en ese momento, de modo que se 
	 *		pueda navegar a las recetas que estos contienen.
	 */
	
	@Test
	public void activesMenus(){
		unauthenticate();
		
		Collection<Menu> menus = menuService.findActiveMenus();
		
		Assert.isTrue(menus.size() == 1);
		
		
	}
	/**		Requisito de administrador:
			+	Gestionar los menús. 
	 */
	 /*------------------------------- TESTS NEGATIVOS --------------------------------- */
	
	@Test(expected = IllegalArgumentException.class)
	public void editMenuUser(){
		authenticate("user1");
		
		Menu m = menuService.findOne(6);
		
		m.setName("new Name");
		
		menuService.save(m);
		
		
		
	}
	
	/**
	 * 		+ Un menú al ser eliminado, será registrado en el sistema, de modo que sólo 
			pueda ser visto por el administrador.
	 */
	
	@Test(expected = IllegalArgumentException.class)
	public void findDeletedMenuUser(){
		authenticate("user1");
		
		menuService.findOne(7);
		
	
	}

}
