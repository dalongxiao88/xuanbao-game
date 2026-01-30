package com.tool.btn;

import com.tool.image.ImageMixDeal;
import com.tool.role.RoleData;
import com.tool.tcpimg.UIUtils;
import come.tool.JDialog.TiShiUtil;
import org.come.Frame.*;
import org.come.Jpanel.*;
import org.come.bean.ChongjipackBean;
import org.come.bean.LoginResult;
import org.come.bean.PayvipBean;
import org.come.bean.XXGDBean;
import org.come.control.TestMain;
import org.come.socket.Agreement;
import org.come.socket.SendMessageUntil;
import org.come.summonequip.JframeCashRewardsMain;
import org.come.until.FormsManagement;
import org.come.until.GoodsListFromServerUntil;
import org.come.until.Util;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.util.List;

public class XyCjBtn extends MoBanBtn {
    public XyCjBtn(String iconpath, int type, String text) {
        super(iconpath, type, UIUtils.COLOR_BTNPUTONG);
        this.setText(text);
        this.setFont(UIUtils.TEXT_FONT82);
        this.setVerticalTextPosition(0);
        this.setHorizontalTextPosition(0);
    }
    public XyCjBtn(Boolean b,String iconpath, int type, String text,Color[] COLOR_WHITE) {
        super(iconpath, type,COLOR_WHITE);
        this.setText(text);
        this.setFont(UIUtils.TEXT_FONT82);
        this.setVerticalTextPosition(0);
        this.setHorizontalTextPosition(0);
    }
    public XyCjBtn(String iconpath, int type, String text,String sm,String jm) {
        super(iconpath, type, UIUtils.COLOR_WHITE);
        this.setText(text);
        this.setFont(UIUtils.TEXT_FONT82);
        this.setVerticalTextPosition(0);
        this.setHorizontalTextPosition(0);
    }
    public XyCjBtn(String iconpath, int type, String text,String sm) {
        super(iconpath, type, UIUtils.COLOR_BLACK);
        this.setText(text);
        if(sm!=null) {
            this.setText(sm);
        }
        this.setFont( new Font("楷体", Font.BOLD , 17));
        this.setVerticalTextPosition(0);
        this.setHorizontalTextPosition(0);
    }
    public XyCjBtn(String iconpath, int type, Color[] colors,String text,String sm) {
        super(iconpath, type, UIUtils.COLOR_WHITE);
        this.setText(text);
        this.setFont(UIUtils.TEXT_HY14);
        this.setVerticalTextPosition(0);
        this.setHorizontalTextPosition(0);
    }
    public XyCjBtn(String iconpath, int type, Color[] colors,String text,String sm, String jm) {
        super(iconpath, type, UIUtils.COLOR_BTNPUTONG2);
        this.setText(text);
        this.setFont(UIUtils.TEXT_HY14);
        this.setVerticalTextPosition(0);
        this.setHorizontalTextPosition(0);
    }
    public XyCjBtn(String iconpath, int type,String text, Color[] colors,String sm, String jm) {
        super(iconpath, type, UIUtils.COLOR_BTNPUTONG2);
        this.setText(text);
        this.setFont(UIUtils.TEXT_FONT42);
        this.setVerticalTextPosition(0);
        this.setHorizontalTextPosition(0);
    }
    public void chooseyes() {
    }

    public void chooseno() {
    }

    public void nochoose(MouseEvent e) {
        if (this.getText().equals("单抽一次")) {
            String sendMes = Agreement.getFiveMsgAgreement("C10|0|4");
            SendMessageUntil.toServer(sendMes);
        } else if (this.getText().equals("连抽十次")) {
            String sendMes = Agreement.getFiveMsgAgreement("C10|1|4");
            SendMessageUntil.toServer(sendMes);
        }else{
            String sendMes = Agreement.getFiveMsgAgreement("F99999");
            SendMessageUntil.toServer(sendMes);
        }
    }
}
