package org.come.action.role;

import org.come.entity.*;
import org.come.model.Alchemy;
import come.tool.Calculation.BaseXingpans;
import org.come.action.buy.AddGoodUtil;
import org.apache.commons.lang.math.NumberUtils;
import io.netty.util.internal.StringUtil;
import come.tool.Calculation.BaseMeridians;
import org.come.action.monitor.MonitorUtil;
import come.tool.Good.ExpUtil;
import come.tool.Calculation.BaseQl;
import come.tool.Battle.BattleMixDeal;

import java.util.*;

import come.tool.Role.PrivateData;
import come.tool.Role.RoleData;
import org.come.model.Dorp;
import org.come.action.suit.SuitComposeAction;
import org.come.until.AchievemUtil;
import org.come.until.AllServiceUtil;
import come.tool.Calculation.RoleReborn;
import java.math.RoundingMode;
import org.come.bean.RoleTransBean;
import come.tool.Calculation.BaseValue;
import come.tool.Role.RolePool;
import java.math.BigDecimal;
import come.tool.Stall.AssetUpdate;
import org.come.until.GsonUtil;
import org.come.action.reward.DrawnitemsAction;
import come.tool.Good.DropUtil;
import org.come.handler.SendMessage;
import org.come.protocol.Agreement;
import org.apache.commons.lang.StringUtils;
import org.come.server.GameServer;
import org.come.bean.LoginResult;
import io.netty.channel.ChannelHandlerContext;
import org.come.action.IAction;

public class RoleChangeAction implements IAction
{
    public static Random random;
    private static final long CONVERSION_EXP = 1000000000L;
    private static final int CONVERSION_NUM = 10;
    
