package org.cbg.panel;

import java.math.BigDecimal;
import java.awt.Graphics;
import org.come.until.CutButtonImage;
import org.come.Frame.ZhuFrame;
import com.tool.tcpimg.UIUtils;
import org.cbg.frame.TrslationMainJframe;
import org.come.socket.SendMessageUntil;
import org.come.until.GsonUtil;
import org.come.socket.Agreement;
import org.cbg.bean.SearchGoodsBean;
import java.awt.event.MouseEvent;
import java.awt.event.MouseAdapter;
import java.awt.Color;
import org.cbg.until.TraslationTableDahuabiUntil;
import java.awt.Dimension;
import com.updateNew.MyIsif;
import javax.swing.ImageIcon;
import javax.swing.JScrollPane;
import org.cbg.btn.TrslationBtn;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;

public class TraslationWantBuyDahuabiJpanel extends JLayeredPane
{
    private JLabel chooseLeft;
    private JLabel chooseRight;
    private JLabel yema;
    private JLabel upSort;
    private JLabel downSort;
    private TraslationSelectOptionJpanel chooseLeftModel;
    private TraslationSelectOptionJpanel chooseRightModel;
    private int leftFlag;
    private int rightFlag;
    private int showOrder;
    private TrslationBtn shouye;
    private TrslationBtn moye;
    private TrslationBtn leftArrows;
    private TrslationBtn rightArrows;
    private TrslationBtn chooseLeftArrows;
    private TrslationBtn chooseRightArrows;
    private JScrollPane jScrollPane;
    private int dangqianyeshu;
    private int zuidayema;
    private TraslationWantBuyDahuabiModelJpanel buyDahuabiModelJpanel;
    private ImageIcon icon1;
    private ImageIcon icon2;
    private ImageIcon icon3;
    
