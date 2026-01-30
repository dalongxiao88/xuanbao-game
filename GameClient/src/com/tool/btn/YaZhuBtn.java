package com.tool.btn;

import org.come.bean.LoginResult;
import org.come.socket.SendMessageUntil;
import org.come.socket.Agreement;
import org.come.Frame.ZhuFrame;
import org.apache.commons.lang.StringUtils;
import com.tool.role.RoleData;
import java.awt.event.MouseEvent;
import java.awt.Font;
import java.awt.Color;
import org.come.Jpanel.YaZhuJpanel;

public class YaZhuBtn extends MoBanBtn
{
    private int caoZuo;
    private YaZhuJpanel yaZhuJpanel;
    
    public YaZhuBtn(String iconpath, int type, String text, Color[] colors, Font font, int caoZuo, YaZhuJpanel yaZhuJpanel) {
        super(iconpath, type, colors);
        this.caoZuo = caoZuo;
        this.yaZhuJpanel = yaZhuJpanel;
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
        if (this.caoZuo == 0) {
            int v = RoleData.getRoleData().getLoginResult().getScoretype("解卦灵力").intValue();
            v = ((v > YaZhuJpanel.max) ? YaZhuJpanel.max : v);
            this.yaZhuJpanel.setLingText(v);
        }
        else {
            String v2 = this.yaZhuJpanel.getLingText();
            if (StringUtils.isNotBlank(v2)) {
                int sum = Integer.parseInt(v2);
                LoginResult loginResult = RoleData.getRoleData().getLoginResult();
                if (sum > loginResult.getScoretype("解卦灵力").intValue()) {
                    ZhuFrame.getZhuJpanel().addPrompt2("灵力不够");
                    return;
                }
                int t = this.yaZhuJpanel.getType();
                SendMessageUntil.toServer(Agreement.getAgreement().JieGuaAgreement("2=" + t + "_" + v2));
            }
        }
    }
}