    @Override
    public void action(ChannelHandlerContext ctx, String message) {
        LoginResult loginResult = (LoginResult)GameServer.getAllLoginRole().get(ctx);
        if (loginResult == null) {
            SendMessage.sendMessageToSlef(ctx, Agreement.getAgreement().PromptAgreement("角色掉线，请重连"));
            return;
        }
        if (message.startsWith("D")) {
            this.DDJD(loginResult, message);
        } else if (message.startsWith("XB")) {
            String[] mess = message.split("=");
            XuanBao bao = AllServiceUtil.getXuanBaoService().selectLingbaoByID(new BigDecimal(mess[1]));
            int lvl = loginResult.getGrade();
            lvl = BattleMixDeal.lvlint(lvl);
            if (lvl <= bao.getLvl()) {
                SendMessage.sendMessageToSlef(ctx, Agreement.getAgreement().PromptAgreement("玄宝等级不能超过人物等级"));
                return;
            }
            Goodstable goodstable = null;
            if (mess.length >= 3) {
                goodstable = AllServiceUtil.getGoodsTableService().getGoodsByRgID(new BigDecimal(mess[3]));
                if (goodstable == null) {
                    return;
                }
                if (goodstable.getRole_id().compareTo(loginResult.getRole_id()) != 0) {
                    return;
                }
                if (goodstable.getUsetime() <= 0) {
                    return;
                }
                //添加记录
                AllServiceUtil.getGoodsrecordService().insert(goodstable, null, 1, 9);
                AllServiceUtil.getGoodsTableService().updateGoodRedis(goodstable);
            }
//            long needmoney = Long.parseLong(message.split("=")[2]) *  15;
            baoExp(bao, ctx, Long.parseLong(mess[2]), loginResult, 0, goodstable);
        }
        else if (message.startsWith("LHSX")) {
            String[] split = message.split("=");
            if (StringUtils.isNotBlank(loginResult.getLianghaoValue())) {
                String[] split2 = loginResult.getLianghaoValue().split("@");
                split2[0] = split[1];
                String join = StringUtils.join(split2, "@");
                loginResult.setLianghaoValue(join);
            }
        }
        //充值积分抽奖
        else if (message.startsWith("CZCJ")) {
            String[] v = message.split("\\|");
            if (v.length != 2) {
                return;
            }
            Dorp dorp = null;
            int i = Integer.parseInt(v[1]);
            if (loginResult.getScoretype("充值积分").longValue() < (long)i && loginResult.getGold().longValue() < (long)i) {
                SendMessage.sendMessageToSlef(ctx, Agreement.getAgreement().PromptAgreement("少侠,你的充值积分不足" + i + "#32!"));
                return;
            }
            AssetUpdate assetUpdate = null;
            if (i == 50) {
                dorp = GameServer.getDorp("5000");//仙玉50积分抽奖dropid
                assetUpdate = DropUtil.getCZJF(loginResult, dorp.getDorpValue(), "#R{角色名}#c00FFFF用充值点卡获得的#R50#c00FFFF积分抽取到了#G{物品名}#c00FFFF真是可喜可贺。#50", 888, 1.0, null, "", "", null);
            }
            else if (i == 150) {
                dorp = GameServer.getDorp("5001");//仙玉1500积分抽奖dropid
                assetUpdate = DropUtil.getCZJF(loginResult, dorp.getDorpValue(), "#R{角色名}#c00FFFF用充值点卡获得的#R150#c00FFFF积分抽取到了#G{物品名}#c00FFFF真是可喜可贺。#50", 888, 1.0, null, "", "", null);
            }
            else if (i == 1000) {
                dorp = GameServer.getDorp("5002");//仙玉1000积分抽奖dropid
                assetUpdate = DropUtil.getCZJF(loginResult, dorp.getDorpValue(), "#R{角色名}#c00FFFF用充值点卡获得的#R1000#c00FFFF积分抽取到了#G{物品名}#c00FFFF真是可喜可贺。#50", 888, 1.0, null, "", "", null);
            }
            loginResult.setScore(DrawnitemsAction.Splice(loginResult.getScore(), "充值积分=" + i, 3));
            assetUpdate.updata("充值积分=-" + i);
            SendMessage.sendMessageToSlef(ctx, Agreement.getAgreement().assetAgreement(GsonUtil.getGsonUtil().getgson().toJson(assetUpdate)));
        }
        else if (message.startsWith("XDFM")) {
            AssetUpdate assetUpdate2 = new AssetUpdate();
            long money = 100000000L;
            if ("XDFM=0".equals(message)) {
                if (loginResult.getGold().longValue() < money) {
                    SendMessage.sendMessageToSlef(ctx, Agreement.getAgreement().PromptAgreement("少侠,你的银两不够！"));
                    return;
                }
                loginResult.setGold(loginResult.getGold().subtract(BigDecimal.valueOf(money)));
                assetUpdate2.updata("D=-" + money);
                assetUpdate2.setMsg("#Y你使用了#R" + money + "#Y两银子,重置了心法");
            }
            BigDecimal scoretype = loginResult.getScoretype("法门选定=");
            if (scoretype.intValue() == 0) {
                loginResult.setScore(DrawnitemsAction.Splice(loginResult.getScore(), "法门选定=1", 2));
            }
            loginResult.setScore(DrawnitemsAction.Splice(loginResult.getScore(), "法门选定=" + message.split("=")[1], 1));
            assetUpdate2.updata("法门选定=" + message.split("=")[1]);
            SendMessage.sendMessageToSlef(ctx, Agreement.getAgreement().assetAgreement(GsonUtil.getGsonUtil().getgson().toJson(assetUpdate2)));
        }
        else if (message.startsWith("QXZ")) {
            RoleData roleData = RolePool.getRoleData(loginResult.getRole_id());
            String born = roleData.getPrivateData().getBorn();
            String born2 = roleData.getPrivateData().getBorn1();
            roleData.getPrivateData().setBorn(born2);
            roleData.getPrivateData().setBorn1(born);
            roleData.setBorns(BaseValue.reborn(born2));
            RoleTransBean roleTransBean = new RoleTransBean();
            roleTransBean.setLoginResult(loginResult);
            roleTransBean.setPrivateData(roleData.getPrivateData());
            SendMessage.sendMessageToSlef(ctx, Agreement.getAgreement().RacialTransformationAgreement(GsonUtil.getGsonUtil().getgson().toJson(roleTransBean)));
        }
        else if (message.startsWith("FM")) {
            BigDecimal exp = new BigDecimal("45000000");
            BigDecimal money2 = new BigDecimal("9000000");
            String[] split3 = message.split("=");
            if (split3.length == 3) {
                int j = Integer.parseInt(split3[2]);
                String s = "法门" + split3[1];
                BigDecimal f1 = loginResult.getScoretype(s);
                BigDecimal roleGradeMaxFMsld = this.getRoleGradeMaxFMsld((int)loginResult.getGrade());
                if (f1.intValue() >= roleGradeMaxFMsld.intValue()) {
                    SendMessage.sendMessageToSlef(ctx, Agreement.getAgreement().PromptAgreement("修炼进度已满！"));
                    return;
                }
                BigDecimal scoreType = loginResult.getScoretype(s);
                if (j == 1) {
                    BigDecimal v2 = loginResult.getExperience().divide(exp, 1, RoundingMode.HALF_DOWN);
                    BigDecimal v3 = loginResult.getGold().divide(money2, 1, RoundingMode.HALF_DOWN);
                    int min = Math.min(v2.intValue(), v3.intValue());
                    min = Math.min(min, 100);
                    int cha = roleGradeMaxFMsld.intValue() - scoreType.intValue();
                    if (exp.longValue() * (long)min > loginResult.getExperience().longValue() || v2.intValue() == 0) {
                        SendMessage.sendMessageToSlef(ctx, Agreement.getAgreement().PromptAgreement("经验不足，无法修炼！"));
                        return;
                    }
                    if (money2.longValue() * (long)min > loginResult.getGold().longValue() || v3.intValue() == 0) {
                        SendMessage.sendMessageToSlef(ctx, Agreement.getAgreement().PromptAgreement("银两不足，无法修炼"));
                        return;
                    }
                    if (cha - min >= 0) {
                        BigDecimal subtract = loginResult.getExperience().subtract(exp.multiply(new BigDecimal(min)));
                        BigDecimal subMoney = money2.multiply(BigDecimal.valueOf((long)min));
                        loginResult.setGold(loginResult.getGold().subtract(subMoney));
                        loginResult.setExperience(subtract);
                        int sld = 1 * min;
                        AssetUpdate assetUpdate3 = new AssetUpdate();
                        assetUpdate3.updata(s + "=" + sld);
                        assetUpdate3.updata("R" + loginResult.getGrade() + "=" + subtract + "=" + loginResult.getHp() + "=" + loginResult.getMp());
                        assetUpdate3.updata("D=-" + money2.longValue() * (long)min);
                        assetUpdate3.setMsg("获得了" + sld + "点熟练度！");
                        loginResult.setScore(DrawnitemsAction.Splice(loginResult.getScore(), s + "=" + sld, 2));
                        SendMessage.sendMessageToSlef(ctx, Agreement.getAgreement().assetAgreement(GsonUtil.getGsonUtil().getgson().toJson(assetUpdate3)));
                    }
                    else {
                        int sld2 = 1 * cha;
                        BigDecimal subtract2 = loginResult.getExperience().subtract(exp.multiply(new BigDecimal(cha)));
                        BigDecimal subMoney2 = money2.multiply(BigDecimal.valueOf((long)cha));
                        loginResult.setGold(loginResult.getGold().subtract(subMoney2));
                        loginResult.setExperience(subtract2);
                        AssetUpdate assetUpdate3 = new AssetUpdate();
                        assetUpdate3.updata(s + "=" + sld2);
                        assetUpdate3.updata("R" + loginResult.getGrade() + "=" + loginResult.getExperience().subtract(exp.multiply(new BigDecimal(min))) + "=" + loginResult.getHp() + "=" + loginResult.getMp());
                        assetUpdate3.updata("D=-" + money2.longValue() * (long)min);
                        assetUpdate3.setMsg("获得" + sld2 + "点熟练度");
                        loginResult.setScore(DrawnitemsAction.Splice(loginResult.getScore(), s + "=" + sld2, 2));
                        SendMessage.sendMessageToSlef(ctx, Agreement.getAgreement().assetAgreement(GsonUtil.getGsonUtil().getgson().toJson(assetUpdate3)));
                    }
                }
                else if (j == 2) {
                    if (exp.longValue() > loginResult.getExperience().longValue()) {
                        SendMessage.sendMessageToSlef(ctx, Agreement.getAgreement().PromptAgreement("经验不足，无法修炼！"));
                        return;
                    }
                    if (money2.longValue() > loginResult.getGold().longValue()) {
                        SendMessage.sendMessageToSlef(ctx, Agreement.getAgreement().PromptAgreement("银两不足，无法修炼"));
                        return;
                    }
                    int sld3 = scoreType.intValue() + 1;
                    loginResult.setGold(loginResult.getGold().subtract(money2));
                    loginResult.setExperience(loginResult.getExperience().subtract(exp));
                    AssetUpdate assetUpdate4 = new AssetUpdate();
                    assetUpdate4.updata(s + "=" + 1);
                    assetUpdate4.updata("R" + loginResult.getGrade() + "=" + loginResult.getExperience().subtract(exp) + "=" + loginResult.getHp() + "=" + loginResult.getMp());
                    assetUpdate4.updata("D=-" + money2);
                    assetUpdate4.setMsg("获得1点熟练度");
                    loginResult.setScore(DrawnitemsAction.Splice(loginResult.getScore(), s + "=" + 1, 2));
                    SendMessage.sendMessageToSlef(ctx, Agreement.getAgreement().assetAgreement(GsonUtil.getGsonUtil().getgson().toJson(assetUpdate4)));
                }
            }
        }
        else if (message.startsWith("switchEquip")) {
            String[] vs = message.split("@");
            loginResult.setEquipments(vs[1]);
        }
        else if (message.startsWith("XZ")) {
            message = message.substring(3);
            String[] v = message.trim().split("-");
            if (v.length > loginResult.getTurnAround() || v.length > 3) {
                SendMessage.sendMessageToSlef(ctx, Agreement.getAgreement().PromptAgreement("只能输入于自身相同的转生次数"));
                return;
            }
            int[] vv = getbz(v);
            if (vv == null) {
                SendMessage.sendMessageToSlef(ctx, Agreement.getAgreement().PromptAgreement("输入格式有误"));
                return;
            }
            String yuben = null;
            RoleData roleData2 = RolePool.getRoleData(loginResult.getRole_id());
            for (int k = 0; k < vv.length; ++k) {
                yuben = RoleReborn.reborn(RoleSkill.getRoleSkill().getAllSkill(vv[k], k * 5000 + 10000), yuben);
            }
            PrivateData privateData = roleData2.getPrivateData();
            privateData.setBorn(yuben);
            roleData2.upPrivateData(privateData);
            roleData2.setBorns(BaseValue.reborn(roleData2.getPrivateData().getBorn()));
            SendMessage.sendMessageToSlef(ctx, Agreement.getAgreement().PromptAgreement("你的修正已更换"));
            return;
        }
        else if (message.startsWith("zy")) {
            String[] s2 = message.split("=");
            BigDecimal zy = loginResult.getScoretype("支援");
            if (zy.intValue() == 0) {
                loginResult.setScore(DrawnitemsAction.Splice(loginResult.getScore(), "支援=" + s2[1], 2));
            }
            loginResult.setScore(DrawnitemsAction.Splice(loginResult.getScore(), "支援=" + s2[1], 1));
            zy = loginResult.getScoretype("支援");
            AssetUpdate assetUpdate5 = new AssetUpdate();
            assetUpdate5.setMsg("召唤兽支援已" + ((loginResult.getScoretype("支援").intValue() == 1) ? "开启" : "关闭"));
            assetUpdate5.updata("支援=" + zy);
            SendMessage.sendMessageToSlef(ctx, Agreement.getAgreement().assetAgreement(GsonUtil.getGsonUtil().getgson().toJson(assetUpdate5)));
        }
        else if (message.startsWith("sf")) {
            try {
                String[] s2 = message.split("=");
                BigDecimal zy = loginResult.getScoretype("首发");
                if (zy.intValue() == 0) {
                    loginResult.setScore(DrawnitemsAction.Splice(loginResult.getScore(), "首发=" + s2[1], 2));
                }
                loginResult.setScore(DrawnitemsAction.Splice(loginResult.getScore(), "首发=" + s2[1], 1));
                zy = loginResult.getScoretype("首发");
                AssetUpdate assetUpdate5 = new AssetUpdate();
                assetUpdate5.setMsg("首发召唤兽支援已" + ((loginResult.getScoretype("首发").intValue() == 1) ? "开启" : "关闭"));
                assetUpdate5.updata("首发=" + zy);
                SendMessage.sendMessageToSlef(ctx, Agreement.getAgreement().assetAgreement(GsonUtil.getGsonUtil().getgson().toJson(assetUpdate5)));
                String[] vs2 = message.split("=");
                if (vs2.length == 2 && loginResult != null) {
                    loginResult.setHp(new BigDecimal(vs2[0].substring(1)));
                    loginResult.setMp(new BigDecimal(vs2[1]));
                }
            }
            catch (Exception ex) {}
        }
        else if (message.startsWith("lzy")) {
            String[] s2 = message.split("=");
            BigDecimal zy = loginResult.getScoretype("灵宝支援");
            if (zy.intValue() == 0) {
                loginResult.setScore(DrawnitemsAction.Splice(loginResult.getScore(), "灵宝支援=" + s2[1], 2));
            }
            loginResult.setScore(DrawnitemsAction.Splice(loginResult.getScore(), "灵宝支援=" + s2[1], 1));
            zy = loginResult.getScoretype("支援");
            AssetUpdate assetUpdate5 = new AssetUpdate();
            assetUpdate5.setMsg("灵宝支援已" + ((loginResult.getScoretype("灵宝支援").intValue() == 1) ? "开启" : "关闭"));
            assetUpdate5.updata("灵宝支援=" + zy);
            SendMessage.sendMessageToSlef(ctx, Agreement.getAgreement().assetAgreement(GsonUtil.getGsonUtil().getgson().toJson(assetUpdate5)));
        }
        else if (message.startsWith("F")) {
            if (loginResult.getTroop_id() == null) {
                if (message.length() != 1) {
                    String[] vs = message.split("=");
                    loginResult.setFly_id(Integer.valueOf(Integer.parseInt(vs[0].substring(1))));
                    RolePool.getRoleData(loginResult.getRole_id()).setMid(new BigDecimal(vs[1]));
                    loginResult.setFlyskin(vs[2]);
                }
                else {
                    loginResult.setFly_id(null);
                    RolePool.getRoleData(loginResult.getRole_id()).setMid(null);
                    loginResult.setFlyskin(null);
                }
                SendMessage.sendMessageToMapRoles(loginResult.getMapid(), Agreement.getAgreement().upRoleShowAgreement(GsonUtil.getGsonUtil().getgson().toJson(loginResult.getRoleShow())));
            }
            else if (message.length() != 1) {
                String[] team = loginResult.getTeamInfo().split("\\|");
                boolean allFlysNotEmpty = true;
                for (int i = 0; i < team.length && allFlysNotEmpty; ++i) {
                    RoleTable userTable = AllServiceUtil.getRoleTableService().selectRoleTableByRoleName(team[i]);
                    List<Fly> flys = AllServiceUtil.getFlyService().selectFlysByRoleID(userTable.getRole_id());
                    if (flys == null || flys.isEmpty()) {
                        SendMessage.sendMessageToSlef(ctx, Agreement.getAgreement().PromptAgreement(team[i] + "没有飞行器！"));
                        allFlysNotEmpty = false;
                    }
                    else {
                        boolean foundNonEmptyFuel = false;
                        for (Fly fly : flys) {
                            if ((long)fly.getFuel() != 0L) {
                                foundNonEmptyFuel = true;
                                break;
                            }
                        }
                        if (!foundNonEmptyFuel) {
                            SendMessage.sendMessageToSlef(ctx, Agreement.getAgreement().PromptAgreement(team[i] + "的飞行器燃料不足"));
                            allFlysNotEmpty = false;
                        }
                    }
                }
                if (allFlysNotEmpty) {
                    for (int i = 0; i < team.length; ++i) {
                        RoleTable userTable = AllServiceUtil.getRoleTableService().selectRoleTableByRoleName(team[i]);
                        List<Fly> flys = AllServiceUtil.getFlyService().selectFlysByRoleID(userTable.getRole_id());
                        List<LoginResult> loginResults = GameServer.gameServer.getUserRole(userTable.getUser_id());
                        int l = 0;
                        while (l < flys.size()) {
                            if ((long)((Fly)flys.get(l)).getFuel() != 0L) {
                                ((LoginResult)loginResults.get(0)).setFly_id(((Fly)flys.get(l)).getFlytid());
                                RolePool.getRoleData(loginResult.getRole_id()).setMid(((Fly)flys.get(l)).getMid());
                                ((LoginResult)loginResults.get(0)).setFlyskin(((Fly)flys.get(l)).getSkin() + "");
                                break;
                            }
                            else {
                                ++l;
                            }
                        }
                        SendMessage.sendMessageToMapRoles(loginResult.getMapid(), Agreement.getAgreement().upRoleShowAgreement(GsonUtil.getGsonUtil().getgson().toJson(((LoginResult)loginResults.get(0)).getRoleShow())));
                    }
                }
            }
            else {
                String[] team = loginResult.getTeamInfo().split("\\|");
                for (int m = 0; m < team.length; ++m) {
                    RoleTable userTable2 = AllServiceUtil.getRoleTableService().selectRoleTableByRoleName(team[m]);
                    List<Fly> flys2 = AllServiceUtil.getFlyService().selectFlysByRoleID(userTable2.getRole_id());
                    List<LoginResult> loginResults2 = GameServer.gameServer.getUserRole(userTable2.getUser_id());
                    if (flys2 != null && flys2.size() > 0) {
                        for (int j2 = 0; j2 < flys2.size(); ++j2) {
                            ((LoginResult)loginResults2.get(0)).setFly_id(null);
                            RolePool.getRoleData(loginResult.getRole_id()).setMid(null);
                            ((LoginResult)loginResults2.get(0)).setFlyskin(null);
                        }
                        SendMessage.sendMessageToMapRoles(loginResult.getMapid(), Agreement.getAgreement().upRoleShowAgreement(GsonUtil.getGsonUtil().getgson().toJson(((LoginResult)loginResults2.get(0)).getRoleShow())));
                    }
                }
            }
        }
        else if (message.startsWith("M")) {
            if (message.length() != 1) {
                String[] vs = message.split("=");
                loginResult.setMount_id(Integer.valueOf(Integer.parseInt(vs[0].substring(1))));
                RolePool.getRoleData(loginResult.getRole_id()).setMid(new BigDecimal(vs[1]));
            }
            else {
                loginResult.setMount_id(null);
                RolePool.getRoleData(loginResult.getRole_id()).setMid(null);
            }
            SendMessage.sendMessageToMapRoles(loginResult.getMapid(), Agreement.getAgreement().upRoleShowAgreement(GsonUtil.getGsonUtil().getgson().toJson(loginResult.getRoleShow())));
        }
        else if (message.startsWith("CAR")) {
            if (message.length() != 3) {
                String[] vs = message.split("=");
                loginResult.setCar_id(Integer.valueOf(Integer.parseInt(vs[0].substring(3))));
                RolePool.getRoleData(loginResult.getRole_id()).setMid(new BigDecimal(vs[1]));

                Car car = AllServiceUtil.getCarService().selectMountsByMID(new BigDecimal(vs[1]));

                StringBuffer SB = new StringBuffer();
                if (car.getBone() != null && car.getBone() != 0) {
                    SB.append("根骨="+car.getBone());
                    SB.append("|");
                }
                if (car.getSpri() != null && car.getSpri() != 0) {
                    if (SB != null && SB.length()>0) {
                        SB.append("|");
                    }
                    SB.append("灵性="+car.getSpri());
                }
                if (car.getPower() != null && car.getPower() != 0) {
                    if (SB != null && SB.length()>0) {
                        SB.append("|");
                    }
                    SB.append("力量="+car.getPower());
                }
                if (car.getGradeexp() != null && car.getGradeexp() != 0) {
                    if (SB != null && SB.length()>0) {
                        SB.append("|");
                    }
                    SB.append("敏捷="+car.getGradeexp());
                }
                if (SB != null && SB.length()>0) {
                    loginResult.setCar_Value(SB.toString());
                }
            }
            else {
                loginResult.setCar_id(null);
                RolePool.getRoleData(loginResult.getRole_id()).setMid(null);
                loginResult.setCar_Value(null);
            }
            SendMessage.sendMessageToMapRoles(loginResult.getMapid(), Agreement.getAgreement().upRoleShowAgreement(GsonUtil.getGsonUtil().getgson().toJson(loginResult.getRoleShow())));
        }
        else if (message.startsWith("P")) {
            if (message.length() != 1) {
                loginResult.setSummoning_id(new BigDecimal(message.substring(1)));
            }
            else {
                loginResult.setSummoning_id(null);
            }
        }
        else if (message.startsWith("H")) {
            String[] v = message.split("=");
            if (v.length == 2) {
                loginResult.setHp(new BigDecimal(v[0].substring(1)));
                loginResult.setMp(new BigDecimal(v[1]));
            }
        }
        else if (message.startsWith("B")) {
            if (message.length() != 1) {
                loginResult.setBabyId(new BigDecimal(message.substring(1)));
            }
            else {
                loginResult.setBabyId(null);
            }
        }
        else if (message.startsWith("T")) {
            this.TYCDH(loginResult, message);
        }
        else if (message.startsWith("1")) {
            loginResult.setPassword(message.substring(1));
        }
        else if (message.startsWith("2")) {
            loginResult.setResistance(message.substring(1));
            this.GangKX(loginResult, message);
        }
        else if (message.startsWith("3")) {
            this.XWDZH(loginResult, message);
        }
        else if (message.startsWith("4")) {
            this.XWDSJ(loginResult, message);
        }
        else if (message.startsWith("5")) {
            this.SXDDH(loginResult, message);
        }
        else if (message.startsWith("6")) {
            this.GangXL(loginResult, message);
        }
        else if (message.startsWith("7")) {
            this.GangFP(loginResult, message);
        }
        else if (message.startsWith("X")) {
            this.meridians(ctx, loginResult, message);
        }
        else if (message.startsWith("Z1")) {
            this.XPJH(ctx, loginResult, message);
        }
        else if (message.startsWith("Z2")) {
            this.XPCL(ctx, loginResult, message);
        }
        else if (message.startsWith("SSH")) {
            loginResult.setSh(message.split("&")[1]);
        }
        else if (message.startsWith("Shouhu")) {
            loginResult.setJiesuo(message.split("&")[1]);
        }
        else if (message.startsWith("S")) {
            updateRoleAttribute(loginResult, message);
        }
        else if (message.startsWith("Y")) {
            this.XY(loginResult, message);
        }
        else if (message.startsWith("jiesuo")) {
            int i2 = Integer.parseInt(message.split("&")[1]);
            if (message.split("&").length >= 2) {
                Goodstable good = AllServiceUtil.getGoodsTableService().getGoodsByRgID(new BigDecimal(message.split("&")[2]));
                if (good != null) {
                    if (i2 != 1) {
                        good.goodxh(i2);
                    }
                    List<Goodstable> goodstableList = new ArrayList<>();
                    goodstableList.add(good);
                    SuitComposeAction.saveGoods(goodstableList, false);
                }
            }
            else {
                return;
            }
        }
        else if (message.startsWith("monshuanimabi")) {
            loginResult.setGold(BigDecimal.valueOf(loginResult.getGold().longValue() - Long.parseLong(message.split("&")[1])));
        }
        else if (message.startsWith("upshuanimabi")) {
            String mes = message.split("&")[1];
            Goodstable goodstable = GsonUtil.getGsonUtil().getgson().fromJson(mes, Goodstable.class);
            List<Goodstable> goodstableList = new ArrayList<>();
            goodstableList.add(goodstable);
            SuitComposeAction.saveGoods(goodstableList, false);
        }
        else if (message.split("&")[0].equals("OPENXL")) {
            RoleSummoning pet = null;
            if (message.split("&").length == 2) {
                List<RoleSummoning> pets = AllServiceUtil.getRoleSummoningService().selectRoleSummoningsByRoleID(loginResult.getRole_id());
                for (RoleSummoning pet2 : pets) {
                    if (pet2.getSummoningid().equals(message.split("&")[1])) {
                        pet = pet2;
                    }
                }
            }
            else if (message.split("&").length == 3) {
                String summoningid = message.split("&")[1];
                String sid = message.split("&")[2];
                List<RoleSummoning> pets2 = AllServiceUtil.getRoleSummoningService().selectRoleSummoningsByRoleID(loginResult.getRole_id());
                for (RoleSummoning pet3 : pets2) {
                    if (pet3.getSummoningid().equals(summoningid) && pet3.getSid().compareTo(new BigDecimal(sid)) == 0) {
                        pet = pet3;
                    }
                }
            }
            if (pet == null) {
                return;
            }
            SendMessage.sendMessageToSlef(ctx, Agreement.getAgreement().rolechangeAgreement("XY-" + pet.getXy()));
        }
        else if (message.split("&")[0].equals("OPENXY")) {
            RoleSummoning pet = null;
            if (message.split("&").length == 2) {
                List<RoleSummoning> pets = AllServiceUtil.getRoleSummoningService().selectRoleSummoningsByRoleID(loginResult.getRole_id());
                for (RoleSummoning pet2 : pets) {
                    if (pet2.getSummoningid().equals(message.split("&")[1])) {
                        pet = pet2;
                    }
                }
            }
            else if (message.split("&").length == 3) {
                String summoningid = message.split("&")[1];
                String sid = message.split("&")[2];
                List<RoleSummoning> pets2 = AllServiceUtil.getRoleSummoningService().selectRoleSummoningsByRoleID(loginResult.getRole_id());
                for (RoleSummoning pet3 : pets2) {
                    if (pet3.getSummoningid().equals(summoningid) && pet3.getSid().compareTo(new BigDecimal(sid)) == 0) {
                        pet = pet3;
                    }
                }
            }
            if (pet == null) {
                return;
            }
            SendMessage.sendMessageToSlef(ctx, Agreement.getAgreement().rolechangeAgreement("XY1-" + pet.getXy()));
        }
    }
    
