package org.come.Frame;

import org.come.until.Music;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import com.tool.tcpimg.UIUtils;
import javax.swing.JComponent;
import javax.swing.plaf.basic.BasicInternalFrameUI;
import javax.swing.BorderFactory;
import org.come.until.FormsManagement;
import org.come.Jpanel.XinJianJpanel;
import java.awt.event.MouseListener;
import javax.swing.JInternalFrame;

public class XinJianJfram extends JInternalFrame implements MouseListener
{
    private int first_x;
    private int first_y;
    private XinJianJpanel xinJianJpanel;
    
    public static XinJianJfram getXinJianJfram() {
        return (XinJianJfram)FormsManagement.getInternalForm(704).getFrame();
    }
    
    public XinJianJfram() throws Exception {
        this.xinJianJpanel = new XinJianJpanel();
        this.getContentPane().add(this.xinJianJpanel);
        this.setBorder(BorderFactory.createEmptyBorder());
        ((BasicInternalFrameUI)this.getUI()).setNorthPane((JComponent)null);
        this.setBounds(480, 150, 320, 149);
        this.pack();
        this.setDefaultCloseOperation(3);
        this.setVisible(false);
        this.setBackground(UIUtils.Color_BACK);
        this.addMouseListener(this);
        this.addMouseMotionListener(new MouseMotionListener() {
            @Override
            public void mouseMoved(MouseEvent e) {
            }
            
            @Override
            public void mouseDragged(MouseEvent e) {
                if (XinJianJfram.this.isVisible()) {
                    int x = e.getX() - XinJianJfram.this.first_x;
                    int y = e.getY() - XinJianJfram.this.first_y;
                    XinJianJfram.this.setBounds(x + XinJianJfram.this.getX(), y + XinJianJfram.this.getY(), XinJianJfram.this.getWidth(), XinJianJfram.this.getHeight());
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
            FormsManagement.HideForm(704);
        }
        else {
            FormsManagement.Switchinglevel(21);
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
    
    public XinJianJpanel getXinJianJpanel() {
        return this.xinJianJpanel;
    }
    
    public void setXinJianJpanel(XinJianJpanel xinJianJpanel) {
        this.xinJianJpanel = xinJianJpanel;
    }
}
