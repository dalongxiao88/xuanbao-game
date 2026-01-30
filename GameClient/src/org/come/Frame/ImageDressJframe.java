package org.come.Frame;

import org.come.until.Music;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import com.tool.tcpimg.UIUtils;
import javax.swing.JComponent;
import javax.swing.plaf.basic.BasicInternalFrameUI;
import javax.swing.BorderFactory;
import org.come.until.FormsManagement;
import org.come.Jpanel.ImageDressJpanel;
import java.awt.event.MouseListener;
import javax.swing.JInternalFrame;

public class ImageDressJframe extends JInternalFrame implements MouseListener
{
    private ImageDressJpanel dressJpanel;
    private int first_x;
    private int first_y;
    
    public static ImageDressJframe getImageDressJframe() {
        return (ImageDressJframe)FormsManagement.getInternalForm(38).getFrame();
    }
    
    public ImageDressJframe() {
        this.dressJpanel = new ImageDressJpanel();
        this.getContentPane().add(this.dressJpanel);
        this.setBorder(BorderFactory.createEmptyBorder());
        ((BasicInternalFrameUI)this.getUI()).setNorthPane((JComponent)null);
        this.setBounds(100, 50, 596, 457);
        this.setBackground(UIUtils.Color_BACK);
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
                if (ImageDressJframe.this.isVisible()) {
                    int x = e.getX() - ImageDressJframe.this.first_x;
                    int y = e.getY() - ImageDressJframe.this.first_y;
                    ImageDressJframe.this.setBounds(x + ImageDressJframe.this.getX(), y + ImageDressJframe.this.getY(), ImageDressJframe.this.getWidth(), ImageDressJframe.this.getHeight());
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
            FormsManagement.HideForm(38);
        }
        else {
            FormsManagement.Switchinglevel(38);
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
    
    public ImageDressJpanel getDressJpanel() {
        return this.dressJpanel;
    }
    
    public void setDressJpanel(ImageDressJpanel dressJpanel) {
        this.dressJpanel = dressJpanel;
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
}
