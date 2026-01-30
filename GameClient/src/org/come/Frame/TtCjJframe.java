package org.come.Frame;

import org.come.until.Music;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import com.tool.tcpimg.UIUtils;
import javax.swing.plaf.basic.BasicInternalFrameUI;
import javax.swing.BorderFactory;
import org.come.until.FormsManagement;
import org.come.Jpanel.TtCjJpanel;
import java.awt.event.MouseListener;
import javax.swing.JInternalFrame;

public class TtCjJframe extends JInternalFrame implements MouseListener
{
    private TtCjJpanel panel;
    private int first_x;
    private int first_y;
    
    public static TtCjJframe getTtCjJframe() {
        return (TtCjJframe)FormsManagement.getInternalForm(607).getFrame();
    }
    
    public TtCjJframe() {
        this.add(this.panel = new TtCjJpanel());
        this.setBorder(BorderFactory.createEmptyBorder());
        ((BasicInternalFrameUI)this.getUI()).setNorthPane(null);
        this.setBounds(210, 170, 684, 455);
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
                if (TtCjJframe.this.isVisible()) {
                    int x = e.getX() - TtCjJframe.this.first_x;
                    int y = e.getY() - TtCjJframe.this.first_y;
                    TtCjJframe.this.setBounds(x + TtCjJframe.this.getX(), y + TtCjJframe.this.getY(), TtCjJframe.this.getWidth(), TtCjJframe.this.getHeight());
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
            FormsManagement.HideForm(607);
        }
        else {
            FormsManagement.Switchinglevel(607);
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
    
    public TtCjJpanel getPanel() {
        return this.panel;
    }
    
    public void setPanel(TtCjJpanel panel) {
        this.panel = panel;
    }
}
