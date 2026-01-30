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

public class LvlupJframe extends JInternalFrame implements MouseListener
{
    private int first_x;
    private int first_y;
    private LvlupJpanel lvlupJpanel;
    
    public static LvlupJframe getLvlupJframe() {
        return (LvlupJframe)FormsManagement.getInternalForm(2258).getFrame();
    }
    
    public LvlupJframe() throws Exception {
        this.setBorder(BorderFactory.createEmptyBorder());
        ((BasicInternalFrameUI)this.getUI()).setNorthPane(null);
        this.setContentPane(this.lvlupJpanel = new LvlupJpanel());
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
                if (LvlupJframe.this.isVisible()) {
                    int x = e.getX() - LvlupJframe.this.first_x;
                    int y = e.getY() - LvlupJframe.this.first_y;
                    LvlupJframe.this.setBounds(x + LvlupJframe.this.getX(), y + LvlupJframe.this.getY(), LvlupJframe.this.getWidth(), LvlupJframe.this.getHeight());
                }
            }
        });
    }
    
    @Override
    public void mouseClicked(MouseEvent e) {
    }
    
    @Override
    public void mousePressed(MouseEvent e) {
        FormsManagement.showForm(2258);
        Music.addyinxiao("关闭窗口.mp3");
        if (e.isMetaDown()) {
            FormsManagement.HideForm(2258);
        }
        else {
            FormsManagement.Switchinglevel(2258);
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
    
    public LvlupJpanel getLvlupJpanel() {
        return this.lvlupJpanel;
    }
    
    public void setLvlupJpanel(LvlupJpanel lvlupJpanel) {
        this.lvlupJpanel = lvlupJpanel;
    }
}
