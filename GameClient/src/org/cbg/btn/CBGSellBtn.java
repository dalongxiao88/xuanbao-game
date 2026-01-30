package org.cbg.btn;

import org.come.socket.SendMessageUntil;
import org.come.until.GsonUtil;
import org.come.socket.Agreement;
import org.cbg.entity.Salegoods;
import come.tool.JDialog.TiShiUtil;
import org.come.Frame.OptionsJframe;
import com.tool.role.RoleData;
import java.math.BigDecimal;
import org.come.Frame.ZhuFrame;
import org.come.until.Util;
import java.awt.event.MouseEvent;
import java.awt.Font;
import java.awt.Color;
import org.cbg.panel.TraslationWantSentjishoujinqianJpanel;
import org.cbg.panel.TraslationWantSendJishoushangpinJpanel;
import com.tool.btn.MoBanBtn;

public class CBGSellBtn extends MoBanBtn
{
    private static final long serialVersionUID = 7278895297657916980L;
    private TraslationWantSendJishoushangpinJpanel jpanel1;
    private TraslationWantSentjishoujinqianJpanel jpanel2;
    
    public CBGSellBtn(String iconpath, int type, Color[] colors, Font font, String text, TraslationWantSendJishoushangpinJpanel jpanel) {
        super(iconpath, type, colors);
        this.setText(text);
        this.setFont(font);
        this.setVerticalTextPosition(0);
        this.setHorizontalTextPosition(0);
        this.jpanel1 = jpanel;
    }
    
    public CBGSellBtn(String iconpath, int type, Color[] colors, Font font, String text, TraslationWantSentjishoujinqianJpanel jpanel) {
        super(iconpath, type, colors);
        this.setText(text);
        this.setFont(font);
        this.setVerticalTextPosition(0);
        this.setHorizontalTextPosition(0);
        this.jpanel2 = jpanel;
    }
    
    @Override
    public void chooseyes() {
    }
    
    @Override
    public void chooseno() {
    }
    
    @Override
    public void nochoose(MouseEvent e) {
        if (!Util.canBuyOrno) {
            ZhuFrame.getZhuJpanel().addPrompt2("背包没有解锁!");
            return;
        }
        if (this.jpanel1 != null) {
            this.jsWP();
        }
        else if (this.jpanel2 != null) {
            this.jsJB();
        }
    }
    
    public void jsWP() {
        Salegoods salegoods = this.jpanel1.getSalegoods();
        if (salegoods == null) {
            ZhuFrame.getZhuJpanel().addPrompt2("先选择要上架的商品");
            return;
        }
        BigDecimal bigDecimal = this.jpanel1.getJG();
        if (bigDecimal == null) {
            ZhuFrame.getZhuJpanel().addPrompt2("两个价格不一致");
            return;
        }
        if (bigDecimal.compareTo(new BigDecimal(100)) < 0) {
            ZhuFrame.getZhuJpanel().addPrompt2("最少100仙玉");
            return;
        }
        if (bigDecimal.compareTo(new BigDecimal(10000000)) > 0) {
            ZhuFrame.getZhuJpanel().addPrompt2("最多1000万仙玉");
            return;
        }
        salegoods.setBuyrole(this.jpanel1.getZD());
        salegoods.setSaleprice(bigDecimal);
        long sx = bigDecimal.longValue() * 2L / 100L;
        if (sx > RoleData.getRoleData().getLoginResult().getCodecard().longValue()) {
            ZhuFrame.getZhuJpanel().addPrompt2("你仙玉不够手续费");
            return;
        }
        OptionsJframe.getOptionsJframe().getOptionsJpanel().showBox(TiShiUtil.CBG, this.jpanel1, "#W上架需要支付手续费#R" + sx + "#W仙玉,确定要寄售吗？#R注:如仙玉不够支付强行寄售物品会消失");
    }
    
    public void jsJB() {
        BigDecimal jg = this.jpanel2.getJG();
        BigDecimal jb = this.jpanel2.getJB();
        if (jg == null) {
            ZhuFrame.getZhuJpanel().addPrompt2("两个价格不一致");
            return;
        }
        if (jg.compareTo(new BigDecimal(100)) < 0) {
            ZhuFrame.getZhuJpanel().addPrompt2("最少100仙玉");
            return;
        }
        if (jg.compareTo(new BigDecimal(10000000)) > 0) {
            ZhuFrame.getZhuJpanel().addPrompt2("最多1000万仙玉");
            return;
        }
        if (jb == null) {
            ZhuFrame.getZhuJpanel().addPrompt2("最少要卖1000万且是100万的整数倍");
            return;
        }
        long sx = jg.longValue() * 2L / 100L;
        if (sx > RoleData.getRoleData().getLoginResult().getCodecard().longValue()) {
            ZhuFrame.getZhuJpanel().addPrompt2("你仙玉不够手续费");
            return;
        }
        Salegoods salegoods = new Salegoods();
        salegoods.setSalename("大话币");
        salegoods.setSaletype(Integer.valueOf(2));
        salegoods.setOtherid(jb);
        salegoods.setBuyrole(this.jpanel2.getZD());
        salegoods.setSaleprice(jg);
        String sendmes = Agreement.getAgreement().searchShelfGoodsAgreement(GsonUtil.getGsonUtil().getgson().toJson(salegoods));
        SendMessageUntil.toServer(sendmes);
        this.jpanel2.qk();
    }
}
