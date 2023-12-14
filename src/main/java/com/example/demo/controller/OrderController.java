package com.example.demo.controller;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.webflow.core.collection.LocalAttributeMap;
import org.springframework.webflow.core.collection.MutableAttributeMap;
import org.springframework.webflow.execution.FlowExecutionOutcome;
import org.springframework.webflow.executor.FlowExecutionResult;
import org.springframework.webflow.executor.FlowExecutor;
import org.springframework.webflow.test.MockExternalContext;

import com.example.demo.dao.CustomerDaoImpl;
import com.example.demo.model.Cart;
import com.example.demo.model.Customer;
import com.example.demo.model.CustomerOrder;
import com.example.demo.service.CartItemService;
import com.example.demo.service.CartService;
import com.example.demo.service.CustomerOrderService;

@Controller
public class OrderController {

	@Autowired
	private CartService cartService;
	
	@Autowired
	private CartItemService cartItemService;

	@Autowired
	private CustomerOrderService customerOrderService;

	@Autowired
	private CustomerDaoImpl customerService;
	//@Autowired
	//private FlowExecutor flowExecutor;

//	@GetMapping("/order/{cartId}")
//    public String createOrder(@PathVariable("cartId") String cartId) {
//		 //MockExternalContext context = new MockExternalContext();
//		  //  context.putRequestParameter("cartId", cartId);  // Set any required parameters
//		  //  context.setEventId("start");  // Set the initial event to start the flow
//
//		    // Launch the flow
//		 //   FlowExecutionResult result = flowExecutor.launchExecution("checkout", null, context);
//
//		    // Now, set the event to transition to the next state
//		 //   context.setEventId("customerInfoCollected");
//
//		    // Get the outcome and return a response
//		 //   String outcome = String.valueOf(result.getOutcome());
//		   // return "Flow execution complete. Outcome: " + outcome;
//		
//		return "redirect:/checkout?execution=e1s1";
//    }

	@GetMapping("/order/{cartId}")
	public String createOrder(@PathVariable("cartId") String cartId, Model model) {

		CustomerOrder customerOrder = new CustomerOrder();

		Cart cart = cartService.getCartByCartId(Integer.parseInt(cartId));

		Customer customer = cart.getCustomer();
		
		customerOrder.setCart(cart);

		customerOrder.setCustomer(customer);

		customerOrder.setShippingAddress(customer.getShippingAddress());

		customerOrder.setBillingAddress(customer.getBillingAddress());

		customerOrderService.addCustomerOrder(customerOrder);
	
		
		model.addAttribute(customerOrder);
	
	
		return "collectCustomerInfo";
		// return "redirect:/checkout?cartId=" + cartId;

		// String encodedCartId = URLEncoder.encode("9", StandardCharsets.UTF_8);
		// return "redirect:/checkout?cartId=" + encodedCartId + "&execution=e1s1";
		// return "redirect:/checkout?cartId=9&execution=e1s1";
	}
	
	
	@PostMapping("/order/ship/{cartId}/{customerOrderId}")
	public String getShipDetails(@PathVariable("cartId") String cartId,@PathVariable("customerOrderId") String customerOrderId,@ModelAttribute("customerOrder") CustomerOrder customerOrder, Model model){
		
		CustomerOrder existing = customerOrderService.getCustomerOrder(Integer.parseInt(customerOrderId));
	
		existing.getCustomer().setBillingAddress(customerOrder.getCustomer().getBillingAddress());
		
		
		customerOrderService.editCustomerOrder(existing);
		
		model.addAttribute(existing);
		return "collectShippingDetail";
		
	}
	
	@PostMapping("/order/confirm/{cartId}/{customerOrderId}")
	public String getConfirmDetails(@PathVariable("cartId") String cartId,@PathVariable("customerOrderId") String customerOrderId,@ModelAttribute("customerOrder") CustomerOrder customerOrder, Model model){
		
		CustomerOrder existing = customerOrderService.getCustomerOrder(Integer.parseInt(customerOrderId));
		
		System.out.println("customer given shipping Details --" + customerOrder.getCustomer().getShippingAddress().toString());
		
		existing.getCustomer().setShippingAddress(customerOrder.getCustomer().getShippingAddress());
		
		System.out.println("New set shipping Details --" + existing.getCustomer().getShippingAddress().toString());
		
		existing.getCart().setTotalPrice(customerOrderService.getCustomerOrderGrandTotal(Integer.parseInt(cartId)));
		
		customerOrderService.editCustomerOrder(existing);
		
		model.addAttribute(existing);
		
		return "orderConfirmation";
		
	}
	
	///order/submit/4
	@PostMapping("/order/submit/{cartId}/{customerOrderId}")
	public String getSubmitDetails(@PathVariable("cartId") String cartId,@PathVariable("customerOrderId") String customerOrderId,@ModelAttribute("customerOrder") CustomerOrder customerOrder){
		
		Cart cart = cartService.getCartByCartId(Integer.parseInt(cartId));
		
		CustomerOrder existing = customerOrderService.getCustomerOrder(Integer.parseInt(customerOrderId));
		
		cartItemService.removeAllCartItems(cart);
		customerOrderService.delete(existing);
		
		
		//cartItemService.removeAllCartItems(cart);
		System.out.println("************cart Id is*******************" + cartId);
		
		
		return "thankCustomer";
	}

	/*
	 * @GetMapping("/order/{cartId}") public String
	 * createOrder(@PathVariable("cartId") String cartId) {
	 * 
	 * 
	 * CustomerOrder customerOrder = new CustomerOrder();
	 * 
	 * Cart cart = cartService.getCartByCartId(Integer.parseInt(cartId)); // Update
	 * CartId for // customerOrder - set CartId customerOrder.setCart(cart);
	 * 
	 * Customer customer = cart.getCustomer();
	 * 
	 * customerOrder.setCustomer(customer); // Set customerid // Set //
	 * ShippingAddressId
	 * customerOrder.setShippingAddress(customer.getShippingAddress());
	 * 
	 * customerOrder.setBillingAddress(customer.getBillingAddress());
	 * 
	 * 
	 * 
	 * if(cart != null) { customerOrder.setCart(cart); }else {
	 * System.out.println("Cart is nulll!!!!"); }
	 * 
	 * customerOrderService.addCustomerOrder(customerOrder);
	 * 
	 * 
	 * // MockExternalContext context = new MockExternalContext(); //
	 * context.setEventId("addCartToOrder"); // Set the initial event to start the
	 * flow // context.putRequestParameter("cartId", cartId);
	 * 
	 * 
	 * // MutableAttributeMap<Object> input = new LocalAttributeMap<>(); //
	 * input.put("cartId", cartId);
	 * 
	 * // FlowExecutionResult result = flowExecutor.launchExecution("checkout",
	 * null, context);
	 * 
	 * // FlowExecutionKey pausedKey = result.getPausedKey(); //
	 * System.out.println("Paused key: " + pausedKey);
	 * 
	 * // Get the last state // String lastState = result.getLastState(); //
	 * System.out.println("Last state: " + lastState);
	 * 
	 * // Get the outcome // String outcome = result.getOutcome(); //
	 * System.out.println("Outcome: " + outcome); // return "redirect:" +
	 * result.getPausedKey();
	 * 
	 * 
	 * 
	 * // System.out.println("Outcome: " + result.getFlowId()); //
	 * System.out.println("Outcome: " + result.getPausedKey()); //
	 * System.out.println("Outcome: " + result.toString()); // String outcome =
	 * String.valueOf(result.getOutcome()); // return "redirect:/checkout?cartId=" +
	 * cartId;
	 * 
	 * }
	 */

	

}
