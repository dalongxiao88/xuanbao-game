package org.come.Jpanel;

import java.awt.event.MouseEvent;
import com.tool.role.RoleData;
import java.awt.Graphics;
import java.awt.Font;
import java.awt.Color;
import javax.swing.BorderFactory;
import com.tool.tcpimg.UIUtils;
import java.awt.Dimension;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import com.tool.btn.LiangHaoItemBtn;
import javax.swing.JTextField;
import java.awt.event.MouseListener;
import javax.swing.JPanel;

public class XinJianJpanel extends JPanel implements MouseListener
{
    private JTextField xNum;
    private LiangHaoItemBtn btnDo;
    private LiangHaoItemBtn btnNo;
    ImageIcon icon;
    private JLabel text;
    
    public XinJianJpanel() throws Exception {
        this.setPreferredSize(new Dimension(532, 149));
        this.setLayout(null);
        this.setBackground(UIUtils.Color_BACK);
        (this.xNum = new JTextField()).setBounds(30, 55, 290, 17);
        this.xNum.setOpaque(false);
        this.xNum.setBorder(BorderFactory.createEmptyBorder());
        this.xNum.setBackground(UIUtils.Color_BACK);
        this.xNum.setForeground(Color.WHITE);
        this.xNum.setCaretColor(Color.white);
        this.add(this.xNum);
        (this.text = new JLabel()).setBounds(30, 35, 500, 15);
        this.text.setForeground(Color.WHITE);
        this.text.setFont(new Font("宋体", 0, 14));
        this.add(this.text);
        (this.btnDo = new LiangHaoItemBtn("inkImg/button1/B20.png", 1, this, 6, "确定")).setBounds(88, 95, 59, 24);
        this.btnDo.setFont(UIUtils.TXT_hyzjt16);
        this.btnDo.setVerticalTextPosition(0);
        this.btnDo.setHorizontalTextPosition(0);
        this.add(this.btnDo);
        (this.btnNo = new LiangHaoItemBtn("inkImg/button1/B20.png", 1, this, 9, "取消")).setBounds(388, 95, 59, 24);
        this.btnNo.setFont(UIUtils.TXT_hyzjt16);
        this.btnNo.setVerticalTextPosition(0);
        this.btnNo.setHorizontalTextPosition(0);
        this.add(this.btnNo);
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (this.icon == null) {
            this.icon = new ImageIcon("inkImg/background1/XJBK.png");
        }
        this.text.setText("可以自选4到6位的任意靓号 , 剩余仙玉 " + RoleData.getRoleData().getLoginResult().getCodecard().toString());
        g.drawImage(this.icon.getImage(), 0, 0, 532, 149, this);
    }
    
    @Override
    public void mouseClicked(MouseEvent e) {
    }
    
    @Override
    public void mousePressed(MouseEvent e) {
    }
    
    @Override
    public void mouseReleased(MouseEvent e) {
    }
    
    @Override
    public void mouseEntered(MouseEvent e) {
    }
    
    @Override
    public void mouseExited(MouseEvent e) {
    }
    
    public JTextField getxNum() {
        return this.xNum;
    }
    
    public void setxNum(JTextField xNum) {
        this.xNum = xNum;
    }
    
    public LiangHaoItemBtn getBtnDo() {
        return this.btnDo;
    }
    
    public void setBtnDo(LiangHaoItemBtn btnDo) {
        this.btnDo = btnDo;
    }
    
    public LiangHaoItemBtn getBtnNo() {
        return this.btnNo;
    }
    
    public void setBtnNo(LiangHaoItemBtn btnNo) {
        this.btnNo = btnNo;
    }
    
    public ImageIcon getIcon() {
        return this.icon;
    }
    
    public void setIcon(ImageIcon icon) {
        this.icon = icon;
    }
}
