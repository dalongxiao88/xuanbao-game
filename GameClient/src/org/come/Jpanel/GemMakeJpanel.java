package org.come.Jpanel;

import org.come.Frame.ZhuFrame;
import java.awt.event.MouseEvent;
import java.awt.event.MouseAdapter;
import org.come.until.AccessSuitMsgUntil;
import java.awt.Component;
import org.come.until.Goodtype;
import java.util.ArrayList;
import org.come.until.GoodsListFromServerUntil;
import org.come.until.Util;
import java.awt.Graphics;
import javax.swing.BorderFactory;
import org.come.entity.Goodstable;
import org.come.until.CutButtonImage;
import java.awt.Color;
import org.cbg.until.TraslationTableZhaohuanshouUntil;
import com.tool.tcpimg.UIUtils;
import com.tool.btn.FormsOnOffBtn;
import java.awt.Dimension;
import com.updateNew.MyIsif;
import java.math.BigDecimal;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import com.tool.btn.GemMakeBtn;
import org.gemstone.panel.GemMakeTabPanel;
import java.util.List;
import javax.swing.JScrollPane;
import javax.swing.JPanel;

public class GemMakeJpanel extends JPanel
{
    private JScrollPane jScrollPane;
    private JPanel jPanel;
    private List<GemMakeTabPanel> lists;
    private GemMakeBtn equipBtn;
    private GemMakeBtn otherBtn;
    private GemMakeBtn dazaoBtn;
    private JLabel[] makeLabel;
    private GemMakeBtn[] options;
    private GemX[] gemXs;
    private GemX gemX;
    private JLabel essence;
    private JLabel essenceLab;
    private GemMakeBtn upBtn;
    private GemMakeBtn downBtn;
    private int yss;
    private static int xz;
    private boolean shoptext;
    private int goodPosition;
    public static ImageIcon iconx2;
    private ImageIcon icon;
    private ImageIcon iconGun;
    private BigDecimal b;
    private int boo;
    
