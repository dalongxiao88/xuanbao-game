package come.tool.FightingData;

import java.util.concurrent.ConcurrentHashMap;

import org.come.model.Configure;
import org.come.server.GameServer;
import org.come.tool.WriteOut;

/**
 * 计算器
 *
 * @author Administrator
 */
public class Calculation {
    private static Calculation calculation;

    public static Calculation getCalculation() {
        if (Calculation.calculation == null) {
            Calculation.calculation = new Calculation();
        }
        return Calculation.calculation;
    }

    //0 抗 1忽视 2强 3伤害
    //人法计算器
    public boolean renfa(ManData mydata, ManData nomyadata, double jichu, String type) {
        return this.renfaCalculation(jichu, mydata.getsx(2, type), mydata.getsx(1, type), nomyadata.getsx(0, type));
    }

    public boolean renfa(ManData mydata, ManData nomyadata, double jichu, String type, double addHs, double addQF, double addKX) {
        int k = 0;
        if (mydata.getSkillType("8076") != null && nomyadata.xzstate(type) != null) {
            k = 5;
        }
        return this.renfaCalculation(jichu, mydata.getsx(2, type) + addHs, mydata.getsx(1, type) + addQF + (double) k, nomyadata.getsx(0, type) + addKX);
    }

    public boolean renfaCalculation(double jichu, double q, double hs, double k) {
        jichu = (jichu + hs - k) * (1.0 + q / 100.0);
        return (double) Battlefield.random.nextInt(108) < jichu;
    }

