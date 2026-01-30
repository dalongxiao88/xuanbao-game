package org.cbg.panel;

import java.awt.Graphics;
import java.awt.Component;
import org.cbg.frame.TrslationMainJframe;
import java.awt.event.MouseEvent;
import java.awt.event.MouseAdapter;
import org.come.until.CutButtonImage;
import org.cbg.until.TraslationTableMygoodsUntil;
import java.awt.Color;
import com.tool.tcpimg.UIUtils;
import java.awt.Dimension;
import com.updateNew.MyIsif;
import javax.swing.ImageIcon;
import javax.swing.JScrollPane;
import org.cbg.btn.TraslationMyMainMygoodsBtn;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class TraslationMyMainMygoodsJpanel extends JPanel
{
    private JLabel yema;
    private TraslationMyMainMygoodsBtn kuaisuquhui;
    private TraslationMyMainMygoodsBtn shouye;
    private TraslationMyMainMygoodsBtn moye;
    private TraslationMyMainMygoodsBtn leftArrows;
    private TraslationMyMainMygoodsBtn rightArrows;
    private JScrollPane jScrollPane;
    private int dangqianyeshu;
    private int zuidayema;
    private JLabel quanxuankuang;
    private JLabel gouxuan;
    private ImageIcon icon;
    
    public TraslationMyMainMygoodsJpanel() {
        this.dangqianyeshu = 1;
        this.zuidayema = 50;
        if (MyIsif.getStyle().equals("水墨")) {
            this.setPreferredSize(new Dimension(590, 380));
            this.setOpaque(false);
            this.setLayout(null);
            this.jScrollPane = new JScrollPane();
            this.kuaisuquhui = new TraslationMyMainMygoodsBtn("inkImg/button/49.png", 1, UIUtils.COLOR_BTNTEXT, UIUtils.TEXT_FONT, 1, "快速取回", this);
            this.yema = new JLabel(this.dangqianyeshu + "/" + this.zuidayema);
            this.shouye = new TraslationMyMainMygoodsBtn("inkImg/button/2.png", 1, UIUtils.COLOR_BTNTEXT, UIUtils.TEXT_FONT, 2, "首页", this);
            this.moye = new TraslationMyMainMygoodsBtn("inkImg/button/2.png", 1, UIUtils.COLOR_BTNTEXT, UIUtils.TEXT_FONT, 3, "末页", this);
            this.leftArrows = new TraslationMyMainMygoodsBtn("inkImg/button/10.png", 1, 4, this);
            this.rightArrows = new TraslationMyMainMygoodsBtn("inkImg/button/9.png", 1, 5, this);
            this.gouxuan = new JLabel();
            this.quanxuankuang = new JLabel();
            this.jScrollPane.setOpaque(false);
            this.kuaisuquhui.setOpaque(false);
            this.yema.setOpaque(false);
            this.shouye.setOpaque(false);
            this.leftArrows.setOpaque(false);
            this.moye.setOpaque(false);
            this.rightArrows.setOpaque(false);
            this.gouxuan.setOpaque(false);
            this.quanxuankuang.setOpaque(false);
            this.jScrollPane.setBorder(null);
            this.kuaisuquhui.setBorder(null);
            this.shouye.setBorder(null);
            this.leftArrows.setBorder(null);
            this.moye.setBorder(null);
            this.rightArrows.setBorder(null);
            this.jScrollPane.setBounds(12, 46, 571, 306);
            this.kuaisuquhui.setBounds(510, 10, 68, 17);
            this.yema.setBounds(238, 356, 58, 17);
            this.shouye.setBounds(187, 354, 34, 17);
            this.leftArrows.setBounds(225, 354, 19, 20);
            this.moye.setBounds(322, 354, 34, 17);
            this.rightArrows.setBounds(300, 354, 19, 20);
            this.gouxuan.setBounds(25, 30, 15, 15);
            this.quanxuankuang.setBounds(25, 30, 15, 15);
            this.yema.setForeground(Color.white);
            TraslationTableMygoodsUntil.TableModel(this.jScrollPane, 10);
            this.jScrollPane.getVerticalScrollBar().setUnitIncrement(50);
            this.gouxuan.setIcon(null);
            this.quanxuankuang.setIcon(CutButtonImage.getImage("inkImg/button/14.png", 15, 15));
            this.yema.setHorizontalAlignment(0);
            this.gouxuan.setName("1");
            this.add(this.jScrollPane);
            this.add(this.kuaisuquhui);
            this.add(this.yema);
            this.add(this.shouye);
            this.add(this.leftArrows);
            this.add(this.moye);
            this.add(this.rightArrows);
            this.add(this.gouxuan);
            this.add(this.quanxuankuang);
            this.gouxuan.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    if ("1".equals(TraslationMyMainMygoodsJpanel.this.gouxuan.getName())) {
                        TraslationMyMainMygoodsJpanel.this.gouxuan.setIcon(CutButtonImage.getImage("inkImg/button/13.png", 15, 15));
                        TraslationMyMainMygoodsJpanel.this.gouxuan.setName("0");
                        TrslationMainJframe.getTrslationMainJframe().setGoodsGouxuangeshu(TrslationMainJframe.getTrslationMainJframe().getGoodsGeshuMax());
                        JPanel view = (JPanel)TraslationMyMainMygoodsJpanel.this.jScrollPane.getViewport().getView();
                        Component[] components = view.getComponents();
                        for (int i = 0; i < components.length; ++i) {
                            JPanel panel = (JPanel)components[i];
                            JLabel component2 = (JLabel)panel.getComponent(3);
                            if (component2.isVisible()) {
                                component2.setName("0");
                                component2.setIcon(CutButtonImage.getImage("inkImg/button/13.png", 15, 15));
                            }
                        }
                    }
                    else {
                        TraslationMyMainMygoodsJpanel.this.gouxuan.setIcon(null);
                        TraslationMyMainMygoodsJpanel.this.gouxuan.setName("1");
                        TrslationMainJframe.getTrslationMainJframe().setGoodsGouxuangeshu(0);
                        JPanel view = (JPanel)TraslationMyMainMygoodsJpanel.this.jScrollPane.getViewport().getView();
                        Component[] components = view.getComponents();
                        for (int i = 0; i < components.length; ++i) {
                            JPanel panel = (JPanel)components[i];
                            JLabel component2 = (JLabel)panel.getComponent(3);
                            if (component2.isVisible()) {
                                component2.setName("1");
                                component2.setIcon(null);
                            }
                        }
                    }
                }
            });
        }
        else {
            this.setPreferredSize(new Dimension(590, 380));
            this.setOpaque(false);
            this.setLayout(null);
            this.jScrollPane = new JScrollPane();
            this.kuaisuquhui = new TraslationMyMainMygoodsBtn("inkImg/hongmu/a7.png", 1, UIUtils.COLOR_BTNTEXT, UIUtils.TEXT_FONT, 1, "快速取回", this);
            this.yema = new JLabel(this.dangqianyeshu + "/" + this.zuidayema);
            this.shouye = new TraslationMyMainMygoodsBtn("inkImg/hongmu/21_png.button.tab_nex.png", 1, UIUtils.COLOR_BTNTEXT, UIUtils.TEXT_FONT, 2, "首页", this);
            this.moye = new TraslationMyMainMygoodsBtn("inkImg/hongmu/21_png.button.tab_nex.png", 1, UIUtils.COLOR_BTNTEXT, UIUtils.TEXT_FONT, 3, "末页", this);
            this.leftArrows = new TraslationMyMainMygoodsBtn("img/xy2uiimg/30_png.button.btn_8.png", 1, 4, this);
            this.rightArrows = new TraslationMyMainMygoodsBtn("img/xy2uiimg/36_png.button.btn_7.png", 1, 5, this);
            this.gouxuan = new JLabel();
            this.quanxuankuang = new JLabel();
            this.jScrollPane.setOpaque(false);
            this.kuaisuquhui.setOpaque(false);
            this.yema.setOpaque(false);
            this.shouye.setOpaque(false);
            this.leftArrows.setOpaque(false);
            this.moye.setOpaque(false);
            this.rightArrows.setOpaque(false);
            this.gouxuan.setOpaque(false);
            this.quanxuankuang.setOpaque(false);
            this.jScrollPane.setBorder(null);
            this.kuaisuquhui.setBorder(null);
            this.shouye.setBorder(null);
            this.leftArrows.setBorder(null);
            this.moye.setBorder(null);
            this.rightArrows.setBorder(null);
            this.jScrollPane.setBounds(12, 46, 571, 306);
            this.kuaisuquhui.setBounds(510, 10, 68, 17);
            this.yema.setBounds(258, 356, 58, 17);
            this.shouye.setBounds(200, 354, 34, 17);
            this.leftArrows.setBounds(225, 354, 19, 20);
            this.moye.setBounds(342, 354, 34, 17);
            this.rightArrows.setBounds(320, 354, 19, 20);
            this.gouxuan.setBounds(25, 30, 15, 15);
            this.quanxuankuang.setBounds(25, 30, 15, 15);
            this.yema.setForeground(Color.white);
            TraslationTableMygoodsUntil.TableModel(this.jScrollPane, 10);
            this.jScrollPane.getVerticalScrollBar().setUnitIncrement(50);
            this.gouxuan.setIcon(null);
            this.quanxuankuang.setIcon(CutButtonImage.getImage("inkImg/button/14.png", 15, 15));
            this.yema.setHorizontalAlignment(0);
            this.gouxuan.setName("1");
            this.add(this.jScrollPane);
            this.add(this.kuaisuquhui);
            this.add(this.yema);
            this.add(this.shouye);
            this.add(this.leftArrows);
            this.add(this.moye);
            this.add(this.rightArrows);
            this.add(this.gouxuan);
            this.add(this.quanxuankuang);
            this.gouxuan.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    if ("1".equals(TraslationMyMainMygoodsJpanel.this.gouxuan.getName())) {
                        TraslationMyMainMygoodsJpanel.this.gouxuan.setIcon(CutButtonImage.getImage("inkImg/button/13.png", 15, 15));
                        TraslationMyMainMygoodsJpanel.this.gouxuan.setName("0");
                        TrslationMainJframe.getTrslationMainJframe().setGoodsGouxuangeshu(TrslationMainJframe.getTrslationMainJframe().getGoodsGeshuMax());
                        JPanel view = (JPanel)TraslationMyMainMygoodsJpanel.this.jScrollPane.getViewport().getView();
                        Component[] components = view.getComponents();
                        for (int i = 0; i < components.length; ++i) {
                            JPanel panel = (JPanel)components[i];
                            JLabel component2 = (JLabel)panel.getComponent(3);
                            if (component2.isVisible()) {
                                component2.setName("0");
                                component2.setIcon(CutButtonImage.getImage("inkImg/button/13.png", 15, 15));
                            }
                        }
                    }
                    else {
                        TraslationMyMainMygoodsJpanel.this.gouxuan.setIcon(null);
                        TraslationMyMainMygoodsJpanel.this.gouxuan.setName("1");
                        TrslationMainJframe.getTrslationMainJframe().setGoodsGouxuangeshu(0);
                        JPanel view = (JPanel)TraslationMyMainMygoodsJpanel.this.jScrollPane.getViewport().getView();
                        Component[] components = view.getComponents();
                        for (int i = 0; i < components.length; ++i) {
                            JPanel panel = (JPanel)components[i];
                            JLabel component2 = (JLabel)panel.getComponent(3);
                            if (component2.isVisible()) {
                                component2.setName("1");
                                component2.setIcon(null);
                            }
                        }
                    }
                }
            });
        }
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        if (MyIsif.getStyle().equals("水墨")) {
            super.paintComponent(g);
            if (this.icon == null) {
                this.icon = new ImageIcon("inkImg/background/173.png");
            }
            g.drawImage(this.icon.getImage(), 0, 0, 590, 380, this);
        }
        else {
            super.paintComponent(g);
            if (this.icon == null) {
                this.icon = new ImageIcon("img/xy2uiimg/我的藏宝阁-我的货物w590,h380px，top97,left22px.png");
            }
            g.drawImage(this.icon.getImage(), 0, 0, 590, 380, this);
        }
    }
    
    public void setPage(int zuidayema) {
        this.zuidayema = zuidayema;
        this.yema.setText(this.dangqianyeshu + "/" + zuidayema);
    }
    
    public JLabel getYema() {
        return this.yema;
    }
    
    public void setYema(JLabel yema) {
        this.yema = yema;
    }
    
    public TraslationMyMainMygoodsBtn getShouye() {
        return this.shouye;
    }
    
    public void setShouye(TraslationMyMainMygoodsBtn shouye) {
        this.shouye = shouye;
    }
    
    public TraslationMyMainMygoodsBtn getMoye() {
        return this.moye;
    }
    
    public void setMoye(TraslationMyMainMygoodsBtn moye) {
        this.moye = moye;
    }
    
    public TraslationMyMainMygoodsBtn getLeftArrows() {
        return this.leftArrows;
    }
    
    public void setLeftArrows(TraslationMyMainMygoodsBtn leftArrows) {
        this.leftArrows = leftArrows;
    }
    
    public TraslationMyMainMygoodsBtn getRightArrows() {
        return this.rightArrows;
    }
    
    public void setRightArrows(TraslationMyMainMygoodsBtn rightArrows) {
        this.rightArrows = rightArrows;
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
    
    public TraslationMyMainMygoodsBtn getKuaisuquhui() {
        return this.kuaisuquhui;
    }
    
    public void setKuaisuquhui(TraslationMyMainMygoodsBtn kuaisuquhui) {
        this.kuaisuquhui = kuaisuquhui;
    }
    
    public JLabel getQuanxuankuang() {
        return this.quanxuankuang;
    }
    
    public void setQuanxuankuang(JLabel quanxuankuang) {
        this.quanxuankuang = quanxuankuang;
    }
    
    public JLabel getGouxuan() {
        return this.gouxuan;
    }
    
    public void setGouxuan(JLabel gouxuan) {
        this.gouxuan = gouxuan;
    }
}
