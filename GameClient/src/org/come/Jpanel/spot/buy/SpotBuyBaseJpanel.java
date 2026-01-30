package org.come.Jpanel.spot.buy;

import java.awt.event.KeyEvent;
import java.awt.event.KeyAdapter;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import org.apache.commons.lang.StringUtils;
import java.awt.Graphics;
import org.come.until.Util;
import java.math.BigDecimal;
import javax.swing.BorderFactory;
import java.awt.Color;
import com.tool.tcpimg.UIUtils;
import org.come.Jpanel.spot.box.SpotBuyBoxJpanel;
import com.tool.btn.spot.SpotPublishBtn;
import javax.swing.JTextField;
import javax.swing.JLabel;
import com.tool.Stall.CommodityBean;

public abstract class SpotBuyBaseJpanel extends SpotBuyJpanel
{
    protected int pageNumber;
    protected CommodityBean commodity;
    protected final JLabel labCommodity;
    protected final JTextField textNumber;
    protected final JLabel labUnitprice;
    protected final JLabel labTotalPrice;
    private final SpotPublishBtn btnCurrentCommodity;
    
    public SpotBuyBaseJpanel(SpotBuyBoxJpanel spotBuyBoxJpanel, String backPath, String type) {
        super(spotBuyBoxJpanel, backPath, type);
        this.pageNumber = 1;
        this.textNumber = new JTextField();
        this.labCommodity = new JLabel();
        this.textNumber.setFont(UIUtils.TEXT_FONT1);
        this.textNumber.setOpaque(false);
        this.textNumber.setCaretColor(Color.WHITE);
        this.textNumber.setForeground(Color.WHITE);
        this.textNumber.setBorder(BorderFactory.createEmptyBorder());
        this.textNumber.addKeyListener(new TextKeyListener());
        this.textNumber.getDocument().addDocumentListener(new TextDocumentListener());
        (this.labUnitprice = new JLabel()).setFont(UIUtils.TEXT_FONT1);
        this.labUnitprice.setForeground(Color.WHITE);
        (this.labTotalPrice = new JLabel()).setFont(UIUtils.TEXT_FONT1);
        this.labTotalPrice.setForeground(Color.WHITE);
        this.btnCurrentCommodity = this.createConductTransactionsBtn();
        this.textNumber.setBounds(200, 424, 110, 18);
        this.labUnitprice.setBounds(200, 450, 110, 18);
        this.labTotalPrice.setBounds(370, 424, 110, 18);
        this.labCommodity.setBounds(100, 422, 48, 48);
        this.btnCurrentCommodity.setBounds(526, 432, 79, 25);
        this.add(this.textNumber);
        this.add(this.labCommodity);
        this.add(this.labUnitprice);
        this.add(this.labTotalPrice);
        this.add(this.btnCurrentCommodity);
    }
    
    protected void setTotalPrice() {
        try {
            int number = Integer.parseInt(this.textNumber.getText());
            long price = Long.parseLong(this.labUnitprice.getText().replaceAll(",", ""));
            this.setPrice(this.labTotalPrice, BigDecimal.valueOf((long)number).multiply(BigDecimal.valueOf(price)));
        }
        catch (NumberFormatException ex) {}
    }
    
    protected void setPrice(JLabel label, BigDecimal money) {
        Util.changeTextColor(label, money);
        StringBuffer gold = new StringBuffer(money + "");
        for (int index = gold.length() - 3; index > 0; index -= 3) {
            gold.insert(index, ',');
        }
        label.setText(gold.toString());
    }
    
    public abstract SpotPublishBtn createConductTransactionsBtn();
    
    public abstract void conductTransactions();
    
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
    }
    
    public CommodityBean getCommodity() {
        return this.commodity;
    }
    
    public void setNumber(int pageNumber) {
        if (pageNumber > 0) {
            this.textNumber.setText(pageNumber + "");
        }
        else {
            this.textNumber.setText("");
        }
    }
    
    public abstract int getGoodsNumber();
    
    public int getNumber() {
        int sum = 0;
        if (StringUtils.isNotBlank(this.textNumber.getText())) {
            sum = Integer.parseInt(this.textNumber.getText());
        }
        return sum;
    }
    
    private class TextDocumentListener implements DocumentListener
    {
        @Override
        public void insertUpdate(DocumentEvent e) {
            SpotBuyBaseJpanel.this.setTotalPrice();
        }
        
        @Override
        public void removeUpdate(DocumentEvent e) {
            SpotBuyBaseJpanel.this.setTotalPrice();
        }
        
        @Override
        public void changedUpdate(DocumentEvent e) {
        }
    }
    
    private class TextKeyListener extends KeyAdapter
    {
        @Override
        public void keyTyped(KeyEvent e) {
            int keyChar = e.getKeyChar();
            if (keyChar < 48 || keyChar > 57 || SpotBuyBaseJpanel.this.commodity == null) {
                e.consume();
                return;
            }
            if (SpotBuyBaseJpanel.this.commodity.getType() == 0 || SpotBuyBaseJpanel.this.commodity.getType() == 3) {
                String key = SpotBuyBaseJpanel.this.textNumber.getText();
                String value = this.getLimit(key, SpotBuyBaseJpanel.this.getGoodsNumber());
                if (value.indexOf((int)e.getKeyChar()) < 0) {
                    e.consume();
                    SpotBuyBaseJpanel.this.textNumber.setText(SpotBuyBaseJpanel.this.getGoodsNumber() + "");
                }
            }
            else {
                e.consume();
            }
        }
        
        @Override
        public void keyReleased(KeyEvent e) {
            if (SpotBuyBaseJpanel.this.commodity != null) {
                if (SpotBuyBaseJpanel.this.commodity.getType() == 0 || SpotBuyBaseJpanel.this.commodity.getType() == 3) {
                    String str = SpotBuyBaseJpanel.this.textNumber.getText();
                    if (str.length() > 0) {
                        if (str.charAt(0) == '0') {
                            SpotBuyBaseJpanel.this.textNumber.setText(Integer.valueOf(str).toString());
                        }
                        if (Integer.parseInt(str) >= SpotBuyBaseJpanel.this.getGoodsNumber()) {
                            SpotBuyBaseJpanel.this.textNumber.setText(SpotBuyBaseJpanel.this.getGoodsNumber() + "");
                        }
                    }
                }
                else {
                    SpotBuyBaseJpanel.this.textNumber.setText("1");
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
