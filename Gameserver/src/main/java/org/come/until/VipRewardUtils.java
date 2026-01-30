package org.come.until;

import java.util.List;
import come.tool.Role.PartJade;
import org.come.entity.Lingbao;
import org.come.tool.EquipTool;
import org.come.server.GameServer;
import come.tool.Role.RoleData;
import come.tool.Stall.AssetUpdate;
import org.come.entity.Goodstable;
import java.math.BigDecimal;
import org.come.bean.LoginResult;

public class VipRewardUtils
{
    public static String[] gradeAndMoney(String grade) {
        String gradeNum = "0";
        String money = "0";
        String[] split = grade.split("v");
        gradeNum = split[1];
        String[] arr = { gradeNum, money };
        return arr;
    }
    
    public static int everydayRecharge(String grade) {
        int gradeNum = 0;
        String[] split = grade.split("v");
        gradeNum = (int)Integer.valueOf(split[1]);
        return gradeNum;
    }
    
    public static int continuityRecharge(String grade) {
        int gradeNum = 0;
        String[] split = grade.split("v");
        gradeNum = (int)Integer.valueOf(split[1]);
        return gradeNum;
    }
    
    public static int getRoleGradeReward(int grade) {
        if (grade >= 480) {
            return 10;
        }
        if (grade >= 480) {
            return 9;
        }
        if (grade >= 470) {
            return 8;
        }
        if (grade >= 460) {
            return 7;
        }
        if (grade >= 459) {
            return 6;
        }
        if (grade >= 439) {
            return 5;
        }
        if (grade >= 399) {
            return 4;
        }
        if (grade >= 338) {
            return 3;
        }
        if (grade >= 210) {
            return 2;
        }
        if (grade >= 102) {
            return 1;
        }
        return 0;
    }
    
    public static boolean checkYesOrNo(String info, String action, String grade) {
        if (info == null) {
            info = "";
        }
        String[] infoArr = info.split("&&");
        for (int i = 0; i < infoArr.length; ++i) {
            String[] arr = infoArr[i].split("=");
            if (arr[0].equals(action)) {
                if (arr.length == 1) {
                    return false;
                }
                String[] gradeArr = arr[1].split("\\|");
                for (int j = 0; j < gradeArr.length; ++j) {
                    if (gradeArr[j].equals(grade)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }
    
    public static void setVipget(LoginResult roleInfo, String action, String grade) {
        String vipget = roleInfo.getVipget();
        StringBuffer str = new StringBuffer();
        if (vipget == null || "".equals(vipget)) {
            str.append(action);
            str.append("=");
            str.append(grade);
        }
        else {
            String[] vipgetArr = vipget.split("&&");
            int flag = 0;
            for (int i = 0; i < vipgetArr.length; ++i) {
                String[] arr = vipgetArr[i].split("=");
                if (i == 0) {
                    str.append(arr[0]);
                    str.append("=");
                }
                else {
                    str.append("&&");
                    str.append(arr[0]);
                    str.append("=");
                }
                String[] rew = arr[1].split("\\|");
                for (int j = 0; j < rew.length; ++j) {
                    str.append(rew[j]);
                    if (j < rew.length - 1) {
                        str.append("|");
                    }
                }
                if (arr[0].equals(action)) {
                    str.append("|");
                    str.append(grade);
                    flag = 1;
                }
            }
            if (flag == 0) {
                str.append("&&");
                str.append(action);
                str.append("=");
                str.append(grade);
            }
        }
        roleInfo.setVipget(str.toString());
    }
    
    public static void giveRoleGoods(BigDecimal goodid, Goodstable goodstable, LoginResult loginResult, int goodSum, AssetUpdate assetUpdate, int buyType, RoleData roleData) {
        goodstable.setRole_id(loginResult.getRole_id());
        long yid = goodid.longValue();
        for (int i = 0; i < goodSum; ++i) {
            if (i != 0) {
                goodstable = GameServer.getGood(goodid);
            }
            goodstable.setRole_id(loginResult.getRole_id());
            long sid = goodstable.getGoodsid().longValue();
            if (sid >= 70001L && sid <= 70030L) {
                Lingbao lingbao = SplitLingbaoValue.addling(goodstable.getGoodsname(), loginResult.getRole_id());
                assetUpdate.setLingbao(lingbao);
                AllServiceUtil.getGoodsrecordService().insert(goodstable, null, Integer.valueOf(goodSum), Integer.valueOf(buyType));
            }
            else if (sid >= 69001L && sid <= 69015L) {
                Lingbao lingbao = SplitLingbaoValue.addfa(sid, loginResult.getRole_id());
                assetUpdate.setLingbao(lingbao);
                AllServiceUtil.getGoodsrecordService().insert(goodstable, null, Integer.valueOf(goodSum), Integer.valueOf(buyType));
            }
            else if (goodstable.getType() == 825L) {
                if (!goodstable.getValue().equals("")) {
                    String[] v = goodstable.getValue().split("\\|");
                    int suitid = Integer.parseInt(v[0]);
                    int part = Integer.parseInt(v[1]);
                    int pz = Integer.parseInt(v[2]);
                    PartJade jade = roleData.getPackRecord().setPartJade(suitid, part, pz, 1);
                    assetUpdate.setJade(jade);
                    AllServiceUtil.getGoodsrecordService().insert(goodstable, null, Integer.valueOf(goodSum), Integer.valueOf(buyType));
                }
            }
            else if (goodstable.getType() == -1L) {
                roleData.getPackRecord().addTX(-sid + "");
                assetUpdate.setGood(goodstable);
                AllServiceUtil.getGoodsrecordService().insert(goodstable, null, Integer.valueOf(1), Integer.valueOf(buyType));
            }
            else if (EquipTool.canSuper(goodstable.getType())) {
                int sum = (yid == sid) ? goodSum : 1;
                List<Goodstable> sameGoodstable = AllServiceUtil.getGoodsTableService().selectGoodsByRoleIDAndGoodsID(loginResult.getRole_id(), goodstable.getGoodsid());
                if (sameGoodstable.size() != 0) {
                    int uses = (int)((Goodstable)sameGoodstable.get(0)).getUsetime() + sum;
                    ((Goodstable)sameGoodstable.get(0)).setUsetime(Integer.valueOf(uses));
                    AllServiceUtil.getGoodsTableService().updateGoodRedis((Goodstable)sameGoodstable.get(0));
                    assetUpdate.setGood((Goodstable)sameGoodstable.get(0));
                    AllServiceUtil.getGoodsrecordService().insert((Goodstable)sameGoodstable.get(0), null, Integer.valueOf(goodSum), Integer.valueOf(buyType));
                }
                else {
                    goodstable.setUsetime(Integer.valueOf(sum));
                    AllServiceUtil.getGoodsTableService().insertGoods(goodstable);
                    assetUpdate.setGood(goodstable);
                    AllServiceUtil.getGoodsrecordService().insert(goodstable, null, Integer.valueOf(goodSum), Integer.valueOf(buyType));
                }
                if (yid == sid) {
                    break;
                }
            }
            else {
                goodstable.setUsetime(Integer.valueOf(1));
                AllServiceUtil.getGoodsTableService().insertGoods(goodstable);
                assetUpdate.setGood(goodstable);
                AllServiceUtil.getGoodsrecordService().insert(goodstable, null, Integer.valueOf(goodSum), Integer.valueOf(buyType));
            }
        }
    }
    
    public static void main(String[] args) {
        String str = "v11";
        String[] split = str.split("v");
        System.out.println(split[1]);
    }
}
