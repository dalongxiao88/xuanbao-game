package com.tool.btn;

import com.tool.PanelDisplay.PetPanelShow;
import org.come.until.MessagrFlagUntil;
import org.come.bean.FightOperation;
import come.tool.Fighting.Fightingimage;
import org.come.Frame.SkillMsgJframe;
import org.come.until.FormsManagement;
import org.come.Jpanel.ZhuJpanel;
import org.come.bean.RoleShow;
import org.come.until.Music;
import org.come.bean.NChatBean;
import org.come.Frame.ZhuFrame;
import com.tool.image.ImageMixDeal;
import come.tool.Fighting.FightingEvents;
import org.come.Jpanel.ExpAddMapJpanel;
import org.come.socket.SendMessageUntil;
import org.come.until.GsonUtil;
import org.come.socket.Agreement;
import com.tool.imagemonitor.FightingMonitor;
import come.tool.Fighting.FightingMixDeal;
import java.awt.event.MouseEvent;
import java.awt.Font;
import java.awt.Color;

public class FightingBtn extends MoBanBtn
{
    int btntype;
    
    public FightingBtn(String iconpath, int type, Color[] colors, Font font, String text, int btntype) {
        super(iconpath, type, colors);
        this.setText(text);
        this.setFont(font);
        this.setVerticalTextPosition(0);
        this.setHorizontalTextPosition(0);
        this.btntype = btntype;
    }
    
    @Override
    public void chooseyes() {
    }
    
    @Override
    public void chooseno() {
    }
    
    @Override
    public void nochoose(MouseEvent e) {
        this.btnjt();
    }
    
    public void btnjt() {
        switch (this.btntype) {
            case 0: {
                Btnzidong();
                break;
            }
            case 1: {
                Btnfashu();
                break;
            }
            case 2: {
                this.Btndaoju();
                break;
            }
            case 3: {
                Btnfangyu();
                break;
            }
            case 4: {
                Btnbaohu();
                break;
            }
            case 5: {
                Btnzhaohuan();
                break;
            }
            case 6: {
                Btnzhaohui();
                break;
            }
            case 7: {
                Btnbuzhua();
                break;
            }
            case 8: {
                Btntaopao();
                break;
            }
            case 9: {
                if (FightingMixDeal.State == 2 || FightingMixDeal.State == 3) {
                    FightingEvents fightingEvents = FightingMonitor.CheHuiFightingEvents();
                    if (FightingMixDeal.camp == -1) {
                        return;
                    }
                    fightingEvents.setFightingsum(FightingMixDeal.FightingNumber);
                    fightingEvents.setCurrentRound(FightingMixDeal.CurrentRound);
                    String sendMes = Agreement.getAgreement().battleroundAgreement(GsonUtil.getGsonUtil().getgson().toJson(fightingEvents));
                    SendMessageUntil.toServer(sendMes);
                    break;
                }
                else {
                    break;
                }
            }
            case 11: {
                qiangtui();
                break;
            }
            case 30034: {
                ExpAddMapJpanel.iWantToFlyGo(30034);
                break;
            }
        }
    }
    
    public static void qiangtui() {
        RoleShow roleShow = ImageMixDeal.userimg.getRoleShow();
        if (roleShow.getTroop_id() != null && ImageMixDeal.userimg.getRoleShow().getCaptian() == 0) {
            ZhuFrame.getZhuJpanel().addPrompt2("不是队长无法进行操作#76");
            return;
        }
        if (FightingMixDeal.CurrentRound <= 2) {//强退修改回合的地方
            ZhuFrame.getZhuJpanel().addPrompt2("2回合后才能进行强退操作离开战斗！");
            return;
        }
        String[] teams = ImageMixDeal.userimg.getRoleShow().getTeam().split("\\|");
        if (teams[0].equals(ImageMixDeal.userimg.getRoleShow().getRolename())) {
            NChatBean bean = new NChatBean();
            bean.setMessage("#躲闪");
            String value = Agreement.getAgreement().chatAgreement(GsonUtil.getGsonUtil().getgson().toJson(bean));
            SendMessageUntil.toServer(value);
            Music.addyinxiao("逃跑成功.mp3");
        }
    }
    
