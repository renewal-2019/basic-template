package com.zsl.template.util;

import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;

@Slf4j
@Component
public class RedisCacheUtil {
    private static final Long CACHE_NULL_TTL = 1L;

    private final StringRedisTemplate stringRedisTemplate;


    public RedisCacheUtil(StringRedisTemplate stringRedisTemplate) {
        this.stringRedisTemplate = stringRedisTemplate;
    }

    public <T> void set(String key, T value, Long time, TimeUnit unit) {
        stringRedisTemplate.opsForValue().set(key, JSONUtil.toJsonStr(value), time, unit);
    }

    public <T> void setWithLogicalExpire(String key, T value, Long time, TimeUnit unit) {
        RedisData redisData = new RedisData();
        redisData.setData(value);
        redisData.setExpireTime(LocalDateTime.now().plusSeconds(unit.toSeconds(time)));
        stringRedisTemplate.opsForValue().set(key, JSONUtil.toJsonStr(redisData));
    }

    public <R, ID> R queryWithPassThrough(String keyPrefix, ID id, Class<R> type, Function<ID, R> dbCallback, Long time, TimeUnit unit) {
        String key = keyPrefix + id;
        // 从redis中查询缓存数据
        String json = stringRedisTemplate.opsForValue().get(key);
        // 缓存存在直接返回，不存在就查询数据库
        if (StrUtil.isNotBlank(json)) {
            return JSONUtil.toBean(json, type);
        }
        if (json != null) { // 此时json是“”
            return null;
        }
        // 查询数据库
        R apply = dbCallback.apply(id);
        if (apply == null) {
            stringRedisTemplate.opsForValue().set(key, "", CACHE_NULL_TTL, TimeUnit.MINUTES);
            return null;
        }
        set(key, apply, time, unit);
        return apply;
    }
}
