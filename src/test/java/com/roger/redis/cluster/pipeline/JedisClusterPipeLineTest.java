package com.roger.redis.cluster.pipeline;

import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class JedisClusterPipeLineTest {

    private JedisClusterPipeLine jedisClusterPipeLine;

    @Before
    public void setUp(){
        jedisClusterPipeLine = new JedisClusterPipeLine();
    }

    @Test
    public void testJedisClusterPipeLine() {
        long startTime = System.currentTimeMillis();
        //获取最新的集群节点信息
        jedisClusterPipeLine.refreshClusterInfo();

        List<Object> batchResult = null;

        try {
            // batch write
            for (int i = 0; i < 10000; i++) {
                jedisClusterPipeLine.set("k" + i, "v1" + i);
            }
            jedisClusterPipeLine.sync();

            // batch read
            for (int i = 0; i < 10000; i++) {
                jedisClusterPipeLine.get("k" + i);
            }
            batchResult = jedisClusterPipeLine.syncAndReturnAll();
        } finally {
            jedisClusterPipeLine.close();
        }

        long endTime = System.currentTimeMillis();

        System.out.println("总共耗时：" + (endTime - startTime) + "ms; " + (endTime-startTime)/1000 + "s");
        System.out.println("结果集数量" + batchResult.size());
    }
}