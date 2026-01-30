package org.come.Jpanel;

import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import org.come.socket.SendMessageUntil;
import org.come.until.GsonUtil;
import org.come.socket.Agreement;
import org.come.bean.BuyShopBean;
import org.come.Frame.ZhuFrame;
import org.come.until.FormsManagement;
import java.awt.Font;
import java.awt.event.MouseEvent;
import com.tool.btn.BtnUtil;
import com.tool.btn.MoBanBtn;
import org.come.until.EquipTool;
import org.come.until.Util;
import org.come.until.CutButtonImage;
import java.awt.Graphics;
import org.apache.commons.lang.StringUtils;
import org.come.until.GoodsListFromServerUntil;
import com.tool.role.RoleData;
import com.tool.btn.FormsOnOffBtn;
import java.awt.Dimension;
import com.updateNew.MyIsif;
import java.awt.Color;
import javax.swing.BorderFactory;
import com.tool.tcpimg.UIUtils;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JTextField;
import java.math.BigDecimal;
import javax.swing.JLabel;
import org.come.entity.Goodstable;
import org.come.model.Shop;
import java.util.List;
import javax.swing.JPanel;

public class DuiHuanLingLiJpanel extends JPanel
{
    private ChangePageBtn[] changePageBtns;
    private List<Shop> shopList;
    private List<Goodstable> goodList;
    public JLabel[] shopLabels;
    public JLabel[] goodsLabels;
    public String currency;
    private BigDecimal unitPrice;
    private BigDecimal totalPrice;
    private BigDecimal balance;
    private JTextField textNumber;
    private DuiHuanLingBtn exchangeBtn;
    private DuiHuanLingBtn maxBtn;
    private int pageNum;
    private int columnIndex;
    private int shopIndex;
    private int goodsIndex;
    private ImageIcon icon;
    
