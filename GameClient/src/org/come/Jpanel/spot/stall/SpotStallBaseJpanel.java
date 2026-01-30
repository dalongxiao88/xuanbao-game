package org.come.Jpanel.spot.stall;

import com.tool.role.RoleData;
import org.come.until.EquipTool;
import org.come.until.UserMessUntil;
import java.awt.event.KeyEvent;
import java.awt.event.KeyAdapter;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.Graphics;
import org.come.until.Util;
import org.apache.commons.lang.StringUtils;
import org.come.until.CutButtonImage;
import org.come.until.GoodsListFromServerUntil;
import org.come.model.Lingbao;
import org.come.entity.RoleSummoning;
import org.come.entity.Goodstable;
import java.math.BigDecimal;
import org.come.Jpanel.spot.Commodity;
import com.tool.Document.NumberDocument;
import javax.swing.BorderFactory;
import java.awt.Color;
import com.tool.tcpimg.UIUtils;
import org.come.Jpanel.spot.box.SpotBoxJpanel;
import com.tool.btn.spot.SpotPublishBtn;
import javax.swing.JTextField;
import javax.swing.JLabel;
import com.tool.Stall.CommodityBean;
import com.tool.Document.InputNum;

public abstract class SpotStallBaseJpanel extends SpotStallJpanel implements InputNum
{
    protected CommodityBean commodity;
    private final JLabel labCommodity;
    private final JTextField textNumber;
    private final JTextField textUnitprice;
    private final JLabel labTotalPrice;
    private final SpotPublishBtn spotPublishBtn;
    
    public SpotStallBaseJpanel(SpotBoxJpanel spotBoxJpanel, String backPath, String type) {
        super(spotBoxJpanel, backPath, type);
        this.labCommodity = new JLabel("", 0);
        (this.textNumber = new JTextField()).setFont(UIUtils.TEXT_FONT1);
        this.textNumber.setText("0");
        this.textNumber.setOpaque(false);
        this.textNumber.setCaretColor(Color.WHITE);
        this.textNumber.setForeground(Color.WHITE);
        this.textNumber.setBorder(BorderFactory.createEmptyBorder());
        this.textNumber.addKeyListener(new TextKeyListener(0));
        this.textNumber.getDocument().addDocumentListener(new TextDocumentListener());
        (this.textUnitprice = new JTextField()).setFont(UIUtils.TEXT_FONT1);
        this.textUnitprice.setText("0");
        this.textUnitprice.setOpaque(false);
        this.textUnitprice.setForeground(Color.WHITE);
        this.textUnitprice.setCaretColor(Color.WHITE);
        this.textUnitprice.setBorder(BorderFactory.createEmptyBorder());
        this.textUnitprice.setDocument(new NumberDocument(this.textUnitprice, 4, this));
        this.textUnitprice.addKeyListener(new TextKeyListener(1));
        (this.labTotalPrice = new JLabel("0")).setFont(UIUtils.TEXT_FONT1);
        this.labTotalPrice.setForeground(Color.WHITE);
        this.setCommodityBounds(this.textNumber, this.textUnitprice, this.labTotalPrice, this.labCommodity);
        this.add(this.labCommodity);
        this.add(this.textNumber);
        this.add(this.textUnitprice);
        this.add(this.labTotalPrice);
        (this.spotPublishBtn = new SpotPublishBtn("inkImg/button/2.png", 1, UIUtils.COLOR_BTNTEXT, UIUtils.TEXT_FONT2, "预览", 10, this)).setBounds(560, 22, 34, 17);
        this.add(this.spotPublishBtn);
    }
    
    public abstract void listing();
    
    public abstract void withdraw();
    
    abstract void setCommodityBounds(JTextField p0, JTextField p1, JLabel p2, JLabel p3);
    
    @Override
    public void updateCommoditys() {
        super.updateCommoditys();
        CommodityBean commodity = this.getCommodity();
        if (commodity != null) {
            CommodityBean commodityBean = this.getCommodity((Commodity)commodity.getCommodityBean());
            this.setCurrentCommodity(commodityBean);
        }
    }
    
