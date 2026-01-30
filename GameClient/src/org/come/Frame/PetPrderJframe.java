package org.come.Frame;

import org.come.until.Music;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import com.tool.tcpimg.UIUtils;
import javax.swing.JComponent;
import javax.swing.plaf.basic.BasicInternalFrameUI;
import javax.swing.BorderFactory;
import org.come.until.FormsManagement;
import org.come.Jpanel.PetPrderJpanel;
import java.awt.event.MouseListener;
import javax.swing.JInternalFrame;

public class PetPrderJframe extends JInternalFrame implements MouseListener
{
    private PetPrderJpanel petPrderJpanel;
    private int first_x;
    private int first_y;
    
    public static PetPrderJframe getPetPrderJframe() {
        return (PetPrderJframe)FormsManagement.getInternalForm(621).getFrame();
    }
    
    public PetPrderJframe() {
        this.petPrderJpanel = new PetPrderJpanel();
        this.getContentPane().add(this.petPrderJpanel);
        this.setBorder(BorderFactory.createEmptyBorder());
        ((BasicInternalFrameUI)this.getUI()).setNorthPane((JComponent)null);
        this.setBounds(100, 50, 276, 441);
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
                if (PetPrderJframe.this.isVisible()) {
                    int x = e.getX() - PetPrderJframe.this.first_x;
                    int y = e.getY() - PetPrderJframe.this.first_y;
                    PetPrderJframe.this.setBounds(x + PetPrderJframe.this.getX(), y + PetPrderJframe.this.getY(), PetPrderJframe.this.getWidth(), PetPrderJframe.this.getHeight());
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
            FormsManagement.HideForm(621);
        }
        else {
            FormsManagement.Switchinglevel(621);
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
    
    public PetPrderJpanel getPetPrderJpanel() {
        return this.petPrderJpanel;
    }
    
    public void setPetPrderJpanel(PetPrderJpanel petPrderJpanel) {
        this.petPrderJpanel = petPrderJpanel;
    }
}
