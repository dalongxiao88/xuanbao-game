package org.come.action.lottery;

import org.come.bean.NChatBean;
import java.util.List;
import org.come.handler.SendMessage;
import org.come.until.GsonUtil;
import org.come.protocol.Agreement;
import java.math.BigDecimal;
import org.come.action.monitor.MonitorUtil;
import java.util.ArrayList;
import org.come.bean.QuackGameBean;
import org.come.server.GameServer;
import org.come.bean.LoginResult;
import io.netty.channel.ChannelHandlerContext;
import java.util.Random;
import java.util.Map;

public class PIXIU
{
    private static PIXIU pixiu;
    private int[] awards;
    private String sendAwards;
    private int[] lotterys;
    private int[] extras;
    private Map<Integer, Integer> itDouble;
    private long[] baseAwards;
    private Random random;
    private static long BASE;
    private static long DOOR;
    private static long COST;
    static int a2;
    static int a3;
    static int a4;
    static int a5;
    
    public static PIXIU getPixiu() {
        if (PIXIU.pixiu == null) {
            PIXIU.pixiu = new PIXIU();
        }
        return PIXIU.pixiu;
    }
    
    public PIXIU() {
        this.random = new Random();
        this.extras = LotteryUtil.getint1();
        this.itDouble = LotteryUtil.getmap1();
        this.lotterys = LotteryUtil.getint2();
        this.resetAwards();
        this.initAward();
    }
    
    public void lottery(ChannelHandlerContext ctx) {
        LoginResult loginResult = (LoginResult)GameServer.getAllLoginRole().get(ctx);
        if (loginResult == null) {
            return;
        }
        QuackGameBean bean = new QuackGameBean();
        bean.setType(2);
        StringBuffer buffer = new StringBuffer();
        List<Integer> zj = null;
        for (int i = 0; i < 20; ++i) {
            int id = this.lotterys[this.random.nextInt(this.lotterys.length)];
            if (this.isAward(id)) {
                if (zj == null) {
                    zj = new ArrayList<>();
                    zj.add(Integer.valueOf(id));
                }
                else if (!zj.contains(Integer.valueOf(id))) {
                    zj.add(Integer.valueOf(id));
                }
            }
            else if (i <= 4 && (zj == null || zj.size() <= 1)) {
                for (int j = 0; j <= i; ++j) {
                    id = this.lotterys[this.random.nextInt(this.lotterys.length)];
                    if (this.isAward(id)) {
                        if (zj == null) {
                            zj = new ArrayList<>();
                            zj.add(Integer.valueOf(id));
                            break;
                        }
                        else if (!zj.contains(Integer.valueOf(id))) {
                            zj.add(Integer.valueOf(id));
                            break;
                        }
                    }
                }
            }
            buffer.append(id);
            buffer.append("|");
        }
        int size = (zj == null) ? 0 : zj.size();
        long money = this.getBaseAward(size);
        int extra = this.getExtra();
        buffer.append(extra);
        money *= (long)this.getDS(extra);
        MonitorUtil.getLotteryM().add(money);
        bean.setPetmsg(buffer.toString());
        bean.setMoney(new BigDecimal(money));
        loginResult.setGold(loginResult.getGold().add(bean.getMoney()));
        MonitorUtil.getMoney().addD(money, 2);
        SendMessage.sendMessageToSlef(ctx, Agreement.getAgreement().getfivemsgAgreement(GsonUtil.getGsonUtil().getgson().toJson(bean)));
        if (money >= PIXIU.DOOR) {
            this.hh(loginResult.getRolename(), money, size, extra);
        }
    }
    
    public static void main(String[] args) {
        long size = 5000000L;
        for (int i = 0; i < 5000000; ++i) {
            getPixiu().lottery(null);
        }
        System.out.println("2个:" + PIXIU.a2);
        System.out.println("3个:" + PIXIU.a3);
        System.out.println("4个:" + PIXIU.a4);
        System.out.println("5个:" + PIXIU.a5);
        System.out.println((double)(PIXIU.a2 + PIXIU.a3 + PIXIU.a4 + PIXIU.a5) / (double)size);
    }
    
