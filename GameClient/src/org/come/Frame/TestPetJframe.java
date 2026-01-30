package org.come.Frame;

import org.come.until.Music;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import com.tool.tcpimg.UIUtils;
import javax.swing.plaf.basic.BasicInternalFrameUI;
import javax.swing.BorderFactory;
import org.come.until.FormsManagement;
import org.come.Jpanel.TestPetJpanel;
import java.awt.event.MouseListener;
import javax.swing.JInternalFrame;

public class TestPetJframe extends JInternalFrame implements MouseListener
{
    private TestPetJpanel testPetJpanel;
    private int first_x;
    private int first_y;
    
    public static TestPetJframe getTestPetJframe() {
        return (TestPetJframe)FormsManagement.getInternalForm(6).getFrame();
    }
    
    public TestPetJframe() throws Exception {
        this.setContentPane(this.testPetJpanel = new TestPetJpanel());
        this.setBorder(BorderFactory.createEmptyBorder());
        ((BasicInternalFrameUI)this.getUI()).setNorthPane(null);
        this.setBackground(UIUtils.Color_BACK);
        this.setBounds(120, 100, 100, 100);
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
                if (TestPetJframe.this.isVisible()) {
                    int x = e.getX() - TestPetJframe.this.first_x;
                    int y = e.getY() - TestPetJframe.this.first_y;
                    TestPetJframe.this.setBounds(x + TestPetJframe.this.getX(), y + TestPetJframe.this.getY(), TestPetJframe.this.getWidth(), TestPetJframe.this.getHeight());
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
            FormsManagement.HideForm(6);
        }
        else {
            FormsManagement.Switchinglevel(6);
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
    
    public TestPetJpanel getTestPetJpanel() {
        return this.testPetJpanel;
    }
    
    public void setTestPetJpanel(TestPetJpanel testPetJpanel) {
        this.testPetJpanel = testPetJpanel;
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
