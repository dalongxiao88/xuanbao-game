package org.come.control;

import org.come.Frame.WelcomeXyJframe;
import org.come.Jpanel.WelcomeXyJpanel;
import org.come.XuanBao.RoleXuanBao;
import org.come.login.LoginFrame;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.TextStyle;
import java.util.Locale;

import org.come.bean.LoginRoleInfo;
import org.come.bean.RoleAttribute;
import org.come.bean.LoginResult;
import org.come.Frame.ZhuFrame;
import org.come.socket.SendMessageUntil;
import org.come.action.MapAction;
import org.come.entity.Mount;
import org.come.until.*;
import com.tool.pet.PetProperty;
import org.come.entity.RoleSummoning;
import org.come.thread.LoadYeguaiRunnable;
import org.come.Jpanel.ZhuJpanel;
import com.tool.role.RoleLingFa;
import com.tool.Stall.StallBean;
import com.tool.role.RoleProperty;
import com.tool.time.TimeLiTXT;
import com.tool.time.TimeLimit;
import com.tool.ModerateTask.Hero;
import org.come.socket.DownLoadTxt;
import com.tool.role.RoleTX;
import com.tool.image.ImageMixDeal;
import com.tool.image.ManimgAttribute;
import org.come.bean.RoleShow;
import com.tool.role.RoleData;
import org.come.socket.GameClient;
import org.come.test.Main;
import org.come.bean.enterGameBean;
import org.come.thread.TimeControlRunnable;
import org.come.action.FromServerAction;

public class EnterGameControl implements FromServerAction
{
    private TimeControlRunnable controlRunnable;
    
