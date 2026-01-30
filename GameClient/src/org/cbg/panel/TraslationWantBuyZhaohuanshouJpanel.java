package org.cbg.panel;

import java.awt.Graphics;
import org.come.until.CutButtonImage;
import org.come.Frame.ZhuFrame;
import com.tool.tcpimg.UIUtils;
import org.cbg.frame.TrslationMainJframe;
import org.come.socket.SendMessageUntil;
import org.come.until.GsonUtil;
import org.come.socket.Agreement;
import org.cbg.until.TreasurePavilionSearchUntil;
import org.cbg.bean.SearchGoodsBean;
import java.awt.event.MouseEvent;
import java.awt.event.MouseAdapter;
import java.awt.Color;
import org.cbg.until.TraslationTableUntil;
import java.awt.Dimension;
import com.updateNew.MyIsif;
import javax.swing.ImageIcon;
import javax.swing.JScrollPane;
import org.cbg.btn.TrslationBtn;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;

public class TraslationWantBuyZhaohuanshouJpanel extends JLayeredPane
{
    private static TraslationWantBuyZhaohuanshouJpanel traslationWantBuyZhaohuanshouJpanel;
    private JLabel chooseLeft;
    private JLabel yema;
    private JLabel upSort;
    private JLabel downSort;
    private TraslationSelectOptionJpanel chooseLeftModel;
    private int leftFlag;
    private int showOrder;
    private TrslationBtn shouye;
    private TrslationBtn moye;
    private TrslationBtn leftArrows;
    private TrslationBtn rightArrows;
    private TrslationBtn chooseLeftArrows;
    private int dangqianyeshu;
    private int zuidayema;
    private JScrollPane jScrollPane;
    private ImageIcon icon1;
    private ImageIcon icon2;
    
