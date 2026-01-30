package org.come.Frame;

import org.come.until.LotteryFromServerUntil;
import org.come.until.Music;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import com.tool.tcpimg.UIUtils;
import javax.swing.JComponent;
import javax.swing.plaf.basic.BasicInternalFrameUI;
import javax.swing.BorderFactory;
import org.come.until.FormsManagement;
import org.come.Jpanel.FanfanleJpanel;
import java.awt.event.MouseListener;
import javax.swing.JInternalFrame;

public class FanfanleJframe extends JInternalFrame implements MouseListener
{
    private FanfanleJpanel fanfanleJpanel;
    private int first_x;
    private int first_y;
    
    public static FanfanleJframe getFanfanleJpanel() {
        return (FanfanleJframe)FormsManagement.getInternalForm(999).getFrame();
    }
    
    public FanfanleJframe() throws Exception {
        this.fanfanleJpanel = new FanfanleJpanel();
        this.getContentPane().add(this.fanfanleJpanel);
        this.setBorder(BorderFactory.createEmptyBorder());
        ((BasicInternalFrameUI)this.getUI()).setNorthPane((JComponent)null);
        this.setBounds(480, 150, 300, 171);
        this.pack();
        this.setDefaultCloseOperation(3);
        this.setBackground(UIUtils.Color_BACK);
        this.setVisible(false);
        this.addMouseListener(this);
        this.addMouseMotionListener(new MouseMotionListener() {
            @Override
            public void mouseMoved(MouseEvent e) {
            }
            
            @Override
            public void mouseDragged(MouseEvent e) {
                if (FanfanleJframe.this.isVisible()) {
                    int x = e.getX() - FanfanleJframe.this.first_x;
                    int y = e.getY() - FanfanleJframe.this.first_y;
                    FanfanleJframe.this.setBounds(x + FanfanleJframe.this.getX(), y + FanfanleJframe.this.getY(), FanfanleJframe.this.getWidth(), FanfanleJframe.this.getHeight());
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
            FormsManagement.HideForm(999);
            LotteryFromServerUntil.drop();
        }
        else {
            FormsManagement.Switchinglevel(999);
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
    
    public void setFanfanleJpanel(FanfanleJpanel fanfanleJpanel) {
        this.fanfanleJpanel = fanfanleJpanel;
    }
}
