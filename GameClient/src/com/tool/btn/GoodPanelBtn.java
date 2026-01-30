package com.tool.btn;

import org.come.bean.LoginResult;
import org.come.Jpanel.FrameMessageChangeJpanel;
import com.tool.image.ImageMixDeal;
import come.tool.JDialog.TiShiUtil;
import org.come.Frame.OptionsJframe;
import org.come.summonequip.JframeCashRewardsMain;
import org.come.until.UserStallUntil;
import org.come.until.Util;
import org.come.until.GoodsListFromServerUntil;
import org.come.Jpanel.TestpackJapnel;
import org.come.Frame.ZhuFrame;
import com.tool.role.RoleData;
import org.come.Jpanel.TeststateJpanel;
import org.come.until.FormsManagement;
import java.awt.event.MouseEvent;
import java.awt.Color;
import com.tool.tcpimg.UIUtils;

public class GoodPanelBtn extends MoBanBtn
{
    public GoodPanelBtn(String iconpath, int type, String text) {
        super(iconpath, type, UIUtils.COLOR_BTNTEXT);
        this.setText(text);
        this.setFont(UIUtils.TEXT_FONT82);
        this.setVerticalTextPosition(0);
        this.setHorizontalTextPosition(0);
    }
    
    public GoodPanelBtn(String iconpath, int type, String text, int num) {
        super(iconpath, type, UIUtils.COLOR_RED_BTNTEXT);
        this.setText(text);
        this.setFont(UIUtils.TEXT_FONT82);
        this.setVerticalTextPosition(0);
        this.setHorizontalTextPosition(0);
    }
    
    public GoodPanelBtn(String iconpath, int type, String text, int num, String sm) {
        super(iconpath, type, UIUtils.COLOR_BTNPUTONG2);
        this.setText(text);
        this.setFont(UIUtils.TEXT_FONT_17);
        this.setVerticalTextPosition(0);
        this.setHorizontalTextPosition(0);
    }
    
    public GoodPanelBtn(Boolean b, String iconpath, int type, String text) {
        super(iconpath, type, UIUtils.COLOR_WHITE);
        this.setText(text);
        this.setFont(UIUtils.TEXT_FONT82);
        this.setVerticalTextPosition(0);
        this.setHorizontalTextPosition(0);
    }
    
    public GoodPanelBtn(String iconpath, int type, String text, String sm, String jm) {
        super(iconpath, type, UIUtils.COLOR_WHITE);
        this.setText(text);
        this.setFont(UIUtils.TEXT_FONT82);
        this.setVerticalTextPosition(0);
        this.setHorizontalTextPosition(0);
    }
    
    public GoodPanelBtn(String iconpath, int type, String text, String sm) {
        super(iconpath, type, UIUtils.COLOR_BLACK);
        this.setText(text);
        if (sm != null) {
            this.setText(sm);
        }
        this.setFont(UIUtils.TEXT_HY88);
        this.setVerticalTextPosition(0);
        this.setHorizontalTextPosition(0);
    }
    
    public GoodPanelBtn(String iconpath, int type, Color[] colors, String text, String sm) {
        super(iconpath, type, UIUtils.COLOR_WHITE);
        this.setText(text);
        this.setFont(UIUtils.TEXT_HY16s);
        this.setVerticalTextPosition(0);
        this.setHorizontalTextPosition(0);
    }
    
    public GoodPanelBtn(String iconpath, int type, Color[] colors, String text, String sm, String jm) {
        super(iconpath, type, UIUtils.COLOR_BTNPUTONG2);
        this.setText(text);
        this.setFont(UIUtils.TEXT_HY16s);
        this.setVerticalTextPosition(0);
        this.setHorizontalTextPosition(0);
    }
    
    public GoodPanelBtn(String iconpath, int type, String text, Color[] colors, String sm, String jm) {
        super(iconpath, type, UIUtils.COLOR_BTNPUTONG2);
        this.setText(text);
        this.setFont(UIUtils.TEXT_FONT_17);
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
        if (this.getText().equals("设") || this.getText().equals("设置密码")) {
            FormsManagement.showForm(32);
        }
        else if (this.getText().equals("改") || this.getText().equals("修改密码")) {
            FormsManagement.showForm(21);
        }
        else if (!this.getText().equals("锁") && !this.getText().equals("解")) {
            if (this.getText().equals("星 录")) {
                TeststateJpanel.showIsTeamBtnS(false, 1);
                FormsManagement.showForm(95);
            }
            else if (this.getText().equals("星 盘")) {
                TeststateJpanel.showIsTeamBtnS(false, 1);
                LoginResult login = RoleData.getRoleData().getLoginResult();
                if (login.getTurnAround() < 3) {
                    ZhuFrame.getZhuJpanel().addPrompt2("三转以上才能修炼星盘");
                    return;
                }
                FormsManagement.showForm(122);
            }
            else if (this.getText().equals("当前物品栏整理")) {
                TestpackJapnel.showIsTeamBtnS(false, 1);
                GoodsListFromServerUntil.arrange();
            }
            else if (this.getText().equals("整")) {
                GoodsListFromServerUntil.arrange();
            }
            else if (this.getText().equals("摆摊")) {
                if (Util.isCanBuyOrno1()) {
                    return;
                }
                UserStallUntil.startStall();
            }
            else if (this.getText().equals("勇者")) {
                if (Util.isCanBuyOrno()) {
                    return;
                }
                JframeCashRewardsMain.getJframeCashRewardsMain().getJpanelCashRewardsMain().setGoodsType(3);
                FormsManagement.showForm(92);
            }
            else if (this.getText().equals("详")) {
                FormsManagement.showForm(6222);
            }
            else if (this.getText().equals("全部物品栏整理")) {
                TestpackJapnel.showIsTeamBtnS(false, 1);
                GoodsListFromServerUntil.allArrange();
            }
            else if (this.getText().equals("当前物品栏清理")) {
                TestpackJapnel.showIsTeamBtnS(false, 1);
                OptionsJframe.getOptionsJframe().getOptionsJpanel().showBox(TiShiUtil.ClearAllGood, null, "#W此功能为#R一键清包#W，会将#Y当前打开的物品栏#W中的非加锁物品全部丢弃，你确定要这么操作吗? #R（注意，物品丢弃后无法找回！）");
            }
            else if (this.getText().equals("回收师贡")) {
                OptionsJframe.getOptionsJframe().getOptionsJpanel().showBox(TiShiUtil.SellAllGood, null, "#W此功能为#R一键回收#W，会将#Y当前打开的物品栏#W中的非加锁且可回收物品全部回收兑换成师门，你确定要这么操作吗? #R（注意，物品回收后不可找回！建议参考新手指引-物品回收列表）");
            }
        }
        else if (Util.canBuyOrno) {
            Util.canBuyOrno = false;
            ImageMixDeal.userimg.Dialogue("背包已加锁！");
            FrameMessageChangeJpanel.addtext(6, "背包已加锁！", null, null);
            this.setText("解");
        }
        else if (RoleData.getRoleData().getLoginResult().getPassword() == null) {
            FormsManagement.showForm(32);
        }
        else {
            FormsManagement.showForm(33);
        }
    }
}
