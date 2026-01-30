package org.come.Jpanel;

import com.tool.tcpimg.UIUtils;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Dimension;
import javax.swing.ImageIcon;
import com.tool.btn.XYBtn;
import javax.swing.JPanel;

public class XinYuanChengShengJpane extends JPanel
{
    public static XYBtn chengsheng;
    public static XYBtn buchengsheng;
    private ImageIcon icon;
    
    public XinYuanChengShengJpane() {
        this.setPreferredSize(new Dimension(770, 480));
        this.setOpaque(false);
        this.setLayout(null);
        (XinYuanChengShengJpane.chengsheng = new XYBtn("inkImg/button1/成圣按钮.png", 1, 445, this, "一念成圣")).setBounds(445, 450, 104, 32);
        this.add(XinYuanChengShengJpane.chengsheng);
        (XinYuanChengShengJpane.buchengsheng = new XYBtn("inkImg/button1/成圣按钮.png", 1, 446, this, "心意未定")).setBounds(250, 450, 104, 32);
        this.add(XinYuanChengShengJpane.buchengsheng);
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (this.icon == null) {
            this.icon = new ImageIcon("inkImg/background1/CSZLxz.png");
        }
        g.drawImage(this.icon.getImage(), 0, 0, 770, 480, this);
        g.setColor(Color.yellow);
        g.setFont(UIUtils.TEXT_FONT2);
    }
    
    public static XYBtn getBuchengsheng() {
        return XinYuanChengShengJpane.buchengsheng;
    }
    
    public static void setBuchengsheng(XYBtn buchengsheng) {
        XinYuanChengShengJpane.buchengsheng = buchengsheng;
    }
}
