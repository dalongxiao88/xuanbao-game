package org.come.redis;

import java.math.BigDecimal;
import java.util.Objects;
import redis.clients.jedis.ScanResult;
import redis.clients.jedis.ScanParams;
import com.gl.controller.UserIp;
import cn.hutool.core.text.CharSequenceUtil;
import com.gl.controller.PageIpInfo;
import java.util.Iterator;
import java.util.Map;
import org.come.entity.PackRecord;
import org.come.bean.LoginResult;
import java.util.Set;
import java.util.ArrayList;
import java.util.List;
import redis.clients.jedis.Jedis;
import org.come.until.GsonUtil;

public class RedisControl
{
    public static final String CADD = "1";
    public static final String CALTER = "2";
    public static final String CDELETE = "3";
    
    public static <T> void insertKeyT(String key, String filed, T t) {
        RedisCacheBean<T> cacheBean = (RedisCacheBean<T>)RedisCacheUtil.getRedisCacheBean(key);
        if (cacheBean != null) {
            cacheBean.addCache(filed, t);
        }
        Jedis jedis = RedisPoolUntil.getJedis();
        jedis.hset(key, filed, GsonUtil.getGsonUtil().getgson().toJson(t));
        RedisPoolUntil.returnResource(jedis);
    }
    public static <T> String getVStr(String key, String field, Class<T> classOfT) {//封IP
        RedisCacheBean<String> cacheBean=(RedisCacheBean<String>) RedisCacheUtil.getRedisCacheBean(key);
        if (cacheBean!=null) {
            String t=cacheBean.getCache(field);
            if (t!=null) {return t;}
        }
        Jedis jedis = RedisPoolUntil.getJedis();// 获取连接资源
        String a = jedis.hget(key, field);// 获取返回的字符串
        RedisPoolUntil.returnResource(jedis);// 返回使用的连接
        return a;
    }
    public static void insertKey(String key, String filed, String value) {
        Jedis jedis = RedisPoolUntil.getJedis();
        jedis.hset(key, filed, value);
        RedisPoolUntil.returnResource(jedis);
    }
    
    public static <T> Long delForKey(String key, String value) {
        RedisCacheBean<T> cacheBean = (RedisCacheBean<T>)RedisCacheUtil.getRedisCacheBean(key);
        if (cacheBean != null) {
            cacheBean.delCache(value);
        }
        Jedis jedis = RedisPoolUntil.getJedis();
        Long length = jedis.hdel(key, new String[] { value });
        RedisPoolUntil.returnResource(jedis);
        return length;
    }
    
    public static Long insertListRedis(String key, String id, String value) {
        Jedis jedis = RedisPoolUntil.getJedis();
        Long okOrNo = null;
        if (id != null) {
            okOrNo = jedis.sadd(key + "_" + id, new String[] { value });
        }
        else {
            okOrNo = jedis.sadd(key, new String[] { value });
        }
        RedisPoolUntil.returnResource(jedis);
        jedis = null;
        return okOrNo;
    }
    
    public static void insertValue(String key, String id, String value, int expireTimeInSeconds) {
        Jedis jedis = RedisPoolUntil.getJedis();
        Long okOrNo = null;
        if (id != null) {
            jedis.setex(key + "_" + id, expireTimeInSeconds, value);
        }
        else {
            jedis.setex(key, expireTimeInSeconds, value);
        }
        RedisPoolUntil.returnResource(jedis);
        jedis = null;
    }
    
    public static <T> T getValue(String key, String id, Class<T> classOfT) {
        Jedis jedis = RedisPoolUntil.getJedis();
        String json = jedis.get(key + "_" + id);
        T t = GsonUtil.getGsonUtil().getgson().fromJson(json, classOfT);
        RedisPoolUntil.returnResource(jedis);
        jedis = null;
        return t;
    }
    
    public static Long insertGoodsRecordRedis(String key, String id, String value) {
        Jedis jedis = RedisGoodPoolUntil.getJedis();
        Long okOrNo = null;
        if (id != null) {
            okOrNo = jedis.sadd(key + "_" + id, new String[] { value });
        }
        else {
            okOrNo = jedis.sadd(key, new String[] { value });
        }
        RedisGoodPoolUntil.returnResource(jedis);
        jedis = null;
        return okOrNo;
    }
    
    public static String deletrValue(String key, String roleID, String member) {
        Jedis jedis = RedisPoolUntil.getJedis();
        Long okOrNo = jedis.srem(key + "_" + roleID, new String[] { member });
        RedisPoolUntil.returnResource(jedis);
        jedis = null;
        return okOrNo + "";
    }
    
