package org.come.redis;

import org.come.action.pack.PackChangeAction;
import org.come.entity.*;
import org.come.handler.MainServerHandler;
import org.come.until.TimeUntil;

import java.util.Objects;
import java.io.File;

import come.tool.Transplant.TransplantBean;
import come.tool.Transplant.UserDataBean;
import come.tool.Transplant.RoleDataBean;
import come.tool.Transplant.GangTransplant;
import org.come.until.CreateTextUtil;
import org.come.tool.GZip;
import come.tool.Transplant.FriendTransplant;

import java.text.SimpleDateFormat;

import come.tool.Scene.PKLS.lsteamBean;
import org.come.until.ReadTxtUtil;
import org.come.tool.ReadExelTool;

import java.util.ArrayList;

import org.come.bean.LoginResult;
import org.come.action.suit.SuitComposeAction;
import org.come.tool.Goodtype;
import come.tool.Scene.LTS.UserData;
import com.google.gson.Gson;
import org.come.until.GsonUtil;

import java.util.Map;
import java.util.List;

import org.come.tool.WriteOut;

import java.util.HashMap;

import org.come.thread.RedisEqualWithSqlThread;

import java.util.Iterator;
import java.util.Set;

import redis.clients.jedis.Jedis;
import org.come.server.GameServer;
import org.come.until.AllServiceUtil;
import org.come.bean.SellXianyu;

import java.math.BigDecimal;

public class RedisCacheUtil {
    public static BigDecimal ADD;
    private static BigDecimal role_pk;
    private static BigDecimal user_pk;
    private static BigDecimal baby_pk;
    private static BigDecimal goods_pk;
    private static BigDecimal mount_pk;
    private static BigDecimal car_pk;
    private static BigDecimal fly_pk;
    private static BigDecimal pet_pk;
    private static BigDecimal lingbao_pk;
    private static BigDecimal xuanbao_pk;
    private static BigDecimal pal_pk;
    private static long record_pk;
    private static BigDecimal sell_pk;
    private static BigDecimal oneAreanNotes_pk;
    public static BigDecimal oneAreanNotes_min;
    private static RedisCacheBean<Goodstable> goodBean;
    private static RedisCacheBean<RoleSummoning> petBean;
    private static RedisCacheBean<Lingbao> lingbaoBean;
    private static RedisCacheBean<Baby> babyBean;
    private static RedisCacheBean<Mount> mountBean;
    private static RedisCacheBean<Pal> palBean;
    private static RedisCacheBean<Fly> flyBean;
    private static RedisCacheBean<SellXianyu> sellXianyuBean;
    private static RedisCacheBean<Car> carBean;
    private static Monitor monitor;

    public static RedisCacheBean<?> getRedisCacheBean(String Key) {
        if (Key.equals(RedisParameterUtil.GOODS)) {
            return RedisCacheUtil.goodBean;
        }
        if (Key.equals(RedisParameterUtil.PET)) {
            return RedisCacheUtil.petBean;
        }
        if (Key.equals(RedisParameterUtil.LINGBAO)) {
            return RedisCacheUtil.lingbaoBean;
        }
        if (Key.equals(RedisParameterUtil.BABY)) {
            return RedisCacheUtil.babyBean;
        }
        if (Key.equals(RedisParameterUtil.MOUNT)) {
            return RedisCacheUtil.mountBean;
        }
        if (Key.equals(RedisParameterUtil.PAL)) {
            return RedisCacheUtil.palBean;
        }
        if (Key.equals(RedisParameterUtil.FLY)) {
            return RedisCacheUtil.flyBean;
        }
        if (Key.equals(RedisParameterUtil.SELL)) {
            return RedisCacheUtil.sellXianyuBean;
        }
        if (Key.equals(RedisParameterUtil.CAR)) {
            return RedisCacheUtil.carBean;
        }
        return null;
    }

    public void InitZhu() {
        Jedis jedis = RedisPoolUntil.getJedis();
        RedisCacheUtil.goods_pk = this.getMax(jedis.hkeys(RedisParameterUtil.GOODS));
        RedisCacheUtil.lingbao_pk = this.getMax(jedis.hkeys(RedisParameterUtil.LINGBAO));
        xuanbao_pk = getMax(jedis.hkeys(RedisParameterUtil.XUANBAO));
        RedisCacheUtil.pet_pk = this.getMax(jedis.hkeys(RedisParameterUtil.PET));
        RedisCacheUtil.sell_pk = this.getMax(jedis.hkeys(RedisParameterUtil.SELL));
        RedisCacheUtil.baby_pk = this.getMax(jedis.hkeys(RedisParameterUtil.BABY));
        RedisCacheUtil.mount_pk = this.getMax(jedis.hkeys(RedisParameterUtil.MOUNT));
        RedisCacheUtil.car_pk = this.getMax(jedis.hkeys(RedisParameterUtil.CAR));
        RedisCacheUtil.fly_pk = this.getMax(jedis.hkeys(RedisParameterUtil.FLY));
        RedisCacheUtil.pal_pk = this.getMax(jedis.hkeys(RedisParameterUtil.PAL));
        RedisPoolUntil.returnResource(jedis);
        RedisCacheUtil.user_pk = AllServiceUtil.getUserTableService().selectUserMax();
        if (RedisCacheUtil.user_pk == null) {
            RedisCacheUtil.user_pk = new BigDecimal(GameServer.getQh());
        }
        RedisCacheUtil.role_pk = AllServiceUtil.getRoleTableService().selectRoleMax();
        if (RedisCacheUtil.role_pk == null) {
            RedisCacheUtil.role_pk = new BigDecimal(GameServer.getQh());
        }
        RedisCacheUtil.goods_pk = getGl(RedisCacheUtil.goods_pk, GameServer.getQh());
        RedisCacheUtil.lingbao_pk = getGl(RedisCacheUtil.lingbao_pk, GameServer.getQh());
        RedisCacheUtil.pet_pk = getGl(RedisCacheUtil.pet_pk, GameServer.getQh());
        RedisCacheUtil.sell_pk = getGl(RedisCacheUtil.sell_pk, GameServer.getQh());
        RedisCacheUtil.baby_pk = getGl(RedisCacheUtil.baby_pk, GameServer.getQh());
        RedisCacheUtil.mount_pk = getGl(RedisCacheUtil.mount_pk, GameServer.getQh());
        RedisCacheUtil.car_pk = getGl(RedisCacheUtil.car_pk, GameServer.getQh());
        RedisCacheUtil.pal_pk = getGl(RedisCacheUtil.pal_pk, GameServer.getQh());
        RedisCacheUtil.fly_pk = getGl(RedisCacheUtil.fly_pk, GameServer.getQh());
        BigDecimal record = AllServiceUtil.getAppVersionService().selectSequence();
        RedisCacheUtil.record_pk = ((record != null) ? record.longValue() : System.currentTimeMillis());
        RedisCacheUtil.user_pk = getGl(RedisCacheUtil.user_pk, GameServer.getQh());
        if (RedisCacheUtil.role_pk.longValue() < 1000000L * (long) GameServer.getQh()) {
            RedisCacheUtil.role_pk = new BigDecimal(1000000L * (long) GameServer.getQh());
        }
        RedisCacheUtil.oneAreanNotes_pk = AllServiceUtil.getOneArenaNotesService().selectMaxID(null);
        if (RedisCacheUtil.oneAreanNotes_pk == null) {
            RedisCacheUtil.oneAreanNotes_pk = new BigDecimal(GameServer.getQh());
        }
        RedisCacheUtil.oneAreanNotes_pk = getGl(RedisCacheUtil.oneAreanNotes_pk, GameServer.getQh());
        resetOneArenaTime();
        System.err.println("单人竞技场战报最大:" + RedisCacheUtil.oneAreanNotes_pk);
        System.err.println("物品最大:" + RedisCacheUtil.goods_pk);
        System.err.println("灵宝最大:" + RedisCacheUtil.lingbao_pk);
        System.err.println("召唤兽最大:" + RedisCacheUtil.pet_pk);
        System.err.println("宝宝最大:" + RedisCacheUtil.baby_pk);
        System.err.println("坐骑最大:" + RedisCacheUtil.mount_pk);
        System.err.println("坐架最大:" + RedisCacheUtil.car_pk);
        System.err.println("伙伴最大:" + RedisCacheUtil.pal_pk);
        System.err.println("角色最大:" + RedisCacheUtil.role_pk);
        System.err.println("用户最大:" + RedisCacheUtil.user_pk);
        System.err.println("出售仙玉最大:" + RedisCacheUtil.sell_pk);
    }

    public static BigDecimal getGl(BigDecimal id, int qh) {
        long v = id.longValue();
        long ys = v % 1000L;
        if (ys == (long) qh) {
            return id;
        }
        v += RedisCacheUtil.ADD.longValue();
        v += (long) qh - ys;
        return new BigDecimal(v);
    }