    public GemMakeJpanel() {
        this.b = new BigDecimal("5000000");
        this.boo = 4;
        this.shoptext = false;
        this.yss = 1;
        if (MyIsif.getStyle().equals("水墨")) {
            this.setPreferredSize(new Dimension(552, 375));
            this.setOpaque(false);
            this.setLayout(null);
            FormsOnOffBtn offBtn = new FormsOnOffBtn("inkImg/button/1.png", 1, 84);
            offBtn.setBounds(515, 10, 25, 25);
            this.add(offBtn);
            (this.upBtn = new GemMakeBtn("inkimg/button/B42.png", 1, 20, this)).setBounds(502, 287, 18, 18);
            this.add(this.upBtn);
            (this.downBtn = new GemMakeBtn("inkimg/button/B43.png", 1, 21, this)).setBounds(502, 312, 18, 18);
            this.add(this.downBtn);
            this.options = new GemMakeBtn[6];
            for (int i = 0; i < this.options.length; ++i) {
                String aaa = (i == 0) ? "全部" : ((i == 1) ? "武器" : ((i == 2) ? "衣服" : ((i == 3) ? "帽子" : ((i == 4) ? "项链" : "鞋子"))));
                (this.options[i] = new GemMakeBtn("inkImg/button/49.png", 1, UIUtils.COLOR_BTNTEXT, aaa, UIUtils.TEXT_FONT, 2 + i, this)).setBounds(147, 48 + i * 17, 68, 17);
                this.options[i].setVisible(false);
                this.add(this.options[i]);
            }
            (this.equipBtn = new GemMakeBtn("inkImg/button1/K60.png", 1, 0, this)).setBounds(46, 14, 75, 33);
            this.add(this.equipBtn);
            (this.otherBtn = new GemMakeBtn("inkImg/button1/K61.png", 1, 1, this)).setBounds(124, 14, 75, 33);
            this.add(this.otherBtn);
            (this.dazaoBtn = new GemMakeBtn("inkImg/button1/B20.png", 1, UIUtils.COLOR_BLACK, "打造", UIUtils.TEXT_HY16, 8, this)).setBounds(352, 248, 59, 24);
            this.add(this.dazaoBtn);
            (this.jScrollPane = new JScrollPane()).setBounds(55, 60, 182, 295);
            this.jPanel = TraslationTableZhaohuanshouUntil.TableModel(this.jScrollPane);
            this.jScrollPane.getVerticalScrollBar().setUnitIncrement(30);
            this.add(this.jScrollPane);
            this.makeLabel = new JLabel[5];
            for (int i = 0; i < this.makeLabel.length; ++i) {
                (this.makeLabel[i] = new JLabel()).setBounds(247 + i * 51, 284, 50, 50);
                this.makeLabel[i].setOpaque(false);
                MListener ml = new MListener(i);
                this.makeLabel[i].addMouseListener(ml);
                this.add(this.makeLabel[i]);
            }
            this.gemXs = new GemX[3];
            for (int i = 0; i < this.gemXs.length; ++i) {
                JLabel jLabel = new JLabel("", 0);
                jLabel.setFont(UIUtils.TEXT_FONT);
                jLabel.setForeground(Color.white);
                jLabel.setVerticalTextPosition(0);
                jLabel.setHorizontalTextPosition(0);
                jLabel.setBounds(331 + i * 57, 69, 47, 47);
                this.add(jLabel);
                JLabel jLabel2 = null;
                if (i == 2) {
                    jLabel2 = new JLabel();
                    jLabel2.setIcon(new ImageIcon("inkImg/background/8.png"));
                    jLabel2.setBounds(443, 67, 51, 51);
                    this.add(jLabel2);
                }
                GemMakeBtn gemMakeBtn = new GemMakeBtn("inkImg/button/34.png", 0, UIUtils.COLOR_WHITE, "摘除", UIUtils.TEXT_FONT10, 9 + i, this);
                gemMakeBtn.setBounds(332 + i * 57, 120, 51, 17);
                this.add(gemMakeBtn);
                this.gemXs[i] = new GemX(jLabel, jLabel2, gemMakeBtn);
            }
            JLabel jLabel3 = new JLabel();
            jLabel3.setBounds(261, 72, 49, 49);
            this.add(jLabel3);
            this.gemX = new GemX(jLabel3, null, null);
            (this.essence = new JLabel()).setBounds(350, 155, 27, 27);
            this.essence.setIcon(CutButtonImage.getGemstoneIcon("744", 27, 27));
            this.add(this.essence);
            (this.essenceLab = new JLabel()).setBounds(398, 164, 80, 14);
            this.essenceLab.setFont(UIUtils.TEXT_FONT1);
            this.essenceLab.setForeground(Color.white);
            this.add(this.essenceLab);
        }
        else {
            this.setPreferredSize(new Dimension(552, 375));
            this.setOpaque(false);
            this.setLayout(null);
            FormsOnOffBtn offBtn = new FormsOnOffBtn("inkImg/hongmu/s74.png", 1, 84);
            offBtn.setBounds(527, 1, 25, 25);
            this.add(offBtn);
            (this.upBtn = new GemMakeBtn("inkimg/hongmu/24.png", 1, 20, this)).setBounds(502, 282, 19, 20);
            this.add(this.upBtn);
            (this.downBtn = new GemMakeBtn("inkimg/hongmu/25.png", 1, 21, this)).setBounds(502, 307, 19, 20);
            this.add(this.downBtn);
            this.options = new GemMakeBtn[6];
            for (int i = 0; i < this.options.length; ++i) {
                String aaa = (i == 0) ? "全部" : ((i == 1) ? "武器" : ((i == 2) ? "衣服" : ((i == 3) ? "帽子" : ((i == 4) ? "项链" : "鞋子"))));
                (this.options[i] = new GemMakeBtn("img/gemstone/" + aaa + "w68,h51px.png", 1, 2 + i, this)).setBounds(115, 53 + i * 17, 68, 17);
                this.options[i].setVisible(false);
                this.add(this.options[i]);
            }
            (this.equipBtn = new GemMakeBtn("img/gemstone/已装备w63,h78px.png", 1, 0, this)).setBounds(46, 24, 70, 35);
            this.add(this.equipBtn);
            (this.otherBtn = new GemMakeBtn("img/gemstone/其他（未来）w63,h78px.png", 1, 1, this)).setBounds(118, 24, 70, 35);
            this.add(this.otherBtn);
            (this.dazaoBtn = new GemMakeBtn("inkImg/hoNGMU/1_png.button.btn_xy.png", 1, UIUtils.COLOR_BTNPUTONG, "打造", UIUtils.TEXT_HY16, 8, this)).setBounds(352, 248, 85, 26);
            this.add(this.dazaoBtn);
            (this.jScrollPane = new JScrollPane()).setBounds(55, 60, 182, 295);
            this.jPanel = TraslationTableZhaohuanshouUntil.TableModel(this.jScrollPane);
            this.jScrollPane.getVerticalScrollBar().setUnitIncrement(30);
            this.add(this.jScrollPane);
            this.makeLabel = new JLabel[5];
            for (int i = 0; i < this.makeLabel.length; ++i) {
                (this.makeLabel[i] = new JLabel()).setBounds(247 + i * 51, 284, 50, 50);
                this.makeLabel[i].setOpaque(false);
                MListener ml = new MListener(i);
                this.makeLabel[i].addMouseListener(ml);
                this.add(this.makeLabel[i]);
            }
            this.gemXs = new GemX[3];
            for (int i = 0; i < this.gemXs.length; ++i) {
                JLabel jLabel = new JLabel("", 0);
                jLabel.setFont(UIUtils.TEXT_FONT);
                jLabel.setForeground(Color.white);
                jLabel.setVerticalTextPosition(0);
                jLabel.setHorizontalTextPosition(0);
                jLabel.setBounds(322 + i * 60, 76, 47, 47);
                this.add(jLabel);
                JLabel jLabel2 = null;
                if (i == 2) {
                    jLabel2 = new JLabel();
                    jLabel2.setIcon(new ImageIcon("img/gemstone/方框w51,h51px.png"));
                    jLabel2.setBounds(440, 73, 51, 51);
                    this.add(jLabel2);
                }
                GemMakeBtn gemMakeBtn = new GemMakeBtn("img/gemstone/摘除w51,h51px.png", 0, UIUtils.COLOR_BTNTEXT, "摘除", UIUtils.TEXT_FONT, 9 + i, this);
                gemMakeBtn.setBounds(320 + i * 60, 120, 51, 17);
                this.add(gemMakeBtn);
                this.gemXs[i] = new GemX(jLabel, jLabel2, gemMakeBtn);
            }
            JLabel jLabel3 = new JLabel();
            jLabel3.setBounds(250, 78, 49, 49);
            this.add(jLabel3);
            this.gemX = new GemX(jLabel3, null, null);
            (this.essence = new JLabel()).setBounds(358, 155, 27, 27);
            this.essence.setIcon(CutButtonImage.getGemstoneIcon("744", 27, 27));
            this.add(this.essence);
            (this.essenceLab = new JLabel()).setBounds(398, 164, 80, 14);
            this.essenceLab.setFont(UIUtils.TEXT_FONT1);
            this.essenceLab.setForeground(Color.white);
            this.add(this.essenceLab);
        }
    }
    
