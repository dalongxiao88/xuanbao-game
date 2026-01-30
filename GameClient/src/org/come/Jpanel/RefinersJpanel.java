package org.come.Jpanel;

import org.come.until.Util;
import org.come.until.CutButtonImage;
import java.awt.Graphics;
import org.come.until.GoodsListFromServerUntil;
import org.come.until.Goodtype;
import org.come.mouslisten.WLLMouslisten;
import javax.swing.BorderFactory;
import org.come.until.SrcollPanelUI;
import java.awt.Component;
import javax.swing.DefaultListCellRenderer;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.Font;
import org.come.mouslisten.RefinersMouslisten;
import java.awt.Color;
import com.tool.tcpimg.UIUtils;
import java.awt.Dimension;
import com.updateNew.MyIsif;
import javax.swing.ImageIcon;
import javax.swing.JList;
import javax.swing.DefaultListModel;
import javax.swing.JScrollPane;
import com.tool.btn.goodbtn;
import org.come.entity.Goodstable;
import javax.swing.JLabel;
import com.tool.btn.GoodPanelBtn;
import com.tool.btn.RefineOperBtn;
import java.math.BigDecimal;
import javax.swing.JPanel;

public class RefinersJpanel extends JPanel
{
    public BigDecimal money;
    private RefineOperBtn workshopBtn;
    private GoodPanelBtn clearAll;
    private JLabel labOwner;
    private JLabel labSegment;
    private JLabel labLevel;
    private JLabel labShulian;
    private JLabel labAchieve;
    public JLabel[] labMark;
    public Goodstable[] goods;
    public JLabel[] GoodsListLabel;
    private goodbtn[] btnrights;
    private JScrollPane jScrollPane;
    private DefaultListModel<String> listModel;
    private static JList<String> RefiningEquilist;
    private JLabel[] labelList;
    private ImageIcon icon;
    private ImageIcon iconSixGoods;
    
