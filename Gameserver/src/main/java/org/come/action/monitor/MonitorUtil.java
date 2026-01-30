package org.come.action.monitor;

import org.come.until.ReadTxtUtil;
import org.come.tool.ReadExelTool;
import org.come.tool.WriteOut;
import org.come.until.GsonUtil;
import org.come.entity.Goodstable;
import org.come.entity.Record;
import org.come.until.AllServiceUtil;
import java.math.BigDecimal;
import java.util.concurrent.ConcurrentHashMap;
import come.tool.Role.PartJade;

public class MonitorUtil
{
    private static MPoint lotteryM;
    private static MPoint stallM;
    private static MPoint dropQM1;
    private static MPoint dropQM2;
    private static MPoint dropHM;
    private static PartJade dropCJ;
    private static PartJade moneyM;
    private static MPoint card;
    private static long UpMoney;
    private static long MAXBY;
    private static MonitorMoney money;
    private static MPoint HdxMoney;
    private static MPoint HdxMoney1;
    private static ConcurrentHashMap<BigDecimal, MonitorRole> sumMap;
    
    public static void addCJ(int type, int v) {
        MonitorUtil.dropCJ.setJade(type, v);
    }
    
    public static boolean isUpMoney(int type, long v) {
        v = Math.abs(v);
        if (v >= MonitorUtil.UpMoney) {
            MonitorUtil.moneyM.setJade(type, 1);
            return true;
        }
        return false;
    }
    
    public static void addCard(int type, int v) {
        if (type == 0) {
            MonitorUtil.card.setKey(MonitorUtil.card.getKey() + (long)v);
        }
        else {
            MonitorUtil.card.setValue(MonitorUtil.card.getValue() + (long)v);
        }
    }
    
    public static MPoint getLotteryM() {
        return MonitorUtil.lotteryM;
    }
    
    public static void setLotteryM(MPoint lotteryM) {
        MonitorUtil.lotteryM = lotteryM;
    }
    
    public static MPoint getStallM() {
        return MonitorUtil.stallM;
    }
    
    public static void setStallM(MPoint stallM) {
        MonitorUtil.stallM = stallM;
    }
    
    public static MPoint getDropQM1() {
        return MonitorUtil.dropQM1;
    }
    
    public static void setDropQM1(MPoint dropQM1) {
        MonitorUtil.dropQM1 = dropQM1;
    }
    
    public static MPoint getDropQM2() {
        return MonitorUtil.dropQM2;
    }
    
    public static void setDropQM2(MPoint dropQM2) {
        MonitorUtil.dropQM2 = dropQM2;
    }
    
    public static MPoint getDropHM() {
        return MonitorUtil.dropHM;
    }
    
    public static void setDropHM(MPoint dropHM) {
        MonitorUtil.dropHM = dropHM;
    }
    
    public static MonitorMoney getMoney() {
        return MonitorUtil.money;
    }
    
    public static void setMoney(MonitorMoney money) {
        MonitorUtil.money = money;
    }
    
    public static MPoint getHdxMoney() {
        return MonitorUtil.HdxMoney;
    }
    
    public static void setHdxMoney(MPoint hdxMoney) {
        MonitorUtil.HdxMoney = hdxMoney;
    }
    
    public static MPoint getHdxMoney1() {
        return MonitorUtil.HdxMoney1;
    }
    
    public static void setHdxMoney1(MPoint hdxMoney1) {
        MonitorUtil.HdxMoney1 = hdxMoney1;
    }
    
    public static int getBSumBase(BigDecimal roleID) {
        MonitorRole role = (MonitorRole)MonitorUtil.sumMap.get(roleID);
        if (role == null) {
            return 0;
        }
        return role.getBSum();
    }
    
    public static int getBSum(BigDecimal roleID) {
        MonitorRole role = (MonitorRole)MonitorUtil.sumMap.get(roleID);
        if (role == null) {
            role = new MonitorRole();
            MonitorUtil.sumMap.put(roleID, role);
        }
        return role.addBSum();
    }
    
    public static int getSum(BigDecimal roleID, int type) {
        MonitorRole role = (MonitorRole)MonitorUtil.sumMap.get(roleID);
        if (role != null) {
            return (type == 0) ? role.getKSum() : role.getXSum();
        }
        return 0;
    }
    
    public static void addSum(BigDecimal roleID, int type) {
        MonitorRole role = (MonitorRole)MonitorUtil.sumMap.get(roleID);
        if (role == null) {
            role = new MonitorRole();
            MonitorUtil.sumMap.put(roleID, role);
        }
        if (type == 0) {
            role.addKSum();
        }
        else {
            role.addXSum();
        }
    }
    
