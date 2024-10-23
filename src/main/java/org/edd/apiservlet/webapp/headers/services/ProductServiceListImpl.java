package org.edd.apiservlet.webapp.headers.services;

import org.edd.apiservlet.webapp.headers.models.entities.Category;
import org.edd.apiservlet.webapp.headers.models.entities.Product;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

//@Alternative
public class ProductServiceListImpl implements ProductService {

    @Override
    public List<Product> list() {
        return Arrays.asList(new Product(1L, "notebook", "computing", 175000),
                new Product(2L, "desk", "office", 10000),
                new Product(3L, "mechanical keyboard", "computing", 4000));
    }

    @Override
    public Optional<Product> findById(Long id) {
        return list().stream().filter(p -> p.getId().equals(id)).findAny();
    }

    @Override
    public void save(Product product) {

    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public void update(Product product) {

    }

    @Override
    public List<Category> listCategories() {
        return null;
    }

    @Override
    public Optional<Category> findCategoryById(Long id) {
        return Optional.empty();
    }
}
