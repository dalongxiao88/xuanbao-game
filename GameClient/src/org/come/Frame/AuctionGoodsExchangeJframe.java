package org.come.Frame;

import org.come.until.Music;
import org.come.until.FormsManagement;
import org.come.until.MessagrFlagUntil;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import com.tool.tcpimg.UIUtils;
import javax.swing.plaf.basic.BasicInternalFrameUI;
import javax.swing.BorderFactory;
import org.come.Jpanel.AuctionGoodsExchangeJpanel;
import java.awt.event.MouseListener;
import javax.swing.JInternalFrame;

public class AuctionGoodsExchangeJframe extends JInternalFrame implements MouseListener
{
    private AuctionGoodsExchangeJpanel auctionGoodsExchangeJpanel;
    private int first_x;
    private int first_y;
    
    public AuctionGoodsExchangeJpanel getAuctionGoodsExchangeJpanel() {
        return this.auctionGoodsExchangeJpanel;
    }
    
    public void setAuctionGoodsExchangeJpanel(AuctionGoodsExchangeJpanel auctionGoodsExchangeJpanel) {
        this.auctionGoodsExchangeJpanel = auctionGoodsExchangeJpanel;
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
    
    public AuctionGoodsExchangeJframe() {
        this.auctionGoodsExchangeJpanel = new AuctionGoodsExchangeJpanel();
        this.getContentPane().add(this.auctionGoodsExchangeJpanel);
        this.setBorder(BorderFactory.createEmptyBorder());
        ((BasicInternalFrameUI)this.getUI()).setNorthPane(null);
        this.setBounds(200, 100, 622, 486);
        this.setBackground(UIUtils.Color_BACK);
        this.pack();
        this.setVisible(false);
        this.setDefaultCloseOperation(3);
        this.addMouseListener(this);
        this.addMouseMotionListener(new MouseMotionListener() {
            @Override
            public void mouseMoved(MouseEvent e) {
                if (MessagrFlagUntil.ImgFlagImg.equals(MessagrFlagUntil.MOUSE13)) {
                    MessagrFlagUntil.setMouse(MessagrFlagUntil.MOUSE1);
                }
            }
            
            @Override
            public void mouseDragged(MouseEvent e) {
                if (AuctionGoodsExchangeJframe.this.isVisible()) {
                    int x = e.getX() - AuctionGoodsExchangeJframe.this.first_x;
                    int y = e.getY() - AuctionGoodsExchangeJframe.this.first_y;
                    AuctionGoodsExchangeJframe.this.setBounds(x + AuctionGoodsExchangeJframe.this.getX(), y + AuctionGoodsExchangeJframe.this.getY(), AuctionGoodsExchangeJframe.this.getWidth(), AuctionGoodsExchangeJframe.this.getHeight());
                }
            }
        });
    }
    
    public static AuctionGoodsExchangeJframe getGoodDetailedJframe() {
        return (AuctionGoodsExchangeJframe)FormsManagement.getInternalForm(900).getFrame();
    }
    
    @Override
    public void mouseClicked(MouseEvent e) {
    }
    
    @Override
    public void mousePressed(MouseEvent e) {
        FormsManagement.showForm(900);
        Music.addyinxiao("关闭窗口.mp3");
        if (e.isMetaDown()) {
            FormsManagement.HideForm(900);
            FormsManagement.disposeForm(900);
        }
        else {
            FormsManagement.Switchinglevel(900);
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
