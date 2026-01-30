package org.come.Frame;

import org.come.until.Music;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import com.tool.tcpimg.UIUtils;
import javax.swing.JComponent;
import javax.swing.plaf.basic.BasicInternalFrameUI;
import javax.swing.BorderFactory;
import org.come.until.FormsManagement;
import org.come.Jpanel.TestChildJpanel;
import java.awt.event.MouseListener;
import javax.swing.JInternalFrame;

public class TestChildJframe extends JInternalFrame implements MouseListener
{
    private TestChildJpanel testChildJpanel;
    private int first_x;
    private int first_y;
    
    public static TestChildJframe getTestChildJframe() {
        return (TestChildJframe)FormsManagement.getInternalForm(1).getFrame();
    }
    
    public TestChildJframe() throws Exception {
        this.add(this.testChildJpanel = new TestChildJpanel());
        this.setBorder(BorderFactory.createEmptyBorder());
        ((BasicInternalFrameUI)this.getUI()).setNorthPane((JComponent)null);
        this.setBounds(80, 100, 582, 375);
        this.testChildJpanel.setBounds(0, 0, 582, 375);
        this.pack();
        this.setBackground(UIUtils.Color_BACK);
        this.setVisible(false);
        this.addMouseListener(this);
        this.addMouseMotionListener(new MouseMotionListener() {
            @Override
            public void mouseMoved(MouseEvent e) {
            }
            
            @Override
            public void mouseDragged(MouseEvent e) {
                if (TestChildJframe.this.isVisible()) {
                    int x = e.getX() - TestChildJframe.this.first_x;
                    int y = e.getY() - TestChildJframe.this.first_y;
                    TestChildJframe.this.setBounds(x + TestChildJframe.this.getX(), y + TestChildJframe.this.getY(), TestChildJframe.this.getWidth(), TestChildJframe.this.getHeight());
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
            FormsManagement.HideForm(1);
        }
        else {
            FormsManagement.Switchinglevel(1);
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
    
    public TestChildJpanel getTestChildJpanel() {
        return this.testChildJpanel;
    }
    
    public void setTestChildJpanel(TestChildJpanel testChildJpanel) {
        this.testChildJpanel = testChildJpanel;
    }
}
