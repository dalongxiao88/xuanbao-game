package com.tool.btn;

import java.util.Map;
import org.come.bean.ConfigureBean;
import org.come.socket.SendMessageUntil;
import org.come.socket.Agreement;
import java.math.BigDecimal;
import org.come.model.Configure;
import org.come.Frame.ZhuFrame;
import org.come.until.UserMessUntil;
import org.come.entity.RoleSummoning;
import org.come.until.UserStallUntil;
import java.awt.event.MouseEvent;
import java.awt.Font;
import java.awt.Color;
import com.tool.tcpimg.UIUtils;
import org.come.Jpanel.DepositListJpanel;
import org.come.Jpanel.TestPetJpanel;

public class DepositBtn extends MoBanBtn
{
    private TestPetJpanel jpanel1;
    private DepositListJpanel jpanel;
    private int p;
    
    public DepositBtn(String iconpath, int type, int p, DepositListJpanel jpanel) {
        super(iconpath, type);
        this.p = p;
        this.jpanel = jpanel;
    }
    
    public DepositBtn(String iconpath, int type, int p, String text, DepositListJpanel jpanel) {
        super(iconpath, type, UIUtils.COLOR_BTNTEXT);
        this.setText(text);
        this.setFont(UIUtils.TEXT_FONT);
        this.setVerticalTextPosition(0);
        this.setHorizontalTextPosition(0);
        this.p = p;
        this.jpanel = jpanel;
    }
    
    public DepositBtn(String iconpath, int type, Color[] colors, Font font, String text, Integer typeBtn, DepositListJpanel jpanel) {
        super(iconpath, type, colors);
        this.setText(text);
        this.setFont(UIUtils.TEXT_HY16);
        this.setVerticalTextPosition(0);
        this.setHorizontalTextPosition(0);
        this.jpanel = jpanel;
    }
    
    public DepositBtn(byte[] iconByte, String fileName, int type, int p, String text, DepositListJpanel jpanel) {
        super(iconByte, fileName, type, UIUtils.COLOR_BTNPUTONG);
        this.setText(text);
        this.setFont(UIUtils.TEXT_FONT);
        this.setVerticalTextPosition(0);
        this.setHorizontalTextPosition(0);
        this.p = p;
        this.jpanel = jpanel;
    }

    public DepositBtn(byte[] iconByte, String fileName, int type, Color[] colors, Font font, String text, Integer typeBtn, DepositListJpanel jpanel) {
        super(iconByte, fileName, type, colors);
        this.setText(text);
        this.setFont(UIUtils.TEXT_HY88);
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
        if (UserStallUntil.isStall()) {
            return;
        }
        int w = this.jpanel.getListpet().getSelectedIndex();
        if (w == -1) {
            return;
        }
        RoleSummoning roleSummoning = (RoleSummoning)UserMessUntil.getDepositPetListTable().get(w);
        if (roleSummoning == null) {
            ZhuFrame.getZhuJpanel().addPrompt("要取出的召唤兽不存在！");
            return;
        }
        ConfigureBean allConfigure = UserMessUntil.getConfigureBean();
        Map<BigDecimal, Configure> item = allConfigure.getAllConfigure();
        Configure configure = (Configure)item.get(new BigDecimal(1));
        if (UserMessUntil.getPetListTable() != null && UserMessUntil.getPetListTable().size() >= Integer.parseInt(configure.getXdzhssx())) {
            ZhuFrame.getZhuJpanel().addPrompt2("您的召唤兽可携带的数量已满！！！");
            return;
        }
        String mes = Agreement.getAgreement().petDepositAction("retrieve=" + roleSummoning.getSid());
        SendMessageUntil.toServer(mes);
    }
}
