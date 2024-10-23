package org.edd.apiservlet.webapp.headers.repositories;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import jakarta.inject.Inject;
import org.edd.apiservlet.webapp.headers.configs.MysqlConn;
import org.edd.apiservlet.webapp.headers.configs.Repository;
import org.edd.apiservlet.webapp.headers.models.entities.Category;
import org.edd.apiservlet.webapp.headers.models.entities.Product;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

@Repository
@RepositoryJdbc
public class ProductRepositoryJdbcImpl implements CrudRepository<Product> {
    @Inject
    private Logger log;
    @Inject
    @MysqlConn
    private Connection conn;

    @PostConstruct
    public void initialize() {
        log.info("Initializing beans " + this.getClass().getName());
    }
    @PreDestroy
    public void destroy() {
        log.info("Destroying beans " + this.getClass().getName());
    }
    @Override
    public List<Product> list() throws SQLException {
        List<Product> products = new ArrayList<>();

        try (Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT p.*, c.name as category FROM products as p" +
                    " Inner join categories as c ON (p.category_id = c.category_id) order by p.id ASC")) {
            while (rs.next()) {
                Product p = getProduct(rs);

                products.add(p);
            }
        }
        return products;
    }

    @Override
    public Product byId(Long id) throws SQLException {
        Product product = null;
        try (PreparedStatement stmt = conn.prepareStatement("SELECT p.*, c.name as category FROM products as p" +
                " Inner join categories as c ON (p.category_id) WHERE p.id = ?")) {
            stmt.setLong(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    product = getProduct(rs);
                }
            }
        }
        return product;
    }

    @Override
    public void save(Product product) throws SQLException {

        String sql;
        if (product.getId() != null && product.getId() > 0) {
            sql = "update products set name=?, price=?, sku=?, category_id=? where id=?";
        } else {
            sql = "insert into products (name, price, sku, category_id, created_date) values (?, ?, ?, ?, ?)";
        }
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, product.getName());
            stmt.setInt(2, product.getPrice());
            stmt.setString(3, product.getSku());
            stmt.setLong(4, product.getCategory().getId());
            if (product.getId() != null && product.getId() > 0) {
                stmt.setLong(5, product.getId());
            } else {
                stmt.setDate(5, Date.valueOf(product.getCreatedDate()));
            }
            stmt.executeUpdate();
        }

    }

    @Override
    public void update(Product product) throws SQLException {
        save(product);
    }

    @Override
    public void delete(Long id) throws SQLException {
        String sql = "delete from products where id=?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setLong(1, id);
            stmt.executeUpdate();
        }
    }
    private static Product getProduct(ResultSet rs) throws SQLException {
        Product p = new Product();
        p.setId(rs.getLong("id"));
        p.setName(rs.getString("name"));
        p.setPrice(rs.getInt("price"));
        p.setSku(rs.getString("sku"));
        p.setCreatedDate(rs.getDate("created_date").toLocalDate());
        Category c = new Category();
        c.setId(rs.getLong("category_id"));
        c.setName(rs.getString("category"));
        p.setCategory(c);
        return p;
    }
}
