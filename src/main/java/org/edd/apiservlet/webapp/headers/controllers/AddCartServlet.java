package org.edd.apiservlet.webapp.headers.controllers;

import jakarta.inject.Inject;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.edd.apiservlet.webapp.headers.configs.ProductServiceMain;
import org.edd.apiservlet.webapp.headers.models.Cart;
import org.edd.apiservlet.webapp.headers.models.ItemCart;
import org.edd.apiservlet.webapp.headers.models.entities.Product;
import org.edd.apiservlet.webapp.headers.services.ProductService;

import java.io.IOException;
import java.util.Optional;

@WebServlet("/cart/add")
public class AddCartServlet extends HttpServlet {
    @Inject
    @ProductServiceMain
    private ProductService service;

    @Inject
    private Cart cart;
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Long id = Long.parseLong(req.getParameter("id"));
        Optional<Product> product = service.findById(id);
        if (product.isPresent()) {
            ItemCart item = new ItemCart(1, product.get());
            cart.addItem(item);
        }
        resp.sendRedirect(req.getContextPath() + "/cart/see");
    }
}
