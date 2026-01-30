package org.come.Jpanel;

import org.come.until.Util;
import org.come.until.CutButtonImage;
import java.awt.Graphics;
import org.come.until.GoodsListFromServerUntil;
import org.come.until.RefiningUtil;
import org.come.mouslisten.WLLMouslisten;
import javax.swing.BorderFactory;
import org.come.until.SrcollPanelUI;
import java.awt.Component;
import javax.swing.DefaultListCellRenderer;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.Font;
import java.awt.Color;
import org.come.mouslisten.RefinersMouslisten;
import com.tool.tcpimg.UIUtils;
import java.awt.Dimension;
import com.updateNew.MyIsif;
import javax.swing.ImageIcon;
import javax.swing.JList;
import javax.swing.DefaultListModel;
import javax.swing.JScrollPane;
import com.tool.btn.goodbtn;
import javax.swing.JLabel;
import org.come.entity.Goodstable;
import com.tool.btn.GoodPanelBtn;
import com.tool.btn.RefineOperBtn;
import java.math.BigDecimal;
import javax.swing.JPanel;

public class RefiningEquiJpanel extends JPanel
{
    public BigDecimal money;
    private RefineOperBtn workshopBtn;
    private GoodPanelBtn clearAll;
    public Goodstable[] goods;
    private JLabel[] labGoods;
    public JLabel[] GoodsListLabel;
    private goodbtn[] btnrights;
    private JScrollPane jScrollPane;
    private DefaultListModel<String> listModel;
    private static JList<String> RefiningEquilist;
    private JLabel[] labelList;
    private int type;
    private ImageIcon icon;
    private ImageIcon icon2;
    
