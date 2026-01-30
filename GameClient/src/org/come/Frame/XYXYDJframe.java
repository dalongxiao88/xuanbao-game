package org.come.Frame;

import org.come.until.Music;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import com.tool.tcpimg.UIUtils;
import javax.swing.plaf.basic.BasicInternalFrameUI;
import javax.swing.BorderFactory;
import org.come.until.FormsManagement;
import org.come.Jpanel.XYXYDJpanel;
import java.awt.event.MouseListener;
import javax.swing.JInternalFrame;

public class XYXYDJframe extends JInternalFrame implements MouseListener
{
    private int first_x;
    private int first_y;
    private XYXYDJpanel xyxydJpanel;
    
    public static XYXYDJframe getXYXYDJfeame() {
        return (XYXYDJframe)FormsManagement.getframe(125);
    }
    
    public XYXYDJframe() throws Exception {
        this.xyxydJpanel = new XYXYDJpanel();
        this.getContentPane().add(this.xyxydJpanel);
        this.setBorder(BorderFactory.createEmptyBorder());
        ((BasicInternalFrameUI)this.getUI()).setNorthPane(null);
        this.setBounds(40, 100, 676, 467);
        this.pack();
        this.setBackground(UIUtils.Color_BACK);
        this.setDefaultCloseOperation(3);
        this.setVisible(false);
        this.addMouseListener(this);
        this.addMouseMotionListener(new MouseMotionListener() {
            @Override
            public void mouseMoved(MouseEvent e) {
            }
            
            @Override
            public void mouseDragged(MouseEvent e) {
                if (XYXYDJframe.this.isVisible()) {
                    int x = e.getX() - XYXYDJframe.this.first_x;
                    int y = e.getY() - XYXYDJframe.this.first_y;
                    XYXYDJframe.this.setBounds(x + XYXYDJframe.this.getX(), y + XYXYDJframe.this.getY(), XYXYDJframe.this.getWidth(), XYXYDJframe.this.getHeight());
                }
            }
        });
    }
    
    @Override
    public void mouseClicked(MouseEvent e) {
        System.out.print(e.getX() + "\n");
        System.out.print(e.getY());
    }
    
    @Override
    public void mousePressed(MouseEvent e) {
        Music.addyinxiao("关闭窗口.mp3");
        if (e.isMetaDown()) {
            FormsManagement.HideForm(125);
        }
        else {
            FormsManagement.Switchinglevel(125);
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
    
    public XYXYDJpanel getXyxydJpanel() {
        return this.xyxydJpanel;
    }
    
    public void setXyxydJpanel(XYXYDJpanel xyxydJpanel) {
        this.xyxydJpanel = xyxydJpanel;
    }
}
