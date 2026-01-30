package org.come.Jpanel;

import come.tool.JDialog.TiShiUtil;
import come.tool.JDialog.TiShiChuLi;
import org.come.until.FormsManagement;
import java.awt.Graphics;
import com.tool.tcpimg.UIUtils;
import java.awt.Dimension;
import com.updateNew.MyIsif;
import javax.swing.ImageIcon;
import com.tool.btn.OptionsBtn;
import com.tool.tcpimg.RichLabel;
import javax.swing.JPanel;

public class OptionsJpanel extends JPanel
{
    private RichLabel richLabel;
    private OptionsBtn btn1;
    private OptionsBtn btn2;
    private String type;
    private Object object;
    private ImageIcon icon;
    
    public OptionsJpanel() {
        if (MyIsif.getStyle().equals("水墨")) {
            this.setPreferredSize(new Dimension(534, 144));
            this.setLayout(null);
            this.setBackground(UIUtils.Color_BACK);
            (this.btn1 = new OptionsBtn("inkImg/button1/B20.png", 1, "确定", this, null)).setBounds(119, 105, 59, 24);
            this.add(this.btn1);
            (this.btn2 = new OptionsBtn("inkImg/button1/B20.png", 1, "取消", this, null)).setBounds(347, 105, 59, 24);
            this.add(this.btn2);
        }
        else {
            this.setPreferredSize(new Dimension(534, 145));
            this.setLayout(null);
            this.setBackground(UIUtils.Color_BACK);
            (this.btn1 = new OptionsBtn("inkImg/hongmu/a1.png", 1, "确定", this)).setBounds(119, 105, 60, 30);
            this.add(this.btn1);
            (this.btn2 = new OptionsBtn("inkImg/hongmu/a1.png", 1, "取消", this)).setBounds(347, 105, 60, 30);
            this.add(this.btn2);
        }
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (MyIsif.getStyle().equals("水墨")) {
            if (this.icon == null) {
                this.icon = new ImageIcon("inkImg/background1/B234.png");
            }
            g.drawImage(this.icon.getImage(), 0, 0, null);
            if (this.richLabel != null) {
                g.translate(40, 35);
                this.richLabel.paint(g);
                g.translate(-40, -35);
            }
            else {
                this.richLabel = new RichLabel("xxxxxxxx", UIUtils.TEXT_NAME_FONT, 454);
            }
        }
        else {
            if (this.icon == null) {
                this.icon = new ImageIcon("inkImg/hongmu/95_png.xy2uiimg.npctalk.png");
            }
            g.drawImage(this.icon.getImage(), 0, 0, null);
            if (this.richLabel != null) {
                g.translate(40, 35);
                this.richLabel.paint(g);
                g.translate(-40, -35);
            }
            else {
                this.richLabel = new RichLabel("xxxxxxxx", UIUtils.TEXT_NAME_FONT, 454);
            }
        }
    }
    
    public void showBox(String type, Object object, String text) {
        this.type = type;
        this.object = object;
        if (this.richLabel == null) {
            this.richLabel = new RichLabel(text, UIUtils.TEXT_NAME_FONT, 454);
        }
        else {
            this.richLabel.setTextSize(text, 454);
        }
        FormsManagement.showForm(104);
    }
    
    public void tipBox(boolean is) {
        FormsManagement.HideForm(104);
        if (this.type == null) {
            return;
        }
        TiShiChuLi chuLi = (TiShiChuLi)TiShiUtil.tishiMap.get(this.type);
        if (chuLi != null) {
            chuLi.tipBox(is, this.object);
        }
    }
}
