package org.cbg.panel;

import java.awt.Graphics;
import org.come.Frame.ZhuFrame;
import com.tool.tcpimg.UIUtils;
import java.math.BigDecimal;
import org.come.socket.SendMessageUntil;
import org.cbg.frame.TrslationMainJframe;
import org.come.until.GsonUtil;
import org.come.socket.Agreement;
import org.cbg.bean.SearchOrderBean;
import com.tool.role.RoleData;
import java.awt.event.MouseEvent;
import java.awt.event.MouseAdapter;
import java.awt.Color;
import org.cbg.until.TraslationTableMyorderUntil;
import com.updateNew.MyIsif;
import javax.swing.ImageIcon;
import javax.swing.JScrollPane;
import org.cbg.btn.TrslationBtn;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;

public class TraslationMyMainMyorderJpanel extends JLayeredPane
{
    private JLabel chooseLeft;
    private JLabel yema;
    private TraslationSelectOptionJpanel chooseLeftModel;
    private int leftFlag;
    private TrslationBtn shouye;
    private TrslationBtn moye;
    private TrslationBtn leftArrows;
    private TrslationBtn rightArrows;
    private TrslationBtn chooseLeftArrows;
    private JScrollPane jScrollPane;
    private int dangqianyeshu;
    private int zuidayema;
    private Integer leftCont;
    private ImageIcon icon1;
    
