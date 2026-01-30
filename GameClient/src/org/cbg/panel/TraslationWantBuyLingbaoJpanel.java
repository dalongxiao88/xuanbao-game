package org.cbg.panel;

import java.awt.Graphics;
import org.come.until.CutButtonImage;
import org.come.Frame.ZhuFrame;
import org.cbg.frame.TrslationMainJframe;
import org.come.socket.SendMessageUntil;
import org.come.until.GsonUtil;
import org.come.socket.Agreement;
import org.cbg.bean.SearchGoodsBean;
import java.awt.event.MouseEvent;
import java.awt.event.MouseAdapter;
import com.tool.tcpimg.UIUtils;
import java.awt.Color;
import org.cbg.until.TraslationTableUntil;
import java.awt.Dimension;
import com.updateNew.MyIsif;
import javax.swing.ImageIcon;
import javax.swing.JScrollPane;
import org.cbg.btn.TrslationBtn;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;

public class TraslationWantBuyLingbaoJpanel extends JLayeredPane
{
    private static TraslationWantBuyLingbaoJpanel traslationWantBuyLingbaoJpanel;
    private JLabel yema;
    private JLabel upSort;
    private JLabel downSort;
    private int showOrder;
    private TrslationBtn shouye;
    private TrslationBtn moye;
    private TrslationBtn leftArrows;
    private TrslationBtn rightArrows;
    private int dangqianyeshu;
    private int zuidayema;
    private JScrollPane jScrollPane;
    private ImageIcon icon1;
    
