package org.come.Frame;

import org.come.until.Music;
import org.come.until.MessagrFlagUntil;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import com.tool.tcpimg.UIUtils;
import javax.swing.JComponent;
import javax.swing.plaf.basic.BasicInternalFrameUI;
import javax.swing.BorderFactory;
import org.come.until.FormsManagement;
import org.come.Jpanel.TjJpanel;
import java.awt.event.MouseListener;
import javax.swing.JInternalFrame;

public class TjJframe extends JInternalFrame implements MouseListener
{
    private TjJpanel tjJpanel;
    private int first_x;
    private int first_y;
    
    public static TjJframe getSupportListJframe() {
        return (TjJframe)FormsManagement.getInternalForm(6222).getFrame();
    }
    
    public TjJframe() {
        this.tjJpanel = new TjJpanel();
        this.getContentPane().add(this.tjJpanel);
        this.setBorder(BorderFactory.createEmptyBorder());
        ((BasicInternalFrameUI)this.getUI()).setNorthPane((JComponent)null);
        this.setBounds(30, 100, 296, 198);
        this.setBackground(UIUtils.Color_BACK);
        this.pack();
        this.setVisible(false);
        this.setDefaultCloseOperation(3);
        this.addMouseListener(this);
        this.addMouseMotionListener(new MouseMotionListener() {
            @Override
            public void mouseMoved(MouseEvent e) {
                if (MessagrFlagUntil.ImgFlagImg.equals(MessagrFlagUntil.MOUSE13)) {
                    MessagrFlagUntil.setMouse(MessagrFlagUntil.MOUSE1);
                }
            }
            
            @Override
            public void mouseDragged(MouseEvent e) {
                if (TjJframe.this.isVisible()) {
                    int x = e.getX() - TjJframe.this.first_x;
                    int y = e.getY() - TjJframe.this.first_y;
                    TjJframe.this.setBounds(x + TjJframe.this.getX(), y + TjJframe.this.getY(), TjJframe.this.getWidth(), TjJframe.this.getHeight());
                }
            }
        });
    }
    
    @Override
    public void mouseClicked(MouseEvent e) {
    }
    
    @Override
    public void mousePressed(MouseEvent e) {
        FormsManagement.showForm(6222);
        Music.addyinxiao("关闭窗口.mp3");
        if (e.isMetaDown()) {
            FormsManagement.HideForm(6222);
            FormsManagement.disposeForm(6222);
        }
        else {
            FormsManagement.Switchinglevel(6222);
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
    
    public TjJpanel getTjJpanel() {
        return this.tjJpanel;
    }
    
    public void setTjJpanel(TjJpanel tjJpanel) {
        this.tjJpanel = tjJpanel;
    }
}
