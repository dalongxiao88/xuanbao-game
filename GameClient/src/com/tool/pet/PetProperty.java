package com.tool.pet;

import java.util.List;
import org.come.until.Util;
import org.come.entity.MountSkill;
import org.come.mouslisten.PetAddPointMouslisten;
import org.come.entity.Mount;
import org.come.Jpanel.ZhuJpanel;
import java.math.BigDecimal;
import org.come.until.GoodsListFromServerUntil;
import org.come.entity.Goodstable;
import org.come.until.JmSum;
import java.util.Map;
import org.come.until.AnalysisString;
import com.tool.role.RoleData;
import org.come.model.PalData;
import com.tool.btn.PetPanelBtn;
import org.come.entity.Pal;
import org.come.until.AccessNedanMsgUntil;
import org.come.entity.RoleSummoning;
import com.tool.role.Ql;

public class PetProperty
{
    static String[] evs;
    private static Ql ql;
    
    public static Ql getQl(RoleSummoning pet) {
        if (PetProperty.ql == null) {
            PetProperty.ql = new Ql();
        }
        return AccessNedanMsgUntil.getPetQl(PetProperty.ql, pet);
    }
    
    public static Ql getQl(Pal pal) {
        if (PetProperty.ql == null) {
            PetProperty.ql = new Ql();
        }
        return AccessNedanMsgUntil.getPalQl(PetProperty.ql, pal);
    }
    
    public static void ShowQl(RoleSummoning pet) {
        PetPanelBtn.showRolesumming(getQl(pet));
    }
    
    public static void ShowQl(Pal pal) {
        PetPanelBtn.showRolesumming(getQl(pal));
    }
    
    public static int[] getPalHMASp(Pal pal, PalData palData) {
        int[] pals = new int[4];
        double grow = (pal != null) ? pal.getGrow() : 1.0;
        Integer grade = RoleData.getRoleData().getLoginResult().getGrade();
        int lvl = AnalysisString.lvlint((int)grade);
        int zs = AnalysisString.lvltrue((int)grade);
        if (pal != null) {
            Map<String, Double> map = AccessNedanMsgUntil.getPalMap(pal, true);
            pals[0] = getBase(lvl, (int)AccessNedanMsgUntil.getMapValue(map, "根骨"), grow, palData.getHp(), 0, map);
            pals[1] = getBase(lvl, (int)AccessNedanMsgUntil.getMapValue(map, "灵性"), grow, palData.getMp(), 1, map);
            pals[2] = getBase(lvl, (int)AccessNedanMsgUntil.getMapValue(map, "力量"), grow, palData.getAp(), 2, map);
            pals[3] = getBase(lvl, (int)AccessNedanMsgUntil.getMapValue(map, "敏捷"), grow, palData.getSp(), 3, map);
            map.clear();
        }
        else {
            int[] jds = palData.getJds();
            int size = jds[0] + jds[1] + jds[2] + jds[3];
            int point = lvl + zs * 40;
            point = lvl % size;
            for (int i = 0; i < pals.length; ++i) {
                int n = i;
                pals[n] += lvl + point / size * jds[i];
                if (point > 0 && jds[i] > 0) {
                    if (point > palData.getJds()[i]) {
                        int n2 = i;
                        pals[n2] += jds[i];
                        point -= jds[i];
                    }
                    else {
                        int n3 = i;
                        pals[n3] += point;
                        point = 0;
                    }
                }
            }
            pals[0] = getRoleValue(lvl, pals[0], grow, palData.getHp(), 0);
            pals[1] = getRoleValue(lvl, pals[1], grow, palData.getMp(), 1);
            pals[2] = getRoleValue(lvl, pals[2], grow, palData.getAp(), 2);
            pals[3] = getRoleValue(lvl, pals[3], grow, palData.getSp(), 3);
        }
        return pals;
    }
    