    public DuiHuanLingLiJpanel() {
        this.changePageBtns = new ChangePageBtn[5];
        this.shopList = new ArrayList<>();
        this.goodList = new ArrayList<>();
        this.shopLabels = new JLabel[24];
        this.goodsLabels = new JLabel[12];
        this.currency = "解卦灵力";
        this.pageNum = 1;
        this.columnIndex = 1;
        this.shopIndex = -1;
        this.goodsIndex = -1;
        this.setBackground(UIUtils.Color_BACK);
        this.setLayout(null);
        this.unitPrice = new BigDecimal(0);
        this.totalPrice = new BigDecimal(0);
        this.balance = new BigDecimal(0);
        for (int i = 0; i < this.shopLabels.length; ++i) {
            (this.shopLabels[i] = new JLabel()).addMouseListener(new ShopMouslisten(i));
        }
        for (int i = 0; i < this.goodsLabels.length; ++i) {
            (this.goodsLabels[i] = new JLabel()).addMouseListener(new GoodsMouslisten(i));
            this.goodsLabels[i].setVisible(false);
        }
        (this.textNumber = new JTextField()).setBackground(UIUtils.Color_BACK);
        this.textNumber.setBorder(BorderFactory.createEmptyBorder());
        this.textNumber.setForeground(Color.white);
        this.textNumber.setCaretColor(Color.white);
        this.textNumber.getDocument().addDocumentListener(new NumberDocumentListener());
        this.textNumber.addKeyListener(new NumberKeyListener());
        if (MyIsif.getStyle().equals("水墨")) {
            this.setPreferredSize(new Dimension(394, 502));
            FormsOnOffBtn offBtn = new FormsOnOffBtn("inkImg/button/1.png", 1, 714);
            offBtn.setBounds(353, 10, 25, 25);
            this.add(offBtn);
            for (int j = 0; j < this.shopLabels.length; ++j) {
                this.shopLabels[j].setBounds(52 + j % 6 * 52 - j % 6, 45 + j / 6 * 51, 48, 49);
            }
            for (int j = 0; j < this.goodsLabels.length; ++j) {
                this.goodsLabels[j].setBounds(51 + j % 6 * 52 - j % 6, 280 + j / 6 * 52, 48, 49);
            }
            for (int j = 0; j < this.changePageBtns.length; ++j) {
                (this.changePageBtns[j] = new ChangePageBtn("inkImg/button1/C0" + (j + 1) + ".png", 0, j)).setBounds(358, 42 + j * 34, 35, 31);
            }
            this.textNumber.setBounds(192, 414, 68, 18);
            (this.exchangeBtn = new DuiHuanLingBtn("inkImg/button1/B20.png", 1, UIUtils.COLOR_BLACK, UIUtils.TEXT_HY16, "兑换", 0)).setBounds(300, 410, 68, 26);
            (this.maxBtn = new DuiHuanLingBtn("inkImg/button/2.png", 1, UIUtils.COLOR_BTNTEXT, UIUtils.TEXT_FONT, "最大", 1)).setBounds(260, 414, 34, 17);
        }
        else {
            this.setPreferredSize(new Dimension(364, 526));
            FormsOnOffBtn offBtn = new FormsOnOffBtn("inkImg/hongmu/S74.png", 1, 714);
            offBtn.setBounds(335, 0, 25, 25);
            this.add(offBtn);
            for (int j = 0; j < this.shopLabels.length; ++j) {
                this.shopLabels[j].setBounds(24 + j % 6 * 52 - j % 6, 60 + j / 6 * 51, 48, 48);
            }
            for (int j = 0; j < this.goodsLabels.length; ++j) {
                this.goodsLabels[j].setBounds(23 + j % 6 * 52 - j % 6, 294 + j / 6 * 51, 48, 48);
            }
            for (int j = 0; j < this.changePageBtns.length; ++j) {
                (this.changePageBtns[j] = new ChangePageBtn("inkImg/hongmu/SBG.png", 0, j)).setBounds(330, 56 + j * 34, 35, 31);
            }
            this.textNumber.setBounds(166, 428, 62, 18);
            (this.exchangeBtn = new DuiHuanLingBtn("inkImg/hongmu/6026.png", 1, UIUtils.COLOR_BTNPUTONG, UIUtils.TEXT_HY88, "兑换", 0)).setBounds(274, 422, 68, 26);
            (this.maxBtn = new DuiHuanLingBtn("inkImg/hongmu/21_png.button.tab_nex.png", 1, UIUtils.COLOR_BTNPUTONG, UIUtils.TEXT_FONT, "最大", 1)).setBounds(232, 426, 34, 17);
        }
        for (int i = 0; i < this.shopLabels.length; ++i) {
            this.add(this.shopLabels[i]);
        }
        for (int i = 0; i < this.goodsLabels.length; ++i) {
            this.add(this.goodsLabels[i]);
        }
        for (int i = 0; i < this.changePageBtns.length; ++i) {
            this.add(this.changePageBtns[i]);
        }
        this.add(this.textNumber);
        this.add(this.exchangeBtn);
        this.add(this.maxBtn);
    }
    
    public void updateBalance() {
        this.balance = RoleData.getRoleData().getLoginResult().getScoretype(this.currency);
        this.selectShop();
    }
    
    public void updatePrice(String shopId, long price) {
        if (this.shopList != null) {
            for (int i = 0; i < this.shopList.size(); ++i) {
                if (((Shop)this.shopList.get(i)).getShopid().equals(shopId)) {
                    ((Shop)this.shopList.get(i)).setShopprice(price);
                }
            }
        }
        this.selectShop();
    }
    
    public void initShopTables(List<Shop> shops) {
        this.updateBalance();
        this.shopList.clear();
        this.shopList.addAll(shops);
        this.pageNum = this.shopList.size() / 24 + ((this.shopList.size() % 24 == 0) ? 0 : 1);
        if (this.pageNum > this.changePageBtns.length) {
            this.pageNum = this.changePageBtns.length;
        }
        if (this.columnIndex > this.pageNum) {
            this.columnIndex = this.pageNum;
        }
        this.shopIndex = -1;
        this.updateShopChangePageBtn();
        this.changeShopPage();
    }
    
    private void updateShopChangePageBtn() {
        for (int i = 0; i < this.changePageBtns.length; ++i) {
            if (i < this.pageNum) {
                this.changePageBtns[i].btntypechange(2);
            }
            else {
                this.changePageBtns[i].btntypechange(0);
            }
            if (i == this.columnIndex - 1) {
                this.changePageBtns[i].btnchange(2);
            }
            else {
                this.changePageBtns[i].btnchange(0);
            }
        }
    }
    
