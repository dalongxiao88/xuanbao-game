package org.come.good;

import org.come.until.GoodsListFromServerUntil;
import org.come.bean.ColorScheme;
import org.come.mouslisten.GoodsMouslisten;
import org.come.Frame.NPCJfram;
import org.come.entity.RoleSummoning;
import org.come.Frame.PetEquipmentJframe;
import org.come.Frame.ZhuFrame;
import org.come.until.UserMessUntil;
import org.come.entity.Goodstable;

public class PetEquip
{
    public static void PetCJS(Goodstable goodstable) {
        RoleSummoning pet = UserMessUntil.getChosePetMes();
        String[] v = goodstable.getValue().split("\\|");
        String petid = null;
        for (int i = 0; i < v.length; ++i) {
            String[] vs = v[i].split("=");
            if (vs[0].equals("召唤兽")) {
                petid = vs[1];
            }
        }
        if (petid != null && !pet.getSummoningid().equals(petid) && !WCSS(Integer.parseInt(pet.getSummoningid()), pet.getSummoningskin(), Integer.valueOf(Integer.parseInt(petid)))) {
            String tsm = "召唤兽无法佩戴该召唤兽装备";
            ZhuFrame.getZhuJpanel().addPrompt2(tsm);
            return;
        }
        PetEquipmentJframe.getPetEquipmentJframe().getEquipmentJpanel().Equip(pet, goodstable, -1);
    }
    
    public static boolean WCSS(int petid, String skin, Integer id) {
        if ((int)id == 200055 && (skin.equals("400050") || skin.equals("400066") || skin.equals("400024") || skin.equals("400073") || skin.equals("400081"))) {
            return true;
        }
        if ((petid >= 200102 && petid <= 200107) || (petid >= 200092 && petid <= 200096) || petid == 200076 || petid == 200068 || petid == 200060 || petid == 200075 || petid == 200065 || petid == 200043) {
            if ((int)id == 200095 && skin.equals("400105")) {
                return true;
            }
            if ((int)id == 200093 && skin.equals("400103")) {
                return true;
            }
            if ((int)id == 200092 && skin.equals("400102")) {
                return true;
            }
            if ((int)id == 200065 && skin.equals("400079")) {
                return true;
            }
            if ((int)id == 200076 && skin.equals("400078")) {
                return true;
            }
        }
        return (getIDSKIN((int)id) + "").equals(skin);
    }
    
    public static void RoleCJS(Goodstable goodstable) {
        if (goodstable.getGoodsname().equals("彩晶石")) {
            String[] vs = goodstable.getValue().split("\\|");
            int pz = 0;
            for (int i = 0; i < vs.length; ++i) {
                String[] vss = vs[i].split("=");
                if (vss[0].equals("品质")) {
                    pz = Integer.parseInt(vss[1]);
                }
            }
            if (pz < 250) {
                ZhuFrame.getZhuJpanel().addPrompt2("品质超过250时可以转为指定召唤兽饰品");
            }
            else {
                NPCJfram.getNpcJfram().getNpcjpanel().showCJS(goodstable);
            }
        }
        else {
            int pz2 = 0;
            String color = null;
            String[] vs2 = goodstable.getValue().split("\\|");
            for (int j = 0; j < vs2.length; ++j) {
                String[] vss2 = vs2[j].split("=");
                if (vss2[0].equals("品质")) {
                    pz2 = Integer.parseInt(vss2[1]);
                }
                else if (vss2[0].equals("颜色")) {
                    color = vs2[j];
                }
            }
            if (pz2 >= 1200 && color.equals("颜色=无")) {
                ColorScheme colorScheme = UserMessUntil.getColors(0);
                if (colorScheme != null) {
                    goodstable.setValue(goodstable.getValue().replace(color, "颜色=" + colorScheme.getName()));
                    GoodsMouslisten.gooduse(goodstable, 2);
                    return;
                }
            }
        }
    }
    
