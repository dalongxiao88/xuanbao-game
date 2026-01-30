package org.come.Jpanel;

import com.tool.role.RoleData;
import org.come.until.Util;
import java.awt.Graphics;
import org.come.entity.Goodstable;
import org.come.Frame.ZhuFrame;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import com.tool.tcpimg.UIUtils;
import java.awt.Color;
import com.updateNew.MyIsif;
import com.tool.btn.FormsOnOffBtn;
import java.awt.Dimension;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import com.tool.btn.BaptizeBtn;
import java.math.BigDecimal;
import javax.swing.JPanel;

public class SuitBaptizeJpanel extends JPanel
{
    public static boolean saveOld;
    public static BigDecimal sxlxz;
    public static BigDecimal money;
    private BaptizeBtn baptizeBtn1;
    private BaptizeBtn baptizeBtn2;
    private BaptizeBtn baptizeBtn3;
    private JLabel[] oldAttr;
    private JLabel[] newAttr;
    private JLabel labtz;
    private JLabel labSave;
    private ImageIcon icon;
    private ImageIcon icon1;
    
    public SuitBaptizeJpanel() {
        this.oldAttr = new JLabel[4];
        this.newAttr = new JLabel[4];
        this.setPreferredSize(new Dimension(456, 372));
        this.setLayout(null);
        this.setOpaque(false);
        FormsOnOffBtn offBtn = new FormsOnOffBtn("img/xy2uiimg/8_jpg.button.btn_close.jpg", 1, 74);
        offBtn.setBounds(430, 0, 23, 23);
        this.add(offBtn);
        if (!MyIsif.getStyle().equals("水墨")) {
            for (int i = 0; i < 4; ++i) {
                (this.oldAttr[i] = new JLabel("", 0)).setForeground(Color.white);
                this.oldAttr[i].setFont(UIUtils.TEXT_FONT1);
                if (i % 2 == 0) {
                    this.oldAttr[i].setBounds(29, 252 + i / 2 * 27, 129, 21);
                }
                else {
                    this.oldAttr[i].setBounds(164, 252 + i / 2 * 27, 50, 21);
                }
                this.add(this.oldAttr[i]);
            }
            for (int i = 0; i < 4; ++i) {
                (this.newAttr[i] = new JLabel("", 0)).setForeground(Color.white);
                this.newAttr[i].setFont(UIUtils.TEXT_FONT1);
                if (i % 2 == 0) {
                    this.newAttr[i].setBounds(239, 252 + i / 2 * 27, 129, 21);
                }
                else {
                    this.newAttr[i].setBounds(374, 252 + i / 2 * 27, 50, 21);
                }
                this.add(this.newAttr[i]);
            }
            (this.labtz = new JLabel("", 0)).setBounds(52, 84, 53, 51);
            this.labtz.addMouseListener(new MouseListener() {
                @Override
                public void mouseReleased(MouseEvent e) {
                }
                
                @Override
                public void mousePressed(MouseEvent e) {
                }
                
                @Override
                public void mouseExited(MouseEvent e) {
                    ZhuFrame.getZhuJpanel().cleargoodtext();
                }
                
                @Override
                public void mouseEntered(MouseEvent e) {
                    Goodstable goodstable = WashJpanel.getGoodstableBean().getGoodstable();
                    if (goodstable == null) {
                        return;
                    }
                    ZhuFrame.getZhuJpanel().creatgoodtext(goodstable);
                }
                
                @Override
                public void mouseClicked(MouseEvent e) {
                }
            });
            this.add(this.labtz);
            (this.labSave = new JLabel()).setBounds(46, 148, 17, 17);
            this.labSave.addMouseListener(new MouseListener() {
                @Override
                public void mouseReleased(MouseEvent e) {
                }
                
                @Override
                public void mousePressed(MouseEvent e) {
                }
                
                @Override
                public void mouseExited(MouseEvent e) {
                }
                
                @Override
                public void mouseEntered(MouseEvent e) {
                }
                
                @Override
                public void mouseClicked(MouseEvent e) {
                    if (!(SuitBaptizeJpanel.saveOld = !SuitBaptizeJpanel.saveOld)) {
                        SuitBaptizeJpanel.money = new BigDecimal(800000);
                        SuitBaptizeJpanel.this.baptizeBtn2.setBtn(-1);
                        SuitBaptizeJpanel.this.baptizeBtn3.setBtn(-1);
                    }
                    else {
                        SuitBaptizeJpanel.money = new BigDecimal(1000000);
                        SuitBaptizeJpanel.this.baptizeBtn2.setBtn(1);
                        SuitBaptizeJpanel.this.baptizeBtn3.setBtn(1);
                    }
                }
            });
            this.add(this.labSave);
            (this.baptizeBtn1 = new BaptizeBtn("inkImg/hongmu/1_png.button.btn_xy.png", 1, UIUtils.COLOR_BTNPUTONG, UIUtils.TEXT_HY88, "开始洗炼", 1, this)).setBounds(315, 170, 85, 24);
            this.add(this.baptizeBtn1);
            (this.baptizeBtn2 = new BaptizeBtn("inkImg/hongmu/1_png.button.btn_xy.png", -1, UIUtils.COLOR_BTNPUTONG, UIUtils.TEXT_HY88, "保留属性", 2, this)).setBounds(72, 320, 85, 24);
            this.add(this.baptizeBtn2);
            (this.baptizeBtn3 = new BaptizeBtn("inkImg/hongmu/1_png.button.btn_xy.png", -1, UIUtils.COLOR_BTNPUTONG, UIUtils.TEXT_HY88, "替换属性", 3, this)).setBounds(303, 320, 85, 24);
            this.add(this.baptizeBtn3);
        }
        else {
            for (int i = 0; i < 4; ++i) {
                (this.oldAttr[i] = new JLabel("", 0)).setForeground(Color.white);
                this.oldAttr[i].setFont(UIUtils.TEXT_FONT1);
                if (i % 2 == 0) {
                    this.oldAttr[i].setBounds(29, 262 + i / 2 * 27, 129, 21);
                }
                else {
                    this.oldAttr[i].setBounds(173, 262 + i / 2 * 27, 50, 21);
                }
                this.add(this.oldAttr[i]);
            }
            for (int i = 0; i < 4; ++i) {
                (this.newAttr[i] = new JLabel("", 0)).setForeground(Color.white);
                this.newAttr[i].setFont(UIUtils.TEXT_FONT1);
                if (i % 2 == 0) {
                    this.newAttr[i].setBounds(239, 262 + i / 2 * 27, 129, 21);
                }
                else {
                    this.newAttr[i].setBounds(380, 262 + i / 2 * 27, 50, 21);
                }
                this.add(this.newAttr[i]);
            }
            (this.labtz = new JLabel("", 0)).setBounds(65, 80, 53, 51);
            this.labtz.addMouseListener(new MouseListener() {
                @Override
                public void mouseReleased(MouseEvent e) {
                }
                
                @Override
                public void mousePressed(MouseEvent e) {
                }
                
                @Override
                public void mouseExited(MouseEvent e) {
                    ZhuFrame.getZhuJpanel().cleargoodtext();
                }
                
                @Override
                public void mouseEntered(MouseEvent e) {
                    Goodstable goodstable = WashJpanel.getGoodstableBean().getGoodstable();
                    if (goodstable == null) {
                        return;
                    }
                    ZhuFrame.getZhuJpanel().creatgoodtext(goodstable);
                }
                
                @Override
                public void mouseClicked(MouseEvent e) {
                }
            });
            this.add(this.labtz);
            (this.labSave = new JLabel()).setBounds(63, 145, 17, 17);
            this.labSave.addMouseListener(new MouseListener() {
                @Override
                public void mouseReleased(MouseEvent e) {
                }
                
                @Override
                public void mousePressed(MouseEvent e) {
                }
                
                @Override
                public void mouseExited(MouseEvent e) {
                }
                
                @Override
                public void mouseEntered(MouseEvent e) {
                }
                
                @Override
                public void mouseClicked(MouseEvent e) {
                    if (!(SuitBaptizeJpanel.saveOld = !SuitBaptizeJpanel.saveOld)) {
                        SuitBaptizeJpanel.money = new BigDecimal(800000);
                        SuitBaptizeJpanel.this.baptizeBtn2.setBtn(-1);
                        SuitBaptizeJpanel.this.baptizeBtn3.setBtn(-1);
                    }
                    else {
                        SuitBaptizeJpanel.money = new BigDecimal(1000000);
                        SuitBaptizeJpanel.this.baptizeBtn2.setBtn(1);
                        SuitBaptizeJpanel.this.baptizeBtn3.setBtn(1);
                    }
                }
            });
            this.add(this.labSave);
            (this.baptizeBtn1 = new BaptizeBtn("inkImg/button1/B21.png", 1, UIUtils.COLOR_BLACK, UIUtils.TEXT_HY88, "开始洗炼", 1, this)).setBounds(315, 170, 99, 24);
            this.add(this.baptizeBtn1);
            (this.baptizeBtn2 = new BaptizeBtn("inkImg/button1/B21.png", -1, UIUtils.COLOR_BLACK, UIUtils.TEXT_HY88, "保留属性", 2, this)).setBounds(72, 320, 85, 24);
            this.add(this.baptizeBtn2);
            (this.baptizeBtn3 = new BaptizeBtn("inkImg/button1/B21.png", -1, UIUtils.COLOR_BLACK, UIUtils.TEXT_HY88, "替换属性", 3, this)).setBounds(303, 320, 85, 24);
            this.add(this.baptizeBtn3);
        }
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (MyIsif.getStyle().equals("水墨")) {
            if (this.icon == null) {
                this.icon = new ImageIcon("inkImg/background/S68.png");
            }
            if (this.icon1 == null) {
                this.icon1 = new ImageIcon("img/button/13.png");
            }
            g.drawImage(this.icon.getImage(), 0, 0, 456, 372, this);
            if (SuitBaptizeJpanel.saveOld) {
                g.drawImage(this.icon1.getImage(), 65, 147, 15, 15, this);
            }
            Util.drawMoney(g, 315, 93);
            if (SuitBaptizeJpanel.money != null) {
                Util.drawPrice(g, SuitBaptizeJpanel.money, 233, 93);
            }
            g.setColor(Color.white);
            if (RoleData.getRoleData().getLoginResult().getScoretype("灵修值") != null) {
                g.drawString(RoleData.getRoleData().getLoginResult().getScoretype("灵修值") + "", 315, 125);
            }
            if (SuitBaptizeJpanel.sxlxz != null) {
                g.drawString(SuitBaptizeJpanel.sxlxz + "", 235, 125);
            }
        }
        else {
            if (this.icon == null) {
                this.icon = new ImageIcon("img/xy2uiimg/suitbaptize.png");
            }
            if (this.icon1 == null) {
                this.icon1 = new ImageIcon("img/xy2uiimg/showjadesuit.png");
            }
            g.drawImage(this.icon.getImage(), 0, 0, 456, 372, this);
            if (SuitBaptizeJpanel.saveOld) {
                g.drawImage(this.icon1.getImage(), 47, 149, 15, 15, this);
            }
            Util.drawMoney(g, 310, 101);
            if (SuitBaptizeJpanel.money != null) {
                Util.drawPrice(g, SuitBaptizeJpanel.money, 223, 101);
            }
            g.setColor(Color.white);
            if (RoleData.getRoleData().getLoginResult().getScoretype("灵修值") != null) {
                g.drawString(RoleData.getRoleData().getLoginResult().getScoretype("灵修值") + "", 310, 131);
            }
            if (SuitBaptizeJpanel.sxlxz != null) {
                g.drawString(SuitBaptizeJpanel.sxlxz + "", 244, 131);
            }
        }
    }
    
