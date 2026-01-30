package org.come.redis;

import org.come.tool.WriteOut;
import org.come.handler.MainServerHandler;
import redis.clients.jedis.Jedis;
import org.come.server.GameServer;
import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.JedisPool;

public class RedisPoolUntil
{
    private static int MAX_ACTIVE;
    private static int MAX_IDLE;
    private static int MAX_WAIT;
    private static boolean TEST_ON_BORROW;
    private static JedisPool jedisPool;
    
    public static void init() {
        try {
            JedisPoolConfig config = new JedisPoolConfig();
            config.setMaxTotal(RedisPoolUntil.MAX_ACTIVE);
            config.setMaxIdle(RedisPoolUntil.MAX_IDLE);
            config.setMaxWaitMillis((long)RedisPoolUntil.MAX_WAIT);
            config.setTestOnBorrow(RedisPoolUntil.TEST_ON_BORROW);
            RedisPoolUntil.jedisPool = new JedisPool(config, GameServer.redisIp, GameServer.redisPort, 5000);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public static synchronized Jedis getJedis() {
        try {
            return RedisPoolUntil.jedisPool.getResource();
        }
        catch (Exception e) {
            WriteOut.addtxt("获取不到REDIS连接:" + MainServerHandler.getErrorMessage(e), 9999L);
            e.printStackTrace();
            try {
                Thread.sleep(10L);
            }
            catch (InterruptedException e2) {
                e2.printStackTrace();
            }
            return RedisPoolUntil.jedisPool.getResource();
        }
    }
    
    public static void returnResource(Jedis jedis) {
        if (jedis != null) {
            RedisPoolUntil.jedisPool.returnResourceObject(jedis);
        }
    }
    
    static {
        RedisPoolUntil.MAX_ACTIVE = -1;
        RedisPoolUntil.MAX_IDLE = 4096;
        RedisPoolUntil.MAX_WAIT = 60000;
        RedisPoolUntil.TEST_ON_BORROW = true;
        RedisPoolUntil.jedisPool = null;
        init();
    }
}
