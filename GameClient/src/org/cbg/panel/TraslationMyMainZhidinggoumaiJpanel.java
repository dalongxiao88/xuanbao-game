package org.cbg.panel;

import java.awt.Graphics;
import org.come.Frame.ZhuFrame;
import org.come.socket.SendMessageUntil;
import org.cbg.frame.TrslationMainJframe;
import org.come.socket.Agreement;
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
import javax.swing.JPanel;

public class TraslationMyMainZhidinggoumaiJpanel extends JPanel
{
    private JLabel yema;
    private TrslationBtn shouye;
    private TrslationBtn moye;
    private TrslationBtn leftArrows;
    private TrslationBtn rightArrows;
    private int dangqianyeshu;
    private int zuidayema;
    private JScrollPane jScrollPane;
    private ImageIcon icon;
    
    public TraslationMyMainZhidinggoumaiJpanel() {
        this.dangqianyeshu = 1;
        this.zuidayema = 50;
        if (MyIsif.getStyle().equals("水墨")) {
            this.setPreferredSize(new Dimension(590, 380));
            this.setOpaque(false);
            this.setLayout(null);
            (this.jScrollPane = new JScrollPane()).setOpaque(false);
            this.jScrollPane.setBounds(12, 46, 571, 306);
            this.jScrollPane.setBorder(null);
            TraslationTableUntil.TableModel(this.jScrollPane, 10);
            this.add(this.jScrollPane);
            (this.yema = new JLabel(this.dangqianyeshu + "/" + this.zuidayema)).setBounds(238, 356, 58, 17);
            this.yema.setForeground(Color.white);
            this.yema.setOpaque(false);
            this.yema.setHorizontalAlignment(0);
            this.add(this.yema);
            (this.shouye = new TrslationBtn("inkImg/button/2.png", 1, UIUtils.COLOR_BTNTEXT, UIUtils.TEXT_FONT, "首页")).setBounds(187, 354, 34, 17);
            this.add(this.shouye);
            this.shouye.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    if (TraslationMyMainZhidinggoumaiJpanel.this.dangqianyeshu != 1) {
                        TraslationMyMainZhidinggoumaiJpanel.this.dangqianyeshu = 1;
                        TraslationMyMainZhidinggoumaiJpanel.this.yema.setText(TraslationMyMainZhidinggoumaiJpanel.this.dangqianyeshu + "/" + TraslationMyMainZhidinggoumaiJpanel.this.zuidayema);
                        String sendmes = Agreement.getAgreement().searchAppointAgreement(TraslationMyMainZhidinggoumaiJpanel.this.dangqianyeshu + "");
                        TrslationMainJframe.getTrslationMainJframe().setPanelOpen(10);
                        SendMessageUntil.toServer(sendmes);
                    }
                    else {
                        ZhuFrame.getZhuJpanel().addPrompt2("已在首页");
                    }
                }
            });
            (this.leftArrows = new TrslationBtn("inkImg/button/10.png", 1)).setBounds(225, 354, 19, 20);
            this.add(this.leftArrows);
            this.leftArrows.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    if (TraslationMyMainZhidinggoumaiJpanel.this.dangqianyeshu > 1) {
                        TraslationMyMainZhidinggoumaiJpanel.this.dangqianyeshu--;
                        TraslationMyMainZhidinggoumaiJpanel.this.yema.setText(TraslationMyMainZhidinggoumaiJpanel.this.dangqianyeshu + "/" + TraslationMyMainZhidinggoumaiJpanel.this.zuidayema);
                        String sendmes = Agreement.getAgreement().searchAppointAgreement(TraslationMyMainZhidinggoumaiJpanel.this.dangqianyeshu + "");
                        TrslationMainJframe.getTrslationMainJframe().setPanelOpen(10);
                        SendMessageUntil.toServer(sendmes);
                    }
                    else {
                        ZhuFrame.getZhuJpanel().addPrompt2("已经没有上一页了");
                    }
                }
            });
            (this.moye = new TrslationBtn("inkImg/button/2.png", 1, UIUtils.COLOR_BTNTEXT, UIUtils.TEXT_FONT, "末页")).setBounds(322, 354, 34, 17);
            this.add(this.moye);
            this.moye.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    if (TraslationMyMainZhidinggoumaiJpanel.this.dangqianyeshu != TraslationMyMainZhidinggoumaiJpanel.this.zuidayema) {
                        TraslationMyMainZhidinggoumaiJpanel.this.dangqianyeshu = TraslationMyMainZhidinggoumaiJpanel.this.zuidayema;
                        TraslationMyMainZhidinggoumaiJpanel.this.yema.setText(TraslationMyMainZhidinggoumaiJpanel.this.dangqianyeshu + "/" + TraslationMyMainZhidinggoumaiJpanel.this.zuidayema);
                        String sendmes = Agreement.getAgreement().searchAppointAgreement(TraslationMyMainZhidinggoumaiJpanel.this.dangqianyeshu + "");
                        TrslationMainJframe.getTrslationMainJframe().setPanelOpen(10);
                        SendMessageUntil.toServer(sendmes);
                    }
                    else {
                        ZhuFrame.getZhuJpanel().addPrompt2("已在末页");
                    }
                }
            });
            (this.rightArrows = new TrslationBtn("inkImg/button/9.png", 1)).setBounds(300, 354, 19, 20);
            this.add(this.rightArrows);
            this.rightArrows.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    if (TraslationMyMainZhidinggoumaiJpanel.this.dangqianyeshu < TraslationMyMainZhidinggoumaiJpanel.this.zuidayema) {
                        TraslationMyMainZhidinggoumaiJpanel.this.dangqianyeshu++;
                        TraslationMyMainZhidinggoumaiJpanel.this.yema.setText(TraslationMyMainZhidinggoumaiJpanel.this.dangqianyeshu + "/" + TraslationMyMainZhidinggoumaiJpanel.this.zuidayema);
                        String sendmes = Agreement.getAgreement().searchAppointAgreement(TraslationMyMainZhidinggoumaiJpanel.this.dangqianyeshu + "");
                        SendMessageUntil.toServer(sendmes);
                    }
                    else {
                        ZhuFrame.getZhuJpanel().addPrompt2("已经没有下一页了");
                    }
                }
            });
        }
        else {
            this.setPreferredSize(new Dimension(590, 380));
            this.setOpaque(false);
            this.setLayout(null);
            (this.jScrollPane = new JScrollPane()).setOpaque(false);
            this.jScrollPane.setBounds(12, 46, 571, 306);
            this.jScrollPane.setBorder(null);
            TraslationTableUntil.TableModel(this.jScrollPane, 10);
            this.add(this.jScrollPane);
            (this.yema = new JLabel(this.dangqianyeshu + "/" + this.zuidayema)).setBounds(238, 356, 58, 17);
            this.yema.setForeground(Color.white);
            this.yema.setOpaque(false);
            this.yema.setHorizontalAlignment(0);
            this.add(this.yema);
            (this.shouye = new TrslationBtn("inkImg/hongmu/21_png.button.tab_nex.png", 1, UIUtils.COLOR_BTNTEXT, UIUtils.TEXT_FONT, "首页")).setBounds(187, 354, 34, 17);
            this.add(this.shouye);
            this.shouye.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    if (TraslationMyMainZhidinggoumaiJpanel.this.dangqianyeshu != 1) {
                        TraslationMyMainZhidinggoumaiJpanel.this.dangqianyeshu = 1;
                        TraslationMyMainZhidinggoumaiJpanel.this.yema.setText(TraslationMyMainZhidinggoumaiJpanel.this.dangqianyeshu + "/" + TraslationMyMainZhidinggoumaiJpanel.this.zuidayema);
                        String sendmes = Agreement.getAgreement().searchAppointAgreement(TraslationMyMainZhidinggoumaiJpanel.this.dangqianyeshu + "");
                        TrslationMainJframe.getTrslationMainJframe().setPanelOpen(10);
                        SendMessageUntil.toServer(sendmes);
                    }
                    else {
                        ZhuFrame.getZhuJpanel().addPrompt2("已在首页");
                    }
                }
            });
            (this.leftArrows = new TrslationBtn("img/xy2uiimg/30_png.button.btn_8.png", 1)).setBounds(225, 354, 19, 20);
            this.add(this.leftArrows);
            this.leftArrows.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    if (TraslationMyMainZhidinggoumaiJpanel.this.dangqianyeshu > 1) {
                        TraslationMyMainZhidinggoumaiJpanel.this.dangqianyeshu--;
                        TraslationMyMainZhidinggoumaiJpanel.this.yema.setText(TraslationMyMainZhidinggoumaiJpanel.this.dangqianyeshu + "/" + TraslationMyMainZhidinggoumaiJpanel.this.zuidayema);
                        String sendmes = Agreement.getAgreement().searchAppointAgreement(TraslationMyMainZhidinggoumaiJpanel.this.dangqianyeshu + "");
                        TrslationMainJframe.getTrslationMainJframe().setPanelOpen(10);
                        SendMessageUntil.toServer(sendmes);
                    }
                    else {
                        ZhuFrame.getZhuJpanel().addPrompt2("已经没有上一页了");
                    }
                }
            });
            (this.moye = new TrslationBtn("inkImg/hongmu/21_png.button.tab_nex.png", 1, UIUtils.COLOR_BTNTEXT, UIUtils.TEXT_FONT, "末页")).setBounds(322, 354, 34, 17);
            this.add(this.moye);
            this.moye.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    if (TraslationMyMainZhidinggoumaiJpanel.this.dangqianyeshu != TraslationMyMainZhidinggoumaiJpanel.this.zuidayema) {
                        TraslationMyMainZhidinggoumaiJpanel.this.dangqianyeshu = TraslationMyMainZhidinggoumaiJpanel.this.zuidayema;
                        TraslationMyMainZhidinggoumaiJpanel.this.yema.setText(TraslationMyMainZhidinggoumaiJpanel.this.dangqianyeshu + "/" + TraslationMyMainZhidinggoumaiJpanel.this.zuidayema);
                        String sendmes = Agreement.getAgreement().searchAppointAgreement(TraslationMyMainZhidinggoumaiJpanel.this.dangqianyeshu + "");
                        TrslationMainJframe.getTrslationMainJframe().setPanelOpen(10);
                        SendMessageUntil.toServer(sendmes);
                    }
                    else {
                        ZhuFrame.getZhuJpanel().addPrompt2("已在末页");
                    }
                }
            });
            (this.rightArrows = new TrslationBtn("img/xy2uiimg/36_png.button.btn_7.png", 1)).setBounds(300, 354, 19, 20);
            this.add(this.rightArrows);
            this.rightArrows.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    if (TraslationMyMainZhidinggoumaiJpanel.this.dangqianyeshu < TraslationMyMainZhidinggoumaiJpanel.this.zuidayema) {
                        TraslationMyMainZhidinggoumaiJpanel.this.dangqianyeshu++;
                        TraslationMyMainZhidinggoumaiJpanel.this.yema.setText(TraslationMyMainZhidinggoumaiJpanel.this.dangqianyeshu + "/" + TraslationMyMainZhidinggoumaiJpanel.this.zuidayema);
                        String sendmes = Agreement.getAgreement().searchAppointAgreement(TraslationMyMainZhidinggoumaiJpanel.this.dangqianyeshu + "");
                        SendMessageUntil.toServer(sendmes);
                    }
                    else {
                        ZhuFrame.getZhuJpanel().addPrompt2("已经没有下一页了");
                    }
                }
            });
        }
    }
    
    public void setPage(int zuidayema) {
        this.zuidayema = zuidayema;
        this.yema.setText(this.dangqianyeshu + "/" + zuidayema);
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        if (MyIsif.getStyle().equals("水墨")) {
            super.paintComponent(g);
            if (this.icon == null) {
                this.icon = new ImageIcon("inkImg/background/24.png");
            }
            g.drawImage(this.icon.getImage(), 0, 0, 590, 380, this);
        }
        else {
            super.paintComponent(g);
            if (this.icon == null) {
                this.icon = new ImageIcon("img/xy2uiimg/我的藏宝阁-指定购买w590,h380px，top97,left22px.png");
            }
            g.drawImage(this.icon.getImage(), 0, 0, 590, 380, this);
        }
    }
    
    public JLabel getYema() {
        return this.yema;
    }
    
    public void setYema(JLabel yema) {
        this.yema = yema;
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
    
    public ImageIcon getIcon() {
        return this.icon;
    }
    
    public void setIcon(ImageIcon icon) {
        this.icon = icon;
    }
}
