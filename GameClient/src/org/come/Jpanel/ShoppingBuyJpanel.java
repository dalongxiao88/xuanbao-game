package org.come.Jpanel;

import org.come.until.CutButtonImage;
import org.come.bean.ShopGoodsBean;
import org.come.bean.LoginResult;
import org.come.mouslisten.ShopFyMouslisten;
import java.awt.Component;
import java.util.ArrayList;
import org.come.socket.SendMessageUntil;
import org.come.socket.Agreement;
import org.come.until.GoodsListFromServerUntil;
import com.tool.role.RoleData;
import org.come.until.Util;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.Color;
import javax.swing.BorderFactory;
import com.tool.btn.FormsOnOffBtn;
import com.tool.tcpimg.UIUtils;
import java.awt.Dimension;
import com.updateNew.MyIsif;
import javax.swing.ImageIcon;
import org.come.mouslisten.ShopMouslisten;
import com.tool.btn.ShoppingBuyBtn;
import javax.swing.JTextField;
import java.math.BigDecimal;
import javax.swing.JLabel;
import org.come.model.Shop;
import java.util.List;
import javax.swing.JPanel;

public class ShoppingBuyJpanel extends JPanel
{
    private List<Shop> shops;
    private List<Shop> shopList;
    private List<JLabel> pages;
    private int index;
    private Shop shop;
    private int xz;
    private BigDecimal Unitprice;
    private BigDecimal Totalsum;
    private JTextField textNumber;
    private ShoppingBuyBtn btnsurebuy;
    private ShoppingBuyBtn xianyuBtn;
    private ShoppingBuyBtn dahuabiBtn;
    public JLabel[] GoodsListLabel;
    public String Currency;
    private JLabel currencyLab;
    public BigDecimal Balance;
    private ShopMouslisten[] shopMouslistens;
    private int count;
    private int goldType;
    private Integer nId;
    private ImageIcon icon;
    private ImageIcon image;
    
