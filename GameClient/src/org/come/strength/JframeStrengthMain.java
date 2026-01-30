package org.come.strength;

import org.come.until.Music;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import com.tool.tcpimg.UIUtils;
import javax.swing.JComponent;
import javax.swing.plaf.basic.BasicInternalFrameUI;
import javax.swing.BorderFactory;
import org.come.model.InternalForm;
import org.come.until.FormsManagement;
import java.awt.event.MouseListener;
import javax.swing.JInternalFrame;

public class JframeStrengthMain extends JInternalFrame implements MouseListener
{
    private int first_x;
    private int first_y;
    private JpanelStrengthMain jpanelStrengthMain;
    
    public static JframeStrengthMain getJframeStrengthMain() {
        return (JframeStrengthMain)FormsManagement.getInternalForm(98).getFrame();
    }
    
    public static JframeStrengthMain getShowJframeStrengthMain() {
        InternalForm form = FormsManagement.getInternalForm2(98);
        if (form == null) {
            return null;
        }
        JframeStrengthMain equipMain = (JframeStrengthMain)form.getFrame();
        return equipMain.isVisible() ? equipMain : null;
    }
    
    public JframeStrengthMain() {
        this.add(this.jpanelStrengthMain = new JpanelStrengthMain());
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
                if (JframeStrengthMain.getJframeStrengthMain().isVisible()) {
                    int x = e.getX() - JframeStrengthMain.this.first_x;
                    int y = e.getY() - JframeStrengthMain.this.first_y;
                    JframeStrengthMain.getJframeStrengthMain().setBounds(x + JframeStrengthMain.getJframeStrengthMain().getX(), y + JframeStrengthMain.getJframeStrengthMain().getY(), JframeStrengthMain.getJframeStrengthMain().getWidth(), JframeStrengthMain.getJframeStrengthMain().getHeight());
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
            FormsManagement.HideForm(98);
        }
        else {
            FormsManagement.Switchinglevel(98);
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
    
    public JpanelStrengthMain getJpanelStrengthMain() {
        return this.jpanelStrengthMain;
    }
    
    public void setJpanelStrengthMain(JpanelStrengthMain jpanelStrengthMain) {
        this.jpanelStrengthMain = jpanelStrengthMain;
    }
}
