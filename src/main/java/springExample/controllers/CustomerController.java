package springExample.controllers;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import springExample.model.Customer;
import springExample.service.CustomerService;

/**
 * Provides web endpoints for the "/customer" url within the application
 */
@Controller
@RequestMapping("/customer")
public class CustomerController
{
	@Autowired
    private CustomerService customerServiceBean;

    /**
     * Provides a JSP web page displaying a list of all customers
     * when a user accesses the "/all" endpoint
     *
     * @param model The model containing parameters sent to the JSP page
     * @return The name of the JSP page to render
     */
    @GetMapping(path = "/all")
    public String findAll(Model model)
    {
    	model.addAttribute("customers", customerServiceBean.findAll());
    	return "customersTable";
    }
    
    /**
     * Provides a JSP web page displaying information on a specific customer
     *
     * @param model The model containing parameters sent to the JSP page
     * @param customerId The id of the customer to display
     * @return The name of the JSP page to render
     */
    @GetMapping(path = "/id/{customerId}")
    public String findById(
    		Model model,
    		@PathVariable("customerId") Long customerId)
    {
    	model.addAttribute("customers", Arrays.asList(customerServiceBean.findById(customerId)));
    	return "customersTable";
    }
    
    /**
     * Provides a JSP web page displaying information on customers that match the search parameters provided
     *
     * @param model The model containing parameters sent to the JSP page
     * @param firstName First name of the customer to search for (optional)
     * @param lastName Last name of the customer to search for (optional)
     * @return The name of the JSP page to render
     */
    @GetMapping(path = "/search")
    public String findSpecific(
    		Model model,
    		@RequestParam(name = "firstName", required = false, defaultValue = "") String firstName, 
    		@RequestParam(name = "lastName", required = false, defaultValue = "") String lastName)
    {
    	model.addAttribute("customers", customerServiceBean.find(firstName, lastName));
    	return "customersTable";
    }
    
    /**
     * Provides information on customers that match the search parameters provided in a JSON format
     *
     * @param firstName First name of the customer to search for (optional)
     * @param lastName Last name of the customer to search for (optional)
     * @return The list of customers converted into a JSON response
     */
    @GetMapping(path = "/search/json")
    public @ResponseBody List<Customer> findSpecificJson(
    		@RequestParam(name = "firstName", required = false, defaultValue = "") String firstName, 
    		@RequestParam(name = "lastName", required = false, defaultValue = "") String lastName)
    {
    	return customerServiceBean.find(firstName, lastName);
    }
    
    /**
     * Displays a JSP page with a form to create a new customer
     * 
     * @return The name of the JSP page to render
     */
    @GetMapping(path = "/create")
    public String createGet()
    {
    	return "customerCreate";
    }
    
    /**
     * The POST endpoint through which a new customer can be created,
     * renders a JSP page showing the details of the created customer if successful
     * 
     * @param model The model containing parameters sent to the JSP page
     * @param formData The form data containing information about the new customer to be created
     * @return The name of the JSP page to render
     */
    @PostMapping(path = "/create", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public String createPost(Model model, @RequestBody MultiValueMap<String, String> formData)
    {
    	Customer created = customerServiceBean.create(formData);
    	model.addAttribute("customers", Arrays.asList(created));
    	return "customersTable";
    }
}
