package springExample.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Description;

import springExample.service.HtmlService;

/**
 * Configuration registering the web interface beans
 * 
 * @author Paul Monk
 */
@Configuration
public class WebConfiguration {
	/**
     * Registers the HtmlService bean in the applications context
     *
     * @return A new instance of HtmlService
     */
    @Bean
    @Description("A bean to display static html")
    public HtmlService htmlService(@Value("${springTraining.indexHtml}") String indexHtml)
    {
        HtmlService myClass = new HtmlService("Spring Training");
        myClass.setHtml(indexHtml);
        return myClass;
    }
}
