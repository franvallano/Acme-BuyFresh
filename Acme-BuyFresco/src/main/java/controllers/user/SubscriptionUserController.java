package controllers.user;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import services.SubscriptionService;
import controllers.AbstractController;
import domain.Subscription;

@Controller
@RequestMapping("/subscription/user")
public class SubscriptionUserController extends AbstractController {
	
	// Supporting services ----------------------------------------------------

	@Autowired
	private SubscriptionService subscriptionService;
	
	// Constructors -----------------------------------------------------------
	
	public SubscriptionUserController() {
		super();
	}
	
	// Listing ----------------------------------------------------------------

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list() {
		ModelAndView result;
		Collection<Subscription> subscriptions;
		
		subscriptions = subscriptionService.findAll();
		
		result = new ModelAndView("subscription/list");
		result.addObject("requestURI", "subscription/user/list.do");
		result.addObject("subscriptions", subscriptions);
		
		return result;
	}

	
}
