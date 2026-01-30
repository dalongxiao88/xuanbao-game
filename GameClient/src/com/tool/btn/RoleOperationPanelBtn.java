package com.tool.btn;

import org.come.Frame.SetPassJfram;
import org.come.Frame.TestpackJframe;
import org.come.Frame.UnLockJframe;
import org.come.Jpanel.FrameMessageChangeJpanel;
import org.come.Frame.ChangePasswordJframe;
import org.come.Frame.ZhuFrame;
import com.tool.role.RoleTX;
import com.tool.role.RoleProperty;
import com.tool.role.RoleData;
import org.come.socket.SendMessageUntil;
import org.come.socket.Agreement;
import com.tool.image.ImageMixDeal;
import org.come.until.FormsManagement;
import org.come.mouslisten.SurePawnMouslisten;
import org.come.Frame.GiveJframe;
import org.come.until.Util;
import come.tool.Fighting.FightingMixDeal;
import java.awt.event.MouseEvent;
import java.awt.Font;
import java.awt.Color;
import com.tool.tcpimg.UIUtils;
import org.come.Jpanel.Change_titleJpanel;

public class RoleOperationPanelBtn extends MoBanBtn
{
    private Change_titleJpanel change_titleJpanel;
    
    public RoleOperationPanelBtn(String iconpath, int type, String text, Change_titleJpanel change_titleJpanel) {
        super(iconpath, type, UIUtils.COLOR_BTNPUTONG);
        this.setText(text);
        this.setFont(UIUtils.TEXT_HY88);
        this.setVerticalTextPosition(0);
        this.setHorizontalTextPosition(0);
        this.change_titleJpanel = change_titleJpanel;
    }
    
    public RoleOperationPanelBtn(String iconpath, int type, String text, Change_titleJpanel change_titleJpanel, String SM) {
        super(iconpath, type, UIUtils.COLOR_BLACK);
        this.setText(text);
        this.setFont(UIUtils.TEXT_HY88);
        this.setVerticalTextPosition(0);
        this.setHorizontalTextPosition(0);
        this.change_titleJpanel = change_titleJpanel;
    }
    
    public RoleOperationPanelBtn(String iconpath, int type, Color[] colors, Font font, String text) {
        super(iconpath, type, colors);
        this.setText(text);
        this.setFont(font);
        this.setVerticalTextPosition(0);
        this.setHorizontalTextPosition(0);
    }
    
    @Override
    public void chooseyes() {
    }
    
    @Override
    public void chooseno() {
    }
    
    @Override
    public void nochoose(MouseEvent e) {
        if (this.getText().equals("更改称谓") && FightingMixDeal.State == 0) {
            this.sureChangeTtile();
        }
        else if (this.getText().equals("隐藏称谓") && FightingMixDeal.State == 0) {
            this.hideTtile();
        }
        else if (this.getText().equals("显示称谓") && FightingMixDeal.State == 0) {
            this.hideTtile();
        }
        else if (this.getText().equals("给予") && FightingMixDeal.State == 0) {
            if (Util.isCanBuyOrno()) {
                return;
            }
            GiveJframe.getGivejframe().getGivejpanel().giveMethod();
        }
        else if (this.getText().equals("修改密码") && FightingMixDeal.State == 0) {
            changePassword();
        }
        else if (this.getText().equals("解  锁") && FightingMixDeal.State == 0) {
            this.unLockPack();
        }
        else if (this.getText().equals("典 当") && FightingMixDeal.State == 0) {
            SurePawnMouslisten.surePawn();
        }
        else if (this.getText().equals("设置密码") && FightingMixDeal.State == 0) {
            this.setPackPwd();
        }
        else if (this.getText().equals("投放")) {
            int state = FightingMixDeal.State;
        }
        else if (this.getText().equals("取  消")) {
            FormsManagement.HideForm(33);
        }
    }
    
    public void sureChangeTtile() {
        if (Util.ditubianma != 3315 && (ImageMixDeal.scene == null || ImageMixDeal.scene.getSceneId() != 1011)) {
            if (this.change_titleJpanel.getListname().getSelectedValue() != null) {
                String value = (String)this.change_titleJpanel.getListname().getSelectedValue();
                String sendmes = Agreement.getAgreement().TitleChangeAgreement(value);
                SendMessageUntil.toServer(sendmes);
                this.change_titleJpanel.getLabnamech().setText(value);
                ImageMixDeal.userimg.getRoleShow().setTitle(value);
                RoleData.getRoleData().getLoginResult().setTitle(value);
                RoleProperty.ResetEw();
                RoleTX.getRoleTX().skin();
            }
            else {
                ZhuFrame.getZhuJpanel().addPrompt2("请选择您要更改的称谓！");
            }
        }
        else {
            ZhuFrame.getZhuJpanel().addPrompt("该场景不能更换称谓");
        }
    }
    
