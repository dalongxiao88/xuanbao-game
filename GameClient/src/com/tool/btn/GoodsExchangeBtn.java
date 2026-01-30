package com.tool.btn;

import org.come.socket.SendMessageUntil;
import org.come.until.GsonUtil;
import org.come.socket.Agreement;
import org.come.Frame.ZhuFrame;
import java.awt.event.MouseEvent;
import java.awt.Color;
import com.tool.tcpimg.UIUtils;
import org.come.bean.GoodsForGoodsBean;
import org.come.bean.GoodSEarchBean;
import org.come.Jpanel.GoodsExchangeJpanel;

public class GoodsExchangeBtn extends MoBanBtn
{
    private final GoodsExchangeJpanel goodJpanel;
    private final int caozuo;
    private int type;
    GoodSEarchBean goods;
    public static GoodsForGoodsBean goodsbean;
    
    public GoodsExchangeBtn(String iconpath, int type, String text, int caozuo, GoodsExchangeJpanel goodJpanel) {
        super(iconpath, type);
        this.type = 0;
        this.goods = new GoodSEarchBean();
        this.setText(text);
        this.setFont(UIUtils.TEXT_FONT1);
        this.setForeground(Color.yellow);
        this.setVerticalTextPosition(0);
        this.setHorizontalTextPosition(0);
        this.goodJpanel = goodJpanel;
        this.caozuo = caozuo;
    }
    
    @Override
    public void chooseyes() {
    }
    
    @Override
    public void chooseno() {
    }
    
    @Override
    public void nochoose(MouseEvent e) {
        switch (this.caozuo) {
            case 1: {
                String mes = this.goodJpanel.getSrmc().getText();
                if (mes == null || mes == "") {
                    ZhuFrame.getZhuJpanel().addPrompt2("搜索名称不能为空!!");
                    return;
                }
                this.goods.setType(this.type);
                this.goods.setGoodname(mes);
                String sendmes = Agreement.getAgreement().GOODSFORGOODSAPPLYAgreement(GsonUtil.getGsonUtil().getgson().toJson(this.goods));
                SendMessageUntil.toServer(sendmes);
                break;
            }
            case 2: {
                String sendmes2 = Agreement.getAgreement().goodforgoodstAgreement(GsonUtil.getGsonUtil().getgson().toJson(GoodsExchangeBtn.goodsbean));
                SendMessageUntil.toServer(sendmes2);
                break;
            }
            case 3: {
                this.goods.setType(0);
                this.goods.setGoodname(null);
                String sendmes3 = Agreement.getAgreement().GOODSFORGOODSAPPLYAgreement(GsonUtil.getGsonUtil().getgson().toJson(this.goods));
                SendMessageUntil.toServer(sendmes3);
                this.type = 0;
                break;
            }
            case 4: {
                this.goods.setType(2);
                this.goods.setGoodname(null);
                String sendmes4 = Agreement.getAgreement().GOODSFORGOODSAPPLYAgreement(GsonUtil.getGsonUtil().getgson().toJson(this.goods));
                SendMessageUntil.toServer(sendmes4);
                break;
            }
            case 5: {
                this.goods.setType(4);
                this.goods.setGoodname(null);
                String sendmes5 = Agreement.getAgreement().GOODSFORGOODSAPPLYAgreement(GsonUtil.getGsonUtil().getgson().toJson(this.goods));
                SendMessageUntil.toServer(sendmes5);
                break;
            }
            case 6: {
                this.goods.setType(3);
                this.goods.setGoodname(null);
                String sendmes6 = Agreement.getAgreement().GOODSFORGOODSAPPLYAgreement(GsonUtil.getGsonUtil().getgson().toJson(this.goods));
                SendMessageUntil.toServer(sendmes6);
                break;
            }
            case 7: {
                this.goods.setType(1);
                this.goods.setGoodname(null);
                String sendmes7 = Agreement.getAgreement().GOODSFORGOODSAPPLYAgreement(GsonUtil.getGsonUtil().getgson().toJson(this.goods));
                SendMessageUntil.toServer(sendmes7);
                break;
            }
        }
    }
}