    public static <T> T getV(String key, String field, Class<T> classOfT) {
        RedisCacheBean<T> cacheBean = (RedisCacheBean<T>)RedisCacheUtil.getRedisCacheBean(key);
        if (cacheBean != null) {
            T t = cacheBean.getCache(field);
            if (t != null) {
                return t;
            }
        }
        Jedis jedis = RedisPoolUntil.getJedis();
        String a = jedis.hget(key, field);
        RedisPoolUntil.returnResource(jedis);
        if (a != null) {
            T t2 = GsonUtil.getGsonUtil().getgson().fromJson(a, classOfT);
            if (cacheBean != null) {
                cacheBean.addCache(field, t2);
            }
            return t2;
        }
        else {
            return null;
        }
    }
    
    public static <T> List<T> getS(String qz, String id, Class<T> classOfT) {
        List<T> Ts = new ArrayList<>();
        Jedis jedis = RedisPoolUntil.getJedis();
        Set<String> list = jedis.smembers(qz + "_" + id);
        if (list == null) {
            RedisPoolUntil.returnResource(jedis);
            return Ts;
        }
        RedisCacheBean<T> cacheBean = (RedisCacheBean<T>)RedisCacheUtil.getRedisCacheBean(qz);
        if (cacheBean != null) {
            cacheBean.getCaches(list, Ts);
        }
        if (list.size() != 0) {
            String[] b = new String[list.size()];
            list.toArray(b);
            List<String> list2 = jedis.hmget(qz, b);
            RedisPoolUntil.returnResource(jedis);
            if (list2 != null) {
                for (int i = 0; i < list2.size(); ++i) {
                    if (list2.get(i) != null) {
                        T t = GsonUtil.getGsonUtil().getgson().fromJson((String)list2.get(i), classOfT);
                        Ts.add(t);
                        if (cacheBean != null) {
                            cacheBean.addCache(b[i], t);
                        }
                    }
                }
            }
            return Ts;
        }
        else {
            RedisPoolUntil.returnResource(jedis);
            return Ts;
        }
    }
    
    public static <T> List<T> getS(String qz, Set<String> ids, Class<T> classOfT) {
        List<T> Ts = new ArrayList<>();
        if (ids == null) {
            return Ts;
        }
        RedisCacheBean<T> cacheBean = (RedisCacheBean<T>)RedisCacheUtil.getRedisCacheBean(qz);
        if (cacheBean != null) {
            cacheBean.getCaches(ids, Ts);
        }
        if (ids.size() != 0) {
            String[] v = new String[ids.size()];
            ids.toArray(v);
            Jedis jedis = RedisPoolUntil.getJedis();
            List<String> list = jedis.hmget(qz, v);
            RedisPoolUntil.returnResource(jedis);
            if (list != null) {
                for (int i = 0; i < list.size(); ++i) {
                    T t = GsonUtil.getGsonUtil().getgson().fromJson((String)list.get(i), classOfT);
                    Ts.add(t);
                    if (cacheBean != null) {
                        cacheBean.addCache(v[i], t);
                    }
                }
            }
        }
        return Ts;
    }
    
    public static void insertController(String key, String id, String control) {
        Jedis jedis = RedisPoolUntil.getJedis();
        String v = key + ":" + id;
        String controlIN = jedis.hget(RedisParameterUtil.ROLE_CONTROL, v);
        if (controlIN == null) {
            jedis.hset(RedisParameterUtil.ROLE_CONTROL, v, control);
        }
        else if (!control.equals("2")) {
            if (control.equals("3")) {
                if (controlIN.equals("1")) {
                    jedis.hdel(RedisParameterUtil.ROLE_CONTROL, new String[] { v });
                }
                else {
                    jedis.hset(RedisParameterUtil.ROLE_CONTROL, v, control);
                }
            }
            else {
                jedis.hset(RedisParameterUtil.ROLE_CONTROL, v, control);
            }
        }
        RedisPoolUntil.returnResource(jedis);
    }
    
