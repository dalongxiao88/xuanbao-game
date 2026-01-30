package org.come.mouslisten;

import javax.swing.Icon;
import java.awt.Color;
import javax.swing.BorderFactory;
import java.awt.event.MouseEvent;
import javax.swing.ImageIcon;
import org.come.Jpanel.QuackGameJpanel;
import java.awt.event.MouseListener;

public class QuackGameMouslisten implements MouseListener
{
    private int index;
    private QuackGameJpanel gameJpanel;
    private static ImageIcon icon;
    
    public QuackGameMouslisten(int index, QuackGameJpanel gameJpanel) {
        this.index = index;
        this.gameJpanel = gameJpanel;
    }
    
    @Override
    public void mouseClicked(MouseEvent e) {
        for (int i = 0; i < 20; ++i) {
            if (BorderFactory.createEmptyBorder() != this.gameJpanel.getLabBorder()[i].getBorder()) {
                this.gameJpanel.getLabBorder()[i].setBorder(BorderFactory.createEmptyBorder());
            }
        }
        this.gameJpanel.getLabBorder()[this.index].setBorder(BorderFactory.createLineBorder(Color.red, 1));
    }
    
    @Override
    public void mousePressed(MouseEvent e) {
    }
    
    @Override
    public void mouseReleased(MouseEvent e) {
    }
    
    @Override
    public void mouseEntered(MouseEvent e) {
        this.gameJpanel.getLabBorder()[this.index].setIcon(QuackGameMouslisten.icon);
    }
    
    @Override
    public void mouseExited(MouseEvent e) {
        this.gameJpanel.getLabBorder()[this.index].setIcon((Icon)null);
    }
    
    static {
        QuackGameMouslisten.icon = new ImageIcon(new ImageIcon("img/xy2uiimg/border_quack.png").getImage().getScaledInstance(48, 48, 10));
    }
}
