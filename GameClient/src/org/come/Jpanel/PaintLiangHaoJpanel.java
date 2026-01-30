package org.come.Jpanel;

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
import com.tool.btn.LiangHaoPaiBtn;
import com.tool.btn.LiangHaoItemBtn;
import javax.swing.JPanel;

public class PaintLiangHaoJpanel extends JPanel
{
    private LiangHaoItemBtn paint1;
    private LiangHaoItemBtn paint2;
    private LiangHaoItemBtn paint3;
    private LiangHaoItemBtn paint4;
    private LiangHaoPaiBtn sureBtn;
    private JLabel labOwnXy;
    private JLabel labPayXy;
    private JLabel text;
    private int selectType;
    private ImageIcon iconBack;
    
    public PaintLiangHaoJpanel() {
        this.selectType = 5;
        this.setPreferredSize(new Dimension(410, 398));
        this.setLayout(null);
        this.setBackground(UIUtils.Color_BACK);
        FormsOnOffBtn offBtn = new FormsOnOffBtn("inkImg/button/1.png", 1, 705);
        offBtn.setBounds(375, 10, 25, 25);
        this.add(offBtn);
        (this.paint1 = new LiangHaoItemBtn("inkImg/button1/B34.png", 1, this, 50, "水波蓝玉")).setColors(UIUtils.COLOR_WHITE);
        this.paint1.setBounds(60, 240, 68, 17);
        this.add(this.paint1);
        (this.paint2 = new LiangHaoItemBtn("inkImg/button1/B34.png", 1, this, 51, "青玉红沁")).setColors(UIUtils.COLOR_WHITE);
        this.paint2.setBounds(138, 240, 68, 17);
        this.add(this.paint2);
        (this.paint3 = new LiangHaoItemBtn("inkImg/button1/B34.png", 1, this, 52, "梅枝白玉")).setColors(UIUtils.COLOR_WHITE);
        this.paint3.setBounds(216, 240, 68, 17);
        this.add(this.paint3);
        (this.paint4 = new LiangHaoItemBtn("inkImg/button1/B34.png", 1, this, 53, "溢彩流光")).setColors(UIUtils.COLOR_WHITE);
        this.paint4.setBounds(294, 240, 68, 17);
        this.add(this.paint4);
        (this.sureBtn = new LiangHaoPaiBtn("inkImg/button1/B22.png", 1, "确定染色", Integer.valueOf(4), this)).setBounds(200, 350, 99, 24);
        this.add(this.sureBtn);
        (this.text = new JLabel()).setBounds(60, 218, 400, 15);
        this.text.setForeground(Color.BLACK);
        this.text.setText("<html><font color='black'>染色后</font><font color='red'>开通频道显示</font><font color='black'>可类似土豪金显示</font></html>");
        this.text.setFont(new Font("宋体", 0, 13));
        this.add(this.text);
        (this.labOwnXy = new JLabel()).setBounds(180, 316, 120, 15);
        this.labOwnXy.setForeground(Color.WHITE);
        this.labOwnXy.setFont(new Font("宋体", 0, 16));
        this.add(this.labOwnXy);
        (this.labPayXy = new JLabel()).setBounds(180, 286, 120, 15);
        this.labPayXy.setForeground(Color.WHITE);
        this.labPayXy.setFont(new Font("宋体", 0, 16));
        this.labPayXy.setText("100");
        this.add(this.labPayXy);
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (this.iconBack == null) {
            this.iconBack = new ImageIcon("inkImg/background1/paintlh.png");
        }
        g.drawImage(this.iconBack.getImage(), 0, 0, 410, 398, this);
        this.labOwnXy.setText(String.valueOf(RoleData.getRoleData().getLoginResult().getCodecard().longValue()));
        LoginResult login = RoleData.getRoleData().getLoginResult();
        String num = login.getLiangHao();
        Integer type = Integer.valueOf((login.getLianghaotype() == null) ? 0 : ((int)login.getLianghaotype()));
        if (StringUtils.isNotEmpty(num)) {
            String[] nums = num.split("");
            for (int j = 0; j < nums.length; ++j) {
                int xnum = 175 + 13 * j;
                int ynum = 110;
                g.drawImage(CutButtonImage.getImage("inkImg/number/" + String.valueOf(this.selectType) + nums[j] + ".png", 12, 16).getImage(), xnum, ynum, 12, 16, this);
            }
        }
    }
    
    public ImageIcon getIconBack() {
        return this.iconBack;
    }
    
    public void setIconBack(ImageIcon iconBack) {
        this.iconBack = iconBack;
    }
    
    public LiangHaoItemBtn getPaint1() {
        return this.paint1;
    }
    
    public void setPaint1(LiangHaoItemBtn paint1) {
        this.paint1 = paint1;
    }
    
    public LiangHaoItemBtn getPaint2() {
        return this.paint2;
    }
    
    public void setPaint2(LiangHaoItemBtn paint2) {
        this.paint2 = paint2;
    }
    
    public LiangHaoItemBtn getPaint3() {
        return this.paint3;
    }
    
    public void setPaint3(LiangHaoItemBtn paint3) {
        this.paint3 = paint3;
    }
    
    public LiangHaoItemBtn getPaint4() {
        return this.paint4;
    }
    
    public void setPaint4(LiangHaoItemBtn paint4) {
        this.paint4 = paint4;
    }
    
    public LiangHaoPaiBtn getSureBtn() {
        return this.sureBtn;
    }
    
    public void setSureBtn(LiangHaoPaiBtn sureBtn) {
        this.sureBtn = sureBtn;
    }
    
    public JLabel getLabOwnXy() {
        return this.labOwnXy;
    }
    
    public void setLabOwnXy(JLabel labOwnXy) {
        this.labOwnXy = labOwnXy;
    }
    
    public JLabel getLabPayXy() {
        return this.labPayXy;
    }
    
    public void setLabPayXy(JLabel labPayXy) {
        this.labPayXy = labPayXy;
    }
    
    public int getSelectType() {
        return this.selectType;
    }
    
    public void setSelectType(int selectType) {
        this.selectType = selectType;
    }
}
