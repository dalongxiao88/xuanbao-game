package org.come.Frame;

import org.come.until.Music;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import com.tool.tcpimg.UIUtils;
import javax.swing.plaf.basic.BasicInternalFrameUI;
import javax.swing.BorderFactory;
import org.come.until.FormsManagement;
import org.come.Jpanel.MountShouhuJpanel;
import java.awt.event.MouseListener;
import javax.swing.JInternalFrame;

public class MountShouhuJframe extends JInternalFrame implements MouseListener
{
    private int first_x;
    private int first_y;
    private MountShouhuJpanel mountShouhuJpanel;
    
    public static MountShouhuJframe getMountShouhuJframe() {
        return (MountShouhuJframe)FormsManagement.getInternalForm(559).getFrame();
    }
    
    public MountShouhuJframe() throws Exception {
        this.setBorder(BorderFactory.createEmptyBorder());
        ((BasicInternalFrameUI)this.getUI()).setNorthPane(null);
        this.setContentPane(this.mountShouhuJpanel = new MountShouhuJpanel());
        this.setBackground(UIUtils.Color_BACK);
        this.setBounds(100, 140, 281, 342);
        this.pack();
        this.setDefaultCloseOperation(3);
        this.setVisible(false);
        this.addMouseListener(this);
        this.addMouseMotionListener(new MouseMotionListener() {
            @Override
            public void mouseMoved(MouseEvent e) {
            }
            
            @Override
            public void mouseDragged(MouseEvent e) {
                if (MountShouhuJframe.this.isVisible()) {
                    int x = e.getX() - MountShouhuJframe.this.first_x;
                    int y = e.getY() - MountShouhuJframe.this.first_y;
                    MountShouhuJframe.this.setBounds(x + MountShouhuJframe.this.getX(), y + MountShouhuJframe.this.getY(), MountShouhuJframe.this.getWidth(), MountShouhuJframe.this.getHeight());
                }
            }
        });
    }
    
    @Override
    public void mouseClicked(MouseEvent e) {
    }
    
    @Override
    public void mousePressed(MouseEvent e) {
        FormsManagement.showForm(559);
        Music.addyinxiao("关闭窗口.mp3");
        if (e.isMetaDown()) {
            FormsManagement.HideForm(559);
        }
        else {
            FormsManagement.Switchinglevel(559);
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
    
    public MountShouhuJpanel getMountShouhuJpanel() {
        return this.mountShouhuJpanel;
    }
    
    public void setMountShouhuJpanel(MountShouhuJpanel mountShouhuJpanel) {
        this.mountShouhuJpanel = mountShouhuJpanel;
    }
}
