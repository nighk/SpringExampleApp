package springExample.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * The customer model
 * 
 * @author Paul Monk
 */
@Entity
public class Customer 
{
	@Id
	@GeneratedValue
    private long id;
	
    private String firstName, lastName;
    
    /**
     * No-args constructor needed by JPA
     */
    protected Customer() {}

    /**
     * Standard constructor
     * 
     * @param firstName Customers first name
     * @param lastName Customer last name
     */
    public Customer(String firstName, String lastName) 
    {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    /**
     * Gets the id if the customer
     * 
     * @return The id value
     */
    public long getId() 
    {
		return id;
	}

    /**
     * Sets the id of the customer
     * 
     * @param id The new value
     */
	public void setId(long id) 
	{
		this.id = id;
	}

	/**
	 * Gets the first name of the customer
	 * 
	 * @return The first name value
	 */
	public String getFirstName() 
	{
		return firstName;
	}

	/**
	 * Sets the first name of the customer
	 * 
	 * @param firstName The new value
	 */
	public void setFirstName(String firstName) 
	{
		this.firstName = firstName;
	}

	/**
	 * Gets the last name of the customer
	 * 
	 * @return The last name value
	 */
	public String getLastName() 
	{
		return lastName;
	}

	/**
	 * Sets the last naem of the customer
	 * 
	 * @param lastName The new value
	 */
	public void setLastName(String lastName) 
	{
		this.lastName = lastName;
	}

	@Override
    public String toString() 
	{
        return String.format(
                "Customer[id=%d, firstName='%s', lastName='%s']",
                id, firstName, lastName);
    }
}