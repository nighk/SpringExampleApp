This is an example Spring Boot application, to show case a simple application design and some operations that can be performed with help of the Spring framework.

It runs using Springs embedded Tomcat server, and an in memory database. So any changes won't be persistent between application restarts.



### Starting the Application

This is a maven project so can be built from the command line using "mvn clean install", and run as a Java application.

Alternatively load it into your favourite IDE and just run the application from the main method within App.java under src/main/java/springExample



### Web Endpoints

The application will start using Spring embedded Tomcat server and will run on port 8080. In your browser go to http://localhost:8080 to view the index page. The other available URL's are as follows:

* /customer/all - lists all customers in a table
* /customer/id/{customerId} - display a specific customer by their id e.g. 1
* /customer/search?firstName={firstName}&lastName={lastName} - search for customers by first and last name e.g. Paul Monk, results displayed in a table
* /customer/search/json?firstName={firstName}&lastName={lastName} - search for customers by first and last name e.g. Paul Monk, results returned as json
* /customer/create - displays a form to create a new customer

There are some additional URL's provided by the Spring framework. These are as follows:

* /h2-console/ - the console for the in memory database, the JDBC URL is "jdbc:h2:mem:testdb", username is "sa" and password is "sa". All this is stored in the application.properties file under src/main/resources/config
* /actuator - shows information about the application
* /actuator/health - shows the running status of the application