package com.redis.demo.controller;

import io.lettuce.core.RedisClient;
import io.lettuce.core.RedisURI;
import io.lettuce.core.api.StatefulRedisConnection;
import io.lettuce.core.api.sync.RedisCommands;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class RedisController {

    private RedisClient redisClient;

    @Autowired
    public RedisController(RedisClient redisClient) {
        this.redisClient = redisClient;
    }

    @GetMapping("/")
    public String hello() {
        return "hello";
    }

    @GetMapping("/redis")
    public String connectBasic() {
        StatefulRedisConnection<String, String> connection = redisClient.connect();
        RedisCommands<String, String> commands = connection.sync();
        commands.set("foo", "bar");
        String result = commands.get("foo");
        System.out.println(result); // >>> bar

        Map<String, String> map = new HashMap<>();
        map.put("name", "jon doe");
        map.put("age", "22");
        map.put("city", "chicago");
        JSONObject jo = new JSONObject(map);
        commands.set("json", String.valueOf(jo));
        result = commands.get("json");

        connection.close();
        redisClient.shutdown();
        return result;
    }


}
