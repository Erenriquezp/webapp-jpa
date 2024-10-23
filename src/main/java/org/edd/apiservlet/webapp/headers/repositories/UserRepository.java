package org.edd.apiservlet.webapp.headers.repositories;

import org.edd.apiservlet.webapp.headers.models.entities.User;

public interface UserRepository extends CrudRepository<User> {
    User byUsername(String username) throws Exception;

}
