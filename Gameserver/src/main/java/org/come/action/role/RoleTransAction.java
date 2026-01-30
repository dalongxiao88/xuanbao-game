package org.come.action.role;

import java.util.List;

import org.come.entity.*;
import org.come.model.TaskData;
import come.tool.Role.RoleData;
import org.come.bean.RoleTransBean;
import come.tool.Battle.BattleMixDeal;
import come.tool.Calculation.BaseValue;
import come.tool.Calculation.RoleReborn;
import org.come.action.monitor.MonitorUtil;
import org.come.until.AchievemUtil;
import org.come.until.GsonUtil;
import come.tool.Stall.AssetUpdate;
import org.come.until.AllServiceUtil;
import org.come.handler.SendMessage;
import org.come.protocol.Agreement;
import come.tool.FightingData.Sepcies_MixDeal;
import java.math.BigDecimal;
import come.tool.Role.RolePool;
import org.come.server.GameServer;
import org.come.bean.LoginResult;
import io.netty.channel.ChannelHandlerContext;
import org.come.action.IAction;
/**
 * 人物转换角色，修改角色种族和基础成长
 * @author 叶豪芳
 * @date 2018年1月10日 下午2:56:59
 *
 */
public class RoleTransAction implements IAction
{
    @Override
    public void action(ChannelHandlerContext ctx, String message) {
        LoginResult loginResult = GameServer.getAllLoginRole().get(ctx);
        RoleData roleData = RolePool.getRoleData(loginResult.getRole_id());
        int zhuantype = Integer.parseInt(message.substring(0, 1));
        BigDecimal species_id = new BigDecimal(message.substring(2));
        Species species = Sepcies_MixDeal.getSpecies(species_id);
        Goodstable goodstable = null;
        if (species_id.compareTo(loginResult.getSpecies_id()) != 0 && roleData.getPrivateData().isSkill("F")) {
            SendMessage.sendMessageByRoleName(loginResult.getRolename(), Agreement.getAgreement().PromptAgreement("换角色需要清空法门"));
            return;
        }
        if (species_id.compareTo(loginResult.getSpecies_id()) != 0 && roleData.getPrivateData().isSkill("T")) {
            SendMessage.sendMessageByRoleName(loginResult.getRolename(), Agreement.getAgreement().PromptAgreement("换角色需要清空天演策"));
            return;
        }
        if (zhuantype == 0) {
            if (loginResult.getGold().compareTo(new BigDecimal(100000)) < 0) {
                SendMessage.sendMessageByRoleName(loginResult.getRolename(), Agreement.getAgreement().PromptAgreement("金钱不足 10W 两！"));
                return;
            }
        }
        else {
            int grade = 0;
            if ((int)loginResult.getGrade() == 102) {
                grade = 1;
            }
            else if ((int)loginResult.getGrade() == 210) {
                grade = 2;
            }
            else if ((int)loginResult.getGrade() == 338) {
                grade = 3;
            }
            else if ((int)loginResult.getGrade() == 459) {
                grade = 4;
            }
            else {
                SendMessage.sendMessageByRoleName(loginResult.getRolename(), Agreement.getAgreement().PromptAgreement("等级不满足条件"));
                return;
            }
            if (grade == 4) {
                TaskData taskData = GameServer.getTaskName("天地劫");
                if (taskData != null && roleData.getTaskWC(taskData.getTaskSetID()) < 1) {
                    SendMessage.sendMessageByRoleName(loginResult.getRolename(), Agreement.getAgreement().PromptAgreement("还未完成天地劫任务"));
                    return;
                }
            }
            List<Goodstable> goods = AllServiceUtil.getGoodsTableService().selectGoodsByRoleIDAndGoodsID(loginResult.getRole_id(), new BigDecimal(192));
            if (goods.size() == 0) {
                SendMessage.sendMessageByRoleName(loginResult.getRolename(), Agreement.getAgreement().PromptAgreement("你并没有携带冥钞，速去准备吧！"));
                return;
            }
            goodstable = (Goodstable)goods.get(0);
        }
        if (goodstable != null) {
            goodstable.goodxh(1);
            AssetUpdate assetUpdate = new AssetUpdate(AssetUpdate.USEGOOD);
            assetUpdate.setData("G" + goodstable.getRgid() + "=" + goodstable.getUsetime());
            SendMessage.sendMessageToSlef(ctx, Agreement.getAgreement().assetAgreement(GsonUtil.getGsonUtil().getgson().toJson(assetUpdate)));
            AllServiceUtil.getGoodsTableService().updateGoodRedis(goodstable);
        }
        else {
            loginResult.setGold(new BigDecimal(loginResult.getGold().longValue() - 100000L));
            MonitorUtil.getMoney().useD(100000L);
        }
        boolean isM = species.getRace_id().compareTo(loginResult.getRace_id()) != 0;
        loginResult.setSex(species.getSex());
        loginResult.setLocalname(species.getLocalname());
        loginResult.setRace_id(species.getRace_id());
        loginResult.setSpecies_id(species.getSpecies_id());
        loginResult.setRace_name(Sepcies_MixDeal.getRaceString(species.getSpecies_id()));
        loginResult.setLiangHao(loginResult.getLiangHao());
        if (zhuantype == 0) {
            roleData.getPrivateData().setSkills("S", changeSkill(roleData.getPrivateData().getSkill("S"), species_id));
            roleData.getPrivateData().setSkills("F", null);
        }
        else {
            loginResult.setExperience(new BigDecimal(0));
            loginResult.setGrade(Integer.valueOf((int)loginResult.getGrade() + 1));
            loginResult.setTurnAround(loginResult.getTurnAround() + 1);
            if (loginResult.getAttachPack() > 2) {
                loginResult.setAttachPack(2);
            }
            roleData.setGoodMax(24 + (loginResult.getAttachPack() + Math.min(loginResult.getTurnAround(), 4)) * 24);
            if (loginResult.getTurnAround() <= 3) {
                String v = RoleReborn.reborn(roleData.getPrivateData().getSkill("S"), roleData.getPrivateData().getBorn());
                roleData.getPrivateData().setSkills(null);
                roleData.getPrivateData().setSkills("F", null);
                roleData.getPrivateData().setBorn(v);
                roleData.setBorns(BaseValue.reborn(roleData.getPrivateData().getBorn()));
            }
            else {
                roleData.getPrivateData().setSkills("S", changeSkill(roleData.getPrivateData().getSkill("S"), species_id));
                roleData.getPrivateData().setSkills("F", null);
            }
            if(loginResult.getTurnAround() == 1) {
                AchievemUtil.detectionAchievem(loginResult, "人物1转");
            }else if(loginResult.getTurnAround() == 2) {
                AchievemUtil.detectionAchievem(loginResult, "人物2转");
            }else if(loginResult.getTurnAround() == 3) {
                AchievemUtil.detectionAchievem(loginResult, "人物3转");
            }
        }
        roleData.setSkills(BaseValue.reSkill(roleData.getPrivateData(), loginResult));
        int lvl = BattleMixDeal.lvlint((int)loginResult.getGrade());
        loginResult.setBone(lvl);
        loginResult.setSpir(lvl);
        loginResult.setPower(lvl);
        loginResult.setSpeed(lvl);
        if (loginResult.getTurnAround() == 4) {
            loginResult.setCalm(lvl);
        }
        loginResult.setHp(new BigDecimal(0));
        loginResult.setMp(new BigDecimal(0));
        SendMessage.sendMessageToMapRoles(ctx, loginResult.getMapid(), Agreement.getAgreement().upRoleShowAgreement(GsonUtil.getGsonUtil().getgson().toJson(loginResult.getRoleShow())));
        RoleTransBean roleTransBean = new RoleTransBean();
        roleTransBean.setLoginResult(loginResult);
        roleTransBean.setPrivateData(roleData.getPrivateData());
        if (isM) {
            List<Mount> mounts = AllServiceUtil.getMountService().selectMountsByRoleID(loginResult.getRole_id());
            for (int i = 0; i < mounts.size(); ++i) {
                Mount mount = (Mount)mounts.get(i);
                Mount mount2 = GameServer.getMount(Sepcies_MixDeal.getRace(loginResult.getSpecies_id()), (int)mount.getMountid());
                if (mount2 != null) {
                    mount.setMountname(mount2.getMountname());
                    AllServiceUtil.getMountService().updateMountRedis(mount);
                }
            }
            roleTransBean.setMounts(mounts);
        }
        List<Car> cars = AllServiceUtil.getCarService().selectMountsByRoleID(loginResult.getRole_id());
        if (cars != null && cars.size()>0) {
            for (int i = 0; i < cars.size(); ++i) {
                Car mount = (Car)cars.get(i);
                Car mount2 = GameServer.getCar(loginResult.getSpecies_id().intValue(), (int)mount.getMountid());
                if (mount2 != null) {
                    mount.setMountname(mount2.getMountname());
                    mount.setBone(mount2.getBone());
                    mount.setPower(mount2.getPower());
                    mount.setSpri(mount2.getSpri());
                    mount.setGradeexp(mount2.getGradeexp());
                    AllServiceUtil.getCarService().updateMountRedis(mount);
                }
            }
            roleTransBean.setCars(cars);
        }


        int Turn = 0;
        if (lvl <= 100) {
            Turn = 0;
        }
        else if (lvl <= 221) {
            Turn = 1;
        }
        else if (lvl <= 362) {
            Turn = 2;
        }
        else {
            Turn = ((lvl <= 543) ? 3 : 4);
        }
        int lvl2 = lvl;
        if (lvl <= 100) {
            lvl2 = lvl;
        }
        else if (lvl <= 221) {
            lvl2 = lvl - 101;
        }
        else if (lvl <= 362) {
            lvl2 = lvl - 222;
        }
        else {
            lvl2 = ((lvl <= 543) ? (lvl - 363) : (lvl - 544));
        }
        int canpoint = lvl2 * 8;
        if (Turn >= 4) {
            canpoint += lvl2;
        }
        canpoint += Turn * 30;
        canpoint -= (int)loginResult.getBone();
        canpoint -= (int)loginResult.getSpir();
        canpoint -= (int)loginResult.getPower();
        canpoint -= (int)loginResult.getSpeed();
        canpoint -= (int)loginResult.getCalm();
        AllServiceUtil.getRoleTableService();
        RoleAttribute roleAttribute = AllServiceUtil.getRoleTableService().selectRoleAttributeRoleId(loginResult.getRole_id());
        if (zhuantype == 1) {
            RoleAttribute roleAttribute2 = new RoleAttribute();
            roleAttribute2.setBONEONE(loginResult.getBone());
            roleAttribute2.setSPIRONE(loginResult.getSpir());
            roleAttribute2.setPOWERONE(loginResult.getPower());
            roleAttribute2.setSPEEDONE(loginResult.getSpeed());
            roleAttribute2.setCALMONE(loginResult.getCalm());
            roleAttribute2.setATTRIBUTENAMEONE("属性一");
            roleAttribute2.setBONETWO(loginResult.getBone());
            roleAttribute2.setSPIRTWO(loginResult.getSpir());
            roleAttribute2.setPOWERTWO(loginResult.getPower());
            roleAttribute2.setSPEEDTWO(loginResult.getSpeed());
            roleAttribute2.setCALMTWO(loginResult.getCalm());
            roleAttribute2.setATTRIBUTENAMETWO("属性二");
            roleAttribute2.setROLE_ID(Integer.valueOf(loginResult.getRole_id().intValue()));
            if (roleAttribute == null) {
                AllServiceUtil.getRoleTableService().insertRoleAttribute(roleAttribute2);
            }
            else {
                AllServiceUtil.getRoleTableService().updateRoleAttributeRoleId(roleAttribute2);
            }
            roleTransBean.setRoleAttribute(roleAttribute2);
        }
        else {
            roleTransBean.setRoleAttribute(null);
        }
        SendMessage.sendMessageToSlef(ctx, Agreement.getAgreement().RacialTransformationAgreement(GsonUtil.getGsonUtil().getgson().toJson(roleTransBean)));
    }
    
