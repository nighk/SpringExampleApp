package springExample;

import java.net.URL;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource(locations = "classpath:config/application.properties")
public class SpringTrainingIntegrationTest {
	@LocalServerPort
	private int port;
	
	@Autowired
	private TestRestTemplate template;
	
	private URL base;
	
	@Before
	public void setup() throws Exception 
	{
		base = new URL("http://localhost:" + port);
	}
	
	@Test
	public void getHello() throws Exception
	{
		ResponseEntity<String> response = template.getForEntity(base.toString() + "/", String.class);
		
		Assert.assertEquals(HttpStatus.OK, response.getStatusCode());
		Assert.assertEquals("Spring Training Java Config", response.getBody());
	}
	
	@Test
	@Sql({"classpath:createDatabase.sql", "classpath:customersTest.sql"})
	@Sql(scripts = "classpath:dropDatabase.sql", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
	public void getCustomers() throws Exception 
	{
		ResponseEntity<String> response = template.getForEntity(base.toString() + "/customer/all", String.class);
		
		Assert.assertEquals(HttpStatus.OK, response.getStatusCode());
		Assert.assertTrue(response.getBody().contains("<td>Paul</td>")); // Paul listed in table
		Assert.assertTrue(response.getBody().contains("<td>Toby</td>")); // Toby listed in table
	}
	
	@Test
	@Sql("classpath:createDatabase.sql")
	@Sql(scripts = "classpath:dropDatabase.sql", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
	public void getCustomersEmpty() throws Exception 
	{
		ResponseEntity<String> response = template.getForEntity(base.toString() + "/customer/all", String.class);
		
		Assert.assertEquals(HttpStatus.OK, response.getStatusCode());
		Assert.assertFalse(response.getBody().contains("<td>")); // no customers listed in table
	}
}