    /**
     * 物理攻击
     *
     * @param mydata
     * @param nomyadata
     * @param jichu
     * @param type
     * @param isZM
     * @param xs
     * @param battlefield
     * @return
     */
    public int PTGJ(ManData mydata, ManData nomyadata, Long jichu, String type, boolean isZM, double xs, Battlefield battlefield) {
        if (nomyadata.xzstate("屠巫剑") != null) {
            jichu = (long) (jichu * (1.0D + nomyadata.xzstate("屠巫剑").getStateEffect2() / 100.0D));
        }
        if (nomyadata.xzstate("凝霜印") != null) {
            jichu = (long) (jichu + nomyadata.xzstate("凝霜印").getStateEffect());
        }
        if (nomyadata.xzstate("长生幡") != null) {
            jichu = (long) (jichu * (1.0D - nomyadata.xzstate("长生幡").getStateEffect2() / 100.0D));
        }
        if (nomyadata.xzstate("斗魂帆") != null) {
            jichu = (long) (jichu * (1.0D - nomyadata.xzstate("斗魂帆").getStateEffect2() / 100.0D));
        }
        if (nomyadata.xzstate("断生契") != null) {
            jichu = (long) (jichu * (1.0D + nomyadata.xzstate("断生契").getStateEffect2() / 100.0D));
        }
        mydata.noMandata = nomyadata;
        double five = FiveLineSystem.getSwing(mydata, nomyadata);
        double w = 1.0 + mydata.getsx(3, "无") / 100.0;
        if (w > five) {
            five = w;
        }
        double hs = mydata.getsx(1, type.endsWith("反击") ? type : TypeUtil.PTGJ) * 0.5;
        long tmpAp = 0L;
        if (hs > 0.0) {
            tmpAp = Math.round((double) (long) jichu * (hs / 100.0)) + (long) jichu;
            jichu = tmpAp;
        }
        double kx = nomyadata.getsx(0, TypeUtil.PTGJ) / 100.0;
        kx *= Math.abs(1.0 - hs / 100.0);

        double fyz = nomyadata.xxywl;
        if (nomyadata.xzstate("千丝网") != null) {
            fyz += nomyadata.xzstate("千丝网").getStateEffect();
        }
        if (nomyadata.xzstate("凝霜印") != null) {
            fyz -= Math.min(nomyadata.xzstate("凝霜印").getStateEffect() * nomyadata.xzstate("凝霜印").getStateEffect5(), nomyadata.xzstate("凝霜印").getStateEffect2());
        }
        int hurt = (int) ((double) (long) jichu * (1.0 - kx) * five);
        hurt -= fyz;

        int total = 0;
        int d = 0;
        for (ManData manData : battlefield.fightingdata) {
            if (manData.getCamp() == nomyadata.getCamp()) {
                total++;
            }
            if (manData.getCamp() == nomyadata.getCamp() && manData.getMan() != nomyadata.getMan() && manData.getStates() == 0) {
                d++;
            }
        }
        if (nomyadata.getSkillId(30006) != null) {
            double p = (battlefield.CurrentRound <= 3) ? nomyadata.getSkillId(30006).getS1() : 0.0D;
            double p2 = (mydata.getType() == 1) ? nomyadata.getSkillId(30006).getP1() : 0.0D;
            double p3 = (d / total <= 0.4D) ? nomyadata.getSkillId(30006).getE1() : 0.0D;
            double o = nomyadata.getSkillId(30006).getSkillgain() + p + p2 + p3;
            if (nomyadata.getSkillId(30006) != null && Battlefield.isV(o)) {
                hurt = (int) (hurt * (1.0D - nomyadata.getSkillId(30006).getSkillgain1() / 100.0D));
            }
        }
        if (mydata.xzstate("淬魂锤") != null) {
            double p = mydata.xzstate("淬魂锤").getStateEffect() * mydata.xzstate("淬魂锤").getStateEffect3() / 100.0D;
            double p1 = (mydata.xzstate("淬魂锤").getSurplus() >= 997) ? (mydata.xzstate("淬魂锤").getStateEffect4() / 100.0D) : 0.0D;
            hurt = (int) (hurt * (1.0D - p - p1));
        }
        if (nomyadata.xzstate("沧溟露") != null) {
            hurt = (int) (hurt * (1.0D - nomyadata.xzstate("沧溟露").getStateEffect2() / 100.0D));
        }
        if (nomyadata.xzstate("冥河纱") != null) {
            hurt = (int) (hurt * (1.0D - nomyadata.xzstate("冥河纱").getStateEffect() / 100.0D));
        }
        if (mydata.xzstate("振魂鼓") != null) {
            double p = mydata.xzstate("振魂鼓").getStateEffect() * mydata.xzstate("振魂鼓").getStateEffect2() / 100.0D;
            double p2 = mydata.xzstate("振魂鼓").getStateEffect() * mydata.xzstate("振魂鼓").getStateEffect3() / 100.0D;
            double p1 = (mydata.xzstate("振魂鼓").getSurplus() >= 997) ? (mydata.xzstate("振魂鼓").getStateEffect4() / 100.0D) : 0.0D;
            hurt = (int) (hurt * (1.0D + p + p1 + p2));
        }
        /**伤害上限过滤*/
        if (hurt > 2139999999) {
            hurt = 1;
            WriteOut.addtxt("伤害上限:" + mydata.getType() + ":" + mydata.getManname() + ":" + mydata.getId(), 9999L);
        }
        if (isZM) {
            hurt = (int) ((double) hurt + (double) mydata.getHp() * 0.1 * (1.0 - kx));
        }
        hurt = (int) ((double) hurt * xs);
        return (hurt > 1) ? hurt : 1;
    }

    public int SMHurt(ManData mydata, ManData nomyadata, double jichu, double wg, String type, int death) {
        return this.SMHurt(mydata, nomyadata, jichu, wg, type, death, 0.0, 0.0, 0.0);
    }

