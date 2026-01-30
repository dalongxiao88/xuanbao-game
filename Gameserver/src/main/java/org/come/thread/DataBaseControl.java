package org.come.thread;

import java.util.HashMap;
import org.come.entity.RoleSummoning;
import org.come.entity.Pal;
import org.come.entity.Mount;
import org.come.entity.Lingbao;
import org.come.entity.Goodstable;
import org.come.entity.Baby;
import org.come.redis.RedisParameterUtil;
import org.come.redis.RedisControl;
import java.math.BigDecimal;
import java.util.Map;

public class DataBaseControl
{
    public static DataBaseManage manageGoodstable;
    public static DataBaseManage manageBaby;
    public static DataBaseManage manageMount;
    public static DataBaseManage manageLingBao;
    public static DataBaseManage managePal;
    public static DataBaseManage managePet;
    private static Map<String, DataBaseManage> map;
    
    public void control(String value, String ID, String type) {
        if ("3".equals(value)) {
            ((DataBaseManage)DataBaseControl.map.get(type)).del(new BigDecimal(ID));
        }
        else {
            Class<Object> list = getList(type);
            if (list == null) {
                return;
            }
            Object v = RedisControl.getV(type, ID, list);
            if (v == null) {
                System.out.println("同步数据库出错:" + type + ":" + ID + ":" + value);
                return;
            }
            if ("2".equals(value)) {
                ((DataBaseManage)DataBaseControl.map.get(type)).upd(v);
            }
            else if ("1".equals(value)) {
                ((DataBaseManage)DataBaseControl.map.get(type)).add(v);
            }
        }
    }
    
    public static <T> Class<T> getList(String value) {
        if (RedisParameterUtil.BABY.equals(value)) {
            return (Class<T>)Baby.class;
        }
        if (RedisParameterUtil.GOODS.equals(value)) {
            return (Class<T>)Goodstable.class;
        }
        if (RedisParameterUtil.LINGBAO.equals(value)) {
            return (Class<T>)Lingbao.class;
        }
        if (RedisParameterUtil.MOUNT.equals(value)) {
            return (Class<T>)Mount.class;
        }
        if (RedisParameterUtil.PAL.equals(value)) {
            return (Class<T>)Pal.class;
        }
        if (RedisParameterUtil.PET.equals(value)) {
            return (Class<T>)RoleSummoning.class;
        }
        return null;
    }
    
    static {
        DataBaseControl.manageGoodstable = new GoodstableManage();
        DataBaseControl.manageBaby = new BabyManage();
        DataBaseControl.manageMount = new MountManage();
        DataBaseControl.manageLingBao = new LingBaoManage();
        DataBaseControl.managePal = new PalManage();
        DataBaseControl.managePet = new PetManage();
        (DataBaseControl.map = new HashMap<>()).put(RedisParameterUtil.GOODS, DataBaseControl.manageGoodstable);
        DataBaseControl.map.put(RedisParameterUtil.PAL, DataBaseControl.managePal);
        DataBaseControl.map.put(RedisParameterUtil.PET, DataBaseControl.managePet);
        DataBaseControl.map.put(RedisParameterUtil.MOUNT, DataBaseControl.manageMount);
        DataBaseControl.map.put(RedisParameterUtil.BABY, DataBaseControl.manageBaby);
        DataBaseControl.map.put(RedisParameterUtil.LINGBAO, DataBaseControl.manageLingBao);
    }
}