    public BigDecimal getMax(Set<String> set) {
        BigDecimal max = new BigDecimal(0);
        for (String key : set) {
            try {
                BigDecimal a = new BigDecimal(key);
                if (a.compareTo(max) > 0) {
                    max = a;
                } else {
                    continue;
                }
            } catch (Exception e) {
                System.err.println("错误key" + key);
            }
        }
        return new BigDecimal(max.longValue());
    }

    public void dataTB() {
        RedisEqualWithSqlThread.AllToDatabase();
        System.err.println("开始同步物品");
        List<Goodstable> goods = AllServiceUtil.getGoodsTableService().getAllGoods();
        Map<BigDecimal, Goodstable> goodmap1 = new HashMap<>();
        for (int i = 0; i < goods.size(); ++i) {
            goodmap1.put(((Goodstable) goods.get(i)).getRgid(), goods.get(i));
        }
        this.DataComparison(RedisParameterUtil.GOODS, goodmap1, Goodstable.class);
        goodmap1 = null;
        System.err.println("开始同步灵宝");
        List<Lingbao> lingbaos = AllServiceUtil.getLingbaoService().selectAllLingbao();
        Map<BigDecimal, Lingbao> lingmap1 = new HashMap<>();
        for (int j = 0; j < lingbaos.size(); ++j) {
            lingmap1.put(((Lingbao) lingbaos.get(j)).getBaoid(), lingbaos.get(j));
        }
        this.DataComparison(RedisParameterUtil.LINGBAO, lingmap1, Lingbao.class);
        lingmap1 = null;
        System.err.println("开始同步召唤兽");
        List<RoleSummoning> pets = AllServiceUtil.getRoleSummoningService().selectAllRoleSummonings();
        Map<BigDecimal, RoleSummoning> petmap1 = new HashMap<>();
        for (int k = 0; k < pets.size(); ++k) {
            petmap1.put(((RoleSummoning) pets.get(k)).getSid(), pets.get(k));
        }
        this.DataComparison(RedisParameterUtil.PET, petmap1, RoleSummoning.class);
        petmap1 = null;
        System.err.println("开始同步孩子");
        List<Baby> babys = AllServiceUtil.getBabyService().selectAllBaby();
        Map<BigDecimal, Baby> babymap1 = new HashMap<>();
        for (int l = 0; l < babys.size(); ++l) {
            babymap1.put(((Baby) babys.get(l)).getBabyid(), babys.get(l));
        }
        this.DataComparison(RedisParameterUtil.BABY, babymap1, Baby.class);
        babymap1 = null;
        System.err.println("开始同步坐骑");
        List<Mount> mounts = AllServiceUtil.getMountService().selectAllMounts();
        Map<BigDecimal, Mount> mountmap1 = new HashMap<>();
        for (int m = 0; m < mounts.size(); ++m) {
            Mount mount = (Mount) mounts.get(m);
            mountmap1.put(mount.getMid(), mount);
        }
        this.DataComparison(RedisParameterUtil.MOUNT, mountmap1, Mount.class);
        mountmap1 = null;
        System.err.print("开始同步飞行器");
        List<Fly> flys = AllServiceUtil.getFlyService().selectAllFlys();
        Map<BigDecimal, Fly> flymap1 = new HashMap<>();
        for (int i2 = 0; i2 < flys.size(); ++i2) {
            Fly fly = (Fly) flys.get(i2);
            flymap1.put(fly.getMid(), fly);
        }
        this.DataComparison(RedisParameterUtil.FLY, flymap1, Fly.class);
        mountmap1 = null;
        System.err.println("开始同步伙伴");
        List<Pal> pals = AllServiceUtil.getPalService().selectAllPalSql();
        Map<BigDecimal, Pal> palmap1 = new HashMap<>();
        for (int i3 = 0; i3 < pals.size(); ++i3) {
            Pal pal = (Pal) pals.get(i3);
            palmap1.put(pal.getId(), pal);
        }
        this.DataComparison(RedisParameterUtil.PAL, palmap1, Pal.class);
        palmap1 = null;
        WriteOut.TB();
        RedisEqualWithSqlThread.AllToDatabase();
    }

    public <T> void DataComparison(String key, Map<BigDecimal, T> map, Class<T> bean) {
        Gson gson = GsonUtil.getGsonUtil().getgson();
        Jedis jedis = RedisPoolUntil.getJedis();
        Set<String> set = jedis.hkeys(key);
        RedisPoolUntil.returnResource(jedis);
        int size = 0;
        int z = 0;
        for (String ID : set) {
            if (++size >= 1000) {
                size = 0;
                if (++z % 10 == 0) {
                    System.err.println(z * 1000);
                }
                try {
                    Thread.sleep(1L);
                } catch (Exception ex) {
                }
            }
            T t = map.remove(new BigDecimal(ID));
            if (t == null) {
                RedisControl.insertController(key, ID, "1");
            } else {
                Jedis jedis2 = RedisPoolUntil.getJedis();
                String a1 = jedis2.hget(key, ID);
                RedisPoolUntil.returnResource(jedis2);
                if (this.BD(t, gson.fromJson(a1, bean))) {
                    WriteOut.addtxt("REDIS:" + a1, 9999L);
                    WriteOut.addtxt("数据库:" + gson.toJson(t), 9999L);
                    RedisControl.insertController(key, ID, "2");
                } else {
                    continue;
                }
            }
        }
        for (BigDecimal id : map.keySet()) {
            if (!set.contains(id.toString())) {
                System.err.println("删除:" + key + ":" + id);
                RedisControl.insertController(key, id.toString(), "3");
            }
        }
    }