    public static void Btnzidong() {
        ZhuFrame.getZhuJpanel();
        if (ZhuJpanel.getZidong().getText().equals("取消")) {
            ZhuFrame.getZhuJpanel();
            ZhuJpanel.getZidong().setText("自动");
            FightingMixDeal.zdhh = 0;
            FormsManagement.HideForm(634);
        }
        else {
            ZhuFrame.getZhuJpanel();
            if (ZhuJpanel.getZidong().getText().equals("自动")) {
                FightingMixDeal.zdhh = 9999;
                ZhuFrame.getZhuJpanel();
                ZhuJpanel.getZidong().setText("取消");
                FormsManagement.showForm(634);
            }
            else {
                ZhuFrame.getZhuJpanel();
                if (ZhuJpanel.getZidong().getText().equals("离开")) {
                    String[] teams = ImageMixDeal.userimg.getRoleShow().getTeam().split("\\|");
                    if (teams[0].equals(ImageMixDeal.userimg.getRoleShow().getRolename())) {
                        String fightMes = Agreement.getAgreement().battleConnectionAgreement("-1");
                        SendMessageUntil.toServer(fightMes);
                    }
                    else {
                        ZhuFrame.getZhuJpanel().addPrompt2("队员没法主动离开观战");
                    }
                }
            }
        }
    }
    
    public static void Btnfashu() {
        if (!FormsManagement.getInternalForm(34).getFrame().isVisible()) {
            if (FightingMixDeal.State == 1 || FightingMixDeal.State == 2) {
                Fightingimage fightingimage = FightingMixDeal.getdata(FightingMixDeal.State - 1);
                SkillMsgJframe.getSkillMsgJframe().getSkillMsgJpaenl().showSkill(fightingimage.getFightingManData().cxxx("技能"), FightingMixDeal.State);
            }
        }
        else {
            FormsManagement.HideForm(46);
            FormsManagement.HideForm(34);
            FormsManagement.HideForm(631);
            FormsManagement.HideForm(603);
        }
    }
    
    public void Btndaoju() {
        if (!FormsManagement.getInternalForm(2).getFrame().isVisible()) {
            FormsManagement.showForm(2);
        }
        else {
            FormsManagement.HideForm(2);
        }
    }
    
    public static void Btnfangyu() {
        FightOperation operation = FightingMonitor.getOperation();
        operation.Record(-1, -1, 5, null);
        FightingMonitor.execution(operation);
    }
    
    public static void Btnbaohu() {
        FightingMonitor.mousesname = "保护";
        FightingMonitor.mousestate = 3;
        MessagrFlagUntil.setMouse(MessagrFlagUntil.MOUSE8);
        ZhuFrame.getZhuJpanel().HideBeastBtn();
    }
    
    public static void Btnzhaohuan() {
        if (!FormsManagement.getframe(710).isVisible()) {
            if (FightingMixDeal.State == 1) {
                PetPanelShow.summonShow();
            }
        }
        else {
            FormsManagement.HideForm(710);
        }
    }
    
    public static void Btnzhaohui() {
        if (FightingMixDeal.MyBeastLifeAndDeath()) {
            FightingMonitor.FightingOperation(SpellGenerate("召回"));
            if (FightingMixDeal.State == 1) {
                if (FightingMixDeal.MyBeastLifeAndDeath()) {
                    FightingMixDeal.changeState(2);
                    ZhuFrame.getZhuJpanel().HideBeastBtn();
                    ZhuFrame.getZhuJpanel().ShowBeastBtn();
                }
                else {
                    FightingMixDeal.changeState(3);
                    FightingMixDeal.RoundFighting();
                }
            }
        }
    }
    
    public static void Btnbuzhua() {
        FightingMonitor.mousestate = 4;
        FightingMonitor.mousesname = "捕捉";
        MessagrFlagUntil.setMouse(MessagrFlagUntil.MOUSE7);
        ZhuFrame.getZhuJpanel().HideBeastBtn();
    }
    
    public static void Btntaopao() {
        FightOperation operation = FightingMonitor.getOperation();
        operation.Record(-1, -1, 6, null);
        FightingMonitor.execution(operation);
    }
    
    @Override
    public void mouseEntered(MouseEvent e) {
        MessagrFlagUntil.setMouse(MessagrFlagUntil.MOUSE12);
    }
    
    @Override
    public void mouseExited(MouseEvent e) {
        MessagrFlagUntil.setMouse(MessagrFlagUntil.MOUSE1);
    }
    
    public static FightingEvents SpellGenerate(String type) {
        FightingEvents fEvents = new FightingEvents();
        if (FightingMixDeal.State == 1) {
            fEvents.setOriginator(FightingMixDeal.FightingState(type, 0));
        }
        else {
            fEvents.setOriginator(FightingMixDeal.FightingState(type, 1));
        }
        return fEvents;
    }
}
