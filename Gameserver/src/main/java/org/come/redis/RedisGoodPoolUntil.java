package org.come.redis;

import redis.clients.jedis.Jedis;
import org.come.server.GameServer;
import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.JedisPool;

public class RedisGoodPoolUntil
{
    private static int MAX_ACTIVE;
    private static int MAX_IDLE;
    private static int MAX_WAIT;
    private static boolean TEST_ON_BORROW;
    private static JedisPool jedisPool;
    
    public static void init() {
        try {
            JedisPoolConfig config = new JedisPoolConfig();
            config.setMaxTotal(RedisGoodPoolUntil.MAX_ACTIVE);
            config.setMaxIdle(RedisGoodPoolUntil.MAX_IDLE);
            config.setMaxWaitMillis(RedisGoodPoolUntil.MAX_WAIT);
            config.setTestOnBorrow(RedisGoodPoolUntil.TEST_ON_BORROW);
            RedisGoodPoolUntil.jedisPool = new JedisPool(config, GameServer.redisIp, GameServer.redisPort);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public static synchronized Jedis getJedis() {
        try {
            if (RedisGoodPoolUntil.jedisPool != null) {
                Jedis resource = RedisGoodPoolUntil.jedisPool.getResource();
                return resource;
            }
            init();
            Jedis resource = RedisGoodPoolUntil.jedisPool.getResource();
            return resource;
        }
        catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    
    public static void returnResource(Jedis jedis) {
        if (jedis != null) {
            RedisGoodPoolUntil.jedisPool.returnResourceObject(jedis);
        }
    }
    
    static {
        RedisGoodPoolUntil.MAX_ACTIVE = -1;
        RedisGoodPoolUntil.MAX_IDLE = 256;
        RedisGoodPoolUntil.MAX_WAIT = 60000;
        RedisGoodPoolUntil.TEST_ON_BORROW = true;
        RedisGoodPoolUntil.jedisPool = null;
    }
}
