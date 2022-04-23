package com.zsl.template;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@SpringBootTest
// 需要注入bean
@ExtendWith(SpringExtension.class)
public class RedisTest {
    @Autowired
    private RedisTemplate redisTemplate;

    @Test
    void test() {
        redisTemplate.opsForValue().set("try", "666");
        Object aTry = redisTemplate.opsForValue().get("try");
        System.out.println((String) aTry);
    }
}