    private void XY(LoginResult loginResult, String message) {
        RoleSummoning pet = null;
        if (message.split("#")[1].split("&")[0].equals("重置")) {
            if (message.split("&").length == 2) {
                List<RoleSummoning> pets = AllServiceUtil.getRoleSummoningService().selectRoleSummoningsByRoleID(loginResult.getRole_id());
                for (RoleSummoning pet2 : pets) {
                    if (pet2.getSummoningid().equals(message.split("#")[1].split("&")[1])) {
                        pet = pet2;
                    }
                }
            }
            else if (message.split("&").length == 3) {
                String summoningid = message.split("&")[1];
                String sid = message.split("&")[2];
                List<RoleSummoning> pets2 = AllServiceUtil.getRoleSummoningService().selectRoleSummoningsByRoleID(loginResult.getRole_id());
                for (RoleSummoning pet3 : pets2) {
                    if (pet3.getSummoningid().equals(summoningid) && pet3.getSid().compareTo(new BigDecimal(sid)) == 0) {
                        pet = pet3;
                    }
                }
            }
            if (pet == null) {
                return;
            }
            pet.setXy(null);
            SendMessage.sendMessageByRoleName(loginResult.getRolename(), Agreement.getAgreement().rolechangeAgreement("XY1-" + pet.getXy()));
            AllServiceUtil.getRoleSummoningService().updatePetRedis(pet);
            return;
        }
        else {
            if (message.split("#")[1].split("&")[0].equals("成圣")) {
                if (message.split("&").length == 2) {
                    List<RoleSummoning> pets = AllServiceUtil.getRoleSummoningService().selectRoleSummoningsByRoleID(loginResult.getRole_id());
                    for (RoleSummoning pet2 : pets) {
                        if (pet2.getSummoningid().equals(message.split("#")[1].split("&")[1])) {
                            pet = pet2;
                        }
                    }
                }
                else if (message.split("&").length == 3) {
                    String summoningid = message.split("&")[1];
                    String sid = message.split("&")[2];
                    List<RoleSummoning> pets2 = AllServiceUtil.getRoleSummoningService().selectRoleSummoningsByRoleID(loginResult.getRole_id());
                    for (RoleSummoning pet3 : pets2) {
                        if (pet3.getSummoningid().equals(summoningid) && pet3.getSid().compareTo(new BigDecimal(sid)) == 0) {
                            pet = pet3;
                        }
                    }
                }
                if (pet == null) {
                    return;
                }
                pet.setXy(null);
                pet.setSummoningname("一念圣猿");
                pet.setGrade(Integer.valueOf(0));
                pet.setBone(Integer.valueOf(0));
                pet.setSpir(Integer.valueOf(0));
                pet.setPower(Integer.valueOf(0));
                pet.setSpeed(Integer.valueOf(0));
                pet.setCalm(Integer.valueOf(0));
                pet.setExp(new BigDecimal(0));
                pet.setTurnRount(BattleMixDeal.petTurnRount(0));
                pet.setFriendliness(Long.valueOf(0L));
                pet.setFaithful(Integer.valueOf(100));
                pet.setGrowlevel("0.612");
                pet.setSsn("3");
                pet.setDragon(0);
                pet.setHp(pet.getHp() - pet.getSI2("hp"));
                pet.setMp(pet.getMp() - pet.getSI2("mp"));
                pet.setAp(pet.getAp() - pet.getSI2("ap"));
                pet.setSp(pet.getSp() - pet.getSI2("sp"));
                String four = pet.getFourattributes();
                four = DrawnitemsAction.Splice(four, "hp=" + pet.getSI2("hp"), 4);
                four = DrawnitemsAction.Splice(four, "mp=" + pet.getSI2("mp"), 4);
                four = DrawnitemsAction.Splice(four, "ap=" + pet.getSI2("ap"), 4);
                four = DrawnitemsAction.Splice(four, "sp=" + pet.getSI2("sp"), 4);
                pet.setFourattributes(four);
                pet.setSpdragon(0);
                pet.setHp(pet.getHp() - pet.getSI2("hps"));
                pet.setMp(pet.getMp() - pet.getSI2("mps"));
                pet.setAp(pet.getAp() - pet.getSI2("aps"));
                pet.setSp(pet.getSp() - pet.getSI2("sps"));
                String four2 = pet.getFourattributes();
                four2 = DrawnitemsAction.Splice(four2, "hps=" + pet.getSI2("hps"), 4);
                four2 = DrawnitemsAction.Splice(four2, "mps=" + pet.getSI2("mps"), 4);
                four2 = DrawnitemsAction.Splice(four2, "aps=" + pet.getSI2("aps"), 4);
                four2 = DrawnitemsAction.Splice(four2, "sps=" + pet.getSI2("sps"), 4);
                pet.setFourattributes(four2);
                pet.setCzjjd(0);
                pet.setPetSkills(null);
                pet.setPetQlSkills(null);
                pet.setHp(360);
                pet.setMp(0);
                pet.setAp(1000);
                pet.setSp(360);
                pet.setBasishp(pet.getHp());
                pet.setBasismp(pet.getMp());
                pet.setOpenSeal(Integer.valueOf(1));
                int xyjl = RoleChangeAction.random.nextInt(2);
                if (xyjl == 1) {
                    pet.setSummoningskin("500195");
                }
                else {
                    pet.setSummoningskin("500196");
                }
                pet.setSkill("25017");
                AllServiceUtil.getRoleSummoningService().updatePetRedis(pet);
                AssetUpdate assetUpdate = new AssetUpdate();
                assetUpdate.setType(AssetUpdate.USEGOOD);
                assetUpdate.setPet(pet);
                SendMessage.sendMessageByRoleName(loginResult.getRolename(), Agreement.getAgreement().assetAgreement(GsonUtil.getGsonUtil().getgson().toJson(assetUpdate)));
            }
            else {
                if (message.split("&").length == 2) {
                    List<RoleSummoning> pets = AllServiceUtil.getRoleSummoningService().selectRoleSummoningsByRoleID(loginResult.getRole_id());
                    for (RoleSummoning pet2 : pets) {
                        if (pet2.getSummoningid().equals(message.split("&")[1])) {
                            pet = pet2;
                        }
                    }
                }
                else if (message.split("&").length == 3) {
                    String summoningid = message.split("&")[1];
                    String sid = message.split("&")[2];
                    List<RoleSummoning> pets2 = AllServiceUtil.getRoleSummoningService().selectRoleSummoningsByRoleID(loginResult.getRole_id());
                    for (RoleSummoning pet3 : pets2) {
                        if (pet3.getSummoningid().equals(summoningid) && pet3.getSid().compareTo(new BigDecimal(sid)) == 0) {
                            pet = pet3;
                        }
                    }
                }
                if (pet == null) {
                    return;
                }
                pet.setXy(message.split("&")[0]);
                AllServiceUtil.getRoleSummoningService().updatePetRedis(pet);
            }
            return;
        }
    }
    
