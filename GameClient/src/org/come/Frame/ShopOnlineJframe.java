package org.come.Frame;

import org.come.until.Music;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import javax.swing.JComponent;
import javax.swing.plaf.basic.BasicInternalFrameUI;
import javax.swing.BorderFactory;
import org.come.until.FormsManagement;
import org.come.Jpanel.ShopOnlineJpanel;
import java.awt.event.MouseListener;
import javax.swing.JInternalFrame;

public class ShopOnlineJframe extends JInternalFrame implements MouseListener
{
    private ShopOnlineJpanel shopOnlinejpanel;
    private int first_x;
    private int first_y;
    
    public ShopOnlineJpanel getShopOnlinejpanel() {
        return this.shopOnlinejpanel;
    }
    
    public void setShopOnlinejpanel(ShopOnlineJpanel shopOnlinejpanel) {
        this.shopOnlinejpanel = shopOnlinejpanel;
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
    
    public static ShopOnlineJframe getshopOnlineJfram() {
        return (ShopOnlineJframe)FormsManagement.getInternalForm(44).getFrame();
    }
    
    public ShopOnlineJframe() throws Exception {
        this.shopOnlinejpanel = new ShopOnlineJpanel();
        this.getContentPane().add(this.shopOnlinejpanel);
        this.setBounds(300, 85, 522, 483);
        this.setBorder(BorderFactory.createEmptyBorder());
        ((BasicInternalFrameUI)this.getUI()).setNorthPane((JComponent)null);
        this.setFocusable(true);
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
                if (ShopOnlineJframe.getshopOnlineJfram().isVisible()) {
                    int x = e.getX() - ShopOnlineJframe.this.first_x;
                    int y = e.getY() - ShopOnlineJframe.this.first_y;
                    ShopOnlineJframe.getshopOnlineJfram().setBounds(x + ShopOnlineJframe.getshopOnlineJfram().getX(), y + ShopOnlineJframe.getshopOnlineJfram().getY(), ShopOnlineJframe.getshopOnlineJfram().getWidth(), ShopOnlineJframe.getshopOnlineJfram().getHeight());
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
            FormsManagement.HideForm(44);
        }
        else {
            FormsManagement.Switchinglevel(44);
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
}
