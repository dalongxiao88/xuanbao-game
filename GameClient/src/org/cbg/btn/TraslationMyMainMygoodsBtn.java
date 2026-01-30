package org.cbg.btn;

import java.awt.Component;
import java.util.List;
import org.cbg.panel.TraslationMyMainMygoodsModelJpanel;
import org.come.socket.SendMessageUntil;
import org.come.until.GsonUtil;
import org.come.socket.Agreement;
import org.come.Frame.ZhuFrame;
import java.math.BigDecimal;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.util.ArrayList;
import org.cbg.bean.GoodsBackBean;
import org.come.until.GoodsListFromServerUntil;
import org.cbg.frame.TrslationMainJframe;
import java.awt.event.MouseEvent;
import java.awt.Font;
import java.awt.Color;
import org.cbg.panel.TraslationMyMainMygoodsJpanel;
import com.tool.btn.MoBanBtn;

public class TraslationMyMainMygoodsBtn extends MoBanBtn
{
    private int caozuo;
    private TraslationMyMainMygoodsJpanel traslationMyMainMygoodsJpanel;
    
    public TraslationMyMainMygoodsBtn(String iconpath, int type, int caozuo, TraslationMyMainMygoodsJpanel traslationMyMainMygoodsJpanel) {
        super(iconpath, type);
        this.caozuo = caozuo;
        this.traslationMyMainMygoodsJpanel = traslationMyMainMygoodsJpanel;
    }
    
    public TraslationMyMainMygoodsBtn(String iconpath, int type, Color[] colors, Font font, int caozuo, String text, TraslationMyMainMygoodsJpanel traslationMyMainMygoodsJpanel) {
        super(iconpath, type, colors);
        this.caozuo = caozuo;
        this.setText(text);
        this.setFont(font);
        this.setHorizontalTextPosition(0);
        this.setVerticalTextPosition(0);
        this.traslationMyMainMygoodsJpanel = traslationMyMainMygoodsJpanel;
    }
    
    @Override
    public void chooseyes() {
    }
    
    @Override
    public void chooseno() {
    }
    
