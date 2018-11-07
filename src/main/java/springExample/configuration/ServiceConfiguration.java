package springExample.configuration;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Description;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.Scope;

import springExample.service.CustomerService;

/**
 * Configuration registering the low level service beans
 * 
 * @author Paul Monk
 */
@Configuration
public class ServiceConfiguration {
	/**
     * Registers the CustomerService bean in the applications context
     *
     * @return A new instance of CustomerService
     */
	@Bean
    @Description("A bean to access information on customers")
	@Scope("singleton") // default scope
    public CustomerService customerService() {
    	return new CustomerService();
    }
	
	/**
	 * Runs on startup as it returns a CommandLineRunner
	 * creates some customers in the database 
	 * and prints the results of some example queries to the console
	 * 
	 * @param customerBean The customerService instance, automatically injected by Spring
	 * @return A CommandLineRunner
	 */
	@Bean
	@Description("Run on startup to create customers in the database")
	@Profile("dev")
	public CommandLineRunner createCustomerService(CustomerService customerBean) {
		return customerBean.createCustomers();
	}
}