    public void lingFan(boolean type) {
        if (type) {
            if (this.yss + 1 < this.makeLabel.length && this.makeLabel[this.yss + 1] != null) {
                this.lingNumChange(this.yss + 1);
            }
        }
        else if (this.yss > 0) {
            this.lingNumChange(this.yss - 1);
        }
    }
    
    public void lingNumChange(int number) {
        this.yss = number;
    }
    
    public void xuanzhong(Goodstable goodstable, int shopPlace) {
        if (goodstable == null) {
            if (GemMakeJpanel.xz != -1) {
                this.makeLabel[GemMakeJpanel.xz].setBorder(null);
            }
            GemMakeJpanel.xz = -1;
        }
        else {
            if (GemMakeJpanel.xz != -1) {
                this.makeLabel[GemMakeJpanel.xz].setBorder(null);
            }
            GemMakeJpanel.xz = shopPlace;
            this.makeLabel[GemMakeJpanel.xz].setBorder(BorderFactory.createLineBorder(Color.red));
        }
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (MyIsif.getStyle().equals("水墨")) {
            if (this.icon == null) {
                this.icon = new ImageIcon("inkImg/background1/B268.png");
            }
            g.drawImage(this.icon.getImage(), 0, 0, 552, 375, this);
            if (this.iconGun == null) {
                this.iconGun = CutButtonImage.getImage("inkImg/background/S43.png", -1, -1);
            }
            g.drawImage(this.iconGun.getImage(), 44, 58, 0, 0, this);
            Util.drawMoney(g, 350, 226);
            Util.drawPrice(g, this.b, 350, 201);
            GoodsListFromServerUntil.drawGemstoneSuit(g, this.yss, 0, this.boo);
            if (this.shoptext) {
                int shop_x = this.goodPosition % 5;
                int shop_y = this.goodPosition / 5;
                g.drawImage(GemMakeJpanel.iconx2.getImage(), 224 + shop_x * 51, 297 + shop_y * 51, this);
            }
        }
        else {
            if (this.icon == null) {
                this.icon = new ImageIcon("img/gemstone/宝石打造w526,h397.png");
            }
            g.drawImage(this.icon.getImage(), 0, 0, 552, 378, this);
            if (this.iconGun == null) {
                this.iconGun = CutButtonImage.getImage("img/gemstone/宝石打造-其他w180,h300,left25,top58px.png", -1, -1);
            }
            g.drawImage(this.iconGun.getImage(), 30, 50, 180, 300, this);
            Util.drawMoney(g, 360, 228);
            Util.drawPrice(g, this.b, 360, 205);
            GoodsListFromServerUntil.drawGemstoneSuit(g, this.yss, 0, this.boo);
            if (this.shoptext) {
                int shop_x = this.goodPosition % 5;
                int shop_y = this.goodPosition / 5;
                g.drawImage(GemMakeJpanel.iconx2.getImage(), 224 + shop_x * 51, 297 + shop_y * 51, this);
            }
        }
    }
    
