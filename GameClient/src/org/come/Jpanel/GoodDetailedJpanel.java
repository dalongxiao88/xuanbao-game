package org.come.Jpanel;

import java.awt.Graphics;
import com.tool.role.RoleData;
import org.come.Frame.TaobaoCourtMainJframe;
import org.come.until.GoodsListFromServerUntil;
import org.come.until.CutButtonImage;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.Color;
import com.tool.tcpimg.UIUtils;
import com.tool.btn.FormsOnOffBtn;
import java.awt.Dimension;
import com.updateNew.MyIsif;
import javax.swing.ImageIcon;
import org.come.model.Shop;
import org.come.model.Eshop;
import javax.swing.JTextField;
import javax.swing.JLabel;
import com.tool.btn.CommonBtn;
import com.tool.btn.TextBtn;
import javax.swing.JPanel;

public class GoodDetailedJpanel extends JPanel
{
    private int type;
    private TextBtn changeXY;
    private CommonBtn buyBtn;
    private CommonBtn addBtn;
    private CommonBtn reduceBtn;
    private JLabel wupinPrice;
    private JLabel xiaohaoXianyu;
    private JLabel yonghuXianyu;
    private JTextField goumaiCount;
    private JLabel priceLa;
    private JLabel priceVal;
    private JLabel haveDuoCai;
    private JLabel duoCaiVal;
    private JLabel wupinDetail;
    private JLabel goodName;
    private Eshop eshop;
    private Shop shop;
    private String nowMoneyType;
    private ImageIcon icon;
    private ImageIcon icon1;
    private ImageIcon icon2;
    
