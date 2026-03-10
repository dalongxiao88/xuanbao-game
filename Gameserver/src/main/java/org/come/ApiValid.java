package org.come;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import redis.clients.jedis.Jedis;
import org.come.redis.RedisPoolUntil;
import java.util.UUID;

/**
 * 后台管理接口的参数名与校验值工具。
 *
 * 当前阶段将分散在各个 Servlet 中的硬编码管理口令收敛到统一入口，
 * 并允许通过 `important.properties` 或系统属性覆盖默认值，
 * 以降低后续调整成本并减少重复硬编码。
 */
public class ApiValid
{
    public static final String VALID_NAME = getConfig("security.admin.validName", "wdltxyss");
    public static final String VALID_VALUE = getConfig("security.admin.validValue", "zzswxy2o!@#HH");
    private String token;
    
    public ApiValid() {
        this.token = "";
    }

    /**
     * 读取后台管理校验配置。
     *
     * 读取优先级：系统属性 > `important.properties` > 默认值。
     */
    private static String getConfig(String key, String defaultValue) {
        String systemValue = System.getProperty(key);
        if (systemValue != null && !systemValue.trim().isEmpty()) {
            return systemValue.trim();
        }
        try (InputStream inputStream = ApiValid.class.getClassLoader().getResourceAsStream("important.properties")) {
            if (inputStream != null) {
                Properties properties = new Properties();
                properties.load(inputStream);
                String propertyValue = properties.getProperty(key);
                if (propertyValue != null && !propertyValue.trim().isEmpty()) {
                    return propertyValue.trim();
                }
            }
        }
        catch (IOException ignored) {
        }
        return defaultValue;
    }
    
    public static String getToken(String userName) {
        if (userName == null || userName.trim().isEmpty()) {
            return "";
        }
        String uuid = UUID.randomUUID().toString();
        try (Jedis jedis = RedisPoolUntil.getJedis()) {
            jedis.set("dhToken:" + userName, uuid);
            jedis.expire("dhToken:" + userName, 1800);
        }
        return uuid;
    }
    
    public static boolean vaildToken(String token, String userName) {
        if (token == null || token.trim().isEmpty() || userName == null || userName.trim().isEmpty()) {
            return false;
        }
        try (Jedis jedis = RedisPoolUntil.getJedis()) {
            String redisToken = jedis.get("dhToken:" + userName);
            if (token.equals(redisToken)) {
                jedis.expire("dhToken:" + userName, 1800);
                return true;
            }
        }
        return false;
    }
}
