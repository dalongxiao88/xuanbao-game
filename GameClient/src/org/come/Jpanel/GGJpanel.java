package org.come.Jpanel;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Arrays;
import io.netty.util.internal.StringUtil;
import org.come.socket.DownLoadTxt;
import java.util.List;
import java.awt.Dimension;
import com.tool.tcpimg.UIUtils;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import com.tool.tcpimg.ChatBox;
import javax.swing.JPanel;

public class GGJpanel extends JPanel
{
    ChatBox box;
    private ImageIcon icon;
    private static JLabel labtext1;
    private static JLabel labtext2;
    
    public GGJpanel() {
        this.box = new ChatBox();
        this.box = new ChatBox();
        List<String> sb = this.getGG();
        for (int i = 0; i < sb.size(); ++i) {
            try {
                this.box.addText((String)sb.get(i), 600, UIUtils.TEXT_MSG);
            }
            catch (Exception e) {}
        }
        this.setPreferredSize(new Dimension(682, 475));
        this.setBackground(UIUtils.Color_BACK);
    }
    
    public static void main(String[] args) {
        List<String> list = new GGJpanel().getGG();
        System.out.println(list);
    }
    
    public List<String> getGG() {
        String v = DownLoadTxt.getDownLoadTxt().GetServerTxt("gg.txt");
        if (v != null && v.length() != 0) {
            String[] vs = v.split("\r\n");
            if (vs.length == 1) {
                vs = v.split("\n");
            }
            for (int i = 0; i < vs.length; ++i) {
                if (StringUtil.isNullOrEmpty(vs[i])) {
                    vs[i] = " ";
                }
            }
            return Arrays.asList(vs);
        }
        else {
            return new ArrayList<>();
        }
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (this.icon == null) {
            this.icon = new ImageIcon("inkImg/background1/gonggao.png");
        }
        g.drawImage(this.icon.getImage(), 0, 0, 682, 475, this);
        Graphics g2 = g.create(50, 45, 675, 475);
        this.box.paint(g2);
        g2.dispose();
    }
}
