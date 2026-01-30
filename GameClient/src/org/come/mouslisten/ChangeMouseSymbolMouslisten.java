package org.come.mouslisten;

import java.util.Map;
import org.come.bean.ConfigureBean;
import org.come.until.GoodsListFromServerUntil;
import org.come.entity.Goodstable;
import org.come.Frame.TestPetJframe;
import org.come.model.Configure;
import javax.swing.JLabel;
import java.math.BigDecimal;
import java.util.Collections;
import org.come.Jpanel.TestChildAttributeJpanel;
import org.come.Frame.TestChildJframe;
import org.come.entity.Baby;
import org.come.Jpanel.TestPetJpanel;
import org.come.until.GsonUtil;
import com.tool.pet.PetProperty;
import org.come.until.UserMessUntil;
import org.come.entity.Mount;
import org.come.entity.RoleSummoning;
import org.come.model.InternalForm;
import org.come.Jpanel.WorldMapJpanel;
import org.come.Jpanel.TeststateJpanel;
import org.come.Jpanel.TestpackJapnel;
import com.tool.role.RoleData;
import org.come.Frame.TeamJframe;
import org.come.until.FormsManagement;
import com.tool.image.ImageMixDeal;
import org.come.socket.SendMessageUntil;
import org.come.socket.Agreement;
import org.come.Frame.ZhuFrame;
import org.come.until.Music;
import org.come.until.MessagrFlagUntil;

public class ChangeMouseSymbolMouslisten
{
    public static void dianji(String FlagMes) {
        if (FlagMes.equals("切磋")) {
            MessagrFlagUntil.setMouse(MessagrFlagUntil.MOUSE6);
            Music.addyinxiao("开关窗口.mp3");
        }
        else if (FlagMes.equals("组队")) {
            if (ZhuFrame.getZhuJpanel().getTeamState() == 1) {
                ZhuFrame.getZhuJpanel().setTeamState(0);
                String sendmes = Agreement.getAgreement().team6Agreement("");
                SendMessageUntil.toServer(sendmes);
            }
            else {
                ZhuFrame.getZhuJpanel().showIsTeamBtn(true, 1);
                ZhuFrame.getZhuJpanel().tradingMenu(false, 0);
                ZhuFrame.getZhuJpanel().showIsSystemBtn(false, 0);
                ZhuFrame.getZhuJpanel().showflybtn(false, 0);
            }
        }
        else if (FlagMes.equals("设置")) {
            ZhuFrame.getZhuJpanel().showIsSystemBtn(true, 1);
            ZhuFrame.getZhuJpanel().tradingMenu(false, 0);
            ZhuFrame.getZhuJpanel().showflybtn(false, 0);
            ZhuFrame.getZhuJpanel().showIsTeamBtn(false, 0);
            ZhuFrame.getZhuJpanel().showIsTeamBtn(false, 0);
        }
        else if (FlagMes.equals("组队快捷键")) {
            if (ZhuFrame.getZhuJpanel().getTeamState() == 1) {
                ZhuFrame.getZhuJpanel().setTeamState(0);
                String sendmes = Agreement.getAgreement().team6Agreement("");
                SendMessageUntil.toServer(sendmes);
            }
            else if (ImageMixDeal.userimg.getRoleShow().getTroop_id() != null) {
                InternalForm internalForm = FormsManagement.getInternalForm2(13);
                if (internalForm != null && internalForm.getFrame().isVisible()) {
                    FormsManagement.HideForm(13);
                }
                else {
                    TeamJframe.getTeamJframe().getTeamjpanel().show(ImageMixDeal.userimg.getRoleShow(), RoleData.getRoleData().getTeamBean());
                }
            }
            else {
                MessagrFlagUntil.setMouse(MessagrFlagUntil.MOUSE2);
            }
        }
        else if (FlagMes.equals("交易")) {
            MessagrFlagUntil.setMouse(MessagrFlagUntil.MOUSE5);
        }
        else if (FlagMes.equals("给予")) {
            MessagrFlagUntil.setMouse(MessagrFlagUntil.MOUSE3);
        }
        else if (FlagMes.equals("交易菜单")) {
            ZhuFrame.getZhuJpanel().tradingMenu(true, 1);
            ZhuFrame.getZhuJpanel().showIsSystemBtn(false, 0);
            ZhuFrame.getZhuJpanel().showflybtn(false, 0);
            ZhuFrame.getZhuJpanel().showIsTeamBtn(false, 0);
        }
        else if (FlagMes.equals("坐骑")) {
            ZhuFrame.getZhuJpanel().showflybtn(true, 1);
            ZhuFrame.getZhuJpanel().tradingMenu(false, 0);
            ZhuFrame.getZhuJpanel().showIsSystemBtn(false, 0);
            ZhuFrame.getZhuJpanel().showIsTeamBtn(false, 0);
        }
        else if (FlagMes.equals("整理")) {
            TestpackJapnel.showIsTeamBtnS(true, 1);
        }
        else if (FlagMes.equals("星盘星录")) {
            TeststateJpanel.showIsTeamBtnS(true, 1);
        }   else if (FlagMes.equals("玄宝")) {
            FormsManagement.showForm(8031);

        }
        else if (FlagMes.equals("海底迷宫")) {
            WorldMapJpanel.showIsTeamBtnSx(true, 1);
            WorldMapJpanel.showduyu(false, 0);
            WorldMapJpanel.showdayanta(false, 0);
            WorldMapJpanel.showfengcao(false, 0);
            WorldMapJpanel.showlongku(false, 0);
        }
        else if (FlagMes.equals("地狱迷宫")) {
            WorldMapJpanel.showduyu(true, 1);
            WorldMapJpanel.showIsTeamBtnSx(false, 0);
            WorldMapJpanel.showdayanta(false, 0);
            WorldMapJpanel.showfengcao(false, 0);
            WorldMapJpanel.showlongku(false, 0);
        }
        else if (FlagMes.equals("大雁塔")) {
            WorldMapJpanel.showdayanta(true, 1);
            WorldMapJpanel.showIsTeamBtnSx(false, 0);
            WorldMapJpanel.showduyu(false, 0);
            WorldMapJpanel.showfengcao(false, 0);
            WorldMapJpanel.showlongku(false, 0);
        }
        else if (FlagMes.equals("凤巢")) {
            WorldMapJpanel.showfengcao(true, 1);
            WorldMapJpanel.showIsTeamBtnSx(false, 0);
            WorldMapJpanel.showduyu(false, 0);
            WorldMapJpanel.showdayanta(false, 0);
            WorldMapJpanel.showlongku(false, 0);
        }
        else if (FlagMes.equals("龙窟")) {
            WorldMapJpanel.showlongku(true, 1);
            WorldMapJpanel.showIsTeamBtnSx(false, 0);
            WorldMapJpanel.showduyu(false, 0);
            WorldMapJpanel.showdayanta(false, 0);
            WorldMapJpanel.showfengcao(false, 0);
        }
    }
    
