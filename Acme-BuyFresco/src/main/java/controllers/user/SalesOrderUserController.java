package controllers.user;

import java.util.Collection;

import javax.sound.midi.Receiver;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import services.MenuService;
import services.RecipeService;
import services.SalesOrderService;
import services.SubscriptionService;
import services.SubstitutesService;
import services.UserService;
import controllers.AbstractController;
import domain.Menu;
import domain.Recipe;
import domain.SalesOrder;
import domain.Subscription;
import domain.Substitutes;
import domain.User;

@Controller
@RequestMapping("/order/user")
public class SalesOrderUserController extends AbstractController {
	
	
	// Supporting services ----------------------------------------------------
	@Autowired
	private SalesOrderService salesOrderService;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private MenuService menuService;
	
	@Autowired
	private SubscriptionService subscriptionService;
	
	@Autowired
	private RecipeService recipeService;
	
	@Autowired
	private SubstitutesService substitutesService;
	
	// Constructors -----------------------------------------------------------

	public SalesOrderUserController() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	// Listing ----------------------------------------------------------------
	
	@RequestMapping(value = "/myOrders", method = RequestMethod.GET)
	public ModelAndView myOrders() {
		ModelAndView result;
		Collection<SalesOrder> orders;
		String requestURI;
		
		User u = userService.findByPrincipal();
		
		orders = salesOrderService.findOrdersByUser(u.getId());		
		Boolean sizenotcero = true;
		if(orders.size() == 0){
			sizenotcero = false;
		}
		requestURI = "order/user/list.do";		
			
		result = new ModelAndView("order/myOrders");
		result.addObject("order", orders);
		result.addObject("requestURI", requestURI);
		result.addObject("edit",true);
		result.addObject("sizecero",sizenotcero);

		return result;
	}
	
	// Details --------------------------------------------------------------------------
	
	@RequestMapping(value = "/details", method = RequestMethod.GET)
	public ModelAndView details(@RequestParam int orderId) {
		ModelAndView result;
		SalesOrder order;
		String requestURI;
		
		order = salesOrderService.findOne(orderId);
		
		requestURI = "order/user/list.do";		
			
		result = new ModelAndView("order/details");
		result.addObject("order", order);
		result.addObject("menu",order.getMenu());
		result.addObject("substitute", order.getSubstitutes());
		result.addObject("requestURI", requestURI);

		return result;
	}
	
	// Assign ---------------------------------------------------------------------------
	/*@RequestMapping(value = "/assign", method = RequestMethod.GET)
	public ModelAndView assign(@RequestParam int orderId) {
		ModelAndView result;
		SalesOrder order = salesOrderService.findOne(orderId);
		Clerk c = clerkService.findByPrincipal();
		order.setClerk(c);
		try {
			salesOrderService.save(order);
			result = new ModelAndView("redirect:/order/clerk/myOrders.do");
		} catch (Exception e) {
			result = editModelAndView(order, "order.commit.error");
		}

		
		
		return result;
	}*/
	
	// Creation ---------------------------------------------------------------
	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ModelAndView create(int menuId) {
		ModelAndView result;
		SalesOrder order;
		Menu menu;

		
		menu = menuService.findOne(menuId);
		order = salesOrderService.create();
		order.setMenu(menu);
		order.setTotalPrice(menu.getPrice());

		result = createModelAndView(order);
		//result.addObject("recipes", recipes);
		
		return result;
	}
	
	@RequestMapping(value = "/create", method = RequestMethod.POST, params = "save")
	public ModelAndView save(@Valid SalesOrder order, BindingResult binding) {
		ModelAndView result;
		
		Subscription s;
		
		s = subscriptionService.create();
		User u = userService.findByPrincipal();
		if (binding.hasErrors()) {
			result = createModelAndView(order, "order.commit.error");
		} else {
			try {
				
				Subscription sub = subscriptionService.save(s);
				order.setSubscription(sub);
				order = salesOrderService.save(order);	
				for(Substitutes substitute: salesOrderService.getSubstitutesByUserMenu(u, order.getMenu())){
					substitute.setOrder(order);
					substitutesService.save(substitute);	
				}
				result = new ModelAndView("redirect:/order/user/myOrders.do");
			} catch (Throwable oops) {
				System.out.println(oops.toString());
				result = createModelAndView(order, "order.commit.error");
			}
		}
		
		return result;
	}
	
	// Edit ---------------------------------------------------------------
	
	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public ModelAndView edit(@RequestParam int orderId) {
		ModelAndView result;
		SalesOrder	order;
		Collection<Menu> menus;
		order = salesOrderService.findOne(orderId);
		menus = menuService.findAll();
		result = editModelAndView(order);
		result.addObject("menus",menus);
		
		return result;
	}
	
	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "update")
	public ModelAndView edit(@Valid SalesOrder order, BindingResult binding) {
		ModelAndView result;
		User u = userService.findByPrincipal();
		
		if (binding.hasErrors()) {
			result = editModelAndView(order, "order.commit.error");
			Collection<Menu> menus;
			menus = menuService.findAll();
			result.addObject("menus",menus);
		} else {
			try {
				order.setTotalPrice(order.getMenu().getPrice());
				order = salesOrderService.save(order);	
				for(Substitutes substitute: salesOrderService.getSubstitutesByUserMenu(u, order.getMenu())){
					substitute.setOrder(order);
					substitutesService.save(substitute);	
				}				
				result = new ModelAndView("redirect:/order/user/myOrders.do");
			} catch (Throwable oops) {
				result = editModelAndView(order, "order.commit.error");
				Collection<Menu> menus;
				menus = menuService.findAll();
				result.addObject("menus",menus);
			}
		}
		
		return result;
	}
		
	// Delete ---------------------------------------------------------------
		
	/*@RequestMapping(value = "/delete", method = RequestMethod.GET)
	public ModelAndView delete(@RequestParam int menuId) {
		ModelAndView result;
		Menu menu = salesOrderService.findOne(menuId);
		salesOrderService.delete(menu);
		result = new ModelAndView("redirect:/menu/administrator/list.do");
		
		
		return result;
	}
			
	
	// Ancillary methods ------------------------------------------------------	
	*/
	public ModelAndView createModelAndView(SalesOrder salesOrder){
		return createModelAndView(salesOrder, null);
	}
	
	public ModelAndView createModelAndView(SalesOrder salesOrder, String message){
		ModelAndView res;
		
		res = new ModelAndView("order/create");
		res.addObject("order", salesOrder);
		res.addObject("message", message);
		res.addObject("requestURI", "order/user/create.do");	

		return res;
	}
	
	
	public ModelAndView editModelAndView(SalesOrder order){
		return editModelAndView(order, null);
	}
	
	public ModelAndView editModelAndView(SalesOrder order, String message){
		ModelAndView res;
		
		res = new ModelAndView("order/edit");
		res.addObject("order", order);
		res.addObject("message", message);
		res.addObject("requestURI", "order/user/edit.do");	

		return res;
	}
}
