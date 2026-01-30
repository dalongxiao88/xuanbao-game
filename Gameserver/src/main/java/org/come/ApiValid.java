package org.come;

import redis.clients.jedis.Jedis;
import org.come.redis.RedisPoolUntil;
import java.util.UUID;

public class ApiValid
{
    public static final String VALID_NAME = "wdltxyss";
    public static final String VALID_VALUE = "zzswxy2o!@#HH";
    private String token;
    
    public ApiValid() {
        this.token = "";
    }
    
    public static String getToken(String userName) {
        String uuid = UUID.randomUUID().toString();
        Jedis jedis = RedisPoolUntil.getJedis();
        jedis.set("dhToken:" + userName, uuid);
        jedis.expire("dhToken:" + userName, 1800);
        return uuid;
    }
    
    public static boolean vaildToken(String token, String userName) {
        Jedis jedis = RedisPoolUntil.getJedis();
        if (jedis.get("dhToken:" + userName).equals(token)) {
            jedis.expire("dhToken:" + userName, 1800);
            return true;
        }
        return false;
    }
}