    @Override
    public void controlMessFromServer(String mes, String type) {
        enterGameBean list2 = (enterGameBean)GsonUtil.getGsonUtil().getgson().fromJson(mes, enterGameBean.class);
        LoginResult role = list2.getLoginResult();
        System.out.println("Rolename=" + role.getRolename() + "=" + role.getSpecies_id() + "=" + Main.index + "=" + role.getRace_name() + "=" + role.getRole_id() + "=" + GameClient.BT);
        RoleAttribute roleAttribute = list2.getRoleAttribute();
        RoleData.getRoleData().setLoginResult(role);
        RoleData.getRoleData().setRoleAttribute(roleAttribute);
        try {
            if (GameClient.DXCL == null) {
                ImageMixDeal.userimg = new ManimgAttribute(new RoleShow(role), 0);
                Util.loadMap(role.getMapid().intValue(), role.getX().intValue(), role.getY().intValue());
                GameClient.DXCL = role.getRole_id().toString() + "|" + GameClient.username + "|" + GameClient.userpasw;
                RoleTX.getRoleTX().initRole(role.getSpecies_id());
                ControlNpcXmlUntil.GetXmlPath("npcMenu.xml");
                new TestMain();
                LoginRoleInfo roleInfo = Main.frame.getRoleInfo();
                roleInfo.setRoleId(role.getRole_id());
                roleInfo.setRoleName(role.getRolename());
                roleInfo.setRoleLvl(role.getGrade());
                roleInfo.setRaceId(role.getSpecies_id());
                roleInfo.setRaceName(role.getRace_name());
                roleInfo.setRoleIp(DownLoadTxt.ip);
                roleInfo.setRolePort(DownLoadTxt.port);
                Main.frame.getLoginInfo().updateLoginRole(roleInfo);
                Main.frame.dispose();
                Main.frame = null;
            }
        }
        catch (Exception e1) {
            e1.printStackTrace();
        }
        Util.uptime(role.getUptime());
        if (role != null) {
            if (ImageMixDeal.userimg == null) {
                ImageMixDeal.userimg = new ManimgAttribute(new RoleShow(role), 0);
                ImageMixDeal.username = role.getRolename();
            }
            else {
                ImageMixDeal.userimg.getRoleShow().init(role);
                ImageMixDeal.username = role.getRolename();
                ImageMixDeal.userimg.changeskin(null);
                ImageMixDeal.userimg.setAppellation(role.getTitle());
                ImageMixDeal.userimg.initTeam();
            }
            Util.dogame(role);
            if (this.controlRunnable == null) {
                this.controlRunnable = new TimeControlRunnable();
                Thread t2 = new Thread(this.controlRunnable);
                t2.start();
            }
        }
        boolean is = false;
        if (list2.getList() != null) {
            GoodsListFromServerUntil.Classification(role, list2.getList(), list2.getPackRecord().getRecord());
            is = true;
        }
        RoleData roleData = RoleData.getRoleData();
        roleData.init(list2.getPackRecord(), list2.getRoleSystem(), list2.getPrivateData(), role);
        Hero.getHero();
        if (list2.getXuanBaos() != null) {
            RoleXuanBao.getRoleXuanBao().lingChange(list2.getXuanBaos());
        }
        if (is) {
            TimeLimit.getLimits();
            TimeLiTXT.getTimeLiTXT();
            RoleProperty.getRoleProperty();
        }
        if (ImageMixDeal.userimg == null) {
            return;
        }
        role = roleData.getLoginResult();
        if (list2.getStall() != null) {
            UserStallUntil.setStall(list2.getStall());
        }
        if (list2.getStallBeans() != null) {
            for (int i = list2.getStallBeans().size() - 1; i >= 0; --i) {
                ImageMixDeal.stall((StallBean)list2.getStallBeans().get(i));
            }
        }
        if (list2.getLingbaos() != null) {
            RoleLingFa.getRoleLingFa().lingChange(list2.getLingbaos());
            RoleProperty.ResetEw();
            if (RoleLingFa.getRoleLingFa().equipBao[0] != null) {
                ZhuJpanel.setLabLingImg(RoleLingFa.getRoleLingFa().equipBao[0].getSkin());
            }
        }

        if (list2.getRoleShows().size() != 0) {
            for (int i = list2.getRoleShows().size() - 1; i >= 0; --i) {
                RoleShow roleShow = (RoleShow)list2.getRoleShows().get(i);
                if (roleShow != null) {
                    ManimgAttribute attribute = ImageMixDeal.huoquLogin(roleShow.getRolename());
                    if (attribute == null) {
                        ImageMixDeal.Playerimgmap.put(roleShow.getRolename(), new ManimgAttribute(roleShow, 1));
                    }
                    else {
                        attribute.setRoleShow(roleShow);
                        attribute.changeskin(null);
                        attribute.CZmove();
                    }
                }
            }
        }
        if (list2.getMonster() != null) {
            LoadYeguaiRunnable intogame_yeguaiRunnable = new LoadYeguaiRunnable(list2.getMonster());
            Thread t3 = new Thread(intogame_yeguaiRunnable);
            t3.start();
        }
        if (list2.getRoleSummonings() != null) {
            UserMessUntil.setPetListTable(PetControl.qc(list2.getRoleSummonings()));
            UserMessUntil.setChosePetMes(null);
            BigDecimal id = role.getSummoning_id();
            for (int j = 0; j < UserMessUntil.getPetListTable().size(); ++j) {
                RoleSummoning pet = (RoleSummoning)UserMessUntil.getPetListTable().get(j);
                if (pet.getBasishp() == 0L) {
                    int[] petvalue = PetProperty.getPetHMASp(pet);
                    pet.setBasishp((long)petvalue[0]);
                    pet.setBasismp((long)petvalue[1]);
                }
                if (id != null && pet.getSid().compareTo(id) == 0) {
                    RoleSummoning petE = (RoleSummoning)UserMessUntil.getPetListTable().get(j);
                    UserMessUntil.setChosePetMes(petE);
                    Article.souxie(petE);
                    String path = "img/head/p" + petE.getSummoningskin() + ".png";
                    ZhuJpanel.setLabpetimg(path);
                }
            }
        }
        if (list2.getRoleDepositSummonings() != null) {
            UserMessUntil.setDepositPetListTable(list2.getRoleDepositSummonings());
        }
        if (list2.getMounts() != null && list2.getMounts().size() != 0) {
            for (int i = 0; i < list2.getMounts().size(); ++i) {
                ZhuJpanel.getListMount().add(i, list2.getMounts().get(i));
                if (role.getMount_id() != null && (int)role.getMount_id() > 0 && role.getMount_id() == ((Mount)list2.getMounts().get(i)).getMountid()) {
                    Util.moveGrade = (int)((Mount)list2.getMounts().get(i)).getMoveGrade();
                }
            }
        }
        if (list2.getCars() != null && list2.getCars().size() != 0) {
            for (int i = 0; i < list2.getCars().size(); ++i) {
                ZhuJpanel.getListCar().add(i, list2.getCars().get(i));
//                if (role.getMount_id() != null && (int)role.getMount_id() > 0 && role.getMount_id() == ((Mount)list2.getMounts().get(i)).getMountid()) {
//                    Util.moveGrade = (int)((Mount)list2.getMounts().get(i)).getMoveGrade();
//                }
            }
        }
        BabyControl.babyinit(list2.getBabys());
        roleData.setPals(list2.getPals());
        ZhuJpanel.isPetZhiyuan = (role.getScoretype("支援").intValue() == 1);
        ZhuJpanel.isPetShouFa = (role.getScoretype("首发").intValue() == 1);
        ZhuJpanel.isLingZhiyuan = (role.getScoretype("灵宝支援").intValue() == 1);
        //登录右下角显示这里开始
        ImageMixDeal.upScene(list2.getSceneMsg());
        MapAction.flagAction.put(SendMessageUntil.gameServerKey, Boolean.valueOf(true));
        UserMessUntil.getTitleColors();
        ZhuJpanel.setListFly(list2.getFlys());
        ZhuFrame.getZhuJpanel().hotKeyImg();
        GoodsListFromServerUntil.isSendPackRecord();
        ImageMixDeal.upScene(list2.getSceneMsg());
        /** zrikka 2020 0414 */
//		SendMessageUntil.CanDoConnectOrNo = true;
        MapAction.flagAction.put(SendMessageUntil.gameServerKey, true);
        /*****/

//		FormsManagement.showForm(104);
//		Hero.getHero().addTask("1004=2=T1486787655=P2_M1230-2180-1880_D300052-万年熊王=P2_M1230-1680-2080_D300053-蓝色妖王=P2_M1230-2260-2380_D300054-黑山妖王=P2_M1230-2760-2120_D300055-三头魔王");
//		Hero.getHero().addTask("1000=2=T1486787655=P2_M1230-2180-1880_D300052-万年熊王=P2_M1230-1680-2080_D300053-蓝色妖王=P2_M1230-2260-2380_D300054-黑山妖王=P2_M1230-2760-2120_D300055-三头魔王");
//		for (int i = 0; i <=104; i++) {
//			try {
//				FormsManagement.showForm(i);
//			} catch (Exception e) {
//				// TODO: handle exception
//			    System.out.println(i);
//			}
//		}

        UserMessUntil.getTitleColors();

        //获取称谓信息开始倒计时
//		String sendmes = Agreement.getAgreement().getTitleAgreement();
//		SendMessageUntil.toServer(sendmes);
        //修复飞行器初始化

        //System.out.println(list2.getFlys());
        ZhuJpanel.setListFly(list2.getFlys());
        //快捷初始化
        ZhuFrame.getZhuJpanel().hotKeyImg();
        //	RoleTX.getRoleTX().BCXX();
        roleData.changeSkin(true);

        TimeLimit.timeLimit.changeSkin();
        if (role.getPassword()==null) {
            FormsManagement.upgradForm(32);
        }else {
            FormsManagement.showForm(33);
        }

        if(role.getGrade()<50){
            FormsManagement.showForm(3081);
            WelcomeXyJpanel.X = 277;
            WelcomeXyJpanel.updateOX();
        }else if(role.getGrade()>60){
            FormsManagement.showForm(3081);
            LocalDate currentDate = LocalDate.now();
            String dayOfWeek = currentDate.getDayOfWeek().getDisplayName(TextStyle.FULL, Locale.CHINA);
            if(dayOfWeek.equals("星期一")){
                WelcomeXyJpanel.taskID = "115";
                WelcomeXyJpanel.MsgList.add("#Y亲爱的玩家今天是#R星期一#r#c00FFFF镖行天下#1#Y活动已开启，#R神兵、神兽、仙器#Y拿不停，快去领取吧。#23#r#Y祝您游戏愉快#23");
                WelcomeXyJpanel.taskmsg = "1207-5140-2980-500143";
            }else if(dayOfWeek.equals("星期二")){
                WelcomeXyJpanel.taskID = "116";
                WelcomeXyJpanel.MsgList.add("#Y亲爱的玩家今天是#R星期二#r#c00FFFF年年有余#1#Y活动已开启，#R神兵、神兽、仙器#Y拿不停，快去领取吧。#23#r#Y祝您游戏愉快#23");
                WelcomeXyJpanel.taskmsg = "1207-5140-2980-500143";
            }else if(dayOfWeek.equals("星期三")){
                WelcomeXyJpanel.taskID = "117";
                WelcomeXyJpanel.MsgList.add("#Y亲爱的玩家今天是#R星期三#r#c00FFFF盛世华夏#1#Y活动已开启，#R神兵、神兽、仙器#Y拿不停，快去领取吧。#23#r#Y祝您游戏愉快#23");
                WelcomeXyJpanel.taskmsg = "1207-5140-2980-500143";
            }else if(dayOfWeek.equals("星期四")){
                WelcomeXyJpanel.taskID = "118";
                WelcomeXyJpanel.MsgList.add("#Y亲爱的玩家今天是#R星期四#r#c00FFFF城东魅影#1#Y活动已开启，#R神兵、神兽、仙器#Y拿不停，快去领取吧。#23#r#Y祝您游戏愉快#23");
                WelcomeXyJpanel.taskmsg = "1207-5140-2980-500143";
            }else if(dayOfWeek.equals("星期五")){
                WelcomeXyJpanel.taskID = "119";
                WelcomeXyJpanel.MsgList.add("#Y亲爱的玩家今天是#R星期五#r#c00FFFF厉兵秣马#1#Y活动已开启，#R神兵、神兽、仙器#Y拿不停，快去领取吧。#23#r#Y祝您游戏愉快#23");
                WelcomeXyJpanel.taskmsg = "1207-5140-2980-500143";
            }else if(dayOfWeek.equals("星期六")){
                WelcomeXyJpanel.taskID = "120";
                WelcomeXyJpanel.MsgList.add("#Y亲爱的玩家今天是#R星期六#r#c00FFFF版载千秋#1#Y活动已开启，#R神兵、神兽、仙器#Y拿不停，快去领取吧。#23#r#Y祝您游戏愉快#23");
                WelcomeXyJpanel.taskmsg = "1207-4810-2770-500172";
            }else if(dayOfWeek.equals("星期日")){
                WelcomeXyJpanel.taskID = "121";
                WelcomeXyJpanel.MsgList.add("#Y亲爱的玩家今天是#R星期日#r#c00FFFF花灯报吉#1#Y活动已开启，#R神兵、神兽、仙器#Y拿不停，快去领取吧。#23#r#Y祝您游戏愉快#23");
                WelcomeXyJpanel.taskmsg = "1207-5140-2980-500143";
            }
            WelcomeXyJpanel.indexs = WelcomeXyJpanel.MsgList.size()-1;
            WelcomeXyJframe.getWelcomeXyJpanel().getStartbel().setText("     点击领取");
            WelcomeXyJpanel.X = 217;
            WelcomeXyJpanel.updateOX();
        }
    }
}
