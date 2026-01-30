package org.cbg.panel;

import java.awt.Graphics;
import org.come.socket.SendMessageUntil;
import org.come.socket.Agreement;
import org.cbg.entity.Collection;
import org.cbg.frame.TrslationMainJframe;
import java.awt.event.MouseEvent;
import java.awt.event.MouseAdapter;
import org.come.until.CutButtonImage;
import java.awt.Color;
import java.awt.Font;
import com.tool.btn.FormsOnOffBtn;
import com.tool.tcpimg.UIUtils;
import java.awt.Dimension;
import com.updateNew.MyIsif;
import javax.swing.ImageIcon;
import javax.swing.JTextField;
import org.cbg.frame.TraslationCommodityJFrame;
import org.cbg.btn.CBGLijigoumaiBtn;
import org.cbg.btn.TrslationBtn;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class TraslationCommodityMainJPane extends JPanel
{
    private JLabel tupian;
    private JLabel wupinming;
    private JLabel jine;
    private JLabel shoucangrenshu;
    private JLabel shoucangshuzi;
    private TrslationBtn shoucang;
    private TrslationBtn tianjiahaoyou;
    private CBGLijigoumaiBtn lijigoumai;
    private TraslationCommodityJFrame traslationCommodityJFrame;
    private JLabel quanxuankuang;
    private JLabel gouxuan;
    private JLabel increaseAmount;
    private JTextField number;
    private JTextField nickname;
    private JTextField sellerID;
    private JTextField assignID;
    private JTextField sellingtime;
    private JTextField publicitytime;
    private int stateOrNo;
    private ImageIcon icon;
    
    public TraslationCommodityMainJPane() {
        this.stateOrNo = 1;
        if (MyIsif.getStyle().equals("水墨")) {
            this.setPreferredSize(new Dimension(312, 385));
            this.setOpaque(false);
            this.setLayout(null);
            this.tupian = new JLabel();
            this.wupinming = new JLabel("元气丹");
            this.jine = new JLabel("￥555.00元");
            this.shoucang = new TrslationBtn("inkImg/button/74.png", 1);
            this.shoucangrenshu = new JLabel("44 人收藏");
            this.tianjiahaoyou = new TrslationBtn("inkImg/button/16.png", 1);
            this.lijigoumai = new CBGLijigoumaiBtn("inkImg/button/18.png", 1, UIUtils.COLOR_BTNPUTONG, UIUtils.TEXT_HY16, "立即购买", this);
            this.number = new JTextField();
            this.nickname = new JTextField();
            this.sellerID = new JTextField();
            this.assignID = new JTextField();
            this.sellingtime = new JTextField();
            this.publicitytime = new JTextField();
            this.gouxuan = new JLabel();
            this.quanxuankuang = new JLabel();
            FormsOnOffBtn guanbi = new FormsOnOffBtn("inkImg/button/1.png", 1, 79);
            this.increaseAmount = new JLabel("<html><body>同意支付<font color='#FF0000' >123121</font>元预定费</body></html>");
            this.tupian.setBounds(58, 41, 55, 53);
            this.wupinming.setBounds(121, 41, 100, 18);
            this.jine.setBounds(118, 61, 100, 18);
            this.shoucang.setBounds(259, 83, 28, 28);
            this.shoucangrenshu.setBounds(155, 88, 100, 18);
            this.tianjiahaoyou.setBounds(259, 180, 18, 18);
            this.lijigoumai.setBounds(168, 335, 100, 26);
            this.number.setBounds(156, 156, 120, 18);
            this.nickname.setBounds(156, 180, 120, 18);
            this.sellerID.setBounds(156, 204, 120, 18);
            this.assignID.setBounds(156, 231, 120, 18);
            this.sellingtime.setBounds(156, 256, 120, 18);
            this.publicitytime.setBounds(156, 281, 120, 18);
            guanbi.setBounds(287, 3, 25, 25);
            this.gouxuan.setBounds(48, 126, 15, 15);
            this.quanxuankuang.setBounds(48, 126, 15, 15);
            this.increaseAmount.setBounds(62, 124, 230, 18);
            this.tupian.setOpaque(false);
            this.wupinming.setOpaque(false);
            this.jine.setOpaque(false);
            this.shoucang.setOpaque(false);
            this.shoucangrenshu.setOpaque(false);
            this.tianjiahaoyou.setOpaque(false);
            this.lijigoumai.setOpaque(false);
            this.number.setOpaque(false);
            this.nickname.setOpaque(false);
            this.sellerID.setOpaque(false);
            this.assignID.setOpaque(false);
            this.sellingtime.setOpaque(false);
            this.publicitytime.setOpaque(false);
            this.gouxuan.setOpaque(false);
            this.quanxuankuang.setOpaque(false);
            this.increaseAmount.setOpaque(false);
            this.lijigoumai.setBorder(null);
            this.number.setBorder(null);
            this.nickname.setBorder(null);
            this.sellerID.setBorder(null);
            this.assignID.setBorder(null);
            this.sellingtime.setBorder(null);
            this.publicitytime.setBorder(null);
            this.increaseAmount.setBorder(null);
            this.wupinming.setFont(new Font("宋体", 1, 15));
            this.jine.setFont(new Font("宋体", 1, 15));
            this.number.setFont(UIUtils.TEXT_FONT1);
            this.nickname.setFont(UIUtils.TEXT_FONT1);
            this.sellerID.setFont(UIUtils.TEXT_FONT1);
            this.assignID.setFont(UIUtils.TEXT_FONT1);
            this.sellingtime.setFont(UIUtils.TEXT_FONT);
            this.publicitytime.setFont(UIUtils.TEXT_FONT1);
            this.increaseAmount.setFont(UIUtils.TEXT_HY17);
            this.wupinming.setForeground(Color.BLACK);
            this.jine.setForeground(Color.red);
            this.shoucangrenshu.setForeground(Color.gray);
            this.number.setForeground(Color.white);
            this.nickname.setForeground(Color.white);
            this.sellerID.setForeground(Color.white);
            this.assignID.setForeground(Color.white);
            this.sellingtime.setForeground(Color.white);
            this.publicitytime.setForeground(Color.white);
            this.increaseAmount.setForeground(new Color(187, 165, 75));
            this.gouxuan.setIcon(null);
            this.quanxuankuang.setIcon(CutButtonImage.getImage("inkImg/button/14.png", 15, 15));
            this.gouxuan.setName("1");
            this.shoucangrenshu.setHorizontalAlignment(4);
            this.number.setEditable(false);
            this.nickname.setEditable(false);
            this.sellerID.setEditable(false);
            this.assignID.setEditable(false);
            this.sellingtime.setEditable(false);
            this.publicitytime.setEditable(false);
            this.add(this.tupian);
            this.add(this.wupinming);
            this.add(this.jine);
            this.add(this.shoucang);
            this.add(this.shoucangrenshu);
            this.add(this.tianjiahaoyou);
            this.add(this.lijigoumai);
            this.add(this.number);
            this.add(this.nickname);
            this.add(this.sellerID);
            this.add(this.assignID);
            this.add(this.sellingtime);
            this.add(this.publicitytime);
            this.add(guanbi);
            this.add(this.gouxuan);
            this.add(this.quanxuankuang);
            this.add(this.increaseAmount);
            this.shoucang.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    try {
                        if (TraslationCommodityMainJPane.this.stateOrNo == 1) {
                            TraslationCommodityMainJPane.this.shoucang.setIcons(CutButtonImage.cuts("inkImg/button/74.png"));
                            TraslationCommodityMainJPane.this.stateOrNo = 0;
                        }
                        else {
                            TraslationCommodityMainJPane.this.shoucang.setIcons(CutButtonImage.cuts("inkImg/button/73.png"));
                            TraslationCommodityMainJPane.this.stateOrNo = 1;
                        }
                    }
                    catch (Exception e2) {
                        e2.printStackTrace();
                    }
                    TrslationBtn shoucangBtn = (TrslationBtn)TrslationMainJframe.getTrslationMainJframe().getShoucangBtn();
                    shoucangBtn.setIcons(TraslationCommodityMainJPane.this.shoucang.getIcons());
                    Collection collection = new Collection();
                    String sendmes = Agreement.getAgreement().searchCollectionAgreement(TraslationCommodityMainJPane.this.number.getText());
                    SendMessageUntil.toServer(sendmes);
                }
            });
            this.gouxuan.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    TraslationMyMessageJpanel traslationMyMessageJpanel = TrslationMainJframe.getTrslationMainJframe().getTrslationMainJpanel().getTranslationMainCardJpanel().getTraslationMyMessageJpanel();
                    if (TraslationCommodityMainJPane.this.gouxuan.getName().equals("1")) {
                        TraslationCommodityMainJPane.this.gouxuan.setIcon(CutButtonImage.getImage("inkImg/button/13.png", 15, 15));
                        TraslationCommodityMainJPane.this.gouxuan.setName("0");
                        TrslationMainJframe.getTrslationMainJframe().setGoodsGouxuangeshu(TrslationMainJframe.getTrslationMainJframe().getGoodsGouxuangeshu() + 1);
                        if (TrslationMainJframe.getTrslationMainJframe().getGoodsGouxuangeshu() == 10) {
                            traslationMyMessageJpanel.getGouxuan().setIcon(CutButtonImage.getImage("inkImg/button/13.png", -1, -1));
                            traslationMyMessageJpanel.getGouxuan().setName("0");
                        }
                    }
                    else {
                        TraslationCommodityMainJPane.this.gouxuan.setIcon(null);
                        TraslationCommodityMainJPane.this.gouxuan.setName("1");
                        TrslationMainJframe.getTrslationMainJframe().setGoodsGouxuangeshu(TrslationMainJframe.getTrslationMainJframe().getGoodsGouxuangeshu() - 1);
                        if (TrslationMainJframe.getTrslationMainJframe().getGoodsGouxuangeshu() != 10) {
                            traslationMyMessageJpanel.getGouxuan().setIcon(null);
                            traslationMyMessageJpanel.getGouxuan().setName("1");
                        }
                    }
                }
            });
        }
        else {
            this.setPreferredSize(new Dimension(281, 405));
            this.setOpaque(false);
            this.setLayout(null);
            this.tupian = new JLabel();
            this.wupinming = new JLabel("元气丹");
            this.jine = new JLabel("￥555.00元");
            this.shoucang = new TrslationBtn("inkImg/button/74.png", 1);
            this.shoucangrenshu = new JLabel("44 人收藏");
            this.tianjiahaoyou = new TrslationBtn("inkImg/button/16.png", 1);
            this.lijigoumai = new CBGLijigoumaiBtn("inkImg/hongmu/1_png.button.btn_xy.png", 1, UIUtils.COLOR_BTNPUTONG, UIUtils.TEXT_HY88, "立即购买", this);
            this.number = new JTextField();
            this.nickname = new JTextField();
            this.sellerID = new JTextField();
            this.assignID = new JTextField();
            this.sellingtime = new JTextField();
            this.publicitytime = new JTextField();
            this.gouxuan = new JLabel();
            this.quanxuankuang = new JLabel();
            FormsOnOffBtn guanbi = new FormsOnOffBtn("img/xy2uiimg/8_jpg.button.btn_close.jpg", 1, 79);
            this.increaseAmount = new JLabel("<html><body>同意支付<font color='#FF0000' >123121</font>元预定费</body></html>");
            this.tupian.setBounds(31, 53, 55, 53);
            this.wupinming.setBounds(97, 53, 100, 18);
            this.jine.setBounds(94, 73, 100, 18);
            this.shoucang.setBounds(235, 95, 28, 28);
            this.shoucangrenshu.setBounds(135, 105, 100, 18);
            this.tianjiahaoyou.setBounds(231, 191, 16, 18);
            this.lijigoumai.setBounds(144, 347, 90, 21);
            this.number.setBounds(134, 168, 120, 18);
            this.nickname.setBounds(134, 192, 120, 18);
            this.sellerID.setBounds(134, 216, 120, 18);
            this.assignID.setBounds(134, 243, 120, 18);
            this.sellingtime.setBounds(134, 268, 120, 18);
            this.publicitytime.setBounds(134, 293, 120, 18);
            guanbi.setBounds(263, 0, 23, 23);
            this.gouxuan.setBounds(24, 138, 15, 15);
            this.quanxuankuang.setBounds(24, 138, 15, 15);
            this.increaseAmount.setBounds(40, 138, 230, 18);
            this.tupian.setOpaque(false);
            this.wupinming.setOpaque(false);
            this.jine.setOpaque(false);
            this.shoucang.setOpaque(false);
            this.shoucangrenshu.setOpaque(false);
            this.tianjiahaoyou.setOpaque(false);
            this.lijigoumai.setOpaque(false);
            this.number.setOpaque(false);
            this.nickname.setOpaque(false);
            this.sellerID.setOpaque(false);
            this.assignID.setOpaque(false);
            this.sellingtime.setOpaque(false);
            this.publicitytime.setOpaque(false);
            this.gouxuan.setOpaque(false);
            this.quanxuankuang.setOpaque(false);
            this.increaseAmount.setOpaque(false);
            this.lijigoumai.setBorder(null);
            this.number.setBorder(null);
            this.nickname.setBorder(null);
            this.sellerID.setBorder(null);
            this.assignID.setBorder(null);
            this.sellingtime.setBorder(null);
            this.publicitytime.setBorder(null);
            this.increaseAmount.setBorder(null);
            this.wupinming.setFont(new Font("宋体", 1, 15));
            this.jine.setFont(new Font("宋体", 1, 15));
            this.number.setFont(UIUtils.TEXT_FONT1);
            this.nickname.setFont(UIUtils.TEXT_FONT1);
            this.sellerID.setFont(UIUtils.TEXT_FONT1);
            this.assignID.setFont(UIUtils.TEXT_FONT1);
            this.sellingtime.setFont(UIUtils.TEXT_FONT);
            this.publicitytime.setFont(UIUtils.TEXT_FONT1);
            this.increaseAmount.setFont(UIUtils.TEXT_HY17);
            this.wupinming.setForeground(Color.BLACK);
            this.jine.setForeground(Color.red);
            this.shoucangrenshu.setForeground(Color.gray);
            this.number.setForeground(Color.white);
            this.nickname.setForeground(Color.white);
            this.sellerID.setForeground(Color.white);
            this.assignID.setForeground(Color.white);
            this.sellingtime.setForeground(Color.white);
            this.publicitytime.setForeground(Color.white);
            this.increaseAmount.setForeground(new Color(187, 165, 75));
            this.gouxuan.setIcon(null);
            this.quanxuankuang.setIcon(CutButtonImage.getImage("img/xy2uiimg/待选.png", 15, 15));
            this.gouxuan.setName("1");
            this.shoucangrenshu.setHorizontalAlignment(4);
            this.number.setEditable(false);
            this.nickname.setEditable(false);
            this.sellerID.setEditable(false);
            this.assignID.setEditable(false);
            this.sellingtime.setEditable(false);
            this.publicitytime.setEditable(false);
            this.add(this.tupian);
            this.add(this.wupinming);
            this.add(this.jine);
            this.add(this.shoucang);
            this.add(this.shoucangrenshu);
            this.add(this.tianjiahaoyou);
            this.add(this.lijigoumai);
            this.add(this.number);
            this.add(this.nickname);
            this.add(this.sellerID);
            this.add(this.assignID);
            this.add(this.sellingtime);
            this.add(this.publicitytime);
            this.add(guanbi);
            this.add(this.gouxuan);
            this.add(this.quanxuankuang);
            this.add(this.increaseAmount);
            this.shoucang.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    try {
                        if (TraslationCommodityMainJPane.this.stateOrNo == 1) {
                            TraslationCommodityMainJPane.this.shoucang.setIcons(CutButtonImage.cuts("inkImg/button/74.png"));
                            TraslationCommodityMainJPane.this.stateOrNo = 0;
                        }
                        else {
                            TraslationCommodityMainJPane.this.shoucang.setIcons(CutButtonImage.cuts("inkImg/button/73.png"));
                            TraslationCommodityMainJPane.this.stateOrNo = 1;
                        }
                    }
                    catch (Exception e2) {
                        e2.printStackTrace();
                    }
                    TrslationBtn shoucangBtn = (TrslationBtn)TrslationMainJframe.getTrslationMainJframe().getShoucangBtn();
                    shoucangBtn.setIcons(TraslationCommodityMainJPane.this.shoucang.getIcons());
                    Collection collection = new Collection();
                    String sendmes = Agreement.getAgreement().searchCollectionAgreement(TraslationCommodityMainJPane.this.number.getText());
                    SendMessageUntil.toServer(sendmes);
                }
            });
            this.gouxuan.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    TraslationMyMessageJpanel traslationMyMessageJpanel = TrslationMainJframe.getTrslationMainJframe().getTrslationMainJpanel().getTranslationMainCardJpanel().getTraslationMyMessageJpanel();
                    if (TraslationCommodityMainJPane.this.gouxuan.getName().equals("1")) {
                        TraslationCommodityMainJPane.this.gouxuan.setIcon(CutButtonImage.getImage("inkImg/button/13.png", 15, 15));
                        TraslationCommodityMainJPane.this.gouxuan.setName("0");
                        TrslationMainJframe.getTrslationMainJframe().setGoodsGouxuangeshu(TrslationMainJframe.getTrslationMainJframe().getGoodsGouxuangeshu() + 1);
                        if (TrslationMainJframe.getTrslationMainJframe().getGoodsGouxuangeshu() == 10) {
                            traslationMyMessageJpanel.getGouxuan().setIcon(CutButtonImage.getImage("inkImg/button/13.png", -1, -1));
                            traslationMyMessageJpanel.getGouxuan().setName("0");
                        }
                    }
                    else {
                        TraslationCommodityMainJPane.this.gouxuan.setIcon(null);
                        TraslationCommodityMainJPane.this.gouxuan.setName("1");
                        TrslationMainJframe.getTrslationMainJframe().setGoodsGouxuangeshu(TrslationMainJframe.getTrslationMainJframe().getGoodsGouxuangeshu() - 1);
                        if (TrslationMainJframe.getTrslationMainJframe().getGoodsGouxuangeshu() != 10) {
                            traslationMyMessageJpanel.getGouxuan().setIcon(null);
                            traslationMyMessageJpanel.getGouxuan().setName("1");
                        }
                    }
                }
            });
        }
    }
    
    public void setViewTrue(boolean boo) {
        this.gouxuan.setVisible(boo);
        this.quanxuankuang.setVisible(boo);
        this.increaseAmount.setVisible(boo);
    }
    
    public void cleanNeirong() {
        this.tupian.setIcon(null);
        this.wupinming.setText("");
        this.jine.setText("");
        this.shoucangrenshu.setText("");
        this.number.setText("");
        this.nickname.setText("");
        this.sellerID.setText("");
        this.assignID.setText("");
        this.sellingtime.setText("");
        this.publicitytime.setText("");
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        if (MyIsif.getStyle().equals("水墨")) {
            super.paintComponent(g);
            if (this.icon == null) {
                this.icon = new ImageIcon("inkImg/background/39.png");
            }
            g.drawImage(this.icon.getImage(), 0, 0, 312, 385, this);
        }
        else {
            super.paintComponent(g);
            if (this.icon == null) {
                this.icon = new ImageIcon("img/xy2uiimg/商品详情w286,h407px.png");
            }
            g.drawImage(this.icon.getImage(), -3, 0, 286, 407, this);
        }
    }
    
    public JLabel getTupian() {
        return this.tupian;
    }
    
    public void setTupian(JLabel tupian) {
        this.tupian = tupian;
    }
    
    public JLabel getWupinming() {
        return this.wupinming;
    }
    
    public void setWupinming(JLabel wupinming) {
        this.wupinming = wupinming;
    }
    
    public JLabel getJine() {
        return this.jine;
    }
    
    public void setJine(JLabel jine) {
        this.jine = jine;
    }
    
    public JLabel getShoucangrenshu() {
        return this.shoucangrenshu;
    }
    
    public void setShoucangrenshu(JLabel shoucangrenshu) {
        this.shoucangrenshu = shoucangrenshu;
    }
    
    public JLabel getShoucangshuzi() {
        return this.shoucangshuzi;
    }
    
    public void setShoucangshuzi(JLabel shoucangshuzi) {
        this.shoucangshuzi = shoucangshuzi;
    }
    
    public TrslationBtn getShoucang() {
        return this.shoucang;
    }
    
    public void setShoucang(TrslationBtn shoucang) {
        this.shoucang = shoucang;
    }
    
    public TrslationBtn getTianjiahaoyou() {
        return this.tianjiahaoyou;
    }
    
    public void setTianjiahaoyou(TrslationBtn tianjiahaoyou) {
        this.tianjiahaoyou = tianjiahaoyou;
    }
    
    public int getStateOrNo() {
        return this.stateOrNo;
    }
    
    public void setStateOrNo(int stateOrNo) {
        this.stateOrNo = stateOrNo;
    }
    
    public ImageIcon getIcon() {
        return this.icon;
    }
    
    public void setIcon(ImageIcon icon) {
        this.icon = icon;
    }
    
    public TraslationCommodityJFrame getTraslationCommodityJFrame() {
        return this.traslationCommodityJFrame;
    }
    
    public void setTraslationCommodityJFrame(TraslationCommodityJFrame traslationCommodityJFrame) {
        this.traslationCommodityJFrame = traslationCommodityJFrame;
    }
    
    public JTextField getNumber() {
        return this.number;
    }
    
    public void setNumber(JTextField number) {
        this.number = number;
    }
    
    public JTextField getNickname() {
        return this.nickname;
    }
    
    public void setNickname(JTextField nickname) {
        this.nickname = nickname;
    }
    
    public JTextField getSellerID() {
        return this.sellerID;
    }
    
    public void setSellerID(JTextField sellerID) {
        this.sellerID = sellerID;
    }
    
    public JTextField getAssignID() {
        return this.assignID;
    }
    
    public void setAssignID(JTextField assignID) {
        this.assignID = assignID;
    }
    
    public JTextField getSellingtime() {
        return this.sellingtime;
    }
    
    public void setSellingtime(JTextField sellingtime) {
        this.sellingtime = sellingtime;
    }
    
    public JTextField getPublicitytime() {
        return this.publicitytime;
    }
    
    public void setPublicitytime(JTextField publicitytime) {
        this.publicitytime = publicitytime;
    }
    
    public CBGLijigoumaiBtn getLijigoumai() {
        return this.lijigoumai;
    }
    
    public void setLijigoumai(CBGLijigoumaiBtn lijigoumai) {
        this.lijigoumai = lijigoumai;
    }
    
    public JLabel getQuanxuankuang() {
        return this.quanxuankuang;
    }
    
    public void setQuanxuankuang(JLabel quanxuankuang) {
        this.quanxuankuang = quanxuankuang;
    }
    
    public JLabel getGouxuan() {
        return this.gouxuan;
    }
    
    public void setGouxuan(JLabel gouxuan) {
        this.gouxuan = gouxuan;
    }
    
    public JLabel getIncreaseAmount() {
        return this.increaseAmount;
    }
    
    public void setIncreaseAmount(JLabel increaseAmount) {
        this.increaseAmount = increaseAmount;
    }
}
