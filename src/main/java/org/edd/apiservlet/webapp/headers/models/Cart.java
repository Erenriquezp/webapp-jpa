package org.edd.apiservlet.webapp.headers.models;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import jakarta.inject.Inject;
import org.edd.apiservlet.webapp.headers.configs.ShoppingCart;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

@ShoppingCart
public class Cart implements Serializable {
    private List<ItemCart> items;

    @Inject
    private transient Logger log;
    @PostConstruct
    public void initialize(){
        this.items = new ArrayList<>();
        log.info("Initializing cart");
    }
    @PreDestroy
    public void destroy(){
        log.info("Destroying cart");
    }
    public List<ItemCart> getItems() {
        return items;
    }
    public void addItem(ItemCart item){
        if (items.contains(item)) {
            //items.get(items.indexOf(item)).setCantidad(items.get(items.indexOf(item)).getCantidad() + item.getCantidad());
            Optional<ItemCart> optionalItem = items.stream().filter(i -> i.equals(item)).findAny();
            if (optionalItem.isPresent()) {
                ItemCart i = optionalItem.get();
                i.setCantidad(i.getCantidad() + 1);
            }
        } else {
            items.add(item);
        }
    }
    public int getTotal(){
        return items.stream().mapToInt(ItemCart::getImporte).sum();
    }

    public void removeProducts(Long id, int cantidad) {
    }
}
