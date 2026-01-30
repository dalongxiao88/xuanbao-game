package org.come.Jpanel;

import java.awt.Graphics;
import com.tool.time.Limit;
import com.tool.time.TimerUtil;
import com.tool.time.TimeLimit;
import java.awt.Color;
import java.awt.Font;
import com.tool.btn.FormsOnOffBtn;
import com.tool.tcpimg.UIUtils;
import java.awt.Dimension;
import com.updateNew.MyIsif;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import com.tool.btn.CommonBtn;
import javax.swing.JPanel;

public class YuekaJpanel extends JPanel
{
    private CommonBtn getMonthBtn;
    private CommonBtn receiveAwardBtn;
    private CommonBtn getMonthBtn1;
    private CommonBtn receiveAwardBtn1;

    private JLabel mrjl;
    private JLabel mrjl1;
    private JLabel dayNum;
    private JLabel dayNum1;
    private ImageIcon icon;
    private ImageIcon icon1;
    private ImageIcon icon2;
    
    public YuekaJpanel() {
        this.icon1 = new ImageIcon("inkImg/button1/82.png");
        this.icon2 = new ImageIcon("inkImg/button1/83.png");
        if (MyIsif.getStyle().equals("水墨")) {
            this.setPreferredSize(new Dimension(523, 430));
            this.setLayout(null);
            this.setBackground(UIUtils.Color_BACK);
            FormsOnOffBtn offBtn = new FormsOnOffBtn("inkImg/button/1.png", 1, 901);
            offBtn.setBounds(488, 5, 25, 25);
            this.add(offBtn);
            (this.getMonthBtn = new CommonBtn("inkImg/button1/B21.png", 1, "购买月卡", 23, this, null)).setBounds(120, 125+8, 79, 72);
            this.add(this.getMonthBtn);
            (this.receiveAwardBtn = new CommonBtn("inkImg/button1/B21.png", 1, "领取奖励", 20, this, null)).setBounds(250, 125+8, 79, 72);
            this.add(this.receiveAwardBtn);
            (this.getMonthBtn1 = new CommonBtn("inkImg/button1/B21.png", 1, "购买季卡", 24, this, null)).setBounds(120, 125+183, 79, 72);
            this.add(this.getMonthBtn1);
            (this.receiveAwardBtn1 = new CommonBtn("inkImg/button1/B21.png", 1, "领取奖励", 25, this, null)).setBounds(250, 125+183, 79, 72);
            this.add(this.receiveAwardBtn1);
            Font font = new Font("楷体", 1, 18);
            (this.mrjl = new JLabel()).setForeground(Color.BLACK);
            this.mrjl.setFont(font);
            this.mrjl.setBounds(160, 15, 250, 150);
            this.mrjl.setText("<html><body>1.召唤兽死亡不掉忠诚|人物死亡惩罚减半</font><br/> 2.解锁挂机助手，</font><br/>3.特技：锦上添花</font><br/>4.每日可获得抽奖次数</font>");
            this.add(this.mrjl);
            font = new Font("楷体", 1, 18);
            (this.mrjl1 = new JLabel()).setForeground(Color.BLACK);
            this.mrjl1.setFont(font);
            this.mrjl1.setBounds(160, 141, 400, 250);
            this.mrjl1.setText("<html><body>1.掉落率+5，</font><br/> 2.加强全系法术+10</font><br/>3.经验加成+10</font><br/>4.每日可获得抽奖次数</font>");
            this.add(this.mrjl1);
            this.getDayNum();
            this.getDayNum1();
        }
        else {
            this.setPreferredSize(new Dimension(523, 430));
            this.setLayout(null);
            this.setBackground(UIUtils.Color_BACK);
            FormsOnOffBtn offBtn = new FormsOnOffBtn("inkImg/button/1.png", 1, 901);
            offBtn.setBounds(488, 5, 25, 25);
            this.add(offBtn);
            (this.getMonthBtn = new CommonBtn("inkImg/hongmu/1_png.button.btn_xyyk.png", 1, "", 23, this, null)).setBounds(120, 125+8, 80, 78);
            this.add(this.getMonthBtn);
            (this.receiveAwardBtn = new CommonBtn("inkImg/hongmu/1_png.button.btn_xylq.png", 1, "", 20, this, null)).setBounds(250, 125+8, 79, 78);
            this.add(this.receiveAwardBtn);
            (this.getMonthBtn1 = new CommonBtn("inkImg/hongmu/1_png.button.btn_xyjk.png", 1, "", 24, this, null)).setBounds(120, 125+183, 79, 78);
            this.add(this.getMonthBtn1);
            (this.receiveAwardBtn1 = new CommonBtn("inkImg/hongmu/1_png.button.btn_xylq.png", 1, "", 25, this, null)).setBounds(250, 125+183, 79, 78);
            this.add(this.receiveAwardBtn1);
            Font font = new Font("楷体", 1, 17);
            (this.mrjl = new JLabel()).setForeground(Color.YELLOW);
            this.mrjl.setFont(font);
            this.mrjl.setBounds(160, 15, 250, 150);
            this.mrjl.setText("<html><body>1.召唤兽死亡不掉忠诚|人物死亡惩罚减半</font><br/> 2.解锁挂机助手，</font><br/>3.特技：锦上添花</font><br/>4.每日可获得抽奖次数</font>");
            this.add(this.mrjl);
            font = new Font("楷体", 1, 17);
            (this.mrjl1 = new JLabel()).setForeground(Color.YELLOW);
            this.mrjl1.setFont(font);
            this.mrjl1.setBounds(160, 141, 400, 250);
            this.mrjl1.setText("<html><body>1.1.掉落率+5，</font><br/> 2.加强全系法术+10</font><br/>3.经验加成+10</font><br/>4.每日可获得抽奖次数</font>");
            this.add(this.mrjl1);
            this.getDayNum();
            this.getDayNum1();
        }
    }
    
