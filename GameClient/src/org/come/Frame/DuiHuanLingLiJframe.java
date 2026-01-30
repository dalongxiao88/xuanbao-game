package org.come.Frame;

import org.come.until.Music;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import javax.swing.JComponent;
import javax.swing.plaf.basic.BasicInternalFrameUI;
import com.tool.tcpimg.UIUtils;
import javax.swing.BorderFactory;
import org.come.model.Shop;
import org.come.until.UserMessUntil;
import java.util.List;
import org.come.until.FormsManagement;
import org.come.Jpanel.DuiHuanLingLiJpanel;
import java.awt.event.MouseListener;
import javax.swing.JInternalFrame;

public class DuiHuanLingLiJframe extends JInternalFrame implements MouseListener
{
    private DuiHuanLingLiJpanel duiHuanLingLiJpanel;
    private int first_x;
    private int first_y;
    
    public static DuiHuanLingLiJframe getDuiHuanLingLiJframe() {
        return (DuiHuanLingLiJframe)FormsManagement.getInternalForm(714).getFrame();
    }
    
    public static void showShop() {
        List<Shop> npcShop = (List<Shop>)UserMessUntil.getNpcshop().getNpcShopMap().get("900");
        getDuiHuanLingLiJframe().getDuiHuanLingLiJpanel().initShopTables(npcShop);
        FormsManagement.showForm(714);
    }
    
    public DuiHuanLingLiJframe() {
        this.duiHuanLingLiJpanel = new DuiHuanLingLiJpanel();
        this.getContentPane().add(this.duiHuanLingLiJpanel);
        this.setBorder(BorderFactory.createEmptyBorder());
        this.setBackground(UIUtils.Color_BACK);
        ((BasicInternalFrameUI)this.getUI()).setNorthPane((JComponent)null);
        this.setBounds(400, 50, 394, 526);
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
                if (DuiHuanLingLiJframe.this.isVisible()) {
                    int x = e.getX() - DuiHuanLingLiJframe.this.first_x;
                    int y = e.getY() - DuiHuanLingLiJframe.this.first_y;
                    DuiHuanLingLiJframe.this.setBounds(x + DuiHuanLingLiJframe.this.getX(), y + DuiHuanLingLiJframe.this.getY(), DuiHuanLingLiJframe.this.getWidth(), DuiHuanLingLiJframe.this.getHeight());
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
            FormsManagement.HideForm(714);
        }
        else {
            FormsManagement.Switchinglevel(714);
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
    
    public DuiHuanLingLiJpanel getDuiHuanLingLiJpanel() {
        return this.duiHuanLingLiJpanel;
    }
}