    public static void userController(String key, String id, String control, String json) {
        Jedis jedis = RedisGoodPoolUntil.getJedis();
        String v = key + ":" + id;
        String controlIN = jedis.hget(RedisParameterUtil.USER_REDIS, v);
        if (controlIN != null && !controlIN.equals("")) {
            controlIN = controlIN.substring(0, 1);
        }
        if (controlIN == null) {
            jedis.hset(RedisParameterUtil.USER_REDIS, v, control + json);
        }
        else if (!control.equals("2")) {
            if (control.equals("3")) {
                if (controlIN.equals("1")) {
                    jedis.hdel(RedisParameterUtil.USER_REDIS, new String[] { v });
                }
                else {
                    jedis.hset(RedisParameterUtil.USER_REDIS, v, control + json);
                }
            }
            else {
                jedis.hset(RedisParameterUtil.USER_REDIS, v, control + json);
            }
        }
        RedisGoodPoolUntil.returnResource(jedis);
    }
    
    public static Set<String> sinterJiao(String... mes) {
        Jedis jedis = RedisPoolUntil.getJedis();
        Set<String> list = jedis.sinter(mes);
        RedisPoolUntil.returnResource(jedis);
        return list;
    }
    
    public static void addUpDate(LoginResult loginResult, PackRecord packRecord) {
        Jedis jedis = RedisPoolUntil.getJedis();
        String id = loginResult.getRole_id().toString();
        jedis.hset(RedisParameterUtil.COPY_LOGIN, id, GsonUtil.getGsonUtil().getgson().toJson(loginResult));
        jedis.hset(RedisParameterUtil.COPY_PACK, id, GsonUtil.getGsonUtil().getgson().toJson(packRecord));
        RedisPoolUntil.returnResource(jedis);
    }
    
    public static <T> List<T> hgetAll(String qz, Class<T> classOfT) {
        List<T> Ts = new ArrayList<>();
        RedisCacheBean<T> cacheBean = (RedisCacheBean<T>)RedisCacheUtil.getRedisCacheBean(qz);
        Jedis jedis = RedisPoolUntil.getJedis();
        Map<String, String> maps = jedis.hgetAll(qz);
        RedisPoolUntil.returnResource(jedis);
        for (String key : maps.keySet()) {
            String entity = (String)maps.get(key);
            T t = GsonUtil.getGsonUtil().getgson().fromJson(entity, classOfT);
            Ts.add(t);
        }
        return Ts;
    }
    
    public static PageIpInfo getUserIpList(Integer page, Integer size, String ip) {
        PageIpInfo pageIpInfo = new PageIpInfo();
        List<UserIp> pageData = new ArrayList<>();
        int totalRecores = 0;
        Jedis jedis = RedisPoolUntil.getJedis();
        if (CharSequenceUtil.isNotBlank(ip)) {
            Set<String> smembers = jedis.smembers("USER_LOGIN_IP:" + ip);
            for (String smember : smembers) {
                UserIp userIp = new UserIp();
                userIp.setIp(ip);
                String userName = smember.split("&")[0].split(":")[1];
                userIp.setUserName(userName);
                String roleName = smember.split("&")[1].split(":")[1];
                userIp.setRoleName(roleName);
                pageData.add(userIp);
            }
            totalRecores = smembers.size();
        }
        else {
            ScanParams scanParams = new ScanParams().match("USER_LOGIN_IP:*").count(Integer.valueOf(5000));
            String cursor = "0";
            do {
                ScanResult<String> scanResult = jedis.scan(cursor, scanParams);
                List<String> resultKey = scanResult.getResult();
                totalRecores += resultKey.size();
                if (Integer.parseInt(cursor) / (int)size < (int)page - 1) {
                    cursor = scanResult.getStringCursor();
                }
                else {
                    for (String s : resultKey) {
                        Set<String> smembers2 = jedis.smembers(s);
                        for (String smember2 : smembers2) {
                            UserIp userIp2 = new UserIp();
                            userIp2.setIp(s.split(":")[1]);
                            String userName2 = smember2.split("&")[0].split(":")[1];
                            userIp2.setUserName(userName2);
                            String roleName2 = smember2.split("&")[1].split(":")[1];
                            userIp2.setRoleName(roleName2);
                            pageData.add(userIp2);
                        }
                        if (pageData.size() == (int)size) {
                            break;
                        }
                    }
                    cursor = scanResult.getStringCursor();
                }
            } while (!cursor.equals("0"));
        }
        int totalPages = (int)Math.ceil((double)totalRecores / (double)(int)size);
        pageIpInfo.setList(pageData);
        pageIpInfo.setPageNum((int)page);
        pageIpInfo.setPages(totalPages);
        pageIpInfo.setTotal((long)totalRecores);
        jedis.close();
        return pageIpInfo;
    }
    
