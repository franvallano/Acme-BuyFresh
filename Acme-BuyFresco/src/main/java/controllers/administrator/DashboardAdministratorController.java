package controllers.administrator;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import services.AdministratorService;
import services.AllergenService;
import services.AssessmentService;
import services.ClerkService;
import services.MenuService;
import services.SalesOrderService;
import services.SubscriptionService;
import services.UserService;
import controllers.AbstractController;
import domain.Allergen;
import domain.Assessment;
import domain.Clerk;
import domain.Menu;
import domain.SalesOrder;
import domain.Subscription;
import domain.User;


@Controller
@RequestMapping("/dashboard/administrator")
public class DashboardAdministratorController extends AbstractController{
	//Services--------------------------------------------------------
	@Autowired
	private AdministratorService administratorService;

	@Autowired
	private SalesOrderService salesOrderService;
	
	@Autowired
	private ClerkService clerkService;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private SubscriptionService subscriptionService;
	
	@Autowired
	private AssessmentService assessmentService;
	
	@Autowired
	private MenuService menuService;
	
	@Autowired
	private AllergenService allergenService;
	
	//Constructor------------------------------------------------------
	public DashboardAdministratorController(){
		super();
	}
	
	//Listing----------------------------------------------------------
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView listDashboardAdmin(){
		ModelAndView result;
		
		Integer numUser = userService.getNumberOfUser();
		Collection <Subscription> subscriptionLastMonth = subscriptionService.SubscriptionLastMonth();
		Collection <Subscription> subscriptionLastWeek = subscriptionService.SubscriptionLastWeek();
		Collection <Menu> menuInMoreOrders = menuService.getMenusInMoreOrders();
		Collection <Menu> menuInLessOrders = menuService.getMenusInLessOrders();
		Integer subscriptionsActives = subscriptionService.NumOfSubscriptionsActives();
		Collection <User> usersMoreSubscriptions = userService.userMoreSubscriptions();
		Collection <User> usersMoreAssessments = userService.userMoreAssessments();
		Collection <Allergen> allergenMoreUsers = allergenService.allergenMoreUsers();
		Collection <Assessment> assessmentsRatingBigger = assessmentService.AssessmentsWithRatingBigger();
		Collection <SalesOrder> salesOrdersWithoutAssign = salesOrderService.findOrdersWithoutClerk();
		Collection <Clerk> clerkMoreSalesOrders = clerkService.getClerksWithMoreOrders();
		Collection <Clerk> clerkLessSalesOrders = clerkService.getClerksWithLessOrders();
		Integer ordersSent = salesOrderService.getNumberOfSentOrders();
		Double avgRatingSystem = assessmentService.findAvgRating();
		
		
		result = new ModelAndView("dashboard/list");
		
		result.addObject("numUser", numUser);
		result.addObject("subscriptionLastMonth", subscriptionLastMonth);
		result.addObject("subscriptionLastWeek", subscriptionLastWeek);
		result.addObject("menuInMoreOrders", menuInMoreOrders);
		result.addObject("menuInLessOrders", menuInLessOrders);
		result.addObject("subscriptionsActives", subscriptionsActives);
		result.addObject("usersMoreSubscriptions", usersMoreSubscriptions);
		result.addObject("usersMoreAssessments", usersMoreAssessments);
		result.addObject("ordersSent", ordersSent);
		result.addObject("allergenMoreUsers", allergenMoreUsers);
		result.addObject("avgRatingSystem", avgRatingSystem);
		result.addObject("assessmentsRatingBigger", assessmentsRatingBigger);
		result.addObject("salesOrdersWithoutAssign", salesOrdersWithoutAssign);
		result.addObject("clerkMoreSalesOrders", clerkMoreSalesOrders);
		result.addObject("clerkLessSalesOrders", clerkLessSalesOrders);
		
		return result;
	}
	
		
}
