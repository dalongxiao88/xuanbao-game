package org.come.good;

import java.util.Collections;
import java.util.HashSet;
import java.util.Arrays;
import org.come.until.GsonUtil;
import java.math.BigDecimal;
import org.come.until.GoodsListFromServerUntil;
import org.come.socket.SendMessageUntil;
import org.come.socket.Agreement;
import java.util.Iterator;
import java.util.Map;
import java.util.List;
import org.come.bean.BabyResult;
import com.tool.pet.BabyProperty;
import org.come.mouslisten.GoodsMouslisten;
import org.come.until.FormsManagement;
import org.come.until.Util;
import org.come.entity.Baby;
import org.come.Frame.ZhuFrame;
import org.come.until.UserMessUntil;
import org.come.Frame.TestChildJframe;
import org.come.entity.Goodstable;
import java.util.Set;

public class BabyGood
{
    private static final Set<String> interestingGoods;
    
    public static void BabyGoods(Goodstable goodstable, long type) {
        Baby baby = UserMessUntil.getbaby(TestChildJframe.getTestChildJframe().getTestChildJpanel().getBabyid());
        if (baby == null) {
            ZhuFrame.getZhuJpanel().addPrompt2("你没有选中的孩子");
        }
        else if (type == 50L) {
            training(baby, goodstable);
        }
        else if (type == 51L) {
            babySkill(baby, goodstable);
        }
        else if (type >= 54L && type <= 61L) {
            babyParts(baby, goodstable);
        }
        else if (type == 53L) {
            ageReturn(baby, goodstable);
        }
    }
    
    public static void training(Baby baby, Goodstable goodstable) {
        String[] v = goodstable.getValue().split("\\|");
        v = v[Util.random.nextInt(v.length)].split("=");
        String k = v[0];
        v = v[1].split("-");
        int min = Integer.parseInt(v[0]);
        int max = Integer.parseInt(v[1]);
        int ch = max - min + 1;
        int value = Util.random.nextInt(ch) + min;
        if (value <= min + 1 || value >= max - 1) {
            value = Util.random.nextInt(ch) + min;
        }
        switch (k.hashCode()) {
            case 668086: {
                if (k.equals("内力")) {
                    baby.setNeili(Integer.valueOf((int)baby.getNeili() + value));
                    break;
                }
                else {
                    break;
                }
            }
            case 694695: {
                if (k.equals("名气")) {
                    baby.setMingqi(Integer.valueOf((int)baby.getMingqi() + value));
                    break;
                }
                else {
                    break;
                }
            }
            case 702347: {
                if (k.equals("叛逆")) {
                    baby.setPanni(Integer.valueOf((int)baby.getPanni() + value));
                    break;
                }
                else {
                    break;
                }
            }
            case 749574: {
                if (k.equals("孝心")) {
                    baby.setXiaoxin(Integer.valueOf((int)baby.getXiaoxin() + value));
                    break;
                }
                else {
                    break;
                }
            }
            case 834401: {
                if (k.equals("智力")) {
                    baby.setZhili(Integer.valueOf((int)baby.getZhili() + value));
                    break;
                }
                else {
                    break;
                }
            }
            case 893844: {
                if (k.equals("气质")) {
                    baby.setQizhi(Integer.valueOf((int)baby.getQizhi() + value));
                    break;
                }
                else {
                    break;
                }
            }
            case 942494: {
                if (k.equals("玩性")) {
                    baby.setWanxing(Integer.valueOf((int)baby.getWanxing() + value));
                    break;
                }
                else {
                    break;
                }
            }
            case 1037451: {
                if (k.equals("耐力")) {
                    baby.setNaili(Integer.valueOf((int)baby.getNaili() + value));
                    break;
                }
                else {
                    break;
                }
            }
            case 1169860: {
                if (k.equals("道德")) {
                    baby.setDaode(Integer.valueOf((int)baby.getDaode() + value));
                    break;
                }
                else {
                    break;
                }
            }
        }
        baby.setBabyage(Integer.valueOf((int)baby.getBabyage() + 1080));
        adult(baby);
        UpdaBaby(baby);
        if (FormsManagement.getframe(1).isVisible()) {
            TestChildJframe.getTestChildJframe().getTestChildJpanel().ShowBaby(baby);
        }
        String msg = "孩子在#G " + k + " #Y上增加了#G " + value + " #Y点属性";
        ZhuFrame.getZhuJpanel().addPrompt2(msg);
        goodstable.goodxh(1);
        GoodsMouslisten.gooduse(goodstable, 1);
    }
    
    public static void adult(Baby baby) {
        if ((int)baby.getBabyage() >= 6480 && (baby.getOutcome() == null || baby.getOutcome().equals(""))) {
            List<BabyResult> babyResults = UserMessUntil.getAllBabyResult().getAllBabyResults();
            Map<String, Integer> maps = BabyProperty.getBabyProperty().getProperty(baby, (baby != null) ? baby.getpartAll() : null);
            int size = 0;
            int max = 0;
            String maxtype = "";
            for (String key : maps.keySet()) {
                int value = (int)maps.get(key);
                size += value;
                if (value >= max) {
                    max = value;
                    maxtype = key;
                }
            }
            BabyResult babyResult = null;
        LOOP:
            for (int i = babyResults.size() - 1; i >= 0; --i) {
                babyResult = (BabyResult)babyResults.get(i);
                if (babyResult.getT1().equals("无") || babyResult.getT1().equals(maxtype)) {
                    String[] t2s = babyResult.getT2().split("\\|");
                    int j = 0;
                    while (j < t2s.length) {
                        String[] vs = t2s[j].split("=");
                        int value2 = size;
                        if (!vs[0].equals("总")) {
                            value2 = (int)maps.get(vs[0]);
                        }
                        vs = vs[1].split("\\-");
                        int bmin = Integer.parseInt(vs[0]);
                        int bmax = Integer.parseInt(vs[1]);
                        if (value2 >= bmin) {
                            if (value2 > bmax) {
                                continue LOOP;
                            }
                            else {
                                ++j;
                            }
                        }
                        else {
                            continue LOOP;
                        }
                    }
                    break;
                }
            }
            if ((int)baby.getChildSex() == 0) {
                baby.setOutcome(babyResult.getNan());
            }
            else {
                baby.setOutcome(babyResult.getNv());
            }
            ZhuFrame.getZhuJpanel().addPrompt2("孩子获得#G" + baby.getOutcome() + "结局");
        }
    }
    
