package org.come.Frame;

import org.come.until.Music;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import com.tool.tcpimg.UIUtils;
import javax.swing.JComponent;
import javax.swing.plaf.basic.BasicInternalFrameUI;
import javax.swing.BorderFactory;
import org.come.until.FormsManagement;
import org.come.Jpanel.SeventyTwoChangesJpanel;
import java.awt.event.MouseListener;
import javax.swing.JInternalFrame;

public class SeventyTwoChangesJframe extends JInternalFrame implements MouseListener
{
    private SeventyTwoChangesJpanel seventyTwoChangesJpanel;
    private int first_x;
    private int first_y;
    
    public static SeventyTwoChangesJframe getSeventyTwoChangesJframe() {
        return (SeventyTwoChangesJframe)FormsManagement.getInternalForm(89).getFrame();
    }
    
    public SeventyTwoChangesJframe() throws Exception {
        this.seventyTwoChangesJpanel = new SeventyTwoChangesJpanel();
        this.getContentPane().add(this.seventyTwoChangesJpanel);
        this.setBorder(BorderFactory.createEmptyBorder());
        ((BasicInternalFrameUI)this.getUI()).setNorthPane((JComponent)null);
        this.setBounds(40, 100, 676, 467);
        this.pack();
        this.setBackground(UIUtils.Color_BACK);
        this.setDefaultCloseOperation(3);
        this.setVisible(false);
        this.addMouseListener(this);
        this.addMouseMotionListener(new MouseMotionListener() {
            @Override
            public void mouseMoved(MouseEvent e) {
            }
            
            @Override
            public void mouseDragged(MouseEvent e) {
                if (SeventyTwoChangesJframe.this.isVisible()) {
                    int x = e.getX() - SeventyTwoChangesJframe.this.first_x;
                    int y = e.getY() - SeventyTwoChangesJframe.this.first_y;
                    SeventyTwoChangesJframe.this.setBounds(x + SeventyTwoChangesJframe.this.getX(), y + SeventyTwoChangesJframe.this.getY(), SeventyTwoChangesJframe.this.getWidth(), SeventyTwoChangesJframe.this.getHeight());
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
            FormsManagement.HideForm(89);
        }
        else {
            FormsManagement.Switchinglevel(89);
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
    
    public SeventyTwoChangesJpanel getSeventyTwoChangesJpanel() {
        return this.seventyTwoChangesJpanel;
    }
    
    public void setSeventyTwoChangesJpanel(SeventyTwoChangesJpanel seventyTwoChangesJpanel) {
        this.seventyTwoChangesJpanel = seventyTwoChangesJpanel;
    }
}