    public static int addBY(BigDecimal roleID, int num, long moeny) {
        MonitorRole role = (MonitorRole)MonitorUtil.sumMap.get(roleID);
        if (role == null) {
            role = new MonitorRole();
            MonitorUtil.sumMap.put(roleID, role);
        }
        long sy = MonitorUtil.MAXBY - role.getAddBY();
        int max = (int)(sy / moeny);
        if (max <= 0) {
            return 0;
        }
        if (num > max) {
            num = max;
        }
        role.setAddBY(role.getAddBY() + moeny * (long)num);
        return num;
    }
    
    public static void reset() {
        MonitorUtil.sumMap.clear();
    }
    
    public static void ASSize() {
        StringBuffer buffer = new StringBuffer();
        buffer.append("仙玉总量:");
        buffer.append(AllServiceUtil.getUserTableService().selectAllCodecard());
        buffer.append("__充值总量:");
        buffer.append(AllServiceUtil.getUserTableService().selectAllPayintegration());
        buffer.append("__大话币总量:");
        buffer.append(AllServiceUtil.getUserTableService().selectAllGold());
        MonitorUtil.money.toString(buffer);
        MonitorUtil.money.reset();
        AllServiceUtil.getRecordService().insert(new Record(6, buffer.toString()));
    }
    
    public static void toSting() {
        StringBuffer buffer = new StringBuffer();
        buffer.append("刮刮乐:");
        buffer.append(MonitorUtil.lotteryM.getKey());
        buffer.append(":");
        buffer.append(MonitorUtil.lotteryM.getValue() / 10000L);
        buffer.append("W,摆摊税收:");
        buffer.append(MonitorUtil.stallM.getKey());
        buffer.append(":");
        buffer.append(MonitorUtil.stallM.getValue() / 10000L);
        buffer.append("W,战斗任务掉落:");
        buffer.append(MonitorUtil.dropQM1.getKey());
        buffer.append(":");
        buffer.append(MonitorUtil.dropQM1.getValue() / 10000L);
        buffer.append("W,前端Robot掉落:");
        buffer.append(MonitorUtil.dropQM2.getKey());
        buffer.append(":");
        buffer.append(MonitorUtil.dropQM2.getValue() / 10000L);
        buffer.append("W,后端掉落");
        buffer.append(MonitorUtil.dropHM.getKey());
        buffer.append(":");
        buffer.append(MonitorUtil.dropHM.getValue() / 10000L);
        buffer.append("W,抽奖1使用次数");
        buffer.append(MonitorUtil.dropCJ.getJade1());
        buffer.append("。");
        buffer.append("大于10E的金额流动统计,交易次数:");
        buffer.append(MonitorUtil.moneyM.getJade1());
        buffer.append("给予次数:");
        buffer.append(MonitorUtil.moneyM.getJade2());
        buffer.append("摆摊次数:");
        buffer.append(MonitorUtil.moneyM.getJade3());
        buffer.append("。七十二变系统消耗大话币:");
        buffer.append(MonitorUtil.card.getKey() / 10000L);
        buffer.append("W,仙玉:");
        buffer.append(MonitorUtil.card.getValue());
        System.out.println(buffer.toString());
        AllServiceUtil.getRecordService().insert(new Record(0, buffer.toString()));
    }
    
    public static void HdxtoSting() {
        StringBuffer buffer = new StringBuffer();
        buffer.append("黄大小:大话币发放总金额");
        buffer.append(MonitorUtil.HdxMoney.getValue());
        buffer.append("，仙玉发放总金额");
        buffer.append(MonitorUtil.HdxMoney1.getValue());
        AllServiceUtil.getRecordService().insert(new Record(11, buffer.toString()));
    }
    
