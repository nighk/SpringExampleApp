package springExample.aspects;

import java.util.Iterator;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

/**
 * A set of example aspects
 * 
 * @author Paul Monk
 */
@Aspect
@Component
public class AspectExample {
	private static final String CUSTOMER_REPOSITORY_FIND_ALL_METHOD = "execution(java.lang.Iterable springExample.persistence.CustomerRepository.findAll())";
	
	/**
	 * Runs before the CustomerRepository.findAll() method and prints out a message to the console
	 */
	@Before(CUSTOMER_REPOSITORY_FIND_ALL_METHOD)
	public void beforeFindAll() {
		System.out.println("CustomerRepository.findAll() will be called...");
	}
	
	/**
	 * Runs after the CustomerRepository.findAll() method whether it was succssful or not
	 * and prints out a message to the console
	 */
	@After(CUSTOMER_REPOSITORY_FIND_ALL_METHOD)
	public void afterFindAll() {
		System.out.println("CustomerRepository.findAll() has been called...");
	}
	
	/**
	 * Runs after the CustomerRepository.findAll() method has returned a successful response
	 * and prints out the number of customers returned to the console
	 */
	@AfterReturning(value = CUSTOMER_REPOSITORY_FIND_ALL_METHOD, returning = "customers")
	public void afterReturningFindAll(JoinPoint jP, Iterable<?> customers) {
		int size = 0;
		
		Iterator<?> iterator = customers.iterator();
		while(iterator.hasNext()) {
			size++;
			iterator.next();
		}
		
		System.out.println("CustomerRepository.findAll() returned " + size + " customers");
	}
}