    public TraslationWantBuyZhaohuanshouJpanel() {
        this.leftFlag = 1;
        this.showOrder = 0;
        this.dangqianyeshu = 1;
        this.zuidayema = 50;
        if (MyIsif.getStyle().equals("水墨")) {
            (TraslationWantBuyZhaohuanshouJpanel.traslationWantBuyZhaohuanshouJpanel = this).setPreferredSize(new Dimension(590, 380));
            this.setOpaque(false);
            this.setLayout(null);
            (this.jScrollPane = new JScrollPane()).setBounds(8, 60, 571, 289);
            TraslationTableUntil.TableModel(this.jScrollPane, 10);
            this.jScrollPane.getVerticalScrollBar().setUnitIncrement(50);
            (this.chooseLeftArrows = new TrslationBtn("inkImg/button/8.png", 1)).setBounds(123, 13, 19, 20);
            this.chooseLeftArrows.setOpaque(false);
            this.add(this.chooseLeftArrows);
            (this.chooseLeft = new JLabel("全部类型")).setBounds(25, 10, 118, 20);
            this.chooseLeft.setForeground(Color.white);
            this.chooseLeft.setOpaque(false);
            this.add(this.chooseLeft);
            String[] rowDataLeft = { "全部类型", "1-14称", "特殊召唤兽", "高级守护", "神兽" };
            (this.chooseLeftModel = new TraslationSelectOptionJpanel(118, 120, "inkImg/background/19.png", rowDataLeft)).setBounds(22, 30, 118, 120);
            this.chooseLeftModel.setVisible(false);
            this.add(this.chooseLeftModel, Integer.valueOf(10), 1);
            this.chooseLeftArrows.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    if (TraslationWantBuyZhaohuanshouJpanel.this.leftFlag == 1) {
                        TraslationWantBuyZhaohuanshouJpanel.this.chooseLeftModel.setVisible(true);
                        TraslationWantBuyZhaohuanshouJpanel.this.leftFlag = 0;
                    }
                    else {
                        TraslationWantBuyZhaohuanshouJpanel.this.chooseLeftModel.setVisible(false);
                        TraslationWantBuyZhaohuanshouJpanel.this.leftFlag = 1;
                    }
                }
            });
            this.chooseLeftModel.getJlist().addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    int count = TraslationWantBuyZhaohuanshouJpanel.this.chooseLeftModel.getJlist().getSelectedIndex();
                    String getname = (String)TraslationWantBuyZhaohuanshouJpanel.this.chooseLeftModel.getJlist().getSelectedValue();
                    TraslationWantBuyZhaohuanshouJpanel.this.chooseLeft.setText(getname);
                    TraslationWantBuyZhaohuanshouJpanel.this.chooseLeftModel.setVisible(false);
                    TraslationWantBuyZhaohuanshouJpanel.this.leftFlag = 1;
                    TraslationWantBuyZhaohuanshouJpanel.this.dangqianyeshu = 1;
                    SearchGoodsBean searchGoodsBean = new SearchGoodsBean();
                    searchGoodsBean.setSaletype(Integer.valueOf(4));
                    searchGoodsBean.setContiontype(TreasurePavilionSearchUntil.getGuardianForceType(TraslationWantBuyZhaohuanshouJpanel.this.chooseLeft.getText()));
                    searchGoodsBean.setPageNum(Integer.valueOf(TraslationWantBuyZhaohuanshouJpanel.this.dangqianyeshu));
                    searchGoodsBean.setOrder(TraslationWantBuyZhaohuanshouJpanel.this.showOrder);
                    String sendmes = Agreement.getAgreement().searchGoodsAgreement(GsonUtil.getGsonUtil().getgson().toJson(searchGoodsBean));
                    SendMessageUntil.toServer(sendmes);
                    TrslationMainJframe.getTrslationMainJframe().setPanelOpen(4);
                }
            });
            this.add(this.jScrollPane, Integer.valueOf(10), -1);
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
                    if (TraslationWantBuyZhaohuanshouJpanel.this.dangqianyeshu != 1) {
                        TraslationWantBuyZhaohuanshouJpanel.this.dangqianyeshu = 1;
                        TraslationWantBuyZhaohuanshouJpanel.this.yema.setText(TraslationWantBuyZhaohuanshouJpanel.this.dangqianyeshu + "/" + TraslationWantBuyZhaohuanshouJpanel.this.zuidayema);
                        SearchGoodsBean searchGoodsBean = new SearchGoodsBean();
                        searchGoodsBean.setSaletype(Integer.valueOf(4));
                        searchGoodsBean.setContiontype(TreasurePavilionSearchUntil.getGuardianForceType(TraslationWantBuyZhaohuanshouJpanel.this.chooseLeft.getText()));
                        searchGoodsBean.setPageNum(Integer.valueOf(TraslationWantBuyZhaohuanshouJpanel.this.dangqianyeshu));
                        searchGoodsBean.setOrder(TraslationWantBuyZhaohuanshouJpanel.this.showOrder);
                        String sendmes = Agreement.getAgreement().searchGoodsAgreement(GsonUtil.getGsonUtil().getgson().toJson(searchGoodsBean));
                        SendMessageUntil.toServer(sendmes);
                        TrslationMainJframe.getTrslationMainJframe().setPanelOpen(4);
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
                    if (TraslationWantBuyZhaohuanshouJpanel.this.dangqianyeshu > 1) {
                        TraslationWantBuyZhaohuanshouJpanel.this.dangqianyeshu--;
                        TraslationWantBuyZhaohuanshouJpanel.this.yema.setText(TraslationWantBuyZhaohuanshouJpanel.this.dangqianyeshu + "/" + TraslationWantBuyZhaohuanshouJpanel.this.zuidayema);
                        SearchGoodsBean searchGoodsBean = new SearchGoodsBean();
                        searchGoodsBean.setSaletype(Integer.valueOf(4));
                        searchGoodsBean.setContiontype(TreasurePavilionSearchUntil.getGuardianForceType(TraslationWantBuyZhaohuanshouJpanel.this.chooseLeft.getText()));
                        searchGoodsBean.setPageNum(Integer.valueOf(TraslationWantBuyZhaohuanshouJpanel.this.dangqianyeshu));
                        searchGoodsBean.setOrder(TraslationWantBuyZhaohuanshouJpanel.this.showOrder);
                        String sendmes = Agreement.getAgreement().searchGoodsAgreement(GsonUtil.getGsonUtil().getgson().toJson(searchGoodsBean));
                        SendMessageUntil.toServer(sendmes);
                        TrslationMainJframe.getTrslationMainJframe().setPanelOpen(4);
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
                    if (TraslationWantBuyZhaohuanshouJpanel.this.dangqianyeshu != TraslationWantBuyZhaohuanshouJpanel.this.zuidayema) {
                        TraslationWantBuyZhaohuanshouJpanel.this.dangqianyeshu = TraslationWantBuyZhaohuanshouJpanel.this.zuidayema;
                        TraslationWantBuyZhaohuanshouJpanel.this.yema.setText(TraslationWantBuyZhaohuanshouJpanel.this.dangqianyeshu + "/" + TraslationWantBuyZhaohuanshouJpanel.this.zuidayema);
                        SearchGoodsBean searchGoodsBean = new SearchGoodsBean();
                        searchGoodsBean.setSaletype(Integer.valueOf(4));
                        searchGoodsBean.setContiontype(TreasurePavilionSearchUntil.getGuardianForceType(TraslationWantBuyZhaohuanshouJpanel.this.chooseLeft.getText()));
                        searchGoodsBean.setPageNum(Integer.valueOf(TraslationWantBuyZhaohuanshouJpanel.this.dangqianyeshu));
                        searchGoodsBean.setOrder(TraslationWantBuyZhaohuanshouJpanel.this.showOrder);
                        String sendmes = Agreement.getAgreement().searchGoodsAgreement(GsonUtil.getGsonUtil().getgson().toJson(searchGoodsBean));
                        SendMessageUntil.toServer(sendmes);
                        TrslationMainJframe.getTrslationMainJframe().setPanelOpen(4);
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
                    if (TraslationWantBuyZhaohuanshouJpanel.this.dangqianyeshu < TraslationWantBuyZhaohuanshouJpanel.this.zuidayema) {
                        TraslationWantBuyZhaohuanshouJpanel.this.dangqianyeshu++;
                        TraslationWantBuyZhaohuanshouJpanel.this.yema.setText(TraslationWantBuyZhaohuanshouJpanel.this.dangqianyeshu + "/" + TraslationWantBuyZhaohuanshouJpanel.this.zuidayema);
                        SearchGoodsBean searchGoodsBean = new SearchGoodsBean();
                        searchGoodsBean.setSaletype(Integer.valueOf(4));
                        searchGoodsBean.setContiontype(TreasurePavilionSearchUntil.getGuardianForceType(TraslationWantBuyZhaohuanshouJpanel.this.chooseLeft.getText()));
                        searchGoodsBean.setPageNum(Integer.valueOf(TraslationWantBuyZhaohuanshouJpanel.this.dangqianyeshu));
                        searchGoodsBean.setOrder(TraslationWantBuyZhaohuanshouJpanel.this.showOrder);
                        String sendmes = Agreement.getAgreement().searchGoodsAgreement(GsonUtil.getGsonUtil().getgson().toJson(searchGoodsBean));
                        SendMessageUntil.toServer(sendmes);
                        TrslationMainJframe.getTrslationMainJframe().setPanelOpen(4);
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
                    TraslationWantBuyZhaohuanshouJpanel.this.upSort.setIcon(CutButtonImage.getImage("img/xy2uiimg/↑(红色)w6,h11px.png", 6, 11));
                    TraslationWantBuyZhaohuanshouJpanel.this.downSort.setIcon(CutButtonImage.getImage("img/xy2uiimg/↓(白色)w6,h11px.png", 6, 11));
                    TraslationWantBuyZhaohuanshouJpanel.this.dangqianyeshu = 1;
                    TraslationWantBuyZhaohuanshouJpanel.this.showOrder = 1;
                    SearchGoodsBean searchGoodsBean = new SearchGoodsBean();
                    searchGoodsBean.setSaletype(Integer.valueOf(4));
                    searchGoodsBean.setContiontype(TreasurePavilionSearchUntil.getGuardianForceType(TraslationWantBuyZhaohuanshouJpanel.this.chooseLeft.getText()));
                    searchGoodsBean.setPageNum(Integer.valueOf(TraslationWantBuyZhaohuanshouJpanel.this.dangqianyeshu));
                    searchGoodsBean.setOrder(TraslationWantBuyZhaohuanshouJpanel.this.showOrder);
                    String sendmes = Agreement.getAgreement().searchGoodsAgreement(GsonUtil.getGsonUtil().getgson().toJson(searchGoodsBean));
                    SendMessageUntil.toServer(sendmes);
                    TrslationMainJframe.getTrslationMainJframe().setPanelOpen(4);
                }
            });
            (this.downSort = new JLabel()).setBounds(343, 43, 6, 11);
            this.downSort.setBorder(null);
            this.downSort.setBackground(null);
            this.downSort.setOpaque(false);
            this.downSort.setIcon(CutButtonImage.getImage("img/xy2uiimg/↓(红色)w6,h11px.png", 6, 11));
            this.add(this.downSort);
            this.downSort.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    TraslationWantBuyZhaohuanshouJpanel.this.upSort.setIcon(CutButtonImage.getImage("img/xy2uiimg/↑(白色)w6,h11px.png", 6, 11));
                    TraslationWantBuyZhaohuanshouJpanel.this.downSort.setIcon(CutButtonImage.getImage("img/xy2uiimg/↓(红色)w6,h11px.png", 6, 11));
                    TraslationWantBuyZhaohuanshouJpanel.this.dangqianyeshu = 1;
                    TraslationWantBuyZhaohuanshouJpanel.this.showOrder = 2;
                    SearchGoodsBean searchGoodsBean = new SearchGoodsBean();
                    searchGoodsBean.setSaletype(Integer.valueOf(4));
                    searchGoodsBean.setContiontype(TreasurePavilionSearchUntil.getGuardianForceType(TraslationWantBuyZhaohuanshouJpanel.this.chooseLeft.getText()));
                    searchGoodsBean.setPageNum(Integer.valueOf(TraslationWantBuyZhaohuanshouJpanel.this.dangqianyeshu));
                    searchGoodsBean.setOrder(TraslationWantBuyZhaohuanshouJpanel.this.showOrder);
                    String sendmes = Agreement.getAgreement().searchGoodsAgreement(GsonUtil.getGsonUtil().getgson().toJson(searchGoodsBean));
                    SendMessageUntil.toServer(sendmes);
                    TrslationMainJframe.getTrslationMainJframe().setPanelOpen(4);
                }
            });
        }
        else {
            (TraslationWantBuyZhaohuanshouJpanel.traslationWantBuyZhaohuanshouJpanel = this).setPreferredSize(new Dimension(590, 380));
            this.setOpaque(false);
            this.setLayout(null);
            (this.jScrollPane = new JScrollPane()).setOpaque(false);
            this.jScrollPane.setBounds(8, 58, 568, 287);
            this.jScrollPane.setBorder(null);
            (this.jScrollPane = new JScrollPane()).setBounds(8, 58, 565, 287);
            TraslationTableUntil.TableModel(this.jScrollPane, 10);
            (this.chooseLeftArrows = new TrslationBtn("img/xy2uiimg/34_png.button.xy_vscroll$down.png", 1)).setBounds(123, 13, 19, 20);
            this.chooseLeftArrows.setOpaque(false);
            this.add(this.chooseLeftArrows);
            (this.chooseLeft = new JLabel("全部类型")).setBounds(25, 10, 118, 20);
            this.chooseLeft.setForeground(Color.white);
            this.chooseLeft.setOpaque(false);
            this.add(this.chooseLeft);
            String[] rowDataLeft = { "全部类型", "1-14称", "特殊召唤兽", "高级守护", "神兽" };
            (this.chooseLeftModel = new TraslationSelectOptionJpanel(118, 120, "img/xy2uiimg/下拉框(2)w118,h120px.png", rowDataLeft)).setBounds(22, 30, 118, 120);
            this.chooseLeftModel.setVisible(false);
            this.add(this.chooseLeftModel, Integer.valueOf(10), 1);
            this.chooseLeftArrows.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    if (TraslationWantBuyZhaohuanshouJpanel.this.leftFlag == 1) {
                        TraslationWantBuyZhaohuanshouJpanel.this.chooseLeftModel.setVisible(true);
                        TraslationWantBuyZhaohuanshouJpanel.this.leftFlag = 0;
                    }
                    else {
                        TraslationWantBuyZhaohuanshouJpanel.this.chooseLeftModel.setVisible(false);
                        TraslationWantBuyZhaohuanshouJpanel.this.leftFlag = 1;
                    }
                }
            });
            this.chooseLeftModel.getJlist().addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    int count = TraslationWantBuyZhaohuanshouJpanel.this.chooseLeftModel.getJlist().getSelectedIndex();
                    String getname = (String)TraslationWantBuyZhaohuanshouJpanel.this.chooseLeftModel.getJlist().getSelectedValue();
                    TraslationWantBuyZhaohuanshouJpanel.this.chooseLeft.setText(getname);
                    TraslationWantBuyZhaohuanshouJpanel.this.chooseLeftModel.setVisible(false);
                    TraslationWantBuyZhaohuanshouJpanel.this.leftFlag = 1;
                    TraslationWantBuyZhaohuanshouJpanel.this.dangqianyeshu = 1;
                    SearchGoodsBean searchGoodsBean = new SearchGoodsBean();
                    searchGoodsBean.setSaletype(Integer.valueOf(4));
                    searchGoodsBean.setContiontype(TreasurePavilionSearchUntil.getGuardianForceType(TraslationWantBuyZhaohuanshouJpanel.this.chooseLeft.getText()));
                    searchGoodsBean.setPageNum(Integer.valueOf(TraslationWantBuyZhaohuanshouJpanel.this.dangqianyeshu));
                    searchGoodsBean.setOrder(TraslationWantBuyZhaohuanshouJpanel.this.showOrder);
                    String sendmes = Agreement.getAgreement().searchGoodsAgreement(GsonUtil.getGsonUtil().getgson().toJson(searchGoodsBean));
                    SendMessageUntil.toServer(sendmes);
                    TrslationMainJframe.getTrslationMainJframe().setPanelOpen(4);
                }
            });
            this.add(this.jScrollPane, Integer.valueOf(10), -1);
            (this.yema = new JLabel(this.dangqianyeshu + "/" + this.zuidayema)).setBounds(258, 356, 58, 17);
            this.yema.setForeground(Color.white);
            this.yema.setOpaque(false);
            this.yema.setHorizontalAlignment(0);
            this.add(this.yema);
            (this.shouye = new TrslationBtn("inkimg/hongmu/21_png.button.tab_nex.png", 1, UIUtils.COLOR_BTNTEXT, UIUtils.TEXT_FONT, "首页")).setBounds(200, 354, 30, 17);
            this.add(this.shouye);
            this.shouye.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    if (TraslationWantBuyZhaohuanshouJpanel.this.dangqianyeshu != 1) {
                        TraslationWantBuyZhaohuanshouJpanel.this.dangqianyeshu = 1;
                        TraslationWantBuyZhaohuanshouJpanel.this.yema.setText(TraslationWantBuyZhaohuanshouJpanel.this.dangqianyeshu + "/" + TraslationWantBuyZhaohuanshouJpanel.this.zuidayema);
                        SearchGoodsBean searchGoodsBean = new SearchGoodsBean();
                        searchGoodsBean.setSaletype(Integer.valueOf(4));
                        searchGoodsBean.setContiontype(TreasurePavilionSearchUntil.getGuardianForceType(TraslationWantBuyZhaohuanshouJpanel.this.chooseLeft.getText()));
                        searchGoodsBean.setPageNum(Integer.valueOf(TraslationWantBuyZhaohuanshouJpanel.this.dangqianyeshu));
                        searchGoodsBean.setOrder(TraslationWantBuyZhaohuanshouJpanel.this.showOrder);
                        String sendmes = Agreement.getAgreement().searchGoodsAgreement(GsonUtil.getGsonUtil().getgson().toJson(searchGoodsBean));
                        SendMessageUntil.toServer(sendmes);
                        TrslationMainJframe.getTrslationMainJframe().setPanelOpen(4);
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
                    if (TraslationWantBuyZhaohuanshouJpanel.this.dangqianyeshu > 1) {
                        TraslationWantBuyZhaohuanshouJpanel.this.dangqianyeshu--;
                        TraslationWantBuyZhaohuanshouJpanel.this.yema.setText(TraslationWantBuyZhaohuanshouJpanel.this.dangqianyeshu + "/" + TraslationWantBuyZhaohuanshouJpanel.this.zuidayema);
                        SearchGoodsBean searchGoodsBean = new SearchGoodsBean();
                        searchGoodsBean.setSaletype(Integer.valueOf(4));
                        searchGoodsBean.setContiontype(TreasurePavilionSearchUntil.getGuardianForceType(TraslationWantBuyZhaohuanshouJpanel.this.chooseLeft.getText()));
                        searchGoodsBean.setPageNum(Integer.valueOf(TraslationWantBuyZhaohuanshouJpanel.this.dangqianyeshu));
                        searchGoodsBean.setOrder(TraslationWantBuyZhaohuanshouJpanel.this.showOrder);
                        String sendmes = Agreement.getAgreement().searchGoodsAgreement(GsonUtil.getGsonUtil().getgson().toJson(searchGoodsBean));
                        SendMessageUntil.toServer(sendmes);
                        TrslationMainJframe.getTrslationMainJframe().setPanelOpen(4);
                    }
                    else {
                        ZhuFrame.getZhuJpanel().addPrompt2("已经没有上一页了");
                    }
                }
            });
            (this.moye = new TrslationBtn("inkimg/hongmu/21_png.button.tab_nex.png", 1, UIUtils.COLOR_BTNTEXT, UIUtils.TEXT_FONT, "末页")).setBounds(342, 354, 30, 17);
            this.add(this.moye);
            this.moye.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    if (TraslationWantBuyZhaohuanshouJpanel.this.dangqianyeshu != TraslationWantBuyZhaohuanshouJpanel.this.zuidayema) {
                        TraslationWantBuyZhaohuanshouJpanel.this.dangqianyeshu = TraslationWantBuyZhaohuanshouJpanel.this.zuidayema;
                        TraslationWantBuyZhaohuanshouJpanel.this.yema.setText(TraslationWantBuyZhaohuanshouJpanel.this.dangqianyeshu + "/" + TraslationWantBuyZhaohuanshouJpanel.this.zuidayema);
                        SearchGoodsBean searchGoodsBean = new SearchGoodsBean();
                        searchGoodsBean.setSaletype(Integer.valueOf(4));
                        searchGoodsBean.setContiontype(TreasurePavilionSearchUntil.getGuardianForceType(TraslationWantBuyZhaohuanshouJpanel.this.chooseLeft.getText()));
                        searchGoodsBean.setPageNum(Integer.valueOf(TraslationWantBuyZhaohuanshouJpanel.this.dangqianyeshu));
                        searchGoodsBean.setOrder(TraslationWantBuyZhaohuanshouJpanel.this.showOrder);
                        String sendmes = Agreement.getAgreement().searchGoodsAgreement(GsonUtil.getGsonUtil().getgson().toJson(searchGoodsBean));
                        SendMessageUntil.toServer(sendmes);
                        TrslationMainJframe.getTrslationMainJframe().setPanelOpen(4);
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
                    if (TraslationWantBuyZhaohuanshouJpanel.this.dangqianyeshu < TraslationWantBuyZhaohuanshouJpanel.this.zuidayema) {
                        TraslationWantBuyZhaohuanshouJpanel.this.dangqianyeshu++;
                        TraslationWantBuyZhaohuanshouJpanel.this.yema.setText(TraslationWantBuyZhaohuanshouJpanel.this.dangqianyeshu + "/" + TraslationWantBuyZhaohuanshouJpanel.this.zuidayema);
                        SearchGoodsBean searchGoodsBean = new SearchGoodsBean();
                        searchGoodsBean.setSaletype(Integer.valueOf(4));
                        searchGoodsBean.setContiontype(TreasurePavilionSearchUntil.getGuardianForceType(TraslationWantBuyZhaohuanshouJpanel.this.chooseLeft.getText()));
                        searchGoodsBean.setPageNum(Integer.valueOf(TraslationWantBuyZhaohuanshouJpanel.this.dangqianyeshu));
                        searchGoodsBean.setOrder(TraslationWantBuyZhaohuanshouJpanel.this.showOrder);
                        String sendmes = Agreement.getAgreement().searchGoodsAgreement(GsonUtil.getGsonUtil().getgson().toJson(searchGoodsBean));
                        SendMessageUntil.toServer(sendmes);
                        TrslationMainJframe.getTrslationMainJframe().setPanelOpen(4);
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
                    TraslationWantBuyZhaohuanshouJpanel.this.upSort.setIcon(CutButtonImage.getImage("img/xy2uiimg/↑(红色)w6,h11px.png", 6, 11));
                    TraslationWantBuyZhaohuanshouJpanel.this.downSort.setIcon(CutButtonImage.getImage("img/xy2uiimg/↓(白色)w6,h11px.png", 6, 11));
                    TraslationWantBuyZhaohuanshouJpanel.this.dangqianyeshu = 1;
                    TraslationWantBuyZhaohuanshouJpanel.this.showOrder = 1;
                    SearchGoodsBean searchGoodsBean = new SearchGoodsBean();
                    searchGoodsBean.setSaletype(Integer.valueOf(4));
                    searchGoodsBean.setContiontype(TreasurePavilionSearchUntil.getGuardianForceType(TraslationWantBuyZhaohuanshouJpanel.this.chooseLeft.getText()));
                    searchGoodsBean.setPageNum(Integer.valueOf(TraslationWantBuyZhaohuanshouJpanel.this.dangqianyeshu));
                    searchGoodsBean.setOrder(TraslationWantBuyZhaohuanshouJpanel.this.showOrder);
                    String sendmes = Agreement.getAgreement().searchGoodsAgreement(GsonUtil.getGsonUtil().getgson().toJson(searchGoodsBean));
                    SendMessageUntil.toServer(sendmes);
                    TrslationMainJframe.getTrslationMainJframe().setPanelOpen(4);
                }
            });
            (this.downSort = new JLabel()).setBounds(343, 43, 6, 11);
            this.downSort.setBorder(null);
            this.downSort.setBackground(null);
            this.downSort.setOpaque(false);
            this.downSort.setIcon(CutButtonImage.getImage("img/xy2uiimg/↓(红色)w6,h11px.png", 6, 11));
            this.add(this.downSort);
            this.downSort.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    TraslationWantBuyZhaohuanshouJpanel.this.upSort.setIcon(CutButtonImage.getImage("img/xy2uiimg/↑(白色)w6,h11px.png", 6, 11));
                    TraslationWantBuyZhaohuanshouJpanel.this.downSort.setIcon(CutButtonImage.getImage("img/xy2uiimg/↓(红色)w6,h11px.png", 6, 11));
                    TraslationWantBuyZhaohuanshouJpanel.this.dangqianyeshu = 1;
                    TraslationWantBuyZhaohuanshouJpanel.this.showOrder = 2;
                    SearchGoodsBean searchGoodsBean = new SearchGoodsBean();
                    searchGoodsBean.setSaletype(Integer.valueOf(4));
                    searchGoodsBean.setContiontype(TreasurePavilionSearchUntil.getGuardianForceType(TraslationWantBuyZhaohuanshouJpanel.this.chooseLeft.getText()));
                    searchGoodsBean.setPageNum(Integer.valueOf(TraslationWantBuyZhaohuanshouJpanel.this.dangqianyeshu));
                    searchGoodsBean.setOrder(TraslationWantBuyZhaohuanshouJpanel.this.showOrder);
                    String sendmes = Agreement.getAgreement().searchGoodsAgreement(GsonUtil.getGsonUtil().getgson().toJson(searchGoodsBean));
                    SendMessageUntil.toServer(sendmes);
                    TrslationMainJframe.getTrslationMainJframe().setPanelOpen(4);
                }
            });
        }
    }
    
    public void setPage(int zuidayema) {
        this.zuidayema = zuidayema;
        this.yema.setText(this.dangqianyeshu + "/" + this.zuidayema);
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        if (MyIsif.getStyle().equals("水墨")) {
            super.paintComponent(g);
            if (this.icon1 == null) {
                this.icon1 = new ImageIcon("inkImg/background/32.png");
            }
            g.drawImage(this.icon1.getImage(), 0, 0, 590, 380, this);
        }
        else {
            super.paintComponent(g);
            if (this.icon1 == null) {
                this.icon1 = new ImageIcon("img/xy2uiimg/新我要买-召唤兽w590,h380px，top97,left22px.png");
            }
            g.drawImage(this.icon1.getImage(), 0, 0, 590, 380, this);
            if (this.icon2 == null) {
                this.icon2 = new ImageIcon("img/xy2uiimg/35_png.button.xy_vscroll$down_副本.png");
            }
            g.drawImage(this.icon2.getImage(), 120, 13, 19, 20, this);
        }
    }
    
    public static TraslationWantBuyZhaohuanshouJpanel getTraslationWantBuyZhaohuanshouJpanel() {
        return TraslationWantBuyZhaohuanshouJpanel.traslationWantBuyZhaohuanshouJpanel;
    }
    
    public static void setTraslationWantBuyZhaohuanshouJpanel(TraslationWantBuyZhaohuanshouJpanel traslationWantBuyZhaohuanshouJpanel) {
        TraslationWantBuyZhaohuanshouJpanel.traslationWantBuyZhaohuanshouJpanel = traslationWantBuyZhaohuanshouJpanel;
    }
    
    public JLabel getChooseLeft() {
        return this.chooseLeft;
    }
    
    public void setChooseLeft(JLabel chooseLeft) {
        this.chooseLeft = chooseLeft;
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
    
    public int getLeftFlag() {
        return this.leftFlag;
    }
    
    public void setLeftFlag(int leftFlag) {
        this.leftFlag = leftFlag;
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
    
    public ImageIcon getIcon1() {
        return this.icon1;
    }
    
    public void setIcon1(ImageIcon icon1) {
        this.icon1 = icon1;
    }
}