    public static int getBase(int lvl, int P, double G, int base, int type, Map<String, Double> map) {
        int value = getRoleValue(lvl, P, G, base, type);
        if (type == 0) {
            value = (int)((double)value + AccessNedanMsgUntil.getMapValue(map, "hp"));
            value = (int)((double)value + AccessNedanMsgUntil.getMapValue(map, "HP"));
            value = (int)((double)value + AccessNedanMsgUntil.getMapValue(map, "附加气血"));
            value = (int)((double)value + AccessNedanMsgUntil.getMapValue(map, "加气血"));
            value = (int)((double)value * (AccessNedanMsgUntil.getMapValue(map, "HP成长") + 1.0));
            value = (int)((double)value * (AccessNedanMsgUntil.getMapValue(map, "加强气血") / 100.0 + 1.0));
            value = (int)((double)value * (AccessNedanMsgUntil.getMapValue(map, "气血增加率") / 100.0 + 1.0));
        }
        else if (type == 1) {
            value = (int)((double)value + AccessNedanMsgUntil.getMapValue(map, "mp"));
            value = (int)((double)value + AccessNedanMsgUntil.getMapValue(map, "MP"));
            value = (int)((double)value + AccessNedanMsgUntil.getMapValue(map, "附加法力"));
            value = (int)((double)value + AccessNedanMsgUntil.getMapValue(map, "附加魔法"));
            value = (int)((double)value + AccessNedanMsgUntil.getMapValue(map, "加魔法"));
            value = (int)((double)value * (AccessNedanMsgUntil.getMapValue(map, "MP成长") + 1.0));
            value = (int)((double)value * (AccessNedanMsgUntil.getMapValue(map, "加强法力") / 100.0 + 1.0));
        }
        else if (type == 2) {
            value = (int)((double)value + AccessNedanMsgUntil.getMapValue(map, "ap"));
            value = (int)((double)value + AccessNedanMsgUntil.getMapValue(map, "AP"));
            value = (int)((double)value + AccessNedanMsgUntil.getMapValue(map, "攻击"));
            value = (int)((double)value + AccessNedanMsgUntil.getMapValue(map, "附加攻击"));
            value = (int)((double)value + AccessNedanMsgUntil.getMapValue(map, "基础攻击"));
            value = (int)((double)value + AccessNedanMsgUntil.getMapValue(map, "加攻击"));
            value = (int)((double)value * (AccessNedanMsgUntil.getMapValue(map, "AP成长") + 1.0));
            value = (int)((double)value * (AccessNedanMsgUntil.getMapValue(map, "加强攻击") / 100.0 + 1.0));
            value = (int)((double)value * (AccessNedanMsgUntil.getMapValue(map, "加成攻击") / 100.0 + 1.0));
        }
        else if (type == 3) {
            value = (int)((double)value + AccessNedanMsgUntil.getMapValue(map, "sp"));
            value = (int)((double)value + AccessNedanMsgUntil.getMapValue(map, "SP"));
            value = (int)((double)value + AccessNedanMsgUntil.getMapValue(map, "速度"));
            value = (int)((double)value + AccessNedanMsgUntil.getMapValue(map, "加速度"));
            value = (int)((double)value + AccessNedanMsgUntil.getMapValue(map, "附加速度"));
            value = (int)((double)value * (AccessNedanMsgUntil.getMapValue(map, "SP成长") + 1.0));
            value = (int)((double)value * (AccessNedanMsgUntil.getMapValue(map, "加强速度") / 100.0 + 1.0));
        }
        return value;
    }
    
