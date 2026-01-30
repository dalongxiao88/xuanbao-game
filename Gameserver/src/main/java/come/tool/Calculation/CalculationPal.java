package come.tool.Calculation;

import java.util.Iterator;
import org.come.entity.Lingbao;
import java.util.List;
import org.come.entity.Goodstable;
import org.come.model.Skill;
import java.util.Map;
import come.tool.FightingData.GetqualityUntil;
import come.tool.FightingData.Ql;
import come.tool.FightingData.FightingLingbao;
import java.util.ArrayList;
import java.math.BigDecimal;
import org.come.until.AllServiceUtil;
import come.tool.FightingData.FightingSkill;
import org.come.server.GameServer;
import java.util.HashMap;
import come.tool.FightingData.ManData;
import org.come.model.PalData;
import org.come.entity.Pal;

public class CalculationPal
{
    public static void getPal(Pal pal, PalData palData, ManData data, int lvl, ManData master) {
        int zs = master.getZs();
        Map<String, Double> map = new HashMap<>();
        int point = lvl + zs * 40;//分配点数
        CalculationUtil.addValue(map, "根骨", (double)(lvl + point / palData.getSize() * palData.getJds()[0]));
        CalculationUtil.addValue(map, "灵性", (double)(lvl + point / palData.getSize() * palData.getJds()[1]));
        CalculationUtil.addValue(map, "力量", (double)(lvl + point / palData.getSize() * palData.getJds()[2]));
        CalculationUtil.addValue(map, "敏捷", (double)(lvl + point / palData.getSize() * palData.getJds()[3]));
        point = lvl % palData.getSize();
        for (int i = 0; i < palData.getJds().length && point > 0; ++i) {
            if (palData.getJds()[i] != 0) {
                if (point > palData.getJds()[i]) {
                    CalculationUtil.addValue(map, (i == 0) ? "根骨" : ((i == 1) ? "灵性" : ((i == 2) ? "力量" : "敏捷")), (double)palData.getJds()[i]);
                    point -= palData.getJds()[i];
                }
                else {
                    CalculationUtil.addValue(map, (i == 0) ? "根骨" : ((i == 1) ? "灵性" : ((i == 2) ? "力量" : "敏捷")), (double)point);
                    point = 0;
                }
            }
        }
        if (palData.getQls() != null) {
            for (int i = 0; i < palData.getQls().length; ++i) {
                PalQl ql = palData.getQls()[i];
                double value = ql.getValue() + ql.getSv() * (double)lvl;
                if (value != 0.0) {
                    CalculationUtil.addValue(map, ql.getKey(), value);
                }
            }
        }
        if (palData.getSkills() != null) {
            double sld = (double)(lvl * 150);
            if (sld < 10000.0) {
                sld = 10000.0;
            }
            else if (sld >= 25000.0) {
                sld = 25000.0;
            }
            long qm = (long)(lvl * (zs + 1) * 10000);
            for (int j = 0; j < palData.getSkills().length; ++j) {
                Skill skill = GameServer.getSkill(palData.getSkills()[j]);
                if (skill != null) {
                    if (skill.getSkillid() >= 1001 && skill.getSkillid() <= 1100) {
                        FightingSkill fightingSkill = new FightingSkill(skill, lvl, zs, sld, 0L, 0);
                        data.addSkill(fightingSkill);
                    }
                    else if (skill.getSkillid() >= 1500 && skill.getSkillid() <= 2000) {
                        FightingSkill fightingSkill = new FightingSkill(skill, lvl, zs, 1.0, qm, 0);
                        data.addSkill(fightingSkill);
                    }
                }
            }
        }
        if (pal.getParts() != null && !pal.getParts().equals("")) {
            String[] v = pal.getParts().split("\\|");
            for (int k = 0; k < v.length; ++k) {
                String[] vs = v[k].split("=");
                Goodstable good = AllServiceUtil.getGoodsTableService().getGoodsByRgID(new BigDecimal(vs[1]));
                if (good != null) {
                    BaseEquip equip = good.getEquip();
                    if (equip != null && equip.getQls() != null) {
                        for (int l = equip.getQls().size() - 1; l >= 0; --l) {
                            BaseQl baseQl = (BaseQl)equip.getQls().get(l);
                            CalculationUtil.addValue(map, baseQl.getKey(), baseQl.getValue());
                        }
                    }
                    if (equip != null && equip.getQlews() != null) {
                        for (int l = equip.getQlews().size() - 1; l >= 0; --l) {
                            BaseQl baseQl = (BaseQl)equip.getQlews().get(l);
                            CalculationUtil.addValue(map, baseQl.getKey(), baseQl.getValue());
                        }
                    }
                }
            }
        }
        List<FightingLingbao> lings = new ArrayList<>();
        Lingbao lingbao = pal.getLingbao();
        if (lingbao != null) {
            CalculationUtil.addVS(map, lingbao.getKangxing());
            FightingLingbao ling = new FightingLingbao(new ManData(lingbao, palData), 1);
            lings.add(ling);
        }
        data.setLings(lings);
        data.setHuoyue(CalculationUtil.removeValue("根骨", map));
        data.setShanghai(CalculationUtil.removeValue("灵性", map));
        data.setKangluobao(CalculationUtil.removeValue("力量", map));
        data.setYuanzhu(CalculationUtil.removeValue("敏捷", map));
        data.setHp_z(getBase(pal.getLvl(), (int)data.getHuoyue(), pal.getGrow(), palData.getHp(), 0, map));
        data.setMp_z(getBase(pal.getLvl(), (int)data.getShanghai(), pal.getGrow(), palData.getMp(), 1, map));
        data.setAp(getBase(pal.getLvl(), (int)data.getKangluobao(), pal.getGrow(), palData.getAp(), 2, map));
        data.setSp(getBase(pal.getLvl(), (int)data.getYuanzhu(), pal.getGrow(), palData.getSp(), 3, map));
        data.setHp(data.getHp_z());
        data.setMp(data.getMp_z());
        Ql ql2 = new Ql();
        double value2 = CalculationUtil.removeValue("四抗", map);
        if (value2 != 0.0) {
            CalculationUtil.addValue(map, "抗混乱", value2);
            CalculationUtil.addValue(map, "抗封印", value2);
            CalculationUtil.addValue(map, "抗昏睡", value2);
            CalculationUtil.addValue(map, "抗遗忘", value2);
        }
        value2 = CalculationUtil.removeValue("抗仙法", map);
        if (value2 != 0.0) {
            CalculationUtil.addValue(map, "抗风", value2);
            CalculationUtil.addValue(map, "抗火", value2);
            CalculationUtil.addValue(map, "抗水", value2);
            CalculationUtil.addValue(map, "抗雷", value2);
        }
        for (String key : map.keySet()) {
            GetqualityUntil.AddR(ql2, key, (double)map.get(key));
        }
        data.setQuality(ql2);
    }
    
