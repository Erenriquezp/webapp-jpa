package org.edd.apiservlet.webapp.headers.controllers;

import jakarta.inject.Inject;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.edd.apiservlet.webapp.headers.models.Cart;

import java.io.IOException;

@WebServlet("/cart/update")
public class UpdateCartServlet extends HttpServlet {

    @Inject
    private Cart cart;
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        updateProducts(req, cart);
        resp.sendRedirect(req.getContextPath() + "/cart/see");
    }
    private void updateProducts(HttpServletRequest request, Cart cart) {
        String[] ids = request.getParameterValues("id");
        String[] cantidades = request.getParameterValues("cantidad");
        for (int i = 0; i < ids.length; i++) {
            Long id = Long.parseLong(ids[i]);
            int cantidad = Integer.parseInt(cantidades[i]);
            cart.removeProducts(id, cantidad);
        }
    }
}
