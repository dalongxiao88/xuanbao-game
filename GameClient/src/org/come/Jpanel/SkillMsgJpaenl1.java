package org.come.Jpanel;

import org.come.until.ScrenceUntil;
import org.come.Frame.SkillMsgJframe1;
import org.come.Frame.SkillMsgJframe;
import com.tool.tcpimg.UIUtils;
import java.awt.AlphaComposite;
import java.awt.Graphics2D;
import java.awt.Graphics;
import java.awt.Dimension;
import org.come.until.CutButtonImage;
import java.awt.Color;
import org.come.bean.ImgZoom;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class SkillMsgJpaenl1 extends JPanel
{
    private JLabel mp;
    private ImgZoom imgZoom854;
    private ImgZoom imgZoom855;
    Color color;
    int bh;
    int bw;
    
    public SkillMsgJpaenl1() {
        this.imgZoom854 = CutButtonImage.cuts("resource/jiuUI/ss854.png", 14, 7, true);
        this.imgZoom855 = CutButtonImage.cuts("resource/jiuUI/ss855.png", 14, 7, true);
        this.color = Color.decode("0xCCCC99");
        this.setPreferredSize(new Dimension(420, 48));
        this.setLayout(null);
        this.setBackground(Color.BLACK);
        this.setBackground(new Color(0, 0, 0, 0));
        this.add(this.mp = new JLabel());
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D)g;
        g2d.setComposite(AlphaComposite.getInstance(3, 0.9f));
        g2d.setFont(UIUtils.TEXT_FONT15);
        g2d.setColor(Color.white);
        if (SkillMsgJframe.getSkillMsgJframe().getSkillMsgJpaenl().getType() == 1) {
            this.bh = 48;
            this.bw = 420;
            this.imgZoom854.setMiddlew(this.bw - 2 * this.imgZoom854.getEdgew());
            this.imgZoom854.setMiddleh(this.bh - 2 * this.imgZoom854.getEdgeh());
            SkillMsgJframe1.getSkillMsgJframe1().setBounds(ScrenceUntil.Screen_x / 2 - 200, 160, this.bw, this.bh);
            this.imgZoom854.draw(g2d);
        }
        else if (SkillMsgJframe.getSkillMsgJframe().getSkillMsgJpaenl().getType() == 2) {
            this.bh = 48;
            this.bw = 290;
            this.imgZoom855.setMiddlew(this.bw - 2 * this.imgZoom855.getEdgew());
            this.imgZoom855.setMiddleh(this.bh - 2 * this.imgZoom855.getEdgeh());
            SkillMsgJframe1.getSkillMsgJframe1().setBounds(ScrenceUntil.Screen_x / 2 - 100, 165, this.bw, this.bh);
            this.imgZoom855.draw(g2d);
        }
        g2d.drawString(SkillMsgJframe.getSkillMsgJframe().getSkillMsgJpaenl().getMp().getText(), 10, 30);
    }
    
    public ImgZoom getImgZoom854() {
        return this.imgZoom854;
    }
    
    public void setImgZoom854(ImgZoom imgZoom854) {
        this.imgZoom854 = imgZoom854;
    }
}