    public static BigDecimal mathDouble(double v1, double v2) {
        BigDecimal b1 = new BigDecimal(Double.toString(v1));
        BigDecimal b2 = new BigDecimal(Double.toString(v2));
        return b1.add(b2);
    }
    
    public void GangKX(LoginResult loginResult, String message) {
        String v = message.substring(1);
        if (v == null || v.length() == 0) {
            return;
        }
        String[] kx = v.split("\\|");
        if (kx.length < 2) {
            return;
        }
        if (!kx[0].startsWith("主-") || !kx[1].startsWith("副-")) {
            return;
        }
        int count = 0;
        String zhu = kx[0].substring(2);
        if (zhu.length() > 0) {
            ++count;
        }
        String fu = kx[1].substring(2);
        if (fu.length() > 0) {
            count += 2;
        }
        if (count == 0) {
            return;
        }
        RoleData data = RolePool.getRoleData(loginResult.getRole_id());
        if (data == null) {
            return;
        }
        BigDecimal achi = loginResult.getAchievement();
        BaseQl[] xls = new BaseQl[(count > 2) ? 2 : 1];
        if (count == 1 || count == 3) {
            xls[0] = new BaseQl(zhu, BaseValue.getBangQuality(achi, true));
            if (count == 3) {
                xls[1] = new BaseQl(fu, BaseValue.getBangQuality(achi, false));
            }
        }
        else {
            xls[0] = new BaseQl(fu, BaseValue.getBangQuality(achi, false));
        }
        data.setXls(40, xls);
        AssetUpdate assetUpdate = new AssetUpdate(AssetUpdate.USEGOOD);
        assetUpdate.setResistance(v);
        String msg = Agreement.getAgreement().assetAgreement(GsonUtil.getGsonUtil().getgson().toJson(assetUpdate));
        SendMessage.sendMessageByRoleName(loginResult.getRolename(), msg);
    }
    
