package org.come.Frame;

import org.come.until.Music;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import com.tool.tcpimg.UIUtils;
import javax.swing.plaf.basic.BasicInternalFrameUI;
import javax.swing.BorderFactory;
import org.come.until.FormsManagement;
import org.come.Jpanel.TesttaskJapnel;
import java.awt.event.MouseListener;
import javax.swing.JInternalFrame;

public class TesttaskJframe extends JInternalFrame implements MouseListener
{
    private TesttaskJapnel jtask;
    private int first_x;
    private int first_y;
    
    public static TesttaskJframe getTesttaskJframe() {
        return (TesttaskJframe)FormsManagement.getInternalForm(3).getFrame();
    }
    
    public TesttaskJframe() throws Exception {
        this.setContentPane(this.jtask = new TesttaskJapnel());
        this.setBorder(BorderFactory.createEmptyBorder());
        ((BasicInternalFrameUI)this.getUI()).setNorthPane(null);
        this.setBackground(UIUtils.Color_BACK);
        this.pack();
        this.setVisible(false);
        this.setBounds(120, 90, 582, 415);
        this.setDefaultCloseOperation(3);
        this.addMouseListener(this);
        this.addMouseMotionListener(new MouseMotionListener() {
            @Override
            public void mouseMoved(MouseEvent e) {
            }
            
            @Override
            public void mouseDragged(MouseEvent e) {
                if (TesttaskJframe.this.isVisible()) {
                    int x = e.getX() - TesttaskJframe.this.first_x;
                    int y = e.getY() - TesttaskJframe.this.first_y;
                    TesttaskJframe.this.setBounds(x + TesttaskJframe.this.getX(), y + TesttaskJframe.this.getY(), TesttaskJframe.this.getWidth(), TesttaskJframe.this.getHeight());
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
            FormsManagement.HideForm(3);
        }
        else {
            FormsManagement.Switchinglevel(3);
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
    
    public TesttaskJapnel getJtask() {
        return this.jtask;
    }
    
    public void setJtask(TesttaskJapnel jtask) {
        this.jtask = jtask;
    }
}
