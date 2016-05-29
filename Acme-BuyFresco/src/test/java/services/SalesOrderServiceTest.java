package services;


import javax.transaction.Transactional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;

import domain.SalesOrder;

import utilities.AbstractTest;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
		"classpath:spring/datasource.xml",
		"classpath:spring/config/packages.xml"})
@Transactional
@TransactionConfiguration(defaultRollback = true)
public class SalesOrderServiceTest extends AbstractTest{
	
	@Autowired
	private SalesOrderService salesOrderService;
	
	 /*------------------------------- TEST POSITIVO --------------------------------- */
	
	/**
	 * 	+ Visualizar su pedido y editar su pedido hasta que este no haya sido puesto como enviado.
	 */
	
	@Test
	public void getOrderAndEdit(){
		authenticate("user1");
		
		SalesOrder order = salesOrderService.findOne(32);
		
		order.setNotes("Recibirlo por la tarde");
		
		salesOrderService.save(order);
		
		unauthenticate();
		
	
	}
	
	 /*------------------------------- TESTS NEGATIVOS --------------------------------- */

	/**
	 * 	+ Visualizar su pedido y editar su pedido hasta que este no haya sido puesto como enviado.
	 */
	
	@Test(expected = IllegalArgumentException.class)
	public void editSentOrder(){
		authenticate("user1");
		
		SalesOrder order = salesOrderService.findOne(32);
		
		order.setNotes("Recibirlo por la tarde");
		
		salesOrderService.save(order);
		
		//unauthenticate();

	}
	

}