    public static int getBase(int lvl, int P, double G, int base, int type, Map<String, Double> map) {
        int value = BaseValue.getPetValue(lvl, P, G, base, type);
        if (type == 0) {
            value = (int)((double)value + CalculationUtil.removeValue("hp", map));
            value = (int)((double)value + CalculationUtil.removeValue("HP", map));
            value = (int)((double)value + CalculationUtil.removeValue("加气血", map));
            value = (int)((double)value + CalculationUtil.removeValue("附加气血", map));
            value = (int)((double)value * (CalculationUtil.removeValue("HP成长", map) + 1.0));
            value = (int)((double)value * (CalculationUtil.removeValue("加强气血", map) / 100.0 + 1.0));
            value = (int)((double)value * (CalculationUtil.removeValue("气血增加率", map) / 100.0 + 1.0));
        }
        else if (type == 1) {
            value = (int)((double)value + CalculationUtil.removeValue("mp", map));
            value = (int)((double)value + CalculationUtil.removeValue("MP", map));
            value = (int)((double)value + CalculationUtil.removeValue("加魔法", map));
            value = (int)((double)value + CalculationUtil.removeValue("附加法力", map));
            value = (int)((double)value + CalculationUtil.removeValue("附加魔法", map));
            value = (int)((double)value * (CalculationUtil.removeValue("MP成长", map) + 1.0));
            value = (int)((double)value * (CalculationUtil.removeValue("加强魔法", map) / 100.0 + 1.0));
        }
        else if (type == 2) {
            value = (int)((double)value + CalculationUtil.removeValue("ap", map));
            value = (int)((double)value + CalculationUtil.removeValue("AP", map));
            value = (int)((double)value + CalculationUtil.removeValue("攻击", map));
            value = (int)((double)value + CalculationUtil.removeValue("加攻击", map));
            value = (int)((double)value + CalculationUtil.removeValue("基础攻击", map));
            value = (int)((double)value + CalculationUtil.removeValue("附加攻击", map));
            value = (int)((double)value * (CalculationUtil.removeValue("AP成长", map) + 1.0));
            value = (int)((double)value * (CalculationUtil.removeValue("加强攻击", map) / 100.0 + 1.0));
            value = (int)((double)value * (CalculationUtil.removeValue("加成攻击", map) / 100.0 + 1.0));
        }
        else if (type == 3) {
            value = (int)((double)value + CalculationUtil.removeValue("sp", map));
            value = (int)((double)value + CalculationUtil.removeValue("SP", map));
            value = (int)((double)value + CalculationUtil.removeValue("速度", map));
            value = (int)((double)value + CalculationUtil.removeValue("加速度", map));
            value = (int)((double)value + CalculationUtil.removeValue("附加速度", map));
            value = (int)((double)value * (CalculationUtil.removeValue("SP成长", map) + 1.0));
            value = (int)((double)value * (CalculationUtil.removeValue("加强速度", map) / 100.0 + 1.0));
        }
        return value;
    }
}
