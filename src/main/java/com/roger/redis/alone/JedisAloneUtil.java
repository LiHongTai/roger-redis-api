package com.roger.redis.alone;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

public class JedisAloneUtil {

    public static JedisPool jedisPool;

    private static class SingltonJedisPool{

        private static JedisPool INSTANCE = initJedisPool();

        private static JedisPool initJedisPool(){
            JedisPool jedisPool = new JedisPool(initJedisPoolConfig(),"172.20.10.121",6379,3000,"roger");
            return jedisPool;
        }

        private static JedisPoolConfig initJedisPoolConfig(){
            JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
            //jedis连接池中最大的连接个数
            jedisPoolConfig.setMaxTotal(60);
            //jedis连接池中最大的空闲连接个数
            jedisPoolConfig.setMaxIdle(30);
            //jedis连接池中最小的空闲连接个数
            jedisPoolConfig.setMinIdle(5);
            //jedis连接池最大的等待连接时间 ms值
            jedisPoolConfig.setMaxWaitMillis(3000);

            return jedisPoolConfig;
        }
    }

    public static JedisPool getJedisPool(){
        return SingltonJedisPool.INSTANCE;
    }

    public static void returnResource(Jedis jedis){
        if(jedis != null){
            jedis.close();
        }
    }

}