    public static void ageReturn(Baby baby, Goodstable goodstable) {
        if ((int)baby.getBabyage() < 360) {
            ZhuFrame.getZhuJpanel().addPrompt2("孩子太小不能使用");
        }
        else if ((int)baby.getBabyage() >= 6480) {
            ZhuFrame.getZhuJpanel().addPrompt2("回不去了#15");
        }
        else {
            String[] v = goodstable.getValue().split("-");
            int min = Integer.parseInt(v[0]);
            int max = Integer.parseInt(v[1]);
            int ch = max - min + 1;
            int value = Util.random.nextInt(ch) + min;
            int[] vs = { (int)baby.getQizhi(), (int)baby.getNeili(), (int)baby.getZhili(), (int)baby.getNaili(), (int)baby.getMingqi(), (int)baby.getDaode(), (int)baby.getPanni(), (int)baby.getWanxing(), (int)baby.getXiaoxin() };
            int pin = value / 5;
            while (value > 0) {
                int a = 0;
                for (int j = 0; j < vs.length; ++j) {
                    if (vs[j] <= 0) {
                        ++a;
                    }
                }
                if (a >= 9) {
                    break;
                }
                else {
                    int j;
                    if ((j = pin) > value) {
                        j = value;
                    }
                    int p;
                    for (p = Util.random.nextInt(9); vs[p] == 0; p = Util.random.nextInt(9)) {}
                    if (j > vs[p]) {
                        j = vs[p];
                    }
                    int n = p;
                    vs[n] -= j;
                    value -= j;
                }
            }
            baby.setQizhi(Integer.valueOf(vs[0]));
            baby.setNeili(Integer.valueOf(vs[1]));
            baby.setZhili(Integer.valueOf(vs[2]));
            baby.setNaili(Integer.valueOf(vs[3]));
            baby.setMingqi(Integer.valueOf(vs[4]));
            baby.setDaode(Integer.valueOf(vs[5]));
            baby.setPanni(Integer.valueOf(vs[6]));
            baby.setWanxing(Integer.valueOf(vs[7]));
            baby.setXiaoxin(Integer.valueOf(vs[8]));
            baby.setBabyage(Integer.valueOf((int)baby.getBabyage() - 360));
            UpdaBaby(baby);
            if (FormsManagement.getframe(1).isVisible()) {
                TestChildJframe.getTestChildJframe().getTestChildJpanel().ShowBaby(baby);
            }
            ZhuFrame.getZhuJpanel().addPrompt2("孩子回到了一年前");
            goodstable.goodxh(1);
            GoodsMouslisten.gooduse(goodstable, 1);
        }
    }
    
    public static void babySkill(Baby baby, Goodstable goodstable) {
        String sendmes = Agreement.getAgreement().userbabyAgreement(goodstable.getRgid().toString() + "|" + baby.getBabyid());
        SendMessageUntil.toServer(sendmes);
    }
    
    public static void babySkillUP(Baby baby, Goodstable goodstable) {
    }
    
    public static void babyParts(Baby baby, Goodstable good2) {
        int type = (int)((long)good2.getType() - 54L) / 2;
        if (type < 0) {
            type = 0;
        }
        else if (type > 3) {
            type = 3;
        }
        if ((int)baby.getChildSex() == 0 && (long)good2.getType() % 2L != 0L && !interestingGood(good2)) {
            ZhuFrame.getZhuJpanel().addPrompt2("这是女孩子用的");
        }
        else if ((int)baby.getChildSex() == 1 && (long)good2.getType() % 2L == 0L && !interestingGood(good2)) {
            ZhuFrame.getZhuJpanel().addPrompt2("这是男孩子用的");
        }
        else {
            Goodstable good3 = (Goodstable)GoodsListFromServerUntil.fushis.get(baby.ChangePart(good2.getRgid(), type));
            if (TestChildJframe.getTestChildJframe().getTestChildJpanel().ChangeParts(baby, good3, good2, type)) {
                UpdaBaby(baby);
            }
            else if (good3 == null) {
                baby.ChangePart(new BigDecimal(-1), type);
            }
            else {
                baby.ChangePart(good3.getRgid(), type);
            }
        }
    }
    
    public static boolean interestingGood(Goodstable good2) {
        if (good2 == null) {
            return false;
        }
        String goodsName = good2.getGoodsname();
        return goodsName != null && BabyGood.interestingGoods.contains(goodsName);
    }
    
    public static void UpdaBaby(Baby baby) {
        String serverMes = null;
        try {
            serverMes = Agreement.getAgreement().updababy(GsonUtil.getGsonUtil().getgson().toJson(baby));
        }
        catch (Exception var3) {
            var3.printStackTrace();
        }
        SendMessageUntil.toServer(serverMes);
    }
    
    static {
        interestingGoods = Collections.unmodifiableSet(new HashSet(Arrays.asList(new String[] { "木筝", "宝螺筝", "楠木花奔筝", "红木山水画筝", "骨雕飞天筝", "庄子", "孟子", "论语", "道德经", "周易" })));
    }
}