    public TraslationWantBuyDahuabiJpanel() {
        this.leftFlag = 1;
        this.rightFlag = 1;
        this.showOrder = 0;
        this.dangqianyeshu = 1;
        this.zuidayema = 50;
        if (MyIsif.getStyle().equals("水墨")) {
            this.setPreferredSize(new Dimension(590, 380));
            this.setOpaque(false);
            this.setLayout(null);
            (this.buyDahuabiModelJpanel = new TraslationWantBuyDahuabiModelJpanel()).setBounds(8, 58, 590, 100);
            this.buyDahuabiModelJpanel.setOpaque(false);
            (this.jScrollPane = new JScrollPane()).setBounds(8, 60, 571, 289);
            TraslationTableDahuabiUntil.TableModel(this.jScrollPane, 10);
            this.jScrollPane.getVerticalScrollBar().setUnitIncrement(50);
            this.chooseLeft = new JLabel("一亿");
            this.chooseRight = new JLabel("一百亿");
            this.chooseLeftArrows = new TrslationBtn("inkImg/button/8.png", 1);
            this.chooseRightArrows = new TrslationBtn("inkImg/button/8.png", 1);
            this.chooseLeft.setForeground(Color.white);
            this.chooseRight.setForeground(Color.white);
            this.chooseLeft.setBounds(59, 11, 108, 20);
            this.chooseRight.setBounds(195, 11, 98, 20);
            this.chooseLeftArrows.setBounds(148, 13, 19, 20);
            this.chooseRightArrows.setBounds(272, 13, 19, 20);
            String[] rowDataLeft = { "一亿", "二亿", "三亿", "四亿", "十亿", "二十亿", "三十亿", "四十亿", "十亿", "五十亿", "六十亿", "七十亿", "八十亿", "九十亿", "一百亿" };
            String[] rowDataRight = { "一亿", "二亿", "三亿", "四亿", "十亿", "二十亿", "三十亿", "四十亿", "十亿", "五十亿", "六十亿", "七十亿", "八十亿", "九十亿", "一百亿" };
            (this.chooseLeftModel = new TraslationSelectOptionJpanel(108, 120, "inkImg/background/18.png", rowDataLeft)).setBounds(57, 30, 108, 120);
            this.chooseLeftModel.setVisible(false);
            (this.chooseRightModel = new TraslationSelectOptionJpanel(98, 120, "inkImg/background/15.png", rowDataRight)).setBounds(192, 30, 98, 120);
            this.chooseRightModel.setVisible(false);
            this.chooseLeftArrows.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    if (TraslationWantBuyDahuabiJpanel.this.leftFlag == 1) {
                        TraslationWantBuyDahuabiJpanel.this.chooseLeftModel.setVisible(true);
                        TraslationWantBuyDahuabiJpanel.this.leftFlag = 0;
                    }
                    else {
                        TraslationWantBuyDahuabiJpanel.this.chooseLeftModel.setVisible(false);
                        TraslationWantBuyDahuabiJpanel.this.leftFlag = 1;
                    }
                }
            });
            this.chooseRightArrows.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    if (TraslationWantBuyDahuabiJpanel.this.rightFlag == 1) {
                        TraslationWantBuyDahuabiJpanel.this.chooseRightModel.setVisible(true);
                        TraslationWantBuyDahuabiJpanel.this.rightFlag = 0;
                    }
                    else {
                        TraslationWantBuyDahuabiJpanel.this.chooseRightModel.setVisible(false);
                        TraslationWantBuyDahuabiJpanel.this.rightFlag = 1;
                    }
                }
            });
            this.chooseLeftModel.getJlist().addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    String getname = (String)TraslationWantBuyDahuabiJpanel.this.chooseLeftModel.getJlist().getSelectedValue();
                    TraslationWantBuyDahuabiJpanel.this.chooseLeft.setText(getname);
                    TraslationWantBuyDahuabiJpanel.this.chooseLeftModel.setVisible(false);
                    TraslationWantBuyDahuabiJpanel.this.leftFlag = 1;
                    TraslationWantBuyDahuabiJpanel.this.dangqianyeshu = 1;
                    SearchGoodsBean searchGoodsBean = new SearchGoodsBean();
                    searchGoodsBean.setSaletype(Integer.valueOf(2));
                    searchGoodsBean.setContiontype(TraslationWantBuyDahuabiJpanel.changeMath(TraslationWantBuyDahuabiJpanel.this.chooseLeft.getText()) + "-" + TraslationWantBuyDahuabiJpanel.changeMath(TraslationWantBuyDahuabiJpanel.this.chooseRight.getText()));
                    searchGoodsBean.setPageNum(Integer.valueOf(TraslationWantBuyDahuabiJpanel.this.dangqianyeshu));
                    searchGoodsBean.setOrder(TraslationWantBuyDahuabiJpanel.this.showOrder);
                    String sendmes = Agreement.getAgreement().searchGoodsAgreement(GsonUtil.getGsonUtil().getgson().toJson(searchGoodsBean));
                    SendMessageUntil.toServer(sendmes);
                    TrslationMainJframe.getTrslationMainJframe().setPanelOpen(2);
                }
            });
            this.chooseRightModel.getJlist().addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    String getname = (String)TraslationWantBuyDahuabiJpanel.this.chooseRightModel.getJlist().getSelectedValue();
                    TraslationWantBuyDahuabiJpanel.this.chooseRight.setText(getname);
                    TraslationWantBuyDahuabiJpanel.this.chooseRightModel.setVisible(false);
                    TraslationWantBuyDahuabiJpanel.this.rightFlag = 1;
                    TraslationWantBuyDahuabiJpanel.this.dangqianyeshu = 1;
                    SearchGoodsBean searchGoodsBean = new SearchGoodsBean();
                    searchGoodsBean.setSaletype(Integer.valueOf(2));
                    searchGoodsBean.setContiontype(TraslationWantBuyDahuabiJpanel.changeMath(TraslationWantBuyDahuabiJpanel.this.chooseLeft.getText()) + "-" + TraslationWantBuyDahuabiJpanel.changeMath(TraslationWantBuyDahuabiJpanel.this.chooseRight.getText()));
                    searchGoodsBean.setPageNum(Integer.valueOf(TraslationWantBuyDahuabiJpanel.this.dangqianyeshu));
                    searchGoodsBean.setOrder(TraslationWantBuyDahuabiJpanel.this.showOrder);
                    String sendmes = Agreement.getAgreement().searchGoodsAgreement(GsonUtil.getGsonUtil().getgson().toJson(searchGoodsBean));
                    SendMessageUntil.toServer(sendmes);
                    TrslationMainJframe.getTrslationMainJframe().setPanelOpen(2);
                }
            });
            this.add(this.chooseLeftArrows);
            this.add(this.chooseRightArrows);
            this.add(this.chooseLeftModel, Integer.valueOf(10), 2);
            this.add(this.chooseLeft);
            this.add(this.chooseRight);
            this.add(this.chooseRightModel, Integer.valueOf(10), 4);
            (this.yema = new JLabel(this.dangqianyeshu + "/" + this.zuidayema)).setBounds(258, 356, 58, 17);
            this.yema.setForeground(Color.white);
            this.yema.setOpaque(false);
            this.yema.setHorizontalAlignment(0);
            this.add(this.yema);
            (this.shouye = new TrslationBtn("inkImg/button/2.png", 1, UIUtils.COLOR_BTNTEXT, UIUtils.TEXT_FONT, "首页")).setBounds(196, 354, 34, 17);
            this.add(this.shouye);
            this.shouye.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    if (TraslationWantBuyDahuabiJpanel.this.dangqianyeshu != 1) {
                        TraslationWantBuyDahuabiJpanel.this.dangqianyeshu = 1;
                        TraslationWantBuyDahuabiJpanel.this.yema.setText(TraslationWantBuyDahuabiJpanel.this.dangqianyeshu + "/" + TraslationWantBuyDahuabiJpanel.this.zuidayema);
                        SearchGoodsBean searchGoodsBean = new SearchGoodsBean();
                        searchGoodsBean.setSaletype(Integer.valueOf(2));
                        searchGoodsBean.setContiontype(TraslationWantBuyDahuabiJpanel.changeMath(TraslationWantBuyDahuabiJpanel.this.chooseLeft.getText()) + "-" + TraslationWantBuyDahuabiJpanel.changeMath(TraslationWantBuyDahuabiJpanel.this.chooseRight.getText()));
                        searchGoodsBean.setPageNum(Integer.valueOf(TraslationWantBuyDahuabiJpanel.this.dangqianyeshu));
                        searchGoodsBean.setOrder(TraslationWantBuyDahuabiJpanel.this.showOrder);
                        String sendmes = Agreement.getAgreement().searchGoodsAgreement(GsonUtil.getGsonUtil().getgson().toJson(searchGoodsBean));
                        SendMessageUntil.toServer(sendmes);
                        TrslationMainJframe.getTrslationMainJframe().setPanelOpen(2);
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
                    if (TraslationWantBuyDahuabiJpanel.this.dangqianyeshu > 1) {
                        TraslationWantBuyDahuabiJpanel.this.dangqianyeshu--;
                        TraslationWantBuyDahuabiJpanel.this.yema.setText(TraslationWantBuyDahuabiJpanel.this.dangqianyeshu + "/" + TraslationWantBuyDahuabiJpanel.this.zuidayema);
                        SearchGoodsBean searchGoodsBean = new SearchGoodsBean();
                        searchGoodsBean.setSaletype(Integer.valueOf(2));
                        searchGoodsBean.setContiontype(TraslationWantBuyDahuabiJpanel.changeMath(TraslationWantBuyDahuabiJpanel.this.chooseLeft.getText()) + "-" + TraslationWantBuyDahuabiJpanel.changeMath(TraslationWantBuyDahuabiJpanel.this.chooseRight.getText()));
                        searchGoodsBean.setPageNum(Integer.valueOf(TraslationWantBuyDahuabiJpanel.this.dangqianyeshu));
                        searchGoodsBean.setOrder(TraslationWantBuyDahuabiJpanel.this.showOrder);
                        String sendmes = Agreement.getAgreement().searchGoodsAgreement(GsonUtil.getGsonUtil().getgson().toJson(searchGoodsBean));
                        SendMessageUntil.toServer(sendmes);
                        TrslationMainJframe.getTrslationMainJframe().setPanelOpen(2);
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
                    if (TraslationWantBuyDahuabiJpanel.this.dangqianyeshu != TraslationWantBuyDahuabiJpanel.this.zuidayema) {
                        TraslationWantBuyDahuabiJpanel.this.dangqianyeshu = TraslationWantBuyDahuabiJpanel.this.zuidayema;
                        TraslationWantBuyDahuabiJpanel.this.yema.setText(TraslationWantBuyDahuabiJpanel.this.dangqianyeshu + "/" + TraslationWantBuyDahuabiJpanel.this.zuidayema);
                        SearchGoodsBean searchGoodsBean = new SearchGoodsBean();
                        searchGoodsBean.setSaletype(Integer.valueOf(2));
                        searchGoodsBean.setContiontype(TraslationWantBuyDahuabiJpanel.changeMath(TraslationWantBuyDahuabiJpanel.this.chooseLeft.getText()) + "-" + TraslationWantBuyDahuabiJpanel.changeMath(TraslationWantBuyDahuabiJpanel.this.chooseRight.getText()));
                        searchGoodsBean.setPageNum(Integer.valueOf(TraslationWantBuyDahuabiJpanel.this.dangqianyeshu));
                        searchGoodsBean.setOrder(TraslationWantBuyDahuabiJpanel.this.showOrder);
                        String sendmes = Agreement.getAgreement().searchGoodsAgreement(GsonUtil.getGsonUtil().getgson().toJson(searchGoodsBean));
                        SendMessageUntil.toServer(sendmes);
                        TrslationMainJframe.getTrslationMainJframe().setPanelOpen(2);
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
                    if (TraslationWantBuyDahuabiJpanel.this.dangqianyeshu < TraslationWantBuyDahuabiJpanel.this.zuidayema) {
                        TraslationWantBuyDahuabiJpanel.this.dangqianyeshu++;
                        TraslationWantBuyDahuabiJpanel.this.yema.setText(TraslationWantBuyDahuabiJpanel.this.dangqianyeshu + "/" + TraslationWantBuyDahuabiJpanel.this.zuidayema);
                        SearchGoodsBean searchGoodsBean = new SearchGoodsBean();
                        searchGoodsBean.setSaletype(Integer.valueOf(2));
                        searchGoodsBean.setContiontype(TraslationWantBuyDahuabiJpanel.changeMath(TraslationWantBuyDahuabiJpanel.this.chooseLeft.getText()) + "-" + TraslationWantBuyDahuabiJpanel.changeMath(TraslationWantBuyDahuabiJpanel.this.chooseRight.getText()));
                        searchGoodsBean.setPageNum(Integer.valueOf(TraslationWantBuyDahuabiJpanel.this.dangqianyeshu));
                        searchGoodsBean.setOrder(TraslationWantBuyDahuabiJpanel.this.showOrder);
                        String sendmes = Agreement.getAgreement().searchGoodsAgreement(GsonUtil.getGsonUtil().getgson().toJson(searchGoodsBean));
                        SendMessageUntil.toServer(sendmes);
                        TrslationMainJframe.getTrslationMainJframe().setPanelOpen(2);
                    }
                    else {
                        ZhuFrame.getZhuJpanel().addPrompt2("已经没有下一页了");
                    }
                }
            });
            (this.upSort = new JLabel()).setBounds(333, 45, 6, 11);
            this.upSort.setBorder(null);
            this.upSort.setBackground(null);
            this.upSort.setOpaque(false);
            this.upSort.setIcon(CutButtonImage.getImage("img/xy2uiimg/↑(白色)w6,h11px.png", 6, 11));
            this.add(this.upSort);
            this.upSort.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    TraslationWantBuyDahuabiJpanel.this.upSort.setIcon(CutButtonImage.getImage("img/xy2uiimg/↑(红色)w6,h11px.png", 6, 11));
                    TraslationWantBuyDahuabiJpanel.this.downSort.setIcon(CutButtonImage.getImage("img/xy2uiimg/↓(白色)w6,h11px.png", 6, 11));
                    TraslationWantBuyDahuabiJpanel.this.dangqianyeshu = 1;
                    TraslationWantBuyDahuabiJpanel.this.showOrder = 1;
                    SearchGoodsBean searchGoodsBean = new SearchGoodsBean();
                    searchGoodsBean.setSaletype(Integer.valueOf(2));
                    searchGoodsBean.setContiontype(TraslationWantBuyDahuabiJpanel.changeMath(TraslationWantBuyDahuabiJpanel.this.chooseLeft.getText()) + "-" + TraslationWantBuyDahuabiJpanel.changeMath(TraslationWantBuyDahuabiJpanel.this.chooseRight.getText()));
                    searchGoodsBean.setPageNum(Integer.valueOf(1));
                    searchGoodsBean.setOrder(TraslationWantBuyDahuabiJpanel.this.showOrder);
                    String sendmes = Agreement.getAgreement().searchGoodsAgreement(GsonUtil.getGsonUtil().getgson().toJson(searchGoodsBean));
                    SendMessageUntil.toServer(sendmes);
                    TrslationMainJframe.getTrslationMainJframe().setPanelOpen(2);
                }
            });
            (this.downSort = new JLabel()).setBounds(343, 45, 6, 11);
            this.downSort.setBorder(null);
            this.downSort.setBackground(null);
            this.downSort.setOpaque(false);
            this.downSort.setIcon(CutButtonImage.getImage("img/xy2uiimg/↓(白色)w6,h11px.png", 6, 11));
            this.add(this.downSort);
            this.downSort.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    TraslationWantBuyDahuabiJpanel.this.upSort.setIcon(CutButtonImage.getImage("img/xy2uiimg/↑(白色)w6,h11px.png", 6, 11));
                    TraslationWantBuyDahuabiJpanel.this.downSort.setIcon(CutButtonImage.getImage("img/xy2uiimg/↓(红色)w6,h11px.png", 6, 11));
                    TraslationWantBuyDahuabiJpanel.this.dangqianyeshu = 1;
                    TraslationWantBuyDahuabiJpanel.this.showOrder = 2;
                    SearchGoodsBean searchGoodsBean = new SearchGoodsBean();
                    searchGoodsBean.setSaletype(Integer.valueOf(2));
                    searchGoodsBean.setContiontype(TraslationWantBuyDahuabiJpanel.changeMath(TraslationWantBuyDahuabiJpanel.this.chooseLeft.getText()) + "-" + TraslationWantBuyDahuabiJpanel.changeMath(TraslationWantBuyDahuabiJpanel.this.chooseRight.getText()));
                    searchGoodsBean.setPageNum(Integer.valueOf(1));
                    searchGoodsBean.setOrder(TraslationWantBuyDahuabiJpanel.this.showOrder);
                    String sendmes = Agreement.getAgreement().searchGoodsAgreement(GsonUtil.getGsonUtil().getgson().toJson(searchGoodsBean));
                    SendMessageUntil.toServer(sendmes);
                    TrslationMainJframe.getTrslationMainJframe().setPanelOpen(2);
                }
            });
            this.add(this.jScrollPane, Integer.valueOf(10), -1);
        }
        else {
            this.setPreferredSize(new Dimension(590, 380));
            this.setOpaque(false);
            this.setLayout(null);
            (this.buyDahuabiModelJpanel = new TraslationWantBuyDahuabiModelJpanel()).setBounds(8, 58, 590, 100);
            this.buyDahuabiModelJpanel.setOpaque(false);
            (this.jScrollPane = new JScrollPane()).setBounds(8, 60, 571, 289);
            TraslationTableDahuabiUntil.TableModel(this.jScrollPane, 10);
            this.jScrollPane.getVerticalScrollBar().setUnitIncrement(50);
            this.chooseLeft = new JLabel("一亿");
            this.chooseRight = new JLabel("一百亿");
            this.chooseLeftArrows = new TrslationBtn("inkImg/button/8.png", 1);
            this.chooseRightArrows = new TrslationBtn("inkImg/button/8.png", 1);
            this.chooseLeft.setForeground(Color.white);
            this.chooseRight.setForeground(Color.white);
            this.chooseLeft.setBounds(59, 11, 108, 20);
            this.chooseRight.setBounds(195, 11, 98, 20);
            this.chooseLeftArrows.setBounds(148, 13, 19, 20);
            this.chooseRightArrows.setBounds(272, 13, 19, 20);
            String[] rowDataLeft = { "一亿", "二亿", "三亿", "四亿", "十亿", "二十亿", "三十亿", "四十亿", "十亿", "五十亿", "六十亿", "七十亿", "八十亿", "九十亿", "一百亿" };
            String[] rowDataRight = { "一亿", "二亿", "三亿", "四亿", "十亿", "二十亿", "三十亿", "四十亿", "十亿", "五十亿", "六十亿", "七十亿", "八十亿", "九十亿", "一百亿" };
            (this.chooseLeftModel = new TraslationSelectOptionJpanel(108, 120, "img/xy2uiimg/下拉框(1)w108,h120px.png", rowDataLeft)).setBounds(57, 30, 108, 120);
            this.chooseLeftModel.setVisible(false);
            (this.chooseRightModel = new TraslationSelectOptionJpanel(98, 120, "img/xy2uiimg/下拉框(2)w118,h120px.png", rowDataRight)).setBounds(192, 30, 98, 120);
            this.chooseRightModel.setVisible(false);
            this.chooseLeftArrows.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    if (TraslationWantBuyDahuabiJpanel.this.leftFlag == 1) {
                        TraslationWantBuyDahuabiJpanel.this.chooseLeftModel.setVisible(true);
                        TraslationWantBuyDahuabiJpanel.this.leftFlag = 0;
                    }
                    else {
                        TraslationWantBuyDahuabiJpanel.this.chooseLeftModel.setVisible(false);
                        TraslationWantBuyDahuabiJpanel.this.leftFlag = 1;
                    }
                }
            });
            this.chooseRightArrows.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    if (TraslationWantBuyDahuabiJpanel.this.rightFlag == 1) {
                        TraslationWantBuyDahuabiJpanel.this.chooseRightModel.setVisible(true);
                        TraslationWantBuyDahuabiJpanel.this.rightFlag = 0;
                    }
                    else {
                        TraslationWantBuyDahuabiJpanel.this.chooseRightModel.setVisible(false);
                        TraslationWantBuyDahuabiJpanel.this.rightFlag = 1;
                    }
                }
            });
            this.chooseLeftModel.getJlist().addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    String getname = (String)TraslationWantBuyDahuabiJpanel.this.chooseLeftModel.getJlist().getSelectedValue();
                    TraslationWantBuyDahuabiJpanel.this.chooseLeft.setText(getname);
                    TraslationWantBuyDahuabiJpanel.this.chooseLeftModel.setVisible(false);
                    TraslationWantBuyDahuabiJpanel.this.leftFlag = 1;
                    TraslationWantBuyDahuabiJpanel.this.dangqianyeshu = 1;
                    SearchGoodsBean searchGoodsBean = new SearchGoodsBean();
                    searchGoodsBean.setSaletype(Integer.valueOf(2));
                    searchGoodsBean.setContiontype(TraslationWantBuyDahuabiJpanel.changeMath(TraslationWantBuyDahuabiJpanel.this.chooseLeft.getText()) + "-" + TraslationWantBuyDahuabiJpanel.changeMath(TraslationWantBuyDahuabiJpanel.this.chooseRight.getText()));
                    searchGoodsBean.setPageNum(Integer.valueOf(TraslationWantBuyDahuabiJpanel.this.dangqianyeshu));
                    searchGoodsBean.setOrder(TraslationWantBuyDahuabiJpanel.this.showOrder);
                    String sendmes = Agreement.getAgreement().searchGoodsAgreement(GsonUtil.getGsonUtil().getgson().toJson(searchGoodsBean));
                    SendMessageUntil.toServer(sendmes);
                    TrslationMainJframe.getTrslationMainJframe().setPanelOpen(2);
                }
            });
            this.chooseRightModel.getJlist().addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    String getname = (String)TraslationWantBuyDahuabiJpanel.this.chooseRightModel.getJlist().getSelectedValue();
                    TraslationWantBuyDahuabiJpanel.this.chooseRight.setText(getname);
                    TraslationWantBuyDahuabiJpanel.this.chooseRightModel.setVisible(false);
                    TraslationWantBuyDahuabiJpanel.this.rightFlag = 1;
                    TraslationWantBuyDahuabiJpanel.this.dangqianyeshu = 1;
                    SearchGoodsBean searchGoodsBean = new SearchGoodsBean();
                    searchGoodsBean.setSaletype(Integer.valueOf(2));
                    searchGoodsBean.setContiontype(TraslationWantBuyDahuabiJpanel.changeMath(TraslationWantBuyDahuabiJpanel.this.chooseLeft.getText()) + "-" + TraslationWantBuyDahuabiJpanel.changeMath(TraslationWantBuyDahuabiJpanel.this.chooseRight.getText()));
                    searchGoodsBean.setPageNum(Integer.valueOf(TraslationWantBuyDahuabiJpanel.this.dangqianyeshu));
                    searchGoodsBean.setOrder(TraslationWantBuyDahuabiJpanel.this.showOrder);
                    String sendmes = Agreement.getAgreement().searchGoodsAgreement(GsonUtil.getGsonUtil().getgson().toJson(searchGoodsBean));
                    SendMessageUntil.toServer(sendmes);
                    TrslationMainJframe.getTrslationMainJframe().setPanelOpen(2);
                }
            });
            this.add(this.chooseLeftArrows);
            this.add(this.chooseRightArrows);
            this.add(this.chooseLeftModel, Integer.valueOf(10), 2);
            this.add(this.chooseLeft);
            this.add(this.chooseRight);
            this.add(this.chooseRightModel, Integer.valueOf(10), 4);
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
                    if (TraslationWantBuyDahuabiJpanel.this.dangqianyeshu != 1) {
                        TraslationWantBuyDahuabiJpanel.this.dangqianyeshu = 1;
                        TraslationWantBuyDahuabiJpanel.this.yema.setText(TraslationWantBuyDahuabiJpanel.this.dangqianyeshu + "/" + TraslationWantBuyDahuabiJpanel.this.zuidayema);
                        SearchGoodsBean searchGoodsBean = new SearchGoodsBean();
                        searchGoodsBean.setSaletype(Integer.valueOf(2));
                        searchGoodsBean.setContiontype(TraslationWantBuyDahuabiJpanel.changeMath(TraslationWantBuyDahuabiJpanel.this.chooseLeft.getText()) + "-" + TraslationWantBuyDahuabiJpanel.changeMath(TraslationWantBuyDahuabiJpanel.this.chooseRight.getText()));
                        searchGoodsBean.setPageNum(Integer.valueOf(TraslationWantBuyDahuabiJpanel.this.dangqianyeshu));
                        searchGoodsBean.setOrder(TraslationWantBuyDahuabiJpanel.this.showOrder);
                        String sendmes = Agreement.getAgreement().searchGoodsAgreement(GsonUtil.getGsonUtil().getgson().toJson(searchGoodsBean));
                        SendMessageUntil.toServer(sendmes);
                        TrslationMainJframe.getTrslationMainJframe().setPanelOpen(2);
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
                    if (TraslationWantBuyDahuabiJpanel.this.dangqianyeshu > 1) {
                        TraslationWantBuyDahuabiJpanel.this.dangqianyeshu--;
                        TraslationWantBuyDahuabiJpanel.this.yema.setText(TraslationWantBuyDahuabiJpanel.this.dangqianyeshu + "/" + TraslationWantBuyDahuabiJpanel.this.zuidayema);
                        SearchGoodsBean searchGoodsBean = new SearchGoodsBean();
                        searchGoodsBean.setSaletype(Integer.valueOf(2));
                        searchGoodsBean.setContiontype(TraslationWantBuyDahuabiJpanel.changeMath(TraslationWantBuyDahuabiJpanel.this.chooseLeft.getText()) + "-" + TraslationWantBuyDahuabiJpanel.changeMath(TraslationWantBuyDahuabiJpanel.this.chooseRight.getText()));
                        searchGoodsBean.setPageNum(Integer.valueOf(TraslationWantBuyDahuabiJpanel.this.dangqianyeshu));
                        searchGoodsBean.setOrder(TraslationWantBuyDahuabiJpanel.this.showOrder);
                        String sendmes = Agreement.getAgreement().searchGoodsAgreement(GsonUtil.getGsonUtil().getgson().toJson(searchGoodsBean));
                        SendMessageUntil.toServer(sendmes);
                        TrslationMainJframe.getTrslationMainJframe().setPanelOpen(2);
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
                    if (TraslationWantBuyDahuabiJpanel.this.dangqianyeshu != TraslationWantBuyDahuabiJpanel.this.zuidayema) {
                        TraslationWantBuyDahuabiJpanel.this.dangqianyeshu = TraslationWantBuyDahuabiJpanel.this.zuidayema;
                        TraslationWantBuyDahuabiJpanel.this.yema.setText(TraslationWantBuyDahuabiJpanel.this.dangqianyeshu + "/" + TraslationWantBuyDahuabiJpanel.this.zuidayema);
                        SearchGoodsBean searchGoodsBean = new SearchGoodsBean();
                        searchGoodsBean.setSaletype(Integer.valueOf(2));
                        searchGoodsBean.setContiontype(TraslationWantBuyDahuabiJpanel.changeMath(TraslationWantBuyDahuabiJpanel.this.chooseLeft.getText()) + "-" + TraslationWantBuyDahuabiJpanel.changeMath(TraslationWantBuyDahuabiJpanel.this.chooseRight.getText()));
                        searchGoodsBean.setPageNum(Integer.valueOf(TraslationWantBuyDahuabiJpanel.this.dangqianyeshu));
                        searchGoodsBean.setOrder(TraslationWantBuyDahuabiJpanel.this.showOrder);
                        String sendmes = Agreement.getAgreement().searchGoodsAgreement(GsonUtil.getGsonUtil().getgson().toJson(searchGoodsBean));
                        SendMessageUntil.toServer(sendmes);
                        TrslationMainJframe.getTrslationMainJframe().setPanelOpen(2);
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
                    if (TraslationWantBuyDahuabiJpanel.this.dangqianyeshu < TraslationWantBuyDahuabiJpanel.this.zuidayema) {
                        TraslationWantBuyDahuabiJpanel.this.dangqianyeshu++;
                        TraslationWantBuyDahuabiJpanel.this.yema.setText(TraslationWantBuyDahuabiJpanel.this.dangqianyeshu + "/" + TraslationWantBuyDahuabiJpanel.this.zuidayema);
                        SearchGoodsBean searchGoodsBean = new SearchGoodsBean();
                        searchGoodsBean.setSaletype(Integer.valueOf(2));
                        searchGoodsBean.setContiontype(TraslationWantBuyDahuabiJpanel.changeMath(TraslationWantBuyDahuabiJpanel.this.chooseLeft.getText()) + "-" + TraslationWantBuyDahuabiJpanel.changeMath(TraslationWantBuyDahuabiJpanel.this.chooseRight.getText()));
                        searchGoodsBean.setPageNum(Integer.valueOf(TraslationWantBuyDahuabiJpanel.this.dangqianyeshu));
                        searchGoodsBean.setOrder(TraslationWantBuyDahuabiJpanel.this.showOrder);
                        String sendmes = Agreement.getAgreement().searchGoodsAgreement(GsonUtil.getGsonUtil().getgson().toJson(searchGoodsBean));
                        SendMessageUntil.toServer(sendmes);
                        TrslationMainJframe.getTrslationMainJframe().setPanelOpen(2);
                    }
                    else {
                        ZhuFrame.getZhuJpanel().addPrompt2("已经没有下一页了");
                    }
                }
            });
            (this.upSort = new JLabel()).setBounds(333, 45, 6, 11);
            this.upSort.setBorder(null);
            this.upSort.setBackground(null);
            this.upSort.setOpaque(false);
            this.upSort.setIcon(CutButtonImage.getImage("img/xy2uiimg/↑(白色)w6,h11px.png", 6, 11));
            this.add(this.upSort);
            this.upSort.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    TraslationWantBuyDahuabiJpanel.this.upSort.setIcon(CutButtonImage.getImage("img/xy2uiimg/↑(红色)w6,h11px.png", 6, 11));
                    TraslationWantBuyDahuabiJpanel.this.downSort.setIcon(CutButtonImage.getImage("img/xy2uiimg/↓(白色)w6,h11px.png", 6, 11));
                    TraslationWantBuyDahuabiJpanel.this.dangqianyeshu = 1;
                    TraslationWantBuyDahuabiJpanel.this.showOrder = 1;
                    SearchGoodsBean searchGoodsBean = new SearchGoodsBean();
                    searchGoodsBean.setSaletype(Integer.valueOf(2));
                    searchGoodsBean.setContiontype(TraslationWantBuyDahuabiJpanel.changeMath(TraslationWantBuyDahuabiJpanel.this.chooseLeft.getText()) + "-" + TraslationWantBuyDahuabiJpanel.changeMath(TraslationWantBuyDahuabiJpanel.this.chooseRight.getText()));
                    searchGoodsBean.setPageNum(Integer.valueOf(1));
                    searchGoodsBean.setOrder(TraslationWantBuyDahuabiJpanel.this.showOrder);
                    String sendmes = Agreement.getAgreement().searchGoodsAgreement(GsonUtil.getGsonUtil().getgson().toJson(searchGoodsBean));
                    SendMessageUntil.toServer(sendmes);
                    TrslationMainJframe.getTrslationMainJframe().setPanelOpen(2);
                }
            });
            (this.downSort = new JLabel()).setBounds(343, 45, 6, 11);
            this.downSort.setBorder(null);
            this.downSort.setBackground(null);
            this.downSort.setOpaque(false);
            this.downSort.setIcon(CutButtonImage.getImage("img/xy2uiimg/↓(白色)w6,h11px.png", 6, 11));
            this.add(this.downSort);
            this.downSort.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    TraslationWantBuyDahuabiJpanel.this.upSort.setIcon(CutButtonImage.getImage("img/xy2uiimg/↑(白色)w6,h11px.png", 6, 11));
                    TraslationWantBuyDahuabiJpanel.this.downSort.setIcon(CutButtonImage.getImage("img/xy2uiimg/↓(红色)w6,h11px.png", 6, 11));
                    TraslationWantBuyDahuabiJpanel.this.dangqianyeshu = 1;
                    TraslationWantBuyDahuabiJpanel.this.showOrder = 2;
                    SearchGoodsBean searchGoodsBean = new SearchGoodsBean();
                    searchGoodsBean.setSaletype(Integer.valueOf(2));
                    searchGoodsBean.setContiontype(TraslationWantBuyDahuabiJpanel.changeMath(TraslationWantBuyDahuabiJpanel.this.chooseLeft.getText()) + "-" + TraslationWantBuyDahuabiJpanel.changeMath(TraslationWantBuyDahuabiJpanel.this.chooseRight.getText()));
                    searchGoodsBean.setPageNum(Integer.valueOf(1));
                    searchGoodsBean.setOrder(TraslationWantBuyDahuabiJpanel.this.showOrder);
                    String sendmes = Agreement.getAgreement().searchGoodsAgreement(GsonUtil.getGsonUtil().getgson().toJson(searchGoodsBean));
                    SendMessageUntil.toServer(sendmes);
                    TrslationMainJframe.getTrslationMainJframe().setPanelOpen(2);
                }
            });
            this.add(this.jScrollPane, Integer.valueOf(10), -1);
        }
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        if (MyIsif.getStyle().equals("水墨")) {
            super.paintComponent(g);
            if (this.icon1 == null) {
                this.icon1 = new ImageIcon("inkImg/background/25.png");
            }
            g.drawImage(this.icon1.getImage(), 0, 0, 590, 380, this);
        }
        else {
            super.paintComponent(g);
            if (this.icon1 == null) {
                this.icon1 = new ImageIcon("img/xy2uiimg/我要买-大话币w590,h380px，top97,left22px.png");
            }
            g.drawImage(this.icon1.getImage(), 0, 0, 590, 380, this);
            if (this.icon2 == null) {
                this.icon2 = new ImageIcon("img/xy2uiimg/35_png.button.xy_vscroll$down_副本.png");
            }
            g.drawImage(this.icon2.getImage(), 145, 13, 19, 20, this);
            if (this.icon3 == null) {
                this.icon3 = new ImageIcon("img/xy2uiimg/35_png.button.xy_vscroll$down_副本.png");
            }
            g.drawImage(this.icon3.getImage(), 272, 13, 19, 20, this);
        }
    }
    
    public void setPage(int zuidayema) {
        this.zuidayema = zuidayema;
        this.yema.setText(this.dangqianyeshu + "/" + this.zuidayema);
    }
    
    public static BigDecimal changeMath(String mathChina) {
        BigDecimal math = null;
        int n = -1;
        switch (mathChina.hashCode()) {
            case 639167: {
                if (mathChina.equals("一亿")) {
                    n = 0;
                    break;
                }
                else {
                    break;
                }
            }
            case 680862: {
                if (mathChina.equals("十亿")) {
                    n = 1;
                    break;
                }
                else {
                    break;
                }
            }
            case 20004650: {
                if (mathChina.equals("二十亿")) {
                    n = 2;
                    break;
                }
                else {
                    break;
                }
            }
            case 19878759: {
                if (mathChina.equals("三十亿")) {
                    n = 3;
                    break;
                }
                else {
                    break;
                }
            }
            case 22048697: {
                if (mathChina.equals("四十亿")) {
                    n = 4;
                    break;
                }
                else {
                    break;
                }
            }
            case 20012338: {
                if (mathChina.equals("五十亿")) {
                    n = 5;
                    break;
                }
                else {
                    break;
                }
            }
            case 20712907: {
                if (mathChina.equals("六十亿")) {
                    n = 6;
                    break;
                }
                else {
                    break;
                }
            }
            case 19872993: {
                if (mathChina.equals("七十亿")) {
                    n = 7;
                    break;
                }
                else {
                    break;
                }
            }
            case 20710985: {
                if (mathChina.equals("八十亿")) {
                    n = 8;
                    break;
                }
                else {
                    break;
                }
            }
            case 19959483: {
                if (mathChina.equals("九十亿")) {
                    n = 9;
                    break;
                }
                else {
                    break;
                }
            }
            case 20149761: {
                if (mathChina.equals("一百亿")) {
                    n = 10;
                    break;
                }
                else {
                    break;
                }
            }
        }
        switch (n) {
            case 0: {
                math = new BigDecimal("100000000");
                break;
            }
            case 1: {
                math = new BigDecimal("1000000000");
                break;
            }
            case 2: {
                math = new BigDecimal("2000000000");
                break;
            }
            case 3: {
                math = new BigDecimal("3000000000");
                break;
            }
            case 4: {
                math = new BigDecimal("4000000000");
                break;
            }
            case 5: {
                math = new BigDecimal("5000000000");
                break;
            }
            case 6: {
                math = new BigDecimal("6000000000");
                break;
            }
            case 7: {
                math = new BigDecimal("7000000000");
                break;
            }
            case 8: {
                math = new BigDecimal("8000000000");
                break;
            }
            case 9: {
                math = new BigDecimal("9000000000");
                break;
            }
            case 10: {
                math = new BigDecimal("10000000000");
                break;
            }
        }
        return math;
    }
    
    public JLabel getChooseLeft() {
        return this.chooseLeft;
    }
    
    public void setChooseLeft(JLabel chooseLeft) {
        this.chooseLeft = chooseLeft;
    }
    
    public JLabel getChooseRight() {
        return this.chooseRight;
    }
    
    public void setChooseRight(JLabel chooseRight) {
        this.chooseRight = chooseRight;
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
    
    public TraslationSelectOptionJpanel getChooseLeftModel() {
        return this.chooseLeftModel;
    }
    
    public void setChooseLeftModel(TraslationSelectOptionJpanel chooseLeftModel) {
        this.chooseLeftModel = chooseLeftModel;
    }
    
    public TraslationSelectOptionJpanel getChooseRightModel() {
        return this.chooseRightModel;
    }
    
    public void setChooseRightModel(TraslationSelectOptionJpanel chooseRightModel) {
        this.chooseRightModel = chooseRightModel;
    }
    
    public int getLeftFlag() {
        return this.leftFlag;
    }
    
    public void setLeftFlag(int leftFlag) {
        this.leftFlag = leftFlag;
    }
    
    public int getRightFlag() {
        return this.rightFlag;
    }
    
    public void setRightFlag(int rightFlag) {
        this.rightFlag = rightFlag;
    }
    
    public int getShowOrder() {
        return this.showOrder;
    }
    
    public void setShowOrder(int showOrder) {
        this.showOrder = showOrder;
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
    
    public TrslationBtn getChooseLeftArrows() {
        return this.chooseLeftArrows;
    }
    
    public void setChooseLeftArrows(TrslationBtn chooseLeftArrows) {
        this.chooseLeftArrows = chooseLeftArrows;
    }
    
    public TrslationBtn getChooseRightArrows() {
        return this.chooseRightArrows;
    }
    
    public void setChooseRightArrows(TrslationBtn chooseRightArrows) {
        this.chooseRightArrows = chooseRightArrows;
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
    
    public TraslationWantBuyDahuabiModelJpanel getBuyDahuabiModelJpanel() {
        return this.buyDahuabiModelJpanel;
    }
    
    public void setBuyDahuabiModelJpanel(TraslationWantBuyDahuabiModelJpanel buyDahuabiModelJpanel) {
        this.buyDahuabiModelJpanel = buyDahuabiModelJpanel;
    }
    
    public ImageIcon getIcon1() {
        return this.icon1;
    }
    
    public void setIcon1(ImageIcon icon1) {
        this.icon1 = icon1;
    }
}
