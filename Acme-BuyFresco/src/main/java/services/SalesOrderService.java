/* 
* Autogenerated service code 
* Variables (text between %) must have been replaced when code is autogenerated
*/

package services;

import java.util.Collection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.SalesOrderRepository;
import domain.SalesOrder;
import domain.User;

@Service
@Transactional
public class SalesOrderService {

	// Managed repository -----------------------------------------------------
	@Autowired
	private SalesOrderRepository orderRepository;

	// Ancillary services -----------------------------------------------------

	@Autowired
	private MenuService menuService;
	
	@Autowired
	private UserService userService;
	
	
	
	// Constructor ------------------------------------------------------------
	public SalesOrderService(){
		super();
	}

	// Simple CRUD methods ----------------------------------------------------
	public SalesOrder create(){
		SalesOrder newbye;
		User u = userService.findByPrincipal();
		newbye = new SalesOrder();
		
		newbye.setAddress(u.getAddress());
		
		return newbye;
	}

	public void save(SalesOrder entity){
		Assert.notNull(entity);
		
		this.orderRepository.save(entity);
	}

	public void delete(SalesOrder entity){
		Assert.isTrue(entity.getId()!=0);
		Assert.isTrue(this.orderRepository.exists(entity.getId()));
		
		this.orderRepository.delete( entity );
		
		Assert.isTrue(!this.orderRepository.exists(entity.getId()));
	}

	public SalesOrder findOne(int id){
		Assert.isTrue(id != 0);
		
		SalesOrder res;
		
		res = this.orderRepository.findOne(id);
		
		return res;
	}

	public Collection<SalesOrder> findAll(){
		Collection<SalesOrder> res;
		
		res = orderRepository.findAll();
		
		return res;
	}

	// Other business methods -------------------------------------------------

	public Collection<SalesOrder> findOrdersWithoutClerk(){
		Collection<SalesOrder> orders;
		
		orders = orderRepository.findOrdersWithoutClerk();
		
		return orders;
	}
	
	public Collection<SalesOrder> findOrdersByClerk(int clerkId){
		Collection<SalesOrder> orders;
		
		orders = orderRepository.findOrdersByClerk(clerkId);
		
		return orders;
	}
	
	// Ancillary methods ------------------------------------------------------

}