    public static BigDecimal getMoney() {
        return SuitBaptizeJpanel.money;
    }
    
    public static void setMoney(BigDecimal money) {
        SuitBaptizeJpanel.money = money;
    }
    
    public BaptizeBtn getBaptizeBtn1() {
        return this.baptizeBtn1;
    }
    
    public void setBaptizeBtn1(BaptizeBtn baptizeBtn1) {
        this.baptizeBtn1 = baptizeBtn1;
    }
    
    public BaptizeBtn getBaptizeBtn2() {
        return this.baptizeBtn2;
    }
    
    public static boolean isSaveOld() {
        return SuitBaptizeJpanel.saveOld;
    }
    
    public static void setSaveOld(boolean saveOld) {
        SuitBaptizeJpanel.saveOld = saveOld;
    }
    
    public static BigDecimal getSxlxz() {
        return SuitBaptizeJpanel.sxlxz;
    }
    
    public static void setSxlxz(BigDecimal sxlxz) {
        SuitBaptizeJpanel.sxlxz = sxlxz;
    }
    
    public ImageIcon getIcon() {
        return this.icon;
    }
    
    public void setIcon(ImageIcon icon) {
        this.icon = icon;
    }
    
    public ImageIcon getIcon1() {
        return this.icon1;
    }
    
