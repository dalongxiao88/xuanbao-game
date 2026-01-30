package org.cbg.panel;

import java.awt.Graphics;
import java.util.List;
import java.math.BigDecimal;
import java.util.ArrayList;
import org.cbg.bean.DeleteMsgBean;
import org.come.Frame.ZhuFrame;
import org.come.socket.SendMessageUntil;
import org.come.until.GsonUtil;
import org.come.socket.Agreement;
import org.cbg.entity.Message;
import com.tool.tcpimg.UIUtils;
import java.awt.Color;
import java.awt.Component;
import org.cbg.frame.TrslationMainJframe;
import org.come.until.CutButtonImage;
import java.awt.event.MouseEvent;
import java.awt.event.MouseAdapter;
import org.cbg.until.TraslationTableMyMessageUntil;
import java.awt.Dimension;
import com.updateNew.MyIsif;
import javax.swing.ImageIcon;
import javax.swing.JScrollPane;
import org.cbg.btn.TrslationBtn;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class TraslationMyMessageJpanel extends JPanel
{
    private JLabel yema;
    private JLabel quanxuankuang;
    private JLabel gouxuan;
    private TrslationBtn shouye;
    private TrslationBtn moye;
    private TrslationBtn leftArrows;
    private TrslationBtn rightArrows;
    private TrslationBtn deleteMessage;
    private JScrollPane jScrollPane;
    private int dangqianyeshu;
    private int zuidayema;
    private int gouxuanzhuangtai;
    private ImageIcon icon;
    
    public TraslationMyMessageJpanel() {
        this.dangqianyeshu = 1;
        this.zuidayema = 50;
        this.gouxuanzhuangtai = 1;
        if (MyIsif.getStyle().equals("水墨")) {
            this.setPreferredSize(new Dimension(590, 380));
            this.setOpaque(false);
            this.setLayout(null);
            (this.jScrollPane = new JScrollPane()).setOpaque(false);
            this.jScrollPane.setBounds(12, 28, 566, 350);
            this.jScrollPane.setBorder(null);
            TraslationTableMyMessageUntil.TableModel(this.jScrollPane, 10);
            this.add(this.jScrollPane);
            (this.gouxuan = new JLabel()).setBounds(25, 12, 15, 15);
            this.gouxuan.setOpaque(false);
            this.gouxuan.setIcon(null);
            this.gouxuan.setName("1");
            this.add(this.gouxuan);
            this.gouxuan.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    if ("1".equals(TraslationMyMessageJpanel.this.gouxuan.getName())) {
                        TraslationMyMessageJpanel.this.gouxuan.setIcon(CutButtonImage.getImage("inkImg/button/13.png", 15, 15));
                        TraslationMyMessageJpanel.this.gouxuan.setName("0");
                        TrslationMainJframe.getTrslationMainJframe().setXiaoxiGouxuangeshu(TrslationMainJframe.getTrslationMainJframe().getXiaoxiGeshuMax());
                        JPanel view = (JPanel)TraslationMyMessageJpanel.this.jScrollPane.getViewport().getView();
                        Component[] components = view.getComponents();
                        for (int i = 0; i < components.length; ++i) {
                            JPanel panel = (JPanel)components[i];
                            JLabel component2 = (JLabel)panel.getComponent(3);
                            component2.setName("0");
                            component2.setIcon(CutButtonImage.getImage("inkImg/button/13.png", 15, 15));
                        }
                    }
                    else {
                        TraslationMyMessageJpanel.this.gouxuan.setIcon(null);
                        TraslationMyMessageJpanel.this.gouxuan.setName("1");
                        TrslationMainJframe.getTrslationMainJframe().setXiaoxiGouxuangeshu(0);
                        JPanel view = (JPanel)TraslationMyMessageJpanel.this.jScrollPane.getViewport().getView();
                        Component[] components = view.getComponents();
                        for (int i = 0; i < components.length; ++i) {
                            JPanel panel = (JPanel)components[i];
                            JLabel component2 = (JLabel)panel.getComponent(3);
                            component2.setName("1");
                            component2.setIcon(null);
                        }
                    }
                }
            });
            (this.quanxuankuang = new JLabel()).setBounds(25, 12, 15, 15);
            this.quanxuankuang.setOpaque(false);
            this.quanxuankuang.setIcon(CutButtonImage.getImage("inkImg/button/14.png", 15, 15));
            this.add(this.quanxuankuang);
            (this.yema = new JLabel(this.dangqianyeshu + "/" + this.zuidayema)).setBounds(268, 388, 51, 16);
            this.yema.setForeground(Color.white);
            this.yema.setOpaque(false);
            this.yema.setHorizontalAlignment(0);
            this.add(this.yema);
            (this.shouye = new TrslationBtn("inkImg/button/2.png", 1, UIUtils.COLOR_BTNTEXT, UIUtils.TEXT_FONT, "首页")).setBounds(208, 386, 34, 17);
            this.add(this.shouye);
            this.shouye.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    if (TraslationMyMessageJpanel.this.dangqianyeshu != 1) {
                        TraslationMyMessageJpanel.this.dangqianyeshu = 1;
                        TraslationMyMessageJpanel.this.yema.setText(TraslationMyMessageJpanel.this.dangqianyeshu + "/" + TraslationMyMessageJpanel.this.zuidayema);
                        Message message = new Message();
                        message.setPage(TraslationMyMessageJpanel.this.dangqianyeshu);
                        String sendmes = Agreement.getAgreement().searchNewsAgreement(GsonUtil.getGsonUtil().getgson().toJson(message));
                        SendMessageUntil.toServer(sendmes);
                    }
                    else {
                        ZhuFrame.getZhuJpanel().addPrompt2("已在首页");
                    }
                }
            });
            (this.leftArrows = new TrslationBtn("inkImg/button/10.png", 1)).setBounds(245, 386, 19, 20);
            this.add(this.leftArrows);
            this.leftArrows.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    if (TraslationMyMessageJpanel.this.dangqianyeshu > 1) {
                        TraslationMyMessageJpanel.this.dangqianyeshu--;
                        TraslationMyMessageJpanel.this.yema.setText(TraslationMyMessageJpanel.this.dangqianyeshu + "/" + TraslationMyMessageJpanel.this.zuidayema);
                        Message message = new Message();
                        message.setPage(TraslationMyMessageJpanel.this.dangqianyeshu);
                        String sendmes = Agreement.getAgreement().searchNewsAgreement(GsonUtil.getGsonUtil().getgson().toJson(message));
                        SendMessageUntil.toServer(sendmes);
                    }
                    else {
                        ZhuFrame.getZhuJpanel().addPrompt2("已经没有上一页了");
                    }
                }
            });
            (this.moye = new TrslationBtn("inkImg/button/2.png", 1, UIUtils.COLOR_BTNTEXT, UIUtils.TEXT_FONT, "末页")).setBounds(344, 386, 34, 17);
            this.add(this.moye);
            this.moye.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    if (TraslationMyMessageJpanel.this.dangqianyeshu != TraslationMyMessageJpanel.this.zuidayema) {
                        TraslationMyMessageJpanel.this.dangqianyeshu = TraslationMyMessageJpanel.this.zuidayema;
                        TraslationMyMessageJpanel.this.yema.setText(TraslationMyMessageJpanel.this.dangqianyeshu + "/" + TraslationMyMessageJpanel.this.zuidayema);
                        Message message = new Message();
                        message.setPage(TraslationMyMessageJpanel.this.dangqianyeshu);
                        String sendmes = Agreement.getAgreement().searchNewsAgreement(GsonUtil.getGsonUtil().getgson().toJson(message));
                        SendMessageUntil.toServer(sendmes);
                    }
                    else {
                        ZhuFrame.getZhuJpanel().addPrompt2("已在末页");
                    }
                }
            });
            (this.rightArrows = new TrslationBtn("inkImg/button/9.png", 1)).setBounds(322, 386, 19, 20);
            this.add(this.rightArrows);
            this.rightArrows.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    if (TraslationMyMessageJpanel.this.dangqianyeshu < TraslationMyMessageJpanel.this.zuidayema) {
                        TraslationMyMessageJpanel.this.dangqianyeshu++;
                        TraslationMyMessageJpanel.this.yema.setText(TraslationMyMessageJpanel.this.dangqianyeshu + "/" + TraslationMyMessageJpanel.this.zuidayema);
                        Message message = new Message();
                        message.setPage(TraslationMyMessageJpanel.this.dangqianyeshu);
                        String sendmes = Agreement.getAgreement().searchNewsAgreement(GsonUtil.getGsonUtil().getgson().toJson(message));
                        SendMessageUntil.toServer(sendmes);
                    }
                    else {
                        ZhuFrame.getZhuJpanel().addPrompt2("已经没有下一页了");
                    }
                }
            });
            (this.deleteMessage = new TrslationBtn("inkImg/button/49.png", 1, UIUtils.COLOR_BTNTEXT, UIUtils.TEXT_FONT, "删除消息")).setBounds(12, 386, 68, 17);
            this.add(this.deleteMessage);
            this.deleteMessage.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    DeleteMsgBean deleteMsgBean = new DeleteMsgBean();
                    List<BigDecimal> mesIdList = new ArrayList<>();
                    JPanel view = (JPanel)TraslationMyMessageJpanel.this.jScrollPane.getViewport().getView();
                    Component[] components = view.getComponents();
                    for (int i = 0; i < components.length; ++i) {
                        JPanel panel = (JPanel)components[i];
                        JLabel component2 = (JLabel)panel.getComponent(3);
                        if (component2.getName().equals("0")) {
                            JLabel component3 = (JLabel)panel.getComponent(0);
                            BigDecimal mesId = new BigDecimal(component3.getName());
                            mesIdList.add(mesId);
                        }
                    }
                    if (mesIdList.size() == 0) {
                        ZhuFrame.getZhuJpanel().addPrompt2("没有选择要删除的消息");
                        return;
                    }
                    deleteMsgBean.setIds(mesIdList);
                    TrslationMainJframe.getTrslationMainJframe().setXiaoxiGeshuMax(TrslationMainJframe.getTrslationMainJframe().getXiaoxiGeshuMax() - TrslationMainJframe.getTrslationMainJframe().getXiaoxiGouxuangeshu());
                    TrslationMainJframe.getTrslationMainJframe().setXiaoxiGouxuangeshu(0);
                    String sendmes = Agreement.getAgreement().searchDeleteNewsAgreement(GsonUtil.getGsonUtil().getgson().toJson(deleteMsgBean));
                    SendMessageUntil.toServer(sendmes);
                }
            });
        }
        else {
            this.setPreferredSize(new Dimension(590, 380));
            this.setOpaque(false);
            this.setLayout(null);
            (this.jScrollPane = new JScrollPane()).setOpaque(false);
            this.jScrollPane.setBounds(12, 28, 566, 350);
            this.jScrollPane.setBorder(null);
            TraslationTableMyMessageUntil.TableModel(this.jScrollPane, 10);
            this.add(this.jScrollPane);
            (this.gouxuan = new JLabel()).setBounds(25, 12, 15, 15);
            this.gouxuan.setOpaque(false);
            this.gouxuan.setIcon(null);
            this.gouxuan.setName("1");
            this.add(this.gouxuan);
            this.gouxuan.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    if ("1".equals(TraslationMyMessageJpanel.this.gouxuan.getName())) {
                        TraslationMyMessageJpanel.this.gouxuan.setIcon(CutButtonImage.getImage("inkImg/button/13.png", 15, 15));
                        TraslationMyMessageJpanel.this.gouxuan.setName("0");
                        TrslationMainJframe.getTrslationMainJframe().setXiaoxiGouxuangeshu(TrslationMainJframe.getTrslationMainJframe().getXiaoxiGeshuMax());
                        JPanel view = (JPanel)TraslationMyMessageJpanel.this.jScrollPane.getViewport().getView();
                        Component[] components = view.getComponents();
                        for (int i = 0; i < components.length; ++i) {
                            JPanel panel = (JPanel)components[i];
                            JLabel component2 = (JLabel)panel.getComponent(3);
                            component2.setName("0");
                            component2.setIcon(CutButtonImage.getImage("inkImg/button/13.png", 15, 15));
                        }
                    }
                    else {
                        TraslationMyMessageJpanel.this.gouxuan.setIcon(null);
                        TraslationMyMessageJpanel.this.gouxuan.setName("1");
                        TrslationMainJframe.getTrslationMainJframe().setXiaoxiGouxuangeshu(0);
                        JPanel view = (JPanel)TraslationMyMessageJpanel.this.jScrollPane.getViewport().getView();
                        Component[] components = view.getComponents();
                        for (int i = 0; i < components.length; ++i) {
                            JPanel panel = (JPanel)components[i];
                            JLabel component2 = (JLabel)panel.getComponent(3);
                            component2.setName("1");
                            component2.setIcon(null);
                        }
                    }
                }
            });
            (this.quanxuankuang = new JLabel()).setBounds(25, 12, 15, 15);
            this.quanxuankuang.setOpaque(false);
            this.quanxuankuang.setIcon(CutButtonImage.getImage("inkImg/button/14.png", 15, 15));
            this.add(this.quanxuankuang);
            (this.yema = new JLabel(this.dangqianyeshu + "/" + this.zuidayema)).setBounds(268, 388, 51, 16);
            this.yema.setForeground(Color.white);
            this.yema.setOpaque(false);
            this.yema.setHorizontalAlignment(0);
            this.add(this.yema);
            (this.shouye = new TrslationBtn("inkImg/hongmu/21_png.button.tab_nex.png", 1, UIUtils.COLOR_BTNTEXT, UIUtils.TEXT_FONT, "首页")).setBounds(208, 386, 34, 17);
            this.add(this.shouye);
            this.shouye.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    if (TraslationMyMessageJpanel.this.dangqianyeshu != 1) {
                        TraslationMyMessageJpanel.this.dangqianyeshu = 1;
                        TraslationMyMessageJpanel.this.yema.setText(TraslationMyMessageJpanel.this.dangqianyeshu + "/" + TraslationMyMessageJpanel.this.zuidayema);
                        Message message = new Message();
                        message.setPage(TraslationMyMessageJpanel.this.dangqianyeshu);
                        String sendmes = Agreement.getAgreement().searchNewsAgreement(GsonUtil.getGsonUtil().getgson().toJson(message));
                        SendMessageUntil.toServer(sendmes);
                    }
                    else {
                        ZhuFrame.getZhuJpanel().addPrompt2("已在首页");
                    }
                }
            });
            (this.leftArrows = new TrslationBtn("img/xy2uiimg/30_png.button.btn_8.png", 1)).setBounds(245, 386, 19, 20);
            this.add(this.leftArrows);
            this.leftArrows.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    if (TraslationMyMessageJpanel.this.dangqianyeshu > 1) {
                        TraslationMyMessageJpanel.this.dangqianyeshu--;
                        TraslationMyMessageJpanel.this.yema.setText(TraslationMyMessageJpanel.this.dangqianyeshu + "/" + TraslationMyMessageJpanel.this.zuidayema);
                        Message message = new Message();
                        message.setPage(TraslationMyMessageJpanel.this.dangqianyeshu);
                        String sendmes = Agreement.getAgreement().searchNewsAgreement(GsonUtil.getGsonUtil().getgson().toJson(message));
                        SendMessageUntil.toServer(sendmes);
                    }
                    else {
                        ZhuFrame.getZhuJpanel().addPrompt2("已经没有上一页了");
                    }
                }
            });
            (this.moye = new TrslationBtn("inkImg/hongmu/21_png.button.tab_nex.png", 1, UIUtils.COLOR_BTNTEXT, UIUtils.TEXT_FONT, "末页")).setBounds(344, 386, 34, 17);
            this.add(this.moye);
            this.moye.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    if (TraslationMyMessageJpanel.this.dangqianyeshu != TraslationMyMessageJpanel.this.zuidayema) {
                        TraslationMyMessageJpanel.this.dangqianyeshu = TraslationMyMessageJpanel.this.zuidayema;
                        TraslationMyMessageJpanel.this.yema.setText(TraslationMyMessageJpanel.this.dangqianyeshu + "/" + TraslationMyMessageJpanel.this.zuidayema);
                        Message message = new Message();
                        message.setPage(TraslationMyMessageJpanel.this.dangqianyeshu);
                        String sendmes = Agreement.getAgreement().searchNewsAgreement(GsonUtil.getGsonUtil().getgson().toJson(message));
                        SendMessageUntil.toServer(sendmes);
                    }
                    else {
                        ZhuFrame.getZhuJpanel().addPrompt2("已在末页");
                    }
                }
            });
            (this.rightArrows = new TrslationBtn("img/xy2uiimg/36_png.button.btn_7.png", 1)).setBounds(322, 386, 19, 20);
            this.add(this.rightArrows);
            this.rightArrows.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    if (TraslationMyMessageJpanel.this.dangqianyeshu < TraslationMyMessageJpanel.this.zuidayema) {
                        TraslationMyMessageJpanel.this.dangqianyeshu++;
                        TraslationMyMessageJpanel.this.yema.setText(TraslationMyMessageJpanel.this.dangqianyeshu + "/" + TraslationMyMessageJpanel.this.zuidayema);
                        Message message = new Message();
                        message.setPage(TraslationMyMessageJpanel.this.dangqianyeshu);
                        String sendmes = Agreement.getAgreement().searchNewsAgreement(GsonUtil.getGsonUtil().getgson().toJson(message));
                        SendMessageUntil.toServer(sendmes);
                    }
                    else {
                        ZhuFrame.getZhuJpanel().addPrompt2("已经没有下一页了");
                    }
                }
            });
            (this.deleteMessage = new TrslationBtn("inkImg/hongmu/a7.png", 1, UIUtils.COLOR_BTNTEXT, UIUtils.TEXT_FONT, "删除消息")).setBounds(12, 386, 68, 17);
            this.add(this.deleteMessage);
            this.deleteMessage.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    DeleteMsgBean deleteMsgBean = new DeleteMsgBean();
                    List<BigDecimal> mesIdList = new ArrayList<>();
                    JPanel view = (JPanel)TraslationMyMessageJpanel.this.jScrollPane.getViewport().getView();
                    Component[] components = view.getComponents();
                    for (int i = 0; i < components.length; ++i) {
                        JPanel panel = (JPanel)components[i];
                        JLabel component2 = (JLabel)panel.getComponent(3);
                        if (component2.getName().equals("0")) {
                            JLabel component3 = (JLabel)panel.getComponent(0);
                            BigDecimal mesId = new BigDecimal(component3.getName());
                            mesIdList.add(mesId);
                        }
                    }
                    if (mesIdList.size() == 0) {
                        ZhuFrame.getZhuJpanel().addPrompt2("没有选择要删除的消息");
                        return;
                    }
                    deleteMsgBean.setIds(mesIdList);
                    TrslationMainJframe.getTrslationMainJframe().setXiaoxiGeshuMax(TrslationMainJframe.getTrslationMainJframe().getXiaoxiGeshuMax() - TrslationMainJframe.getTrslationMainJframe().getXiaoxiGouxuangeshu());
                    TrslationMainJframe.getTrslationMainJframe().setXiaoxiGouxuangeshu(0);
                    String sendmes = Agreement.getAgreement().searchDeleteNewsAgreement(GsonUtil.getGsonUtil().getgson().toJson(deleteMsgBean));
                    SendMessageUntil.toServer(sendmes);
                }
            });
        }
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        if (MyIsif.getStyle().equals("水墨")) {
            if (this.icon == null) {
                this.icon = new ImageIcon("inkImg/background/40.png");
            }
            g.drawImage(this.icon.getImage(), 0, 0, 590, 412, this);
        }
        else {
            if (this.icon == null) {
                this.icon = new ImageIcon("img/xy2uiimg/消息w590,h412x，top66,left22px.png");
            }
            g.drawImage(this.icon.getImage(), 0, 0, 590, 412, this);
        }
    }
    
    public void setPage(int zuidayema) {
        this.zuidayema = zuidayema;
        this.yema.setText(this.dangqianyeshu + "/" + this.zuidayema);
    }
    
    public JLabel getYema() {
        return this.yema;
    }
    
    public void setYema(JLabel yema) {
        this.yema = yema;
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
    
    public TrslationBtn getDeleteMessage() {
        return this.deleteMessage;
    }
    
    public void setDeleteMessage(TrslationBtn deleteMessage) {
        this.deleteMessage = deleteMessage;
    }
    
    public ImageIcon getIcon() {
        return this.icon;
    }
    
    public void setIcon(ImageIcon icon) {
        this.icon = icon;
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
    
    public int getGouxuanzhuangtai() {
        return this.gouxuanzhuangtai;
    }
    
    public void setGouxuanzhuangtai(int gouxuanzhuangtai) {
        this.gouxuanzhuangtai = gouxuanzhuangtai;
    }
}