    public GoodDetailedJpanel() throws Exception {
        this.type = 0;
        if (MyIsif.getStyle().equals("水墨")) {
            this.setPreferredSize(new Dimension(382, 465));
            this.setLayout(null);
            this.setOpaque(false);
            FormsOnOffBtn offBtn = new FormsOnOffBtn("inkImg/button/1.png", 1, 44);
            offBtn.setBounds(356, 2, 25, 25);
            this.add(offBtn);
            (this.wupinPrice = new JLabel()).setFont(UIUtils.TEXT_FONT1);
            this.wupinPrice.setText("3");
            this.wupinPrice.setOpaque(false);
            this.wupinPrice.setForeground(Color.WHITE);
            this.wupinPrice.setBounds(167, 251, 164, 22);
            (this.goumaiCount = new JTextField()).setFont(UIUtils.TEXT_FONT1);
            this.goumaiCount.setText("0");
            this.goumaiCount.setForeground(Color.WHITE);
            this.goumaiCount.setOpaque(false);
            this.goumaiCount.setBorder(null);
            this.goumaiCount.setBounds(167, 283, 138, 16);
            this.goumaiCount.getDocument().addDocumentListener(new DocumentListener() {
                @Override
                public void removeUpdate(DocumentEvent e) {
                    if ("".equals(GoodDetailedJpanel.this.goumaiCount.getText().trim())) {
                        GoodDetailedJpanel.this.xiaohaoXianyu.setText("0");
                        GoodDetailedJpanel.this.priceVal.setText("0" + GoodDetailedJpanel.this.nowMoneyType);
                    }
                    else {
                        int wupinCount = (int)Integer.valueOf(GoodDetailedJpanel.this.goumaiCount.getText());
                        int goodsPrice = (int)Integer.valueOf(GoodDetailedJpanel.this.wupinPrice.getText());
                        GoodDetailedJpanel.this.xiaohaoXianyu.setText(goodsPrice * wupinCount + "");
                        GoodDetailedJpanel.this.priceVal.setText(goodsPrice * wupinCount + GoodDetailedJpanel.this.nowMoneyType);
                    }
                }
                
                @Override
                public void insertUpdate(DocumentEvent e) {
                    int wupinCount = (int)Integer.valueOf(GoodDetailedJpanel.this.goumaiCount.getText());
                    int goodsPrice = (int)Integer.valueOf(GoodDetailedJpanel.this.wupinPrice.getText());
                    GoodDetailedJpanel.this.xiaohaoXianyu.setText(goodsPrice * wupinCount + "");
                    GoodDetailedJpanel.this.priceVal.setText(goodsPrice * wupinCount + GoodDetailedJpanel.this.nowMoneyType);
                }
                
                @Override
                public void changedUpdate(DocumentEvent e) {
                }
            });
            this.goumaiCount.addKeyListener(new KeyListener() {
                @Override
                public void keyTyped(KeyEvent e) {
                    int charstr = e.getKeyChar();
                    String str = GoodDetailedJpanel.this.goumaiCount.getText();
                    if (str.length() == 0) {
                        GoodDetailedJpanel.this.goumaiCount.setText("0");
                    }
                    if (charstr < 48 || charstr > 57) {
                        e.consume();
                    }
                    if (str.length() == 1 && str.equals("0")) {
                        GoodDetailedJpanel.this.goumaiCount.setText("");
                    }
                    if (str.length() > 0 && str.length() > 1 && str.length() > 2) {
                        e.consume();
                        GoodDetailedJpanel.this.goumaiCount.setText("999");
                    }
                }
                
                @Override
                public void keyPressed(KeyEvent e) {
                }
                
                @Override
                public void keyReleased(KeyEvent e) {
                }
            });
            (this.xiaohaoXianyu = new JLabel()).setFont(UIUtils.TEXT_FONT1);
            this.xiaohaoXianyu.setText("0");
            this.xiaohaoXianyu.setForeground(Color.WHITE);
            this.xiaohaoXianyu.setOpaque(false);
            this.xiaohaoXianyu.setBounds(167, 312, 138, 16);
            (this.yonghuXianyu = new JLabel()).setFont(UIUtils.TEXT_FONT1);
            this.yonghuXianyu.setText("0");
            this.yonghuXianyu.setForeground(Color.WHITE);
            this.yonghuXianyu.setOpaque(false);
            this.yonghuXianyu.setBounds(167, 341, 138, 16);
            (this.changeXY = new TextBtn("inkImg/button/2.png", 1, "充值", 1)).setBounds(274, 340, 34, 17);
            (this.buyBtn = new CommonBtn("inkImg/button/18.png", 1, "确认购买", 1, this, null)).setBounds(155, 414, 100, 26);
            (this.addBtn = new CommonBtn("inkImg/button/16.png", 1, "", 2, this)).setBounds(290, 281, 19, 20);
            (this.reduceBtn = new CommonBtn("inkImg/button/17.png", 1, "", 3, this)).setBounds(272, 281, 19, 20);
            (this.priceLa = new JLabel("售价:")).setFont(UIUtils.TEXT_FONT1);
            this.priceLa.setForeground(Color.WHITE);
            this.priceLa.setBounds(143, 211, 45, 20);
            (this.priceVal = new JLabel()).setFont(UIUtils.TEXT_FONT1);
            this.priceVal.setText("1" + this.nowMoneyType);
            this.priceVal.setOpaque(false);
            this.priceVal.setForeground(new Color(255, 0, 0));
            this.priceVal.setBounds(189, 211, 120, 20);
            (this.wupinDetail = new JLabel()).setOpaque(false);
            this.wupinDetail.setIcon(CutButtonImage.getImage("img/item/1.png", 114, 114));
            this.wupinDetail.setBounds(125, 74, 114, 114);
            (this.goodName = new JLabel()).setFont(UIUtils.TEXT_BOLD_FONT);
            this.goodName.setBounds(55, 45, 300, 40);
            this.goodName.setOpaque(false);
            this.goodName.setHorizontalAlignment(0);
            this.add(this.goodName);
            this.add(this.wupinDetail);
            this.add(this.priceLa);
            this.add(this.priceVal);
            this.add(this.addBtn);
            this.add(this.reduceBtn);
            this.add(this.wupinPrice);
            this.add(this.goumaiCount);
            this.add(this.xiaohaoXianyu);
            this.add(this.yonghuXianyu);
            this.add(this.buyBtn);
        }
        else {
            this.setPreferredSize(new Dimension(382, 465));
            this.setLayout(null);
            this.setOpaque(false);
            FormsOnOffBtn offBtn = new FormsOnOffBtn("inkImg/hongmu/s74.png", 1, 44);
            offBtn.setBounds(356, 2, 25, 25);
            this.add(offBtn);
            (this.wupinPrice = new JLabel()).setFont(UIUtils.TEXT_FONT1);
            this.wupinPrice.setText("3");
            this.wupinPrice.setOpaque(false);
            this.wupinPrice.setForeground(Color.WHITE);
            this.wupinPrice.setBounds(155, 251, 164, 22);
            (this.goumaiCount = new JTextField()).setFont(UIUtils.TEXT_FONT1);
            this.goumaiCount.setText("0");
            this.goumaiCount.setForeground(Color.WHITE);
            this.goumaiCount.setOpaque(false);
            this.goumaiCount.setBorder(null);
            this.goumaiCount.setBounds(155, 283, 138, 16);
            this.goumaiCount.getDocument().addDocumentListener(new DocumentListener() {
                @Override
                public void removeUpdate(DocumentEvent e) {
                    if ("".equals(GoodDetailedJpanel.this.goumaiCount.getText().trim())) {
                        GoodDetailedJpanel.this.xiaohaoXianyu.setText("0");
                        GoodDetailedJpanel.this.priceVal.setText("0" + GoodDetailedJpanel.this.nowMoneyType);
                    }
                    else {
                        int wupinCount = (int)Integer.valueOf(GoodDetailedJpanel.this.goumaiCount.getText());
                        int goodsPrice = (int)Integer.valueOf(GoodDetailedJpanel.this.wupinPrice.getText());
                        GoodDetailedJpanel.this.xiaohaoXianyu.setText(goodsPrice * wupinCount + "");
                        GoodDetailedJpanel.this.priceVal.setText(goodsPrice * wupinCount + GoodDetailedJpanel.this.nowMoneyType);
                    }
                }
                
                @Override
                public void insertUpdate(DocumentEvent e) {
                    int wupinCount = (int)Integer.valueOf(GoodDetailedJpanel.this.goumaiCount.getText());
                    int goodsPrice = (int)Integer.valueOf(GoodDetailedJpanel.this.wupinPrice.getText());
                    GoodDetailedJpanel.this.xiaohaoXianyu.setText(goodsPrice * wupinCount + "");
                    GoodDetailedJpanel.this.priceVal.setText(goodsPrice * wupinCount + GoodDetailedJpanel.this.nowMoneyType);
                }
                
                @Override
                public void changedUpdate(DocumentEvent e) {
                }
            });
            this.goumaiCount.addKeyListener(new KeyListener() {
                @Override
                public void keyTyped(KeyEvent e) {
                    int charstr = e.getKeyChar();
                    String str = GoodDetailedJpanel.this.goumaiCount.getText();
                    if (str.length() == 0) {
                        GoodDetailedJpanel.this.goumaiCount.setText("0");
                    }
                    if (charstr < 48 || charstr > 57) {
                        e.consume();
                    }
                    if (str.length() == 1 && str.equals("0")) {
                        GoodDetailedJpanel.this.goumaiCount.setText("");
                    }
                    if (str.length() > 0 && str.length() > 1 && str.length() > 2) {
                        e.consume();
                        GoodDetailedJpanel.this.goumaiCount.setText("999");
                    }
                }
                
                @Override
                public void keyPressed(KeyEvent e) {
                }
                
                @Override
                public void keyReleased(KeyEvent e) {
                }
            });
            (this.xiaohaoXianyu = new JLabel()).setFont(UIUtils.TEXT_FONT1);
            this.xiaohaoXianyu.setText("0");
            this.xiaohaoXianyu.setForeground(Color.WHITE);
            this.xiaohaoXianyu.setOpaque(false);
            this.xiaohaoXianyu.setBounds(155, 310, 138, 16);
            (this.yonghuXianyu = new JLabel()).setFont(UIUtils.TEXT_FONT1);
            this.yonghuXianyu.setText("0");
            this.yonghuXianyu.setForeground(Color.WHITE);
            this.yonghuXianyu.setOpaque(false);
            this.yonghuXianyu.setBounds(155, 338, 138, 16);
            (this.buyBtn = new CommonBtn("inkImg/hongmu/1_png.button.btn_xy.png", 1, "确认购买", 1, this)).setBounds(155, 414, 100, 26);
            (this.addBtn = new CommonBtn("img/xy2uiimg/55_png.button.btn_12.png", 1, "", 2, this)).setBounds(290, 281, 19, 20);
            (this.reduceBtn = new CommonBtn("img/xy2uiimg/34_png.button.btn_6.png", 1, "", 3, this)).setBounds(272, 281, 19, 20);
            (this.priceLa = new JLabel("售价:")).setFont(UIUtils.TEXT_FONT1);
            this.priceLa.setForeground(Color.WHITE);
            this.priceLa.setBounds(143, 211, 45, 20);
            (this.priceVal = new JLabel()).setFont(UIUtils.TEXT_FONT1);
            this.priceVal.setText("1" + this.nowMoneyType);
            this.priceVal.setOpaque(false);
            this.priceVal.setForeground(new Color(255, 0, 0));
            this.priceVal.setBounds(189, 211, 120, 20);
            (this.wupinDetail = new JLabel()).setOpaque(false);
            this.wupinDetail.setIcon(CutButtonImage.getImage("img/item/1.png", 114, 114));
            this.wupinDetail.setBounds(125, 74, 114, 114);
            (this.goodName = new JLabel()).setFont(UIUtils.TEXT_BOLD_FONT);
            this.goodName.setBounds(55, 45, 300, 40);
            this.goodName.setOpaque(false);
            this.goodName.setHorizontalAlignment(0);
            this.add(this.goodName);
            this.add(this.wupinDetail);
            this.add(this.priceLa);
            this.add(this.priceVal);
            this.add(this.addBtn);
            this.add(this.reduceBtn);
            this.add(this.wupinPrice);
            this.add(this.goumaiCount);
            this.add(this.xiaohaoXianyu);
            this.add(this.yonghuXianyu);
            this.add(this.buyBtn);
        }
    }
    
