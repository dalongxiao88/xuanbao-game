package org.cbg.panel;

import org.come.socket.SendMessageUntil;
import org.come.until.GsonUtil;
import org.come.socket.Agreement;
import org.cbg.bean.SearchGoodsBean;
import org.come.until.CutButtonImage;
import java.awt.event.MouseEvent;
import java.awt.event.MouseAdapter;
import java.awt.Dimension;
import com.updateNew.MyIsif;
import org.cbg.btn.TrslationBtn;
import javax.swing.JPanel;

public class TraslationWantSentJpanel extends JPanel
{
    private TraslationWantSendCardJpanel traslationWantSendCardJpanel;
    private TrslationBtn shoumaishangpin;
    private TrslationBtn jishoujinqian;
    private TrslationBtn yijishoupin;
    
    public TraslationWantSentJpanel() {
        if (MyIsif.getStyle().equals("水墨")) {
            this.setPreferredSize(new Dimension(590, 380));
            this.setOpaque(false);
            this.setLayout(null);
            this.add(this.traslationWantSendCardJpanel = new TraslationWantSendCardJpanel());
            this.shoumaishangpin = new TrslationBtn("inkImg/button/B135.png", 1);
            this.yijishoupin = new TrslationBtn("inkImg/button/B148.png", 1);
            this.shoumaishangpin.setBounds(10, 1, 100, 26);
            this.yijishoupin.setBounds(110, 1, 100, 26);
            this.add(this.shoumaishangpin);
            this.add(this.yijishoupin);
            this.yijishoupin.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    try {
                        TraslationWantSentJpanel.this.shoumaishangpin.setIcons(CutButtonImage.cuts("inkImg/button/B136.png"));
                        TraslationWantSentJpanel.this.yijishoupin.setIcons(CutButtonImage.cuts("inkImg/button/B147.png"));
                    }
                    catch (Exception e2) {
                        e2.printStackTrace();
                    }
                    SearchGoodsBean searchGoodsBean = new SearchGoodsBean();
                    searchGoodsBean.setPageNum(Integer.valueOf(1));
                    searchGoodsBean.setSaletype(Integer.valueOf(1));
                    searchGoodsBean.setSalename("");
                    String sendmes = Agreement.getAgreement().searchMyWaresAgreement(GsonUtil.getGsonUtil().getgson().toJson(searchGoodsBean));
                    SendMessageUntil.toServer(sendmes);
                    TraslationWantSentJpanel.this.traslationWantSendCardJpanel.getVcarCardLayout().show(TraslationWantSentJpanel.this.traslationWantSendCardJpanel, "yijishoupin");
                }
            });
            this.shoumaishangpin.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    try {
                        TraslationWantSentJpanel.this.shoumaishangpin.setIcons(CutButtonImage.cuts("inkImg/button/B135.png"));
                        TraslationWantSentJpanel.this.yijishoupin.setIcons(CutButtonImage.cuts("inkImg/button/B148.png"));
                    }
                    catch (Exception e2) {
                        e2.printStackTrace();
                    }
                    TraslationWantSentJpanel.this.traslationWantSendCardJpanel.getVcarCardLayout().show(TraslationWantSentJpanel.this.traslationWantSendCardJpanel, "jishoushangpin");
                }
            });
        }
        else {
            this.setPreferredSize(new Dimension(590, 380));
            this.setOpaque(false);
            this.setLayout(null);
            this.add(this.traslationWantSendCardJpanel = new TraslationWantSendCardJpanel());
            this.shoumaishangpin = new TrslationBtn("img/xy2uiimg/B135.png", 1);
            this.yijishoupin = new TrslationBtn("img/xy2uiimg/B148.png", 1);
            this.shoumaishangpin.setBounds(10, 1, 100, 26);
            this.yijishoupin.setBounds(110, 1, 100, 26);
            this.add(this.shoumaishangpin);
            this.add(this.yijishoupin);
            this.yijishoupin.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    try {
                        TraslationWantSentJpanel.this.shoumaishangpin.setIcons(CutButtonImage.cuts("img/xy2uiimg/B136.png"));
                        TraslationWantSentJpanel.this.yijishoupin.setIcons(CutButtonImage.cuts("img/xy2uiimg/B147.png"));
                    }
                    catch (Exception e2) {
                        e2.printStackTrace();
                    }
                    SearchGoodsBean searchGoodsBean = new SearchGoodsBean();
                    searchGoodsBean.setPageNum(Integer.valueOf(1));
                    searchGoodsBean.setSaletype(Integer.valueOf(1));
                    searchGoodsBean.setSalename("");
                    String sendmes = Agreement.getAgreement().searchMyWaresAgreement(GsonUtil.getGsonUtil().getgson().toJson(searchGoodsBean));
                    SendMessageUntil.toServer(sendmes);
                    TraslationWantSentJpanel.this.traslationWantSendCardJpanel.getVcarCardLayout().show(TraslationWantSentJpanel.this.traslationWantSendCardJpanel, "yijishoupin");
                }
            });
            this.shoumaishangpin.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    try {
                        TraslationWantSentJpanel.this.shoumaishangpin.setIcons(CutButtonImage.cuts("img/xy2uiimg/B135.png"));
                        TraslationWantSentJpanel.this.yijishoupin.setIcons(CutButtonImage.cuts("img/xy2uiimg/B148.png"));
                    }
                    catch (Exception e2) {
                        e2.printStackTrace();
                    }
                    TraslationWantSentJpanel.this.traslationWantSendCardJpanel.getVcarCardLayout().show(TraslationWantSentJpanel.this.traslationWantSendCardJpanel, "jishoushangpin");
                }
            });
        }
    }
    
    public TraslationWantSendCardJpanel getTraslationWantSendCardJpanel() {
        return this.traslationWantSendCardJpanel;
    }
    
    public void setTraslationWantSendCardJpanel(TraslationWantSendCardJpanel traslationWantSendCardJpanel) {
        this.traslationWantSendCardJpanel = traslationWantSendCardJpanel;
    }
}
