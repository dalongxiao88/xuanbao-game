package org.come.Jpanel;

import com.tool.tcp.SpriteFactory;
import com.tool.image.ImageMixDeal;
import org.come.Frame.ZhuFrame;
import java.util.ArrayList;
import com.updateNew.MyIsif;
import java.awt.Graphics;
import org.come.mouslisten.QuackGameMouslisten;
import java.awt.Color;
import com.tool.btn.FormsOnOffBtn;
import com.tool.tcpimg.UIUtils;
import java.awt.Dimension;
import org.come.until.CutButtonImage;
import com.tool.tcp.Sprite;
import org.come.bean.PathPoint;
import java.util.List;
import java.math.BigDecimal;
import javax.swing.ImageIcon;
import com.tool.btn.QuackGameBtn;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class QuackGameJpanel extends JPanel
{
    public int kyNum;// 剩余的刮刮乐卡数量
    private JLabel[] labSysNum;// 能中奖的召唤兽图片
    private JLabel[] labOpenNum;// 本次抽奖的召唤兽图片
    private JLabel[] labBorder;// 边框图片
    private JLabel labBs; // 倍数图片
    private QuackGameBtn gameBtn;// 一键召唤按钮
    private JLabel labWh;
    private ImageIcon icon2;
    public String[] sysNum;
    public String[] sysNum2;
    public String[] drawNum;
    public BigDecimal money;
    public List<PathPoint> points;
    private long time;
    private int index;
    static Sprite tcp;
    
    public QuackGameJpanel() {
        this.labSysNum = new JLabel[5];
        this.labOpenNum = new JLabel[20];
        this.labBorder = new JLabel[20];
        this.icon2 = new ImageIcon(CutButtonImage.getImage("img/xy2uiimg/101_png.xy2uiimg.pet_def.png", -1, -1).getImage().getScaledInstance(35, 35, 10));
        this.sysNum = null; // 能中奖的五个号码
        this.sysNum2 = null; // 能中奖的五个号码
        this.drawNum = null;// 抽中的号码
        this.money = null;// 中奖金额
        this.points = null;
        this.setPreferredSize(new Dimension(400, 400));
        this.setBackground(UIUtils.Color_BACK);
        this.setLayout(null);
        // 关闭按钮事件
        FormsOnOffBtn offBtn = new FormsOnOffBtn("img/xy2uiimg/8_jpg.button.btn_close.jpg", 1, 71);
        offBtn.setBounds(374, 0, 23, 23);
        this.add(offBtn);
        // 一键召唤按钮
        (this.gameBtn = new QuackGameBtn("inkImg/hongmu/1_png.button.btn_xy.png", 1, UIUtils.COLOR_BTNPUTONG, UIUtils.TEXT_HY88, "一键召唤", this)).setBounds(168, 360, 80, 26);
        this.add(this.gameBtn);
        // 倍数图片
        (this.labBs = new JLabel("", 0)).setBounds(313, 141, 35, 35);
        this.labBs.setForeground(Color.WHITE);
        this.labBs.setFont(UIUtils.nameFont);
        this.labBs.setIcon(this.icon2);
        this.labBs.setIcon(CutButtonImage.getImage("img/xy2uiimg/101_png.xy2uiimg.pet_def.png", -1, -1));
        this.add(this.labBs);
        // 问号
        (this.labWh = new JLabel()).setBounds(331, 110, 18, 18);
        this.add(this.labWh);
        // 能中奖的召唤兽图片
        for (int i = 0; i < 5; ++i) {
            (this.labSysNum[i] = new JLabel()).setBounds(48 + i * 66, 50, 35, 35);
            this.add(this.labSysNum[i]);
        }
        // 本次抽奖的召唤兽图片
        for (int i = 0; i < 20; ++i) {
            int row = i % 5;
            int col = i / 5;
            (this.labOpenNum[i] = new JLabel()).setIcon(this.icon2);
            this.labOpenNum[i].setBounds(40 + row * 51, 140 + col * 51, 35, 35);
            this.add(this.labOpenNum[i]);
            (this.labBorder[i] = new JLabel("", 0)).setBounds(34 + row * 51, 133 + col * 51, 48, 48);
            this.labBorder[i].addMouseListener(new QuackGameMouslisten(i, this));
            this.add(this.labBorder[i]);
        }
    }
    /** 重置 */
    public void reset() {
        for (int i = 0; i < this.labOpenNum.length; ++i) {
            this.labOpenNum[i].setIcon(this.icon2);
        }
        this.points = null;
        this.labBs.setIcon(this.icon2);
    }
    /** 重置守护 */
    public void resetGuard(String[] v) {
        this.sysNum = v;
        for (int i = 0; i < v.length; ++i) {
            ImageIcon icon = CutButtonImage.getImage("img/head/p" + v[i] + ".png", -1, -1);
            this.labSysNum[i].setIcon(icon);
        }
    }
    /** 开奖 */
    public void drawLottery(String[] v, BigDecimal moeny) {
        this.drawNum = v;
        this.money = moeny;
        this.time = 0L;
        this.index = 0;
        if (this.points != null) {
            this.points.clear();
        }
        this.sysNum2 = new String[this.sysNum.length];
        System.arraycopy(this.sysNum, 0, this.sysNum2, 0, 5);
    }
    private ImageIcon icon = CutButtonImage.getImage("inkImg/background1/B321.png", -1, -1);
    private ImageIcon icon1 = new ImageIcon("img/xy2uiimg/quackgame.png");
    //通灵宝券界面
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        String typr=MyIsif.getStyle();
        if (typr.equals("水墨")) {
            g.drawImage(this.icon.getImage(), 0, 0, 400, 400, this);
        } else {
            // 道具栏
            g.drawImage(this.icon1.getImage(), 0, 0, 400, 400, this);
        }
        // 画剩余数量
        g.setColor(Color.white);
        g.setFont(UIUtils.TEXT_FONT1);
        g.drawString(this.kyNum + "", 108, 377);
        if (this.drawNum != null) {
            ++this.time;
            if (this.time % 10L == 0L) {
                ImageIcon icon = null;
                if (!this.drawNum[this.index].equals("0")) {
                    icon = CutButtonImage.getImage("img/head/p" + this.drawNum[this.index] + ".png", -1, -1);
                }
                if (this.index == 20) {
                    this.labBs.setIcon(icon);
                }
                else {
                    for (int i = 0; i < this.sysNum2.length; ++i) {
                        if (this.sysNum2[i] != null && this.sysNum2[i].equals(this.drawNum[this.index])) {
                            this.sysNum2[i] = null;
                            if (this.points == null) {
                                this.points = new ArrayList<>();
                            }
                            int row = this.index % 5;
                            int col = this.index / 5;
                            this.points.add(new PathPoint(34 + row * 51, 133 + col * 51));
                        }
                    }
                    this.labOpenNum[this.index].setIcon(icon);
                }
                ++this.index;
                if (this.index > 20) {
                    this.gameBtn.setBtn(1);
                    this.gameBtn.setText("再来一次");
                    if (this.drawNum[this.drawNum.length - 1].equals("400151")) {
                        ZhuFrame.getZhuJpanel().addPrompt("你遇到了金灵鼠， 硕鼠硕鼠，勿食我黍。本次颗粒无收！！");
                    }
                    else if (this.money != null && this.money.intValue() != 0) {
                        ZhuFrame.getZhuJpanel().addPrompt("获得了" + this.money + "银两");
                    }
                    this.drawNum = null;
                }
            }
        }
        if (this.points != null) {
            QuackGameJpanel.tcp.updateToTime(ImageMixDeal.userimg.getTime(), 0);
            for (int j = 0; j < this.points.size(); ++j) {
                QuackGameJpanel.tcp.draw(g, ((PathPoint)this.points.get(j)).getX(), ((PathPoint)this.points.get(j)).getY());
            }
        }
    }
    
    public JLabel[] getLabSysNum() {
        return this.labSysNum;
    }
    
    public void setLabSysNum(JLabel[] labSysNum) {
        this.labSysNum = labSysNum;
    }
    
    public JLabel[] getLabOpenNum() {
        return this.labOpenNum;
    }
    
    public void setLabOpenNum(JLabel[] labOpenNum) {
        this.labOpenNum = labOpenNum;
    }
    
    public JLabel[] getLabBorder() {
        return this.labBorder;
    }
    
    public void setLabBorder(JLabel[] labBorder) {
        this.labBorder = labBorder;
    }
    
    public JLabel getLabBs() {
        return this.labBs;
    }
    
    public void setLabBs(JLabel labBs) {
        this.labBs = labBs;
    }
    
    public JLabel getLabWh() {
        return this.labWh;
    }
    
    public void setLabWh(JLabel labWh) {
        this.labWh = labWh;
    }
    
    public QuackGameBtn getGameBtn() {
        return this.gameBtn;
    }
    
    public void setGameBtn(QuackGameBtn gameBtn) {
        this.gameBtn = gameBtn;
    }
    
    public ImageIcon getIcon2() {
        return this.icon2;
    }
    
    public void setIcon2(ImageIcon icon2) {
        this.icon2 = icon2;
    }
    
    static {
        QuackGameJpanel.tcp = SpriteFactory.VloadSprite("resource/mouse/flicker.tcp", null);
    }
}
