package org.come.Frame;

import org.come.until.Music;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import com.tool.tcpimg.UIUtils;
import javax.swing.JComponent;
import javax.swing.plaf.basic.BasicInternalFrameUI;
import javax.swing.BorderFactory;
import org.come.until.FormsManagement;
import org.come.Jpanel.FactionMainJpanel;
import java.awt.event.MouseListener;
import javax.swing.JInternalFrame;

public class FactionMainJframe extends JInternalFrame implements MouseListener
{
    private FactionMainJpanel factionMainJpanel;
    private int first_x;
    private int first_y;
    
    public static FactionMainJframe getFactionMainJframe() {
        return (FactionMainJframe)FormsManagement.getInternalForm(48).getFrame();
    }
    
    public FactionMainJframe() throws Exception {
        this.add(this.factionMainJpanel = new FactionMainJpanel());
        this.setBorder(BorderFactory.createEmptyBorder());
        ((BasicInternalFrameUI)this.getUI()).setNorthPane((JComponent)null);
        this.setBackground(UIUtils.Color_BACK);
        this.setBounds(380, 70, 330, 450);
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
                if (FactionMainJframe.this.isVisible()) {
                    int x = e.getX() - FactionMainJframe.this.first_x;
                    int y = e.getY() - FactionMainJframe.this.first_y;
                    FactionMainJframe.this.setBounds(x + FactionMainJframe.this.getX(), y + FactionMainJframe.this.getY(), FactionMainJframe.this.getWidth(), FactionMainJframe.this.getHeight());
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
            FormsManagement.HideForm(48);
        }
        else {
            FormsManagement.Switchinglevel(48);
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
    
    public FactionMainJpanel getFactionMainJpanel() {
        return this.factionMainJpanel;
    }
    
    public void setFactionMainJpanel(FactionMainJpanel factionMainJpanel) {
        this.factionMainJpanel = factionMainJpanel;
    }
}