    public static void addProperties(RoleSummoning roleSummoning, Mount mount) {
        if (UserMessUntil.getChosePetMes() != null && UserMessUntil.getChosePetMes().getSid().compareTo(roleSummoning.getSid()) == 0) {
            PetAddPointMouslisten.showPetValue();
            if (FormsManagement.getframe(58).isVisible()) {
                PetProperty.ShowQl(roleSummoning);
            }
        }
    }
    
    public static void clearPropertie(RoleSummoning roleSummoning) {
        if (UserMessUntil.getChosePetMes() != null && UserMessUntil.getChosePetMes().getSid().compareTo(roleSummoning.getSid()) == 0) {
            PetAddPointMouslisten.showPetValue();
            if (FormsManagement.getframe(58).isVisible()) {
                PetProperty.ShowQl(roleSummoning);
            }
        }
    }
    
    public static void releasePet(RoleSummoning roleSummoning) {
        String sendmes = Agreement.getAgreement().PetReleaseAgreement(GsonUtil.getGsonUtil().getgson().toJson(roleSummoning.getSid()));
        SendMessageUntil.toServer(sendmes);
        int exi = -1;
        for (int i = 0; i < UserMessUntil.getPetListTable().size(); ++i) {
            if (((RoleSummoning)UserMessUntil.getPetListTable().get(i)).getSid().compareTo(roleSummoning.getSid()) == 0) {
                exi = i;
            }
        }
        UserMessUntil.getPetListTable().remove(exi);
        TestPetJpanel.showListModel(UserMessUntil.getPetListTable(), RoleData.getRoleData().getLoginResult().getSummoning_id());
        FormsManagement.HideForm(19);
        if (UserMessUntil.getPetListTable().size() <= 0) {
            TestPetJpanel.getLabname().setText("");
            TestPetJpanel.getLabHP().setText("");
            TestPetJpanel.getLabMP().setText("");
            TestPetJpanel.getLabAP().setText("");
            TestPetJpanel.getLabSP().setText("");
            TestPetJpanel.getLabEXP().setText("");
            TestPetJpanel.getLabpower().setText("");
            TestPetJpanel.getLabspeed().setText("");
            TestPetJpanel.getLabpointnumber().setText("");
            TestPetJpanel.getLabrootbone().setText("");
            TestPetJpanel.getLabintelligence().setText("");
            TestPetJpanel.getLablevel().setText("");
            TestPetJpanel.getLabloyalty().setText("");
            TestPetJpanel.getTfLab().setVisible(false);
            TestPetJpanel.getTianfucount().setVisible(false);
            UserMessUntil.setChosePetMes(null);
        }
        else if (RoleData.getRoleData().getLoginResult().getSummoning_id() != null) {
            int exi2 = 0;
            for (int j = 0; j < UserMessUntil.getPetListTable().size(); ++j) {
                if (RoleData.getRoleData().getLoginResult().getSummoning_id().compareTo(((RoleSummoning)UserMessUntil.getPetListTable().get(j)).getSid()) == 0) {
                    exi2 = j;
                }
            }
            UserMessUntil.setChosePetMes((RoleSummoning)UserMessUntil.getPetListTable().get(exi2));
            PetAddPointMouslisten.showPetValue();
        }
        else {
            UserMessUntil.setChosePetMes((RoleSummoning)UserMessUntil.getPetListTable().get(0));
            PetAddPointMouslisten.showPetValue();
        }
        FormsManagement.HideForm(18);
    }
    
