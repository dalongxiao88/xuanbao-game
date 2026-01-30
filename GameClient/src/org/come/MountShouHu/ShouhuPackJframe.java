package org.come.MountShouHu;

import org.come.until.Music;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import com.tool.tcpimg.UIUtils;
import javax.swing.plaf.basic.BasicInternalFrameUI;
import javax.swing.BorderFactory;
import org.come.until.FormsManagement;
import java.awt.event.MouseListener;
import javax.swing.JInternalFrame;

public class ShouhuPackJframe extends JInternalFrame implements MouseListener
{
    private int first_x;
    private int first_y;
    private ShouhuPackJpanel shouhuPackJpanel;
    
    public static ShouhuPackJframe getShouhuPackJframe() {
        return (ShouhuPackJframe)FormsManagement.getInternalForm(2257).getFrame();
    }
    
    public ShouhuPackJframe() throws Exception {
        this.setBorder(BorderFactory.createEmptyBorder());
        ((BasicInternalFrameUI)this.getUI()).setNorthPane(null);
        this.setContentPane(this.shouhuPackJpanel = new ShouhuPackJpanel());
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
                if (ShouhuPackJframe.this.isVisible()) {
                    int x = e.getX() - ShouhuPackJframe.this.first_x;
                    int y = e.getY() - ShouhuPackJframe.this.first_y;
                    ShouhuPackJframe.this.setBounds(x + ShouhuPackJframe.this.getX(), y + ShouhuPackJframe.this.getY(), ShouhuPackJframe.this.getWidth(), ShouhuPackJframe.this.getHeight());
                }
            }
        });
    }
    
    @Override
    public void mouseClicked(MouseEvent e) {
    }
    
    @Override
    public void mousePressed(MouseEvent e) {
        FormsManagement.showForm(2257);
        Music.addyinxiao("关闭窗口.mp3");
        if (e.isMetaDown()) {
            FormsManagement.HideForm(2257);
        }
        else {
            FormsManagement.Switchinglevel(2257);
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
    
    public ShouhuPackJpanel getShouhuPackJpanel() {
        return this.shouhuPackJpanel;
    }
    
    public void setShouhuPackJpanel(ShouhuPackJpanel shouhuPackJpanel) {
        this.shouhuPackJpanel = shouhuPackJpanel;
    }
}