    public void GangFP(LoginResult loginResult, String message) {
        String V = message.substring(1, 2);
        int type = V.equals("X") ? 1 : (V.equals("D") ? 2 : 0);
        if (type == 0) {
            return;
        }
        RoleData data = RolePool.getRoleData(loginResult.getRole_id());
        if (data == null) {
            return;
        }
        BaseQl[] qls = data.getXls(type);
        if (message.length() == 2) {
            if (qls == null) {
                SendMessage.sendMessageByRoleName(loginResult.getRolename(), Agreement.getAgreement().PromptAgreement("你还没加点怎么洗点"));
                return;
            }
            long money = 500000L;
            if (money > loginResult.getGold().longValue()) {
                SendMessage.sendMessageByRoleName(loginResult.getRolename(), Agreement.getAgreement().PromptAgreement("金钱不足" + money));
                return;
            }
            data.setXls(type, (BaseQl[])null);
            AssetUpdate assetUpdate = new AssetUpdate(AssetUpdate.USEGOOD);
            loginResult.setGold(new BigDecimal(loginResult.getGold().longValue() - money));
            assetUpdate.updata("D=-" + money);
            assetUpdate.setResistance(loginResult.setResistance(type, null));
            String msg = Agreement.getAgreement().assetAgreement(GsonUtil.getGsonUtil().getgson().toJson(assetUpdate));
            SendMessage.sendMessageByRoleName(loginResult.getRolename(), msg);
            return;
        }
        else {
            int max = loginResult.getExtraPointInt(V);
            String kx = message.substring(2);
            String[] v;
            for (String s1 : v = kx.split("\\|")) {
                String[] v2;
                for (String string : v2 = s1.split("#")) {
                    String[] split = string.split("=");
                    if (split.length == 2) {
                        double d = Double.parseDouble(split[1]);
                        if (d > 40.0) {
                            SendMessage.sendMessageByRoleName(loginResult.getRolename(), Agreement.getAgreement().PromptAgreement("分配方案异常"));
                            return;
                        }
                    }
                }
            }
            BaseQl[] xls = BaseValue.isXls(qls, kx.split("#"), max, type);
            if (xls == null) {
                SendMessage.sendMessageByRoleName(loginResult.getRolename(), Agreement.getAgreement().PromptAgreement("分配方案异常"));
                return;
            }
            data.setXls(type, xls);
            AssetUpdate assetUpdate2 = new AssetUpdate(AssetUpdate.USEGOOD);
            assetUpdate2.setResistance(loginResult.setResistance(type, kx));
            String msg2 = Agreement.getAgreement().assetAgreement(GsonUtil.getGsonUtil().getgson().toJson(assetUpdate2));
            SendMessage.sendMessageByRoleName(loginResult.getRolename(), msg2);
            return;
        }
    }
    
