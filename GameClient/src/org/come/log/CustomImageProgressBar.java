package org.come.log;

import java.awt.Image;
import java.awt.AlphaComposite;
import java.awt.RenderingHints;
import java.awt.Graphics2D;
import java.awt.Graphics;
import java.io.InputStream;
import java.io.IOException;
import com.tool.tcp.SpriteFactory;
import com.tool.tcp.Sprite;
import javax.swing.JPanel;

public class CustomImageProgressBar extends JPanel
{
    private int progress;
    private int imageHeight;
    private Sprite sgbj;
    
    public CustomImageProgressBar(Integer imageHeight) {
        this.progress = 0;
        try {
            InputStream input = CustomImageProgressBar.class.getResource("黄色石头背景").openStream();
            this.sgbj = SpriteFactory.VloadSprite1("inkImg/background/黄色石头背景.tcp", input, "1|0|256|100|468|0|101|0|343|0|10|11");
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        this.imageHeight = (int)imageHeight;
        this.setOpaque(false);
    }
    
    public void setProgress(int progress) {
        if (progress < 0) {
            progress = 0;
        }
        else if (progress > 100) {
            progress = 100;
        }
        this.progress = progress;
        this.repaint();
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        this.sgbj.updateToTime(System.currentTimeMillis(), 0);
        Image image = this.sgbj.draw1(g, 0, 0);
        if (image != null) {
            Graphics2D g2d = (Graphics2D)g;
            g2d.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
            g2d.setComposite(AlphaComposite.getInstance(3, 0.55f));
            int x = 10;
            int y = 1;
            int width = 100;
            int height = 60;
            g2d.drawImage(image, 0, 100 - LanderJPanel.loadRaio, this.getWidth(), this.getHeight(), 0, 100 - LanderJPanel.loadRaio, this.getWidth(), this.getHeight(), this);
        }
    }
}
