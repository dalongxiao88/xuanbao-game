package org.come.Frame;

import org.come.until.Music;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import com.tool.tcpimg.UIUtils;
import javax.swing.plaf.basic.BasicInternalFrameUI;
import javax.swing.BorderFactory;
import org.come.until.FormsManagement;
import org.come.Jpanel.ChaojifeiListJpanel;
import java.awt.event.MouseListener;
import javax.swing.JInternalFrame;

public class ChaojifeiJframe extends JInternalFrame implements MouseListener
{
    private static ChaojifeiListJpanel chaojifeiListJpanel;
    private int first_x;
    private int first_y;
    
    public static ChaojifeiJframe getRankingListJframe() {
        return (ChaojifeiJframe)FormsManagement.getInternalForm(60).getFrame();
    }
    
    public ChaojifeiJframe() {
        ChaojifeiJframe.chaojifeiListJpanel = new ChaojifeiListJpanel();
        this.getContentPane().add(ChaojifeiJframe.chaojifeiListJpanel);
        this.setBorder(BorderFactory.createEmptyBorder());
        ((BasicInternalFrameUI)this.getUI()).setNorthPane(null);
        this.setBounds(50, 50, 800, 700);
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
                if (ChaojifeiJframe.this.isVisible()) {
                    int x = e.getX() - ChaojifeiJframe.this.first_x;
                    int y = e.getY() - ChaojifeiJframe.this.first_y;
                    ChaojifeiJframe.this.setBounds(x + ChaojifeiJframe.this.getX(), y + ChaojifeiJframe.this.getY(), ChaojifeiJframe.this.getWidth(), ChaojifeiJframe.this.getHeight());
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
            FormsManagement.HideForm(1101);
        }
        else {
            FormsManagement.Switchinglevel(1101);
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
    
    public static ChaojifeiListJpanel getChaojifeiListJpanel() {
        return ChaojifeiJframe.chaojifeiListJpanel;
    }
    
    public static void setChaojifeiListJpanel(ChaojifeiListJpanel chaojifeiListJpanel) {
        ChaojifeiJframe.chaojifeiListJpanel = chaojifeiListJpanel;
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
