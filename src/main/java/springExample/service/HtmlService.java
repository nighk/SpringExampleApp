package springExample.service;

/**
 * A simple class providing HTML messages
 * 
 * @author Paul Monk
 */
public class HtmlService {
	private String prefix;
	private String html;
	
	/**
	 * Constructor
	 * 
	 * @param prefixIn The HTML prefix string
	 */
	public HtmlService(String prefixIn) {
		prefix = prefixIn;
	}
	
	/**
	 * Sets the HTML content
	 * 
	 * @param htmlIn The new content
	 */
	public void setHtml(String htmlIn) {
		html = htmlIn;
	}
	
	/**
	 * Creates the HTML appending the content to the end of the prefix
	 * 
	 * @return The HTML string
	 */
	public String getHtml() {
		return prefix + " " + html;
	}
}
