package org.come.Jpanel;

import org.come.until.JmSum;
import com.tool.role.RoleProperty;
import org.come.bean.RoleAttribute;
import com.tool.PanelDisplay.RolePanelShow;
import org.come.until.FormsManagement;
import java.awt.event.MouseEvent;
import org.come.bean.LoginResult;
import org.come.until.AnalysisString;
import com.tool.role.RoleData;
import java.awt.Graphics;
import com.tool.tcpimg.UIUtils;
import java.awt.Font;
import java.awt.Color;
import org.come.mouslisten.PointChangeTwoMouslisten;
import org.come.mouslisten.PointChangeOneMouslisten;
import com.tool.btn.FormsOnOffBtn;
import java.awt.Dimension;
import com.updateNew.MyIsif;
import org.come.Frame.RoleToggleJframe;
import javax.swing.ImageIcon;
import org.soaring.btn.CharacterBtn;
import com.tool.btn.JpanelOnJalbelBtn;
import javax.swing.JLabel;
import java.awt.event.MouseListener;
import javax.swing.JPanel;

public class RoleToggleJpanel extends JPanel implements MouseListener
{
    private final long serialVersionUID = 1L;
    private int expsize;
    private JLabel label;
    private int level;
    private JLabel explain;
    private JLabel state1;
    private JLabel state2;
    private JLabel labrolename1;
    private JLabel labrolename2;
    private JLabel labrootbone1;
    private JLabel labintelligence1;
    private JLabel labpower1;
    private JLabel labspeed1;
    private JLabel labability1;
    private JLabel labpointnumber1;
    private JLabel labrootbone2;
    private JLabel labintelligence2;
    private JLabel labpower2;
    private JLabel labspeed2;
    private JLabel labability2;
    private JLabel labpointnumber2;
    private JpanelOnJalbelBtn btnsure1;
    private JpanelOnJalbelBtn btnsure2;
    private JpanelOnJalbelBtn switchbtn1;
    private JpanelOnJalbelBtn switchbtn2;
    private JpanelOnJalbelBtn resetbtn1;
    private JpanelOnJalbelBtn resetbtn2;
    private CharacterBtn nameBtn;
    private CharacterBtn idBtn;
    private CharacterBtn labappellationBtn;
    private CharacterBtn attributeBtn;
    private CharacterBtn transformationBtn;
    private CharacterBtn[] dians1;
    private CharacterBtn[] dians2;
    private ImageIcon icon;
    private String style;
    
