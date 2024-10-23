package org.edd.apiservlet.webapp.headers.services;

import jakarta.inject.Inject;
import org.edd.apiservlet.webapp.headers.configs.Service;
import org.edd.apiservlet.webapp.headers.interceptors.TransactionalJpa;
import org.edd.apiservlet.webapp.headers.models.entities.User;
import org.edd.apiservlet.webapp.headers.repositories.RepositoryJpa;
import org.edd.apiservlet.webapp.headers.repositories.UserRepository;

import java.util.Optional;

@Service
@TransactionalJpa
public class UserServiceImpl implements UserService {
    private UserRepository userRepository;

    @Inject
    public UserServiceImpl(@RepositoryJpa UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public Optional<User> login(String username, String password) {
        try {
            return Optional.ofNullable(userRepository.byUsername(username))
                    .filter(user -> user.getPassword().equals(password));
        } catch (Exception e) {
            throw new ServiceJdbcException(e.getMessage(), e.getCause());
        }
    }
}