    public static String changeSkill(String[] vs, BigDecimal se, Integer skilled) {
        String[] ses = null;
        if (vs != null) {
            if (se != null) {
                ses = getSepciesS(getSepciesN(se));
            }
            StringBuffer buffer = new StringBuffer();
            for (int i = 0; i < vs.length; ++i) {
                String[] vss = vs[i].split("_");
                if (buffer.length() != 0) {
                    buffer.append("#");
                }
                if (ses == null) {
                    buffer.append(vss[0]);
                }
                else {
                    buffer.append(changeSkillId(Integer.parseInt(vss[0]), ses));
                }
                buffer.append("_");
                if (skilled == null) {
                    buffer.append(vss[1]);
                }
                else {
                    buffer.append(skilled);
                }
            }
            return buffer.toString();
        }
        else {
            if (se == null) {
                return null;
            }
            if (skilled == null) {
                return null;
            }
            if (se != null) {
                ses = getSepciesByAi(getSepciesN(se));
            }
            StringBuffer buffer = new StringBuffer();
            for (int i = 0; i < ses.length; ++i) {
                if (buffer.length() != 0) {
                    buffer.append("#");
                }
                buffer.append(getSkillId(ses[i], 5));
                buffer.append("_");
                buffer.append(skilled);
            }
            return buffer.toString();
        }
    }
    
