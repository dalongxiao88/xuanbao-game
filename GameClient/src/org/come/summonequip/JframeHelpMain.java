package org.come.summonequip;

import org.come.until.Music;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import com.tool.tcpimg.UIUtils;
import javax.swing.JComponent;
import javax.swing.plaf.basic.BasicInternalFrameUI;
import javax.swing.BorderFactory;
import org.come.until.FormsManagement;
import java.awt.event.MouseListener;
import javax.swing.JInternalFrame;

public class JframeHelpMain extends JInternalFrame implements MouseListener
{
    private int first_x;
    private int first_y;
    private JpanelHelpMain jpanelHelpMain;
    
    public static JframeHelpMain getJframeHelpMain() {
        return (JframeHelpMain)FormsManagement.getInternalForm(93).getFrame();
    }
    
    public JframeHelpMain() {
        this.add(this.jpanelHelpMain = new JpanelHelpMain());
        this.setBorder(BorderFactory.createEmptyBorder());
        ((BasicInternalFrameUI)this.getUI()).setNorthPane((JComponent)null);
        this.setBounds(270, 110, 561, 537);
        this.setOpaque(false);
        this.pack();
        this.setBackground(UIUtils.Color_BACK);
        this.setVisible(false);
        this.addMouseMotionListener(new MouseMotionListener() {
            @Override
            public void mouseMoved(MouseEvent e) {
            }
            
            @Override
            public void mouseDragged(MouseEvent e) {
                if (JframeHelpMain.getJframeHelpMain().isVisible()) {
                    int x = e.getX() - JframeHelpMain.this.first_x;
                    int y = e.getY() - JframeHelpMain.this.first_y;
                    JframeHelpMain.getJframeHelpMain().setBounds(x + JframeHelpMain.getJframeHelpMain().getX(), y + JframeHelpMain.getJframeHelpMain().getY(), JframeHelpMain.getJframeHelpMain().getWidth(), JframeHelpMain.getJframeHelpMain().getHeight());
                }
            }
        });
        this.addMouseListener(this);
    }
    
    @Override
    public void mouseClicked(MouseEvent e) {
    }
    
    @Override
    public void mousePressed(MouseEvent e) {
        Music.addyinxiao("关闭窗口.mp3");
        if (e.isMetaDown()) {
            FormsManagement.HideForm(93);
        }
        else {
            FormsManagement.Switchinglevel(93);
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
    
    public JpanelHelpMain getJpanelHelpMain() {
        return this.jpanelHelpMain;
    }
    
    public void setJpanelHelpMain(JpanelHelpMain jpanelHelpMain) {
        this.jpanelHelpMain = jpanelHelpMain;
    }
}