    public void gainGoodsMessage(Eshop eshop) {
        this.eshop = null;
        this.shop = null;
        this.type = 0;
        if (MyIsif.getStyle().equals("水墨")) {
            if ((this.eshop = eshop) == null) {
                return;
            }
            this.goodName.setText("<html><body><font color='#FFFF00'>你确定要购买</font><font color='#00FF00'>" + eshop.getEshopname() + "</font><font color='#FFFF00'>吗?</font></body></html>");
            this.wupinDetail.setIcon(GoodsListFromServerUntil.imgpathAdaptive(eshop.getEshopskin(), 114, 114));
            this.wupinPrice.setText(eshop.getEshopprice());
            if (TaobaoCourtMainJframe.getTaobaoCourtJframe().getTaobaoCourtMainJpanel().getTaobaoCourtCardJpanel().getShopOnlineJpanel().getShopType() == 11) {
                this.nowMoneyType = "积分";
                this.yonghuXianyu.setText(RoleData.getRoleData().getLoginResult().getMoney().toString());
            }
            else if (TaobaoCourtMainJframe.getTaobaoCourtJframe().getTaobaoCourtMainJpanel().getTaobaoCourtCardJpanel().getShopOnlineJpanel().getShopType() == 15) {
                this.nowMoneyType = "转区币";
                this.yonghuXianyu.setText(RoleData.getRoleData().getLoginResult().getTransfergold().toString());
            }
            else {
                this.yonghuXianyu.setText(RoleData.getRoleData().getLoginResult().getCodecard().toString());
                this.nowMoneyType = "仙玉";
            }
            this.goumaiCount.setText("1");
        }
        else {
            if ((this.eshop = eshop) == null) {
                return;
            }
            this.goodName.setText("<html><body><font color='#FFFF00'>你确定要购买</font><font color='#00FF00'>" + eshop.getEshopname() + "</font><font color='#FFFF00'>吗?</font></body></html>");
            this.wupinDetail.setIcon(GoodsListFromServerUntil.imgpathAdaptive(eshop.getEshopskin(), 114, 114));
            this.wupinPrice.setText(eshop.getEshopprice());
            if (TaobaoCourtMainJframe.getTaobaoCourtJframe().getTaobaoCourtMainJpanel().getTaobaoCourtCardJpanel().getShopOnlineJpanel().getShopType() == 11) {
                this.nowMoneyType = "积分";
                this.yonghuXianyu.setText(RoleData.getRoleData().getLoginResult().getMoney().toString());
            }
            else if (TaobaoCourtMainJframe.getTaobaoCourtJframe().getTaobaoCourtMainJpanel().getTaobaoCourtCardJpanel().getShopOnlineJpanel().getShopType() == 15) {
                this.nowMoneyType = "转区币";
                this.yonghuXianyu.setText(RoleData.getRoleData().getLoginResult().getTransfergold().toString());
            }
            else {
                this.yonghuXianyu.setText(RoleData.getRoleData().getLoginResult().getCodecard().toString());
                this.nowMoneyType = "仙玉";
            }
            this.goumaiCount.setText("1");
        }
    }
    
