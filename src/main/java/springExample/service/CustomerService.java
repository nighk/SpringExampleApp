package springExample.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.util.MultiValueMap;

import com.fasterxml.jackson.databind.ObjectMapper;

import springExample.exceptions.ResourceNotFoundException;
import springExample.model.Customer;
import springExample.persistence.CustomerRepository;

/**
 * Provides access to the customer data held by the application
 * 
 * @author Paul Monk
 */
public class CustomerService {
	@Autowired
	private CustomerRepository customerRepository;
	
	@Autowired
	private ObjectMapper objectMapper;
	
	/**
	 * Example PostConstruct method run immediately after this bean has been created
	 */
	@PostConstruct
	public void setup() {
		System.out.println("CustomerBean.setup()");
	}
	
	/**
	 * Example PreDestroy method run immediately before this bean is removed from the application context
	 */
	@PreDestroy
	public void teardown() {
		System.out.println("CustomerBean.teardown()");
	}
	
	/**
	 * Connects to the database to return a list fo all customers
	 * 
	 * @return A list of all customers
	 */
	public List<Customer> findAll() 
	{
		List<Customer> customers = new ArrayList<>();
		customerRepository.findAll().forEach(customer -> customers.add(customer));
		return customers;
	}
	
	/**
	 * Connects to the database to find a customer with a given id
	 * 
	 * @param id The id to search for
	 * @return The matching customer, or an exception is thrown to return a 404 response if that customer can't be found
	 */
	public Customer findById(Long id) 
	{
		Optional<Customer> optional = customerRepository.findById(id);
		
		if(optional.isPresent()) {
			return optional.get();
		}
		
		throw new ResourceNotFoundException("No customer with id '" + id + "' could be found.");
	}
	
	/**
	 * Connects to the database to search for a customer by their first and last names
	 * 
	 * @param firstName  The first name to search for, this is case insensitive and can be a partial name
	 * @param lastName The last name to search for, this is case insensitive and can be a partial name
	 * @return The list of customers matching the search criteria
	 */
	public List<Customer> find(String firstName, String lastName) 
	{
		List<Customer> customers = new ArrayList<>();
		customerRepository.findByFirstNameContainingIgnoreCaseAndLastNameContainingIgnoreCase(firstName, lastName).forEach(customer -> customers.add(customer));
		return customers;
	}
	
	/**
	 * Creates a new customer based on information from a map
	 * 
	 * @param multiValuedMap The map containing information on the customer to be created
	 * @return The customer object that has been persited in the database
	 */
	public Customer create(MultiValueMap<String, String> multiValuedMap) 
	{
		Map<String, String> map = multiValuedMap.toSingleValueMap();
		Customer newCustomer = objectMapper.convertValue(map, Customer.class);
		Customer created = customerRepository.save(newCustomer);
		return created;
	}
	
	/**
	 * Used at application startup to create customers in the database, 
	 * and run some example queries, logging the output
	 * 
	 * Some customers are also loaded automatically by Spring from the data.sql script under src/main/resources/
	 * 
	 * @return A command line runner, telling spring to run this method at startup when it is declared as a bean
	 */
	public CommandLineRunner createCustomers()
	{
		return (args) -> {
			// save a couple of customers
			customerRepository.save(new Customer("Jack", "Bauer"));
			customerRepository.save(new Customer("Chloe", "O'Brian"));
			customerRepository.save(new Customer("Kim", "Bauer"));
			customerRepository.save(new Customer("David", "Palmer"));
			customerRepository.save(new Customer("Michelle", "Dessler"));

			// fetch all customers
			System.out.println("Customers found with findAll():");
			System.out.println("-------------------------------");
			for (Customer customer : customerRepository.findAll()) {
				System.out.println(customer.toString());
			}
			System.out.println("");

			// fetch an individual customer by ID
			customerRepository.findById(1L)
				.ifPresent(customer -> {
					System.out.println("Customer found with findById(1L):");
					System.out.println("--------------------------------");
					System.out.println(customer.toString());
					System.out.println("");
				});

			// fetch customers by last name
			System.out.println("Customer found with findByLastName('Bauer'):");
			System.out.println("--------------------------------------------");
			customerRepository.findByLastName("Bauer").forEach(bauer -> {
				System.out.println(bauer.toString());
			});

			System.out.println("");
		};
	}
}
