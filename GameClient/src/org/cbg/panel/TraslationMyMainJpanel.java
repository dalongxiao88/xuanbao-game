package org.cbg.panel;

import org.come.until.GsonUtil;
import org.cbg.bean.SearchOrderBean;
import org.come.socket.SendMessageUntil;
import org.come.socket.Agreement;
import org.come.until.CutButtonImage;
import java.awt.event.MouseEvent;
import java.awt.event.MouseAdapter;
import java.awt.Dimension;
import com.updateNew.MyIsif;
import org.cbg.btn.TrslationBtn;
import javax.swing.JPanel;

public class TraslationMyMainJpanel extends JPanel
{
    private TrslationBtn myorder;
    private TrslationBtn zhidinggoumai;
    private TrslationBtn mygoods;
    private TraslationMyMainCardJpanel traslationMyMainCardJpanel;
    
    public TraslationMyMainJpanel() {
        if (MyIsif.getStyle().equals("水墨")) {
            this.setPreferredSize(new Dimension(590, 380));
            this.setLayout(null);
            this.setOpaque(false);
            this.add(this.traslationMyMainCardJpanel = new TraslationMyMainCardJpanel());
            this.myorder = new TrslationBtn("inkImg/button/B143.png", 1);
            this.zhidinggoumai = new TrslationBtn("inkImg/button/B152.png", 1);
            this.mygoods = new TrslationBtn("inkImg/button/B146.png", 1);
            this.myorder.setBounds(10, 2, 100, 26);
            this.zhidinggoumai.setBounds(110, 2, 100, 26);
            this.mygoods.setBounds(210, 2, 100, 26);
            this.add(this.myorder);
            this.add(this.zhidinggoumai);
            this.add(this.mygoods);
            this.mygoods.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    try {
                        TraslationMyMainJpanel.this.myorder.setIcons(CutButtonImage.cuts("inkImg/button/B144.png"));
                        TraslationMyMainJpanel.this.zhidinggoumai.setIcons(CutButtonImage.cuts("inkImg/button/B152.png"));
                        TraslationMyMainJpanel.this.mygoods.setIcons(CutButtonImage.cuts("inkImg/button/B145.png"));
                    }
                    catch (Exception e2) {
                        e2.printStackTrace();
                    }
                    String sendmes = Agreement.getAgreement().searchMyGoodsAgreement("1");
                    SendMessageUntil.toServer(sendmes);
                }
            });
            this.zhidinggoumai.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    try {
                        TraslationMyMainJpanel.this.myorder.setIcons(CutButtonImage.cuts("inkImg/button/B144.png"));
                        TraslationMyMainJpanel.this.zhidinggoumai.setIcons(CutButtonImage.cuts("inkImg/button/B151.png"));
                        TraslationMyMainJpanel.this.mygoods.setIcons(CutButtonImage.cuts("inkImg/button/B146.png"));
                    }
                    catch (Exception e2) {
                        e2.printStackTrace();
                    }
                    String sendmes = Agreement.getAgreement().searchAppointAgreement("1");
                    SendMessageUntil.toServer(sendmes);
                }
            });
            this.myorder.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    try {
                        TraslationMyMainJpanel.this.myorder.setIcons(CutButtonImage.cuts("inkImg/button/B143.png"));
                        TraslationMyMainJpanel.this.zhidinggoumai.setIcons(CutButtonImage.cuts("inkImg/button/B152.png"));
                        TraslationMyMainJpanel.this.mygoods.setIcons(CutButtonImage.cuts("inkImg/button/B146.png"));
                    }
                    catch (Exception e2) {
                        e2.printStackTrace();
                    }
                    SearchOrderBean searchOrderBean = new SearchOrderBean();
                    searchOrderBean.setTime(Integer.valueOf(1));
                    searchOrderBean.setPageNum(Integer.valueOf(1));
                    String sendmes = Agreement.getAgreement().searchMyOrderAgreement(GsonUtil.getGsonUtil().getgson().toJson(searchOrderBean));
                    SendMessageUntil.toServer(sendmes);
                }
            });
        }
        else {
            this.setPreferredSize(new Dimension(590, 380));
            this.setLayout(null);
            this.setOpaque(false);
            this.add(this.traslationMyMainCardJpanel = new TraslationMyMainCardJpanel());
            this.myorder = new TrslationBtn("img/xy2uiimg/B143.png", 1);
            this.zhidinggoumai = new TrslationBtn("img/xy2uiimg/B152.png", 1);
            this.mygoods = new TrslationBtn("img/xy2uiimg/B146.png", 1);
            this.myorder.setBounds(10, 2, 100, 27);
            this.zhidinggoumai.setBounds(110, 2, 100, 27);
            this.mygoods.setBounds(210, 2, 100, 27);
            this.add(this.myorder);
            this.add(this.zhidinggoumai);
            this.add(this.mygoods);
            this.mygoods.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    try {
                        TraslationMyMainJpanel.this.myorder.setIcons(CutButtonImage.cuts("img/xy2uiimg/B144.png"));
                        TraslationMyMainJpanel.this.zhidinggoumai.setIcons(CutButtonImage.cuts("img/xy2uiimg/B152.png"));
                        TraslationMyMainJpanel.this.mygoods.setIcons(CutButtonImage.cuts("img/xy2uiimg/B145.png"));
                    }
                    catch (Exception e2) {
                        e2.printStackTrace();
                    }
                    String sendmes = Agreement.getAgreement().searchMyGoodsAgreement("1");
                    SendMessageUntil.toServer(sendmes);
                }
            });
            this.zhidinggoumai.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    try {
                        TraslationMyMainJpanel.this.myorder.setIcons(CutButtonImage.cuts("img/xy2uiimg/B144.png"));
                        TraslationMyMainJpanel.this.zhidinggoumai.setIcons(CutButtonImage.cuts("img/xy2uiimg/B151.png"));
                        TraslationMyMainJpanel.this.mygoods.setIcons(CutButtonImage.cuts("img/xy2uiimg/B146.png"));
                    }
                    catch (Exception e2) {
                        e2.printStackTrace();
                    }
                    String sendmes = Agreement.getAgreement().searchAppointAgreement("1");
                    SendMessageUntil.toServer(sendmes);
                }
            });
            this.myorder.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    try {
                        TraslationMyMainJpanel.this.myorder.setIcons(CutButtonImage.cuts("img/xy2uiimg/B143.png"));
                        TraslationMyMainJpanel.this.zhidinggoumai.setIcons(CutButtonImage.cuts("img/xy2uiimg/B152.png"));
                        TraslationMyMainJpanel.this.mygoods.setIcons(CutButtonImage.cuts("img/xy2uiimg/B146.png"));
                    }
                    catch (Exception e2) {
                        e2.printStackTrace();
                    }
                    SearchOrderBean searchOrderBean = new SearchOrderBean();
                    searchOrderBean.setTime(Integer.valueOf(1));
                    searchOrderBean.setPageNum(Integer.valueOf(1));
                    String sendmes = Agreement.getAgreement().searchMyOrderAgreement(GsonUtil.getGsonUtil().getgson().toJson(searchOrderBean));
                    SendMessageUntil.toServer(sendmes);
                }
            });
        }
    }
    
    public TrslationBtn getMyorder() {
        return this.myorder;
    }
    
    public void setMyorder(TrslationBtn myorder) {
        this.myorder = myorder;
    }
    
    public TrslationBtn getZhidinggoumai() {
        return this.zhidinggoumai;
    }
    
    public void setZhidinggoumai(TrslationBtn zhidinggoumai) {
        this.zhidinggoumai = zhidinggoumai;
    }
    
    public TrslationBtn getMygoods() {
        return this.mygoods;
    }
    
    public void setMygoods(TrslationBtn mygoods) {
        this.mygoods = mygoods;
    }
    
    public TraslationMyMainCardJpanel getTraslationMyMainCardJpanel() {
        return this.traslationMyMainCardJpanel;
    }
    
    public void setTraslationMyMainCardJpanel(TraslationMyMainCardJpanel traslationMyMainCardJpanel) {
        this.traslationMyMainCardJpanel = traslationMyMainCardJpanel;
    }
}