    public void GangXL(LoginResult loginResult, String message) {
        String V = message.substring(1, 2);
        int type = V.equals("X") ? 1 : (V.equals("D") ? 2 : 0);
        if (type == 0) {
            return;
        }
        int E = loginResult.getExtraPointInt(V) + 1;
        if (E > ((type == 1) ? 30 : 60)) {
            SendMessage.sendMessageByRoleName(loginResult.getRolename(), Agreement.getAgreement().PromptAgreement("修炼等级达到上限"));
            return;
        }
        if (type == 2) {
            if (loginResult.getExtraPointInt("X") < 30) {
                SendMessage.sendMessageByRoleName(loginResult.getRolename(), Agreement.getAgreement().PromptAgreement("小成修炼完才能进行大成修炼"));
                return;
            }
            if ((int)loginResult.getGrade() < 419) {
                SendMessage.sendMessageByRoleName(loginResult.getRolename(), Agreement.getAgreement().PromptAgreement("3转140级才能开始进行大成修炼"));
                return;
            }
        }
        long money = (type == 1) ? 2000000L : 5000000L;
        long exp = (type == 1) ? 2000000L : 5000000L;
        long contribution = (type == 1) ? 300L : 1500L;
        if (money > loginResult.getGold().longValue()) {
            SendMessage.sendMessageByRoleName(loginResult.getRolename(), Agreement.getAgreement().PromptAgreement("金钱不足" + money));
            return;
        }
        if (exp > loginResult.getExperience().longValue()) {
            SendMessage.sendMessageByRoleName(loginResult.getRolename(), Agreement.getAgreement().PromptAgreement("经验不足" + exp));
            return;
        }
        if (contribution > loginResult.getContribution().longValue()) {
            SendMessage.sendMessageByRoleName(loginResult.getRolename(), Agreement.getAgreement().PromptAgreement("金钱不足" + contribution));
            return;
        }
        AssetUpdate assetUpdate = new AssetUpdate(AssetUpdate.USEGOOD);
        assetUpdate.setMsg("你的修炼等级提升一级");
        loginResult.setGold(new BigDecimal(loginResult.getGold().longValue() - money));
        assetUpdate.updata("D=-" + money);
        loginResult.setExperience(new BigDecimal(loginResult.getExperience().longValue() - exp));
        assetUpdate.updata("R" + loginResult.getGrade() + "=" + loginResult.getExperience());
        loginResult.setContribution(new BigDecimal(loginResult.getContribution().longValue() - contribution));
        assetUpdate.updata("B=-" + contribution);
        loginResult.setExtraPoint(V, 1);
        assetUpdate.updata("E" + V + "=" + E);
        String msg = Agreement.getAgreement().assetAgreement(GsonUtil.getGsonUtil().getgson().toJson(assetUpdate));
        SendMessage.sendMessageByRoleName(loginResult.getRolename(), msg);
    }
    
    public void SXDDH(LoginResult loginResult, String message) {
        int E = loginResult.getExtraPointInt("F") + 1;
        if (E > (int)loginResult.getXiuwei()) {
            SendMessage.sendMessageByRoleName(loginResult.getRolename(), Agreement.getAgreement().PromptAgreement("修为点不够兑换"));
            return;
        }
        if (E >= 61) {
            SendMessage.sendMessageByRoleName(loginResult.getRolename(), Agreement.getAgreement().PromptAgreement("属性点兑换达到上限"));
            return;
        }
        loginResult.setXiuwei(Integer.valueOf((int)loginResult.getXiuwei() - E));
        loginResult.setExtraPoint("F", 1);
    }
    
    public void XWDSJ(LoginResult loginResult, String message) {
        int lvl = BattleMixDeal.lvlint((int)loginResult.getGrade());
        int max = 200;
        if (loginResult.getGradeincrease() != null && (int)loginResult.getGradeincrease() > 0) {
            max += (int)loginResult.getGradeincrease();
        }
        if (lvl >= max) {
            SendMessage.sendMessageByRoleName(loginResult.getRolename(), Agreement.getAgreement().PromptAgreement("已达到等级上限"));
            return;
        }
        int sxXW = xiuwei(lvl);
        if (sxXW > (int)loginResult.getXiuwei()) {
            SendMessage.sendMessageByRoleName(loginResult.getRolename(), Agreement.getAgreement().PromptAgreement("不够修为点升级"));
            return;
        }
        loginResult.setXiuwei(Integer.valueOf((int)loginResult.getXiuwei() - sxXW));
        ExpUtil.increasePointAndValue(loginResult);
    }
    
    public void XWDZH(LoginResult loginResult, String message) {
        int num = Integer.parseInt(message.substring(1));
        if (num > 10) {
            num = 10;
        }
        long exp = loginResult.getExperience().longValue();
        int allowConversionNum = (int)(exp / 1000000000L);
        if (allowConversionNum == 0 || num > allowConversionNum) {
            SendMessage.sendMessageByRoleName(loginResult.getRolename(), Agreement.getAgreement().PromptAgreement("当前经验不足"));
            return;
        }
        int up = xwUP(BattleMixDeal.lvlint((int)loginResult.getGrade()));
        if ((int)loginResult.getXiuwei() >= up || (int)loginResult.getXiuwei() + num > up) {
            SendMessage.sendMessageByRoleName(loginResult.getRolename(), Agreement.getAgreement().PromptAgreement("修为点以达到上限,无法继续转换"));
            return;
        }
        exp -= 1000000000L * (long)num;
        loginResult.setExperience(new BigDecimal(exp));
        loginResult.setXiuwei(Integer.valueOf((int)loginResult.getXiuwei() + num));
    }
    
    public void TYCDH(LoginResult loginResult, String message) {//天演测修改
        int num = Integer.parseInt(message.substring(1));
        if (num <= 0) {
            return;
        }
        long gold = loginResult.getGold().longValue();
        long exp = loginResult.getExperience().longValue();
        gold -= (long)num * 2000000L;
        exp -= (long)num * 5000000L;
        if (gold < 0L) {
            SendMessage.sendMessageByRoleName(loginResult.getRolename(), Agreement.getAgreement().PromptAgreement("金钱不足"));
            return;
        }
        if (exp < 0L) {
            SendMessage.sendMessageByRoleName(loginResult.getRolename(), Agreement.getAgreement().PromptAgreement("经验不足"));
            return;
        }
        loginResult.setGold(new BigDecimal(gold));
        loginResult.setExperience(new BigDecimal(exp));
        loginResult.setExtraPoint("T", num);
        MonitorUtil.getMoney().useD((long)num * 750000L);
    }
    
