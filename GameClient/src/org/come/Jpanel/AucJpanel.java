package org.come.Jpanel;

import java.awt.event.MouseEvent;
import com.tool.role.RoleData;
import java.awt.Graphics;
import java.awt.Font;
import java.awt.Color;
import javax.swing.BorderFactory;
import com.tool.tcpimg.UIUtils;
import java.awt.Dimension;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import org.come.entity.SellLianghaoAuc;
import org.come.entity.SellLiangHaoBase;
import com.tool.btn.LiangHaoItemBtn;
import javax.swing.JTextField;
import java.awt.event.MouseListener;
import javax.swing.JPanel;

public class AucJpanel extends JPanel implements MouseListener
{
    private JTextField aucPrice;
    private LiangHaoItemBtn btnDoAuc;
    private LiangHaoItemBtn btnNoAuc;
    private SellLiangHaoBase liangHaoItem;
    private SellLianghaoAuc selllianghaoAuc;
    private JLabel lianghao;
    private boolean isReRuc;
    ImageIcon icon;
    
    public AucJpanel() throws Exception {
        this.isReRuc = false;
        this.setPreferredSize(new Dimension(532, 149));
        this.setLayout(null);
        this.setBackground(UIUtils.Color_BACK);
        (this.aucPrice = new JTextField()).setBounds(30, 55, 290, 17);
        this.aucPrice.setOpaque(false);
        this.aucPrice.setBorder(BorderFactory.createEmptyBorder());
        this.aucPrice.setBackground(UIUtils.Color_BACK);
        this.aucPrice.setForeground(Color.WHITE);
        this.aucPrice.setCaretColor(Color.white);
        this.add(this.aucPrice);
        (this.lianghao = new JLabel()).setBounds(30, 35, 500, 15);
        this.lianghao.setForeground(Color.WHITE);
        this.lianghao.setFont(new Font("宋体", 0, 14));
        this.add(this.lianghao);
        (this.btnDoAuc = new LiangHaoItemBtn("inkImg/button1/B20.png", 1, this, 3, "竞价")).setBounds(88, 95, 59, 24);
        this.btnDoAuc.setFont(UIUtils.TXT_hyzjt16);
        this.btnDoAuc.setVerticalTextPosition(0);
        this.btnDoAuc.setHorizontalTextPosition(0);
        this.add(this.btnDoAuc);
        (this.btnNoAuc = new LiangHaoItemBtn("inkImg/button1/B20.png", 1, this, 10, "取消")).setBounds(388, 95, 59, 24);
        this.btnNoAuc.setFont(UIUtils.TXT_hyzjt16);
        this.btnNoAuc.setVerticalTextPosition(0);
        this.btnNoAuc.setHorizontalTextPosition(0);
        this.add(this.btnNoAuc);
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (this.icon == null) {
            this.icon = new ImageIcon("inkImg/background1/XJBK.png");
        }
        if (!this.isReRuc && this.liangHaoItem != null && this.liangHaoItem.getLianghao() != null) {
            this.lianghao.setText("当前竞拍靓号 " + this.liangHaoItem.getLianghao() + " , 剩余仙玉 " + RoleData.getRoleData().getLoginResult().getCodecard().toString());
        }
        else if (this.isReRuc && this.selllianghaoAuc != null && this.selllianghaoAuc.getLianghao() != null) {
            this.lianghao.setText("当前竞拍靓号 " + this.selllianghaoAuc.getLianghao() + " , 剩余仙玉 " + RoleData.getRoleData().getLoginResult().getCodecard().toString());
        }
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
    
    public JTextField getAucPrice() {
        return this.aucPrice;
    }
    
    public void setAucPrice(JTextField aucPrice) {
        this.aucPrice = aucPrice;
    }
    
    public LiangHaoItemBtn getBtnDoAuc() {
        return this.btnDoAuc;
    }
    
    public void setBtnDoAuc(LiangHaoItemBtn btnDoAuc) {
        this.btnDoAuc = btnDoAuc;
    }
    
    public LiangHaoItemBtn getBtnNoAuc() {
        return this.btnNoAuc;
    }
    
    public void setBtnNoAuc(LiangHaoItemBtn btnNoAuc) {
        this.btnNoAuc = btnNoAuc;
    }
    
    public SellLiangHaoBase getLiangHaoItem() {
        return this.liangHaoItem;
    }
    
    public void setLiangHaoItem(SellLiangHaoBase liangHaoItem) {
        this.liangHaoItem = liangHaoItem;
    }
    
    public ImageIcon getIcon() {
        return this.icon;
    }
    
    public void setIcon(ImageIcon icon) {
        this.icon = icon;
    }
    
    public SellLianghaoAuc getSelllianghaoAuc() {
        return this.selllianghaoAuc;
    }
    
    public void setSelllianghaoAuc(SellLianghaoAuc selllianghaoAuc) {
        this.selllianghaoAuc = selllianghaoAuc;
    }
    
    public boolean isReRuc() {
        return this.isReRuc;
    }
    
    public void setReRuc(boolean reRuc) {
        this.isReRuc = reRuc;
    }
}