    public ShoppingBuyJpanel() throws Exception {
        this.index = 0;
        this.xz = -1;
        this.GoodsListLabel = new JLabel[24];
        this.Currency = "金钱";
        this.shopMouslistens = new ShopMouslisten[24];
        this.count = 1;
        this.goldType = 1;
        this.image = new ImageIcon("img/xy2uiimg/93_png.xy2uiimg.label_chat.png");
        if (MyIsif.getStyle().equals("水墨")) {
            this.setPreferredSize(new Dimension(378, 398));
            this.setLayout(null);
            this.setBackground(UIUtils.Color_BACK);
            FormsOnOffBtn offBtn = new FormsOnOffBtn("inkImg/button/1.png", 1, 23);
            offBtn.setBounds(341, 10, 25, 25);
            this.add(offBtn);
            this.add(this.getXianyuBtn());
            this.add(this.getDahuabiBtn());
            this.add(this.getCurrencyLab());
            this.Unitprice = new BigDecimal(0);
            this.Totalsum = new BigDecimal(0);
            (this.textNumber = new JTextField()).setText("0");
            this.textNumber.setBackground(UIUtils.Color_BACK);
            this.textNumber.setBorder(BorderFactory.createEmptyBorder());
            this.textNumber.setForeground(Color.white);
            this.textNumber.setCaretColor(Color.white);
            this.textNumber.setBounds(100, 297, 151, 16);
            this.textNumber.getDocument().addDocumentListener(new DocumentListener() {
                @Override
                public void removeUpdate(DocumentEvent e) {
                    String s = ShoppingBuyJpanel.this.textNumber.getText();
                    try {
                        int sum = Integer.parseInt(s);
                        long price = Long.parseLong(ShoppingBuyJpanel.this.Unitprice.toString());
                        ShoppingBuyJpanel.this.Totalsum = new BigDecimal(price * (long)sum);
                    }
                    catch (NumberFormatException ex) {}
                }
                
                @Override
                public void insertUpdate(DocumentEvent e) {
                    String s = ShoppingBuyJpanel.this.textNumber.getText();
                    try {
                        int sum = Integer.parseInt(s);
                        long price = Long.parseLong(ShoppingBuyJpanel.this.Unitprice.toString());
                        ShoppingBuyJpanel.this.Totalsum = new BigDecimal(price * (long)sum);
                    }
                    catch (NumberFormatException ex) {}
                }
                
                @Override
                public void changedUpdate(DocumentEvent e) {
                }
            });
            this.textNumber.addKeyListener(new KeyListener() {
                @Override
                public void keyTyped(KeyEvent e) {
                    int charstr = e.getKeyChar();
                    String str = ShoppingBuyJpanel.this.textNumber.getText();
                    if (str.length() == 0) {
                        ShoppingBuyJpanel.this.textNumber.setText("0");
                    }
                    if (charstr < 48 || charstr > 57) {
                        e.consume();
                    }
                    if (str.length() == 1 && str.equals("0")) {
                        ShoppingBuyJpanel.this.textNumber.setText("");
                    }
                    if (str.length() > 0 && str.length() > 2) {
                        e.consume();
                        ShoppingBuyJpanel.this.textNumber.setText("999");
                        int sum = Integer.parseInt(ShoppingBuyJpanel.this.textNumber.getText());
                        long price = Long.parseLong(ShoppingBuyJpanel.this.Unitprice.toString());
                        ShoppingBuyJpanel.this.Totalsum = new BigDecimal(price * (long)sum);
                    }
                }
                
                @Override
                public void keyPressed(KeyEvent e) {
                }
                
                @Override
                public void keyReleased(KeyEvent e) {
                }
            });
            this.add(this.textNumber);
            (this.btnsurebuy = new ShoppingBuyBtn("inkImg/button1/B22.png", 1, UIUtils.COLOR_BLACK, UIUtils.TEXT_HY16, "确认购买", 1, this)).setBounds(263, 298, 99, 24);
            this.add(this.btnsurebuy);
            for (int i = 0; i < 24; ++i) {
                this.GoodsListLabel[i] = new JLabel();
                this.shopMouslistens[i] = new ShopMouslisten(i, this);
                this.GoodsListLabel[i].addMouseListener(this.shopMouslistens[i]);
                this.GoodsListLabel[i].setBounds(47 + i % 6 * 52 - i % 6, 35 + i / 6 * 52, 48, 49);
                this.add(this.GoodsListLabel[i]);
            }
            this.image.setImage(this.image.getImage().getScaledInstance(38, 20, 1));
        }
        else {
            this.setPreferredSize(new Dimension(345, 436));
            this.setLayout(null);
            this.setBackground(UIUtils.Color_BACK);
            FormsOnOffBtn offBtn = new FormsOnOffBtn("img/xy2uiimg/8_jpg.button.btn_close.jpg", 1, 23);
            offBtn.setBounds(325, 0, 23, 23);
            this.add(offBtn);
            this.add(this.getXianyuBtn());
            this.add(this.getDahuabiBtn());
            this.add(this.getCurrencyLab());
            this.Unitprice = new BigDecimal(0);
            this.Totalsum = new BigDecimal(0);
            (this.textNumber = new JTextField()).setText("0");
            this.textNumber.setBackground(UIUtils.Color_BACK);
            this.textNumber.setBorder(BorderFactory.createEmptyBorder());
            this.textNumber.setForeground(Color.white);
            this.textNumber.setCaretColor(Color.white);
            this.textNumber.setBounds(74, 304, 141, 17);
            this.textNumber.getDocument().addDocumentListener(new DocumentListener() {
                @Override
                public void removeUpdate(DocumentEvent e) {
                    String s = ShoppingBuyJpanel.this.textNumber.getText();
                    try {
                        int sum = Integer.parseInt(s);
                        long price = Long.parseLong(ShoppingBuyJpanel.this.Unitprice.toString());
                        ShoppingBuyJpanel.this.Totalsum = new BigDecimal(price * (long)sum);
                    }
                    catch (NumberFormatException ex) {}
                }
                
                @Override
                public void insertUpdate(DocumentEvent e) {
                    String s = ShoppingBuyJpanel.this.textNumber.getText();
                    try {
                        int sum = Integer.parseInt(s);
                        long price = Long.parseLong(ShoppingBuyJpanel.this.Unitprice.toString());
                        ShoppingBuyJpanel.this.Totalsum = new BigDecimal(price * (long)sum);
                    }
                    catch (NumberFormatException ex) {}
                }
                
                @Override
                public void changedUpdate(DocumentEvent e) {
                }
            });
            this.textNumber.addKeyListener(new KeyListener() {
                @Override
                public void keyTyped(KeyEvent e) {
                    int charstr = e.getKeyChar();
                    String str = ShoppingBuyJpanel.this.textNumber.getText();
                    if (str.length() == 0) {
                        ShoppingBuyJpanel.this.textNumber.setText("0");
                    }
                    if (charstr < 48 || charstr > 57) {
                        e.consume();
                    }
                    if (str.length() == 1 && str.equals("0")) {
                        ShoppingBuyJpanel.this.textNumber.setText("");
                    }
                    if (str.length() > 0 && str.length() > 2) {
                        e.consume();
                        ShoppingBuyJpanel.this.textNumber.setText("999");
                        int sum = Integer.parseInt(ShoppingBuyJpanel.this.textNumber.getText());
                        long price = Long.parseLong(ShoppingBuyJpanel.this.Unitprice.toString());
                        ShoppingBuyJpanel.this.Totalsum = new BigDecimal(price * (long)sum);
                    }
                }
                
                @Override
                public void keyPressed(KeyEvent e) {
                }
                
                @Override
                public void keyReleased(KeyEvent e) {
                }
            });
            this.add(this.textNumber);
            (this.btnsurebuy = new ShoppingBuyBtn("inkImg/hongmu/1_png.button.btn_xy.png", 1, UIUtils.COLOR_BTNPUTONG, UIUtils.TEXT_HY88, "确认购买", 1, this)).setBounds(135, 375, 80, 26);
            this.add(this.btnsurebuy);
            for (int i = 0; i < 24; ++i) {
                this.GoodsListLabel[i] = new JLabel();
                this.shopMouslistens[i] = new ShopMouslisten(i, this);
                this.GoodsListLabel[i].addMouseListener(this.shopMouslistens[i]);
                this.GoodsListLabel[i].setBounds(20 + i % 6 * 51 - i % 6, 48 + i / 6 * 51, 48, 48);
                this.add(this.GoodsListLabel[i]);
            }
            this.image.setImage(this.image.getImage().getScaledInstance(38, 20, 1));
        }
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (MyIsif.getStyle().equals("水墨")) {
            this.icon = new ImageIcon("inkImg/background1/B260.png");
            g.drawImage(this.icon.getImage(), 0, 0, 378, 398, this);
            if (this.count == 1) {
                if (this.Balance != null) {
                    Util.drawPrice(g, this.Balance, 100, 362);
                }
                else if (this.Currency.equals("金钱")) {
                    Util.drawMoney(g, 100, 362);
                }
                else {
                    Util.drawPrice(g, RoleData.getRoleData().getLoginResult().getSavegold(), 100, 362);
                }
            }
            else if (this.Currency.equals("金钱")) {
                Util.drawMoney(g, 100, 362);
            }
            else {
                Util.drawPrice(g, RoleData.getRoleData().getLoginResult().getCodecard(), 100, 362);
            }
            Util.drawPrice(g, this.Unitprice, 100, 285);
            Util.drawPrice(g, this.Totalsum, 100, 336);
        }
        else {
            this.icon = new ImageIcon("img/xy2uiimg/购买_w356,h417.png");
            g.drawImage(this.icon.getImage(), 0, 0, 345, 417, this);
            if (this.count == 1) {
                if (this.Balance != null) {
                    Util.drawPrice(g, this.Balance, 74, 367);
                }
                else if (this.Currency.equals("金钱")) {
                    Util.drawMoney(g, 74, 367);
                }
                else {
                    Util.drawPrice(g, RoleData.getRoleData().getLoginResult().getSavegold(), 74, 367);
                }
            }
            else if (this.Currency.equals("金钱")) {
                Util.drawMoney(g, 74, 367);
            }
            else {
                Util.drawPrice(g, RoleData.getRoleData().getLoginResult().getCodecard(), 74, 367);
            }
            Util.drawPrice(g, this.Unitprice, 74, 292);
            Util.drawPrice(g, this.Totalsum, 74, 342);
        }
    }
    
