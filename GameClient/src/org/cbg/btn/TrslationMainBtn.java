package org.cbg.btn;

import org.cbg.panel.TraslationWantBuyShouyeCardJpanel;
import java.math.BigDecimal;
import org.come.Frame.ZhuFrame;
import org.cbg.panel.TraslationWantBuyGongshiqiJpanel;
import org.cbg.panel.TraslationWantBuyLingbaoJpanel;
import org.cbg.panel.TraslationWantBuyZhaungbeiJpanel;
import org.cbg.panel.TraslationWantBuyZhaohuanshouJpanel;
import org.cbg.panel.TraslationWantBuyDaojuJpanel;
import org.cbg.entity.Message;
import com.tool.role.RoleData;
import org.cbg.bean.SearchOrderBean;
import org.come.socket.SendMessageUntil;
import org.come.until.GsonUtil;
import org.come.socket.Agreement;
import org.cbg.frame.TrslationMainJframe;
import org.come.until.CutButtonImage;
import com.updateNew.MyIsif;
import org.cbg.bean.SearchGoodsBean;
import java.awt.event.MouseEvent;
import java.awt.Font;
import java.awt.Color;
import com.tool.tcpimg.UIUtils;
import org.come.Jpanel.BoothBoxJpanel;
import org.cbg.panel.TraslationWantBuyShouyeSousuojieguoJpanel;
import org.cbg.panel.TraslationWantBuyShouyeJpanel;
import org.cbg.panel.TraslationWantBuyJpanel;
import org.cbg.panel.TrslationTreasurepavilionJpanel;
import com.tool.btn.MoBanBtn;

public class TrslationMainBtn extends MoBanBtn
{
    private int caozuo;
    private TrslationTreasurepavilionJpanel trslationTreasurepavilionJpanel;
    private TraslationWantBuyJpanel traslationWantBuyJpanel;
    private TraslationWantBuyShouyeJpanel traslationWantBuyShouyeJpanel;
    private TraslationWantBuyShouyeSousuojieguoJpanel traslationWantBuyShouyeSousuojieguoJpanel;
    private BoothBoxJpanel boothBoxJpanel;
    
    public TrslationMainBtn(String iconpath, int type, int caozuo, String text, TraslationWantBuyShouyeSousuojieguoJpanel traslationWantBuyShouyeSousuojieguoJpanel) {
        super(iconpath, type);
        this.caozuo = caozuo;
        this.traslationWantBuyShouyeSousuojieguoJpanel = traslationWantBuyShouyeSousuojieguoJpanel;
        this.setText(text);
        this.setFont(UIUtils.TEXT_HY14);
        this.setForeground(Color.white);
        this.setVerticalTextPosition(0);
        this.setHorizontalTextPosition(0);
    }
    
    public TrslationMainBtn(String iconpath, int type, Color[] colors, Font font, int caozuo, String text, TraslationWantBuyShouyeSousuojieguoJpanel traslationWantBuyShouyeSousuojieguoJpanel) {
        super(iconpath, type, colors);
        this.caozuo = caozuo;
        this.traslationWantBuyShouyeSousuojieguoJpanel = traslationWantBuyShouyeSousuojieguoJpanel;
        this.setText(text);
        this.setFont(font);
        this.setVerticalTextPosition(0);
        this.setHorizontalTextPosition(0);
    }
    
    public TrslationMainBtn(String iconpath, int type, int caozuo, TrslationTreasurepavilionJpanel trslationTreasurepavilionJpanel) {
        super(iconpath, type);
        this.caozuo = caozuo;
        this.trslationTreasurepavilionJpanel = trslationTreasurepavilionJpanel;
    }
    
    public TrslationMainBtn(String iconpath, int type, Color[] colors, Font font, String text, int caozuo, TrslationTreasurepavilionJpanel trslationTreasurepavilionJpanel) {
        super(iconpath, type, colors);
        this.caozuo = caozuo;
        this.trslationTreasurepavilionJpanel = trslationTreasurepavilionJpanel;
        this.setText(text);
        this.setFont(font);
        this.setVerticalTextPosition(0);
        this.setHorizontalTextPosition(0);
    }
    
    public TrslationMainBtn(String iconpath, int type, int caozuo, TraslationWantBuyJpanel traslationWantBuyJpanel) {
        super(iconpath, type);
        this.caozuo = caozuo;
        this.traslationWantBuyJpanel = traslationWantBuyJpanel;
    }
    
    public TrslationMainBtn(String iconpath, int type, int caozuo, String text, TraslationWantBuyShouyeJpanel traslationWantBuyShouyeJpanel) {
        super(iconpath, type);
        this.caozuo = caozuo;
        this.setText(text);
        this.setVerticalTextPosition(0);
        this.setFont(UIUtils.TEXT_HY88);
        this.setForeground(Color.white);
        this.setHorizontalTextPosition(0);
        this.traslationWantBuyShouyeJpanel = traslationWantBuyShouyeJpanel;
    }
    
    public TrslationMainBtn(String iconpath, int type, int caozuo, String text, BoothBoxJpanel boothBoxJpanel) {
        super(iconpath, type);
        this.caozuo = caozuo;
        this.setText(text);
        this.setFont(UIUtils.TEXT_HY16);
        this.setVerticalTextPosition(0);
        this.setHorizontalTextPosition(0);
        this.boothBoxJpanel = boothBoxJpanel;
    }
    
    @Override
    public void chooseyes() {
    }
    
    @Override
    public void chooseno() {
    }
    