    private void changeShopPage() {
        for (int i = 0; i < this.shopLabels.length; ++i) {
            int index = this.getCurrentShopIndex(i);
            if (index < this.shopList.size()) {
                this.shopLabels[i].setVisible(true);
            }
            else {
                this.shopLabels[i].setVisible(false);
            }
        }
    }
    
    private void selectShop() {
        Shop selectGoods = this.getCurrentShop(this.shopIndex);
        this.selectShop(selectGoods, this.shopIndex);
    }
    
    private void selectShop(Shop shop, int index) {
        if (shop != null) {
            if (this.shopIndex != -1) {
                this.shopLabels[this.shopIndex].setBorder(BorderFactory.createEmptyBorder());
            }
            this.shopLabels[index].setBorder(BorderFactory.createLineBorder(Color.RED));
            this.unitPrice = BigDecimal.valueOf(shop.getShopprice());
            this.textNumber.setText("");
            this.totalPrice = BigDecimal.ZERO;
            List<Goodstable> goodsList = GoodsListFromServerUntil.getGoodsListByGoodsId(shop.getShopiid());
            this.initGoodsTables(goodsList, -1);
            this.shopIndex = index;
        }
    }
    
    private Shop getCurrentShop(int index) {
        index = this.getCurrentShopIndex(index);
        if (index != -1 && this.shopList.size() > index) {
            return (Shop)this.shopList.get(index);
        }
        return null;
    }
    
    private int getCurrentShopIndex(int index) {
        return (index == -1) ? index : ((this.columnIndex - 1) * 24 + index);
    }
    
    private void initGoodsTables() {
        Shop selectGoods = this.getCurrentShop(this.shopIndex);
        List<Goodstable> goodsList = GoodsListFromServerUntil.getGoodsListByGoodsId(selectGoods.getShopiid());
        this.initGoodsTables(goodsList, this.goodsIndex);
    }
    
    private void initGoodsTables(List<Goodstable> goods, int goodsIndex) {
        this.goodList.clear();
        if (goods != null) {
            this.goodList.addAll(goods);
        }
        if (goodsIndex == -1) {
            goodsIndex = 0;
        }
        this.changeGoodsPage();
        this.selectGoods(this.getCurrentGoods(goodsIndex), goodsIndex);
    }
    
    private void changeGoodsPage() {
        for (int i = 0; i < this.goodsLabels.length; ++i) {
            if (i < this.goodList.size()) {
                this.goodsLabels[i].setVisible(true);
            }
            else {
                this.goodsLabels[i].setVisible(false);
            }
        }
    }
    
    private void selectGoods(Goodstable goods, int index) {
        if (goods != null) {
            Goodstable currentGoods = this.getCurrentGoods(this.goodsIndex);
            int sum = 0;
            if (currentGoods != null && currentGoods.getRgid().compareTo(currentGoods.getRgid()) == 0) {
                String numText = this.textNumber.getText();
                if (StringUtils.isNotEmpty(numText)) {
                    sum = Integer.parseInt(numText);
                }
                if (++sum > (int)goods.getUsetime()) {
                    sum = (int)goods.getUsetime();
                }
            }
            else {
                if (this.goodsIndex != -1) {
                    this.goodsLabels[this.goodsIndex].setBorder(BorderFactory.createEmptyBorder());
                }
                this.goodsLabels[index].setBorder(BorderFactory.createLineBorder(Color.RED));
                ++sum;
            }
            this.textNumber.setText(sum + "");
            this.totalPrice = this.unitPrice.multiply(BigDecimal.valueOf((long)sum));
        }
        else {
            if (this.goodsIndex != -1) {
                this.goodsLabels[this.goodsIndex].setBorder(BorderFactory.createEmptyBorder());
            }
            this.textNumber.setText("");
            this.totalPrice = BigDecimal.ZERO;
        }
        this.goodsIndex = index;
    }
    
