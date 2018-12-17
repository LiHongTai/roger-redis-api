package com.roger.redis.cluster;

import org.junit.Before;
import org.junit.Test;
import redis.clients.jedis.JedisCluster;

import java.util.List;

public class JedisClusterUtilTest {

    private JedisCluster jedisCluster;

    @Before
    public void setUp() {
        jedisCluster = JedisClusterUtil.getJedisClusterInstance();
    }

    @Test
    public void testJedisClusterPipeLine() {
        long startTime = System.currentTimeMillis();

        List<Object> batchResult = null;

        // batch write
        for (int i = 0; i < 10000; i++) {
            jedisCluster.set("k" + i, "v1" + i);
        }

        // batch read
        for (int i = 0; i < 10000; i++) {
            batchResult.add(jedisCluster.get("k" + i));
        }


        long endTime = System.currentTimeMillis();

        System.out.println("总共耗时：" + (endTime - startTime) + "ms; " + (endTime - startTime) / 1000 + "s");
        System.out.println("结果集数量" + batchResult.size());
    }
}