    public RoleToggleJpanel(RoleToggleJframe roleToggleJframe) throws Exception {
        this.dians1 = new CharacterBtn[10];
        this.dians2 = new CharacterBtn[10];
        this.style = MyIsif.getStyle();
        if (MyIsif.getStyle().equals("水墨")) {
            this.setPreferredSize(new Dimension(350, 311));
            this.setOpaque(false);
            this.setLayout(null);
            FormsOnOffBtn offBtn = new FormsOnOffBtn("inkImg/button/1.png", 1, 123);
            offBtn.setBounds(313, 10, 25, 25);
            this.add(offBtn);
            for (int i = 0; i < this.dians1.length; ++i) {
                if (i % 2 == 0) {
                    this.dians1[i] = new CharacterBtn("inkImg/button/12.png", 1, 10 + i);
                }
                else {
                    this.dians1[i] = new CharacterBtn("inkImg/button/11.png", 1, 10 + i);
                }
                this.dians1[i].setBounds(133 + i % 2 * 14, 110 + i / 2 * 22, 12, 16);
                this.dians1[i].addMouseListener(new PointChangeOneMouslisten(i));
                this.add(this.dians1[i]);
            }
            for (int i = 0; i < this.dians2.length; ++i) {
                if (i % 2 == 0) {
                    this.dians2[i] = new CharacterBtn("inkImg/button/12.png", 1, 10 + i);
                }
                else {
                    this.dians2[i] = new CharacterBtn("inkImg/button/11.png", 1, 10 + i);
                }
                this.dians2[i].setBounds(295 + i % 2 * 14, 110 + i / 2 * 22, 12, 16);
                this.dians2[i].addMouseListener(new PointChangeTwoMouslisten(i));
                this.add(this.dians2[i]);
            }
            (this.labrootbone1 = new JLabel()).setBounds(66, 111, 61, 15);
            this.labrootbone1.setForeground(Color.WHITE);
            this.labrootbone1.setFont(new Font("微软雅黑", 1, 12));
            this.add(this.labrootbone1);
            (this.labintelligence1 = new JLabel()).setBounds(66, 133, 61, 15);
            this.labintelligence1.setForeground(Color.WHITE);
            this.labintelligence1.setFont(new Font("微软雅黑", 1, 12));
            this.add(this.labintelligence1);
            (this.labpower1 = new JLabel()).setBounds(66, 155, 61, 15);
            this.labpower1.setForeground(Color.WHITE);
            this.labpower1.setFont(new Font("微软雅黑", 1, 12));
            this.add(this.labpower1);
            (this.labspeed1 = new JLabel()).setBounds(66, 177, 61, 15);
            this.labspeed1.setForeground(Color.WHITE);
            this.labspeed1.setFont(new Font("微软雅黑", 1, 12));
            this.add(this.labspeed1);
            (this.labability1 = new JLabel("0")).setBounds(66, 199, 61, 15);
            this.labability1.setForeground(Color.WHITE);
            this.labability1.setFont(new Font("微软雅黑", 1, 12));
            this.add(this.labability1);
            (this.labpointnumber1 = new JLabel()).setBounds(101, 89, 41, 15);
            this.labpointnumber1.setForeground(Color.WHITE);
            this.labpointnumber1.setFont(new Font("微软雅黑", 1, 12));
            this.add(this.labpointnumber1);
            (this.labrootbone2 = new JLabel()).setBounds(229, 111, 61, 15);
            this.labrootbone2.setForeground(Color.WHITE);
            this.labrootbone2.setFont(new Font("微软雅黑", 1, 12));
            this.add(this.labrootbone2);
            (this.labintelligence2 = new JLabel()).setBounds(229, 133, 61, 15);
            this.labintelligence2.setForeground(Color.WHITE);
            this.labintelligence2.setFont(new Font("微软雅黑", 1, 12));
            this.add(this.labintelligence2);
            (this.labpower2 = new JLabel()).setBounds(229, 155, 61, 15);
            this.labpower2.setForeground(Color.WHITE);
            this.labpower2.setFont(new Font("微软雅黑", 1, 12));
            this.add(this.labpower2);
            (this.labspeed2 = new JLabel()).setBounds(229, 177, 61, 15);
            this.labspeed2.setForeground(Color.WHITE);
            this.labspeed2.setFont(new Font("微软雅黑", 1, 12));
            this.add(this.labspeed2);
            (this.labability2 = new JLabel("0")).setBounds(229, 199, 61, 15);
            this.labability2.setForeground(Color.WHITE);
            this.labability2.setFont(new Font("微软雅黑", 1, 12));
            this.add(this.labability2);
            (this.labpointnumber2 = new JLabel()).setBounds(263, 89, 41, 15);
            this.labpointnumber2.setForeground(Color.WHITE);
            this.labpointnumber2.setFont(new Font("微软雅黑", 1, 12));
            this.add(this.labpointnumber2);
            (this.labrolename1 = new JLabel()).setBounds(28, 35, 120, 15);
            this.labrolename1.setForeground(Color.black);
            this.labrolename1.setFont(new Font("微软雅黑", 1, 12));
            this.labrolename1.setText("属性一");
            this.add(this.labrolename1);
            (this.labrolename2 = new JLabel()).setBounds(191, 35, 120, 15);
            this.labrolename2.setForeground(Color.black);
            this.labrolename2.setFont(new Font("微软雅黑", 1, 12));
            this.labrolename2.setText("属性二");
            this.add(this.labrolename2);
            (this.state1 = new JLabel()).setBounds(30, 60, 120, 15);
            this.state1.setForeground(Color.WHITE);
            this.state1.setFont(new Font("楷体", 1, 14));
            this.state1.setText("未启用");
            this.add(this.state1);
            (this.state2 = new JLabel()).setBounds(193, 60, 120, 15);
            this.state2.setForeground(Color.WHITE);
            this.state2.setFont(new Font("楷体", 1, 14));
            this.state2.setText("未启用");
            this.add(this.state2);
            Font font = new Font("宋体", 0, 12);
            (this.switchbtn1 = new JpanelOnJalbelBtn("inkImg/button1/B30.png", 1, UIUtils.COLOR_BTNTEXT, 12, "切换", this)).setBounds(122, 34, 34, 17);
            this.switchbtn1.setFont(font);
            this.add(this.switchbtn1);
            (this.switchbtn2 = new JpanelOnJalbelBtn("inkImg/button1/B30.png", 1, UIUtils.COLOR_BTNTEXT, 13, "切换", this)).setBounds(285, 34, 34, 17);
            this.switchbtn2.setFont(font);
            this.add(this.switchbtn2);
            (this.resetbtn1 = new JpanelOnJalbelBtn("inkImg/button1/B30.png", 1, UIUtils.COLOR_BTNTEXT, 14, "重置", this)).setBounds(43, 250, 34, 17);
            this.resetbtn1.setFont(font);
            this.add(this.resetbtn1);
            (this.resetbtn2 = new JpanelOnJalbelBtn("inkImg/button1/B30.png", 1, UIUtils.COLOR_BTNTEXT, 15, "重置", this)).setBounds(205, 250, 34, 17);
            this.resetbtn2.setFont(font);
            this.add(this.resetbtn2);
            (this.btnsure1 = new JpanelOnJalbelBtn("inkImg/button1/B32.png", 1, UIUtils.COLOR_BTNTEXT, 11, "确认加点", this)).setBounds(98, 250, 51, 17);
            this.btnsure1.setFont(font);
            this.add(this.btnsure1);
            (this.btnsure2 = new JpanelOnJalbelBtn("inkImg/button1/B32.png", 1, UIUtils.COLOR_BTNTEXT, 11, "确认加点", this)).setBounds(260, 250, 51, 17);
            this.btnsure2.setFont(font);
            this.add(this.btnsure2);
        }
        else {
            this.setPreferredSize(new Dimension(350, 311));
            this.setOpaque(false);
            this.setLayout(null);
            (this.labrolename1 = new JLabel()).setBounds(38, 67, 120, 15);
            this.labrolename1.setForeground(Color.WHITE);
            this.labrolename1.setFont(new Font("微软雅黑", 1, 14));
            this.labrolename1.setText("属性一");
            this.add(this.labrolename1);
            (this.labrolename2 = new JLabel()).setBounds(193, 67, 120, 15);
            this.labrolename2.setForeground(Color.WHITE);
            this.labrolename2.setFont(new Font("微软雅黑", 1, 14));
            this.labrolename2.setText("属性二");
            this.add(this.labrolename2);
            (this.state1 = new JLabel()).setBounds(30, 42, 120, 15);
            this.state1.setForeground(Color.WHITE);
            this.state1.setFont(new Font("楷体", 1, 14));
            this.state1.setText("未启用");
            this.add(this.state1);
            (this.state2 = new JLabel()).setBounds(185, 42, 120, 15);
            this.state2.setForeground(Color.WHITE);
            this.state2.setFont(new Font("楷体", 1, 14));
            this.state2.setText("未启用");
            this.add(this.state2);
            FormsOnOffBtn offBtn = new FormsOnOffBtn("inkImg/hongmu/s74.png", 1, 123);
            offBtn.setBounds(325, 0, 23, 23);
            this.add(offBtn);
            for (int i = 0; i < this.dians1.length; ++i) {
                if (i % 2 == 0) {
                    this.dians1[i] = new CharacterBtn("img/xy2uiimg/26_png.button.btn_left.png", 1, 10 + i);
                }
                else {
                    this.dians1[i] = new CharacterBtn("img/xy2uiimg/54_png.button.btn_right.png", 1, 10 + i);
                }
                this.dians1[i].setBounds(129 + i % 2 * 13, 118 + i / 2 * 24, 13, 13);
                this.dians1[i].addMouseListener(new PointChangeOneMouslisten(i));
                this.add(this.dians1[i]);
            }
            for (int i = 0; i < this.dians2.length; ++i) {
                if (i % 2 == 0) {
                    this.dians2[i] = new CharacterBtn("img/xy2uiimg/26_png.button.btn_left.png", 1, 10 + i);
                }
                else {
                    this.dians2[i] = new CharacterBtn("img/xy2uiimg/54_png.button.btn_right.png", 1, 10 + i);
                }
                this.dians2[i].setBounds(283 + i % 2 * 13, 118 + i / 2 * 24, 13, 13);
                this.dians2[i].addMouseListener(new PointChangeTwoMouslisten(i));
                this.add(this.dians2[i]);
            }
            Font font = new Font("楷体", 0, 14);
            (this.labrootbone1 = new JLabel()).setBounds(88, 118, 61, 15);
            this.labrootbone1.setForeground(Color.WHITE);
            this.labrootbone1.setFont(font);
            this.add(this.labrootbone1);
            (this.labintelligence1 = new JLabel()).setBounds(88, 141, 61, 15);
            this.labintelligence1.setForeground(Color.WHITE);
            this.labintelligence1.setFont(font);
            this.add(this.labintelligence1);
            (this.labpower1 = new JLabel()).setBounds(88, 165, 61, 15);
            this.labpower1.setForeground(Color.WHITE);
            this.labpower1.setFont(font);
            this.add(this.labpower1);
            (this.labspeed1 = new JLabel()).setBounds(88, 190, 61, 15);
            this.labspeed1.setForeground(Color.WHITE);
            this.labspeed1.setFont(font);
            this.add(this.labspeed1);
            (this.labability1 = new JLabel("0")).setBounds(88, 213, 61, 15);
            this.labability1.setForeground(Color.WHITE);
            this.labability1.setFont(font);
            this.add(this.labability1);
            (this.labpointnumber1 = new JLabel()).setBounds(115, 95, 41, 15);
            this.labpointnumber1.setForeground(Color.WHITE);
            this.labpointnumber1.setFont(font);
            this.add(this.labpointnumber1);
            (this.labrootbone2 = new JLabel()).setBounds(245, 118, 61, 15);
            this.labrootbone2.setForeground(Color.WHITE);
            this.labrootbone2.setFont(font);
            this.add(this.labrootbone2);
            (this.labintelligence2 = new JLabel()).setBounds(245, 141, 61, 15);
            this.labintelligence2.setForeground(Color.WHITE);
            this.labintelligence2.setFont(font);
            this.add(this.labintelligence2);
            (this.labpower2 = new JLabel()).setBounds(245, 165, 61, 15);
            this.labpower2.setForeground(Color.WHITE);
            this.labpower2.setFont(font);
            this.add(this.labpower2);
            (this.labspeed2 = new JLabel()).setBounds(245, 190, 61, 15);
            this.labspeed2.setForeground(Color.WHITE);
            this.labspeed2.setFont(font);
            this.add(this.labspeed2);
            (this.labability2 = new JLabel("0")).setBounds(245, 213, 61, 15);
            this.labability2.setForeground(Color.WHITE);
            this.labability2.setFont(font);
            this.add(this.labability2);
            (this.labpointnumber2 = new JLabel()).setBounds(270, 95, 41, 15);
            this.labpointnumber2.setForeground(Color.WHITE);
            this.labpointnumber2.setFont(font);
            this.add(this.labpointnumber2);
            (this.btnsure1 = new JpanelOnJalbelBtn("inkImg/hongmu/a20.png", 1, UIUtils.COLOR_BTNTEXT, 11, "", this)).setBounds(95, 231, 68, 17);
            this.add(this.btnsure1);
            (this.btnsure2 = new JpanelOnJalbelBtn("inkImg/hongmu/a20.png", 1, UIUtils.COLOR_BTNTEXT, 11, "", this)).setBounds(245, 231, 68, 17);
            this.add(this.btnsure2);
            font = new Font("宋体", 0, 12);
            (this.switchbtn1 = new JpanelOnJalbelBtn("inkImg/hongmu/21_png.button.tab_nex.png", 1, UIUtils.COLOR_BTNPUTONG, 12, "切换", this)).setBounds(120, 41, 34, 17);
            this.switchbtn1.setFont(font);
            this.add(this.switchbtn1);
            (this.switchbtn2 = new JpanelOnJalbelBtn("inkImg/hongmu/21_png.button.tab_nex.png", 1, UIUtils.COLOR_BTNPUTONG, 13, "切换", this)).setBounds(275, 41, 34, 17);
            this.switchbtn2.setFont(font);
            this.add(this.switchbtn2);
            (this.resetbtn1 = new JpanelOnJalbelBtn("inkImg/hongmu/21_png.button.tab_nex.png", 1, UIUtils.COLOR_BTNPUTONG, 14, "重置", this)).setBounds(45, 231, 34, 17);
            this.resetbtn1.setFont(font);
            this.add(this.resetbtn1);
            (this.resetbtn2 = new JpanelOnJalbelBtn("inkImg/hongmu/21_png.button.tab_nex.png", 1, UIUtils.COLOR_BTNPUTONG, 15, "重置", this)).setBounds(200, 231, 34, 17);
            this.resetbtn2.setFont(font);
            this.add(this.resetbtn2);
        }
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        LoginResult loginResult = RoleData.getRoleData().getLoginResult();
        this.level = AnalysisString.lvltrue((int)loginResult.getGrade());
        if (this.style.equals("水墨")) {
            if (this.level <= 3) {
                this.icon = new ImageIcon("inkImg/background1/B280.png");
                g.drawImage(this.icon.getImage(), 0, 0, 350, 311, this);
            }
            else {
                this.icon = new ImageIcon("inkImg/background1/B279.png");
                g.drawImage(this.icon.getImage(), 0, 0, 350, 311, this);
            }
        }
        else if (this.level <= 3) {
            this.icon = new ImageIcon("img/xy2uiimg/切属性_w450,h300.png");
            g.drawImage(this.icon.getImage(), 0, 0, 350, 311, this);
        }
        else {
            this.icon = new ImageIcon("img/xy2uiimg/切属性_飞升_w450,h300.png");
            g.drawImage(this.icon.getImage(), 0, 0, 350, 311, this);
        }
    }
    
