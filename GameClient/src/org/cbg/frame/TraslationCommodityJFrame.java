package org.cbg.frame;

import org.come.until.Music;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import com.tool.tcpimg.UIUtils;
import javax.swing.plaf.basic.BasicInternalFrameUI;
import javax.swing.BorderFactory;
import org.come.until.FormsManagement;
import org.cbg.panel.TraslationCommodityMainJPane;
import java.awt.event.MouseListener;
import javax.swing.JInternalFrame;

public class TraslationCommodityJFrame extends JInternalFrame implements MouseListener
{
    private int first_x;
    private int first_y;
    private TraslationCommodityMainJPane traslationCommodityMainJPane;
    
    public static TraslationCommodityJFrame getTraslationCommodityJFrame() {
        return (TraslationCommodityJFrame)FormsManagement.getInternalForm(79).getFrame();
    }
    
    public TraslationCommodityJFrame() {
        this.traslationCommodityMainJPane = new TraslationCommodityMainJPane();
        this.getContentPane().add(this.traslationCommodityMainJPane);
        this.setBorder(BorderFactory.createEmptyBorder());
        ((BasicInternalFrameUI)this.getUI()).setNorthPane(null);
        this.setBounds(400, 140, 286, 407);
        this.setVisible(false);
        this.setDefaultCloseOperation(3);
        this.pack();
        this.setBackground(UIUtils.Color_BACK);
        this.addMouseMotionListener(new MouseMotionListener() {
            @Override
            public void mouseMoved(MouseEvent e) {
            }
            
            @Override
            public void mouseDragged(MouseEvent e) {
                if (TraslationCommodityJFrame.this.isVisible()) {
                    int x = e.getX() - TraslationCommodityJFrame.this.first_x;
                    int y = e.getY() - TraslationCommodityJFrame.this.first_y;
                    TraslationCommodityJFrame.this.setBounds(x + TraslationCommodityJFrame.this.getX(), y + TraslationCommodityJFrame.this.getY(), TraslationCommodityJFrame.this.getWidth(), TraslationCommodityJFrame.this.getHeight());
                }
            }
        });
        this.addMouseListener(this);
    }
    
    @Override
    public void mouseClicked(MouseEvent e) {
    }
    
    @Override
    public void mousePressed(MouseEvent e) {
        Music.addyinxiao("关闭窗口.mp3");
        if (e.isMetaDown()) {
            FormsManagement.HideForm(79);
        }
        else {
            FormsManagement.Switchinglevel(79);
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
    
    public TraslationCommodityMainJPane getTraslationCommodityMainJPane() {
        return this.traslationCommodityMainJPane;
    }
    
    public void setTraslationCommodityMainJPane(TraslationCommodityMainJPane traslationCommodityMainJPane) {
        this.traslationCommodityMainJPane = traslationCommodityMainJPane;
    }
}
