package org.come.Jpanel;

import org.come.bean.LoginResult;
import org.come.until.CutButtonImage;
import org.apache.commons.lang.StringUtils;
import java.time.temporal.ChronoUnit;
import java.time.LocalDate;
import com.tool.role.RoleData;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.Font;
import com.tool.btn.FormsOnOffBtn;
import com.tool.tcpimg.UIUtils;
import java.awt.Dimension;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import com.tool.btn.LiangHaoItemBtn;
import javax.swing.JPanel;

public class ContinueLiangHaoJpanel extends JPanel
{
    private LiangHaoItemBtn sureBtn;
    private LiangHaoItemBtn cancelBtn;
    private JLabel lab1;
    private JLabel lab2;
    private JLabel lab3;
    private JLabel lab4;
    private JLabel lab5;
    private JLabel lab6;
    private ImageIcon iconBack;
    
    public ContinueLiangHaoJpanel() {
        this.setPreferredSize(new Dimension(342, 323));
        this.setLayout(null);
        this.setBackground(UIUtils.Color_BACK);
        FormsOnOffBtn offBtn = new FormsOnOffBtn("inkImg/button/1.png", 1, 706);
        offBtn.setBounds(307, 10, 25, 25);
        this.add(offBtn);
        (this.sureBtn = new LiangHaoItemBtn("inkImg/button1/B20.png", 1, this, 70, "确定")).setBounds(80, 275, 60, 25);
        this.sureBtn.setFont(UIUtils.TEXT_HY16);
        this.sureBtn.setVerticalTextPosition(0);
        this.sureBtn.setHorizontalTextPosition(0);
        this.add(this.sureBtn);
        (this.cancelBtn = new LiangHaoItemBtn("inkImg/button1/B20.png", 1, this, 71, "取消")).setBounds(200, 275, 60, 25);
        this.cancelBtn.setFont(UIUtils.TEXT_HY16);
        this.cancelBtn.setVerticalTextPosition(0);
        this.cancelBtn.setHorizontalTextPosition(0);
        this.add(this.cancelBtn);
        Font font = new Font("宋体", 0, 14);
        (this.lab1 = new JLabel()).setBounds(140, 249, 120, 15);
        this.lab1.setFont(font);
        this.lab1.setForeground(Color.WHITE);
        this.add(this.lab1);
        (this.lab2 = new JLabel()).setBounds(140, 221, 120, 15);
        this.lab2.setFont(font);
        this.lab2.setForeground(Color.WHITE);
        this.add(this.lab2);
        (this.lab3 = new JLabel()).setBounds(140, 193, 120, 15);
        this.lab3.setFont(font);
        this.lab3.setForeground(Color.WHITE);
        this.add(this.lab3);
        (this.lab4 = new JLabel()).setBounds(140, 164, 120, 15);
        this.lab4.setFont(font);
        this.lab4.setForeground(Color.WHITE);
        this.add(this.lab4);
        (this.lab5 = new JLabel()).setBounds(120, 130, 120, 15);
        this.lab5.setForeground(Color.WHITE);
        this.lab5.setFont(font);
        this.add(this.lab5);
        (this.lab6 = new JLabel()).setBounds(190, 130, 120, 15);
        this.lab6.setForeground(Color.RED);
        this.lab6.setFont(font);
        this.add(this.lab6);
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (this.iconBack == null) {
            this.iconBack = new ImageIcon("inkImg/background1/addtime.png");
        }
        g.drawImage(this.iconBack.getImage(), 0, 0, 342, 323, this);
        this.lab1.setText(String.valueOf(RoleData.getRoleData().getLoginResult().getCodecard()));
        LoginResult login = RoleData.getRoleData().getLoginResult();
        long Money = 888L;
        this.lab2.setText(String.valueOf(Money));
        this.lab3.setText(String.valueOf(Money));
        this.lab4.setText("30天");
        this.lab5.setText("剩余时间：");
        if (login.getLianghaoexpire() != null) {
            LocalDate expDate = LocalDate.parse(login.getLianghaoexpire());
            LocalDate nowDate = LocalDate.now();
            long daysBetween = ChronoUnit.DAYS.between(nowDate, expDate);
            this.lab6.setText(daysBetween + "天");
        }
        String num = login.getLiangHao();
        Integer type = Integer.valueOf((login.getLianghaotype() == null) ? 0 : ((int)login.getLianghaotype()));
        if (StringUtils.isNotEmpty(num)) {
            String[] nums = num.split("");
            for (int j = 0; j < nums.length; ++j) {
                int xnum = 140 + 11 * j;
                int ynum = 78;
                g.drawImage(CutButtonImage.getImage("inkImg/number/" + String.valueOf(type) + nums[j] + ".png", 11, 15).getImage(), xnum, ynum, 11, 15, this);
            }
        }
    }
    
    public ImageIcon getIconBack() {
        return this.iconBack;
    }
    
    public void setIconBack(ImageIcon iconBack) {
        this.iconBack = iconBack;
    }
    
    public LiangHaoItemBtn getSureBtn() {
        return this.sureBtn;
    }
    
    public void setSureBtn(LiangHaoItemBtn sureBtn) {
        this.sureBtn = sureBtn;
    }
    
    public LiangHaoItemBtn getCancelBtn() {
        return this.cancelBtn;
    }
    
    public void setCancelBtn(LiangHaoItemBtn cancelBtn) {
        this.cancelBtn = cancelBtn;
    }
    
    public JLabel getLab1() {
        return this.lab1;
    }
    
    public void setLab1(JLabel lab1) {
        this.lab1 = lab1;
    }
    
    public JLabel getLab2() {
        return this.lab2;
    }
    
    public void setLab2(JLabel lab2) {
        this.lab2 = lab2;
    }
    
    public JLabel getLab3() {
        return this.lab3;
    }
    
    public void setLab3(JLabel lab3) {
        this.lab3 = lab3;
    }
    
    public JLabel getLab4() {
        return this.lab4;
    }
    
    public void setLab4(JLabel lab4) {
        this.lab4 = lab4;
    }
    
    public JLabel getLab5() {
        return this.lab5;
    }
    
    public void setLab5(JLabel lab5) {
        this.lab5 = lab5;
    }
    
    public JLabel getLab6() {
        return this.lab6;
    }
    
    public void setLab6(JLabel lab6) {
        this.lab6 = lab6;
    }
}