    /**
     * 仙法鬼火
     *
     * @param mydata
     * @param nomyadata
     * @param jichu
     * @param wg
     * @param type
     * @param death
     * @param addHs
     * @param addQF
     * @param addKX
     * @return
     */
    public int SMHurt(ManData mydata, ManData nomyadata, double jichu, double wg, String type, int death, double addHs, double addQF, double addKX) {
        mydata.noMandata = nomyadata;
        if (type.equals("鬼火")) {
            if (++death > 10) {
                death = 10;
            }
            jichu *= 1.0 + (double) death / 16.0;
        }
        if (type.equals("疾风迅雷")) {
            type = "雷";
        }
        double five = FiveLineSystem.getSwing(mydata, nomyadata);
        double w = 1.0 + mydata.getsx(3, "无") / 100.0;
        if (w > five) {
            five = w;
        }
        double hs = mydata.getsx(1, type) + addHs;
        if (mydata.getSkillType("8075") != null && (nomyadata.xzstate("混乱") != null || nomyadata.xzstate("昏睡") != null || nomyadata.xzstate("遗忘") != null)) {
            hs += 5.0;
        }
        FightingSkill skill = mydata.getSkillType(TypeUtil.TZ_ZCCG);
        if (skill != null && nomyadata.xzstate("昏睡") != null) {
            hs += skill.getSkillhurt();
        }
        double kx = nomyadata.getsx(0, type) + addKX;
        double qf = 1.0 + mydata.getsx(2, type) / 100.0 + addQF;
        if (nomyadata.xzstate("8074") != null) {
            qf += 8.0;
        }
        hs = 1.0 + (hs - kx) / 100.0;
        int hurt = (int) ((jichu + mydata.getsx(3, type)) * hs * qf * five);
        if (wg != 0.0) {
            hurt = (int) ((double) hurt + wg * five);
        }
        //   hurt*=2;
        if (hurt > 2139999999) {
            hurt = 1;
            WriteOut.addtxt("伤害上限:" + mydata.getType() + ":" + mydata.getManname() + ":" + mydata.getId(), 9999L);
        }
        return (hurt > 1) ? hurt : 1;
    }

    public int xianfaCalculation(double jichu, double erwai, double q, double hs, double k, double five) {
        //最终数字=基础伤害*(1+强法/100)*(1+忽视/100-对方抗性/100)*五行或者无属性加成系数*(狂暴程度/100+1.5)
        jichu = (jichu + erwai) * (1.0 + q / 100.0) * (1.0 + hs / 100.0 - k / 100.0) * five;
        jichu = ((jichu > 1.0) ? jichu : 1.0);
        return (int) jichu;
    }

    //魔计算器
    //震慑hp伤害 mp伤害只算技能基础伤害
    public double mozs(ManData mydata, ManData nomyadata, double jichu) {
        return this.mozsCalculation(jichu, mydata.getsx(3, "无"), mydata.getsx(2, "震慑"), mydata.getsx(1, "震慑"), nomyadata.getsx(0, "震慑"), FiveLineSystem.getSwing(mydata, nomyadata));
    }

    public double mozsCalculation(double jichu, double w, double q, double hs, double k, double five) {
        //		(法术震慑伤害-对方抗震+忽视抗震慑)*(1+无属性伤害加成%)*(1+强吸)*五行加成;
        if (1.0 + w / 100.0 > five) {
            five = 1.0 + w / 100.0;
        }
        jichu = (jichu - k + hs) * (1.0 + q / 100.0) * five;
        jichu = ((jichu < 50.0) ? jichu : 50.0);
        jichu = ((jichu > 0.0) ? jichu : 0.0);
        return jichu;
    }

    public double mozs2(ManData mydata, ManData nomyadata, double jichu, double q) {
        return this.mozsCalculation2(jichu, mydata.getsx(3, "无"), mydata.getsx(2, "震慑") + q, mydata.getsx(1, "震慑"), nomyadata.getsx(0, "震慑"), FiveLineSystem.getSwing(mydata, nomyadata), mydata, nomyadata);
    }

    public double mozsCalculation2(double jichu, double w, double q, double hs, double k, double five, ManData manData, ManData nomyData) {
        double fssh = 0.0D;
        if (nomyData.xzstate("冥河纱") != null) {
            fssh = nomyData.xzstate("冥河纱").getStateEffect2();
        }
        jichu = (jichu + hs - k) * (1.0D + q / 100.0D);
        if (nomyData.xzstate("冥河纱") != null) {
            jichu = (int) (jichu * (1.0D - nomyData.xzstate("冥河纱").getStateEffect() / 100.0D));
        }
        if (1.0 + w / 100.0 > five) {
            five = 1.0 + w / 100.0;
        }
        jichu = (jichu - k + hs) * (1.0 + q / 100.0) * five;
        jichu = ((jichu > 0.0) ? jichu : 0.0);
        return jichu;
    }

