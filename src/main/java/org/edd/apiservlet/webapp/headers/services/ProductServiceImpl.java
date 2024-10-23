package org.edd.apiservlet.webapp.headers.services;

import jakarta.inject.Inject;
import org.edd.apiservlet.webapp.headers.configs.ProductServiceMain;
import org.edd.apiservlet.webapp.headers.configs.Service;
import org.edd.apiservlet.webapp.headers.interceptors.TransactionalJpa;
import org.edd.apiservlet.webapp.headers.models.entities.Category;
import org.edd.apiservlet.webapp.headers.models.entities.Product;
import org.edd.apiservlet.webapp.headers.repositories.CrudRepository;
import org.edd.apiservlet.webapp.headers.repositories.RepositoryJpa;

import java.util.List;
import java.util.Optional;

@Service
@ProductServiceMain
@TransactionalJpa
public class ProductServiceImpl implements ProductService {
    @Inject
    @RepositoryJpa
    private CrudRepository<Product> repositoryJdbc;
    @Inject
    @RepositoryJpa
    private CrudRepository<Category> categoryRepositoryJdbc;

    @Override
    public List<Product> list() {
        try {
            return repositoryJdbc.list();
        } catch (Exception e) {
            throw new ServiceJdbcException(e.getMessage(), e.getCause());
        }
    }

    @Override
    public Optional<Product> findById(Long id) {
        try {
            return Optional.ofNullable(repositoryJdbc.byId(id));
        } catch (Exception e) {
            throw new ServiceJdbcException(e.getMessage(), e.getCause());
        }
    }

    @Override
    public void save(Product product) {
        try {
            repositoryJdbc.save(product);
        } catch (Exception e) {
            throw new ServiceJdbcException(e.getMessage(), e.getCause());
        }
    }

    @Override
    public void delete(Long id) {
        try {
            repositoryJdbc.delete(id);
        } catch (Exception e) {
            throw new ServiceJdbcException(e.getMessage(), e.getCause());
        }
    }

    @Override
    public void update(Product product) {

    }

    @Override
    public List<Category> listCategories() {
        try {
            return categoryRepositoryJdbc.list();
        } catch (Exception e) {
            throw new ServiceJdbcException(e.getMessage(), e.getCause());
        }
    }

    @Override
    public Optional<Category> findCategoryById(Long id) {
        try {
            return Optional.ofNullable(categoryRepositoryJdbc.byId(id));
        } catch (Exception e) {
            throw new ServiceJdbcException(e.getMessage(), e.getCause());
        }
    }
}
