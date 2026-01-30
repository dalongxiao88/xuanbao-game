package org.come.Jpanel;

import java.awt.Color;
import java.awt.Graphics;
import org.come.until.CutButtonImage;
import com.tool.tcpimg.UIUtils;
import java.awt.Dimension;
import com.updateNew.MyIsif;
import org.come.mouslisten.AddTeXiaoMouslisten;
import javax.swing.JLabel;
import com.tool.btn.SpecificBtn;
import javax.swing.JPanel;

public class ShowImageJpanel extends JPanel
{
    private SpecificBtn btnBuy;// 购买按钮
    private JLabel labBorder;// 背景框
    private JLabel labImg;// 特效图片展示
    private String name;// 特效名字
    private String value;// 价值多少仙玉
    private AddTeXiaoMouslisten xiaoMouslisten;
    
    public ShowImageJpanel() {
        if (MyIsif.getStyle().equals("水墨")) {
            this.setPreferredSize(new Dimension(120, 155));
            this.setLayout(null);
            this.setBackground(UIUtils.Color_BACK);
            this.xiaoMouslisten = new AddTeXiaoMouslisten();
            // 特效图片展示
            (this.labImg = new JLabel()).setBounds(2, 2, 120, 120);
            this.labImg.addMouseListener(this.xiaoMouslisten);
            this.add(this.labImg);
            // 背景框
            (this.labBorder = new JLabel()).setBounds(0, 0, 120, 120);
            this.labBorder.setIcon(CutButtonImage.getImage("inkImg/background/3.png", -1, -1));
            this.add(this.labBorder);
            (this.btnBuy = new SpecificBtn("inkImg/button/2.png", 1, "购买", 10)).setBounds(86, 138, 34, 17);
            this.add(this.btnBuy);
        }
        else {
            this.setPreferredSize(new Dimension(120, 155));
            this.setLayout(null);
            this.setBackground(UIUtils.Color_BACK);
            this.xiaoMouslisten = new AddTeXiaoMouslisten();
            (this.labImg = new JLabel()).setBounds(10, 0, 120, 120);
            this.labImg.addMouseListener(this.xiaoMouslisten);
            this.add(this.labImg);
            (this.labBorder = new JLabel()).setBounds(0, 0, 120, 120);
            this.labBorder.setIcon(CutButtonImage.getImage("img/xy2uiimg/物品框_120×120.png", -1, -1));
            this.add(this.labBorder);
            (this.btnBuy = new SpecificBtn("inkImg/hongmu/21_png.button.tab_nex.png", 1, "购买", 10)).setBounds(86, 138, 34, 17);
            this.add(this.btnBuy);
        }
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        if (MyIsif.getStyle().equals("水墨")) {
            super.paintComponent(g);
            g.setFont(UIUtils.TEXT_FONT);
            g.setColor(Color.BLACK);
            if (this.name != null) {
                g.drawString(this.name, 0, 134);
            }
            g.setColor(Color.red);
            if (this.value != null) {
                g.drawString(this.value, 0, 149);
            }
        }
        else {
            super.paintComponent(g);
            g.setFont(UIUtils.TEXT_FONT);
            g.setColor(Color.white);
            if (this.name != null) {
                g.drawString(this.name, 0, 134);
            }
            g.setColor(Color.red);
            if (this.value != null) {
                g.drawString(this.value, 0, 149);
            }
        }
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