    public void DDJD(LoginResult loginResult, String message) {
        String[] vs = message.split("=");
        if (vs.length != 5 && vs.length != 6) {
            return;
        }
        int lvl = BattleMixDeal.lvlint((int)loginResult.getGrade());
        int trun = BattleMixDeal.lvltrue((int)loginResult.getGrade());
        int gg = Integer.parseInt(vs[0].substring(1));
        int lx = Integer.parseInt(vs[1]);
        int ll = Integer.parseInt(vs[2]);
        int mj = Integer.parseInt(vs[3]);
        int dl = Integer.parseInt(vs[4]);
        if (lvl > gg || lvl > lx || lvl > ll || lvl > mj || (trun == 4 && lvl > dl)) {
            System.out.println("修改点数异常:" + message);
            return;
        }
        if (0 > gg || 0 > lx || 0 > ll || 0 > mj || 0 > dl) {
            System.out.println("修改点数异常:" + message);
            return;
        }

        int canpoint = lvl * 8;
        if (trun < 4) {
            canpoint += trun * 60;
        }
        else {
            canpoint += 180 + lvl;
        }
        canpoint += loginResult.getExtraPointInt();
        canpoint += loginResult.getExtPointInt();
        canpoint -= gg;
        canpoint -= lx;
        canpoint -= ll;
        canpoint -= mj;
        canpoint -= dl;

        if (canpoint < 0) {
            System.out.println("修改点数异常:" + message + ":" + canpoint);
            return;
        }
        loginResult.setBone(gg);
        loginResult.setSpir(lx);
        loginResult.setPower(ll);
        loginResult.setSpeed(mj);
        loginResult.setCalm(dl);
        if (vs.length > 5) {//修复切属性报错
            int attribute = Integer.parseInt(vs[5]);
            loginResult.setCurrentattribute(attribute);
        }
        int max = Arrays.stream(new int[]{gg, lx, ll, mj}).max().orElseThrow(NoSuchElementException::new);
        int[] thresholds = {100, 200, 500, 1000};
        String[] achievements = {"单项100点", "单项200点", "单项500点", "单项1000点"};
        for (int i = 0; i < thresholds.length; i++) {
            if (max >= thresholds[i]) {
                AchievemUtil.detectionAchievem(loginResult, achievements[i]);
            }
        }
    }
    
    public static int xiuwei(int lvl) {
        if (lvl < 150) {
            return 6;
        }
        return lvl - 144;
    }
    
    public static int xwUP(int lvl) {
        return xiuwei(lvl) * 5;
    }
    
    void meridians(ChannelHandlerContext ctx, LoginResult loginResult, String message) {
        SendMessage.sendMessageToSlef(ctx, Agreement.getAgreement().PromptAgreement("功能未开放！"));
    }
    
    void saveAndSendMeridians(ChannelHandlerContext ctx, LoginResult loginResult, List<BaseMeridians> list, BaseMeridians bm) {
        String newstr = BaseMeridians.createBaseMeridiansString(list);
        loginResult.setMeridians(newstr);
        AllServiceUtil.getMeridiansService().saveMeridians(Long.valueOf(loginResult.getRole_id().longValue()), newstr);
        String msg = Agreement.getAgreement().rolechangeAgreement(bm.toString());
        SendMessage.sendMessageToSlef(ctx, msg);
    }
    
    public void XPCL(ChannelHandlerContext ctx, LoginResult loginResult, String message) {
        if (StringUtil.isNullOrEmpty(message)) {
            return;
        }
        String[] values = message.split("=");
        if (values.length < 3) {
            return;
        }
        if (StringUtil.isNullOrEmpty(values[1]) || !NumberUtils.isDigits(values[1])) {
            return;
        }
        List<Goodstable> good1 = AllServiceUtil.getGoodsTableService().selectGoodsByRoleIDAndGoodsID(loginResult.getRole_id(), BigDecimal.valueOf((long)(51000 + Integer.parseInt(values[1]) - 1)));
        if (good1 == null || good1.size() == 0 || good1.get(0) == null) {
            SendMessage.sendMessageByRoleName(loginResult.getRolename(), Agreement.getAgreement().PromptAgreement("道具不足"));
            return;
        }
        if (StringUtil.isNullOrEmpty(values[2]) || !NumberUtils.isDigits(values[2])) {
            return;
        }
        List<Goodstable> good2 = AllServiceUtil.getGoodsTableService().selectGoodsByRoleIDAndGoodsID(loginResult.getRole_id(), BigDecimal.valueOf((long)(51000 + Integer.parseInt(values[2]) - 1)));
        if (good2 == null || good2.size() == 0 || good2.get(0) == null) {
            SendMessage.sendMessageByRoleName(loginResult.getRolename(), Agreement.getAgreement().PromptAgreement("道具不足"));
            return;
        }
        Goodstable goodstable1 = (Goodstable)good1.get(0);
        Goodstable goodstable2 = (Goodstable)good2.get(0);
        goodstable1.goodxh(1);
        goodstable2.goodxh(1);
        AssetUpdate assetUpdate = new AssetUpdate(AssetUpdate.USEGOOD);
        assetUpdate.updata("G" + goodstable1.getRgid() + "=" + goodstable1.getUsetime());
        assetUpdate.updata("G" + goodstable2.getRgid() + "=" + goodstable2.getUsetime());
        AllServiceUtil.getGoodsTableService().updateGoodRedis(goodstable1);
        AllServiceUtil.getGoodsTableService().updateGoodRedis(goodstable2);
        SendMessage.sendMessageToSlef(ctx, Agreement.getAgreement().assetAgreement(GsonUtil.getGsonUtil().getgson().toJson(assetUpdate)));
        int id = GameServer.random.nextInt(36);
        Goodstable good3 = GameServer.getGood(BigDecimal.valueOf((long)(51000 + id)));
        good3.setRole_id(loginResult.getRole_id());
        AllServiceUtil.getGoodsTableService().insertGoods(good3);
        AddGoodUtil.addGood(ctx, good3);
        String msg = Agreement.getAgreement().skillxplearnAgreement("H" + id);
        SendMessage.sendMessageByRoleName(loginResult.getRolename(), msg);
    }
    
    public void XPJH(ChannelHandlerContext ctx, LoginResult loginResult, String message) {
        if (StringUtil.isNullOrEmpty(message)) {
            return;
        }
        String[] values = message.split("=");
        if (values.length < 3) {
            return;
        }
        if (StringUtil.isNullOrEmpty(values[1]) || !NumberUtils.isDigits(values[1])) {
            return;
        }
        int id = Integer.parseInt(values[1]);
        if (StringUtil.isNullOrEmpty(values[2]) || !NumberUtils.isDigits(values[2])) {
            return;
        }
        String num1 = values[2];
        BigDecimal goodsId = BigDecimal.valueOf((long)(51000 + Integer.parseInt(num1) - 1));
        BaseXingpans xingpans = loginResult.getXingpans(id);
        if (xingpans != null && xingpans.getExp().contains(num1)) {
            SendMessage.sendMessageByRoleName(loginResult.getRolename(), Agreement.getAgreement().PromptAgreement("#R你已经激活过了"));
            return;
        }
        List<Goodstable> good = null;
        if (goodsId != null) {
            good = AllServiceUtil.getGoodsTableService().selectGoodsByRoleIDAndGoodsID(loginResult.getRole_id(), goodsId);
        }
        if (good == null || good.size() == 0 || good.get(0) == null) {
            SendMessage.sendMessageByRoleName(loginResult.getRolename(), Agreement.getAgreement().PromptAgreement("道具不足"));
            return;
        }
        Goodstable goodstable = (Goodstable)good.get(0);
        goodstable.goodxh(1);
        AssetUpdate assetUpdate = new AssetUpdate(AssetUpdate.USEGOOD);
        assetUpdate.setData("G" + goodstable.getRgid() + "=" + goodstable.getUsetime());
        SendMessage.sendMessageToSlef(ctx, Agreement.getAgreement().assetAgreement(GsonUtil.getGsonUtil().getgson().toJson(assetUpdate)));
        AllServiceUtil.getGoodsTableService().updateGoodRedis(goodstable);
        if (xingpans == null) {
            xingpans = new BaseXingpans(id, num1);
        }
        else {
            xingpans.setExp(xingpans.getExp() + "=" + num1);
        }
        loginResult.setXingpans(id, xingpans);
        String msg = Agreement.getAgreement().skillxplearnAgreement(GsonUtil.getGsonUtil().getgson().toJson(xingpans.toString()));
        SendMessage.sendMessageByRoleName(loginResult.getRolename(), msg);
        SendMessage.sendMessageByRoleName(loginResult.getRolename(), Agreement.getAgreement().PromptAgreement("#Y激活星魂"));
    }
    
