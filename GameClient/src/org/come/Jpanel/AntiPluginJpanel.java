package org.come.Jpanel;

import java.awt.Graphics;
import org.come.thread.TimeControlRunnable;
import org.come.until.ScrenceUntil;
import org.come.until.Util;
import org.come.until.FormsManagement;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import javax.swing.ImageIcon;
import com.tool.btn.AntiPluginBtn;
import javax.swing.JPanel;

public class AntiPluginJpanel extends JPanel
{
    private String value;
    private int sum;
    private AntiPluginBtn[] btns;
    private ImageIcon icon;
    private Font font;
    
    public AntiPluginJpanel() {
        this.value = "大";
        this.font = new Font("宋体", 0, 16);
        this.setPreferredSize(new Dimension(600, 200));
        this.setLayout(null);
        this.setBackground(new Color(0, 0, 0, 0));
        this.btns = new AntiPluginBtn[4];
        for (int i = 0; i < this.btns.length; ++i) {
            (this.btns[i] = new AntiPluginBtn("img/xy2uiimg/antipluginbtn" + (i + 1) + ".png", 1, (i == 0) ? "大" : ((i == 1) ? "话" : ((i == 2) ? "西" : "游")), this)).setBounds(39 + i * 133, 66, 123, 127);
            this.add(this.btns[i]);
        }
    }
    
    @Override
    public void show() {
        if (FormsManagement.getframe(69).isVisible()) {
            return;
        }
        FormsManagement.getframe(69).setBounds(Util.random.nextInt(ScrenceUntil.Screen_x - 600), Util.random.nextInt(ScrenceUntil.Screen_y - 200), 600, 200);
        TimeControlRunnable.JBTime = 150000;
        this.sum = 0;
        int i = Util.random.nextInt(4);
        this.value = ((i == 0) ? "大" : ((i == 1) ? "话" : ((i == 2) ? "西" : "游")));
        FormsManagement.showForm(69);
    }
    
    public void correct() {
        TimeControlRunnable.JBTime = 0;
        FormsManagement.HideForm(69);
    }
    
    public void error() {
        ++this.sum;
        if (this.sum >= 3) {
            System.exit(0);
            return;
        }
        int i = Util.random.nextInt(4);
        this.value = ((i == 0) ? "大" : ((i == 1) ? "话" : ((i == 2) ? "西" : "游")));
    }
    
    public void overtime() {
        System.exit(0);
    }
    
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (this.icon == null) {
            this.icon = new ImageIcon("img/xy2uiimg/95_png.xy2uiimg.npctalk.png");
        }
        g.drawImage(this.icon.getImage(), 0, 0, 600, 200, this);
        g.setFont(this.font);
        g.setColor(Color.white);
        g.drawString("请选择下方“", 30, 37);
        g.drawString("”字图标,", 142, 37);
        g.drawString("剩余时间:", 30, 57);
        g.setColor(Color.green);
        g.drawString(this.value, 126, 37);
        g.setColor(Color.red);
        g.drawString(TimeControlRunnable.JBTime / 1000 + "s", 120, 57);
        g.drawString("您的信任与支持，是我们前进最大的动力！", 238, 37);
        if (this.sum != 0) {
            g.drawString("你已经选错" + this.sum + "次,选错3次关闭游戏", 238, 57);
        }
    }
    
    public String getValue() {
        return this.value;
    }
    
    public void setValue(String value) {
        this.value = value;
    }
}