    public static void releasePetFP(RoleSummoning roleSummoning) {
        String sendmes = Agreement.getAgreement().PetReleaseFPAgreement(GsonUtil.getGsonUtil().getgson().toJson(roleSummoning.getSid()));
        SendMessageUntil.toServer(sendmes);
        int exi = -1;
        for (int i = 0; i < UserMessUntil.getPetListTable().size(); ++i) {
            if (((RoleSummoning)UserMessUntil.getPetListTable().get(i)).getSid().compareTo(roleSummoning.getSid()) == 0) {
                exi = i;
            }
        }
        UserMessUntil.getPetListTable().remove(exi);
        TestPetJpanel.showListModel(UserMessUntil.getPetListTable(), RoleData.getRoleData().getLoginResult().getSummoning_id());
        FormsManagement.HideForm(19);
        if (UserMessUntil.getPetListTable().size() <= 0) {
            TestPetJpanel.getLabname().setText("");
            TestPetJpanel.getLabHP().setText("");
            TestPetJpanel.getLabMP().setText("");
            TestPetJpanel.getLabAP().setText("");
            TestPetJpanel.getLabSP().setText("");
            TestPetJpanel.getLabEXP().setText("");
            TestPetJpanel.getLabpower().setText("");
            TestPetJpanel.getLabspeed().setText("");
            TestPetJpanel.getLabpointnumber().setText("");
            TestPetJpanel.getLabrootbone().setText("");
            TestPetJpanel.getLabintelligence().setText("");
            TestPetJpanel.getLablevel().setText("");
            TestPetJpanel.getLabloyalty().setText("");
            TestPetJpanel.getTfLab().setVisible(false);
            TestPetJpanel.getTianfucount().setVisible(false);
            UserMessUntil.setChosePetMes(null);
        }
        else if (RoleData.getRoleData().getLoginResult().getSummoning_id() != null) {
            int exi2 = 0;
            for (int j = 0; j < UserMessUntil.getPetListTable().size(); ++j) {
                if (RoleData.getRoleData().getLoginResult().getSummoning_id().compareTo(((RoleSummoning)UserMessUntil.getPetListTable().get(j)).getSid()) == 0) {
                    exi2 = j;
                }
            }
            UserMessUntil.setChosePetMes((RoleSummoning)UserMessUntil.getPetListTable().get(exi2));
            PetAddPointMouslisten.showPetValue();
        }
        else {
            UserMessUntil.setChosePetMes((RoleSummoning)UserMessUntil.getPetListTable().get(0));
            PetAddPointMouslisten.showPetValue();
        }
        FormsManagement.HideForm(18);
    }
    
