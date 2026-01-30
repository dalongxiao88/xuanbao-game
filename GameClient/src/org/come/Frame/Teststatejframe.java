package org.come.Frame;

import org.come.until.Music;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import com.tool.tcpimg.UIUtils;
import javax.swing.plaf.basic.BasicInternalFrameUI;
import javax.swing.BorderFactory;
import org.come.until.FormsManagement;
import org.come.Jpanel.TeststateJpanel;
import java.awt.event.MouseListener;
import javax.swing.JInternalFrame;

public class Teststatejframe extends JInternalFrame implements MouseListener
{
    private TeststateJpanel teststateJpanel;
    private int first_x;
    private int first_y;
    
    public static Teststatejframe getTeststatejframe() {
        return (Teststatejframe)FormsManagement.getInternalForm(0).getFrame();
    }
    
    public Teststatejframe() throws Exception {
        this.teststateJpanel = new TeststateJpanel(null);
        this.setBorder(BorderFactory.createEmptyBorder());
        ((BasicInternalFrameUI)this.getUI()).setNorthPane(null);
        this.setContentPane(this.teststateJpanel = new TeststateJpanel(this));
        this.setBounds(610, 150, 400, 300);
        this.pack();
        this.setBackground(UIUtils.Color_BACK);
        this.setVisible(false);
        this.setDefaultCloseOperation(3);
        this.addMouseListener(this);
        this.addMouseMotionListener(new MouseMotionListener() {
            @Override
            public void mouseMoved(MouseEvent e) {
            }
            
            @Override
            public void mouseDragged(MouseEvent e) {
                if (Teststatejframe.this.isVisible()) {
                    int x = e.getX() - Teststatejframe.this.first_x;
                    int y = e.getY() - Teststatejframe.this.first_y;
                    Teststatejframe.this.setBounds(x + Teststatejframe.this.getX(), y + Teststatejframe.this.getY(), Teststatejframe.this.getWidth(), Teststatejframe.this.getHeight());
                }
            }
        });
    }
    
    public TeststateJpanel getTeststateJpanel() {
        return this.teststateJpanel;
    }
    
    public void setTeststateJpanel(TeststateJpanel teststateJpanel) {
        this.teststateJpanel = teststateJpanel;
    }
    
    @Override
    public void mouseClicked(MouseEvent e) {
    }
    
    @Override
    public void mousePressed(MouseEvent e) {
        Music.addyinxiao("关闭窗口.mp3");
        if (e.isMetaDown()) {
            FormsManagement.HideForm(0);
            FormsManagement.disposeForm(0);
        }
        else {
            FormsManagement.Switchinglevel(0);
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
}
