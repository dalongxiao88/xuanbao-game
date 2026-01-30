package org.come.Frame;

import org.come.until.Music;
import org.come.until.FormsManagement;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import com.tool.tcpimg.UIUtils;
import javax.swing.plaf.basic.BasicInternalFrameUI;
import javax.swing.BorderFactory;
import org.come.Jpanel.GoodsExchangeJpanel;
import java.awt.event.MouseListener;
import javax.swing.JInternalFrame;

public class GoodsExchangeJframe extends JInternalFrame implements MouseListener
{
    private GoodsExchangeJpanel goodsExchangeJpanel;
    private int first_x;
    private int first_y;
    
    public GoodsExchangeJpanel getGoodsExchangeJpanel() {
        return this.goodsExchangeJpanel;
    }
    
    public void setGoodsExchangeJpanel(GoodsExchangeJpanel goodsExchangeJpanel) {
        this.goodsExchangeJpanel = goodsExchangeJpanel;
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
    
    public GoodsExchangeJframe() {
        this.goodsExchangeJpanel = new GoodsExchangeJpanel();
        this.getContentPane().add(this.goodsExchangeJpanel);
        this.setBorder(BorderFactory.createEmptyBorder());
        ((BasicInternalFrameUI)this.getUI()).setNorthPane(null);
        this.setBounds(80, 50, 622, 486);
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
                if (GoodsExchangeJframe.this.isVisible()) {
                    int x = e.getX() - GoodsExchangeJframe.this.first_x;
                    int y = e.getY() - GoodsExchangeJframe.this.first_y;
                    GoodsExchangeJframe.this.setBounds(x + GoodsExchangeJframe.this.getX(), y + GoodsExchangeJframe.this.getY(), GoodsExchangeJframe.this.getWidth(), GoodsExchangeJframe.this.getHeight());
                }
            }
        });
    }
    
    public static GoodsExchangeJframe getGoodDetailedJframe() {
        return (GoodsExchangeJframe)FormsManagement.getInternalForm(90).getFrame();
    }
    
    @Override
    public void mouseClicked(MouseEvent e) {
    }
    
    @Override
    public void mousePressed(MouseEvent e) {
        Music.addyinxiao("关闭窗口.mp3");
        if (e.isMetaDown()) {
            FormsManagement.HideForm(90);
        }
        else {
            FormsManagement.Switchinglevel(90);
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