    public void hh(String role, long money, int size, int extra) {
        StringBuffer buffer = new StringBuffer();
        buffer.append("#c00ffff");
        buffer.append(role);
        buffer.append("#Y召唤兽");
        buffer.append(size);
        buffer.append("种守护召唤兽,进入宝库");
        if (size == 5) {
            buffer.append("牛刀小试,");
        }
        else if (size == 4) {
            buffer.append("如鱼得水,");
        }
        else if (size == 3) {
            buffer.append("大显身手,");
        }
        else {
            buffer.append("炉火纯青,");
        }
        String v = this.getExtra(extra);
        if (v != null) {
            buffer.append("兼有神兽");
            buffer.append(v);
            buffer.append("相助,");
        }
        buffer.append("喜获得#G");
        buffer.append(money);
        buffer.append("银两");
        NChatBean bean = new NChatBean();
        bean.setId(5);
        bean.setMessage(buffer.toString());
        String msg = Agreement.getAgreement().chatAgreement(GsonUtil.getGsonUtil().getgson().toJson(bean));
        SendMessage.sendMessageToAllRoles(msg);
    }
    
    public String getExtra(int extra) {
        switch (extra) {
            case 400107: {
                return "垂云叟";
            }
            case 400108: {
                return "范式之魂";
            }
            case 400109: {
                return "浪淘沙";
            }
            case 400110: {
                return "五叶";
            }
            case 400111: {
                return "颜如玉";
            }
            case 400127: {
                return "白泽";
            }
            case 400121: {
                return "画中仙";
            }
            case 400120: {
                return "年";
            }
            default: {
                return null;
            }
        }
    }
    
    public int getExtra() {
        if (this.random.nextInt(25) == 0) {
            if (this.random.nextInt(25) <= 7) {
                if (this.random.nextInt(5) == 0) {
                    return this.extras[this.random.nextInt(this.extras.length - 1)];
                }
                return this.extras[this.random.nextInt(5)];
            }
            else {
                return this.extras[this.extras.length - 1];
            }
        }
        else {
            return 0;
        }
    }
    
    public int getDS(int extra) {
        Integer bs = (Integer)this.itDouble.get(Integer.valueOf(extra));
        if (bs != null) {
            return (int)bs;
        }
        return 1;
    }
    
    public long getBaseAward(int sum) {
        if (sum >= 2) {
            switch (sum) {
                case 2: {
                    ++PIXIU.a2;
                    break;
                }
                case 3: {
                    ++PIXIU.a3;
                    break;
                }
                case 4: {
                    ++PIXIU.a4;
                    break;
                }
                case 5: {
                    ++PIXIU.a5;
                    break;
                }
            }
        }
        sum -= 2;
        if (sum < 0) {
            return 0L;
        }
        return this.baseAwards[sum];
    }
    
    public boolean isAward(int id) {
        for (int i = 0; i < this.awards.length; ++i) {
            if (this.awards[i] == id) {
                return true;
            }
        }
        return false;
    }
    
    public void resetAwards() {
        this.awards = LotteryUtil.getint3(this.lotterys);
        StringBuffer buffer = new StringBuffer();
        for (int i = 0; i < this.awards.length; ++i) {
            if (i != 0) {
                buffer.append("|");
            }
            buffer.append(this.awards[i]);
        }
        QuackGameBean bean = new QuackGameBean();
        bean.setType(1);
        bean.setMoney(null);
        bean.setPetmsg(buffer.toString());
        this.sendAwards = Agreement.getAgreement().getfivemsgAgreement(GsonUtil.getGsonUtil().getgson().toJson(bean));
    }
    
    public void initAward() {
        (this.baseAwards = new long[4])[0] = PIXIU.BASE * 4L;
        this.baseAwards[1] = PIXIU.BASE * 8L;
        this.baseAwards[2] = PIXIU.BASE * 40L;
        this.baseAwards[3] = PIXIU.BASE * 100L;
    }
    
    public String getSendAwards() {
        return this.sendAwards;
    }
    
    static {
        PIXIU.BASE = 800000L;
        PIXIU.DOOR = PIXIU.BASE * 20L;
        PIXIU.COST = PIXIU.BASE * 5L;
        PIXIU.a2 = 0;
        PIXIU.a3 = 0;
        PIXIU.a4 = 0;
        PIXIU.a5 = 0;
    }
}
