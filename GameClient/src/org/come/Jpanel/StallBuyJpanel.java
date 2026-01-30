package org.come.Jpanel;

import org.come.until.GoodsListFromServerUntil;
import org.come.until.FormsManagement;
import javax.swing.Icon;
import com.tool.Stall.StallBean;
import com.tool.Stall.Commodity;
import org.come.until.Util;
import java.awt.LayoutManager;
import org.come.mouslisten.CancelStallMouslisten;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.BorderFactory;
import java.awt.Font;
import java.awt.Color;
import java.awt.Graphics;
import javax.swing.GrayFilter;
import java.awt.Image;
import com.tool.btn.FormsOnOffBtn;
import com.tool.tcpimg.UIUtils;
import java.awt.Dimension;
import com.updateNew.MyIsif;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import com.tool.btn.StallBuyBtn;
import java.math.BigDecimal;
import javax.swing.JTextField;
import com.tool.Stall.Stall;
import javax.swing.JPanel;

public class StallBuyJpanel extends JPanel
{
    private int id;
    private String type;
    private Stall stall;
    private JTextField textNumber;
    private String Boothname;
    private BigDecimal GoodsPrice;
    private BigDecimal GoodsTotalPrice;
    private StallBuyBtn btnBuy;
    public JLabel[] GoodsListLabel;
    public String[] Usetime;
    public JLabel[] PetsListLabel;
    final ImageIcon imageIcon;
    ImageIcon icon;
    
