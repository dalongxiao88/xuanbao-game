package org.come.Frame;

import org.come.until.Music;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import com.tool.tcpimg.UIUtils;
import javax.swing.JComponent;
import javax.swing.plaf.basic.BasicInternalFrameUI;
import javax.swing.BorderFactory;
import org.come.until.FormsManagement;
import org.come.Jpanel.GemMakeJpanel;
import java.awt.event.MouseListener;
import javax.swing.JInternalFrame;

public class GemMakeFrame extends JInternalFrame implements MouseListener
{
    private GemMakeJpanel jpanel;
    private int first_x;
    private int first_y;
    
    public static GemMakeFrame getGemMakeFrame() {
        return (GemMakeFrame)FormsManagement.getInternalForm(84).getFrame();
    }
    
    public GemMakeFrame() throws Exception {
        this.jpanel = new GemMakeJpanel();
        this.getContentPane().add(this.jpanel);
        this.setBorder(BorderFactory.createEmptyBorder());
        ((BasicInternalFrameUI)this.getUI()).setNorthPane((JComponent)null);
        this.setBounds(100, 100, 526, 397);
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
                if (GemMakeFrame.this.isVisible()) {
                    int x = e.getX() - GemMakeFrame.this.first_x;
                    int y = e.getY() - GemMakeFrame.this.first_y;
                    GemMakeFrame.this.setBounds(x + GemMakeFrame.this.getX(), y + GemMakeFrame.this.getY(), GemMakeFrame.this.getWidth(), GemMakeFrame.this.getHeight());
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
            FormsManagement.HideForm(84);
        }
        else {
            FormsManagement.Switchinglevel(84);
        }
        this.first_x = e.getX();
        this.first_y = e.getY();
    }
    
    @Override
    public void mouseEntered(MouseEvent e) {
    }
    
    @Override
    public void mouseExited(MouseEvent e) {
    }
    
    @Override
    public void mouseReleased(MouseEvent e) {
    }
    
    public GemMakeJpanel getJpanel() {
        return this.jpanel;
    }
    
    public void setJpanel(GemMakeJpanel jpanel) {
        this.jpanel = jpanel;
    }
}
