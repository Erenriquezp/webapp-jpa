package org.edd.apiservlet.webapp.headers.listeners;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebListener;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.HttpSessionEvent;
import jakarta.servlet.http.HttpSessionListener;
import org.edd.apiservlet.webapp.headers.models.Cart;

@WebListener
public class ApplicationListener implements ServletContextListener,
        ServletRequestListener, HttpSessionListener {

    private ServletContext servletContext;
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        sce.getServletContext().log("ApplicationListener.contextInitialized");
        servletContext = sce.getServletContext();
        servletContext.setAttribute("message", "Some global value for the application");
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        servletContext.log("ApplicationListener.contextDestroyed");
    }

    @Override
    public void requestInitialized(ServletRequestEvent sre) {
        servletContext.log("ApplicationListener.requestInitialized");
        sre.getServletRequest().setAttribute("message", "Some global value for the request");
        sre.getServletRequest().setAttribute("title", "Catalogo Servlet");
    }
    @Override
    public void requestDestroyed(ServletRequestEvent sre) {
        servletContext.log("ApplicationListener.requestDestroyed");
    }

    @Override
    public void sessionCreated(HttpSessionEvent se) {
        servletContext.log("ApplicationListener.sessionCreated");
//        Cart cart = new Cart();
//        HttpSession session = se.getSession();
//        session.setAttribute("cart", cart);
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent se) {
        servletContext.log("ApplicationListener.sessionDestroyed");
    }
}
