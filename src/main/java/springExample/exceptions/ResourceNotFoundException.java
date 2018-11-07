package springExample.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * An exeception that results in a 404 response when passed through a controller
 * 
 * @author Paul Monk
 */
@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class ResourceNotFoundException extends RuntimeException 
{
	private static final long serialVersionUID = 1L;
		
	/**
	 * The constructor
	 * 
	 * @param messageIn An error message for the exception
	 */
	public ResourceNotFoundException(String messageIn) 
	{
		super(messageIn);
	}
}
