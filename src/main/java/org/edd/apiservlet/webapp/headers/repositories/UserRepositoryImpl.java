package org.edd.apiservlet.webapp.headers.repositories;

import jakarta.inject.Inject;
import org.edd.apiservlet.webapp.headers.configs.MysqlConn;
import org.edd.apiservlet.webapp.headers.configs.Repository;
import org.edd.apiservlet.webapp.headers.models.entities.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
@RepositoryJdbc
public class UserRepositoryImpl implements UserRepository {

    @Inject
    @MysqlConn
    private Connection conn;

    @Override
    public List<User> list() throws SQLException {
        return null;
    }

    @Override
    public User byId(Long id) throws SQLException {
        return null;
    }

    @Override
    public void save(User user) throws SQLException {

    }

    @Override
    public void update(User user) throws SQLException {

    }

    @Override
    public void delete(Long id) throws SQLException {

    }

    @Override
    public User byUsername(String username) throws Exception {
        User user = null;
        try (PreparedStatement stmt = conn.prepareStatement("SELECT * FROM users WHERE username=?")) {
            stmt.setString(1, username);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    user = new User();
                    user.setId(rs.getLong("id"));
                    user.setUsername(rs.getString("username"));
                    user.setPassword(rs.getString("password"));
                    user.setEmail(rs.getString("email"));
                }
            }
        }
        return user;
    }
}
