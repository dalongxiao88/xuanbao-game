package org.come.Jpanel;

import java.awt.Graphics;
import java.awt.Color;
import com.tool.tcpimg.UIUtils;
import java.awt.Dimension;
import org.come.until.CutButtonImage;
import org.come.mouslisten.AddTeXiaoMouslisten;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import com.tool.btn.SpecificBtn;
import javax.swing.JPanel;

public class DailyLoginGoodsJpanel extends JPanel
{
    private SpecificBtn btnBuy;
    private JLabel labBorder;
    private JLabel labImg;
    private String name;
    private String value;
    public ImageIcon icon;
    private AddTeXiaoMouslisten xiaoMouslisten;
    private ImageIcon iconLine;
    
    public DailyLoginGoodsJpanel() {
        this.icon = CutButtonImage.getImage("inkImg/background/8.png", 51, 51);
        this.setPreferredSize(new Dimension(250, 65));
        this.setLayout(null);
        this.setOpaque(false);
        this.setBackground(UIUtils.Color_BACK);
        this.xiaoMouslisten = new AddTeXiaoMouslisten();
        (this.labImg = new JLabel()).setBounds(21, 8, 49, 49);
        this.labImg.addMouseListener(this.xiaoMouslisten);
        this.add(this.labImg);
        (this.btnBuy = new SpecificBtn("inkImg/button/32.png", 1, "购买", 10)).setBounds(180, 20, 60, 26);
        this.btnBuy.setForeground(Color.black);
        this.add(this.btnBuy);
        (this.labBorder = new JLabel()).setBounds(20, 7, 51, 51);
        this.labBorder.setIcon(this.icon);
        this.add(this.labBorder);
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setFont(UIUtils.TEXT_FONT2);
        g.setColor(Color.white);
        g.drawString("198800仙玉", 81, 20);
        g.setColor(Color.red);
        g.drawString("￥1988元", 81, 50);
        if (this.iconLine == null) {
            this.iconLine = CutButtonImage.getImage("inkImg/background/57.png", 230, 1);
        }
        g.drawImage(this.iconLine.getImage(), 0, 0, 230, 1, this);
    }
    
    public void clearWindow() {
        this.name = "";
        this.value = "";
        this.labImg.setIcon(null);
        this.btnBuy.setBtn(0);
        this.btnBuy.setIcon(null);
        this.btnBuy.setText("");
        this.labBorder.setIcon(null);
        this.xiaoMouslisten.setEshop(null);
        this.btnBuy.setEshop(null);
    }
    
    public SpecificBtn getBtnBuy() {
        return this.btnBuy;
    }
    
    public void setBtnBuy(SpecificBtn btnBuy) {
        this.btnBuy = btnBuy;
    }
    
    public JLabel getLabImg() {
        return this.labImg;
    }
    
    public void setLabImg(JLabel labImg) {
        this.labImg = labImg;
    }
    
    @Override
    public String getName() {
        return this.name;
    }
    
    @Override
    public void setName(String name) {
        this.name = name;
    }
    
    public String getValue() {
        return this.value;
    }
    
    public void setValue(String value) {
        this.value = value;
    }
    
    public JLabel getLabBorder() {
        return this.labBorder;
    }
    
    public void setLabBorder(JLabel labBorder) {
        this.labBorder = labBorder;
    }
    
    public AddTeXiaoMouslisten getXiaoMouslisten() {
        return this.xiaoMouslisten;
    }
    
    public void setXiaoMouslisten(AddTeXiaoMouslisten xiaoMouslisten) {
        this.xiaoMouslisten = xiaoMouslisten;
    }
}