    public static String isGoodMonitor(Goodstable good, Goodstable goodstable, int I) {
        if (I != 0 && I != 1 && I != 2) {
            return "非修改范围类型";
        }
        if (good == null) {
            WriteOut.addtxt("物品找不到:" + GsonUtil.getGsonUtil().getgson().toJson(goodstable), 9999L);
            return "找不到物品";
        }
        if (goodstable.getRole_id() != null && good.getRole_id().compareTo(goodstable.getRole_id()) != 0) {
            String value = "使用物品角色ID改变:原:" + GsonUtil.getGsonUtil().getgson().toJson(good) + ":新:" + GsonUtil.getGsonUtil().getgson().toJson(goodstable);
            WriteOut.addtxt(value, 9999L);
            return value;
        }
        if (I == 2 && good.getType() != 80156L && good.getType() != 2012L && good.getType() != 729L && good.getType() != 2010L && good.getType() != 2011L && good.getType() != 2125L) {
            I = 1;
        }
        if (goodstable.getGoodsid() != null && !good.getGoodsid().equals(goodstable.getGoodsid())) {
            String value = "物品突变:原:" + GsonUtil.getGsonUtil().getgson().toJson(good) + ":新:" + GsonUtil.getGsonUtil().getgson().toJson(goodstable);
            WriteOut.addtxt(value, 9999L);
            return value;
        }
        if (good.getType() != goodstable.getType()) {
            String value = "物品突变:原:" + GsonUtil.getGsonUtil().getgson().toJson(good) + ":新:" + GsonUtil.getGsonUtil().getgson().toJson(goodstable);
            WriteOut.addtxt(value, 9999L);
            return value;
        }
        goodstable.setQhv(good.getQhv());
        goodstable.setQht(good.getQht());
        goodstable.setQhb(good.getQhb());
        if (I == 2) {
            if (good.getType() == 729L && goodstable.getValue() != null) {
                String[] vs1 = good.getValue().split("\\|");
                String[] vs2 = goodstable.getValue().split("\\|");
                int size1 = 0;
                int size2 = 0;
                for (int j = 0; j < vs1.length; ++j) {
                    String[] vss = vs1[j].split("=");
                    if (vss[0].equals("根骨") || vss[0].equals("灵性") || vss[0].equals("力量") || vss[0].equals("敏捷") || vss[0].equals("品质")) {
                        int p = Integer.parseInt(vss[1]);
                        size1 += p;
                    }
                }
                for (int j = 0; j < vs2.length; ++j) {
                    String[] vss = vs2[j].split("=");
                    if (vss[0].equals("根骨") || vss[0].equals("灵性") || vss[0].equals("力量") || vss[0].equals("敏捷") || vss[0].equals("品质")) {
                        int p = Integer.parseInt(vss[1]);
                        size2 += p;
                    }
                }
                if (size1 != size2) {
                    String value2 = "物品突变:原:" + GsonUtil.getGsonUtil().getgson().toJson(good) + ":新:" + GsonUtil.getGsonUtil().getgson().toJson(goodstable);
                    WriteOut.addtxt(value2, 9999L);
                    return value2;
                }
            }
        }
        else {
            if (goodstable.getValue() != null && !good.getValue().equals(goodstable.getValue())) {
                String value = "物品突变:原:" + GsonUtil.getGsonUtil().getgson().toJson(good) + ":新:" + GsonUtil.getGsonUtil().getgson().toJson(goodstable);
                WriteOut.addtxt(value, 9999L);
                return value;
            }
            if (goodstable.getGoodsname() != null && !good.getGoodsname().equals(goodstable.getGoodsname())) {
                String value = "物品突变:原:" + GsonUtil.getGsonUtil().getgson().toJson(good) + ":新:" + GsonUtil.getGsonUtil().getgson().toJson(goodstable);
                WriteOut.addtxt(value, 9999L);
                return value;
            }
        }
        int yUse = (int)good.getUsetime();
        int nUse = (int)goodstable.getUsetime();
        if (I == 1) {
            if (nUse >= yUse) {
                String value3 = "物品突变:原:" + GsonUtil.getGsonUtil().getgson().toJson(good) + ":新:" + GsonUtil.getGsonUtil().getgson().toJson(goodstable);
                WriteOut.addtxt(value3, 9999L);
                return value3;
            }
        }
        else if (nUse > yUse) {
            String value3 = "物品突变:原:" + GsonUtil.getGsonUtil().getgson().toJson(good) + ":新:" + GsonUtil.getGsonUtil().getgson().toJson(goodstable);
            WriteOut.addtxt(value3, 9999L);
            return value3;
        }
        return null;
    }
    
    static {
        MonitorUtil.UpMoney = 1L;
        MonitorUtil.MAXBY = 50000000L;
        MonitorUtil.sumMap = new ConcurrentHashMap<>();
        MonitorUtil.lotteryM = new MPoint();
        MonitorUtil.stallM = new MPoint();
        MonitorUtil.dropQM1 = new MPoint();
        MonitorUtil.dropQM2 = new MPoint();
        MonitorUtil.dropHM = new MPoint();
        MonitorUtil.HdxMoney = new MPoint();
        MonitorUtil.HdxMoney1 = new MPoint();
        MonitorUtil.dropCJ = new PartJade(0, 0);
        MonitorUtil.moneyM = new PartJade(0, 0);
        MonitorUtil.card = new MPoint();
        try {
            String text = ReadTxtUtil.readFile1(ReadExelTool.class.getResource("/").getPath() + "money.txt");
            if (text == null || text.equals("")) {
                MonitorUtil.money = new MonitorMoney();
            }
            else {
                MonitorUtil.money = (MonitorMoney)GsonUtil.getGsonUtil().getgson().fromJson(text, MonitorMoney.class);
            }
        }
        catch (Exception ex) {}
        if (MonitorUtil.money == null) {
            MonitorUtil.money = new MonitorMoney();
        }
    }
}
