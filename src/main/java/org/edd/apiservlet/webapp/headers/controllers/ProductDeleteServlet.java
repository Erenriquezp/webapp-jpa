package org.edd.apiservlet.webapp.headers.controllers;

import jakarta.inject.Inject;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.edd.apiservlet.webapp.headers.configs.ProductServiceMain;
import org.edd.apiservlet.webapp.headers.models.entities.Product;
import org.edd.apiservlet.webapp.headers.services.ProductService;

import java.io.IOException;
import java.util.Optional;

@WebServlet("/products/delete")
public class ProductDeleteServlet extends HttpServlet {
    @Inject
    @ProductServiceMain
    private ProductService service;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Long id;
        try {
            id = Long.parseLong(req.getParameter("id"));
        } catch (NumberFormatException e) {
            id = 0L;
        }
        if (id > 0) {
            Optional<Product> optionalProduct = service.findById(id);
            if (optionalProduct.isPresent()) {
                service.delete(id);
                resp.sendRedirect(req.getContextPath() + "/products");
            } else {
                resp.sendError(HttpServletResponse.SC_NOT_FOUND, "Product not found in the data base");
            }
        } else {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid product id");
        }
    }
}
