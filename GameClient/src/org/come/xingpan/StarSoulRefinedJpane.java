package org.come.xingpan;

import java.awt.event.MouseEvent;
import java.awt.event.MouseAdapter;
import com.tool.tcpimg.UIUtils;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.Font;
import org.come.entity.Goodstable;
import org.come.until.CutButtonImage;
import org.come.until.GoodsListFromServerUntil;
import org.come.until.UserMessUntil;
import java.math.BigDecimal;
import com.tool.btn.FormsOnOffBtn;
import java.awt.Dimension;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class StarSoulRefinedJpane extends JPanel
{
    private JLabel[] shuliangLab;
    private JLabel[] iconLab;
    private StarSoulLabel[] materialLab;
    private BtnStarCard starCardBtn;
    private ImageIcon iconBack;
    
    public StarSoulRefinedJpane() {
        this.setPreferredSize(new Dimension(382, 495));
        this.setLayout(null);
        this.setOpaque(false);
        FormsOnOffBtn offBtn = new FormsOnOffBtn("inkImg/button/1.png", 1, 120);
        offBtn.setBounds(352, 10, 25, 25);
        this.add(offBtn);
        this.getShuliangLab();
        this.getIconLab();
        this.getMaterialLab();
        this.getStarCardBtn();
        this.init(0);
    }
    
    public void init() {
        this.init((this.materialLab[2].getId() == null) ? 0 : ((int)this.materialLab[2].getId()));
    }
    
    public void init(int id) {
        this.materialLab[0].removeStarSoul();
        this.materialLab[1].removeStarSoul();
        if (id > 0) {
            this.materialLab[2].setStarSoul(id);
        }
        else {
            this.materialLab[2].removeStarSoul();
        }
        for (int i = 0; i < JpanelXingBackMain.starSouls.length; ++i) {
            Goodstable goodstable = UserMessUntil.getgoodstable(new BigDecimal(51000 + i));
            int goodNum = (goodstable != null) ? GoodsListFromServerUntil.getStarSoulNum(goodstable.getGoodsid()) : 0;
            if (goodNum > 0) {
                this.iconLab[i].setIcon(CutButtonImage.getImage("resource/mouse/xp/XPSoul1.png", 43, 44));
            }
            else {
                this.iconLab[i].setIcon(CutButtonImage.getImage("resource/mouse/xp/XPSoul2.png", 43, 44));
            }
            this.shuliangLab[i].setText("" + goodNum);
        }
    }
    
    public JLabel[] getShuliangLab() {
        if (this.shuliangLab == null) {
            this.shuliangLab = new JLabel[JpanelXingBackMain.starSouls.length];
            for (int i = 0; i < this.shuliangLab.length; ++i) {
                (this.shuliangLab[i] = new JLabel()).setFont(new Font("宋体", 1, 15));
                this.shuliangLab[i].setForeground(Color.WHITE);
                this.shuliangLab[i].setBounds(49 + i / 6 * 51, 40 + i % 6 * 51, 18, 18);
                this.add(this.shuliangLab[i]);
            }
        }
        return this.shuliangLab;
    }
    
    public JLabel[] getIconLab() {
        if (this.iconLab == null) {
            this.iconLab = new JLabel[JpanelXingBackMain.starSouls.length];
            for (int i = 0; i < this.iconLab.length; ++i) {
                int finalI = i;
                (this.iconLab[i] = new JLabel() {
                    @Override
                    protected void paintComponent(Graphics g) {
                        super.paintComponent(g);
                        g.setFont(UIUtils.TEXT_HY15);
                        g.setColor(Color.BLACK);
                        int height = g.getFontMetrics().getHeight();
                        char[] charArray = JpanelXingBackMain.starSouls[finalI].toCharArray();
                        g.drawString(charArray[0] + "", 14, height + 5);
                        g.drawString(charArray[1] + "", 14, height + 5 + height);
                    }
                }).setBounds(48 + i / 6 * 51, 40 + i % 6 * 51, 50, 50);
                this.iconLab[i].addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        Goodstable goodstable = UserMessUntil.getgoodstable(new BigDecimal(51000 + finalI));
                        int goodNum = (goodstable != null) ? GoodsListFromServerUntil.getStarSoulNum(goodstable.getGoodsid()) : 0;
                        if (StarSoulRefinedJpane.this.materialLab[0].getId() != null && (int)StarSoulRefinedJpane.this.materialLab[0].getId() == finalI + 1) {
                            --goodNum;
                        }
                        if (StarSoulRefinedJpane.this.materialLab[1].getId() != null && (int)StarSoulRefinedJpane.this.materialLab[1].getId() == finalI + 1) {
                            --goodNum;
                        }
                        if (goodNum > 0) {
                            if (StarSoulRefinedJpane.this.materialLab[0].getId() == null) {
                                StarSoulRefinedJpane.this.materialLab[0].setStarSoul(finalI + 1);
                            }
                            else if (StarSoulRefinedJpane.this.materialLab[1].getId() == null) {
                                StarSoulRefinedJpane.this.materialLab[1].setStarSoul(finalI + 1);
                            }
                        }
                    }
                });
                this.add(this.iconLab[i]);
            }
        }
        return this.iconLab;
    }
    
    public StarSoulLabel[] getMaterialLab() {
        if (this.materialLab == null) {
            this.materialLab = new StarSoulLabel[3];
            for (int i = 0; i < this.materialLab.length; ++i) {
                int finalI = i;
                (this.materialLab[i] = new StarSoulLabel()).setBounds(119 + i / 6 * 51, 70 + i % 6 * 51, 50, 50);
                if (i < 2) {
                    int finalI2 = i;
                    this.materialLab[i].addMouseListener(new MouseAdapter() {
                        @Override
                        public void mouseClicked(MouseEvent e) {
                            if (e.isMetaDown()) {
                                StarSoulRefinedJpane.this.materialLab[finalI2].removeStarSoul();
                            }
                        }
                    });
                }
                this.add(this.materialLab[i]);
            }
            this.materialLab[0].setBounds(91, 379, 50, 50);
            this.materialLab[1].setBounds(157, 379, 50, 50);
            this.materialLab[2].setBounds(277, 379, 50, 50);
        }
        return this.materialLab;
    }
    
    public BtnStarCard getStarCardBtn() {
        if (this.starCardBtn == null) {
            (this.starCardBtn = new BtnStarCard("inkImg/button/32.png", 1, UIUtils.COLOR_BLACK, UIUtils.TEXT_HY16, "重炼", 10, this)).setBounds(270, 443, 65, 24);
            this.add(this.starCardBtn);
        }
        return this.starCardBtn;
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (this.iconBack == null) {
            this.iconBack = new ImageIcon("resource/mouse/xp/重练.png");
        }
        g.drawImage(this.iconBack.getImage(), 0, 0, 382, 495, this);
    }
    
    public Integer getSelectedStarSoulId(int index) {
        return this.getMaterialLab()[index].getId();
    }
    
    private class StarSoulLabel extends JLabel
    {
        private Integer id;
        private String starSoulName;
        
        public void setStarSoul(int id) {
            this.setIcon(CutButtonImage.getImage("resource/mouse/xp/XPSoul1.png", 43, 44));
            this.starSoulName = JpanelXingBackMain.starSouls[id - 1];
            this.id = Integer.valueOf(id);
        }
        
        public void removeStarSoul() {
            this.setIcon(null);
            this.starSoulName = null;
            this.id = null;
        }
        
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            if (this.starSoulName != null) {
                g.setFont(UIUtils.TEXT_HY15);
                g.setColor(Color.BLACK);
                int height = g.getFontMetrics().getHeight();
                char[] charArray = this.starSoulName.toCharArray();
                g.drawString(charArray[0] + "", 14, height + 5);
                g.drawString(charArray[1] + "", 14, height + 5 + height);
            }
        }
        
        public Integer getId() {
            return this.id;
        }
    }
}
