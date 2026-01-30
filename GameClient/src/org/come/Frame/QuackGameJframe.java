package org.come.Frame;

import org.come.until.Music;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import javax.swing.JComponent;
import javax.swing.plaf.basic.BasicInternalFrameUI;
import javax.swing.BorderFactory;
import org.come.until.FormsManagement;
import org.come.Jpanel.QuackGameJpanel;
import java.awt.event.MouseListener;
import javax.swing.JInternalFrame;

public class QuackGameJframe extends JInternalFrame implements MouseListener
{
    private QuackGameJpanel gameJpanel;
    private int first_x;
    private int first_y;
    
    public static QuackGameJframe getQuackGameJframe() {
        return (QuackGameJframe)FormsManagement.getInternalForm(71).getFrame();
    }
    
    public QuackGameJframe() {
        this.setContentPane(this.gameJpanel = new QuackGameJpanel());
        this.setBorder(BorderFactory.createEmptyBorder());
        ((BasicInternalFrameUI)this.getUI()).setNorthPane((JComponent)null);
        this.setBounds(250, 100, 366, 527);
        this.setOpaque(false);
        this.pack();
        this.setDefaultCloseOperation(3);
        this.setVisible(false);
        this.addMouseListener(this);
        this.addMouseMotionListener(new MouseMotionListener() {
            @Override
            public void mouseMoved(MouseEvent e) {
            }
            
            @Override
            public void mouseDragged(MouseEvent e) {
                if (QuackGameJframe.this.isVisible()) {
                    int x = e.getX() - QuackGameJframe.this.first_x;
                    int y = e.getY() - QuackGameJframe.this.first_y;
                    QuackGameJframe.this.setBounds(x + QuackGameJframe.this.getX(), y + QuackGameJframe.this.getY(), QuackGameJframe.this.getWidth(), QuackGameJframe.this.getHeight());
                }
            }
        });
    }
    
    @Override
    public void mouseClicked(MouseEvent e) {
    }
    
    @Override
    public void mousePressed(MouseEvent e) {
        if (e.isMetaDown()) {
            FormsManagement.HideForm(71);
        }
        else {
            FormsManagement.Switchinglevel(71);
        }
        Music.addyinxiao("关闭窗口.mp3");
        this.first_x = e.getX();
        this.first_y = e.getY();
    }
    
    @Override
    public void mouseReleased(MouseEvent e) {
    }
    
    @Override
    public void mouseEntered(MouseEvent e) {
    }
    
    @Override
    public void mouseExited(MouseEvent e) {
    }
    
    public QuackGameJpanel getGameJpanel() {
        return this.gameJpanel;
    }
    
    public void setGameJpanel(QuackGameJpanel gameJpanel) {
        this.gameJpanel = gameJpanel;
    }
    
    public int getFirst_x() {
        return this.first_x;
    }
    
    public void setFirst_x(int first_x) {
        this.first_x = first_x;
    }
    
    public int getFirst_y() {
        return this.first_y;
    }
    
    public void setFirst_y(int first_y) {
        this.first_y = first_y;
    }
}
