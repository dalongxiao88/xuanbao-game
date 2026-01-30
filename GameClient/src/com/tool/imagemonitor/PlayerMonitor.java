package com.tool.imagemonitor;

import org.come.Jpanel.NewRefiningJpanel;
import org.come.until.AccessTeamInfoUntil;
import com.tool.role.RoleData;
import org.come.Jpanel.GiveJpanel;
import org.come.Frame.GiveJframe;
import org.come.until.JTreeData;
import org.come.Frame.TestfriendlistJframe;
import org.come.until.FormsManagement;
import org.come.entity.Friend;
import org.come.entity.Friendtable;
import org.come.until.UserMessUntil;
import org.come.Jpanel.FrameMessageChangeJpanel;
import java.math.BigDecimal;
import org.come.until.GsonUtil;
import come.tool.FightingData.FightingForesee;
import org.come.until.UserStallUntil;
import org.come.bean.RoleShow;
import come.tool.BangBattle.BangFight;
import org.come.until.Util;
import org.come.socket.SendMessageUntil;
import org.come.socket.Agreement;
import org.come.Frame.ZhuFrame;
import com.tool.image.ImageMixDeal;
import org.come.until.MessagrFlagUntil;
import com.tool.image.ManimgAttribute;

public class PlayerMonitor
{
    public static void Player(ManimgAttribute attribute) {
        RoleShow roleShow = attribute.getRoleShow();
        int type = attribute.getLeixing();
        String Id = roleShow.getRolename();
        String var4;
        switch ((var4 = MessagrFlagUntil.ImgFlagImg).hashCode()) {
            case 650223: {
                if (var4.equals("交易")) {
                    if (type == 0) {
                        MessagrFlagUntil.setMouse(MessagrFlagUntil.MOUSE1);
                    }
                    else if (type == 1) {
                        transApply(Id);
                        return;
                    }
                    return;
                }
                else {
                    break;
                }
            }
            case 681892: {
                if (var4.equals("切磋")) {
                    MessagrFlagUntil.setMouse(MessagrFlagUntil.MOUSE1);
                    if (roleShow.getFighting() != 0) {
                        String a = ImageMixDeal.userimg.getRoleShow().getTeamInfo();
                        if (a != null && !a.equals("")) {
                            ZhuFrame.getZhuJpanel().addPrompt2("只有单人才可以进行观战");
                        }
                        else {
                            String fightMes = Agreement.getAgreement().battleConnectionAgreement(String.valueOf(roleShow.getFighting()));
                            SendMessageUntil.toServer(fightMes);
                        }
                        return;
                    }
                    else {
                        String[] v = ImageMixDeal.userimg.getTeams();
                        if (v == null) {
                            ZhuFrame.getZhuJpanel().addPrompt2("叫队长帮你点");
                            return;
                        }
                        if (Util.ditubianma == 3315) {
                            if (BangFight.getBangFight().manstate == 1) {
                                ZhuFrame.getZhuJpanel().addPrompt2("你需要等待一段时间才能投入战斗");
                                return;
                            }
                            if (!BangFight.getBangFight().isChao()) {
                                ZhuFrame.getZhuJpanel().addPrompt2("您当前处于开炮、控塔、挑战状态，无法进行其他操作");
                                return;
                            }
                            if (ImageMixDeal.userimg.getRoleShow().getTeam().split("\\|").length < BangFight.MINSUM) {
                                ZhuFrame.getZhuJpanel().addPrompt2("最少人数" + BangFight.MINSUM);
//                                return;
                            }
                            pk(roleShow, 11);
                            return;
                        }
                        else {
                            if (type == 0) {
                                ZhuFrame.getZhuJpanel().addPrompt2("您不能和自己切磋！！");
                                return;
                            }
                            if ((int)ImageMixDeal.userimg.getRoleShow().getGrade() < 70) {
                                ZhuFrame.getZhuJpanel().addPrompt2("70级才开放PK");
                                return;
                            }
                            if ((int)roleShow.getGrade() < 30) {
                                ZhuFrame.getZhuJpanel().addPrompt2("玩家处于新手保护期");
                                return;
                            }
                            if (ImageMixDeal.userimg.getRoleShow().getBooth_id() != null) {
                                ZhuFrame.getZhuJpanel().addPrompt2("你还在摆摊啊");
                                return;
                            }
                            if (roleShow.getBooth_id() != null) {
                                ZhuFrame.getZhuJpanel().addPrompt2("玩家在摆摊");
                                return;
                            }
                            for (int i = 0; i < v.length; ++i) {
                                if (v[i].equals(roleShow.getRolename())) {
                                    ZhuFrame.getZhuJpanel().addPrompt2("他是你的队员");
                                    return;
                                }
                            }
                            pk(roleShow, 5);
                            return;
                        }
                    }
                }
                else {
                    break;
                }
            }
            case 731630: {
                if (var4.equals("好友")) {
                    addFriend(roleShow.getRole_id(), roleShow.getRolename());
                    return;
                }
                else {
                    break;
                }
            }
            case 1026767: {
                if (var4.equals("给予")) {
                    give(attribute);
                    return;
                }
                else {
                    break;
                }
            }
            case 1044443: {
                if (var4.equals("组队")) {
                    if (type == 0) {
                        createTeam();
                    }
                    else {
                        teamApply(roleShow.getRole_id());
                    }
                    return;
                }
                else {
                    break;
                }
            }
        }
        if (MessagrFlagUntil.ImgFlagImg == MessagrFlagUntil.MOUSE1) {
            ZhuFrame.getZhuJpanel().creatroletext(roleShow.getRole_id(), roleShow.getRolename());
            return;
        }
    }
    