    public void ShoppingsetIcon(int index) {
        if (MyIsif.getStyle().equals("水墨")) {
            this.index = index;
            this.fysum();
            this.xz = -1;
            for (int i = 0; i < this.GoodsListLabel.length; ++i) {
                this.GoodsListLabel[i].setIcon(null);
                this.GoodsListLabel[i].setBorder(null);
            }
            this.textNumber.setText("0");
            this.Unitprice = new BigDecimal(0);
            this.Totalsum = new BigDecimal(0);
            this.shop = null;
            for (int size = (this.shops.size() > (index + 1) * 24) ? 24 : (this.shops.size() - index * 24), j = 0; j < size; ++j) {
                ImageIcon image = GoodsListFromServerUntil.imgpathAdaptive(((Shop)this.shops.get(index * 24 + j)).getShopskin(), 49, 49);
                this.GoodsListLabel[j].setIcon(image);
            }
        }
        else {
            this.index = index;
            this.fysum();
            this.xz = -1;
            for (int i = 0; i < this.GoodsListLabel.length; ++i) {
                this.GoodsListLabel[i].setIcon(null);
                this.GoodsListLabel[i].setBorder(null);
            }
            this.textNumber.setText("0");
            this.Unitprice = new BigDecimal(0);
            this.Totalsum = new BigDecimal(0);
            this.shop = null;
            for (int size = (this.shops.size() > (index + 1) * 24) ? 24 : (this.shops.size() - index * 24), j = 0; j < size; ++j) {
                ImageIcon image = GoodsListFromServerUntil.imgpathAdaptive(((Shop)this.shops.get(index * 24 + j)).getShopskin(), 49, 49);
                this.GoodsListLabel[j].setIcon(image);
            }
        }
    }
    