    public void setIcon1(ImageIcon icon1) {
        this.icon1 = icon1;
    }
    
    public void setBaptizeBtn2(BaptizeBtn baptizeBtn2) {
        this.baptizeBtn2 = baptizeBtn2;
    }
    
    public BaptizeBtn getBaptizeBtn3() {
        return this.baptizeBtn3;
    }
    
    public void setBaptizeBtn3(BaptizeBtn baptizeBtn3) {
        this.baptizeBtn3 = baptizeBtn3;
    }
    
    public JLabel[] getOldAttr() {
        return this.oldAttr;
    }
    
    public void setOldAttr(JLabel[] oldAttr) {
        this.oldAttr = oldAttr;
    }
    
    public JLabel[] getNewAttr() {
        return this.newAttr;
    }
    
    public void setNewAttr(JLabel[] newAttr) {
        this.newAttr = newAttr;
    }
    
    public JLabel getLabtz() {
        return this.labtz;
    }
    
    public void setLabtz(JLabel labtz) {
        this.labtz = labtz;
    }
    
    public JLabel getLabSave() {
        return this.labSave;
    }
    
    public void setLabSave(JLabel labSave) {
        this.labSave = labSave;
    }
    
    static {
        SuitBaptizeJpanel.saveOld = true;
        SuitBaptizeJpanel.sxlxz = new BigDecimal(30);
        SuitBaptizeJpanel.money = new BigDecimal(1000000);
    }
}
