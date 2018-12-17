package com.roger.redis.monitor;

import lombok.extern.slf4j.Slf4j;
import redis.clients.jedis.JedisMonitor;

@Slf4j
public class RedisMonitor extends JedisMonitor {

    @Override
    public void onCommand(String command) {
        log.info("redis执行的命令是：" + command);
    }
}