    public RefiningEquiJpanel() {
        this.money = new BigDecimal(100000);
        this.goods = new Goodstable[6];
        this.labGoods = new JLabel[6];
        this.GoodsListLabel = new JLabel[24];
        this.labelList = new JLabel[6];
        this.type = 1;
        if (MyIsif.getStyle().equals("水墨")) {
            this.setPreferredSize(new Dimension(536, 542));
            this.setLayout(null);
            this.setOpaque(false);
            (this.workshopBtn = new RefineOperBtn("inkImg/button1/B21.png", 1, UIUtils.COLOR_BLACK, UIUtils.TEXT_FONT, this, 0, "?")).setBounds(441, 125, 79, 24);
            this.add(this.workshopBtn);
            for (int i = 0; i < 6; ++i) {
                int row = i % 3;
                int col = i / 3;
                (this.labGoods[i] = new JLabel()).addMouseListener(new RefinersMouslisten(this, 24 + i));
                this.labGoods[i].setBounds(250 + row * 67, 79 + col * 65, 54, 51);
                (this.labelList[i] = new JLabel()).setBounds(250 + row * 67 + 2, 79 + col * 65, 70, 51);
                this.labelList[i].setForeground(Color.red);
                this.labelList[i].setVisible(false);
                this.add(this.labelList[i]);
                this.add(this.labGoods[i]);
            }
            for (int i = 0; i < 24; ++i) {
                int row = i % 6;
                int col = i / 6;
                (this.GoodsListLabel[i] = new JLabel()).addMouseListener(new RefinersMouslisten(this, i));
                this.GoodsListLabel[i].setBounds(218 + row * 51, 277 + col * 51, 49, 49);
                this.add(this.GoodsListLabel[i]);
            }
            this.btnrights = new goodbtn[6];
            for (int i = 0; i < this.btnrights.length; ++i) {
                (this.btnrights[i] = new goodbtn("inkImg/button1/C0" + (i + 1) + ".png", 0, this, i)).setBounds(524, 284 + i * 35, 35, 31);
                this.add(this.btnrights[i]);
            }
            this.listModel = new DefaultListModel<>();
            (RefiningEquiJpanel.RefiningEquilist = new JList<String>() {
                {
                    this.setOpaque(false);
                }
            }).setSelectionBackground(new Color(33, 42, 52));
            RefiningEquiJpanel.RefiningEquilist.setSelectionForeground(Color.white);
            RefiningEquiJpanel.RefiningEquilist.setForeground(Color.white);
            RefiningEquiJpanel.RefiningEquilist.setFont(new Font("微软雅黑", 1, 14));
            RefiningEquiJpanel.RefiningEquilist.setBackground(UIUtils.Color_BACK);
            RefiningEquiJpanel.RefiningEquilist.setModel(this.listModel);
            RefiningEquiJpanel.RefiningEquilist.setSelectionMode(0);
            RefiningEquiJpanel.RefiningEquilist.addMouseListener(new MouseListener() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    for (int i = 0; i <= RefiningEquiJpanel.this.labelList.length - 1; ++i) {
                        RefiningEquiJpanel.this.labelList[i].setVisible(false);
                        RefiningEquiJpanel.this.labelList[i].setVerticalTextPosition(0);
                        RefiningEquiJpanel.this.labelList[i].setForeground(Color.white);
                        RefiningEquiJpanel.this.labelList[i].setFont(UIUtils.TEXT_FONT);
                        RefiningEquiJpanel.this.labelList[i].setVisible(true);
                    }
                    if (RefiningEquiJpanel.this.type == 1) {
                        switch (RefiningEquiJpanel.RefiningEquilist.getSelectedIndex()) {
                            case 0: {
                                RefiningEquiJpanel.this.labelList[0].setText("普通装备");
                                RefiningEquiJpanel.this.labelList[0].setVisible(true);
                                RefiningEquiJpanel.this.labelList[1].setText("内丹精华");
                                RefiningEquiJpanel.this.labelList[1].setVisible(true);
                                RefiningEquiJpanel.this.labelList[2].setText("血玲珑");
                                RefiningEquiJpanel.this.labelList[2].setVisible(true);
                                RefiningEquiJpanel.this.labelList[3].setText("九彩");
                                RefiningEquiJpanel.this.labelList[3].setVisible(true);
                                RefiningEquiJpanel.this.labelList[4].setText("九彩");
                                RefiningEquiJpanel.this.labelList[4].setVisible(true);
                                RefiningEquiJpanel.this.labelList[5].setText("九彩");
                                RefiningEquiJpanel.this.labelList[5].setVisible(true);
                                break;
                            }
                            case 1: {
                                RefiningEquiJpanel.this.labelList[0].setText("护身符");
                                RefiningEquiJpanel.this.labelList[0].setVisible(true);
                                RefiningEquiJpanel.this.labelList[1].setText("内丹精华");
                                RefiningEquiJpanel.this.labelList[1].setVisible(true);
                                RefiningEquiJpanel.this.labelList[2].setText("血玲珑");
                                RefiningEquiJpanel.this.labelList[2].setVisible(true);
                                RefiningEquiJpanel.this.labelList[3].setText("九彩");
                                RefiningEquiJpanel.this.labelList[3].setVisible(true);
                                RefiningEquiJpanel.this.labelList[4].setText("九彩");
                                RefiningEquiJpanel.this.labelList[4].setVisible(true);
                                RefiningEquiJpanel.this.labelList[5].setText("九彩");
                                RefiningEquiJpanel.this.labelList[5].setVisible(true);
                                break;
                            }
                            case 2: {
                                RefiningEquiJpanel.this.labelList[0].setText("仙器");
                                RefiningEquiJpanel.this.labelList[0].setVisible(true);
                                RefiningEquiJpanel.this.labelList[1].setText("仙器精华");
                                RefiningEquiJpanel.this.labelList[1].setVisible(true);
                                RefiningEquiJpanel.this.labelList[2].setText("");
                                RefiningEquiJpanel.this.labelList[2].setVisible(false);
                                RefiningEquiJpanel.this.labelList[3].setText("");
                                RefiningEquiJpanel.this.labelList[3].setVisible(false);
                                RefiningEquiJpanel.this.labelList[4].setText("");
                                RefiningEquiJpanel.this.labelList[4].setVisible(false);
                                RefiningEquiJpanel.this.labelList[5].setText("");
                                RefiningEquiJpanel.this.labelList[5].setVisible(false);
                                break;
                            }
                            case 3: {
                                RefiningEquiJpanel.this.labelList[0].setText("神兵");
                                RefiningEquiJpanel.this.labelList[0].setVisible(true);
                                RefiningEquiJpanel.this.labelList[1].setText("神兵石");
                                RefiningEquiJpanel.this.labelList[1].setVisible(true);
                                RefiningEquiJpanel.this.labelList[2].setText("");
                                RefiningEquiJpanel.this.labelList[2].setVisible(false);
                                RefiningEquiJpanel.this.labelList[3].setText("");
                                RefiningEquiJpanel.this.labelList[3].setVisible(false);
                                RefiningEquiJpanel.this.labelList[4].setText("");
                                RefiningEquiJpanel.this.labelList[4].setVisible(false);
                                RefiningEquiJpanel.this.labelList[5].setText("");
                                RefiningEquiJpanel.this.labelList[5].setVisible(false);
                                break;
                            }
                            case 4: {
                                RefiningEquiJpanel.this.labelList[0].setText("高级装备");
                                RefiningEquiJpanel.this.labelList[0].setVisible(true);
                                RefiningEquiJpanel.this.labelList[1].setText("对应矿石");
                                RefiningEquiJpanel.this.labelList[1].setVisible(true);
                                RefiningEquiJpanel.this.labelList[2].setText("血玲珑");
                                RefiningEquiJpanel.this.labelList[2].setVisible(true);
                                RefiningEquiJpanel.this.labelList[3].setText("巫铸材料");
                                RefiningEquiJpanel.this.labelList[3].setVisible(true);
                                RefiningEquiJpanel.this.labelList[4].setText("");
                                RefiningEquiJpanel.this.labelList[4].setVisible(false);
                                RefiningEquiJpanel.this.labelList[5].setText("");
                                RefiningEquiJpanel.this.labelList[5].setVisible(false);
                                break;
                            }
                            case 5: {
                                RefiningEquiJpanel.this.labelList[0].setText("伙伴装备");
                                RefiningEquiJpanel.this.labelList[0].setVisible(true);
                                RefiningEquiJpanel.this.labelList[1].setText("龙鳞");
                                RefiningEquiJpanel.this.labelList[1].setVisible(true);
                                RefiningEquiJpanel.this.labelList[2].setText("");
                                RefiningEquiJpanel.this.labelList[2].setVisible(false);
                                RefiningEquiJpanel.this.labelList[3].setText("");
                                RefiningEquiJpanel.this.labelList[3].setVisible(false);
                                RefiningEquiJpanel.this.labelList[4].setText("");
                                RefiningEquiJpanel.this.labelList[4].setVisible(false);
                                RefiningEquiJpanel.this.labelList[5].setText("");
                                RefiningEquiJpanel.this.labelList[5].setVisible(false);
                                break;
                            }
                            case 6: {
                                RefiningEquiJpanel.this.labelList[0].setText("升级高装");
                                RefiningEquiJpanel.this.labelList[0].setVisible(true);
                                RefiningEquiJpanel.this.labelList[1].setText("补天神石");
                                RefiningEquiJpanel.this.labelList[1].setVisible(true);
                                RefiningEquiJpanel.this.labelList[2].setText("");
                                RefiningEquiJpanel.this.labelList[2].setVisible(false);
                                RefiningEquiJpanel.this.labelList[3].setText("");
                                RefiningEquiJpanel.this.labelList[3].setVisible(false);
                                RefiningEquiJpanel.this.labelList[4].setText("");
                                RefiningEquiJpanel.this.labelList[4].setVisible(false);
                                RefiningEquiJpanel.this.labelList[5].setText("");
                                RefiningEquiJpanel.this.labelList[5].setVisible(false);
                                break;
                            }
                            case 7: {
                                RefiningEquiJpanel.this.labelList[0].setText("14级高装");
                                RefiningEquiJpanel.this.labelList[0].setVisible(true);
                                RefiningEquiJpanel.this.labelList[1].setText("六魂之玉");
                                RefiningEquiJpanel.this.labelList[1].setVisible(true);
                                RefiningEquiJpanel.this.labelList[2].setText("");
                                RefiningEquiJpanel.this.labelList[2].setVisible(false);
                                RefiningEquiJpanel.this.labelList[3].setText("");
                                RefiningEquiJpanel.this.labelList[3].setVisible(false);
                                RefiningEquiJpanel.this.labelList[4].setText("");
                                RefiningEquiJpanel.this.labelList[4].setVisible(false);
                                RefiningEquiJpanel.this.labelList[5].setText("");
                                RefiningEquiJpanel.this.labelList[5].setVisible(false);
                                break;
                            }
                            case 8: {
                                RefiningEquiJpanel.this.labelList[0].setText("15级高装");
                                RefiningEquiJpanel.this.labelList[0].setVisible(true);
                                RefiningEquiJpanel.this.labelList[1].setText("无量琉璃");
                                RefiningEquiJpanel.this.labelList[1].setVisible(true);
                                RefiningEquiJpanel.this.labelList[2].setText("");
                                RefiningEquiJpanel.this.labelList[2].setVisible(false);
                                RefiningEquiJpanel.this.labelList[3].setText("");
                                RefiningEquiJpanel.this.labelList[3].setVisible(false);
                                RefiningEquiJpanel.this.labelList[4].setText("");
                                RefiningEquiJpanel.this.labelList[4].setVisible(false);
                                RefiningEquiJpanel.this.labelList[5].setText("");
                                RefiningEquiJpanel.this.labelList[5].setVisible(false);
                                break;
                            }
                        }
                    }
                    else {
                        switch (RefiningEquiJpanel.RefiningEquilist.getSelectedIndex()) {
                            case 0: {
                                RefiningEquiJpanel.this.labelList[0].setText("配饰");
                                RefiningEquiJpanel.this.labelList[0].setVisible(true);
                                RefiningEquiJpanel.this.labelList[1].setText("配饰精华");
                                RefiningEquiJpanel.this.labelList[1].setVisible(true);
                                RefiningEquiJpanel.this.labelList[2].setText("");
                                RefiningEquiJpanel.this.labelList[2].setVisible(false);
                                RefiningEquiJpanel.this.labelList[3].setText("");
                                RefiningEquiJpanel.this.labelList[3].setVisible(false);
                                RefiningEquiJpanel.this.labelList[4].setText("");
                                RefiningEquiJpanel.this.labelList[4].setVisible(false);
                                RefiningEquiJpanel.this.labelList[5].setText("");
                                RefiningEquiJpanel.this.labelList[5].setVisible(false);
                                break;
                            }
                            case 1: {
                                RefiningEquiJpanel.this.labelList[0].setText("配饰");
                                RefiningEquiJpanel.this.labelList[0].setVisible(true);
                                RefiningEquiJpanel.this.labelList[1].setText("对应矿石");
                                RefiningEquiJpanel.this.labelList[1].setVisible(true);
                                RefiningEquiJpanel.this.labelList[2].setText("");
                                RefiningEquiJpanel.this.labelList[2].setVisible(false);
                                RefiningEquiJpanel.this.labelList[3].setText("");
                                RefiningEquiJpanel.this.labelList[3].setVisible(false);
                                RefiningEquiJpanel.this.labelList[4].setText("");
                                RefiningEquiJpanel.this.labelList[4].setVisible(false);
                                RefiningEquiJpanel.this.labelList[5].setText("");
                                RefiningEquiJpanel.this.labelList[5].setVisible(false);
                                break;
                            }
                            case 2: {
                                RefiningEquiJpanel.this.labelList[0].setText("主护身符");
                                RefiningEquiJpanel.this.labelList[0].setVisible(true);
                                RefiningEquiJpanel.this.labelList[1].setText("护身符");
                                RefiningEquiJpanel.this.labelList[1].setVisible(true);
                                RefiningEquiJpanel.this.labelList[2].setText("");
                                RefiningEquiJpanel.this.labelList[2].setVisible(false);
                                RefiningEquiJpanel.this.labelList[3].setText("");
                                RefiningEquiJpanel.this.labelList[3].setVisible(false);
                                RefiningEquiJpanel.this.labelList[4].setText("");
                                RefiningEquiJpanel.this.labelList[4].setVisible(false);
                                RefiningEquiJpanel.this.labelList[5].setText("");
                                RefiningEquiJpanel.this.labelList[5].setVisible(false);
                                break;
                            }
                            case 3: {
                                RefiningEquiJpanel.this.labelList[0].setText("护身符");
                                RefiningEquiJpanel.this.labelList[0].setVisible(true);
                                RefiningEquiJpanel.this.labelList[1].setText("六魂之玉");
                                RefiningEquiJpanel.this.labelList[1].setVisible(true);
                                RefiningEquiJpanel.this.labelList[2].setText("");
                                RefiningEquiJpanel.this.labelList[2].setVisible(false);
                                RefiningEquiJpanel.this.labelList[3].setText("");
                                RefiningEquiJpanel.this.labelList[3].setVisible(false);
                                RefiningEquiJpanel.this.labelList[4].setText("");
                                RefiningEquiJpanel.this.labelList[4].setVisible(false);
                                RefiningEquiJpanel.this.labelList[5].setText("");
                                RefiningEquiJpanel.this.labelList[5].setVisible(false);
                                break;
                            }
                            case 4: {
                                RefiningEquiJpanel.this.labelList[0].setText("护身符");
                                RefiningEquiJpanel.this.labelList[0].setVisible(true);
                                RefiningEquiJpanel.this.labelList[1].setText("补天神石");
                                RefiningEquiJpanel.this.labelList[1].setVisible(true);
                                RefiningEquiJpanel.this.labelList[2].setText("");
                                RefiningEquiJpanel.this.labelList[2].setVisible(false);
                                RefiningEquiJpanel.this.labelList[3].setText("");
                                RefiningEquiJpanel.this.labelList[3].setVisible(false);
                                RefiningEquiJpanel.this.labelList[4].setText("");
                                RefiningEquiJpanel.this.labelList[4].setVisible(false);
                                RefiningEquiJpanel.this.labelList[5].setText("");
                                RefiningEquiJpanel.this.labelList[5].setVisible(false);
                                break;
                            }
                            case 5: {
                                RefiningEquiJpanel.this.labelList[0].setText("配饰");
                                RefiningEquiJpanel.this.labelList[0].setVisible(true);
                                RefiningEquiJpanel.this.labelList[1].setText("补天");
                                RefiningEquiJpanel.this.labelList[1].setVisible(true);
                                RefiningEquiJpanel.this.labelList[2].setText("血玲珑");
                                RefiningEquiJpanel.this.labelList[2].setVisible(true);
                                RefiningEquiJpanel.this.labelList[3].setText("");
                                RefiningEquiJpanel.this.labelList[3].setVisible(false);
                                RefiningEquiJpanel.this.labelList[4].setText("");
                                RefiningEquiJpanel.this.labelList[4].setVisible(false);
                                RefiningEquiJpanel.this.labelList[5].setText("");
                                RefiningEquiJpanel.this.labelList[5].setVisible(false);
                                break;
                            }
                            case 6: {
                                RefiningEquiJpanel.this.labelList[0].setText("配饰");
                                RefiningEquiJpanel.this.labelList[0].setVisible(true);
                                RefiningEquiJpanel.this.labelList[1].setText("粹玉");
                                RefiningEquiJpanel.this.labelList[1].setVisible(true);
                                RefiningEquiJpanel.this.labelList[2].setText("");
                                RefiningEquiJpanel.this.labelList[2].setVisible(false);
                                RefiningEquiJpanel.this.labelList[3].setText("");
                                RefiningEquiJpanel.this.labelList[3].setVisible(false);
                                RefiningEquiJpanel.this.labelList[4].setText("");
                                RefiningEquiJpanel.this.labelList[4].setVisible(false);
                                RefiningEquiJpanel.this.labelList[5].setText("");
                                RefiningEquiJpanel.this.labelList[5].setVisible(false);
                                break;
                            }
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
            RefiningEquiJpanel.RefiningEquilist.addMouseMotionListener(new MouseMotionListener() {
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
                    if (index == RefiningEquiJpanel.RefiningEquilist.getSelectedIndex()) {
                        this.setForeground(Color.white);
                    }
                    else {
                        this.setForeground(Color.green);
                    }
                    return this;
                }
            };
            RefiningEquiJpanel.RefiningEquilist.setCellRenderer(renderer);
            (this.jScrollPane = new JScrollPane(RefiningEquiJpanel.RefiningEquilist)).setVerticalScrollBarPolicy(22);
            this.jScrollPane.getVerticalScrollBar().setUI(new SrcollPanelUI());
            this.jScrollPane.getViewport().setOpaque(false);
            this.jScrollPane.setOpaque(false);
            this.jScrollPane.setBounds(48, 72, 165, 200);
            this.jScrollPane.setBorder(BorderFactory.createEmptyBorder());
            this.jScrollPane.setHorizontalScrollBarPolicy(31);
            this.add(this.jScrollPane);
            this.listModel.add(0, "炼化普通装备");
            this.listModel.add(1, "炼化护身符");
            this.listModel.add(2, "炼化仙器");
            this.listModel.add(3, "炼化神兵");
            this.listModel.add(4, "巫铸");
            this.listModel.add(5, "伙伴装备品质");
            this.listModel.add(6, "打造11-14装备");
            this.listModel.add(7, "打造/重铸15装备");
            this.listModel.add(8, "打造/重铸16装备");
        }
        else {
            this.setPreferredSize(new Dimension(536, 481));
            this.setLayout(null);
            this.setOpaque(false);
            (this.clearAll = new GoodPanelBtn("inkImg/hongmu/a3.png", 1, "整")).addMouseListener(new WLLMouslisten(17));
            this.clearAll.setBounds(168, 290, 17, 17);
            this.add(this.clearAll);
            (this.workshopBtn = new RefineOperBtn("inkImg/hongmu/w50.png", 1, UIUtils.COLOR_BTNPUTONG, UIUtils.TEXT_FONT, this, 0, "?")).setBounds(421, 125, 59, 26);
            this.add(this.workshopBtn);
            for (int i = 0; i < 6; ++i) {
                int row = i % 3;
                int col = i / 3;
                (this.labGoods[i] = new JLabel()).addMouseListener(new RefinersMouslisten(this, 24 + i));
                this.labGoods[i].setBounds(226 + row * 67, 99 + col * 65, 54, 51);
                (this.labelList[i] = new JLabel()).setBounds(226 + row * 67 + 2, 99 + col * 65, 70, 51);
                this.labelList[i].setForeground(Color.red);
                this.labelList[i].setVisible(false);
                this.add(this.labelList[i]);
                this.add(this.labGoods[i]);
            }
            for (int i = 0; i < 24; ++i) {
                int row = i % 6;
                int col = i / 6;
                (this.GoodsListLabel[i] = new JLabel()).addMouseListener(new RefinersMouslisten(this, i));
                this.GoodsListLabel[i].setBounds(193 + row * 51, 298 + col * 51, 49, 49);
                this.add(this.GoodsListLabel[i]);
            }
            this.btnrights = new goodbtn[6];
            for (int i = 0; i < this.btnrights.length; ++i) {
                (this.btnrights[i] = new goodbtn("inkImg/hongmu/SBG.png", 0, this, i)).setBounds(500, 296 + i * 33, 24, 33);
                this.add(this.btnrights[i]);
            }
            this.listModel = new DefaultListModel<>();
            (RefiningEquiJpanel.RefiningEquilist = new JList<String>() {
                {
                    this.setOpaque(false);
                }
            }).setSelectionBackground(new Color(33, 42, 52));
            RefiningEquiJpanel.RefiningEquilist.setSelectionForeground(Color.white);
            RefiningEquiJpanel.RefiningEquilist.setForeground(Color.white);
            RefiningEquiJpanel.RefiningEquilist.setFont(new Font("微软雅黑", 1, 14));
            RefiningEquiJpanel.RefiningEquilist.setBackground(UIUtils.Color_BACK);
            RefiningEquiJpanel.RefiningEquilist.setModel(this.listModel);
            RefiningEquiJpanel.RefiningEquilist.setSelectionMode(0);
            RefiningEquiJpanel.RefiningEquilist.addMouseListener(new MouseListener() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    for (int i = 0; i <= RefiningEquiJpanel.this.labelList.length - 1; ++i) {
                        RefiningEquiJpanel.this.labelList[i].setVisible(false);
                        RefiningEquiJpanel.this.labelList[i].setVerticalTextPosition(0);
                        RefiningEquiJpanel.this.labelList[i].setForeground(Color.white);
                        RefiningEquiJpanel.this.labelList[i].setFont(UIUtils.TEXT_FONT);
                        RefiningEquiJpanel.this.labelList[i].setVisible(true);
                    }
                    if (RefiningEquiJpanel.this.type == 1) {
                        switch (RefiningEquiJpanel.RefiningEquilist.getSelectedIndex()) {
                            case 0: {
                                RefiningEquiJpanel.this.labelList[0].setText("普通装备");
                                RefiningEquiJpanel.this.labelList[0].setVisible(true);
                                RefiningEquiJpanel.this.labelList[1].setText("内丹精华");
                                RefiningEquiJpanel.this.labelList[1].setVisible(true);
                                RefiningEquiJpanel.this.labelList[2].setText("血玲珑");
                                RefiningEquiJpanel.this.labelList[2].setVisible(true);
                                RefiningEquiJpanel.this.labelList[3].setText("九彩");
                                RefiningEquiJpanel.this.labelList[3].setVisible(true);
                                RefiningEquiJpanel.this.labelList[4].setText("九彩");
                                RefiningEquiJpanel.this.labelList[4].setVisible(true);
                                RefiningEquiJpanel.this.labelList[5].setText("九彩");
                                RefiningEquiJpanel.this.labelList[5].setVisible(true);
                                break;
                            }
                            case 1: {
                                RefiningEquiJpanel.this.labelList[0].setText("护身符");
                                RefiningEquiJpanel.this.labelList[0].setVisible(true);
                                RefiningEquiJpanel.this.labelList[1].setText("内丹精华");
                                RefiningEquiJpanel.this.labelList[1].setVisible(true);
                                RefiningEquiJpanel.this.labelList[2].setText("血玲珑");
                                RefiningEquiJpanel.this.labelList[2].setVisible(true);
                                RefiningEquiJpanel.this.labelList[3].setText("九彩");
                                RefiningEquiJpanel.this.labelList[3].setVisible(true);
                                RefiningEquiJpanel.this.labelList[4].setText("九彩");
                                RefiningEquiJpanel.this.labelList[4].setVisible(true);
                                RefiningEquiJpanel.this.labelList[5].setText("九彩");
                                RefiningEquiJpanel.this.labelList[5].setVisible(true);
                                break;
                            }
                            case 2: {
                                RefiningEquiJpanel.this.labelList[0].setText("仙器");
                                RefiningEquiJpanel.this.labelList[0].setVisible(true);
                                RefiningEquiJpanel.this.labelList[1].setText("仙器精华");
                                RefiningEquiJpanel.this.labelList[1].setVisible(true);
                                RefiningEquiJpanel.this.labelList[2].setText("");
                                RefiningEquiJpanel.this.labelList[2].setVisible(false);
                                RefiningEquiJpanel.this.labelList[3].setText("");
                                RefiningEquiJpanel.this.labelList[3].setVisible(false);
                                RefiningEquiJpanel.this.labelList[4].setText("");
                                RefiningEquiJpanel.this.labelList[4].setVisible(false);
                                RefiningEquiJpanel.this.labelList[5].setText("");
                                RefiningEquiJpanel.this.labelList[5].setVisible(false);
                                break;
                            }
                            case 3: {
                                RefiningEquiJpanel.this.labelList[0].setText("神兵");
                                RefiningEquiJpanel.this.labelList[0].setVisible(true);
                                RefiningEquiJpanel.this.labelList[1].setText("神兵石");
                                RefiningEquiJpanel.this.labelList[1].setVisible(true);
                                RefiningEquiJpanel.this.labelList[2].setText("");
                                RefiningEquiJpanel.this.labelList[2].setVisible(false);
                                RefiningEquiJpanel.this.labelList[3].setText("");
                                RefiningEquiJpanel.this.labelList[3].setVisible(false);
                                RefiningEquiJpanel.this.labelList[4].setText("");
                                RefiningEquiJpanel.this.labelList[4].setVisible(false);
                                RefiningEquiJpanel.this.labelList[5].setText("");
                                RefiningEquiJpanel.this.labelList[5].setVisible(false);
                                break;
                            }
                            case 4: {
                                RefiningEquiJpanel.this.labelList[0].setText("高级装备");
                                RefiningEquiJpanel.this.labelList[0].setVisible(true);
                                RefiningEquiJpanel.this.labelList[1].setText("对应矿石");
                                RefiningEquiJpanel.this.labelList[1].setVisible(true);
                                RefiningEquiJpanel.this.labelList[2].setText("血玲珑");
                                RefiningEquiJpanel.this.labelList[2].setVisible(true);
                                RefiningEquiJpanel.this.labelList[3].setText("巫铸材料");
                                RefiningEquiJpanel.this.labelList[3].setVisible(true);
                                RefiningEquiJpanel.this.labelList[4].setText("");
                                RefiningEquiJpanel.this.labelList[4].setVisible(false);
                                RefiningEquiJpanel.this.labelList[5].setText("");
                                RefiningEquiJpanel.this.labelList[5].setVisible(false);
                                break;
                            }
                            case 5: {
                                RefiningEquiJpanel.this.labelList[0].setText("伙伴装备");
                                RefiningEquiJpanel.this.labelList[0].setVisible(true);
                                RefiningEquiJpanel.this.labelList[1].setText("龙鳞");
                                RefiningEquiJpanel.this.labelList[1].setVisible(true);
                                RefiningEquiJpanel.this.labelList[2].setText("");
                                RefiningEquiJpanel.this.labelList[2].setVisible(false);
                                RefiningEquiJpanel.this.labelList[3].setText("");
                                RefiningEquiJpanel.this.labelList[3].setVisible(false);
                                RefiningEquiJpanel.this.labelList[4].setText("");
                                RefiningEquiJpanel.this.labelList[4].setVisible(false);
                                RefiningEquiJpanel.this.labelList[5].setText("");
                                RefiningEquiJpanel.this.labelList[5].setVisible(false);
                                break;
                            }
                            case 6: {
                                RefiningEquiJpanel.this.labelList[0].setText("升级高装");
                                RefiningEquiJpanel.this.labelList[0].setVisible(true);
                                RefiningEquiJpanel.this.labelList[1].setText("补天神石");
                                RefiningEquiJpanel.this.labelList[1].setVisible(true);
                                RefiningEquiJpanel.this.labelList[2].setText("");
                                RefiningEquiJpanel.this.labelList[2].setVisible(false);
                                RefiningEquiJpanel.this.labelList[3].setText("");
                                RefiningEquiJpanel.this.labelList[3].setVisible(false);
                                RefiningEquiJpanel.this.labelList[4].setText("");
                                RefiningEquiJpanel.this.labelList[4].setVisible(false);
                                RefiningEquiJpanel.this.labelList[5].setText("");
                                RefiningEquiJpanel.this.labelList[5].setVisible(false);
                                break;
                            }
                            case 7: {
                                RefiningEquiJpanel.this.labelList[0].setText("14级高装");
                                RefiningEquiJpanel.this.labelList[0].setVisible(true);
                                RefiningEquiJpanel.this.labelList[1].setText("六魂之玉");
                                RefiningEquiJpanel.this.labelList[1].setVisible(true);
                                RefiningEquiJpanel.this.labelList[2].setText("");
                                RefiningEquiJpanel.this.labelList[2].setVisible(false);
                                RefiningEquiJpanel.this.labelList[3].setText("");
                                RefiningEquiJpanel.this.labelList[3].setVisible(false);
                                RefiningEquiJpanel.this.labelList[4].setText("");
                                RefiningEquiJpanel.this.labelList[4].setVisible(false);
                                RefiningEquiJpanel.this.labelList[5].setText("");
                                RefiningEquiJpanel.this.labelList[5].setVisible(false);
                                break;
                            }
                            case 8: {
                                RefiningEquiJpanel.this.labelList[0].setText("15级高装");
                                RefiningEquiJpanel.this.labelList[0].setVisible(true);
                                RefiningEquiJpanel.this.labelList[1].setText("无量琉璃");
                                RefiningEquiJpanel.this.labelList[1].setVisible(true);
                                RefiningEquiJpanel.this.labelList[2].setText("");
                                RefiningEquiJpanel.this.labelList[2].setVisible(false);
                                RefiningEquiJpanel.this.labelList[3].setText("");
                                RefiningEquiJpanel.this.labelList[3].setVisible(false);
                                RefiningEquiJpanel.this.labelList[4].setText("");
                                RefiningEquiJpanel.this.labelList[4].setVisible(false);
                                RefiningEquiJpanel.this.labelList[5].setText("");
                                RefiningEquiJpanel.this.labelList[5].setVisible(false);
                                break;
                            }
                        }
                    }
                    else {
                        switch (RefiningEquiJpanel.RefiningEquilist.getSelectedIndex()) {
                            case 0: {
                                RefiningEquiJpanel.this.labelList[0].setText("配饰");
                                RefiningEquiJpanel.this.labelList[0].setVisible(true);
                                RefiningEquiJpanel.this.labelList[1].setText("配饰精华");
                                RefiningEquiJpanel.this.labelList[1].setVisible(true);
                                RefiningEquiJpanel.this.labelList[2].setText("");
                                RefiningEquiJpanel.this.labelList[2].setVisible(false);
                                RefiningEquiJpanel.this.labelList[3].setText("");
                                RefiningEquiJpanel.this.labelList[3].setVisible(false);
                                RefiningEquiJpanel.this.labelList[4].setText("");
                                RefiningEquiJpanel.this.labelList[4].setVisible(false);
                                RefiningEquiJpanel.this.labelList[5].setText("");
                                RefiningEquiJpanel.this.labelList[5].setVisible(false);
                                break;
                            }
                            case 1: {
                                RefiningEquiJpanel.this.labelList[0].setText("配饰");
                                RefiningEquiJpanel.this.labelList[0].setVisible(true);
                                RefiningEquiJpanel.this.labelList[1].setText("对应矿石");
                                RefiningEquiJpanel.this.labelList[1].setVisible(true);
                                RefiningEquiJpanel.this.labelList[2].setText("");
                                RefiningEquiJpanel.this.labelList[2].setVisible(false);
                                RefiningEquiJpanel.this.labelList[3].setText("");
                                RefiningEquiJpanel.this.labelList[3].setVisible(false);
                                RefiningEquiJpanel.this.labelList[4].setText("");
                                RefiningEquiJpanel.this.labelList[4].setVisible(false);
                                RefiningEquiJpanel.this.labelList[5].setText("");
                                RefiningEquiJpanel.this.labelList[5].setVisible(false);
                                break;
                            }
                            case 2: {
                                RefiningEquiJpanel.this.labelList[0].setText("主护身符");
                                RefiningEquiJpanel.this.labelList[0].setVisible(true);
                                RefiningEquiJpanel.this.labelList[1].setText("护身符");
                                RefiningEquiJpanel.this.labelList[1].setVisible(true);
                                RefiningEquiJpanel.this.labelList[2].setText("");
                                RefiningEquiJpanel.this.labelList[2].setVisible(false);
                                RefiningEquiJpanel.this.labelList[3].setText("");
                                RefiningEquiJpanel.this.labelList[3].setVisible(false);
                                RefiningEquiJpanel.this.labelList[4].setText("");
                                RefiningEquiJpanel.this.labelList[4].setVisible(false);
                                RefiningEquiJpanel.this.labelList[5].setText("");
                                RefiningEquiJpanel.this.labelList[5].setVisible(false);
                                break;
                            }
                            case 3: {
                                RefiningEquiJpanel.this.labelList[0].setText("护身符");
                                RefiningEquiJpanel.this.labelList[0].setVisible(true);
                                RefiningEquiJpanel.this.labelList[1].setText("六魂之玉");
                                RefiningEquiJpanel.this.labelList[1].setVisible(true);
                                RefiningEquiJpanel.this.labelList[2].setText("");
                                RefiningEquiJpanel.this.labelList[2].setVisible(false);
                                RefiningEquiJpanel.this.labelList[3].setText("");
                                RefiningEquiJpanel.this.labelList[3].setVisible(false);
                                RefiningEquiJpanel.this.labelList[4].setText("");
                                RefiningEquiJpanel.this.labelList[4].setVisible(false);
                                RefiningEquiJpanel.this.labelList[5].setText("");
                                RefiningEquiJpanel.this.labelList[5].setVisible(false);
                                break;
                            }
                            case 4: {
                                RefiningEquiJpanel.this.labelList[0].setText("护身符");
                                RefiningEquiJpanel.this.labelList[0].setVisible(true);
                                RefiningEquiJpanel.this.labelList[1].setText("补天神石");
                                RefiningEquiJpanel.this.labelList[1].setVisible(true);
                                RefiningEquiJpanel.this.labelList[2].setText("");
                                RefiningEquiJpanel.this.labelList[2].setVisible(false);
                                RefiningEquiJpanel.this.labelList[3].setText("");
                                RefiningEquiJpanel.this.labelList[3].setVisible(false);
                                RefiningEquiJpanel.this.labelList[4].setText("");
                                RefiningEquiJpanel.this.labelList[4].setVisible(false);
                                RefiningEquiJpanel.this.labelList[5].setText("");
                                RefiningEquiJpanel.this.labelList[5].setVisible(false);
                                break;
                            }
                            case 5: {
                                RefiningEquiJpanel.this.labelList[0].setText("配饰");
                                RefiningEquiJpanel.this.labelList[0].setVisible(true);
                                RefiningEquiJpanel.this.labelList[1].setText("补天");
                                RefiningEquiJpanel.this.labelList[1].setVisible(true);
                                RefiningEquiJpanel.this.labelList[2].setText("血玲珑");
                                RefiningEquiJpanel.this.labelList[2].setVisible(true);
                                RefiningEquiJpanel.this.labelList[3].setText("");
                                RefiningEquiJpanel.this.labelList[3].setVisible(false);
                                RefiningEquiJpanel.this.labelList[4].setText("");
                                RefiningEquiJpanel.this.labelList[4].setVisible(false);
                                RefiningEquiJpanel.this.labelList[5].setText("");
                                RefiningEquiJpanel.this.labelList[5].setVisible(false);
                                break;
                            }
                            case 6: {
                                RefiningEquiJpanel.this.labelList[0].setText("配饰");
                                RefiningEquiJpanel.this.labelList[0].setVisible(true);
                                RefiningEquiJpanel.this.labelList[1].setText("粹玉");
                                RefiningEquiJpanel.this.labelList[1].setVisible(true);
                                RefiningEquiJpanel.this.labelList[2].setText("");
                                RefiningEquiJpanel.this.labelList[2].setVisible(false);
                                RefiningEquiJpanel.this.labelList[3].setText("");
                                RefiningEquiJpanel.this.labelList[3].setVisible(false);
                                RefiningEquiJpanel.this.labelList[4].setText("");
                                RefiningEquiJpanel.this.labelList[4].setVisible(false);
                                RefiningEquiJpanel.this.labelList[5].setText("");
                                RefiningEquiJpanel.this.labelList[5].setVisible(false);
                                break;
                            }
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
            RefiningEquiJpanel.RefiningEquilist.addMouseMotionListener(new MouseMotionListener() {
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
                    if (index == RefiningEquiJpanel.RefiningEquilist.getSelectedIndex()) {
                        this.setForeground(Color.white);
                    }
                    else {
                        this.setForeground(Color.green);
                    }
                    return this;
                }
            };
            RefiningEquiJpanel.RefiningEquilist.setCellRenderer(renderer);
            (this.jScrollPane = new JScrollPane(RefiningEquiJpanel.RefiningEquilist)).setVerticalScrollBarPolicy(22);
            this.jScrollPane.getVerticalScrollBar().setUI(new SrcollPanelUI());
            this.jScrollPane.getViewport().setOpaque(false);
            this.jScrollPane.setOpaque(false);
            this.jScrollPane.setBounds(28, 83, 165, 200);
            this.jScrollPane.setBorder(BorderFactory.createEmptyBorder());
            this.jScrollPane.setHorizontalScrollBarPolicy(31);
            this.add(this.jScrollPane);
            this.listModel.add(0, "炼化普通装备");
            this.listModel.add(1, "炼化护身符");
            this.listModel.add(2, "炼化仙器");
            this.listModel.add(3, "炼化神兵");
            this.listModel.add(4, "巫铸");
            this.listModel.add(5, "伙伴装备品质");
            this.listModel.add(6, "打造11-14装备");
            this.listModel.add(7, "打造/重铸15装备");
            this.listModel.add(8, "打造/重铸16装备");
        }
    }
    
    public void ClickGood(Goodstable good, int path) {
        if (path < 24) {
            boolean a = true;
            if (good != null && (long)good.getType() >= 497L && (long)good.getType() <= 499L && (long)good.getType() >= 810L && (long)good.getType() <= 815L) {
                int i = 1;
                if ((long)good.getType() == 497L) {
                    i = 1;
                }
                else if ((long)good.getType() == 499L) {
                    i = 2;
                }
                else if ((long)good.getType() == 498L || ((long)good.getType() >= 810L && (long)good.getType() <= 815L)) {
                    i = 3;
                }
                if (this.goods[i] == null) {
                    this.change(good, i);
                    a = false;
                }
                else if (this.goods[i].getType() != good.getType()) {
                    this.change(good, i);
                    a = false;
                }
            }
            if (a) {
                int i = 0;
                while (i < this.goods.length) {
                    if (this.goods[i] == null) {
                        this.change(good, i);
                        break;
                    }
                    else {
                        ++i;
                    }
                }
            }
        }
        else {
            this.change(null, path - 24);
        }
        String v = RefiningUtil.detection(this.goods, this.type);
        if (!this.workshopBtn.getText().equals(v)) {
            this.workshopBtn.setText(v);
        }
    }
    
    public void change(Goodstable good, int path) {
        this.goods[path] = good;
        if (good != null) {
            this.labGoods[path].setIcon(GoodsListFromServerUntil.imgpathAdaptive(good.getSkin(), 49, 49));
            this.getLabelList()[path].setVisible(false);
        }
        else {
            if (this.getLabelList()[path].getText() != null) {
                this.getLabelList()[path].setVisible(true);
            }
            this.labGoods[path].setIcon(null);
        }
    }
    
    public void ResetGood(Goodstable good) {
        int i = 0;
        while (i < this.goods.length) {
            if (this.goods[i] != null && this.goods[i].getRgid().compareTo(good.getRgid()) == 0) {
                if ((int)good.getUsetime() <= 0) {
                    this.goods[i] = null;
                    this.labGoods[i].setIcon(null);
                }
                else {
                    this.goods[i] = good;
                    this.labGoods[i].setIcon(GoodsListFromServerUntil.imgpathAdaptive(good.getSkin(), 49, 49));
                }
                String v = RefiningUtil.detection(this.goods, this.type);
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
    
    public void changeFrom(int type) {
        if (this.type == type) {
            return;
        }
        for (int i = 0; i < this.goods.length; ++i) {
            this.change(null, i);
        }
        this.type = type;
        this.icon2 = null;
        if (type == 1) {
            this.listModel.removeAllElements();
            this.listModel.add(0, "炼化普通装备");
            this.listModel.add(1, "炼化护身符");
            this.listModel.add(2, "炼化仙器");
            this.listModel.add(3, "炼化神兵");
            this.listModel.add(4, "巫铸");
            this.listModel.add(5, "伙伴装备品质");
            this.listModel.add(6, "打造11-14装备");
            this.listModel.add(7, "打造/重铸15装备");
            this.listModel.add(8, "打造/重铸16装备");
        }
        else {
            this.listModel.removeAllElements();
            this.listModel.add(0, "配饰培养");
            this.listModel.add(1, "配饰重铸");
            this.listModel.add(2, "护身符培养");
            this.listModel.add(3, "护身符升级");
            this.listModel.add(4, "护身符重铸");
            this.listModel.add(5, "配饰点粹");
            this.listModel.add(6, "配饰点粹升级");
        }
        for (int i = 0; i <= this.labelList.length - 1; ++i) {
            this.labelList[i].setText(null);
            this.labelList[i].setVisible(false);
        }
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (MyIsif.getStyle().equals("水墨")) {
            if (this.icon == null) {
                this.icon = CutButtonImage.getImage("inkImg/background1/B238.png", -1, -1);
            }
            if (this.icon2 == null) {
                this.icon2 = new ImageIcon("inkImg/background1/B247.png");
            }
            g.drawImage(this.icon.getImage(), 0, 0, this);
            g.drawImage(this.icon2.getImage(), 214, 55, this);
            GoodsListFromServerUntil.draw(g, 218, 287);
            Util.drawMoney(g, 322, 231);
            if (this.money != null) {
                Util.drawPrice(g, this.money, 322, 258);
            }
        }
        else {
            if (this.icon == null) {
                this.icon = new ImageIcon("img/xy2uiimg/workshop_refine.png");
            }
            g.drawImage(this.icon.getImage(), 0, 61, this);
            GoodsListFromServerUntil.draw(g, 193, 298);
            Util.drawMoney(g, 297, 242);
            Util.drawMoneyS(g, 87, 325);
            if (this.money != null) {
                Util.drawPrice(g, this.money, 297, 269);
            }
        }
    }
    
    public JLabel[] getGoodsListLabel() {
        return this.GoodsListLabel;
    }
    
    public void setGoodsListLabel(JLabel[] goodsListLabel) {
        this.GoodsListLabel = goodsListLabel;
    }
    
    public goodbtn[] getBtnrights() {
        return this.btnrights;
    }
    
    public void setBtnrights(goodbtn[] btnrights) {
        this.btnrights = btnrights;
    }
    
    public int getType() {
        return this.type;
    }
    
    public void setType(int type) {
        this.type = type;
    }
    
    public Goodstable[] getGoods() {
        return this.goods;
    }
    
    public void setGoods(Goodstable[] goods) {
        this.goods = goods;
    }
    
    public RefineOperBtn getWorkshopBtn() {
        return this.workshopBtn;
    }
    
    public void setWorkshopBtn(RefineOperBtn workshopBtn) {
        this.workshopBtn = workshopBtn;
    }
    
    public JLabel[] getLabGoods() {
        return this.labGoods;
    }
    
    public void setLabGoods(JLabel[] labGoods) {
        this.labGoods = labGoods;
    }
    
    public JLabel[] getLabelList() {
        return this.labelList;
    }
    
    public void setLabelList(JLabel[] labelList) {
        this.labelList = labelList;
    }
}
