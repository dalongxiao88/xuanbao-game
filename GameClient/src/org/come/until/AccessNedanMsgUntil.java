package org.come.until;

import org.come.bean.Skill;
import java.util.List;
import org.come.bean.ConfigureBean;
import com.gl.util.LingXiUtil;
import org.come.entity.MountSkill;
import org.come.MountShouHu.ShouhuPackJpanel;
import org.come.MountShouHu.ShouhuPackJframe;
import org.come.mouslisten.PetAddPointMouslisten;
import org.come.entity.Mount;
import org.come.Jpanel.ZhuJpanel;
import org.come.model.Configure;
import org.come.entity.RoleSummoning;
import java.util.Iterator;
import com.tool.role.QualityZW;
import com.tool.role.Ql;
import org.come.model.PalData;
import com.tool.role.RoleProperty;
import com.tool.btn.BaptizeBtn;
import java.math.BigDecimal;
import org.come.entity.Goodstable;
import com.tool.role.RoleData;
import java.util.HashMap;
import org.come.entity.Pal;
import java.util.Map;

public class AccessNedanMsgUntil
{
    private static Map<String, Double> map;
    
    public static void addMapValue(Map<String, Double> map, String key, double value) {
        if (value == 0.0) {
            return;
        }
        Double mapValue = (Double)map.get(key);
        if (mapValue != null) {
            value += (double)mapValue;
        }
        map.put(key, Double.valueOf(value));
    }
    
    public static double getMapValue(Map<String, Double> map, String key) {
        Double mapValue = (Double)map.get(key);
        return (mapValue != null) ? ((double)mapValue) : 0.0;
    }
    
    public static synchronized Map<String, Double> getPalMap(Pal pal, boolean isV) {
        if (AccessNedanMsgUntil.map == null) {
            AccessNedanMsgUntil.map = new HashMap<>();
        }
        else {
            AccessNedanMsgUntil.map.clear();
        }
        PalData palData = UserMessUntil.getPalData(pal.getpId());
        if (palData == null) {
            return AccessNedanMsgUntil.map;
        }
        Integer grade = RoleData.getRoleData().getLoginResult().getGrade();
        int lvl = AnalysisString.lvlint((int)grade);
        int zs = AnalysisString.lvltrue((int)grade);
        if (isV) {
            int[] jds = palData.getJds();
            int size = jds[0] + jds[1] + jds[2] + jds[3];
            int point = lvl + zs * 40;
            addMapValue(AccessNedanMsgUntil.map, "根骨", (double)(lvl + point / size * palData.getJds()[0]));
            addMapValue(AccessNedanMsgUntil.map, "灵性", (double)(lvl + point / size * palData.getJds()[1]));
            addMapValue(AccessNedanMsgUntil.map, "力量", (double)(lvl + point / size * palData.getJds()[2]));
            addMapValue(AccessNedanMsgUntil.map, "敏捷", (double)(lvl + point / size * palData.getJds()[3]));
            point = lvl % size;
            for (int i = 0; i < jds.length && point > 0; ++i) {
                if (jds[i] != 0) {
                    if (point > jds[i]) {
                        addMapValue(AccessNedanMsgUntil.map, (i == 0) ? "根骨" : ((i == 1) ? "灵性" : ((i == 2) ? "力量" : "敏捷")), (double)palData.getJds()[i]);
                        point -= palData.getJds()[i];
                    }
                    else {
                        addMapValue(AccessNedanMsgUntil.map, (i == 0) ? "根骨" : ((i == 1) ? "灵性" : ((i == 2) ? "力量" : "敏捷")), (double)point);
                        point = 0;
                    }
                }
            }
        }
        else if (palData.getKx() != null && !palData.getKx().equals("")) {
            String[] v = palData.getKx().split("\\|");
            for (int j = 0; j < v.length; ++j) {
                String[] vs = v[j].split("=");
                double value = Double.parseDouble(vs[1]);
                if (vs.length == 3) {
                    value += Double.parseDouble(vs[2]) * (double)lvl;
                }
                addMapValue(AccessNedanMsgUntil.map, vs[0], value);
            }
        }
        if (pal.getParts() != null && !pal.getParts().equals("")) {
            String[] v = pal.getParts().split("\\|");
            for (int j = 0; j < v.length; ++j) {
                String[] vs = v[j].split("=");
                Goodstable good = (Goodstable)GoodsListFromServerUntil.fushis.get(new BigDecimal(vs[1]));
                if (good != null) {
                    String[] vss = good.getValue().split("\\|");
                    for (int k = 1; k < vss.length; ++k) {
                        if (vss[k].startsWith(BaptizeBtn.Extras[0])) {
                            String[] vStrings = vss[k].split("\\&");
                            for (int l = 1; l < vStrings.length; ++l) {
                                String[] mes = vStrings[l].split("=");
                                addMapValue(AccessNedanMsgUntil.map, mes[0], Double.parseDouble(mes[1]));
                            }
                        }
                        else {
                            String[] mes2 = vss[k].split("=");
                            addMapValue(AccessNedanMsgUntil.map, mes2[0], RoleProperty.getdouble(mes2[1]));
                        }
                    }
                }
            }
        }
        if (!isV) {
            Double value2 = (Double)AccessNedanMsgUntil.map.remove("四抗");
            if (value2 != null) {
                addMapValue(AccessNedanMsgUntil.map, "抗混乱", (double)value2);
                addMapValue(AccessNedanMsgUntil.map, "抗封印", (double)value2);
                addMapValue(AccessNedanMsgUntil.map, "抗昏睡", (double)value2);
                addMapValue(AccessNedanMsgUntil.map, "抗遗忘", (double)value2);
            }
            value2 = (Double)AccessNedanMsgUntil.map.remove("抗仙法");
            if (value2 != null) {
                addMapValue(AccessNedanMsgUntil.map, "抗风", (double)value2);
                addMapValue(AccessNedanMsgUntil.map, "抗火", (double)value2);
                addMapValue(AccessNedanMsgUntil.map, "抗水", (double)value2);
                addMapValue(AccessNedanMsgUntil.map, "抗雷", (double)value2);
            }
        }
        int spir = (int)getMapValue(AccessNedanMsgUntil.map, "灵性");
        addMapValue(AccessNedanMsgUntil.map, "法术命中率", (double)spir * 0.02);
        addMapValue(AccessNedanMsgUntil.map, "法术暴击", (double)spir * 0.03);
        addMapValue(AccessNedanMsgUntil.map, "法术暴击增伤", (double)spir * 0.04);
        return AccessNedanMsgUntil.map;
    }
    