    //魔加成   目前多少就是多少
    public double mofa(double jichu, ManData mydata, String type) {
        return this.mofaCalculation(jichu, mydata.getsx(2, type));
    }

    //魔加成   目前多少就是多少
    public double mofa(double jichu, ManData mydata, String type, double qian) {
        return this.mofaCalculation(jichu, mydata.getsx(2, type) + qian);
    }

    public double mofaCalculation(double jichu, double qian) {
        return jichu * (1.0 + qian / 100.0);
    }

    //鬼计算器
//	鬼三尸伤害
    public int sssh(ManData mydata, ManData nomyadata, double jichu) {
        return this.sssh(mydata, nomyadata, jichu, 0.0, 0.0, 0.0);
    }

    public int sssh(ManData mydata, ManData nomyadata, double jichu, double addQFS, double addQF, double addKX) {
        if (nomyadata.xzstate("斗魂帆") != null) {
            jichu *= 1.0D - nomyadata.xzstate("斗魂帆").getStateEffect2() / 100.0D;
        }
        if (nomyadata.xzstate("长生幡") != null) {
            jichu *= 1.0D - nomyadata.xzstate("长生幡").getStateEffect2() / 100.0D;
        }
        if (nomyadata.xzstate("断生契") != null) {
            jichu = (long) (jichu * (1.0D + nomyadata.xzstate("断生契").getStateEffect2() / 100.0D));
        }
        if (nomyadata.xzstate("屠巫剑") != null) {
            jichu = (long) (jichu * (1.0D + nomyadata.xzstate("屠巫剑").getStateEffect2() / 100.0D));
        }

        int hurt = this.ssshCalculation(jichu + mydata.getsx(3, "三尸") + addQFS, mydata.getsx(2, "三尸") + addQF, nomyadata.getsx(0, "三尸") + addKX, FiveLineSystem.getSwing(mydata, nomyadata), mydata, nomyadata);
        /**伤害上限过滤*/
        if (hurt > 2139999999) {
            hurt = 1;
            WriteOut.addtxt("伤害上限:" + mydata.getType() + ":" + mydata.getManname() + ":" + mydata.getId(), 9999L);
        }
        if (mydata.xzstate("淬魂锤") != null) {
            double p1 = (mydata.xzstate("淬魂锤").getSurplus() >= 997) ? (mydata.xzstate("淬魂锤").getStateEffect4() / 100.0D) : 0.0D;
            double p2 = mydata.xzstate("淬魂锤").getStateEffect3() / 100.0D;
            hurt = (int) (hurt * (1.0D - p1 - p2));
        }
        if (mydata.xzstate("振魂鼓") != null) {
            double p = mydata.xzstate("振魂鼓").getStateEffect() * mydata.xzstate("振魂鼓").getStateEffect2() / 100.0D;
            double p2 = mydata.xzstate("振魂鼓").getStateEffect() * mydata.xzstate("振魂鼓").getStateEffect3() / 100.0D;
            double p1 = (mydata.xzstate("振魂鼓").getSurplus() >= 997) ? (mydata.xzstate("振魂鼓").getStateEffect4() / 100.0D) : 0.0D;
            hurt = (int) (hurt * (1.0D + p + p1 + p2));
        }
        if (nomyadata.xzstate("沧溟露") != null) {
            hurt = (int) (hurt * (1.0D - nomyadata.xzstate("沧溟露").getStateEffect2() / 100.0D));
        }
        if (nomyadata.xzstate("冥河纱") != null) {
            hurt = (int) (hurt * (1.0D - nomyadata.xzstate("冥河纱").getStateEffect() / 100.0D));
        }
        return hurt;
    }

