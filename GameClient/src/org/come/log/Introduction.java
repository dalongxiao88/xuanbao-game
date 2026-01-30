package org.come.log;

import com.tool.tcp.SpriteFactory;
import java.awt.RenderingHints;
import java.awt.Graphics2D;
import java.awt.Graphics;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Locale;
import java.util.Date;
import java.util.TimerTask;
import java.util.Timer;
import org.come.login.LoginMouslisten;
import javax.swing.BorderFactory;
import java.awt.Dimension;
import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Font;
import java.util.ArrayList;
import java.util.Arrays;
import org.come.socket.DownLoadTxt;
import org.come.login.LoginJpanel;
import java.util.List;
import com.tool.tcpimg.ChatBox;
import javax.swing.JScrollPane;
import org.come.login.BindAccountTipView;
import org.come.login.BindAccountView;
import javax.swing.JLabel;
import com.tool.tcp.Sprite;
import org.come.login.SpriteBtn;
import org.come.view.View;

public class Introduction extends View
{
    private SpriteBtn xiayibu;
    private SpriteBtn up;
    private SpriteBtn down;
    private SpriteBtn jianyi;
    private SpriteBtn rules;
    private SpriteBtn security;
    private SpriteBtn service;
    private SpriteBtn icon1;
    private SpriteBtn icon2;
    private SpriteBtn icon3;
    private SpriteBtn icon4;
    private SpriteBtn icon5;
    private SpriteBtn icon6;
    private SpriteBtn icon7;
    private long time;
    static Sprite tcp5;
    static Sprite tcp;
    private JLabel labMsgTip;
    private JLabel labMsgTip1;
    private BindAccountView accountView;
    private BindAccountTipView accountTipView;
    private static JScrollPane jScrollPane;
    ChatBox box;
    private List<String> sb;
    public JLabel mouse;
    
    public Introduction(LoginJpanel loginJpanel) {
        this.time = 0L;
        this.box = new ChatBox();
        this.sb = this.getGG();
        this.setBounds(0, 0, LanderJPanel.width, LanderJPanel.high);
        this.drawSectors(loginJpanel);
    }
    
    public List<String> getGG() {
        String v = DownLoadTxt.getDownLoadTxt().GetServerTxt("gg.txt");
        if (v != null && v.length() != 0) {
            String[] vs = v.split("\r\n");
            if (vs.length == 1) {
                vs = v.split("\n");
            }
            return Arrays.asList(vs);
        }
        else {
            return new ArrayList<>();
        }
    }
    
