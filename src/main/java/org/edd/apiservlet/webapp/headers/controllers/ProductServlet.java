package org.edd.apiservlet.webapp.headers.controllers;

import jakarta.inject.Inject;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.edd.apiservlet.webapp.headers.configs.ProductServiceMain;
import org.edd.apiservlet.webapp.headers.models.entities.Product;
import org.edd.apiservlet.webapp.headers.services.*;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@WebServlet({"/products.html", "/products"})
public class ProductServlet extends HttpServlet {
    @Inject
    @ProductServiceMain
    private ProductService service;
    @Inject
    private LoginService auth;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Product> products = service.list();

        Optional<String> usernameOptional = auth.getUsername(req);

        req.setAttribute("products", products);
        req.setAttribute("username", usernameOptional);
        req.setAttribute("title", req.getAttribute("title") + ": Product's List");

        getServletContext().getRequestDispatcher("/list.jsp").forward(req, resp);
    }
}
