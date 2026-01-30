package org.come.Frame;

import org.come.until.Music;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import com.tool.tcpimg.UIUtils;
import javax.swing.plaf.basic.BasicInternalFrameUI;
import javax.swing.BorderFactory;
import org.come.until.FormsManagement;
import org.come.Jpanel.TtDhJpanel;
import java.awt.event.MouseListener;
import javax.swing.JInternalFrame;

public class TtDhJframe extends JInternalFrame implements MouseListener
{
    private TtDhJpanel panel;
    private int first_x;
    private int first_y;
    
    public static TtDhJframe geTtDhJframe() {
        return (TtDhJframe)FormsManagement.getInternalForm(608).getFrame();
    }
    
    public TtDhJframe() {
        this.add(this.panel = new TtDhJpanel());
        this.setBorder(BorderFactory.createEmptyBorder());
        ((BasicInternalFrameUI)this.getUI()).setNorthPane(null);
        this.setBounds(210, 170, 588, 478);
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
                if (TtDhJframe.this.isVisible()) {
                    int x = e.getX() - TtDhJframe.this.first_x;
                    int y = e.getY() - TtDhJframe.this.first_y;
                    TtDhJframe.this.setBounds(x + TtDhJframe.this.getX(), y + TtDhJframe.this.getY(), TtDhJframe.this.getWidth(), TtDhJframe.this.getHeight());
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
            FormsManagement.HideForm(608);
        }
        else {
            FormsManagement.Switchinglevel(608);
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
    
    public TtDhJpanel getPanel() {
        return this.panel;
    }
    
    public void setPanel(TtDhJpanel panel) {
        this.panel = panel;
    }
}
