package org.come.Jpanel;

import org.come.entity.Goodstable;
import org.come.until.GoodsListFromServerUntil;
import org.come.until.Util;
import java.awt.Graphics;
import com.tool.tcpimg.UIUtils;
import com.tool.btn.FormsOnOffBtn;
import java.awt.Dimension;
import com.updateNew.MyIsif;
import com.tool.btn.goodbtn;
import org.come.mouslisten.TransmuteMouslisten;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import java.awt.Color;
import javax.swing.DefaultListModel;
import javax.swing.JList;
import com.tool.btn.DazaoBtn;
import java.math.BigDecimal;
import javax.swing.JPanel;

public class ForgeJpanel extends JPanel
{
    private BigDecimal money1;
    private DazaoBtn btndazao;
    private JList<String> listEquipment;
    private DefaultListModel<String> modelTrade;
    private DefaultListModel<String> modelname;
    private Color fontcolor;
    private JLabel[] topTitle;
    private ImageIcon jiemianimg;
    private JLabel[] GoodsListLabel;
    private String type;
    private boolean shoptext;
    private int goodPosition;
    private TransmuteMouslisten[] transmuteMouslistens;
    private goodbtn[] btnrights;
    private int Flag;
    private int count;
    
    public ForgeJpanel() throws Exception {
        this.money1 = new BigDecimal(100000);
        this.GoodsListLabel = new JLabel[26];
        this.type = "我要炼器";
        this.shoptext = false;
        this.transmuteMouslistens = new TransmuteMouslisten[26];
        this.Flag = 0;
        this.count = 1;
        if (MyIsif.getStyle().equals("水墨")) {
            this.setPreferredSize(new Dimension(400, 400));
            this.setLayout(null);
            this.setOpaque(false);
            FormsOnOffBtn offBtn = new FormsOnOffBtn("inkImg/button/1.png", 1, 26);
            offBtn.setBounds(363, 10, 25, 25);
            this.add(offBtn);
            this.btnrights = new goodbtn[6];
            for (int i = 0; i < this.btnrights.length; ++i) {
                (this.btnrights[i] = new goodbtn("inkImg/button1/C0" + (i + 1) + ".png", 0, this, i)).setBounds(353, 174 + i * 35, 35, 31);
                this.add(this.btnrights[i]);
            }
            this.topTitle = new JLabel[2];
            for (int i = 0; i < this.topTitle.length; ++i) {
                (this.topTitle[i] = new JLabel()).setBounds(93 + i * 134, 15, 56 + i * 34, 20);
                this.topTitle[i].setHorizontalAlignment(0);
                this.topTitle[i].setFont(UIUtils.TEXT_FONT1);
                this.topTitle[i].setForeground(Color.white);
                this.topTitle[i].setText("1阶仙器");
                this.add(this.topTitle[i]);
            }
            for (int i = 0; i < 26; ++i) {
                this.GoodsListLabel[i] = new JLabel();
                this.transmuteMouslistens[i] = new TransmuteMouslisten(i, this);
                this.GoodsListLabel[i].addMouseListener(this.transmuteMouslistens[i]);
                if (i < 24) {
                    this.GoodsListLabel[i].setBounds(44 + i % 6 * 51, 175 + i / 6 * 51, 48, 48);
                    this.add(this.GoodsListLabel[i]);
                }
                else {
                    this.GoodsListLabel[i].setBounds(96 + i % 6 * 150, 40, 55, 51);
                    this.add(this.GoodsListLabel[i]);
                }
            }
            (this.btndazao = new DazaoBtn("inkImg/button1/B21.png", 1, UIUtils.COLOR_BLACK, "打造", UIUtils.TEXT_HY16, this)).setBounds(266, 123, 79, 24);
            this.add(this.btndazao);
        }
        else {
            this.setPreferredSize(new Dimension(360, 386));
            this.setLayout(null);
            this.setOpaque(false);
            FormsOnOffBtn offBtn = new FormsOnOffBtn("img/xy2uiimg/8_jpg.button.btn_close.jpg", 1, 26);
            offBtn.setBounds(340, 0, 23, 23);
            this.add(offBtn);
            this.btnrights = new goodbtn[6];
            for (int i = 0; i < this.btnrights.length; ++i) {
                (this.btnrights[i] = new goodbtn("inkImg/hongmu/SBG.png", 0, this, i)).setBounds(326, 151 + i * 33, 24, 33);
                this.add(this.btnrights[i]);
            }
            this.topTitle = new JLabel[2];
            for (int i = 0; i < this.topTitle.length; ++i) {
                (this.topTitle[i] = new JLabel()).setHorizontalAlignment(0);
                this.topTitle[i].setFont(UIUtils.TEXT_FONT1);
                this.topTitle[i].setForeground(Color.white);
                this.topTitle[i].setText("1阶仙器");
                this.add(this.topTitle[i]);
            }
            for (int i = 0; i < 26; ++i) {
                this.GoodsListLabel[i] = new JLabel();
                this.transmuteMouslistens[i] = new TransmuteMouslisten(i, this);
                this.GoodsListLabel[i].addMouseListener(this.transmuteMouslistens[i]);
                if (this.Flag < 6 && this.count == 1) {
                    this.GoodsListLabel[i].setBounds(19 + this.Flag * 51, 153, 49, 49);
                    ++this.Flag;
                    this.add(this.GoodsListLabel[i]);
                }
                if (this.Flag < 6 && this.count == 2) {
                    this.GoodsListLabel[i].setBounds(19 + this.Flag * 51, 205, 49, 49);
                    ++this.Flag;
                    this.add(this.GoodsListLabel[i]);
                }
                if (this.Flag < 6 && this.count == 3) {
                    this.GoodsListLabel[i].setBounds(19 + this.Flag * 51, 257, 49, 49);
                    ++this.Flag;
                    this.add(this.GoodsListLabel[i]);
                }
                if (this.Flag < 6 && this.count == 4) {
                    this.GoodsListLabel[i].setBounds(19 + this.Flag * 51, 309, 49, 49);
                    ++this.Flag;
                    this.add(this.GoodsListLabel[i]);
                }
                if (this.Flag < 2 && this.count == 5) {
                    this.GoodsListLabel[i].setBounds(43 + this.Flag * 93, 61, 65, 65);
                    ++this.Flag;
                    this.add(this.GoodsListLabel[i]);
                }
                else if (this.Flag == 6) {
                    this.Flag = 0;
                    ++this.count;
                }
            }
            (this.btndazao = new DazaoBtn("inkImg/hoNGMU/1_png.button.btn_xy.png", 1, UIUtils.COLOR_BTNPUTONG, "打造", UIUtils.TEXT_HY88, this)).setBounds(246, 123, 80, 24);
            this.add(this.btndazao);
        }
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (MyIsif.getStyle().equals("水墨")) {
            if (this.jiemianimg == null) {
                this.jiemianimg = new ImageIcon("inkImg/background1/B261.png");
            }
            g.drawImage(this.jiemianimg.getImage(), 0, 0, 400, 400, this);
            if (this.shoptext) {
                int shop_x = this.goodPosition % 6;
                int shop_y = this.goodPosition / 6;
                g.setColor(Color.red);
                g.drawLine(47 + shop_x * 51, 175 + shop_y * 51, 95 + shop_x * 51, 175 + shop_y * 51);
                g.drawLine(47 + shop_x * 51, 175 + shop_y * 51, 47 + shop_x * 51, 223 + shop_y * 51);
                g.drawLine(95 + shop_x * 51, 176 + shop_y * 51, 95 + shop_x * 51, 223 + shop_y * 51);
                g.drawLine(47 + shop_x * 51, 223 + shop_y * 51, 95 + shop_x * 51, 223 + shop_y * 51);
            }
            Util.drawMoney(g, 104, 128);
            Util.drawPrice(g, this.money1, 104, 162);
            GoodsListFromServerUntil.draw(g, 44, 174);
        }
        else {
            if (this.jiemianimg == null) {
                this.jiemianimg = new ImageIcon("img/xy2uiimg/108_png.xy2uiimg.equip.png");
            }
            g.drawImage(this.jiemianimg.getImage(), 0, 0, 360, 386, this);
            if (this.shoptext) {
                int shop_x = this.goodPosition % 6;
                int shop_y = this.goodPosition / 6;
                g.setColor(Color.red);
                g.drawLine(20 + shop_x * 51, 154 + shop_y * 51, 67 + shop_x * 51, 154 + shop_y * 51);
                g.drawLine(20 + shop_x * 51, 154 + shop_y * 51, 20 + shop_x * 51, 201 + shop_y * 51);
                g.drawLine(67 + shop_x * 51, 154 + shop_y * 51, 67 + shop_x * 51, 201 + shop_y * 51);
                g.drawLine(20 + shop_x * 51, 201 + shop_y * 51, 67 + shop_x * 51, 201 + shop_y * 51);
            }
            Util.drawMoney(g, 239, 104);
            Util.drawPrice(g, this.money1, 239, 63);
            GoodsListFromServerUntil.draw(g, 19, 153);
        }
    }
    
