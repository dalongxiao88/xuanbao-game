package com.tool.btn;

import org.come.bean.LoginResult;
import org.come.socket.SendMessageUntil;
import org.come.socket.Agreement;
import org.wing.panel.LHMainFrame;
import org.come.until.FormsManagement;
import org.apache.commons.lang.StringUtils;
import com.tool.role.RoleData;
import java.awt.event.MouseEvent;
import com.tool.tcpimg.UIUtils;
import java.awt.Font;
import java.awt.Color;
import org.come.Jpanel.ChooseLiangHaoTypeJpanel;

public class ChooseLhBtn extends MoBanBtn
{
    private ChooseLiangHaoTypeJpanel jpanel;
    private int p;
    
    public ChooseLhBtn(String iconpath, int type, Color[] colors, Font font, String text, Integer typeBtn, ChooseLiangHaoTypeJpanel jpanel) {
        super(iconpath, type, colors);
        this.setText(text);
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
        if (this.getText().equals("炼化")) {
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
        else {
            int w = this.jpanel.getListLh().getSelectedIndex();
            if (w == -1) {
                return;
            }
            String mes = Agreement.getAgreement().setLiangHaoType("LHTYPE=" + String.valueOf(w));
            SendMessageUntil.toServer(mes);
            LoginResult loginResult = RoleData.getRoleData().getLoginResult();
            loginResult.setLianghaotype(Integer.valueOf(w));
            return;
        }
    }
}