    public int getdian1(int type) {
        try {
            if (type == 0) {
                type = Integer.parseInt(this.labrootbone1.getText());
            }
            else if (type == 1) {
                type = Integer.parseInt(this.labintelligence1.getText());
            }
            else if (type == 2) {
                type = Integer.parseInt(this.labpower1.getText());
            }
            else if (type == 3) {
                type = Integer.parseInt(this.labspeed1.getText());
            }
            else if (type == 4) {
                type = Integer.parseInt(this.labability1.getText());
            }
            else if (type == 5) {
                type = Integer.parseInt(this.labpointnumber1.getText());
            }
        }
        catch (Exception e) {
            type = 0;
        }
        return type;
    }
    
    public int getdian2(int type) {
        try {
            if (type == 0) {
                type = Integer.parseInt(this.labrootbone2.getText());
            }
            else if (type == 1) {
                type = Integer.parseInt(this.labintelligence2.getText());
            }
            else if (type == 2) {
                type = Integer.parseInt(this.labpower2.getText());
            }
            else if (type == 3) {
                type = Integer.parseInt(this.labspeed2.getText());
            }
            else if (type == 4) {
                type = Integer.parseInt(this.labability2.getText());
            }
            else if (type == 5) {
                type = Integer.parseInt(this.labpointnumber2.getText());
            }
        }
        catch (Exception e) {
            type = 0;
        }
        return type;
    }
    
