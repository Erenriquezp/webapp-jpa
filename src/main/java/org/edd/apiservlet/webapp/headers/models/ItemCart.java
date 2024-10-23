package org.edd.apiservlet.webapp.headers.models;

import org.edd.apiservlet.webapp.headers.models.entities.Product;

import java.util.Objects;

public class ItemCart {
    private int cantidad;
    private Product product;

    public ItemCart(int cantidad, Product product) {
        this.cantidad = cantidad;
        this.product = product;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }
    public int getImporte(){
        return this.cantidad * this.product.getPrice();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ItemCart itemCart = (ItemCart) o;
        return Objects.equals(product.getId(), itemCart.product.getId())
                && Objects.equals(product.getName(), itemCart.product.getName());
    }

}
