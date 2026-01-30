package org.come.Frame;

import org.come.until.Music;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import com.tool.tcpimg.UIUtils;
import javax.swing.JComponent;
import javax.swing.plaf.basic.BasicInternalFrameUI;
import javax.swing.BorderFactory;
import org.come.until.FormsManagement;
import org.come.Jpanel.FactionDetailsJpanel;
import java.awt.event.MouseListener;
import javax.swing.JInternalFrame;

public class FactionDetailsJframe extends JInternalFrame implements MouseListener
{
    private FactionDetailsJpanel factionDetailsJpanel;
    private int first_x;
    private int first_y;
    
    public static FactionDetailsJframe getFactionDetailsJframe() {
        return (FactionDetailsJframe)FormsManagement.getInternalForm(49).getFrame();
    }
    
    public FactionDetailsJframe() throws Exception {
        this.add(this.factionDetailsJpanel = new FactionDetailsJpanel());
        this.setBorder(BorderFactory.createEmptyBorder());
        ((BasicInternalFrameUI)this.getUI()).setNorthPane((JComponent)null);
        this.setBackground(UIUtils.Color_BACK);
        this.setBounds(380, 170, 330, 450);
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
                if (FactionDetailsJframe.this.isVisible()) {
                    int x = e.getX() - FactionDetailsJframe.this.first_x;
                    int y = e.getY() - FactionDetailsJframe.this.first_y;
                    FactionDetailsJframe.this.setBounds(x + FactionDetailsJframe.this.getX(), y + FactionDetailsJframe.this.getY(), FactionDetailsJframe.this.getWidth(), FactionDetailsJframe.this.getHeight());
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
            FormsManagement.HideForm(49);
        }
        else {
            FormsManagement.Switchinglevel(49);
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
    
    public FactionDetailsJpanel getFactionDetailsJpanel() {
        return this.factionDetailsJpanel;
    }
    
    public void setFactionDetailsJpanel(FactionDetailsJpanel factionDetailsJpanel) {
        this.factionDetailsJpanel = factionDetailsJpanel;
    }
}
