package org.come.Frame;

import org.come.until.Music;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import com.tool.tcpimg.UIUtils;
import javax.swing.plaf.basic.BasicInternalFrameUI;
import javax.swing.BorderFactory;
import org.come.until.FormsManagement;
import org.come.Jpanel.TakeBackPawnGoodsJpanel;
import java.awt.event.MouseListener;
import javax.swing.JInternalFrame;

public class TakeBackPawnGoodsJframe extends JInternalFrame implements MouseListener
{
    private TakeBackPawnGoodsJpanel backPawnGoodsJpanel;
    private int first_x;
    private int first_y;
    
    public static TakeBackPawnGoodsJframe getTakeBackPawnGoodsJframe() {
        return (TakeBackPawnGoodsJframe)FormsManagement.getInternalForm(55).getFrame();
    }
    
    public TakeBackPawnGoodsJframe() throws Exception {
        this.backPawnGoodsJpanel = new TakeBackPawnGoodsJpanel();
        this.getContentPane().add(this.backPawnGoodsJpanel);
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
                if (TakeBackPawnGoodsJframe.this.isVisible()) {
                    int x = e.getX() - TakeBackPawnGoodsJframe.this.first_x;
                    int y = e.getY() - TakeBackPawnGoodsJframe.this.first_y;
                    TakeBackPawnGoodsJframe.this.setBounds(x + TakeBackPawnGoodsJframe.this.getX(), y + TakeBackPawnGoodsJframe.this.getY(), TakeBackPawnGoodsJframe.this.getWidth(), TakeBackPawnGoodsJframe.this.getHeight());
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
            FormsManagement.HideForm(55);
        }
        else {
            FormsManagement.Switchinglevel(55);
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
