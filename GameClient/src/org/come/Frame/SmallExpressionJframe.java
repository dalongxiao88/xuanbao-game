package org.come.Frame;

import org.come.until.Music;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.awt.Color;
import javax.swing.plaf.basic.BasicInternalFrameUI;
import javax.swing.BorderFactory;
import org.come.until.FormsManagement;
import org.come.Jpanel.SmallExpressionJpanel;
import java.awt.event.MouseListener;
import javax.swing.JInternalFrame;

public class SmallExpressionJframe extends JInternalFrame implements MouseListener
{
    private SmallExpressionJpanel smallExpressionJpanel;
    private int first_x;
    private int first_y;
    
    public static SmallExpressionJframe getSmallExpressionJframe() {
        return (SmallExpressionJframe)FormsManagement.getInternalForm(52).getFrame();
    }
    
    public SmallExpressionJframe() throws Exception {
        this.smallExpressionJpanel = new SmallExpressionJpanel();
        this.getContentPane().add(this.smallExpressionJpanel);
        this.setBorder(BorderFactory.createEmptyBorder());
        ((BasicInternalFrameUI)this.getUI()).setNorthPane(null);
        this.setBounds(270, 150, 504, 348);
        this.setBackground(new Color(0, 0, 0, 0));
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
                if (SmallExpressionJframe.this.isVisible()) {
                    int x = e.getX() - SmallExpressionJframe.this.first_x;
                    int y = e.getY() - SmallExpressionJframe.this.first_y;
                    SmallExpressionJframe.this.setBounds(x + SmallExpressionJframe.this.getX(), y + SmallExpressionJframe.this.getY(), SmallExpressionJframe.this.getWidth(), SmallExpressionJframe.this.getHeight());
                }
            }
        });
    }
    
    @Override
    public void mouseClicked(MouseEvent e) {
    }
    
    @Override
    public void mousePressed(MouseEvent e) {
        Music.addyinxiao("关闭窗口.mp3");
        if (e.isMetaDown()) {
            FormsManagement.HideForm(52);
        }
        else {
            FormsManagement.Switchinglevel(52);
        }
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
    
    public SmallExpressionJpanel getSmallExpressionJpanel() {
        return this.smallExpressionJpanel;
    }
    
    public void setSmallExpressionJpanel(SmallExpressionJpanel smallExpressionJpanel) {
        this.smallExpressionJpanel = smallExpressionJpanel;
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