    public <T> boolean BD(T t1, T t2) {
        try {
            if (t1 instanceof Goodstable) {
                return this.BD0((Goodstable) t1, (Goodstable) t2);
            }
            if (t1 instanceof Lingbao) {
                return this.BD1((Lingbao) t1, (Lingbao) t2);
            }
            if (t1 instanceof RoleSummoning) {
                return this.BD2((RoleSummoning) t1, (RoleSummoning) t2);
            }
            if (t1 instanceof Baby) {
                return this.BD3((Baby) t1, (Baby) t2);
            }
            if (t1 instanceof Mount) {
                return this.BD4((Mount) t1, (Mount) t2);
            }
            if (t1 instanceof Pal) {
                return this.BD5((Pal) t1, (Pal) t2);
            }
            if (t1 instanceof Fly) {
                return this.BD6((Fly) t1, (Fly) t2);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return true;
    }

    public boolean BD0(Goodstable t1, Goodstable t2) {
        if (t1.getRgid().compareTo(t2.getRgid()) != 0) {
            return true;
        }
        if (t1.getRole_id().compareTo(t2.getRole_id()) != 0) {
            return true;
        }
        if (t1.getGoodsid().compareTo(t2.getGoodsid()) != 0) {
            return true;
        }
        if (t1.getType() != t2.getType()) {
            return true;
        }
        if ((int) t1.getStatus() != (int) t2.getStatus()) {
            return true;
        }
        if ((int) t1.getUsetime() != (int) t2.getUsetime()) {
            return true;
        }
        if (t1.getGoodlock() != t2.getGoodlock()) {
            return true;
        }
        if (t1.getValue() == null) {
            t1.setValue("");
        }
        if (t2.getValue() == null) {
            t2.setValue("");
        }
        return !t1.getValue().equals(t2.getValue()) || !t1.isQh(t2);
    }

    public boolean BD1(Lingbao t1, Lingbao t2) {
        if (t1.getBaoactive().compareTo(t2.getBaoactive()) != 0) {
            return true;
        }
        if (!t1.getBaospeed().equals(t2.getBaospeed())) {
            return true;
        }
        if (!t1.getBaoreply().equals(t2.getBaoreply())) {
            return true;
        }
        if (!t1.getBaoshot().equals(t2.getBaoshot())) {
            return true;
        }
        if (!t1.getBaoap().equals(t2.getBaoap())) {
            return true;
        }
        if (!t1.getResistshot().equals(t2.getResistshot())) {
            return true;
        }
        if (!t1.getAssistance().equals(t2.getAssistance())) {
            return true;
        }
        if (t1.getRoleid().compareTo(t2.getRoleid()) != 0) {
            return true;
        }
        if (t1.getFushis() == null) {
            t1.setFushis("");
        }
        if (t2.getFushis() == null) {
            t2.setFushis("");
        }
        if (!t1.getFushis().equals(t2.getFushis())) {
            return true;
        }
        if (t1.getSkills() == null) {
            t1.setSkills("");
        }
        if (t2.getSkills() == null) {
            t2.setSkills("");
        }
        return !t1.getSkills().equals(t2.getSkills()) || t1.getLingbaolvl().compareTo(t2.getLingbaolvl()) != 0 || t1.getLingbaoexe().compareTo(t2.getLingbaoexe()) != 0 || t1.getLingbaoqihe() != t2.getLingbaoqihe() || !t1.getKangxing().equals(t2.getKangxing()) || t1.getBaoid().compareTo(t2.getBaoid()) != 0 || t1.getEquipment() != t2.getEquipment() || !t1.getBaoquality().equals(t2.getBaoquality()) || (int) t1.getSkillsum() != (int) t2.getSkillsum() || (int) t1.getFusum() != (int) t2.getFusum();
    }

    private boolean BD2(RoleSummoning t1, RoleSummoning t2) {
        return !t1.getSummoningid().equals(t2.getSummoningid()) || !t1.getSummoningname().equals(t2.getSummoningname()) || !t1.getSummoningskin().equals(t2.getSummoningskin()) || !t1.getSsn().equals(t2.getSsn()) || !t1.getStye().equals(t2.getStye()) || t1.getHp() != t2.getHp() || t1.getMp() != t2.getMp() || t1.getAp() != t2.getAp() || t1.getSp() != t2.getSp() || !t1.getGrowlevel().equals(t2.getGrowlevel()) || !t1.getResistance().equals(t2.getResistance()) || !t1.getGold().equals(t2.getGold()) || !t1.getWood().equals(t2.getWood()) || !t1.getSoil().equals(t2.getSoil()) || !t1.getWater().equals(t2.getWater()) || !t1.getFire().equals(t2.getFire()) || this.zf(t1.getColorScheme(), t2.getColorScheme()) || t1.getRoleid().compareTo(t2.getRoleid()) != 0 || (int) t1.getBone() != (int) t2.getBone() || (int) t1.getSpir() != (int) t2.getSpir() || (int) t1.getPower() != (int) t2.getPower() || (int) t1.getSpeed() != (int) t2.getSpeed() || (int) t1.getCalm() != (int) t2.getCalm() || (int) t1.getGrade() != (int) t2.getGrade() || (int) t1.getFaithful() != (int) t2.getFaithful() || (int) t1.getOpenSeal() != (int) t2.getOpenSeal() || (int) t1.getOpenql() != (int) t2.getOpenql() || t1.getBasishp() != t2.getBasishp() || t1.getBasismp() != t2.getBasismp() || (long) t1.getFriendliness() != (long) t2.getFriendliness() || t1.getExp().compareTo(t2.getExp()) != 0 || t1.getSid().compareTo(t2.getSid()) != 0 || this.zf(t1.getInnerGoods(), t2.getInnerGoods()) || this.zf(t1.getPetSkills(), t2.getPetSkills()) || t1.getDragon() != t2.getDragon() || t1.getSpdragon() != t2.getSpdragon() || t1.getTurnRount() != t2.getTurnRount() || t1.getRevealNum() != t2.getRevealNum() || t1.getFlyupNum() != t2.getFlyupNum() || t1.getAlchemynum() != t2.getAlchemynum() || t1.getGrowUpDanNum() != t2.getGrowUpDanNum() || t1.getDraC() != t2.getDraC() || t1.getTrainNum() != t2.getTrainNum() || t1.getPetlock() != t2.getPetlock() || this.zf(t1.getNedanResistance(), t2.getNedanResistance()) || this.zf(t1.getBeastSkills(), t2.getBeastSkills()) || this.zf(t1.getFourattributes(), t2.getFourattributes()) || this.zf(t1.getSkillData(), t2.getSkillData());
    }

    private boolean BD3(Baby t1, Baby t2) {
        return t1.getBabyid().compareTo(t2.getBabyid()) != 0 || t1.getRoleid().compareTo(t2.getRoleid()) != 0 || (int) t1.getBabyage() != (int) t2.getBabyage() || this.zf(t1.getOutcome(), t2.getOutcome()) || this.zf(t1.getTalents(), t2.getTalents()) || this.zf(t1.getParts(), t2.getParts());
    }

    private boolean BD6(Fly t1, Fly t2) {
        return (int) t1.getFlylvl() != (int) t2.getFlylvl() || (int) t1.getExp() != (int) t2.getExp();
    }

    private boolean BD4(Mount t1, Mount t2) {
        if ((int) t1.getMountlvl() != (int) t2.getMountlvl()) {
            return true;
        }
        if ((int) t1.getExp() != (int) t2.getExp()) {
            return true;
        }
        if (t1.getShouhu() != t2.getShouhu()) {
            return true;
        }
        if (t1.getSh() != t2.getSh()) {
            return true;
        }
        if ((int) t1.getSpri() != (int) t2.getSpri()) {
            return true;
        }
        if ((int) t1.getPower() != (int) t2.getPower()) {
            return true;
        }
        if ((int) t1.getBone() != (int) t2.getBone()) {
            return true;
        }
        if ((int) t1.getProficiency() != (int) t2.getProficiency()) {
            return true;
        }
        if ((int) t1.getUseNumber() != (int) t2.getUseNumber()) {
            return true;
        }
        if (t1.getSid() == null) {
            if (t2.getSid() != null) {
                return true;
            }
        } else if (t2.getSid() == null) {
            if (t1.getSid() != null) {
                return true;
            }
        } else if (t1.getSid().compareTo(t2.getSid()) != 0) {
            return true;
        }
        if (t1.getSid3() == null) {
            if (t2.getSid3() != null) {
                return true;
            }
        } else if (t2.getSid3() == null) {
            if (t1.getSid3() != null) {
                return true;
            }
        } else if (t1.getSid3().compareTo(t2.getSid3()) != 0) {
            return true;
        }
        if (t1.getOthrersid() == null) {
            if (t2.getOthrersid() != null) {
                return true;
            }
        } else if (t2.getOthrersid() == null) {
            if (t1.getOthrersid() != null) {
                return true;
            }
        } else if (t1.getOthrersid().compareTo(t2.getOthrersid()) != 0) {
            return true;
        }
        if (t1.getSid4() == null) {
            if (t2.getSid4() != null) {
                return true;
            }
        } else if (t2.getSid4() == null) {
            if (t1.getSid4() != null) {
                return true;
            }
        } else if (t1.getSid4().compareTo(t2.getSid4()) != 0) {
            return true;
        }
        if (t1.getSid5() == null) {
            if (t2.getSid5() != null) {
                return true;
            }
        } else if (t2.getSid5() == null) {
            if (t1.getSid5() != null) {
                return true;
            }
        } else if (t1.getSid5().compareTo(t2.getSid5()) != 0) {
            return true;
        }
        return false;
    }

    private boolean BD5(Pal t1, Pal t2) {
        return t1.getLvl() != t2.getLvl() || t1.getExp() != t2.getExp() || t1.getGrow() != t2.getGrow() || this.zf(t1.getParts(), t2.getParts());
    }

    public boolean zf(String v1, String v2) {
        if (v1 == null) {
            v1 = "";
        }
        if (v2 == null) {
            v2 = "";
        }
        return !v1.equals(v2);
    }

    public void salegoods() {
        System.err.println("重置藏宝阁订单状态");
        Jedis salesS = RedisPoolUntil.getJedis();
        salesS.del(RedisParameterUtil.SALESGOODS_STATUES);
        RedisPoolUntil.returnResource(salesS);
        List<Salegoods> salegoods = AllServiceUtil.getSalegoodsService().selectByAll();
        for (int i = 0; i < salegoods.size(); ++i) {
            Salegoods salegood = (Salegoods) salegoods.get(i);
            AllServiceUtil.getSalegoodsService().updateFlag(salegood.getSaleid(), salegood.getFlag());
            if (GameServer.redisReset == 4 && (int) salegood.getFlag() != 4 && (int) salegood.getSaletype() != 2) {
                System.err.println(salegood.getSalename() + "已被取回");
                AllServiceUtil.getSalegoodsService().deleteByPrimaryKey(salegood.getSaleid());
                if ((int) salegood.getSaletype() == 3 || (int) salegood.getSaletype() == 5) {
                    Goodstable goods2 = AllServiceUtil.getGoodsTableService().getGoodsByRgID(salegood.getOtherid());
                    if (goods2 != null && goods2.getRole_id().abs().compareTo(salegood.getRoleid()) == 0) {
                        AllServiceUtil.getGoodsrecordService().insert(goods2, null, Integer.valueOf(1), Integer.valueOf(13));
                        AllServiceUtil.getGoodsTableService().updateGoodsIndex(goods2, salegood.getRoleid(), null, null);
                    }
                } else if ((int) salegood.getSaletype() == 4) {
                    RoleSummoning pet = AllServiceUtil.getRoleSummoningService().selectRoleSummoningsByRgID(salegood.getOtherid());
                    if (pet != null && pet.getRoleid().abs().compareTo(salegood.getRoleid()) == 0) {
                        AllServiceUtil.getRoleSummoningService().updateRoleSummoningIndex(pet, salegood.getRoleid());
                        List<BigDecimal> goodses = pet.getGoods();
                        if (goodses != null) {
                            for (BigDecimal bigDecimal : goodses) {
                                Goodstable goodstable = AllServiceUtil.getGoodsTableService().getGoodsByRgID(bigDecimal);
                                if (goodstable == null) {
                                    continue;
                                } else {
                                    AllServiceUtil.getGoodsTableService().updateGoodsIndex(goodstable, salegood.getRoleid(), null, null);
                                }
                            }
                        }
                    }
                } else if ((int) salegood.getSaletype() == 6) {
                    Lingbao lingbao = AllServiceUtil.getLingbaoService().selectByPrimaryKey(salegood.getOtherid());
                    if (lingbao != null && lingbao.getRoleid().abs().compareTo(salegood.getRoleid()) == 0) {
                        AllServiceUtil.getLingbaoService().updateLingbaoIndex(lingbao, salegood.getRoleid());
                        if (lingbao.getFushis() != null && !"".equals(lingbao.getFushis())) {
                            String[] baos;
                            for (String string : baos = lingbao.getFushis().split("\\|")) {
                                Goodstable goodstable2 = AllServiceUtil.getGoodsTableService().getGoodsByRgID(new BigDecimal(string));
                                if (goodstable2 != null) {
                                    AllServiceUtil.getGoodsTableService().updateGoodsIndex(goodstable2, salegood.getRoleid(), null, null);
                                }
                            }
                        }
                    }
                } else if ((int) salegood.getSaletype() == 2) {
                    BigDecimal gold = AllServiceUtil.getRoleTableService().selectMoneyRoleID(salegood.getRoleid());
                    gold = gold.add(salegood.getOtherid());
                    if (gold.compareTo(new BigDecimal("999999999999")) > 0) {
                        gold = new BigDecimal("999999999999");
                    }
                    AllServiceUtil.getRoleTableService().updateMoneyRoleID(salegood.getRoleid(), gold);
                }
            }
        }
        if (GameServer.redisReset == 4) {
            RedisEqualWithSqlThread.AllToDatabase();
        }
    }

    public void databaseToCache() {
        RedisEqualWithSqlThread.AllToDataRole();
        if (GameServer.redisReset == 0) {
            System.err.println("重置redis");
            Jedis jedis = RedisPoolUntil.getJedis();
            long x = System.currentTimeMillis();
            Set<String> sets = jedis.keys(GameServer.area + "*");
            int size = 0;
            for (String value : sets) {
                ++size;
                if (!value.equals(RedisParameterUtil.GOODS_RECORD) || !value.equals(RedisParameterUtil.USER_REDIS) || !value.equals(RedisParameterUtil.USER_REDIS)) {
                    jedis.del(value);
                }
            }
            long y = System.currentTimeMillis();
            System.err.println("清空redis的key个数:" + size + " 耗时:" + (y - x));
            RedisPoolUntil.returnResource(jedis);
            System.err.println("同步数据库开始");
            List<Goodstable> goods = AllServiceUtil.getGoodsTableService().getAllGoods();
            for (Goodstable goodstable : goods) {
                RedisControl.insertKey(RedisParameterUtil.GOODS, goodstable.getRgid() + "", GsonUtil.getGsonUtil().getgson().toJson(goodstable));
                RedisControl.insertListRedis(RedisParameterUtil.GOODS, goodstable.getRole_id().toString(), goodstable.getRgid().toString());
                RedisControl.insertListRedis(RedisParameterUtil.GOODSID + "_" + goodstable.getRole_id().toString(), goodstable.getGoodsid().toString(), goodstable.getRgid().toString());
                RedisControl.insertListRedis(RedisParameterUtil.GOODSST + "_" + goodstable.getRole_id().toString(), goodstable.getStatus().toString(), goodstable.getRgid().toString());
            }
            System.err.println("物品完毕");
            List<Lingbao> lingbaos = AllServiceUtil.getLingbaoService().selectAllLingbao();
            for (Lingbao lingbao : lingbaos) {
                RedisControl.insertKey(RedisParameterUtil.LINGBAO, lingbao.getBaoid() + "", GsonUtil.getGsonUtil().getgson().toJson(lingbao));
                RedisControl.insertListRedis(RedisParameterUtil.LINGBAO, lingbao.getRoleid().toString(), lingbao.getBaoid() + "");
            }
            System.err.println("灵宝完毕");
            List<RoleSummoning> pets = AllServiceUtil.getRoleSummoningService().selectAllRoleSummonings();
            for (RoleSummoning roleSummoning : pets) {
                RedisControl.insertKey(RedisParameterUtil.PET, roleSummoning.getSid().toString(), GsonUtil.getGsonUtil().getgson().toJson(roleSummoning));
                RedisControl.insertListRedis(RedisParameterUtil.PET, roleSummoning.getRoleid().toString(), roleSummoning.getSid().toString());
            }
            System.err.println("召唤兽完毕");
            List<Baby> babys = AllServiceUtil.getBabyService().selectAllBaby();
            for (Baby baby : babys) {
                RedisControl.insertKey(RedisParameterUtil.BABY, baby.getBabyid().toString(), GsonUtil.getGsonUtil().getgson().toJson(baby));
                RedisControl.insertListRedis(RedisParameterUtil.BABY, baby.getRoleid().toString(), baby.getBabyid().toString());
            }
            System.err.println("孩子完毕");
            List<Mount> mounts = AllServiceUtil.getMountService().selectAllMounts();
            for (Mount mount : mounts) {
                List<MountSkill> mountskill = AllServiceUtil.getMountskillService().selectMountskillsByMountid(mount.getMid());
                mount.setMountskill(mountskill);
                RedisControl.insertKey(RedisParameterUtil.MOUNT, mount.getMid().toString(), GsonUtil.getGsonUtil().getgson().toJson(mount));
                RedisControl.insertListRedis(RedisParameterUtil.MOUNT, mount.getRoleid().toString(), mount.getMid().toString());
            }
            System.err.println("坐骑完毕");
            List<Car> cars = AllServiceUtil.getCarService().selectAllMounts();
            for (Car mount : cars) {
//                List<MountSkill> mountskill = AllServiceUtil.getMountskillService().selectMountskillsByMountid(mount.getMid());
//                mount.setMountskill(mountskill);
                RedisControl.insertKey(RedisParameterUtil.CAR, mount.getMid().toString(), GsonUtil.getGsonUtil().getgson().toJson(mount));
                RedisControl.insertListRedis(RedisParameterUtil.CAR, mount.getRoleid().toString(), mount.getMid().toString());
            }
            System.err.println("坐架完毕");
            List<Pal> pals = AllServiceUtil.getPalService().selectAllPalSql();
            for (Pal pal : pals) {
                RedisControl.insertKeyT(RedisParameterUtil.PAL, pal.getId().toString(), pal);
                RedisControl.insertListRedis(RedisParameterUtil.PAL, pal.getRoleId().toString(), pal.getId().toString());
            }
            System.err.println("伙伴完毕");
            List<Fly> flys = AllServiceUtil.getFlyService().selectAllFlys();
            for (Fly fly : flys) {
                RedisControl.insertKey(RedisParameterUtil.FLY, fly.getMid().toString(), GsonUtil.getGsonUtil().getgson().toJson(fly));
                RedisControl.insertListRedis(RedisParameterUtil.FLY, fly.getRoleid().toString(), fly.getMid().toString());
            }
            System.err.println("飞行器完毕");
            System.err.println("同步数据库结束");
        } else if (GameServer.redisReset == -1) {
            System.err.println("redis同步到数据库");
            this.dataTB();
        } else {
            System.err.println("不重置redis");
        }
        this.salegoods();
        this.InitZhu();
        if (GameServer.redisReset == 2) {
            this.sbwld();
        } else if (GameServer.redisReset == 3) {
            this.LSData();
        } else if (GameServer.redisReset == 4) {
            try {
                this.writeData();
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (GameServer.redisReset == 5) {
            try {
                this.loadData();
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (GameServer.redisReset == 6) {
            try {
                ZHBC();
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (GameServer.redisReset == 7) {
            try {
                ZHBC2();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void sbwld() {
        Gson gson = GsonUtil.getGsonUtil().getgson();
        Jedis jedis = RedisPoolUntil.getJedis();
        Set<String> set = jedis.hkeys(RedisParameterUtil.PET);
        RedisPoolUntil.returnResource(jedis);
        int size = 0;
        int z = 0;
        for (String ID : set) {
            if (++size >= 1000) {
                size = 0;
                if (++z % 10 == 0) {
                    System.err.println(z * 1000);
                }
                try {
                    Thread.sleep(1L);
                } catch (Exception ex) {
                }
            }
            RoleSummoning pet = AllServiceUtil.getRoleSummoningService().selectRoleSummoningsByRgID(new BigDecimal(ID));
            if (pet != null && pet.getSummoningid().equals("512")) {
                WriteOut.addtxt(GsonUtil.getGsonUtil().getgson().toJson(pet), 9999L);
                AllServiceUtil.getRoleSummoningService().deleteRoleSummoningBySid(pet.getSid());
            }
        }
    }

    public void LSData() {
        List<UserData> userDatas = this.getLSUserData();
        System.err.println("总数" + userDatas.size());
        for (int i = 0; i < userDatas.size(); ++i) {
            UserData userData = (UserData) userDatas.get(i);
            UserTable userTable = userData.getUserTable();
            while (AllServiceUtil.getUserTableService().findUserByUserNameAndUserPassword(userTable.getUsername(), null) != null) {
                userTable.setUsername(userTable.getUsername() + "(" + userData.getI() + ")");
            }
            AllServiceUtil.getUserTableService().insertIntoUser(userTable);
            LoginResult loginResult = userData.getLoginResult();
            loginResult.setUser_id(userTable.getUser_id());
            while (AllServiceUtil.getRoleTableService().selectRoleTableByRoleName(loginResult.getRolename()) != null) {
                loginResult.setRolename(loginResult.getRolename() + "(" + userData.getI() + ")");
            }
            AllServiceUtil.getRoleTableService().insertIntoRoleTable(loginResult);
            AllServiceUtil.getRoleTableService().updateRoleWhenExit(loginResult);
            List<Goodstable> goods = userData.getGoodstables();
            Map<BigDecimal, BigDecimal> ids = new HashMap<>();
            for (Goodstable goodstable : goods) {
                BigDecimal rgid = getGoods_pk();
                ids.put(goodstable.getRgid(), rgid);
                goodstable.setRgid(rgid);
                goodstable.setRole_id(loginResult.getRole_id());
            }
            for (Goodstable goodstable : goods) {
                if (Goodtype.EquipGem(goodstable.getType())) {
                    String[] vs = goodstable.getValue().split("\\|");
                    String extra = SuitComposeAction.getExtra(vs, SuitComposeAction.Extras[4]);
                    if (extra != null) {
                        String[] extras = extra.split("&");
                        StringBuffer buffer = new StringBuffer();
                        buffer.append("宝石属性");
                        for (int j = 1; j < extras.length; ++j) {
                            BigDecimal id = new BigDecimal(extras[j]);
                            if (ids.get(id) != null) {
                                id = (BigDecimal) ids.get(id);
                            }
                            buffer.append("&");
                            buffer.append(id);
                        }
                        goodstable.setValue(SuitComposeAction.newExtra(vs, 4, buffer.toString()));
                    }
                }
                RedisControl.insertKey(RedisParameterUtil.GOODS, goodstable.getRgid() + "", GsonUtil.getGsonUtil().getgson().toJson(goodstable));
                RedisControl.insertListRedis(RedisParameterUtil.GOODS, goodstable.getRole_id().toString(), goodstable.getRgid().toString());
                RedisControl.insertListRedis(RedisParameterUtil.GOODSID + "_" + goodstable.getRole_id().toString(), goodstable.getGoodsid().toString(), goodstable.getRgid().toString());
                RedisControl.insertListRedis(RedisParameterUtil.GOODSST + "_" + goodstable.getRole_id().toString(), goodstable.getStatus().toString(), goodstable.getRgid().toString());
            }
            List<Lingbao> lingbaos = userData.getLingbaos();
            for (Lingbao lingbao : lingbaos) {
                if (lingbao.getFushis() != null && !lingbao.getFushis().equals("")) {
                    StringBuffer buffer2 = new StringBuffer();
                    String[] vs2 = lingbao.getFushis().split("\\|");
                    for (int k = 0; k < vs2.length; ++k) {
                        BigDecimal id2 = new BigDecimal(vs2[k]);
                        if (ids.get(id2) != null) {
                            id2 = (BigDecimal) ids.get(id2);
                        }
                        if (buffer2.length() != 0) {
                            buffer2.append("|");
                        }
                        buffer2.append(id2);
                    }
                    lingbao.setFushis(buffer2.toString());
                }
                lingbao.setBaoid(getLingbao_pk());
                lingbao.setRoleid(loginResult.getRole_id());
                RedisControl.insertKey(RedisParameterUtil.LINGBAO, lingbao.getBaoid() + "", GsonUtil.getGsonUtil().getgson().toJson(lingbao));
                RedisControl.insertListRedis(RedisParameterUtil.LINGBAO, lingbao.getRoleid().toString(), lingbao.getBaoid() + "");
            }
            List<RoleSummoning> pets = userData.getPets();
            for (RoleSummoning roleSummoning : pets) {
                if (roleSummoning.getInnerGoods() != null && !roleSummoning.getInnerGoods().equals("")) {
                    StringBuffer buffer3 = new StringBuffer();
                    String[] v = roleSummoning.getInnerGoods().split("\\|");
                    for (int j = 0; j < v.length; ++j) {
                        if (!v[j].equals("")) {
                            BigDecimal id = new BigDecimal(v[j]);
                            if (ids.get(id) != null) {
                                id = (BigDecimal) ids.get(id);
                            }
                            if (buffer3.length() != 0) {
                                buffer3.append("|");
                            }
                            buffer3.append(id);
                        }
                    }
                    roleSummoning.setInnerGoods(buffer3.toString());
                }
                if (roleSummoning.getStye() != null && roleSummoning.getStye().length() > 1) {
                    StringBuffer buffer3 = new StringBuffer();
                    String[] v = roleSummoning.getStye().split("\\|");
                    buffer3.append(v[0]);
                    for (int j = 1; j < v.length; ++j) {
                        String[] vs3 = v[j].split("-");
                        if (vs3.length < 2) {
                            buffer3.append("|");
                            buffer3.append(v[j]);
                        } else {
                            BigDecimal id3 = new BigDecimal(vs3[1]);
                            if (ids.get(id3) != null) {
                                id3 = (BigDecimal) ids.get(id3);
                            }
                            buffer3.append("|");
                            for (int l = 0; l < vs3.length; ++l) {
                                if (l != 0) {
                                    buffer3.append("-");
                                }
                                if (l == 1) {
                                    buffer3.append(id3);
                                } else {
                                    buffer3.append(vs3[l]);
                                }
                            }
                        }
                    }
                    roleSummoning.setStye(buffer3.toString());
                }
                roleSummoning.setSid(getPet_pk());
                roleSummoning.setRoleid(loginResult.getRole_id());
                RedisControl.insertKey(RedisParameterUtil.PET, roleSummoning.getSid().toString(), GsonUtil.getGsonUtil().getgson().toJson(roleSummoning));
                RedisControl.insertListRedis(RedisParameterUtil.PET, roleSummoning.getRoleid().toString(), roleSummoning.getSid().toString());
            }
            List<Baby> babys = userData.getBabys();
            for (Baby baby : babys) {
                baby.setBabyid(getBaby_pk());
                baby.setRoleid(loginResult.getRole_id());
                RedisControl.insertKey(RedisParameterUtil.BABY, baby.getBabyid().toString(), GsonUtil.getGsonUtil().getgson().toJson(baby));
                RedisControl.insertListRedis(RedisParameterUtil.BABY, baby.getRoleid().toString(), baby.getBabyid().toString());
            }
            List<Mount> mounts = userData.getMounts();
            for (Mount mount : mounts) {
                mount.setSid(null);
                mount.setOthrersid(null);
                mount.setSid3(null);
                mount.setMid(getMount_pk());
                mount.setRoleid(loginResult.getRole_id());
                RedisControl.insertKey(RedisParameterUtil.MOUNT, mount.getMid().toString(), GsonUtil.getGsonUtil().getgson().toJson(mount));
                RedisControl.insertListRedis(RedisParameterUtil.MOUNT, mount.getRoleid().toString(), mount.getMid().toString());
            }
            List<Fly> flys = userData.getFlys();
            for (Fly fly : flys) {
                fly.setMid(getFly_pk());
                fly.setRoleid(loginResult.getRole_id());
                RedisControl.insertKey(RedisParameterUtil.FLY, fly.getMid().toString(), GsonUtil.getGsonUtil().getgson().toJson(fly));
                RedisControl.insertListRedis(RedisParameterUtil.FLY, fly.getRoleid().toString(), fly.getMid().toString());
            }
        }
    }

    public List<UserData> getLSUserData() {
        List<UserData> userDatas = new ArrayList<>();
        for (int i = 1; i < 6; ++i) {
            String text = ReadTxtUtil.readFile1(ReadExelTool.class.getResource("/").getPath() + "lsteam(" + i + ").txt");
            List<UserData> datas;
            if (text == null || text.equals("")) {
                datas = new ArrayList<>();
            } else {
                lsteamBean lsteamBean = (lsteamBean) GsonUtil.getGsonUtil().getgson().fromJson(text, lsteamBean.class);
                datas = lsteamBean.getUserDatas();
                if (datas == null) {
                    datas = new ArrayList<>();
                }
            }
            for (int j = 0; j < datas.size(); ++j) {
                ((UserData) datas.get(j)).setI(i);
                userDatas.add(datas.get(j));
            }
        }
        return userDatas;
    }

    public static long date2TimeStamp(String date_str, String format) {
        if (date_str == null || date_str.equals("") || date_str.equals("0")) {
            return 0L;
        }
        try {
            SimpleDateFormat sdf = new SimpleDateFormat(format);
            return sdf.parse(date_str).getTime();
        } catch (Exception e) {
            e.printStackTrace();
            return 0L;
        }
    }

    public void writeData() throws Exception {
        List<Friend> friends = AllServiceUtil.getFriendService().allFriend();
        FriendTransplant friendTransplant = new FriendTransplant(friends);
        CreateTextUtil.createFile(ReadExelTool.class.getResource("/").getPath() + "/GetTXT/HQFriend.txt", GZip.gZip(GsonUtil.getGsonUtil().getgson().toJson(friendTransplant).getBytes()));
        friends = null;
        friendTransplant = null;
        List<Gang> gangs = AllServiceUtil.getGangService().findAllGang();
        GangTransplant gangTransplant = new GangTransplant(gangs);
        CreateTextUtil.createFile(ReadExelTool.class.getResource("/").getPath() + "/GetTXT/HQGang.txt", GZip.gZip(GsonUtil.getGsonUtil().getgson().toJson(gangTransplant).getBytes()));
        gangs = null;
        gangTransplant = null;
        long TIME = System.currentTimeMillis() / 1000L;
        int SIZE = 0;
        List<UserDataBean> userDataBeans = new ArrayList<>();
        List<UserTable> allUserTables = AllServiceUtil.getUserTableService().findAllUser();
        for (int i = 0; i < allUserTables.size(); ++i) {
            System.err.println(i + ":" + allUserTables.size());
            UserTable userTable = (UserTable) allUserTables.get(i);
            Thread.sleep(100L);
            List<RoleDataBean> roleDataBeans = new ArrayList<>();
            List<LoginResult> loginResults = AllServiceUtil.getUserTableService().findRoleByUserNameAndPassword(userTable.getUsername(), userTable.getUserpwd(), null);
            for (int j = 0; j < loginResults.size(); ++j) {
                LoginResult loginResult = (LoginResult) loginResults.get(j);
                RoleDataBean bean = new RoleDataBean(loginResult, AllServiceUtil.getPackRecordService().selectByPrimaryKey(loginResult.getRole_id()), AllServiceUtil.getGoodsTableService().getGoodsByRoleID(loginResult.getRole_id()), AllServiceUtil.getRoleSummoningService().selectRoleSummoningsByRoleID(loginResult.getRole_id()), AllServiceUtil.getMountService().selectMountsByRoleID(loginResult.getRole_id()), AllServiceUtil.getLingbaoService().selectLingbaoByRoleID(loginResult.getRole_id()), AllServiceUtil.getBabyService().selectBabyByRolename(loginResult.getRole_id()), AllServiceUtil.getTitletableService().selectRoleAllTitle(loginResult.getRole_id()), AllServiceUtil.getFlyService().selectFlysByRoleID(loginResult.getRole_id()));
                roleDataBeans.add(bean);
            }
            UserDataBean userDataBean = new UserDataBean(userTable, roleDataBeans);
            userDataBeans.add(userDataBean);
            if (userDataBeans.size() >= 75) {
                ++SIZE;
                CreateTextUtil.createFile(ReadExelTool.class.getResource("/").getPath() + "/GetTXT/HQUser" + SIZE + ".txt", GZip.gZip(GsonUtil.getGsonUtil().getgson().toJson(new TransplantBean(userDataBeans)).getBytes()));
                userDataBeans.clear();
            }
        }
        ++SIZE;
        CreateTextUtil.createFile(ReadExelTool.class.getResource("/").getPath() + "/GetTXT/HQUser" + SIZE + ".txt", GZip.gZip(GsonUtil.getGsonUtil().getgson().toJson(new TransplantBean(userDataBeans)).getBytes()));
        userDataBeans.clear();
    }

    public void loadData() throws Exception {
        String QZ = GameServer.QZ;
        Map<BigDecimal, Gang> gangMap = new HashMap<>();
        Map<BigDecimal, BigDecimal> roleMap = new HashMap<>();
        byte[] text = ReadTxtUtil.readFile(ReadExelTool.class.getResource("/").getPath() + "/GetTXT/HQGang.txt");
        if (text != null) {
            text = GZip.unGZip(text);
            String msg = new String(text);
            GangTransplant gangTransplant = (GangTransplant) GsonUtil.getGsonUtil().getgson().fromJson(msg, GangTransplant.class);
            for (int i = 0; i < gangTransplant.getGangs().size(); ++i) {
                Gang gang = (Gang) gangTransplant.getGangs().get(i);
                while (AllServiceUtil.getGangService().findGangByGangName(gang.getGangname()) != null) {
                    gang.setGangname(gang.getGangname() + QZ);
                }
                BigDecimal yid = gang.getGangid();
                AllServiceUtil.getGangService().createGang(gang);
                Gang newGang = AllServiceUtil.getGangService().findGangByGangName(gang.getGangname());
                gang.setGangid(newGang.getGangid());
                AllServiceUtil.getGangService().updateGang(gang);
                gangMap.put(yid, gang);
            }
        }
        int kh = 0;
        List<String> filePaths = GetFilePaths(ReadExelTool.class.getResource("/").getPath() + "/GetTXT", "HQUser");
        for (int z = 0; z < filePaths.size(); ++z) {
            try {
                byte[] text2 = ReadTxtUtil.readFile((String) filePaths.get(z));
                if (text2 != null) {
                    text2 = GZip.unGZip(text2);
                    String msg2 = new String(text2);
                    TransplantBean transplantBean = (TransplantBean) GsonUtil.getGsonUtil().getgson().fromJson(msg2, TransplantBean.class);
                    for (int j = 0; j < transplantBean.getList().size(); ++j) {
                        System.err.println(z + ":" + filePaths.size() + "__" + j + ":" + transplantBean.getList().size());
                        UserDataBean userData = (UserDataBean) transplantBean.getList().get(j);
                        if (userData.getRoleDataBeans().size() == 0) {
                            ++kh;
                            System.err.println("空号" + kh);
                        } else {
                            Thread.sleep(100L);
                            UserTable userTable = userData.getUserTable();
                            while (AllServiceUtil.getUserTableService().findUserByUserNameAndUserPassword(userTable.getUsername(), null) != null) {
                                userTable.setUsername(userTable.getUsername() + QZ);
                            }
                            AllServiceUtil.getUserTableService().insertIntoUser(userTable);
                            AllServiceUtil.getUserTableService().updateUser(userTable);
                            for (int k = 0; k < userData.getRoleDataBeans().size(); ++k) {
                                RoleDataBean roleDataBean = (RoleDataBean) userData.getRoleDataBeans().get(k);
                                LoginResult loginResult = roleDataBean.getLoginResult();
                                loginResult.setUser_id(userTable.getUser_id());
                                while (AllServiceUtil.getRoleTableService().selectRoleTableByRoleName(loginResult.getRolename()) != null) {
                                    loginResult.setRolename(loginResult.getRolename() + QZ);
                                }
                                BigDecimal yid2 = loginResult.getRole_id();
                                BigDecimal gang_id = loginResult.getGang_id();
                                if (gang_id != null) {
                                    Gang gang2 = (Gang) gangMap.get(gang_id);
                                    if (gang2 != null) {
                                        loginResult.setGang_id(gang2.getGangid());
                                        loginResult.setGangname(gang2.getGangname());
                                    }
                                }
                                AllServiceUtil.getRoleTableService().insertIntoRoleTable(loginResult);
                                AllServiceUtil.getRoleTableService().updateRoleWhenExit(loginResult);
                                roleMap.put(yid2, loginResult.getRole_id());
                                PackRecord packRecord = roleDataBean.getPackRecord();
                                if (packRecord != null) {
                                    packRecord.setRoleId(loginResult.getRole_id());
                                    AllServiceUtil.getPackRecordService().insert(packRecord);
                                    AllServiceUtil.getPackRecordService().updateByPrimaryKeySelective(packRecord);
                                }
                                List<Goodstable> goods = roleDataBean.getGoodstables();
                                Map<BigDecimal, BigDecimal> ids = new HashMap<>();
                                for (Goodstable goodstable : goods) {
                                    BigDecimal rgid = getGoods_pk();
                                    ids.put(goodstable.getRgid(), rgid);
                                    goodstable.setRgid(rgid);
                                    goodstable.setRole_id(loginResult.getRole_id());
                                }
                                for (Goodstable goodstable : goods) {
                                    if (Goodtype.EquipGem(goodstable.getType())) {
                                        String[] vs = goodstable.getValue().split("\\|");
                                        String extra = SuitComposeAction.getExtra(vs, SuitComposeAction.Extras[4]);
                                        if (extra != null) {
                                            String[] extras = extra.split("&");
                                            StringBuffer buffer = new StringBuffer();
                                            buffer.append("宝石属性");
                                            for (int l = 1; l < extras.length; ++l) {
                                                BigDecimal id = new BigDecimal(extras[l]);
                                                if (ids.get(id) != null) {
                                                    id = (BigDecimal) ids.get(id);
                                                }
                                                buffer.append("&");
                                                buffer.append(id);
                                            }
                                            goodstable.setValue(SuitComposeAction.newExtra(vs, 4, buffer.toString()));
                                        }
                                    }
                                    RedisControl.insertKey(RedisParameterUtil.GOODS, goodstable.getRgid() + "", GsonUtil.getGsonUtil().getgson().toJson(goodstable));
                                    RedisControl.insertListRedis(RedisParameterUtil.GOODS, goodstable.getRole_id().toString(), goodstable.getRgid().toString());
                                    RedisControl.insertListRedis(RedisParameterUtil.GOODSID + "_" + goodstable.getRole_id().toString(), goodstable.getGoodsid().toString(), goodstable.getRgid().toString());
                                    RedisControl.insertListRedis(RedisParameterUtil.GOODSST + "_" + goodstable.getRole_id().toString(), goodstable.getStatus().toString(), goodstable.getRgid().toString());
                                    RedisControl.insertController(RedisParameterUtil.GOODS, goodstable.getRgid().toString(), "1");
                                }
                                List<Lingbao> lingbaos = roleDataBean.getLingbaos();
                                for (Lingbao lingbao : lingbaos) {
                                    if (lingbao.getFushis() != null && !lingbao.getFushis().equals("")) {
                                        StringBuffer buffer2 = new StringBuffer();
                                        String[] vs2 = lingbao.getFushis().split("\\|");
                                        for (int m = 0; m < vs2.length; ++m) {
                                            BigDecimal id2 = new BigDecimal(vs2[m]);
                                            if (ids.get(id2) != null) {
                                                id2 = (BigDecimal) ids.get(id2);
                                            }
                                            if (buffer2.length() != 0) {
                                                buffer2.append("|");
                                            }
                                            buffer2.append(id2);
                                        }
                                        lingbao.setFushis(buffer2.toString());
                                    }
                                    lingbao.setBaoid(getLingbao_pk());
                                    lingbao.setRoleid(loginResult.getRole_id());
                                    RedisControl.insertKey(RedisParameterUtil.LINGBAO, lingbao.getBaoid() + "", GsonUtil.getGsonUtil().getgson().toJson(lingbao));
                                    RedisControl.insertListRedis(RedisParameterUtil.LINGBAO, lingbao.getRoleid().toString(), lingbao.getBaoid() + "");
                                    RedisControl.insertController(RedisParameterUtil.LINGBAO, lingbao.getBaoid().toString(), "1");
                                }
                                List<RoleSummoning> pets = roleDataBean.getPets();
                                for (RoleSummoning roleSummoning : pets) {
                                    if (roleSummoning.getInnerGoods() != null && !roleSummoning.getInnerGoods().equals("")) {
                                        StringBuffer buffer3 = new StringBuffer();
                                        String[] v = roleSummoning.getInnerGoods().split("\\|");
                                        for (int l = 0; l < v.length; ++l) {
                                            if (!v[l].equals("")) {
                                                BigDecimal id = new BigDecimal(v[l]);
                                                if (ids.get(id) != null) {
                                                    id = (BigDecimal) ids.get(id);
                                                }
                                                if (buffer3.length() != 0) {
                                                    buffer3.append("|");
                                                }
                                                buffer3.append(id);
                                            }
                                        }
                                        roleSummoning.setInnerGoods(buffer3.toString());
                                    }
                                    if (roleSummoning.getStye() != null && roleSummoning.getStye().length() > 1) {
                                        StringBuffer buffer3 = new StringBuffer();
                                        String[] v = roleSummoning.getStye().split("\\|");
                                        buffer3.append(v[0]);
                                        for (int l = 1; l < v.length; ++l) {
                                            String[] vs3 = v[l].split("-");
                                            if (vs3.length < 2) {
                                                buffer3.append("|");
                                                buffer3.append(v[l]);
                                            } else {
                                                BigDecimal id3 = new BigDecimal(vs3[1]);
                                                if (ids.get(id3) != null) {
                                                    id3 = (BigDecimal) ids.get(id3);
                                                }
                                                buffer3.append("|");
                                                for (int k2 = 0; k2 < vs3.length; ++k2) {
                                                    if (k2 != 0) {
                                                        buffer3.append("-");
                                                    }
                                                    if (k2 == 1) {
                                                        buffer3.append(id3);
                                                    } else {
                                                        buffer3.append(vs3[k2]);
                                                    }
                                                }
                                            }
                                        }
                                        roleSummoning.setStye(buffer3.toString());
                                    }
                                    roleSummoning.setSid(getPet_pk());
                                    roleSummoning.setRoleid(loginResult.getRole_id());
                                    RedisControl.insertKey(RedisParameterUtil.PET, roleSummoning.getSid().toString(), GsonUtil.getGsonUtil().getgson().toJson(roleSummoning));
                                    RedisControl.insertListRedis(RedisParameterUtil.PET, roleSummoning.getRoleid().toString(), roleSummoning.getSid().toString());
                                    RedisControl.insertController(RedisParameterUtil.PET, roleSummoning.getSid().toString(), "1");
                                }
                                List<Baby> babys = roleDataBean.getBabys();
                                for (Baby baby : babys) {
                                    baby.setBabyid(getBaby_pk());
                                    baby.setRoleid(loginResult.getRole_id());
                                    RedisControl.insertKey(RedisParameterUtil.BABY, baby.getBabyid().toString(), GsonUtil.getGsonUtil().getgson().toJson(baby));
                                    RedisControl.insertListRedis(RedisParameterUtil.BABY, baby.getRoleid().toString(), baby.getBabyid().toString());
                                    RedisControl.insertController(RedisParameterUtil.BABY, baby.getBabyid().toString(), "1");
                                }
                                List<Mount> mounts = roleDataBean.getMounts();
                                for (Mount mount : mounts) {
                                    mount.setSid(null);
                                    mount.setOthrersid(null);
                                    mount.setSid3(null);
                                    mount.setMid(getMount_pk());
                                    mount.setRoleid(loginResult.getRole_id());
                                    if (mount.getMountskill() != null) {
                                        for (MountSkill mountSkill : mount.getMountskill()) {
                                            mountSkill.setMid(mount.getMid());
                                            AllServiceUtil.getMountskillService().insertMountskills(mountSkill);
                                        }
                                    }
                                    RedisControl.insertKey(RedisParameterUtil.MOUNT, mount.getMid().toString(), GsonUtil.getGsonUtil().getgson().toJson(mount));
                                    RedisControl.insertListRedis(RedisParameterUtil.MOUNT, mount.getRoleid().toString(), mount.getMid().toString());
                                    RedisControl.insertController(RedisParameterUtil.MOUNT, mount.getMid().toString(), "1");
                                }
                                List<Fly> flys = roleDataBean.getFlys();
                                for (Fly fly : flys) {
                                    fly.setMid(getFly_pk());
                                    fly.setRoleid(loginResult.getRole_id());
                                    RedisControl.insertKey(RedisParameterUtil.FLY, fly.getMid().toString(), GsonUtil.getGsonUtil().getgson().toJson(fly));
                                    RedisControl.insertListRedis(RedisParameterUtil.FLY, fly.getRoleid().toString(), fly.getMid().toString());
                                    RedisControl.insertController(RedisParameterUtil.FLY, fly.getMid().toString(), "1");
                                }
                                List<Titletable> titletables = roleDataBean.getTitletables();
                                for (Titletable titletable : titletables) {
                                    titletable.setRoleid(loginResult.getRole_id());
                                    AllServiceUtil.getTitletableService().createRoleTitle(titletable);
                                }
                            }
                        }
                    }
                    RedisEqualWithSqlThread.AllToDatabase();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static void ZHBC() throws Exception {
        long TIME = System.currentTimeMillis() / 1000L;
        int SIZE = 0;
        List<UserDataBean> userDataBeans = new ArrayList<>();
        List<UserTable> allUserTables = AllServiceUtil.getUserTableService().findAllUser();
        for (int i = 0; i < allUserTables.size(); ++i) {
            UserTable userTable = (UserTable) allUserTables.get(i);
            long time = date2TimeStamp(userTable.getUSERLASTLOGIN(), "yyyy-MM-dd HH:mm:ss") / 1000L;
            if (TIME - time <= 2592000L) {
                List<RoleDataBean> roleDataBeans = new ArrayList<>();
                UserDataBean userDataBean = new UserDataBean(userTable, roleDataBeans);
                userDataBeans.add(userDataBean);
                if (userDataBeans.size() >= 2000) {
                    ++SIZE;
                    CreateTextUtil.createFile(ReadExelTool.class.getResource("/").getPath() + "/GetTXT/HQUser" + SIZE + ".txt", GZip.gZip(GsonUtil.getGsonUtil().getgson().toJson(new TransplantBean(userDataBeans)).getBytes()));
                    userDataBeans.clear();
                }
            }
        }
        ++SIZE;
        CreateTextUtil.createFile(ReadExelTool.class.getResource("/").getPath() + "/GetTXT/HQUser" + SIZE + ".txt", GZip.gZip(GsonUtil.getGsonUtil().getgson().toJson(new TransplantBean(userDataBeans)).getBytes()));
        userDataBeans.clear();
    }

    public static void ZHBC2() throws Exception {
        String QZ = GameServer.QZ;
        List<String> filePaths = GetFilePaths(ReadExelTool.class.getResource("/").getPath() + "/GetTXT", "HQUser");
        for (int z = 0; z < filePaths.size(); ++z) {
            try {
                byte[] text1 = ReadTxtUtil.readFile((String) filePaths.get(z));
                if (text1 != null) {
                    text1 = GZip.unGZip(text1);
                    String msg = new String(text1);
                    TransplantBean transplantBean = (TransplantBean) GsonUtil.getGsonUtil().getgson().fromJson(msg, TransplantBean.class);
                    for (int i = 0; i < transplantBean.getList().size(); ++i) {
                        System.err.println(z + ":" + filePaths.size() + "__" + i + ":" + transplantBean.getList().size());
                        UserDataBean userData = (UserDataBean) transplantBean.getList().get(i);
                        UserTable userTable = userData.getUserTable();
                        UserTable newUserTable = null;
                        String userName = userTable.getUsername();
                        int size = 0;
                        while (userName != null) {
                            if (++size > 5) {
                                System.err.println(userName);
                                break;
                            } else {
                                newUserTable = AllServiceUtil.getUserTableService().findUserByUserNameAndUserPassword(userName, null);
                                if (newUserTable == null || newUserTable.getUser_id().compareTo(new BigDecimal(126593439446L)) <= 0) {
                                    userName += QZ;
                                    newUserTable = null;
                                } else {
                                    userName = null;
                                }
                            }
                        }
                        if (newUserTable != null) {
                            newUserTable.setUser_id(userTable.getUser_id());
                            AllServiceUtil.getUserTableService().updateUser(userTable);
                        }
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static List<String> GetFilePaths(String paths, String QZ) {
        File file = new File(paths);
        File[] array = file.listFiles();
        List<String> wuqi = new ArrayList<>();
        for (int i = 0; i < array.length; ++i) {
            if (array[i].getPath().indexOf(QZ) != -1) {
                System.err.println(array[i].getPath());
                wuqi.add(array[i].getPath());
            }
        }
        return wuqi;
    }

    public static List removeDuplicate(List list) {
        List listTemp = new ArrayList<>();
        for (int i = 0; i < list.size(); ++i) {
            if (!listTemp.contains(list.get(i))) {
                listTemp.add(list.get(i));
            }
        }
        return listTemp;
    }

    public static synchronized BigDecimal getBaby_pk() {
        return RedisCacheUtil.baby_pk = RedisCacheUtil.baby_pk.add(RedisCacheUtil.ADD);
    }

    public static synchronized BigDecimal getGoods_pk() {
        return RedisCacheUtil.goods_pk = RedisCacheUtil.goods_pk.add(RedisCacheUtil.ADD);
    }

    public static synchronized BigDecimal getSell_pk() {
        return RedisCacheUtil.sell_pk = RedisCacheUtil.sell_pk.add(RedisCacheUtil.ADD);
    }

    public static synchronized BigDecimal getMount_pk() {
        return RedisCacheUtil.mount_pk = RedisCacheUtil.mount_pk.add(RedisCacheUtil.ADD);
    }

    public static synchronized BigDecimal getCar_pk() {
        return RedisCacheUtil.car_pk = RedisCacheUtil.car_pk.add(RedisCacheUtil.ADD);
    }

    public static synchronized BigDecimal getFly_pk() {
        return RedisCacheUtil.fly_pk = RedisCacheUtil.fly_pk.add(RedisCacheUtil.ADD);
    }

    public static synchronized BigDecimal getPet_pk() {
        return RedisCacheUtil.pet_pk = RedisCacheUtil.pet_pk.add(RedisCacheUtil.ADD);
    }

    public static synchronized BigDecimal getLingbao_pk() {
        return RedisCacheUtil.lingbao_pk = RedisCacheUtil.lingbao_pk.add(RedisCacheUtil.ADD);
    }

    public static synchronized BigDecimal getPal_pk() {
        return RedisCacheUtil.pal_pk = RedisCacheUtil.pal_pk.add(RedisCacheUtil.ADD);
    }

    public static synchronized BigDecimal getxuanbao_pk() {
        xuanbao_pk = xuanbao_pk.add(ADD);
        return xuanbao_pk;
    }

    public static synchronized long getRecord_pk() {
        return ++RedisCacheUtil.record_pk;
    }

    public static synchronized BigDecimal getRole_pk() {
        if (Objects.nonNull(RedisCacheUtil.role_pk)) {
            RedisCacheUtil.role_pk = RedisControl.getRoleId(Integer.parseInt(RedisCacheUtil.role_pk.toString()));
        } else {
            RedisCacheUtil.role_pk = RedisControl.getRoleId(1);
        }
        return RedisCacheUtil.role_pk;
    }

    public static synchronized BigDecimal getUser_pk() {
        return RedisCacheUtil.user_pk = RedisCacheUtil.user_pk.add(RedisCacheUtil.ADD);
    }

    public static synchronized BigDecimal getOneAreanNotes_pk() {
        return RedisCacheUtil.oneAreanNotes_pk = RedisCacheUtil.oneAreanNotes_pk.add(RedisCacheUtil.ADD);
    }

    public static void resetOneArenaTime() {
        long time = System.currentTimeMillis();
        time -= 259200000L;
        RedisCacheUtil.oneAreanNotes_min = AllServiceUtil.getOneArenaNotesService().selectMaxID(TimeUntil.getPastDate(time));
        if (RedisCacheUtil.oneAreanNotes_min == null) {
            RedisCacheUtil.oneAreanNotes_min = new BigDecimal(0);
        }
    }

    public static void reset() {
        StringBuffer buffer = new StringBuffer();
        buffer.append("最高在线人数");
        buffer.append(GameServer.inlineMax);
        buffer.append("__连接次数");
        buffer.append(MainServerHandler.a);
        buffer.append("__连接异常次数");
        buffer.append(MainServerHandler.b);
        buffer.append("__物品使用规则异常次数");
        buffer.append(PackChangeAction.size);
        buffer.append((GameServer.getPortNumber() == 7109) ? redisMonitor() : "");
        buffer.append("__物品缓存清除:");
        RedisCacheUtil.goodBean.reset(buffer);
        buffer.append("__召唤兽缓存清除:");
        RedisCacheUtil.petBean.reset(buffer);
        buffer.append("__灵宝缓存清除:");
        RedisCacheUtil.lingbaoBean.reset(buffer);
        buffer.append("__孩子缓存清除:");
        RedisCacheUtil.babyBean.reset(buffer);
        buffer.append("__坐骑缓存清除:");
        RedisCacheUtil.mountBean.reset(buffer);
        buffer.append("__坐架缓存清除:");
        RedisCacheUtil.carBean.reset(buffer);
        buffer.append("__伙伴缓存清除:");
        RedisCacheUtil.palBean.reset(buffer);
        System.err.println(buffer.toString());
    }

    public static String redisMonitor() {
        Jedis salesS = RedisPoolUntil.getJedis();
        String value = salesS.info("Stats");
        String[] values = value.split("\\r?\\n");
        if (values[2].startsWith("total_commands_processed:")) {
            long time = System.currentTimeMillis();
            long processed = Long.parseLong(values[2].substring(25, values[2].length()));
            if (RedisCacheUtil.monitor.time != 0L) {
                long maxsec = (processed - RedisCacheUtil.monitor.processed) / ((time - RedisCacheUtil.monitor.time) / 1000L);
                if (maxsec > RedisCacheUtil.monitor.MaxSec) {
                    RedisCacheUtil.monitor.MaxSec = maxsec;
                }
            }
            RedisCacheUtil.monitor.processed = processed;
            RedisCacheUtil.monitor.time = time;
        }
        if (values[3].startsWith("instantaneous_ops_per_sec:")) {
            long sec = Long.parseLong(values[3].substring(26, values[3].length()));
            if (sec > RedisCacheUtil.monitor.redis_MaxSec) {
                RedisCacheUtil.monitor.redis_MaxSec = sec;
            }
        }
        RedisPoolUntil.returnResource(salesS);
        return RedisCacheUtil.monitor.getString();
    }

    static {
        RedisCacheUtil.ADD = new BigDecimal(1000);
        RedisCacheUtil.oneAreanNotes_min = new BigDecimal(0);
        RedisCacheUtil.goodBean = new RedisCacheBean(1);
        RedisCacheUtil.petBean = new RedisCacheBean(2);
        RedisCacheUtil.lingbaoBean = new RedisCacheBean(3);
        RedisCacheUtil.babyBean = new RedisCacheBean(4);
        RedisCacheUtil.mountBean = new RedisCacheBean(5);
        RedisCacheUtil.palBean = new RedisCacheBean(6);
        RedisCacheUtil.flyBean = new RedisCacheBean(7);
        RedisCacheUtil.carBean = new RedisCacheBean(8);
        RedisCacheUtil.monitor = new Monitor();
    }

    static class Monitor {
        private long time;
        private long processed;
        private long redis_MaxSec;
        private long MaxSec;

        public String getString() {
            return "#redis最高qps" + this.redis_MaxSec + "#换算最高qps" + this.MaxSec;
        }
    }
}