    public void priceSX(String shopId, long price) {
        if (this.shop != null && this.shop.getShopid().equals(shopId)) {
            this.shop.setShopprice(price);
            int TextNumber = Integer.parseInt(this.textNumber.getText());
            this.Unitprice = new BigDecimal(price);
            this.Totalsum = new BigDecimal(price * (long)TextNumber);
        }
    }
    
    public void xuanzhong(Shop yxshop, int shopPlace) {
        if (this.shop != null && yxshop.getShopid() == this.shop.getShopid()) {
            int TextNumber = Integer.parseInt(this.textNumber.getText()) + 1;
            if (TextNumber > 99) {
                return;
            }
            long LabUnitprice = this.shop.getShopprice();
            this.textNumber.setText(TextNumber + "");
            this.Totalsum = new BigDecimal(LabUnitprice * (long)TextNumber);
        }
        else {
            if (this.shop != null) {
                this.GoodsListLabel[this.xz].setBorder(null);
            }
            this.xz = shopPlace;
            this.shop = yxshop;
            this.textNumber.setText("1");
            this.Unitprice = new BigDecimal(this.shop.getShopprice());
            this.Totalsum = new BigDecimal(this.shop.getShopprice());
        }
        if (this.shop.getIsPrice() != null) {
            String senmes = Agreement.getAgreement().ShopPriceAgreement("1|" + this.shop.getShopid());
            SendMessageUntil.toServer(senmes);
        }
    }
    
