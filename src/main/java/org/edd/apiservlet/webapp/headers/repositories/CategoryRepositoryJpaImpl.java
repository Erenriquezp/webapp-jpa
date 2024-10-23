package org.edd.apiservlet.webapp.headers.repositories;

import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import org.edd.apiservlet.webapp.headers.configs.Repository;
import org.edd.apiservlet.webapp.headers.models.entities.Category;

import java.util.List;

@RepositoryJpa
@Repository
public class CategoryRepositoryJpaImpl implements CrudRepository<Category> {
    @Inject
    private EntityManager em;

    @Override
    public List<Category> list() throws Exception {
        return em.createQuery("FROM Category", Category.class).getResultList();
    }

    @Override
    public Category byId(Long id) throws Exception {
        return em.find(Category.class, id);
    }

    @Override
    public void save(Category category) throws Exception {
        if (category.getId() != null && category.getId() > 0) {
            em.merge(category);
        } else {
            em.persist(category);
        }
    }

    @Override
    public void update(Category category) throws Exception {
        this.save(category);
    }

    @Override
    public void delete(Long id) throws Exception {
        em.remove(this.byId(id));
    }
}