    public static synchronized Ql getPalQl(Ql ql, Pal pal) {
        if (ql == null) {
            ql = new Ql();
        }
        else {
            ql.Reset();
        }
        if (pal == null) {
            return ql;
        }
        PalData palData = UserMessUntil.getPalData(pal.getpId());
        if (palData == null) {
            return ql;
        }
        Map<String, Double> map = getPalMap(pal, false);
        for (Map.Entry<String, Double> entry : map.entrySet()) {
            QualityZW.insertValues(ql, (String)entry.getKey(), (double)entry.getValue());
        }
        return ql;
    }
    
    public static synchronized Ql getPetQl(Ql ql, RoleSummoning pet) {
        ConfigureBean allConfigure = UserMessUntil.getConfigureBean();
        Map<BigDecimal, Configure> item = allConfigure.getAllConfigure();
        Configure configure = (Configure)item.get(new BigDecimal(1));
        if (ql == null) {
            ql = new Ql();
        }
        else {
            ql.Reset();
        }
        if (pet == null) {
            return ql;
        }
        int up = 75;
        if (configure.getZhskxsx() != null && !configure.getZhskxsx().equals("")) {
            up += Integer.parseInt(configure.getZhskxsx());
        }
        try {
            if (pet.getStye() != null && pet.getStye().length() > 1) {
                String[] v = pet.getStye().split("\\|");
                for (int i = 1; i < v.length; ++i) {
                    String[] vs = v[i].split("-");
                    if (vs.length >= 2) {
                        Goodstable goodstable = (Goodstable)GoodsListFromServerUntil.fushis.get(new BigDecimal(vs[1]));
                        if (goodstable != null && (long)goodstable.getType() != 729L) {
                            String[] t = goodstable.getValue().split("\\|");
                            for (int j = 6; j < t.length; ++j) {
                                if (t[j].startsWith("炼化属性")) {
                                    String[] vStrings = t[j].split("\\&");
                                    for (int k = 1; k < vStrings.length; ++k) {
                                        String[] mes = vStrings[k].split("=");
                                        if (!mes[0].endsWith("等级")) {
                                            QualityZW.insertValues(ql, mes[0], Double.parseDouble(mes[1]));
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
            if (pet.getResistance() != null && !pet.getResistance().equals("")) {
                String[] v = pet.getResistance().split("\\|");
                if (v.length >= 3) {
                    JmSum.xiugaiqi();
                    return ql;
                }
                for (int i = 0; i < v.length; ++i) {
                    String[] v2 = v[i].split("=");
                    double value = Double.parseDouble(v2[1]);
                    if (value != 30.0) {
                        JmSum.xiugaiqi();
                        return ql;
                    }
                    QualityZW.insertValues(ql, v2[0], value);
                }
            }
            int l = 0;
            while (l < ZhuJpanel.getListMount().size()) {
                Mount mount = (Mount)ZhuJpanel.getListMount().get(l);
                if (mount.isID(pet.getSid())) {
                    up += 20;
                    inster(ql, "狂暴率", (double)PetAddPointMouslisten.value("狂暴率", (int)mount.getMountid())[1]);
                    inster(ql, "命中率", (double)PetAddPointMouslisten.value("命中率", (int)mount.getMountid())[1]);
                    inster(ql, "抗致命率", (double)PetAddPointMouslisten.value("抗致命率", (int)mount.getMountid())[1]);
                    inster(ql, "抗风法狂暴", (double)PetAddPointMouslisten.value("抗仙法鬼火狂暴几率", (int)mount.getMountid())[1]);
                    inster(ql, "抗火法狂暴", (double)PetAddPointMouslisten.value("抗仙法鬼火狂暴几率", (int)mount.getMountid())[1]);
                    inster(ql, "抗水法狂暴", (double)PetAddPointMouslisten.value("抗仙法鬼火狂暴几率", (int)mount.getMountid())[1]);
                    inster(ql, "抗雷法狂暴", (double)PetAddPointMouslisten.value("抗仙法鬼火狂暴几率", (int)mount.getMountid())[1]);
                    inster(ql, "抗鬼火狂暴", (double)PetAddPointMouslisten.value("抗仙法鬼火狂暴几率", (int)mount.getMountid())[1]);
                    inster(ql, "物理吸收率", (double)PetAddPointMouslisten.value("物理吸收率", (int)mount.getMountid())[1]);
                    inster(ql, "法术躲闪", (double)PetAddPointMouslisten.value("法术躲闪几率", (int)mount.getMountid())[1]);
                    inster(ql, "忽视抗风", (double)PetAddPointMouslisten.value("忽视抗仙法鬼火", (int)mount.getMountid())[1]);
                    inster(ql, "忽视抗雷", (double)PetAddPointMouslisten.value("忽视抗仙法鬼火", (int)mount.getMountid())[1]);
                    inster(ql, "忽视抗水", (double)PetAddPointMouslisten.value("忽视抗仙法鬼火", (int)mount.getMountid())[1]);
                    inster(ql, "忽视抗火", (double)PetAddPointMouslisten.value("忽视抗仙法鬼火", (int)mount.getMountid())[1]);
                    inster(ql, "忽视抗鬼火", (double)PetAddPointMouslisten.value("忽视抗仙法鬼火", (int)mount.getMountid())[1]);
                    if (mount.getShouhu() != 0) {
                        Ql finalQl = ql;
                        ShouhuPackJframe.getShouhuPackJframe().getShouhuPackJpanel();
                        ShouhuPackJpanel.Eqment.forEach(P/* org.come.entity.Goodstable, */ -> {
                            if (P.getRgid().intValue() == mount.getShouhu()) {
                                if (P.getValue() != null && !P.getValue().equals("")) {
                                    String[] mes2 = P.getValue().split("\\|");
                                    for (int t2 = 1; t2 <= mes2.length - 1; ++t2) {
                                        if (mes2[t2].split("=")[0].matches("\\d+") && UserMessUntil.getSkillId(mes2[t2].split("=")[0]) != null) {
                                            Skill skill = UserMessUntil.getSkillById(mes2[t2].split("=")[0]);
                                            if (skill.getSkillid().equals("4022")) {
                                                QualityZW.insertValues(finalQl, "抗物理", -Double.parseDouble(skill.getValue1()));
                                                QualityZW.insertValues(finalQl, "抗风", -Double.parseDouble(skill.getValue1()));
                                            }
                                            else if (skill.getSkillid().equals("4022")) {
                                                QualityZW.insertValues(finalQl, "抗物理", -Double.parseDouble(skill.getValue1()));
                                                QualityZW.insertValues(finalQl, "抗风", -Double.parseDouble(skill.getValue1()));
                                            }
                                        }
                                    }
                                }
                                if (P.getValue() != null && !P.getValue().equals("")) {
                                    String[] mes3 = P.getValue().split("\\|");
                                    for (int d = 1; d <= mes3.length - 1; ++d) {
                                        if (!mes3[d].split("=")[0].contains("气血") && !mes3[d].split("=")[0].contains("魔法") && !mes3[d].split("=")[0].contains("速度") && !mes3[d].split("=")[0].contains("攻击") && mes3[d].split("=").length == 2) {
                                            inster(finalQl, mes3[d].split("=")[0], Double.parseDouble(mes3[d].split("=")[1]));
                                        }
                                    }
                                }
                            }
                            return;
                        });
                    }
                    List<MountSkill> mountSkills = mount.getMountskill();
                    if (mountSkills != null) {
                        for (int m = 0; m < mountSkills.size(); ++m) {
                            String[] mountStrings = Util.calculateAddition(mount, ((MountSkill)mountSkills.get(m)).getSkillname()).split("\\|");
                            for (int k2 = 0; k2 < mountStrings.length; ++k2) {
                                String[] vs2 = mountStrings[k2].split("=");
                                double value2 = Double.parseDouble(vs2[1]);
                                if (!vs2[0].equals("HP") && !vs2[0].equals("MP") && !vs2[0].equals("AP") && !vs2[0].equals("SP")) {
                                    if (!vs2[0].equals("抗灵宝伤害")) {
                                        if (vs2[0].equals("风法伤害") || vs2[0].equals("水法伤害") || vs2[0].equals("鬼火伤害") || vs2[0].equals("火法伤害") || vs2[0].equals("雷法伤害")) {
                                            value2 *= 10000.0;
                                        }
                                        else if (vs2[0].indexOf("次数") == -1 && vs2[0].indexOf("三尸") == -1) {
                                            value2 *= 100.0;
                                        }
                                    }
                                    QualityZW.insertValues(ql, vs2[0], value2);
                                }
                            }
                        }
                        break;
                    }
                    else {
                        break;
                    }
                }
                else {
                    ++l;
                }
            }
            if (pet.getInnerGoods() != null && !pet.getInnerGoods().equals("")) {
                String[] vv = pet.getInnerGoods().split("\\|");
                for (int i = 0; i < vv.length; ++i) {
                    Goodstable goodstable2 = (Goodstable)GoodsListFromServerUntil.fushis.get(new BigDecimal(vv[i]));
                    if (goodstable2 != null) {
                        String goodname = goodstable2.getGoodsname();
                        boolean type = goodname.equals("红颜白发") || goodname.equals("梅花三弄") || goodname.equals("开天辟地") || goodname.equals("万佛朝宗") || goodname.equals("暗渡陈仓") || goodname.equals("借力打力") || goodname.equals("凌波微步");
                        if (type) {
                            String[] strings = goodstable2.getValue().split("\\|");
                            String[] stringLevel = strings[2].split("\\=");
                            String[] stringLevel2 = stringLevel[1].split("\\转");
                            int nddj = Integer.parseInt(stringLevel2[1]);
                            int ndzscs = Integer.parseInt(stringLevel2[0]);
                            if (type) {
                                addNedanMsg(pet, ql, nddj, ndzscs, goodname);
                            }
                        }
                    }
                }
            }
            if (pet.getLyk() != null && !pet.getLyk().equals("")) {
                String[] v = pet.getLyk().split("\\|");
                for (int i = 0; i < v.length; ++i) {
                    String[] v2 = v[i].split("=");
                    QualityZW.insertValues(ql, v2[0], Double.parseDouble(v2[1]));
                }
            }
            addQlSkill(ql, pet.getSkill(), (long)pet.getFriendliness());
            addQlSkill(ql, pet.getPetSkills(), (long)pet.getFriendliness());
            addQlSkill(ql, pet.getBeastSkills(), (long)pet.getFriendliness());
            if ((long)pet.getFriendliness() > 100000L) {
                double ljv = 5.0 + CustomFunction.XS((long)pet.getFriendliness(), 0.7);
                if (ljv > 12.0) {
                    ljv = 12.0;
                }
                int ljs = (int)(3.0 + CustomFunction.XS((long)pet.getFriendliness(), 0.1));
                if (ljs > 9) {
                    ljs = 9;
                }
                double mzv = 2.0 + CustomFunction.XS((long)pet.getFriendliness(), 0.4);
                if (mzv > 30.0) {
                    mzv = 30.0;
                }
                QualityZW.insertValues(ql, "连击率", ljv);
                QualityZW.insertValues(ql, "连击次数", (double)ljs);
                QualityZW.insertValues(ql, "命中率", mzv);
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        ql.addKUp((double)up);
        ql.addKKUp((double)(up + ((pet.getTurnRount() >= 4) ? 10 : 0)));
        int kx = LingXiUtil.getNumberByStr(pet.getLingxi(), "11005", 1);
        if (kx > 0) {
            QualityZW.insertValues(ql, "抗混乱", (double)kx);
            QualityZW.insertValues(ql, "抗封印", (double)kx);
            QualityZW.insertValues(ql, "抗昏睡", (double)kx);
            QualityZW.insertValues(ql, "抗遗忘", (double)kx);
        }
        if (ql.getRolewxj() != 0.0 || ql.getRolewxm() != 0.0 || ql.getRolewxt() != 0.0 || ql.getRolewxs() != 0.0 || ql.getRolewxh() != 0.0) {
            QualityZW.insertValues(ql, "金", Double.parseDouble(pet.getGold()) / 2.0);
            QualityZW.insertValues(ql, "木", Double.parseDouble(pet.getWood()) / 2.0);
            QualityZW.insertValues(ql, "土", Double.parseDouble(pet.getSoil()) / 2.0);
            QualityZW.insertValues(ql, "水", Double.parseDouble(pet.getWater()) / 2.0);
            QualityZW.insertValues(ql, "火", Double.parseDouble(pet.getFire()) / 2.0);
        }
        else {
            QualityZW.insertValues(ql, "金", Double.parseDouble(pet.getGold()));
            QualityZW.insertValues(ql, "木", Double.parseDouble(pet.getWood()));
            QualityZW.insertValues(ql, "土", Double.parseDouble(pet.getSoil()));
            QualityZW.insertValues(ql, "水", Double.parseDouble(pet.getWater()));
            QualityZW.insertValues(ql, "火", Double.parseDouble(pet.getFire()));
        }
        ql.setRolewxj((double)(int)ql.getRolewxj());
        ql.setRolewxm((double)(int)ql.getRolewxm());
        ql.setRolewxt((double)(int)ql.getRolewxt());
        ql.setRolewxs((double)(int)ql.getRolewxs());
        ql.setRolewxh((double)(int)ql.getRolewxh());
        return ql;
    }
    
    public static void inster(Ql ql, String v, double value2) {
        QualityZW.insertValues(ql, v, value2);
    }
    
    public static void addQlSkill(Ql ql, String skill, long qm) {
        if (skill == null || skill.equals("")) {
            return;
        }
        String[] v = skill.split("\\|");
        for (int i = 0; i < v.length; ++i) {
            String s = v[i];
            int n = -1;
            switch (s.hashCode()) {
                case 1515142: {
                    if (s.equals("1810")) {
                        n = 0;
                        break;
                    }
                    else {
                        break;
                    }
                }
                case 1515143: {
                    if (s.equals("1811")) {
                        n = 1;
                        break;
                    }
                    else {
                        break;
                    }
                }
                case 1515145: {
                    if (s.equals("1813")) {
                        n = 2;
                        break;
                    }
                    else {
                        break;
                    }
                }
                case 1515173: {
                    if (s.equals("1820")) {
                        n = 3;
                        break;
                    }
                    else {
                        break;
                    }
                }
                case 1515174: {
                    if (s.equals("1821")) {
                        n = 4;
                        break;
                    }
                    else {
                        break;
                    }
                }
                case 1515175: {
                    if (s.equals("1822")) {
                        n = 5;
                        break;
                    }
                    else {
                        break;
                    }
                }
                case 1515176: {
                    if (s.equals("1823")) {
                        n = 6;
                        break;
                    }
                    else {
                        break;
                    }
                }
                case 1515177: {
                    if (s.equals("1824")) {
                        n = 7;
                        break;
                    }
                    else {
                        break;
                    }
                }
                case 1515147: {
                    if (s.equals("1815")) {
                        n = 8;
                        break;
                    }
                    else {
                        break;
                    }
                }
                case 1515148: {
                    if (s.equals("1816")) {
                        n = 9;
                        break;
                    }
                    else {
                        break;
                    }
                }
                case 1515149: {
                    if (s.equals("1817")) {
                        n = 10;
                        break;
                    }
                    else {
                        break;
                    }
                }
                case 1515150: {
                    if (s.equals("1818")) {
                        n = 11;
                        break;
                    }
                    else {
                        break;
                    }
                }
                case 1515151: {
                    if (s.equals("1819")) {
                        n = 12;
                        break;
                    }
                    else {
                        break;
                    }
                }
                case 1509352: {
                    if (s.equals("1207")) {
                        n = 13;
                        break;
                    }
                    else {
                        break;
                    }
                }
                case 1509567: {
                    if (s.equals("1275")) {
                        n = 14;
                        break;
                    }
                    else {
                        break;
                    }
                }
                case 1509353: {
                    if (s.equals("1208")) {
                        n = 15;
                        break;
                    }
                    else {
                        break;
                    }
                }
                case 1509566: {
                    if (s.equals("1274")) {
                        n = 16;
                        break;
                    }
                    else {
                        break;
                    }
                }
                case 1509354: {
                    if (s.equals("1209")) {
                        n = 17;
                        break;
                    }
                    else {
                        break;
                    }
                }
                case 1509379: {
                    if (s.equals("1213")) {
                        n = 18;
                        break;
                    }
                    else {
                        break;
                    }
                }
                case 1509380: {
                    if (s.equals("1214")) {
                        n = 19;
                        break;
                    }
                    else {
                        break;
                    }
                }
                case 1509409: {
                    if (s.equals("1222")) {
                        n = 20;
                        break;
                    }
                    else {
                        break;
                    }
                }
                case 1509413: {
                    if (s.equals("1226")) {
                        n = 21;
                        break;
                    }
                    else {
                        break;
                    }
                }
                case 1515240: {
                    if (s.equals("1845")) {
                        n = 22;
                        break;
                    }
                    else {
                        break;
                    }
                }
                case 1515241: {
                    if (s.equals("1846")) {
                        n = 23;
                        break;
                    }
                    else {
                        break;
                    }
                }
                case 1515271: {
                    if (s.equals("1855")) {
                        n = 24;
                        break;
                    }
                    else {
                        break;
                    }
                }
                case 1515272: {
                    if (s.equals("1856")) {
                        n = 25;
                        break;
                    }
                    else {
                        break;
                    }
                }
                case 1515273: {
                    if (s.equals("1857")) {
                        n = 26;
                        break;
                    }
                    else {
                        break;
                    }
                }
                case 1515275: {
                    if (s.equals("1859")) {
                        n = 27;
                        break;
                    }
                    else {
                        break;
                    }
                }
                case 1515297: {
                    if (s.equals("1860")) {
                        n = 28;
                        break;
                    }
                    else {
                        break;
                    }
                }
                case 1509568: {
                    if (s.equals("1276")) {
                        n = 29;
                        break;
                    }
                    else {
                        break;
                    }
                }
            }
            switch (n) {
                case 0: {
                    double value = 5.0 + CustomFunction.XS(qm, 0.3);
                    QualityZW.insertValues(ql, "抗风", -value);
                    QualityZW.insertValues(ql, "抗火", -value);
                    QualityZW.insertValues(ql, "抗水", -value);
                    QualityZW.insertValues(ql, "抗雷", -value);
                    QualityZW.insertValues(ql, "抗鬼火", -value);
                    QualityZW.insertValues(ql, "反震率", value);
                    QualityZW.insertValues(ql, "反震程度", value);
                    break;
                }
                case 1: {
                    double value = 10.0 + CustomFunction.XS(qm, 0.6);
                    QualityZW.insertValues(ql, "抗风", -value);
                    QualityZW.insertValues(ql, "抗火", -value);
                    QualityZW.insertValues(ql, "抗水", -value);
                    QualityZW.insertValues(ql, "抗雷", -value);
                    QualityZW.insertValues(ql, "抗鬼火", -value);
                    QualityZW.insertValues(ql, "反震率", value);
                    QualityZW.insertValues(ql, "反震程度", value);
                    break;
                }
                case 2:
                case 3:
                case 4:
                case 5:
                case 6:
                case 7: {
                    double value = 2.0 + CustomFunction.XS(qm, 0.2);
                    QualityZW.insertValues(ql, "抗封印", value);
                    QualityZW.insertValues(ql, "抗混乱", value);
                    QualityZW.insertValues(ql, "抗遗忘", value);
                    break;
                }
                case 8: {
                    double value = 2.0 + CustomFunction.XS(qm, 0.2);
                    QualityZW.insertValues(ql, "金", 50.0);
                    QualityZW.insertValues(ql, "抗封印", value);
                    QualityZW.insertValues(ql, "抗混乱", value);
                    QualityZW.insertValues(ql, "抗遗忘", value);
                    break;
                }
                case 9: {
                    double value = 2.0 + CustomFunction.XS(qm, 0.2);
                    QualityZW.insertValues(ql, "木", 50.0);
                    QualityZW.insertValues(ql, "抗封印", value);
                    QualityZW.insertValues(ql, "抗混乱", value);
                    QualityZW.insertValues(ql, "抗遗忘", value);
                    break;
                }
                case 10: {
                    double value = 2.0 + CustomFunction.XS(qm, 0.2);
                    QualityZW.insertValues(ql, "水", 50.0);
                    QualityZW.insertValues(ql, "抗封印", value);
                    QualityZW.insertValues(ql, "抗混乱", value);
                    QualityZW.insertValues(ql, "抗遗忘", value);
                    break;
                }
                case 11: {
                    double value = 2.0 + CustomFunction.XS(qm, 0.2);
                    QualityZW.insertValues(ql, "火", 50.0);
                    QualityZW.insertValues(ql, "抗封印", value);
                    QualityZW.insertValues(ql, "抗混乱", value);
                    QualityZW.insertValues(ql, "抗遗忘", value);
                    break;
                }
                case 12: {
                    double value = 2.0 + CustomFunction.XS(qm, 0.2);
                    QualityZW.insertValues(ql, "土", 50.0);
                    QualityZW.insertValues(ql, "抗封印", value);
                    QualityZW.insertValues(ql, "抗混乱", value);
                    QualityZW.insertValues(ql, "抗遗忘", value);
                    break;
                }
                case 13:
                case 14: {
                    double value = 3.0 + CustomFunction.XS(qm, 0.2);
                    QualityZW.insertValues(ql, "连击率", value);
                    break;
                }
                case 15:
                case 16: {
                    double value = 10.0 + CustomFunction.XS(qm, 0.6);
                    QualityZW.insertValues(ql, "忽视防御几率", value);
                    QualityZW.insertValues(ql, "忽视防御程度", value);
                    break;
                }
                case 17: {
                    double value = 5.0 + CustomFunction.XS(qm, 0.3);
                    QualityZW.insertValues(ql, "狂暴率", value);
                    QualityZW.insertValues(ql, "致命率", value);
                    break;
                }
                case 18: {
                    double value = 5.0 + CustomFunction.XS(qm, 0.3);
                    QualityZW.insertValues(ql, "反震率", value);
                    QualityZW.insertValues(ql, "反震程度", value);
                    break;
                }
                case 19: {
                    double value = 5.0 + CustomFunction.XS(qm, 0.4);
                    QualityZW.insertValues(ql, "狂暴率", value);
                    QualityZW.insertValues(ql, "致命率", value);
                    QualityZW.insertValues(ql, "命中率", value);
                    break;
                }
                case 20: {
                    double value = 5.0 + CustomFunction.XS(qm, 0.4);
                    QualityZW.insertValues(ql, "抗震慑", value);
                    break;
                }
                case 21: {
                    double value = 10.0 + CustomFunction.XS(qm, 0.6);
                    QualityZW.insertValues(ql, "物理吸收率", value);
                    break;
                }
                case 22: {
                    double value = 5.0 + CustomFunction.XS(qm, 0.5);
                    QualityZW.insertValues(ql, "附加封印攻击", value);
                    break;
                }
                case 23: {
                    double value = 5.0 + CustomFunction.XS(qm, 0.5);
                    QualityZW.insertValues(ql, "附加混乱攻击", value);
                    break;
                }
                case 24: {
                    double value = 10.0 + CustomFunction.XS(qm, 0.6);
                    QualityZW.insertValues(ql, "抗致命率", value);
                    break;
                }
                case 25: {
                    double value = 10.0 + CustomFunction.XS(qm, 0.6);
                    QualityZW.insertValues(ql, "仙法狂暴", value);
                    QualityZW.insertValues(ql, "鬼火狂暴", value);
                    break;
                }
                case 26: {
                    double value = 10.0 + CustomFunction.XS(qm, 0.6);
                    QualityZW.insertValues(ql, "忽视仙法", value);
                    QualityZW.insertValues(ql, "忽视鬼火", value);
                    break;
                }
                case 27: {
                    double value = 3.0 + CustomFunction.XS(qm, 0.3);
                    QualityZW.insertValues(ql, "抗封印", value);
                    QualityZW.insertValues(ql, "抗混乱", value);
                    QualityZW.insertValues(ql, "抗遗忘", value);
                    break;
                }
                case 28:
                case 29: {
                    double value = 5.0 + CustomFunction.XS(qm, 0.5);
                    QualityZW.insertValues(ql, "附加三尸攻击", value);
                    break;
                }
            }
        }
    }
    
    public static void addNedanMsg(RoleSummoning roleSummoning, Ql ql, int nddj, int ndzscs, String goodsname) {
        int zhsdj = AnalysisString.petLvlint((int)roleSummoning.getGrade());
        int zhszscs = roleSummoning.getTurnRount();
        long zhsqm = (long)roleSummoning.getFriendliness();
        if (goodsname.equals("暗渡陈仓")) {
            double ndjl = Math.floor((Math.pow((double)(zhsdj * nddj) * 0.04, 0.5) * (1.0 + 0.25 * (double)ndzscs) + Math.pow((double)zhsqm, 0.16666666666666666) * xstz(zhszscs, ndzscs) * (double)nddj / 50.0) * 1000.0) * 3.5E-6;
            QualityZW.insertValues(ql, "忽视躲闪", (double)Math.round(ndjl * 10000.0) / 100.0);
            QualityZW.insertValues(ql, "忽视反击", (double)Math.round(ndjl * 10000.0) / 100.0);
        }
        else if (goodsname.equals("凌波微步")) {
            double ndjl = Math.floor((Math.pow((double)(zhsdj * nddj) * 0.04, 0.5) * (1.0 + 0.25 * (double)ndzscs) + Math.pow((double)zhsqm, 0.16666666666666666) * xstz(zhszscs, ndzscs) * (double)nddj / 50.0) * 1000.0) * 1.0E-5;
            QualityZW.insertValues(ql, "躲闪率", (double)Math.round(ndjl * 10000.0) / 100.0);
        }
        else if (goodsname.equals("借力打力")) {
            double ndjl = Math.floor((Math.pow((double)(zhsdj * nddj) * 0.04, 0.5) * (1.0 + 0.25 * (double)ndzscs) + Math.pow((double)zhsqm, 0.16666666666666666) * xstz(zhszscs, ndzscs) * (double)nddj / 50.0) * 1000.0) * 1.0E-5;
            int ndcd_jldl = nd_jldl_fjcs(ndjl);
            QualityZW.insertValues(ql, "反击率", (double)Math.round(ndjl * 10000.0) / 100.0);
            QualityZW.insertValues(ql, "反击次数", (double)ndcd_jldl);
        }
        else if (goodsname.equals("梅花三弄")) {
            double ndjl = Math.floor((Math.pow((double)(zhsdj * nddj) * 0.04, 0.5) * (1.0 + 0.25 * (double)ndzscs) + Math.pow((double)zhsqm, 0.16666666666666666) * xstz(zhszscs, ndzscs) * (double)nddj / 50.0) * 1000.0) * 5.0E-6;
            int ndcd_jldl = nd_mhsn_ljcs(ndjl);
            QualityZW.insertValues(ql, "仙法连击率", (double)Math.round(ndjl * 10000.0) / 100.0);
            QualityZW.insertValues(ql, "仙法连击次数", (double)ndcd_jldl);
        }
        else if (goodsname.equals("红颜白发")) {
            double ndjl = Math.floor((Math.pow((double)(zhsdj * nddj) * 0.04, 0.5) * (1.0 + 0.25 * (double)ndzscs) + Math.pow((double)zhsqm, 0.16666666666666666) * xstz(zhszscs, ndzscs) * (double)nddj / 50.0) * 1000.0) * 5.0E-6;
            QualityZW.insertValues(ql, "仙法狂暴", (double)Math.round(ndjl * 10000.0) / 100.0);
            QualityZW.insertValues(ql, "鬼火狂暴", (double)Math.round(ndjl * 10000.0) / 100.0);
        }
        else if (goodsname.equals("开天辟地")) {
            double ndjl = Math.floor((Math.pow((double)(zhsdj * nddj) * 0.04, 0.5) * (1.0 + 0.25 * (double)ndzscs) + Math.pow((double)zhsqm, 0.16666666666666666) * xstz(zhszscs, ndzscs) * (double)nddj / 50.0) * 1000.0) * 5.0E-6;
            QualityZW.insertValues(ql, "忽视仙法抗性率", (double)Math.round(ndjl * 10000.0) / 100.0);
            QualityZW.insertValues(ql, "忽视仙法抗性程度", (double)Math.round(ndjl * 10000.0) / 100.0);
            QualityZW.insertValues(ql, "忽视鬼火", (double)Math.round(ndjl * 10000.0) / 100.0);
        }
        else if (goodsname.equals("万佛朝宗")) {
            double ndjl = Math.floor((Math.pow((double)(zhsdj * nddj) * 0.04, 0.5) * (1.0 + 0.25 * (double)ndzscs) + Math.pow((double)zhsqm, 0.16666666666666666) * xstz(zhszscs, ndzscs) * (double)nddj / 50.0) * 1000.0) * 5.0E-6;
            double ndcd = ndjl * 2.0;
            if (roleSummoning.getPetSkills() != null && roleSummoning.getPetSkills().contains("1247")) {
                QualityZW.insertValues(ql, "反震率", (double)Math.round(ndjl * 10100.0) / 100.0);
                QualityZW.insertValues(ql, "反震程度", (double)Math.round(ndcd * 10100.0) / 100.0);
            }
            else {
                QualityZW.insertValues(ql, "反震率", (double)Math.round(ndjl * 10000.0) / 100.0);
                QualityZW.insertValues(ql, "反震程度", (double)Math.round(ndcd * 10000.0) / 100.0);
            }
        }
    }
    
    public static String NedanMsg(RoleSummoning roleSummoning, int nddj, int ndzscs, String goodsname, int zhsmpz) {
        zhsmpz += 100;
        String NdMsg = null;
        int zhsdj = AnalysisString.petLvlint((int)roleSummoning.getGrade());
        int zhszscs = roleSummoning.getTurnRount();
        long zhsqm = (long)roleSummoning.getFriendliness();
        if ("暗渡陈仓".equals(goodsname)) {
            double ndjl = Math.floor((Math.pow((double)(zhsdj * nddj) * 0.04, 0.5) * (1.0 + 0.25 * (double)ndzscs) + Math.pow((double)zhsqm, 0.16666666666666666) * xstz(zhszscs, ndzscs) * (double)nddj / 50.0) * 1000.0) * 5.0E-6;
            NdMsg = "物理攻击的时能够忽视目标" + (double)Math.round(ndjl * 1000.0) / 10.0 + "%躲闪率和" + (double)Math.round(ndjl * 1000.0) / 10.0 + "%反击率。";
        }
        else if ("隔山打牛".equals(goodsname)) {
            double ndjl = Math.floor((Math.pow((double)(zhsdj * nddj) * 0.04, 0.5) * (1.0 + 0.25 * (double)ndzscs) + Math.pow((double)zhsqm, 0.16666666666666666) * xstz(zhszscs, ndzscs) * (double)nddj / 50.0) * 1000.0) * 5.0E-6;
            double ndcd = Math.floor(((double)(zhsdj * zhsdj) * 0.2 / (double)(zhsmpz * 1 + 1) + ndjl * 3.0) * 1000.0) / 1000.0;
            NdMsg = "物理攻击的时候有" + (double)Math.round(ndjl * 1000.0 * 0.7) / 10.0 + "%几率对攻击对象之外随机目标造成" + (double)Math.round(ndcd * 1000.0 * 0.7) / 10.0 + "%的伤害。";
        }
        else if ("浩然正气".equals(goodsname)) {
            double ndjl = Math.floor((Math.pow((double)(zhsdj * nddj) * 0.04, 0.5) * (1.0 + 0.25 * (double)ndzscs) + Math.pow((double)zhsqm, 0.16666666666666666) * xstz(zhszscs, ndzscs) * (double)nddj / 50.0) * 1000.0) * 5.0E-6;
            double ndcd = Math.floor(((double)(zhsdj * zhsdj) * 0.2 / (double)(zhsmpz * 1 + 1) + ndjl) * 1000.0) / 1000.0;
            NdMsg = "物理攻击时有" + (double)Math.round(ndjl * 1000.0) / 10.0 + "%几率对目标造成目标法力值" + (double)Math.round(ndcd * 1000.0) / 10.0 + "的伤害。";
        }
        else if ("凌波微步".equals(goodsname)) {
            double ndjl = Math.floor((Math.pow((double)(zhsdj * nddj) * 0.04, 0.5) * (1.0 + 0.25 * (double)ndzscs) + Math.pow((double)zhsqm, 0.16666666666666666) * xstz(zhszscs, ndzscs) * (double)nddj / 50.0) * 1000.0) * 1.0E-5;
            NdMsg = "提高召唤兽。" + (double)Math.round(ndjl * 1000.0) / 10.0 + "%的躲闪率";
        }
        else if ("借力打力".equals(goodsname)) {
            double ndjl = Math.floor((Math.pow((double)(zhsdj * nddj) * 0.04, 0.5) * (1.0 + 0.25 * (double)ndzscs) + Math.pow((double)zhsqm, 0.16666666666666666) * xstz(zhszscs, ndzscs) * (double)nddj / 50.0) * 1000.0) * 1.0E-5;
            int ndcd_jldl = nd_jldl_fjcs(ndjl);
            NdMsg = "在受到物理攻击的一回合内有" + (double)Math.round(ndjl * 1000.0) / 10.0 + "%的几率产生反击效果,反击次数为" + ndcd_jldl + "次";
        }
        else if ("梅花三弄".equals(goodsname)) {
            double ndjl = Math.floor((Math.pow((double)(zhsdj * nddj) * 0.04, 0.5) * (1.0 + 0.25 * (double)ndzscs) + Math.pow((double)zhsqm, 0.16666666666666666) * xstz(zhszscs, ndzscs) * (double)nddj / 50.0) * 1000.0) * 5.0E-6;
            int ndcd_jldl = nd_mhsn_ljcs(ndjl);
            NdMsg = "使用单体仙法、鬼火攻击时" + (double)Math.round(ndjl * 1000.0) / 10.0 + "%的几率出现法术连击,法术连击次数为" + ndcd_jldl + "次";
        }
        else if ("红颜白发".equals(goodsname)) {
            double ndjl = Math.floor((Math.pow((double)(zhsdj * nddj) * 0.04, 0.5) * (1.0 + 0.25 * (double)ndzscs) + Math.pow((double)zhsqm, 0.16666666666666666) * xstz(zhszscs, ndzscs) * (double)nddj / 50.0) * 1000.0) * 5.0E-6;
            NdMsg = "召唤兽使用仙法、鬼火攻击时，有" + (double)Math.round(ndjl * 1000.0) / 10.0 + "%几率出现法术狂暴";
        }
        else if ("开天辟地".equals(goodsname)) {
            double ndjl = Math.floor((Math.pow((double)(zhsdj * nddj) * 0.04, 0.5) * (1.0 + 0.25 * (double)ndzscs) + Math.pow((double)zhsqm, 0.16666666666666666) * xstz(zhszscs, ndzscs) * (double)nddj / 50.0) * 1000.0) * 5.0E-6;
            NdMsg = "召唤兽使用仙法攻击时，有" + (double)Math.round(ndjl * 1000.0) / 10.0 + "%几率忽视目标的法术抗性。使用鬼火时直接增加" + (double)Math.round(ndjl * 1000.0) / 10.0 + "%忽视抗鬼火";
        }
        else if ("万佛朝宗".equals(goodsname)) {
            double ndjl = Math.floor((Math.pow((double)(zhsdj * nddj) * 0.04, 0.5) * (1.0 + 0.25 * (double)ndzscs) + Math.pow((double)zhsqm, 0.16666666666666666) * xstz(zhszscs, ndzscs) * (double)nddj / 50.0) * 1000.0) * 5.0E-6;
            double ndcd = ndjl * 2.0;
            NdMsg = "召唤兽被对方攻击时，有" + (double)Math.round(ndjl * 1000.0) / 10.0 + "%几率产生" + (double)Math.round(ndcd * 1000.0) / 10.0 + "%反震效果";
        }
        else if ("天魔解体".equals(goodsname)) {
            double ndjl = 0.0;
            if (zhszscs == 0) {
                ndjl = Math.floor((Math.pow((double)(zhsdj * nddj) / 160000.0, 0.5) * (1.0 + 0.25 * (double)ndzscs) + Math.pow((double)zhsqm, 0.16666666666666666) * (double)nddj / 4000.0) * 1000.0) / 1000.0 + 0.01;
            }
            else {
                ndjl = Math.floor((Math.pow((double)(zhsdj * nddj) / 160000.0, 0.5) * (1.0 + 0.25 * (double)ndzscs) + Math.pow((double)zhsqm, 0.16666666666666666) * (double)nddj / 3755.0) * 1000.0) / 1000.0 + 0.01;
            }
            double ndhq = Math.floor(ndjl * ((double)(zhsdj * zhsdj) * 0.15 / ((double)(zhsmpz * 1) + 0.01) + 0.2) * 1000.0) / 1000.0;
            if (ndjl > 0.9) {
                ndjl = 0.9;
            }
            NdMsg = "召唤兽通过牺牲自己的" + (double)Math.round(ndjl * 10000.0) / 100.0 + "%HP给对手造成" + (double)Math.round(ndhq * 1000.0) / 10.0 + "%HP伤害";
        }
        else if ("分光化影".equals(goodsname)) {
            double ndjl = Math.floor((Math.pow((double)(zhsdj * nddj) / 160000.0, 0.5) * (1.0 + 0.25 * (double)ndzscs) + Math.pow((double)zhsqm, 0.16666666666666666) * (double)nddj / 4000.0) * 1000.0) / 1000.0 + 0.01;
            double ndhq = Math.floor(ndjl * ((double)(zhsdj * zhsdj) * 0.15 / ((double)(zhsmpz * 1) + 0.01) + 0.2) * 1000.0) / 1000.0;
            if (ndjl > 0.9) {
                ndjl = 0.9;
            }
            NdMsg = "召唤兽通过牺牲自己的" + (double)Math.round(ndjl * 10000.0) / 100.0 + "%HP给对手造成" + (double)Math.round(ndhq * 1000.0) / 10.0 + "%MP伤害";
        }
        else if ("小楼夜哭".equals(goodsname)) {
            double ndjl = Math.floor((Math.pow((double)(zhsdj * nddj) / 206600.0, 0.5) * (1.0 + 0.25 * (double)ndzscs) + Math.pow((double)zhsqm, 0.16666666666666666) * (double)nddj / 4170.0) * 1000.0) / 1000.0 + 0.01;
            double ndhq = ndjl * 0.3;
            if (ndjl > 0.9) {
                ndjl = 0.9;
            }
            NdMsg = "召唤兽通过牺牲自己的" + (double)Math.round(ndjl * 10000.0) / 100.0 + "%MP给对手造成" + (double)Math.round(ndhq * 1000.0) / 10.0 + "%MP伤害";
        }
        else if ("青面獠牙".equals(goodsname)) {
            double ndjl = Math.floor((Math.pow((double)(zhsdj * nddj) / 698000.0, 0.5) * (1.0 + 0.25 * (double)ndzscs) + Math.pow((double)zhsqm, 0.16666666666666666) * (double)nddj / 7500.0) * 1000.0) / 1000.0 + 0.01;
            double ndhq = ndjl * 0.7;
            ndjl *= 2.0;
            if (ndjl > 0.9) {
                ndjl = 0.9;
            }
            NdMsg = "召唤兽通过牺牲自己的" + (double)Math.round(ndjl * 10000.0) / 100.0 + "%MP给对手造成" + (double)Math.round(ndhq * 1000.0) / 10.0 + "%HP伤害";
        }
        else if ("大海无量".equals(goodsname)) {
            double ndjl = Math.floor((Math.pow((double)(zhsdj * nddj) * 0.04, 0.5) * (1.0 + 0.25 * (double)ndzscs) + Math.pow((double)zhsqm, 0.16666666666666666) * xstz(zhszscs, ndzscs) * (double)nddj / 50.0) * 1000.0) * 4.0E-6;
            double ndcd = Math.floor(296.1572 + 2.364957E-4 * Math.pow((double)zhsmpz, 1.57));
            NdMsg = "召唤兽物理攻击时，有" + (double)Math.round(ndjl * 10000.0) / 100.0 + "%几率产生水系法术伤害";
        }
        else if ("乘风破浪".equals(goodsname)) {
            double ndjl = Math.floor((Math.pow((double)(zhsdj * nddj) * 0.04, 0.5) * (1.0 + 0.25 * (double)ndzscs) + Math.pow((double)zhsqm, 0.16666666666666666) * xstz(zhszscs, ndzscs) * (double)nddj / 50.0) * 1000.0) * 4.0E-6;
            double ndcd = Math.floor(296.1572 + 2.364957E-4 * Math.pow((double)zhsmpz, 1.57));
            NdMsg = "召唤兽物理攻击时，有" + (double)Math.round(ndjl * 10000.0) / 100.0 + "%几率产生风系法术伤害";
        }
        else if ("霹雳流星".equals(goodsname)) {
            double ndjl = Math.floor((Math.pow((double)(zhsdj * nddj) * 0.04, 0.5) * (1.0 + 0.25 * (double)ndzscs) + Math.pow((double)zhsqm, 0.16666666666666666) * xstz(zhszscs, ndzscs) * (double)nddj / 50.0) * 1000.0) * 4.0E-6;
            double ndcd = Math.floor(296.1572 + 2.364957E-4 * Math.pow((double)zhsmpz, 1.57));
            NdMsg = "召唤兽物理攻击时，有" + (double)Math.round(ndjl * 10000.0) / 100.0 + "%几率产生雷系法术伤害";
        }
        else if ("祝融取火".equals(goodsname)) {
            double ndjl = Math.floor((Math.pow((double)(zhsdj * nddj) * 0.04, 0.5) * (1.0 + 0.25 * (double)ndzscs) + Math.pow((double)zhsqm, 0.16666666666666666) * xstz(zhszscs, ndzscs) * (double)nddj / 50.0) * 1000.0) * 4.0E-6;
            double ndcd = Math.floor(296.1572 + 2.364957E-4 * Math.pow((double)zhsmpz, 1.57));
            NdMsg = "召唤兽物理攻击时，有" + (double)Math.round(ndjl * 10000.0) / 100.0 + "%几率产生火系法术伤害";
        }
        return NdMsg;
    }
    
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
}
