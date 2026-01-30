package org.come.summonequip;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import com.tool.role.RoleData;
import com.tool.tcpimg.UIUtils;
import java.awt.Color;
import org.come.until.CutButtonImage;
import org.come.until.GoodsListFromServerUntil;
import org.come.until.UserMessUntil;
import java.util.List;
import java.awt.Graphics;
import com.tool.btn.FormsOnOffBtn;
import java.awt.Dimension;
import com.updateNew.MyIsif;
import javax.swing.ImageIcon;
import java.math.BigDecimal;
import org.come.model.Shop;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class JpanelCashRewardsMain extends JPanel
{
    private JLabel[] goodsLabs;
    private JLabel[] consumptionGoodsLabs;
    private JLabel chooseGoods;
    private JTextField textNum;
    private BtnSummonEquipMain exchangeBtn;
    private Shop chooseShop;
    private BigDecimal shopPrice;
    private int goodsType;
    private ImageIcon iconBack;
    
    public JpanelCashRewardsMain() {
        this.goodsType = -1;
        if (MyIsif.getStyle().equals("水墨")) {
            this.setPreferredSize(new Dimension(378, 332));
            this.setOpaque(false);
            this.setLayout(null);
            FormsOnOffBtn offBtn = new FormsOnOffBtn("inkImg/button/1.png", 1, 92);
            offBtn.setBounds(341, 10, 25, 25);
            this.add(offBtn);
            this.getGoodsLabs();
            this.getChooseGoods();
            this.getExchangeBtn();
            this.getTextNum();
        }
        else {
            this.setPreferredSize(new Dimension(356, 362));
            this.setOpaque(false);
            this.setLayout(null);
            FormsOnOffBtn offBtn = new FormsOnOffBtn("inkImg/hongmu/s74.png", 1, 92);
            offBtn.setBounds(331, 0, 23, 23);
            this.add(offBtn);
            this.getGoodsLabs();
            this.getChooseGoods();
            this.getExchangeBtn();
            this.getTextNum();
        }
    }
    
    public void showShop(Graphics g) {
        List<Shop> npcshop = (List<Shop>)UserMessUntil.getNpcshop().getNpcShopMap().get("600");
        if (npcshop == null) {
            return;
        }
        if (MyIsif.getStyle().equals("水墨")) {
            for (int i = 0; i < npcshop.size(); ++i) {
                g.drawImage(GoodsListFromServerUntil.imgpath(((Shop)npcshop.get(i)).getShopskin()).getImage(), 49 + i % 6 * 51, 56 + i / 6 * 51, 49, 49, null);
                if (i >= 11) {
                    return;
                }
            }
        }
        else {
            for (int i = 0; i < npcshop.size(); ++i) {
                g.drawImage(GoodsListFromServerUntil.imgpath(((Shop)npcshop.get(i)).getShopskin()).getImage(), 22 + i % 6 * 51, 56 + i / 6 * 51, 49, 49, null);
                if (i >= 11) {
                    return;
                }
            }
        }
    }
    
    public void showStarCard(Graphics g) {
        List<Shop> npcshop = (List<Shop>)UserMessUntil.getNpcshop().getNpcShopMap().get("601");
        if (npcshop == null) {
            return;
        }
        if (MyIsif.getStyle().equals("水墨")) {
            for (int i = 0; i < npcshop.size(); ++i) {
                g.drawImage(GoodsListFromServerUntil.imgpath(((Shop)npcshop.get(i)).getShopskin()).getImage(), 49 + i % 6 * 51, 56 + i / 6 * 51, 49, 49, null);
                if (i >= 11) {
                    return;
                }
            }
        }
        else {
            for (int i = 0; i < npcshop.size(); ++i) {
                g.drawImage(GoodsListFromServerUntil.imgpath(((Shop)npcshop.get(i)).getShopskin()).getImage(), 22 + i % 6 * 51, 57 + i / 6 * 51, 49, 49, null);
                if (i >= 11) {
                    return;
                }
            }
        }
    }
    
    public void TeatStarCard(Graphics g) {
        List<Shop> npcshop = (List<Shop>)UserMessUntil.getNpcshop().getNpcShopMap().get("602");
        if (npcshop == null) {
            return;
        }
        if (MyIsif.getStyle().equals("水墨")) {
            for (int i = 0; i < npcshop.size(); ++i) {
                g.drawImage(GoodsListFromServerUntil.imgpath(((Shop)npcshop.get(i)).getShopskin()).getImage(), 49 + i % 6 * 51, 56 + i / 6 * 51, 49, 49, null);
                if (i >= 11) {
                    return;
                }
            }
        }
        else {
            for (int i = 0; i < npcshop.size(); ++i) {
                g.drawImage(GoodsListFromServerUntil.imgpath(((Shop)npcshop.get(i)).getShopskin()).getImage(), 22 + i % 6 * 51, 57 + i / 6 * 51, 49, 49, null);
                if (i >= 11) {
                    return;
                }
            }
        }
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (MyIsif.getStyle().equals("水墨")) {
            if (this.iconBack == null) {
                this.iconBack = CutButtonImage.getImage("inkImg/background1/B293.png", 356, 362);
            }
            g.drawImage(this.iconBack.getImage(), 0, 0, 378, 332, this);
            if (this.goodsType == 1) {
                if (this.chooseShop != null && this.shopPrice != null) {
                    g.setColor(Color.black);
                    g.setFont(UIUtils.TEXT_FONT);
                    g.drawString("奖章:" + (long)Integer.parseInt(this.textNum.getText()) * this.chooseShop.getShopprice() + "/" + RoleData.getRoleData().getLoginResult().getScoretype("比斗奖章"), 224, 238);
                }
                this.showShop(g);
                g.setColor(UIUtils.COLOR_NAME8);
                g.setFont(UIUtils.TEXT_FONT1);
                g.drawString("拥有比斗奖章:" + RoleData.getRoleData().getLoginResult().getScoretype("比斗奖章"), 58, 300);
            }
            else if (this.goodsType == 2) {
                if (this.chooseShop != null && this.shopPrice != null) {
                    g.setColor(Color.black);
                    g.setFont(UIUtils.TEXT_FONT);
                    g.drawString("星芒:" + (long)Integer.parseInt(this.textNum.getText()) * this.chooseShop.getShopprice() + "/" + RoleData.getRoleData().getLoginResult().getScoretype("星芒"), 224, 238);
                }
                this.showStarCard(g);
            }
            else if (this.goodsType == 3) {
                if (this.chooseShop != null && this.shopPrice != null) {
                    g.setColor(Color.white);
                    g.setFont(UIUtils.TEXT_FONT1);
                    g.drawString("勇者积分:" + (long)Integer.parseInt(this.textNum.getText()) * this.chooseShop.getShopprice() + "/" + RoleData.getRoleData().getLoginResult().getScoretype("勇者积分"), 182, 235);
                }
                g.setColor(UIUtils.COLOR_NAME16);
                g.setFont(UIUtils.TEXT_FONT1);
                g.drawString("拥有勇者积分:" + RoleData.getRoleData().getLoginResult().getScoretype("勇者积分"), 58, 300);
                this.TeatStarCard(g);
            }
        }
        else {
            if (this.iconBack == null) {
                this.iconBack = CutButtonImage.getImage("img/xy2uiimg/兑换奖励_底板_w356,h362.png", 356, 362);
            }
            g.drawImage(this.iconBack.getImage(), 0, 0, 356, 362, this);
            if (this.goodsType == 1) {
                if (this.chooseShop != null && this.shopPrice != null) {
                    g.setColor(Color.white);
                    g.setFont(UIUtils.TEXT_FONT1);
                    g.drawString("奖章:" + (long)Integer.parseInt(this.textNum.getText()) * this.chooseShop.getShopprice() + "/" + RoleData.getRoleData().getLoginResult().getScoretype("比斗奖章"), 182, 235);
                }
                this.showShop(g);
                g.setColor(UIUtils.COLOR_HURTB3);
                g.setFont(UIUtils.TEXT_FONT1);
                g.drawString("拥有比斗奖章:" + RoleData.getRoleData().getLoginResult().getScoretype("比斗奖章"), 20, 330);
            }
            else if (this.goodsType == 2) {
                if (this.chooseShop != null && this.shopPrice != null) {
                    g.setColor(Color.white);
                    g.setFont(UIUtils.TEXT_FONT1);
                    g.drawString("星芒:" + (long)Integer.parseInt(this.textNum.getText()) * this.chooseShop.getShopprice() + "/" + RoleData.getRoleData().getLoginResult().getScoretype("星芒"), 182, 235);
                }
                this.showStarCard(g);
            }
            else if (this.goodsType == 3) {
                if (this.chooseShop != null && this.shopPrice != null) {
                    g.setColor(Color.white);
                    g.setFont(UIUtils.TEXT_FONT1);
                    g.drawString("勇者积分:" + (long)Integer.parseInt(this.textNum.getText()) * this.chooseShop.getShopprice() + "/" + RoleData.getRoleData().getLoginResult().getScoretype("勇者积分"), 182, 235);
                }
                g.setColor(UIUtils.COLOR_HURTB3);
                g.setFont(UIUtils.TEXT_FONT1);
                g.drawString("拥有勇者积分:" + RoleData.getRoleData().getLoginResult().getScoretype("勇者积分"), 20, 330);
                this.TeatStarCard(g);
            }
        }
    }
    
    public ImageIcon getIconBack() {
        return this.iconBack;
    }
    
    public void setIconBack(ImageIcon iconBack) {
        this.iconBack = iconBack;
    }
    
    public JLabel[] getGoodsLabs() {
        if (this.goodsLabs == null) {
            this.goodsLabs = new JLabel[12];
            if (MyIsif.getStyle().equals("水墨")) {
                for (int i = 0; i < this.goodsLabs.length; ++i) {
                    (this.goodsLabs[i] = new JLabel()).setBounds(49 + i % 6 * 51, 56 + i / 6 * 51, 50, 50);
                    this.goodsLabs[i].addMouseListener(new MouseListenerCashRewards(i, this));
                    this.add(this.goodsLabs[i]);
                }
            }
            else {
                for (int i = 0; i < this.goodsLabs.length; ++i) {
                    (this.goodsLabs[i] = new JLabel()).setBounds(22 + i % 6 * 51, 57 + i / 6 * 51, 50, 50);
                    this.goodsLabs[i].addMouseListener(new MouseListenerCashRewards(i, this));
                    this.add(this.goodsLabs[i]);
                }
            }
        }
        return this.goodsLabs;
    }
    
    public void setGoodsLabs(JLabel[] goodsLabs) {
        this.goodsLabs = goodsLabs;
    }
    
    public JLabel getChooseGoods() {
        if (this.chooseGoods == null) {
            this.chooseGoods = new JLabel();
            if (MyIsif.getStyle().equals("水墨")) {
                this.chooseGoods.setBounds(103, 189, 55, 53);
            }
            else {
                this.chooseGoods.setBounds(55, 192, 55, 53);
            }
            this.add(this.chooseGoods);
        }
        return this.chooseGoods;
    }
    
    public void setChooseGoods(JLabel chooseGoods) {
        this.chooseGoods = chooseGoods;
    }
    
    public JTextField getTextNum() {
        if (this.textNum == null) {
            (this.textNum = new JTextField("0")).setForeground(Color.white);
            this.textNum.setFont(UIUtils.TEXT_FONT);
            if (MyIsif.getStyle().equals("水墨")) {
                this.textNum.setBounds(224, 192, 48, 16);
            }
            else {
                this.textNum.setBounds(185, 201, 48, 16);
            }
            this.textNum.setOpaque(false);
            this.textNum.setCaretColor(Color.white);
            this.textNum.setBorder(null);
            this.add(this.textNum);
            this.textNum.getDocument().addDocumentListener(new DocumentListener() {
                @Override
                public void removeUpdate(DocumentEvent e) {
                    String s = JpanelCashRewardsMain.this.textNum.getText();
                    try {
                        int sum = Integer.parseInt(s);
                        if (JpanelCashRewardsMain.this.chooseShop != null) {
                            JpanelCashRewardsMain.this.shopPrice = new BigDecimal((long)sum * JpanelCashRewardsMain.this.chooseShop.getShopprice());
                        }
                    }
                    catch (NumberFormatException ex) {}
                }
                
                @Override
                public void insertUpdate(DocumentEvent e) {
                    String s = JpanelCashRewardsMain.this.textNum.getText();
                    try {
                        int sum = Integer.parseInt(s);
                        if (JpanelCashRewardsMain.this.chooseShop != null) {
                            JpanelCashRewardsMain.this.shopPrice = new BigDecimal((long)sum * JpanelCashRewardsMain.this.chooseShop.getShopprice());
                        }
                    }
                    catch (NumberFormatException ex) {}
                }
                
                @Override
                public void changedUpdate(DocumentEvent e) {
                }
            });
            this.textNum.addKeyListener(new KeyListener() {
                @Override
                public void keyTyped(KeyEvent e) {
                    int charstr = e.getKeyChar();
                    String str = JpanelCashRewardsMain.this.textNum.getText();
                    if (str.length() == 0) {
                        JpanelCashRewardsMain.this.textNum.setText("0");
                    }
                    if (charstr < 48 || charstr > 57) {
                        e.consume();
                    }
                    if (str.length() == 1 && str.equals("0")) {
                        JpanelCashRewardsMain.this.textNum.setText("");
                    }
                    if (str.length() > 0 && str.length() > 1) {
                        e.consume();
                        JpanelCashRewardsMain.this.textNum.setText("99");
                        int sum = Integer.parseInt(JpanelCashRewardsMain.this.textNum.getText());
                        if (JpanelCashRewardsMain.this.chooseShop != null) {
                            JpanelCashRewardsMain.this.shopPrice = new BigDecimal((long)sum * JpanelCashRewardsMain.this.chooseShop.getShopprice());
                        }
                    }
                }
                
                @Override
                public void keyPressed(KeyEvent e) {
                }
                
                @Override
                public void keyReleased(KeyEvent e) {
                }
            });
        }
        return this.textNum;
    }
    
    public void setTextNum(JTextField textNum) {
        this.textNum = textNum;
    }
    
    public BtnSummonEquipMain getExchangeBtn() {
        if (this.exchangeBtn == null) {
            if (MyIsif.getStyle().equals("水墨")) {
                (this.exchangeBtn = new BtnSummonEquipMain("inkImg/button1/B20.png", 1, UIUtils.COLOR_BLACK, UIUtils.TEXT_HY16, "兑换", 30, this)).setBounds(164, 255, 60, 26);
            }
            else {
                (this.exchangeBtn = new BtnSummonEquipMain("inkImg/hongmu/6026.png", 1, UIUtils.COLOR_BTNPUTONG, UIUtils.TEXT_HY88, "兑换", 30, this)).setBounds(134, 271, 60, 26);
            }
            this.add(this.exchangeBtn);
        }
        return this.exchangeBtn;
    }
    
    public void setExchangeBtn(BtnSummonEquipMain exchangeBtn) {
        this.exchangeBtn = exchangeBtn;
    }
    
    public JLabel[] getConsumptionGoodsLabs() {
        if (this.consumptionGoodsLabs == null) {
            this.consumptionGoodsLabs = new JLabel[2];
            if (MyIsif.getStyle().equals("水墨")) {
                for (int i = 0; i < this.consumptionGoodsLabs.length; ++i) {
                    (this.consumptionGoodsLabs[i] = new JLabel("奖章:" + i * 60 + "/" + 12354 * i)).setForeground(Color.white);
                    this.consumptionGoodsLabs[i].setFont(UIUtils.TEXT_FONT);
                    this.consumptionGoodsLabs[i].setBounds(182, 226 + i * 22, 120, 20);
                    this.add(this.consumptionGoodsLabs[i]);
                }
            }
            else {
                for (int i = 0; i < this.consumptionGoodsLabs.length; ++i) {
                    (this.consumptionGoodsLabs[i] = new JLabel("奖章:" + i * 60 + "/" + 12354 * i)).setForeground(Color.white);
                    this.consumptionGoodsLabs[i].setFont(UIUtils.TEXT_FONT);
                    this.consumptionGoodsLabs[i].setBounds(182, 226 + i * 22, 120, 20);
                    this.add(this.consumptionGoodsLabs[i]);
                }
            }
        }
        return this.consumptionGoodsLabs;
    }
    
    public void setConsumptionGoodsLabs(JLabel[] consumptionGoodsLabs) {
        this.consumptionGoodsLabs = consumptionGoodsLabs;
    }
    
    public Shop getChooseShop() {
        return this.chooseShop;
    }
    
    public void setChooseShop(Shop chooseShop) {
        this.chooseShop = chooseShop;
    }
    
    public BigDecimal getShopPrice() {
        return this.shopPrice;
    }
    
    public void setShopPrice(BigDecimal shopPrice) {
        this.shopPrice = shopPrice;
    }
    
    public int getGoodsType() {
        return this.goodsType;
    }
    
    public void setGoodsType(int goodsType) {
        this.goodsType = goodsType;
    }
}