    public static void pk(RoleShow roleShow, int type) {
        if (UserStallUntil.isStall()) {
            return;
        }
        FightingForesee fightingForesee = new FightingForesee();
        if (ImageMixDeal.userimg.getRoleShow().getTeamInfo() != null && !ImageMixDeal.userimg.getRoleShow().getTeamInfo().equals("")) {
            fightingForesee.setYidui(ImageMixDeal.userimg.getRoleShow().getTeamInfo());
        }
        else {
            fightingForesee.setYidui(ImageMixDeal.userimg.getRoleShow().getRolename());
        }
        if (roleShow.getTeamInfo() != null && !roleShow.getTeamInfo().equals("")) {
            fightingForesee.setErdui(roleShow.getTeamInfo());
        }
        else {
            fightingForesee.setErdui(roleShow.getRolename());
        }
        fightingForesee.setType(type);
        String fightMes = Agreement.FightingForeseeAgreement(GsonUtil.getGsonUtil().getgson().toJson(fightingForesee));
        SendMessageUntil.toServer(fightMes);
    }
    
    public static void transApply(String Id) {

        if (UserStallUntil.isStall()) {
            return;
        }
        FormsManagement.HideForm(11);
        if (NewRefiningJpanel.isLH) {
            ZhuFrame.getZhuJpanel().addPrompt("炼化中无法打开其他操作");
            return;
        }
        if (ImageMixDeal.Playerimgmap.get(Id) != null) {
            MessagrFlagUntil.setMouse(MessagrFlagUntil.MOUSE1);
            String send = Agreement.getAgreement().TransStateAgreement("0|" + Id);
            SendMessageUntil.toServer(send);
        }
    }
    
    public static void addFriend(BigDecimal id, String name) {
        MessagrFlagUntil.setMouse(MessagrFlagUntil.MOUSE1);
        if (id.compareTo(ImageMixDeal.userimg.getRoleShow().getRole_id()) == 0) {
            FrameMessageChangeJpanel.addtext(6, "自己加自己?", (BigDecimal)null, (String)null);
        }
        else {
            if (UserMessUntil.getFriendtables() != null) {
                for (int i = 0; i < UserMessUntil.getFriendtables().size(); ++i) {
                    if (((Friendtable)UserMessUntil.getFriendtables().get(i)).getRole_id().compareTo(id) == 0) {
                        ImageMixDeal.userimg.Dialogue("对方已经是你好友");
                        FrameMessageChangeJpanel.addtext(6, "对方已经是你好友", (BigDecimal)null, (String)null);
                        return;
                    }
                }
            }
            Friend fiend = new Friend();
            fiend.setFriendid(id);
            fiend.setRoleid(ImageMixDeal.userimg.getRoleShow().getRole_id());
            String sendmes = Agreement.getAgreement().addFrientAgreement(GsonUtil.getGsonUtil().getgson().toJson(fiend));
            SendMessageUntil.toServer(sendmes);
            ZhuFrame.getZhuJpanel().addPrompt2("你添加#G" + name + "#Y为好友");
        }
    }
    