    public RefinersJpanel() {
        this.money = new BigDecimal(100000);
        this.labMark = new JLabel[5];
        this.goods = new Goodstable[5];
        this.GoodsListLabel = new JLabel[24];
        this.labelList = new JLabel[5];
        if (MyIsif.getStyle().equals("水墨")) {
            this.setPreferredSize(new Dimension(558, 516));
            this.setLayout(null);
            this.setBackground(UIUtils.Color_BACK);
            (this.workshopBtn = new RefineOperBtn("inkImg/button1/B20.png", 1, UIUtils.COLOR_BLACK, UIUtils.TEXT_HY16, this, 0, "?")).setBounds(448, 125, 59, 24);
            this.add(this.workshopBtn);
            for (int i = 0; i < 5; ++i) {
                this.labMark[i] = new JLabel();
                this.labelList[i] = new JLabel();
                if (i == 4) {
                    this.labMark[i].setBounds(385, 112, 53, 51);
                }
                else {
                    int row = i % 2;
                    int col = i / 2;
                    this.labMark[i].setBounds(250 + row * 67, 79 + col * 65, 53, 51);
                    this.labelList[i].setBounds(250 + row * 67 + 2, 79 + col * 65, 70, 51);
                    this.labelList[i].setForeground(Color.red);
                    this.labelList[i].setVisible(false);
                }
                this.labMark[i].addMouseListener(new RefinersMouslisten(this, i + 24));
                this.add(this.labelList[i]);
                this.add(this.labMark[i]);
            }
            Font font = new Font("宋体", 0, 14);
            Color color = new Color(187, 165, 75);
            (this.labOwner = new JLabel()).setBounds(88, 312, 96, 14);
            this.labOwner.setForeground(color);
            this.labOwner.setFont(font);
            this.add(this.labOwner);
            (this.labSegment = new JLabel()).setBounds(88, 344, 96, 14);
            this.labSegment.setForeground(color);
            this.labSegment.setFont(font);
            this.add(this.labSegment);
            (this.labLevel = new JLabel()).setBounds(88, 376, 96, 14);
            this.labLevel.setForeground(color);
            this.labLevel.setFont(font);
            this.add(this.labLevel);
            (this.labShulian = new JLabel()).setBounds(88, 408, 96, 14);
            this.labShulian.setForeground(color);
            this.labShulian.setFont(font);
            this.add(this.labShulian);
            (this.labAchieve = new JLabel()).setBounds(88, 440, 96, 14);
            this.labAchieve.setForeground(color);
            this.labAchieve.setFont(font);
            this.add(this.labAchieve);
            for (int j = 0; j < 24; ++j) {
                int row2 = j % 6;
                int col2 = j / 6;
                (this.GoodsListLabel[j] = new JLabel()).addMouseListener(new RefinersMouslisten(this, j));
                this.GoodsListLabel[j].setBounds(218 + row2 * 51, 277 + col2 * 51, 49, 49);
                this.add(this.GoodsListLabel[j]);
            }
            this.btnrights = new goodbtn[6];
            for (int j = 0; j < this.btnrights.length; ++j) {
                (this.btnrights[j] = new goodbtn("inkImg/button1/C0" + (j + 1) + ".png", 0, this, j)).setBounds(524, 284 + j * 35, 35, 31);
                this.add(this.btnrights[j]);
            }
            this.listModel = new DefaultListModel<>();
            (RefinersJpanel.RefiningEquilist = new JList<String>() {
                {
                    this.setOpaque(false);
                }
            }).setSelectionBackground(new Color(33, 42, 52));
            RefinersJpanel.RefiningEquilist.setSelectionForeground(Color.white);
            RefinersJpanel.RefiningEquilist.setForeground(Color.white);
            RefinersJpanel.RefiningEquilist.setFont(new Font("微软雅黑", 1, 14));
            RefinersJpanel.RefiningEquilist.setBackground(UIUtils.Color_BACK);
            RefinersJpanel.RefiningEquilist.setModel(this.listModel);
            RefinersJpanel.RefiningEquilist.setSelectionMode(0);
            RefinersJpanel.RefiningEquilist.addMouseListener(new MouseListener() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    for (int i = 0; i <= RefinersJpanel.this.labelList.length - 1; ++i) {
                        RefinersJpanel.this.labelList[i].setVisible(false);
                        RefinersJpanel.this.labelList[i].setVerticalTextPosition(0);
                        RefinersJpanel.this.labelList[i].setForeground(Color.white);
                        RefinersJpanel.this.labelList[i].setFont(UIUtils.TEXT_FONT);
                        RefinersJpanel.this.labelList[i].setVisible(true);
                    }
                    switch (RefinersJpanel.RefiningEquilist.getSelectedIndex()) {
                        case 0: {
                            RefinersJpanel.this.labelList[0].setText("武器");
                            RefinersJpanel.this.labelList[0].setVisible(true);
                            RefinersJpanel.this.labelList[1].setText("九玄仙玉");
                            RefinersJpanel.this.labelList[1].setVisible(true);
                            RefinersJpanel.this.labelList[2].setText("");
                            RefinersJpanel.this.labelList[2].setVisible(false);
                            RefinersJpanel.this.labelList[3].setText("");
                            RefinersJpanel.this.labelList[3].setVisible(false);
                            RefinersJpanel.this.labelList[4].setText("");
                            RefinersJpanel.this.labelList[4].setVisible(false);
                            break;
                        }
                        case 1: {
                            RefinersJpanel.this.labelList[0].setText("武器");
                            RefinersJpanel.this.labelList[0].setVisible(true);
                            RefinersJpanel.this.labelList[1].setText("落魄沙");
                            RefinersJpanel.this.labelList[1].setVisible(true);
                            RefinersJpanel.this.labelList[2].setText("落魄沙");
                            RefinersJpanel.this.labelList[2].setVisible(true);
                            RefinersJpanel.this.labelList[3].setText("落魄沙");
                            RefinersJpanel.this.labelList[3].setVisible(true);
                            RefinersJpanel.this.labelList[4].setText("");
                            RefinersJpanel.this.labelList[4].setVisible(false);
                            break;
                        }
                    }
                }
                
                @Override
                public void mousePressed(MouseEvent e) {
                }
                
                @Override
                public void mouseReleased(MouseEvent e) {
                }
                
                @Override
                public void mouseEntered(MouseEvent e) {
                }
                
                @Override
                public void mouseExited(MouseEvent e) {
                }
            });
            RefinersJpanel.RefiningEquilist.addMouseMotionListener(new MouseMotionListener() {
                @Override
                public void mouseDragged(MouseEvent e) {
                }
                
                @Override
                public void mouseMoved(MouseEvent e) {
                }
            });
            DefaultListCellRenderer renderer = new DefaultListCellRenderer() {
                Color color = new Color(203, 181, 91);
                
                @Override
                public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
                    super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
                    if (index == RefinersJpanel.RefiningEquilist.getSelectedIndex()) {
                        this.setForeground(Color.white);
                    }
                    else {
                        this.setForeground(Color.green);
                    }
                    return this;
                }
            };
            RefinersJpanel.RefiningEquilist.setCellRenderer(renderer);
            (this.jScrollPane = new JScrollPane(RefinersJpanel.RefiningEquilist)).setVerticalScrollBarPolicy(22);
            this.jScrollPane.getVerticalScrollBar().setUI(new SrcollPanelUI());
            this.jScrollPane.getViewport().setOpaque(false);
            this.jScrollPane.setOpaque(false);
            this.jScrollPane.setBounds(48, 72, 165, 200);
            this.jScrollPane.setBorder(BorderFactory.createEmptyBorder());
            this.jScrollPane.setHorizontalScrollBarPolicy(31);
            this.add(this.jScrollPane);
            this.listModel.add(0, "开光");
            this.listModel.add(1, "炼器");
        }
        else {
            this.setPreferredSize(new Dimension(536, 542));
            this.setLayout(null);
            this.setBackground(UIUtils.Color_BACK);
            (this.clearAll = new GoodPanelBtn("inkImg/hongmu/a3.png", 1, "整")).addMouseListener(new WLLMouslisten(17));
            this.clearAll.setBounds(168, 290, 17, 17);
            this.add(this.clearAll);
            (this.workshopBtn = new RefineOperBtn("inkImg/hongmu/w50.png", 1, UIUtils.COLOR_BTNPUTONG, UIUtils.TEXT_FONT, this, 0, "?")).setBounds(421, 125, 59, 26);
            this.add(this.workshopBtn);
            for (int i = 0; i < 5; ++i) {
                this.labMark[i] = new JLabel();
                this.labelList[i] = new JLabel();
                if (i == 4) {
                    this.labMark[i].setBounds(361, 132, 53, 51);
                }
                else {
                    int row = i % 2;
                    int col = i / 2;
                    this.labMark[i].setBounds(226 + row * 67, 99 + col * 65, 54, 51);
                    this.labelList[i].setBounds(226 + row * 67 + 2, 99 + col * 65, 70, 51);
                    this.labelList[i].setForeground(Color.red);
                    this.labelList[i].setVisible(false);
                }
                this.labMark[i].addMouseListener(new RefinersMouslisten(this, i + 24));
                this.add(this.labelList[i]);
                this.add(this.labMark[i]);
            }
            Font font = new Font("宋体", 0, 14);
            Color color = new Color(187, 165, 75);
            (this.labOwner = new JLabel()).setBounds(88, 312, 96, 14);
            this.labOwner.setForeground(color);
            this.labOwner.setFont(font);
            this.add(this.labOwner);
            (this.labSegment = new JLabel()).setBounds(88, 344, 96, 14);
            this.labSegment.setForeground(color);
            this.labSegment.setFont(font);
            this.add(this.labSegment);
            (this.labLevel = new JLabel()).setBounds(88, 376, 96, 14);
            this.labLevel.setForeground(color);
            this.labLevel.setFont(font);
            this.add(this.labLevel);
            (this.labShulian = new JLabel()).setBounds(88, 408, 96, 14);
            this.labShulian.setForeground(color);
            this.labShulian.setFont(font);
            this.add(this.labShulian);
            (this.labAchieve = new JLabel()).setBounds(88, 440, 96, 14);
            this.labAchieve.setForeground(color);
            this.labAchieve.setFont(font);
            this.add(this.labAchieve);
            for (int j = 0; j < 24; ++j) {
                int row2 = j % 6;
                int col2 = j / 6;
                (this.GoodsListLabel[j] = new JLabel()).addMouseListener(new RefinersMouslisten(this, j));
                this.GoodsListLabel[j].setBounds(200 + row2 * 51, 287 + col2 * 51, 49, 49);
                this.add(this.GoodsListLabel[j]);
            }
            this.btnrights = new goodbtn[6];
            for (int j = 0; j < this.btnrights.length; ++j) {
                (this.btnrights[j] = new goodbtn("inkImg/hongmu/SBG.png", 0, this, j)).setBounds(500, 296 + j * 33, 24, 33);
                this.add(this.btnrights[j]);
            }
            this.listModel = new DefaultListModel<>();
            (RefinersJpanel.RefiningEquilist = new JList<String>() {
                {
                    this.setOpaque(false);
                }
            }).setSelectionBackground(new Color(33, 42, 52));
            RefinersJpanel.RefiningEquilist.setSelectionForeground(Color.white);
            RefinersJpanel.RefiningEquilist.setForeground(Color.white);
            RefinersJpanel.RefiningEquilist.setFont(new Font("微软雅黑", 1, 14));
            RefinersJpanel.RefiningEquilist.setBackground(UIUtils.Color_BACK);
            RefinersJpanel.RefiningEquilist.setModel(this.listModel);
            RefinersJpanel.RefiningEquilist.setSelectionMode(0);
            RefinersJpanel.RefiningEquilist.addMouseListener(new MouseListener() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    for (int i = 0; i <= RefinersJpanel.this.labelList.length - 1; ++i) {
                        RefinersJpanel.this.labelList[i].setVisible(false);
                        RefinersJpanel.this.labelList[i].setVerticalTextPosition(0);
                        RefinersJpanel.this.labelList[i].setForeground(Color.white);
                        RefinersJpanel.this.labelList[i].setFont(UIUtils.TEXT_FONT);
                        RefinersJpanel.this.labelList[i].setVisible(true);
                    }
                    switch (RefinersJpanel.RefiningEquilist.getSelectedIndex()) {
                        case 0: {
                            RefinersJpanel.this.labelList[0].setText("武器");
                            RefinersJpanel.this.labelList[0].setVisible(true);
                            RefinersJpanel.this.labelList[1].setText("九玄仙玉");
                            RefinersJpanel.this.labelList[1].setVisible(true);
                            RefinersJpanel.this.labelList[2].setText("");
                            RefinersJpanel.this.labelList[2].setVisible(false);
                            RefinersJpanel.this.labelList[3].setText("");
                            RefinersJpanel.this.labelList[3].setVisible(false);
                            RefinersJpanel.this.labelList[4].setText("");
                            RefinersJpanel.this.labelList[4].setVisible(false);
                            break;
                        }
                        case 1: {
                            RefinersJpanel.this.labelList[0].setText("武器");
                            RefinersJpanel.this.labelList[0].setVisible(true);
                            RefinersJpanel.this.labelList[1].setText("落魄沙");
                            RefinersJpanel.this.labelList[1].setVisible(true);
                            RefinersJpanel.this.labelList[2].setText("落魄沙");
                            RefinersJpanel.this.labelList[2].setVisible(true);
                            RefinersJpanel.this.labelList[3].setText("落魄沙");
                            RefinersJpanel.this.labelList[3].setVisible(true);
                            RefinersJpanel.this.labelList[4].setText("");
                            RefinersJpanel.this.labelList[4].setVisible(false);
                            break;
                        }
                    }
                }
                
                @Override
                public void mousePressed(MouseEvent e) {
                }
                
                @Override
                public void mouseReleased(MouseEvent e) {
                }
                
                @Override
                public void mouseEntered(MouseEvent e) {
                }
                
                @Override
                public void mouseExited(MouseEvent e) {
                }
            });
            RefinersJpanel.RefiningEquilist.addMouseMotionListener(new MouseMotionListener() {
                @Override
                public void mouseDragged(MouseEvent e) {
                }
                
                @Override
                public void mouseMoved(MouseEvent e) {
                }
            });
            DefaultListCellRenderer renderer = new DefaultListCellRenderer() {
                Color color = new Color(203, 181, 91);
                
                @Override
                public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
                    super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
                    if (index == RefinersJpanel.RefiningEquilist.getSelectedIndex()) {
                        this.setForeground(Color.white);
                    }
                    else {
                        this.setForeground(Color.green);
                    }
                    return this;
                }
            };
            RefinersJpanel.RefiningEquilist.setCellRenderer(renderer);
            (this.jScrollPane = new JScrollPane(RefinersJpanel.RefiningEquilist)).setVerticalScrollBarPolicy(22);
            this.jScrollPane.getVerticalScrollBar().setUI(new SrcollPanelUI());
            this.jScrollPane.getViewport().setOpaque(false);
            this.jScrollPane.setOpaque(false);
            this.jScrollPane.setBounds(28, 85, 165, 200);
            this.jScrollPane.setBorder(BorderFactory.createEmptyBorder());
            this.jScrollPane.setHorizontalScrollBarPolicy(31);
            this.add(this.jScrollPane);
            this.listModel.add(0, "开光");
            this.listModel.add(1, "炼器");
        }
    }
    
    public void ClickGood(Goodstable good, int path) {
        if (path < 24) {
            if (Goodtype.Weapons((long)good.getType())) {
                this.change(good, 0);
            }
            else if ((long)good.getType() == 505L || (long)good.getType() == 507L) {
                path = this.vacantGood();
                if (path > 0) {
                    this.change(good, path);
                }
            }
        }
        else {
            this.change(null, path - 24);
        }
        String v = this.detection();
        if (!this.workshopBtn.getText().equals(v)) {
            this.workshopBtn.setText(v);
        }
    }
    
    public void ResetGood(Goodstable good) {
        int i = 0;
        while (i < this.goods.length) {
            if (this.goods[i] != null && this.goods[i].getRgid().compareTo(good.getRgid()) == 0) {
                if ((int)good.getUsetime() <= 0) {
                    this.goods[i] = null;
                    this.labMark[i].setIcon(null);
                }
                else {
                    this.goods[i] = good;
                    this.labMark[i].setIcon(GoodsListFromServerUntil.imgpathAdaptive(good.getSkin(), 49, 49));
                }
                String v = this.detection();
                if (!this.workshopBtn.getText().equals(v)) {
                    this.workshopBtn.setText(v);
                    break;
                }
                else {
                    break;
                }
            }
            else {
                ++i;
            }
        }
    }
    
    public String detection() {
        int path = this.vacantGood();
        if (path == 0) {
            return "?";
        }
        Goodstable good = this.goods[0];
        if (!Goodtype.Weapons((long)good.getType())) {
            return "?";
        }
        if (path == 1) {
            if (good.getValue().indexOf("炼器属性") != -1) {
                return "清除";
            }
        }
        else if (path == 2) {
            if ((long)this.goods[1].getType() == 505L) {
                return "开光";
            }
        }
        else if (path == 4 && ((long)this.goods[1].getType() == 507L || (long)this.goods[2].getType() == 507L || (long)this.goods[3].getType() == 507L)) {
            return "炼器";
        }
        return "?";
    }
    
    public void change(Goodstable good, int path) {
        this.goods[path] = good;
        if (good != null) {
            this.labMark[path].setIcon(GoodsListFromServerUntil.imgpathAdaptive(good.getSkin(), 49, 49));
            this.getLabelList()[path].setVisible(false);
        }
        else {
            if (this.getLabelList()[path].getText() != null) {
                this.getLabelList()[path].setVisible(true);
            }
            this.labMark[path].setIcon(null);
        }
    }
    
    public int vacantGood() {
        for (int i = 0; i < this.goods.length; ++i) {
            if (this.goods[i] == null) {
                return i;
            }
        }
        return -1;
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (MyIsif.getStyle().equals("水墨")) {
            if (this.icon == null) {
                this.icon = CutButtonImage.getImage("inkImg/background1/B238.png", -1, -1);
            }
            g.drawImage(this.icon.getImage(), 0, 0, this);
            if (this.iconSixGoods == null) {
                this.iconSixGoods = new ImageIcon("inkImg/background1/B248.png");
            }
            g.drawImage(this.iconSixGoods.getImage(), 214, 55, this);
            GoodsListFromServerUntil.draw(g, 218, 287);
            Util.drawMoney(g, 322, 231);
            if (this.money != null) {
                Util.drawPrice(g, this.money, 322, 258);
            }
        }
        else {
            if (this.icon == null) {
                this.icon = new ImageIcon("img/xy2uiimg/worhshop_lq.png");
            }
            g.drawImage(this.icon.getImage(), 0, 0, this);
            GoodsListFromServerUntil.draw(g, 193, 298);
            Util.drawMoney(g, 297, 242);
            Util.drawMoneyS(g, 87, 325);
            if (this.money != null) {
                Util.drawPrice(g, this.money, 297, 269);
            }
        }
    }
    
    public goodbtn[] getBtnrights() {
        return this.btnrights;
    }
    
    public void setBtnrights(goodbtn[] btnrights) {
        this.btnrights = btnrights;
    }
    
    public JLabel[] getLabelList() {
        return this.labelList;
    }
    
    public void setLabelList(JLabel[] labelList) {
        this.labelList = labelList;
    }
}
