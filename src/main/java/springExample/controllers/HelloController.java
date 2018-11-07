package springExample.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import springExample.service.HtmlService;

/**
 * Provides web endpoints for the "/" url of the application
 */
@RestController
public class HelloController
{
    @Autowired
    private ApplicationContext context;

    /**
     * Provides a HTML web page when a user accesses the "/" endpoint
     *
     * @return The HTML provided by a bean
     */
    @RequestMapping(path = "/", method = RequestMethod.GET)
    public String index()
    {
        return context.getBean(HtmlService.class).getHtml();
    }
}