    public static String changeSkill(String[] vs, BigDecimal se) {
        String[] ses = getSepciesS(getSepciesN(se));
        if (vs != null) {
            StringBuffer buffer = new StringBuffer();
            for (int i = 0; i < vs.length; ++i) {
                String[] vss = vs[i].split("_");
                if (buffer.length() != 0) {
                    buffer.append("#");
                }
                buffer.append(changeSkillId(Integer.parseInt(vss[0]), ses));
                buffer.append("_");
                buffer.append(vss[1]);
            }
            return buffer.toString();
        }
        else {
            return null;
        }
    }
    
    public static int changeSkillId(int id, String[] ses) {
        int p = 0;
        if ((id >= 1006 && id <= 1010) || (id >= 1046 && id <= 1050) || (id >= 1021 && id <= 1025) || (id >= 1071 && id <= 1075) || (id >= 1081 && id <= 1085)) {
            p = 0;
        }
        else if ((id >= 1011 && id <= 1015) || (id >= 1051 && id <= 1055) || (id >= 1026 && id <= 1030) || (id >= 1061 && id <= 1065) || (id >= 1071 && id <= 1075) || (id >= 1091 && id <= 1095)) {
            p = 1;
        }
        else {
            p = 2;
        }
        String leixing = ses[p];
        int lvl = id % 5;
        if (lvl == 0) {
            lvl = 5;
        }
        if (leixing.equals("封印")) {
            id = 1005 + lvl;
        }
        else if (leixing.equals("昏睡")) {
            id = 1010 + lvl;
        }
        else if (leixing.equals("混乱")) {
            id = 1000 + lvl;
        }
        else if (leixing.equals("毒")) {
            id = 1015 + lvl;
        }
        else if (leixing.equals("雷")) {
            id = 1045 + lvl;
        }
        else if (leixing.equals("水")) {
            id = 1050 + lvl;
        }
        else if (leixing.equals("风")) {
            id = 1040 + lvl;
        }
        else if (leixing.equals("火")) {
            id = 1055 + lvl;
        }
        else if (leixing.equals("震慑")) {
            id = 1020 + lvl;
        }
        else if (leixing.equals("加攻")) {
            id = 1025 + lvl;
        }
        else if (leixing.equals("加速")) {
            id = 1035 + lvl;
        }
        else if (leixing.equals("盘丝")) {
            id = 1030 + lvl;
        }
        else if (leixing.equals("遗忘")) {
            id = 1070 + lvl;
        }
        else if (leixing.equals("鬼火")) {
            id = 1060 + lvl;
        }
        else if (leixing.equals("三尸虫")) {
            id = 1065 + lvl;
        }
        else if (leixing.equals("魅惑")) {
            id = 1075 + lvl;
        }
        else if (leixing.equals("霹雳")) {
            id = 1080 + lvl;
        }
        else if (leixing.equals("沧波")) {
            id = 1085 + lvl;
        }
        else if (leixing.equals("甘霖")) {
            id = 1090 + lvl;
        }
        else if (leixing.equals("扶摇")) {
            id = 1095 + lvl;
        }
        return id;
    }
    