    public void gainGoodsMessage(Shop shop) {
        this.eshop = null;
        this.shop = null;
        this.type = 1;
        if (MyIsif.getStyle().equals("水墨")) {
            if ((this.shop = shop) == null) {
                return;
            }
            this.goodName.setText("<html><body><font color='#FFFF00'>你确定要购买</font><font color='#00FF00'>" + shop.getShopname() + "</font><font color='#FFFF00'>吗?</font></body></html>");
            this.wupinDetail.setIcon(GoodsListFromServerUntil.imgpathAdaptive(shop.getShopskin(), 114, 114));
            this.wupinPrice.setText(shop.getShopprice() + "");
            if (shop.getShoptype() == 0) {
                this.yonghuXianyu.setText(RoleData.getRoleData().getLoginResult().getGold().toString());
                this.nowMoneyType = "金钱";
            }
            else if (shop.getShoptype() == 1) {
                this.yonghuXianyu.setText(RoleData.getRoleData().getLoginResult().getCodecard().toString());
                this.nowMoneyType = "仙玉";
            }
            else if (shop.getShoptype() == 2) {
                this.nowMoneyType = "积分";
                this.yonghuXianyu.setText(RoleData.getRoleData().getLoginResult().getMoney().toString());
            }
            else {
                this.nowMoneyType = "转区币";
                this.yonghuXianyu.setText(RoleData.getRoleData().getLoginResult().getTransfergold().toString());
            }
            this.goumaiCount.setText("1");
        }
        else {
            if ((this.shop = shop) == null) {
                return;
            }
            this.goodName.setText("<html><body><font color='#FFFF00'>你确定要购买</font><font color='#00FF00'>" + shop.getShopname() + "</font><font color='#FFFF00'>吗?</font></body></html>");
            this.wupinDetail.setIcon(GoodsListFromServerUntil.imgpathAdaptive(shop.getShopskin(), 114, 114));
            this.wupinPrice.setText(shop.getShopprice() + "");
            if (shop.getShoptype() == 0) {
                this.yonghuXianyu.setText(RoleData.getRoleData().getLoginResult().getGold().toString());
                this.nowMoneyType = "金钱";
            }
            else if (shop.getShoptype() == 1) {
                this.yonghuXianyu.setText(RoleData.getRoleData().getLoginResult().getCodecard().toString());
                this.nowMoneyType = "仙玉";
            }
            else if (shop.getShoptype() == 2) {
                this.nowMoneyType = "积分";
                this.yonghuXianyu.setText(RoleData.getRoleData().getLoginResult().getMoney().toString());
            }
            else {
                this.nowMoneyType = "转区币";
                this.yonghuXianyu.setText(RoleData.getRoleData().getLoginResult().getTransfergold().toString());
            }
            this.goumaiCount.setText("1");
        }
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (MyIsif.getStyle().equals("水墨")) {
            if (TaobaoCourtMainJframe.getTaobaoCourtJframe().getTaobaoCourtMainJpanel().getTaobaoCourtCardJpanel().getShopOnlineJpanel().getShopType() == 11) {
                if (this.icon1 == null) {
                    this.icon1 = new ImageIcon("inkImg/background1/47-1.png");
                }
                g.drawImage(this.icon1.getImage(), 0, 0, 382, 465, this);
            }
            else if (TaobaoCourtMainJframe.getTaobaoCourtJframe().getTaobaoCourtMainJpanel().getTaobaoCourtCardJpanel().getShopOnlineJpanel().getShopType() == 15) {
                if (this.icon2 == null) {
                    this.icon2 = new ImageIcon("inkImg/background1/47-2.png");
                }
                g.drawImage(this.icon2.getImage(), 0, 0, 382, 465, this);
            }
            else {
                if (this.icon == null) {
                    this.icon = new ImageIcon("inkImg/background1/47.png");
                }
                g.drawImage(this.icon.getImage(), 0, 0, 382, 465, this);
            }
        }
        else if (TaobaoCourtMainJframe.getTaobaoCourtJframe().getTaobaoCourtMainJpanel().getTaobaoCourtCardJpanel().getShopOnlineJpanel().getShopType() == 11) {
            if (this.icon1 == null) {
                this.icon1 = new ImageIcon("inkImg/hongmu1/gm-1.png");
            }
            g.drawImage(this.icon1.getImage(), 0, 0, 382, 465, this);
        }
        else if (TaobaoCourtMainJframe.getTaobaoCourtJframe().getTaobaoCourtMainJpanel().getTaobaoCourtCardJpanel().getShopOnlineJpanel().getShopType() == 15) {
            if (this.icon2 == null) {
                this.icon2 = new ImageIcon("inkImg/hongmu1/gm-2.png");
            }
            g.drawImage(this.icon2.getImage(), 0, 0, 382, 465, this);
        }
        else {
            if (this.icon == null) {
                this.icon = new ImageIcon("img/xy2uiimg/购买_w356,h487.png");
            }
            g.drawImage(this.icon.getImage(), 0, 0, 382, 465, this);
        }
    }
    
