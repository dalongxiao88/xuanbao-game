package com.tool.role;

import org.come.XuanBao.RoleXuanBao;
import org.come.bean.*;
import org.come.entity.Baby;
import org.come.model.Title;
import org.come.model.Lingbao;
import com.tool.time.Limit;
import com.tool.time.TimeLimit;
import org.come.until.*;
import org.come.Frame.TestpackJframe;
import org.come.XuanBao.RoleXuanBao;
import org.come.mouslisten.PetAddPointMouslisten;
import com.tool.btn.JpanelOnJalbelBtn;
import org.apache.commons.lang.StringUtils;
import org.wing.panel.WingMainPanel;
import com.tool.btn.BaptizeBtn;
import org.come.entity.Goodstable;

import java.util.Iterator;

import org.come.model.Configure;

import java.math.BigDecimal;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Vector;
import java.util.List;
import java.util.Map;
//抗性上限控制
public class RoleProperty
{
    public static RoleProperty property;
    Map<String, Double> grade;
    Map<String, Double> born;
    Map<String, Double> equip;
    Map<String, Double> additional;
    Map<String, Double> bangpai;
    Map<String, Double> xianzhi;
    Map<String, Double> EXE;
    List<Stunt> stunts;
    double hp;
    double mp;
    double ap;
    int qhv;
    Ql quality;
    public Vector<BaseMeridians> meridiansVector;
    public Vector<BaseXingpans> xingpanVector;
    
    public RoleProperty() {
        this.hp = 0.0;
        this.mp = 0.0;
        this.ap = 0.0;
    }
    
    public static RoleProperty getRoleProperty() {
        if (RoleProperty.property == null) {
            initial();
        }
        return RoleProperty.property;
    }
    
    public static void initial() {
        RoleProperty.property = new RoleProperty();
        RoleProperty.property.grade = new HashMap<>();
        RoleProperty.property.born = new HashMap<>();
        RoleProperty.property.equip = new HashMap<>();
        RoleProperty.property.additional = new HashMap<>();
        RoleProperty.property.bangpai = new HashMap<>();
        RoleProperty.property.xianzhi = new HashMap<>();
        RoleProperty.property.EXE = new HashMap<>();
        RoleProperty.property.quality = new Ql();
        RoleProperty.property.stunts = new ArrayList<>();
        RoleProperty.property.meridiansVector = new Vector<>();
        RoleProperty.property.xingpanVector = new Vector<>();
        LoginResult loginResult = RoleData.getRoleData().getLoginResult();
        BigDecimal hp = loginResult.getHp();
        BigDecimal mp = loginResult.getMp();
        Resetgrade((int)loginResult.getGrade(), loginResult.getRace_id());
        RoleProperty.property.upMeridians(loginResult.getMeridians());
        RoleProperty.property.upXingpans(loginResult.getXingpans());
        PrivateData data = RoleData.getRoleData().getPrivateData();
        Resetborn(null, data.getBorn());
        ResetEw();
        ResetBp();
        RoleProperty.property.equipWearOff();
        if (hp.longValue() != 0L) {
            loginResult.setHp(hp);
            loginResult.setMp(mp);
        }
        int spir = getSpir(loginResult);
        property.SETadditional("法术命中率",(double)spir * 0.02);
        property.SETadditional("法术暴击", (double)spir * 0.03);
        property.SETadditional("法术暴击增伤", (double)spir * 0.04);
        Article.manxie(loginResult);
    }
    
    public Ql getQuality() {
        this.quality.Reset();
        List<String> keys = allProperty();
        if (keys.contains("四抗")) {
            String value = "抗封印";
            if (!keys.contains(value)) {
                keys.add(value);
            }
            value = "抗混乱";
            if (!keys.contains(value)) {
                keys.add(value);
            }
            value = "抗昏睡";
            if (!keys.contains(value)) {
                keys.add(value);
            }
            value = "抗遗忘";
            if (!keys.contains(value)) {
                keys.add(value);
            }
        }
        for (int i = 0; i < keys.size(); ++i) {
            QualityZW.insertValues(this.quality, (String)keys.get(i), this.getvalue((String)keys.get(i)));
        }
        if (keys.contains("水魔附身")) {
            double value2 = this.getvalue("水魔附身");
            if (value2 > 0.0) {
                this.quality.setRolewxj(0.0);
                this.quality.setRolewxm(0.0);
                this.quality.setRolewxh(0.0);
                this.quality.setRolewxt(0.0);
                this.quality.setRolewxs(100.0);
                this.quality.setRolewxqkh(this.quality.getRolewxqkh() + 20.0 * value2);
            }
        }
        if (keys.contains("五行归一")) {
            double[] numbers = { this.quality.getRolewxqkj(), this.quality.getRolewxqks(), this.quality.getRolewxqkt(), this.quality.getRolewxqkm(), this.quality.getRolewxqkh() };
            double max = this.quality.getRolewxqkj();
            int c = 0;
            for (int j = 1; j < numbers.length; ++j) {
                double tmpMax = max;
                max = Math.max(max, numbers[j]);
                if (max != tmpMax) {
                    c = j;
                }
            }
            switch (c) {
                case 0: {
                    this.quality.setRolewxqkj(this.quality.getRolewxqkj() + 100.0);
                    break;
                }
                case 1: {
                    this.quality.setRolewxqks(this.quality.getRolewxqks() + 100.0);
                    break;
                }
                case 2: {
                    this.quality.setRolewxqkt(this.quality.getRolewxqkt() + 100.0);
                    break;
                }
                case 3: {
                    this.quality.setRolewxqkm(this.quality.getRolewxqkm() + 100.0);
                    break;
                }
                case 4: {
                    this.quality.setRolewxqkh(this.quality.getRolewxqkh() + 100.0);
                    break;
                }
            }
        }
        return this.quality;
    }
    
    public double filterUp(String key, double grade, double equip, double born, double additional, double bangpai) {
        ConfigureBean allConfigure = UserMessUntil.getConfigureBean();
        Map<BigDecimal, Configure> item = allConfigure.getAllConfigure();
        Configure configure = item.get(new BigDecimal(1));
        int max = 0;
        if (configure.getRwkxsx() != null && configure.getRwkxsx() != "") {
            max = Integer.parseInt(configure.getRwkxsx());
        }
        if (key.endsWith("上限") || key.equals("四抗") || key.equals("抗震慑")) {
            return grade + equip + born + additional + bangpai;
        }
        if (key.equals("抗混乱") || key.equals("抗昏睡") || key.equals("抗封印") || key.equals("抗遗忘")) {
            LoginResult loginResult = RoleData.getRoleData().getLoginResult();
            double sx = BaseValue.Upper(key, loginResult.getRace_id()) + (double)this.qhv * 0.4;
            sx += (double)max;
            sx *= 1.0 + (this.getvalue(key + "上限") + this.getvalue("四抗上限")) / 100.0;
            double z = grade + equip + born + bangpai + this.getvalue("四抗");
            return ((z > sx) ? sx : z) + additional;
        }
        if (!key.equals("抗三尸") && !key.equals("抗浩然正气") && !key.equals("抗青面獠牙") && !key.equals("抗天魔解体") && !key.equals("抗小楼夜哭") && !key.equals("抗分光化影")) {
            if (key.contains("物理吸收率") || key.startsWith("抗")) {
                if ((key.startsWith("抗雷") || key.startsWith("抗水") || key.startsWith("抗火") || key.startsWith("抗风")) && grade < 0.0) {
                    if (grade + bangpai > 0.0) {
                        return (equip + grade > (double)(75 + max)) ? ((double)(75 + max) + (grade + bangpai) + born) : (equip + grade + bangpai + born);
                    }
                    return (equip + grade > (double)(75 + max)) ? ((double)(75 + max) + bangpai + born) : (equip + grade + bangpai + born);
                }
                else {
                    return (equip > (double)(75 + max)) ? ((double)(75 + max) + grade + bangpai + born) : (equip + grade + bangpai + born);
                }
            }
            else {
                if (key.equals("连击率")) {
                    return (grade + equip + born + additional > (double)(75 + max)) ? ((double)(75 + max) + grade + additional) : (grade + equip + born + additional);
                }
                if (key.equals("躲闪率")) {
                    if (equip > 75.0) {
                        equip = 75.0;
                    }
                    return grade + equip + born + additional;
                }
            }
        }
        double var=grade + equip + born + additional + bangpai;
        if ((key.equals("命中率") ||key.equals("命中") ||key.equals("致命")||key.equals("致命率")||key.equals("致命几率"))&&var>75+grade  + born+ additional) {
            var=75+grade  + born+ additional;//龙和魔族命中连击致命狂暴上限
        }
        if ((key.equals("狂暴几率")||key.equals("狂暴")||key.equals("狂暴率"))&&var>80+grade  + born+ additional) {
            var = 80+grade  + born+ additional  ;
        }
        if (key.equals("连击次数")&&var>14) {
            var = 14;
        }

        return var;
    }
    