    public void PaintingText(int goodPosition) {
        this.goodPosition = goodPosition;
        this.shoptext = true;
    }
    
    public void ClearText() {
        this.shoptext = false;
    }
    
    public void remove() {
        TransmuteMouslisten.goods2[0] = null;
        TransmuteMouslisten.goods2[1] = null;
        this.GoodsListLabel[24].setIcon(null);
        this.GoodsListLabel[25].setIcon(null);
    }
    
    public JList<String> getListEquipment() {
        return this.listEquipment;
    }
    
    public void setListEquipment(JList<String> listEquipment) {
        this.listEquipment = listEquipment;
    }
    
    public DefaultListModel<String> getModelTrade() {
        return this.modelTrade;
    }
    
    public void setModelTrade(DefaultListModel<String> modelTrade) {
        this.modelTrade = modelTrade;
    }
    
    public DefaultListModel<String> getModelname() {
        return this.modelname;
    }
    
    public void setModelname(DefaultListModel<String> modelname) {
        this.modelname = modelname;
    }
    
    public Color getFontcolor() {
        return this.fontcolor;
    }
    
    public void setFontcolor(Color fontcolor) {
        this.fontcolor = fontcolor;
    }
    
    public JLabel[] getGoodsListLabel() {
        return this.GoodsListLabel;
    }
    
