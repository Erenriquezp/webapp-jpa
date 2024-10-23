package org.edd.apiservlet.webapp.headers.repositories;

import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import org.edd.apiservlet.webapp.headers.configs.Repository;
import org.edd.apiservlet.webapp.headers.models.entities.User;

import java.util.List;

@RepositoryJpa
@Repository
public class UserRepositoryJpaImpl implements UserRepository{
    @Inject
    private EntityManager em;

    @Override
    public List<User> list() throws Exception {
        return em.createQuery("FROM User", User.class).getResultList();
    }

    @Override
    public User byId(Long id) throws Exception {
        return em.find(User.class, id);
    }

    @Override
    public void save(User user) throws Exception {
        if (user.getId() != null && user.getId() > 0) {
            em.merge(user);
        } else {
            em.persist(user);
        }
    }

    @Override
    public void update(User user) throws Exception {

    }

    @Override
    public void delete(Long id) throws Exception {

    }

    @Override
    public User byUsername(String username) throws Exception {
        return em.createQuery("FROM User u WHERE u.username = :username", User.class)
                .setParameter("username", username)
                .getSingleResult();
    }
}
