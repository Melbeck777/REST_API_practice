package com.booksite.util;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DbUtils {

    private String url;
    private String username;
    private String password;

    public DbUtils(Map<String, Object> config) {
        this.url = (String) config.get("url");
        this.username = (String) config.get("username");
        this.password = (String) config.get("password");
    }

    public List<Map<String, Object>> readAll(String query) {
        List<Map<String, Object>> list = new ArrayList<>();
        try (Connection connection = DriverManager.getConnection(url, username, password);
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {
            ResultSetMetaData metaData = resultSet.getMetaData();
            int columnCount = metaData.getColumnCount();
            while (resultSet.next()) {
                Map<String, Object> row = new HashMap<>();
                for (int i = 1; i <= columnCount; i++) {
                    String columnName = metaData.getColumnName(i).toLowerCase();
                    row.put(columnName, resultSet.getObject(i));
                }
                list.add(row);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return list;
    }
}
