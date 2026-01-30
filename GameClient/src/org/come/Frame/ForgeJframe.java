package org.come.Frame;

import org.come.until.Music;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import com.tool.tcpimg.UIUtils;
import javax.swing.plaf.basic.BasicInternalFrameUI;
import javax.swing.BorderFactory;
import org.come.until.FormsManagement;
import org.come.Jpanel.ForgeJpanel;
import java.awt.event.MouseListener;
import javax.swing.JInternalFrame;

public class ForgeJframe extends JInternalFrame implements MouseListener
{
    private ForgeJpanel forgejpanel;
    private int first_x;
    private int first_y;
    
    public static ForgeJframe getForgejframe() {
        return (ForgeJframe)FormsManagement.getInternalForm(26).getFrame();
    }
    
    public ForgeJframe() throws Exception {
        this.forgejpanel = new ForgeJpanel();
        this.getContentPane().add(this.forgejpanel);
        this.setBorder(BorderFactory.createEmptyBorder());
        ((BasicInternalFrameUI)this.getUI()).setNorthPane(null);
        this.setBackground(UIUtils.Color_BACK);
        this.setBounds(600, 200, 330, 450);
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
                if (ForgeJframe.this.isVisible()) {
                    int x = e.getX() - ForgeJframe.this.first_x;
                    int y = e.getY() - ForgeJframe.this.first_y;
                    ForgeJframe.this.setBounds(x + ForgeJframe.this.getX(), y + ForgeJframe.this.getY(), ForgeJframe.this.getWidth(), ForgeJframe.this.getHeight());
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
            FormsManagement.HideForm(26);
        }
        else {
            FormsManagement.Switchinglevel(26);
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
    
    public ForgeJpanel getJpanel() {
        return this.forgejpanel;
    }
    
    public void setforgeJframe(ForgeJpanel forgejpanel) {
        this.forgejpanel = forgejpanel;
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
    
    public ForgeJpanel getForgejpanel() {
        return this.forgejpanel;
    }
    
    public void setForgejpanel(ForgeJpanel forgejpanel) {
        this.forgejpanel = forgejpanel;
    }
}
