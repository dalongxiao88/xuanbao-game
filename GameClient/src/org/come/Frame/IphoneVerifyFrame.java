package org.come.Frame;

import org.come.until.Music;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import com.tool.tcpimg.UIUtils;
import javax.swing.JComponent;
import javax.swing.plaf.basic.BasicInternalFrameUI;
import javax.swing.BorderFactory;
import org.come.model.InternalForm;
import org.come.until.FormsManagement;
import org.come.Jpanel.IphoneVerifyPanel;
import java.awt.event.MouseListener;
import javax.swing.JInternalFrame;

public class IphoneVerifyFrame extends JInternalFrame implements MouseListener
{
    private int first_x;
    private int first_y;
    private IphoneVerifyPanel iphoneVerifyPanel;
    
    public static IphoneVerifyFrame getIphoneVerifyFrame() {
        return (IphoneVerifyFrame)FormsManagement.getInternalForm(99).getFrame();
    }
    
    public static IphoneVerifyFrame getShowIphoneVerifyFrame() {
        InternalForm form = FormsManagement.getInternalForm2(99);
        if (form == null) {
            return null;
        }
        IphoneVerifyFrame equipMain = (IphoneVerifyFrame)form.getFrame();
        return equipMain.isVisible() ? equipMain : null;
    }
    
    public IphoneVerifyFrame() {
        this.add(this.iphoneVerifyPanel = new IphoneVerifyPanel());
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
                if (IphoneVerifyFrame.getIphoneVerifyFrame().isVisible()) {
                    int x = e.getX() - IphoneVerifyFrame.this.first_x;
                    int y = e.getY() - IphoneVerifyFrame.this.first_y;
                    IphoneVerifyFrame.getIphoneVerifyFrame().setBounds(x + IphoneVerifyFrame.getIphoneVerifyFrame().getX(), y + IphoneVerifyFrame.getIphoneVerifyFrame().getY(), IphoneVerifyFrame.getIphoneVerifyFrame().getWidth(), IphoneVerifyFrame.getIphoneVerifyFrame().getHeight());
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
            FormsManagement.HideForm(99);
        }
        else {
            FormsManagement.Switchinglevel(99);
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
    
    public IphoneVerifyPanel getIphoneVerifyPanel() {
        return this.iphoneVerifyPanel;
    }
    
    public void setIphoneVerifyPanel(IphoneVerifyPanel iphoneVerifyPanel) {
        this.iphoneVerifyPanel = iphoneVerifyPanel;
    }
}