    public JLabel getWupinPrice() {
        return this.wupinPrice;
    }
    
    public void setWupinPrice(JLabel wupinPrice) {
        this.wupinPrice = wupinPrice;
    }
    
    public JTextField getGoumaiCount() {
        return this.goumaiCount;
    }
    
    public void setGoumaiCount(JTextField goumaiCount) {
        this.goumaiCount = goumaiCount;
    }
    
    public JLabel getXiaohaoXianyu() {
        return this.xiaohaoXianyu;
    }
    
    public void setXiaohaoXianyu(JLabel xiaohaoXianyu) {
        this.xiaohaoXianyu = xiaohaoXianyu;
    }
    
    public JLabel getYonghuXianyu() {
        return this.yonghuXianyu;
    }
    
    public void setYonghuXianyu(JLabel yonghuXianyu) {
        this.yonghuXianyu = yonghuXianyu;
    }
    
    public JLabel getPriceVal() {
        return this.priceVal;
    }
    
    public void setPriceVal(JLabel priceVal) {
        this.priceVal = priceVal;
    }
    
    public TextBtn getChangeXY() {
        return this.changeXY;
    }
    
    public void setChangeXY(TextBtn changeXY) {
        this.changeXY = changeXY;
    }
    
    public CommonBtn getBuyBtn() {
        return this.buyBtn;
    }
    