    public int ssshCalculation(double jichu, double q, double k, double five, ManData manData, ManData nomyData) {
        double gzsshh = Integer.parseInt("100");
        double fsfy = nomyData.xxyfs;
        if (nomyData.xzstate("冥河纱") != null) {
            fsfy += nomyData.xzstate("冥河纱").getStateEffect2();
        }
        if (nomyData.xzstate("凝霜印") != null) {
            fsfy -= Math.min(nomyData.xzstate("凝霜印").getStateEffect3() * nomyData.xzstate("凝霜印").getStateEffect5(), nomyData.xzstate("凝霜印").getStateEffect4());
        }
        if (nomyData.xzstate("冥河纱") != null) {
            jichu = (int) (jichu * (1.0D - nomyData.xzstate("冥河纱").getStateEffect() / 100.0D));
        }
        //(三尸法术伤害+装备强三尸伤害-抗三尸)×(1+强力克)×(1+五行伤害)
        jichu = (jichu + q - k-fsfy) * five;
        jichu = ((jichu > 1.0) ? jichu : 1.0);
        return (int) jichu;
    }

    //鬼三尸回血
    public int sshx(ManData mydata, ManData nomyadata, double jichu, double sh) {
        return this.sshxCalculation(jichu, sh, mydata.getsx(2, "三尸回血"));
    }

    public int sshxCalculation(double jichu, double sh, double q) {
        //回血量=三尸伤害×(法术回血程度+装备回血程度)×2
        jichu = sh * 2.0 * (jichu + q) / 100.0;
        jichu = ((jichu > 1.0) ? jichu : 1.0);
        return (int) jichu;
    }

    /**
     * 浩然正气技能
     */
    public long hrzq(ManData mydata, ManData nomyadata, double xs) {
        long sh = (long) this.hrzqCalculation(xs, (double) mydata.getMp_z(), (double) nomyadata.getMp_z());
        if (nomyadata.getType() == 2) {
            if (sh >= 95418L) {
                sh = 95418L;
            }
        } else if (sh >= 59600L) {
            sh = 59600L;
        }
        return sh;
    }

    //敌方最大法力*系数
    public int hrzqCalculation(double xs, double mymp, double nomymp) {
        nomymp -= mymp / 10.0;
        if (nomymp <= 0.0) {
            nomymp = 1.0;
        }
        return (int) (nomymp * xs / 100.0);
    }

    /**
     * 计算毒伤上限
     */
    public int getzdup(ManData mydata, FightingSkill skill, double qds, ManData nomyadata) {
//        ConcurrentHashMap<Integer, Configure> s = GameServer.getAllConfigure();
//        Configure configure = s.get(1);
//        double szd = Double.parseDouble(configure.getDshsz());
//        double qzds = mydata.getQuality().getQzds();
//        qzds *= szd;
//        qzds += qds;
//        double five = FiveLineSystem.getSwing(mydata, nomyadata);
//        if (five == 1.0) {
//            five = 1.0 + mydata.getsx(3, "无") / 100.0;
//        }
//        return (int)(skill.getSkillgain() * (1.0 + qzds / 100.0) * five);
        double qzds = mydata.getQuality().getQzds();
        double js = 103.6;
        qzds *= js;
        double five = FiveLineSystem.getSwing(mydata, nomyadata);
        if (five == 1) {
            five = 1 + mydata.getsx(3, "无") / 100.0D;
        }
        int re = (int) (skill.getSkillgain() * (1 + qzds / 100) * five);
        if (re < 0) {
            re = 2100000000;
        }
        return re;
    }
    //		this.skillhurt=skilllvl>3?15:skilllvl>1?12.5:10;//毒伤上限
//		this.skillgain=(lvl+sld/100)*8+(skilllvl==5?10000:skilllvl==4?12000:8000);

