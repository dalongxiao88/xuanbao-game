package org.come.Frame;

import java.awt.Graphics;
import java.awt.Image;
import org.come.until.ScrenceUntil;
import org.come.until.flyunit;
import javax.swing.JFrame;

public class FlyJram extends JFrame
{
    public FlyJram() throws Exception {
        Image image = flyunit.image;
        this.setSize(ScrenceUntil.Screen_x, ScrenceUntil.Screen_y);
        this.setUndecorated(true);
        this.setVisible(true);
    }
    
    @Override
    public void printComponents(Graphics g) {
        super.printComponents(g);
        g.drawImage(flyunit.image, ScrenceUntil.Screen_x, ScrenceUntil.Screen_y, null);
    }
}