    public static void useGood(Goodstable good, int sum) {
        good.goodxh(sum);
        AllServiceUtil.getGoodsrecordService().insert(good, null, Integer.valueOf(1), Integer.valueOf(9));
        AllServiceUtil.getGoodsTableService().updateGoodRedis(good);
    }
    
    public Alchemy randXP(int num) {
        List<Alchemy> alchemies = (List<Alchemy>)GameServer.getAllAlchemy().get(num + "号星盘");
        if (alchemies.size() > 0) {
            int i = new Random().nextInt(alchemies.size());
            return (Alchemy)alchemies.get(i);
        }
        return null;
    }
    
    public static void updateRoleAttribute(LoginResult loginResult, String message) {
        String[] dataVs = message.split(",");
        String[] vs = dataVs[0].split("=");
        if (vs.length != 6) {
            return;
        }
        int lvl = BattleMixDeal.lvlint((int)loginResult.getGrade());
        int trun = BattleMixDeal.lvltrue((int)loginResult.getGrade());
        int gg = Integer.parseInt(vs[0].substring(1));
        int lx = Integer.parseInt(vs[1]);
        int ll = Integer.parseInt(vs[2]);
        int mj = Integer.parseInt(vs[3]);
        int dl = 0;
        if (vs[4] != null && !vs[4].equals("null")) {
            dl = Integer.parseInt(vs[4]);
        }
        if (0 > gg || 0 > lx || 0 > ll || 0 > mj || 0 > dl) {
            System.out.println("修改点数异常:" + message);
            return;
        }

        String name1 = vs[5];
        if (lvl > gg || lvl > lx || lvl > ll || lvl > mj || (trun == 4 && lvl > dl)) {
            System.out.println("修改点数异常:" + message);
            return;
        }
        String[] vs2 = dataVs[1].split("=");
        if (vs2.length != 6) {
            return;
        }
        int lvl2 = BattleMixDeal.lvlint((int)loginResult.getGrade());
        int trun2 = BattleMixDeal.lvltrue((int)loginResult.getGrade());
        int gg2 = Integer.parseInt(vs2[0].substring(1));
        int lx2 = Integer.parseInt(vs2[1]);
        int ll2 = Integer.parseInt(vs2[2]);
        int mj2 = Integer.parseInt(vs2[3]);
        int dl2 = 0;
        if (vs2[4] != null && !vs[4].equals("null")) {
            dl2 = Integer.parseInt(vs2[4]);
        }
        if (0 > gg2 || 0 > lx2 || 0 > ll2 || 0 > mj2 || 0 > dl2) {
            System.out.println("修改点数异常:" + message);
            return;
        }
        String name2 = vs2[5];
        if (lvl2 > gg2 || lvl2 > lx2 || lvl2 > ll2 || lvl2 > mj2 || (trun2 == 4 && lvl2 > dl2)) {
            System.out.println("修改点数异常:" + message);
            return;
        }
        RoleAttribute roleAttribute = AllServiceUtil.getRoleTableService().selectRoleAttributeRoleId(loginResult.getRole_id());
        if (roleAttribute != null) {
            roleAttribute.setBONEONE(Integer.valueOf(gg));
            roleAttribute.setSPIRONE(Integer.valueOf(lx));
            roleAttribute.setPOWERONE(Integer.valueOf(ll));
            roleAttribute.setSPEEDONE(Integer.valueOf(mj));
            roleAttribute.setCALMONE(Integer.valueOf(dl));
            roleAttribute.setATTRIBUTENAMEONE(name1);
            roleAttribute.setBONETWO(Integer.valueOf(gg2));
            roleAttribute.setSPIRTWO(Integer.valueOf(lx2));
            roleAttribute.setPOWERTWO(Integer.valueOf(ll2));
            roleAttribute.setSPEEDTWO(Integer.valueOf(mj2));
            roleAttribute.setCALMTWO(Integer.valueOf(dl2));
            roleAttribute.setATTRIBUTENAMETWO(name2);
            AllServiceUtil.getRoleTableService().updateRoleAttributeRoleId(roleAttribute);
        }
        else {
            roleAttribute = new RoleAttribute();
            roleAttribute.setROLE_ID(Integer.valueOf(Integer.parseInt(loginResult.getRole_id().toString())));
            roleAttribute.setBONEONE(Integer.valueOf(gg));
            roleAttribute.setSPIRONE(Integer.valueOf(lx));
            roleAttribute.setPOWERONE(Integer.valueOf(ll));
            roleAttribute.setSPEEDONE(Integer.valueOf(mj));
            roleAttribute.setCALMONE(Integer.valueOf(dl));
            roleAttribute.setATTRIBUTENAMEONE(name1);
            roleAttribute.setBONETWO(Integer.valueOf(gg2));
            roleAttribute.setSPIRTWO(Integer.valueOf(lx2));
            roleAttribute.setPOWERTWO(Integer.valueOf(ll2));
            roleAttribute.setSPEEDTWO(Integer.valueOf(mj2));
            roleAttribute.setCALMTWO(Integer.valueOf(dl2));
            roleAttribute.setATTRIBUTENAMETWO(name2);
            AllServiceUtil.getRoleTableService().insertRoleAttribute(roleAttribute);
        }
    }
    public static void baoExp(XuanBao lingbao, ChannelHandlerContext ctx, long addexp, LoginResult login, int type, Goodstable good) {
        if (addexp <= 0) {
            return;
        }
        AssetUpdate assetUpdate = new AssetUpdate();
        assetUpdate.setType(AssetUpdate.USEGOOD);
        if (lingbao.lvl >= 200) {
            SendMessage.sendMessageByRoleName(login.getRolename(), Agreement.getAgreement().PromptAgreement("玄宝等级已满"));
        } else {
            if (good == null && addexp != 1000L && addexp != 2000L) {
                long needmoney = addexp * 600;
                if (login.getGold().longValue() < needmoney) {
                    SendMessage.sendMessageByRoleName(login.getRolename(), Agreement.getAgreement().PromptAgreement("金币不足，一次需要+#R" + needmoney + "#Y金币"));
                    return;
                }
                if (login.getExperience().longValue() < 30000000L) {
                    SendMessage.sendMessageByRoleName(login.getRolename(), Agreement.getAgreement().PromptAgreement("经验不足"));
                    return;
                }
                login.setGold(new BigDecimal(login.getGold().longValue() - needmoney));
                login.setExperience(new BigDecimal(login.getExperience().longValue() - 30000000L));
                assetUpdate.updata("R" + login.getGrade() + "=" + login.getExperience());
            }
            if (good != null) {
                useGood(good, 1);
                assetUpdate.updata("G" + good.getRgid() + "=" + good.getUsetime());
            }

            String msg = ExpUtil.XBExp(lingbao, addexp);
            lingbao.setLvl(Math.min(lingbao.getLvl(), BattleMixDeal.lvlint(login.getGrade())));
            AllServiceUtil.getXuanBaoService().updateLingbaoRedis(lingbao);
            assetUpdate.setXuanBao(lingbao);
            assetUpdate.setMsg(msg);
            SendMessage.sendMessageToSlef(ctx, Agreement.getAgreement().assetAgreement(GsonUtil.getGsonUtil().getgson().toJson(assetUpdate)));
        }
    }
    public static int[] getbz(String[] v) {
        try {
            int[] a = new int[v.length];
            for (int i = 0; i < v.length; ++i) {
                a[i] = Integer.parseInt(v[i]);
                if (a[i] < 1 || a[i] > 10) {
                    return null;
                }
            }
            return a;
        }
        catch (Exception ex) {
            return null;
        }
    }
    
    public BigDecimal getRoleGradeMaxFMsld(int grade) {
        if (grade >= 500) {
            return new BigDecimal(10000);
        }
        if (grade >= 490) {
            return new BigDecimal(8000);
        }
        if (grade >= 480) {
            return new BigDecimal(6000);
        }
        if (grade >= 470) {
            return new BigDecimal(4000);
        }
        if (grade >= 460) {
            return new BigDecimal(2000);
        }
        return new BigDecimal(2000);
    }
    
    static {
        RoleChangeAction.random = new Random();
    }
}