    @Override
    public void nochoose(MouseEvent e) {
        SearchGoodsBean searchGoodsBean = new SearchGoodsBean();
        try {
            if (MyIsif.getStyle().equals("水墨")) {
                switch (this.caozuo) {
                    case 1: {
                        this.trslationTreasurepavilionJpanel.getIwantbuy().setIcons(CutButtonImage.cuts("inkImg/button/B113.png"));
                        this.trslationTreasurepavilionJpanel.getIwantsent().setIcons(CutButtonImage.cuts("inkImg/button/B114.png"));
                        this.trslationTreasurepavilionJpanel.getIcangbaoge().setIcons(CutButtonImage.cuts("inkImg/button/B110.png"));
                        this.trslationTreasurepavilionJpanel.getImes().setIcons(CutButtonImage.cuts("inkImg/button/B116.png"));
                        this.trslationTreasurepavilionJpanel.getTranslationMainCardJpanel().getCardLayout().show(this.trslationTreasurepavilionJpanel.getTranslationMainCardJpanel(), "iwantbuy");
                        if (TrslationMainJframe.getTrslationMainJframe().getPanelOpen() == 0) {
                            searchGoodsBean.setSaletype(Integer.valueOf(1));
                            searchGoodsBean.setPageNum(Integer.valueOf(1));
                            String sendmes = Agreement.getAgreement().searchGoodsAgreement(GsonUtil.getGsonUtil().getgson().toJson(searchGoodsBean));
                            SendMessageUntil.toServer(sendmes);
                            TrslationMainJframe.getTrslationMainJframe().setPanelOpen(0);
                            break;
                        }
                        else {
                            break;
                        }
                    }
                    case 2: {
                        this.trslationTreasurepavilionJpanel.getIwantbuy().setIcons(CutButtonImage.cuts("inkImg/button/B112.png"));
                        this.trslationTreasurepavilionJpanel.getIwantsent().setIcons(CutButtonImage.cuts("inkImg/button/B115.png"));
                        this.trslationTreasurepavilionJpanel.getIcangbaoge().setIcons(CutButtonImage.cuts("inkImg/button/B110.png"));
                        this.trslationTreasurepavilionJpanel.getImes().setIcons(CutButtonImage.cuts("inkImg/button/B116.png"));
                        this.trslationTreasurepavilionJpanel.getTranslationMainCardJpanel().getCardLayout().show(this.trslationTreasurepavilionJpanel.getTranslationMainCardJpanel(), "iwantsent");
                        break;
                    }
                    case 3: {
                        this.trslationTreasurepavilionJpanel.getIwantbuy().setIcons(CutButtonImage.cuts("inkImg/button/B112.png"));
                        this.trslationTreasurepavilionJpanel.getIwantsent().setIcons(CutButtonImage.cuts("inkImg/button/B114.png"));
                        this.trslationTreasurepavilionJpanel.getIcangbaoge().setIcons(CutButtonImage.cuts("inkImg/button/B111.png"));
                        this.trslationTreasurepavilionJpanel.getImes().setIcons(CutButtonImage.cuts("inkImg/button/B116.png"));
                        this.trslationTreasurepavilionJpanel.getTranslationMainCardJpanel().getCardLayout().show(this.trslationTreasurepavilionJpanel.getTranslationMainCardJpanel(), "icangbaoge");
                        if (this.trslationTreasurepavilionJpanel.getTranslationMainCardJpanel().getTraslationMyMainJpanel().getTraslationMyMainCardJpanel().getTraslationMyMainMyorderJpanel().isVisible()) {
                            SearchOrderBean searchOrderBean = new SearchOrderBean();
                            searchOrderBean.setTime(Integer.valueOf(1));
                            searchOrderBean.setPageNum(Integer.valueOf(1));
                            String sendmes = Agreement.getAgreement().searchMyOrderAgreement(GsonUtil.getGsonUtil().getgson().toJson(searchOrderBean));
                            SendMessageUntil.toServer(sendmes);
                            break;
                        }
                        else {
                            break;
                        }
                    }
                    case 4: {
                        this.trslationTreasurepavilionJpanel.getIwantbuy().setIcons(CutButtonImage.cuts("inkImg/button/B112.png"));
                        this.trslationTreasurepavilionJpanel.getIwantsent().setIcons(CutButtonImage.cuts("inkImg/button/B114.png"));
                        this.trslationTreasurepavilionJpanel.getIcangbaoge().setIcons(CutButtonImage.cuts("inkImg/button/B110.png"));
                        this.trslationTreasurepavilionJpanel.getImes().setIcons(CutButtonImage.cuts("inkImg/button/B117.png"));
                        BigDecimal roleid = RoleData.getRoleData().getLoginResult().getRole_id();
                        Message message = new Message();
                        String sendmes = Agreement.getAgreement().searchNewsAgreement("1");
                        SendMessageUntil.toServer(sendmes);
                        break;
                    }
                    case 5: {
                        this.traslationWantBuyJpanel.getShouye().setIcons(CutButtonImage.cuts("inkImg/button/B142.png"));
                        this.traslationWantBuyJpanel.getDaoju().setIcons(CutButtonImage.cuts("inkImg/button/B129.png"));
                        this.traslationWantBuyJpanel.getZhaohuansou().setIcons(CutButtonImage.cuts("inkImg/button/B149.png"));
                        this.traslationWantBuyJpanel.getZhuangbei().setIcons(CutButtonImage.cuts("inkImg/button/B153.png"));
                        this.traslationWantBuyJpanel.getLingbao().setIcons(CutButtonImage.cuts("inkImg/button/B137.png"));
                        this.traslationWantBuyJpanel.getGongshiqi().setIcons(CutButtonImage.cuts("inkImg/button/B131.png"));
                        this.traslationWantBuyJpanel.getShoucang().setIcons(CutButtonImage.cuts("inkImg/button/B139.png"));
                        this.traslationWantBuyJpanel.changeOrder();
                        searchGoodsBean.setSaletype(Integer.valueOf(1));
                        searchGoodsBean.setPageNum(Integer.valueOf(1));
                        String sendmes = Agreement.getAgreement().searchGoodsAgreement(GsonUtil.getGsonUtil().getgson().toJson(searchGoodsBean));
                        SendMessageUntil.toServer(sendmes);
                        TrslationMainJframe.getTrslationMainJframe().setPanelOpen(0);
                        break;
                    }
                    case 6: {
                        this.traslationWantBuyJpanel.getShouye().setIcons(CutButtonImage.cuts("inkImg/button/B141.png"));
                        this.traslationWantBuyJpanel.getDaoju().setIcons(CutButtonImage.cuts("inkImg/button/B129.png"));
                        this.traslationWantBuyJpanel.getZhaohuansou().setIcons(CutButtonImage.cuts("inkImg/button/B149.png"));
                        this.traslationWantBuyJpanel.getZhuangbei().setIcons(CutButtonImage.cuts("inkImg/button/B153.png"));
                        this.traslationWantBuyJpanel.getLingbao().setIcons(CutButtonImage.cuts("inkImg/button/B137.png"));
                        this.traslationWantBuyJpanel.getGongshiqi().setIcons(CutButtonImage.cuts("inkImg/button/B131.png"));
                        this.traslationWantBuyJpanel.getShoucang().setIcons(CutButtonImage.cuts("inkImg/button/B139.png"));
                        this.traslationWantBuyJpanel.changeOrder();
                        searchGoodsBean.setSaletype(Integer.valueOf(2));
                        searchGoodsBean.setContiontype("100000000-10000000000");
                        TrslationMainJframe.getTrslationMainJframe().getTrslationMainJpanel().getTranslationMainCardJpanel().getTraslationWantBuyJpanel().getTraslationWantBuyCardJapel().getTraslationWantBuyDahuabiJpanel().setDangqianyeshu(1);
                        searchGoodsBean.setPageNum(Integer.valueOf(1));
                        String sendmes = Agreement.getAgreement().searchGoodsAgreement(GsonUtil.getGsonUtil().getgson().toJson(searchGoodsBean));
                        SendMessageUntil.toServer(sendmes);
                        TrslationMainJframe.getTrslationMainJframe().setPanelOpen(2);
                        break;
                    }
                    case 7: {
                        this.traslationWantBuyJpanel.getShouye().setIcons(CutButtonImage.cuts("inkImg/button/B141.png"));
                        this.traslationWantBuyJpanel.getDaoju().setIcons(CutButtonImage.cuts("inkImg/button/B130.png"));
                        this.traslationWantBuyJpanel.getZhaohuansou().setIcons(CutButtonImage.cuts("inkImg/button/B149.png"));
                        this.traslationWantBuyJpanel.getZhuangbei().setIcons(CutButtonImage.cuts("inkImg/button/B153.png"));
                        this.traslationWantBuyJpanel.getLingbao().setIcons(CutButtonImage.cuts("inkImg/button/B137.png"));
                        this.traslationWantBuyJpanel.getGongshiqi().setIcons(CutButtonImage.cuts("inkImg/button/B131.png"));
                        this.traslationWantBuyJpanel.getShoucang().setIcons(CutButtonImage.cuts("inkImg/button/B139.png"));
                        this.traslationWantBuyJpanel.changeOrder();
                        searchGoodsBean.setSaletype(Integer.valueOf(3));
                        searchGoodsBean.setContiontype("0");
                        TraslationWantBuyDaojuJpanel.getTraslationWantBuyDaojuJpanel().setDangqianyeshu(1);
                        searchGoodsBean.setPageNum(Integer.valueOf(1));
                        String sendmes = Agreement.getAgreement().searchGoodsAgreement(GsonUtil.getGsonUtil().getgson().toJson(searchGoodsBean));
                        SendMessageUntil.toServer(sendmes);
                        TrslationMainJframe.getTrslationMainJframe().setPanelOpen(3);
                        TraslationWantBuyDaojuJpanel.getTraslationWantBuyDaojuJpanel().getChooseLeft().setText("全部");
                        break;
                    }
                    case 8: {
                        this.traslationWantBuyJpanel.getShouye().setIcons(CutButtonImage.cuts("inkImg/button/B141.png"));
                        this.traslationWantBuyJpanel.getDaoju().setIcons(CutButtonImage.cuts("inkImg/button/B129.png"));
                        this.traslationWantBuyJpanel.getZhaohuansou().setIcons(CutButtonImage.cuts("inkImg/button/B150.png"));
                        this.traslationWantBuyJpanel.getZhuangbei().setIcons(CutButtonImage.cuts("inkImg/button/B153.png"));
                        this.traslationWantBuyJpanel.getLingbao().setIcons(CutButtonImage.cuts("inkImg/button/B137.png"));
                        this.traslationWantBuyJpanel.getGongshiqi().setIcons(CutButtonImage.cuts("inkImg/button/B131.png"));
                        this.traslationWantBuyJpanel.getShoucang().setIcons(CutButtonImage.cuts("inkImg/button/B139.png"));
                        this.traslationWantBuyJpanel.changeOrder();
                        searchGoodsBean.setSaletype(Integer.valueOf(4));
                        searchGoodsBean.setContiontype("0");
                        TraslationWantBuyZhaohuanshouJpanel.getTraslationWantBuyZhaohuanshouJpanel().setDangqianyeshu(1);
                        searchGoodsBean.setPageNum(Integer.valueOf(1));
                        String sendmes = Agreement.getAgreement().searchGoodsAgreement(GsonUtil.getGsonUtil().getgson().toJson(searchGoodsBean));
                        SendMessageUntil.toServer(sendmes);
                        TrslationMainJframe.getTrslationMainJframe().setPanelOpen(4);
                        TraslationWantBuyZhaohuanshouJpanel.getTraslationWantBuyZhaohuanshouJpanel().getChooseLeft().setText("全部类型");
                        break;
                    }
                    case 9: {
                        this.traslationWantBuyJpanel.getShouye().setIcons(CutButtonImage.cuts("inkImg/button/B141.png"));
                        this.traslationWantBuyJpanel.getDaoju().setIcons(CutButtonImage.cuts("inkImg/button/B129.png"));
                        this.traslationWantBuyJpanel.getZhaohuansou().setIcons(CutButtonImage.cuts("inkImg/button/B149.png"));
                        this.traslationWantBuyJpanel.getZhuangbei().setIcons(CutButtonImage.cuts("inkImg/button/B154.png"));
                        this.traslationWantBuyJpanel.getLingbao().setIcons(CutButtonImage.cuts("inkImg/button/B137.png"));
                        this.traslationWantBuyJpanel.getGongshiqi().setIcons(CutButtonImage.cuts("inkImg/button/B131.png"));
                        this.traslationWantBuyJpanel.getShoucang().setIcons(CutButtonImage.cuts("inkImg/button/B139.png"));
                        this.traslationWantBuyJpanel.changeOrder();
                        searchGoodsBean.setSaletype(Integer.valueOf(5));
                        searchGoodsBean.setContiontype("0");
                        TraslationWantBuyZhaungbeiJpanel.getTraslationWantBuyZhaungbeiJpanel().setDangqianyeshu(1);
                        searchGoodsBean.setPageNum(Integer.valueOf(1));
                        String sendmes = Agreement.getAgreement().searchGoodsAgreement(GsonUtil.getGsonUtil().getgson().toJson(searchGoodsBean));
                        SendMessageUntil.toServer(sendmes);
                        TrslationMainJframe.getTrslationMainJframe().setPanelOpen(5);
                        TraslationWantBuyZhaungbeiJpanel.getTraslationWantBuyZhaungbeiJpanel().getChooseZbType().setText("全部");
                        break;
                    }
                    case 10: {
                        this.traslationWantBuyJpanel.getShouye().setIcons(CutButtonImage.cuts("inkImg/button/B141.png"));
                        this.traslationWantBuyJpanel.getDaoju().setIcons(CutButtonImage.cuts("inkImg/button/B129.png"));
                        this.traslationWantBuyJpanel.getZhaohuansou().setIcons(CutButtonImage.cuts("inkImg/button/B149.png"));
                        this.traslationWantBuyJpanel.getZhuangbei().setIcons(CutButtonImage.cuts("inkImg/button/B153.png"));
                        this.traslationWantBuyJpanel.getLingbao().setIcons(CutButtonImage.cuts("inkImg/button/B138.png"));
                        this.traslationWantBuyJpanel.getGongshiqi().setIcons(CutButtonImage.cuts("inkImg/button/B131.png"));
                        this.traslationWantBuyJpanel.getShoucang().setIcons(CutButtonImage.cuts("inkImg/button/B139.png"));
                        this.traslationWantBuyJpanel.changeOrder();
                        searchGoodsBean.setSaletype(Integer.valueOf(6));
                        TraslationWantBuyLingbaoJpanel.getTraslationWantBuyLingbaoJpanel().setDangqianyeshu(1);
                        searchGoodsBean.setPageNum(Integer.valueOf(1));
                        String sendmes = Agreement.getAgreement().searchGoodsAgreement(GsonUtil.getGsonUtil().getgson().toJson(searchGoodsBean));
                        SendMessageUntil.toServer(sendmes);
                        TrslationMainJframe.getTrslationMainJframe().setPanelOpen(6);
                        break;
                    }
                    case 11: {
                        this.traslationWantBuyJpanel.getShouye().setIcons(CutButtonImage.cuts("inkImg/button/B141.png"));
                        this.traslationWantBuyJpanel.getDaoju().setIcons(CutButtonImage.cuts("inkImg/button/B129.png"));
                        this.traslationWantBuyJpanel.getZhaohuansou().setIcons(CutButtonImage.cuts("inkImg/button/B149.png"));
                        this.traslationWantBuyJpanel.getZhuangbei().setIcons(CutButtonImage.cuts("inkImg/button/B153.png"));
                        this.traslationWantBuyJpanel.getLingbao().setIcons(CutButtonImage.cuts("inkImg/button/B137.png"));
                        this.traslationWantBuyJpanel.getGongshiqi().setIcons(CutButtonImage.cuts("inkImg/button/B132.png"));
                        this.traslationWantBuyJpanel.getShoucang().setIcons(CutButtonImage.cuts("inkImg/button/B139.png"));
                        this.traslationWantBuyJpanel.changeOrder();
                        TraslationWantBuyGongshiqiJpanel.getTraslationWantBuyGongshiqiJpanel().setDangqianyeshu(1);
                        searchGoodsBean.setPageNum(Integer.valueOf(1));
                        searchGoodsBean.setShow(2);
                        String sendmes = Agreement.getAgreement().searchGoodsAgreement(GsonUtil.getGsonUtil().getgson().toJson(searchGoodsBean));
                        SendMessageUntil.toServer(sendmes);
                        TrslationMainJframe.getTrslationMainJframe().setPanelOpen(7);
                        TraslationWantBuyGongshiqiJpanel.getTraslationWantBuyGongshiqiJpanel().getChooseLeft().setText("全部");
                        break;
                    }
                    case 12: {
                        this.traslationWantBuyJpanel.getShouye().setIcons(CutButtonImage.cuts("inkImg/button/B141.png"));
                        this.traslationWantBuyJpanel.getDaoju().setIcons(CutButtonImage.cuts("inkImg/button/B129.png"));
                        this.traslationWantBuyJpanel.getZhaohuansou().setIcons(CutButtonImage.cuts("inkImg/button/B149.png"));
                        this.traslationWantBuyJpanel.getZhuangbei().setIcons(CutButtonImage.cuts("inkImg/button/B153.png"));
                        this.traslationWantBuyJpanel.getLingbao().setIcons(CutButtonImage.cuts("inkImg/button/B137.png"));
                        this.traslationWantBuyJpanel.getGongshiqi().setIcons(CutButtonImage.cuts("inkImg/button/B131.png"));
                        this.traslationWantBuyJpanel.getShoucang().setIcons(CutButtonImage.cuts("inkImg/button/B140.png"));
                        this.traslationWantBuyJpanel.changeOrder();
                        String sendmes = Agreement.getAgreement().searchCollectionQueryAgreement("1");
                        SendMessageUntil.toServer(sendmes);
                        TrslationMainJframe.getTrslationMainJframe().setPanelOpen(8);
                        break;
                    }
                    case 13: {
                        this.traslationWantBuyShouyeJpanel.getChooseLeftModel().setVisible(!this.traslationWantBuyShouyeJpanel.getChooseLeftModel().isVisible());
                        break;
                    }
                    case 14: {
                        this.traslationWantBuyShouyeJpanel.getChooseRightModel().setVisible(!this.traslationWantBuyShouyeJpanel.getChooseRightModel().isVisible());
                        break;
                    }
                    case 15: {
                        searchGoodsBean.setSaletype((this.traslationWantBuyShouyeJpanel.getOpenType() == 1) ? null : Integer.valueOf(this.traslationWantBuyShouyeJpanel.getOpenType()));
                        if (this.traslationWantBuyShouyeJpanel.getOpenType() != 2) {
                            if (this.traslationWantBuyShouyeJpanel.getjTextField().getText().equals("关键字或者ID") || this.traslationWantBuyShouyeJpanel.getjTextField().getText().trim().equals("")) {
                                return;
                            }
                            searchGoodsBean.setSalename(this.traslationWantBuyShouyeJpanel.getjTextField().getText().trim());
                        }
                        else {
                            searchGoodsBean.setContiontype(TraslationWantBuyShouyeJpanel.changeMath(this.traslationWantBuyShouyeJpanel.getChooseLeft().getText()) + "-" + TraslationWantBuyShouyeJpanel.changeMath(this.traslationWantBuyShouyeJpanel.getChooseRight().getText()));
                        }
                        TraslationWantBuyShouyeJpanel.searchGoodsBean = searchGoodsBean;
                        TraslationWantBuyShouyeJpanel.souyeSousuo(Integer.valueOf(1), Integer.valueOf(0), Integer.valueOf(0), TraslationWantBuyShouyeJpanel.searchGoodsBean);
                        break;
                    }
                    case 16: {
                        this.traslationWantBuyShouyeSousuojieguoJpanel.setShowOrder(0);
                        this.traslationWantBuyShouyeSousuojieguoJpanel.getUpSort().setIcon(CutButtonImage.getImage("img/xy2uiimg/↑(白色)w6,h11px.png", 6, 11));
                        this.traslationWantBuyShouyeSousuojieguoJpanel.getDownSort().setIcon(CutButtonImage.getImage("img/xy2uiimg/↓(白色)w6,h11px.png", 6, 11));
                        this.traslationWantBuyShouyeSousuojieguoJpanel.getShowperioditems().setIcon(null);
                        this.traslationWantBuyShouyeSousuojieguoJpanel.setShowperioditemsFlag(0);
                        TraslationWantBuyShouyeCardJpanel traslationWantBuyShouyeCardJpanel = TrslationMainJframe.getTrslationMainJframe().getTrslationMainJpanel().getTranslationMainCardJpanel().getTraslationWantBuyJpanel().getTraslationWantBuyCardJapel().getTraslationWantBuyShouyeCardJpanel();
                        traslationWantBuyShouyeCardJpanel.getCardLayout().show(traslationWantBuyShouyeCardJpanel, "sousuo");
                        break;
                    }
                    case 17: {
                        if (this.traslationWantBuyShouyeSousuojieguoJpanel.getDangqianyeshu() != 1) {
                            this.traslationWantBuyShouyeSousuojieguoJpanel.setDangqianyeshu(1);
                            this.traslationWantBuyShouyeSousuojieguoJpanel.getYema().setText(this.traslationWantBuyShouyeSousuojieguoJpanel.getDangqianyeshu() + "/" + this.traslationWantBuyShouyeSousuojieguoJpanel.getZuidayema());
                            TraslationWantBuyShouyeJpanel.souyeSousuo(Integer.valueOf(this.traslationWantBuyShouyeSousuojieguoJpanel.getDangqianyeshu()), Integer.valueOf(this.traslationWantBuyShouyeSousuojieguoJpanel.getShowperioditemsFlag()), Integer.valueOf(this.traslationWantBuyShouyeSousuojieguoJpanel.getShowOrder()), TraslationWantBuyShouyeJpanel.searchGoodsBean);
                            break;
                        }
                        else {
                            ZhuFrame.getZhuJpanel().addPrompt2("已在首页");
                            break;
                        }
                    }
                    case 18: {
                        if (this.traslationWantBuyShouyeSousuojieguoJpanel.getDangqianyeshu() > 1) {
                            this.traslationWantBuyShouyeSousuojieguoJpanel.setDangqianyeshu(this.traslationWantBuyShouyeSousuojieguoJpanel.getDangqianyeshu() - 1);
                            this.traslationWantBuyShouyeSousuojieguoJpanel.getYema().setText(this.traslationWantBuyShouyeSousuojieguoJpanel.getDangqianyeshu() + "/" + this.traslationWantBuyShouyeSousuojieguoJpanel.getZuidayema());
                            TraslationWantBuyShouyeJpanel.souyeSousuo(Integer.valueOf(this.traslationWantBuyShouyeSousuojieguoJpanel.getDangqianyeshu()), Integer.valueOf(this.traslationWantBuyShouyeSousuojieguoJpanel.getShowperioditemsFlag()), Integer.valueOf(this.traslationWantBuyShouyeSousuojieguoJpanel.getShowOrder()), TraslationWantBuyShouyeJpanel.searchGoodsBean);
                            break;
                        }
                        else {
                            ZhuFrame.getZhuJpanel().addPrompt2("已经没有上一页了");
                            break;
                        }
                    }
                    case 19: {
                        if (this.traslationWantBuyShouyeSousuojieguoJpanel.getDangqianyeshu() != this.traslationWantBuyShouyeSousuojieguoJpanel.getZuidayema()) {
                            this.traslationWantBuyShouyeSousuojieguoJpanel.setDangqianyeshu(this.traslationWantBuyShouyeSousuojieguoJpanel.getZuidayema());
                            this.traslationWantBuyShouyeSousuojieguoJpanel.getYema().setText(this.traslationWantBuyShouyeSousuojieguoJpanel.getDangqianyeshu() + "/" + this.traslationWantBuyShouyeSousuojieguoJpanel.getZuidayema());
                            TraslationWantBuyShouyeJpanel.souyeSousuo(Integer.valueOf(this.traslationWantBuyShouyeSousuojieguoJpanel.getDangqianyeshu()), Integer.valueOf(this.traslationWantBuyShouyeSousuojieguoJpanel.getShowperioditemsFlag()), Integer.valueOf(this.traslationWantBuyShouyeSousuojieguoJpanel.getShowOrder()), TraslationWantBuyShouyeJpanel.searchGoodsBean);
                            break;
                        }
                        else {
                            ZhuFrame.getZhuJpanel().addPrompt2("已在末页");
                            break;
                        }
                    }
                    case 20: {
                        if (this.traslationWantBuyShouyeSousuojieguoJpanel.getDangqianyeshu() < this.traslationWantBuyShouyeSousuojieguoJpanel.getZuidayema()) {
                            this.traslationWantBuyShouyeSousuojieguoJpanel.setDangqianyeshu(this.traslationWantBuyShouyeSousuojieguoJpanel.getDangqianyeshu() + 1);
                            this.traslationWantBuyShouyeSousuojieguoJpanel.getYema().setText(this.traslationWantBuyShouyeSousuojieguoJpanel.getDangqianyeshu() + "/" + this.traslationWantBuyShouyeSousuojieguoJpanel.getZuidayema());
                            TraslationWantBuyShouyeJpanel.souyeSousuo(Integer.valueOf(this.traslationWantBuyShouyeSousuojieguoJpanel.getDangqianyeshu()), Integer.valueOf(this.traslationWantBuyShouyeSousuojieguoJpanel.getShowperioditemsFlag()), Integer.valueOf(this.traslationWantBuyShouyeSousuojieguoJpanel.getShowOrder()), TraslationWantBuyShouyeJpanel.searchGoodsBean);
                        }
                        else {
                            ZhuFrame.getZhuJpanel().addPrompt2("已经没有下一页了");
                        }
                    }
                }
            }
            else {
                switch (this.caozuo) {
                    case 1: {
                        this.trslationTreasurepavilionJpanel.getIwantbuy().setIcons(CutButtonImage.cuts("img/xy2uiimg/B113.png"));
                        this.trslationTreasurepavilionJpanel.getIwantsent().setIcons(CutButtonImage.cuts("img/xy2uiimg/B114.png"));
                        this.trslationTreasurepavilionJpanel.getIcangbaoge().setIcons(CutButtonImage.cuts("img/xy2uiimg/B110.png"));
                        this.trslationTreasurepavilionJpanel.getImes().setIcons(CutButtonImage.cuts("img/xy2uiimg/B116.png"));
                        this.trslationTreasurepavilionJpanel.getTranslationMainCardJpanel().getCardLayout().show(this.trslationTreasurepavilionJpanel.getTranslationMainCardJpanel(), "iwantbuy");
                        if (TrslationMainJframe.getTrslationMainJframe().getPanelOpen() == 0) {
                            searchGoodsBean.setSaletype(Integer.valueOf(1));
                            searchGoodsBean.setPageNum(Integer.valueOf(1));
                            String sendmes = Agreement.getAgreement().searchGoodsAgreement(GsonUtil.getGsonUtil().getgson().toJson(searchGoodsBean));
                            SendMessageUntil.toServer(sendmes);
                            TrslationMainJframe.getTrslationMainJframe().setPanelOpen(0);
                            break;
                        }
                        else {
                            break;
                        }
                    }
                    case 2: {
                        this.trslationTreasurepavilionJpanel.getIwantbuy().setIcons(CutButtonImage.cuts("img/xy2uiimg/B112.png"));
                        this.trslationTreasurepavilionJpanel.getIwantsent().setIcons(CutButtonImage.cuts("img/xy2uiimg/B115.png"));
                        this.trslationTreasurepavilionJpanel.getIcangbaoge().setIcons(CutButtonImage.cuts("img/xy2uiimg/B110.png"));
                        this.trslationTreasurepavilionJpanel.getImes().setIcons(CutButtonImage.cuts("img/xy2uiimg/B116.png"));
                        this.trslationTreasurepavilionJpanel.getTranslationMainCardJpanel().getCardLayout().show(this.trslationTreasurepavilionJpanel.getTranslationMainCardJpanel(), "iwantsent");
                        break;
                    }
                    case 3: {
                        this.trslationTreasurepavilionJpanel.getIwantbuy().setIcons(CutButtonImage.cuts("img/xy2uiimg/B112.png"));
                        this.trslationTreasurepavilionJpanel.getIwantsent().setIcons(CutButtonImage.cuts("img/xy2uiimg/B114.png"));
                        this.trslationTreasurepavilionJpanel.getIcangbaoge().setIcons(CutButtonImage.cuts("img/xy2uiimg/B111.png"));
                        this.trslationTreasurepavilionJpanel.getImes().setIcons(CutButtonImage.cuts("img/xy2uiimg/B116.png"));
                        this.trslationTreasurepavilionJpanel.getTranslationMainCardJpanel().getCardLayout().show(this.trslationTreasurepavilionJpanel.getTranslationMainCardJpanel(), "icangbaoge");
                        if (this.trslationTreasurepavilionJpanel.getTranslationMainCardJpanel().getTraslationMyMainJpanel().getTraslationMyMainCardJpanel().getTraslationMyMainMyorderJpanel().isVisible()) {
                            SearchOrderBean searchOrderBean = new SearchOrderBean();
                            searchOrderBean.setTime(Integer.valueOf(1));
                            searchOrderBean.setPageNum(Integer.valueOf(1));
                            String sendmes = Agreement.getAgreement().searchMyOrderAgreement(GsonUtil.getGsonUtil().getgson().toJson(searchOrderBean));
                            SendMessageUntil.toServer(sendmes);
                            break;
                        }
                        else {
                            break;
                        }
                    }
                    case 4: {
                        this.trslationTreasurepavilionJpanel.getIwantbuy().setIcons(CutButtonImage.cuts("img/xy2uiimg/B112.png"));
                        this.trslationTreasurepavilionJpanel.getIwantsent().setIcons(CutButtonImage.cuts("img/xy2uiimg/B114.png"));
                        this.trslationTreasurepavilionJpanel.getIcangbaoge().setIcons(CutButtonImage.cuts("img/xy2uiimg/B110.png"));
                        this.trslationTreasurepavilionJpanel.getImes().setIcons(CutButtonImage.cuts("img/xy2uiimg/B117.png"));
                        BigDecimal roleid = RoleData.getRoleData().getLoginResult().getRole_id();
                        Message message = new Message();
                        String sendmes = Agreement.getAgreement().searchNewsAgreement("1");
                        SendMessageUntil.toServer(sendmes);
                        break;
                    }
                    case 5: {
                        this.traslationWantBuyJpanel.getShouye().setIcons(CutButtonImage.cuts("img/xy2uiimg/B142.png"));
                        this.traslationWantBuyJpanel.getDaoju().setIcons(CutButtonImage.cuts("img/xy2uiimg/B129.png"));
                        this.traslationWantBuyJpanel.getZhaohuansou().setIcons(CutButtonImage.cuts("img/xy2uiimg/B149.png"));
                        this.traslationWantBuyJpanel.getZhuangbei().setIcons(CutButtonImage.cuts("img/xy2uiimg/B234.png"));
                        this.traslationWantBuyJpanel.getLingbao().setIcons(CutButtonImage.cuts("img/xy2uiimg/B137.png"));
                        this.traslationWantBuyJpanel.getGongshiqi().setIcons(CutButtonImage.cuts("img/xy2uiimg/B131.png"));
                        this.traslationWantBuyJpanel.getShoucang().setIcons(CutButtonImage.cuts("img/xy2uiimg/B139.png"));
                        this.traslationWantBuyJpanel.changeOrder();
                        searchGoodsBean.setSaletype(Integer.valueOf(1));
                        searchGoodsBean.setPageNum(Integer.valueOf(1));
                        String sendmes = Agreement.getAgreement().searchGoodsAgreement(GsonUtil.getGsonUtil().getgson().toJson(searchGoodsBean));
                        SendMessageUntil.toServer(sendmes);
                        TrslationMainJframe.getTrslationMainJframe().setPanelOpen(0);
                        break;
                    }
                    case 6: {
                        this.traslationWantBuyJpanel.getShouye().setIcons(CutButtonImage.cuts("img/xy2uiimg/B141.png"));
                        this.traslationWantBuyJpanel.getDaoju().setIcons(CutButtonImage.cuts("img/xy2uiimg/B129.png"));
                        this.traslationWantBuyJpanel.getZhaohuansou().setIcons(CutButtonImage.cuts("img/xy2uiimg/B149.png"));
                        this.traslationWantBuyJpanel.getZhuangbei().setIcons(CutButtonImage.cuts("img/xy2uiimg/B234.png"));
                        this.traslationWantBuyJpanel.getLingbao().setIcons(CutButtonImage.cuts("img/xy2uiimg/B137.png"));
                        this.traslationWantBuyJpanel.getGongshiqi().setIcons(CutButtonImage.cuts("img/xy2uiimg/B131.png"));
                        this.traslationWantBuyJpanel.getShoucang().setIcons(CutButtonImage.cuts("img/xy2uiimg/B139.png"));
                        this.traslationWantBuyJpanel.changeOrder();
                        searchGoodsBean.setSaletype(Integer.valueOf(2));
                        searchGoodsBean.setContiontype("100000000-10000000000");
                        TrslationMainJframe.getTrslationMainJframe().getTrslationMainJpanel().getTranslationMainCardJpanel().getTraslationWantBuyJpanel().getTraslationWantBuyCardJapel().getTraslationWantBuyDahuabiJpanel().setDangqianyeshu(1);
                        searchGoodsBean.setPageNum(Integer.valueOf(1));
                        String sendmes = Agreement.getAgreement().searchGoodsAgreement(GsonUtil.getGsonUtil().getgson().toJson(searchGoodsBean));
                        SendMessageUntil.toServer(sendmes);
                        TrslationMainJframe.getTrslationMainJframe().setPanelOpen(2);
                        break;
                    }
                    case 7: {
                        this.traslationWantBuyJpanel.getShouye().setIcons(CutButtonImage.cuts("img/xy2uiimg/B141.png"));
                        this.traslationWantBuyJpanel.getDaoju().setIcons(CutButtonImage.cuts("img/xy2uiimg/B130.png"));
                        this.traslationWantBuyJpanel.getZhaohuansou().setIcons(CutButtonImage.cuts("img/xy2uiimg/B149.png"));
                        this.traslationWantBuyJpanel.getZhuangbei().setIcons(CutButtonImage.cuts("img/xy2uiimg/B234.png"));
                        this.traslationWantBuyJpanel.getLingbao().setIcons(CutButtonImage.cuts("img/xy2uiimg/B137.png"));
                        this.traslationWantBuyJpanel.getGongshiqi().setIcons(CutButtonImage.cuts("img/xy2uiimg/B131.png"));
                        this.traslationWantBuyJpanel.getShoucang().setIcons(CutButtonImage.cuts("img/xy2uiimg/B139.png"));
                        this.traslationWantBuyJpanel.changeOrder();
                        searchGoodsBean.setSaletype(Integer.valueOf(3));
                        searchGoodsBean.setContiontype("0");
                        TraslationWantBuyDaojuJpanel.getTraslationWantBuyDaojuJpanel().setDangqianyeshu(1);
                        searchGoodsBean.setPageNum(Integer.valueOf(1));
                        String sendmes = Agreement.getAgreement().searchGoodsAgreement(GsonUtil.getGsonUtil().getgson().toJson(searchGoodsBean));
                        SendMessageUntil.toServer(sendmes);
                        TrslationMainJframe.getTrslationMainJframe().setPanelOpen(3);
                        TraslationWantBuyDaojuJpanel.getTraslationWantBuyDaojuJpanel().getChooseLeft().setText("全部");
                        break;
                    }
                    case 8: {
                        this.traslationWantBuyJpanel.getShouye().setIcons(CutButtonImage.cuts("img/xy2uiimg/B141.png"));
                        this.traslationWantBuyJpanel.getDaoju().setIcons(CutButtonImage.cuts("img/xy2uiimg/B129.png"));
                        this.traslationWantBuyJpanel.getZhaohuansou().setIcons(CutButtonImage.cuts("img/xy2uiimg/B150.png"));
                        this.traslationWantBuyJpanel.getZhuangbei().setIcons(CutButtonImage.cuts("img/xy2uiimg/B234.png"));
                        this.traslationWantBuyJpanel.getLingbao().setIcons(CutButtonImage.cuts("img/xy2uiimg/B137.png"));
                        this.traslationWantBuyJpanel.getGongshiqi().setIcons(CutButtonImage.cuts("img/xy2uiimg/B131.png"));
                        this.traslationWantBuyJpanel.getShoucang().setIcons(CutButtonImage.cuts("img/xy2uiimg/B139.png"));
                        this.traslationWantBuyJpanel.changeOrder();
                        searchGoodsBean.setSaletype(Integer.valueOf(4));
                        searchGoodsBean.setContiontype("0");
                        TraslationWantBuyZhaohuanshouJpanel.getTraslationWantBuyZhaohuanshouJpanel().setDangqianyeshu(1);
                        searchGoodsBean.setPageNum(Integer.valueOf(1));
                        String sendmes = Agreement.getAgreement().searchGoodsAgreement(GsonUtil.getGsonUtil().getgson().toJson(searchGoodsBean));
                        SendMessageUntil.toServer(sendmes);
                        TrslationMainJframe.getTrslationMainJframe().setPanelOpen(4);
                        TraslationWantBuyZhaohuanshouJpanel.getTraslationWantBuyZhaohuanshouJpanel().getChooseLeft().setText("全部类型");
                        break;
                    }
                    case 9: {
                        this.traslationWantBuyJpanel.getShouye().setIcons(CutButtonImage.cuts("img/xy2uiimg/B141.png"));
                        this.traslationWantBuyJpanel.getDaoju().setIcons(CutButtonImage.cuts("img/xy2uiimg/B129.png"));
                        this.traslationWantBuyJpanel.getZhaohuansou().setIcons(CutButtonImage.cuts("img/xy2uiimg/B149.png"));
                        this.traslationWantBuyJpanel.getZhuangbei().setIcons(CutButtonImage.cuts("img/xy2uiimg/B233.png"));
                        this.traslationWantBuyJpanel.getLingbao().setIcons(CutButtonImage.cuts("img/xy2uiimg/B137.png"));
                        this.traslationWantBuyJpanel.getGongshiqi().setIcons(CutButtonImage.cuts("img/xy2uiimg/B131.png"));
                        this.traslationWantBuyJpanel.getShoucang().setIcons(CutButtonImage.cuts("img/xy2uiimg/B139.png"));
                        this.traslationWantBuyJpanel.changeOrder();
                        searchGoodsBean.setSaletype(Integer.valueOf(5));
                        searchGoodsBean.setContiontype("0");
                        TraslationWantBuyZhaungbeiJpanel.getTraslationWantBuyZhaungbeiJpanel().setDangqianyeshu(1);
                        searchGoodsBean.setPageNum(Integer.valueOf(1));
                        String sendmes = Agreement.getAgreement().searchGoodsAgreement(GsonUtil.getGsonUtil().getgson().toJson(searchGoodsBean));
                        SendMessageUntil.toServer(sendmes);
                        TrslationMainJframe.getTrslationMainJframe().setPanelOpen(5);
                        TraslationWantBuyZhaungbeiJpanel.getTraslationWantBuyZhaungbeiJpanel().getChooseZbType().setText("全部");
                        break;
                    }
                    case 10: {
                        this.traslationWantBuyJpanel.getShouye().setIcons(CutButtonImage.cuts("img/xy2uiimg/B141.png"));
                        this.traslationWantBuyJpanel.getDaoju().setIcons(CutButtonImage.cuts("img/xy2uiimg/B129.png"));
                        this.traslationWantBuyJpanel.getZhaohuansou().setIcons(CutButtonImage.cuts("img/xy2uiimg/B149.png"));
                        this.traslationWantBuyJpanel.getZhuangbei().setIcons(CutButtonImage.cuts("img/xy2uiimg/B234.png"));
                        this.traslationWantBuyJpanel.getLingbao().setIcons(CutButtonImage.cuts("img/xy2uiimg/B138.png"));
                        this.traslationWantBuyJpanel.getGongshiqi().setIcons(CutButtonImage.cuts("img/xy2uiimg/B131.png"));
                        this.traslationWantBuyJpanel.getShoucang().setIcons(CutButtonImage.cuts("img/xy2uiimg/B139.png"));
                        this.traslationWantBuyJpanel.changeOrder();
                        searchGoodsBean.setSaletype(Integer.valueOf(6));
                        TraslationWantBuyLingbaoJpanel.getTraslationWantBuyLingbaoJpanel().setDangqianyeshu(1);
                        searchGoodsBean.setPageNum(Integer.valueOf(1));
                        String sendmes = Agreement.getAgreement().searchGoodsAgreement(GsonUtil.getGsonUtil().getgson().toJson(searchGoodsBean));
                        SendMessageUntil.toServer(sendmes);
                        TrslationMainJframe.getTrslationMainJframe().setPanelOpen(6);
                        break;
                    }
                    case 11: {
                        this.traslationWantBuyJpanel.getShouye().setIcons(CutButtonImage.cuts("img/xy2uiimg/B141.png"));
                        this.traslationWantBuyJpanel.getDaoju().setIcons(CutButtonImage.cuts("img/xy2uiimg/B129.png"));
                        this.traslationWantBuyJpanel.getZhaohuansou().setIcons(CutButtonImage.cuts("img/xy2uiimg/B149.png"));
                        this.traslationWantBuyJpanel.getZhuangbei().setIcons(CutButtonImage.cuts("img/xy2uiimg/B234.png"));
                        this.traslationWantBuyJpanel.getLingbao().setIcons(CutButtonImage.cuts("img/xy2uiimg/B137.png"));
                        this.traslationWantBuyJpanel.getGongshiqi().setIcons(CutButtonImage.cuts("img/xy2uiimg/B132.png"));
                        this.traslationWantBuyJpanel.getShoucang().setIcons(CutButtonImage.cuts("img/xy2uiimg/B139.png"));
                        this.traslationWantBuyJpanel.changeOrder();
                        TraslationWantBuyGongshiqiJpanel.getTraslationWantBuyGongshiqiJpanel().setDangqianyeshu(1);
                        searchGoodsBean.setPageNum(Integer.valueOf(1));
                        searchGoodsBean.setShow(2);
                        String sendmes = Agreement.getAgreement().searchGoodsAgreement(GsonUtil.getGsonUtil().getgson().toJson(searchGoodsBean));
                        SendMessageUntil.toServer(sendmes);
                        TrslationMainJframe.getTrslationMainJframe().setPanelOpen(7);
                        TraslationWantBuyGongshiqiJpanel.getTraslationWantBuyGongshiqiJpanel().getChooseLeft().setText("全部");
                        break;
                    }
                    case 12: {
                        this.traslationWantBuyJpanel.getShouye().setIcons(CutButtonImage.cuts("img/xy2uiimg/B141.png"));
                        this.traslationWantBuyJpanel.getDaoju().setIcons(CutButtonImage.cuts("img/xy2uiimg/B129.png"));
                        this.traslationWantBuyJpanel.getZhaohuansou().setIcons(CutButtonImage.cuts("img/xy2uiimg/B149.png"));
                        this.traslationWantBuyJpanel.getZhuangbei().setIcons(CutButtonImage.cuts("img/xy2uiimg/B234.png"));
                        this.traslationWantBuyJpanel.getLingbao().setIcons(CutButtonImage.cuts("img/xy2uiimg/B137.png"));
                        this.traslationWantBuyJpanel.getGongshiqi().setIcons(CutButtonImage.cuts("img/xy2uiimg/B131.png"));
                        this.traslationWantBuyJpanel.getShoucang().setIcons(CutButtonImage.cuts("img/xy2uiimg/B140.png"));
                        this.traslationWantBuyJpanel.changeOrder();
                        String sendmes = Agreement.getAgreement().searchCollectionQueryAgreement("1");
                        SendMessageUntil.toServer(sendmes);
                        TrslationMainJframe.getTrslationMainJframe().setPanelOpen(8);
                        break;
                    }
                    case 13: {
                        this.traslationWantBuyShouyeJpanel.getChooseLeftModel().setVisible(!this.traslationWantBuyShouyeJpanel.getChooseLeftModel().isVisible());
                        break;
                    }
                    case 14: {
                        this.traslationWantBuyShouyeJpanel.getChooseRightModel().setVisible(!this.traslationWantBuyShouyeJpanel.getChooseRightModel().isVisible());
                        break;
                    }
                    case 15: {
                        searchGoodsBean.setSaletype((this.traslationWantBuyShouyeJpanel.getOpenType() == 1) ? null : Integer.valueOf(this.traslationWantBuyShouyeJpanel.getOpenType()));
                        if (this.traslationWantBuyShouyeJpanel.getOpenType() != 2) {
                            if (this.traslationWantBuyShouyeJpanel.getjTextField().getText().equals("关键字或者ID") || this.traslationWantBuyShouyeJpanel.getjTextField().getText().trim().equals("")) {
                                return;
                            }
                            searchGoodsBean.setSalename(this.traslationWantBuyShouyeJpanel.getjTextField().getText().trim());
                        }
                        else {
                            searchGoodsBean.setContiontype(TraslationWantBuyShouyeJpanel.changeMath(this.traslationWantBuyShouyeJpanel.getChooseLeft().getText()) + "-" + TraslationWantBuyShouyeJpanel.changeMath(this.traslationWantBuyShouyeJpanel.getChooseRight().getText()));
                        }
                        TraslationWantBuyShouyeJpanel.searchGoodsBean = searchGoodsBean;
                        TraslationWantBuyShouyeJpanel.souyeSousuo(Integer.valueOf(1), Integer.valueOf(0), Integer.valueOf(0), TraslationWantBuyShouyeJpanel.searchGoodsBean);
                        break;
                    }
                    case 16: {
                        this.traslationWantBuyShouyeSousuojieguoJpanel.setShowOrder(0);
                        this.traslationWantBuyShouyeSousuojieguoJpanel.getUpSort().setIcon(CutButtonImage.getImage("img/xy2uiimg/↑(白色)w6,h11px.png", 6, 11));
                        this.traslationWantBuyShouyeSousuojieguoJpanel.getDownSort().setIcon(CutButtonImage.getImage("img/xy2uiimg/↓(白色)w6,h11px.png", 6, 11));
                        this.traslationWantBuyShouyeSousuojieguoJpanel.getShowperioditems().setIcon(null);
                        this.traslationWantBuyShouyeSousuojieguoJpanel.setShowperioditemsFlag(0);
                        TraslationWantBuyShouyeCardJpanel traslationWantBuyShouyeCardJpanel = TrslationMainJframe.getTrslationMainJframe().getTrslationMainJpanel().getTranslationMainCardJpanel().getTraslationWantBuyJpanel().getTraslationWantBuyCardJapel().getTraslationWantBuyShouyeCardJpanel();
                        traslationWantBuyShouyeCardJpanel.getCardLayout().show(traslationWantBuyShouyeCardJpanel, "sousuo");
                        break;
                    }
                    case 17: {
                        if (this.traslationWantBuyShouyeSousuojieguoJpanel.getDangqianyeshu() != 1) {
                            this.traslationWantBuyShouyeSousuojieguoJpanel.setDangqianyeshu(1);
                            this.traslationWantBuyShouyeSousuojieguoJpanel.getYema().setText(this.traslationWantBuyShouyeSousuojieguoJpanel.getDangqianyeshu() + "/" + this.traslationWantBuyShouyeSousuojieguoJpanel.getZuidayema());
                            TraslationWantBuyShouyeJpanel.souyeSousuo(Integer.valueOf(this.traslationWantBuyShouyeSousuojieguoJpanel.getDangqianyeshu()), Integer.valueOf(this.traslationWantBuyShouyeSousuojieguoJpanel.getShowperioditemsFlag()), Integer.valueOf(this.traslationWantBuyShouyeSousuojieguoJpanel.getShowOrder()), TraslationWantBuyShouyeJpanel.searchGoodsBean);
                            break;
                        }
                        else {
                            ZhuFrame.getZhuJpanel().addPrompt2("已在首页");
                            break;
                        }
                    }
                    case 18: {
                        if (this.traslationWantBuyShouyeSousuojieguoJpanel.getDangqianyeshu() > 1) {
                            this.traslationWantBuyShouyeSousuojieguoJpanel.setDangqianyeshu(this.traslationWantBuyShouyeSousuojieguoJpanel.getDangqianyeshu() - 1);
                            this.traslationWantBuyShouyeSousuojieguoJpanel.getYema().setText(this.traslationWantBuyShouyeSousuojieguoJpanel.getDangqianyeshu() + "/" + this.traslationWantBuyShouyeSousuojieguoJpanel.getZuidayema());
                            TraslationWantBuyShouyeJpanel.souyeSousuo(Integer.valueOf(this.traslationWantBuyShouyeSousuojieguoJpanel.getDangqianyeshu()), Integer.valueOf(this.traslationWantBuyShouyeSousuojieguoJpanel.getShowperioditemsFlag()), Integer.valueOf(this.traslationWantBuyShouyeSousuojieguoJpanel.getShowOrder()), TraslationWantBuyShouyeJpanel.searchGoodsBean);
                            break;
                        }
                        else {
                            ZhuFrame.getZhuJpanel().addPrompt2("已经没有上一页了");
                            break;
                        }
                    }
                    case 19: {
                        if (this.traslationWantBuyShouyeSousuojieguoJpanel.getDangqianyeshu() != this.traslationWantBuyShouyeSousuojieguoJpanel.getZuidayema()) {
                            this.traslationWantBuyShouyeSousuojieguoJpanel.setDangqianyeshu(this.traslationWantBuyShouyeSousuojieguoJpanel.getZuidayema());
                            this.traslationWantBuyShouyeSousuojieguoJpanel.getYema().setText(this.traslationWantBuyShouyeSousuojieguoJpanel.getDangqianyeshu() + "/" + this.traslationWantBuyShouyeSousuojieguoJpanel.getZuidayema());
                            TraslationWantBuyShouyeJpanel.souyeSousuo(Integer.valueOf(this.traslationWantBuyShouyeSousuojieguoJpanel.getDangqianyeshu()), Integer.valueOf(this.traslationWantBuyShouyeSousuojieguoJpanel.getShowperioditemsFlag()), Integer.valueOf(this.traslationWantBuyShouyeSousuojieguoJpanel.getShowOrder()), TraslationWantBuyShouyeJpanel.searchGoodsBean);
                            break;
                        }
                        else {
                            ZhuFrame.getZhuJpanel().addPrompt2("已在末页");
                            break;
                        }
                    }
                    case 20: {
                        if (this.traslationWantBuyShouyeSousuojieguoJpanel.getDangqianyeshu() < this.traslationWantBuyShouyeSousuojieguoJpanel.getZuidayema()) {
                            this.traslationWantBuyShouyeSousuojieguoJpanel.setDangqianyeshu(this.traslationWantBuyShouyeSousuojieguoJpanel.getDangqianyeshu() + 1);
                            this.traslationWantBuyShouyeSousuojieguoJpanel.getYema().setText(this.traslationWantBuyShouyeSousuojieguoJpanel.getDangqianyeshu() + "/" + this.traslationWantBuyShouyeSousuojieguoJpanel.getZuidayema());
                            TraslationWantBuyShouyeJpanel.souyeSousuo(Integer.valueOf(this.traslationWantBuyShouyeSousuojieguoJpanel.getDangqianyeshu()), Integer.valueOf(this.traslationWantBuyShouyeSousuojieguoJpanel.getShowperioditemsFlag()), Integer.valueOf(this.traslationWantBuyShouyeSousuojieguoJpanel.getShowOrder()), TraslationWantBuyShouyeJpanel.searchGoodsBean);
                            break;
                        }
                        else {
                            ZhuFrame.getZhuJpanel().addPrompt2("已经没有下一页了");
                            break;
                        }
                    }
                }
            }
        }
        catch (Exception e2) {
            e2.printStackTrace();
        }
    }
}
