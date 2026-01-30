package org.come.Frame;

import org.come.until.Music;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import com.tool.tcpimg.UIUtils;
import javax.swing.plaf.basic.BasicInternalFrameUI;
import javax.swing.BorderFactory;
import org.come.until.FormsManagement;
import org.come.Jpanel.TestpackJapnel;
import java.awt.event.MouseListener;
import javax.swing.JInternalFrame;

public class TestpackJframe extends JInternalFrame implements MouseListener
{
    private TestpackJapnel jpac;
    private int first_x;
    private int first_y;
    
    public static TestpackJframe getTestpackJframe() {
        return (TestpackJframe)FormsManagement.getInternalForm(2).getFrame();
    }
    
    public TestpackJframe() throws Exception {
        this.setBorder(BorderFactory.createEmptyBorder());
        ((BasicInternalFrameUI)this.getUI()).setNorthPane(null);
        this.setContentPane(this.jpac = new TestpackJapnel());
        this.setBackground(UIUtils.Color_BACK);
        this.setBounds(5, 100, 363, 471);
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
                if (TestpackJframe.this.isVisible()) {
                    int x = e.getX() - TestpackJframe.this.first_x;
                    int y = e.getY() - TestpackJframe.this.first_y;
                    TestpackJframe.this.setBounds(x + TestpackJframe.this.getX(), y + TestpackJframe.this.getY(), TestpackJframe.this.getWidth(), TestpackJframe.this.getHeight());
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
            FormsManagement.HideForm(2);
            Music.addyinxiao("关闭窗口.mp3");
        }
        else {
            FormsManagement.Switchinglevel(2);
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
    
    public TestpackJapnel getJpac() {
        return this.jpac;
    }
    
    public void setJpac(TestpackJapnel jpac) {
        this.jpac = jpac;
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
