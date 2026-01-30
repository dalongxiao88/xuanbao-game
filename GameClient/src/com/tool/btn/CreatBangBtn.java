package com.tool.btn;

import java.math.BigDecimal;
import org.come.until.FormsManagement;
import org.come.until.GsonUtil;
import org.come.until.GoodsListFromServerUntil;
import org.come.socket.SendMessageUntil;
import org.come.socket.Agreement;
import org.come.entity.Gang;
import org.come.Frame.ZhuFrame;
import com.tool.image.ImageMixDeal;
import java.awt.event.MouseEvent;
import com.tool.tcpimg.UIUtils;
import org.come.Jpanel.JoinBangJpanel;
import org.come.Jpanel.CreatBangJpanel;

public class CreatBangBtn extends MoBanBtn
{
    private CreatBangJpanel bangJpanel;
    private JoinBangJpanel joinBangJpanel;
    
    public CreatBangBtn(String iconpath, int type, String text, CreatBangJpanel bangJpanel) {
        super(iconpath, type, UIUtils.COLOR_BTNPUTONG);
        this.setText(text);
        this.setFont(UIUtils.TEXT_HY88);
        this.setVerticalTextPosition(0);
        this.setHorizontalTextPosition(0);
        this.bangJpanel = bangJpanel;
    }
    
    public CreatBangBtn(String iconpath, int type, String text, CreatBangJpanel bangJpanel, String sm) {
        super(iconpath, type, UIUtils.COLOR_BLACK);
        this.setText(text);
        this.setFont(UIUtils.TEXT_HY88);
        this.setVerticalTextPosition(0);
        this.setHorizontalTextPosition(0);
        this.bangJpanel = bangJpanel;
    }
    
    public CreatBangBtn(String iconpath, int type, String text, JoinBangJpanel joinBangJpanel) {
        super(iconpath, type, UIUtils.COLOR_BTNPUTONG);
        this.setText(text);
        this.setFont(UIUtils.TEXT_HY88);
        this.setVerticalTextPosition(0);
        this.setHorizontalTextPosition(0);
        this.joinBangJpanel = joinBangJpanel;
    }
    
    public CreatBangBtn(String iconpath, int type, String text, JoinBangJpanel joinBangJpanel, String sm) {
        super(iconpath, type, UIUtils.COLOR_BLACK);
        this.setText(text);
        this.setFont(UIUtils.TEXT_HY88);
        this.setVerticalTextPosition(0);
        this.setHorizontalTextPosition(0);
        this.joinBangJpanel = joinBangJpanel;
    }
    
    @Override
    public void chooseyes() {
    }
    
    @Override
    public void chooseno() {
    }
    
    @Override
    public void nochoose(MouseEvent e) {
        BigDecimal gangid = ImageMixDeal.userimg.getRoleShow().getGang_id();
        if (gangid != null && gangid.intValue() != 0) {
            ZhuFrame.getZhuJpanel().addPrompt2("你已有帮派");
        }
        else if (this.joinBangJpanel != null) {
            int cx = this.joinBangJpanel.getTableMsg().getSelectedRow();
            if (cx == -1) {
                ZhuFrame.getZhuJpanel().addPrompt2("你未选中要加入的帮派");
                return;
            }
            Gang gangCreate = (Gang)this.joinBangJpanel.getGangs().get(cx);
            String senmes = Agreement.getAgreement().GangApplyAgreement(gangCreate.getGangid().toString());
            SendMessageUntil.toServer(senmes);
        }
        else if (this.bangJpanel != null) {
            if (this.bangJpanel.getSetword1().getText().equals("")) {
                ZhuFrame.getZhuJpanel().addPrompt2("帮派名为空");
            }
            else if (this.bangJpanel.getSetword2().getText().equals("")) {
                ZhuFrame.getZhuJpanel().addPrompt2("帮派宗旨为空");
            }
            else {
                int cx = GoodsListFromServerUntil.chaxuns(501);
                if (cx != -1) {
                    Gang gangCreate = new Gang();
                    gangCreate.setGangname(this.bangJpanel.getSetword2().getText());
                    gangCreate.setIntroduction(this.bangJpanel.getSetword1().getText());
                    String senmes = Agreement.getAgreement().GangCreateAgreement(GsonUtil.getGsonUtil().getgson().toJson(gangCreate));
                    SendMessageUntil.toServer(senmes);
                }
                else {
                    ZhuFrame.getZhuJpanel().addPrompt2("你背包没有三界召集令");
                }
                FormsManagement.HideForm(25);
            }
        }
    }
}
