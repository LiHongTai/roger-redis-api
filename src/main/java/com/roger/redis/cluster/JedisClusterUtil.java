package com.roger.redis.cluster;

import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.JedisCluster;
import redis.clients.jedis.JedisPoolConfig;

import java.util.HashSet;
import java.util.Set;

public class JedisClusterUtil {


    public static JedisCluster getJedisClusterInstance(){
        return SingletonJedisCluster.INSTANCE;
    }

    private static class SingletonJedisCluster{

        private static JedisCluster INSTANCE = initJedisCluster();

       public static JedisCluster initJedisCluster(){
           //redis 节点信息
           Set<HostAndPort> clusterNodes = new HashSet<>();

           initClusterNodes(clusterNodes,"192.168.1.11:8001");
           initClusterNodes(clusterNodes,"192.168.1.11:8004");

           initClusterNodes(clusterNodes,"192.168.1.12:8002");
           initClusterNodes(clusterNodes,"192.168.1.12:8005");

           initClusterNodes(clusterNodes,"192.168.1.13:8003");
           initClusterNodes(clusterNodes,"192.168.1.13:8006");


           JedisCluster jedisCluster = new JedisCluster(clusterNodes,3000,3000,10,"roger",initJedisPoolConfig());
           return jedisCluster;
       }
    }

    private static void initClusterNodes(Set<HostAndPort> clusterNodes,String hostAndPortStr){
        HostAndPort hostAndPort = HostAndPort.parseString(hostAndPortStr);
        clusterNodes.add(hostAndPort);
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