    public static void BabyasePet(Baby baby) {
        String sendmes = Agreement.getAgreement().BabyReleaseAgreement(GsonUtil.getGsonUtil().getgson().toJson(baby.getBabyid()));
        SendMessageUntil.toServer(sendmes);
        Baby babyx = UserMessUntil.getbaby(TestChildJframe.getTestChildJframe().getTestChildJpanel().getBabyid());
        BigDecimal[] bigs = (BigDecimal[])((babyx != null) ? babyx.getpartAll() : null);
        int exi = -1;
        for (int i = 0; i < UserMessUntil.getMyListBaby().size(); ++i) {
            if (((Baby)UserMessUntil.getMyListBaby().get(i)).getBabyid().compareTo(baby.getBabyid()) == 0) {
                exi = i;
            }
        }
        UserMessUntil.getMyListBaby().remove(exi);
        FormsManagement.HideForm(19);
        if (UserMessUntil.getMyListBaby().size() <= 0) {
            TestChildAttributeJpanel.getLabPetname().setText("");
            TestChildAttributeJpanel.getLabtemperament().setText("");
            TestChildAttributeJpanel.getLabintelligence().setText("");
            TestChildAttributeJpanel.getLabFame().setText("");
            TestChildAttributeJpanel.getLabtreason().setText("");
            TestChildAttributeJpanel.getLabfood().setText("");
            TestChildAttributeJpanel.getLabclose().setText("");
            TestChildAttributeJpanel.getLabforce().setText("");
            TestChildAttributeJpanel.getLabendurance().setText("");
            TestChildAttributeJpanel.getLabMorality().setText("");
            TestChildAttributeJpanel.getLabPlay().setText("");
            TestChildAttributeJpanel.getLabLove().setText("");
            TestChildAttributeJpanel.getLabfatigue().setText("");
            TestChildAttributeJpanel.getLabrisingmoney().setText("");
            JLabel[] jLabels = TestChildJframe.getTestChildJframe().getTestChildJpanel().getLabListChild();
            for (int j = 0; j < 6; ++j) {
                jLabels[j].setIcon(null);
            }
            UserMessUntil.setMyListBaby(null);
        }
        else if (RoleData.getRoleData().getLoginResult().getBabyId() != null) {
            int exi2 = 0;
            for (int j = 0; j < UserMessUntil.getMyListBaby().size(); ++j) {
                if (RoleData.getRoleData().getLoginResult().getBabyId().compareTo(((Baby)UserMessUntil.getMyListBaby().get(j)).getBabyid()) == 0) {
                    exi2 = j;
                }
            }
            UserMessUntil.setMyListBaby(Collections.singletonList(UserMessUntil.getMyListBaby().get(exi2)));
            TestChildJframe.getTestChildJframe().getTestChildJpanel().getTestChildCardJpanel().getChildAttributeJpanel().showBaby(baby, bigs);
        }
        else {
            UserMessUntil.setMyListBaby(Collections.singletonList(UserMessUntil.getMyListBaby().get(0)));
            TestChildJframe.getTestChildJframe().getTestChildJpanel().getTestChildCardJpanel().getChildAttributeJpanel().showBaby(baby, bigs);
        }
        FormsManagement.HideForm(1);
    }
    
    public static void refreshNedan(RoleSummoning roleSummoning) {
        ConfigureBean allConfigure = UserMessUntil.getConfigureBean();
        Map<BigDecimal, Configure> item = allConfigure.getAllConfigure();
        Configure configure = (Configure)item.get(new BigDecimal(1));
        if (roleSummoning != null) {
            for (int i = 0; i < Integer.parseInt(configure.getNdslsx()); ++i) {
                TestPetJpanel.getLabNedan()[i].setGoodstable(null);
                TestPetJframe.getTestPetJframe().getTestPetJpanel().remove(TestPetJpanel.getLabNedan()[i]);
            }
            if (roleSummoning.getInnerGoods() != null && !roleSummoning.getInnerGoods().equals("")) {
                String[] strings = roleSummoning.getInnerGoods().split("\\|");
                if (strings.length > 0) {
                    for (int j = 0; j < strings.length; ++j) {
                        TestPetJframe.getTestPetJframe().getTestPetJpanel().add(TestPetJpanel.getLabNedan()[j]);
                        TestPetJpanel.getLabNedan()[j].setGoodstable((Goodstable)GoodsListFromServerUntil.fushis.get(new BigDecimal(strings[j])));
                    }
                }
            }
            else {
                for (int i = 0; i < Integer.parseInt(configure.getNdslsx()); ++i) {
                    TestPetJpanel.getLabNedan()[i].setGoodstable(null);
                    TestPetJframe.getTestPetJframe().getTestPetJpanel().remove(TestPetJpanel.getLabNedan()[i]);
                }
            }
        }
    }
}