    public double getvalue(String key) {
        return this.filterUp1(key, this.getgrade(key), this.getequip(key), this.getborn(key), this.getadditional(key), this.getbangpai(key));
    }
    
    public double getgrade(String key) {
        if (this.grade.containsKey(key)) {
            return this.grade.get(key) - 195764.0;
        }
        return 0.0;
    }
    
    public double getequip(String key) {
        if (this.equip.containsKey(key)) {
            return this.equip.get(key) - 195764.0;
        }
        return 0.0;
    }
    
    public double getborn(String key) {
        if (this.born.containsKey(key)) {
            return this.born.get(key) - 195764.0;
        }
        return 0.0;
    }
    
    public double getadditional(String key) {
        if (this.additional.containsKey(key)) {
            return this.additional.get(key) - 195764.0;
        }
        return 0.0;
    }
    
    public double getbangpai(String key) {
        if (this.bangpai.containsKey(key)) {
            return this.bangpai.get(key) - 195764.0;
        }
        return 0.0;
    }
    
    public void addgrade(String key, double value) {
        this.grade.put(key, this.getgrade(key) + value + 195764.0);
    }
    
    public void addequip(String key, double value) {
        this.equip.put(key, this.getequip(key) + value + 195764.0);
    }
    
    public void addborn(String key, double value) {
        this.born.put(key, this.getborn(key) + value + 195764.0);
    }
    
    public void addadditional(String key, double value) {
        this.additional.put(key, this.getadditional(key) + value + 195764.0);
    }
    public void SETadditional(String key, double value) {
        this.additional.put(key, value + 195764.0);
    }

    public void addbangpai(String key, double value) {
        this.bangpai.put(key, this.getbangpai(key) + value + 195764.0);
    }
    
    public static List<String> allProperty() {
        List<String> Propertys = new ArrayList<>();
        RoleProperty roleProperty = getRoleProperty();
        for (Map.Entry<String, Double> entry : roleProperty.born.entrySet()) {
            if (!Propertys.contains(entry.getKey())) {
                Propertys.add(entry.getKey());
            }
        }
        for (Map.Entry<String, Double> entry : roleProperty.grade.entrySet()) {
            if (!Propertys.contains(entry.getKey())) {
                Propertys.add(entry.getKey());
            }
        }
        for (Map.Entry<String, Double> entry : roleProperty.equip.entrySet()) {
            if (!Propertys.contains(entry.getKey())) {
                Propertys.add(entry.getKey());
            }
        }
        for (Map.Entry<String, Double> entry : roleProperty.additional.entrySet()) {
            if (!Propertys.contains(entry.getKey())) {
                Propertys.add(entry.getKey());
            }
        }
        for (Map.Entry<String, Double> entry : roleProperty.bangpai.entrySet()) {
            if (!Propertys.contains(entry.getKey())) {
                Propertys.add(entry.getKey());
            }
        }
        return Propertys;
    }
    
    public void equipWearOff2(String value, EquipBase equipBase) {
        String[] vStrings = value.split("\\&");
        for (int k = 1; k < vStrings.length; ++k) {
            String[] mes = vStrings[k].split("=");
            if (mes[0].equals("特技")) {
                for (int l = 1; l < mes.length; ++l) {
                    int id = Integer.parseInt(mes[l]);
                    this.addStunt(id, 0, 0, 1);
                    if (id == 8016) {
                        equipBase.xs = -999.0;
                    }
                    else if (id == 8017) {
                        equipBase.isL = true;
                    }
                }
            }
            else if (!mes[0].equals("星阵属性")) {
                RoleProperty.property.addequip(mes[0], Double.parseDouble(mes[1]));
            }
        }
    }
    
    public void equipWearOff3(String value, EquipBase equipBase) {
        String[] vStrings = value.split("\\&");
        for (int k = 2; k < vStrings.length; ++k) {
            String[] mes = vStrings[k].split("=");
            RoleProperty.property.addequip(mes[0], Double.parseDouble(mes[1]));
        }
    }
    
    public void equipWearOff4(String value, EquipBase equipBase) {
        String[] vStrings = value.split("\\&");
        for (int k = 1; k < vStrings.length; ++k) {
            String[] mes = vStrings[k].split("=");
            if (mes[0].startsWith("属性需求")) {
                double zhi = Double.parseDouble(mes[1]) / 100.0;
                if (mes[0].indexOf("减少") != -1) {
                    zhi = -zhi;
                }
                if (equipBase.xs != -999.0) {
                    equipBase.xs += zhi;
                }
            }
            else {
                RoleProperty.property.addequip(mes[0], Double.parseDouble(mes[1]));
            }
        }
    }
    
    public void equipWearOff5(String value, EquipBase equipBase) {
        String[] vStrings = value.split("\\&");
        for (int k = 4; k < vStrings.length; ++k) {
            String[] mes = vStrings[k].split("=");
            RoleProperty.property.addequip(mes[0], Double.parseDouble(mes[1]));
        }
        Double sum = (Double)this.xianzhi.get(vStrings[1]);
        if (sum == null) {
            sum = Double.valueOf(0.0);
        }
        sum = Double.valueOf((double)sum + 1.0);
        this.xianzhi.put(vStrings[1], sum);
        int suitid = Integer.parseInt(vStrings[1]);
        RoleSuitBean suit = UserMessUntil.getSuit(suitid);
        if (suit != null) {
            String[] vs = suit.getHaveSkill().split("\\|");
            for (int i = 0; i < vs.length; ++i) {
                String[] vss = vs[i].split("-");
                int maxsum = Integer.parseInt(vss[0]);
                int id = Integer.parseInt(vss[1]);
                this.addStunt(id, suitid, RoleLingFa.getQv(vStrings[3]) / 10, maxsum);
            }
        }
    }
    
    public void equipWearOff6(String value, EquipBase equipBase) {
        String[] vStrings = value.split("\\&");
        for (int k = 1; k < vStrings.length; ++k) {
            Goodstable good = (Goodstable)GoodsListFromServerUntil.fushis.get(new BigDecimal(vStrings[k]));
            if (good != null) {
                String[] bs = good.getValue().split("\\|")[1].split("=");
                RoleProperty.property.addequip(bs[0], Double.parseDouble(bs[1]));
            }
        }
    }
    
    public void equipWearOff7(String value, EquipBase equipBase) {
        String[] vStrings = value.split("\\&");
        for (int k = 1; k < vStrings.length; ++k) {
            String[] mes = vStrings[k].split("=");
            RoleProperty.property.addequip(mes[0], Double.parseDouble(mes[1]));
        }
    }
    
