package springExample.persistence;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

import springExample.model.Customer;

public interface CustomerRepository extends CrudRepository<Customer, Long> {
	/**
	 * Provides a way to search for a customer by their last name
	 * 
	 * @param lastName The last name to search for
	 * @return The list of customers matching the search criteria
	 */
	@Transactional(readOnly=true)
	List<Customer> findByLastName(String lastName);
	
	/**
	 * Provides a way to search for a customer by their first and last name
	 * 
	 * @param firstName  The first name to search for, this is case insensitive and can be a partial name
	 * @param lastName The last name to search for, this is case insensitive and can be a partial name
	 * @return The list of customers matching the search criteria
	 */
	@Transactional(readOnly=true)
	List<Customer> findByFirstNameContainingIgnoreCaseAndLastNameContainingIgnoreCase(String firstName, String lastName);
}