    public static String getSepciesN(BigDecimal se) {
        int id = se.intValue();
        if (id == 23001 || id == 23002 || id == 23003 || id == 23007) {
            return "男鬼";
        }
        if (id == 23004 || id == 23005 || id == 23006 || id == 23008) {
            return "女鬼";
        }
        if (id == 24001 || id == 24002 || id == 24003 || id == 24007) {
            return "男龙";
        }
        if (id == 24004 || id == 24005 || id == 24006 || id == 24008) {
            return "女龙";
        }
        if (id == 22001 || id == 22002 || id == 22003 || id == 22007 || id == 22009 || id == 22011) {
            return "男仙";
        }
        if (id == 22004 || id == 22005 || id == 22006 || id == 22008 || id == 22010 || id == 22012) {
            return "女仙";
        }
        if (id == 21001 || id == 21002 || id == 21003 || id == 21007 || id == 21009 || id == 21011) {
            return "男魔";
        }
        if (id == 21004 || id == 21005 || id == 21006 || id == 21008 || id == 21010 || id == 21012) {
            return "女魔";
        }
        if (id == 20001 || id == 20002 || id == 20003 || id == 20007 || id == 20009 || id == 20011) {
            return "男人";
        }
        if (id == 20004 || id == 20005 || id == 20006 || id == 20008 || id == 20010 || id == 20012) {
            return "女人";
        }
        return null;
    }
    
