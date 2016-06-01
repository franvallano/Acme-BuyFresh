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

import repositories.SalesOrderRepository;

import utilities.AbstractTest;
import domain.Clerk;
import domain.SalesOrder;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
		"classpath:spring/datasource.xml",
		"classpath:spring/config/packages.xml"})
@Transactional
@TransactionConfiguration(defaultRollback = true)
public class ClerkServiceTest extends AbstractTest{
	
	// Service under test ----------------------------------------
	
	@Autowired
	private ClerkService clerkService;
	
	
	@Autowired
	private SalesOrderService salesOrderService;
	
	@Autowired
	private SalesOrderRepository salesOrderRepository;
	
	// Test -----------------------------------------------------
	
	
	/**
	 * +	Ver los pedidos que están sin asignar y los pedidos que ya tiene asignado. 
	 */
	@Test
	public void allAssignedSalesOrderByClerk(){
		authenticate("clerk1");
		
		Collection<SalesOrder> salesOrder = salesOrderService.findOrdersByClerk(28);

		Assert.isTrue(salesOrder.size() == 1);
		
	}
	
	/**
	 * +	Editar la fecha de entrega de un pedido que no haya sido enviado.
	 */
	
	@Test(expected=IllegalArgumentException.class)
	public void editSentSalesOrder(){
		authenticate("clerk1");
		
		SalesOrder salesOrder = salesOrderService.findOne(32);
		
		Clerk c = clerkService.findByPrincipal();
		salesOrder.setClerk(c);
		
		salesOrderService.save(salesOrder);
			
	}
	
	/**
	 * +	Asignarse un pedido que no esté asignado
	 * 
	 */
	@Test(expected=IllegalArgumentException.class)
	public void assignClerkToSalesOrderWithClerk(){
		authenticate("clerk1");
		
		SalesOrder salesOrder = salesOrderService.findOne(32);
		
		Clerk c = clerkService.findByPrincipal();
		salesOrder.setClerk(c);
		
		salesOrderService.save(salesOrder);
		
		salesOrderRepository.flush();
		
		
		salesOrder = salesOrderService.findOne(32);
		
		salesOrder.setClerk(c);
		
		salesOrderService.save(salesOrder);
			
	}

}
