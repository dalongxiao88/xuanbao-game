package org.come.until;

import com.tool.role.RoleProperty;
import com.tool.role.RoleData;
import com.tool.btn.BaptizeBtn;
import org.come.entity.Goodstable;
/**
 * 物品解析
 *
 * @author 黄建彬
 *
 * @date
 */
public class DevicGoodsEquiptmentUntil
{
    /**
     * 判断物品能否穿戴
     */
    public static int getEquiptmentOrNo(Goodstable goodstable) {
        int part = -1;
        //		8016	无属性
//		8017	无级别
        boolean is1 = false;
        boolean is2 = false;
        double xs = 1.0;
        String[] mesVal = goodstable.getValue().split("\\|");
        for (int i = mesVal.length - 1; i >= 0; --i) {
            if (mesVal[i].startsWith(BaptizeBtn.Extras[0])) {
                String[] vStrings = mesVal[i].split("\\&");
                for (int k = 1; k < vStrings.length; ++k) {
                    String[] mes = vStrings[k].split("=");
                    if (mes[0].equals("特技")) {
                        for (int l = 1; l < mes.length; ++l) {
                            if (mes[l].equals("8016")) {
                                is1 = true;
                            }
                            else if (mes[l].equals("8017")) {
                                is2 = true;
                            }
                        }
                    }
                }
            }
            else if (mesVal[i].startsWith(BaptizeBtn.Extras[2])) {
                String[] vStrings = mesVal[i].split("\\&");
                for (int k = 1; k < vStrings.length; ++k) {
                    if (vStrings[k].startsWith("属性需求")) {
                        int path = vStrings[k].indexOf("=");
                        if (path != -1) {
                            double value = Double.parseDouble(vStrings[k].substring(path + 1)) / 100.0;
                            xs += (vStrings[k].startsWith("属性需求减少") ? (-value) : value);
                        }
                    }
                }
            }
            else {
                String[] mes2 = mesVal[i].split("=");
                if (mes2[0].equals("装备角色") || mes2[0].equals("性别要求") || mes2[0].equals("性别")) {
                    if (!contriolContinue(mes2, xs)) {
                        return -2;
                    }
                }
                else if (mes2[0].equals("等级要求") || mes2[0].equals("最高携带等级")) {
                    if (!is2 && !contriolContinue(mes2, xs)) {
                        return -2;
                    }
                }
                else if (mes2[0].equals("力量要求") || mes2[0].equals("灵性要求") || mes2[0].equals("根骨要求") || mes2[0].equals("敏捷要求")) {
                    if (!is1 && !contriolContinue(mes2, xs)) {
                        return -2;
                    }
                }else if (mes2[0].equals("功绩需求")) {
                    if (!is1&&!contriolContinue(mes2,xs)) {
                        return -2;
                    }
                }
                else if (mes2[0].equals("装备部位")) {
                    try {
                        part = Integer.parseInt(mes2[1]);
                    }
                    catch (Exception ex) {}
                }
            }
        }
        return part;
    }

    public static boolean contriolContinue(String[] mes,double xs) {
        // 判断要求
        switch (mes[0]) {
            case "装备角色":
                if (mes[1].indexOf(RoleData.getRoleData().getLoginResult().getLocalname()) != -1) {
                    return true;
                }
                break;
            case "力量要求":
                if (RoleProperty.getPower(RoleData.getRoleData().getLoginResult()) >= Integer.valueOf(mes[1])*xs) {
                    return true;
                }
                break;
            case "灵性要求":
                if (RoleProperty.getSpir(RoleData.getRoleData().getLoginResult()) >= Integer.valueOf(mes[1])*xs) {
                    return true;
                }
                break;
            case "根骨要求":
                if (RoleProperty.getBone(RoleData.getRoleData().getLoginResult()) >= Integer.valueOf(mes[1])*xs) {
                    return true;
                }
                break;
            case "敏捷要求":
                if (RoleProperty.getSpeed(RoleData.getRoleData().getLoginResult()) >= Integer.valueOf(mes[1])*xs) {
                    return true;
                }
                break;
            case "功绩需求":
                if (RoleData.getRoleData().getLoginResult().getScoretype("功绩").intValue() >= Integer.valueOf(mes[1])*xs) {
                    return true;
                }
                break;
            case "等级要求":
                if (AnalysisString.lvlfull(RoleData.getRoleData().getLoginResult().getGrade(), mes[1])) {
                    return true;
                }
                break;
            case "最高携带等级":
                if (AnalysisString.lvlfull2(RoleData.getRoleData().getLoginResult().getGrade(), mes[1])) {
                    return true;
                }
                break;
            case "性别要求":
            case "性别":
                if (mes[1].equals("2")) {
                    return true;
                }
                String sexBtn = Integer.valueOf(mes[1]) == 1 ? "男" : "女";
                if (Util.getSexSting(RoleData.getRoleData().getLoginResult().getSpecies_id()).equals(sexBtn)) {
                    return true;
                }
                break;
        }
        return false;
    }
}
