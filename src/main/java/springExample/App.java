package springExample;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * The main starting point for the application
 * 
 * @author Paul Monk
 */
@SpringBootApplication
public class App
{
	/**
	 * Starts the Spring application
	 * 
	 * @param args Command line arguments
	 */
    public static void main( String[] args )
    {
        SpringApplication.run(App.class, args);
    }
}