    private Goodstable getCurrentGoods(int index) {
        if (index != -1 && this.goodList.size() > index) {
            return (Goodstable)this.goodList.get(index);
        }
        return null;
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        if (MyIsif.getStyle().equals("水墨")) {
            if (this.icon == null) {
                this.icon = CutButtonImage.getImage("img/jiegua/dh1.png", -1, -1);
            }
            g.drawImage(this.icon.getImage(), 0, 0, this);
            if (this.unitPrice.compareTo(BigDecimal.ZERO) > 0) {
                Util.drawPrice(g, this.unitPrice, 192, 404);
            }
            if (this.totalPrice.compareTo(BigDecimal.ZERO) > 0) {
                Util.drawPrice(g, this.totalPrice, 192, 450);
            }
            Util.drawPrice(g, this.balance, 192, 474);
        }
        else {
            if (this.icon == null) {
                this.icon = CutButtonImage.getImage("img/jiegua/dh2.png", -1, -1);
            }
            g.drawImage(this.icon.getImage(), 0, 0, this);
            if (this.unitPrice.compareTo(BigDecimal.ZERO) > 0) {
                Util.drawPrice(g, this.unitPrice, 166, 418);
            }
            if (this.totalPrice.compareTo(BigDecimal.ZERO) > 0) {
                Util.drawPrice(g, this.totalPrice, 166, 464);
            }
            Util.drawPrice(g, this.balance, 166, 488);
        }
        this.paintShop(g);
        this.paintGoods(g);
    }
    
    private void paintShop(Graphics g) {
        for (int i = 0, index = (this.columnIndex - 1) * 24; index < this.shopList.size() && i < this.shopLabels.length; ++i, ++index) {
            ImageIcon image = GoodsListFromServerUntil.imgpathAdaptive(((Shop)this.shopList.get(index)).getShopskin(), 49, 49);
            g.drawImage(image.getImage(), this.shopLabels[i].getX(), this.shopLabels[i].getY(), null);
        }
    }
    
    private void paintGoods(Graphics g) {
        g.setFont(UIUtils.TEXT_FONT2);
        g.setColor(Color.WHITE);
        for (int i = 0; i < this.goodList.size() && i < this.goodsLabels.length; ++i) {
            Goodstable goods = (Goodstable)this.goodList.get(i);
            if (!EquipTool.isEquip(goods.getType())) {
                g.drawString(goods.getUsetime().toString(), this.goodsLabels[i].getX() + 4, this.goodsLabels[i].getY() + 15);
            }
            ImageIcon image = GoodsListFromServerUntil.imgpathAdaptive(goods.getSkin(), 49, 49);
            g.drawImage(image.getImage(), this.goodsLabels[i].getX(), this.goodsLabels[i].getY(), null);
        }
        Goodstable currentGoods = this.getCurrentGoods(this.goodsIndex);
        if (currentGoods != null) {
            if (MyIsif.getStyle().equals("水墨")) {
                ImageIcon image2 = GoodsListFromServerUntil.imgpathAdaptive(currentGoods.getSkin(), 49, 49);
                g.drawImage(image2.getImage(), 52, 410, null);
            }
            else {
                ImageIcon image2 = GoodsListFromServerUntil.imgpathAdaptive(currentGoods.getSkin(), 49, 49);
                g.drawImage(image2.getImage(), 26, 424, null);
            }
        }
    }
    
