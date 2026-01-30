package come.tool.Calculation;

import java.math.BigDecimal;
import come.tool.FightingData.GetqualityUntil;
import come.tool.FightingData.Ql;
import org.come.entity.Mount;

public class CalculationMount
{
    /** 坐骑技能效果系数 */
    public static double[] xishu;
    public static double[][] zuoqi;
    /**计算坐骑技能加成的方法*/
    public static String calculateAddition(Mount mount, String skillname, Ql ql) {
        if (skillname.equals("夺命追魂")) {
            double xs = returnsCalculation("连击率", mount, skillname);
            GetqualityUntil.AddR(ql, "连击率", xs);
            GetqualityUntil.AddR(ql, "致命", xs);
            GetqualityUntil.AddR(ql, "命中", xs);
            return "SP=" + returnsCalculation("SP", mount, skillname) / 100.0;
        }
        if (skillname.equals("天雷怒火")) {
            double xs2 = returnsCalculation("火法伤害", mount, skillname) * 2000.0;
            GetqualityUntil.AddR(ql, "火法伤害", xs2);
            GetqualityUntil.AddR(ql, "雷法伤害", xs2);
            GetqualityUntil.AddR(ql, "鬼火伤害", xs2);
            double xs3 = returnsCalculation("抗火", mount, skillname);
            GetqualityUntil.AddR(ql, "抗火", xs3);
            GetqualityUntil.AddR(ql, "抗雷", xs3);
            GetqualityUntil.AddR(ql, "抗鬼火", xs3);
            return "MP=" + returnsCalculation("MP", mount, skillname) / 100.0;
        }
        if (skillname.equals("金身不坏")) {
            GetqualityUntil.AddR(ql, "抗物理", returnsCalculation("抗物理", mount, skillname));
            GetqualityUntil.AddR(ql, "抗震慑", returnsCalculation("抗震慑", mount, skillname));
            GetqualityUntil.AddR(ql, "抗中毒", returnsCalculation("抗中毒", mount, skillname));
            GetqualityUntil.AddR(ql, "抗三尸", returnsCalculation("抗三尸虫", mount, skillname));
            return "HP=" + returnsCalculation("HP", mount, skillname) / 100.0;
        }
        if (skillname.equals("破釜沉舟")) {
            GetqualityUntil.AddR(ql, "狂暴率", returnsCalculation("狂暴", mount, skillname));
            double xs = returnsCalculation("忽视防御几率", mount, skillname);
            GetqualityUntil.AddR(ql, "忽视防御几率", xs);
            GetqualityUntil.AddR(ql, "忽视防御程度", xs);
            return "AP=" + returnsCalculation("AP", mount, skillname) / 100.0;
        }
        if (skillname.equals("兴风作浪")) {
            double xs2 = returnsCalculation("风法伤害", mount, skillname) * 2000.0;
            GetqualityUntil.AddR(ql, "风法伤害", xs2);
            GetqualityUntil.AddR(ql, "水法伤害", xs2);
            double xs3 = returnsCalculation("抗风", mount, skillname);
            GetqualityUntil.AddR(ql, "抗风", xs3);
            GetqualityUntil.AddR(ql, "抗水", xs3);
            GetqualityUntil.AddR(ql, "抗鬼火", xs3);
            return "MP=" + returnsCalculation("MP", mount, skillname) / 100.0;
        }
        if (skillname.equals("天神护体")) {
            double xs = returnsCalculation("抗风", mount, skillname);
            GetqualityUntil.AddR(ql, "抗风", xs);
            GetqualityUntil.AddR(ql, "抗火", xs);
            GetqualityUntil.AddR(ql, "抗水", xs);
            GetqualityUntil.AddR(ql, "抗雷", xs);
            GetqualityUntil.AddR(ql, "抗鬼火", xs);
            return "SP=" + returnsCalculation("SP", mount, skillname) / 100.0;
        }
        if (skillname.equals("后发制人")) {
            GetqualityUntil.AddR(ql, "狂暴率", returnsCalculation("狂暴", mount, skillname));
            return "HP=" + returnsCalculation("HP", mount, skillname) / 100.0;
        }
        if (skillname.equals("万劫不复")) {
            double xs = returnsCalculation("加强风", mount, skillname);
            GetqualityUntil.AddR(ql, "加强风", xs);
            GetqualityUntil.AddR(ql, "加强火", xs);
            GetqualityUntil.AddR(ql, "加强水", xs);
            GetqualityUntil.AddR(ql, "加强雷", xs);
            GetqualityUntil.AddR(ql, "加强鬼火", xs);
            return "MP=" + returnsCalculation("MP", mount, skillname) / 100.0;
        }
        if (skillname.equals("心如止水")) {
            double xs = returnsCalculation("抗昏睡", mount, skillname);
            GetqualityUntil.AddR(ql, "抗昏睡", xs);
            GetqualityUntil.AddR(ql, "抗封印", xs);
            GetqualityUntil.AddR(ql, "抗中毒", xs);
            GetqualityUntil.AddR(ql, "抗混乱", xs);
            GetqualityUntil.AddR(ql, "抗遗忘", xs);
            return "HP=" + returnsCalculation("HP", mount, skillname) / 100.0;
        }
        if (skillname.equals("视险如夷")) {
            double xs = returnsCalculation("抵御强克效果", mount, skillname);
            GetqualityUntil.AddR(ql, "抵御强克效果", xs);
            return "HP=" + returnsCalculation("HP", mount, skillname) / 100.0;
        }
        if (skillname.equals("游刃有余")) {
            double xs = returnsCalculation("法术躲闪率", mount, skillname);
            GetqualityUntil.AddR(ql, "法术躲闪率", xs);
            xs = returnsCalculation("抗灵宝伤害", mount, skillname);
            GetqualityUntil.AddR(ql, "抗灵宝伤害", xs);
            return "SP=" + returnsCalculation("SP", mount, skillname) / 100.0;
        }
        if (skillname.equals("反客为主")) {
            double xs = returnsCalculation("反击率", mount, skillname);
            GetqualityUntil.AddR(ql, "反击率", xs);
            xs = returnsCalculation("反击忽视防御几率", mount, skillname);
            GetqualityUntil.AddR(ql, "反击忽视防御几率", xs);
            GetqualityUntil.AddR(ql, "反击忽视防御程度", xs);
            return "AP=" + returnsCalculation("AP", mount, skillname) / 100.0;
        }
        if (skillname.equals("反治其身")) {
            double xs = returnsCalculation("躲闪率", mount, skillname);
            GetqualityUntil.AddR(ql, "躲闪率", xs);
            xs = returnsCalculation("反震率", mount, skillname);
            GetqualityUntil.AddR(ql, "反震率", xs);
            GetqualityUntil.AddR(ql, "反震程度", xs);
            return "HP=" + returnsCalculation("HP", mount, skillname) / 100.0;
        }
        if (skillname.equals("得心应手")) {
            double xs = returnsCalculation("忽视仙法", mount, skillname);
            GetqualityUntil.AddR(ql, "忽视仙法", xs);
            GetqualityUntil.AddR(ql, "忽视鬼火", xs);
            xs = returnsCalculation("仙法狂暴", mount, skillname);
            GetqualityUntil.AddR(ql, "仙法狂暴", xs);
            GetqualityUntil.AddR(ql, "鬼火狂暴几率", xs);
            return "MP=" + returnsCalculation("MP", mount, skillname) / 100.0;
        }
        if (skillname.equals("山外青山")) {
            double xs = returnsCalculation("增加强克效果", mount, skillname);
            GetqualityUntil.AddR(ql, "增加强克效果", xs);
            return "SP=" + returnsCalculation("SP", mount, skillname) / 100.0;
        }
        return null;
    }
    /**
     * 计算技能效果的方法
     */
    public static double returnsCalculation(String mes, Mount mount, String skillname) {
        double zjxz = 1.0;
        if ((int)mount.getMountid() == 2 || (int)mount.getMountid() == 4) {
            zjxz = 1.2;
        }
        int grade = (int)mount.getMountlvl();
        if (grade > 100) {
            grade -= 100;
        }
        // 最新的坐骑属性
        int lingxing = (int)Math.floor((double)((float)(int)mount.getSpri() + (float)grade / 10.0f * (float)(int)mount.getSpri() / 2.0f));
        int liliang = (int)Math.floor((double)((float)(int)mount.getPower() + (float)grade / 10.0f * (float)(int)mount.getPower() / 2.0f));
        int genggu = (int)Math.floor((double)((float)(int)mount.getBone() + (float)grade / 10.0f * (float)(int)mount.getBone() / 2.0f));
        int shulian = (int)mount.getProficiency();
        // 计算出来的技能效果值
        double jnxgz = 0.0;
        if (mes.equals("HP")) {
            if (skillname.equals("金身不坏")) {
                jnxgz = ((double)genggu * CalculationMount.xishu[2] + (double)lingxing * CalculationMount.xishu[4] + (double)liliang * CalculationMount.xishu[1]) * zjxz / CalculationMount.zuoqi[4][0] + (double)(shulian / 10000) / CalculationMount.zuoqi[4][1];
            }
            else if (skillname.equals("后发制人")) {
                jnxgz = ((double)genggu * CalculationMount.xishu[0] + (double)lingxing * CalculationMount.xishu[4] + (double)liliang * CalculationMount.xishu[3]) * zjxz / CalculationMount.zuoqi[16][0] + (double)shulian / CalculationMount.xishu[7] / CalculationMount.zuoqi[16][1];
            }
            else if (skillname.equals("心如止水")) {
                jnxgz = ((double)genggu * CalculationMount.xishu[2] + (double)lingxing * CalculationMount.xishu[1] + (double)liliang * CalculationMount.xishu[5]) * zjxz / CalculationMount.zuoqi[1][0] + (double)shulian / CalculationMount.xishu[6] / CalculationMount.zuoqi[1][1];
            }
            else if (skillname.equals("视险如夷")) {
                jnxgz = ((double)genggu * CalculationMount.xishu[2] + (double)lingxing * CalculationMount.xishu[1] + (double)liliang * CalculationMount.xishu[5]) * zjxz / CalculationMount.zuoqi[1][0] + (double)shulian / CalculationMount.xishu[6] / CalculationMount.zuoqi[22][0];
            }
            else if (skillname.equals("反治其身")) {
                jnxgz = ((double)genggu * CalculationMount.xishu[2] + (double)lingxing * CalculationMount.xishu[1] + (double)liliang * CalculationMount.xishu[5]) * zjxz / CalculationMount.zuoqi[1][0] + (double)shulian / CalculationMount.xishu[6] / CalculationMount.zuoqi[1][1];
            }
        }
        else if (mes.equals("MP")) {
            if (skillname.equals("天雷怒火")) {
                jnxgz = ((double)genggu * CalculationMount.xishu[4] + (double)lingxing * CalculationMount.xishu[3] + (double)liliang * CalculationMount.xishu[0]) * zjxz / CalculationMount.zuoqi[13][0] + (double)shulian / CalculationMount.xishu[6] / CalculationMount.zuoqi[13][1];
            }
            else if (skillname.equals("兴风作浪")) {
                jnxgz = ((double)genggu * CalculationMount.xishu[1] + (double)lingxing * CalculationMount.xishu[2] + (double)liliang * CalculationMount.xishu[5]) * zjxz / CalculationMount.zuoqi[10][0] + (double)shulian / CalculationMount.xishu[6] / CalculationMount.zuoqi[10][1];
            }
            else if (skillname.equals("万劫不复")) {
                jnxgz = ((double)genggu * CalculationMount.xishu[5] + (double)(lingxing * 1) + (double)liliang * CalculationMount.xishu[4]) * zjxz / CalculationMount.zuoqi[8][0] + (double)shulian / CalculationMount.xishu[6] / CalculationMount.zuoqi[8][1];
            }
            else if (skillname.equals("得心应手")) {
                jnxgz = ((double)genggu * CalculationMount.xishu[5] + (double)lingxing * CalculationMount.xishu[2] + (double)liliang * CalculationMount.xishu[2]) * zjxz / CalculationMount.zuoqi[8][0] + (double)shulian / CalculationMount.xishu[6] / CalculationMount.zuoqi[8][1];
            }
        }
        else if (mes.equals("AP")) {
            jnxgz = ((double)genggu * CalculationMount.xishu[5] + (double)lingxing * CalculationMount.xishu[4] + (double)(liliang * 1)) * zjxz / CalculationMount.zuoqi[18][0] + (double)shulian / CalculationMount.xishu[7] / CalculationMount.zuoqi[18][1];
            if (skillname.equals("反客为主")) {
                jnxgz = ((double)genggu * CalculationMount.xishu[5] + (double)lingxing * CalculationMount.xishu[4] + (double)liliang * CalculationMount.xishu[3]) * zjxz / CalculationMount.zuoqi[22][0] + (double)shulian / CalculationMount.xishu[7] / CalculationMount.zuoqi[22][1];
            }
        }
        else if (mes.equals("SP")) {
            if (skillname.equals("夺命追魂")) {
                jnxgz = ((double)genggu * CalculationMount.xishu[4] + (double)lingxing * CalculationMount.xishu[0] + (double)liliang * CalculationMount.xishu[3]) * zjxz / CalculationMount.zuoqi[21][0] + (double)shulian / CalculationMount.xishu[6] / CalculationMount.zuoqi[21][1];
            }
            else if (skillname.equals("天神护体")) {
                jnxgz = ((double)(genggu * 1) + (double)lingxing * CalculationMount.xishu[4] + (double)liliang * CalculationMount.xishu[4]) * zjxz / CalculationMount.zuoqi[3][0] + (double)shulian / CalculationMount.xishu[6] / CalculationMount.zuoqi[3][1];
            }
            else if (skillname.equals("山外青山")) {
                jnxgz = ((double)genggu * CalculationMount.xishu[5] + (double)lingxing * CalculationMount.xishu[2] + (double)liliang * CalculationMount.xishu[2]) * zjxz / CalculationMount.zuoqi[21][0] + (double)shulian / CalculationMount.xishu[6] / CalculationMount.zuoqi[22][0];
            }
            else if (skillname.equals("游刃有余")) {
                jnxgz = ((double)genggu * CalculationMount.xishu[1] + (double)liliang * CalculationMount.xishu[1] + (double)lingxing * CalculationMount.xishu[4]) * zjxz / CalculationMount.zuoqi[21][0] + (double)shulian / CalculationMount.xishu[6] / CalculationMount.zuoqi[20][0];
            }
        }
        else if (mes.equals("连击率") || mes.equals("致命") || mes.equals("命中")) {
            jnxgz = ((double)genggu * CalculationMount.xishu[5] + (double)lingxing * CalculationMount.xishu[1] + (double)liliang * CalculationMount.xishu[2]) * zjxz / CalculationMount.zuoqi[22][0] + (double)(shulian / 10000) / CalculationMount.zuoqi[22][1];
        }
        else if (mes.equals("反击率")) {
            jnxgz = ((double)genggu * CalculationMount.xishu[5] + (double)lingxing * CalculationMount.xishu[1] + (double)liliang * CalculationMount.xishu[2]) * zjxz / CalculationMount.zuoqi[2][0] + (double)(shulian / 10000) / CalculationMount.zuoqi[20][0];
        }
        else if (mes.equals("躲闪率")) {
            jnxgz = ((double)genggu * CalculationMount.xishu[3] + (double)lingxing * CalculationMount.xishu[3] + (double)liliang * CalculationMount.xishu[5]) * zjxz / CalculationMount.zuoqi[22][0] + (double)(shulian / 10000) / CalculationMount.zuoqi[21][0];
        }
        else if (mes.equals("反震率") || mes.equals("反震程度")) {
            jnxgz = ((double)genggu * CalculationMount.xishu[3] + (double)lingxing * CalculationMount.xishu[3] + (double)liliang * CalculationMount.xishu[5]) * zjxz / CalculationMount.zuoqi[22][0] + (double)(shulian / 10000) / CalculationMount.zuoqi[21][1];
        }
        else if (mes.equals("法术躲闪率")) {
            jnxgz = ((double)genggu * CalculationMount.xishu[1] + (double)liliang * CalculationMount.xishu[1] + (double)lingxing * CalculationMount.xishu[4]) * zjxz / CalculationMount.zuoqi[22][0] + (double)(shulian / 10000) / CalculationMount.zuoqi[21][0];
        }
        else if (mes.equals("抗灵宝伤害")) {
            jnxgz = ((double)genggu * CalculationMount.xishu[1] + (double)liliang * CalculationMount.xishu[1] + (double)lingxing * CalculationMount.xishu[4]) * zjxz / CalculationMount.zuoqi[21][0] + (double)shulian / CalculationMount.zuoqi[22][1];
        }
        else if (mes.equals("抵御强克效果")) {
            jnxgz = ((double)genggu * CalculationMount.xishu[8] + (double)lingxing * CalculationMount.xishu[1] + (double)liliang * CalculationMount.xishu[5]) * zjxz / CalculationMount.zuoqi[1][0] + (double)shulian / CalculationMount.xishu[6] / CalculationMount.zuoqi[0][1];
        }
        else if (mes.equals("增加强克效果")) {
            jnxgz = ((double)genggu * CalculationMount.xishu[5] + (double)lingxing * CalculationMount.xishu[2] + (double)liliang * CalculationMount.xishu[2]) * zjxz / CalculationMount.zuoqi[22][0] + (double)shulian / CalculationMount.xishu[6] / CalculationMount.zuoqi[20][1];
        }
        else if (mes.equals("忽视仙法")) {
            jnxgz = ((double)genggu * CalculationMount.xishu[5] + (double)lingxing * CalculationMount.xishu[2] + (double)liliang * CalculationMount.xishu[2]) * zjxz / CalculationMount.zuoqi[8][0] + (double)shulian / CalculationMount.xishu[6] / CalculationMount.zuoqi[8][1];
        }
        else if (mes.equals("忽视鬼火")) {
            jnxgz = ((double)genggu * CalculationMount.xishu[5] + (double)lingxing * CalculationMount.xishu[2] + (double)liliang * CalculationMount.xishu[2]) * zjxz / CalculationMount.zuoqi[8][0] + (double)shulian / CalculationMount.xishu[6] / CalculationMount.zuoqi[8][1];
        }
        else if (mes.equals("仙法狂暴")) {
            jnxgz = ((double)genggu * CalculationMount.xishu[5] + (double)lingxing * CalculationMount.xishu[2] + (double)liliang * CalculationMount.xishu[2]) * zjxz / CalculationMount.zuoqi[8][0] + (double)shulian / CalculationMount.xishu[6] / CalculationMount.zuoqi[8][1];
        }
        else if (mes.equals("鬼火狂暴几率")) {
            jnxgz = ((double)genggu * CalculationMount.xishu[5] + (double)lingxing * CalculationMount.xishu[2] + (double)liliang * CalculationMount.xishu[2]) * zjxz / CalculationMount.zuoqi[8][0] + (double)shulian / CalculationMount.xishu[6] / CalculationMount.zuoqi[8][1];
        }
        else if (mes.equals("抗鬼火")) {
            if (skillname.equals("天神护体")) {
                jnxgz = ((double)(genggu * 1) + (double)lingxing * CalculationMount.xishu[4] + (double)liliang * CalculationMount.xishu[4]) * zjxz / CalculationMount.zuoqi[2][0] + (double)shulian / CalculationMount.xishu[6] / CalculationMount.zuoqi[2][1];
            }
            else if (skillname.equals("天雷怒火")) {
                jnxgz = ((double)genggu * CalculationMount.xishu[4] + (double)lingxing * CalculationMount.xishu[2] + (double)liliang * CalculationMount.xishu[1]) * zjxz / CalculationMount.zuoqi[15][0] + (double)shulian / CalculationMount.xishu[6] / CalculationMount.zuoqi[15][1];
            }
            else if (skillname.equals("兴风作浪")) {
                jnxgz = ((double)genggu * CalculationMount.xishu[1] + (double)lingxing * CalculationMount.xishu[2] + (double)liliang * CalculationMount.xishu[5]) * zjxz / CalculationMount.zuoqi[12][0] + (double)shulian / CalculationMount.xishu[6] / CalculationMount.zuoqi[12][1];
            }
        }
        else if (mes.equals("抗风法") || mes.equals("抗火法") || mes.equals("抗水法") || mes.equals("抗雷法")) {
            if (skillname.equals("天神护体")) {
                jnxgz = ((double)(genggu * 1) + (double)lingxing * CalculationMount.xishu[4] + (double)liliang * CalculationMount.xishu[4]) * zjxz / CalculationMount.zuoqi[2][0] + (double)shulian / CalculationMount.xishu[6] / CalculationMount.zuoqi[2][1];
            }
            else if (skillname.equals("兴风作浪")) {
                jnxgz = ((double)genggu * CalculationMount.xishu[1] + (double)lingxing * CalculationMount.xishu[2] + (double)liliang * CalculationMount.xishu[5]) * zjxz / CalculationMount.zuoqi[12][0] + (double)shulian / CalculationMount.xishu[6] / CalculationMount.zuoqi[12][1];
            }
            else if (skillname.equals("天雷怒火")) {
                jnxgz = ((double)genggu * CalculationMount.xishu[4] + (double)lingxing * CalculationMount.xishu[2] + (double)liliang * CalculationMount.xishu[1]) * zjxz / CalculationMount.zuoqi[15][0] + (double)shulian / CalculationMount.xishu[6] / CalculationMount.zuoqi[15][1];
            }
        }
        else if (mes.equals("火法伤害") || mes.equals("雷法伤害") || mes.equals("鬼火伤害") || mes.equals("火雷伤害")) {
            jnxgz = ((double)genggu * CalculationMount.xishu[5] + (double)lingxing * CalculationMount.xishu[3] + (double)liliang * CalculationMount.xishu[0]) * zjxz / CalculationMount.zuoqi[14][0] + (double)(shulian / 10000) / CalculationMount.zuoqi[14][1];
        }
        else if (mes.equals("风系狂暴几率") || mes.equals("火系狂暴几率") || mes.equals("水系狂暴几率") || mes.equals("雷系狂暴几率") || mes.equals("鬼火狂暴几率")) {
            jnxgz = ((double)genggu * CalculationMount.xishu[1] + (double)lingxing * CalculationMount.xishu[2] + (double)liliang * CalculationMount.xishu[5]) * zjxz / CalculationMount.zuoqi[19][0] + (double)shulian / CalculationMount.xishu[6] / CalculationMount.zuoqi[19][1];
        }
        else if (mes.equals("抗火雷")) {
            jnxgz = ((double)genggu * CalculationMount.xishu[4] + (double)lingxing * CalculationMount.xishu[2] + (double)liliang * CalculationMount.xishu[1]) * zjxz / CalculationMount.zuoqi[15][0] + (double)shulian / CalculationMount.xishu[6] / CalculationMount.zuoqi[15][1];
        }
        else if (mes.equals("抗物理")) {
            jnxgz = ((double)genggu * CalculationMount.xishu[3] + (double)lingxing * CalculationMount.xishu[5] + (double)liliang * CalculationMount.xishu[0]) * zjxz / CalculationMount.zuoqi[5][0] + (double)shulian / CalculationMount.xishu[6] / CalculationMount.zuoqi[5][1];
        }
        else if (mes.equals("抗震慑")) {
            jnxgz = ((double)genggu * CalculationMount.xishu[3] + (double)lingxing * CalculationMount.xishu[4] + (double)liliang * CalculationMount.xishu[1]) * zjxz / CalculationMount.zuoqi[6][0] + (double)shulian / CalculationMount.xishu[6] / CalculationMount.zuoqi[6][1];
        }
        else if (mes.equals("抗中毒")) {
            if (skillname.equals("金身不坏")) {
                jnxgz = ((double)genggu * CalculationMount.xishu[2] + (double)lingxing * CalculationMount.xishu[4] + (double)liliang * CalculationMount.xishu[0]) * zjxz / CalculationMount.zuoqi[7][0] + (double)(shulian / 10000) / CalculationMount.zuoqi[7][1];
            }
            else if (skillname.equals("心如止水")) {
                jnxgz = ((double)genggu * CalculationMount.xishu[3] + (double)lingxing * CalculationMount.xishu[0] + (double)liliang * CalculationMount.xishu[4]) * zjxz / CalculationMount.zuoqi[0][0] + (double)shulian / CalculationMount.xishu[7] / CalculationMount.zuoqi[0][1];
            }
        }
        else if (mes.equals("抗三尸虫")) {
            jnxgz = ((double)genggu * CalculationMount.xishu[2] + (double)lingxing * CalculationMount.xishu[4] + (double)liliang * CalculationMount.xishu[0]) * 125.0 / 3.0 + (double)(shulian * 1500 / 100000);
        }
        else if (mes.equals("狂暴")) {
            if (skillname.equals("破釜沉舟")) {
                jnxgz = ((double)genggu * CalculationMount.xishu[4] + (double)lingxing * CalculationMount.xishu[4] + (double)(liliang * 1)) * zjxz / CalculationMount.zuoqi[19][0] + (double)shulian / CalculationMount.xishu[6] / CalculationMount.zuoqi[19][1];
            }
            else if (skillname.equals("后发制人")) {
                jnxgz = ((double)genggu * CalculationMount.xishu[1] + (double)lingxing * CalculationMount.xishu[5] + (double)liliang * CalculationMount.xishu[3]) * zjxz / CalculationMount.zuoqi[17][0] + (double)shulian / CalculationMount.xishu[6] / CalculationMount.zuoqi[17][1];
            }
        }
        else if (mes.equals("忽视防御几率") || mes.equals("忽视防御程度")) {
            jnxgz = ((double)genggu * CalculationMount.xishu[4] + (double)lingxing * CalculationMount.xishu[5] + (double)(liliang * 1)) * zjxz / CalculationMount.zuoqi[20][0] + (double)shulian / CalculationMount.xishu[6] / CalculationMount.zuoqi[20][1];
        }
        else if (mes.equals("反击忽视防御几率") || mes.equals("反击忽视防御程度")) {
            jnxgz = ((double)genggu * CalculationMount.xishu[5] + (double)lingxing * CalculationMount.xishu[4] + (double)liliang * CalculationMount.xishu[3]) * zjxz / CalculationMount.zuoqi[22][0] + (double)shulian / CalculationMount.xishu[7] / CalculationMount.zuoqi[22][1];
        }
        else if (mes.equals("风法伤害") || mes.equals("水法伤害")) {
            jnxgz = ((double)genggu * CalculationMount.xishu[0] + (double)lingxing * CalculationMount.xishu[3] + (double)liliang * CalculationMount.xishu[5]) * zjxz / CalculationMount.zuoqi[11][0] + (double)shulian / CalculationMount.xishu[6] / CalculationMount.zuoqi[11][1];
        }
        else if (mes.equals("加强风") || mes.equals("加强火") || mes.equals("加强水") || mes.equals("加强雷") || mes.equals("加强鬼火")) {
            jnxgz = ((double)genggu * CalculationMount.xishu[5] + (double)(lingxing * 1) + (double)liliang * CalculationMount.xishu[5]) * zjxz / CalculationMount.zuoqi[9][0] + (double)shulian / CalculationMount.xishu[6] / CalculationMount.zuoqi[9][1];
        }
        else if (mes.equals("抗昏睡") || mes.equals("抗封印") || mes.equals("抗混乱")) {
            jnxgz = ((double)genggu * CalculationMount.xishu[3] + (double)lingxing * CalculationMount.xishu[0] + (double)liliang * CalculationMount.xishu[4]) * zjxz / CalculationMount.zuoqi[0][0] + (double)shulian / CalculationMount.xishu[7] / CalculationMount.zuoqi[0][1];
        }
        else if (mes.equals("抗遗忘")) {
            jnxgz = ((double)genggu * CalculationMount.xishu[3] + (double)lingxing * CalculationMount.xishu[0] + (double)liliang * CalculationMount.xishu[4]) * zjxz / CalculationMount.zuoqi[0][0] + (double)shulian / CalculationMount.xishu[7] / CalculationMount.zuoqi[0][1];
        }
        return jnxgz;
    }
    /**保留两位小数的方法*/
    public static double keepTwoDecimals(Double value) {
        BigDecimal b = new BigDecimal((double)value);
        double d = b.setScale(2, 4).doubleValue();
        return d;
    }
    
    static {
        CalculationMount.xishu = new double[] { 0.3, 0.3, 0.7, 0.7, 0.0, 0.0, 10000.0, 10000.0, 1.2 };
        CalculationMount.zuoqi = new double[][] { { 4.115226337, 1.141552511 }, { 14.40329218, 3.99543379 }, { 4.8, 1.333333333 }, { 14.4, 4.0 }, { 14.4, 4.0 }, { 3.6, 1.0 }, { 7.2, 2.0 }, { 4.8, 1.333333333 }, { 7.2, 2.0 }, { 4.8, 1.333333333 }, { 7.2, 2.0 }, { 4.8, 1.333333333 }, { 7.2, 2.0 }, { 7.2, 2.0 }, { 4.8, 1.333333333 }, { 7.2, 2.0 }, { 14.4, 4.0 }, { 4.8, 1.333333333 }, { 7.2, 2.0 }, { 4.8, 1.333333333 }, { 3.6, 1.0 }, { 14.4, 4.0 }, { 7.2, 2.0 } };
    }
}
