package org.come.until;

import java.text.DecimalFormat;

import org.come.entity.XuanBao;
import org.come.server.GameServer;
import org.come.entity.Lingbao;
import java.math.BigDecimal;
import java.util.List;
import java.util.Random;

public class SplitLingbaoValue
{
    static String[] v;
    static String[] v1;
    static String[] v2;
    public static Random random;
    static String[] fabaos;
    static String[] vk;
    
    public static Lingbao addling(String ling, BigDecimal roleid) {
        Lingbao bean = GameServer.getLingbao(ling);
        double min = 0.0;
        double max = 0.0;
        String[] v = bean.getBaospeed().split("-");
        min = Double.parseDouble(v[0]);
        max = Double.parseDouble(v[1]);
        double sp = getDouble(min, max, 0);
        bean.setBaospeed((sp + "").split("\\.")[0]);
        v = (bean.getBaoreply() + "").split("-");
        if (v.length > 1) {
            min = Double.parseDouble(v[0]);
            max = Double.parseDouble(v[1]);
            double ap = getDouble(min, max, 3);
            bean.setBaoreply(ap + "");
            bean.setBaoquality(BaoQuality(sp, ap));
        }
        v = (bean.getBaoap() + "").split("-");
        if (v.length > 1) {
            min = Double.parseDouble(v[0]);
            max = Double.parseDouble(v[1]);
            double ap = getDouble(min, max, 3);
            bean.setBaoap(ap + "");
            bean.setBaoquality(BaoQuality(sp, ap));
        }
        if (bean.getBaoquality() == null || bean.getBaoquality().equals("")) {
            bean.setBaoquality(BaoQuality(sp, 0.13));
        }
        bean.setLingbaolvl(new BigDecimal(1));
        bean.setLingbaoexe(new BigDecimal(0));
        bean.setTianfuskill(tianfu(bean.getGethard(), bean.getBaotype()));
        bean.setKangxing(kangxing(1));
        bean.setRoleid(roleid);
        bean.setOperation(null);
        AllServiceUtil.getLingbaoService().insertLingbao(bean);
        return bean;
    }
    
    public static Lingbao addfa(long id, BigDecimal roleid) {
        id -= 69001L;
        if (id > (long)SplitLingbaoValue.fabaos.length) {
            return null;
        }
        String fa = SplitLingbaoValue.fabaos[(int)id];
        Lingbao lingbao = new Lingbao();
        lingbao.setBaoname(fa);
        lingbao.setBaotype("法宝");
        lingbao.setBaoquality("把玩");
        lingbao.setLingbaolvl(new BigDecimal(1));
        lingbao.setLingbaoexe(new BigDecimal(0));
        lingbao.setBaoactive(Integer.valueOf(0));
        lingbao.setBaospeed("0");
        lingbao.setBaoreply("0");
        lingbao.setBaoshot("0");
        lingbao.setBaoap("0");
        lingbao.setResistshot("0");
        lingbao.setAssistance("0");
        lingbao.setSkin(101L + id + "");
        lingbao.setKangxing(kangxing(1));
        lingbao.setRoleid(roleid);
        lingbao.setOperation(null);
        AllServiceUtil.getLingbaoService().insertLingbao(lingbao);
        return lingbao;
    }
    
    public static double getDouble(double min, double max, int type) {
        StringBuffer a = new StringBuffer();
        for (int i = 0; i < type; ++i) {
            a.append("0");
        }
        max -= min;
        max = max / 4.0 * (double)SplitLingbaoValue.random.nextInt(5);
        DecimalFormat df = new DecimalFormat("#." + a);
        double b = (double)Double.valueOf(df.format(SplitLingbaoValue.random.nextDouble() * max + min));
        return b;
    }
    
    public static String BaoQuality(double sp, double ap) {
        sp = (double)((int)sp / 10 + 2);
        if (sp < 1.0) {
            sp = -(sp - 2.0);
        }
        ap = (double)((int)((ap - 0.01) / 0.02) + 1);
        int zhilian = (int)((ap + sp) / 4.0 + 0.5);
        switch (zhilian) {
            case 1: {
                return "把玩";
            }
            case 2: {
                return "贴身";
            }
            case 3: {
                return "珍藏";
            }
            case 4: {
                return "无价";
            }
            case 5: {
                return "传世";
            }
            default: {
                return "把玩";
            }
        }
    }
    
