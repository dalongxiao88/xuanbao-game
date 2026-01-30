package org.come.Jpanel;

import org.come.until.FormsManagement;
import org.come.bean.LoginResult;
import org.come.until.CutButtonImage;
import org.apache.commons.lang.StringUtils;
import com.tool.role.RoleData;
import java.awt.Graphics;
import java.awt.Font;
import java.awt.Color;
import com.tool.btn.FormsOnOffBtn;
import com.tool.tcpimg.UIUtils;
import java.awt.Dimension;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import com.tool.btn.LiangHaoBtn;
import javax.swing.JPanel;

public class MyLiangHaoJpanel extends JPanel
{
    private LiangHaoBtn openLiangHao;
    private LiangHaoBtn colorLiangHao;
    private LiangHaoBtn btnbottomset;
    private LiangHaoBtn andtime;
    private LiangHaoBtn dropBtn;
    private JLabel lianghaoexpire;
    private JLabel oldid;
    private LiangHaoBtn lhBtn;
    private ImageIcon icon;
    private ImageIcon icon1;
    
    public MyLiangHaoJpanel() {
        this.setPreferredSize(new Dimension(399, 396));
        this.setLayout(null);
        this.setBackground(UIUtils.Color_BACK);
        FormsOnOffBtn offBtn = new FormsOnOffBtn("inkImg/button/1.png", 1, 640);
        offBtn.setBounds(365, 6, 25, 25);
        this.add(offBtn);
        (this.openLiangHao = new LiangHaoBtn("inkImg/button1/B22.png", 1, UIUtils.COLOR_WHITE2, UIUtils.TEXT_HY16, "开通靓号", Integer.valueOf(1), this)).setBounds(255, 250, 100, 24);
        this.add(this.openLiangHao);
        (this.colorLiangHao = new LiangHaoBtn("inkImg/button1/B22.png", 1, UIUtils.COLOR_WHITE2, UIUtils.TEXT_HY16, "靓号染色", Integer.valueOf(2), this)).setBounds(255, 292, 100, 24);
        this.add(this.colorLiangHao);
        (this.btnbottomset = new LiangHaoBtn("inkImg/button1/B22.png", 1, UIUtils.COLOR_WHITE2, UIUtils.TEXT_HY16, "炼化属性", Integer.valueOf(6), this)).setBounds(255, 335, 100, 24);
        this.add(this.btnbottomset);
        (this.andtime = new LiangHaoBtn("inkImg/button1/B20.png", 1, UIUtils.COLOR_WHITE2, UIUtils.TEXT_HY16, "续费", Integer.valueOf(4), this)).setBounds(255, 185, 59, 24);
        this.andtime.setVisible(false);
        this.add(this.andtime);
        (this.dropBtn = new LiangHaoBtn("inkImg/button1/B30.png", 1, UIUtils.COLOR_WHITE2, UIUtils.TEXT_HY16, "捐献", Integer.valueOf(5), this)).setBounds(325, 185, 45, 24);
        this.dropBtn.setFont(UIUtils.TEXT_FONT11);
        this.dropBtn.setColors(UIUtils.COLOR_WHITE);
        this.dropBtn.setVerticalTextPosition(0);
        this.dropBtn.setHorizontalTextPosition(0);
        this.dropBtn.setVisible(false);
        this.add(this.dropBtn);
        (this.oldid = new JLabel()).setBounds(60, 55, 220, 15);
        this.oldid.setForeground(Color.WHITE);
        this.oldid.setFont(new Font("宋体", 0, 14));
        this.add(this.oldid);
        (this.lianghaoexpire = new JLabel()).setBounds(60, 190, 220, 15);
        this.lianghaoexpire.setForeground(Color.WHITE);
        this.lianghaoexpire.setFont(new Font("宋体", 0, 12));
        this.add(this.lianghaoexpire);
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (this.icon == null) {
            this.icon = new ImageIcon("inkImg/background1/MyLiangHao.png");
        }
        g.drawImage(this.icon.getImage(), 0, 0, 399, 396, this);
        LoginResult login = RoleData.getRoleData().getLoginResult();
        if (login.getLianghaoexpire() != null) {
            this.lianghaoexpire.setText("靓号到期时间：" + login.getLianghaoexpire());
            this.lianghaoexpire.setVisible(true);
            this.dropBtn.setVisible(true);
            this.andtime.setVisible(true);
        }
        else {
            this.lianghaoexpire.setVisible(false);
            this.dropBtn.setVisible(false);
            this.andtime.setVisible(false);
        }
        this.oldid.setText("原始ID：" + login.getRole_id());
        String num = login.getLiangHao();
        Integer type = Integer.valueOf((login.getLianghaotype() == null) ? 0 : ((int)login.getLianghaotype()));
        if (StringUtils.isNotEmpty(num)) {
            String[] nums = num.split("");
            for (int j = 0; j < nums.length; ++j) {
                int xnum = 175 + 11 * j;
                int ynum = 110;
                g.drawImage(CutButtonImage.getImage("inkImg/number/" + String.valueOf(type) + nums[j] + ".png", 11, 15).getImage(), xnum, ynum, 11, 15, this);
            }
        }
    }
    
    public void showBox(String type, Object object, String text) {
        FormsManagement.showForm(104);
    }
}