    /**
     * 计算毒伤害
     */
    public int getzdsh(ManData mydata, ManData nomyadata, FightingSkill skill, double qds) {
//        qds += mydata.getQuality().getQzds();
//        double five = FiveLineSystem.getSwing(mydata, nomyadata);
//        if (five == 1.0) {
//            five = 1.0 + mydata.getsx(3, "无") / 100.0;
//        }
//        int ss = (int)((double)nomyadata.getHp_z() * (skill.getSkillhurt() / 100.0 + qds) * five);
//        return ss / 3;
        //原版↑  随风改版↓
        //(等级+熟练度/100)*8+8000]*(1+加强毒伤害)
        ConcurrentHashMap<Integer, Configure> s = GameServer.getAllConfigure();
        Configure configure = s.get(1);
        double szd = Double.parseDouble(configure.getDshsz());
        qds += mydata.getQuality().getQzds();
        double five = FiveLineSystem.getSwing(mydata, nomyadata);
        if (five == 1) {
            five = (1 + mydata.getsx(3, "无") / 100);
        }
        double fsfy = nomyadata.xxyfs;
        if (nomyadata.xzstate("冥河纱") != null) {
            fsfy += nomyadata.xzstate("冥河纱").getStateEffect2();
        }
        int ss;
        ss = (int) (((mydata.getlvl() + mydata.getZs() * 5000L / 100) * 8 + 7000) * (skill.getSkillhurt() / 12) * (1 + qds / 12) * five * szd);
        if (ss < 0 || ss > 2100000000) {
            ss = 2100000000;
        }
        if (mydata.xzstate("淬魂锤") != null) {
            double p1 = (mydata.xzstate("淬魂锤").getSurplus() >= 997) ? (mydata.xzstate("淬魂锤").getStateEffect4() / 100.0D) : 0.0D;
            double p2 = mydata.xzstate("淬魂锤").getStateEffect3() / 100.0D;
            ss = (int) (ss * (1.0D - p1 - p2));
        }
        if (mydata.xzstate("振魂鼓") != null) {
            double p = mydata.xzstate("振魂鼓").getStateEffect() * mydata.xzstate("振魂鼓").getStateEffect2() / 100.0D;
            double p2 = mydata.xzstate("振魂鼓").getStateEffect() * mydata.xzstate("振魂鼓").getStateEffect3() / 100.0D;
            double p1 = (mydata.xzstate("振魂鼓").getSurplus() >= 997) ? (mydata.xzstate("振魂鼓").getStateEffect4() / 100.0D) : 0.0D;
            ss = (int) (ss * (1.0D + p + p1 + p2));
        }
        if (nomyadata.xzstate("斗魂帆") != null) {
            ss = (int) (ss * (1.0D - nomyadata.xzstate("斗魂帆").getStateEffect2() / 100.0D));
        }
        if (nomyadata.xzstate("长生幡") != null) {
            ss = (int) (ss * (1.0D - nomyadata.xzstate("长生幡").getStateEffect2() / 100.0D));
        }
        if (nomyadata.xzstate("断生契") != null) {
            ss = (int) (ss * (1.0D + nomyadata.xzstate("断生契").getStateEffect2() / 100.0D));
        }
        if (nomyadata.xzstate("屠巫剑") != null) {
            ss = (int) (ss * (1.0D + nomyadata.xzstate("屠巫剑").getStateEffect2() / 100.0D));
        }
        if (nomyadata.xzstate("沧溟露") != null) {
            ss = (int) (ss * (1.0D - nomyadata.xzstate("沧溟露").getStateEffect2() / 100.0D));
        }
        if (nomyadata.xzstate("冥河纱") != null) {
            ss = (int) (ss * (1.0D - nomyadata.xzstate("冥河纱").getStateEffect() / 100.0D));
        }
        return ss;
    }

    public double renfa_value(ManData mydata, ManData nomyadata, double jichu, String type, double addHs, double addQF, double addKX) {
        int k = 0;
        if (mydata.getSkillType("8076") != null &&
                nomyadata.xzstate(type) != null) {
            k = 5;
        }
        return renfaCalculation_value(jichu, mydata.getsx(2, type) + addHs, mydata.getsx(1, type) + addQF + k, nomyadata.getsx(0, type) + addKX);
    }

    public double renfaCalculation_value(double jichu, double q, double hs, double k) {
        double rfbhs = Integer.parseInt("100");
        jichu = rfbhs / 100.0D * (jichu - k - hs) * (1.0D + q / 100.0D);
        return jichu;
    }
}