    public void fysum() {
        int sum = (this.shops.size() - 1) / 24 + 1;
        if (this.pages == null) {
            this.pages = new ArrayList<>();
        }
        for (int i = 0; i < sum && i < 8; ++i) {
            if (i >= this.pages.size()) {
                if (sum > 8 && i == 0) {
                    this.pages.add(this.fyjlb("首页", i));
                }
                else if (sum > 8 && i == 7) {
                    this.pages.add(this.fyjlb("尾页", i));
                }
                else if (sum > 8) {
                    if (this.index == 0) {
                        this.pages.add(this.fyjlb(i + "", i));
                    }
                    else if (this.index > sum - 5) {
                        int q = sum - 6;
                        this.pages.add(this.fyjlb(i + q + "", i));
                    }
                    else {
                        this.pages.add(this.fyjlb(i + this.index - 1 + "", i));
                    }
                }
                else {
                    this.pages.add(this.fyjlb(i + 1 + "", i));
                }
            }
            else if (sum > 8 && i == 0) {
                ((JLabel)this.pages.get(i)).setText("首页");
            }
            else if (sum > 8 && i == 7) {
                ((JLabel)this.pages.get(i)).setText("尾页");
            }
            else if (sum > 8) {
                if (this.index == 0) {
                    ((JLabel)this.pages.get(i)).setText(i + "");
                }
                else if (this.index > sum - 5) {
                    int q = sum - 6;
                    ((JLabel)this.pages.get(i)).setText(i + q + "");
                }
                else {
                    ((JLabel)this.pages.get(i)).setText(i + this.index - 1 + "");
                }
            }
            else {
                ((JLabel)this.pages.get(i)).setText(i + 1 + "");
            }
        }
        if (this.pages.size() > sum) {
            for (int i = this.pages.size() - 1; i >= sum; --i) {
                this.remove((Component)this.pages.get(i));
                this.pages.remove(i);
            }
        }
    }
    
    public JLabel fyjlb(String text, int i) {
        if (MyIsif.getStyle().equals("水墨")) {
            this.image = new ImageIcon("inkImg/button/B162.png");
            JLabel lab = new JLabel();
            lab.setBounds(47 + i * 40, 242, 39, 23);
            lab.setIcon(this.image);
            lab.setBackground(new Color(0, 0, 0, 0));
            lab.setText(text);
            lab.setForeground(Color.WHITE);
            lab.setHorizontalTextPosition(0);
            lab.setFont(UIUtils.TEXT_FONT1);
            lab.addMouseListener(new ShopFyMouslisten(lab, this, null));
            this.add(lab);
            return lab;
        }
        this.image = new ImageIcon("img/xy2uiimg/93_png.xy2uiimg.label_chat.png");
        JLabel lab = new JLabel();
        lab.setBounds(19 + i * 40, 252, 41, 25);
        lab.setIcon(this.image);
        lab.setBackground(new Color(0, 0, 0, 0));
        lab.setText(text);
        lab.setForeground(Color.WHITE);
        lab.setHorizontalTextPosition(0);
        lab.setFont(UIUtils.TEXT_FONT1);
        lab.addMouseListener(new ShopFyMouslisten(lab, this, null));
        this.add(lab);
        return lab;
    }
    
    public void PaintingText(int shopPlace) {
        this.GoodsListLabel[shopPlace].setBorder(BorderFactory.createLineBorder(Color.red));
    }
    
    public void ClearText(int shopPlace) {
        if (this.xz != shopPlace) {
            this.GoodsListLabel[shopPlace].setBorder(null);
        }
    }
    
    public void showshop(List<Shop> shops, String type, BigDecimal money) {
        this.shops = shops;
        this.count = 1;
        this.changeView();
        if (this.shops == null) {
            this.shops = new ArrayList<>();
        }
        this.ShoppingsetIcon(0);
        if (type.equals("45")) {
            this.Currency = "师徒积分";
        }
        else if (type.equals("10")) {
            this.Currency = "天庭积分";
        }
        else if (type.equals("61")) {
            this.Currency = "帮战积分";
        }
        else if (type.equals("120")) {
            this.Currency = "大雁塔积分";
        }
        else if (type.equals("121")) {
            this.Currency = "地宫积分";
        }
        else if (type.equals("123")) {
            this.Currency = "寻芳积分";
        }
        else if (type.equals("124")) {
            this.Currency = "木魁积分";
        }
        else if (type.equals("126")) {
            this.Currency = "水陆积分";
        }
        else if (type.equals("1106")) {
            this.Currency = "种族赛积分";
        }
        else if (type.equals("500")) {
            this.Currency = "幸运奖池积分";
        }
        else if (type.equals("515")) {
            this.Currency = "擂台赛积分";
        }
        else if (type.equals("605")) {
            this.Currency = "大闹天宫积分";
        }
        else if (type.equals("89")) {
            this.Currency = "师贡";
        }
        else if (type.equals("2027")) {
            this.Currency = "竞技积分";
        }
        else if (type.equals("887")) {
            this.Currency = "孤竹城积分";
        }
        else if (type.equals("2029")) {
            this.Currency = "天梯积分";
        }
        else if (type.equals("8900")) {
            this.Currency = "副本积分";
        }
        else if (type.equals("7120")) {
            this. Currency = "功绩积分";
        }
        else if (type.equals("99129")) {
            this. Currency = "种树积分";
        }
        else {
            this.Currency = "金钱";
        }
        if (money != null) {
            this.Balance = money;
        }
        else if (!this.Currency.equals("金钱") && !this.Currency.equals("师贡")) {
            LoginResult loginResult = RoleData.getRoleData().getLoginResult();
            this.Balance = loginResult.getScoretype(this.Currency);
        }
        else {
            this.Balance = null;
        }
    }
    
