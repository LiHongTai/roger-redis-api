package com.roger.redis.task;

import com.roger.redis.monitor.RedisMonitor;
import redis.clients.jedis.Jedis;

/**
 * 监控Redis执行什么命令的任务
 */
public class RedisMonitorTask implements Runnable{

    private Jedis jedisMonitor;

    public RedisMonitorTask(Jedis jedisMonitor) {
        this.jedisMonitor = jedisMonitor;
    }

    @Override
    public void run() {
        this.jedisMonitor.monitor(new RedisMonitor());
    }
}
