package com.roger.redis.alone;

import org.junit.Test;
import redis.clients.jedis.JedisPool;

import static org.junit.Assert.*;

public class JedisAloneUtilTest {

    @Test
    public void testGetJedisPool() {

        JedisPool jedisPool = JedisAloneUtil.getJedisPool();

        JedisPool jedisPool2 = JedisAloneUtil.getJedisPool();

        assertTrue(jedisPool == jedisPool2);
    }
}