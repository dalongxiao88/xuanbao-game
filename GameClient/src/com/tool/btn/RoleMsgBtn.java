package com.tool.btn;

import org.come.bean.RoleShow;
import java.math.BigDecimal;
import org.come.until.FormsManagement;
import org.come.until.MessagrFlagUntil;
import org.come.Frame.FriendChatMessageJframe;
import org.come.entity.Friendtable;
import org.come.until.UserMessUntil;
import com.tool.imagemonitor.PlayerMonitor;
import org.come.Frame.ZhuFrame;
import com.tool.image.ImageMixDeal;
import com.tool.image.ManimgAttribute;
import org.come.Frame.RoleMsgJframe;
import java.awt.event.MouseEvent;
import java.awt.Font;
import java.awt.Color;
import org.come.bean.Role_bean;
import org.come.Jpanel.RoleMsgJpanel;
import java.awt.event.MouseListener;

public class RoleMsgBtn extends MoBanBtn implements MouseListener
{
    private int caozuo;
    private RoleMsgJpanel jxp;
    public static Role_bean role_bean;
    
    public RoleMsgBtn(String iconpath, int type, Color[] colors, Font font, String text, int caozuo) {
        super(iconpath, type, colors);
        this.caozuo = caozuo;
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
        try {
            BigDecimal id = RoleMsgJframe.getRoleMsgJframe().getJpanel().getId();
            String role = RoleMsgJframe.getRoleMsgJframe().getJpanel().getRole();
            ManimgAttribute manimgAttribute = (ManimgAttribute)ImageMixDeal.Playerimgmap.get(role);
            RoleShow roleShow = null;
            BigDecimal role_id;
            String rolename;
            if (manimgAttribute != null) {
                roleShow = manimgAttribute.getRoleShow();
                role_id = roleShow.getRole_id();
                rolename = roleShow.getRolename();
            }
            else {
                role_id = RoleMsgBtn.role_bean.getRole_id();
                rolename = RoleMsgBtn.role_bean.getRolename();
            }
            ManimgAttribute attribute = ImageMixDeal.huoquLogin(role);
            if (this.caozuo == 1) {
                if (roleShow == null) {
                    ZhuFrame.getZhuJpanel().addPrompt("该玩家已走远了#36");
                    return;
                }
                PlayerMonitor.teamApply(id);
            }
            else if (this.caozuo == 2) {
                if (roleShow == null) {
                    ZhuFrame.getZhuJpanel().addPrompt("该玩家已走远了#36");
                    return;
                }
                PlayerMonitor.transApply(role);
            }
            else if (this.caozuo == 3) {
                PlayerMonitor.addFriend(role_id, role);
            }
            else if (this.caozuo == 4) {
                if (roleShow == null) {
                    ZhuFrame.getZhuJpanel().addPrompt("该玩家已走远了#36");
                    return;
                }
                PlayerMonitor.give(attribute);
            }
            else if (this.caozuo != 5) {
                if (this.caozuo == 6) {
                    if ("锁定".equals(RoleMsgJframe.getRoleMsgJframe().getJpanel().getLbasuod().getText())) {
                        RoleMsgJframe.getRoleMsgJframe().getJpanel().getLbasuod().setText("解锁");
                    }
                    else {
                        RoleMsgJframe.getRoleMsgJframe().getJpanel().getLbasuod().setText("锁定");
                    }
                }
                else if (this.caozuo == 7) {
                    PlayerMonitor.addFriend(role_id, rolename);
                    new Thread(()/*  */ -> {
                        try {
                            Thread.sleep(500L);
                        }
                        catch (InterruptedException ee) {
                            ee.printStackTrace();
                        }
                        for (int i = 0; i < UserMessUntil.getFriendtables().size(); ++i) {
                            Friendtable friend = (Friendtable)UserMessUntil.getFriendtables().get(i);
                            if (friend.getRolename().equals(rolename)) {
                                FriendChatMessageJframe.getFriendChatMessageJframe().getJpanel().showFriend(friend, MessagrFlagUntil.getRichLabel(friend.getRolename()));
                                FormsManagement.showForm(56);
                                return;
                            }
                        }
                        return;
                    }).start();
                }
            }
        }
        catch (Exception exception) {
            ZhuFrame.getZhuJpanel().addPrompt("该玩家已走远了#36");
        }
        if (this.caozuo == 20) {
            if ("锁定".equals(RoleMsgJframe.getRoleMsgJframe().getJpanel().getLbasuod().getText())) {
                RoleMsgJframe.getRoleMsgJframe().getJpanel().getLbasuod().setText("解锁");
            }
            else {
                RoleMsgJframe.getRoleMsgJframe().getJpanel().getLbasuod().setText("锁定");
            }
        }
    }
    
    @Override
    public void mousePressed(MouseEvent e) {
    }
}