    public void setBuyBtn(CommonBtn buyBtn) {
        this.buyBtn = buyBtn;
    }
    
    public CommonBtn getAddBtn() {
        return this.addBtn;
    }
    
    public void setAddBtn(CommonBtn addBtn) {
        this.addBtn = addBtn;
    }
    
    public CommonBtn getReduceBtn() {
        return this.reduceBtn;
    }
    
    public void setReduceBtn(CommonBtn reduceBtn) {
        this.reduceBtn = reduceBtn;
    }
    
    public JLabel getPriceLa() {
        return this.priceLa;
    }
    
    public void setPriceLa(JLabel priceLa) {
        this.priceLa = priceLa;
    }
    
    public JLabel getHaveDuoCai() {
        return this.haveDuoCai;
    }
    
    public void setHaveDuoCai(JLabel haveDuoCai) {
        this.haveDuoCai = haveDuoCai;
    }
    
    public JLabel getDuoCaiVal() {
        return this.duoCaiVal;
    }
    
    public void setDuoCaiVal(JLabel duoCaiVal) {
        this.duoCaiVal = duoCaiVal;
    }
    
    public JLabel getWupinDetail() {
        return this.wupinDetail;
    }
    
    public void setWupinDetail(JLabel wupinDetail) {
        this.wupinDetail = wupinDetail;
    }
    
    public JLabel getGoodName() {
        return this.goodName;
    }
    
    public void setGoodName(JLabel goodName) {
        this.goodName = goodName;
    }
    
    public Eshop getEshop() {
        return this.eshop;
    }
    
    public void setEshop(Eshop eshop) {
        this.eshop = eshop;
    }
    
    public Shop getShop() {
        return this.shop;
    }
    
    public void setShop(Shop shop) {
        this.shop = shop;
    }
    
    public int getType() {
        return this.type;
    }
    
    public void setType(int type) {
        this.type = type;
    }
    
    public ImageIcon getIcon() {
        return this.icon;
    }
    
    public void setIcon(ImageIcon icon) {
        this.icon = icon;
    }
    
    public String getNowMoneyType() {
        return this.nowMoneyType;
    }
    
    public void setNowMoneyType(String nowMoneyType) {
        this.nowMoneyType = nowMoneyType;
    }
}
