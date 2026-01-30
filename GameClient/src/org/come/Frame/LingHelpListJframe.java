package org.come.Frame;

import org.come.until.Music;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import com.tool.tcpimg.UIUtils;
import javax.swing.JComponent;
import javax.swing.plaf.basic.BasicInternalFrameUI;
import javax.swing.BorderFactory;
import org.come.until.FormsManagement;
import org.come.Jpanel.LingHelpListJpanel;
import java.awt.event.MouseListener;
import javax.swing.JInternalFrame;

public class LingHelpListJframe extends JInternalFrame implements MouseListener
{
    private LingHelpListJpanel lingHelpListJpanel;
    private int first_x;
    private int first_y;
    
    public static LingHelpListJframe getLingHelpListJframe() {
        return (LingHelpListJframe)FormsManagement.getInternalForm(622).getFrame();
    }
    
    public LingHelpListJframe() {
        this.lingHelpListJpanel = new LingHelpListJpanel();
        this.getContentPane().add(this.lingHelpListJpanel);
        this.setBorder(BorderFactory.createEmptyBorder());
        ((BasicInternalFrameUI)this.getUI()).setNorthPane((JComponent)null);
        this.setBounds(100, 50, 276, 441);
        this.setBackground(UIUtils.Color_BACK);
        this.pack();
        this.setVisible(false);
        this.setDefaultCloseOperation(3);
        this.addMouseListener(this);
        this.addMouseMotionListener(new MouseMotionListener() {
            @Override
            public void mouseMoved(MouseEvent e) {
            }
            
            @Override
            public void mouseDragged(MouseEvent e) {
                if (LingHelpListJframe.this.isVisible()) {
                    int x = e.getX() - LingHelpListJframe.this.first_x;
                    int y = e.getY() - LingHelpListJframe.this.first_y;
                    LingHelpListJframe.this.setBounds(x + LingHelpListJframe.this.getX(), y + LingHelpListJframe.this.getY(), LingHelpListJframe.this.getWidth(), LingHelpListJframe.this.getHeight());
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
            FormsManagement.HideForm(622);
        }
        else {
            FormsManagement.Switchinglevel(622);
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
    
    public LingHelpListJpanel getLingHelpListJpanel() {
        return this.lingHelpListJpanel;
    }
    
    public void setLingHelpListJpanel(LingHelpListJpanel lingHelpListJpanel) {
        this.lingHelpListJpanel = lingHelpListJpanel;
    }
}
