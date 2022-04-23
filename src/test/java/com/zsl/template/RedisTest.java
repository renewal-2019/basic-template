package com.zsl.template;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.zsl.template.entity.PersonInfo;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@SpringBootTest
// 需要注入bean
@ExtendWith(SpringExtension.class)
public class RedisTest {
    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    private static final ObjectMapper objectMapper = new ObjectMapper();

    @Test
    void test() throws JsonProcessingException {
        PersonInfo personInfo = new PersonInfo();
        personInfo.setAge(20);
        personInfo.setName("zsl");
//        redisTemplate.opsForValue().set("asd",personInfo );
//        Object aTry = redisTemplate.opsForValue().get("asd");
//        System.out.println();

        stringRedisTemplate.opsForValue().set("try",objectMapper.writeValueAsString(personInfo));
        String aTry = stringRedisTemplate.opsForValue().get("try");
        PersonInfo personInfo1 = objectMapper.readValue(aTry, PersonInfo.class);
        System.out.println(personInfo1.getName());
    }
}