    public void qh(int p) {
        if (MyIsif.getStyle().equals("水墨")) {
            if (p == 0) {
                try {
                    this.equipBtn.setIcons(CutButtonImage.cuts("inkImg/button1/K60.png"));
                    this.otherBtn.setIcons(CutButtonImage.cuts("inkImg/button1/K61.png"));
                }
                catch (Exception e) {
                    e.printStackTrace();
                }
                this.jScrollPane.setBounds(45, 70, 180, 272);
                this.iconGun = CutButtonImage.getImage("inkImg/background/S43.png", -1, -1);
                this.chang(this.option(-2));
            }
            else {
                try {
                    this.equipBtn.setIcons(CutButtonImage.cuts("inkImg/button1/K59.png"));
                    this.otherBtn.setIcons(CutButtonImage.cuts("inkImg/button1/K62.png"));
                }
                catch (Exception e) {
                    e.printStackTrace();
                }
                this.jScrollPane.setBounds(45, 70, 180, 272);
                this.iconGun = CutButtonImage.getImage("inkImg/background/S42.png", -1, -1);
                this.chang(this.option(-1));
            }
        }
        else if (p == 0) {
            try {
                this.equipBtn.setIcons(CutButtonImage.cuts("img/gemstone/已装备w63,h78px.png"));
                this.otherBtn.setIcons(CutButtonImage.cuts("img/gemstone/其他（未来）w63,h78px.png"));
            }
            catch (Exception e) {
                e.printStackTrace();
            }
            this.jScrollPane.setBounds(35, 68, 172, 295);
            this.iconGun = CutButtonImage.getImage("img/gemstone/宝石打造-其他w180,h300,left25,top58px.png", -1, -1);
            this.chang(this.option(-2));
        }
        else {
            try {
                this.equipBtn.setIcons(CutButtonImage.cuts("img/gemstone/已装备（未）w63,h78px.png"));
                this.otherBtn.setIcons(CutButtonImage.cuts("img/gemstone/其他w63,h78px.png"));
            }
            catch (Exception e) {
                e.printStackTrace();
            }
            this.jScrollPane.setBounds(35, 68, 172, 295);
            this.iconGun = CutButtonImage.getImage("img/gemstone/宝石打造-其他w180,h300,left25,top58px.png", -1, -1);
            this.chang(this.option(-1));
        }
    }
    
    public void chang(int type) {
        if (this.lists == null) {
            this.lists = new ArrayList<>();
        }
        int size = 0;
        Goodstable[] goods = null;
        if (type == -2) {
            goods = GoodsListFromServerUntil.getChoseGoodsList();
        }
        else {
            goods = GoodsListFromServerUntil.getGoodslist();
        }
        for (int i = 0; i < goods.length; ++i) {
            Goodstable good = goods[i];
            if (good != null && ((type < 0 && Goodtype.EquipGem((long)good.getType())) || (type >= 0 && type == Goodtype.EquipmentType((long)good.getType())))) {
                if (this.lists.size() > size) {
                    ((GemMakeTabPanel)this.lists.get(size)).SX(good);
                }
                else {
                    GemMakeTabPanel gemMakeTabPanel = new GemMakeTabPanel(good);
                    gemMakeTabPanel.addMouseListener(new OptionListener(gemMakeTabPanel));
                    this.lists.add(gemMakeTabPanel);
                    this.jPanel.add(gemMakeTabPanel);
                }
                ++size;
            }
        }
        for (int i = this.lists.size() - 1; i >= size; --i) {
            this.jPanel.remove((Component)this.lists.remove(i));
        }
        this.tz();
        this.optionClick(null);
    }
    
