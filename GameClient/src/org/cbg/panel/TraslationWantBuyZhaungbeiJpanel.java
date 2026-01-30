package org.cbg.panel;

import java.awt.Graphics;
import org.come.until.CutButtonImage;
import org.come.Frame.ZhuFrame;
import com.tool.tcpimg.UIUtils;
import org.cbg.until.TraslationTableUntil;
import org.cbg.frame.TrslationMainJframe;
import org.come.socket.SendMessageUntil;
import org.come.until.GsonUtil;
import org.come.socket.Agreement;
import org.cbg.until.TreasurePavilionSearchUntil;
import org.cbg.bean.SearchGoodsBean;
import java.awt.event.MouseEvent;
import java.awt.event.MouseAdapter;
import java.awt.Color;
import java.awt.Dimension;
import com.updateNew.MyIsif;
import javax.swing.ImageIcon;
import javax.swing.JScrollPane;
import org.cbg.btn.TrslationBtn;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;

public class TraslationWantBuyZhaungbeiJpanel extends JLayeredPane
{
    private static TraslationWantBuyZhaungbeiJpanel traslationWantBuyZhaungbeiJpanel;
    private JLabel chooseZbType;
    private JLabel yema;
    private TraslationSelectOptionJpanel chooseZbTypeModel;
    private TrslationBtn upPage;
    private TrslationBtn downPage;
    private TrslationBtn shouYe;
    private TrslationBtn moYe;
    private TrslationBtn chooseLeftArrows;
    private TrslationBtn refurbish;
    private JLabel sortUp;
    private JLabel sortDown;
    private int ZbTypeFlag;
    private int showOrder;
    private int dangqianyeshu;
    private int zuidayema;
    private JScrollPane jScrollPane;
    private ImageIcon icon1;
    
