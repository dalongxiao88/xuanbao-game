package org.cbg.panel;

import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import org.come.until.CutButtonImage;
import java.awt.Color;
import org.cbg.until.TraslationTableUntil;
import com.tool.tcpimg.UIUtils;
import java.awt.Dimension;
import com.updateNew.MyIsif;
import javax.swing.ImageIcon;
import javax.swing.JScrollPane;
import javax.swing.JLabel;
import org.cbg.btn.TrslationMainBtn;
import javax.swing.JPanel;

public class TraslationWantBuyShouyeSousuojieguoJpanel extends JPanel
{
    private TrslationMainBtn back;
    private JLabel yema;
    private JLabel upSort;
    private JLabel downSort;
    private JLabel showperioditems;
    private int showperioditemsFlag;
    private int showOrder;
    private JScrollPane jScrollPane;
    private TrslationMainBtn shouye;
    private TrslationMainBtn moye;
    private TrslationMainBtn leftArrows;
    private TrslationMainBtn rightArrows;
    private int dangqianyeshu;
    private int zuidayema;
    private ImageIcon icon1;
    
    public TraslationWantBuyShouyeSousuojieguoJpanel() {
        this.showperioditemsFlag = 0;
        this.showOrder = 0;
        this.dangqianyeshu = 1;
        this.zuidayema = 50;
        if (MyIsif.getStyle().equals("水墨")) {
            this.setPreferredSize(new Dimension(590, 380));
            this.setOpaque(false);
            this.setLayout(null);
            (this.back = new TrslationMainBtn("inkImg/button/2.png", 1, UIUtils.COLOR_BTNTEXT, UIUtils.TEXT_FONT, 16, "返回", this)).setBounds(9, 9, 34, 17);
            this.add(this.back);
            (this.jScrollPane = new JScrollPane()).setOpaque(false);
            this.jScrollPane.setBorder(null);
            (this.jScrollPane = new JScrollPane()).setBounds(8, 45, 570, 303);
            TraslationTableUntil.TableModel(this.jScrollPane, 10);
            this.add(this.jScrollPane);
            (this.yema = new JLabel(this.dangqianyeshu + "/" + this.zuidayema)).setBounds(258, 356, 58, 17);
            this.yema.setForeground(Color.white);
            this.yema.setOpaque(false);
            this.yema.setHorizontalAlignment(0);
            this.add(this.yema);
            (this.shouye = new TrslationMainBtn("inkImg/button/2.png", 1, UIUtils.COLOR_BTNTEXT, UIUtils.TEXT_FONT, 17, "首页", this)).setBounds(196, 354, 34, 17);
            this.shouye.setOpaque(false);
            this.shouye.setBorder(null);
            this.add(this.shouye);
            (this.leftArrows = new TrslationMainBtn("inkImg/button/10.png", 1, 18, "", this)).setBounds(235, 354, 18, 18);
            this.leftArrows.setBorder(null);
            this.leftArrows.setBackground(null);
            this.leftArrows.setOpaque(false);
            this.add(this.leftArrows);
            (this.moye = new TrslationMainBtn("inkImg/button/2.png", 1, UIUtils.COLOR_BTNTEXT, UIUtils.TEXT_FONT, 19, "末页", this)).setBounds(342, 354, 34, 17);
            this.moye.setBorder(null);
            this.moye.setBackground(null);
            this.moye.setOpaque(false);
            this.add(this.moye);
            (this.rightArrows = new TrslationMainBtn("inkImg/button/9.png", 1, 20, "", this)).setBounds(320, 354, 18, 18);
            this.rightArrows.setBorder(null);
            this.rightArrows.setBackground(null);
            this.rightArrows.setOpaque(false);
            this.add(this.rightArrows);
            (this.upSort = new JLabel()).setBounds(333, 31, 6, 11);
            this.upSort.setBorder(null);
            this.upSort.setBackground(null);
            this.upSort.setOpaque(false);
            this.upSort.setIcon(CutButtonImage.getImage("img/xy2uiimg/↑(白色)w6,h11px.png", 6, 11));
            this.add(this.upSort);
            this.upSort.addMouseListener(new MouseListener() {
                @Override
                public void mouseReleased(MouseEvent e) {
                }
                
                @Override
                public void mousePressed(MouseEvent e) {
                }
                
                @Override
                public void mouseExited(MouseEvent e) {
                }
                
                @Override
                public void mouseEntered(MouseEvent e) {
                }
                
                @Override
                public void mouseClicked(MouseEvent e) {
                    TraslationWantBuyShouyeSousuojieguoJpanel.this.upSort.setIcon(CutButtonImage.getImage("img/xy2uiimg/↑(红色)w6,h11px.png", 6, 11));
                    TraslationWantBuyShouyeSousuojieguoJpanel.this.downSort.setIcon(CutButtonImage.getImage("img/xy2uiimg/↓(白色)w6,h11px.png", 6, 11));
                    TraslationWantBuyShouyeSousuojieguoJpanel.this.dangqianyeshu = 1;
                    TraslationWantBuyShouyeSousuojieguoJpanel.this.showOrder = 1;
                    TraslationWantBuyShouyeJpanel.souyeSousuo(Integer.valueOf(TraslationWantBuyShouyeSousuojieguoJpanel.this.dangqianyeshu), Integer.valueOf(TraslationWantBuyShouyeSousuojieguoJpanel.this.showperioditemsFlag), Integer.valueOf(TraslationWantBuyShouyeSousuojieguoJpanel.this.showOrder), TraslationWantBuyShouyeJpanel.searchGoodsBean);
                }
            });
            (this.downSort = new JLabel()).setBounds(343, 31, 6, 11);
            this.downSort.setBorder(null);
            this.downSort.setBackground(null);
            this.downSort.setOpaque(false);
            this.downSort.setIcon(CutButtonImage.getImage("img/xy2uiimg/↓(白色)w6,h11px.png", 6, 11));
            this.add(this.downSort);
            this.downSort.addMouseListener(new MouseListener() {
                @Override
                public void mouseReleased(MouseEvent e) {
                }
                
                @Override
                public void mousePressed(MouseEvent e) {
                }
                
                @Override
                public void mouseExited(MouseEvent e) {
                }
                
                @Override
                public void mouseEntered(MouseEvent e) {
                }
                
                @Override
                public void mouseClicked(MouseEvent e) {
                    TraslationWantBuyShouyeSousuojieguoJpanel.this.upSort.setIcon(CutButtonImage.getImage("img/xy2uiimg/↑(白色)w6,h11px.png", 6, 11));
                    TraslationWantBuyShouyeSousuojieguoJpanel.this.downSort.setIcon(CutButtonImage.getImage("img/xy2uiimg/↓(红色)w6,h11px.png", 6, 11));
                    TraslationWantBuyShouyeSousuojieguoJpanel.this.dangqianyeshu = 1;
                    TraslationWantBuyShouyeSousuojieguoJpanel.this.showOrder = 2;
                    TraslationWantBuyShouyeJpanel.souyeSousuo(Integer.valueOf(TraslationWantBuyShouyeSousuojieguoJpanel.this.dangqianyeshu), Integer.valueOf(TraslationWantBuyShouyeSousuojieguoJpanel.this.showperioditemsFlag), Integer.valueOf(TraslationWantBuyShouyeSousuojieguoJpanel.this.showOrder), TraslationWantBuyShouyeJpanel.searchGoodsBean);
                }
            });
            (this.showperioditems = new JLabel()).setBounds(446, 6, 15, 15);
            this.showperioditems.setOpaque(false);
            this.showperioditems.setBorder(null);
            this.add(this.showperioditems);
            this.showperioditems.addMouseListener(new MouseListener() {
                @Override
                public void mouseReleased(MouseEvent e) {
                }
                
                @Override
                public void mousePressed(MouseEvent e) {
                }
                
                @Override
                public void mouseExited(MouseEvent e) {
                }
                
                @Override
                public void mouseEntered(MouseEvent e) {
                }
                
                @Override
                public void mouseClicked(MouseEvent e) {
                    if (TraslationWantBuyShouyeSousuojieguoJpanel.this.showperioditemsFlag == 0) {
                        TraslationWantBuyShouyeSousuojieguoJpanel.this.showperioditems.setIcon(CutButtonImage.getImage("inkImg/button/13.png", 15, 15));
                        TraslationWantBuyShouyeSousuojieguoJpanel.this.showperioditemsFlag = 1;
                        TraslationWantBuyShouyeJpanel.souyeSousuo(Integer.valueOf(TraslationWantBuyShouyeSousuojieguoJpanel.this.dangqianyeshu), Integer.valueOf(TraslationWantBuyShouyeSousuojieguoJpanel.this.showperioditemsFlag), Integer.valueOf(TraslationWantBuyShouyeSousuojieguoJpanel.this.showOrder), TraslationWantBuyShouyeJpanel.searchGoodsBean);
                    }
                    else {
                        TraslationWantBuyShouyeSousuojieguoJpanel.this.showperioditems.setIcon(null);
                        TraslationWantBuyShouyeSousuojieguoJpanel.this.showperioditemsFlag = 0;
                        TraslationWantBuyShouyeJpanel.souyeSousuo(Integer.valueOf(TraslationWantBuyShouyeSousuojieguoJpanel.this.dangqianyeshu), Integer.valueOf(TraslationWantBuyShouyeSousuojieguoJpanel.this.showperioditemsFlag), Integer.valueOf(TraslationWantBuyShouyeSousuojieguoJpanel.this.showOrder), TraslationWantBuyShouyeJpanel.searchGoodsBean);
                    }
                }
            });
        }
        else {
            this.setPreferredSize(new Dimension(590, 380));
            this.setOpaque(false);
            this.setLayout(null);
            (this.back = new TrslationMainBtn("inkimg/hongmu/21_png.button.tab_nex.png", 1, UIUtils.COLOR_BTNTEXT, UIUtils.TEXT_FONT, 16, "返回", this)).setBounds(9, 9, 34, 17);
            this.add(this.back);
            (this.jScrollPane = new JScrollPane()).setOpaque(false);
            this.jScrollPane.setBorder(null);
            (this.jScrollPane = new JScrollPane()).setBounds(8, 45, 565, 300);
            TraslationTableUntil.TableModel(this.jScrollPane, 10);
            this.add(this.jScrollPane);
            (this.yema = new JLabel(this.dangqianyeshu + "/" + this.zuidayema)).setBounds(258, 356, 58, 17);
            this.yema.setForeground(Color.white);
            this.yema.setOpaque(false);
            this.yema.setHorizontalAlignment(0);
            this.add(this.yema);
            (this.shouye = new TrslationMainBtn("inkimg/hongmu/21_png.button.tab_nex.png", 1, UIUtils.COLOR_BTNTEXT, UIUtils.TEXT_FONT, 17, "首页", this)).setBounds(200, 354, 30, 17);
            this.shouye.setOpaque(false);
            this.shouye.setBorder(null);
            this.add(this.shouye);
            (this.leftArrows = new TrslationMainBtn("img/xy2uiimg/30_png.button.btn_8.png", 1, 18, "", this)).setBounds(235, 354, 18, 18);
            this.leftArrows.setBorder(null);
            this.leftArrows.setBackground(null);
            this.leftArrows.setOpaque(false);
            this.add(this.leftArrows);
            (this.moye = new TrslationMainBtn("inkimg/hongmu/21_png.button.tab_nex.png", 1, UIUtils.COLOR_BTNTEXT, UIUtils.TEXT_FONT, 19, "末页", this)).setBounds(342, 354, 34, 17);
            this.moye.setBorder(null);
            this.moye.setBackground(null);
            this.moye.setOpaque(false);
            this.add(this.moye);
            (this.rightArrows = new TrslationMainBtn("img/xy2uiimg/36_png.button.btn_7.png", 1, 20, "", this)).setBounds(320, 354, 18, 18);
            this.rightArrows.setBorder(null);
            this.rightArrows.setBackground(null);
            this.rightArrows.setOpaque(false);
            this.add(this.rightArrows);
            (this.upSort = new JLabel()).setBounds(333, 31, 6, 11);
            this.upSort.setBorder(null);
            this.upSort.setBackground(null);
            this.upSort.setOpaque(false);
            this.upSort.setIcon(CutButtonImage.getImage("img/xy2uiimg/↑(白色)w6,h11px.png", 6, 11));
            this.add(this.upSort);
            this.upSort.addMouseListener(new MouseListener() {
                @Override
                public void mouseReleased(MouseEvent e) {
                }
                
                @Override
                public void mousePressed(MouseEvent e) {
                }
                
                @Override
                public void mouseExited(MouseEvent e) {
                }
                
                @Override
                public void mouseEntered(MouseEvent e) {
                }
                
                @Override
                public void mouseClicked(MouseEvent e) {
                    TraslationWantBuyShouyeSousuojieguoJpanel.this.upSort.setIcon(CutButtonImage.getImage("img/xy2uiimg/↑(红色)w6,h11px.png", 6, 11));
                    TraslationWantBuyShouyeSousuojieguoJpanel.this.downSort.setIcon(CutButtonImage.getImage("img/xy2uiimg/↓(白色)w6,h11px.png", 6, 11));
                    TraslationWantBuyShouyeSousuojieguoJpanel.this.dangqianyeshu = 1;
                    TraslationWantBuyShouyeSousuojieguoJpanel.this.showOrder = 1;
                    TraslationWantBuyShouyeJpanel.souyeSousuo(Integer.valueOf(TraslationWantBuyShouyeSousuojieguoJpanel.this.dangqianyeshu), Integer.valueOf(TraslationWantBuyShouyeSousuojieguoJpanel.this.showperioditemsFlag), Integer.valueOf(TraslationWantBuyShouyeSousuojieguoJpanel.this.showOrder), TraslationWantBuyShouyeJpanel.searchGoodsBean);
                }
            });
            (this.downSort = new JLabel()).setBounds(343, 31, 6, 11);
            this.downSort.setBorder(null);
            this.downSort.setBackground(null);
            this.downSort.setOpaque(false);
            this.downSort.setIcon(CutButtonImage.getImage("img/xy2uiimg/↓(白色)w6,h11px.png", 6, 11));
            this.add(this.downSort);
            this.downSort.addMouseListener(new MouseListener() {
                @Override
                public void mouseReleased(MouseEvent e) {
                }
                
                @Override
                public void mousePressed(MouseEvent e) {
                }
                
                @Override
                public void mouseExited(MouseEvent e) {
                }
                
                @Override
                public void mouseEntered(MouseEvent e) {
                }
                
                @Override
                public void mouseClicked(MouseEvent e) {
                    TraslationWantBuyShouyeSousuojieguoJpanel.this.upSort.setIcon(CutButtonImage.getImage("img/xy2uiimg/↑(白色)w6,h11px.png", 6, 11));
                    TraslationWantBuyShouyeSousuojieguoJpanel.this.downSort.setIcon(CutButtonImage.getImage("img/xy2uiimg/↓(红色)w6,h11px.png", 6, 11));
                    TraslationWantBuyShouyeSousuojieguoJpanel.this.dangqianyeshu = 1;
                    TraslationWantBuyShouyeSousuojieguoJpanel.this.showOrder = 2;
                    TraslationWantBuyShouyeJpanel.souyeSousuo(Integer.valueOf(TraslationWantBuyShouyeSousuojieguoJpanel.this.dangqianyeshu), Integer.valueOf(TraslationWantBuyShouyeSousuojieguoJpanel.this.showperioditemsFlag), Integer.valueOf(TraslationWantBuyShouyeSousuojieguoJpanel.this.showOrder), TraslationWantBuyShouyeJpanel.searchGoodsBean);
                }
            });
            (this.showperioditems = new JLabel()).setBounds(446, 6, 15, 15);
            this.showperioditems.setOpaque(false);
            this.showperioditems.setBorder(null);
            this.add(this.showperioditems);
            this.showperioditems.addMouseListener(new MouseListener() {
                @Override
                public void mouseReleased(MouseEvent e) {
                }
                
                @Override
                public void mousePressed(MouseEvent e) {
                }
                
                @Override
                public void mouseExited(MouseEvent e) {
                }
                
                @Override
                public void mouseEntered(MouseEvent e) {
                }
                
                @Override
                public void mouseClicked(MouseEvent e) {
                    if (TraslationWantBuyShouyeSousuojieguoJpanel.this.showperioditemsFlag == 0) {
                        TraslationWantBuyShouyeSousuojieguoJpanel.this.showperioditems.setIcon(CutButtonImage.getImage("inkImg/button/13.png", 15, 15));
                        TraslationWantBuyShouyeSousuojieguoJpanel.this.showperioditemsFlag = 1;
                        TraslationWantBuyShouyeJpanel.souyeSousuo(Integer.valueOf(TraslationWantBuyShouyeSousuojieguoJpanel.this.dangqianyeshu), Integer.valueOf(TraslationWantBuyShouyeSousuojieguoJpanel.this.showperioditemsFlag), Integer.valueOf(TraslationWantBuyShouyeSousuojieguoJpanel.this.showOrder), TraslationWantBuyShouyeJpanel.searchGoodsBean);
                    }
                    else {
                        TraslationWantBuyShouyeSousuojieguoJpanel.this.showperioditems.setIcon(null);
                        TraslationWantBuyShouyeSousuojieguoJpanel.this.showperioditemsFlag = 0;
                        TraslationWantBuyShouyeJpanel.souyeSousuo(Integer.valueOf(TraslationWantBuyShouyeSousuojieguoJpanel.this.dangqianyeshu), Integer.valueOf(TraslationWantBuyShouyeSousuojieguoJpanel.this.showperioditemsFlag), Integer.valueOf(TraslationWantBuyShouyeSousuojieguoJpanel.this.showOrder), TraslationWantBuyShouyeJpanel.searchGoodsBean);
                    }
                }
            });
        }
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        if (MyIsif.getStyle().equals("水墨")) {
            super.paintComponent(g);
            if (this.icon1 == null) {
                this.icon1 = new ImageIcon("inkImg/background/31.png");
            }
            g.drawImage(this.icon1.getImage(), 0, 0, 590, 380, this);
        }
        else {
            super.paintComponent(g);
            if (this.icon1 == null) {
                this.icon1 = new ImageIcon("img/xy2uiimg/我要买-首页-搜索w590,h380px，top97,left22px.png");
            }
            g.drawImage(this.icon1.getImage(), 0, 0, 590, 380, this);
        }
    }
    
