package org.come.daily;

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

public class JframeDailyMain extends JInternalFrame implements MouseListener
{
    private int first_x;
    private int first_y;
    private JpanelDailyMain jpanelDailyMain;
    
    public static JframeDailyMain getJframeDailyMain() {
        return (JframeDailyMain)FormsManagement.getInternalForm(90).getFrame();
    }
    
    public JframeDailyMain() {
        this.add(this.jpanelDailyMain = new JpanelDailyMain());
        this.setBorder(BorderFactory.createEmptyBorder());
        ((BasicInternalFrameUI)this.getUI()).setNorthPane((JComponent)null);
        this.setBounds(200, 60, 561, 537);
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
                if (JframeDailyMain.this.isVisible()) {
                    int x = e.getX() - JframeDailyMain.this.first_x;
                    int y = e.getY() - JframeDailyMain.this.first_y;
                    JframeDailyMain.this.setBounds(x + JframeDailyMain.this.getX(), y + JframeDailyMain.this.getY(), JframeDailyMain.this.getWidth(), JframeDailyMain.this.getHeight());
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
            FormsManagement.HideForm(90);
        }
        else {
            FormsManagement.Switchinglevel(90);
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
    
    public JpanelDailyMain getJpanelDailyMain() {
        return this.jpanelDailyMain;
    }
    
    public void setJpanelDailyMain(JpanelDailyMain jpanelDailyMain) {
        this.jpanelDailyMain = jpanelDailyMain;
    }
}
