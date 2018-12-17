package com.roger.redis.alone.api;

import com.roger.redis.alone.JedisAloneUtil;
import org.junit.Before;
import org.junit.Test;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import static org.junit.Assert.*;

public class JedisApiTest {

    private JedisPool jedisPool;

    @Before
    public void setUp(){
        jedisPool = JedisAloneUtil.getJedisPool();
    }

    @Test
    public void testString(){
        Jedis jedis = jedisPool.getResource();
        jedis.flushDB();
        //添加key的值
        jedis.set("name","Mar");
        //在原有的key的值上追加内容,返回当前key追加后的值的字符串长度
        System.out.println(jedis.append("name","y"));
        //如果key不存在时，追加即相当于set命令
        System.out.println(jedis.append("x","exists"));

        jedis.flushDB();

        JedisAloneUtil.returnResource(jedis);

    }

}