    private String getLimit(String key, int num) {
        String value = String.valueOf(num);
        int[] a = { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
        int b = num;
        int len = key.length();
        int length = value.length();
        int i = 0;
        while (b > 0) {
            a[length - 1 - i] = b % 10;
            ++i;
            b /= 10;
        }
        int in;
        if (key.equals("")) {
            in = 0;
        }
        else {
            in = (int)Integer.valueOf(key) * 10;
        }
        if (len < length - 1) {
            if (len == 0) {
                value = string(1, 9);
            }
            else {
                value = string(0, 9);
            }
        }
        else if (len == length - 1) {
            if (in <= num) {
                if (in == 0) {
                    value = string(1, a[len]);
                }
                else {
                    value = string(0, a[len]);
                }
            }
            else {
                value = "";
            }
        }
        else {
            value = "";
        }
        return value;
    }
    
    private static String string(int a, int b) {
        String value = "";
        for (int i = a; i <= b; ++i) {
            value += String.valueOf(i);
        }
        return value;
    }
    
    private class ChangePageBtn extends MoBanBtn
    {
        private int index;
        
        public ChangePageBtn(String iconpath, int type, int index) {
            super(iconpath, type);
            this.index = index;
        }
        
        @Override
        public void chooseyes() {
            this.btnchange(this.type = 2);
            BtnUtil.btnBinding(DuiHuanLingLiJpanel.this.changePageBtns, this.index);
            DuiHuanLingLiJpanel.this.columnIndex = this.index + 1;
            DuiHuanLingLiJpanel.this.changeShopPage();
        }
        
        @Override
        public void chooseno() {
        }
        
        @Override
        public void nochoose(MouseEvent e) {
        }
    }
    
    private class DuiHuanLingBtn extends MoBanBtn
    {
        private int caozuo;
        
        public DuiHuanLingBtn(String iconpath, int type, int caozuo) {
            super(iconpath, type);
            this.caozuo = caozuo;
        }
        
        public DuiHuanLingBtn(String iconpath, int type, Color[] colors, Font font, String text, int caozuo) {
            super(iconpath, type, colors);
            this.setText(text);
            this.setFont(font);
            this.setVerticalTextPosition(0);
            this.setHorizontalTextPosition(0);
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
            if (this.caozuo == 0) {
                if (Util.isCanBuyOrno()) {
                    return;
                }
                if (FormsManagement.getframe(14).isVisible()) {
                    ZhuFrame.getZhuJpanel().addPrompt2("交易时不能兑换灵力");
                    return;
                }
                int sum = StringUtils.isNotBlank(DuiHuanLingLiJpanel.this.textNumber.getText()) ? Integer.parseInt(DuiHuanLingLiJpanel.this.textNumber.getText()) : 0;
                long price = StringUtils.isNotBlank(DuiHuanLingLiJpanel.this.totalPrice.toString()) ? Long.parseLong(DuiHuanLingLiJpanel.this.totalPrice.toString()) : 0L;
                Shop currentShop = DuiHuanLingLiJpanel.this.getCurrentShop(DuiHuanLingLiJpanel.this.shopIndex);
                if (currentShop != null) {
                    Goodstable currentGoods = DuiHuanLingLiJpanel.this.getCurrentGoods(DuiHuanLingLiJpanel.this.goodsIndex);
                    if (GoodsListFromServerUntil.isExist(currentGoods)) {
                        return;
                    }
                    Integer usetime = currentGoods.getUsetime();
                    if (sum > (int)usetime) {
                        sum = (int)usetime;
                    }
                    if (sum <= 0) {
                        ZhuFrame.getZhuJpanel().addPrompt2("你可兑换数量为零");
                        return;
                    }
                    BuyShopBean bean = new BuyShopBean();
                    bean.setAte(2);
                    bean.setCd(currentShop.getShopid());
                    bean.setSum(sum);
                    bean.setPrice(price);
                    bean.setGoodsId(currentGoods.getRgid());
                    String senmes = Agreement.getAgreement().nbuyAgreement(GsonUtil.getGsonUtil().getgson().toJson(bean));
                    SendMessageUntil.toServer(senmes);
                    currentGoods.goodxh(sum);
                }
            }
            else if (this.caozuo == 1) {
                Goodstable currentGoods2 = DuiHuanLingLiJpanel.this.getCurrentGoods(DuiHuanLingLiJpanel.this.goodsIndex);
                if (currentGoods2 != null) {
                    DuiHuanLingLiJpanel.this.textNumber.setText(currentGoods2.getUsetime().toString());
                }
            }
        }
    }
    
    private class ShopMouslisten extends MouseAdapter
    {
        private int index;
        
        public ShopMouslisten(int index) {
            this.index = index;
        }
        
        @Override
        public void mousePressed(MouseEvent e) {
            Shop shop = DuiHuanLingLiJpanel.this.getCurrentShop(this.index);
            if (shop != null) {
                DuiHuanLingLiJpanel.this.selectShop(shop, this.index);
            }
        }
        
        @Override
        public void mouseEntered(MouseEvent e) {
            Shop shop = DuiHuanLingLiJpanel.this.getCurrentShop(this.index);
            if (shop != null) {
                if (this.index != DuiHuanLingLiJpanel.this.shopIndex) {
                    DuiHuanLingLiJpanel.this.shopLabels[this.index].setBorder(BorderFactory.createLineBorder(Color.WHITE));
                }
                ZhuFrame.getZhuJpanel().creatgoodtext(shop);
            }
        }
        
        @Override
        public void mouseExited(MouseEvent e) {
            if (DuiHuanLingLiJpanel.this.shopIndex != this.index) {
                DuiHuanLingLiJpanel.this.shopLabels[this.index].setBorder(BorderFactory.createEmptyBorder());
            }
            ZhuFrame.getZhuJpanel().cleargoodtext();
        }
    }
    
    private class GoodsMouslisten extends MouseAdapter
    {
        private int index;
        
        public GoodsMouslisten(int index) {
            this.index = index;
        }
        
        @Override
        public void mousePressed(MouseEvent e) {
            Goodstable goods = DuiHuanLingLiJpanel.this.getCurrentGoods(this.index);
            if (goods != null) {
                DuiHuanLingLiJpanel.this.selectGoods(goods, this.index);
            }
        }
        
        @Override
        public void mouseEntered(MouseEvent e) {
            Goodstable goods = DuiHuanLingLiJpanel.this.getCurrentGoods(this.index);
            if (goods != null) {
                DuiHuanLingLiJpanel.this.goodsLabels[this.index].setBorder(BorderFactory.createLineBorder(Color.WHITE));
                ZhuFrame.getZhuJpanel().creatgoodtext(goods);
            }
        }
        
        @Override
        public void mouseExited(MouseEvent e) {
            if (DuiHuanLingLiJpanel.this.goodsIndex != this.index) {
                DuiHuanLingLiJpanel.this.goodsLabels[this.index].setBorder(null);
            }
            ZhuFrame.getZhuJpanel().cleargoodtext();
        }
    }
    
    private class NumberKeyListener implements KeyListener
    {
        @Override
        public void keyTyped(KeyEvent e) {
            int charstr = e.getKeyChar();
            String str = DuiHuanLingLiJpanel.this.textNumber.getText();
            if (charstr < 48 || charstr > 57) {
                e.consume();
            }
            Goodstable currentGoods = DuiHuanLingLiJpanel.this.getCurrentGoods(DuiHuanLingLiJpanel.this.goodsIndex);
            if (currentGoods != null) {
                String key = DuiHuanLingLiJpanel.this.textNumber.getText();
                String value = DuiHuanLingLiJpanel.this.getLimit(key, (int)currentGoods.getUsetime());
                if (value.indexOf((int)e.getKeyChar()) < 0) {
                    e.consume();
                }
            }
            else {
                e.consume();
            }
        }
        
        @Override
        public void keyReleased(KeyEvent e) {
            Goodstable currentGoods = DuiHuanLingLiJpanel.this.getCurrentGoods(DuiHuanLingLiJpanel.this.goodsIndex);
            if (currentGoods != null) {
                String str = DuiHuanLingLiJpanel.this.textNumber.getText();
                if (str.length() > 0) {
                    if (str.charAt(0) == '0') {
                        DuiHuanLingLiJpanel.this.textNumber.setText(Integer.valueOf(str).toString());
                    }
                    if (Integer.parseInt(str) >= (int)currentGoods.getUsetime()) {
                        DuiHuanLingLiJpanel.this.textNumber.setText(currentGoods.getUsetime().toString());
                    }
                }
            }
        }
        
        @Override
        public void keyPressed(KeyEvent e) {
        }
    }
    
    private class NumberDocumentListener implements DocumentListener
    {
        @Override
        public void insertUpdate(DocumentEvent e) {
            String s = DuiHuanLingLiJpanel.this.textNumber.getText();
            try {
                int sum = Integer.parseInt(s);
                long price = Long.parseLong(DuiHuanLingLiJpanel.this.unitPrice.toString());
                DuiHuanLingLiJpanel.this.totalPrice = BigDecimal.valueOf((long)sum).multiply(BigDecimal.valueOf(price));
            }
            catch (NumberFormatException ex) {}
        }
        
        @Override
        public void removeUpdate(DocumentEvent e) {
            String s = DuiHuanLingLiJpanel.this.textNumber.getText();
            try {
                int sum = Integer.parseInt(s);
                long price = Long.parseLong(DuiHuanLingLiJpanel.this.unitPrice.toString());
                DuiHuanLingLiJpanel.this.totalPrice = BigDecimal.valueOf((long)sum).multiply(BigDecimal.valueOf(price));
            }
            catch (NumberFormatException ex) {}
        }
        
        @Override
        public void changedUpdate(DocumentEvent e) {
        }
    }
}
