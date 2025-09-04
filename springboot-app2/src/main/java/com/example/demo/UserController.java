package com.example.demo;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestParam;



@RestController
public class UserController {

   @Autowired
   private JdbcTemplate jdbcTemplate;

   @GetMapping()
   public String home() {
       return "home";
   }
   

   @GetMapping("/hello")
   public String hello() {
       return "Hello from Spring Boot!";
   }

   @GetMapping("/db-test")
   public String dbTest() {
       try {
           String sql = "SELECT 1 + 1";
           Integer result = jdbcTemplate.queryForObject(sql, Integer.class);
           return "Database test successful. The result of '1 + 1' is: " + result;
       } catch (Exception e) {
           e.printStackTrace();
           return "Database connection failed! Error: " + e.getMessage();
       }
   }

   @GetMapping("/db-test-all")
   public String dbTestAll() {
       try {
            String sql = "SELECT * from test";
            List<Map<String, Object>> rows = jdbcTemplate.queryForList(sql);
            return rows.toString();
       } catch (Exception e) {
           e.printStackTrace();
           return "Database connection failed! Error: " + e.getMessage();
       }
   }
   
}