    public int option(int p) {
        if (p == -2) {
            for (int i = 0; i < this.options.length; ++i) {
                this.options[i].setVisible(false);
            }
            return -2;
        }
        else if (p == -1) {
            this.options[0].setVisible(true);
            for (int i = 1; i < this.options.length; ++i) {
                this.options[i].setVisible(false);
            }
            return (int)this.options[0].getB();
        }
        else if (!this.options[1].isVisible()) {
            this.options[0].setText("全部");
            for (int i = 1; i < this.options.length; ++i) {
                this.options[i].setVisible(true);
            }
            return -2;
        }
        else {
            for (int i = 1; i < this.options.length; ++i) {
                this.options[i].setVisible(false);
            }
            int b = (p == 2) ? -1 : ((p == 3) ? 0 : ((p == 4) ? 3 : ((p == 5) ? 1 : ((p == 6) ? 2 : 5))));
            String aaa = (p == 2) ? "全部" : ((p == 3) ? "武器" : ((p == 4) ? "衣服" : ((p == 5) ? "帽子" : ((p == 6) ? "项链" : "鞋子"))));
            try {
                this.options[0].setText(aaa);
            }
            catch (Exception e) {
                e.printStackTrace();
            }
            this.options[0].setB(Integer.valueOf(b));
            return b;
        }
    }
    
    public void optionClick(Goodstable good) {
        this.essenceLab.setText(null);
        this.gemX.SX(good);
        if (good != null) {
            boolean is = false;
            for (int i = this.lists.size() - 1; i >= 0; --i) {
                GemMakeTabPanel panel = (GemMakeTabPanel)this.lists.get(i);
                if (panel.getGood().getRgid().compareTo(good.getRgid()) == 0) {
                    panel.setBorder(BorderFactory.createLineBorder(Color.YELLOW));
                    panel.SX(good);
                    is = true;
                }
                else {
                    panel.setBorder(null);
                }
            }
            if (is) {
                this.boo = Goodtype.EquipmentType((long)good.getType());
                for (int i = 0; i < this.gemXs.length; ++i) {
                    this.gemXs[i].SX(null, 0);
                    if (i == 2) {
                        this.gemXs[i].setV(this.boo == 0);
                    }
                }
                String extra = AccessSuitMsgUntil.getExtra(good.getValue(), "宝石属性");
                if (extra != null) {
                    String[] extras = extra.split("&");
                    for (int j = 1; j < extras.length; ++j) {
                        BigDecimal id = new BigDecimal(extras[j]);
                        Goodstable goodstable = (Goodstable)GoodsListFromServerUntil.fushis.get(id);
                        if (goodstable != null) {
                            int path = Goodtype.GemPath(this.boo, (long)goodstable.getType());
                            if (path != 0) {
                                --path;
                                this.gemXs[path].SX(goodstable, 2);
                            }
                        }
                    }
                }
            }
        }
        else {
            this.boo = 4;
            for (int k = 0; k < this.gemXs.length; ++k) {
                this.gemXs[k].SX(null, 0);
                if (k == 2) {
                    this.gemXs[k].setV(false);
                }
            }
        }
    }
    
    public void tz() {
        if (MyIsif.getStyle().equals("水墨")) {
            for (int i = 0; i < this.lists.size(); ++i) {
                GemMakeTabPanel modelJpanel = (GemMakeTabPanel)this.lists.get(i);
                modelJpanel.setBounds(3, i * 69, 160, 67);
            }
            this.jPanel.setPreferredSize(new Dimension(160, 68 * this.lists.size()));
            this.jScrollPane.getViewport().setViewSize(this.jPanel.getPreferredSize());
        }
        else {
            for (int i = 0; i < this.lists.size(); ++i) {
                GemMakeTabPanel modelJpanel = (GemMakeTabPanel)this.lists.get(i);
                modelJpanel.setBounds(0, i * 68, 160, 67);
            }
            this.jPanel.setPreferredSize(new Dimension(160, 68 * this.lists.size()));
            this.jScrollPane.getViewport().setViewSize(this.jPanel.getPreferredSize());
        }
    }
    
