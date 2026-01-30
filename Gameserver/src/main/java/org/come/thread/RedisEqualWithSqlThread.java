package org.come.thread;

import org.come.entity.PackRecord;
import org.come.until.TimeUntil;
import org.come.entity.UserTable;
import org.come.until.GsonUtil;
import org.come.bean.LoginResult;
import org.come.entity.Pal;
import org.come.entity.Mount;
import org.come.entity.Baby;
import org.come.entity.RoleSummoning;
import org.come.redis.RedisControl;
import org.come.entity.Lingbao;
import java.math.BigDecimal;
import org.come.until.AllServiceUtil;
import java.util.Iterator;
import java.util.Map;
import redis.clients.jedis.Jedis;
import org.come.tool.WriteOut;
import org.come.redis.RedisParameterUtil;
import org.come.redis.RedisPoolUntil;

public class RedisEqualWithSqlThread implements Runnable
{
    public static Object object;
    private static DataBaseControl dataBaseControl;
    
    @Override
    public void run() {
        AllToDatabase();
    }
    
    public static void AllToDatabase() {
        synchronized (RedisEqualWithSqlThread.object) {
            Jedis jedis = RedisPoolUntil.getJedis();
            Map<String, String> redisChangeMap = jedis.hgetAll(RedisParameterUtil.ROLE_CONTROL);
            jedis.del(RedisParameterUtil.ROLE_CONTROL);
            RedisPoolUntil.returnResource(jedis);
            long x = System.currentTimeMillis();
            int size = 0;
            System.err.println("开始同步数据库本次数量:" + redisChangeMap.size());
            for (String key : redisChangeMap.keySet()) {
                insertIntoValueToSql(key, (String)redisChangeMap.get(key));
                if (++size % 50000 == 0) {
                    System.out.println(size + ":" + redisChangeMap.size());
                }
            }
            DataBaseControl.manageBaby.ClearList();
            DataBaseControl.manageGoodstable.ClearList();
            DataBaseControl.manageLingBao.ClearList();
            DataBaseControl.manageMount.ClearList();
            DataBaseControl.managePal.ClearList();
            DataBaseControl.managePet.ClearList();
            if (size == 0) {
                System.err.println("本次没有物品数据同步");
                return;
            }
            long y = System.currentTimeMillis() - x;
            StringBuffer buffer = new StringBuffer();
            buffer.append("同步");
            buffer.append(size);
            buffer.append("条物品数据用时:");
            buffer.append(y);
            buffer.append("平均用时");
            buffer.append(y / (long)size);
            System.err.println(buffer.toString());
            if (y > 200000L) {
                System.err.println("同步数据库超时:" + y);
                WriteOut.addtxt("同步数据库超时", y);
            }
        }
    }
    
    public static void insertIntoValueToSql(String key, String value) {
        String[] mesGet = key.split(":");
        RedisEqualWithSqlThread.dataBaseControl.control(value, mesGet[1], mesGet[0]);
    }
    
    public static void controlLingbaoForm(String value, String ID) {
        if (value.equals("3")) {
            AllServiceUtil.getLingbaoService().deleteLingbaosql(new BigDecimal(ID));
            return;
        }
        Lingbao lingbao = (Lingbao)RedisControl.getV(RedisParameterUtil.LINGBAO, ID, Lingbao.class);
        if (lingbao == null) {
            System.out.println("同步数据库出错:" + RedisParameterUtil.LINGBAO + ":" + ID + ":" + value);
            return;
        }
        if (value.equals("1")) {
            AllServiceUtil.getLingbaoService().insertLingbaosql(lingbao);
        }
        else if (value.equals("2")) {
            AllServiceUtil.getLingbaoService().updateLingbaosql(lingbao);
        }
    }
    
    public static void controlPetForm(String value, String ID) {
        if (value.equals("3")) {
            AllServiceUtil.getRoleSummoningService().deleteRoleSummoningBySidsql(new BigDecimal(ID));
            return;
        }
        RoleSummoning pet = (RoleSummoning)RedisControl.getV(RedisParameterUtil.PET, ID, RoleSummoning.class);
        if (pet == null) {
            System.out.println("同步数据库出错:" + RedisParameterUtil.PET + ":" + ID + ":" + value);
            return;
        }
        if (value.equals("1")) {
            AllServiceUtil.getRoleSummoningService().insertRoleSummoningsql(pet);
            AllServiceUtil.getRoleSummoningService().updateRoleSummoningsql(pet);
        }
        else if (value.equals("2")) {
            AllServiceUtil.getRoleSummoningService().updateRoleSummoningsql(pet);
        }
    }
    