    public void drawSectors(LoginJpanel loginJpanel) {
        (this.labMsgTip = new JLabel()).setBounds(45, 406, 300, 30);
        this.labMsgTip.setFont(new Font("楷体", 1, 16));
        this.labMsgTip.setVisible(false);
        this.labMsgTip.setForeground(Color.red);
        this.add(this.labMsgTip);
        (this.labMsgTip1 = new JLabel()).setBounds(45, 406, 300, 30);
        this.labMsgTip1.setFont(new Font("楷体", 1, 16));
        this.labMsgTip1.setVisible(false);
        this.labMsgTip1.setForeground(Color.red);
        this.add(this.labMsgTip1);
        Font font = new Font("宋体", 0, 12);
        JPanel jPanel = new JPanel();
        jPanel.setPreferredSize(new Dimension(350, this.sb.size() * 19));
        jPanel.setOpaque(false);
        jPanel.setLayout(null);
        (Introduction.jScrollPane = new JScrollPane()).setVerticalScrollBarPolicy(22);
        Introduction.jScrollPane.getVerticalScrollBar().setUnitIncrement(10);
        Introduction.jScrollPane.getViewport().setOpaque(false);
        Introduction.jScrollPane.setOpaque(false);
        Introduction.jScrollPane.setBorder(BorderFactory.createEmptyBorder());
        Introduction.jScrollPane.setHorizontalScrollBarPolicy(31);
        Introduction.jScrollPane.setViewportView(jPanel);
        this.box = new ChatBox();
        for (int i = 0; i < this.sb.size(); ++i) {
            this.box.addText((String)this.sb.get(i), 350, font);
            JLabel jLabel = new JLabel((String)this.sb.get(i));
            jLabel.setBounds(10, 18 * i, 350, 18);
            jLabel.setBackground(null);
            jLabel.setForeground(Color.white);
            jLabel.setFont(font);
            jLabel.setOpaque(false);
            jPanel.add(this.box);
        }
        this.add(Introduction.jScrollPane);
        (this.xiayibu = new SpriteBtn("resource/intro/bulletin-continue.was", 543, 438, false)).setBounds(543, 438, 91, 37);
        this.xiayibu.addMouseListener(new LoginMouslisten(17, this.xiayibu, loginJpanel));
        this.add(this.xiayibu);
        (this.up = new SpriteBtn("resource/intro/bulletin-up.was", 425, 438, false)).setBounds(425, 438, 58, 37);
        this.up.addMouseListener(new LoginMouslisten(6, this.up, loginJpanel));
        this.add(this.up);
        (this.down = new SpriteBtn("resource/intro/bulletin-down.was", 481, 438, false)).setBounds(481, 438, 58, 37);
        this.down.addMouseListener(new LoginMouslisten(7, this.down, loginJpanel));
        this.add(this.down);
        (this.jianyi = new SpriteBtn("resource/intro/bulletin-advice.was", 495, 192, false)).setBounds(495, 192, 143, 37);
        this.jianyi.addMouseListener(new LoginMouslisten(8, this.jianyi, loginJpanel));
        this.add(this.jianyi);
        (this.rules = new SpriteBtn("resource/intro/bulletin-rules.was", 495, 102, false)).setBounds(495, 102, 143, 37);
        this.rules.addMouseListener(new LoginMouslisten(9, this.rules, loginJpanel));
        this.add(this.rules);
        (this.security = new SpriteBtn("resource/intro/bulletin-security.was", 495, 147, false)).setBounds(495, 147, 143, 37);
        this.security.addMouseListener(new LoginMouslisten(9, this.security, loginJpanel));
        this.add(this.security);
        (this.service = new SpriteBtn("resource/intro/bulletin-service.was", 495, 57, false)).setBounds(495, 57, 143, 37);
        this.service.addMouseListener(new LoginMouslisten(10, this.service, loginJpanel));
        this.add(this.service);
        (this.icon1 = new SpriteBtn("resource/intro/bulletin-icon1.was", 220, 118, false)).setBounds(222, 118, 26, 27);
        this.icon1.addMouseListener(new LoginMouslisten(10, this.icon1, loginJpanel));
        this.add(this.icon1);
        (this.icon2 = new SpriteBtn("resource/intro/bulletin-icon2.was", 255, 118, false)).setBounds(255, 118, 26, 27);
        this.icon2.addMouseListener(new LoginMouslisten(10, this.icon2, loginJpanel));
        this.add(this.icon2);
        (this.icon3 = new SpriteBtn("resource/intro/bulletin-icon3.was", 288, 118, false)).setBounds(288, 118, 26, 27);
        this.icon3.addMouseListener(new LoginMouslisten(10, this.icon3, loginJpanel));
        this.add(this.icon3);
        (this.icon4 = new SpriteBtn("resource/intro/bulletin-icon1.was", 319, 118, false)).setBounds(319, 118, 26, 27);
        this.icon4.addMouseListener(new LoginMouslisten(10, this.icon4, loginJpanel));
        this.add(this.icon4);
        (this.icon5 = new SpriteBtn("resource/intro/bulletin-icon2.was", 352, 118, false)).setBounds(352, 118, 26, 27);
        this.icon5.addMouseListener(new LoginMouslisten(10, this.icon5, loginJpanel));
        this.add(this.icon5);
        (this.icon6 = new SpriteBtn("resource/intro/bulletin-icon3.was", 385, 118, false)).setBounds(385, 118, 26, 27);
        this.icon6.addMouseListener(new LoginMouslisten(10, this.icon6, loginJpanel));
        this.add(this.icon6);
        (this.icon7 = new SpriteBtn("resource/intro/bulletin-icon1.was", 418, 118, false)).setBounds(418, 118, 26, 27);
        this.icon7.addMouseListener(new LoginMouslisten(10, this.icon7, loginJpanel));
        this.add(this.icon7);
        Timer timer = new Timer();
        long date = 0L;
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                Date date = new Date();
                DateFormat df = new SimpleDateFormat("yyyy.MM.dd.E", Locale.CHINA);
                String format = df.format(date);
                Introduction.this.labMsgTip.setText(format);
            }
        }, 0L, 1000L);
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2d = (Graphics2D)g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        this.time += 10L;
        Introduction.tcp5.updateToTime(this.time, 0);
        Introduction.tcp5.draw(g, 0, 0);
        Introduction.tcp.draw(g, 0, 0);
        Introduction.tcp.updateToTime(this.time, 0);
        if (this.xiayibu != null) {
            this.xiayibu.draw(g);
        }
        if (this.down != null) {
            this.down.draw(g);
        }
        if (this.up != null) {
            this.up.draw(g);
        }
        if (this.jianyi != null) {
            this.jianyi.draw(g);
        }
        if (this.rules != null) {
            this.rules.draw(g);
        }
        if (this.security != null) {
            this.security.draw(g);
        }
        if (this.service != null) {
            this.service.draw(g);
        }
        if (this.icon3 != null) {
            this.icon3.draw(g);
        }
        if (this.icon2 != null) {
            this.icon2.draw(g);
        }
        if (this.icon1 != null) {
            this.icon1.draw(g);
        }
        if (this.icon4 != null) {
            this.icon4.draw(g);
        }
        if (this.icon5 != null) {
            this.icon5.draw(g);
        }
        if (this.icon6 != null) {
            this.icon6.draw(g);
        }
        if (this.icon7 != null) {
            this.icon7.draw(g);
        }
        String vs = this.labMsgTip.getText();
        String[] vss = vs.split("\\|");
        String[] vc = vss[0].split("\\.");
        String[] xq = null;
        if (vc.length > 3) {
            xq = vc[3].split("\\&");
        }
        String quxq = null;
        if (xq != null) {
            quxq = xq[0].substring(2);
        }
        g.setColor(new Color(193, 204, 172));
        g.setFont(new Font("楷体", 1, 17));
        g.drawString(vc[0], 207, 82);
        g.drawString(vc[1], 272, 82);
        g.drawString(vc[2], 310, 82);
        if (quxq != null) {
            g.drawString(quxq, 400, 81);
            g.drawString(quxq, 400, 81);
        }
        g.setFont(new Font("楷体", 1, 15));
        Date date = new Date();
        SimpleDateFormat sys = new SimpleDateFormat("yyyy.MM.dd", Locale.CHINA);
        String ri = sys.format(date);
        String[] rr = ri.split("\\.");
        g.drawString(Lauar.getLunar(rr[0], rr[1], rr[2]), 180, 104);
        g.drawString(Lauar.getLunar(rr[0], rr[1], rr[2]), 180, 104);
        g.drawString(Lauar.getLunar(rr[0], rr[1], rr[2]), 180, 104);
        Introduction.jScrollPane.setBounds(128, 194, 340, 175);
        Introduction.jScrollPane.getVerticalScrollBar().setUI(null);
        Introduction.jScrollPane.validate();
        if (Introduction.jScrollPane != null) {
            g2d.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_OFF);
        }
    }
    
    public BindAccountView getAccountView() {
        return this.accountView;
    }
    
    public void setAccountView(BindAccountView accountView) {
        this.accountView = accountView;
    }
    
    public BindAccountTipView getAccountTipView() {
        return this.accountTipView;
    }
    
    public void setAccountTipView(BindAccountTipView accountTipView) {
        this.accountTipView = accountTipView;
    }
    
    public JLabel getLabMsgTip() {
        return this.labMsgTip;
    }
    
    public void setLabMsgTip(JLabel labMsgTip) {
        this.labMsgTip = labMsgTip;
    }
    
    static {
        Introduction.tcp5 = SpriteFactory.VloadSprite("resource/intro/bulletin-background.was", null);
        Introduction.tcp = SpriteFactory.VloadSprite("resource/intro/bulletin-leaf.was", null);
    }
}