    public void hideTtile() {
        if (this.change_titleJpanel.getBtnhide().getText().equals("隐藏称谓")) {
            Util.hideTitle = false;
            this.change_titleJpanel.getBtnhide().setText("显示称谓");
        }
        else if (this.change_titleJpanel.getBtnhide().getText().equals("显示称谓")) {
            Util.hideTitle = true;
            this.change_titleJpanel.getBtnhide().setText("隐藏称谓");
        }
        FormsManagement.HideForm(10);
    }
    
    public static void changePassword() {
        if (!ChangePasswordJframe.getChangePasswordJframe().getChangePasswordJpanel().getOldpassword().getText().equals("") && !ChangePasswordJframe.getChangePasswordJframe().getChangePasswordJpanel().getNewpassword().getText().equals("")) {
            if (RoleData.getRoleData().getLoginResult().getPassword().equals(ChangePasswordJframe.getChangePasswordJframe().getChangePasswordJpanel().getOldpassword().getText())) {
                RoleData.getRoleData().getLoginResult().setPassword(ChangePasswordJframe.getChangePasswordJframe().getChangePasswordJpanel().getNewpassword().getText().trim());
                ZhuFrame.getZhuJpanel().addPrompt2("背包密码修改成功!!!");
                ChangePasswordJframe.getChangePasswordJframe().getChangePasswordJpanel().getOldpassword().setText("");
                ChangePasswordJframe.getChangePasswordJframe().getChangePasswordJpanel().getNewpassword().setText("");
                FormsManagement.HideForm(21);
                String mes = Agreement.getAgreement().rolechangeAgreement("1" + RoleData.getRoleData().getLoginResult().getPassword());
                SendMessageUntil.toServer(mes);
            }
            else {
                FrameMessageChangeJpanel.addtext(6, "原始密码错误", null, null);
                ChangePasswordJframe.getChangePasswordJframe().getChangePasswordJpanel().getOldpassword().setText("");
                ChangePasswordJframe.getChangePasswordJframe().getChangePasswordJpanel().getNewpassword().setText("");
                FormsManagement.HideForm(21);
            }
        }
        else {
            FrameMessageChangeJpanel.addtext(6, "新密码或者旧密码格式错误！", null, null);
        }
    }
    
    public void unLockPack() {
        if (UnLockJframe.getUnLockJframe().getUnLockJpanel().getTextUnlockpwd().equals("")) {
            FrameMessageChangeJpanel.addtext(6, "请输入解锁密码!！", null, null);
        }
        else if (UnLockJframe.getUnLockJframe().getUnLockJpanel().getTextUnlockpwd().getText().equals(RoleData.getRoleData().getLoginResult().getPassword())) {
            Util.canBuyOrno = true;
            FrameMessageChangeJpanel.addtext(6, "背包解锁成功", null, null);
            UnLockJframe.getUnLockJframe().getUnLockJpanel().getTextUnlockpwd().setText("");
            ZhuFrame.getZhuJpanel().addPrompt("解锁成功");
            FormsManagement.HideForm(33);
            TestpackJframe.getTestpackJframe().getJpac().getBtnunlock().setText("锁");
        }
        else {
            FrameMessageChangeJpanel.addtext(6, "背包解锁密码错误！！", null, null);
            UnLockJframe.getUnLockJframe().getUnLockJpanel().getTextUnlockpwd().setText("");
            ZhuFrame.getZhuJpanel().addPrompt("密码错误！！！");
            FormsManagement.HideForm(33);
        }
    }
    
    public void setPackPwd() {
        if (!SetPassJfram.getSetPassJframe().getSetPasswordJpanel().getPassAreaText().getText().equals("")) {
            TestpackJframe.getTestpackJframe().getJpac().getBtnchangepwd().setText("改");
            TestpackJframe.getTestpackJframe().getJpac().repaint();
            RoleData.getRoleData().getLoginResult().setPassword(SetPassJfram.getSetPassJframe().getSetPasswordJpanel().getPassAreaText().getText().trim());
            ZhuFrame.getZhuJpanel().addPrompt2("密码设置成功!!!");
            FormsManagement.HideForm(32);
            String mes = Agreement.getAgreement().rolechangeAgreement("1" + RoleData.getRoleData().getLoginResult().getPassword());
            SendMessageUntil.toServer(mes);
        }
    }
}