    public void setPage(int zuidayema) {
        this.zuidayema = zuidayema;
        this.yema.setText(this.dangqianyeshu + "/" + this.zuidayema);
    }
    
    public JLabel getYema() {
        return this.yema;
    }
    
    public TrslationMainBtn getBack() {
        return this.back;
    }
    
    public void setBack(TrslationMainBtn back) {
        this.back = back;
    }
    
    public void setYema(JLabel yema) {
        this.yema = yema;
    }
    
    public JLabel getUpSort() {
        return this.upSort;
    }
    
    public void setUpSort(JLabel upSort) {
        this.upSort = upSort;
    }
    
    public JLabel getDownSort() {
        return this.downSort;
    }
    
    public void setDownSort(JLabel downSort) {
        this.downSort = downSort;
    }
    
    public JLabel getShowperioditems() {
        return this.showperioditems;
    }
    
    public void setShowperioditems(JLabel showperioditems) {
        this.showperioditems = showperioditems;
    }
    
    public int getShowperioditemsFlag() {
        return this.showperioditemsFlag;
    }
    
    public void setShowperioditemsFlag(int showperioditemsFlag) {
        this.showperioditemsFlag = showperioditemsFlag;
    }
    
    public JScrollPane getjScrollPane() {
        return this.jScrollPane;
    }
    