    public StallBuyJpanel() throws Exception {
        this.type = "金币";
        this.GoodsListLabel = new JLabel[24];
        this.Usetime = new String[24];
        this.PetsListLabel = new JLabel[5];
        this.imageIcon = new ImageIcon("img/123_副本.png");
        if (MyIsif.getStyle().equals("水墨")) {
            this.setPreferredSize(new Dimension(418, 489));
            this.setLayout(null);
            this.setBackground(UIUtils.Color_BACK);
            this.GoodsPrice = new BigDecimal(0);
            this.GoodsTotalPrice = new BigDecimal(0);
            FormsOnOffBtn offBtn = new FormsOnOffBtn("inkImg/button/1.png", 1, 35);
            offBtn.setBounds(381, 10, 25, 25);
            this.add(offBtn);
            (this.textNumber = new JTextField() {
                Image image = StallBuyJpanel.this.imageIcon.getImage();
                Image grayImage = GrayFilter.createDisabledImage(this.image);
                
                {
                    this.setOpaque(false);
                }
                
                @Override
                public void paint(Graphics g) {
                    g.drawImage(this.grayImage, 0, 0, this);
                    super.paint(g);
                }
            }).setBounds(90, 396, 153, 16);
            this.textNumber.setText("0");
            this.textNumber.setForeground(Color.WHITE);
            this.textNumber.setFont(new Font("微软雅黑", 0, 14));
            this.textNumber.setBackground(UIUtils.Color_BACK);
            this.textNumber.setCaretColor(Color.white);
            this.textNumber.setBorder(BorderFactory.createEmptyBorder());
            this.textNumber.getDocument().addDocumentListener(new DocumentListener() {
                @Override
                public void removeUpdate(DocumentEvent e) {
                    String s = StallBuyJpanel.this.textNumber.getText();
                    try {
                        if (StallBuyJpanel.this.btnBuy.getCommodity() != null) {
                            if (StallBuyJpanel.this.btnBuy.getCommodity().getGood() != null) {
                                BigDecimal number = new BigDecimal(s);
                                StallBuyJpanel.this.GoodsTotalPrice = StallBuyJpanel.this.GoodsPrice.multiply(number);
                            }
                            else if (StallBuyJpanel.this.btnBuy.getCommodity().getPet() != null) {
                                StallBuyJpanel.this.GoodsTotalPrice = StallBuyJpanel.this.GoodsPrice;
                            }
                        }
                        else {
                            StallBuyJpanel.this.GoodsTotalPrice = new BigDecimal(0);
                            StallBuyJpanel.this.GoodsPrice = StallBuyJpanel.this.GoodsTotalPrice;
                        }
                    }
                    catch (NumberFormatException ex) {}
                }
                
                @Override
                public void insertUpdate(DocumentEvent e) {
                    String s = StallBuyJpanel.this.textNumber.getText();
                    try {
                        if (StallBuyJpanel.this.btnBuy.getCommodity() != null) {
                            if (StallBuyJpanel.this.btnBuy.getCommodity().getGood() != null) {
                                BigDecimal number = new BigDecimal(s);
                                StallBuyJpanel.this.GoodsTotalPrice = StallBuyJpanel.this.GoodsPrice.multiply(number);
                            }
                            else if (StallBuyJpanel.this.btnBuy.getCommodity().getPet() != null) {
                                StallBuyJpanel.this.GoodsTotalPrice = StallBuyJpanel.this.GoodsPrice;
                            }
                        }
                        else {
                            StallBuyJpanel.this.GoodsTotalPrice = new BigDecimal(0);
                            StallBuyJpanel.this.GoodsPrice = StallBuyJpanel.this.GoodsTotalPrice;
                        }
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
                    int keyChar = e.getKeyChar();
                    if (keyChar < 48 || keyChar > 57) {
                        e.consume();
                        return;
                    }
                    if (StallBuyJpanel.this.btnBuy.getCommodity() == null) {
                        e.consume();
                        return;
                    }
                    if (StallBuyJpanel.this.btnBuy.getCommodity().getPet() != null) {
                        e.consume();
                        return;
                    }
                    if (StallBuyJpanel.this.btnBuy.getCommodity().getGood() != null) {
                        String key = StallBuyJpanel.this.textNumber.getText();
                        String value = StallBuyJpanel.this.getLimit(key, (int)StallBuyJpanel.this.btnBuy.getCommodity().getGood().getUsetime());
                        if (value.indexOf((int)e.getKeyChar()) < 0) {
                            e.consume();
                        }
                    }
                }
                
                @Override
                public void keyReleased(KeyEvent e) {
                }
                
                @Override
                public void keyPressed(KeyEvent e) {
                }
            });
            this.add(this.textNumber);
            int Flag = 0;
            int count = 1;
            for (int i = 0; i < 24; ++i) {
                Flag = i % 6;
                count = i / 6;
                (this.GoodsListLabel[i] = new JLabel()).addMouseListener(new CancelStallMouslisten(i, 0, this));
                this.GoodsListLabel[i].setBounds(48 + Flag * 51, 71 + 51 * count, 48, 48);
                this.add(this.GoodsListLabel[i]);
            }
            for (int j = 0; j < 5; ++j) {
                (this.PetsListLabel[j] = new JLabel()).addMouseListener(new CancelStallMouslisten(j, 1, this));
                this.PetsListLabel[j].setBounds(50 + j * 63, 305, 50, 51);
                this.add(this.PetsListLabel[j]);
            }
            (this.btnBuy = new StallBuyBtn("inkImg/button1/B20.png", 1, UIUtils.COLOR_BLACK, UIUtils.TEXT_HY16, "购买", this)).setBounds(290, 407, 59, 24);
            this.add(this.btnBuy);
        }
        else {
            this.setPreferredSize(new Dimension(378, 477));
            this.setLayout((LayoutManager)null);
            this.setBackground(UIUtils.Color_BACK);
            this.GoodsPrice = new BigDecimal(0);
            this.GoodsTotalPrice = new BigDecimal(0);
            FormsOnOffBtn offBtn = new FormsOnOffBtn("inkImg/hongmu/S74.png", 1, 35);
            offBtn.setBounds(353, 0, 25, 25);
            this.add(offBtn);
            (this.textNumber = new JTextField() {
                Image image = StallBuyJpanel.this.imageIcon.getImage();
                Image grayImage = GrayFilter.createDisabledImage(this.image);
                
                {
                    this.setOpaque(false);
                }
                
                @Override
                public void paint(Graphics g) {
                    g.drawImage(this.grayImage, 0, 0, this);
                    super.paint(g);
                }
            }).setBounds(67, 393, 153, 16);
            this.textNumber.setText("0");
            this.textNumber.setForeground(Color.WHITE);
            this.textNumber.setFont(new Font("微软雅黑", 0, 14));
            this.textNumber.setBackground(UIUtils.Color_BACK);
            this.textNumber.setCaretColor(Color.white);
            this.textNumber.setBorder(BorderFactory.createEmptyBorder());
            this.textNumber.getDocument().addDocumentListener(new DocumentListener() {
                @Override
                public void removeUpdate(DocumentEvent e) {
                    String s = StallBuyJpanel.this.textNumber.getText();
                    try {
                        if (StallBuyJpanel.this.btnBuy.getCommodity() != null) {
                            if (StallBuyJpanel.this.btnBuy.getCommodity().getGood() != null) {
                                BigDecimal number = new BigDecimal(s);
                                StallBuyJpanel.this.GoodsTotalPrice = StallBuyJpanel.this.GoodsPrice.multiply(number);
                            }
                            else if (StallBuyJpanel.this.btnBuy.getCommodity().getPet() != null) {
                                StallBuyJpanel.this.GoodsTotalPrice = StallBuyJpanel.this.GoodsPrice;
                            }
                        }
                        else {
                            StallBuyJpanel.this.GoodsTotalPrice = new BigDecimal(0);
                            StallBuyJpanel.this.GoodsPrice = StallBuyJpanel.this.GoodsTotalPrice;
                        }
                    }
                    catch (NumberFormatException ex) {}
                }
                
                @Override
                public void insertUpdate(DocumentEvent e) {
                    String s = StallBuyJpanel.this.textNumber.getText();
                    try {
                        if (StallBuyJpanel.this.btnBuy.getCommodity() != null) {
                            if (StallBuyJpanel.this.btnBuy.getCommodity().getGood() != null) {
                                BigDecimal number = new BigDecimal(s);
                                StallBuyJpanel.this.GoodsTotalPrice = StallBuyJpanel.this.GoodsPrice.multiply(number);
                            }
                            else if (StallBuyJpanel.this.btnBuy.getCommodity().getPet() != null) {
                                StallBuyJpanel.this.GoodsTotalPrice = StallBuyJpanel.this.GoodsPrice;
                            }
                        }
                        else {
                            StallBuyJpanel.this.GoodsTotalPrice = new BigDecimal(0);
                            StallBuyJpanel.this.GoodsPrice = StallBuyJpanel.this.GoodsTotalPrice;
                        }
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
                    int keyChar = e.getKeyChar();
                    if (keyChar >= 48 && keyChar <= 57) {
                        if (StallBuyJpanel.this.btnBuy.getCommodity() == null) {
                            e.consume();
                        }
                        else if (StallBuyJpanel.this.btnBuy.getCommodity().getPet() != null) {
                            e.consume();
                        }
                        else if (StallBuyJpanel.this.btnBuy.getCommodity().getGood() != null) {
                            String key = StallBuyJpanel.this.textNumber.getText();
                            String value = StallBuyJpanel.this.getLimit(key, (int)StallBuyJpanel.this.btnBuy.getCommodity().getGood().getUsetime());
                            if (value.indexOf((int)e.getKeyChar()) < 0) {
                                e.consume();
                            }
                        }
                    }
                    else {
                        e.consume();
                    }
                }
                
                @Override
                public void keyReleased(KeyEvent e) {
                }
                
                @Override
                public void keyPressed(KeyEvent e) {
                }
            });
            this.add(this.textNumber);
            boolean Flag2 = false;
            boolean count2 = true;
            for (int j = 0; j < 24; ++j) {
                int Flag3 = j % 6;
                int count3 = j / 6;
                (this.GoodsListLabel[j] = new JLabel()).addMouseListener(new CancelStallMouslisten(j, 0, this));
                this.GoodsListLabel[j].setBounds(21 + Flag3 * 56, 45 + 58 * count3, 48, 48);
                this.add(this.GoodsListLabel[j]);
            }
            for (int j = 0; j < 5; ++j) {
                (this.PetsListLabel[j] = new JLabel()).addMouseListener(new CancelStallMouslisten(j, 1, this));
                this.PetsListLabel[j].setBounds(25 + j * 69, 285, 50, 51);
                this.add(this.PetsListLabel[j]);
            }
            (this.btnBuy = new StallBuyBtn("img/xy2uiimg/1_png.button.btn_xy.png", 1, UIUtils.COLOR_BTNPUTONG, UIUtils.TEXT_HY16, "购买", this)).setBounds(267, 407, 60, 26);
            this.add(this.btnBuy);
        }
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        if (MyIsif.getStyle().equals("水墨")) {
            super.paintComponent(g);
            if (this.icon == null) {
                this.icon = new ImageIcon("inkImg/background1/B249.png");
            }
            g.drawImage(this.icon.getImage(), 0, 0, 418, 489, this);
            g.setFont(UIUtils.TEXT_MSG);
            g.setColor(Color.CYAN);
            if (this.Boothname != null) {
                g.drawString(this.Boothname, 100, 46);
            }
            g.setColor(Color.white);
            for (int i = 0; i < this.Usetime.length; ++i) {
                int row = i % 6;
                int col = i / 6;
                if (this.Usetime[i] != null) {
                    g.drawString(this.Usetime[i], 48 + row * 51, 84 + col * 51);
                }
            }
            Util.drawMoney(g, 90, 459);
            Util.drawPrice(g, this.GoodsPrice, 90, 384);
            Util.drawPrice(g, this.GoodsTotalPrice, 90, 434);
        }
        else {
            super.paintComponent(g);
            if (this.icon == null) {
                if (this.type.equals("积分")) {
                    this.icon = new ImageIcon("Img/xy2uiimg/30_png.xy2uiimg.boothpanel.png");
                }
                else {
                    this.icon = new ImageIcon("Img/xy2uiimg/31_png.xy2uiimg.boothpanel.png");
                }
            }
            g.drawImage(this.icon.getImage(), 0, 0, 378, 477, this);
            g.setFont(UIUtils.TEXT_MSG);
            g.setColor(Color.yellow);
            if (this.Boothname != null) {
                g.drawString(this.Boothname, 160, 23);
            }
            g.setColor(Color.white);
            for (int i = 0; i < this.Usetime.length; ++i) {
                int row = i % 6;
                int col = i / 6;
                if (this.Usetime[i] != null) {
                    g.drawString(this.Usetime[i], 25 + row * 55, 51 + col * 58);
                }
            }
            if (this.type.equals("积分")) {
                Util.drawMoneyJF(g, 242, 377);
            }
            else {
                Util.drawMoney(g, 242, 377);
            }
            Util.drawPrice(g, this.GoodsPrice, 67, 377);
            Util.drawPrice(g, this.GoodsTotalPrice, 67, 439);
        }
    }
    
    public JTextField getTextNumber() {
        return this.textNumber;
    }
    
    public void setTextNumber(JTextField textNumber) {
        this.textNumber = textNumber;
    }
    
    public BigDecimal getGoodsPrice() {
        return this.GoodsPrice;
    }
    
    public void setGoodsPrice(BigDecimal goodsPrice) {
        this.GoodsPrice = goodsPrice;
    }
    
    public BigDecimal getGoodsTotalPrice() {
        return this.GoodsTotalPrice;
    }
    
    public void setGoodsTotalPrice(BigDecimal goodsTotalPrice) {
        this.GoodsTotalPrice = goodsTotalPrice;
    }
    
    public void reset(Commodity commodity) {
        this.btnBuy.setCommodity(commodity);
        this.textNumber.setText("1");
        this.GoodsPrice = new BigDecimal(commodity.getMoney());
        this.GoodsTotalPrice = this.GoodsPrice;
    }
    
    public void no(StallBean bean) {
        if (this.id == bean.getId()) {
            for (int i = 0; i < this.GoodsListLabel.length; ++i) {
                this.GoodsListLabel[i].setIcon((Icon)null);
                this.Usetime[i] = null;
            }
            for (int i = 0; i < this.PetsListLabel.length; ++i) {
                this.PetsListLabel[i].setIcon((Icon)null);
            }
            this.Boothname = "";
            FormsManagement.HideForm(35);
        }
    }
    
    public void initstall(Stall stall) {
        this.id = stall.getId();
        this.stall = stall;
        this.Boothname = stall.getStall();
        for (int i = 0; i < stall.getGoodstables().length; ++i) {
            Commodity commodity = stall.getGoodstables()[i];
            if (commodity == null) {
                this.GoodsListLabel[i].setIcon((Icon)null);
                this.Usetime[i] = null;
            }
            else {
                ImageIcon img = GoodsListFromServerUntil.imgpathAdaptive(commodity.getGood().getSkin(), 49, 49);
                this.GoodsListLabel[i].setIcon(img);
                this.Usetime[i] = commodity.getGood().getUsetime().toString();
            }
        }
        for (int i = 0; i < stall.getPets().length; ++i) {
            Commodity commodity = stall.getPets()[i];
            if (commodity == null) {
                this.PetsListLabel[i].setIcon((Icon)null);
            }
            else {
                ImageIcon img = new ImageIcon("img/xy2uiimg/101_png.xy2uiimg.pet_def.png");
                img.setImage(img.getImage().getScaledInstance(49, 49, 10));
                this.PetsListLabel[i].setIcon(img);
            }
        }
    }
    
    private String getLimit(String key, int num) {
        String value = String.valueOf(num);
        int[] a = new int[13];
        int b = num;
        int len = key.length();
        int length = value.length();
        int in = 0;
        while (b > 0) {
            a[length - 1 - in] = b % 10;
            ++in;
            b /= 10;
        }
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
    
    public int getId() {
        return this.id;
    }
    
    public void setId(int id) {
        this.id = id;
    }
    
    public Stall getStall() {
        return this.stall;
    }
    
    public void setStall(Stall stall) {
        this.stall = stall;
    }
}