    public void adddian1(int type, int point) {
        try {
            if (type == 0) {
                type = Integer.parseInt(this.labrootbone1.getText());
                this.labrootbone1.setText(type + point + "");
            }
            else if (type == 1) {
                type = Integer.parseInt(this.labintelligence1.getText());
                this.labintelligence1.setText(type + point + "");
            }
            else if (type == 2) {
                type = Integer.parseInt(this.labpower1.getText());
                this.labpower1.setText(type + point + "");
            }
            else if (type == 3) {
                type = Integer.parseInt(this.labspeed1.getText());
                this.labspeed1.setText(type + point + "");
            }
            else if (type == 4) {
                type = Integer.parseInt(this.labability1.getText());
                this.labability1.setText(type + point + "");
            }
            type = Integer.parseInt(this.labpointnumber1.getText());
            this.labpointnumber1.setText(type - point + "");
        }
        catch (Exception ex) {}
    }
    
    public void adddian2(int type, int point) {
        try {
            if (type == 0) {
                type = Integer.parseInt(this.labrootbone2.getText());
                this.labrootbone2.setText(type + point + "");
            }
            else if (type == 1) {
                type = Integer.parseInt(this.labintelligence2.getText());
                this.labintelligence2.setText(type + point + "");
            }
            else if (type == 2) {
                type = Integer.parseInt(this.labpower2.getText());
                this.labpower2.setText(type + point + "");
            }
            else if (type == 3) {
                type = Integer.parseInt(this.labspeed2.getText());
                this.labspeed2.setText(type + point + "");
            }
            else if (type == 4) {
                type = Integer.parseInt(this.labability2.getText());
                this.labability2.setText(type + point + "");
            }
            type = Integer.parseInt(this.labpointnumber2.getText());
            this.labpointnumber2.setText(type - point + "");
        }
        catch (Exception ex) {}
    }
    