    public static void getChangeCJS(Goodstable goodstable, String pet) {
        if (!goodstable.getGoodsname().equals("彩晶石")) {
            return;
        }
        String[] vs = goodstable.getValue().split("\\|");
        int pz = 0;
        for (int i = 0; i < vs.length; ++i) {
            String[] vss = vs[i].split("=");
            if (vss[0].equals("品质")) {
                pz = Integer.parseInt(vss[1]);
            }
        }
        if (pz < 250) {
            return;
        }
        int petid = getPetId(pet.substring(0, pet.length() - 2));
        StringBuffer buffer = new StringBuffer();
        buffer.append("召唤兽=");
        buffer.append(petid);
        buffer.append("|皮肤=");
        buffer.append(getCJSID(petid));
        buffer.append("|颜色=无|");
        buffer.append(goodstable.getValue());
        goodstable.setValue(buffer.toString());
        goodstable.setGoodsname(pet);
        goodstable.setSkin("pets" + petid);
        GoodsMouslisten.gooduse(goodstable, 2);
        GoodsListFromServerUntil.PageNumberChange(GoodsListFromServerUntil.Pagenumber);
    }
    
    public static int getCJSID(int id) {
        switch (id) {
            case 200095: {
                return 400527;
            }
            case 200124: {
                return 400528;
            }
            case 200126: {
                return 400529;
            }
            case 200127: {
                return 400530;
            }
            case 200156: {
                return 400531;
            }
            case 200134: {
                return 400532;
            }
            case 200154: {
                return 400533;
            }
            case 200092: {
                return 400526;
            }
            case 200141: {
                return 400548;
            }
            case 200132: {
                return 400547;
            }
            case 200138: {
                return 400546;
            }
            case 200135: {
                return 400545;
            }
            case 200093: {
                return 400544;
            }
            case 200085: {
                return 400543;
            }
            case 200087: {
                return 400542;
            }
            case 200090: {
                return 400541;
            }
            case 200065: {
                return 400540;
            }
            case 200076: {
                return 400539;
            }
            case 200061: {
                return 400538;
            }
            case 200009: {
                return 400537;
            }
            case 200055: {
                return 400536;
            }
            case 200182: {
                return 400535;
            }
            case 200147: {
                return 400534;
            }
            case 200077: {
                return 400549;
            }
            case 200149: {
                return 400550;
            }
            case 200148: {
                return 400551;
            }
            case 200155: {
                return 400552;
            }
            case 200158: {
                return 400553;
            }
            case 200183: {
                return 400559;
            }
            case 200184: {
                return 400576;
            }
            case 200185: {
                return 400557;
            }
            case 506: {
                return 500013;
            }
            case 508: {
                return 400319;
            }
            case 512: {
                return 500105;
            }
            case 200140: {
                return 99112;
            }
            case 200238: {
                return 500790;
            }
            default: {
                return -1;
            }
        }
    }
    
    public static int getIDSKIN(int id) {
        switch (id) {
            case 200095: {
                return 400105;
            }
            case 200124: {
                return 400135;
            }
            case 200126: {
                return 400142;
            }
            case 200127: {
                return 400145;
            }
            case 200156: {
                return 400147;
            }
            case 200134: {
                return 400151;
            }
            case 200154: {
                return 400311;
            }
            case 200092: {
                return 400102;
            }
            case 200141: {
                return 400181;
            }
            case 200132: {
                return 400148;
            }
            case 200138: {
                return 400140;
            }
            case 200135: {
                return 400138;
            }
            case 200093: {
                return 400103;
            }
            case 200085: {
                return 400095;
            }
            case 200087: {
                return 400092;
            }
            case 200090: {
                return 400091;
            }
            case 200065: {
                return 400079;
            }
            case 200076: {
                return 400078;
            }
            case 200061: {
                return 400077;
            }
            case 200009: {
                return 400051;
            }
            case 200055: {
                return 400024;
            }
            case 200182: {
                return 400509;
            }
            case 200147: {
                return 400316;
            }
            case 200077: {
                return 400088;
            }
            case 200149: {
                return 400134;
            }
            case 200148: {
                return 400150;
            }
            case 200155: {
                return 400304;
            }
            case 200158: {
                return 400506;
            }
            case 200183: {
                return 400558;
            }
            case 200184: {
                return 400575;
            }
            case 200185: {
                return 400556;
            }
            case 506: {
                return 500012;
            }
            case 508: {
                return 400318;
            }
            case 512: {
                return 500104;
            }
            case 200140: {
                return 99112;
            }
            case 200238: {
                return 500790;
            }
            default: {
                return -1;
            }
        }
    }
    
