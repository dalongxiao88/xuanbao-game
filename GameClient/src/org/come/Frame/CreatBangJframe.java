package org.come.Frame;

import org.come.until.Music;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import com.tool.tcpimg.UIUtils;
import javax.swing.JComponent;
import javax.swing.plaf.basic.BasicInternalFrameUI;
import javax.swing.BorderFactory;
import org.come.until.FormsManagement;
import org.come.Jpanel.CreatBangJpanel;
import java.awt.event.MouseListener;
import javax.swing.JInternalFrame;

public class CreatBangJframe extends JInternalFrame implements MouseListener
{
    private CreatBangJpanel creatBangjpanel;
    private int first_x;
    private int first_y;
    
    public static CreatBangJframe getCreatBangjframe() {
        return (CreatBangJframe)FormsManagement.getInternalForm(25).getFrame();
    }
    
    public CreatBangJframe() throws Exception {
        this.creatBangjpanel = new CreatBangJpanel(this);
        this.getContentPane().add(this.creatBangjpanel);
        this.setBorder(BorderFactory.createEmptyBorder());
        ((BasicInternalFrameUI)this.getUI()).setNorthPane((JComponent)null);
        this.setBackground(UIUtils.Color_BACK);
        this.setBounds(380, 70, 330, 450);
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
                if (CreatBangJframe.this.isVisible()) {
                    int x = e.getX() - CreatBangJframe.this.first_x;
                    int y = e.getY() - CreatBangJframe.this.first_y;
                    CreatBangJframe.this.setBounds(x + CreatBangJframe.this.getX(), y + CreatBangJframe.this.getY(), CreatBangJframe.this.getWidth(), CreatBangJframe.this.getHeight());
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
            FormsManagement.HideForm(25);
        }
        else {
            FormsManagement.Switchinglevel(25);
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
    
    public CreatBangJpanel getJpanel() {
        return this.creatBangjpanel;
    }
    
    public void setcreatBangJframe(CreatBangJpanel creatBangjpanel) {
        this.creatBangjpanel = creatBangjpanel;
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
