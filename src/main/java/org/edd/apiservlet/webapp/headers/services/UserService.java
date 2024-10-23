package org.edd.apiservlet.webapp.headers.services;

import org.edd.apiservlet.webapp.headers.models.entities.User;

import java.util.Optional;

public interface UserService {
    Optional<User> login(String username, String password);
}