    public static void controlBabyForm(String value, String ID) {
        if (value.equals("3")) {
            return;
        }
        Baby baby = (Baby)RedisControl.getV(RedisParameterUtil.BABY, ID, Baby.class);
        if (baby == null) {
            System.out.println("同步数据库出错:" + RedisParameterUtil.BABY + ":" + ID + ":" + value);
            return;
        }
        if (value.equals("1")) {
            AllServiceUtil.getBabyService().createBabysql(baby);
            AllServiceUtil.getBabyService().updateBabysql(baby);
        }
        else if (value.equals("2")) {
            AllServiceUtil.getBabyService().updateBabysql(baby);
        }
    }
    
    public static void controlMountForm(String value, String ID) {
        if (value.equals("3")) {
            AllServiceUtil.getMountService().deleteMountsByMidsql(new BigDecimal(ID));
            return;
        }
        Mount mount = (Mount)RedisControl.getV(RedisParameterUtil.MOUNT, ID, Mount.class);
        if (mount == null) {
            System.out.println("同步数据库出错:" + RedisParameterUtil.MOUNT + ":" + ID + ":" + value);
            return;
        }
        if (value.equals("1")) {
            AllServiceUtil.getMountService().insertMountsql(mount);
            AllServiceUtil.getMountService().updateMountsql(mount);
        }
        else if (value.equals("2")) {
            AllServiceUtil.getMountService().updateMountsql(mount);
        }
    }
    
    public static void controlPalForm(String value, String ID) {
        if (value.equals("3")) {
            AllServiceUtil.getPalService().deletePalSql(new BigDecimal(ID));
            return;
        }
        Pal pal = (Pal)RedisControl.getV(RedisParameterUtil.PAL, ID, Pal.class);
        if (pal == null) {
            System.out.println("同步数据库出错:" + RedisParameterUtil.PAL + ":" + ID + ":" + value);
            return;
        }
        if (value.equals("1")) {
            AllServiceUtil.getPalService().insertPalSql(pal);
        }
        else if (value.equals("2")) {
            AllServiceUtil.getPalService().updatePalSql(pal);
        }
    }
    
    public static void AllToDataRole() {
        Jedis jedis = RedisPoolUntil.getJedis();
        Map<String, String> Map1 = jedis.hgetAll(RedisParameterUtil.COPY_LOGIN);
        Map<String, String> Map2 = jedis.hgetAll(RedisParameterUtil.COPY_PACK);
        RedisPoolUntil.returnResource(jedis);
        long x = System.currentTimeMillis();
        int size = 0;
        System.out.println("开始同步玩家数据本次数量:" + Map1.size());
        for (String key : Map1.keySet()) {
            try {
                LoginResult loginResult = (LoginResult)GsonUtil.getGsonUtil().getgson().fromJson((String)Map1.get(key), LoginResult.class);
                UserTable userTable = new UserTable();
                userTable.setCodecard(loginResult.getCodecard());
                userTable.setMoney(loginResult.getMoney());
                userTable.setUsername(loginResult.getUserName());
                userTable.setUSERLASTLOGIN(TimeUntil.getPastDate());
                AllServiceUtil.getUserTableService().updateUser(userTable);
                AllServiceUtil.getRoleTableService().updateRoleWhenExit(loginResult);
                PackRecord packRecord = (PackRecord)GsonUtil.getGsonUtil().getgson().fromJson((String)Map2.get(key), PackRecord.class);
                if (packRecord != null) {
                    AllServiceUtil.getPackRecordService().updateByPrimaryKeySelective(packRecord);
                }
                else {
                    System.err.println("缺少背包记录");
                }
                System.err.println("处理玩家下线成功" + loginResult.getRolename());
            }
            catch (Exception e) {
                System.err.println("同步人物数据库报错" + key + ":" + (String)Map1.get(key) + ":" + (String)Map2.get(key));
                WriteOut.addtxt("同步人物数据库报错" + key + ":" + (String)Map1.get(key) + ":" + (String)Map2.get(key), 999L);
                e.printStackTrace();
            }
            ++size;
        }
        if (size == 0) {
            System.err.println("本次没有人物数据同步");
            return;
        }
        long y = System.currentTimeMillis() - x;
        StringBuffer buffer = new StringBuffer();
        buffer.append("同步");
        buffer.append(size);
        buffer.append("条人物数据用时:");
        buffer.append(y);
        buffer.append("平均用时");
        buffer.append(y / (long)size);
        System.out.println(buffer.toString());
        if (y > 200000L) {
            System.err.println("同步数据库超时:" + y);
            WriteOut.addtxt("同步数据库超时", y);
        }
        jedis = RedisPoolUntil.getJedis();
        jedis.del(RedisParameterUtil.COPY_LOGIN);
        jedis.del(RedisParameterUtil.COPY_PACK);
        RedisPoolUntil.returnResource(jedis);
    }
    
    static {
        RedisEqualWithSqlThread.object = new Object();
        RedisEqualWithSqlThread.dataBaseControl = new DataBaseControl();
    }
}
