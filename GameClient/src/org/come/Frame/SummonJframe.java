package org.come.Frame;

import org.come.until.Music;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import com.tool.tcpimg.UIUtils;
import javax.swing.plaf.basic.BasicInternalFrameUI;
import javax.swing.BorderFactory;
import org.come.until.FormsManagement;
import org.come.Jpanel.SummonJpanel;
import java.awt.event.MouseListener;
import javax.swing.JInternalFrame;

public class SummonJframe extends JInternalFrame implements MouseListener
{
    private SummonJpanel chatSwitchJpanel;
    private int first_x;
    private int first_y;
    
    public static SummonJframe getSummonJframe() {
        return (SummonJframe)FormsManagement.getInternalForm(710).getFrame();
    }
    
    public SummonJframe() throws Exception {
        this.setContentPane(this.chatSwitchJpanel = new SummonJpanel());
        this.setBorder(BorderFactory.createEmptyBorder());
        ((BasicInternalFrameUI)this.getUI()).setNorthPane(null);
        this.setBackground(UIUtils.Color_BACK);
        this.setBounds(120, 100, 100, 100);
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
                if (SummonJframe.this.isVisible()) {
                    int x = e.getX() - SummonJframe.this.first_x;
                    int y = e.getY() - SummonJframe.this.first_y;
                    SummonJframe.this.setBounds(x + SummonJframe.this.getX(), y + SummonJframe.this.getY(), SummonJframe.this.getWidth(), SummonJframe.this.getHeight());
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
            FormsManagement.HideForm(710);
        }
        else {
            FormsManagement.Switchinglevel(710);
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
    
    public SummonJpanel getChatSwitchJpanel() {
        return this.chatSwitchJpanel;
    }
    
    public void setChatSwitchJpanel(SummonJpanel chatSwitchJpanel) {
        this.chatSwitchJpanel = chatSwitchJpanel;
    }
}