    protected BigDecimal getCommodityId(Commodity commodity, int type) {
        if (commodity instanceof Goodstable) {
            Goodstable goods = (Goodstable)commodity;
            if (type == 0) {
                return goods.getRgid();
            }
            return goods.getGoodsid();
        }
        else if (commodity instanceof RoleSummoning) {
            RoleSummoning pet = (RoleSummoning)commodity;
            if (type == 0) {
                return pet.getSid();
            }
            return new BigDecimal(pet.getSummoningid());
        }
        else if (commodity instanceof Lingbao) {
            Lingbao lingbao = (Lingbao)commodity;
            if (type == 0) {
                return lingbao.getBaoid();
            }
            return lingbao.getBaoid();
        }
        else {
            return null;
        }
    }
    
    public abstract CommodityBean getCommodity(Commodity p0);
    
    @Override
    public void setCurrentCommodity(CommodityBean commodity, int sum) {
        this.commodity = commodity;
        if (commodity != null) {
            this.textNumber.setText(((commodity.getSum() > 0) ? commodity.getSum() : 1) + "");
            if (commodity.getMoney() != null && commodity.getMoney().compareTo(BigDecimal.ZERO) > 0) {
                this.setUnitprice(commodity.getMoney());
                this.setTotalPrice();
            }
            else {
                this.textUnitprice.setText("");
                this.labTotalPrice.setText("");
            }
            if (commodity.getType() == 0 || commodity.getType() == 3) {
                this.labCommodity.setIcon(GoodsListFromServerUntil.imgpathAdaptive(commodity.getCommoditySkin(), 49, 49));
            }
            else if (commodity.getType() == 1) {
                this.labCommodity.setIcon(CutButtonImage.getImage("img/head/p" + commodity.getCommoditySkin() + ".png", 49, 49));
            }
            else if (commodity.getType() == 2) {
                this.labCommodity.setIcon(CutButtonImage.getImage("img/lingbao/" + commodity.getCommoditySkin() + ".png", 49, 49));
            }
            this.changePublishBtn(commodity.getState() == 1);
        }
        else {
            this.textNumber.setText("");
            this.textUnitprice.setText("");
            this.labTotalPrice.setText("");
            this.labCommodity.setIcon(null);
            this.changePublishBtn(false);
        }
    }
    
    protected abstract void changePublishBtn(boolean p0);
    
    public int getNumber() {
        String text = this.textNumber.getText();
        if (StringUtils.isNotBlank(text)) {
            return Integer.parseInt(text);
        }
        return 0;
    }
    
    public long getTotalPrice() {
        if (StringUtils.isNotBlank(this.labTotalPrice.getText())) {
            return Long.parseLong(this.labTotalPrice.getText().replaceAll(",", ""));
        }
        return 0L;
    }
    
    public long getUnitprice() {
        if (StringUtils.isNotBlank(this.textUnitprice.getText())) {
            return Long.parseLong(this.textUnitprice.getText().replaceAll(",", ""));
        }
        return 0L;
    }
    
    protected void setTotalPrice() {
        try {
            int number = Integer.parseInt(this.textNumber.getText());
            long price = Long.parseLong(this.textUnitprice.getText().replaceAll(",", ""));
            this.setTotalPrice(BigDecimal.valueOf((long)number).multiply(BigDecimal.valueOf(price)));
        }
        catch (NumberFormatException ex) {}
    }
    
    protected void setTotalPrice(BigDecimal totalPrice) {
        Util.changeTextColor(this.labTotalPrice, totalPrice);
        StringBuffer gold = new StringBuffer(totalPrice + "");
        for (int index = gold.length() - 3; index > 0; index -= 3) {
            gold.insert(index, ',');
        }
        this.labTotalPrice.setText(gold.toString());
    }
    
    public void setUnitprice(BigDecimal unitprice) {
        Util.changeTextColor(this.textUnitprice, unitprice);
        StringBuffer gold = new StringBuffer(unitprice + "");
        for (int index = gold.length() - 3; index > 0; index -= 3) {
            gold.insert(index, ',');
        }
        this.textUnitprice.setText(gold.toString());
    }
    
    public CommodityBean getCommodity() {
        return this.commodity;
    }
    
    @Override
    public void upNum() {
        this.setTotalPrice();
    }
    
    @Override
    public boolean isChange() {
        return false;
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
    }
    
    private class TextDocumentListener implements DocumentListener
    {
        @Override
        public void insertUpdate(DocumentEvent e) {
            SpotStallBaseJpanel.this.setTotalPrice();
        }
        