    public static void deleteFriden(String role) {
        Friendtable friendtable = null;
        int i = 0;
        while (i < UserMessUntil.getFriendtables().size()) {
            if (role.equals(((Friendtable)UserMessUntil.getFriendtables().get(i)).getRolename())) {
                friendtable = (Friendtable)UserMessUntil.getFriendtables().remove(i);
                break;
            }
            else {
                ++i;
            }
        }
        if (friendtable != null) {
            if (FormsManagement.getframe(4).isVisible()) {
                JTreeData.ShowFriendMsg(TestfriendlistJframe.getTestfriendlistJframe().getJflist().getTop(), TestfriendlistJframe.getTestfriendlistJframe().getJflist().getjTree());
            }
            Friend fiendFriend = new Friend();
            fiendFriend.setRoleid(ImageMixDeal.userimg.getRoleShow().getRole_id());
            fiendFriend.setFriendid(friendtable.getRole_id());
            String mes = Agreement.getAgreement().delectFriend(GsonUtil.getGsonUtil().getgson().toJson(fiendFriend));
            SendMessageUntil.toServer(mes);
        }
    }
    
    public static void give(ManimgAttribute attribute) {
//        FormsManagement.HideForm(11);

        if (NewRefiningJpanel.isLH) {
            ZhuFrame.getZhuJpanel().addPrompt("炼化中无法打开其他操作");
            return;
        }
        if (UserStallUntil.isStall()) {
            return;
        }
        if (attribute.getLeixing() == 0) {
            ZhuFrame.getZhuJpanel().addPrompt2("不能将物品给与自己！！");
        }
        else {
            GiveJpanel giveJpanel = GiveJframe.getGivejframe().getGivejpanel();
            if (FormsManagement.getframe(12).isVisible()) {
                ZhuFrame.getZhuJpanel().addPrompt("您当前状态不可给予,请稍后再试！");
            }
            else {
                MessagrFlagUntil.setMouse(MessagrFlagUntil.MOUSE1);
                giveJpanel.giveShow(attribute);
            }
        }
    }
    
    public static void createTeam() {
        if (UserStallUntil.isStall()) {
            return;
        }
        MessagrFlagUntil.setMouse(MessagrFlagUntil.MOUSE1);
        if ((long)ImageMixDeal.userimg.getRoleShow().getMapid() != 3321L && (long)ImageMixDeal.userimg.getRoleShow().getMapid() != 3322L) {
            if (AccessTeamInfoUntil.isJail(RoleData.getRoleData().getLoginResult().getTaskDaily())) {
                ZhuFrame.getZhuJpanel().addPrompt2("坐牢状态无法组队");
            }
            else {
                RoleShow roleShow = ImageMixDeal.userimg.getRoleShow();
                if (roleShow.getTroop_id() != null) {
                    ZhuFrame.getZhuJpanel().addPrompt2("你已经有队伍了");
                }
                else {
                    String mes = Agreement.getAgreement().team1Agreement("");
                    SendMessageUntil.toServer(mes);
                }
            }
        }
        else {
            ZhuFrame.getZhuJpanel().addPrompt2("该地图无法组队");
        }
    }
    
    public static void teamApply(BigDecimal Id) {
        MessagrFlagUntil.setMouse(MessagrFlagUntil.MOUSE1);
        if ((long)ImageMixDeal.userimg.getRoleShow().getMapid() != 3321L && (long)ImageMixDeal.userimg.getRoleShow().getMapid() != 3322L) {
            if (AccessTeamInfoUntil.isJail(RoleData.getRoleData().getLoginResult().getTaskDaily())) {
                ZhuFrame.getZhuJpanel().addPrompt2("坐牢状态无法组队");
            }
            else {
                RoleShow roleShow = ImageMixDeal.userimg.getRoleShow();
                if (roleShow.getTroop_id() != null) {
                    ZhuFrame.getZhuJpanel().addPrompt2("你已经有队伍了");
                }
                else {
                    String mes = Agreement.getAgreement().team2Agreement(Id.toString());
                    SendMessageUntil.toServer(mes);
                }
            }
        }
        else {
            ZhuFrame.getZhuJpanel().addPrompt2("该地图无法组队");
        }
    }
}