    public static double getQHGemXS(int lvl) {
        double xs = 0.0;
        for (int i = 1; i <= lvl; ++i) {
            xs += (double)((i - 1) / 3 + 1) * 0.8;
        }
        return xs;
    }
    
    public void equipWearOff() {
        LoginResult loginResult = RoleData.getRoleData().getLoginResult();
        this.equip.clear();
        Goodstable[] goodstables = GoodsListFromServerUntil.getChoseGoodsList();
        EquipBase[] a = new EquipBase[14];
        for (int i = 0; i < goodstables.length; ++i) {
            Goodstable goodstable = goodstables[i];
            String value = (goodstable != null) ? goodstable.getValue() : null;
            if (value != null && !value.equals("")) {
                String[] v = value.split("\\|");
                a[i] = new EquipBase(v, goodstable.getQhv());
            }
        }
        LOOP:
        while (true) {
            this.equip.clear();
            this.xianzhi.clear();
            this.stunts.clear();
            this.qhv = 0;
            int size = 0;
            int qhv = 0;
            for (int j = 0; j < a.length; ++j) {
                EquipBase equipBase = a[j];
                String[] v2 = (String[])((equipBase != null) ? equipBase.v : null);
                if (v2 != null) {
                    double qhXS = 1.0;
                    if (equipBase.qhv != null) {
                        ++size;
                        if (qhv == 0 || qhv > (int)equipBase.qhv) {
                            qhv = (int)equipBase.qhv;
                        }
                        qhXS += getQHGemXS((int)equipBase.qhv) / 100.0;
                    }
                    if (j == 14) {
                        int pz = RoleLingFa.getQv(v2[0].split("=")[1]) / 10;
                        int lvl = Integer.parseInt(v2[1].split("=")[1]);
                        for (int k = 4; k < v2.length; ++k) {
                            if (v2[k].startsWith("根骨=") || v2[k].startsWith("灵性=") || v2[k].startsWith("力量=") || v2[k].startsWith("敏捷=") || v2[k].startsWith("定力=")) {
                                String[] mes = v2[k].split("=");
                                if (mes.length == 2) {
                                    int value2 = Integer.parseInt(mes[1]);
                                    value2 = (int)((double)value2 * (1.0 + (double)lvl * 0.1));
                                    value2 = (int)((double)value2 * (1.0 + ((pz == 6) ? 3.2 : ((pz == 5) ? 1.6 : ((pz == 4) ? 0.8 : ((pz == 3) ? 0.4 : ((pz == 2) ? 0.2 : 0.0)))))));
                                    RoleProperty.property.addequip(mes[0], (double)value2);
                                }
                            }
                            else if (v2[k].startsWith(BaptizeBtn.Extras[0])) {
                                this.equipWearOff2(v2[k], equipBase);
                            }
                        }
                    }
                    else {
                        for (int l = 0; l < v2.length; ++l) {
                            if (v2[l].startsWith(BaptizeBtn.Extras[0])) {
                                this.equipWearOff2(v2[l], equipBase);
                            }
                            else if (v2[l].startsWith(BaptizeBtn.Extras[1])) {
                                this.equipWearOff3(v2[l], equipBase);
                            }
                            else if (v2[l].startsWith(BaptizeBtn.Extras[2])) {
                                this.equipWearOff4(v2[l], equipBase);
                            }
                            else if (v2[l].startsWith(BaptizeBtn.Extras[3])) {
                                this.equipWearOff5(v2[l], equipBase);
                            }
                            else if (v2[l].startsWith(BaptizeBtn.Extras[4])) {
                                this.equipWearOff6(v2[l], equipBase);
                            }
                            else if (v2[l].startsWith(BaptizeBtn.Extras[8])) {
                                this.equipWearOff7(v2[l], equipBase);
                            }
                            else if (v2[l].startsWith(BaptizeBtn.Extras[9])) {
                                this.equipWearOff2(v2[l], equipBase);
                            }
                            else {
                                String[] mes2 = v2[l].split("=");
                                if (mes2.length == 2) {
                                    if (mes2[0].equals("装备角色")) {
                                        if (mes2[1].indexOf(loginResult.getLocalname()) == -1) {
                                            a[j] = null;
                                            continue LOOP;
                                        }
                                    }
                                    else if (mes2[0].equals("性别要求") || mes2[0].equals("性别")) {
                                        if (!mes2[1].equals("2")) {
                                            String sexBtn = ((int)Integer.valueOf(mes2[1]) == 1) ? "男" : "女";
                                            if (!loginResult.getSex().equals(sexBtn)) {
                                                a[j] = null;
                                                continue LOOP;
                                            }
                                        }
                                    }
                                    else if (mes2[0].equals("等级要求")) {
                                        equipBase.uplvl(0, mes2[1]);
                                    }
                                    else if (mes2[0].equals("最高携带等级")) {
                                        equipBase.uplvl(1, mes2[1]);
                                    }
                                    else if (mes2[0].equals("根骨要求")) {
                                        equipBase.gg = Integer.parseInt(mes2[1]);
                                    }
                                    else if (mes2[0].equals("灵性要求")) {
                                        equipBase.lx = Integer.parseInt(mes2[1]);
                                    }
                                    else if (mes2[0].equals("力量要求")) {
                                        equipBase.lm = Integer.parseInt(mes2[1]);
                                    }
                                    else if (mes2[0].equals("敏捷要求")) {
                                        equipBase.mj = Integer.parseInt(mes2[1]);
                                    }
                                    else if (!mes2[0].equals("品质") && !mes2[0].equals("颜色") && !mes2[0].equals("星级")) {
                                        if (mes2[0].equals("经验")) {
                                            continue;
                                        }
                                    }
                                    else {
                                        continue;
                                    }
                                    if ((long)goodstables[j].getType() == 8888L) {
                                        int start = 0;
                                        String quality = "";
                                        for (int m = 0; m < v2.length; ++m) {
                                            if (v2[m].startsWith("星级=")) {
                                                String[] str = v2[m].split("=");
                                                start = (int)Integer.valueOf(str[1]);
                                            }
                                            if (v2[m].startsWith("品质=")) {
                                                String[] str = v2[m].split("=");
                                                quality = str[1];
                                            }
                                        }
                                        RoleProperty.property.addequip(mes2[0], getdouble(WingMainPanel.getUpStarData(mes2[1], start, quality) + ""));
                                    }
                                    else {
                                        RoleProperty.property.addequip(mes2[0], getdouble(mes2[1]) * qhXS);
                                    }
                                }
                            }
                        }
                    }
                }
            }

            if (StringUtils.isNotBlank(RoleData.getRoleData().getLoginResult().getCar_value())) {
                String lianghaoValue = RoleData.getRoleData().getLoginResult().getCar_value();
                String[] vv=lianghaoValue.split("\\|");
                for (int i = 0; i < vv.length; i++) {
                    if (!vv[i].contains("=")) {
                        continue;
                    }
                    String[] vv1 =vv[i].split("=");
                    RoleProperty.property.addequip(vv1[0], Double.parseDouble(vv1[1]));
                }
            }

            if (StringUtils.isNotBlank(RoleData.getRoleData().getLoginResult().getLianghaoValue())) {
                String lianghaoValue = RoleData.getRoleData().getLoginResult().getLianghaoValue();
                if (lianghaoValue.contains("@")) {
                    String lhStr = "";
                    String[] split1 = lianghaoValue.split("@");
                    if (split1[0].equals("6")) {
                        lhStr = split1[1];
                    }
                    else if (split1[0].equals("7")) {
                        lhStr = split1[3];
                    }
                    else if (split1[0].equals("8")) {
                        lhStr = split1[4];
                    }
                    else {
                        lhStr = split1[2];
                    }
                    String[] v3 = lhStr.split("&");
                    for (int i2 = 1; i2 < v3.length; ++i2) {
                        String[] v4 = v3[i2].split("=");
                        RoleProperty.property.addequip(v4[0], getdouble(v4[1]) * 1.0);
                    }
                }
                else {
                    String[] v = lianghaoValue.split("&");
                    for (int i3 = 1; i3 < v.length; ++i3) {
                        String[] v5 = v[i3].split("=");
                        RoleProperty.property.addequip(v5[0], getdouble(v5[1]) * 1.0);
                    }
                }
            }
            this.resetStunt();
            if (size == 5 && qhv != 0) {
                RoleProperty.property.addequip("HP", (double)(2000 * qhv));
                RoleProperty.property.addequip("MP", (double)(1000 * qhv));
                this.qhv = qhv;
            }
            for (int j = 0; j < a.length; ++j) {
                EquipBase equipBase = a[j];
                if (equipBase != null) {
                    if (!equipBase.isL) {
                        int zs = AnalysisString.lvltrue((int)loginResult.getGrade());
                        int lvl2 = AnalysisString.lvlint((int)loginResult.getGrade());
                        if (zs < equipBase.zs) {
                            a[j] = null;
                            continue LOOP;
                        }
                        else if (zs == equipBase.zs && lvl2 < equipBase.lvl) {
                            a[j] = null;
                            continue LOOP;
                        }
                        else if (zs > equipBase.zsMax) {
                            a[j] = null;
                            continue LOOP;
                        }
                        else if (zs == equipBase.zsMax && lvl2 > equipBase.lvlMax) {
                            a[j] = null;
                            continue LOOP;
                        }
                    }
                    if (equipBase.xs != -999.0) {
                        if (equipBase.gg != 0 && (double)getBone(loginResult) < (double)equipBase.gg * equipBase.xs) {
                            a[j] = null;
                            continue LOOP;
                        }
                        else if (equipBase.lx != 0 && (double)getSpir(loginResult) < (double)equipBase.lx * equipBase.xs) {
                            a[j] = null;
                            continue LOOP;
                        }
                        else if (equipBase.lm != 0 && (double)getPower(loginResult) < (double)equipBase.lm * equipBase.xs) {
                            a[j] = null;
                            continue LOOP;
                        }
                        else if (equipBase.mj != 0 && (double)getSpeed(loginResult) < (double)equipBase.mj * equipBase.xs) {
                            a[j] = null;
                            continue LOOP;
                        }
                    }
                }
            }
            break;
        }

        for (XuanBao xuanBao : (RoleXuanBao.getRoleXuanBao()).equipBao) {
            if (xuanBao != null && xuanBao.getFushi() != null && !xuanBao.getFushi().isEmpty()) {
                String[] vlaue = xuanBao.getFushi().split("\\|");
                for (int m = 0; m < vlaue.length; m++) {
                    Goodstable goodstable = GoodsListFromServerUntil.fushis.get(new BigDecimal(vlaue[m]));
                    if (goodstable != null) {
                        String[] arrayOfString = goodstable.getValue().split("\\|");
                        for (int n = 0; n < arrayOfString.length; n++) {
                            if (!arrayOfString[n].startsWith("等级")) {
                                String[] mes = arrayOfString[n].split("=");
                                property.addadditional(mes[0], Double.parseDouble(mes[1]));
                            }
                        }
                    }
                }
            }
        }

        int hpz = getHp(loginResult);
        int mpz = getMp(loginResult);
        if (loginResult.getHp().intValue() == 0) {
            loginResult.setHp(new BigDecimal(hpz));
            loginResult.setMp(new BigDecimal(mpz));
        }
        else {
            if (loginResult.getHp().intValue() > hpz) {
                loginResult.setHp(new BigDecimal(hpz));
            }
            if (loginResult.getMp().intValue() > mpz) {
                loginResult.setMp(new BigDecimal(mpz));
            }
        }
        int spir = getSpir(loginResult);
        property.SETadditional("法术命中率",(double)spir * 0.02);
        property.SETadditional("法术暴击", (double)spir * 0.03);
        property.SETadditional("法术暴击增伤", (double)spir * 0.04);
        try {
            JpanelOnJalbelBtn.testReflect(this.getQuality());
            PetAddPointMouslisten.getplayerValue();
            TestpackJframe.getTestpackJframe().getJpac().isShowGemImg(this.qhv);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public static void Resetgrade(int grade, BigDecimal raceid) {
        int lvl = AnalysisString.lvlint(grade);
        int turn = AnalysisString.lvltrue(grade);
        if (turn > 4 || lvl > 200) {
            JmSum.xiugaiqi();
            return;
        }
        RoleProperty property = getRoleProperty();
        property.grade.clear();
        String v = RoleUpGrade.getGradeKX().upGrade(lvl, raceid);
        String[] vs = v.split("\\|");
        for (int i = 0; i < vs.length; ++i) {
            String[] a = vs[i].split("=");
            property.addgrade(a[0], Double.parseDouble(a[1]));
        }
        if (turn == 4) {
            lvl -= 140;
            lvl /= 10;
            if (raceid.intValue() == 10001) {
                property.addgrade("四抗上限", (double)lvl * 1.2);
            }
            else if (raceid.intValue() == 10002) {
                property.addgrade("四抗上限", (double)lvl * 1.1);
            }
            else if (raceid.intValue() == 10003) {
                property.addgrade("四抗上限", (double)lvl * 1.0);
            }
            else if (raceid.intValue() == 10004) {
                property.addgrade("四抗上限", (double)lvl * 0.9);
            }
        }
    }
    
    public static String Resetborn(String[] skills, String yuben) {
        RoleProperty property = getRoleProperty();
        property.born.clear();
        String v = RoleReborn.reborn(skills, yuben);
        if (v == null || v.equals("")) {
            return v;
        }
        String[] vs = v.split("\\|");
        for (int i = 0; i < vs.length; ++i) {
            String[] a = vs[i].split("=");
            property.addborn(a[0], Double.parseDouble(a[1]));
        }
        return v;
    }
    
    public static void ResetBp() {
        LoginResult loginResult = RoleData.getRoleData().getLoginResult();
        RoleProperty property = getRoleProperty();
        property.bangpai.clear();
        String b = loginResult.getResistance();
        BigDecimal achi = loginResult.getAchievement();
        if (b != null && !b.equals("")) {
            String[] v = b.split("\\|");
            for (int i = 0; i < v.length; ++i) {
                if (v[i].startsWith("主-")) {
                    String zhu = v[i].substring(2);
                    if (zhu.length() > 0) {
                        property.addbangpai(zhu, BaseValue.getBangQuality(achi, true));
                    }
                }
                if (v[i].startsWith("副-")) {
                    String fu = v[i].substring(2);
                    if (fu.length() > 0) {
                        property.addbangpai(fu, BaseValue.getBangQuality(achi, false));
                    }
                }
                if (v[i].startsWith("X") || v[i].startsWith("D")) {
                    String[] vs = v[i].split("#");
                    vs[0] = vs[0].substring(1);
                    for (int j = 0; j < vs.length; ++j) {
                        String[] vss = vs[j].split("=");
                        try {
                            if (v[i].startsWith("X")) {
                                property.addbangpai(vss[0], Double.parseDouble(vss[1]));
                            }
                            else {
                                property.addbangpai(vss[0], Double.parseDouble(vss[1]));
                            }
                        }
                        catch (Exception ex) {}
                    }
                }
            }
        }
    }
    
    public static void ResetEw() {
        RoleProperty property = getRoleProperty();
        property.additional.clear();
        Lingbao[] equipBao = RoleLingFa.getRoleLingFa().equipBao;
        for (int i = 0; i < equipBao.length; ++i) {
            if (equipBao[i] != null) {
                String[] a = equipBao[i].getKangxing().split("=");
                double value = Double.parseDouble(a[1]);
                if (!a[0].startsWith("抗") || value > 10.1) {
                    JmSum.xiugaiqi();
                    return;
                }
                property.addadditional(a[0], value);
            }
        }
        TimeLimit card = TimeLimit.getLimits();
        for (int j = 0; j < card.limits.size(); ++j) {
            Limit limit = (Limit)card.limits.get(j);
            if (limit.getType().equals("变身卡") || limit.getType().equals("强法型") || limit.getType().equals("加抗型") || limit.getType().equals("增益型") || limit.getType().equals("VIP") || limit.getType().equals("JVIP") || limit.getType().equals("帮派") || limit.getType().equals("单人竞技场")) {
                String value2 = limit.getValue();
                if (value2 != null && !value2.equals("")) {
                    String[] v = value2.split("\\|");
                    for (int z = 0; z < v.length; ++z) {
                        try {
                            String[] mes = v[z].split("=");
                            if (mes.length > 1) {
                                property.addadditional(mes[0], Double.parseDouble(mes[1]));
                            }
                        }
                        catch (Exception ex) {}
                    }
                }
            }
        }
        String[] v2 = RoleData.getRoleData().getPrivateData().getSkill("T");
        if (v2 != null) {
            for (int k = 1; k < v2.length; ++k) {
                String[] vs = v2[k].split("_");
                int id = Integer.parseInt(vs[0]);
                int lvl = Integer.parseInt(vs[1]);
                if (id <= 9100) {
                    if (lvl > 5) {
                        JmSum.xiugaiqi();
                        return;
                    }
                    if (id == 9001) {
                        property.addadditional("MP", (double)(lvl * 2000));
                    }
                    else if (id == 9002) {
                        property.addadditional("HP", (double)(lvl * 4000));
                    }
                    else if (id == 9003) {
                        property.addadditional("HP", (double)(lvl * 1000));
                    }
                    else if (id == 9004) {
                        property.addadditional("根骨", (double)lvl);
                    }
                    else if (id == 9005) {
                        property.addadditional("力量", (double)lvl);
                    }
                    else if (id == 9006) {
                        property.addadditional("AP", (double)(lvl * 100));
                    }
                    else if (id == 9007) {
                        property.addadditional("SP", (double)lvl);
                    }
                    else if (id == 9008) {
                        property.addadditional("抗风", (double)lvl);
                    }
                    else if (id == 9009) {
                        property.addadditional("抗火", (double)lvl);
                    }
                    else if (id == 9010) {
                        property.addadditional("抗水", (double)lvl);
                    }
                    else if (id == 9011) {
                        property.addadditional("抗雷", (double)lvl);
                    }
                    else if (id == 9012) {
                        property.addadditional("抗鬼火", (double)lvl);
                    }
                    else if (id == 9013) {
                        property.addadditional("抗三尸", (double)(lvl * 100));
                    }
                    else if (id == 9014) {
                        property.addadditional("抗反震", (double)(lvl * 500));
                    }
                    else if (id == 9015) {
                        property.addadditional("风法狂暴", (double)lvl);
                    }
                    else if (id == 9016) {
                        property.addadditional("火法狂暴", (double)lvl);
                    }
                    else if (id == 9017) {
                        property.addadditional("水法狂暴", (double)lvl);
                    }
                    else if (id == 9018) {
                        property.addadditional("鬼火伤害", (double)(lvl * 100));
                    }
                    else if (id == 9019) {
                        property.addadditional("强力克金", (double)lvl);
                    }
                    else if (id == 9020) {
                        property.addadditional("强力克木", (double)lvl);
                    }
                    else if (id == 9021) {
                        property.addadditional("强力克火", (double)lvl);
                    }
                    else if (id == 9022) {
                        property.addadditional("强力克土", (double)lvl);
                    }
                    else if (id == 9023) {
                        property.addadditional("物理吸收率", (double)lvl);
                    }
                    else if (id == 9024) {
                        property.addadditional("躲闪率", (double)lvl);
                    }
                    else if (id == 9025) {
                        property.addadditional("强震慑", (double)lvl);
                    }
                    else if (id == 9026) {
                        property.addadditional("反击率", (double)lvl);
                    }
                    else if (id == 9027) {
                        property.addadditional("反击次数", (double)lvl);
                    }
                    else if (id == 9028) {
                        property.addadditional("狂暴率", (double)lvl);
                    }
                    else if (id == 9029) {
                        property.addadditional("反震率", (double)lvl);
                    }
                    else if (id == 9030) {
                        property.addadditional("敏捷", (double)lvl);
                    }
                    else if (id == 9031) {
                        property.addadditional("恢复气血", (double)(lvl * 400));
                    }
                    else if (id == 9030) {
                        property.addadditional("敏捷", (double)lvl);
                    }
                }
            }
        }
        LoginResult loginResult = RoleData.getRoleData().getLoginResult();
        if (loginResult.getTitle() != null && !loginResult.getTitle().equals("")) {
            Title title = UserMessUntil.getTitle(loginResult.getTitle());
            if (title != null && title.getValue() != null) {
                String[] v3 = title.getValue().split("\\|");
                for (int z = 0; z < v3.length; ++z) {
                    String[] mes = v3[z].split("=");
                    if (mes.length > 1) {
                        property.addadditional(mes[0], Double.parseDouble(mes[1]));
                    }
                }
            }
        }
        for (int l = 0; l < property.meridiansVector.size(); ++l) {
            BaseMeridians meridians = (BaseMeridians)property.meridiansVector.get(l);
            if (meridians.getKey().equals("抗仙法")) {
                property.addadditional("抗风", meridians.getKeyValue());
                property.addadditional("抗火", meridians.getKeyValue());
                property.addadditional("抗水", meridians.getKeyValue());
                property.addadditional("抗雷", meridians.getKeyValue());
            }
            else {
                property.addadditional(meridians.getKey(), meridians.getKeyValue());
            }
        }
        String[] v4 = RoleData.getRoleData().getPrivateData().getSkill("X");
        if (v4 != null) {
            for (int m = 1; m < v4.length; ++m) {
                String[] vs2 = v4[m].split("_");
                int id2 = Integer.parseInt(vs2[0]);
                if (id2 <= 23010 && id2 >= 23000 && id2 == 23003) {
                    property.addadditional("AP", (double)((int)loginResult.getPower() * 5));
                }
            }
        }
        for (int m = 0; m < property.xingpanVector.size(); ++m) {
            BaseXingpans xingpans = (BaseXingpans)property.xingpanVector.get(m);
            property.addadditional(xingpans.getKey(), xingpans.getKeyValue());
            property.addadditional(xingpans.getKey1(), xingpans.getKeyValue1());
        }
        int hpz = getHp(loginResult);
        if (loginResult.getHp().intValue() > hpz) {
            loginResult.setHp(new BigDecimal(hpz));
        }
        int mpz = getMp(loginResult);
        if (loginResult.getMp().intValue() > mpz) {
            loginResult.setMp(new BigDecimal(mpz));
        }
        int spir = getSpir(loginResult);
        property.addadditional("法术命中率", (double)spir * 0.02);
        property.addadditional("法术暴击", (double)spir * 0.03);
        property.addadditional("法术暴击增伤", (double)spir * 0.04);
        try {
            JpanelOnJalbelBtn.testReflect(property.getQuality());
            PetAddPointMouslisten.getplayerValue();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public static void ResetBaby(Baby baby) {
        RoleProperty property = getRoleProperty();
        property.hp = 0.0;
        property.mp = 0.0;
        property.ap = 0.0;
        if (baby != null) {
            String Talents = baby.getTalents();
            if (Talents != null && !Talents.equals("")) {
                String[] v = Talents.split("\\|");
                for (int i = 0; i < v.length; ++i) {
                    String[] vs = v[i].split("=");
                    int id = Integer.parseInt(vs[0]);
                    if (id == 1) {
                        property.ap = 0.01;
                    }
                    else if (id == 2) {
                        property.hp = 0.01;
                    }
                    else if (id == 3) {
                        property.mp = 0.01;
                    }
                }
            }
        }
        try {
            LoginResult loginResult = RoleData.getRoleData().getLoginResult();
            int hpz = getHp(loginResult);
            if (loginResult.getHp().intValue() > hpz) {
                loginResult.setHp(new BigDecimal(hpz));
            }
            int mpz = getMp(loginResult);
            if (loginResult.getMp().intValue() > mpz) {
                loginResult.setMp(new BigDecimal(mpz));
            }
            PetAddPointMouslisten.getplayerValue();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public static int getHp(LoginResult loginResult) {
        RoleProperty property = getRoleProperty();
        return (int)(((double)BaseValue.getRoleValue(loginResult.getRace_id(), getBone(loginResult), AnalysisString.lvlint((int)loginResult.getGrade()), 0) + property.getvalue("hp") + property.getvalue("HP") + property.getvalue("附加气血") + property.getvalue("加气血")) * (property.getvalue("HP成长") + property.hp + 1.0) * ((property.getvalue("加强气血") + property.getvalue("气血增加率")) / 100.0 + 1.0));
    }
    
    public static int getMp(LoginResult loginResult) {
        RoleProperty property = getRoleProperty();
        return (int)(((double)BaseValue.getRoleValue(loginResult.getRace_id(), getSpir(loginResult), AnalysisString.lvlint((int)loginResult.getGrade()), 1) + property.getvalue("mp") + property.getvalue("MP") + property.getvalue("附加法力") + property.getvalue("加魔法") + property.getvalue("附加魔法")) * (property.getvalue("加强法力") / 100.0 + property.getvalue("MP成长") + property.mp + 1.0));
    }
    
    public static int getAp(LoginResult loginResult) {
        RoleProperty property = getRoleProperty();
//        return (int)(((double)BaseValue.getRoleValue(loginResult.getRace_id(), getPower(loginResult), AnalysisString.lvlint((int)loginResult.getGrade()), 2) + property.getvalue("AP") + property.getvalue("ap") + property.getvalue("攻击") + property.getvalue("附加攻击") + property.getvalue("加攻击") + property.getvalue("基础攻击")) * (property.getvalue("加强攻击") / 100.0 + property.getvalue("加成攻击") / 100.0 + property.getvalue("AP成长") + property.ap + 1.0));
        return (int)(((double)BaseValue.getRoleValue(loginResult.getRace_id(), getPower(loginResult), AnalysisString.lvlint((int)loginResult.getGrade()), 2)
                + property.getvalue("AP")
                + property.getvalue("ap")
                + property.getvalue("攻击")

                + property.getvalue("加攻击")
                + property.getvalue("基础攻击")) *
                (property.getvalue("加强攻击") / 100.0 + property.getvalue("加成攻击") / 100.0 + property.getvalue("AP成长") + property.ap + 1.0))+ (int)property.getvalue("附加攻击") ;
    }
    
    public static int getSp(LoginResult loginResult) {
        RoleProperty property = getRoleProperty();
        return (int)(((double)BaseValue.getRoleValue(loginResult.getRace_id(), getSpeed(loginResult), AnalysisString.lvlint((int)loginResult.getGrade()), 3) + property.getvalue("sp") + property.getvalue("SP") + property.getvalue("速度") + property.getvalue("附加速度") + property.getvalue("加速度")) * (property.getvalue("加强速度") / 100.0 + property.getvalue("SP成长") + 1.0));
    }
    
    public static int getCdz(LoginResult loginResult) {
        return BaseValue.getRoleValue(loginResult.getRace_id(), getCalm(loginResult), AnalysisString.lvlint((int)loginResult.getGrade()), 4);
    }
    
    public static int getBone(LoginResult loginResult) {
        RoleProperty property = getRoleProperty();
        int i= (int)((double)(int)loginResult.getBone() + property.getvalue("根骨"));
        return (int)((double)(int)loginResult.getBone() + property.getvalue("根骨"));
    }
    
    public static int getSpir(LoginResult loginResult) {
        RoleProperty property = getRoleProperty();
        return (int)((double)(int)loginResult.getSpir() + property.getvalue("灵性"));
    }

    public static int getPower(LoginResult loginResult) {
        RoleProperty property = getRoleProperty();
        return (int)((double)(int)loginResult.getPower() + property.getvalue("力量"));
    }
    
    public static int getSpeed(LoginResult loginResult) {
        RoleProperty property = getRoleProperty();
        return (int)((double)(int)loginResult.getSpeed() + property.getvalue("敏捷"));
    }
    
    public static int getCalm(LoginResult loginResult) {
        RoleProperty property = getRoleProperty();
        return (int)((double)(int)loginResult.getCalm() + property.getvalue("定力"));
    }
    
    public static int getBone1(RoleAttribute roleAttribute) {
        RoleProperty property = getRoleProperty();
        return (int)((double)(int)roleAttribute.getBONEONE() + property.getvalue("根骨"));
    }
    
    public static int getSpir1(RoleAttribute roleAttribute) {
        RoleProperty property = getRoleProperty();
        return (int)((double)(int)roleAttribute.getSPIRONE() + property.getvalue("灵性"));
    }
    
    public static int getPower1(RoleAttribute roleAttribute) {
        RoleProperty property = getRoleProperty();
        return (int)((double)(int)roleAttribute.getPOWERONE() + property.getvalue("力量"));
    }
    
    public static int getSpeed1(RoleAttribute roleAttribute) {
        RoleProperty property = getRoleProperty();
        return (int)((double)(int)roleAttribute.getSPEEDONE() + property.getvalue("敏捷"));
    }
    
    public static int getCalm1(RoleAttribute roleAttribute) {
        RoleProperty property = getRoleProperty();
        return (int)((double)(int)roleAttribute.getCALMONE() + property.getvalue("定力"));
    }
    
    public static boolean contriolContinue(String v, String v2) {
        int n = -1;
        switch (v.hashCode()) {
            case 1066101570: {
                if (v.equals("装备角色")) {
                    n = 0;
                    break;
                }
                else {
                    break;
                }
            }
            case 666980469: {
                if (v.equals("力量要求")) {
                    n = 1;
                    break;
                }
                else {
                    break;
                }
            }
            case 882427059: {
                if (v.equals("灵性要求")) {
                    n = 2;
                    break;
                }
                else {
                    break;
                }
            }
            case 834020528: {
                if (v.equals("根骨要求")) {
                    n = 3;
                    break;
                }
                else {
                    break;
                }
            }
            case 972511199: {
                if (v.equals("等级要求")) {
                    n = 4;
                    break;
                }
                else {
                    break;
                }
            }
            case 798218473: {
                if (v.equals("敏捷要求")) {
                    n = 5;
                    break;
                }
                else {
                    break;
                }
            }
            case 754639045: {
                if (v.equals("性别要求")) {
                    n = 6;
                    break;
                }
                else {
                    break;
                }
            }
            case 784100: {
                if (v.equals("性别")) {
                    n = 7;
                    break;
                }
                else {
                    break;
                }
            }
        }
        switch (n) {
            case 0: {
                return v2.indexOf(RoleData.getRoleData().getLoginResult().getLocalname()) != -1;
            }
            case 1: {
                return getPower(RoleData.getRoleData().getLoginResult()) >= (int)Integer.valueOf(v2);
            }
            case 2: {
                return getSpir(RoleData.getRoleData().getLoginResult()) >= (int)Integer.valueOf(v2);
            }
            case 3: {
                return getBone(RoleData.getRoleData().getLoginResult()) >= (int)Integer.valueOf(v2);
            }
            case 4: {
                return AnalysisString.lvlfull((int)RoleData.getRoleData().getLoginResult().getGrade(), v2);
            }
            case 5: {
                return getSpeed(RoleData.getRoleData().getLoginResult()) >= (int)Integer.valueOf(v2);
            }
            case 6:
            case 7: {
                String sexBtn = ((int)Integer.valueOf(v2) == 1) ? "男" : "女";
                return RoleData.getRoleData().getLoginResult().getSex().equals(sexBtn);
            }
            default: {
                return true;
            }
        }
    }
    
    public static double getdouble(String v) {
        try {
            return Double.parseDouble(v);
        }
        catch (Exception ex) {
            return 0.0;
        }
    }
    
    public void addStunt(int id, int suitid, int lvl, int maxsum) {
        int i = this.stunts.size() - 1;
        while (i >= 0) {
            Stunt stunt = (Stunt)this.stunts.get(i);
            if (stunt.getSkillId() == id && stunt.getSuitid() == suitid) {
                stunt.addSum();
                if (lvl < stunt.getLvl()) {
                    stunt.setLvl(lvl);
                }
                stunt.setMaxSum(maxsum);
                return;
            }
            else {
                --i;
            }
        }
        Stunt stunt2 = new Stunt(id, suitid, lvl, maxsum);
        stunt2.addSum();
        this.stunts.add(stunt2);
    }
    
    public void resetStunt() {
        for (int i = this.stunts.size() - 1; i >= 0; --i) {
            Stunt stunt = (Stunt)this.stunts.get(i);
            this.stuntAffect(stunt, 1);
            this.stuntAffect(stunt, 0);
        }
    }
    
    public void stuntAffect(Stunt stunt, int type) {
        if (!stunt.isValid()) {
            return;
        }
        if (!stunt.isAffect()) {
            return;
        }
        String zhi = SkillUtil.getsuitSkill(stunt.getSkillId());
        if (zhi == null) {
            return;
        }
        if (type == 0) {
            double value = SkillUtil.getSuitValue(stunt.getSkillId(), stunt.getLvl());
            if (zhi.equals("加强法术")) {
                RoleProperty.property.addequip("加强风", value);
                RoleProperty.property.addequip("加强雷", value);
                RoleProperty.property.addequip("加强水", value);
                RoleProperty.property.addequip("加强火", value);
                RoleProperty.property.addequip("加强鬼火", value);
            }
            else if (zhi.equals("提抗上限")) {
                RoleProperty.property.addequip("四抗", -value);
                RoleProperty.property.addequip("四抗上限", value);
            }
            else {
                RoleProperty.property.addequip(zhi, value);
            }
            stunt.setValue(value);
        }
        else {
            double value = stunt.getValue();
            if (zhi.equals("加强法术")) {
                RoleProperty.property.addequip("加强风", -value);
                RoleProperty.property.addequip("加强雷", -value);
                RoleProperty.property.addequip("加强水", -value);
                RoleProperty.property.addequip("加强火", -value);
                RoleProperty.property.addequip("加强鬼火", -value);
            }
            else if (zhi.equals("提抗上限")) {
                RoleProperty.property.addequip("四抗", value);
                RoleProperty.property.addequip("四抗上限", -value);
            }
            else {
                RoleProperty.property.addequip(zhi, -value);
            }
        }
    }
    
    public int getSuitSum(String id) {
        Double value = (Double)this.xianzhi.get(id);
        if (value == null) {
            return 0;
        }
        return value.intValue();
    }
    
    public List<Stunt> getStunts() {
        return this.stunts;
    }
    
    public Stunt getStuntId(int id) {
        int i = this.stunts.size() - 1;
        while (i >= 0) {
            if (((Stunt)this.stunts.get(i)).getSkillId() == id) {
                if (((Stunt)this.stunts.get(i)).isValid()) {
                    return (Stunt)this.stunts.get(i);
                }
                return null;
            }
            else {
                --i;
            }
        }
        return null;
    }
    
    public int getQhv() {
        return this.qhv;
    }
    
	public BaseMeridians upMeridians(String meridians) {
		if (meridians == null || meridians.equals(""))
			return null;
		BaseMeridians baseMeridiansR = null;
		String[] vs = meridians.split("\\|");
		for (int i = 0; i < vs.length; i++) {
			String[] vss = vs[i].split("_");
			int bh = Integer.parseInt(vss[0]);
			int j = 0;
			while (true) {
				if (j < this.meridiansVector.size()) {
					if (((BaseMeridians) this.meridiansVector.get(j)).getId() == bh) {
						baseMeridiansR = this.meridiansVector.get(j);
						baseMeridiansR.setExp(Integer.parseInt(vss[1]));
						baseMeridiansR.setQuality(Integer.parseInt(vss[2]));
						baseMeridiansR.setKey(vss[3]);
						baseMeridiansR.setValue(Double.parseDouble(vss[4]));
						baseMeridiansR.setStage(Integer.parseInt(vss[5]));
						break;
					}
					j++;
					continue;
				}
				baseMeridiansR = new BaseMeridians(bh, Integer.parseInt(vss[1]), Integer.parseInt(vss[2]), vss[3],
						Double.parseDouble(vss[4]), Integer.parseInt(vss[5]));
				this.meridiansVector.add(baseMeridiansR);
				break;
			}
		}
		return baseMeridiansR;
	}
    
    public BaseXingpans upXingpans(String xingpans) {
        if (xingpans == null || xingpans.equals("")) {
            return null;
        }
        BaseXingpans baseXingpansR = null;
        String[] vs = xingpans.split("\\|");
    LOOP:
        for (int i = 0; i < vs.length; ++i) {
            String[] vss = vs[i].split("_");
            if (vss.length == 7) {
                int bh = Integer.parseInt(vss[0]);
                vss[1] = vss[1].replace("\\u003d", "=");
                int j = 0;
                while (j < this.xingpanVector.size()) {
                    if (((BaseXingpans)this.xingpanVector.get(j)).getBh() == bh) {
                        baseXingpansR = (BaseXingpans)this.xingpanVector.get(j);
                        baseXingpansR.setExp(vss[1]);
                        baseXingpansR.init(Integer.parseInt(vss[2]), vss[3], Double.parseDouble(vss[4]), vss[5], Double.parseDouble(vss[6]));
                        continue LOOP;
                    }
                    else {
                        ++j;
                    }
                }
                baseXingpansR = new BaseXingpans(bh, vss[1], Integer.parseInt(vss[2]), vss[3], Double.parseDouble(vss[4]), vss[5], Double.parseDouble(vss[6]));
                this.xingpanVector.add(baseXingpansR);
            }
        }
        return baseXingpansR;
    }
    
    class EquipBase
    {
        private String[] v;
        private Integer qhv;
        private int lm;
        private int lx;
        private int gg;
        private int mj;
        private double xs;
        private int zs;
        private int lvl;
        private int zsMax;
        private int lvlMax;
        private boolean isL;
        
        public EquipBase(String[] v, Integer qhv) {
            this.v = v;
            this.qhv = qhv;
            this.reset();
        }
        
        public void uplvl(int type, String value) {
            String[] lvls = value.split("转");
            if (lvls.length == 1) {
                if (type == 0) {
                    this.lvl = Integer.parseInt(lvls[0]);
                }
                else {
                    this.lvlMax = Integer.parseInt(lvls[0]);
                }
            }
            else {
                if (lvls[0].equals("飞升")) {
                    if (type == 0) {
                        this.zs = 4;
                    }
                    else {
                        this.zsMax = 4;
                    }
                }
                else if (type == 0) {
                    this.zs = Integer.parseInt(lvls[0]);
                }
                else {
                    this.zsMax = Integer.parseInt(lvls[0]);
                }
                if (type == 0) {
                    this.lvl = Integer.parseInt(lvls[1]);
                }
                else {
                    this.lvlMax = Integer.parseInt(lvls[1]);
                }
            }
        }
        
        public void reset() {
            this.lm = 0;
            this.lx = 0;
            this.gg = 0;
            this.mj = 0;
            this.xs = 1.0;
            this.zs = 0;
            this.lvl = 0;
            this.zsMax = 4;
            this.lvlMax = 999;
            this.isL = false;
        }
    }
    public static List<String> xian = new ArrayList<>();
    public static Map<String, Double> xianMap = new HashMap<>();

    public double filterUp1(String key, double grade, double equip, double born, double additional, double bangpai) {

        ConfigureBean allConfigure = UserMessUntil.getConfigureBean();
        Map<BigDecimal, Configure> item = allConfigure.getAllConfigure();
        Configure configure = item.get(new BigDecimal(1));

        int max = 0;
        if (configure.getRwkxsx() != null && configure.getRwkxsx() != "") {
            max = Integer.parseInt(configure.getRwkxsx());
        }

        if (key.endsWith("上限") || key.equals("四抗") || key.equals("抗震慑")) {
            return grade + equip + born + additional + bangpai;
        } else if (key.equals("抗混乱") || key.equals("抗昏睡") || key.equals("抗封印") || key.equals("抗遗忘")) {
            LoginResult loginResult = RoleData.getRoleData().getLoginResult();
            double sx = BaseValue.Upper(key, loginResult.getRace_id()) + qhv * 0.4;
            sx = sx + max;
            sx = sx * (1 + (getvalue(key + "上限") + getvalue("四抗上限")) / 100.0);
            double z = grade + equip + born + bangpai + getvalue("四抗") + additional;
//            if (z >= sx) {
            xian.add(key);
            xianMap.put(key, sx);
//            }

//            return (z > sx ? sx : z) + additional;
            return z;
        } else if (key.equals("抗三尸") || key.equals("抗浩然正气") || key.equals("抗青面獠牙") || key.equals("抗天魔解体")
                || key.equals("抗小楼夜哭") || key.equals("抗分光化影")) {

        } else if (key.contains("物理吸收率") || key.equals("抗物理")) {
            double getvalue = getvalue("抗物理上限");
            if (getvalue > 100) {
                max += 0;
            }

            xian.add("抗物理");
            xianMap.put("抗物理", Double.valueOf(75 + max));
//                return equip > (75 + max) ? (75 + max + grade + bangpai) + born : equip + grade + bangpai + born;
            return (max + grade + bangpai + equip + additional + born);
        } else if (key.startsWith("抗")) {
            if ((key.startsWith("抗雷") || key.startsWith("抗水") || key.startsWith("抗火") || key.startsWith("抗风")
            ||key.startsWith("抗雷法") || key.startsWith("抗水法") || key.startsWith("抗火法") || key.startsWith("抗风法")) && grade < 0) {
                String raceSting = Util.getRaceSting(RoleData.getRoleData().getLoginResult().getSpecies_id());
                if (raceSting.equals("仙"))
                    max += 0;

                xianMap.put(key, Double.valueOf(120 + max));
                if ((grade + bangpai) > 0) {
                    if (equip + grade > (120 + max)) {
                        xian.add(key);
                    }
                    return equip + grade > (120 + max) ? (120 + max + (grade + bangpai) + born) : equip + grade + bangpai + born;
                } else {
                    if (equip + grade > (120 + max)) {
                        xian.add(key);
                    }
                    return equip + grade > (120 + max) ? (120 + max + bangpai) + born : equip + grade + bangpai + born;
                }
            } else {

                if (key.startsWith("抗雷") || key.startsWith("抗水") || key.startsWith("抗火") || key.startsWith("抗风")||key.startsWith("抗雷法") || key.startsWith("抗水法") || key.startsWith("抗火法") || key.startsWith("抗风法")){
                    String raceSting = Util.getRaceSting(RoleData.getRoleData().getLoginResult().getSpecies_id());
                    if (raceSting.equals("仙"))
                        max += 0;
                }
                xian.add(key);
                xianMap.put(key, Double.valueOf(120 + max));
//                return equip > (75 + max) ? (75 + max + grade + bangpai) + born : equip + grade + bangpai + born;
                return (max + grade + bangpai + equip + additional+born);
            }
        } else if (key.equals("连击率")) {
            if (grade + equip + born + additional > (75 + max)) {
                xian.add(key);
                xianMap.put(key, Double.valueOf(75 + max));
            }
//            return grade + equip + born + additional > (75 + max) ? (75 + max + grade + additional) : grade + equip + born + additional;
            return grade + equip + born + additional;
        } else if ((key.equals("狂暴几率")||key.equals("狂暴")||key.equals("狂暴率"))) {
            if (equip > 80) {
                xian.add(key);
                xianMap.put(key, Double.valueOf(80-born));
                return grade + 80 + born + additional;
            }
//            return grade + equip + born + additional > (75 + max) ? (75 + max + grade + additional) : grade + equip + born + additional;
            return grade + equip + born + additional;
        } else if ((key.equals("致命")||key.equals("致命率")||key.equals("致命几率"))) {
            if (equip > 75) {
                xian.add(key);
                xianMap.put(key, Double.valueOf(75));
                return grade + 75 + born + additional;
            }
//            return grade + equip + born + additional > (75 + max) ? (75 + max + grade + additional) : grade + equip + born + additional;
            return grade + equip + born + additional;
        } else if (key.equals("命中率") ||key.equals("命中") ) {
//            if (equip > 75) {
                xian.add(key);
                xianMap.put(key, raceMax(key)+grade);
                return grade + equip + born + additional;
//                return grade + 75 + born + additional;
//            }
//            return grade + equip + born + additional > (75 + max) ? (75 + max + grade + additional) : grade + equip + born + additional;
//            return grade + equip + born + additional;
        } else if (key.equals("躲闪率")) {
            if (equip > 75) {
                xian.add(key);
                equip = 75;
                xianMap.put(key, Double.valueOf(187));

            }
//			return grade + equip + born + additional > (75+max) ? (75 + max + grade + additional) : grade + equip + born + additional;
            return grade + equip + born + additional;
        } else if (key.equals("连击次数")) {
            xian.add(key);
            xianMap.put(key, Double.valueOf(14));
        }
        return grade + equip + born + additional + bangpai;
    }
    public static double raceMax(String type){
        LoginResult loginResult = RoleData.getRoleData().getLoginResult();
        int raceid=loginResult.getSpecies_id().intValue();
        if (type.equals("连击率")) {
            if (raceid > 24000) {
                return 80;
            } else if (raceid > 23000) {
                return 75;
            } else if (raceid > 22000) {
                return 75;
            } else if (raceid > 21000) {
                return 80;
            } else {
                return 75;
            }
        } else if (type.equals("命中率")||type.equals("命中")) {
            if (raceid > 24000) {
                return 75;
            } else if (raceid > 23000) {
                return 75;
            } else if (raceid > 22000) {
                return 75;
            } else if (raceid > 21000) {
                return 75;
            } else {
                return 75;
            }
        } else if (type.equals("连击次数")) {
            return 14;
//            if (raceid > 50000) {
//                return 80;
//            } else if (raceid > 40000) {
//
//            } else if (raceid > 30000) {
//
//            } else if (raceid > 20000) {
//
//            } else if (raceid > 10000) {
//
//            } else {
//
//            }
        }
        return 75;
    }
}
