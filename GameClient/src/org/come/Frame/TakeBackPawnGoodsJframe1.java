package org.come.Frame;

import org.come.until.Music;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import com.tool.tcpimg.UIUtils;
import javax.swing.plaf.basic.BasicInternalFrameUI;
import javax.swing.BorderFactory;
import org.come.until.FormsManagement;
import org.come.Jpanel.TakeBackPawnGoodsJpanel1;
import java.awt.event.MouseListener;
import javax.swing.JInternalFrame;

public class TakeBackPawnGoodsJframe1 extends JInternalFrame implements MouseListener
{
    private TakeBackPawnGoodsJpanel1 backPawnGoodsJpanel1;
    private int first_x;
    private int first_y;
    
    public static TakeBackPawnGoodsJframe1 getTakeBackPawnGoodsJframe1() {
        return (TakeBackPawnGoodsJframe1)FormsManagement.getInternalForm(1000).getFrame();
    }
    
    public TakeBackPawnGoodsJframe1() throws Exception {
        this.backPawnGoodsJpanel1 = new TakeBackPawnGoodsJpanel1();
        this.getContentPane().add(this.backPawnGoodsJpanel1);
        this.setBorder(BorderFactory.createEmptyBorder());
        ((BasicInternalFrameUI)this.getUI()).setNorthPane(null);
        this.setBounds(445, 65, 355, 436);
        this.pack();
        this.setVisible(false);
        this.setDefaultCloseOperation(3);
        this.setBackground(UIUtils.Color_BACK);
        this.addMouseListener(this);
        this.addMouseMotionListener(new MouseMotionListener() {
            @Override
            public void mouseMoved(MouseEvent e) {
            }
            
            @Override
            public void mouseDragged(MouseEvent e) {
                if (TakeBackPawnGoodsJframe1.this.isVisible()) {
                    int x = e.getX() - TakeBackPawnGoodsJframe1.this.first_x;
                    int y = e.getY() - TakeBackPawnGoodsJframe1.this.first_y;
                    TakeBackPawnGoodsJframe1.this.setBounds(x + TakeBackPawnGoodsJframe1.this.getX(), y + TakeBackPawnGoodsJframe1.this.getY(), TakeBackPawnGoodsJframe1.this.getWidth(), TakeBackPawnGoodsJframe1.this.getHeight());
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
            FormsManagement.HideForm(1000);
        }
        else {
            FormsManagement.Switchinglevel(1000);
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