    public void getShopGoods(ShopGoodsBean shopGoodsBean) {
        if (shopGoodsBean.getType() == 0) {
            this.count = 0;
            this.goldType = 1;
            this.nId = shopGoodsBean.getnId();
            this.getShopGoodsType(this.shopList = shopGoodsBean.getShopList(), 1);
            this.changeView();
            this.changeGoodsView();
        }
    }
    
    public void getShopGoodsType(List<Shop> shopList, int type) {
        if ("1".equals(Integer.valueOf(type))) {
            this.Currency = "仙玉";
        }
        else {
            this.Currency = "金钱";
        }
        if (this.shops == null) {
            this.shops = new ArrayList<>();
        }
        this.shops.clear();
        for (int i = 0; i < shopList.size(); ++i) {
            if (((Shop)shopList.get(i)).getShoptype() == type) {
                this.shops.add(shopList.get(i));
            }
        }
        this.ShoppingsetIcon(0);
    }
    
    public void changeView() {
        if (MyIsif.getStyle().equals("水墨")) {
            if (this.count == 0) {
                this.getXianyuBtn().setVisible(true);
                this.getDahuabiBtn().setVisible(true);
                this.currencyLab.setIcon(CutButtonImage.getImage("inkImg/background/S125.png", 42, 14));
            }
            else {
                this.getXianyuBtn().setVisible(false);
                this.getDahuabiBtn().setVisible(false);
                this.currencyLab.setIcon(CutButtonImage.getImage("inkImg/background/S126.png", 42, 14));
            }
        }
        else if (this.count == 0) {
            this.getXianyuBtn().setVisible(true);
            this.getDahuabiBtn().setVisible(true);
            this.currencyLab.setIcon(CutButtonImage.getImage("img/xy2uiimg/仙玉W41,h13.png", 42, 13));
        }
        else {
            this.getXianyuBtn().setVisible(false);
            this.getDahuabiBtn().setVisible(false);
            this.currencyLab.setIcon(CutButtonImage.getImage("img/xy2uiimg/现金W41,h13.png", 42, 13));
        }
    }
    