    public static String tianfu(String dengji, String type) {
        StringBuffer tianfu = new StringBuffer();
        if (type.equals("攻击")) {
            if (!dengji.equals("高")) {
                tianfu.append("低级" + suiji(SplitLingbaoValue.v1));
            }
            else {
                tianfu.append("高级" + suiji(SplitLingbaoValue.v1));
            }
        }
        else if (type.equals("辅助")) {
            String suiji = suiji(SplitLingbaoValue.v2);
            if ("招魂".equals(suiji)) {
                tianfu.append(suiji);
            }
            else if (!dengji.equals("高")) {
                tianfu.append("低级" + suiji);
            }
            else {
                tianfu.append("高级" + suiji);
            }
        }
        else if (!dengji.equals("高")) {
            tianfu.append("低级" + suiji(SplitLingbaoValue.v));
        }
        else {
            tianfu.append("高级" + suiji(SplitLingbaoValue.v));
        }
        if (!type.equals("低")) {
            String vs;
            for (vs = "高级" + suiji(SplitLingbaoValue.v); vs.equals(tianfu.toString()); vs = "高级" + suiji(SplitLingbaoValue.v)) {}
            tianfu.append("|" + vs);
        }
        return tianfu.toString();
    }
    
    public static String kangxing(int lvl) {
        StringBuffer buffer = new StringBuffer();
        buffer.append(suiji(SplitLingbaoValue.vk));
        buffer.append("=");
        int fudu = (lvl - 1) / 40 + 1;
        double a = getDouble((double)(fudu * 2) + 0.1, (double)(fudu * 2) + 0.1, 1);
        buffer.append(a);
        return buffer.toString();
    }
    
    public static String suiji(String[] v) {
        return v[SplitLingbaoValue.random.nextInt(v.length)];
    }
    
    static {
        SplitLingbaoValue.v = new String[] { "契合", "闪现", "活跃", "抵抗", "敏捷", "归根", "回灵", "灵动", "回光", "挣扎" };
        SplitLingbaoValue.v1 = new String[] { "契合", "闪现", "活跃", "抵抗", "敏捷", "归根", "回灵", "灵动", "回光", "挣扎", "根骨增强", "力量增强", "灵性增强", "敏捷增强" };
        SplitLingbaoValue.v2 = new String[] { "契合", "闪现", "活跃", "抵抗", "敏捷", "归根", "回灵", "灵动", "回光", "挣扎", "招魂", "根骨回生", "力量回生", "灵性回生", "敏捷回生", "招魂" };
        SplitLingbaoValue.random = new Random();
        SplitLingbaoValue.fabaos = new String[] { "银索金铃", "将军令", "大势锤", "七宝玲珑塔", "黑龙珠", "幽冥鬼手", "大手印", "绝情鞭", "情网", "宝莲灯", "金箍儿", "番天印", "锦襕袈裟", "白骨爪", "化蝶" };
        SplitLingbaoValue.vk = new String[] { "抗混乱", "抗封印", "抗物理", "抗遗忘", "抗鬼火", "抗风", "抗雷", "抗水", "抗火", "抗昏睡", "抗震慑", "抗中毒" };
    }

    public static XuanBao addxuan(String ling, BigDecimal roleid) {
        XuanBao bean = GameServer.getAllxuanbaos().get(Integer.parseInt(ling));
        List<XuanBao> xuanBaoList = AllServiceUtil.getXuanBaoService().selectLingbaoByRoleID(roleid);
        for (XuanBao xuanBao : xuanBaoList) {
            if (xuanBao.getId() == bean.getId()) {
                return null;
            }
        }
        bean.setEquipment(0);
        bean.setRoleid(roleid);
        bean.setNum(0);
        bean.setLvl(1);
        AllServiceUtil.getXuanBaoService().insertLingbao(bean);
        return bean;
    }
}
