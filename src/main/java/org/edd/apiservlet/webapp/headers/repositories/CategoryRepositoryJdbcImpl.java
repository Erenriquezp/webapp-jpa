package org.edd.apiservlet.webapp.headers.repositories;

import jakarta.inject.Inject;
import org.edd.apiservlet.webapp.headers.configs.MysqlConn;
import org.edd.apiservlet.webapp.headers.configs.Repository;
import org.edd.apiservlet.webapp.headers.models.entities.Category;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@RepositoryJdbc
@Repository
public class CategoryRepositoryJdbcImpl implements CrudRepository<Category> {
    private Connection conn;

    @Inject
    public CategoryRepositoryJdbcImpl(@MysqlConn Connection conn) {
        this.conn = conn;
    }

    @Override
    public List<Category> list() throws SQLException {
        List<Category> categories = new ArrayList<>();
        try (Statement stmt = conn.createStatement();
             var rs = stmt.executeQuery("SELECT * FROM categories")) {
            while (rs.next()) {
                Category c = getCategory(rs);
                categories.add(c);
            }
        }
        return categories;
    }

    @Override
    public Category byId(Long id) throws SQLException {
        Category category = null;
        try (PreparedStatement stst = conn.prepareStatement("SELECT * FROM categories WHERE category_id = ?")) {
            stst.setLong(1, id);
            try (ResultSet rs = stst.executeQuery()) {
                if (rs.next()) {
                    category = getCategory(rs);
                }
            }
        }
        return category;
    }

    @Override
    public void save(Category category) throws SQLException {

    }

    @Override
    public void update(Category category) throws SQLException {

    }

    @Override
    public void delete(Long id) throws SQLException {

    }
    private static Category getCategory(ResultSet rs) throws SQLException {
        Category c = new Category();
        c.setId(rs.getLong("category_id"));
        c.setName(rs.getString("name"));
        return c;
    }
}
