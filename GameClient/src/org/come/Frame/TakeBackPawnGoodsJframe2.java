package org.come.Frame;

import org.come.until.Music;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import com.tool.tcpimg.UIUtils;
import javax.swing.plaf.basic.BasicInternalFrameUI;
import javax.swing.BorderFactory;
import org.come.until.FormsManagement;
import org.come.Jpanel.TakeBackPawnGoodsJpanel2;
import java.awt.event.MouseListener;
import javax.swing.JInternalFrame;

public class TakeBackPawnGoodsJframe2 extends JInternalFrame implements MouseListener
{
    private TakeBackPawnGoodsJpanel2 backPawnGoodsJpanel2;
    private int first_x;
    private int first_y;
    
    public static TakeBackPawnGoodsJframe2 getTakeBackPawnGoodsJframe2() {
        return (TakeBackPawnGoodsJframe2)FormsManagement.getInternalForm(1001).getFrame();
    }
    
    public TakeBackPawnGoodsJframe2() throws Exception {
        this.backPawnGoodsJpanel2 = new TakeBackPawnGoodsJpanel2();
        this.getContentPane().add(this.backPawnGoodsJpanel2);
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
                if (TakeBackPawnGoodsJframe2.this.isVisible()) {
                    int x = e.getX() - TakeBackPawnGoodsJframe2.this.first_x;
                    int y = e.getY() - TakeBackPawnGoodsJframe2.this.first_y;
                    TakeBackPawnGoodsJframe2.this.setBounds(x + TakeBackPawnGoodsJframe2.this.getX(), y + TakeBackPawnGoodsJframe2.this.getY(), TakeBackPawnGoodsJframe2.this.getWidth(), TakeBackPawnGoodsJframe2.this.getHeight());
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
            FormsManagement.HideForm(1001);
        }
        else {
            FormsManagement.Switchinglevel(1001);
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
