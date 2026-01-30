package org.come.Frame;

import org.come.until.Music;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import javax.swing.JComponent;
import javax.swing.plaf.basic.BasicInternalFrameUI;
import javax.swing.BorderFactory;
import org.come.until.FormsManagement;
import org.come.Jpanel.RuneOperateJpanel;
import java.awt.event.MouseListener;
import javax.swing.JInternalFrame;

public class RuneOperateJframe extends JInternalFrame implements MouseListener
{
    private RuneOperateJpanel operateJpanel;
    private int first_x;
    private int first_y;
    
    public static RuneOperateJframe getRuneOperateJframe() {
        return (RuneOperateJframe)FormsManagement.getInternalForm(70).getFrame();
    }
    
    public RuneOperateJframe() {
        this.setContentPane(this.operateJpanel = new RuneOperateJpanel());
        this.setBorder(BorderFactory.createEmptyBorder());
        ((BasicInternalFrameUI)this.getUI()).setNorthPane((JComponent)null);
        this.setBounds(220, 50, 366, 527);
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
                if (RuneOperateJframe.this.isVisible()) {
                    int x = e.getX() - RuneOperateJframe.this.first_x;
                    int y = e.getY() - RuneOperateJframe.this.first_y;
                    RuneOperateJframe.this.setBounds(x + RuneOperateJframe.this.getX(), y + RuneOperateJframe.this.getY(), RuneOperateJframe.this.getWidth(), RuneOperateJframe.this.getHeight());
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
            FormsManagement.HideForm(70);
        }
        else {
            FormsManagement.Switchinglevel(70);
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
    
    public RuneOperateJpanel getOperateJpanel() {
        return this.operateJpanel;
    }
    
    public void setOperateJpanel(RuneOperateJpanel operateJpanel) {
        this.operateJpanel = operateJpanel;
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