    public TraslationWantBuyLingbaoJpanel() {
        this.showOrder = 0;
        this.dangqianyeshu = 1;
        this.zuidayema = 50;
        if (MyIsif.getStyle().equals("水墨")) {
            (TraslationWantBuyLingbaoJpanel.traslationWantBuyLingbaoJpanel = this).setPreferredSize(new Dimension(590, 380));
            this.setOpaque(false);
            this.setLayout(null);
            (this.jScrollPane = new JScrollPane()).setBounds(8, 60, 571, 289);
            TraslationTableUntil.TableModel(this.jScrollPane, 10);
            this.jScrollPane.getVerticalScrollBar().setUnitIncrement(50);
            this.add(this.jScrollPane, Integer.valueOf(10), -1);
            (this.yema = new JLabel(this.dangqianyeshu + "/" + this.zuidayema)).setBounds(258, 356, 58, 17);
            this.yema.setForeground(Color.white);
            this.yema.setOpaque(false);
            this.yema.setHorizontalAlignment(0);
            this.add(this.yema);
            (this.shouye = new TrslationBtn("inkImg/button/2.png", 1, UIUtils.COLOR_BTNTEXT, UIUtils.TEXT_FONT, "首页")).setBounds(200, 354, 34, 17);
            this.add(this.shouye);
            this.shouye.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    if (TraslationWantBuyLingbaoJpanel.this.dangqianyeshu != 1) {
                        TraslationWantBuyLingbaoJpanel.this.dangqianyeshu = 1;
                        TraslationWantBuyLingbaoJpanel.this.yema.setText(TraslationWantBuyLingbaoJpanel.this.dangqianyeshu + "/" + TraslationWantBuyLingbaoJpanel.this.zuidayema);
                        SearchGoodsBean searchGoodsBean = new SearchGoodsBean();
                        searchGoodsBean.setSaletype(Integer.valueOf(6));
                        searchGoodsBean.setPageNum(Integer.valueOf(TraslationWantBuyLingbaoJpanel.this.dangqianyeshu));
                        searchGoodsBean.setOrder(TraslationWantBuyLingbaoJpanel.this.showOrder);
                        String sendmes = Agreement.getAgreement().searchGoodsAgreement(GsonUtil.getGsonUtil().getgson().toJson(searchGoodsBean));
                        SendMessageUntil.toServer(sendmes);
                        TrslationMainJframe.getTrslationMainJframe().setPanelOpen(6);
                    }
                    else {
                        ZhuFrame.getZhuJpanel().addPrompt2("已在首页");
                    }
                }
            });
            (this.leftArrows = new TrslationBtn("inkImg/button/10.png", 1)).setBounds(235, 354, 19, 20);
            this.add(this.leftArrows);
            this.leftArrows.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    if (TraslationWantBuyLingbaoJpanel.this.dangqianyeshu > 1) {
                        TraslationWantBuyLingbaoJpanel.this.dangqianyeshu--;
                        TraslationWantBuyLingbaoJpanel.this.yema.setText(TraslationWantBuyLingbaoJpanel.this.dangqianyeshu + "/" + TraslationWantBuyLingbaoJpanel.this.zuidayema);
                        SearchGoodsBean searchGoodsBean = new SearchGoodsBean();
                        searchGoodsBean.setSaletype(Integer.valueOf(6));
                        searchGoodsBean.setPageNum(Integer.valueOf(TraslationWantBuyLingbaoJpanel.this.dangqianyeshu));
                        searchGoodsBean.setOrder(TraslationWantBuyLingbaoJpanel.this.showOrder);
                        String sendmes = Agreement.getAgreement().searchGoodsAgreement(GsonUtil.getGsonUtil().getgson().toJson(searchGoodsBean));
                        SendMessageUntil.toServer(sendmes);
                        TrslationMainJframe.getTrslationMainJframe().setPanelOpen(6);
                    }
                    else {
                        ZhuFrame.getZhuJpanel().addPrompt2("已经没有上一页了");
                    }
                }
            });
            (this.moye = new TrslationBtn("inkImg/button/2.png", 1, UIUtils.COLOR_BTNTEXT, UIUtils.TEXT_FONT, "末页")).setBounds(342, 354, 34, 17);
            this.add(this.moye);
            this.moye.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    if (TraslationWantBuyLingbaoJpanel.this.dangqianyeshu != TraslationWantBuyLingbaoJpanel.this.zuidayema) {
                        TraslationWantBuyLingbaoJpanel.this.dangqianyeshu = TraslationWantBuyLingbaoJpanel.this.zuidayema;
                        TraslationWantBuyLingbaoJpanel.this.yema.setText(TraslationWantBuyLingbaoJpanel.this.dangqianyeshu + "/" + TraslationWantBuyLingbaoJpanel.this.zuidayema);
                        SearchGoodsBean searchGoodsBean = new SearchGoodsBean();
                        searchGoodsBean.setSaletype(Integer.valueOf(6));
                        searchGoodsBean.setPageNum(Integer.valueOf(TraslationWantBuyLingbaoJpanel.this.dangqianyeshu));
                        searchGoodsBean.setOrder(TraslationWantBuyLingbaoJpanel.this.showOrder);
                        String sendmes = Agreement.getAgreement().searchGoodsAgreement(GsonUtil.getGsonUtil().getgson().toJson(searchGoodsBean));
                        SendMessageUntil.toServer(sendmes);
                        TrslationMainJframe.getTrslationMainJframe().setPanelOpen(6);
                    }
                    else {
                        ZhuFrame.getZhuJpanel().addPrompt2("已在末页");
                    }
                }
            });
            (this.rightArrows = new TrslationBtn("inkImg/button/9.png", 1)).setBounds(320, 354, 19, 20);
            this.add(this.rightArrows);
            this.rightArrows.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    if (TraslationWantBuyLingbaoJpanel.this.dangqianyeshu < TraslationWantBuyLingbaoJpanel.this.zuidayema) {
                        TraslationWantBuyLingbaoJpanel.this.dangqianyeshu++;
                        TraslationWantBuyLingbaoJpanel.this.yema.setText(TraslationWantBuyLingbaoJpanel.this.dangqianyeshu + "/" + TraslationWantBuyLingbaoJpanel.this.zuidayema);
                        SearchGoodsBean searchGoodsBean = new SearchGoodsBean();
                        searchGoodsBean.setSaletype(Integer.valueOf(6));
                        searchGoodsBean.setPageNum(Integer.valueOf(TraslationWantBuyLingbaoJpanel.this.dangqianyeshu));
                        searchGoodsBean.setOrder(TraslationWantBuyLingbaoJpanel.this.showOrder);
                        String sendmes = Agreement.getAgreement().searchGoodsAgreement(GsonUtil.getGsonUtil().getgson().toJson(searchGoodsBean));
                        SendMessageUntil.toServer(sendmes);
                        TrslationMainJframe.getTrslationMainJframe().setPanelOpen(6);
                    }
                    else {
                        ZhuFrame.getZhuJpanel().addPrompt2("已经没有下一页了");
                    }
                }
            });
            (this.upSort = new JLabel()).setBounds(333, 43, 6, 11);
            this.upSort.setBorder(null);
            this.upSort.setBackground(null);
            this.upSort.setOpaque(false);
            this.upSort.setIcon(CutButtonImage.getImage("img/xy2uiimg/↑(白色)w6,h11px.png", 6, 11));
            this.add(this.upSort);
            this.upSort.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    TraslationWantBuyLingbaoJpanel.this.upSort.setIcon(CutButtonImage.getImage("img/xy2uiimg/↑(红色)w6,h11px.png", 6, 11));
                    TraslationWantBuyLingbaoJpanel.this.downSort.setIcon(CutButtonImage.getImage("img/xy2uiimg/↓(白色)w6,h11px.png", 6, 11));
                    TraslationWantBuyLingbaoJpanel.this.dangqianyeshu = 1;
                    TraslationWantBuyLingbaoJpanel.this.showOrder = 1;
                    SearchGoodsBean searchGoodsBean = new SearchGoodsBean();
                    searchGoodsBean.setSaletype(Integer.valueOf(6));
                    searchGoodsBean.setPageNum(Integer.valueOf(TraslationWantBuyLingbaoJpanel.this.dangqianyeshu));
                    searchGoodsBean.setOrder(TraslationWantBuyLingbaoJpanel.this.showOrder);
                    String sendmes = Agreement.getAgreement().searchGoodsAgreement(GsonUtil.getGsonUtil().getgson().toJson(searchGoodsBean));
                    SendMessageUntil.toServer(sendmes);
                    TrslationMainJframe.getTrslationMainJframe().setPanelOpen(6);
                }
            });
            (this.downSort = new JLabel()).setBounds(343, 43, 6, 11);
            this.downSort.setBorder(null);
            this.downSort.setBackground(null);
            this.downSort.setOpaque(false);
            this.downSort.setIcon(CutButtonImage.getImage("img/xy2uiimg/↓(白色)w6,h11px.png", 6, 11));
            this.add(this.downSort);
            this.downSort.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    TraslationWantBuyLingbaoJpanel.this.upSort.setIcon(CutButtonImage.getImage("img/xy2uiimg/↑(白色)w6,h11px.png", 6, 11));
                    TraslationWantBuyLingbaoJpanel.this.downSort.setIcon(CutButtonImage.getImage("img/xy2uiimg/↓(红色)w6,h11px.png", 6, 11));
                    TraslationWantBuyLingbaoJpanel.this.dangqianyeshu = 1;
                    TraslationWantBuyLingbaoJpanel.this.showOrder = 2;
                    SearchGoodsBean searchGoodsBean = new SearchGoodsBean();
                    searchGoodsBean.setSaletype(Integer.valueOf(6));
                    searchGoodsBean.setPageNum(Integer.valueOf(TraslationWantBuyLingbaoJpanel.this.dangqianyeshu));
                    searchGoodsBean.setOrder(TraslationWantBuyLingbaoJpanel.this.showOrder);
                    String sendmes = Agreement.getAgreement().searchGoodsAgreement(GsonUtil.getGsonUtil().getgson().toJson(searchGoodsBean));
                    SendMessageUntil.toServer(sendmes);
                    TrslationMainJframe.getTrslationMainJframe().setPanelOpen(6);
                }
            });
        }
        else {
            (TraslationWantBuyLingbaoJpanel.traslationWantBuyLingbaoJpanel = this).setPreferredSize(new Dimension(590, 380));
            this.setOpaque(false);
            this.setLayout(null);
            (this.jScrollPane = new JScrollPane()).setBounds(8, 60, 571, 289);
            TraslationTableUntil.TableModel(this.jScrollPane, 10);
            this.jScrollPane.getVerticalScrollBar().setUnitIncrement(50);
            this.add(this.jScrollPane, Integer.valueOf(10), -1);
            (this.yema = new JLabel(this.dangqianyeshu + "/" + this.zuidayema)).setBounds(258, 356, 58, 17);
            this.yema.setForeground(Color.white);
            this.yema.setOpaque(false);
            this.yema.setHorizontalAlignment(0);
            this.add(this.yema);
            (this.shouye = new TrslationBtn("inkImg/hongmu/21_png.button.tab_nex.png", 1, UIUtils.COLOR_BTNTEXT, UIUtils.TEXT_FONT, "首页")).setBounds(200, 354, 34, 17);
            this.add(this.shouye);
            this.shouye.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    if (TraslationWantBuyLingbaoJpanel.this.dangqianyeshu != 1) {
                        TraslationWantBuyLingbaoJpanel.this.dangqianyeshu = 1;
                        TraslationWantBuyLingbaoJpanel.this.yema.setText(TraslationWantBuyLingbaoJpanel.this.dangqianyeshu + "/" + TraslationWantBuyLingbaoJpanel.this.zuidayema);
                        SearchGoodsBean searchGoodsBean = new SearchGoodsBean();
                        searchGoodsBean.setSaletype(Integer.valueOf(6));
                        searchGoodsBean.setPageNum(Integer.valueOf(TraslationWantBuyLingbaoJpanel.this.dangqianyeshu));
                        searchGoodsBean.setOrder(TraslationWantBuyLingbaoJpanel.this.showOrder);
                        String sendmes = Agreement.getAgreement().searchGoodsAgreement(GsonUtil.getGsonUtil().getgson().toJson(searchGoodsBean));
                        SendMessageUntil.toServer(sendmes);
                        TrslationMainJframe.getTrslationMainJframe().setPanelOpen(6);
                    }
                    else {
                        ZhuFrame.getZhuJpanel().addPrompt2("已在首页");
                    }
                }
            });
            (this.leftArrows = new TrslationBtn("img/xy2uiimg/30_png.button.btn_8.png", 1)).setBounds(235, 354, 19, 20);
            this.add(this.leftArrows);
            this.leftArrows.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    if (TraslationWantBuyLingbaoJpanel.this.dangqianyeshu > 1) {
                        TraslationWantBuyLingbaoJpanel.this.dangqianyeshu--;
                        TraslationWantBuyLingbaoJpanel.this.yema.setText(TraslationWantBuyLingbaoJpanel.this.dangqianyeshu + "/" + TraslationWantBuyLingbaoJpanel.this.zuidayema);
                        SearchGoodsBean searchGoodsBean = new SearchGoodsBean();
                        searchGoodsBean.setSaletype(Integer.valueOf(6));
                        searchGoodsBean.setPageNum(Integer.valueOf(TraslationWantBuyLingbaoJpanel.this.dangqianyeshu));
                        searchGoodsBean.setOrder(TraslationWantBuyLingbaoJpanel.this.showOrder);
                        String sendmes = Agreement.getAgreement().searchGoodsAgreement(GsonUtil.getGsonUtil().getgson().toJson(searchGoodsBean));
                        SendMessageUntil.toServer(sendmes);
                        TrslationMainJframe.getTrslationMainJframe().setPanelOpen(6);
                    }
                    else {
                        ZhuFrame.getZhuJpanel().addPrompt2("已经没有上一页了");
                    }
                }
            });
            (this.moye = new TrslationBtn("inkImg/hongmu/21_png.button.tab_nex.png", 1, UIUtils.COLOR_BTNTEXT, UIUtils.TEXT_FONT, "末页")).setBounds(342, 354, 34, 17);
            this.add(this.moye);
            this.moye.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    if (TraslationWantBuyLingbaoJpanel.this.dangqianyeshu != TraslationWantBuyLingbaoJpanel.this.zuidayema) {
                        TraslationWantBuyLingbaoJpanel.this.dangqianyeshu = TraslationWantBuyLingbaoJpanel.this.zuidayema;
                        TraslationWantBuyLingbaoJpanel.this.yema.setText(TraslationWantBuyLingbaoJpanel.this.dangqianyeshu + "/" + TraslationWantBuyLingbaoJpanel.this.zuidayema);
                        SearchGoodsBean searchGoodsBean = new SearchGoodsBean();
                        searchGoodsBean.setSaletype(Integer.valueOf(6));
                        searchGoodsBean.setPageNum(Integer.valueOf(TraslationWantBuyLingbaoJpanel.this.dangqianyeshu));
                        searchGoodsBean.setOrder(TraslationWantBuyLingbaoJpanel.this.showOrder);
                        String sendmes = Agreement.getAgreement().searchGoodsAgreement(GsonUtil.getGsonUtil().getgson().toJson(searchGoodsBean));
                        SendMessageUntil.toServer(sendmes);
                        TrslationMainJframe.getTrslationMainJframe().setPanelOpen(6);
                    }
                    else {
                        ZhuFrame.getZhuJpanel().addPrompt2("已在末页");
                    }
                }
            });
            (this.rightArrows = new TrslationBtn("img/xy2uiimg/36_png.button.btn_7.png", 1)).setBounds(320, 354, 19, 20);
            this.add(this.rightArrows);
            this.rightArrows.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    if (TraslationWantBuyLingbaoJpanel.this.dangqianyeshu < TraslationWantBuyLingbaoJpanel.this.zuidayema) {
                        TraslationWantBuyLingbaoJpanel.this.dangqianyeshu++;
                        TraslationWantBuyLingbaoJpanel.this.yema.setText(TraslationWantBuyLingbaoJpanel.this.dangqianyeshu + "/" + TraslationWantBuyLingbaoJpanel.this.zuidayema);
                        SearchGoodsBean searchGoodsBean = new SearchGoodsBean();
                        searchGoodsBean.setSaletype(Integer.valueOf(6));
                        searchGoodsBean.setPageNum(Integer.valueOf(TraslationWantBuyLingbaoJpanel.this.dangqianyeshu));
                        searchGoodsBean.setOrder(TraslationWantBuyLingbaoJpanel.this.showOrder);
                        String sendmes = Agreement.getAgreement().searchGoodsAgreement(GsonUtil.getGsonUtil().getgson().toJson(searchGoodsBean));
                        SendMessageUntil.toServer(sendmes);
                        TrslationMainJframe.getTrslationMainJframe().setPanelOpen(6);
                    }
                    else {
                        ZhuFrame.getZhuJpanel().addPrompt2("已经没有下一页了");
                    }
                }
            });
            (this.upSort = new JLabel()).setBounds(333, 43, 6, 11);
            this.upSort.setBorder(null);
            this.upSort.setBackground(null);
            this.upSort.setOpaque(false);
            this.upSort.setIcon(CutButtonImage.getImage("img/xy2uiimg/↑(白色)w6,h11px.png", 6, 11));
            this.add(this.upSort);
            this.upSort.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    TraslationWantBuyLingbaoJpanel.this.upSort.setIcon(CutButtonImage.getImage("img/xy2uiimg/↑(红色)w6,h11px.png", 6, 11));
                    TraslationWantBuyLingbaoJpanel.this.downSort.setIcon(CutButtonImage.getImage("img/xy2uiimg/↓(白色)w6,h11px.png", 6, 11));
                    TraslationWantBuyLingbaoJpanel.this.dangqianyeshu = 1;
                    TraslationWantBuyLingbaoJpanel.this.showOrder = 1;
                    SearchGoodsBean searchGoodsBean = new SearchGoodsBean();
                    searchGoodsBean.setSaletype(Integer.valueOf(6));
                    searchGoodsBean.setPageNum(Integer.valueOf(TraslationWantBuyLingbaoJpanel.this.dangqianyeshu));
                    searchGoodsBean.setOrder(TraslationWantBuyLingbaoJpanel.this.showOrder);
                    String sendmes = Agreement.getAgreement().searchGoodsAgreement(GsonUtil.getGsonUtil().getgson().toJson(searchGoodsBean));
                    SendMessageUntil.toServer(sendmes);
                    TrslationMainJframe.getTrslationMainJframe().setPanelOpen(6);
                }
            });
            (this.downSort = new JLabel()).setBounds(343, 43, 6, 11);
            this.downSort.setBorder(null);
            this.downSort.setBackground(null);
            this.downSort.setOpaque(false);
            this.downSort.setIcon(CutButtonImage.getImage("img/xy2uiimg/↓(白色)w6,h11px.png", 6, 11));
            this.add(this.downSort);
            this.downSort.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    TraslationWantBuyLingbaoJpanel.this.upSort.setIcon(CutButtonImage.getImage("img/xy2uiimg/↑(白色)w6,h11px.png", 6, 11));
                    TraslationWantBuyLingbaoJpanel.this.downSort.setIcon(CutButtonImage.getImage("img/xy2uiimg/↓(红色)w6,h11px.png", 6, 11));
                    TraslationWantBuyLingbaoJpanel.this.dangqianyeshu = 1;
                    TraslationWantBuyLingbaoJpanel.this.showOrder = 2;
                    SearchGoodsBean searchGoodsBean = new SearchGoodsBean();
                    searchGoodsBean.setSaletype(Integer.valueOf(6));
                    searchGoodsBean.setPageNum(Integer.valueOf(TraslationWantBuyLingbaoJpanel.this.dangqianyeshu));
                    searchGoodsBean.setOrder(TraslationWantBuyLingbaoJpanel.this.showOrder);
                    String sendmes = Agreement.getAgreement().searchGoodsAgreement(GsonUtil.getGsonUtil().getgson().toJson(searchGoodsBean));
                    SendMessageUntil.toServer(sendmes);
                    TrslationMainJframe.getTrslationMainJframe().setPanelOpen(6);
                }
            });
        }
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        if (MyIsif.getStyle().equals("水墨")) {
            super.paintComponent(g);
            if (this.icon1 == null) {
                this.icon1 = new ImageIcon("inkImg/background/168.png");
            }
            g.drawImage(this.icon1.getImage(), 0, 0, 590, 380, this);
        }
        else {
            super.paintComponent(g);
            if (this.icon1 == null) {
                this.icon1 = new ImageIcon("img/xy2uiimg/新我要买-灵宝w590,h380px，top97,left22px (1).png");
            }
            g.drawImage(this.icon1.getImage(), 0, 0, 590, 380, this);
        }
    }
    
    public void setPage(int zuidayema) {
        this.zuidayema = zuidayema;
        this.yema.setText(this.dangqianyeshu + "/" + this.zuidayema);
    }
    
    public static TraslationWantBuyLingbaoJpanel getTraslationWantBuyLingbaoJpanel() {
        return TraslationWantBuyLingbaoJpanel.traslationWantBuyLingbaoJpanel;
    }
    
    public static void setTraslationWantBuyLingbaoJpanel(TraslationWantBuyLingbaoJpanel traslationWantBuyLingbaoJpanel) {
        TraslationWantBuyLingbaoJpanel.traslationWantBuyLingbaoJpanel = traslationWantBuyLingbaoJpanel;
    }
    
    public JLabel getYema() {
        return this.yema;
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
    
    public TrslationBtn getShouye() {
        return this.shouye;
    }
    
    public void setShouye(TrslationBtn shouye) {
        this.shouye = shouye;
    }
    
    public TrslationBtn getMoye() {
        return this.moye;
    }
    
    public void setMoye(TrslationBtn moye) {
        this.moye = moye;
    }
    
    public TrslationBtn getLeftArrows() {
        return this.leftArrows;
    }
    
    public void setLeftArrows(TrslationBtn leftArrows) {
        this.leftArrows = leftArrows;
    }
    
    public TrslationBtn getRightArrows() {
        return this.rightArrows;
    }
    
    public void setRightArrows(TrslationBtn rightArrows) {
        this.rightArrows = rightArrows;
    }
    
    public ImageIcon getIcon1() {
        return this.icon1;
    }
    
    public void setIcon1(ImageIcon icon1) {
        this.icon1 = icon1;
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
    
    public JScrollPane getjScrollPane() {
        return this.jScrollPane;
    }
    
    public void setjScrollPane(JScrollPane jScrollPane) {
        this.jScrollPane = jScrollPane;
    }
    
    public int getShowOrder() {
        return this.showOrder;
    }
    
    public void setShowOrder(int showOrder) {
        this.showOrder = showOrder;
    }
}