    public void setGoodsListLabel(JLabel[] goodsListLabel) {
        this.GoodsListLabel = goodsListLabel;
    }
    
    public int getFlag() {
        return this.Flag;
    }
    
    public void setFlag(int flag) {
        this.Flag = flag;
    }
    
    public int getCount() {
        return this.count;
    }
    
    public void setCount(int count) {
        this.count = count;
    }
    
    public boolean isShoptext() {
        return this.shoptext;
    }
    
    public void setShoptext(boolean shoptext) {
        this.shoptext = shoptext;
    }
    
    public int getGoodPosition() {
        return this.goodPosition;
    }
    
    public void setGoodPosition(int goodPosition) {
        this.goodPosition = goodPosition;
    }
    
    public TransmuteMouslisten[] getTransmuteMouslistens() {
        return this.transmuteMouslistens;
    }
    
    public void setTransmuteMouslistens(TransmuteMouslisten[] transmuteMouslistens) {
        this.transmuteMouslistens = transmuteMouslistens;
    }
    
    public String getType() {
        return this.type;
    }
    
    public void setType(String type) {
        this.type = type;
    }
    
    public DazaoBtn getBtndazao() {
        return this.btndazao;
    }
    
    public void setBtndazao(DazaoBtn btndazao) {
        this.btndazao = btndazao;
    }
    
    public ImageIcon getJiemianimg() {
        return this.jiemianimg;
    }
    
    public void setJiemianimg(ImageIcon jiemianimg) {
        this.jiemianimg = jiemianimg;
    }
    
    public goodbtn[] getBtnrights() {
        return this.btnrights;
    }
    
    public void setBtnrights(goodbtn[] btnrights) {
        this.btnrights = btnrights;
    }
    
    public BigDecimal getMoney1() {
        return this.money1;
    }
    
    public void setMoney1(BigDecimal money1) {
        this.money1 = money1;
    }
    
    public JLabel[] getTopTitle() {
        return this.topTitle;
    }
    
    public void setTopTitle(JLabel[] topTitle) {
        this.topTitle = topTitle;
    }
}