    public void changeGoodsView() {
        try {
            if (this.goldType == 1) {
                this.xianyuBtn.setIcons(CutButtonImage.cuts("img/xy2uiimg/小选卡_仙玉_w63,h78.png"));
                this.dahuabiBtn.setIcons(CutButtonImage.cuts("img/xy2uiimg/小选卡_金币_未选中_w63,h78.png"));
                this.currencyLab.setIcon(CutButtonImage.getImage("img/xy2uiimg/仙玉W41,h13.png", 41, 13));
                this.getShopGoodsType(this.shopList, 1);
                this.Balance = RoleData.getRoleData().getLoginResult().getCodecard();
            }
            else {
                this.xianyuBtn.setIcons(CutButtonImage.cuts("img/xy2uiimg/小选卡_仙玉_未选中_w63,h78.png"));
                this.dahuabiBtn.setIcons(CutButtonImage.cuts("img/xy2uiimg/小选卡_金币_w63,h78.png"));
                this.currencyLab.setIcon(CutButtonImage.getImage("img/xy2uiimg/现金W41,h13.png", 41, 13));
                this.getShopGoodsType(this.shopList, 0);
                this.Balance = RoleData.getRoleData().getLoginResult().getGold();
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public JTextField getTextNumber() {
        return this.textNumber;
    }
    
    public void setTextNumber(JTextField textNumber) {
        this.textNumber = textNumber;
    }
    
    public ShoppingBuyBtn getBtnsurebuy() {
        return this.btnsurebuy;
    }
    
    public void setBtnsurebuy(ShoppingBuyBtn btnsurebuy) {
        this.btnsurebuy = btnsurebuy;
    }
    
    public JLabel[] getGoodsListLabel() {
        return this.GoodsListLabel;
    }
    
    public void setGoodsListLabel(JLabel[] goodsListLabel) {
        this.GoodsListLabel = goodsListLabel;
    }
    
    public Shop getShop(int i) {
        if (this.index * 24 + i >= this.shops.size()) {
            return null;
        }
        return (Shop)this.shops.get(this.index * 24 + i);
    }
    
    public List<Shop> getShops() {
        return this.shops;
    }
    
    public void setShops(List<Shop> shops) {
        this.shops = shops;
        this.ShoppingsetIcon(0);
    }
    
    public Shop getShop() {
        return this.shop;
    }
    
    public void setShop(Shop shop) {
        this.shop = shop;
    }
    
    public int getIndex() {
        return this.index;
    }
    
    public void setIndex(int index) {
        this.index = index;
    }
    
    public ShoppingBuyBtn getXianyuBtn() {
        if (this.xianyuBtn == null) {
            (this.xianyuBtn = new ShoppingBuyBtn("img/xy2uiimg/小选卡_仙玉_w63,h78.png", 1, 2, this)).setBounds(50, 15, 63, 26);
            this.xianyuBtn.setOpaque(false);
        }
        return this.xianyuBtn;
    }
    
    public void setXianyuBtn(ShoppingBuyBtn xianyuBtn) {
        this.xianyuBtn = xianyuBtn;
    }
    
    public ShoppingBuyBtn getDahuabiBtn() {
        if (this.dahuabiBtn == null) {
            (this.dahuabiBtn = new ShoppingBuyBtn("img/xy2uiimg/小选卡_金币_未选中_w63,h78.png", 1, 3, this)).setBounds(115, 15, 63, 26);
            this.dahuabiBtn.setOpaque(false);
        }
        return this.dahuabiBtn;
    }
    
    public void setDahuabiBtn(ShoppingBuyBtn dahuabiBtn) {
        this.dahuabiBtn = dahuabiBtn;
    }
    
    public List<Shop> getShopList() {
        return this.shopList;
    }
    
    public void setShopList(List<Shop> shopList) {
        this.shopList = shopList;
    }
    
    public JLabel getCurrencyLab() {
        if (this.currencyLab == null) {
            (this.currencyLab = new JLabel()).setOpaque(false);
            if (MyIsif.getStyle().equals("水墨")) {
                this.currencyLab.setBounds(50, 348, 42, 14);
                this.currencyLab.setIcon(CutButtonImage.getImage("inkImg/background/S126.png", 42, 14));
            }
            else {
                this.currencyLab.setBounds(25, 357, 42, 13);
                this.currencyLab.setIcon(CutButtonImage.getImage("img/xy2uiimg/现金W41,h13.png", 42, 13));
            }
        }
        return this.currencyLab;
    }
    
    public void setCurrencyLab(JLabel currencyLab) {
        this.currencyLab = currencyLab;
    }
    
    public int getCount() {
        return this.count;
    }
    
    public void setCount(int count) {
        this.count = count;
    }
    
    public int getGoldType() {
        return this.goldType;
    }
    
    public void setGoldType(int goldType) {
        this.goldType = goldType;
    }
    
    public Integer getnId() {
        return this.nId;
    }
    
    public void setnId(Integer nId) {
        this.nId = nId;
    }
}