    public static void fengJinIp(String value) {
        Jedis jedis = RedisPoolUntil.getJedis();
        jedis.sadd("FENG_USER_IP:" + value, new String[] { value });
        jedis.close();
    }
    
    public static void jieFengIp(String value) {
        Jedis jedis = RedisPoolUntil.getJedis();
        jedis.del("FENG_USER_IP:" + value);
        jedis.close();
    }
    
    public static boolean checkIpFenJen(String value) {
        Jedis jedis = RedisPoolUntil.getJedis();
        Boolean hexists = jedis.exists("FENG_USER_IP:" + value);
        jedis.close();
        return (boolean)hexists;
    }
    
    public static void clearUserIp() {
        Jedis jedis = RedisPoolUntil.getJedis();
        Set<String> keys = jedis.keys("USER_LOGIN_IP:*");
        keys.forEach(f/* java.lang.String, */ -> jedis.del(f));
        jedis.close();
    }
    
    public static PageIpInfo getFengJinIpList(Integer page, Integer size, String ip) {
        PageIpInfo pageIpInfo = new PageIpInfo();
        List<UserIp> pageData = new ArrayList<>();
        int totalRecores = 0;
        Jedis jedis = RedisPoolUntil.getJedis();
        if (CharSequenceUtil.isNotBlank(ip)) {
            Set<String> smembers = jedis.smembers("FENG_USER_IP:" + ip);
            for (String smember : smembers) {
                UserIp userIp = new UserIp();
                userIp.setIp(ip);
                pageData.add(userIp);
            }
            totalRecores = smembers.size();
        }
        else {
            ScanParams scanParams = new ScanParams().match("FENG_USER_IP:*").count(Integer.valueOf(5000));
            String cursor = "0";
            do {
                ScanResult<String> scanResult = jedis.scan(cursor, scanParams);
                List<String> resultKey = scanResult.getResult();
                totalRecores += resultKey.size();
                if (Integer.parseInt(cursor) / (int)size < (int)page - 1) {
                    cursor = scanResult.getStringCursor();
                }
                else {
                    for (String s : resultKey) {
                        Set<String> smembers2 = jedis.smembers(s);
                        for (String smember2 : smembers2) {
                            UserIp userIp2 = new UserIp();
                            userIp2.setIp(smember2);
                            pageData.add(userIp2);
                        }
                        if (pageData.size() == (int)size) {
                            break;
                        }
                    }
                    cursor = scanResult.getStringCursor();
                }
            } while (!cursor.equals("0"));
        }
        int totalPages = (int)Math.ceil((double)totalRecores / (double)(int)size);
        pageIpInfo.setList(pageData);
        pageIpInfo.setPageNum((int)page);
        pageIpInfo.setPages(totalPages);
        pageIpInfo.setTotal((long)totalRecores);
        jedis.close();
        return pageIpInfo;
    }
    
    public static void insertUserIp(String ip, String userName, String roleName) {
        Jedis jedis = RedisPoolUntil.getJedis();
        String s = "userName:" + userName + " & roleName:" + roleName;
        if (Objects.isNull(ip)) {
            ip = "127.0.0.1";
            return;
        }
        jedis.sadd("USER_LOGIN_IP:".concat(ip), new String[] { s });
        jedis.close();
    }
    
    public static void deltUserIp(String ip, String userName, String roleName) {
        Jedis jedis = RedisPoolUntil.getJedis();
        String s = "userName:" + userName + " & roleName:" + roleName;
        if (Objects.isNull(ip)) {
            ip = "127.0.0.1";
            return;
        }
        jedis.srem("USER_LOGIN_IP:".concat(ip), new String[] { s });
        jedis.close();
    }
    
    public static int getUserIpCount(String ip) {
        Jedis jedis = RedisPoolUntil.getJedis();
        if (Objects.isNull(ip)) {
            ip = "127.0.0.1";
        }
        Set<String> smembers = jedis.smembers("USER_LOGIN_IP:".concat(ip));
        int size = smembers.size();
        jedis.close();
        return size;
    }
    
    public static BigDecimal getRoleId(int i) {
        Jedis jedis = RedisPoolUntil.getJedis();
        String s = jedis.get("ROLE:ID");
        if (Objects.isNull(s)) {
            jedis.set("ROLE:ID", String.valueOf(i));
            BigDecimal bigDecimal = new BigDecimal(i);
            return bigDecimal;
        }
        Long incr = jedis.incr("ROLE:ID");
        return new BigDecimal(String.valueOf(incr));
    }
}
