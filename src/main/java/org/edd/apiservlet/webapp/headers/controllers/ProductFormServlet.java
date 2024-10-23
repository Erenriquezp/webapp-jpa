package org.edd.apiservlet.webapp.headers.controllers;

import jakarta.inject.Inject;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.edd.apiservlet.webapp.headers.configs.ProductServiceMain;
import org.edd.apiservlet.webapp.headers.models.entities.Category;
import org.edd.apiservlet.webapp.headers.models.entities.Product;
import org.edd.apiservlet.webapp.headers.services.ProductService;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@WebServlet("/products/form")
public class ProductFormServlet extends HttpServlet {
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
        Product product = new Product();
        product.setCategory(new Category());
        if (id > 0) {
            Optional<Product> optionalProduct = service.findById(id);
            if (optionalProduct.isPresent()) {
                product = optionalProduct.get();
            }
        }
        req.setAttribute("categories", service.listCategories());
        req.setAttribute("product", product);
        req.setAttribute("title", req.getAttribute("title") + ": Product's Form");


        getServletContext().getRequestDispatcher("/form.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("name");

        Integer price;
        try {
            price = Integer.valueOf(req.getParameter("price"));
        } catch (NumberFormatException e) {
            price = 0;
        }
        String sku = req.getParameter("sku");
        String dateStr = req.getParameter("createdDate");

        Long categoryId;
        try {
            categoryId = Long.valueOf(req.getParameter("category"));
        } catch (NumberFormatException e) {
            categoryId = 0L;
        }

        Map<String, String > errors = new HashMap<>();
        if (name == null || name.isBlank()) {
            errors.put("name", "name can't be empty!");
        }
        if (sku == null || sku.isBlank()) {
            errors.put("sku", "sku can't be empty!");
        } else if (sku.length() > 10) {
            errors.put("sku", "sku can't be longer than 10 characters!");
        }
        if (dateStr == null || dateStr.isBlank()) {
            errors.put("createdDate", "created date is required!");
        }
        if (price.equals(0)) {
            errors.put("price", "price is required!");
        }
        if (categoryId.equals(0L)) {
            errors.put("category", "category is required!");
        }

        LocalDate date;
        try {
            date = LocalDate.parse(dateStr, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        } catch (Exception e) {
            errors.put("createdDate", "Invalid date format!");
            date = null;
        }

        Long id;
        try {
            id = Long.valueOf(req.getParameter("id"));
        } catch (NumberFormatException e) {
            id = null;
        }
        Product product = new Product();
        product.setId(id);
        product.setName(name);
        product.setPrice(price);
        product.setSku(sku);
        product.setCreatedDate(date);

        Category category = new Category();
        category.setId(categoryId);
        product.setCategory(category);

        if (errors.isEmpty()) {
            service.save(product);
            resp.sendRedirect(req.getContextPath() + "/products");
        } else {
            req.setAttribute("errors", errors);
            req.setAttribute("categories", service.listCategories());
            req.setAttribute("product", product);
            req.setAttribute("title", req.getAttribute("title") + ": Product's Form");

            getServletContext().getRequestDispatcher("/form.jsp").forward(req, resp);
        }
    }
}
