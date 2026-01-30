package org.cbg.panel;

import java.awt.Graphics;
import java.util.Map;
import org.come.bean.ConfigureBean;
import org.come.socket.SendMessageUntil;
import org.come.until.GsonUtil;
import org.come.socket.Agreement;
import org.cbg.frame.TrslationMainJframe;
import org.come.Frame.ZhuFrame;
import java.math.BigDecimal;
import org.come.model.Configure;
import org.come.until.UserMessUntil;
import org.come.until.UserData;
import java.awt.Color;
import com.tool.tcpimg.UIUtils;
import org.come.until.CutButtonImage;
import java.awt.Dimension;
import com.updateNew.MyIsif;
import javax.swing.ImageIcon;
import org.cbg.mouslisten.CBGmoveMouslisten;
import org.cbg.btn.CBGMySaleBtn;
import org.cbg.entity.Salegoods;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class TraslationWantSentYijishangpinModelJpanel extends JPanel
{
    private JLabel zhanshidikuang;
    private JLabel tupian;
    private JLabel dahuabishang;
    private JLabel dahuabixia;
    private JLabel danjia;
    private JLabel price;
    private Salegoods salegoods;
    private CBGMySaleBtn mySaleBtn2;
    private CBGmoveMouslisten cbGmoveMouslisten;
    private ImageIcon icon;
    
    public TraslationWantSentYijishangpinModelJpanel(Salegoods salegoods) {
        if (MyIsif.getStyle().equals("水墨")) {
            this.setLayout(null);
            this.setPreferredSize(new Dimension(550, 50));
            this.setOpaque(false);
            this.tupian = new JLabel();
            this.zhanshidikuang = new JLabel();
            this.dahuabishang = new JLabel();
            this.dahuabixia = new JLabel();
            this.danjia = new JLabel();
            this.zhanshidikuang.setBorder(null);
            this.zhanshidikuang.setBounds(40, 5, 39, 39);
            this.zhanshidikuang.setIcon(CutButtonImage.getImage("inkImg/background/10.png", -1, -1));
            this.zhanshidikuang.setOpaque(false);
            this.add(this.zhanshidikuang);
            this.price = new JLabel();
            this.dahuabixia.setFont(UIUtils.TEXT_FONT2);
            this.dahuabixia.setForeground(Color.white);
            this.dahuabixia.setHorizontalAlignment(0);
            this.dahuabishang.setFont(UIUtils.TEXT_FONT2);
            this.dahuabishang.setForeground(Color.white);
            this.dahuabishang.setHorizontalAlignment(0);
            this.danjia.setFont(UIUtils.TEXT_FONT2);
            this.danjia.setForeground(Color.white);
            this.danjia.setHorizontalAlignment(0);
            this.price.setFont(UIUtils.TEXT_FONT2);
            this.price.setForeground(Color.white);
            this.price.setHorizontalAlignment(0);
            this.cbGmoveMouslisten = new CBGmoveMouslisten(salegoods.getSaletype(), salegoods.getOtherid());
            this.tupian.addMouseListener(this.cbGmoveMouslisten);
            this.tupian.setBounds(40, 5, 39, 39);
            this.dahuabixia.setBounds(110, 25, 150, 24);
            this.dahuabishang.setBounds(110, 5, 150, 24);
            this.price.setBounds(230, 0, 125, 40);
            this.danjia.setBounds(350, 0, 125, 40);
            (this.mySaleBtn2 = new CBGMySaleBtn("inkImg/button/2.png", 1, UIUtils.COLOR_BTNTEXT, UIUtils.TEXT_FONT, 5, "下架", this)).setBounds(500, 15, 43, 17);
            this.mySaleBtn2.setName("下架");
            this.add(this.mySaleBtn2);
            this.add(this.tupian);
            this.add(this.dahuabixia);
            this.add(this.dahuabishang);
            this.add(this.danjia);
            this.add(this.price);
            this.SX(salegoods);
        }
        else {
            this.setLayout(null);
            this.setPreferredSize(new Dimension(550, 50));
            this.setOpaque(false);
            this.tupian = new JLabel();
            this.zhanshidikuang = new JLabel();
            this.dahuabishang = new JLabel();
            this.dahuabixia = new JLabel();
            this.danjia = new JLabel();
            this.zhanshidikuang.setBorder(null);
            this.zhanshidikuang.setBounds(40, 5, 39, 39);
            this.zhanshidikuang.setIcon(CutButtonImage.getImage("img/xy2uiimg/预览图片底框w39，h39px.png", -1, -1));
            this.zhanshidikuang.setOpaque(false);
            this.add(this.zhanshidikuang);
            this.price = new JLabel();
            this.dahuabixia.setFont(UIUtils.TEXT_FONT2);
            this.dahuabixia.setForeground(Color.white);
            this.dahuabixia.setHorizontalAlignment(0);
            this.dahuabishang.setFont(UIUtils.TEXT_FONT2);
            this.dahuabishang.setForeground(Color.white);
            this.dahuabishang.setHorizontalAlignment(0);
            this.danjia.setFont(UIUtils.TEXT_FONT2);
            this.danjia.setForeground(Color.white);
            this.danjia.setHorizontalAlignment(0);
            this.price.setFont(UIUtils.TEXT_FONT2);
            this.price.setForeground(Color.white);
            this.price.setHorizontalAlignment(0);
            this.cbGmoveMouslisten = new CBGmoveMouslisten(salegoods.getSaletype(), salegoods.getOtherid());
            this.tupian.addMouseListener(this.cbGmoveMouslisten);
            this.tupian.setBounds(40, 5, 39, 39);
            this.dahuabixia.setBounds(110, 25, 150, 24);
            this.dahuabishang.setBounds(110, 5, 150, 24);
            this.price.setBounds(230, 0, 125, 40);
            this.danjia.setBounds(350, 0, 125, 40);
            (this.mySaleBtn2 = new CBGMySaleBtn("img/xy2uiimg/21_png.button.tab_nex.png", 1, UIUtils.COLOR_BTNTEXT, UIUtils.TEXT_FONT, 5, "下架", this)).setBounds(500, 15, 43, 17);
            this.mySaleBtn2.setName("下架");
            this.add(this.mySaleBtn2);
            this.add(this.tupian);
            this.add(this.dahuabixia);
            this.add(this.dahuabishang);
            this.add(this.danjia);
            this.add(this.price);
            this.SX(salegoods);
        }
    }
    
    public void SX(Salegoods salegoods) {
        this.salegoods = salegoods;
        this.tupian.setIcon(CutButtonImage.getCBG((int)salegoods.getSaletype(), salegoods.getSaleskin(), 39, 39));
        this.dahuabishang.setText(salegoods.getSalename());
        this.cbGmoveMouslisten.qh(salegoods.getSaletype(), salegoods.getOtherid());
        if ((int)salegoods.getSaletype() == 2) {
            this.dahuabixia.setText(UserData.getMS(salegoods.getOtherid()));
        }
        else {
            this.dahuabixia.setText(null);
        }
        if ((int)salegoods.getFlag() == 1) {
            this.danjia.setText("已下架");
        }
        else if ((int)salegoods.getFlag() == 2) {
            this.danjia.setText("已上架");
        }
        else if ((int)salegoods.getFlag() == 3) {
            this.danjia.setText("被下单");
        }
        else if ((int)salegoods.getFlag() == 4) {
            this.danjia.setText("已售出");
        }
        else if ((int)salegoods.getFlag() == 5) {
            this.danjia.setText("已取出");
        }
        else {
            this.danjia.setText(null);
        }
        this.price.setText(salegoods.getSaleprice() + "");
        if ((int)salegoods.getFlag() == 2) {
            try {
                this.mySaleBtn2.setText("下架");
            }
            catch (Exception e) {
                e.printStackTrace();
            }
            this.mySaleBtn2.setName("下架");
            this.mySaleBtn2.setVisible(true);
        }
        else if ((int)salegoods.getFlag() == 1) {
            try {
                this.mySaleBtn2.setText("取回");
            }
            catch (Exception e) {
                e.printStackTrace();
            }
            this.mySaleBtn2.setName("取回");
            this.mySaleBtn2.setVisible(true);
        }
        else {
            this.mySaleBtn2.setVisible(false);
        }
    }
    
    public void caozuo(String type) {
        Salegoods salegoods = new Salegoods();
        salegoods.setSaleid(this.salegoods.getSaleid());
        if (type.equals("上架")) {
            try {
                this.mySaleBtn2.setText("下架");
            }
            catch (Exception e) {
                e.printStackTrace();
            }
            this.mySaleBtn2.setName("下架");
            salegoods.setFlag(Integer.valueOf(2));
        }
        else if (type.equals("下架")) {
            try {
                this.mySaleBtn2.setText("取回");
            }
            catch (Exception e) {
                e.printStackTrace();
            }
            this.mySaleBtn2.setName("取回");
            salegoods.setFlag(Integer.valueOf(1));
        }
        else if (type.equals("取回")) {
            ConfigureBean allConfigure = UserMessUntil.getConfigureBean();
            Map<BigDecimal, Configure> item = allConfigure.getAllConfigure();
            Configure configure = (Configure)item.get(new BigDecimal(1));
            if (UserMessUntil.getPetListTable() != null && UserMessUntil.getPetListTable().size() >= Integer.parseInt(configure.getXdzhssx())) {
                ZhuFrame.getZhuJpanel().addPrompt2("您的召唤兽可携带的数量已满！！！");
                return;
            }
            TraslationWantSendCardJpanel traslationWantSendCardJpanel = TrslationMainJframe.getTrslationMainJframe().getTrslationMainJpanel().getTranslationMainCardJpanel().getTraslationWantSentJpanel().getTraslationWantSendCardJpanel();
            traslationWantSendCardJpanel.getTraslationWantSentYijishangpinJpanel().removeJpanel(this);
            salegoods.setFlag(Integer.valueOf(5));
        }
        String sendmes = Agreement.getAgreement().searchOperationGoodAgreement(GsonUtil.getGsonUtil().getgson().toJson(salegoods));
        SendMessageUntil.toServer(sendmes);
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        if (MyIsif.getStyle().equals("水墨")) {
            super.paintComponent(g);
            g.setColor(new Color(245, 245, 245));
            if (this.icon == null) {
                this.icon = CutButtonImage.getImage("inkImg/background/12.png", 548, 2);
            }
            g.drawImage(this.icon.getImage(), 0, 48, 548, 2, this);
        }
        else {
            super.paintComponent(g);
            g.setColor(new Color(245, 245, 245));
            if (this.icon == null) {
                this.icon = CutButtonImage.getImage("img/xy2uiimg/分割线w538px,h1px,上下间隔51px.png", 548, 2);
            }
            g.drawImage(this.icon.getImage(), 0, 48, 548, 2, this);
        }
    }
}