    public Goodstable getGood(int type) {
        if (type == 0) {
            return GoodsListFromServerUntil.getRgid(this.gemX.gemXListener.rgid);
        }
        if (type == 1) {
            for (int i = 0; i < this.gemXs.length; ++i) {
                if (this.gemXs[i].type == 1) {
                    return GoodsListFromServerUntil.getRgid(this.gemXs[i].gemXListener.rgid);
                }
            }
        }
        else {
            GemX gemX = this.gemXs[type - 2];
            if (gemX.type == 2) {
                return GoodsListFromServerUntil.getRgid(gemX.gemXListener.rgid);
            }
        }
        return null;
    }
    
    public boolean isShoptext() {
        return this.shoptext;
    }
    
    public void setShoptext(boolean shoptext) {
        this.shoptext = shoptext;
    }
    
    public void PaintingText(int goodPosition) {
        this.goodPosition = goodPosition;
        this.shoptext = true;
    }
    
    public void ClearText() {
        this.shoptext = false;
    }
    
    public int getGoodPosition() {
        return this.goodPosition;
    }
    
    public void setGoodPosition(int goodPosition) {
        this.goodPosition = goodPosition;
    }
    
    public static ImageIcon getIconx2() {
        return GemMakeJpanel.iconx2;
    }
    
    public static void setIconx2(ImageIcon iconx2) {
        GemMakeJpanel.iconx2 = iconx2;
    }
    
    static {
        GemMakeJpanel.xz = -1;
        GemMakeJpanel.iconx2 = new ImageIcon("jiuUI/jiuuijiemian/border_quack.png");
    }
    
    class MListener extends MouseAdapter
    {
        private int m;
        
        public MListener(int m) {
            this.m = m;
        }
        
        @Override
        public void mouseEntered(MouseEvent e) {
            Goodstable good = GoodsListFromServerUntil.getGemstoneSuit(GemMakeJpanel.this.yss, this.m, GemMakeJpanel.this.boo);
            if (good != null) {
                GemMakeJpanel.this.PaintingText(this.m);
                ZhuFrame.getZhuJpanel().creatgoodtext(good);
            }
        }
        
        @Override
        public void mouseExited(MouseEvent e) {
            GemMakeJpanel.this.ClearText();
            ZhuFrame.getZhuJpanel().cleargoodtext();
        }
        
        @Override
        public void mousePressed(MouseEvent e) {
            Goodstable good = GoodsListFromServerUntil.getGemstoneSuit(GemMakeJpanel.this.yss, this.m, GemMakeJpanel.this.boo);
            if (good != null) {
                int path = Goodtype.GemPath(GemMakeJpanel.this.boo, (long)good.getType()) - 1;
                if (GemMakeJpanel.this.gemXs[path].type == 2) {
                    ZhuFrame.getZhuJpanel().addPrompt2("已打造该类型宝石");
                    return;
                }
                for (int i = 0; i < GemMakeJpanel.this.gemXs.length; ++i) {
                    if (GemMakeJpanel.this.gemXs[i].type == 1) {
                        GemMakeJpanel.this.gemXs[i].SX(null, 0);
                    }
                }
                GemMakeJpanel.this.gemXs[path].SX(good, 1);
                int lvl = Integer.parseInt(good.getValue().split("\\|")[0].split("=")[1]);
                GemMakeJpanel.this.essenceLab.setText(((lvl <= 4) ? 1 : (lvl - 3)) + "/" + GoodsListFromServerUntil.getGoodNum(new BigDecimal(81095)));
                GemMakeJpanel.this.b = new BigDecimal(5000000 + lvl * 1000000);
            }
        }
    }
    
    class OptionListener extends MouseAdapter
    {
        private GemMakeTabPanel panel;
        
        public OptionListener(GemMakeTabPanel panel) {
            this.panel = panel;
        }
        
        @Override
        public void mousePressed(MouseEvent e) {
            GemMakeJpanel.this.optionClick(this.panel.getGood());
        }
    }
    
