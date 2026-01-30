package org.come.Frame;

import org.come.until.Music;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import com.tool.tcpimg.UIUtils;
import javax.swing.JComponent;
import javax.swing.plaf.basic.BasicInternalFrameUI;
import javax.swing.BorderFactory;
import org.come.until.FormsManagement;
import org.come.Jpanel.ShoppingBuyJpanel;
import java.awt.event.MouseListener;
import javax.swing.JInternalFrame;

public class ShoppingBuyJframe extends JInternalFrame implements MouseListener
{
    private ShoppingBuyJpanel shoppingBuyJpanel;
    private int first_x;
    private int first_y;
    
    public static ShoppingBuyJframe getShoppingBuyJframe() {
        return (ShoppingBuyJframe)FormsManagement.getInternalForm(23).getFrame();
    }
    
    public ShoppingBuyJframe() throws Exception {
        this.shoppingBuyJpanel = new ShoppingBuyJpanel();
        this.getContentPane().add(this.shoppingBuyJpanel);
        this.setBorder(BorderFactory.createEmptyBorder());
        ((BasicInternalFrameUI)this.getUI()).setNorthPane((JComponent)null);
        this.setBounds(120, 100, 550, 180);
        this.pack();
        this.setVisible(false);
        this.setBackground(UIUtils.Color_BACK);
        this.setDefaultCloseOperation(3);
        this.addMouseListener(this);
        this.addMouseMotionListener(new MouseMotionListener() {
            @Override
            public void mouseMoved(MouseEvent e) {
            }
            
            @Override
            public void mouseDragged(MouseEvent e) {
                if (ShoppingBuyJframe.this.isVisible()) {
                    int x = e.getX() - ShoppingBuyJframe.this.first_x;
                    int y = e.getY() - ShoppingBuyJframe.this.first_y;
                    ShoppingBuyJframe.this.setBounds(x + ShoppingBuyJframe.this.getX(), y + ShoppingBuyJframe.this.getY(), ShoppingBuyJframe.this.getWidth(), ShoppingBuyJframe.this.getHeight());
                }
            }
        });
    }
    
    public static void main(String[] args) throws Exception {
        new ShoppingBuyJframe();
    }
    
    @Override
    public void mouseClicked(MouseEvent e) {
    }
    
    @Override
    public void mousePressed(MouseEvent e) {
        Music.addyinxiao("关闭窗口.mp3");
        if (e.isMetaDown()) {
            FormsManagement.HideForm(23);
        }
        else {
            FormsManagement.Switchinglevel(23);
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
    
    public ShoppingBuyJpanel getShoppingBuyJpanel() {
        return this.shoppingBuyJpanel;
    }
    
    public void setShoppingBuyJpanel(ShoppingBuyJpanel shoppingBuyJpanel) {
        this.shoppingBuyJpanel = shoppingBuyJpanel;
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
