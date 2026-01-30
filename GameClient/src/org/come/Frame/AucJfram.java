package org.come.Frame;

import org.come.until.Music;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import com.tool.tcpimg.UIUtils;
import javax.swing.JComponent;
import javax.swing.plaf.basic.BasicInternalFrameUI;
import javax.swing.BorderFactory;
import org.come.until.FormsManagement;
import org.come.Jpanel.AucJpanel;
import java.awt.event.MouseListener;
import javax.swing.JInternalFrame;

public class AucJfram extends JInternalFrame implements MouseListener
{
    private int first_x;
    private int first_y;
    private AucJpanel aucjpanel;
    
    public static AucJfram getAucJfram() {
        return (AucJfram)FormsManagement.getInternalForm(703).getFrame();
    }
    
    public AucJfram() throws Exception {
        this.aucjpanel = new AucJpanel();
        this.getContentPane().add(this.aucjpanel);
        this.setBorder(BorderFactory.createEmptyBorder());
        ((BasicInternalFrameUI)this.getUI()).setNorthPane((JComponent)null);
        this.setBounds(380, 150, 532, 149);
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
                if (AucJfram.this.isVisible()) {
                    int x = e.getX() - AucJfram.this.first_x;
                    int y = e.getY() - AucJfram.this.first_y;
                    AucJfram.this.setBounds(x + AucJfram.this.getX(), y + AucJfram.this.getY(), AucJfram.this.getWidth(), AucJfram.this.getHeight());
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
            FormsManagement.HideForm(703);
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
    
    public AucJpanel getAucjpanel() {
        return this.aucjpanel;
    }
    
    public void setAucjpanel(AucJpanel aucjpanel) {
        this.aucjpanel = aucjpanel;
    }
}