    @Override
    public void nochoose(MouseEvent e) {
        TrslationMainJframe trslationMainJframe = TrslationMainJframe.getTrslationMainJframe();
        if (this.caozuo == 1) {
            int packNumber = GoodsListFromServerUntil.Surplussum("-1", "-1", 999);
            if (packNumber >= trslationMainJframe.getGoodsGouxuangeshu()) {
                GoodsBackBean backBean = new GoodsBackBean();
                List<BigDecimal> mesIdList = new ArrayList<>();
                JPanel view = (JPanel)this.traslationMyMainMygoodsJpanel.getjScrollPane().getViewport().getView();
                Component[] components = view.getComponents();
                for (int i = 0; i < components.length; ++i) {
                    JPanel panel = (JPanel)components[i];
                    JLabel component2 = (JLabel)panel.getComponent(3);
                    if (component2.getName().equals("0")) {
                        JLabel component3 = (JLabel)panel.getComponent(0);
                        BigDecimal mesId = new BigDecimal(component3.getName());
                        mesIdList.add(mesId);
                    }
                }
                if (mesIdList.size() == 0) {
                    ZhuFrame.getZhuJpanel().addPrompt2("没有选择要取回的物品");
                    return;
                }
                backBean.setIds(mesIdList);
                trslationMainJframe.setGoodsGeshuMax(trslationMainJframe.getGoodsGeshuMax() - trslationMainJframe.getGoodsGouxuangeshu());
                trslationMainJframe.setGoodsGouxuangeshu(0);
                String sendmes = Agreement.getAgreement().searchGoodsBackAgreement(GsonUtil.getGsonUtil().getgson().toJson(backBean));
                SendMessageUntil.toServer(sendmes);
                for (int j = 0; j < components.length; ++j) {
                    JPanel panel2 = (JPanel)components[j];
                    JLabel component4 = (JLabel)panel2.getComponent(3);
                    if (component4.getName().equals("0")) {
                        JLabel component5 = (JLabel)panel2.getComponent(5);
                        component5.setText(TraslationMyMainMygoodsModelJpanel.setBack(Integer.valueOf(4)));
                        JLabel gouxuan = (JLabel)panel2.getComponent(3);
                        gouxuan.setName("1");
                        gouxuan.setIcon(null);
                        gouxuan.setVisible(false);
                        panel2.getComponent(4).setVisible(false);
                        panel2.getComponent(6).setVisible(false);
                    }
                }
                this.traslationMyMainMygoodsJpanel.getGouxuan().setIcon(null);
                this.traslationMyMainMygoodsJpanel.getGouxuan().setName("1");
            }
            else {
                ZhuFrame.getZhuJpanel().addPrompt2("背包格数不足");
            }
        }
        else if (this.caozuo == 2) {
            if (this.traslationMyMainMygoodsJpanel.getDangqianyeshu() != 1) {
                this.traslationMyMainMygoodsJpanel.setDangqianyeshu(1);
                this.traslationMyMainMygoodsJpanel.getYema().setText(this.traslationMyMainMygoodsJpanel.getDangqianyeshu() + "/" + this.traslationMyMainMygoodsJpanel.getZuidayema());
                String sendmes2 = Agreement.getAgreement().searchMyGoodsAgreement(GsonUtil.getGsonUtil().getgson().toJson(Integer.valueOf(this.traslationMyMainMygoodsJpanel.getDangqianyeshu())));
                trslationMainJframe.setPanelOpen(9);
                SendMessageUntil.toServer(sendmes2);
            }
            else {
                ZhuFrame.getZhuJpanel().addPrompt2("已在首页");
            }
        }
        else if (this.caozuo == 3) {
            if (this.traslationMyMainMygoodsJpanel.getDangqianyeshu() != this.traslationMyMainMygoodsJpanel.getZuidayema()) {
                this.traslationMyMainMygoodsJpanel.setDangqianyeshu(this.traslationMyMainMygoodsJpanel.getZuidayema());
                this.traslationMyMainMygoodsJpanel.getYema().setText(this.traslationMyMainMygoodsJpanel.getDangqianyeshu() + "/" + this.traslationMyMainMygoodsJpanel.getZuidayema());
                String sendmes2 = Agreement.getAgreement().searchMyGoodsAgreement(GsonUtil.getGsonUtil().getgson().toJson(Integer.valueOf(this.traslationMyMainMygoodsJpanel.getDangqianyeshu())));
                trslationMainJframe.setPanelOpen(9);
                SendMessageUntil.toServer(sendmes2);
            }
            else {
                ZhuFrame.getZhuJpanel().addPrompt2("已在末页");
            }
        }
        else if (this.caozuo == 4) {
            if (this.traslationMyMainMygoodsJpanel.getDangqianyeshu() > 1) {
                this.traslationMyMainMygoodsJpanel.setDangqianyeshu(this.traslationMyMainMygoodsJpanel.getDangqianyeshu() - 1);
                this.traslationMyMainMygoodsJpanel.getYema().setText(this.traslationMyMainMygoodsJpanel.getDangqianyeshu() + "/" + this.traslationMyMainMygoodsJpanel.getZuidayema());
                String sendmes2 = Agreement.getAgreement().searchMyGoodsAgreement(GsonUtil.getGsonUtil().getgson().toJson(Integer.valueOf(this.traslationMyMainMygoodsJpanel.getDangqianyeshu())));
                trslationMainJframe.setPanelOpen(9);
                SendMessageUntil.toServer(sendmes2);
            }
            else {
                ZhuFrame.getZhuJpanel().addPrompt2("已经没有上一页了");
            }
        }
        else if (this.caozuo == 5) {
            if (this.traslationMyMainMygoodsJpanel.getDangqianyeshu() < this.traslationMyMainMygoodsJpanel.getZuidayema()) {
                this.traslationMyMainMygoodsJpanel.setDangqianyeshu(this.traslationMyMainMygoodsJpanel.getDangqianyeshu() + 1);
                this.traslationMyMainMygoodsJpanel.getYema().setText(this.traslationMyMainMygoodsJpanel.getDangqianyeshu() + "/" + this.traslationMyMainMygoodsJpanel.getZuidayema());
                String sendmes2 = Agreement.getAgreement().searchMyGoodsAgreement(GsonUtil.getGsonUtil().getgson().toJson(Integer.valueOf(this.traslationMyMainMygoodsJpanel.getDangqianyeshu())));
                trslationMainJframe.setPanelOpen(9);
                SendMessageUntil.toServer(sendmes2);
            }
            else {
                ZhuFrame.getZhuJpanel().addPrompt2("已经没有下一页了");
            }
        }
    }
}
