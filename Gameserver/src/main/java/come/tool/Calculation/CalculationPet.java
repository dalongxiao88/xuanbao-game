package come.tool.Calculation;

import java.math.BigDecimal;
import org.come.model.Skill;
import org.come.action.suit.SuitPetEquip;
import come.tool.FightingData.FightingSkill;
import org.come.entity.Goodstable;
import come.tool.Battle.BattleMixDeal;
import org.come.entity.RoleSummoning;
import come.tool.FightingData.GetqualityUntil;
import org.come.tool.CustomFunction;
import come.tool.FightingData.Ql;

public class CalculationPet
{
    /**根据技能id添加*/
    public static void addQlLingXi(Ql ql, int id, long qm, int lv) {
    }
    
    public static void addQlSkill(Ql ql, int id, long qm) {
        switch (id) {
            case 1810: {
                double value = 5.0 + CustomFunction.XS(qm, 0.3);
                GetqualityUntil.AddR(ql, "抗风", -value);
                GetqualityUntil.AddR(ql, "抗火", -value);
                GetqualityUntil.AddR(ql, "抗水", -value);
                GetqualityUntil.AddR(ql, "抗雷", -value);
                GetqualityUntil.AddR(ql, "抗鬼火", -value);
                GetqualityUntil.AddR(ql, "反震率", value);
                GetqualityUntil.AddR(ql, "反震程度", value);
                break;
            }
            case 1811: {
                double value = 10.0 + CustomFunction.XS(qm, 0.6);
                GetqualityUntil.AddR(ql, "抗风", -value);
                GetqualityUntil.AddR(ql, "抗火", -value);
                GetqualityUntil.AddR(ql, "抗水", -value);
                GetqualityUntil.AddR(ql, "抗雷", -value);
                GetqualityUntil.AddR(ql, "抗鬼火", -value);
                GetqualityUntil.AddR(ql, "反震率", value);
                GetqualityUntil.AddR(ql, "反震程度", value);
                break;
            }
            case 1813:
            case 1820:
            case 1821:
            case 1822:
            case 1823:
            case 1824: {
                double value = 2.0 + CustomFunction.XS(qm, 0.2);
                GetqualityUntil.AddR(ql, "抗封印", value);
                GetqualityUntil.AddR(ql, "抗混乱", value);
                GetqualityUntil.AddR(ql, "抗遗忘", value);
                break;
            }
            case 1815: {
                double value = 2.0 + CustomFunction.XS(qm, 0.2);
                GetqualityUntil.AddR(ql, "金", 50.0);
                GetqualityUntil.AddR(ql, "抗封印", value);
                GetqualityUntil.AddR(ql, "抗混乱", value);
                GetqualityUntil.AddR(ql, "抗遗忘", value);
                break;
            }
            case 1816: {
                double value = 2.0 + CustomFunction.XS(qm, 0.2);
                GetqualityUntil.AddR(ql, "木", 50.0);
                GetqualityUntil.AddR(ql, "抗封印", value);
                GetqualityUntil.AddR(ql, "抗混乱", value);
                GetqualityUntil.AddR(ql, "抗遗忘", value);
                break;
            }
            case 1817: {
                double value = 2.0 + CustomFunction.XS(qm, 0.2);
                GetqualityUntil.AddR(ql, "水", 50.0);
                GetqualityUntil.AddR(ql, "抗封印", value);
                GetqualityUntil.AddR(ql, "抗混乱", value);
                GetqualityUntil.AddR(ql, "抗遗忘", value);
                break;
            }
            case 1818: {
                double value = 2.0 + CustomFunction.XS(qm, 0.2);
                GetqualityUntil.AddR(ql, "火", 50.0);
                GetqualityUntil.AddR(ql, "抗封印", value);
                GetqualityUntil.AddR(ql, "抗混乱", value);
                GetqualityUntil.AddR(ql, "抗遗忘", value);
                break;
            }
            case 1819: {
                double value = 2.0 + CustomFunction.XS(qm, 0.2);
                GetqualityUntil.AddR(ql, "土", 50.0);
                GetqualityUntil.AddR(ql, "抗封印", value);
                GetqualityUntil.AddR(ql, "抗混乱", value);
                GetqualityUntil.AddR(ql, "抗遗忘", value);
                break;
            }
            case 1207: {
                double value = 3.0 + CustomFunction.XS(qm, 0.2);
                GetqualityUntil.AddR(ql, "连击率", value);
                break;
            }
            case 1208: {
                double value = 10.0 + CustomFunction.XS(qm, 0.6);
                GetqualityUntil.AddR(ql, "忽视防御几率", value);
                GetqualityUntil.AddR(ql, "忽视防御程度", value);
                break;
            }
            case 1209: {
                double value = 5.0 + CustomFunction.XS(qm, 0.3);
                GetqualityUntil.AddR(ql, "狂暴率", value);
                GetqualityUntil.AddR(ql, "致命率", value);
                break;
            }
            case 1213: {
                double value = 5.0 + CustomFunction.XS(qm, 0.3);
                GetqualityUntil.AddR(ql, "反震率", value);
                GetqualityUntil.AddR(ql, "反震程度", value);
                break;
            }
            case 1222: {
                double value = 5.0 + CustomFunction.XS(qm, 0.4);
                GetqualityUntil.AddR(ql, "抗震慑", value);
                break;
            }
            case 1226: {
                double value = 10.0 + CustomFunction.XS(qm, 0.6);
                GetqualityUntil.AddR(ql, "物理吸收率", value);
                break;
            }
            case 1845: {
                double value = 5.0 + CustomFunction.XS(qm, 0.5);
                GetqualityUntil.AddR(ql, "附加封印攻击", value);
                break;
            }
            case 1846: {
                double value = CustomFunction.XS(qm, 0.1);
                GetqualityUntil.AddR(ql, "附加混乱攻击", value);
                break;
            }
            case 1855: {
                double value = 10.0 + CustomFunction.XS(qm, 0.6);
                GetqualityUntil.AddR(ql, "抗致命率", value);
                break;
            }
            case 1856: {
                double value = 10.0 + CustomFunction.XS(qm, 0.6);
                GetqualityUntil.AddR(ql, "仙法狂暴", value);
                GetqualityUntil.AddR(ql, "鬼火狂暴", value);
                break;
            }
            case 1857: {
                double value = 10.0 + CustomFunction.XS(qm, 0.6);
                GetqualityUntil.AddR(ql, "忽视仙法", value);
                GetqualityUntil.AddR(ql, "忽视鬼火", value);
                break;
            }
            case 1859: {
                double value = 3.0 + CustomFunction.XS(qm, 0.3);
                GetqualityUntil.AddR(ql, "抗封印", value);
                GetqualityUntil.AddR(ql, "抗混乱", value);
                GetqualityUntil.AddR(ql, "抗遗忘", value);
                break;
            }
            case 1860: {
                double value = 5.0 + CustomFunction.XS(qm, 0.5);
                GetqualityUntil.AddR(ql, "附加三尸攻击", value);
                break;
            }
            case 1235: {
                GetqualityUntil.AddR(ql, "伤害减免", 15.0);
                break;
            }
            case 25017: {
                ql.setRolehsfyv(ql.getRolehsfyv() + 20.0);
                ql.setRolehsfyl(ql.getRolehsfyl() + 20.0);
                break;
            }
            case 1252: {
                ql.setRolehsfyv(ql.getRolehsfyv() + 25.0);
                ql.setRolehsfyl(ql.getRolehsfyl() + 25.0);
                ql.setRolehsfyl(ql.getRolefljl() + 25.0);
                break;
            }
            case 450051: {
                ql.setRolehsfyv(ql.getRolehsfyv() + 25.0);
                ql.setRolehsfyl(ql.getRolehsfyl() + 25.0);
                break;
            }
        }
    }
    //抗性型内丹加抗性方法
    public static void addNedanMsg(RoleSummoning roleSummoning, Ql ql, int nddj, int ndzscs, String goodsname) {
        int zhsdj = BattleMixDeal.petLvlint((int)roleSummoning.getGrade());//召唤兽等级
        int zhszscs = roleSummoning.getTurnRount();//召唤兽转生次数
        long zhsqm = (long)roleSummoning.getFriendliness();//召唤兽亲密值
        if (goodsname.equals("暗度陈仓")) {
            double ndjl = Math.floor((Math.pow((double)(zhsdj * nddj) * 0.04, 0.5) * (1.0 + 0.25 * (double)ndzscs) + Math.pow((double)zhsqm, 0.16666666666666666) * xstz(zhszscs, ndzscs) * (double)nddj / 50.0) * 1000.0) * 2.5E-6;
            GetqualityUntil.AddR(ql, "忽视躲闪", (double)Math.round(ndjl * 10000.0) / 100.0);
            GetqualityUntil.AddR(ql, "忽视反击", (double)Math.round(ndjl * 10000.0) / 100.0);
        }
        else if (goodsname.equals("凌波微步")) {
            double ndjl = Math.floor((Math.pow((double)(zhsdj * nddj) * 0.04, 0.5) * (1.0 + 0.25 * (double)ndzscs) + Math.pow((double)zhsqm, 0.16666666666666666) * xstz(zhszscs, ndzscs) * (double)nddj / 50.0) * 1000.0) * 1.0E-5;
            GetqualityUntil.AddR(ql, "躲闪率", (double)Math.round(ndjl * 10000.0) / 100.0);
        }
        else if (goodsname.equals("借力打力")) {
            double ndjl = Math.floor((Math.pow((double)(zhsdj * nddj) * 0.04, 0.5) * (1.0 + 0.25 * (double)ndzscs) + Math.pow((double)zhsqm, 0.16666666666666666) * xstz(zhszscs, ndzscs) * (double)nddj / 50.0) * 1000.0) * 1.0E-5;
            int ndcd_jldl = nd_jldl_fjcs(ndjl);
            GetqualityUntil.AddR(ql, "反击率", (double)Math.round(ndjl * 10000.0) / 100.0);
            GetqualityUntil.AddR(ql, "反击次数", (double)ndcd_jldl);
        }
        else if (goodsname.equals("梅花三弄")) {
            double ndjl = Math.floor((Math.pow((double)(zhsdj * nddj) * 0.04, 0.5) * (1.0 + 0.25 * (double)ndzscs) + Math.pow((double)zhsqm, 0.16666666666666666) * xstz(zhszscs, ndzscs) * (double)nddj / 50.0) * 1000.0) * 5.0E-6;
            int ndcd_jldl = nd_mhsn_ljcs(ndjl);
            GetqualityUntil.AddR(ql, "仙法连击率", (double)Math.round(ndjl * 10000.0) / 100.0);
            GetqualityUntil.AddR(ql, "仙法连击次数", (double)ndcd_jldl);
        }
        else if (goodsname.equals("红颜白发")) {
            double ndjl = Math.floor((Math.pow((double)(zhsdj * nddj) * 0.04, 0.5) * (1.0 + 0.25 * (double)ndzscs) + Math.pow((double)zhsqm, 0.16666666666666666) * xstz(zhszscs, ndzscs) * (double)nddj / 50.0) * 1000.0) * 5.0E-6;
            GetqualityUntil.AddR(ql, "仙法狂暴", (double)Math.round(ndjl * 10000.0) / 100.0);
            GetqualityUntil.AddR(ql, "鬼火狂暴", (double)Math.round(ndjl * 10000.0) / 100.0);
        }
        else if (goodsname.equals("开天辟地")) {
            double ndjl = Math.floor((Math.pow((double)(zhsdj * nddj) * 0.04, 0.5) * (1.0 + 0.25 * (double)ndzscs) + Math.pow((double)zhsqm, 0.16666666666666666) * xstz(zhszscs, ndzscs) * (double)nddj / 50.0) * 1000.0) * 5.0E-6;
            GetqualityUntil.AddR(ql, "忽视仙法抗性率", (double)Math.round(ndjl * 10000.0) / 100.0);
            GetqualityUntil.AddR(ql, "忽视仙法抗性程度", (double)Math.round(ndjl * 10000.0) / 100.0);
            GetqualityUntil.AddR(ql, "忽视鬼火", (double)Math.round(ndjl * 10000.0) / 100.0);
        }
        else if (goodsname.equals("万佛朝宗")) {
            double ndjl = Math.floor((Math.pow((double)(zhsdj * nddj) * 0.04, 0.5) * (1.0 + 0.25 * (double)ndzscs) + Math.pow((double)zhsqm, 0.16666666666666666) * xstz(zhszscs, ndzscs) * (double)nddj / 50.0) * 1000.0) * 5.0E-6;
            double ndcd = ndjl * 2.0;
            GetqualityUntil.AddR(ql, "反震率", (double)Math.round(ndjl * 10000.0) / 100.0);
            GetqualityUntil.AddR(ql, "反震程度", (double)Math.round(ndcd * 10000.0) / 100.0);
        }
    }
    //非抗性型内丹获取几率、伤害率的方法
    public static FightingSkill accessNedanMsg(Goodstable goodstable, int nddj, int ndzscs, int zhsdj, int zhszscs, long zhsqm, int zhsmpz) {
        zhsmpz += 100;
        if (goodstable.getGoodsname().equals("浩然正气")) {
            double ndjl = Math.floor((Math.pow((double)(zhsdj * nddj) * 0.04, 0.5) * (1.0 + 0.25 * (double)ndzscs) + Math.pow((double)zhsqm, 0.16666666666666666) * xstz(zhszscs, ndzscs) * (double)nddj / 50.0) * 1000.0) * 5.0E-6;
            double ndcd = Math.floor(((double)(zhsdj * zhsdj) * 0.2 / (double)(zhsmpz * 1 + 1) + ndjl) * 10000.0) / 10000.0;
            return new FightingSkill(goodstable.getGoodsname(), (double)Math.round(ndjl * 10000.0) / 100.0, (double)Math.round(ndcd * 10000.0) / 100.0);
        }
        if (goodstable.getGoodsname().equals("隔山打牛")) {
            double ndjl = Math.floor((Math.pow((double)(zhsdj * nddj) * 0.04, 0.5) * (1.0 + 0.25 * (double)ndzscs) + Math.pow((double)zhsqm, 0.16666666666666666) * xstz(zhszscs, ndzscs) * (double)nddj / 50.0) * 1000.0) * 5.0E-6;
            double ndcd = Math.floor(((double)(zhsdj * zhsdj) * 0.2 / (double)(zhsmpz * 1 + 1) + ndjl * 3.0) * 1000.0) / 1000.0;
            return new FightingSkill(goodstable.getGoodsname(), (double)Math.round(ndjl * 10000.0) / 100.0, (double)Math.round(ndcd * 10000.0) / 100.0);
        }
        if (goodstable.getGoodsname().equals("天魔解体")) {
            double ndjl = 0.0;
            if (zhszscs == 0) {
                ndjl = Math.floor((Math.pow((double)(zhsdj * nddj) / 160000.0, 0.5) * (1.0 + 0.25 * (double)ndzscs) + Math.pow((double)zhsqm, 0.16666666666666666) * (double)nddj / 4000.0) * 1000.0) / 1000.0 + 0.01;
            }
            else {
                ndjl = Math.floor((Math.pow((double)(zhsdj * nddj) / 160000.0, 0.5) * (1.0 + 0.25 * (double)ndzscs) + Math.pow((double)zhsqm, 0.16666666666666666) * (double)nddj / 3755.0) * 1000.0) / 1000.0 + 0.01;
            }
            double ndhq = Math.floor(ndjl * ((double)(zhsdj * zhsdj) * 0.15 / ((double)(zhsmpz * 1) + 0.01) + 0.2) * 1000.0) / 1000.0;
            return new FightingSkill(goodstable.getGoodsname(), (double)Math.round(ndjl * 10000.0) / 100.0, (double)Math.round(ndhq * 10000.0) / 100.0);
        }
        else {
            if (goodstable.getGoodsname().equals("分光化影")) {
                double ndjl = Math.floor((Math.pow((double)(zhsdj * nddj) / 160000.0, 0.5) * (1.0 + 0.25 * (double)ndzscs) + Math.pow((double)zhsqm, 0.16666666666666666) * (double)nddj / 4000.0) * 1000.0) / 1000.0 + 0.01;
                double ndhq = Math.floor(ndjl * ((double)(zhsdj * zhsdj) * 0.15 / ((double)(zhsmpz * 1) + 0.01) + 0.2) * 1000.0) / 1000.0;
                return new FightingSkill(goodstable.getGoodsname(), (double)Math.round(ndjl * 10000.0) / 100.0, (double)Math.round(ndhq * 10000.0) / 100.0);
            }
            if (goodstable.getGoodsname().equals("小楼夜哭")) {
                double ndjl = Math.floor((Math.pow((double)(zhsdj * nddj) / 206600.0, 0.5) * (1.0 + 0.25 * (double)ndzscs) + Math.pow((double)zhsqm, 0.16666666666666666) * (double)nddj / 4170.0) * 1000.0) / 1000.0 + 0.01;
                double ndhq = ndjl * 0.3;
                return new FightingSkill(goodstable.getGoodsname(), (double)Math.round(ndjl * 10000.0) / 100.0, (double)Math.round(ndhq * 10000.0) / 100.0);
            }
            if (goodstable.getGoodsname().equals("青面獠牙")) {
                double ndjl = Math.floor((Math.pow((double)(zhsdj * nddj) / 698000.0, 0.5) * (1.0 + 0.25 * (double)ndzscs) + Math.pow((double)zhsqm, 0.16666666666666666) * (double)nddj / 7500.0) * 1000.0) / 1000.0 + 0.01;
                double ndhq = ndjl * 0.7;
                return new FightingSkill(goodstable.getGoodsname(), (double)Math.round(ndjl * 10000.0) / 100.0, (double)Math.round(ndhq * 10000.0) / 100.0);
            }
            double ndjl = Math.floor((Math.pow((double)(zhsdj * nddj) * 0.04, 0.5) * (1.0 + 0.25 * (double)ndzscs) + Math.pow((double)zhsqm, 0.16666666666666666) * xstz(zhszscs, ndzscs) * (double)nddj / 50.0) * 1000.0) * 4.0E-6;
            double ndcd = Math.floor(296.1572 + 2.364957E-4 * Math.pow((double)zhsmpz, 1.57));
            return new FightingSkill(goodstable.getGoodsname(), (double)Math.round(ndjl * 10000.0) / 100.0, ndcd);
        }
    }
    //获得转生系数的方法
    public static double xstz(int zhs_zscs, int nd_zscs) {
        if (zhs_zscs * nd_zscs == 1) {
            return 1.04;
        }
        if (zhs_zscs * nd_zscs == 4) {
            return 1.071;
        }
        if (zhs_zscs * nd_zscs == 6) {
            return 1.073;
        }
        if (zhs_zscs * nd_zscs == 9) {
            return 1.09;
        }
        return 1.0;
    }
    //获得反击次数的方法
    public static int nd_jldl_fjcs(double jl) {
        if (jl > 0.56) {
            return 10;
        }
        if (jl > 0.51) {
            return 8;
        }
        if (jl > 0.45) {
            return 7;
        }
        if (jl > 0.39) {
            return 6;
        }
        if (jl > 0.32) {
            return 5;
        }
        if (jl > 0.25) {
            return 4;
        }
        if (jl > 0.17) {
            return 3;
        }
        if (jl > 0.09) {
            return 2;
        }
        return 1;
    }
    //获得连击次数的方法
    public static int nd_mhsn_ljcs(double mhsn) {
        if (mhsn > 0.28) {
            return 5;
        }
        if (mhsn > 0.21) {
            return 4;
        }
        if (mhsn > 0.14) {
            return 3;
        }
        if (mhsn > 0.7) {
            return 2;
        }
        return 1;
    }
    /**根据觉醒技返回技能*/
    public static FightingSkill JX(BaseSkill jx1, BaseSkill jx2, BaseSkill jx3) {
        if (jx1 == null || jx2 == null || jx3 == null) {
            return null;
        }
        if (jx1.getSkillId() != jx2.getSkillId() || jx1.getSkillId() != jx3.getSkillId()) {
            return null;
        }
        Skill skill = jx1.getSkill();
        if (skill == null) {
            return null;
        }
        double sld = averageMath(jx1.getPz(), jx2.getPz(), jx3.getPz()).doubleValue();
        long exp = averageMath((double)jx1.getLvl(), (double)jx2.getLvl(), (double)jx3.getLvl()).longValue();
        int lvl = SuitPetEquip.expChangeLevel(exp);
        FightingSkill fightingSkill = new FightingSkill(skill, lvl, 0, sld, 0L, 0);
        return fightingSkill;
    }
    /** 三个参数求平均 */
    public static BigDecimal averageMath(double one, double two, double three) {
        return new BigDecimal(one).add(new BigDecimal(two)).add(new BigDecimal(three)).divide(new BigDecimal(3), 2, 4);
    }
}