    class GemX
    {
        private JLabel jLabel;
        private GemXListener gemXListener;
        private JLabel jLabel2;
        private GemMakeBtn gemMakeBtn;
        private int type;
        
        public GemX(JLabel jLabel, JLabel jLabel2, GemMakeBtn gemMakeBtn) {
            this.jLabel = jLabel;
            this.gemXListener = new GemXListener();
            this.jLabel.addMouseListener(this.gemXListener);
            this.jLabel2 = jLabel2;
            this.gemMakeBtn = gemMakeBtn;
        }
        
        public void SX(Goodstable good) {
            if (good != null) {
                this.jLabel.setIcon(CutButtonImage.getGemstoneIcon(good.getSkin(), 49, 49));
                this.gemXListener.rgid = good.getRgid();
                int type = Goodtype.EquipmentType((long)good.getType());
                for (int i = 0; i < GemMakeJpanel.this.gemXs.length; ++i) {
                    if (type == 0) {
                        GemMakeJpanel.this.gemXs[i].jLabel.setText((i == 0) ? "赤炎石" : ((i == 1) ? "紫烟石" : "孔雀石"));
                    }
                    else if (type == 1) {
                        GemMakeJpanel.this.gemXs[i].jLabel.setText((i == 0) ? "落星石" : "芙蓉石");
                    }
                    else if (type == 2) {
                        GemMakeJpanel.this.gemXs[i].jLabel.setText((i == 0) ? "沐阳石" : "芙蓉石");
                    }
                    else if (type == 3) {
                        GemMakeJpanel.this.gemXs[i].jLabel.setText((i == 0) ? "落星石" : "沐阳石");
                    }
                    else if (type == 5) {
                        GemMakeJpanel.this.gemXs[i].jLabel.setText((i == 0) ? "琉璃石" : "寒山石");
                    }
                    GemMakeJpanel.this.gemXs[i].jLabel.setName(GemMakeJpanel.this.gemXs[i].jLabel.getText());
                }
            }
            else {
                this.jLabel.setIcon(null);
                this.gemXListener.rgid = null;
                for (int j = 0; j < GemMakeJpanel.this.gemXs.length; ++j) {
                    GemMakeJpanel.this.gemXs[j].jLabel.setText(null);
                    GemMakeJpanel.this.gemXs[j].jLabel.setName(null);
                }
            }
        }
        
        public void SX(Goodstable good, int type) {
            this.type = type;
            if (good != null) {
                this.jLabel.setText(null);
                this.jLabel.setIcon(CutButtonImage.getGemstoneIcon(good.getSkin(), 45, 45));
                this.gemXListener.rgid = good.getRgid();
            }
            else {
                this.jLabel.setText(this.jLabel.getName());
                this.jLabel.setIcon(null);
                this.gemXListener.rgid = null;
            }
            if (type == 0) {
                this.gemMakeBtn.setIcon(null);
                this.gemMakeBtn.setText("可打造");
                this.gemMakeBtn.setForeground(Color.GRAY);
                this.gemMakeBtn.setBtn(0);
            }
            else if (type == 1) {
                this.gemMakeBtn.setIcon(null);
                this.gemMakeBtn.setText("待打造");
                this.gemMakeBtn.setForeground(Color.red);
                this.gemMakeBtn.setBtn(0);
            }
            else if (type == 2) {
                this.gemMakeBtn.setForeground(Color.WHITE);
                this.gemMakeBtn.setText("摘除");
                this.gemMakeBtn.setBtn(1);
                this.gemMakeBtn.btnchange(0);
            }
        }
        
        public void setV(boolean is) {
            this.jLabel.setVisible(is);
            if (this.jLabel2 != null) {
                this.jLabel2.setVisible(is);
            }
            this.gemMakeBtn.setVisible(is);
        }
    }
    
    class GemXListener extends MouseAdapter
    {
        private BigDecimal rgid;
        
        @Override
        public void mouseEntered(MouseEvent e) {
            if (this.rgid != null) {
                Goodstable goodstable = GoodsListFromServerUntil.getRgid(this.rgid);
                if (goodstable != null) {
                    ZhuFrame.getZhuJpanel().creatgoodtext(goodstable);
                }
            }
        }
        
        @Override
        public void mouseExited(MouseEvent e) {
            ZhuFrame.getZhuJpanel().cleargoodtext();
        }
    }
}
