package ro.foodwise.service;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Map;

@Service
public class DatabaseDemoService {
    private final JdbcTemplate jdbc;

    public DatabaseDemoService(JdbcTemplate jdbc) { this.jdbc = jdbc; }

    public Map<String, Object> snapshot() {
        Integer count = jdbc.queryForObject("select count(*) from product", Integer.class);
        List<String> products = jdbc.query("select name from product order by name", (rs, row) -> rs.getString("name"));
        return Map.of("database", "H2", "connection", "JDBC", "productCount", count, "products", products);
    }
}
