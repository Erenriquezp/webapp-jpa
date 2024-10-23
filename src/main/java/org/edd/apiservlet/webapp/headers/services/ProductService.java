package org.edd.apiservlet.webapp.headers.services;

import org.edd.apiservlet.webapp.headers.models.entities.Category;
import org.edd.apiservlet.webapp.headers.models.entities.Product;

import java.util.List;
import java.util.Optional;

public interface ProductService {
    List<Product> list();
    Optional<Product> findById(Long id);
    void save(Product product);
    void delete(Long id);
    void update(Product product);
    List<Category> listCategories();
    Optional<Category> findCategoryById(Long id);
}
