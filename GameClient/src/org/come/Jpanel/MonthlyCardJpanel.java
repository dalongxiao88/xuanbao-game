package org.come.Jpanel;

import com.tool.tcpimg.UIUtils;
import com.tool.time.Limit;
import com.tool.time.TimerUtil;
import com.tool.time.TimeLimit;
import java.awt.Graphics;
import java.util.Map;
import org.come.bean.ConfigureBean;
import java.awt.Color;
import java.awt.Font;
import java.awt.Dimension;
import com.updateNew.MyIsif;
import java.math.BigDecimal;
import org.come.model.Configure;
import org.come.until.UserMessUntil;
import javax.swing.ImageIcon;
import com.tool.btn.CommonBtn;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class MonthlyCardJpanel extends JPanel
{
    private JLabel dayNum;
    private JLabel xy;
    private JLabel mrjl;
    private JLabel mrjl1;
    public String str;
    private CommonBtn getMonthBtn;
    private CommonBtn getWeekBtn;
    private CommonBtn receiveAwardBtn;
    private ImageIcon iconBack;
    
    public MonthlyCardJpanel() {
        this.str = "";
        ConfigureBean allConfigure = UserMessUntil.getConfigureBean();
        Map<BigDecimal, Configure> item = allConfigure.getAllConfigure();
        Configure configure = (Configure)item.get(new BigDecimal(5));
        this.str = "<html>" + configure.getZqsld() + "</html>";
        if (MyIsif.getStyle().equals("水墨")) {
            this.setPreferredSize(new Dimension(600, 390));
            this.setLayout(null);
            this.setOpaque(false);
            this.getDayNum();
            this.getReceiveAwardBtn();
            Font font = new Font("楷体", 1, 16);
            (this.xy = new JLabel()).setForeground(Color.black);
            this.xy.setFont(font);
            this.xy.setBounds(45, 360, 50, 50);
            this.xy.setText("仙玉");
            font = new Font("楷体", 1, 18);
            (this.mrjl = new JLabel()).setForeground(Color.black);
            this.mrjl.setFont(font);
            this.mrjl.setBounds(95, 150, 250, 250);
            this.mrjl.setText("每日福利：");
            this.add(this.mrjl);
            font = new Font("楷体", 1, 18);
            (this.mrjl1 = new JLabel(this.str)).setForeground(Color.black);
            this.mrjl1.setFont(font);
            this.mrjl1.setBounds(190, 170, 400, 250);
            this.add(this.mrjl1);
        }
        else {
            this.setPreferredSize(new Dimension(675, 420));
            this.setLayout(null);
            this.setOpaque(false);
            this.getDayNum();
            this.getReceiveAwardBtn();
            Font font = new Font("楷体", 1, 16);
            (this.xy = new JLabel()).setForeground(Color.green);
            this.xy.setFont(font);
            this.xy.setBounds(45, 360, 50, 50);
            this.xy.setText("仙玉");
            this.add(this.xy);
            font = new Font("楷体", 1, 18);
            (this.mrjl = new JLabel()).setForeground(Color.yellow);
            this.mrjl.setFont(font);
            this.mrjl.setBounds(95, 130, 250, 250);
            this.mrjl.setText("每日福利：");
            this.add(this.mrjl);
            font = new Font("楷体", 1, 18);
            (this.mrjl1 = new JLabel(this.str)).setForeground(Color.green);
            this.mrjl1.setFont(font);
            this.mrjl1.setBounds(190, 150, 400, 250);
            this.add(this.mrjl1);
        }
    }
    
    public void paintComponent(Graphics g) {
        super.paintComponents(g);
        if (MyIsif.getStyle().equals("水墨")) {
            if (this.iconBack == null) {
                this.iconBack = new ImageIcon("inkImg/background/ykmb.png");
            }
            g.drawImage(this.iconBack.getImage(), 53, 5, 600, 395, this);
        }
        else {
            if (this.iconBack == null) {
                this.iconBack = new ImageIcon("img/xy2uiimg/mall-month-card-bg.png");
            }
            g.drawImage(this.iconBack.getImage(), 35, 10, 660, 400, this);
        }
    }
    
    public void changeTime() {
        Limit limit = TimeLimit.getLimits().getLimit("VIP");
        if (limit != null) {
            int residueDay = TimerUtil.residueDay(limit.getTime());
            if (residueDay >= 0) {
                this.dayNum.setText(residueDay + "");
                return;
            }
        }
        this.dayNum.setText("--");
    }
    
    public JLabel getDayNum() {
        if (MyIsif.getStyle().equals("水墨")) {
            if (this.dayNum == null) {
                (this.dayNum = new JLabel("30", 0)).setFont(UIUtils.TEXT_FONT3);
                this.dayNum.setForeground(Color.white);
                this.dayNum.setBounds(548, 370, 50, 16);
                this.dayNum.setOpaque(false);
                this.add(this.dayNum);
            }
        }
        else if (this.dayNum == null) {
            (this.dayNum = new JLabel("30", 0)).setFont(UIUtils.TEXT_FONT82);
            this.dayNum.setForeground(Color.white);
            this.dayNum.setBounds(495, 375, 200, 16);
            this.dayNum.setOpaque(false);
            this.add(this.dayNum);
        }
        return this.dayNum;
    }
    
    public void setDayNum(JLabel dayNum) {
        this.dayNum = dayNum;
    }
    
    public ImageIcon getIconBack() {
        return this.iconBack;
    }
    
    public void setIconBack(ImageIcon iconBack) {
        this.iconBack = iconBack;
    }
    
    public CommonBtn getReceiveAwardBtn() {
        if (MyIsif.getStyle().equals("水墨")) {
            if (this.receiveAwardBtn == null) {
                (this.receiveAwardBtn = new CommonBtn("inkImg/button/18.png", 1, "领取奖励", 20, this, null)).setBounds(304, 365, 100, 26);
                this.add(this.receiveAwardBtn);
            }
        }
        else if (this.receiveAwardBtn == null) {
            (this.receiveAwardBtn = new CommonBtn("inkImg/hongmu/1_png.button.btn_xy.png", 1, "领取奖励", 20, this)).setBounds(304, 365, 100, 26);
            this.add(this.receiveAwardBtn);
        }
        return this.receiveAwardBtn;
    }
    
    public void setReceiveAwardBtn(CommonBtn receiveAwardBtn) {
        this.receiveAwardBtn = receiveAwardBtn;
    }
}