    public static int[] getPetHMASp(RoleSummoning pet) {
        int[] pets = new int[5];
        if (pet == null) {
            return pets;
        }
        int lvl = AnalysisString.petLvlint((int)pet.getGrade());
        double grow = Double.parseDouble(pet.getGrowlevel());
        pets[0] = pet.getHp();
        pets[1] = pet.getMp();
        pets[2] = pet.getAp();
        pets[3] = pet.getSp();
        pets[4] = 0;
        if (grow >= 3.0 || pets[0] > 9999 || pets[1] > 9999 || pets[2] > 9999 || pets[3] > 9999) {
            JmSum.xiugaiqi();
        }
        int zBone = (int)pet.getBone();
        int zSpir = (int)pet.getSpir();
        int zPower = (int)pet.getPower();
        int zSpeed = (int)pet.getSpeed();
        int zCalm = (int)pet.getCalm();
        int addhp = 0;
        int addmp = 0;
        int addap = 0;
        if (pet.getStye() != null && pet.getStye().length() > 1) {
            String[] v = pet.getStye().split("\\|");
            for (int i = 1; i < v.length; ++i) {
                String[] vs = v[i].split("-");
                if (vs.length >= 2) {
                    Goodstable goodstable = (Goodstable)GoodsListFromServerUntil.fushis.get(new BigDecimal(vs[1]));
                    if (goodstable != null) {
                        String[] t = goodstable.getValue().split("\\|");
                        for (int j = 0; j < t.length; ++j) {
                            if (t[j].startsWith(PetProperty.evs[0])) {
                                String[] ts = t[j].split("=");
                                zBone += Integer.parseInt(ts[1]);
                            }
                            else if (t[j].startsWith(PetProperty.evs[1])) {
                                String[] ts = t[j].split("=");
                                zSpir += Integer.parseInt(ts[1]);
                            }
                            else if (t[j].startsWith(PetProperty.evs[2])) {
                                String[] ts = t[j].split("=");
                                zPower += Integer.parseInt(ts[1]);
                            }
                            else if (t[j].startsWith(PetProperty.evs[3])) {
                                String[] ts = t[j].split("=");
                                zSpeed += Integer.parseInt(ts[1]);
                            }
                            else if (t[j].startsWith(PetProperty.evs[4])) {
                                String[] vStrings = t[j].split("\\&");
                                for (int k = 1; k < vStrings.length; ++k) {
                                    if (vStrings[k].startsWith(PetProperty.evs[5])) {
                                        String[] mes = vStrings[k].split("=");
                                        addhp = (int)((double)addhp + Double.parseDouble(mes[1]));
                                    }
                                    else if (vStrings[k].startsWith(PetProperty.evs[6])) {
                                        String[] mes = vStrings[k].split("=");
                                        addmp = (int)((double)addmp + Double.parseDouble(mes[1]));
                                    }
                                    else if (vStrings[k].startsWith(PetProperty.evs[7])) {
                                        String[] mes = vStrings[k].split("=");
                                        addap = (int)((double)addap + Double.parseDouble(mes[1]));
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        pets[0] = getRoleValue(lvl, zBone, grow, pets[0], 0) + addhp;
        pets[1] = getRoleValue(lvl, zSpir, grow, pets[1], 1) + addmp;
        pets[2] = getRoleValue(lvl, zPower, grow, pets[2], 2) + addap;
        pets[3] = getRoleValue(lvl, zSpeed, grow, pets[3], 3);
        pets[4] = getRoleValue(lvl, zCalm, grow, pets[4], 4);
        pet.getSI(pets);
        pet.getLX(pets);
        if (pet.getPetSkills() != null && pet.getPetSkills().contains("1248")) {
            int n = 2;
            pets[n] = (int)((double)pets[n] * 1.3);
        }
        int l = 0;
        while (l < ZhuJpanel.getListMount().size()) {
            Mount mount = (Mount)ZhuJpanel.getListMount().get(l);
            if (mount.isID(pet.getSid())) {
                List<MountSkill> mountSkills = mount.getMountskill();
                PetAddPointMouslisten.shouhuvalue(pets, pet);
                if (mountSkills != null) {
                    double xs1 = 1.0;
                    double xs2 = 1.0;
                    double xs3 = 1.0;
                    double xs4 = 1.0;
                    for (int m = 0; m < mountSkills.size(); ++m) {
                        String ms = Util.calculateAdditionBase(mount, ((MountSkill)mountSkills.get(m)).getSkillname());
                        if (ms != null) {
                            String[] v2 = ms.split("=");
                            if (v2[0].equals("HP")) {
                                xs1 += Double.parseDouble(v2[1]);
                            }
                            else if (v2[0].equals("MP")) {
                                xs2 += Double.parseDouble(v2[1]);
                            }
                            else if (v2[0].equals("AP")) {
                                xs3 += Double.parseDouble(v2[1]);
                            }
                            else if (v2[0].equals("SP")) {
                                xs4 += Double.parseDouble(v2[1]);
                            }
                        }
                    }
                    int n2 = 0;
                    pets[n2] = (int)((double)pets[n2] * xs1);
                    int n3 = 1;
                    pets[n3] = (int)((double)pets[n3] * xs2);
                    int n4 = 2;
                    pets[n4] = (int)((double)pets[n4] * xs3);
                    int n5 = 3;
                    pets[n5] = (int)((double)pets[n5] * xs4);
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
        if (pet.getSkill() != null && pet.getSkill().contains("1299")) {
            int n6 = 0;
            pets[n6] = (int)((double)pets[n6] * 0.6);
            int n7 = 1;
            pets[n7] = (int)((double)pets[n7] * 0.6);
            int n8 = 2;
            pets[n8] = (int)((double)pets[n8] * 0.6);
            int n9 = 3;
            pets[n9] += 330;
        }
        return pets;
    }
    
    public static int getRoleValue(int lvl, int P, double G, int base, int type) {
        if (type == 0 || type == 1) {
            return (int)((double)(lvl * P) * G) + (int)((0.7 * (double)lvl * G + 1.0) * (double)base);
        }
        if (type == 2) {
            return (int)((double)(lvl * P) * G / 5.0) + (int)((0.14 * (double)lvl * G + 1.0) * (double)base);
        }
        if (type == 3) {
            return (int)((double)(base + P) * G);
        }
        return P;
    }
    
    static {
        PetProperty.evs = new String[] { "根骨=", "灵性=", "力量=", "敏捷=", "炼化属性", "增加气血", "增加法力", "增加攻击" };
    }
}
