package org.come.Frame;

import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import javax.swing.JComponent;
import javax.swing.plaf.basic.BasicInternalFrameUI;
import javax.swing.BorderFactory;
import org.come.until.FormsManagement;
import org.come.Jpanel.AntiPluginJpanel;
import java.awt.event.MouseListener;
import javax.swing.JInternalFrame;

public class AntiPluginJframe extends JInternalFrame implements MouseListener
{
    private AntiPluginJpanel pluginJpanel;
    private int first_x;
    private int first_y;
    
    public static AntiPluginJframe getAntiPluginJframe() {
        return (AntiPluginJframe)FormsManagement.getInternalForm(69).getFrame();
    }
    
    public AntiPluginJframe() {
        this.setContentPane(this.pluginJpanel = new AntiPluginJpanel());
        this.setBorder(BorderFactory.createEmptyBorder());
        ((BasicInternalFrameUI)this.getUI()).setNorthPane((JComponent)null);
        this.setBounds(100, 150, 600, 200);
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
                if (AntiPluginJframe.this.isVisible()) {
                    int x = e.getX() - AntiPluginJframe.this.first_x;
                    int y = e.getY() - AntiPluginJframe.this.first_y;
                    AntiPluginJframe.this.setBounds(x + AntiPluginJframe.this.getX(), y + AntiPluginJframe.this.getY(), AntiPluginJframe.this.getWidth(), AntiPluginJframe.this.getHeight());
                }
            }
        });
    }
    
    @Override
    public void mouseClicked(MouseEvent e) {
    }
    
    @Override
    public void mousePressed(MouseEvent e) {
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
    
    public AntiPluginJpanel getPluginJpanel() {
        return this.pluginJpanel;
    }
    
    public void setPluginJpanel(AntiPluginJpanel pluginJpanel) {
        this.pluginJpanel = pluginJpanel;
    }
}