    public TraslationMyMainMyorderJpanel() {
        this.leftFlag = 1;
        this.dangqianyeshu = 1;
        this.zuidayema = 50;
        this.leftCont = Integer.valueOf(1);
        if (MyIsif.getStyle().equals("水墨")) {
            this.setBounds(0, 27, 590, 380);
            this.setOpaque(false);
            this.setLayout(null);
            this.jScrollPane = new JScrollPane();
            this.chooseLeft = new JLabel("近一周");
            this.jScrollPane.setOpaque(false);
            this.jScrollPane.setBounds(12, 46, 571, 306);
            this.chooseLeft.setBounds(56, 4, 98, 20);
            this.jScrollPane.setBorder(null);
            TraslationTableMyorderUntil.TableModel(this.jScrollPane, 10);
            this.jScrollPane.getVerticalScrollBar().setUnitIncrement(50);
            this.chooseLeft.setForeground(Color.white);
            String[] rowDataRight = { "近一周", "近一个月", "一个月前" };
            (this.chooseLeftModel = new TraslationSelectOptionJpanel(98, 120, "inkImg/background/15.png", rowDataRight)).setBounds(52, 24, 98, 120);
            this.chooseLeftModel.setVisible(false);
            this.add(this.getChooseLeftArrows());
            this.getChooseLeftArrows().addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    if (TraslationMyMainMyorderJpanel.this.leftFlag == 1) {
                        TraslationMyMainMyorderJpanel.this.chooseLeftModel.setVisible(true);
                        TraslationMyMainMyorderJpanel.this.leftFlag = 0;
                    }
                    else {
                        TraslationMyMainMyorderJpanel.this.chooseLeftModel.setVisible(false);
                        TraslationMyMainMyorderJpanel.this.leftFlag = 1;
                    }
                }
            });
            this.chooseLeftModel.getJlist().addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    int count = TraslationMyMainMyorderJpanel.this.chooseLeftModel.getJlist().getSelectedIndex();
                    String getname = (String)TraslationMyMainMyorderJpanel.this.chooseLeftModel.getJlist().getSelectedValue();
                    TraslationMyMainMyorderJpanel.this.chooseLeft.setText(getname);
                    TraslationMyMainMyorderJpanel.this.chooseLeftModel.setVisible(false);
                    TraslationMyMainMyorderJpanel.this.leftFlag = 1;
                    TraslationMyMainMyorderJpanel.this.dangqianyeshu = 1;
                    TraslationMyMainMyorderJpanel.this.leftCont = Integer.valueOf(count + 1);
                    BigDecimal roleId = RoleData.getRoleData().getLoginResult().getRole_id();
                    SearchOrderBean searchOrderBean = new SearchOrderBean();
                    searchOrderBean.setTime(TraslationMyMainMyorderJpanel.this.leftCont);
                    searchOrderBean.setPageNum(Integer.valueOf(TraslationMyMainMyorderJpanel.this.dangqianyeshu));
                    String sendmes = Agreement.getAgreement().searchMyOrderAgreement(GsonUtil.getGsonUtil().getgson().toJson(searchOrderBean));
                    TrslationMainJframe.getTrslationMainJframe().setPanelOpen(11);
                    SendMessageUntil.toServer(sendmes);
                }
            });
            this.add(this.chooseLeftModel, Integer.valueOf(10), 1);
            this.add(this.chooseLeft);
            (this.yema = new JLabel(this.dangqianyeshu + "/" + this.zuidayema)).setBounds(246, 356, 58, 17);
            this.yema.setForeground(Color.white);
            this.yema.setOpaque(false);
            this.yema.setHorizontalAlignment(0);
            this.add(this.yema);
            (this.shouye = new TrslationBtn("inkImg/button/2.png", 1, UIUtils.COLOR_BTNTEXT, UIUtils.TEXT_FONT, "首页")).setBounds(188, 354, 34, 17);
            this.add(this.shouye);
            this.shouye.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    if (TraslationMyMainMyorderJpanel.this.dangqianyeshu != 1) {
                        TraslationMyMainMyorderJpanel.this.dangqianyeshu = 1;
                        TraslationMyMainMyorderJpanel.this.yema.setText(TraslationMyMainMyorderJpanel.this.dangqianyeshu + "/" + TraslationMyMainMyorderJpanel.this.zuidayema);
                        SearchOrderBean searchOrderBean = new SearchOrderBean();
                        searchOrderBean.setTime(TraslationMyMainMyorderJpanel.this.leftCont);
                        searchOrderBean.setPageNum(Integer.valueOf(TraslationMyMainMyorderJpanel.this.dangqianyeshu));
                        String sendmes = Agreement.getAgreement().searchMyOrderAgreement(GsonUtil.getGsonUtil().getgson().toJson(searchOrderBean));
                        TrslationMainJframe.getTrslationMainJframe().setPanelOpen(11);
                        SendMessageUntil.toServer(sendmes);
                    }
                    else {
                        ZhuFrame.getZhuJpanel().addPrompt2("已在首页");
                    }
                }
            });
            (this.leftArrows = new TrslationBtn("inkImg/button/10.png", 1)).setBounds(223, 354, 19, 20);
            this.add(this.leftArrows);
            this.leftArrows.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    if (TraslationMyMainMyorderJpanel.this.dangqianyeshu > 1) {
                        TraslationMyMainMyorderJpanel.this.dangqianyeshu--;
                        TraslationMyMainMyorderJpanel.this.yema.setText(TraslationMyMainMyorderJpanel.this.dangqianyeshu + "/" + TraslationMyMainMyorderJpanel.this.zuidayema);
                        SearchOrderBean searchOrderBean = new SearchOrderBean();
                        searchOrderBean.setTime(TraslationMyMainMyorderJpanel.this.leftCont);
                        searchOrderBean.setPageNum(Integer.valueOf(TraslationMyMainMyorderJpanel.this.dangqianyeshu));
                        String sendmes = Agreement.getAgreement().searchMyOrderAgreement(GsonUtil.getGsonUtil().getgson().toJson(searchOrderBean));
                        TrslationMainJframe.getTrslationMainJframe().setPanelOpen(11);
                        SendMessageUntil.toServer(sendmes);
                    }
                    else {
                        ZhuFrame.getZhuJpanel().addPrompt2("已经没有上一页了");
                    }
                }
            });
            (this.moye = new TrslationBtn("inkImg/button/2.png", 1, UIUtils.COLOR_BTNTEXT, UIUtils.TEXT_FONT, "末页")).setBounds(319, 354, 34, 17);
            this.add(this.moye);
            this.moye.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    if (TraslationMyMainMyorderJpanel.this.dangqianyeshu != TraslationMyMainMyorderJpanel.this.zuidayema) {
                        TraslationMyMainMyorderJpanel.this.dangqianyeshu = TraslationMyMainMyorderJpanel.this.zuidayema;
                        TraslationMyMainMyorderJpanel.this.yema.setText(TraslationMyMainMyorderJpanel.this.dangqianyeshu + "/" + TraslationMyMainMyorderJpanel.this.zuidayema);
                        SearchOrderBean searchOrderBean = new SearchOrderBean();
                        searchOrderBean.setTime(TraslationMyMainMyorderJpanel.this.leftCont);
                        searchOrderBean.setPageNum(Integer.valueOf(TraslationMyMainMyorderJpanel.this.dangqianyeshu));
                        String sendmes = Agreement.getAgreement().searchMyOrderAgreement(GsonUtil.getGsonUtil().getgson().toJson(searchOrderBean));
                        TrslationMainJframe.getTrslationMainJframe().setPanelOpen(11);
                        SendMessageUntil.toServer(sendmes);
                    }
                    else {
                        ZhuFrame.getZhuJpanel().addPrompt2("已在末页");
                    }
                }
            });
            (this.rightArrows = new TrslationBtn("inkImg/button/9.png", 1)).setBounds(299, 354, 19, 20);
            this.add(this.rightArrows);
            this.rightArrows.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    if (TraslationMyMainMyorderJpanel.this.dangqianyeshu < TraslationMyMainMyorderJpanel.this.zuidayema) {
                        TraslationMyMainMyorderJpanel.this.dangqianyeshu++;
                        TraslationMyMainMyorderJpanel.this.yema.setText(TraslationMyMainMyorderJpanel.this.dangqianyeshu + "/" + TraslationMyMainMyorderJpanel.this.zuidayema);
                        SearchOrderBean searchOrderBean = new SearchOrderBean();
                        searchOrderBean.setTime(TraslationMyMainMyorderJpanel.this.leftCont);
                        searchOrderBean.setPageNum(Integer.valueOf(TraslationMyMainMyorderJpanel.this.dangqianyeshu));
                        String sendmes = Agreement.getAgreement().searchMyOrderAgreement(GsonUtil.getGsonUtil().getgson().toJson(searchOrderBean));
                        TrslationMainJframe.getTrslationMainJframe().setPanelOpen(11);
                        SendMessageUntil.toServer(sendmes);
                    }
                    else {
                        ZhuFrame.getZhuJpanel().addPrompt2("已经没有下一页了");
                    }
                }
            });
            this.add(this.jScrollPane, Integer.valueOf(10), -1);
        }
        else {
            this.setBounds(0, 27, 590, 380);
            this.setOpaque(false);
            this.setLayout(null);
            this.jScrollPane = new JScrollPane();
            this.chooseLeft = new JLabel("近一周");
            this.jScrollPane.setOpaque(false);
            this.jScrollPane.setBounds(12, 46, 571, 306);
            this.chooseLeft.setBounds(56, 4, 98, 20);
            this.jScrollPane.setBorder(null);
            TraslationTableMyorderUntil.TableModel(this.jScrollPane, 10);
            this.jScrollPane.getVerticalScrollBar().setUnitIncrement(50);
            this.chooseLeft.setForeground(Color.white);
            String[] rowDataRight = { "近一周", "近一个月", "一个月前" };
            (this.chooseLeftModel = new TraslationSelectOptionJpanel(98, 120, "img/xy2uiimg/下拉框(2)w98,h120px.png", rowDataRight)).setBounds(52, 24, 98, 120);
            this.chooseLeftModel.setVisible(false);
            this.add(this.getChooseLeftArrows());
            this.getChooseLeftArrows().addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    if (TraslationMyMainMyorderJpanel.this.leftFlag == 1) {
                        TraslationMyMainMyorderJpanel.this.chooseLeftModel.setVisible(true);
                        TraslationMyMainMyorderJpanel.this.leftFlag = 0;
                    }
                    else {
                        TraslationMyMainMyorderJpanel.this.chooseLeftModel.setVisible(false);
                        TraslationMyMainMyorderJpanel.this.leftFlag = 1;
                    }
                }
            });
            this.chooseLeftModel.getJlist().addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    int count = TraslationMyMainMyorderJpanel.this.chooseLeftModel.getJlist().getSelectedIndex();
                    String getname = (String)TraslationMyMainMyorderJpanel.this.chooseLeftModel.getJlist().getSelectedValue();
                    TraslationMyMainMyorderJpanel.this.chooseLeft.setText(getname);
                    TraslationMyMainMyorderJpanel.this.chooseLeftModel.setVisible(false);
                    TraslationMyMainMyorderJpanel.this.leftFlag = 1;
                    TraslationMyMainMyorderJpanel.this.dangqianyeshu = 1;
                    TraslationMyMainMyorderJpanel.this.leftCont = Integer.valueOf(count + 1);
                    BigDecimal roleId = RoleData.getRoleData().getLoginResult().getRole_id();
                    SearchOrderBean searchOrderBean = new SearchOrderBean();
                    searchOrderBean.setTime(TraslationMyMainMyorderJpanel.this.leftCont);
                    searchOrderBean.setPageNum(Integer.valueOf(TraslationMyMainMyorderJpanel.this.dangqianyeshu));
                    String sendmes = Agreement.getAgreement().searchMyOrderAgreement(GsonUtil.getGsonUtil().getgson().toJson(searchOrderBean));
                    TrslationMainJframe.getTrslationMainJframe().setPanelOpen(11);
                    SendMessageUntil.toServer(sendmes);
                }
            });
            this.add(this.chooseLeftModel, Integer.valueOf(10), 1);
            this.add(this.chooseLeft);
            (this.yema = new JLabel(this.dangqianyeshu + "/" + this.zuidayema)).setBounds(246, 356, 58, 17);
            this.yema.setForeground(Color.white);
            this.yema.setOpaque(false);
            this.yema.setHorizontalAlignment(0);
            this.add(this.yema);
            (this.shouye = new TrslationBtn("inkImg/hongmu/21_png.button.tab_nex.png", 1, UIUtils.COLOR_BTNTEXT, UIUtils.TEXT_FONT, "首页")).setBounds(188, 354, 34, 17);
            this.add(this.shouye);
            this.shouye.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    if (TraslationMyMainMyorderJpanel.this.dangqianyeshu != 1) {
                        TraslationMyMainMyorderJpanel.this.dangqianyeshu = 1;
                        TraslationMyMainMyorderJpanel.this.yema.setText(TraslationMyMainMyorderJpanel.this.dangqianyeshu + "/" + TraslationMyMainMyorderJpanel.this.zuidayema);
                        SearchOrderBean searchOrderBean = new SearchOrderBean();
                        searchOrderBean.setTime(TraslationMyMainMyorderJpanel.this.leftCont);
                        searchOrderBean.setPageNum(Integer.valueOf(TraslationMyMainMyorderJpanel.this.dangqianyeshu));
                        String sendmes = Agreement.getAgreement().searchMyOrderAgreement(GsonUtil.getGsonUtil().getgson().toJson(searchOrderBean));
                        TrslationMainJframe.getTrslationMainJframe().setPanelOpen(11);
                        SendMessageUntil.toServer(sendmes);
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
                    if (TraslationMyMainMyorderJpanel.this.dangqianyeshu > 1) {
                        TraslationMyMainMyorderJpanel.this.dangqianyeshu--;
                        TraslationMyMainMyorderJpanel.this.yema.setText(TraslationMyMainMyorderJpanel.this.dangqianyeshu + "/" + TraslationMyMainMyorderJpanel.this.zuidayema);
                        SearchOrderBean searchOrderBean = new SearchOrderBean();
                        searchOrderBean.setTime(TraslationMyMainMyorderJpanel.this.leftCont);
                        searchOrderBean.setPageNum(Integer.valueOf(TraslationMyMainMyorderJpanel.this.dangqianyeshu));
                        String sendmes = Agreement.getAgreement().searchMyOrderAgreement(GsonUtil.getGsonUtil().getgson().toJson(searchOrderBean));
                        TrslationMainJframe.getTrslationMainJframe().setPanelOpen(11);
                        SendMessageUntil.toServer(sendmes);
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
                    if (TraslationMyMainMyorderJpanel.this.dangqianyeshu != TraslationMyMainMyorderJpanel.this.zuidayema) {
                        TraslationMyMainMyorderJpanel.this.dangqianyeshu = TraslationMyMainMyorderJpanel.this.zuidayema;
                        TraslationMyMainMyorderJpanel.this.yema.setText(TraslationMyMainMyorderJpanel.this.dangqianyeshu + "/" + TraslationMyMainMyorderJpanel.this.zuidayema);
                        SearchOrderBean searchOrderBean = new SearchOrderBean();
                        searchOrderBean.setTime(TraslationMyMainMyorderJpanel.this.leftCont);
                        searchOrderBean.setPageNum(Integer.valueOf(TraslationMyMainMyorderJpanel.this.dangqianyeshu));
                        String sendmes = Agreement.getAgreement().searchMyOrderAgreement(GsonUtil.getGsonUtil().getgson().toJson(searchOrderBean));
                        TrslationMainJframe.getTrslationMainJframe().setPanelOpen(11);
                        SendMessageUntil.toServer(sendmes);
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
                    if (TraslationMyMainMyorderJpanel.this.dangqianyeshu < TraslationMyMainMyorderJpanel.this.zuidayema) {
                        TraslationMyMainMyorderJpanel.this.dangqianyeshu++;
                        TraslationMyMainMyorderJpanel.this.yema.setText(TraslationMyMainMyorderJpanel.this.dangqianyeshu + "/" + TraslationMyMainMyorderJpanel.this.zuidayema);
                        SearchOrderBean searchOrderBean = new SearchOrderBean();
                        searchOrderBean.setTime(TraslationMyMainMyorderJpanel.this.leftCont);
                        searchOrderBean.setPageNum(Integer.valueOf(TraslationMyMainMyorderJpanel.this.dangqianyeshu));
                        String sendmes = Agreement.getAgreement().searchMyOrderAgreement(GsonUtil.getGsonUtil().getgson().toJson(searchOrderBean));
                        TrslationMainJframe.getTrslationMainJframe().setPanelOpen(11);
                        SendMessageUntil.toServer(sendmes);
                    }
                    else {
                        ZhuFrame.getZhuJpanel().addPrompt2("已经没有下一页了");
                    }
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
                this.icon1 = new ImageIcon("inkImg/background/22.png");
            }
            g.drawImage(this.icon1.getImage(), 0, 0, 590, 380, this);
        }
        else {
            super.paintComponent(g);
            if (this.icon1 == null) {
                this.icon1 = new ImageIcon("img/xy2uiimg/我的藏宝阁-新我的订单w590,h380px，top97,left22px.png");
            }
            g.drawImage(this.icon1.getImage(), 0, 0, 590, 380, this);
        }
    }
    
    public void setPage(int zuidayema) {
        this.zuidayema = zuidayema;
        this.yema.setText(this.dangqianyeshu + "/" + this.zuidayema);
    }
    
    public Integer setOrder(String leftCont) {
        Integer type = null;
        int n = -1;
        switch (leftCont.hashCode()) {
            case 657623155: {
                if (leftCont.equals("全部订单")) {
                    n = 0;
                    break;
                }
                else {
                    break;
                }
            }
            case 24152491: {
                if (leftCont.equals("待付款")) {
                    n = 1;
                    break;
                }
                else {
                    break;
                }
            }
            case 23765208: {
                if (leftCont.equals("已付款")) {
                    n = 2;
                    break;
                }
                else {
                    break;
                }
            }
            case 733751: {
                if (leftCont.equals("失效")) {
                    n = 3;
                    break;
                }
                else {
                    break;
                }
            }
        }
        switch (n) {
            case 0: {
                return type;
            }
            case 1: {
                type = Integer.valueOf(1);
                break;
            }
            case 2: {
                type = Integer.valueOf(3);
                break;
            }
            case 3: {
                type = Integer.valueOf(2);
                break;
            }
        }
        return type;
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
    
    public Integer getLeftCont() {
        return this.leftCont;
    }
    
    public void setLeftCont(Integer leftCont) {
        this.leftCont = leftCont;
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
        if (MyIsif.getStyle().equals("水墨")) {
            if (this.chooseLeftArrows == null) {
                (this.chooseLeftArrows = new TrslationBtn("inkImg/button/8.png", 1)).setBounds(134, 6, 18, 18);
                this.chooseLeftArrows.setOpaque(false);
            }
            return this.chooseLeftArrows;
        }
        else {
            if (this.chooseLeftArrows == null) {
                (this.chooseLeftArrows = new TrslationBtn("img/xy2uiimg/35_png.button.xy_vscroll$down.png", 1)).setBounds(134, 6, 19, 20);
                this.chooseLeftArrows.setOpaque(false);
            }
            return this.chooseLeftArrows;
        }
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
