package org.come.Frame;

import org.come.until.Music;
import com.tool.tcpimg.UIUtils;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import javax.swing.plaf.basic.BasicInternalFrameUI;
import javax.swing.BorderFactory;
import org.come.until.FormsManagement;
import org.come.Jpanel.FindDropJpanel;
import java.awt.event.MouseListener;
import javax.swing.JInternalFrame;

public class FindDropJfram extends JInternalFrame implements MouseListener
{
    private static final long serialVersionUID = 1L;
    private FindDropJpanel findDropJpanel;
    private int first_x;
    private int first_y;
    
    public FindDropJpanel getFindDropJpanel() {
        return this.findDropJpanel;
    }
    
    public void setFindDropJpanel(FindDropJpanel findDropJpanel) {
        this.findDropJpanel = findDropJpanel;
    }
    
    public static FindDropJfram getFindDropJfram() {
        return (FindDropJfram)FormsManagement.getInternalForm(600).getFrame();
    }
    
    public FindDropJfram() throws Exception {
        this.findDropJpanel = new FindDropJpanel();
        this.getContentPane().add(this.findDropJpanel);
        this.setBounds(300, 200, 296, 257);
        this.setBorder(BorderFactory.createEmptyBorder());
        ((BasicInternalFrameUI)this.getUI()).setNorthPane(null);
        this.addMouseListener(this);
        this.addMouseMotionListener(new MouseMotionListener() {
            @Override
            public void mouseMoved(MouseEvent e) {
            }
            
            @Override
            public void mouseDragged(MouseEvent e) {
                if (FindDropJfram.this.isVisible()) {
                    int x = e.getX() - FindDropJfram.this.first_x;
                    int y = e.getY() - FindDropJfram.this.first_y;
                    FindDropJfram.this.setBounds(x + FindDropJfram.this.getX(), y + FindDropJfram.this.getY(), FindDropJfram.this.getWidth(), FindDropJfram.this.getHeight());
                }
            }
        });
        this.setBackground(UIUtils.Color_BACK);
        this.pack();
        this.setVisible(false);
        this.setDefaultCloseOperation(2);
    }
    
    @Override
    public void mousePressed(MouseEvent e) {
        Music.addyinxiao("关闭窗口.mp3");
        if (e.isMetaDown()) {
            FormsManagement.HideForm(112);
        }
        this.first_x = e.getX();
        this.first_y = e.getY();
    }
    
    @Override
    public void mouseClicked(MouseEvent e) {
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
}
