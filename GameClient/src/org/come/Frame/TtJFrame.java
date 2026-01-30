package org.come.Frame;

import org.come.until.Music;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import com.tool.tcpimg.UIUtils;
import javax.swing.plaf.basic.BasicInternalFrameUI;
import javax.swing.BorderFactory;
import org.come.until.FormsManagement;
import org.come.Jpanel.TtJpanel;
import java.awt.event.MouseListener;
import javax.swing.JInternalFrame;

public class TtJFrame extends JInternalFrame implements MouseListener
{
    private TtJpanel ttJpanel;
    private int first_x;
    private int first_y;
    
    public static TtJFrame getTtJFrame() {
        return (TtJFrame)FormsManagement.getInternalForm(606).getFrame();
    }
    
    public TtJFrame() {
        this.add(this.ttJpanel = new TtJpanel());
        this.setBorder(BorderFactory.createEmptyBorder());
        ((BasicInternalFrameUI)this.getUI()).setNorthPane(null);
        this.setBounds(210, 170, 674, 449);
        this.setOpaque(false);
        this.pack();
        this.setBackground(UIUtils.Color_BACK);
        this.setVisible(false);
        this.setDefaultCloseOperation(3);
        this.addMouseListener(this);
        this.addMouseMotionListener(new MouseMotionListener() {
            @Override
            public void mouseMoved(MouseEvent e) {
            }
            
            @Override
            public void mouseDragged(MouseEvent e) {
                if (TtJFrame.this.isVisible()) {
                    int x = e.getX() - TtJFrame.this.first_x;
                    int y = e.getY() - TtJFrame.this.first_y;
                    TtJFrame.this.setBounds(x + TtJFrame.this.getX(), y + TtJFrame.this.getY(), TtJFrame.this.getWidth(), TtJFrame.this.getHeight());
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
            FormsManagement.HideForm(606);
        }
        else {
            FormsManagement.Switchinglevel(606);
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
    
    public TtJpanel getTtJpanel() {
        return this.ttJpanel;
    }
    
    public void setTtJpanel(TtJpanel ttJpanel) {
        this.ttJpanel = ttJpanel;
    }
}
