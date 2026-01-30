package org.come.Frame;

import org.come.until.Music;
import com.tool.tcpimg.UIUtils;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import javax.swing.plaf.basic.BasicInternalFrameUI;
import javax.swing.BorderFactory;
import org.come.until.FormsManagement;
import org.come.Jpanel.QZQQJpanel;
import java.awt.event.MouseListener;
import javax.swing.JInternalFrame;

public class QZQQJfram extends JInternalFrame implements MouseListener
{
    private QZQQJpanel qzqqJpanel;
    private int first_x;
    private int first_y;
    
    public QZQQJpanel getQZQQJpanel() {
        return this.qzqqJpanel;
    }
    
    public void setQZQQJpanel(QZQQJpanel qzqqJpanel) {
        this.qzqqJpanel = qzqqJpanel;
    }
    
    public static QZQQJfram getqzcqJfram() {
        return (QZQQJfram)FormsManagement.getInternalForm(912).getFrame();
    }
    
    public QZQQJfram() throws Exception {
        this.qzqqJpanel = new QZQQJpanel();
        this.getContentPane().add(this.qzqqJpanel);
        this.setBounds(300, 200, 268, 309);
        this.setBorder(BorderFactory.createEmptyBorder());
        ((BasicInternalFrameUI)this.getUI()).setNorthPane(null);
        this.addMouseListener(this);
        this.addMouseMotionListener(new MouseMotionListener() {
            @Override
            public void mouseMoved(MouseEvent e) {
            }
            
            @Override
            public void mouseDragged(MouseEvent e) {
                if (QZQQJfram.this.isVisible()) {
                    int x = e.getX() - QZQQJfram.this.first_x;
                    int y = e.getY() - QZQQJfram.this.first_y;
                    QZQQJfram.this.setBounds(x + QZQQJfram.this.getX(), y + QZQQJfram.this.getY(), QZQQJfram.this.getWidth(), QZQQJfram.this.getHeight());
                }
            }
        });
        this.setBackground(UIUtils.Color_BACK);
        this.pack();
        this.setVisible(false);
        this.setDefaultCloseOperation(2);
    }
    
    @Override
    public void mousePressed(MouseEvent e) {
        Music.addyinxiao("关闭窗口.mp3");
        if (e.isMetaDown()) {
            FormsManagement.HideForm(912);
        }
        this.first_x = e.getX();
        this.first_y = e.getY();
    }
    
    @Override
    public void mouseClicked(MouseEvent e) {
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
