package com.tool.btn;

import org.come.bean.LoginResult;
import org.wing.panel.LHMainFrame;
import org.apache.commons.lang.StringUtils;
import come.tool.JDialog.TiShiUtil;
import org.come.Frame.OptionsJframe;
import org.come.Frame.ZhuFrame;
import com.tool.role.RoleData;
import org.come.socket.SendMessageUntil;
import org.come.socket.Agreement;
import org.come.until.FormsManagement;
import java.awt.event.MouseEvent;
import com.tool.tcpimg.UIUtils;
import java.awt.Font;
import java.awt.Color;
import org.come.Jpanel.MyLiangHaoJpanel;

public class LiangHaoBtn extends MoBanBtn
{
    int typeBtn;
    private MyLiangHaoJpanel jpanel;
    private int p;
    
    public LiangHaoBtn(String iconpath, int type, Color[] colors, Font font, String text, Integer typeBtn, MyLiangHaoJpanel jpanel) {
        super(iconpath, type, colors);
        this.setText(text);
        this.typeBtn = (int)typeBtn;
        this.setFont(UIUtils.TEXT_HY16);
        this.setVerticalTextPosition(0);
        this.setHorizontalTextPosition(0);
        this.jpanel = jpanel;
    }
    
    @Override
    public void chooseyes() {
    }
    
    @Override
    public void chooseno() {
    }
    
    @Override
    public void nochoose(MouseEvent e) {
        if (this.typeBtn == 1) {
            FormsManagement.showForm(641);
            String mes2 = Agreement.getAgreement().selllianghaoAgreement("SELLLIST");
            SendMessageUntil.toServer(mes2);
        }
        else if (this.typeBtn == 2) {
            LoginResult login = RoleData.getRoleData().getLoginResult();
            if (login.getLianghaoexpire() == null) {
                ZhuFrame.getZhuJpanel().addPrompt2("请先购买靓号！#32");
                return;
            }
            FormsManagement.showForm(705);
        }
        else if (this.typeBtn != 3) {
            if (this.typeBtn == 4) {
                FormsManagement.showForm(706);
            }
            else if (this.typeBtn == 5) {
                LoginResult login = RoleData.getRoleData().getLoginResult();
                OptionsJframe.getOptionsJframe().getOptionsJpanel().showBox(TiShiUtil.droplh, null, "#W您确定要将靓号:#G" + login.getLiangHao() + " #W捐赠吗?  #R捐赠后无法找回三思而后行");
            }
            else if (this.typeBtn == 6) {
                LoginResult login = RoleData.getRoleData().getLoginResult();
                if (login.getLianghaoexpire() == null) {
                    ZhuFrame.getZhuJpanel().addPrompt2("请先购买靓号！#32");
                    return;
                }
                if (this.getText().equals("炼化属性")) {
                    int type = 6;
                    String lianghaoValue = RoleData.getRoleData().getLoginResult().getLianghaoValue();
                    if (StringUtils.isNotBlank(lianghaoValue) && lianghaoValue.contains("@")) {
                        String[] split = lianghaoValue.split("@");
                        type = Integer.parseInt(split[0]);
                    }
                    else {
                        type = 6;
                    }
                    FormsManagement.showForm(866);
                    LHMainFrame.LHMainFrame().getLhMainPanel().changLeftType(type);
                    return;
                }
            }
        }
    }
}
