package com.example.demo;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.data.redis.core.StringRedisTemplate;



@RestController
public class UserController {

   @Autowired
   private JdbcTemplate jdbcTemplate;
   @Autowired
   private StringRedisTemplate redis;

   @GetMapping
   public String hello() {
       return "Spring Boot START PAGE";
   }

   @GetMapping("/mysql")
   public String dbTest() {
       try {
            String sql = "SELECT now()";
            List<Map<String, Object>> result = jdbcTemplate.queryForList(sql);
            return result.toString();
       } catch (Exception e) {
            e.printStackTrace();
            return "Database connection failed! Error: " + e.getMessage();
       }
   }

    @GetMapping("/redis-set")
    public String redisSet() {
        try {
            redis.opsForValue().set("key", "100");
            return "Redis SET OK. key=key, value=100";
        } catch (Exception e) {
            e.printStackTrace();
            return "Redis SET failed! Error: " + e.getMessage();
        }
    }

    @GetMapping("/redis-get")
    public String redisGet() {
        try {
            String value = redis.opsForValue().get("key");
            return "Redis GET OK. key=key >> " + value;
        } catch (Exception e) {
            e.printStackTrace();
            return "Redis GET failed! Error: " + e.getMessage();
        }
    }

   
}
