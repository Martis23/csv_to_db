package com.example.csv_to_db.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class MyTableService {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Value("${spring.datasource.username}")
    private String user;

    @Value("${spring.datasource.password}")
    private String pass;

    public List<Map<String, Object>> getAllRows(String csv_file_db) {
        String sql = "SELECT * FROM "+csv_file_db;
        return jdbcTemplate.queryForList(sql);
    }
}