    public void changeTime() {
        Limit limit = TimeLimit.getLimits().getLimit("VIP");
        if (limit != null) {
            int residueDay = TimerUtil.residueDay(limit.getTime());
            if (residueDay >= 0) {
                this.dayNum.setText("剩余 " + residueDay + "  天");
                return;
            }
        }
        this.dayNum.setText("--");
    }
    
    public JLabel getDayNum() {
        if (MyIsif.getStyle().equals("水墨")) {
            if (this.dayNum == null) {
                (this.dayNum = new JLabel("30", 0)).setFont(UIUtils.TEXT_FONT3);
                this.dayNum.setForeground(Color.BLACK);
                this.dayNum.setBounds(304, 165, 200, 16);
                this.dayNum.setOpaque(false);
                this.add(this.dayNum);
            }
        }
        else if (this.dayNum == null) {
            (this.dayNum = new JLabel("30", 0)).setFont(UIUtils.TEXT_FONT3);
            this.dayNum.setForeground(Color.YELLOW);
            this.dayNum.setBounds(304, 165, 200, 16);
            this.dayNum.setOpaque(false);
            this.add(this.dayNum);
        }
        return this.dayNum;
    }

    public void changeTime1() {
        Limit limit = TimeLimit.getLimits().getLimit("JVIP");
        if (limit != null) {
            int residueDay = TimerUtil.residueDay(limit.getTime());
            if (residueDay >= 0) {
                this.dayNum1.setText("剩余 " + residueDay + "  天");
                return;
            }
        }
        this.dayNum1.setText("--");
    }

    public JLabel getDayNum1() {
        if (MyIsif.getStyle().equals("水墨")) {
            if (this.dayNum1 == null) {
                (this.dayNum1 = new JLabel("30", 0)).setFont(UIUtils.TEXT_FONT3);
                this.dayNum1.setForeground(Color.BLACK);
                this.dayNum1.setBounds(306, 165+173, 200, 16);
                this.dayNum1.setOpaque(false);
                this.add(this.dayNum1);
            }
        }
        else if (this.dayNum1 == null) {
            (this.dayNum1 = new JLabel("30", 0)).setFont(UIUtils.TEXT_FONT3);
            this.dayNum1.setForeground(Color.YELLOW);
            this.dayNum1.setBounds(306, 165+173, 202, 16);
            this.dayNum1.setOpaque(false);
            this.add(this.dayNum1);
        }
        return this.dayNum1;
    }
    
    public void setDayNum(JLabel dayNum) {
        this.dayNum = dayNum;
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (MyIsif.getStyle().equals("水墨")) {
            this.icon = new ImageIcon("inkImg/background1/yktq.png");
            g.drawImage(this.icon.getImage(), 0, 0, 523, 430, this);
            if (this.icon1 != null) {
                g.drawImage(this.icon1.getImage(), 70, 53, 77, 76, this);
            }
            if (this.icon2 != null) {
                g.drawImage(this.icon2.getImage(), 70, 228, 77, 76, this);
            }
        }
        else {
            this.icon = new ImageIcon("inkImg/background1/hyktq.png");
            g.drawImage(this.icon.getImage(), 0, 0, 523, 430, this);
            if (this.icon1 != null) {
                g.drawImage(this.icon1.getImage(), 70, 53, 77, 76, this);
                if (this.icon2 != null) {
                    g.drawImage(this.icon2.getImage(), 70, 228, 77, 76, this);
                }
            }
        }
    }
}
