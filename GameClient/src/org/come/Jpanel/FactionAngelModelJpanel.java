package org.come.Jpanel;

import com.tool.tcpimg.UIUtils;
import java.awt.Graphics;
import org.come.Frame.FactionAngelJframe;
import java.awt.Color;
import org.come.until.CutButtonImage;
import java.awt.Dimension;
import javax.swing.ImageIcon;
import com.tool.btn.FactionBtn;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class FactionAngelModelJpanel extends JPanel
{
    private JLabel labImg;
    private JLabel labName;
    private JLabel labDegree;
    private JLabel labLvl;
    private FactionBtn btnLeft;
    private FactionBtn btnRight;
    private int lvlNow;
    private int lvlChange;
    private ImageIcon icon;
    
    public FactionAngelModelJpanel() {
        this.lvlNow = 0;
        this.lvlChange = 0;
        this.setPreferredSize(new Dimension(324, 34));
        this.setOpaque(false);
        this.setLayout(null);
        this.getLabDegree();
        this.getLabImg();
        this.getLabLvl();
        this.getLabName();
        this.getBtnLeft();
        this.getBtnRight();
    }
    
    public void showModel(int num, String[] values, FactionAngelJpanel factionAngelJpanel) {
        int skillNum = 0;
        if (num == 0) {
            this.labName.setText("抗昏睡");
            this.labLvl.setText("0/20");
            this.labDegree.setText("0.0%");
            skillNum = 1015;
        }
        else if (num == 1) {
            this.labName.setText("抗混乱");
            this.labLvl.setText("0/20");
            this.labDegree.setText("0.0%");
            skillNum = 1005;
        }
        else if (num == 2) {
            this.labName.setText("抗封印");
            this.labLvl.setText("0/20");
            this.labDegree.setText("0.0%");
            skillNum = 1010;
        }
        else if (num == 3) {
            this.labName.setText("抗中毒");
            this.labLvl.setText("0/20");
            this.labDegree.setText("0.0%");
            skillNum = 1020;
        }
        else if (num == 4) {
            this.labName.setText("抗火");
            this.labLvl.setText("0/20");
            this.labDegree.setText("0.0%");
            skillNum = 1060;
        }
        else if (num == 5) {
            this.labName.setText("抗雷");
            this.labLvl.setText("0/20");
            this.labDegree.setText("0.0%");
            skillNum = 1050;
        }
        else if (num == 6) {
            this.labName.setText("抗水");
            this.labLvl.setText("0/20");
            this.labDegree.setText("0.0%");
            skillNum = 1055;
        }
        else if (num == 7) {
            this.labName.setText("抗风");
            this.labLvl.setText("0/20");
            this.labDegree.setText("0.0%");
            skillNum = 1045;
        }
        else if (num == 8) {
            this.labName.setText("抗震慑");
            this.labLvl.setText("0/20");
            this.labDegree.setText("0.0%");
            skillNum = 1025;
        }
        else if (num == 9) {
            this.labName.setText("抗物理");
            this.labLvl.setText("0/20");
            this.labDegree.setText("0.0%");
            skillNum = 1030;
        }
        else if (num == 10) {
            this.labName.setText("抗鬼火");
            this.labLvl.setText("0/20");
            this.labDegree.setText("0.0%");
            skillNum = 1065;
        }
        else if (num == 11) {
            this.labName.setText("抗三尸虫");
            this.labLvl.setText("0/20");
            this.labDegree.setText("0.0%");
            skillNum = 1070;
        }
        else if (num == 12) {
            this.labName.setText("抗遗忘");
            this.labLvl.setText("0/20");
            this.labDegree.setText("0.0%");
            skillNum = 1075;
        }
        else if (num == 13) {
            this.labName.setText("物理躲闪");
            this.labLvl.setText("0/30");
            this.labDegree.setText("0.0%");
            skillNum = 1030;
        }
        else if (num == 14) {
            this.labName.setText("震慑躲闪");
            this.labLvl.setText("0/30");
            this.labDegree.setText("0.0%");
            skillNum = 1025;
        }
        else if (num == 15) {
            this.labName.setText("火法躲闪");
            this.labLvl.setText("0/30");
            this.labDegree.setText("0.0%");
            skillNum = 1060;
        }
        else if (num == 16) {
            this.labName.setText("雷法躲闪");
            this.labLvl.setText("0/30");
            this.labDegree.setText("0.0%");
            skillNum = 1050;
        }
        else if (num == 17) {
            this.labName.setText("水法躲闪");
            this.labLvl.setText("0/30");
            this.labDegree.setText("0.0%");
            skillNum = 1055;
        }
        else if (num == 18) {
            this.labName.setText("风法躲闪");
            this.labLvl.setText("0/30");
            this.labDegree.setText("0.0%");
            skillNum = 1045;
        }
        else if (num == 19) {
            this.labName.setText("毒法躲闪");
            this.labLvl.setText("0/30");
            this.labDegree.setText("0.0%");
            skillNum = 1020;
        }
        else if (num == 20) {
            this.labName.setText("封印躲闪");
            this.labLvl.setText("0/30");
            this.labDegree.setText("0.0%");
            skillNum = 1010;
        }
        else if (num == 21) {
            this.labName.setText("昏睡躲闪");
            this.labLvl.setText("0/30");
            this.labDegree.setText("0.0%");
            skillNum = 1015;
        }
        else if (num == 22) {
            this.labName.setText("混乱躲闪");
            this.labLvl.setText("0/30");
            this.labDegree.setText("0.0%");
            skillNum = 1005;
        }
        else if (num == 23) {
            this.labName.setText("遗忘躲闪");
            this.labLvl.setText("0/30");
            this.labDegree.setText("0.0%");
            skillNum = 1075;
        }
        else if (num == 24) {
            this.labName.setText("鬼火躲闪");
            this.labLvl.setText("0/30");
            this.labDegree.setText("0.0%");
            skillNum = 1065;
        }
        else if (num == 25) {
            this.labName.setText("三尸虫躲闪");
            this.labLvl.setText("0/30");
            this.labDegree.setText("0.0%");
            skillNum = 1070;
        }
        else if (num == 26) {
            this.labName.setText("火法伤害减免");
            this.labLvl.setText("0/30");
            this.labDegree.setText("0.0%");
            skillNum = 1060;
        }
        else if (num == 27) {
            this.labName.setText("雷法伤害减免");
            this.labLvl.setText("0/30");
            this.labDegree.setText("0.0%");
            skillNum = 1050;
        }
        else if (num == 28) {
            this.labName.setText("风法伤害减免");
            this.labLvl.setText("0/30");
            this.labDegree.setText("0.0%");
            skillNum = 1045;
        }
        else if (num == 29) {
            this.labName.setText("水法伤害减免");
            this.labLvl.setText("0/30");
            this.labDegree.setText("0.0%");
            skillNum = 1055;
        }
        else if (num == 30) {
            this.labName.setText("鬼火伤害减免");
            this.labLvl.setText("0/30");
            this.labDegree.setText("0.0%");
            skillNum = 1065;
        }
        this.labImg.setIcon(CutButtonImage.getImage("img/masterDoor/" + skillNum + ".png", 30, 30));
        this.labDegree.setForeground(Color.white);
        this.labLvl.setForeground(Color.WHITE);
        this.judgeName(values, this.labName.getText(), factionAngelJpanel);
    }
    
    public void judgeName(String[] values, String prefix, FactionAngelJpanel factionAngelJpanel) {
        double value = 0.0;
        this.lvlNow = 0;
        this.lvlChange = this.lvlNow;
        if (values != null && !"".equals(values)) {
            int i = 0;
            while (i < values.length) {
                if (values[i].startsWith(prefix)) {
                    String[] vs = values[i].split("=");
                    if (vs[0].equals("抗三尸虫")) {
                        this.lvlNow = (int)(Double.parseDouble(vs[1]) / 100.0);
                        value = (double)this.lvlNow * 100.0;
                    }
                    else if (vs[0].endsWith("躲闪")) {
                        if (vs[0].equals("物理躲闪")) {
                            this.lvlNow = (int)(Double.parseDouble(vs[1]) / 1.0);
                            value = (double)this.lvlNow * 1.0;
                        }
                        else {
                            this.lvlNow = (int)(Double.parseDouble(vs[1]) / 0.4);
                            value = (double)this.lvlNow * 0.4;
                        }
                    }
                    else if (vs[0].endsWith("减免")) {
                        this.lvlNow = (int)(Double.parseDouble(vs[1]) / 0.4);
                        value = (double)this.lvlNow * 0.4;
                    }
                    else {
                        this.lvlNow = (int)(Double.parseDouble(vs[1]) / 1.5);
                        value = (double)this.lvlNow * 1.5;
                    }
                    this.lvlChange = this.lvlNow;
                    this.labDegree.setText(value + this.percentageIsTrue(vs[0]));
                    this.labLvl.setText(this.lvlNow + "/" + ((factionAngelJpanel.getMenuType() == 1) ? 20 : 30));
                    factionAngelJpanel.setTypeLvlResidue(factionAngelJpanel.getTypeLvlResidue() + this.lvlNow);
                    break;
                }
                else {
                    ++i;
                }
            }
        }
    }
    
    public void refreshLvlChange() {
        int menuType = FactionAngelJframe.getFactionAngelJframe().getFactionAngelJpanel().getMenuType();
        double value = 0.0;
        String text = this.labName.getText();
        if (text.equals("抗三尸虫")) {
            value = (double)this.lvlChange * 100.0;
        }
        else if (text.endsWith("躲闪")) {
            if (text.equals("物理躲闪")) {
                value = (double)this.lvlChange * 1.0;
            }
            else {
                value = (double)this.lvlChange * 0.4;
            }
        }
        else if (text.endsWith("减免")) {
            value = (double)this.lvlChange * 0.4;
        }
        else {
            value = (double)this.lvlChange * 1.5;
        }
        this.labDegree.setText(value + this.percentageIsTrue(text));
        this.labLvl.setText(this.lvlChange + "/" + ((menuType == 1) ? 20 : 30));
    }
    
    public String getLvlValue() {
        double value = 0.0;
        String text = this.labName.getText();
        if (text.equals("抗三尸虫")) {
            value = (double)this.lvlChange * 100.0;
        }
        else if (text.endsWith("躲闪")) {
            if (text.equals("物理躲闪")) {
                value = (double)this.lvlChange * 1.0;
            }
            else {
                value = (double)this.lvlChange * 0.4;
            }
        }
        else if (text.endsWith("减免")) {
            value = (double)this.lvlChange * 0.4;
        }
        else {
            value = (double)this.lvlChange * 1.5;
        }
        if (value == (double)(int)value) {
            return (int)value + "";
        }
        return value + "";
    }
    
    public String percentageIsTrue(String text) {
        if ("抗三尸虫".equals(text)) {
            return "";
        }
        return "%";
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (this.icon == null) {
            this.icon = CutButtonImage.getImage("inkImg/background/S173.png", -1, -1);
        }
        g.drawImage(this.icon.getImage(), 200, 6, 87, 22, null);
    }
    
    public JLabel getLabImg() {
        if (this.labImg == null) {
            (this.labImg = new JLabel()).setBounds(5, 2, 30, 30);
            this.add(this.labImg);
        }
        return this.labImg;
    }
    
    public void setLabImg(JLabel labImg) {
        this.labImg = labImg;
    }
    
    public JLabel getLabName() {
        if (this.labName == null) {
            (this.labName = new JLabel("无敌闪躲")).setForeground(Color.white);
            this.labName.setFont(UIUtils.TEXT_FONT1);
            this.labName.setBounds(37, 7, 90, 20);
            this.add(this.labName);
        }
        return this.labName;
    }
    
    public void setLabName(JLabel labName) {
        this.labName = labName;
    }
    
    public JLabel getLabDegree() {
        if (this.labDegree == null) {
            (this.labDegree = new JLabel("0.0%")).setForeground(Color.white);
            this.labDegree.setFont(UIUtils.TEXT_FONT1);
            this.labDegree.setBounds(140, 7, 50, 20);
            this.add(this.labDegree);
        }
        return this.labDegree;
    }
    
    public void setLabDegree(JLabel labDegree) {
        this.labDegree = labDegree;
    }
    
    public JLabel getLabLvl() {
        if (this.labLvl == null) {
            (this.labLvl = new JLabel("0/20")).setForeground(Color.white);
            this.labLvl.setFont(UIUtils.TEXT_FONT1);
            this.labLvl.setBounds(202, 6, 60, 22);
            this.add(this.labLvl);
        }
        return this.labLvl;
    }
    
    public void setLabLvl(JLabel labLvl) {
        this.labLvl = labLvl;
    }
    
    public FactionBtn getBtnLeft() {
        if (this.btnLeft == null) {
            (this.btnLeft = new FactionBtn("img/xy2uiimg/26_png.button.btn_left.png", 1, 18, this)).setBounds(255, 9, 12, 16);
            this.add(this.btnLeft);
        }
        return this.btnLeft;
    }
    
    public void setBtnLeft(FactionBtn btnLeft) {
        this.btnLeft = btnLeft;
    }
    
    public FactionBtn getBtnRight() {
        if (this.btnRight == null) {
            (this.btnRight = new FactionBtn("img/xy2uiimg/54_png.button.btn_right.png", 1, 19, this)).setBounds(270, 9, 12, 16);
            this.add(this.btnRight);
        }
        return this.btnRight;
    }
    
    public void setBtnRight(FactionBtn btnRight) {
        this.btnRight = btnRight;
    }
    
    public int getLvlNow() {
        return this.lvlNow;
    }
    
    public void setLvlNow(int lvlNow) {
        this.lvlNow = lvlNow;
    }
    
    public ImageIcon getIcon() {
        return this.icon;
    }
    
    public void setIcon(ImageIcon icon) {
        this.icon = icon;
    }
    
    public int getLvlChange() {
        return this.lvlChange;
    }
    
    public void setLvlChange(int lvlChange) {
        this.lvlChange = lvlChange;
    }
}
