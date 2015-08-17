package com.mightymerce.paypal;

import java.io.File;
import java.net.URL;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.ServerConnector;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.eclipse.jetty.webapp.Configuration;
import org.eclipse.jetty.webapp.WebAppContext;

public class Application {
	public static void main(String[] args) throws Exception 
    {
		
		Application app = new Application();
		app.start();
		    
    }
	
	public void start() throws Exception{
			// See http://www.eclipse.org/jetty/documentation/current/embedded-examples.html#embedded-webapp-jsp for JSP delivery in Jetty,
			Server server = new Server();
			ServerConnector c = new ServerConnector(server);
			c.setIdleTimeout(1000);
			c.setAcceptQueueSize(10);
			c.setPort(8080);
			c.setHost("localhost");
			
			String webDir = Application.class.getClassLoader().getResource("webapp").toExternalForm();
			
	        Configuration.ClassList classlist = Configuration.ClassList
	                .setServerDefault( server );
	        classlist.addBefore(
	                "org.eclipse.jetty.webapp.JettyWebXmlConfiguration",
	                "org.eclipse.jetty.annotations.AnnotationConfiguration" );
	
			
			ServletContextHandler handler = new ServletContextHandler(server,
					"/paypal", true, false);
			ServletHolder checkoutServletHolder = new ServletHolder(
					CheckoutServlet.class);
			handler.addServlet(checkoutServletHolder, "/Checkout");
			ServletHolder returnServletHolder = new ServletHolder(
					ReturnServlet.class);
			handler.addServlet(returnServletHolder, "/Return");
			handler.setResourceBase(webDir);
			
			WebAppContext webapp = new WebAppContext(webDir, "/paypal");
	        webapp.setAttribute(
	                "org.eclipse.jetty.server.webapp.ContainerIncludeJarPattern",
	                ".*/[^/]*servlet-api-[^/]*\\.jar$|.*/javax.servlet.jsp.jstl-.*\\.jar$|.*/[^/]*taglibs.*\\.jar$" );
	        
			server.setHandler(webapp);			
			server.addConnector(c);
			server.start();

	}
}