    public TraslationWantBuyZhaungbeiJpanel() {
        this.ZbTypeFlag = 1;
        this.showOrder = 0;
        this.dangqianyeshu = 1;
        this.zuidayema = 50;
        if (MyIsif.getStyle().equals("水墨")) {
            (TraslationWantBuyZhaungbeiJpanel.traslationWantBuyZhaungbeiJpanel = this).setPreferredSize(new Dimension(590, 380));
            this.setOpaque(false);
            this.setLayout(null);
            (this.chooseLeftArrows = new TrslationBtn("inkImg/button/8.png", 1)).setBounds(163, 13, 18, 18);
            this.chooseLeftArrows.setOpaque(false);
            this.add(this.chooseLeftArrows);
            this.chooseZbType = new JLabel("全部");
            (this.yema = new JLabel(this.dangqianyeshu + "/" + this.zuidayema)).setHorizontalAlignment(0);
            this.chooseZbType.setForeground(Color.white);
            this.yema.setForeground(Color.white);
            this.chooseZbType.setBounds(95, 10, 98, 20);
            this.yema.setBounds(258, 356, 58, 17);
            String[] zbTypeData = { "全部", "常规装备", "神兵", "仙器", "配饰", "护身符", "其他" };
            (this.chooseZbTypeModel = new TraslationSelectOptionJpanel(88, 120, "inkImg/background/16.png", zbTypeData)).setBounds(91, 30, 100, 120);
            this.chooseZbTypeModel.setVisible(false);
            this.chooseLeftArrows.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    if (TraslationWantBuyZhaungbeiJpanel.this.ZbTypeFlag == 1) {
                        TraslationWantBuyZhaungbeiJpanel.this.chooseZbTypeModel.setVisible(true);
                        TraslationWantBuyZhaungbeiJpanel.this.ZbTypeFlag = 0;
                    }
                    else {
                        TraslationWantBuyZhaungbeiJpanel.this.chooseZbTypeModel.setVisible(false);
                        TraslationWantBuyZhaungbeiJpanel.this.ZbTypeFlag = 1;
                    }
                }
            });
            this.chooseZbTypeModel.getJlist().addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    int count = TraslationWantBuyZhaungbeiJpanel.this.chooseZbTypeModel.getJlist().getSelectedIndex();
                    String getname = (String)TraslationWantBuyZhaungbeiJpanel.this.chooseZbTypeModel.getJlist().getSelectedValue();
                    TraslationWantBuyZhaungbeiJpanel.this.chooseZbType.setText(getname);
                    TraslationWantBuyZhaungbeiJpanel.this.chooseZbTypeModel.setVisible(false);
                    TraslationWantBuyZhaungbeiJpanel.this.ZbTypeFlag = 1;
                    TraslationWantBuyZhaungbeiJpanel.this.dangqianyeshu = 1;
                    SearchGoodsBean searchGoodsBean = new SearchGoodsBean();
                    searchGoodsBean.setSaletype(Integer.valueOf(5));
                    searchGoodsBean.setContiontype(TreasurePavilionSearchUntil.getEquipmentType(TraslationWantBuyZhaungbeiJpanel.this.chooseZbType.getText()));
                    searchGoodsBean.setPageNum(Integer.valueOf(TraslationWantBuyZhaungbeiJpanel.this.dangqianyeshu));
                    searchGoodsBean.setOrder(TraslationWantBuyZhaungbeiJpanel.this.showOrder);
                    String sendmes = Agreement.getAgreement().searchGoodsAgreement(GsonUtil.getGsonUtil().getgson().toJson(searchGoodsBean));
                    SendMessageUntil.toServer(sendmes);
                    TrslationMainJframe.getTrslationMainJframe().setPanelOpen(5);
                }
            });
            (this.jScrollPane = new JScrollPane()).setBounds(8, 60, 571, 289);
            TraslationTableUntil.TableModel(this.jScrollPane, 10);
            this.jScrollPane.getVerticalScrollBar().setUnitIncrement(50);
            (this.upPage = new TrslationBtn("inkImg/button/10.png", 1)).setBounds(235, 354, 19, 20);
            (this.downPage = new TrslationBtn("inkImg/button/9.png", 1)).setBounds(320, 354, 19, 20);
            (this.shouYe = new TrslationBtn("inkImg/button/2.png", 1, UIUtils.COLOR_BTNTEXT, UIUtils.TEXT_FONT, "首页")).setBounds(196, 354, 34, 17);
            (this.moYe = new TrslationBtn("inkImg/button/2.png", 1, UIUtils.COLOR_BTNTEXT, UIUtils.TEXT_FONT, "末页")).setBounds(342, 354, 34, 17);
            this.upPage.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    if (TraslationWantBuyZhaungbeiJpanel.this.dangqianyeshu > 1) {
                        TraslationWantBuyZhaungbeiJpanel.this.dangqianyeshu--;
                        TraslationWantBuyZhaungbeiJpanel.this.yema.setText(TraslationWantBuyZhaungbeiJpanel.this.dangqianyeshu + "/" + TraslationWantBuyZhaungbeiJpanel.this.zuidayema);
                        SearchGoodsBean searchGoodsBean = new SearchGoodsBean();
                        searchGoodsBean.setSaletype(Integer.valueOf(5));
                        searchGoodsBean.setContiontype(TreasurePavilionSearchUntil.getEquipmentType(TraslationWantBuyZhaungbeiJpanel.this.chooseZbType.getText()));
                        searchGoodsBean.setPageNum(Integer.valueOf(TraslationWantBuyZhaungbeiJpanel.this.dangqianyeshu));
                        searchGoodsBean.setOrder(TraslationWantBuyZhaungbeiJpanel.this.showOrder);
                        String sendmes = Agreement.getAgreement().searchGoodsAgreement(GsonUtil.getGsonUtil().getgson().toJson(searchGoodsBean));
                        SendMessageUntil.toServer(sendmes);
                        TrslationMainJframe.getTrslationMainJframe().setPanelOpen(5);
                    }
                    else {
                        ZhuFrame.getZhuJpanel().addPrompt2("已经没有上一页了");
                    }
                }
            });
            this.downPage.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    if (TraslationWantBuyZhaungbeiJpanel.this.dangqianyeshu < TraslationWantBuyZhaungbeiJpanel.this.zuidayema) {
                        TraslationWantBuyZhaungbeiJpanel.this.dangqianyeshu++;
                        TraslationWantBuyZhaungbeiJpanel.this.yema.setText(TraslationWantBuyZhaungbeiJpanel.this.dangqianyeshu + "/" + TraslationWantBuyZhaungbeiJpanel.this.zuidayema);
                        SearchGoodsBean searchGoodsBean = new SearchGoodsBean();
                        searchGoodsBean.setSaletype(Integer.valueOf(5));
                        searchGoodsBean.setContiontype(TreasurePavilionSearchUntil.getEquipmentType(TraslationWantBuyZhaungbeiJpanel.this.chooseZbType.getText()));
                        searchGoodsBean.setPageNum(Integer.valueOf(TraslationWantBuyZhaungbeiJpanel.this.dangqianyeshu));
                        searchGoodsBean.setOrder(TraslationWantBuyZhaungbeiJpanel.this.showOrder);
                        String sendmes = Agreement.getAgreement().searchGoodsAgreement(GsonUtil.getGsonUtil().getgson().toJson(searchGoodsBean));
                        SendMessageUntil.toServer(sendmes);
                        TrslationMainJframe.getTrslationMainJframe().setPanelOpen(5);
                    }
                    else {
                        ZhuFrame.getZhuJpanel().addPrompt2("已经没有下一页了");
                    }
                }
            });
            this.shouYe.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    if (TraslationWantBuyZhaungbeiJpanel.this.dangqianyeshu != 1) {
                        TraslationWantBuyZhaungbeiJpanel.this.dangqianyeshu = 1;
                        TraslationWantBuyZhaungbeiJpanel.this.yema.setText(TraslationWantBuyZhaungbeiJpanel.this.dangqianyeshu + "/" + TraslationWantBuyZhaungbeiJpanel.this.zuidayema);
                        SearchGoodsBean searchGoodsBean = new SearchGoodsBean();
                        searchGoodsBean.setSaletype(Integer.valueOf(5));
                        searchGoodsBean.setContiontype(TreasurePavilionSearchUntil.getEquipmentType(TraslationWantBuyZhaungbeiJpanel.this.chooseZbType.getText()));
                        searchGoodsBean.setPageNum(Integer.valueOf(TraslationWantBuyZhaungbeiJpanel.this.dangqianyeshu));
                        searchGoodsBean.setOrder(TraslationWantBuyZhaungbeiJpanel.this.showOrder);
                        String sendmes = Agreement.getAgreement().searchGoodsAgreement(GsonUtil.getGsonUtil().getgson().toJson(searchGoodsBean));
                        SendMessageUntil.toServer(sendmes);
                        TrslationMainJframe.getTrslationMainJframe().setPanelOpen(5);
                    }
                    else {
                        ZhuFrame.getZhuJpanel().addPrompt2("已在首页");
                    }
                }
            });
            this.moYe.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    if (TraslationWantBuyZhaungbeiJpanel.this.dangqianyeshu != TraslationWantBuyZhaungbeiJpanel.this.zuidayema) {
                        TraslationWantBuyZhaungbeiJpanel.this.dangqianyeshu = TraslationWantBuyZhaungbeiJpanel.this.zuidayema;
                        TraslationWantBuyZhaungbeiJpanel.this.yema.setText(TraslationWantBuyZhaungbeiJpanel.this.dangqianyeshu + "/" + TraslationWantBuyZhaungbeiJpanel.this.zuidayema);
                        SearchGoodsBean searchGoodsBean = new SearchGoodsBean();
                        searchGoodsBean.setSaletype(Integer.valueOf(5));
                        searchGoodsBean.setContiontype(TreasurePavilionSearchUntil.getEquipmentType(TraslationWantBuyZhaungbeiJpanel.this.chooseZbType.getText()));
                        searchGoodsBean.setPageNum(Integer.valueOf(TraslationWantBuyZhaungbeiJpanel.this.dangqianyeshu));
                        searchGoodsBean.setOrder(TraslationWantBuyZhaungbeiJpanel.this.showOrder);
                        String sendmes = Agreement.getAgreement().searchGoodsAgreement(GsonUtil.getGsonUtil().getgson().toJson(searchGoodsBean));
                        SendMessageUntil.toServer(sendmes);
                        TrslationMainJframe.getTrslationMainJframe().setPanelOpen(5);
                    }
                    else {
                        ZhuFrame.getZhuJpanel().addPrompt2("已在末页");
                    }
                }
            });
            (this.sortUp = new JLabel()).setBounds(340, 44, 6, 11);
            this.sortUp.setIcon(CutButtonImage.getImage("img/xy2uiimg/↑(白色)w6,h11px.png", 6, 11));
            this.sortUp.setBorder(null);
            this.sortUp.setOpaque(false);
            (this.sortDown = new JLabel()).setBounds(350, 44, 6, 11);
            this.sortDown.setIcon(CutButtonImage.getImage("img/xy2uiimg/↓(红色)w6,h11px.png", 6, 11));
            this.sortDown.setBorder(null);
            this.sortDown.setOpaque(false);
            this.sortUp.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    TraslationWantBuyZhaungbeiJpanel.this.sortUp.setIcon(CutButtonImage.getImage("img/xy2uiimg/↑(红色)w6,h11px.png", 6, 11));
                    TraslationWantBuyZhaungbeiJpanel.this.sortDown.setIcon(CutButtonImage.getImage("img/xy2uiimg/↓(白色)w6,h11px.png", 6, 11));
                    TraslationWantBuyZhaungbeiJpanel.this.dangqianyeshu = 1;
                    TraslationWantBuyZhaungbeiJpanel.this.showOrder = 1;
                    SearchGoodsBean searchGoodsBean = new SearchGoodsBean();
                    searchGoodsBean.setSaletype(Integer.valueOf(5));
                    searchGoodsBean.setContiontype(TreasurePavilionSearchUntil.getEquipmentType(TraslationWantBuyZhaungbeiJpanel.this.chooseZbType.getText()));
                    searchGoodsBean.setPageNum(Integer.valueOf(TraslationWantBuyZhaungbeiJpanel.this.dangqianyeshu));
                    searchGoodsBean.setOrder(TraslationWantBuyZhaungbeiJpanel.this.showOrder);
                    String sendmes = Agreement.getAgreement().searchGoodsAgreement(GsonUtil.getGsonUtil().getgson().toJson(searchGoodsBean));
                    SendMessageUntil.toServer(sendmes);
                    TrslationMainJframe.getTrslationMainJframe().setPanelOpen(5);
                }
            });
            this.sortDown.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    TraslationWantBuyZhaungbeiJpanel.this.sortUp.setIcon(CutButtonImage.getImage("img/xy2uiimg/↑(白色)w6,h11px.png", 6, 11));
                    TraslationWantBuyZhaungbeiJpanel.this.sortDown.setIcon(CutButtonImage.getImage("img/xy2uiimg/↓(红色)w6,h11px.png", 6, 11));
                    TraslationWantBuyZhaungbeiJpanel.this.dangqianyeshu = 1;
                    TraslationWantBuyZhaungbeiJpanel.this.showOrder = 2;
                    SearchGoodsBean searchGoodsBean = new SearchGoodsBean();
                    searchGoodsBean.setSaletype(Integer.valueOf(5));
                    searchGoodsBean.setContiontype(TreasurePavilionSearchUntil.getEquipmentType(TraslationWantBuyZhaungbeiJpanel.this.chooseZbType.getText()));
                    searchGoodsBean.setPageNum(Integer.valueOf(TraslationWantBuyZhaungbeiJpanel.this.dangqianyeshu));
                    searchGoodsBean.setOrder(TraslationWantBuyZhaungbeiJpanel.this.showOrder);
                    String sendmes = Agreement.getAgreement().searchGoodsAgreement(GsonUtil.getGsonUtil().getgson().toJson(searchGoodsBean));
                    SendMessageUntil.toServer(sendmes);
                    TrslationMainJframe.getTrslationMainJframe().setPanelOpen(5);
                }
            });
            this.add(this.chooseZbType);
            this.add(this.yema);
            this.add(this.chooseZbTypeModel, Integer.valueOf(10), 1);
            this.add(this.jScrollPane, Integer.valueOf(10), -1);
            this.add(this.upPage);
            this.add(this.downPage);
            this.add(this.shouYe);
            this.add(this.moYe);
            this.add(this.sortUp);
            this.add(this.sortDown);
        }
        else {
            (TraslationWantBuyZhaungbeiJpanel.traslationWantBuyZhaungbeiJpanel = this).setPreferredSize(new Dimension(590, 380));
            this.setOpaque(false);
            this.setLayout(null);
            (this.chooseLeftArrows = new TrslationBtn("img/xy2uiimg/35_png.button.xy_vscroll$down.png", 1)).setBounds(163, 13, 18, 18);
            this.chooseLeftArrows.setOpaque(false);
            this.add(this.chooseLeftArrows);
            this.chooseZbType = new JLabel("全部");
            (this.yema = new JLabel(this.dangqianyeshu + "/" + this.zuidayema)).setHorizontalAlignment(0);
            this.chooseZbType.setForeground(Color.white);
            this.yema.setForeground(Color.white);
            this.chooseZbType.setBounds(95, 10, 98, 20);
            this.yema.setBounds(258, 356, 58, 17);
            String[] zbTypeData = { "全部", "常规装备", "神兵", "仙器", "配饰", "护身符", "其他" };
            (this.chooseZbTypeModel = new TraslationSelectOptionJpanel(88, 120, "img/xy2uiimg/下拉框(1)w108,h120px.png", zbTypeData)).setBounds(91, 30, 100, 120);
            this.chooseZbTypeModel.setVisible(false);
            this.chooseLeftArrows.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    if (TraslationWantBuyZhaungbeiJpanel.this.ZbTypeFlag == 1) {
                        TraslationWantBuyZhaungbeiJpanel.this.chooseZbTypeModel.setVisible(true);
                        TraslationWantBuyZhaungbeiJpanel.this.ZbTypeFlag = 0;
                    }
                    else {
                        TraslationWantBuyZhaungbeiJpanel.this.chooseZbTypeModel.setVisible(false);
                        TraslationWantBuyZhaungbeiJpanel.this.ZbTypeFlag = 1;
                    }
                }
            });
            this.chooseZbTypeModel.getJlist().addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    int count = TraslationWantBuyZhaungbeiJpanel.this.chooseZbTypeModel.getJlist().getSelectedIndex();
                    String getname = (String)TraslationWantBuyZhaungbeiJpanel.this.chooseZbTypeModel.getJlist().getSelectedValue();
                    TraslationWantBuyZhaungbeiJpanel.this.chooseZbType.setText(getname);
                    TraslationWantBuyZhaungbeiJpanel.this.chooseZbTypeModel.setVisible(false);
                    TraslationWantBuyZhaungbeiJpanel.this.ZbTypeFlag = 1;
                    TraslationWantBuyZhaungbeiJpanel.this.dangqianyeshu = 1;
                    SearchGoodsBean searchGoodsBean = new SearchGoodsBean();
                    searchGoodsBean.setSaletype(Integer.valueOf(5));
                    searchGoodsBean.setContiontype(TreasurePavilionSearchUntil.getEquipmentType(TraslationWantBuyZhaungbeiJpanel.this.chooseZbType.getText()));
                    searchGoodsBean.setPageNum(Integer.valueOf(TraslationWantBuyZhaungbeiJpanel.this.dangqianyeshu));
                    searchGoodsBean.setOrder(TraslationWantBuyZhaungbeiJpanel.this.showOrder);
                    String sendmes = Agreement.getAgreement().searchGoodsAgreement(GsonUtil.getGsonUtil().getgson().toJson(searchGoodsBean));
                    SendMessageUntil.toServer(sendmes);
                    TrslationMainJframe.getTrslationMainJframe().setPanelOpen(5);
                }
            });
            (this.jScrollPane = new JScrollPane()).setBounds(8, 60, 571, 289);
            TraslationTableUntil.TableModel(this.jScrollPane, 10);
            this.jScrollPane.getVerticalScrollBar().setUnitIncrement(50);
            (this.upPage = new TrslationBtn("img/xy2uiimg/30_png.button.btn_8.png", 1)).setBounds(235, 354, 19, 20);
            (this.downPage = new TrslationBtn("img/xy2uiimg/36_png.button.btn_7.png", 1)).setBounds(320, 354, 19, 20);
            (this.shouYe = new TrslationBtn("inkImg/hongmu/21_png.button.tab_nex.png", 1, UIUtils.COLOR_BTNTEXT, UIUtils.TEXT_FONT, "首页")).setBounds(196, 354, 34, 17);
            (this.moYe = new TrslationBtn("inkImg/hongmu/21_png.button.tab_nex.png", 1, UIUtils.COLOR_BTNTEXT, UIUtils.TEXT_FONT, "末页")).setBounds(342, 354, 34, 17);
            this.upPage.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    if (TraslationWantBuyZhaungbeiJpanel.this.dangqianyeshu > 1) {
                        TraslationWantBuyZhaungbeiJpanel.this.dangqianyeshu--;
                        TraslationWantBuyZhaungbeiJpanel.this.yema.setText(TraslationWantBuyZhaungbeiJpanel.this.dangqianyeshu + "/" + TraslationWantBuyZhaungbeiJpanel.this.zuidayema);
                        SearchGoodsBean searchGoodsBean = new SearchGoodsBean();
                        searchGoodsBean.setSaletype(Integer.valueOf(5));
                        searchGoodsBean.setContiontype(TreasurePavilionSearchUntil.getEquipmentType(TraslationWantBuyZhaungbeiJpanel.this.chooseZbType.getText()));
                        searchGoodsBean.setPageNum(Integer.valueOf(TraslationWantBuyZhaungbeiJpanel.this.dangqianyeshu));
                        searchGoodsBean.setOrder(TraslationWantBuyZhaungbeiJpanel.this.showOrder);
                        String sendmes = Agreement.getAgreement().searchGoodsAgreement(GsonUtil.getGsonUtil().getgson().toJson(searchGoodsBean));
                        SendMessageUntil.toServer(sendmes);
                        TrslationMainJframe.getTrslationMainJframe().setPanelOpen(5);
                    }
                    else {
                        ZhuFrame.getZhuJpanel().addPrompt2("已经没有上一页了");
                    }
                }
            });
            this.downPage.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    if (TraslationWantBuyZhaungbeiJpanel.this.dangqianyeshu < TraslationWantBuyZhaungbeiJpanel.this.zuidayema) {
                        TraslationWantBuyZhaungbeiJpanel.this.dangqianyeshu++;
                        TraslationWantBuyZhaungbeiJpanel.this.yema.setText(TraslationWantBuyZhaungbeiJpanel.this.dangqianyeshu + "/" + TraslationWantBuyZhaungbeiJpanel.this.zuidayema);
                        SearchGoodsBean searchGoodsBean = new SearchGoodsBean();
                        searchGoodsBean.setSaletype(Integer.valueOf(5));
                        searchGoodsBean.setContiontype(TreasurePavilionSearchUntil.getEquipmentType(TraslationWantBuyZhaungbeiJpanel.this.chooseZbType.getText()));
                        searchGoodsBean.setPageNum(Integer.valueOf(TraslationWantBuyZhaungbeiJpanel.this.dangqianyeshu));
                        searchGoodsBean.setOrder(TraslationWantBuyZhaungbeiJpanel.this.showOrder);
                        String sendmes = Agreement.getAgreement().searchGoodsAgreement(GsonUtil.getGsonUtil().getgson().toJson(searchGoodsBean));
                        SendMessageUntil.toServer(sendmes);
                        TrslationMainJframe.getTrslationMainJframe().setPanelOpen(5);
                    }
                    else {
                        ZhuFrame.getZhuJpanel().addPrompt2("已经没有下一页了");
                    }
                }
            });
            this.shouYe.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    if (TraslationWantBuyZhaungbeiJpanel.this.dangqianyeshu != 1) {
                        TraslationWantBuyZhaungbeiJpanel.this.dangqianyeshu = 1;
                        TraslationWantBuyZhaungbeiJpanel.this.yema.setText(TraslationWantBuyZhaungbeiJpanel.this.dangqianyeshu + "/" + TraslationWantBuyZhaungbeiJpanel.this.zuidayema);
                        SearchGoodsBean searchGoodsBean = new SearchGoodsBean();
                        searchGoodsBean.setSaletype(Integer.valueOf(5));
                        searchGoodsBean.setContiontype(TreasurePavilionSearchUntil.getEquipmentType(TraslationWantBuyZhaungbeiJpanel.this.chooseZbType.getText()));
                        searchGoodsBean.setPageNum(Integer.valueOf(TraslationWantBuyZhaungbeiJpanel.this.dangqianyeshu));
                        searchGoodsBean.setOrder(TraslationWantBuyZhaungbeiJpanel.this.showOrder);
                        String sendmes = Agreement.getAgreement().searchGoodsAgreement(GsonUtil.getGsonUtil().getgson().toJson(searchGoodsBean));
                        SendMessageUntil.toServer(sendmes);
                        TrslationMainJframe.getTrslationMainJframe().setPanelOpen(5);
                    }
                    else {
                        ZhuFrame.getZhuJpanel().addPrompt2("已在首页");
                    }
                }
            });
            this.moYe.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    if (TraslationWantBuyZhaungbeiJpanel.this.dangqianyeshu != TraslationWantBuyZhaungbeiJpanel.this.zuidayema) {
                        TraslationWantBuyZhaungbeiJpanel.this.dangqianyeshu = TraslationWantBuyZhaungbeiJpanel.this.zuidayema;
                        TraslationWantBuyZhaungbeiJpanel.this.yema.setText(TraslationWantBuyZhaungbeiJpanel.this.dangqianyeshu + "/" + TraslationWantBuyZhaungbeiJpanel.this.zuidayema);
                        SearchGoodsBean searchGoodsBean = new SearchGoodsBean();
                        searchGoodsBean.setSaletype(Integer.valueOf(5));
                        searchGoodsBean.setContiontype(TreasurePavilionSearchUntil.getEquipmentType(TraslationWantBuyZhaungbeiJpanel.this.chooseZbType.getText()));
                        searchGoodsBean.setPageNum(Integer.valueOf(TraslationWantBuyZhaungbeiJpanel.this.dangqianyeshu));
                        searchGoodsBean.setOrder(TraslationWantBuyZhaungbeiJpanel.this.showOrder);
                        String sendmes = Agreement.getAgreement().searchGoodsAgreement(GsonUtil.getGsonUtil().getgson().toJson(searchGoodsBean));
                        SendMessageUntil.toServer(sendmes);
                        TrslationMainJframe.getTrslationMainJframe().setPanelOpen(5);
                    }
                    else {
                        ZhuFrame.getZhuJpanel().addPrompt2("已在末页");
                    }
                }
            });
            (this.sortUp = new JLabel()).setBounds(340, 44, 6, 11);
            this.sortUp.setIcon(CutButtonImage.getImage("img/xy2uiimg/↑(白色)w6,h11px.png", 6, 11));
            this.sortUp.setBorder(null);
            this.sortUp.setOpaque(false);
            (this.sortDown = new JLabel()).setBounds(350, 44, 6, 11);
            this.sortDown.setIcon(CutButtonImage.getImage("img/xy2uiimg/↓(红色)w6,h11px.png", 6, 11));
            this.sortDown.setBorder(null);
            this.sortDown.setOpaque(false);
            this.sortUp.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    TraslationWantBuyZhaungbeiJpanel.this.sortUp.setIcon(CutButtonImage.getImage("img/xy2uiimg/↑(红色)w6,h11px.png", 6, 11));
                    TraslationWantBuyZhaungbeiJpanel.this.sortDown.setIcon(CutButtonImage.getImage("img/xy2uiimg/↓(白色)w6,h11px.png", 6, 11));
                    TraslationWantBuyZhaungbeiJpanel.this.dangqianyeshu = 1;
                    TraslationWantBuyZhaungbeiJpanel.this.showOrder = 1;
                    SearchGoodsBean searchGoodsBean = new SearchGoodsBean();
                    searchGoodsBean.setSaletype(Integer.valueOf(5));
                    searchGoodsBean.setContiontype(TreasurePavilionSearchUntil.getEquipmentType(TraslationWantBuyZhaungbeiJpanel.this.chooseZbType.getText()));
                    searchGoodsBean.setPageNum(Integer.valueOf(TraslationWantBuyZhaungbeiJpanel.this.dangqianyeshu));
                    searchGoodsBean.setOrder(TraslationWantBuyZhaungbeiJpanel.this.showOrder);
                    String sendmes = Agreement.getAgreement().searchGoodsAgreement(GsonUtil.getGsonUtil().getgson().toJson(searchGoodsBean));
                    SendMessageUntil.toServer(sendmes);
                    TrslationMainJframe.getTrslationMainJframe().setPanelOpen(5);
                }
            });
            this.sortDown.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    TraslationWantBuyZhaungbeiJpanel.this.sortUp.setIcon(CutButtonImage.getImage("img/xy2uiimg/↑(白色)w6,h11px.png", 6, 11));
                    TraslationWantBuyZhaungbeiJpanel.this.sortDown.setIcon(CutButtonImage.getImage("img/xy2uiimg/↓(红色)w6,h11px.png", 6, 11));
                    TraslationWantBuyZhaungbeiJpanel.this.dangqianyeshu = 1;
                    TraslationWantBuyZhaungbeiJpanel.this.showOrder = 2;
                    SearchGoodsBean searchGoodsBean = new SearchGoodsBean();
                    searchGoodsBean.setSaletype(Integer.valueOf(5));
                    searchGoodsBean.setContiontype(TreasurePavilionSearchUntil.getEquipmentType(TraslationWantBuyZhaungbeiJpanel.this.chooseZbType.getText()));
                    searchGoodsBean.setPageNum(Integer.valueOf(TraslationWantBuyZhaungbeiJpanel.this.dangqianyeshu));
                    searchGoodsBean.setOrder(TraslationWantBuyZhaungbeiJpanel.this.showOrder);
                    String sendmes = Agreement.getAgreement().searchGoodsAgreement(GsonUtil.getGsonUtil().getgson().toJson(searchGoodsBean));
                    SendMessageUntil.toServer(sendmes);
                    TrslationMainJframe.getTrslationMainJframe().setPanelOpen(5);
                }
            });
            this.add(this.chooseZbType);
            this.add(this.yema);
            this.add(this.chooseZbTypeModel, Integer.valueOf(10), 1);
            this.add(this.jScrollPane, Integer.valueOf(10), -1);
            this.add(this.upPage);
            this.add(this.downPage);
            this.add(this.shouYe);
            this.add(this.moYe);
            this.add(this.sortUp);
            this.add(this.sortDown);
        }
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        if (MyIsif.getStyle().equals("水墨")) {
            super.paintComponent(g);
            if (this.icon1 == null) {
                this.icon1 = new ImageIcon("inkImg/background/33.png");
            }
            g.drawImage(this.icon1.getImage(), 0, 0, 590, 380, this);
        }
        else {
            super.paintComponent(g);
            if (this.icon1 == null) {
                this.icon1 = new ImageIcon("img/xy2uiimg/新我要买-装备w590,h380px，top97,left22px.png");
            }
            g.drawImage(this.icon1.getImage(), 0, 0, 590, 380, this);
        }
    }
    
    public void setPage(int zuidayema) {
        this.zuidayema = zuidayema;
        this.yema.setText(this.dangqianyeshu + "/" + this.zuidayema);
    }
    
    public static TraslationWantBuyZhaungbeiJpanel getTraslationWantBuyZhaungbeiJpanel() {
        return TraslationWantBuyZhaungbeiJpanel.traslationWantBuyZhaungbeiJpanel;
    }
    
    public static void setTraslationWantBuyZhaungbeiJpanel(TraslationWantBuyZhaungbeiJpanel traslationWantBuyZhaungbeiJpanel) {
        TraslationWantBuyZhaungbeiJpanel.traslationWantBuyZhaungbeiJpanel = traslationWantBuyZhaungbeiJpanel;
    }
    
    public JLabel getChooseZbType() {
        return this.chooseZbType;
    }
    
    public void setChooseZbType(JLabel chooseZbType) {
        this.chooseZbType = chooseZbType;
    }
    
    public JLabel getPage() {
        return this.yema;
    }
    
    public void setPage(JLabel page) {
        this.yema = page;
    }
    
    public TraslationSelectOptionJpanel getChooseZbTypeModel() {
        return this.chooseZbTypeModel;
    }
    
    public void setChooseZbTypeModel(TraslationSelectOptionJpanel chooseZbTypeModel) {
        this.chooseZbTypeModel = chooseZbTypeModel;
    }
    
    public TrslationBtn getUpPage() {
        return this.upPage;
    }
    
    public void setUpPage(TrslationBtn upPage) {
        this.upPage = upPage;
    }
    
    public TrslationBtn getDownPage() {
        return this.downPage;
    }
    
    public void setDownPage(TrslationBtn downPage) {
        this.downPage = downPage;
    }
    
    public TrslationBtn getShouYe() {
        return this.shouYe;
    }
    
    public void setShouYe(TrslationBtn shouYe) {
        this.shouYe = shouYe;
    }
    
    public TrslationBtn getMoYe() {
        return this.moYe;
    }
    
    public void setMoYe(TrslationBtn moYe) {
        this.moYe = moYe;
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
    
    public JLabel getSortUp() {
        return this.sortUp;
    }
    
    public void setSortUp(JLabel sortUp) {
        this.sortUp = sortUp;
    }
    
    public JLabel getSortDown() {
        return this.sortDown;
    }
    
    public void setSortDown(JLabel sortDown) {
        this.sortDown = sortDown;
    }
    
    public int getZbTypeFlag() {
        return this.ZbTypeFlag;
    }
    
    public void setZbTypeFlag(int zbTypeFlag) {
        this.ZbTypeFlag = zbTypeFlag;
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
    
    public JLabel getYema() {
        return this.yema;
    }
    
    public void setYema(JLabel yema) {
        this.yema = yema;
    }
    
    public int getShowOrder() {
        return this.showOrder;
    }
    
    public void setShowOrder(int showOrder) {
        this.showOrder = showOrder;
    }
    
    public TrslationBtn getRefurbish() {
        return this.refurbish;
    }
    
    public void setRefurbish(TrslationBtn refurbish) {
        this.refurbish = refurbish;
    }
}
