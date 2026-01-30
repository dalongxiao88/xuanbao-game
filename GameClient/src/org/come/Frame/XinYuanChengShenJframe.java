package org.come.Frame;

import org.come.until.Music;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import com.tool.tcpimg.UIUtils;
import javax.swing.plaf.basic.BasicInternalFrameUI;
import javax.swing.BorderFactory;
import org.come.until.FormsManagement;
import org.come.Jpanel.XinYuanChengShengJpane;
import java.awt.event.MouseListener;
import javax.swing.JInternalFrame;

public class XinYuanChengShenJframe extends JInternalFrame implements MouseListener
{
    private int first_x;
    private int first_y;
    private XinYuanChengShengJpane XinYuanChengShengJpane;
    
    public static XinYuanChengShenJframe getXinYuanChengShengJfeame() {
        return (XinYuanChengShenJframe)FormsManagement.getframe(126);
    }
    
    public XinYuanChengShenJframe() throws Exception {
        this.XinYuanChengShengJpane = new XinYuanChengShengJpane();
        this.getContentPane().add(this.XinYuanChengShengJpane);
        this.setBorder(BorderFactory.createEmptyBorder());
        ((BasicInternalFrameUI)this.getUI()).setNorthPane(null);
        this.setBounds(0, 30, 676, 467);
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
                if (XinYuanChengShenJframe.this.isVisible()) {
                    int x = e.getX() - XinYuanChengShenJframe.this.first_x;
                    int y = e.getY() - XinYuanChengShenJframe.this.first_y;
                    XinYuanChengShenJframe.this.setBounds(x + XinYuanChengShenJframe.this.getX(), y + XinYuanChengShenJframe.this.getY(), XinYuanChengShenJframe.this.getWidth(), XinYuanChengShenJframe.this.getHeight());
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
            FormsManagement.HideForm(126);
        }
        else {
            FormsManagement.Switchinglevel(126);
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
    
    public XinYuanChengShengJpane getXinYuanChengShengJpane() {
        return this.XinYuanChengShengJpane;
    }
    
    public void setXinYuanChengShengJpane(XinYuanChengShengJpane xinYuanChengShengJpane) {
        this.XinYuanChengShengJpane = xinYuanChengShengJpane;
    }
}
