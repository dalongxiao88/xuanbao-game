package org.come.Frame;

import org.come.until.Music;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import com.tool.tcpimg.UIUtils;
import javax.swing.JComponent;
import javax.swing.plaf.basic.BasicInternalFrameUI;
import javax.swing.BorderFactory;
import org.come.until.FormsManagement;
import org.come.Jpanel.Change_titleJpanel;
import java.awt.event.MouseListener;
import javax.swing.JInternalFrame;

public class Change_titleJframe extends JInternalFrame implements MouseListener
{
    private Change_titleJpanel change_titlejpanel;
    private int first_x;
    private int first_y;
    
    public static Change_titleJframe getChange_titlejframe() {
        return (Change_titleJframe)FormsManagement.getInternalForm(10).getFrame();
    }
    
    public Change_titleJframe() throws Exception {
        this.change_titlejpanel = new Change_titleJpanel(this);
        this.getContentPane().add(this.change_titlejpanel);
        this.setBorder(BorderFactory.createEmptyBorder());
        ((BasicInternalFrameUI)this.getUI()).setNorthPane((JComponent)null);
        this.setBounds(400, 120, 292, 395);
        this.pack();
        this.setDefaultCloseOperation(3);
        this.setVisible(false);
        this.setBackground(UIUtils.Color_BACK);
        this.addMouseListener(this);
        this.addMouseMotionListener(new MouseMotionListener() {
            @Override
            public void mouseMoved(MouseEvent e) {
            }
            
            @Override
            public void mouseDragged(MouseEvent e) {
                if (Change_titleJframe.this.isVisible()) {
                    int x = e.getX() - Change_titleJframe.this.first_x;
                    int y = e.getY() - Change_titleJframe.this.first_y;
                    Change_titleJframe.this.setBounds(x + Change_titleJframe.this.getX(), y + Change_titleJframe.this.getY(), Change_titleJframe.this.getWidth(), Change_titleJframe.this.getHeight());
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
            FormsManagement.HideForm(10);
        }
        else {
            FormsManagement.Switchinglevel(10);
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
    
    public Change_titleJpanel getChange_titlejpanel() {
        return this.change_titlejpanel;
    }
    
    public void setChange_titlejpanel(Change_titleJpanel change_titlejpanel) {
        this.change_titlejpanel = change_titlejpanel;
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