    public void changeSoaringPanel(int lvltrue) {
        this.level = lvltrue;
        if (lvltrue <= 3) {
            this.dians1[8].setVisible(false);
            this.dians2[8].setVisible(false);
            this.dians1[9].setVisible(false);
            this.dians2[9].setVisible(false);
            this.labability1.setVisible(false);
            this.labability2.setVisible(false);
        }
        else {
            this.dians1[8].setVisible(true);
            this.dians2[8].setVisible(true);
            this.dians1[9].setVisible(true);
            this.dians2[9].setVisible(true);
            this.labability1.setVisible(true);
            this.labability2.setVisible(true);
        }
    }
    
    @Override
    public void mouseClicked(MouseEvent e) {
    }
    
    @Override
    public void mousePressed(MouseEvent e) {
    }
    
    @Override
    public void mouseReleased(MouseEvent e) {
        FormsManagement.HideForm(0);
    }
    
    @Override
    public void mouseEntered(MouseEvent e) {
    }
    
    @Override
    public void mouseExited(MouseEvent e) {
    }
    
    public JLabel getLabel() {
        return this.label;
    }
    
    public void setLabel(JLabel label) {
        this.label = label;
    }
    
    public JLabel getLabrootbone1() {
        return this.labrootbone1;
    }
    