    public void setjScrollPane(JScrollPane jScrollPane) {
        this.jScrollPane = jScrollPane;
    }
    
    public int getDangqianyeshu() {
        return this.dangqianyeshu;
    }
    
    public void setDangqianyeshu(int dangqianyeshu) {
        this.dangqianyeshu = dangqianyeshu;
    }
    
    public int getZuidayema() {
        return this.zuidayema;
    }
    
    public void setZuidayema(int zuidayema) {
        this.zuidayema = zuidayema;
    }
    
    public int getShowOrder() {
        return this.showOrder;
    }
    
    public void setShowOrder(int showOrder) {
        this.showOrder = showOrder;
    }
    
    public ImageIcon getIcon1() {
        return this.icon1;
    }
    
    public void setIcon1(ImageIcon icon1) {
        this.icon1 = icon1;
    }
    
    public TrslationMainBtn getShouye() {
        return this.shouye;
    }
    
    public void setShouye(TrslationMainBtn shouye) {
        this.shouye = shouye;
    }
    
    public TrslationMainBtn getMoye() {
        return this.moye;
    }
    
    public void setMoye(TrslationMainBtn moye) {
        this.moye = moye;
    }
    
    public TrslationMainBtn getLeftArrows() {
        return this.leftArrows;
    }
    
    public void setLeftArrows(TrslationMainBtn leftArrows) {
        this.leftArrows = leftArrows;
    }
    
    public TrslationMainBtn getRightArrows() {
        return this.rightArrows;
    }
    
    public void setRightArrows(TrslationMainBtn rightArrows) {
        this.rightArrows = rightArrows;
    }
}
