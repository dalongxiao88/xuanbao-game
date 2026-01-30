package org.come.Jpanel;

import java.awt.event.ActionEvent;
import org.come.until.CutButtonImage;
import java.awt.Graphics;
import org.come.until.FormsManagement;
import org.come.Frame.JieGuaJframe;
import java.awt.event.MouseEvent;
import com.tool.tcpimg.UIUtils;
import java.awt.Dimension;
import javax.swing.ImageIcon;
import javax.swing.Timer;
import com.tool.btn.FormsOnOffBtn;
import com.tool.btn.GuaBtn;
import com.tool.tcpimg.RichLabel;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class JieGuaJpanel extends JPanel
{
    public static int MAX;
    public static int[] moneys;
    private int yangIndex;
    private int yinIndex;
    private JLabel textLab;
    private RichLabel richLabel;
    private GuaBtn[] guaBtns;
    private FormsOnOffBtn offBtn;
    private Timer timer;
    private static String instructions;
    private long yaTime;
    private int state;
    private int count;
    private boolean isRun;
    private final int num = 6;
    private ImageIcon icon;
    
    public JieGuaJpanel() {
        this.guaBtns = new GuaBtn[8];
        this.state = 0;
        this.isRun = false;
        this.setPreferredSize(new Dimension(641, 464));
        this.setLayout(null);
        this.setBackground(UIUtils.Color_BACK);
        (this.offBtn = new FormsOnOffBtn("img/jiegua/gb.png", 1, 712) {
            @Override
            public void nochoose(MouseEvent e) {
                JieGuaJframe.close();
            }
        }).setBounds(541, 235, 17, 56);
        this.add(this.offBtn);
        (this.textLab = new JLabel()).setBounds(468, 370, 70, 70);
        this.add(this.textLab);
        this.add(this.richLabel = new RichLabel("", UIUtils.TEXT_FONT1));
        for (int i = 0; i < this.guaBtns.length; ++i) {
            this.guaBtns[i] = new GuaBtn("img/jiegua/jg" + (i + 1) + ".png", 1, i, this);
            switch (i) {
                case 0: {
                    this.guaBtns[i].setBounds(242, 8, 137, 65);
                    break;
                }
                case 1: {
                    this.guaBtns[i].setBounds(382, 28, 115, 113);
                    break;
                }
                case 2: {
                    this.guaBtns[i].setBounds(464, 142, 65, 137);
                    break;
                }
                case 3: {
                    this.guaBtns[i].setBounds(392, 280, 114, 115);
                    break;
                }
                case 4: {
                    this.guaBtns[i].setBounds(257, 362, 137, 61);
                    break;
                }
                case 5: {
                    this.guaBtns[i].setBounds(142, 290, 114, 116);
                    break;
                }
                case 6: {
                    this.guaBtns[i].setBounds(110, 160, 65, 140);
                    break;
                }
                case 7: {
                    this.guaBtns[i].setBounds(133, 40, 114, 113);
                    break;
                }
            }
            this.add(this.guaBtns[i]);
        }
    }
    
    public void setState(String info) {
        String[] val = info.split("=");
        int state = Integer.parseInt(val[0]);
        switch (state) {
            case 0: {
                this.state = state;
                this.stop(val[1]);
                this.richLabel.setTextSize((val.length > 2) ? val[2] : "", 230);
                this.richLabel.setBounds(210, 185, 230, 300);
                this.richLabel.setVisible(true);
                break;
            }
            case 1: {
                this.state = state;
                this.richLabel.setTextSize(JieGuaJpanel.instructions, 230);
                this.richLabel.setBounds(210, 240, 230, 300);
                this.richLabel.setVisible(true);
                this.textLab.setVisible(false);
                this.yaTime = Long.parseLong(val[1]);
                this.setMoneys(val[2]);
                this.updateState(true);
                break;
            }
            case 2: {
                this.state = state;
                FormsManagement.HideForm(713);
                this.start(Long.parseLong(val[1]) - System.currentTimeMillis());
                this.richLabel.setVisible(false);
                this.textLab.setVisible(true);
                break;
            }
            case 3: {
                this.setMoneys(val[1]);
                break;
            }
        }
    }
    
    public void start(long time) {
        if (!this.isRun) {
            this.isRun = true;
            this.updateState(false);
            this.update(0, 4);
            this.count = (int)(time / 30L);
            (this.timer = new Timer(30, e/* java.awt.event.ActionEvent, */ -> {
                if (this.count % 6 == 0) {
                    this.update(this.yangIndex + 1, this.yinIndex - 1);
                }
                if (this.count > this.getCount(10, 0) || this.count < this.getCount(1, 0)) {
                    this.count -= this.compute(1);
                }
                else if (this.count > this.getCount(8, 0) || this.count < this.getCount(2, 0)) {
                    this.count -= this.compute(2);
                }
                else {
                    this.count -= this.compute(6);
                }
                return;
            })).start();
        }
    }
    
    private void stop(String info) {
        this.isRun = false;
        String[] val = info.split("_");
        this.update(Integer.parseInt(val[0]), Integer.parseInt(val[1]));
        this.textLab.setIcon(new ImageIcon("img/jiegua/82" + (this.yangIndex + 1) + ".png"));
        if (this.timer != null) {
            this.timer.stop();
        }
    }
    
    private int getCount(int num, int count) {
        int n = num * 8 + count;
        this.getClass();
        return n * 6;
    }
    
    private int compute(int i) {
        if (i > 1) {
            int count = this.count + i - 1;
            if (count != this.count && count % 6 == 0) {
                return this.compute(i - 1);
            }
        }
        return i;
    }
    
    private void update(int yangIndex, int yinIndex) {
        if (yangIndex > 7) {
            yangIndex = 0;
        }
        if (yinIndex < 0) {
            yinIndex = 7;
        }
        this.yangIndex = yangIndex;
        this.yinIndex = yinIndex;
        for (int i = 0; i < this.guaBtns.length; ++i) {
            this.guaBtns[i].update(yangIndex, yinIndex, true);
        }
        this.textLab.setIcon(new ImageIcon("img/jiegua/81" + (yangIndex + 1) + ".png"));
    }
    
    private void updateState(boolean is) {
        for (int i = 0; i < this.guaBtns.length; ++i) {
            this.guaBtns[i].changeState(is);
        }
    }
    
    private void setMoneys(String msg) {
        String[] vs = msg.split("\\|");
        for (int i = 0; i < JieGuaJpanel.moneys.length; ++i) {
            JieGuaJpanel.moneys[i] = Integer.parseInt(vs[i]);
            if (JieGuaJpanel.moneys[i] >= JieGuaJpanel.MAX) {
                this.guaBtns[i].changeState(false);
                this.guaBtns[i].update();
            }
        }
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (this.icon == null) {
            this.icon = CutButtonImage.getImage("img/jiegua/yyjg.png", -1, -1);
        }
        g.drawImage(this.icon.getImage(), 0, 0, this);
        if (this.state == 0 || this.state == 1) {
            if (this.state == 0) {
                g.setFont(UIUtils.TEXT_BOLD_FONT);
                g.setColor(UIUtils.COLOR_HURTY1);
                g.drawString("开卦卦位", 284, 110);
                ImageIcon imageIcon = new ImageIcon("img/jiegua/82" + (this.yangIndex + 1) + ".png");
                g.drawImage(imageIcon.getImage(), 280, 110, this);
                g.setFont(UIUtils.TEXT_FONT1);
                g.setColor(UIUtils.COLOR_HURTY1);
                g.drawString("恭喜以下玩家获得奖励：", 210, 180);
            }
            else if (this.state == 1) {
                g.setFont(UIUtils.TEXT_FONT1);
                g.setColor(UIUtils.COLOR_HURTY1);
                g.drawString("压注倒计时", 280, 110);
                if (this.yaTime >= System.currentTimeMillis()) {
                    String numStr = String.valueOf((this.yaTime - System.currentTimeMillis()) / 1000L);
                    char[] charArray = numStr.toCharArray();
                    int x = 314;
                    x -= charArray.length * 42 / 2;
                    for (int i = 0; i < charArray.length; ++i) {
                        ImageIcon imageIcon2 = new ImageIcon("img/jiegua/" + charArray[i] + ".png");
                        g.drawImage(imageIcon2.getImage(), x, 115, this);
                        x += 42;
                    }
                }
                g.setFont(UIUtils.TEXT_FONT1);
                g.setColor(UIUtils.COLOR_HURTY1);
                g.drawString("玩法说明：", 210, 235);
            }
        }
    }
    
    static {
        JieGuaJpanel.MAX = 200000;
        JieGuaJpanel.moneys = new int[8];
        JieGuaJpanel.instructions = "倒计时期间在各个卦位注入灵力，押中主卦每#R1#n点灵力可以获得#R50000#n两金钱奖励，主卦客卦重合，奖励翻倍";
    }
}