    public void setLabrootbone1(JLabel labrootbone1) {
        this.labrootbone1 = labrootbone1;
    }
    
    public JLabel getLabintelligence1() {
        return this.labintelligence1;
    }
    
    public void setLabintelligence1(JLabel labintelligence1) {
        this.labintelligence1 = labintelligence1;
    }
    
    public JLabel getLabpower1() {
        return this.labpower1;
    }
    
    public void setLabpower1(JLabel labpower1) {
        this.labpower1 = labpower1;
    }
    
    public JLabel getLabspeed1() {
        return this.labspeed1;
    }
    
    public void setLabspeed1(JLabel labspeed1) {
        this.labspeed1 = labspeed1;
    }
    
    public JLabel getLabpointnumber1() {
        return this.labpointnumber1;
    }
    
    public void setLabpointnumber1(JLabel labpointnumber1) {
        this.labpointnumber1 = labpointnumber1;
    }
    
    public int getExpsize() {
        return this.expsize;
    }
    
    public void setExpsize(int expsize) {
        this.expsize = expsize;
    }
    
    public JLabel getLabability1() {
        return this.labability1;
    }
    
    public void setLabability1(JLabel labability1) {
        this.labability1 = labability1;
    }
    
    public JpanelOnJalbelBtn getBtnsure1() {
        return this.btnsure1;
    }
    
    public void setBtnsure1(JpanelOnJalbelBtn btnsure1) {
        this.btnsure1 = btnsure1;
    }
    
    public JpanelOnJalbelBtn getBtnsure2() {
        return this.btnsure2;
    }
    
    public void setBtnsure2(JpanelOnJalbelBtn btnsure2) {
        this.btnsure2 = btnsure2;
    }
    
    public CharacterBtn getNameBtn() {
        return this.nameBtn;
    }
    
    public void setNameBtn(CharacterBtn nameBtn) {
        this.nameBtn = nameBtn;
    }
    
    public CharacterBtn getIdBtn() {
        return this.idBtn;
    }
    
    public void setIdBtn(CharacterBtn idBtn) {
        this.idBtn = idBtn;
    }
    
    public CharacterBtn getLabappellationBtn() {
        return this.labappellationBtn;
    }
    
    public void setLabappellationBtn(CharacterBtn labappellationBtn) {
        this.labappellationBtn = labappellationBtn;
    }
    
    public CharacterBtn getAttributeBtn() {
        return this.attributeBtn;
    }
    
    public void setAttributeBtn(CharacterBtn attributeBtn) {
        this.attributeBtn = attributeBtn;
    }
    
    public CharacterBtn getTransformationBtn() {
        return this.transformationBtn;
    }
    
    public void setTransformationBtn(CharacterBtn transformationBtn) {
        this.transformationBtn = transformationBtn;
    }
    
    public CharacterBtn[] getDians1() {
        return this.dians1;
    }
    
    public void setDians1(CharacterBtn[] dians1) {
        this.dians1 = dians1;
    }
    
    public CharacterBtn[] getDians2() {
        return this.dians2;
    }
    
    public void setDians2(CharacterBtn[] dians2) {
        this.dians2 = dians2;
    }
    
    public ImageIcon getIcon() {
        return this.icon;
    }
    
    public void setIcon(ImageIcon icon) {
        this.icon = icon;
    }
    
    public long getSerialversionuid() {
        return 1L;
    }
    
    public JLabel getLabrootbone2() {
        return this.labrootbone2;
    }
    
    public void setLabrootbone2(JLabel labrootbone2) {
        this.labrootbone2 = labrootbone2;
    }
    
    public JLabel getLabintelligence2() {
        return this.labintelligence2;
    }
    
    public void setLabintelligence2(JLabel labintelligence2) {
        this.labintelligence2 = labintelligence2;
    }
    
    public JLabel getLabpower2() {
        return this.labpower2;
    }
    
    public void setLabpower2(JLabel labpower2) {
        this.labpower2 = labpower2;
    }
    
    public JLabel getLabspeed2() {
        return this.labspeed2;
    }
    
    public void setLabspeed2(JLabel labspeed2) {
        this.labspeed2 = labspeed2;
    }
    
    public JLabel getLabability2() {
        return this.labability2;
    }
    
    public void setLabability2(JLabel labability2) {
        this.labability2 = labability2;
    }
    
    public JLabel getLabpointnumber2() {
        return this.labpointnumber2;
    }
    
    public void setLabpointnumber2(JLabel labpointnumber2) {
        this.labpointnumber2 = labpointnumber2;
    }
    
