package org.come.Frame;

import org.come.until.Music;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import com.tool.tcpimg.UIUtils;
import javax.swing.JComponent;
import javax.swing.plaf.basic.BasicInternalFrameUI;
import javax.swing.BorderFactory;
import org.come.until.FormsManagement;
import org.come.Jpanel.OpenSkillGridJpanel;
import java.awt.event.MouseListener;
import javax.swing.JInternalFrame;

public class OpenSkillGridJframe extends JInternalFrame implements MouseListener
{
    private OpenSkillGridJpanel openSkillGridJpanel;
    private int first_x;
    private int first_y;
    
    public static OpenSkillGridJframe getOpenSkillGridJframe() {
        return (OpenSkillGridJframe)FormsManagement.getInternalForm(188888).getFrame();
    }
    
    public OpenSkillGridJframe() throws Exception {
        this.openSkillGridJpanel = new OpenSkillGridJpanel();
        this.getContentPane().add(this.openSkillGridJpanel);
        this.setBorder(BorderFactory.createEmptyBorder());
        ((BasicInternalFrameUI)this.getUI()).setNorthPane((JComponent)null);
        this.setBackground(UIUtils.Color_BACK);
        this.setBounds(300, 85, 100, 100);
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
                if (OpenSkillGridJframe.this.isVisible()) {
                    int x = e.getX() - OpenSkillGridJframe.this.first_x;
                    int y = e.getY() - OpenSkillGridJframe.this.first_y;
                    OpenSkillGridJframe.this.setBounds(x + OpenSkillGridJframe.this.getX(), y + OpenSkillGridJframe.this.getY(), OpenSkillGridJframe.this.getWidth(), OpenSkillGridJframe.this.getHeight());
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
            FormsManagement.HideForm(188888);
        }
        else {
            FormsManagement.Switchinglevel(188888);
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
    
    public OpenSkillGridJpanel getOpenSkillGridJpanel() {
        return this.openSkillGridJpanel;
    }
    
    public void setOpenSkillGridJpanel(OpenSkillGridJpanel openSkillGridJpanel) {
        this.openSkillGridJpanel = openSkillGridJpanel;
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
