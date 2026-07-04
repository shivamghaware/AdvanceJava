package com.voting.listeners;

import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.ServletRequestEvent;
import jakarta.servlet.ServletRequestListener;
import jakarta.servlet.annotation.WebListener;
import jakarta.servlet.http.HttpSessionEvent;
import jakarta.servlet.http.HttpSessionListener;

/**
 * Application Lifecycle Listener implementation class WebAppListener
 *
 */
@WebListener
public class WebAppListener implements ServletContextListener, HttpSessionListener, ServletRequestListener {

	/**
     * @see HttpSessionListener#sessionCreated(HttpSessionEvent)
     */
    public void sessionCreated(HttpSessionEvent se)  { 
         System.out.println("session created");
    }

	/**
     * @see HttpSessionListener#sessionDestroyed(HttpSessionEvent)
     */
    public void sessionDestroyed(HttpSessionEvent se)  { 
    	System.out.println("session destroyed");
    }

	/**
     * @see ServletContextListener#contextInitialized(ServletContextEvent)
     * 
     * 
     */
    public void contextInitialized(ServletContextEvent sce)  { 
    	System.out.println("ctx inited");
    }

	/**
     * @see ServletContextListener#contextDestroyed(ServletContextEvent)
     */
    public void contextDestroyed(ServletContextEvent sce)  { 
    	System.out.println("ctx destroyed");
    }

	/**
     * @see ServletRequestListener#requestInitialized(ServletRequestEvent)
     * 
     *	
     */
    public void requestInitialized(ServletRequestEvent sre)  { 
    	System.out.println("req inited");
    }

	/**
     * @see ServletRequestListener#requestDestroyed(ServletRequestEvent)
     */
    public void requestDestroyed(ServletRequestEvent sre)  { 
         System.out.println("req destroyed");
    }
	
}
