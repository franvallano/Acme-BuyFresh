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

import repositories.OrderRepository;
import domain.Order;

@Service
@Transactional
public class OrderService {

	// Managed repository -----------------------------------------------------
	@Autowired
	private OrderRepository orderRepository;

	// Ancillary services -----------------------------------------------------

	// Constructor ------------------------------------------------------------
	public OrderService(){
		super();
	}

	// Simple CRUD methods ----------------------------------------------------
	public Order create(){
		Order newbye;
		
		newbye = new Order();
		
		return newbye;
	}

	public void save(Order entity){
		Assert.notNull(entity);
		
		this.orderRepository.save(entity);
	}

	public void delete(Order entity){
		Assert.isTrue(entity.getId()!=0);
		Assert.isTrue(this.orderRepository.exists(entity.getId()));
		
		this.orderRepository.delete( entity );
		
		Assert.isTrue(!this.orderRepository.exists(entity.getId()));
	}

	public Order findOne(int id){
		Assert.isTrue(id != 0);
		
		Order res;
		
		res = this.orderRepository.findOne(id);
		
		return res;
	}

	public Collection<Order> findAll(){
		Collection<Order> res;
		
		res = orderRepository.findAll();
		
		return res;
	}

	// Other business methods -------------------------------------------------

	// Ancillary methods ------------------------------------------------------

}