    public int getLevel() {
        return this.level;
    }
    
    public void setLevel(int level) {
        this.level = level;
    }
    
    public JLabel getState1() {
        return this.state1;
    }
    
    public void setState1(JLabel state1) {
        this.state1 = state1;
    }
    
    public JLabel getState2() {
        return this.state2;
    }
    
    public void setState2(JLabel state2) {
        this.state2 = state2;
    }
    
    public JLabel getLabrolename1() {
        return this.labrolename1;
    }
    
    public void setLabrolename1(JLabel labrolename1) {
        this.labrolename1 = labrolename1;
    }
    
    public JLabel getLabrolename2() {
        return this.labrolename2;
    }
    
    public void setLabrolename2(JLabel labrolename2) {
        this.labrolename2 = labrolename2;
    }
    
    public void getplayerValue() {
        LoginResult loginResult = RoleData.getRoleData().getLoginResult();
        if (loginResult.getCurrentattribute() != null && (int)loginResult.getCurrentattribute() != 0) {
            if ((int)loginResult.getCurrentattribute() == 1) {
                this.state1.setForeground(Color.green);
                this.state1.setText("当前启用中");
            }
            else if ((int)loginResult.getCurrentattribute() == 2) {
                this.state2.setForeground(Color.green);
                this.state2.setText("当前启用中");
            }
        }
        int lvltrue = AnalysisString.lvltrue((int)loginResult.getGrade());
        this.changeSoaringPanel(lvltrue);
        RolePanelShow.changeGrade((int)loginResult.getGrade());
        RoleAttribute roleAttribute = RoleData.getRoleData().getRoleAttribute();
        int lvl = AnalysisString.lvlint((int)loginResult.getGrade());
        if (roleAttribute == null) {
            roleAttribute = new RoleAttribute();
        }
        if (roleAttribute.getBONEONE() == null) {
            roleAttribute.setBONEONE(Integer.valueOf(lvl));
            roleAttribute.setSPIRONE(Integer.valueOf(lvl));
            roleAttribute.setPOWERONE(Integer.valueOf(lvl));
            roleAttribute.setSPEEDONE(Integer.valueOf(lvl));
            if (loginResult.getTurnAround() >= 4) {
                roleAttribute.setCALMONE(Integer.valueOf(lvl));
            }
            else {
                roleAttribute.setCALMONE(Integer.valueOf(0));
            }
        }
        if (roleAttribute.getBONETWO() == null) {
            roleAttribute.setBONETWO(Integer.valueOf(lvl));
            roleAttribute.setSPIRTWO(Integer.valueOf(lvl));
            roleAttribute.setPOWERTWO(Integer.valueOf(lvl));
            roleAttribute.setSPEEDTWO(Integer.valueOf(lvl));
            if (loginResult.getTurnAround() >= 4) {
                roleAttribute.setCALMTWO(Integer.valueOf(lvl));
            }
            else {
                roleAttribute.setCALMTWO(Integer.valueOf(0));
            }
        }
        this.getLabpointnumber1().setText(getCanpoint1(roleAttribute).toString());
        RoleProperty property1 = RoleProperty.property;
        if (property1 == null) {
            return;
        }
        this.getLabrootbone1().setText(roleAttribute.getBONEONE() + "");
        this.getLabintelligence1().setText(roleAttribute.getSPIRONE() + "");
        this.getLabpower1().setText(roleAttribute.getPOWERONE() + "");
        this.getLabspeed1().setText(roleAttribute.getSPEEDONE() + "");
        this.getLabability1().setText(roleAttribute.getCALMONE() + "");
        this.getLabpointnumber2().setText(getCanpoint2(roleAttribute).toString());
        RoleProperty property2 = RoleProperty.property;
        if (property2 == null) {
            return;
        }
        this.getLabrootbone2().setText(roleAttribute.getBONETWO() + "");
        this.getLabintelligence2().setText(roleAttribute.getSPIRTWO() + "");
        this.getLabpower2().setText(roleAttribute.getPOWERTWO() + "");
        this.getLabspeed2().setText(roleAttribute.getSPEEDTWO() + "");
        this.getLabability2().setText(roleAttribute.getCALMTWO() + "");
    }
    
