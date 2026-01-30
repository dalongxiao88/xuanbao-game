package org.come.Frame;

import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import javax.swing.JComponent;
import javax.swing.plaf.basic.BasicInternalFrameUI;
import javax.swing.BorderFactory;
import org.come.until.ScrenceUntil;
import org.come.until.FormsManagement;
import org.come.Jpanel.TestfriendlistJapnel;
import java.awt.event.MouseListener;
import javax.swing.JInternalFrame;

public class TestfriendlistJframe extends JInternalFrame implements MouseListener
{
    private TestfriendlistJapnel jflist;
    private int first_x;
    private int first_y;
    
    public static TestfriendlistJframe getTestfriendlistJframe() {
        return (TestfriendlistJframe)FormsManagement.getInternalForm(4).getFrame();
    }
    
    public TestfriendlistJframe() throws Exception {
        this.setBounds(ScrenceUntil.Screen_x - 158, 130, 141, 499);
        this.jflist = new TestfriendlistJapnel();
        this.getContentPane().add(this.jflist);
        this.setBorder(BorderFactory.createEmptyBorder());
        ((BasicInternalFrameUI)this.getUI()).setNorthPane((JComponent)null);
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
                if (TestfriendlistJframe.this.isVisible()) {
                    int x = e.getX() - TestfriendlistJframe.this.first_x;
                    int y = e.getY() - TestfriendlistJframe.this.first_y;
                    TestfriendlistJframe.this.setBounds(x + TestfriendlistJframe.this.getX(), y + TestfriendlistJframe.this.getY(), TestfriendlistJframe.this.getWidth(), TestfriendlistJframe.this.getHeight());
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
            FormsManagement.HideForm(4);
        }
        else {
            FormsManagement.Switchinglevel(4);
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
    
    public TestfriendlistJapnel getJflist() {
        return this.jflist;
    }
    
    public void setJflist(TestfriendlistJapnel jflist) {
        this.jflist = jflist;
    }
}