    public static String[] getSepciesS(String se) {
        if (se.equals("男鬼")) {
            return new String[] { "遗忘", "鬼火", "三尸虫" };
        }
        if (se.equals("女鬼")) {
            return new String[] { "遗忘", "鬼火", "魅惑" };
        }
        if (se.equals("男仙")) {
            return new String[] { "雷", "水", "风" };
        }
        if (se.equals("女仙")) {
            return new String[] { "雷", "水", "火" };
        }
        if (se.equals("男魔")) {
            return new String[] { "震慑", "加攻", "加速" };
        }
        if (se.equals("女魔")) {
            return new String[] { "震慑", "加攻", "盘丝" };
        }
        if (se.equals("男人")) {
            return new String[] { "封印", "昏睡", "混乱" };
        }
        if (se.equals("女人")) {
            return new String[] { "封印", "昏睡", "毒" };
        }
        if (se.equals("男龙")) {
            return new String[] { "霹雳", "甘霖", "扶摇" };
        }
        if (se.equals("女龙")) {
            return new String[] { "霹雳", "甘霖", "沧波" };
        }
        return null;
    }
    
    public static String[] getSepciesByAi(String se) {
        if (se.equals("男鬼")) {
            return new String[] { "三尸虫", "鬼火" };
        }
        if (se.equals("女鬼")) {
            return new String[] { "鬼火", "遗忘" };
        }
        if (se.equals("男仙")) {
            return new String[] { "风", "水" };
        }
        if (se.equals("女仙")) {
            return new String[] { "火", "雷" };
        }
        if (se.equals("男魔")) {
            return new String[] { "震慑", "加攻" };
        }
        if (se.equals("女魔")) {
            return new String[] { "震慑", "盘丝" };
        }
        if (se.equals("男人")) {
            return new String[] { "混乱" };
        }
        if (se.equals("女人")) {
            return new String[] { "毒" };
        }
        if (se.equals("男龙")) {
            return new String[] { "霹雳" };
        }
        if (se.equals("女龙")) {
            return new String[] { "霹雳" };
        }
        return null;
    }
    
    public static int getSkillId(String leixing, int lvl) {
        int id = 0;
        if (leixing.equals("封印")) {
            id = 1005 + lvl;
        }
        else if (leixing.equals("昏睡")) {
            id = 1010 + lvl;
        }
        else if (leixing.equals("混乱")) {
            id = 1000 + lvl;
        }
        else if (leixing.equals("毒")) {
            id = 1015 + lvl;
        }
        else if (leixing.equals("雷")) {
            id = 1045 + lvl;
        }
        else if (leixing.equals("水")) {
            id = 1050 + lvl;
        }
        else if (leixing.equals("风")) {
            id = 1040 + lvl;
        }
        else if (leixing.equals("火")) {
            id = 1055 + lvl;
        }
        else if (leixing.equals("震慑")) {
            id = 1020 + lvl;
        }
        else if (leixing.equals("加攻")) {
            id = 1025 + lvl;
        }
        else if (leixing.equals("加速")) {
            id = 1035 + lvl;
        }
        else if (leixing.equals("盘丝")) {
            id = 1030 + lvl;
        }
        else if (leixing.equals("遗忘")) {
            id = 1070 + lvl;
        }
        else if (leixing.equals("鬼火")) {
            id = 1060 + lvl;
        }
        else if (leixing.equals("三尸虫")) {
            id = 1065 + lvl;
        }
        else if (leixing.equals("魅惑")) {
            id = 1075 + lvl;
        }
        else if (leixing.equals("霹雳")) {
            id = 1080 + lvl;
        }
        else if (leixing.equals("沧波")) {
            id = 1085 + lvl;
        }
        else if (leixing.equals("甘霖")) {
            id = 1090 + lvl;
        }
        else if (leixing.equals("扶摇")) {
            id = 1095 + lvl;
        }
        return id;
    }
}