    public static int getPetId(String pet) {
        int n = -1;
        switch (pet.hashCode()) {
            case 992639858: {
                if (pet.equals("罗刹鬼姬")) {
                    n = 0;
                    break;
                }
                else {
                    break;
                }
            }
            case 1306099: {
                if (pet.equals("龙马")) {
                    n = 1;
                    break;
                }
                else {
                    break;
                }
            }
            case 686578: {
                if (pet.equals("冥雷")) {
                    n = 2;
                    break;
                }
                else {
                    break;
                }
            }
            case 1070943615: {
                if (pet.equals("西域响马")) {
                    n = 3;
                    break;
                }
                else {
                    break;
                }
            }
            case 36518022: {
                if (pet.equals("金不换")) {
                    n = 4;
                    break;
                }
                else {
                    break;
                }
            }
            case 862050: {
                if (pet.equals("松鼠")) {
                    n = 5;
                    break;
                }
                else {
                    break;
                }
            }
            case 25241553: {
                if (pet.equals("拨浪鼓")) {
                    n = 6;
                    break;
                }
                else {
                    break;
                }
            }
            case 21542489: {
                if (pet.equals("哥俩好")) {
                    n = 7;
                    break;
                }
                else {
                    break;
                }
            }
            case 734439977: {
                if (pet.equals("孔雀明王")) {
                    n = 8;
                    break;
                }
                else {
                    break;
                }
            }
            case 1151169: {
                if (pet.equals("赭炎")) {
                    n = 9;
                    break;
                }
                else {
                    break;
                }
            }
            case 923993163: {
                if (pet.equals("画皮娘子")) {
                    n = 10;
                    break;
                }
                else {
                    break;
                }
            }
            case 651061661: {
                if (pet.equals("冥灵妃子")) {
                    n = 11;
                    break;
                }
                else {
                    break;
                }
            }
            case 40236944: {
                if (pet.equals("黄金兽")) {
                    n = 12;
                    break;
                }
                else {
                    break;
                }
            }
            case 21269896: {
                if (pet.equals("剑精灵")) {
                    n = 13;
                    break;
                }
                else {
                    break;
                }
            }
            case 27766332: {
                if (pet.equals("泥石怪")) {
                    n = 14;
                    break;
                }
                else {
                    break;
                }
            }
            case 21333850: {
                if (pet.equals("冰雪魔")) {
                    n = 15;
                    break;
                }
                else {
                    break;
                }
            }
            case 1067007385: {
                if (pet.equals("蝴蝶仙子")) {
                    n = 16;
                    break;
                }
                else {
                    break;
                }
            }
            case 670860: {
                if (pet.equals("凤凰")) {
                    n = 17;
                    break;
                }
                else {
                    break;
                }
            }
            case 20781099: {
                if (pet.equals("冲冲虫")) {
                    n = 18;
                    break;
                }
                else {
                    break;
                }
            }
            case 938560: {
                if (pet.equals("猪怪")) {
                    n = 19;
                    break;
                }
                else {
                    break;
                }
            }
            case 960729675: {
                if (pet.equals("符咒女娲")) {
                    n = 20;
                    break;
                }
                else {
                    break;
                }
            }
            case 26541119: {
                if (pet.equals("棕小仙")) {
                    n = 21;
                    break;
                }
                else {
                    break;
                }
            }
            case 1011309: {
                if (pet.equals("精卫")) {
                    n = 22;
                    break;
                }
                else {
                    break;
                }
            }
            case 946186: {
                if (pet.equals("猴精")) {
                    n = 23;
                    break;
                }
                else {
                    break;
                }
            }
            case 23224291: {
                if (pet.equals("天龙女")) {
                    n = 24;
                    break;
                }
                else {
                    break;
                }
            }
            case 21663904: {
                if (pet.equals("吉祥果")) {
                    n = 25;
                    break;
                }
                else {
                    break;
                }
            }
            case 29361245: {
                if (pet.equals("狮虎兽")) {
                    n = 26;
                    break;
                }
                else {
                    break;
                }
            }
            case 721976815: {
                if (pet.equals("妙音鸾女")) {
                    n = 27;
                    break;
                }
                else {
                    break;
                }
            }
            case 947804019: {
                if (pet.equals("碧水精魄")) {
                    n = 28;
                    break;
                }
                else {
                    break;
                }
            }
            case 20372551: {
                if (pet.equals("俏娘子")) {
                    n = 29;
                    break;
                }
                else {
                    break;
                }
            }
            case 26328304: {
                if (pet.equals("木甲人")) {
                    n = 30;
                    break;
                }
                else {
                    break;
                }
            }
            case 1448910114: {
                if (pet.equals("瀚威猫将的")) {
                    n = 31;
                    break;
                }
                else {
                    break;
                }
            }
            case 23225869: {
                if (pet.equals("孙小圣")) {
                    n = 32;
                    break;
                }
                else {
                    break;
                }
            }
            case 30440673: {
                if (pet.equals("白龙帝")) {
                    n = 33;
                    break;
                }
                else {
                    break;
                }
            }
            case 751618: {
                if (pet.equals("孟极")) {
                    n = 34;
                    break;
                }
                else {
                    break;
                }
            }
            case 868959946: {
                if (pet.equals("海螺姑娘")) {
                    n = 35;
                    break;
                }
                else {
                    break;
                }
            }
        }
        switch (n) {
            case 0: {
                return 200095;
            }
            case 1: {
                return 200124;
            }
            case 2: {
                return 200126;
            }
            case 3: {
                return 200127;
            }
            case 4: {
                return 200156;
            }
            case 5: {
                return 200134;
            }
            case 6: {
                return 200154;
            }
            case 7: {
                return 200092;
            }
            case 8: {
                return 200141;
            }
            case 9: {
                return 200132;
            }
            case 10: {
                return 200138;
            }
            case 11: {
                return 200135;
            }
            case 12: {
                return 200085;
            }
            case 13: {
                return 200093;
            }
            case 14: {
                return 200087;
            }
            case 15: {
                return 200090;
            }
            case 16: {
                return 200065;
            }
            case 17: {
                return 200076;
            }
            case 18: {
                return 200061;
            }
            case 19: {
                return 200009;
            }
            case 20: {
                return 200055;
            }
            case 21: {
                return 200182;
            }
            case 22: {
                return 200147;
            }
            case 23: {
                return 200077;
            }
            case 24: {
                return 200149;
            }
            case 25: {
                return 200148;
            }
            case 26: {
                return 200155;
            }
            case 27: {
                return 200158;
            }
            case 28: {
                return 200183;
            }
            case 29: {
                return 200184;
            }
            case 30: {
                return 200185;
            }
            case 31: {
                return 506;
            }
            case 32: {
                return 508;
            }
            case 33: {
                return 512;
            }
            case 34: {
                return 200140;
            }
            case 35: {
                return 200238;
            }
            default: {
                return -1;
            }
        }
    }
}
