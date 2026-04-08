package client;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component
public class DbClient {
    private final JdbcTemplate jdbcTemplate;

    public DbClient(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public Map<String, Object> selectOne(String sql, Object... params) {
        return jdbcTemplate.queryForMap(sql, params);
    }

    public List<Map<String, Object>> selectList(String sql, Object... params) {
        return jdbcTemplate.queryForList(sql, params);
    }

    public int selectCount(String sql, Object... params) {
        Integer count = jdbcTemplate.queryForObject(sql, Integer.class, params);
        return count == null ? 0 : count;
    }
}