        @Override
        public void removeUpdate(DocumentEvent e) {
            SpotStallBaseJpanel.this.setTotalPrice();
        }
        
        @Override
        public void changedUpdate(DocumentEvent e) {
        }
    }
    
    private class TextKeyListener extends KeyAdapter
    {
        private int type;
        
        public TextKeyListener(int type) {
            this.type = type;
        }
        
        @Override
        public void keyTyped(KeyEvent e) {
            int keyChar = e.getKeyChar();
            if (keyChar < 48 || keyChar > 57 || SpotStallBaseJpanel.this.commodity == null) {
                e.consume();
                return;
            }
            if (this.type == 0) {
                if (SpotStallBaseJpanel.this.commodity.getType() == 0 || SpotStallBaseJpanel.this.commodity.getType() == 3) {
                    String key = SpotStallBaseJpanel.this.textNumber.getText();
                    int size;
                    if (SpotStallBaseJpanel.this.commodity.getType() == 0) {
                        Goodstable goods = GoodsListFromServerUntil.czgood(SpotStallBaseJpanel.this.commodity.getCommodityId());
                        size = (int)goods.getUsetime();
                    }
                    else {
                        Goodstable goods = UserMessUntil.getgoodstable(SpotStallBaseJpanel.this.commodity.getCommodityId());
                        if (!EquipTool.isEquip(goods.getType())) {
                            BigDecimal gold = RoleData.getRoleData().getLoginResult().getGold();
                            long unitprice = SpotStallBaseJpanel.this.getUnitprice();
                            if (unitprice != 0L) {
                                size = (int)(gold.longValue() / unitprice);
                            }
                            else {
                                size = Integer.MAX_VALUE;
                            }
                        }
                        else {
                            size = 1;
                        }
                    }
                    String value = this.getLimit(key, size);
                    if (value.indexOf((int)e.getKeyChar()) < 0) {
                        e.consume();
                        SpotStallBaseJpanel.this.textNumber.setText(size + "");
                    }
                }
                else {
                    e.consume();
                }
            }
        }
        
        @Override
        public void keyReleased(KeyEvent e) {
            if (SpotStallBaseJpanel.this.commodity != null) {
                if (SpotStallBaseJpanel.this.commodity.getType() == 0 || SpotStallBaseJpanel.this.commodity.getType() == 3) {
                    String str = (this.type == 0) ? SpotStallBaseJpanel.this.textNumber.getText() : SpotStallBaseJpanel.this.textUnitprice.getText();
                    if (str.length() > 0) {
                        if (str.charAt(0) == '0') {
                            if (this.type == 0) {
                                SpotStallBaseJpanel.this.textNumber.setText(Integer.valueOf(str).toString());
                            }
                            else {
                                SpotStallBaseJpanel.this.textUnitprice.setText(Long.valueOf(str).toString());
                            }
                        }
                        long size;
                        if (SpotStallBaseJpanel.this.commodity.getType() == 0) {
                            Goodstable goods = GoodsListFromServerUntil.czgood(SpotStallBaseJpanel.this.commodity.getCommodityId());
                            size = (long)(int)goods.getUsetime();
                        }
                        else {
                            BigDecimal gold = RoleData.getRoleData().getLoginResult().getGold();
                            long unitprice = SpotStallBaseJpanel.this.getUnitprice();
                            if (unitprice != 0L) {
                                size = gold.longValue() / unitprice;
                            }
                            else {
                                size = 2147483647L;
                            }
                        }
                        if (this.type == 0 && (long)Integer.parseInt(str) >= size) {
                            SpotStallBaseJpanel.this.textNumber.setText(size + "");
                        }
                    }
                }
                else if (this.type == 0) {
                    SpotStallBaseJpanel.this.textNumber.setText("1");
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
                    value = this.string(1, 9);
                }
                else {
                    value = this.string(0, 9);
                }
            }
            else if (len == length - 1) {
                if (in <= num) {
                    if (in == 0) {
                        value = this.string(1, a[len]);
                    }
                    else {
                        value = this.string(0, a[len]);
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
        
        private String string(int a, int b) {
            String value = "";
            for (int i = a; i <= b; ++i) {
                value += String.valueOf(i);
            }
            return value;
        }
    }
}
