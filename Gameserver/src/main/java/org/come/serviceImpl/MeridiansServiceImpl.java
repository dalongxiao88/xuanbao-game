package org.come.serviceImpl;

import redis.clients.jedis.Jedis;
import org.come.redis.RedisPoolUntil;
import org.come.service.MeridiansService;

public class MeridiansServiceImpl implements MeridiansService
{
    static String meridians_key;
    
    @Override
    public String selectMeridians(Long roleid) {
        Jedis jedis = RedisPoolUntil.getJedis();
        try {
            return jedis.hget(MeridiansServiceImpl.meridians_key, roleid + "");
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            RedisPoolUntil.returnResource(jedis);
        }
        return null;
    }
    
    @Override
    public void saveMeridians(Long roleid, String meridians) {
        Jedis jedis = RedisPoolUntil.getJedis();
        try {
            jedis.hset(MeridiansServiceImpl.meridians_key, roleid + "", meridians);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            RedisPoolUntil.returnResource(jedis);
        }
    }
    
    static {
        MeridiansServiceImpl.meridians_key = "meridians";
    }
}