    public static Integer getCanpoint1(RoleAttribute roleAttribute) {
        LoginResult loginResult = RoleData.getRoleData().getLoginResult();
        int lvl = (int)loginResult.getGrade();
        int Turn = AnalysisString.lvltrue(lvl);
        lvl = AnalysisString.lvlint(lvl);
        int canpoint = lvl * 8;
        canpoint += loginResult.getExtraPointInt();
        canpoint += loginResult.getExtPointInt();
        if (Turn < 4) {
            canpoint += Turn * 60;
        }
        else {
            canpoint += 180 + lvl;
        }
        int v = (int)roleAttribute.getBONEONE();
        if (v < lvl) {
            roleAttribute.setBONEONE(Integer.valueOf(lvl));
            canpoint -= lvl;
        }
        else {
            canpoint -= v;
        }
        v = (int)roleAttribute.getSPIRONE();
        if (v < lvl) {
            roleAttribute.setSPIRONE(Integer.valueOf(lvl));
            canpoint -= lvl;
        }
        else {
            canpoint -= v;
        }
        v = (int)roleAttribute.getPOWERONE();
        if (v < lvl) {
            roleAttribute.setPOWERONE(Integer.valueOf(lvl));
            canpoint -= lvl;
        }
        else {
            canpoint -= v;
        }
        v = (int)roleAttribute.getSPEEDONE();
        if (v < lvl) {
            roleAttribute.setSPEEDONE(Integer.valueOf(lvl));
            canpoint -= lvl;
        }
        else {
            canpoint -= v;
        }
        v = (int)roleAttribute.getCALMONE();
        if (v < 0 || (Turn >= 4 && v < lvl)) {
            roleAttribute.setCALMONE(Integer.valueOf(lvl));
            canpoint -= lvl;
        }
        else {
            canpoint -= v;
        }
        if (canpoint < 0) {
            JmSum.xiugaiqi();
        }
        return Integer.valueOf(canpoint);
    }
    
    public static Integer getCanpoint2(RoleAttribute roleAttribute) {
        LoginResult loginResult = RoleData.getRoleData().getLoginResult();
        int lvl = (int)loginResult.getGrade();
        int Turn = AnalysisString.lvltrue(lvl);
        lvl = AnalysisString.lvlint(lvl);
        int canpoint = lvl * 8;
        canpoint += loginResult.getExtraPointInt();
        canpoint += loginResult.getExtPointInt();
        if (Turn < 4) {
            canpoint += Turn * 60;
        }
        else {
            canpoint += 180 + lvl;
        }
        int v = (int)roleAttribute.getBONETWO();
        if (v < lvl) {
            roleAttribute.setBONETWO(Integer.valueOf(lvl));
            canpoint -= lvl;
        }
        else {
            canpoint -= v;
        }
        v = (int)roleAttribute.getSPIRTWO();
        if (v < lvl) {
            roleAttribute.setSPIRTWO(Integer.valueOf(lvl));
            canpoint -= lvl;
        }
        else {
            canpoint -= v;
        }
        v = (int)roleAttribute.getPOWERTWO();
        if (v < lvl) {
            roleAttribute.setPOWERTWO(Integer.valueOf(lvl));
            canpoint -= lvl;
        }
        else {
            canpoint -= v;
        }
        v = (int)roleAttribute.getSPEEDTWO();
        if (v < lvl) {
            roleAttribute.setSPEEDTWO(Integer.valueOf(lvl));
            canpoint -= lvl;
        }
        else {
            canpoint -= v;
        }
        v = (int)roleAttribute.getCALMTWO();
        if (v < 0 || (Turn >= 4 && v < lvl)) {
            roleAttribute.setCALMTWO(Integer.valueOf(lvl));
            canpoint -= lvl;
        }
        else {
            canpoint -= v;
        }
        if (canpoint < 0) {
            JmSum.xiugaiqi();
        }
        return Integer.valueOf(canpoint);
    }
    
    public static Integer getTotalpoint() {
        LoginResult loginResult = RoleData.getRoleData().getLoginResult();
        int lvl = (int)loginResult.getGrade();
        int Turn = AnalysisString.lvltrue(lvl);
        lvl = AnalysisString.lvlint(lvl);
        int totalpoint = lvl * 8;
        totalpoint += loginResult.getExtraPointInt();
        if (Turn < 4) {
            totalpoint += Turn * 60;
        }
        else {
            totalpoint += 180 + lvl;
        }
        return Integer.valueOf(totalpoint);
    }
    
    public static Integer getTotalpointBylvl(int realLvl, int extrapoint) {
        int Turn = AnalysisString.lvltrue(realLvl);
        int lvl = AnalysisString.lvlint(realLvl);
        int totalpoint = lvl * 8;
        totalpoint += extrapoint;
        if (Turn < 4) {
            totalpoint += Turn * 60;
        }
        else {
            totalpoint += 180 + lvl;
        }
        return Integer.valueOf(totalpoint);
    }
    
    public static void main(String[] str) {
        System.out.println(getTotalpointBylvl(211, 0));
    }
}
