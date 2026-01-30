package org.come.Frame;

import org.come.until.Music;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import javax.swing.plaf.basic.BasicInternalFrameUI;
import javax.swing.BorderFactory;
import org.come.until.FormsManagement;
import org.come.Jpanel.TestSetupJpanel;
import java.awt.event.MouseListener;
import javax.swing.JInternalFrame;

public class TestSetupJframe extends JInternalFrame implements MouseListener
{
    private TestSetupJpanel testSetupJpanel;
    private int first_x;
    private int first_y;
    
    public static TestSetupJframe getTestSetupJframe() {
        return (TestSetupJframe)FormsManagement.getInternalForm(50).getFrame();
    }
    
    public TestSetupJframe() throws Exception {
        this.setContentPane(this.testSetupJpanel = new TestSetupJpanel());
        this.setBorder(BorderFactory.createEmptyBorder());
        ((BasicInternalFrameUI)this.getUI()).setNorthPane(null);
        this.setOpaque(false);
        this.setBounds(280, 200, 337, 450);
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
                if (TestSetupJframe.this.isVisible()) {
                    int x = e.getX() - TestSetupJframe.this.first_x;
                    int y = e.getY() - TestSetupJframe.this.first_y;
                    TestSetupJframe.this.setBounds(x + TestSetupJframe.this.getX(), y + TestSetupJframe.this.getY(), TestSetupJframe.this.getWidth(), TestSetupJframe.this.getHeight());
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
            FormsManagement.HideForm(50);
        }
        else {
            FormsManagement.Switchinglevel(50);
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
    
    public TestSetupJpanel getTestSetupJpanel() {
        return this.testSetupJpanel;
    }
    
    public void setTestSetupJpanel(TestSetupJpanel testSetupJpanel) {
        this.testSetupJpanel = testSetupJpanel;
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
