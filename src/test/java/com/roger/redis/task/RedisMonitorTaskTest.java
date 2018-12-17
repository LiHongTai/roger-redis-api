package com.roger.redis.task;

import com.roger.redis.alone.JedisAloneUtil;
import org.junit.Test;

import java.util.concurrent.TimeUnit;

import static org.junit.Assert.*;

public class RedisMonitorTaskTest {

    @Test
    public void testRun() throws Exception{

        Thread thread = new Thread(
                new RedisMonitorTask(
                        JedisAloneUtil.getJedisPool().getResource()));
        thread.start();
        TimeUnit.SECONDS.sleep(Integer.MAX_VALUE);
    }
}